package vn.sunnet.game.farm.screen;

import vn.sunnet.game.farm.assets.Assets;
import vn.sunnet.game.farm.assets.Audio;
import vn.sunnet.game.farm.assets.Data;
import vn.sunnet.game.farm.main.Farm;
import vn.sunnet.game.farm.nature.F;
import vn.sunnet.game.stage.GuideLine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuScreen extends BaseScreen {

	private Farm farm;
//	private OrthographicCamera cam;
//	private SpriteBatch batch;//cai nay ko a
	private Texture background;
	private Button btn_info, btn_guide, btn_download, btn_play, btn_setting, btn_exit;
	private String pathTexture = "data/menu/";
	private Setting settingScreen;
	private GuideLine tutorialScreen;
	private boolean isSetting = false, isGuide = false;
	public static boolean isShowAd;
	
	public MenuScreen(Farm farm) {
		super();
		this.farm = farm;
//		cam = new OrthographicCamera(F.viewportWidth, F.viewportHeight);
//		cam.position.set(F.viewportWidth/2, F.viewportHeight/2, 0);
//		viewport = new StretchViewport(F.viewportWidth, F.viewportHeight, cam);
//		batch = new SpriteBatch();
//		stage = new Stage();
//		stage.setViewport(viewport);

		String demo = F.CCFS("app_name");

		System.out.println("===========>   demo:   " + demo);
		
		btn_info = createButton("thong-tin.png");
		btn_info.setPosition(105, 190);

		btn_guide = createButton("huong-dan.png");
		btn_guide.setPosition(290, 350);

		btn_download = createButton("download.png");
		btn_download.setPosition(526, 410);

		btn_play = createButton("choi.png");
		btn_play.setPosition(680, 450);

		btn_setting = createButton("cai-dat.png");
		btn_setting.setPosition(1030, 270);

		btn_exit = createButton("thoat.png");
		btn_exit.setPosition(1150, 40);
		
		stage.addActor(btn_info);
		stage.addActor(btn_guide);
		stage.addActor(btn_download);
		stage.addActor(btn_play);
		stage.addActor(btn_setting);
		stage.addActor(btn_exit);

		
		if(Data.getisMusic())	Audio.music1.play();
		if(Data.getisSound())	Audio.soundVolume = 1;
		else 	
			Audio.soundVolume = 0;
		
//		background = Assets.manager.get(pathTexture + "menu.jpg", Texture.class);//new Texture(Assets.resolver.resolve(pathTexture + "menu.jpg"));
		background = new Texture(Gdx.files.internal(pathTexture + "menu.jpg"));
		isShowAd = Data.showAdview();
		if(isShowAd) {
			Farm.payment.showAdview();
		}
		
		F.money = Data.getMoney();
	}
	
	public Button createButton(String name) {
//		Texture texture = Assets.manager.get(pathTexture + name, Texture.class);
		Texture texture = new Texture(Gdx.files.internal(pathTexture + name));
		TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		return new Button(new TextureRegionDrawable(region[0]), new TextureRegionDrawable(region[1]),
				new TextureRegionDrawable(region[0]));
	}

	@Override
	protected void draw(float delta){
		batch.draw(background, 0, 0);
	}

	@Override
	protected  void update(float delta){
		updateButton();
		if(isSetting) {
			settingScreen.render(batch);
			if(settingScreen.gc()) {
				isSetting = false;
				resume_();
				Gdx.input.setInputProcessor(stage);
			}
		}

		if(isGuide) {
			tutorialScreen.render(batch);
			if(tutorialScreen.gc()) {
				isGuide = false;
				resume_();
				Gdx.input.setInputProcessor(stage);
			}
		}
	}

	public void updateButton() {
		if(btn_info.isChecked()) {
			clicked_(btn_info);
			Farm.payment.onInfo();
		}
		
		if(btn_guide.isChecked()) {
			clicked_(btn_guide);
			tutorialScreen = new GuideLine(stage);
			isGuide = true;
			pause_();
		}
		
		if(btn_download.isChecked()) {
			clicked_(btn_download);
			Farm.payment.onDownload();
		}
		
		if(btn_play.isChecked()) {
			btn_play.setChecked(false);
			Audio.btnClick.play(Audio.soundVolume);
			Assets.load();
			if(isShowAd) {
				Farm.payment.hideAdview();
			}
			farm.setScreen(new LoadingScreen(farm));
		}
		
		if(btn_setting.isChecked()) {
			clicked_(btn_setting);
			settingScreen = new Setting(stage);
			isSetting = true;
			pause_();
		}
		
		if(btn_exit.isChecked() || Gdx.input.isKeyPressed(Keys.BACK)) {
			clicked_(btn_exit);
			if(isShowAd) {
				Farm.payment.onExit();
			} else {
				Farm.payment.onQuit();
			}
		}
	}	
	
	public void pause_() {
		if(Data.getisMusic()) {
			Audio.music1.stop();
		}
	}
	
	public void resume_() {
		if(Data.getisMusic()) {	
			Audio.music1.play();
		}
	}
	
	public void clicked_(Button btn) {
		btn.setChecked(false);
		Audio.btnClick.play(Audio.soundVolume);
	}
}