package com.leaptechjsc.game.happyfarm.object;

import com.leaptechjsc.game.happyfarm.assets.Assets;
import com.leaptechjsc.game.happyfarm.assets.Data;
import com.leaptechjsc.game.happyfarm.nature.F;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;

public class Table {

	private final int FLOWER = 0, FRUIT = 1;
	private Texture table;
	private Rectangle bound;
	private Vector2 pos;
	public int id, kind, type, index, total;
	public boolean flag;
	private BitmapFont font;
	
	public Table(int id) {
		this.id = id;
		flag = false;
		initTable();
	}
	
	public Table(int id, int n) {
		this.id = id;
		assignSeed(n);
		initTable();
	}
	
	public void initTable() {
		bound = new Rectangle(980, 65 + 155 * id, 50, 50);
		pos = new Vector2(965, id * 155);
		table = Assets.manager.get("data/texture/ban.png", Texture.class);
		font = Assets.manager.get("data/font/font_clock.fnt", BitmapFont.class);
	}
	
	public void assignSeed(int index) {
		this.index = index;
		flag = true;
		if(index < 12) {
			type = FLOWER;
			kind = index;
		} else {
			type = FRUIT;
			kind = index - 12;
		}
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(table, pos.x, pos.y);
		
		if(flag) {
			switch(type) {
			case FLOWER:
				batch.draw(F.flower[kind], bound.x, bound.y);
				font.draw(batch, "" + total, 965, pos.y + 78, 80, Align.center, false);
				break;
			case FRUIT:
				batch.draw(F.fruit[kind], bound.x, bound.y);
				font.draw(batch, "" + total, 965, pos.y + 78, 80,  Align.center, false);
				break;
			}
		}
	}
	
	public void update() {
		if(flag) {
			switch(type) {
			case FLOWER:
				total = Data.loadFlWavehouse(kind);
				if(total <= 0)
					flag = false;
				break;
			case FRUIT:
				total = Data.loadFrWavehouse(kind);
				if(total <= 0)
					flag = false;
				break;
			}
		}
	}
	
	public boolean isContains(Vector3 touchPoint) {
		return bound.contains(touchPoint.x, touchPoint.y);
	}
	
	public void reset() {
		flag = false;
	}
}