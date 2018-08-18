package vn.sunnet.game.farm.object;

import java.util.ArrayList;

import vn.sunnet.game.farm.assets.Data;
import vn.sunnet.game.farm.nature.F;
import vn.sunnet.game.farm.nature.SeedNature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class RetailCustomer extends Customer {

	private int cType, amount;
	
	public RetailCustomer(ArrayList<Table> arrayTable, ArrayList<Experience> arrayExp) {
		super(arrayTable, arrayExp);
		
		cType = random.nextInt(2);
		switch(cType) {
		case 0:
			animation[0] = createAnimation("khach-le-a.png");
			animation[1] = createAnimation("khach-le-b.png");
			break;
		case 1:
			animation[0] = createAnimation("khach-le-2b.png");
			animation[1] = createAnimation("khach-le-2c.png");
			break;
		}
		
		index = resetMission();
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
					if(table.flag && table.index == index) {
						type = (index < 12) ? FLOWER : FRUIT;
						
						switch(type) {
						case FLOWER:
							kind = index;
							amount = Data.loadFlWavehouse(index);
							break;
						case FRUIT:
							kind = index - 12;
							amount = Data.loadFrWavehouse(index - 12);
							break;
						}
						
						int ctotal = total;
						if(amount < total) {
							ctotal = amount;		
						}
						
						total -= ctotal;
						amount -= ctotal;
						if(amount == 0) {
							table.reset();
						}
						
						switch(type) {
						case FLOWER:
							coins = ctotal * SeedNature.getflowerValue(kind);
							F.coins += coins;
							Data.addFlWavehouse(index, -ctotal);
							break;
						case FRUIT:								
							Data.addFrWavehouse(index - 12, -ctotal);
							coins = ctotal * SeedNature.getfruitValue(kind);
							F.coins += coins;
							break;
						}
						
						exp = new Experience(1, id, coins);
						arrayExp.add(exp);
						exp = null;
					}
				}
				break;
			case RIGHT:
				pos.x += velocity;
				if(pos.x >= range.y) {
					index = resetMission();
					state = STANDING;
					pauseTime = random.nextInt(30);
				}
				break;
			}
			break;
		}	
	}
	
	public int resetMission() {
		int lvUnlock, count, array[];
		boolean flag;
		array = new int[24];
		
		count = 0;
		for(int i = 0; i < 12; i++) {
			lvUnlock = SeedNature.getflowerUnlock(i);
			flag = (F.level >= lvUnlock) ? false : true;			
			if(!flag) {
				array[count] = i;
				count ++;
			}
			
			lvUnlock = SeedNature.getfruitUnlock(i);
			flag = (F.level >= lvUnlock) ? false : true;			
			if(!flag) {
				array[count] = i + 12;
				count ++;
			}
		}	
		
		flag = false;		
		if(count < 6) {
			index = array[random.nextInt(count)];
		} else {		
			index = array[(count - 6) + random.nextInt(6)];
		}
		
		int size = arrayTable.size();
		int tab[] = reverse(size);
		
		for(int i = 0; i < size; i++) {
			table = arrayTable.get(tab[i]);
			if(table.flag && table.index == index) {
				id = tab[i];
				flag = true;
				pos = new Vector2(1300, 5 + 155 * id);
				break;
			}
		}
		
		if(!flag) {
			id = random.nextInt(size);
			pos = new Vector2(1300, 5 + 155 * id);
		}
		
		total = 1 + random.nextInt(2);
		return index;
	}
	
	public void back() {
		state = MOVING;
		direction = RIGHT;
	}
}