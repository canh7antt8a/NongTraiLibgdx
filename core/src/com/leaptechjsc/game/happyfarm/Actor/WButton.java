package com.leaptechjsc.game.happyfarm.Actor;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

public class WButton extends Button {

	public BitmapFont font;
	public int type, kind, amount;
	Random random = new Random();
	
	public WButton(Drawable up, Drawable down, Drawable checked, BitmapFont font,
			int type, int kind, int amount) {
		super(up, down, checked);
		this.font = font;
		this.type = type;
		this.kind = kind;
		this.amount = amount;
	}
	
	public void draw (SpriteBatch batch, float parentAlpha) {
		validate();
		super.draw(batch, parentAlpha);
		font.draw(batch, Integer.toString(amount), getX() + 54, getY() + 55, 100, Align.center, false);
	}
}