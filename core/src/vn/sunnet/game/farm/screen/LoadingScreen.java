package vn.sunnet.game.farm.screen;

import vn.sunnet.game.farm.assets.Assets;
import vn.sunnet.game.farm.assets.Audio;
import vn.sunnet.game.farm.main.Farm;
import vn.sunnet.game.farm.nature.F;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class LoadingScreen extends BaseScreen{
	private BitmapFont bmpfont;
	private Farm farm;
	private String path = "data/menu/";
	private Texture background, loading;
	private TextureRegion[] tregion;
	private Animation animation;
	private Vector2 origin, pos1, pos2;
	private int num = 0, distance = 490, step = 40, percent;
	private float delx, progress, stateTime = 0, frameDuration = 0.12f;
	
	public LoadingScreen(Farm farm) {
		super();
		this.farm = farm;
		
		bmpfont = new BitmapFont(Gdx.files.internal(F.strFontNormal), false);
		bmpfont.getData().setScale(0.7f);

		background = new Texture(Gdx.files.internal(path + "loading-1.jpg"));
		loading = new Texture(Gdx.files.internal(path + "loading-2.png"));
		
		tregion = TextureRegion.split(loading, loading.getWidth()/4, loading.getHeight())[0];
		animation = new Animation(frameDuration, tregion);
//		loading = Assets.manager.get(path + "loading-3.png", Texture.class);
		loading = new Texture(Gdx.files.internal(path + "loading-3.png"));
		
		origin = new Vector2(650, 0);
		pos1 = new Vector2(650, 95);
		pos2 = new Vector2(0, 100);
	}

	@Override
	protected void draw(float delta){
		batch.draw(background, 0, 0);
		bmpfont.draw(batch, "" + percent + "%", 900, 100);
		batch.draw(((TextureRegion) animation.getKeyFrame(stateTime, false)), pos1.x, pos1.y);
		for(int i = 0; i < num; i++) {
			pos2.x = origin.x + i * step;
			batch.draw(loading, pos2.x, pos2.y);
		}
	}

	@Override
	protected  void update(float delta) {
		stateTime += Gdx.graphics.getDeltaTime();
		if(stateTime > frameDuration * 4)
			stateTime = 0;
		progress = Assets.manager.getProgress();
		percent = (int) (progress * 100);
		delx = distance * progress;
		num = (int) (delx/step);
		pos1.x = origin.x + delx - 50;

		if (Assets.manager.update()) {
			F.load();
			Farm.payment.Tracker(0, 0, "");
			Audio.music1.stop();
			farm.setScreen(new PlayScreen(farm));
		}
	}
}