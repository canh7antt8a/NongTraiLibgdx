package com.leaptechjsc.game.stage;

import java.util.ArrayList;

import com.leaptechjsc.game.happyfarm.Actor.MyButton;
import com.leaptechjsc.game.happyfarm.Actor.WButton;
import com.leaptechjsc.game.happyfarm.assets.Assets;
import com.leaptechjsc.game.happyfarm.assets.Audio;
import com.leaptechjsc.game.happyfarm.assets.Data;
import com.leaptechjsc.game.happyfarm.assets.Language;
import com.leaptechjsc.game.happyfarm.nature.F;
import com.leaptechjsc.game.happyfarm.nature.SeedNature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class Warehouse {
	private static final int viewportWidth = 1280, viewportHeight = 720;
	private static final int FLOWER = 0, FRUIT = 1;
	private static final int STATUS1 = 0, STATUS2 = 1;
	
	private static final String path = "data/warehouse/", path2 = "data/texture/";
	private String name, text;
	private Stage stage, istage;
	private Group group;
	private BitmapFont font1, font2, font3, font4, font5, fontContent;
	private Texture frame1, frame2;
	private Button btn_left, btn_right, btnsell, close;
	private Button[] increment, decrement;
	private Drawable up, down, checked;
	private boolean gc = false;
	public int value, amount, count, coins, state;
	private int currentPos = 0, maxPos, index;
	private ArrayList<WButton> arrayList;
	private ArrayList<MyButton> arrayListButton;
	private WButton wbutton;
//	private MyButton mbutton;
	public int n; // type * 12 + kind
	
	public Warehouse(int state, Stage sta) {
		this.state = state;	
		if(state == 0)  text =  Language.General.KHO_CHUA_NONG_PHAM.getStr();//"Kho chứa nông phẩm";
		else	text =  Language.General.CHON_NP_DE_BAN.getStr();//"Chọn nông phẩm để bán";
		
		stage = new Stage();
	    istage = new Stage();

		stage.setViewport(sta.getViewport());
		istage.setViewport(sta.getViewport());

	    group = new Group();
	    stage.addActor(group);
	    
	    font1 = Assets.manager.get("data/font/kho-chua.fnt", BitmapFont.class);
	    font1.getData().setScale(0.5f);
	    font2 = Assets.manager.get("data/font/level.fnt", BitmapFont.class);
	    font2.getData().setScale(0.6f);
	    font3 = Assets.manager.get(F.strFontNormal, BitmapFont.class);
	    font4 = new BitmapFont(Gdx.files.internal("data/font/font.fnt"), false);
		font5 = new BitmapFont(Gdx.files.internal("data/font/cua-hang.fnt"), false);
		fontContent = new BitmapFont(Gdx.files.internal("data/font/cua-hang.fnt"), false);
	    fontContent.setColor(Color.BLACK);
        fontContent.getData().setScale(.8f);

	    frame1 = Assets.manager.get(path + "bar.png", Texture.class);
	    frame2 = Assets.manager.get(path + "thong-tin-ban.png", Texture.class);
	    
	    btn_left = createButton(path + "icon-mui-ten-1.png");
	    btn_left.setPosition(40, 330);	    
	    btn_right = createButton(path + "icon-mui-ten-2.png");
	    btn_right.setPosition(1175, 330);	    
	    btnsell = createButton(path + "btn-ban.png");
	    btnsell.setPosition(520, 60);
	    close = createButton(path2 + "close.png");
	    close.setPosition(935, 640);
	    
	    decrement = new Button[2];
	    for(int i = 0; i < 2; i++) {
	    	decrement[i] = createButton(path2 + "mui-ten-trai-" + i + ".png");
	    	istage.addActor(decrement[i]);
	    }
	    
	    decrement[0].setPosition(450, 330);
	    decrement[1].setPosition(370, 335);
	    
	    increment = new Button[2];
	    for(int i = 0; i < 2; i++) {
	    	increment[i] = createButton(path2 + "mui-ten-phai-" + i + ".png");
	    	istage.addActor(increment[i]);
	    }
	    
	    increment[0].setPosition(735, 330);
	    increment[1].setPosition(815, 335);
	    
	    istage.addActor(btnsell);
	    istage.addActor(close);

		arrayList = new ArrayList<WButton>();
		arrayListButton = new ArrayList<MyButton>();
//		public static String flower[] = {"dong-tien", "lily", "hoa-sen", "tu-cau", "cam-chuong",
//				"lily-loa-ken", "trang-nguyen", "hoa-hong", "huong-duong", "violet", "bo-cong-anh", "tulip"};
//		public static String fruit[] = {"bi-ngo", "ca-chua", "ca-tim", "cam", "chuoi", "cu-cai", "dua-hau",
//				"nho", "tao", "xoai", "dau-tay", "le"};
		float posXFlower[] = {0, -15, -15, 0, 5, 5, 0, -10, 0, -5, -5, -20};
		float posYFlower[] = {-20, -30, -30, -20, -25, -35, -35, -25, -25, -30, -28, -25};
	    for(int i = 0; i < 12; i++) {
	    	int amount = Data.loadFlWavehouse(i);
	    	if(amount > 0) {
			final int ii = i;
	    		loadDrawable(path + SeedNature.flower[i] + ".png");
			final WButton wbtn = new WButton(up, down, checked, font1, FLOWER, i, amount);
			MyButton mbutton = new MyButton(((TextureRegionDrawable)up).getRegion(), SeedNature.flname[i], posXFlower[i], posYFlower[i]) {
					@Override
					public void precessClicked() {
						wbtn.setChecked(true);
//						arrayList[ii].setChecked(true);
					}
				};
				mbutton.setColorText(Color.BLUE);
				arrayListButton.add(mbutton);
	    		arrayList.add(wbtn);
	    	}
	    }

		float posXFruit[] = {-10, -25, -25, -25, -25, 0, -10, -10, -25, -25, -20, -25};
		float posYFruit[] = {-23, -20, -20, -20, -25, -35, -20, -25, -30, -25, -25, -30};
	    for(int i = 0; i < 12; i++) {
	    	int amount = Data.loadFrWavehouse(i);
	    	if(amount > 0) {

			final int ii = i;
	    		loadDrawable(path + SeedNature.fruit[i] + ".png");
			final WButton wbtn = new WButton(up, down, checked, font1, FRUIT, i, amount);
				MyButton mbutton = new MyButton(((TextureRegionDrawable)up).getRegion(), SeedNature.frname[i], posXFruit[i], posYFruit[i]) {
					@Override
					public void precessClicked() {
						wbtn.setChecked(true);

//						arrayList[ii].setChecked(true);
					}
				};
			mbutton.setColorText(Color.BLUE);
				arrayListButton.add(mbutton);
	    		arrayList.add(wbtn);
	    	}
	    }
	    
	    updateWarehouse();
	    Gdx.input.setInputProcessor(stage);
	}
	
	public void updateWarehouse() {
		int pos, des;
		group.clear();
		group.addActor(btn_left);
		group.addActor(btn_right);
		
		int size = arrayList.size();
	    maxPos = (size % 4 == 0) ? size / 4: (size /4 + 1);
	    
	    pos = currentPos * 4;
	    des = (pos + 4 < size) ? pos + 4 : size;
	    
		for(int i = pos; i < des; i++) {
			wbutton = arrayList.get(i);
			MyButton mbutton = arrayListButton.get(i);
			mbutton.setPosition(150 + ((i - pos) % 4) * 250, 270);
			group.addActor(mbutton);
//			wbutton.setPosition(150 + ((i - pos) % 4) * 250, 270);
//			group.addActor(wbutton);
		}
	}
	
	public void loadDrawable(String name) {
		Texture texture = Assets.manager.get(name, Texture.class);
		TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		up = new TextureRegionDrawable(region[0]);
		down = new TextureRegionDrawable(region[1]);
		checked = new TextureRegionDrawable(region[0]);
	}
	
	public Button createButton(String name) {
		Texture texture = Assets.manager.get(name, Texture.class);
		TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		return new Button(new TextureRegionDrawable(region[0]), new TextureRegionDrawable(region[1]), 
				new TextureRegionDrawable(region[0]));
	}
		
	public void render(SpriteBatch batch) {
		batch.begin();
		font3.draw(batch, text, 0, 600, 1280, Align.center, false);
		batch.draw(frame1, 0, 200);
		batch.end();
		stage.draw();
		switch(state) {
		case STATUS1:
			if(gc) renderSeed(batch, index);
			break;
		case STATUS2:
			break;
		}
		
		if(!gc)
			update();
	}
	
	public void update() {
		if(btn_left.isChecked()) {
			btn_left.setChecked(false);
			Audio.btnClick.play(Audio.soundVolume);
			if(currentPos > 0) {
				currentPos --;
				updateWarehouse();
			}
		}
		
		if(btn_right.isChecked()) {
			btn_right.setChecked(false);
			Audio.btnClick.play(Audio.soundVolume);
			if(currentPos < (maxPos - 1)) {
				currentPos ++;
				updateWarehouse();
			}
		}
		
		for(int i = 0; i < arrayList.size(); i++) {
			wbutton = arrayList.get(i);
			if(wbutton.isChecked()) {
				wbutton.setChecked(false);
				Audio.btnClick.play(Audio.soundVolume);
				
				gc = true;
				switch(state) {
				case 0:
					index = i;
					amount = wbutton.amount;
					
					count = 0;	coins = 0;
					if(wbutton.type == FLOWER) {
						name = SeedNature.flname[wbutton.kind];
						value = SeedNature.getflowerValue(wbutton.kind);
					}
					else {
						name = SeedNature.frname[wbutton.kind];
						value = SeedNature.getfruitValue(wbutton.kind);
					}
					
					value -= value/5;
					Gdx.input.setInputProcessor(istage);
					break;
				case 1:
					n = wbutton.type * 12 + wbutton.kind;
					break;
				}
				break;
			}
		}
	}
	
	public void renderSeed(SpriteBatch batch, int index) {
		batch.begin();
		batch.draw(frame2, 295, 20);
        fontContent.draw(batch, Language.General.GIA_BAN_NHANH.getStr(), 400, 570);
        fontContent.draw(batch, Language.General.NHAP_SO_LUONG.getStr(), 500, 470);
        fontContent.draw(batch, Language.General.TONG_SO_TIEN.getStr(), 400, 270);
		font5.draw(batch, name, 400, 660, 480, Align.center, false);
		font2.draw(batch, Integer.toString(value), 640, 588, 200, Align.center, false);
		font4.draw(batch, "-20%", 850, 570);
		font2.draw(batch, Integer.toString(count), 580, 418, 120, Align.center, false);
		font2.draw(batch, Integer.toString(coins), 640, 295, 200, Align.center, false);
		batch.end();
		istage.draw();
		
		if(close.isChecked()) {
			close.setChecked(false);
			Audio.btnClick.play(Audio.soundVolume);
			gc = false;
			Gdx.input.setInputProcessor(stage);
		}
		
		for(int i = 0; i < 2; i++) {
			if(increment[i].isChecked()) {
				increment[i].setChecked(false);
				Audio.btnClick.play(Audio.soundVolume);
				if(count < amount) {
					if(i == 0)  count ++;
					else if(count + 10 <= amount)
							count += 10;
						else  count = amount;
				}
				coins = count * value;
			}
			
			if(decrement[i].isChecked()) {
				decrement[i].setChecked(false);
				Audio.btnClick.play(Audio.soundVolume);
				
				if(count > 0) {
					if(i == 0)  count --;
					else if(count >= 10)
							 count -= 10;
						 else count = 0;
					coins = count * value;
				}
			}
		}
		
		if(btnsell.isChecked()) {
			btnsell.setChecked(false);
			gc = false;
			if(count > 0) {
				Audio.sbuy.play(Audio.soundVolume);
				wbutton.amount -= count;
				if(wbutton.type == FLOWER)
					Data.addFlWavehouse(wbutton.kind, -count);
				else 
					Data.addFrWavehouse(wbutton.kind, -count);
				
				F.coins += coins;			
				if(wbutton.amount == 0) {
					arrayList.remove(index);
				    updateWarehouse();
				}
			}
			Gdx.input.setInputProcessor(stage);
		}
	}
	
	public void dispose() {
		font4.dispose();
		font5.dispose();
		stage.dispose();
	}
	
	public boolean gc() {		
		return gc;
	}
}