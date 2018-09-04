package com.leaptechjsc.game.stage;

import com.leaptechjsc.game.happyfarm.Actor.SButton;
import com.leaptechjsc.game.happyfarm.assets.Assets;
import com.leaptechjsc.game.happyfarm.assets.Audio;
import com.leaptechjsc.game.happyfarm.assets.Data;
import com.leaptechjsc.game.happyfarm.assets.Language;
import com.leaptechjsc.game.happyfarm.main.Farm;
import com.leaptechjsc.game.happyfarm.nature.F;
import com.leaptechjsc.game.happyfarm.nature.SeedNature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class Shop {

	private final int FLOWER = 0, FRUIT = 1, TOOL = 2;
	private Stage stage, stage2, stage3;
	private Texture shop, frame1, frame2;
	private BitmapFont font1, font2, font3, font4, fontTitle;
	private SButton[] flower, fruit, tool;
	private Button btn_money, btn_coins, btn_money_, btn_coins_;
	private int type, kind, gcost, xcost, value, experience, growth_time, lvUnlock;
	public int tab_pos[][] = {{308, 596}, {415, 596}, {522, 596}};
	private int money, coins, total;
	private int prev = 0, cur = 0, renderType;
	private String time, name, guide;
	private boolean lock, renderFrame;
	public Button[] tab, increment, decrement, close;
	
	public Shop(Stage sta) {
		stage = new Stage();
		stage2 = new Stage();
		stage3 = new Stage();

		stage.setViewport(sta.getViewport());
		stage2.setViewport(sta.getViewport());
		stage3.setViewport(sta.getViewport());
		
		font1 = Assets.manager.get("data/font/font-xanh.fnt", BitmapFont.class);
	    font2 = Assets.manager.get("data/font/level.fnt", BitmapFont.class);
	    font2.getData().setScale(0.6f);
	    font3 = Assets.manager.get("data/font/cua-hang.fnt", BitmapFont.class);
	    font4 = new BitmapFont(Gdx.files.internal("data/font/cua-hang.fnt"), false);
	    font4.getData().setScale(0.5f);

		fontTitle = new BitmapFont(Gdx.files.internal(F.strFontNormal), false);
	    
		shop = Assets.manager.get("data/shop/cua-hang.png", Texture.class);
		frame1 = Assets.manager.get("data/shop/khung-thong-tin.png", Texture.class);
		frame2 = Assets.manager.get("data/shop/khung-thong-tin-2.png", Texture.class);
		
		btn_money = F.createButton("data/shop/icon-xu.png");
		btn_money.setPosition(886, 90);
		btn_coins = F.createButton("data/shop/icon-gold.png");
		btn_coins.setPosition(240, 90);
		btn_money_ = F.createButton("data/shop/icon-xu.png");
		btn_money_.setPosition(886, 80);
		btn_coins_ = F.createButton("data/shop/icon-gold.png");
		btn_coins_.setPosition(240, 90);
		
		increment = new Button[4];
		for(int i = 0; i < 4; i++) {
			increment[i] = F.createButton("data/texture/mui-ten-phai-" + (i%2) + ".png");
			if(i%2 == 0)
				increment[i].setPosition(730, 260);
			else
				increment[i].setPosition(820, 265);
			if(i/2 == 0) stage2.addActor(increment[i]);
			else stage3.addActor(increment[i]);
		}
		decrement = new Button[4];
		for(int i = 0; i < 4; i++) {
			decrement[i] = F.createButton("data/texture/mui-ten-trai-" + (i%2) + ".png");
			if(i%2 == 0)
				decrement[i].setPosition(415, 260);
			else
				decrement[i].setPosition(325, 265);
			if(i/2 == 0) stage2.addActor(decrement[i]);
			else stage3.addActor(decrement[i]);
		}	
		
		String tab_name[] = {"hoa", "qua", "icnhan-cong"};
		tab = new Button[3];
		for(int i = 0; i < 3; i++) {
			tab[i] = F.createButton_("data/shop/" + tab_name[i] + ".png");
			tab[i].setPosition(tab_pos[i][0], tab_pos[i][1]);
			stage.addActor(tab[i]);
		}		
		tab[0].setChecked(true);
		
		Vector2 base = new Vector2(100, 360);
		flower = new SButton[12];
		for(int i = 0; i < 12; i++) {
			flower[i] = createSButton(SeedNature.flower[i] + ".png", FLOWER, i);
			flower[i].setPosition(base.x + (i % 6) * 180, base.y - (i / 6) * 210);
		}
		
		fruit = new SButton[12];
		for(int i = 0; i < 12; i++) {
			fruit[i] = createSButton(SeedNature.fruit[i] + ".png", FRUIT, i);
			fruit[i].setPosition(base.x + (i % 6) * 180, base.y - (i / 6) * 210);
		}
		
		tool = new SButton[4];
		close = new Button[3];
		close[2] = F.createButton("data/texture/close.png");
		close[2].setPosition(1090, 640);
		
		for(int i = 0; i < 2; i++) {
			close[i] = F.createButton("data/texture/close.png");
			close[i].setPosition(1170 - i * 80, 560 + i * 80);
		}
		
		for(int i = 0; i < 4; i++) {
			tool[i] = createSButton(SeedNature.tool[i] + ".png", TOOL, i);
			tool[i].setPosition(base.x + 60 + (i % 6) * 250, 280);
		}
		
		for(int i = 0; i < 12; i++)
			stage.addActor(flower[i]);
		
		stage.addActor(close[0]);		
		stage2.addActor(btn_money);
		stage2.addActor(btn_coins);
		stage2.addActor(close[1]);		
		stage3.addActor(btn_money_);
		stage3.addActor(btn_coins_);
		stage3.addActor(close[2]);
		Gdx.input.setInputProcessor(stage);
	}
	
	public SButton createSButton(String name, int type, int kind) {
		Texture texture = Assets.manager.get("data/shop/" + name, Texture.class);
		TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/3, texture.getHeight())[0];
		return new SButton(new TextureRegionDrawable(region[0]), new TextureRegionDrawable(region[1]), 
				region[2], type, kind, font4);
	}
	
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(shop, 50, 90);
		batch.end();
		stage.draw();
		if(renderFrame)
			if(renderType == 0)
				renderFrame(batch, type, kind);
			else
				renderFrame_(batch, kind);
		
		updateTab();
		updateItem();
	}
	
	public void updateTab() {
		int i;
		for(i = 0; i < 3; i++)
			if(tab[i].isChecked()) {
				if(i != cur) {
					Audio.btnClick.play(Audio.soundVolume);
					prev = cur;
					cur = i;
					break;
				}
			}
		
		if(cur != prev) {
			tab[prev].setChecked(false);
			prev = cur;
			updateShop();
		}
		else {
			tab[cur].setChecked(true);
		}
	}
	
	public void updateShop() {
		stage.clear();
		stage.addActor(tab[0]);
		stage.addActor(tab[1]);
		stage.addActor(tab[2]);
		stage.addActor(close[0]);
		
		switch(cur) {
		case 0:
			for(int i = 0; i < 12; i++)
				stage.addActor(flower[i]);
			break;
		case 1:
			for(int i = 0; i < 12; i++)
				stage.addActor(fruit[i]);
			break;
		case 2:
			for(int i = 0; i < 4; i++)
				stage.addActor(tool[i]);
			break;
		}
	}
	
	public void updateItem() {
		for(int i = 0; i < 12; i++) {
			if(flower[i].isChecked()) {
				clicked_(flower[i]);
				loadIF(flower[i]);
				Gdx.input.setInputProcessor(stage2);
			}
			
			if(fruit[i].isChecked()) {
				clicked_(fruit[i]);
				loadIF(fruit[i]);
				Gdx.input.setInputProcessor(stage2);
			}
		}
		
		for(int i = 0; i < 4; i++) {
			if(tool[i].isChecked()) {
				tool[i].setChecked(false);
				renderFrame = true;
				renderType = 1;
				kind = i;
								
				switch(kind) {
				case 0:
					gcost = (int) (10000 * Math.ceil(F.level/10f));
					xcost = gcost/10;
					guide =  Language.General.THUE_1_NHAN_CONG.getStr();//"Thuê 1 nhân công tự động trồng trọt, chăn nuôi\n thu hoạch. Thuê trong 1 mùa";
					break;
				case 1:
					gcost = 300 * F.level;
					xcost = gcost/10;
					guide =  Language.General.THEM_1_CHO_BAN.getStr();//"Thêm 1 chỗ bán nông phẩm trong 1 mùa";
					break;
				case 2:
					gcost = 30000;
					xcost = gcost/10;
					guide =  Language.General.NGOC_MM_TANG_TC.getStr();//"Ngọc may mắn tăng thêm 20% \nkhả năng thành công";
					break;
				case 3:
					gcost = 10000;
					xcost = gcost/10;
					guide =  Language.General.NC_TB_VUONG_0_GIAM.getStr();//"Nếu nâng cấp vườn thất bại thì \nvườn không bị giảm cấp";
					break;
				}
				
				total = 0;
				coins = money = 0;
				Gdx.input.setInputProcessor(stage3);
			}
		}
	}
	
	public void loadIF(SButton button) {
		renderFrame = true;
		renderType = 0;
		total = 0;
		coins = 0;
		money = 0;
		
		lock = button.lock;		
		name = button.name;
		type = button.type;	
		kind = button.kind;
		gcost = button.gcost;
		xcost = button.xcost;
		value = button.value;
		
		experience = button.experience;
		lvUnlock = button.lvUnlock;
		
		growth_time = button.growth_time;
		int hour = growth_time / 3600;
		growth_time -= hour * 3600;
		int minute = growth_time / 60;
		int second = growth_time - minute * 60;
		
		time = String.format("%02d", hour) + ":" + String.format("%02d", minute) 
				+ ":" + String.format("%02d", second);
	}
	
	public void renderFrame(SpriteBatch batch, int type, int kind) {
		batch.begin();
		batch.draw(frame1, 100, 0);
		font3.draw(batch, name, 400, 660, 480, Align.center, false);
		font1.draw(batch, Integer.toString(total), 555, 324, 120, Align.center, false);
		font2.draw(batch, Integer.toString(gcost), 500, 600);
		font2.draw(batch, Integer.toString(xcost), 820, 600);	
		font1.draw(batch, time, 420, 518);
		font1.draw(batch, Integer.toString(experience), 420, 464);
		font1.draw(batch, Integer.toString(value), 915, 518);
		font1.draw(batch, Integer.toString(lvUnlock), 915, 464);
		font2.draw(batch, Integer.toString(coins), 360, 192, 200, Align.center, false);
		font2.draw(batch, Integer.toString(money), 660, 192, 200, Align.center, false);
		
		batch.end();
		stage2.draw();
		
		for(int i = 0; i < 2; i++) {
			if(decrement[i].isChecked()) {
				clicked_(decrement[i]);
				
				if(!lock)
				if(total > 0) {
					if(i == 0)  total --;
					else if (total >= 10)
							 total -= 10;
						 else  total = 0;
					coins = total * gcost;
					money = total * xcost;
				}
			}
			
			if(increment[i].isChecked()) {
				clicked_(increment[i]);
				
				if(!lock) {
					if(i == 0)  total ++;
					else total += 10;
					coins = total * gcost;
					money = total * xcost;
				}
			}
		}
		
		if(btn_coins.isChecked()) {
			btn_coins.setChecked(false);
			if(total > 0) {
				if(F.coins >= coins) {
					Audio.sbuy.play(Audio.soundVolume);
					F.coins -= coins;
					
					if(type == FLOWER)
						Data.addFlRepository(kind, total);
					else
						Data.addFrRepository(kind, total);
					
					renderFrame = false;
					Gdx.input.setInputProcessor(stage);
				} else
					Farm.payment.onMCDialog();
			} else {
				renderFrame = false;
				Gdx.input.setInputProcessor(stage);
			}
		}
			
		if(btn_money.isChecked()) {
			btn_money.setChecked(false);
			if(total > 0) {
				if(F.money >= money) {
					Audio.sbuy.play(Audio.soundVolume);
					F.money -= money;
					
					if(type == FLOWER)
						Data.addFlRepository(kind, total);
					else
						Data.addFrRepository(kind, total);
					
					renderFrame = false;
					//Tracker:
					Farm.payment.Tracker(2, money, name);
					Gdx.input.setInputProcessor(stage);
				} else
					Farm.payment.onMXDialog();
			} else {
				renderFrame = false;
				Gdx.input.setInputProcessor(stage);
			}
		}
		
		if(close[1].isChecked()) {
			clicked_(close[1]);
			renderFrame = false;
			Gdx.input.setInputProcessor(stage);
		}
	}
	
	public void renderFrame_(SpriteBatch batch, int kind) {		
		batch.begin();
		batch.draw(frame2, 130, 20);
		font3.draw(batch, SeedNature.tool_name[kind], 400, 660, 480, Align.center, false);
		font3.draw(batch, guide, 400, 510, 480, Align.center, false);
		font1.draw(batch, Integer.toString(total), 555, 324, 120, Align.center, false);
		font2.draw(batch, Integer.toString(gcost), 500, 600);
		font2.draw(batch, Integer.toString(xcost), 820, 600);	
		font2.draw(batch, Integer.toString(coins), 360, 192, 200, Align.center, false);
		font2.draw(batch, Integer.toString(money), 660, 192, 200, Align.center, false);
		
		batch.end();
		stage3.draw();
		
		for(int i = 0; i < 2; i++) {
			if(decrement[i + 2].isChecked()) {
				clicked_(decrement[i+2]);
				
				if(total > 0) {
					if(i == 0)  total --;
					else if (total >= 10)
							 total -= 10;
						 else  total = 0;
					
					coins = total * gcost;
					money = total * xcost;
				}
			}
				
			if(increment[i + 2].isChecked()) {
				clicked_(increment[i+2]);
				
				switch(kind) {
				case 0:
					if(F.nof_employee < 1)
						total = 1;
					break;
				case 1:
					if(F.nof_table < 3)
						total = 1;
					break;
				case 2:
					if(i == 0)  total ++;
					else  total += 10;
					break;
				case 3:
					if(i == 0)  total ++;
					else  total += 10;
					break;
				}		
				
				coins = total * gcost;
				money = total * xcost;
			}
		}
		
		if(btn_coins_.isChecked()) {
			btn_coins_.setChecked(false);
			if(total > 0) {
				if(F.coins >= coins) {
					Audio.sbuy.play(Audio.soundVolume);
					F.coins -= coins;
					
					switch(kind) {
					case 0:
						Data.savenofEmployee(1);
						break;
					case 1:
						Data.savenofTable(3);
						break;
					case 2:
						Data.addLRock(total);
						break;
					case 3:
						Data.addEPaper(total);
						break;
					}
					
					renderFrame = false;
					Gdx.input.setInputProcessor(stage);
				} else
					Farm.payment.onMCDialog();
			} else {
				renderFrame = false;
				Gdx.input.setInputProcessor(stage);
			}
		}
			
		if(btn_money_.isChecked()) {
			btn_money_.setChecked(false);
			if(total > 0) {
				if(F.money >= money) {
					Audio.sbuy.play(Audio.soundVolume);
					F.money -= money;
					
					switch(kind) {
					case 0:
						Data.savenofEmployee(1);
						break;
					case 1:
						Data.savenofTable(3);
						break;
					case 2:
						Data.addLRock(total);
						break;
					case 3:
						Data.addEPaper(total);
						break;
					}
					
					renderFrame = false;
					Farm.payment.Tracker(2, money, name);
					Gdx.input.setInputProcessor(stage);
				} else
					Farm.payment.onMXDialog();
			} else {
				Audio.btnClick.play(Audio.soundVolume);
				renderFrame = false;
				Gdx.input.setInputProcessor(stage);
			}
		}
		
		if(close[2].isChecked()) {
			clicked_(close[2]);
			renderFrame = false;
			Gdx.input.setInputProcessor(stage);
		}
	}
	
	public void clicked_(Button btn) {
		btn.setChecked(false);
		Audio.btnClick.play(Audio.soundVolume);
	}
	
	public void dispose() {
		font4.dispose();
		stage.dispose();
		stage2.dispose();
		stage3.dispose();
	}
}