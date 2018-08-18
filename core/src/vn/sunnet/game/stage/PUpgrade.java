package vn.sunnet.game.stage;

import java.util.Random;

import vn.sunnet.game.farm.assets.Assets;
import vn.sunnet.game.farm.assets.Audio;
import vn.sunnet.game.farm.assets.Data;
import vn.sunnet.game.farm.main.Farm;
import vn.sunnet.game.farm.nature.F;
import vn.sunnet.game.farm.object.Plot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class PUpgrade {

	private static final int NORMAL = 0, VIEW_INFO = 1, UPGRADE = 2;
	private String wsname[] = {"Đất sỏi", "Bụi cây xanh", "Bụi cây trắng", "Bụi cây vàng", "Bụi cây đỏ",
			"Bụi cây tím"};
	private final int ccost[] = {10000, 30000, 60000, 100000, 200000};
	private final int xcost[] = {1000, 3000, 6000, 10000, 20000};
	private final int array_success[] = {80, 60, 40, 20, 10};
	private final int array_lvfail[] = {45, 40, 35, 30, 25};
	private final int array_exceed[] = {15, 5, 1, 0, 0};
	
	private Stage stage, i_stage, u_stage;
	private Texture bg, ibg, ubg, wayside[];
	private Button btn_upgrade_1, btn_coins, btn_money;
	private Button close, btn_info, checkbox1, checkbox2;
	private BitmapFont font1, font2;
	private Vector2 pos;
	private Plot plot;
	private int state, level, ensure, lrock;
	private int success, lvfail, exceed;
	private Random random;
	private Rectangle bound;
	private boolean gc = false;
	
	public PUpgrade(int id, Plot plot, Stage sta) {
		this.plot = plot;
		random = new Random();
		success = random.nextInt(100);
		lvfail = random.nextInt(100);
		exceed = random.nextInt(100);
		
		ensure = Data.loadnofEPaper();
		lrock = Data.loadnofLRock();
		state = NORMAL;
		level = plot.level;
		
		stage = new Stage();
		i_stage = new Stage();
		u_stage = new Stage();

		stage.setViewport(sta.getViewport());
		i_stage.setViewport(sta.getViewport());
		i_stage.setViewport(sta.getViewport());
		
		wayside = new Texture[6];
		for(int i = 0; i < 6; i++)
			wayside[i] = Assets.manager.get("data/texture/dat-nc-" + i + ".png", Texture.class);
		
		font1 = Assets.manager.get("data/font/cua-hang.fnt", BitmapFont.class);
		font2 = Assets.manager.get("data/font/xu.fnt", BitmapFont.class);
		
		pos = new Vector2(75 + 210 * ((id%12)%4), 371 - 153 * ((id%12)/4));
		bound = new Rectangle(pos.x, pos.y - 20, 179, 183);
		btn_info = createButton("thong-tin.png");
		
		btn_coins = createButton("nang-cap-2.png");
		
		btn_money = createButton("nang-cap-2.png");
		btn_money.setPosition(840, 100);
		close = createButton("close.png");
		checkbox1 = createButton("check-box.png");
		checkbox1.setPosition(470, 366);
		checkbox2 = createButton("check-box.png");
		checkbox2.setPosition(470, 260);
		
		if(lrock <= 0)	checkbox2.setDisabled(true);
		if(ensure <= 0)  checkbox1.setDisabled(true);
		
		stage.addActor(btn_info);
		if(level <= 4) {
			btn_upgrade_1 = createButton("nang-cap.png");
			btn_upgrade_1.setPosition(pos.x + 40, pos.y + 85);
			btn_info.setPosition(pos.x + 40, pos.y + 20);
			stage.addActor(btn_upgrade_1);
		} else {
			btn_info.setPosition(pos.x + 40, pos.y + 50);
		}
		
		u_stage.addActor(checkbox1);
		u_stage.addActor(checkbox2);
		bg = Assets.manager.get("data/texture/khung-upg.png", Texture.class);
		ibg = Assets.manager.get("data/texture/bg-thong-tin.png", Texture.class);
		ubg = Assets.manager.get("data/texture/bg-nang-cap.png", Texture.class);
		Gdx.input.setInputProcessor(stage);
	}
	
	public Button createButton(String name) {
		Texture texture = Assets.manager.get("data/texture/" + name, Texture.class);
		TextureRegion tregion[] = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		return new Button(new TextureRegionDrawable(tregion[0]), new TextureRegionDrawable(tregion[1]), 
				new TextureRegionDrawable(tregion[1]));
	}
	
	public void render(SpriteBatch batch) {
		switch(state) {
		case NORMAL:
			batch.begin();
			batch.draw(bg, pos.x + 30, pos.y + 10);
			batch.end();
			
			stage.draw();
			break;
		case VIEW_INFO:
			batch.begin();
			batch.draw(ibg, 290, 50);
			batch.draw(wayside[level], 420, 400);
			font1.draw(batch, level * 10 + "%", 450, 360);
			font1.draw(batch, level * 10 + "%", 450, 270);
			font1.draw(batch, wsname[level], 600, 480);
			batch.end();
			
			i_stage.draw();
			break;
		case UPGRADE:
			batch.begin();
			batch.draw(ubg, 90, 20);
			batch.draw(wayside[level], 210, 490);
			batch.draw(wayside[level], 780, 490);
			
			font2.draw(batch, "+" + ensure, 660, 465);
			font2.draw(batch, "+" + lrock, 660, 360);
			font2.draw(batch, "" + ccost[level], 230, 465, 200, Align.center, false);
			font2.draw(batch, "" + array_success[level], 300, 360, 150, Align.center, false);
			
			font2.draw(batch, "" + xcost[level], 942, 465, 200, Align.center, false);
			font2.draw(batch, "" + 100, 990, 355, 150, Align.center, false);
			
			if(level < 6) {
				batch.draw(wayside[level + 1], 500, 490);
				batch.draw(wayside[level + 1], 1020, 490);
			}
			batch.end();
			
			u_stage.draw();
			break;
		}
	}
	
	public void update() {
		switch(state) {
		case NORMAL:
			if(btn_info.isChecked()) {
				btn_info.setChecked(false);
				Audio.btnClick.play(Audio.soundVolume);
				state = VIEW_INFO;
				close.setPosition(938, 566);
				btn_coins.setPosition(525, 90);
				i_stage.addActor(close);
				
				if(level <= 4)
					i_stage.addActor(btn_coins);
				Gdx.input.setInputProcessor(i_stage);
			}
			
			if(level <= 4) {
				if(btn_upgrade_1.isChecked()) {
					btn_upgrade_1.setChecked(false);
					Audio.btnClick.play(Audio.soundVolume);
					initialUpgPopup();
				}
			}
			
			break;
		case VIEW_INFO:
			if(close.isChecked()) {
				Audio.btnClick.play(Audio.soundVolume);
				gc = true;
			}
			
			if(btn_coins.isChecked()) {
				btn_coins.setChecked(false);
				Audio.btnClick.play(Audio.soundVolume);
				initialUpgPopup();
			}
			break;
		case UPGRADE:
			if(close.isChecked()) {
				Audio.btnClick.play(Audio.soundVolume);
				gc = true;
			}
			
			int n = success;
			if(level < 5) {
				if(btn_coins.isChecked()) {
					clicked_(btn_coins);
					
					if(F.coins < ccost[level]) {
						Farm.payment.onMCDialog();
					}
					else {						
						if(checkbox2.isChecked()) {
							n = success + 20;
						}	
						
						if(n < array_success[level]) {
							plot.upgrade();
							if(checkbox2.isChecked()) {								
								if(exceed < array_exceed[level]) {
									plot.upgrade();
								}
							}
							Audio.supgradeP.play(Audio.soundVolume);
							Farm.payment.onToast("Nâng cấp thành công lên cấp " + plot.level + " !", 0);
						}
						else {
							if(!checkbox1.isChecked()) {
								if(lvfail < array_lvfail[level])
									if(plot.level > 0) {
										plot.level --;
									}
							}							
							Farm.payment.onToast("Nâng cấp thất bại !", 0);
						}	
						
						//Tru 1 giay dam bao
						if(checkbox1.isChecked()) {
							Data.addEPaper(-1);
						}						
						//Tru 1 ngoc may man
						if(checkbox2.isChecked()) {
							Data.addLRock(-1);
						}						
						gc = true;
						F.coins -= ccost[level];
					}
				}
				
				if(btn_money.isChecked()) {
					clicked_(btn_money);
					
					if(F.money < xcost[level]) {
						Farm.payment.onMXDialog();
					}
					else {
						F.money -= xcost[level];
						
						plot.upgrade();
						if(exceed < array_exceed[level]) {
							plot.upgrade();
						}
						
						gc = true;
						Farm.payment.onToast("Nâng cấp thành công lên cấp " + plot.level + " !", 0);
						Farm.payment.Tracker(2, xcost[level], "Nâng cấp ruộng" + plot.id + " lên cấp " + plot.level);
						Audio.supgradeP.play(Audio.soundVolume);
					}
				}
			}
			break;
		}
	}
	
	public void initialUpgPopup() {
		state = UPGRADE;
		close.setPosition(1138, 646);
		btn_coins.setPosition(320, 100);
		u_stage.addActor(close);
		u_stage.addActor(btn_coins);
		u_stage.addActor(btn_money);
		Gdx.input.setInputProcessor(u_stage);
	}
	
	public void contains(float x, float y) {
		if(!bound.contains(x, y)) {
			gc = true;
		}
	}
	
	public void clicked_(Button btn) {
		btn.setChecked(false);
		Audio.btnClick.play(Audio.soundVolume);
	}
	
	public int state() {
		return state;
	}
	
	public boolean gc() {
		return gc;
	}
}