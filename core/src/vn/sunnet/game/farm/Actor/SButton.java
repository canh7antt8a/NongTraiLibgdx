package vn.sunnet.game.farm.Actor;

import vn.sunnet.game.farm.nature.F;
import vn.sunnet.game.farm.nature.SeedNature;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

public class SButton extends Button {

	public int gcost, xcost, value, type, kind, lvUnlock;
	public int experience, growth_time;
	public TextureRegion region;
	public boolean lock;
	private BitmapFont font;
	public String name = "";
	
	public SButton(Drawable up, Drawable down, TextureRegion region, int type, int kind, BitmapFont font) {
		super(up, down, up);
		this.region = region;
		this.type = type;
		this.kind = kind;
		this.font = font;
		
		switch(type) {
		case 0:
			name = SeedNature.flname[kind];
			experience = SeedNature.getflowerExperience(kind);
			growth_time = SeedNature.getflgrowthTime(kind);
			lvUnlock = SeedNature.getflowerUnlock(kind);
			gcost = SeedNature.getgflowerCost(kind);
			xcost = SeedNature.getxflowerCost(kind);
			value = SeedNature.getflowerValue(kind);
			break;
		case 1:
			name = SeedNature.frname[kind];
			experience = SeedNature.getfruitExperience(kind);
			growth_time = SeedNature.getfrgrowthTime(kind);
			lvUnlock = SeedNature.getfruitUnlock(kind);
			gcost = SeedNature.getgfruitCost(kind);
			xcost = SeedNature.getxfruitCost(kind);
			value = SeedNature.getfruitValue(kind);
			break;
		case 2:
			name = SeedNature.tool_name[kind];
			break;
		}		
		
		lock = (F.level >= lvUnlock) ? false : true;
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		validate();
		super.draw(batch, parentAlpha);
		
		if(lock) {
			batch.draw(region, getX(), getY());
		}
		
		font.draw(batch, name, getX() + 20, getY() + 20, 180, Align.center, false);
	}
}