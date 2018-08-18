package vn.sunnet.game.stage;

import vn.sunnet.game.farm.assets.Assets;
import vn.sunnet.game.farm.assets.Audio;
import vn.sunnet.game.farm.main.Farm;
import vn.sunnet.game.farm.nature.F;
import vn.sunnet.game.farm.nature.PlotNature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class ExpansionGarden {

	private Stage stage;
	private Texture texture;
	private Button btn_gold, btn_money, close;
	private BitmapFont bmpfont;
	private int coins, money, success = -1, nof;
	
	/*
	 * n: number plot
	 */
	public ExpansionGarden(int n, Stage sta) {
		this.nof = n;
		stage = new Stage();
		stage.setViewport(sta.getViewport());
		texture = Assets.manager.get("data/texture/mo-rong-dat.png", Texture.class);
		bmpfont = Assets.manager.get("data/font/xu.fnt", BitmapFont.class);
		
		coins = PlotNature.getcost_expension(n - 9);
		money = coins/10;
		
		close = F.createButton("data/texture/close.png");
		close.setPosition(932, 592);
		btn_gold = F.createButton("data/shop/icon-gold.png");
		btn_gold.setPosition(400, 260);
		btn_money = F.createButton("data/shop/icon-xu.png");
		btn_money.setPosition(400, 130);
		
		stage.addActor(btn_gold);
		stage.addActor(btn_money);
		stage.addActor(close);
		Gdx.input.setInputProcessor(stage);
	}
	
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(texture, 284, 73);
		bmpfont.draw(batch, "" + coins, 570, 382);
		bmpfont.draw(batch, "" + money, 570, 266);
		batch.end();
		stage.draw();
		update();
	}	
	
	public void update() {
		if(close.isChecked()) {
			Audio.btnClick.play(Audio.soundVolume);
			success = 0;
		}
		
		if(btn_gold.isChecked()) {
			Audio.btnClick.play(Audio.soundVolume);
			if(F.coins >= coins) {
				success = 1;
				F.coins -= coins;
				Farm.payment.onToast("Mở rộng thành công !", 0);
			}
			else {
				btn_gold.setChecked(false);
				Farm.payment.onMCDialog();
			}
		}
		
		if(btn_money.isChecked()) {
			Audio.btnClick.play(Audio.soundVolume);
			if(F.money >= money) {
				success = 1;
				F.money -= money;
				Farm.payment.Tracker(2, money, "Mở rộng ô đất: " + (nof + 1));
				Farm.payment.onToast("Mở rộng thành công !", 0);
			}
			else {
				btn_money.setChecked(false);
				Farm.payment.onMXDialog();
			}
		}
	}
	
	/*
	 * Default: -1
	 * Close: 0
	 * Success: 1
	 */
	public int success() {
		return success;
	}
}