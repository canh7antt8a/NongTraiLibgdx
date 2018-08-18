package vn.sunnet.game.farm.Actor;

import vn.sunnet.game.farm.nature.SeedNature;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

public class RButton extends WButton {
	
	public BitmapFont font2;
	public String name;

	public RButton(Drawable up, Drawable down, Drawable checked, BitmapFont font1, 
			BitmapFont font2, int type, int kind, int amount) {
		super(up, down, checked, font1, type, kind, amount);
		this.font2 = font2;
		
		switch(type) {
		case 0:
			name = SeedNature.flname_[kind];
			break;
		case 1:
			name = SeedNature.frname[kind];
			break;
		case 2:
			name = "Nước thần"; 
			System.out.println("Kind: " + kind);
			break;
		}
	}
	
	public void draw (SpriteBatch batch, float parentAlpha) {
		validate();
		super.draw(batch, parentAlpha);
		font2.draw(batch, name, getX() + 54, getY() + 230, 100, Align.center, false);
	}
}