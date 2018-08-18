package vn.sunnet.game.farm.object;

import java.util.ArrayList;

import vn.sunnet.game.farm.assets.Data;
import vn.sunnet.game.farm.screen.PlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Employee {

	private static final int NONE = 0, ACTION_PLANT = 1, ACTION_WATERING = 3, ACTION_HARVEST = 4;
	private static final int FLOWER = 0, FRUIT = 1;
	public int type, kind, action, i = 0, j = 0, PPGarden, size;
	private int currentGarden = 0;
	private ArrayList<Plot> arrayPlot;
	private Vector2 cell;
	private Player e;
	private PlayScreen s;
	public boolean gc = false, reverse = false, pause = false, thanhbq;
	public int pidwasOccupied, p_id, index;
	
	public Employee(PlayScreen s, int x, int y) {
		this.s = s;
		arrayPlot = s.arrayPlot;
		e = new Player(s, 1, x, y);
		cell = new Vector2(208, 150);
		PPGarden = (arrayPlot.size() > 12) ? 12 : arrayPlot.size();
		size = arrayPlot.size();
		
		int n = Data.loadEmployee();
		if(n != -1) {
			thanhbq(n);
		}
		
		Gdx.app.log("Message", "AddEmployee: " + e.velocity);
	}
	
	//size: So thua ruong tren manh dat hien tai
	public void render(SpriteBatch batch) {
		e.render(batch);
		pidwasOccupied = s.pidwasOccupied();		
	}
	
	public void update() {
		if(!pause) {
			if(pidwasOccupied != -1) {
				if(p_id == pidwasOccupied) {
					e.hasNextPos = true;
				}
			}
			
			if(e.hasNextPos) {
				if(PPGarden > 8)
					j = caculate(i);
				else
					j = i;
				p_id = j + 12 * currentGarden;
				
				if(p_id > size - 1)
					return;
				
				if(p_id != pidwasOccupied) {
					action = actionPPlot(p_id);
					
					switch(action) {
					case NONE:
						break;
					case ACTION_WATERING:
						setDestination(ACTION_WATERING, p_id, -1, -1);
						break;
					case ACTION_HARVEST:
						setDestination(ACTION_HARVEST, p_id, -1, -1);
						if(reverse)   i ++;
						else	i --;
						break;
					case ACTION_PLANT:
						setDestination(ACTION_PLANT, p_id, type, kind);
						break;
					}
				} else {
					e.action = NONE;
				}
				
				if(reverse) {
					i --;
					if(i < 0) {
						i = 0;
						reverse = false;
					}
				} else {
					i++;
					if(i > PPGarden - 1) {
						i = PPGarden - 1;
						reverse = true;
					}
				}
			}
		}
	}
	
	public void setDestination(int action, int id, int type, int kind) {
		Vector3 pos;
		pos = new Vector3(102 + cell.x * ((id%12)%4), 375 - cell.y * ((id%12)/4), 0);
		e.setDestination(pos, action, id, type, kind);
		e.hasNextPos = false;
	}
	
	// Lay action can thuc hien tren 1 thua ruong
	public int actionPPlot(int p_id) {
		int act = NONE;
		Plot p = arrayPlot.get(p_id);
		
		if(p.flag) {
			int state;
			state = p.seed.state();
			switch(state) {
			case 0:		act = NONE;
				break;
			case 1:		act = ACTION_WATERING;
				break;					
			case 2:		act = ACTION_HARVEST;
				break;
			}			
		} else {
			int total = 0;
			if(thanhbq) {
				switch(type) {
				case 0:
					total = Data.loadFlRepository(kind);
					break;
				case 1:
					total = Data.loadFrRepository(kind);
					break;
				}
				
				if(total <= 0) {
					thanhbq = false;
					act = NONE;
				} else {
					act = ACTION_PLANT;
				}
				
				//System.out.println("TYPE: " + type + " KIND: " + kind);
			} else {
				int n = getSKind();
				 
				if(n != -1) {
					if(n < 12) {
						type = FLOWER;
						kind = n;
					}
					else {
						type = FRUIT;
						kind = n - 12;
					}
					
					act = ACTION_PLANT;
				}	
			}						
		}
		
		return act;
	}
	
	public int caculate(int i) {
		if(i >= 4 && i <= 7)
			i = 11 - i;		
		return i;
	}
	
	public int getSKind() {
		for(int i = 0; i < 12; i++) {
		    int amount = Data.loadFlRepository(i);
		    if(amount > 0)
		    	return i;
		   }
		    
		 for(int i = 0; i < 12; i++) {
		    int amount = Data.loadFrRepository(i);
		    if(amount > 0) {
		    	return i + 12;
		    }
		 }
		 
		 return -1;
	}	
	
	public void updateGarden(int currentGarden) {
		this.currentGarden = currentGarden;
		e.action = NONE;
		size = arrayPlot.size();
		PPGarden = (size - currentGarden * 12 > 12) ? 12 : size - currentGarden * 12;
		if(i > PPGarden - 1) {
			i = 0;
		}
	}
	
	public void thanhbq(int index) {
		this.index = index;
		
		if(index < 12) {
			type = 0;
			kind = index;
		} else {
			type = 1;
			kind = index - 12;
		}
		
		thanhbq = true; 
	}
	
	public void resetAction() {
		e.action = NONE;
	}
	
	public void pause_() {
		e.pause = true;
		pause = true;
	}
	
	public void resume_() {
		e.pause = false;
		pause = false;
	}
	
	public boolean garbageCollection() {
		return gc;
	}
}
