package vn.sunnet.game.farm.object;

import com.badlogic.gdx.math.Vector2;

public class Plot {
	public int id, level;
	public Vector2 pos1, pos2, pos3;
	public Seed seed;
	public boolean flag = false;
	
	public Plot(int id, int level) {
		this.id = id;
		this.level = level;
		pos1 = new Vector2(75 + 210 * ((id%12)%4), 371 - 153 * ((id%12)/4));
		pos2 = new Vector2(pos1.x - 10, pos1.y - 5);	
		pos3 = new Vector2(pos1.x - 20, pos1.y - 13);
	}	
	
	public void upgrade() {
		if(level < 5)
			level ++;
	}
	
	public void assignSeed(Seed seed) {
		this.seed = seed;
		flag = true;
	}
	
	public void assignEmpty() {
		this.seed = null;
		flag = false;
	}
}