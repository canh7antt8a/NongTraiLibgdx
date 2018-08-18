package vn.sunnet.game.farm.object;

import vn.sunnet.game.farm.assets.Audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

public class Experience {

	private final float delTime = 0.02f;
	public BitmapFont font;
	private Color color;
	private Vector2 pos;
	private float time, alpha;
	private boolean gc = false;
	private String name;
	
	public Experience(int type, int id, int n) {
		alpha = 1;
		name = "+" + n;
		if(type == 0) {
			font = new BitmapFont(Gdx.files.internal("data/font/kho-chua.fnt"), false);
			font.getData().setScale(0.4f);
			pos = new Vector2(120 + 210 * ((id%12)%4), 481 - 153 * ((id%12)/4));
			name += "exp";
		} else {
			font = new BitmapFont(Gdx.files.internal("data/font/font-hoan-chinh.fnt"), false);
			pos = new Vector2(1030, 200 + 155 * id);
		}
		
		color = font.getColor();
		Audio.sharvest.play(Audio.soundVolume);
	}
	
	public void render(SpriteBatch batch) {
//		font.drawMultiLine(batch, name, pos.x, pos.y, 100, HAlignment.CENTER);

		font.draw(batch, name, pos.x, pos.y, 100, Align.center, false);
	}
	
	public void update() {
		time += Gdx.graphics.getDeltaTime();
		if(time > delTime) {
			time = 0;
			alpha -= 0.01;
			if(alpha <= 0.4)
				gc = true;
			
			font.setColor(color.a, color.b, color.g, alpha);
			pos.add(0, 3);
		}
	}
	
	public boolean gc() {
		return gc;
	}
	
	public void dispose() {
		font.dispose();
	}
}