package com.leaptechjsc.game.happyfarm.screen;

import com.leaptechjsc.game.happyfarm.assets.Audio;
import com.leaptechjsc.game.happyfarm.assets.Data;
import com.leaptechjsc.game.happyfarm.assets.Language;
import com.leaptechjsc.game.happyfarm.nature.F;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Setting {
	private Texture background;
	private Button btnSound, btnMusic, btnclose;
	private Stage stage;
	private boolean isSound, isMusic, gc = false;
	private BitmapFont fontTitle;
	private BitmapFont font;
	
	public Setting(Stage sta) {
		stage = new Stage();
//		background = Assets.manager.get("data/menu/cai-dat-2.png", Texture.class);//new Texture(Assets.resolver.resolve("data/menu/cai-dat-2.png"));
		background = new Texture(Gdx.files.internal("data/menu/cai-dat-2.png"));

		fontTitle = new BitmapFont(Gdx.files.internal(F.strFontNormal));
		fontTitle.setColor(Color.RED);
		fontTitle.getData().setScale(1.75f);

		font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
		font.setColor(Color.BLACK);
		font.getData().setScale(1.2f);

        isMusic = Data.getisMusic();
		isSound = Data.getisSound();
		
		btnSound = createButton("data/menu/am-thanh.png");
		btnSound.setChecked(!isSound);
		btnSound.setPosition(440, 230);
		btnMusic = createButton("data/menu/nhac-nen.png");
		btnMusic.setChecked(!isMusic);
		btnMusic.setPosition(660, 340);
		
		btnclose = createButton("data/texture/close.png");
		btnclose.setPosition(1090, 575);



        stage.setViewport(sta.getViewport());

		stage.addActor(btnSound);
		stage.addActor(btnMusic);
		stage.addActor(btnclose);
		Gdx.input.setInputProcessor(stage);

	}
	
	public Button createButton(String path) {
//		Texture texture =  Assets.manager.get(path, Texture.class);
		Texture texture = new Texture(Gdx.files.internal(path));
		TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		return new Button(new TextureRegionDrawable(region[0]), new TextureRegionDrawable(region[1]), 
				new TextureRegionDrawable(region[1]));
	}
	
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(background, 133, 83);
		fontTitle.draw(batch, Language.General.SETTING.getStr(), 550, 600);
        font.draw(batch, Language.General.MUSIC.getStr(), 220, 415);
        font.draw(batch, Language.General.SOUND.getStr(), 580, 310);
		batch.end();
		stage.draw();
		updateButton();
	}
	
	public void updateButton() {		
		if(btnclose.isChecked() || Gdx.input.isKeyPressed(Keys.BACK)) {
			btnclose.setChecked(false);
			gc = true;
			Data.setisMusic(!btnMusic.isChecked());
			Data.setisSound(!btnSound.isChecked());
			if(btnSound.isChecked())		Audio.soundVolume = 0;
			else	Audio.soundVolume = 1;
		}
	}
	
	public boolean gc() {
		return gc;
	}
}