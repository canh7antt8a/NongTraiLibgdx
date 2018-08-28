package vn.sunnet.game.stage;

import vn.sunnet.game.farm.assets.Assets;
import vn.sunnet.game.farm.assets.Audio;
import vn.sunnet.game.farm.assets.Language;
import vn.sunnet.game.farm.nature.F;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GuideLine {

	private Texture background[];
	private Button close, prev, next;
	private Stage stage;
	private boolean gc = false;
	private int index = 0;

	private BitmapFont fontTitle;
	private BitmapFont font;
	public GuideLine(Stage sta) {
		stage = new Stage();
		stage.setViewport(sta.getViewport());
		background = new Texture[2];
		for(int i = 0; i < 2; i++) {
			background[i] = new Texture(Gdx.files.internal("data/guide/huong-dan-" + i + ".png"));
		}

		fontTitle = new BitmapFont(Gdx.files.internal(F.strFontNormal));
        fontTitle.setColor(Color.RED);
		fontTitle.getData().setScale(1.75f);

		font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
		font.setColor(Color.WHITE);
		font.getData().setScale(.9f);
		
		next = createButton("data/texture/mui-ten-phai-0.png");
        next.setPosition(1040, 335);
		prev = createButton("data/texture/mui-ten-trai-0.png");
        prev.setPosition(240, 335);

        close = createButton("data/texture/close.png");
        close.setPosition(1084, 614);

		stage.addActor(next);
		stage.addActor(prev);
		stage.addActor(close);
		Gdx.input.setInputProcessor(stage);
	}
	
	public Button createButton(String path) {
		Texture texture = new Texture(Gdx.files.internal(path));
		TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		return new Button(new TextureRegionDrawable(region[0]), new TextureRegionDrawable(region[1]), 
				new TextureRegionDrawable(region[1]));
	}
	
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(background[index], 140, 16);

		fontTitle.draw(batch, Language.General.HUONG_DAN.getStr(), 580, 700);
		if(index == 0){
//			group1.setVisible(true);
//			group2.setVisible(false);

			guide1(batch);
		}else{
//			group1.setVisible(false);
//			group2.setVisible(true);

			guide2(batch);
		}
		batch.end();
		
		stage.draw();
		updateButton();
	}

	public void guide1(SpriteBatch batch){
        font.draw(batch, Language.General.HUONG_DAN_1.getStr(), 440, 610);
        font.draw(batch, Language.General.HUONG_DAN_2.getStr(), 440, 530);
        font.draw(batch, Language.General.HUONG_DAN_3.getStr(), 440, 430);
        font.draw(batch, Language.General.HUONG_DAN_4.getStr(), 500, 340);
        font.draw(batch, Language.General.HUONG_DAN_5.getStr(), 500, 270);
        font.draw(batch, Language.General.HUONG_DAN_6.getStr(), 500, 170);
	}

	public void guide2(SpriteBatch batch){
        font.draw(batch, Language.General.HUONG_DAN_7.getStr(), 470, 600);
        font.draw(batch, Language.General.HUONG_DAN_8.getStr(), 470, 450);
        font.draw(batch, Language.General.HUONG_DAN_9.getStr(), 580, 340);
        font.draw(batch, Language.General.HUONG_DAN_10.getStr(), 410, 180);
	}
	
	public void updateButton() {		
		if(next.isChecked()) {
			next.setChecked(false);
			Audio.btnClick.play(Audio.soundVolume);
			if(index < 1) {
				index ++;

                font.setColor(Color.BLACK);
			}
		}
		
		if(prev.isChecked()) {
			prev.setChecked(false);
			Audio.btnClick.play(Audio.soundVolume);
			if(index > 0) {
				index --;
                font.setColor(Color.WHITE);
			}
		}
		
		if(close.isChecked() || Gdx.input.isKeyPressed(Keys.BACK)) {
			close.setChecked(false);
			Audio.btnClick.play(Audio.soundVolume);
			gc = true;			
		}
	}
	
	public boolean gc() {
		return gc;
	}
}