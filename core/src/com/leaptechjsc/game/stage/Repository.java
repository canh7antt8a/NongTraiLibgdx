package com.leaptechjsc.game.stage;

import java.util.ArrayList;

import com.leaptechjsc.game.happyfarm.Actor.RButton;
import com.leaptechjsc.game.happyfarm.assets.Assets;
import com.leaptechjsc.game.happyfarm.assets.Audio;
import com.leaptechjsc.game.happyfarm.assets.Data;
import com.leaptechjsc.game.happyfarm.assets.Language;
import com.leaptechjsc.game.happyfarm.nature.F;
import com.leaptechjsc.game.happyfarm.nature.SeedNature;

import com.badlogic.gdx.Gdx;
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

public class Repository {
	private static final int FLOWER = 0, FRUIT = 1;
	private static final String path = "data/warehouse/", spath = "data/shop/";
	
	private Stage stage;
	private Group group;
	private Texture background;
	private ArrayList<RButton> arrayList;
	private RButton rbutton;
	private BitmapFont font1, font2, font3;
	private Button btn_left, btn_right;
	private int currentPos = 0, maxPos, id, nof_wgod;
	private boolean choose = false;
	private Drawable up, down, checked;
	
	public Repository(int type, Stage sta) {
		stage = new Stage();

		stage.setViewport(sta.getViewport());
		group = new Group();
		stage.addActor(group);
		
		background = Assets.manager.get(path + "bar.png", Texture.class);
		nof_wgod = F.nof_wgod;
	    
	    font1 = new BitmapFont(Gdx.files.internal("data/font/kho-chua.fnt"), false);
	    font1.getData().setScale(0.4f);
	    font2 = Assets.manager.get(F.strFontNormal, BitmapFont.class);
	    font3 = new BitmapFont(Gdx.files.internal("data/font/cua-hang.fnt"), false);
	    font3.getData().setScale(0.8f);
	    
	    btn_left = F.createButton(path + "icon-mui-ten-1.png");
	    btn_left.setPosition(40, 335);	    
	    btn_right = F.createButton(path + "icon-mui-ten-2.png");
	    btn_right.setPosition(1175, 335);	    
	    
	    arrayList = new ArrayList<RButton>();
	    if(type == 0) {
		    if(nof_wgod > 0) {
		    	loadDrawable(spath + "nuoc-than.png", 2);
		    	rbutton = new RButton(up, down, checked, font1, font3, 2, -2, nof_wgod);
		    	arrayList.add(rbutton);
		    }
	    }
	    
	    for(int i = 0; i < 12; i++) {
	    	int count = Data.loadFlRepository(i);
	    	if(count > 0) {
	    		loadDrawable(spath + SeedNature.flower[i] + ".png", 3);
	    		rbutton = new RButton(up, down, checked, font1, font3, FLOWER, i, count);
	    		arrayList.add(rbutton);
	    	}
	    }
	    
	    for(int i = 0; i < 12; i++) {
	    	int count = Data.loadFrRepository(i);
	    	if(count > 0) {
	    		loadDrawable(spath + SeedNature.fruit[i] + ".png", 3);
	    		rbutton = new RButton(up, down, checked, font1, font3, FRUIT, i, count);
	    		arrayList.add(rbutton);
	    	}
	    }
	    
	    updateRepository();
	    Gdx.input.setInputProcessor(stage);
	}
	
	public void loadDrawable(String name, int n) {
		Texture texture = Assets.manager.get(name, Texture.class);
		TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/n, texture.getHeight())[0];
		up = new TextureRegionDrawable(region[0]);
		down = new TextureRegionDrawable(region[1]);
		checked = new TextureRegionDrawable(region[0]);
	}	
	
	public void render(SpriteBatch batch) {
		batch.begin();
		font2.draw(batch,  Language.General.KHO_CHUA_HAT_GIONG.getStr(), 0, 600, 1280, Align.center, false);
		batch.draw(background, 0, 200);		
		batch.end();
		stage.draw();
		update();
	}
	
	public void update() {
		if(btn_left.isChecked()) {
			clicked_(btn_left);			
			if(currentPos > 0) {
				currentPos --;
				updateRepository();
			}
		}
		
		if(btn_right.isChecked()) {
			clicked_(btn_right);			
			if(currentPos < (maxPos - 1)) {
				currentPos ++;
				updateRepository();
			}
		}
		
		int size = arrayList.size();
		for(int i = 0; i < size; i++) {
			rbutton = arrayList.get(i);
			if(rbutton.isChecked()) {
				clicked_(rbutton);				
				choose = true;
				if(rbutton.type == 0)
					id = rbutton.kind;
				else if(rbutton.type == 1)
						id = 12 + rbutton.kind;
					else id = -2;
				break;
			}
		}
	}
	
	public void updateRepository() {		
		int pos, des;
		int size = arrayList.size();
		group.clear();
		group.addActor(btn_left);
		group.addActor(btn_right);
		
	    maxPos = (size % 4 == 0) ? size / 4: (size /4 + 1);
	    
	    pos = currentPos * 4;
	    des = (pos + 4 < size) ? pos + 4 : size;
	    
		for(int i = pos; i < des; i++) {
			rbutton = arrayList.get(i);
			rbutton.setPosition(150 + ((i - pos) % 4) * 250, 270);
			group.addActor(rbutton);
		}
	}
	
	public int choose_id() {
		if(choose)
			return id;
		return -1;
	}
	
	public void resetChooseValue() {
		choose = false;
	}
	
	public void clicked_(Button btn) {
		btn.setChecked(false);
		Audio.btnClick.play(Audio.soundVolume);
	}
	
	public void dispose() {
		font1.dispose();
		font3.dispose();
		stage.dispose();
	}
}