package vn.sunnet.game.stage;

import vn.sunnet.game.farm.assets.Assets;
import vn.sunnet.game.farm.assets.Audio;
import vn.sunnet.game.farm.nature.F;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GuideLine {

	private Texture background[];
	private Button close, prev, next;
	private Stage stage;
	private boolean gc = false;
	private int index = 0;
	
	public GuideLine(Stage sta) {
		stage = new Stage();
		stage.setViewport(sta.getViewport());
		background = new Texture[2];
		for(int i = 0; i < 2; i++) {
//			background[i] = Assets.manager.get("data/guide/huong-dan-" + i + ".png", Texture.class);//new Texture(Assets.resolver.resolve("data/guide/huong-dan-" + i + ".png"));
			background[i] = new Texture(Gdx.files.internal("data/guide/huong-dan-" + i + ".png"));
		}
		
		next = createButton("data/texture/mui-ten-phai-0.png");
//		next.setPosition(770, 280);
        next.setPosition(1040, 335);
		prev = createButton("data/texture/mui-ten-trai-0.png");
//		prev.setPosition(180, 280);
        prev.setPosition(240, 335);

        close = createButton("data/texture/close.png");
//		close.setPosition(780, 430);
        close.setPosition(1084, 614);

		stage.addActor(next);
		stage.addActor(prev);
		stage.addActor(close);
		Gdx.input.setInputProcessor(stage);
	}
	
	public Button createButton(String path) {
//		Texture texture = Assets.manager.get(path, Texture.class);
		Texture texture = new Texture(Gdx.files.internal(path));
		TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		return new Button(new TextureRegionDrawable(region[0]), new TextureRegionDrawable(region[1]), 
				new TextureRegionDrawable(region[1]));
	}
	
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(background[index], 140, 16);
		batch.end();
		
		stage.draw();
		updateButton();
	}
	
	public void updateButton() {		
		if(next.isChecked()) {
			next.setChecked(false);
			Audio.btnClick.play(Audio.soundVolume);
			if(index < 1) {
				index ++;
//				next.setDisabled(true);
//				prev.setDisabled(false);
			}
		}
		
		if(prev.isChecked()) {
			prev.setChecked(false);
			Audio.btnClick.play(Audio.soundVolume);
			if(index > 0) {
				index --;

//				next.setDisabled(false);
//				prev.setDisabled(true);
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