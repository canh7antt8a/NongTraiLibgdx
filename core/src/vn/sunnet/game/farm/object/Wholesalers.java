package vn.sunnet.game.farm.object;

import java.util.ArrayList;

import vn.sunnet.game.farm.assets.Data;
import vn.sunnet.game.farm.nature.F;
import vn.sunnet.game.farm.nature.SeedNature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Wholesalers extends Customer{

	private int require[][];
	public int mid, len, rid;
	private int amount;
	private String s, srequire[];
	public boolean gc = false;
	
	public Wholesalers(int mid, ArrayList<Table> arrayTable, ArrayList<Experience> arrayExp) {
		super(arrayTable, arrayExp);
		this.mid = mid;
		require = new int[3][3];
		
		animation[0] = createAnimation("khach-buon-b.png");
		animation[1] = createAnimation("khach-buon-a.png");
		srequire = Data.loadMarket(mid).split(":");
		
		//System.out.println("Require: " + Data.loadMarket(mid));
		len = srequire.length/3;
		for(int i = 0; i < len; i++) {
			require[i][0] = Integer.parseInt(srequire[i*3]);
			require[i][1] = Integer.parseInt(srequire[i*3 + 1]);
			require[i][2] = Integer.parseInt(srequire[i*3 + 2]);
		}
		
		pos = new Vector2(1300, 5 + 155 * random.nextInt(arrayTable.size()));
		rid = resetMission(len);
	}
	
	public void update() {			
		switch(state) {
		case STANDING:
			time += Gdx.graphics.getDeltaTime();
			if(time >= pauseTime) {
				time = 0;
				state = MOVING;
				direction = 1 - direction;
			}
			break;
		case MOVING:
			stateTime += Gdx.graphics.getDeltaTime();
			if(stateTime >= totalFrame * frameDuration)
				stateTime = 0;
			
			switch(direction) {
			case LEFT:
				pos.x -= velocity;
				if(pos.x <= range.x) {
					state = STANDING;
					pauseTime = 1;
					
					table = arrayTable.get(id);
					if(table.flag) {
						int delta = require[rid][1] - require[rid][2];
						if(require[rid][0] == table.index && delta > 0) {
							
							type = (require[rid][0] < 12) ? FLOWER : FRUIT;
							switch(type) {
							case FLOWER:
								kind = require[rid][0];
								amount = Data.loadFlWavehouse(kind);
								break;
							case FRUIT:
								kind = require[rid][0] - 12;
								amount = Data.loadFrWavehouse(kind);
								break;
							}
							
							int ctotal = total;
							if(amount < total) {
								ctotal = amount;		
							}
							total -= ctotal;
							
							require[rid][2] += ctotal;
							amount -= ctotal;
							if(amount == 0)
								table.reset();
							
							switch(type) {
							case 0:
								coins = ctotal * SeedNature.getflowerValue(kind);
								F.coins += coins;
								Data.addFlWavehouse(kind, -ctotal);
								break;
							case 1:
								coins = ctotal * SeedNature.getfruitValue(kind);
								F.coins += coins;
								Data.addFrWavehouse(kind, -ctotal);
								break;
							}
							
							s = "";
							for(int i = 0; i < len; i++)
								if(i == len - 1)
									s += require[i][0] + ":" + require[i][1] + ":" + require[i][2];
								else
									s += require[i][0] + ":" + require[i][1] + ":" + require[i][2] + ":";
							
							
							exp = new Experience(1, id, coins);
							arrayExp.add(exp);
							exp = null;
							Data.saveMarket(mid, s);
						}
					}
				}
				break;
			case RIGHT:
				pos.x += velocity;
				if(pos.x >= range.y) {
					state = STANDING;
					rid = resetMission(len);
					pauseTime = random.nextInt(30);
				}
				break;
			}
			break;
		}	
	}
	
	// Tra lai index cua mang require
	public int resetMission(int len) {
		int rid = -1, size, temp = -1;
		size = arrayTable.size();
		int tab[] = reverse(len);
		int wab[] = reverse(size);
		
		//n1 check not full require, n2 check find table index
		boolean n1 = false, n2 = false;		
		for(int i = 0; i < len ; i++) {
			int k = tab[i];
			if(require[k][2] < require[k][1]) {
				n1 = true;
				if(temp == -1) {
					temp = k;
				}				
				//System.out.println("Require not empty: " + k);
				
				for(int j = 0; j < size; j++) {
					table = arrayTable.get(wab[j]);
					if(table.flag && require[k][0] == table.index) {
						n2 = true;
						rid = k;
						id = wab[j];
						break;
					} 
				}
				
				if(n2) {
					break;
				}
			}			
		}		
		
		if(n1) {
			if(!n2) {
				rid = temp;
				id = random.nextInt(size);
			}
			
			pos = new Vector2(1300, 5 + 155 * id);
			index = require[rid][0];			
			total = 1 + random.nextInt(4);			
			
			int delta = require[rid][1] - require[rid][2];
			if(delta < total) {
				total = delta;
			}
		} else {		
			gc = true;
			Data.savesMarket(mid, 3);
		}

		return rid;
	}
	
	//Hàm xóa người mua buôn
	public boolean gc() {
		return gc;
	}
}