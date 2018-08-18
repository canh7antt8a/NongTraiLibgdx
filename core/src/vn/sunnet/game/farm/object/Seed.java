package vn.sunnet.game.farm.object;

import java.util.Random;

import vn.sunnet.game.farm.assets.Assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Seed {

	private static final int NORMAL = 0, WATERING = 1, HARVEST = 2;
	public Texture watering, harvest;
	public Texture animation[];
	public Vector2 pos;
	public Random random;
	public int value, experience;
	public float growth_period[], stateTime, time, wTime, cTime;
	public int id, type, kind, state, period = 0, totalFrame = 4;
	public boolean wither = false, needWatering;
	
	public Seed(int id) {
		this.id = id;
		state = NORMAL;
		random = new Random();
		animation = new Texture[4];
		
		pos = new Vector2(90 + 210 * ((id%12)%4), 380 - 153 * ((id%12)/4));
		watering = Assets.manager.get("data/texture/tuoi-nuoc.png", Texture.class);
		harvest = Assets.manager.get("data/texture/thu-hoach.png", Texture.class);
	}
	
	public void initialAnimation(String name) {
		String s[] = {"hat-giong", "cay-non", "cay-lon"};
		for(int i = 0; i < 3; i++) {
			animation[i] = Assets.manager.get("data/seed/" + s[i] + ".png", Texture.class);
		}
		
		animation[3] = Assets.manager.get("data/seed/" + name + ".png", Texture.class);
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(animation[period], pos.x, pos.y);
		
		switch(state) {
		case NORMAL:
			break;
		case WATERING:
			batch.draw(watering, pos.x + 120, pos.y + 90);
			break;
		case HARVEST:
			batch.draw(harvest, pos.x + 120, pos.y + 95);
			break;
		}
	}
	
	public void update(float deltaTime) {
		if(state != HARVEST) {
			time += deltaTime;
			stateTime += deltaTime;
			
			if(period < (totalFrame - 1)) {
				if(stateTime > growth_period[period]) {
					period ++;
					stateTime = 0;
				}
			} else {
				if(state == WATERING)
					wither = true;
				state = HARVEST;
				return;
			}
			
			if(needWatering) {
				if(state != WATERING) {
					if(time > wTime) {
						state = WATERING;
					}
				} else {
					if(time > wTime + cTime) {
						wither = true;
						state = NORMAL;
						needWatering = false;
					}
				}
			}
		}
	}
	
	//Thay doi thoi gian sinh truong cua hat giong
	public void changeGR_period(int level) {
		for(int i = 0; i < 3; i ++)
			growth_period[i] -= growth_period[i] * 0.1f * level;
	}
	
	public void setTime(float time) {
		float totalTime = 0;
		
		this.time = time;
		while(totalTime + growth_period[period] < time) {
			totalTime += growth_period[period++];
			if(period > 2) {
				state = HARVEST;
				break;
			}
		}
		
		stateTime = time - totalTime;
		System.out.println("Time: " + time);
	}
	
	public void setTime(float time, float wTime, float cTime) {
		this.wTime = wTime;
		this.cTime = cTime;
		needWatering = true;
		if(time > wTime + cTime) {
			wither = true;
			needWatering = false;
		}
		else {
			if(time > wTime) {
				state = WATERING;
			}
		}
		
		setTime(time);
	}
	
	public int state() {
		return state;
	}
	
	public void reset() {
		this.state = NORMAL;
		needWatering = false;
	}
}