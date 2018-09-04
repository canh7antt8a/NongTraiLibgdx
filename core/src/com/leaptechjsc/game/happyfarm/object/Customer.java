package com.leaptechjsc.game.happyfarm.object;

import java.util.ArrayList;
import java.util.Random;

import com.leaptechjsc.game.happyfarm.assets.Assets;
import com.leaptechjsc.game.happyfarm.nature.F;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Customer {

	public static final int STANDING = 0, MOVING = 1;
	public static final int FLOWER = 0, FRUIT = 1;
	public static final int LEFT = 0, RIGHT = 1;
	public static Vector2 range = new Vector2(1030, 1300);
	public Animation animation[];
	public int id, state, direction, velocity = 1;
	public int currentFrame = 0, totalFrame = 4;
	public int index, total, type, kind, coins;
	public float pauseTime, time, stateTime, frameDuration = 0.2f;
	public Vector2 pos;
	public Random random;
	public boolean gc = false;
	public ArrayList<Table> arrayTable;
	public ArrayList<Experience> arrayExp;
	public Table table;
	public Experience exp;
	public Texture texture;
	
	public Customer(ArrayList<Table> arrayTable, ArrayList<Experience> arrayExp) {
		this.arrayTable = arrayTable;
		this.arrayExp = arrayExp;
		random = new Random();
		state = STANDING;
		direction = RIGHT;
		animation = new Animation[2];
		pauseTime = random.nextInt(30);
	}
	
	public Animation createAnimation(String name) {
		Texture texture = Assets.manager.get("data/texture/" + name, Texture.class);
		TextureRegion tregion[] = TextureRegion.split(texture, texture.getWidth()/totalFrame, texture.getHeight())[0];
		return new Animation(frameDuration, tregion);
	}
	
	public void render(SpriteBatch batch) {
		switch (state) {
		case STANDING:
			batch.draw((TextureRegion) animation[direction].getKeyFrame(0), pos.x, pos.y);
			break;
		case MOVING:
			batch.draw((TextureRegion)animation[direction].getKeyFrame(stateTime), pos.x, pos.y);
			break;
		}
		
		if(total > 0) {
			if(index < 12) {
				batch.draw(F.flower[index], pos.x + 15, pos.y + 135);
			} else {
				batch.draw(F.fruit[index - 12], pos.x + 15, pos.y + 135);
			}
			F.font.draw(batch, "x" + total, pos.x + 65, pos.y + 200);
		}
	}
	
	public int[] reverse(int size) {
		boolean flag;
		int n, table[] = new int[size];
		for(int i = 0; i < size; i++) {
			while(true) {
				n = random.nextInt(size);
				flag = true;
				for(int j = 0; j < i; j++) {
					if(n == table[j]) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					table[i] = n;
					break;
				}
			}
		}
		return table;
	}
}