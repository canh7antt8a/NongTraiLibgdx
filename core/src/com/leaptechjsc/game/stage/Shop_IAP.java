package com.leaptechjsc.game.stage;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.leaptechjsc.game.happyfarm.Actor.MyButton;
import com.leaptechjsc.game.happyfarm.nature.F;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Shop_IAP{
	private Stage stage;

	private boolean gc = false;

	public Shop_IAP(Stage sta) {
		this.stage = new Stage();
		stage.setViewport(sta.getViewport());
		Gdx.input.setInputProcessor(this.stage);

		Image bkgggg = new Image(new TextureRegion(new Texture(Gdx.files.internal("data/shopiap/bg_popup.png"))));
		stage.addActor(bkgggg);
		bkgggg.setPosition(640 - 400, 320 - 213);

		BitmapFont font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
		Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);

		Label labelTitle = new Label("Shop IAP", lblStyle);
		stage.addActor(labelTitle);
		labelTitle.setPosition(640 - labelTitle.getWidth()*.5f, 320 + 200 + 50);
		Texture texture = new Texture(Gdx.files.internal("data/shopiap/btn_close_popup.png"));
		TextureRegion region = new TextureRegion(texture);
		MyButton btnClose = new MyButton(region) {
			@Override
			public void precessClicked() {
				gc = true;
			}
		};

		stage.addActor(btnClose);
		btnClose.setPosition(1040 - 80, 570 - 80 + 50);

		final Table scrollTable = new Table();
		for(int i =0 ; i < 20; i++){
			MyButton bkgITem = new MyButton(new TextureRegion(new Texture(Gdx.files.internal("data/shopiap/lineIAP.png"))), (i + 1)*1000 + "") {
				@Override
				public void precessClicked() {

				}
			};

			int ide = i + 1;
			if(ide > 7){
				ide = 7;
			}

			Image iconChip = new Image(new TextureRegion(new Texture(Gdx.files.internal("data/shopiap/chip" + ide + ".png"))));
			bkgITem.addChild(iconChip);
			iconChip.setPosition(25, bkgITem.getHeight()*.25f);

			Image iconBtn = new Image(new TextureRegion(new Texture(Gdx.files.internal("data/shopiap/eventButton.png"))));
			bkgITem.addChild(iconBtn);
			iconBtn.setPosition( bkgITem.getWidth()*.75f, bkgITem.getHeight()*.25f);

			Label labelTemp = new Label("200", lblStyle);
			labelTemp.setAlignment(Align.center);
			labelTemp.setTouchable(Touchable.disabled);
			bkgITem.addChild(labelTemp);
			labelTemp.setPosition(iconBtn.getX() + iconBtn.getWidth()*.25f, iconBtn.getY());

			scrollTable.add(bkgITem);
			scrollTable.row();
		}

		final ScrollPane scroller = new ScrollPane(scrollTable);

		final Table table = new Table();
		table.setSize(800, 400);
		table.add(scroller).fill().expand();
		this.stage.addActor(table);
		table.setPosition(640 - 400, 320 - 200);

		Gdx.input.setInputProcessor(stage);
	}

	public void render(SpriteBatch batch) {
		batch.begin();
		batch.end();
		this.stage.act();
		this.stage.draw();
		updateButton();
	}

	public void updateButton() {
		if(Gdx.input.isKeyPressed(Keys.BACK)) {
			gc = true;
		}
	}

	public boolean gc() {
		return gc;
	}
}