package com.leaptechjsc.game.happyfarm.object;

import java.util.Random;

import com.leaptechjsc.game.happyfarm.assets.Assets;
import com.leaptechjsc.game.happyfarm.screen.PlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Player {

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	public static final int STANDING = 0, MOVING = 1;
	public static final int NONE = 0, ACTION_PLANT = 1, ACTION_FLUCK = 2, ACTION_WATERING = 3, 
			ACTION_HARVEST = 4;
	
	public PlayScreen s;
	public Texture texture;
	public TextureRegion[] tregion;
	public Animation action_animation[][], moving_animation[];
	public String path = "data/texture/";
	public int o_action, a_action, nof_action, type, kind, PType, p_id;
	public int state = 0, action, direction, value, quadrant;
	public int len, index, currentFrame = 0, totalFrame = 4;
	public float velocity, stateTime = 0, frameDuration1 = 0.15f, frameDuration2 = 0.2f;
	public Vector2 origin1, origin2, cur, des, cell;
	public Array< Vector3> array;
	public Vector3 pos1, pos2, pos3;
	public Vector3 pos, prev;
	public Random random;
	public boolean nextPos, pause = false, hasNextPos = true; //isEmployee
	public String str[] = {"gieo-hat-", "nho-cay-", "tuoi-cay-"};
	
	public Player(PlayScreen s, int PType, int x, int y) {
		this.s = s;
		this.PType = PType;
		
		if(PType == 0) {
			velocity = 4;
			o_action = LEFT;
		} else {
			velocity = 3;
			o_action = RIGHT;
		}
		
		a_action = 0;
		action = NONE;
		state = STANDING;
		
		origin1 = new Vector2(60, 60);
		origin2 = new Vector2(15, 55);
		cur = new Vector2(x, y);
		prev = new Vector3(-1, -1, -1);
		des = new Vector2(x, y);
		cell = new Vector2(208, 150);
		array = new Array<Vector3>();
		random = new Random();
		
		moving_animation = new Animation[4];
		for(int i = 0; i < 4; i++) {
			if(PType == 0)
				moving_animation[i] = createAnimation(path + "nhan-cong-animation-" + i + ".png", totalFrame);
			else
				moving_animation[i] = createAnimation(path + "nhan-cong-animation-" + i + "0.png", totalFrame);
		}
		
		action_animation = new Animation[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(PType == 0)
					texture = Assets.manager.get(path + str[i] + j + ".png", Texture.class);
				else
					texture = Assets.manager.get(path + str[i] + j + "0.png", Texture.class);
				tregion = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
				action_animation[i][j] = new Animation(frameDuration1, tregion);
			}
		}
	}
	
	public Animation createAnimation(String path, int totalFrame) {
		texture = Assets.manager.get(path, Texture.class);
		tregion = TextureRegion.split(texture, texture.getWidth()/totalFrame, texture.getHeight())[0];
		return new Animation(frameDuration1, tregion);
	}
	
	public void render(SpriteBatch batch) {
		Vector2 pos = new Vector2(cur.x + origin2.x, cur.y + origin2.y);
		
		switch(state) {
		case STANDING:
			switch(action) {
			case NONE:
				if(o_action == 3)
					pos.x -= 15;
				hasNextPos = true;
				batch.draw((TextureRegion) moving_animation[o_action].getKeyFrame(0), pos.x, pos.y);
				break;
			case ACTION_PLANT:
				//if(a_action != 2)
					pos.x -= 12;
				if(PType == 1)
					if(s.arrayPlot.get(p_id).flag)
						action = NONE;
				batch.draw((TextureRegion)action_animation[0][a_action].getKeyFrame(stateTime, true), pos.x, pos.y);
				
				break;
			case ACTION_FLUCK:
				if(a_action == 1)
					pos.x -= 15;
				batch.draw((TextureRegion)action_animation[1][a_action].getKeyFrame(stateTime, true), pos.x, pos.y);
				break;
			case ACTION_WATERING:
				if(s.arrayPlot.get(p_id).seed.state != 1)
					action = NONE;
				pos.sub(15, 25);
				batch.draw((TextureRegion)action_animation[2][a_action].getKeyFrame(stateTime, true), pos.x, pos.y);
				break;
			case ACTION_HARVEST:
				if(a_action == 1)
					pos.x -= 15;
				/*if(!s.arrayPlot.get(p_id).flag)
					action = NONE;
				*/
				Plot plot = s.arrayPlot.get(p_id);
				if(plot.flag) {
					if(plot.seed.state != 2) {
						action = NONE;
					}
				} else {
					//System.out.println("Break harvest");
					action = NONE;
				}
				batch.draw((TextureRegion)action_animation[1][a_action].getKeyFrame(stateTime, true), pos.x, pos.y);
				
				break;				
			}
			
			if(action != NONE && !pause)
				update_action();
			break;
		case MOVING:
			if(direction == UP || direction == DOWN)
				pos.x -=  10;
			batch.draw((TextureRegion)moving_animation[direction].getKeyFrame(stateTime, true), pos.x, pos.y);
			if(!pause)
				update();
			break;
		}
	}
	
	public void update_action() {
		stateTime += Gdx.graphics.getDeltaTime();
		if(stateTime > frameDuration2 * 2) {
			nof_action ++;
			stateTime = 0;
			if(nof_action >= 3) {
				s.actionPerform(action, p_id, type, kind);
				nof_action = 0;
				action = NONE;
			}
		}
	}
	
	public void update() {
		stateTime += Gdx.graphics.getDeltaTime();
		if(nextPos)
			if(index < len) {
				pos = array.get(index);
				index ++;
				direction = (int) pos.z;
				nextPos = false;
			} else {
				a_action = changeOrientation(o_action);
				prev = new Vector3(pos1.x, pos1.y, o_action);
				state = STANDING;
				if(action == NONE)
					hasNextPos = true;
			}
		
		switch(direction) {
		case UP:
			cur.y += velocity;
			if(cur.y >= pos.y) {
				cur.y = pos.y;
				nextPos = true;
			}
			break;
		case DOWN:
			cur.y -= velocity;
			if(cur.y <= pos.y) {
				cur.y = pos.y;
				nextPos = true;
			}
			break;
		case LEFT:
			cur.x -= velocity;
			if(cur.x <= pos.x) {
				cur.x = pos.x;
				nextPos = true;
			}
			break;
		case RIGHT:
			cur.x += velocity;
			if(cur.x >= pos.x) {
				cur.x = pos.x;
				nextPos = true;
			}
			break;
		}
	}
	
	public void setDestination(Vector3 pos, int action, int p_id, int type, int kind) {
		this.des = new Vector2(pos.x, pos.y);
		this.action = action;	this.p_id = p_id;
		this.type = type;	this.kind = kind;
		if(PType == 0)
			des.sub(origin1);
		array.clear();
		calculate();
		len = array.size;
		index = 0;
		nof_action = 0;
		nextPos = true;
	}
	
	public void calculate() {
		Vector2 curlie, deslie;
		int ran, xCell1, yCell1, xCell2, yCell2;
		int delXL, delXR, delY, minDistance;
		
		curlie = new Vector2(-1, -1);
		deslie = new Vector2(-1, -1);
		
		xCell1 = (int) (des.x/cell.x);
		yCell1 = (int) (des.y/cell.y);
		xCell1 = (xCell1 > 3) ? 3 : xCell1;
		yCell1 = (yCell1 > 2) ? 2 : yCell1;
		
		if(yCell1 == 0) {
			if(xCell1 == 0) {
				delXR = (int) (cell.x - des.x);
				delY = (int) (cell.y - des.y);
				if(delXR < delY) {
					o_action = LEFT;
					pos1 = new Vector3(cell.x, cell.y/2, -1);
				}
				else {
					o_action = DOWN;
					pos1 = new Vector3(cell.x/2, cell.y, -1);
				}
			} else if(xCell1 == 3) {
				delXL = (int) (des.x - xCell1 * cell.x);
				delY = (int) ((yCell1 + 1) * cell.y - des.y);
				minDistance = (delXL < delY) ? delXL : delY;
				if(minDistance == delXL) {
					o_action = RIGHT;
					pos1 = new Vector3(xCell1 * cell.x, (yCell1 + 0.5f) * cell.y, -1);
				}
				else {
					o_action = DOWN;
					pos1 = new Vector3((xCell1 + 0.5f) * cell.x, (yCell1 + 1) * cell.y, -1);
				}				
			} else {
				pos1 = generalPos(xCell1, yCell1);
			}
		} else if(yCell1 == 2) {
			if(xCell1 == 0) {
				o_action = LEFT;
				pos1 = new Vector3(cell.x, (yCell1 + 0.5f) * cell.y, -1);
			} else if(xCell1 == 3) {
				o_action = RIGHT;
				pos1 = new Vector3(xCell1 * cell.x, (yCell1 + 0.5f) * cell.y, -1);
			} else {
				delXL = (int) ((xCell1 + 1) * cell.x - des.x);
				delXR = (int) (des.x - xCell1 * cell.x);
				minDistance = (delXL < delXR) ? delXL : delXR;
				if(minDistance == delXL) {
					o_action = LEFT;
					pos1 = new Vector3((xCell1 + 1) * cell.x, (yCell1 + 0.5f) * cell.y, -1);
				}
				else {
					o_action = RIGHT;
					pos1 = new Vector3(xCell1 * cell.x, (yCell1 + 0.5f) * cell.y, -1);
				}
			}
		} else {
			if(xCell1 == 0) {
				delXR = (int) (cell.x - des.x);
				delY = (int) ((int) (yCell1 + 1) * cell.y - des.y);
				minDistance = (delXR < delY) ? delXR : delY;
				if(minDistance == delXR) {
					o_action = LEFT;
					pos1 = new Vector3(cell.x, (yCell1 + 0.5f) * cell.y, -1);
				}
				else {
					o_action = DOWN;
					pos1 = new Vector3(cell.x/2, (yCell1 + 1) * cell.y, -1);
				}
			} else if(xCell1 == 3) {
				delXL = (int) (des.x - xCell1 * cell.x);
				delY = (int) ((yCell1 + 1) * cell.y - des.y);
				minDistance = (delXL < delY) ? delXL : delY;
				if(minDistance == delXL) {
					o_action = RIGHT;
					pos1 = new Vector3(xCell1 * cell.x, (yCell1 + 0.5f) * cell.y, -1);
				}
				else {
					o_action = DOWN;
					pos1 = new Vector3((xCell1 + 0.5f) * cell.x, (yCell1 + 1) * cell.y, -1);
				}
			} else {
				pos1 = generalPos(xCell1, yCell1);
			}
		}
		
		//System.out.println("Pos11: " + pos1.x + " " + pos1.y + " " + pos1.z);
		Vector3 v = new Vector3(pos1.x, pos1.y, o_action);
		if(v.equals(prev)) {
			//state = STANDING;
			//System.out.println("Prev: " + prev.x + " " + prev.y + " " + prev.z);
		}
		else
			state = MOVING;
		
		xCell2 = (int) (cur.x/cell.x);
		yCell2 = (int) (cur.y/cell.y);
		xCell2 = (xCell2 > 3) ? 3 : xCell2;
		yCell2 = (yCell2 > 2) ? 2 : yCell2;
		
		if(pos1.y == cur.y && pos1.y % cell.y == 0) {
			if(pos1.x > cur.x)
				pos1.z = RIGHT;
			else
				pos1.z = LEFT;
			array.add(pos1);
		} else if(pos1.x == cur.x && pos1.x % cell.x == 0) {
			if(pos1.y > cur.y)
				pos1.z = UP;
			else
				pos1.z = DOWN;
			array.add(pos1);
		} else {
			if(pos1.y % cell.y == 0)
				deslie.x = 1;
			if(pos1.x % cell.x == 0)
				deslie.y = 1;
			if(cur.y % cell.y == 0)
				curlie.x = 1;
			if(cur.x % cell.x == 0)
				curlie.y = 1;
			
			if(deslie.x == 1 && curlie.x == 1 && deslie.y == -1 && curlie.y == -1)
				quadrant = 0;
			else if(deslie.y == 1 && curlie.y == 1 && deslie.x == -1 && curlie.x == -1)
				quadrant = 1;
			else
				quadrant = 2;
			
			//System.out.println("Quadrant: " + quadrant);
			/** quadrant = 0: Nam tren 2 hang khac nhau
			 *  quadrant = 1: Nam tren 2 cot khac nhau
			 *  quadrant = 2: Nam tren 1 hang va 1 cot
			 */
			
			switch(quadrant) {
			case 0:
				if(pos1.x > cur.x) {
					if(pos1.y > cur.y) {
						pos1.z = RIGHT;
						pos2 = new Vector3(pos1.x - cell.x/2, pos1.y, UP);
						pos3 = new Vector3(pos1.x - cell.x/2, cur.y, RIGHT);
					} else {
						pos1.z = RIGHT;
						pos2 = new Vector3(pos1.x - cell.x/2, pos1.y, DOWN);
						pos3 = new Vector3(pos1.x - cell.x/2, cur.y, RIGHT);
					}
				} else if(pos1.x < cur.x) {
					if(pos1.y > cur.y) {
						pos1.z = LEFT;
						pos2 = new Vector3(pos1.x + cell.x/2, pos1.y, UP);
						pos3 = new Vector3(pos1.x + cell.x/2, cur.y, LEFT);
					} else {
						pos1.z = LEFT;
						pos2 = new Vector3(pos1.x + cell.x/2, pos1.y, DOWN);
						pos3 = new Vector3(pos1.x + cell.x/2, cur.y, LEFT);
					}
				} else {
					ran = random.nextInt(2);
					if(pos1.y > cur.y) {
						if(ran == 0) {
							pos1.z = RIGHT;
							pos2 = new Vector3(pos1.x - cell.x/2, pos1.y, UP);
							pos3 = new Vector3(pos1.x - cell.x/2, cur.y, LEFT);
						} else {
							pos1.z = LEFT;
							pos2 = new Vector3(pos1.x + cell.x/2, pos1.y, UP);
							pos3 = new Vector3(pos1.x + cell.x/2, cur.y, RIGHT);
						}
					} else {
						if(ran == 0) {
							pos1.z = RIGHT;
							pos2 = new Vector3(pos1.x - cell.x/2, pos1.y, DOWN);
							pos3 = new Vector3(pos1.x - cell.x/2, cur.y, LEFT);
						} else {
							pos1.z = LEFT;
							pos2 = new Vector3(pos1.x + cell.x/2, pos1.y, DOWN);
							pos3 = new Vector3(pos1.x + cell.x/2, cur.y, RIGHT);
						}
					}
				}
				
				array.add(pos3);
				array.add(pos2);
				array.add(pos1);
				break;
			case 1:
				if(pos1.y > cur.y) {
					if(pos1.x > cur.x) {
						pos1.z = UP;
						pos2 = new Vector3(pos1.x, pos1.y - cell.y/2, RIGHT);
						pos3 = new Vector3(cur.x, pos1.y - cell.y/2, UP);
					} else {
						pos1.z = UP;
						pos2 = new Vector3(pos1.x, pos1.y - cell.y/2, LEFT);
						pos3 = new Vector3(cur.x, pos1.y - cell.y/2, UP);
					}
				} else if(pos1.y < cur.y) {
					if(pos1.x > cur.x) {
						pos1.z = DOWN;
						pos2 = new Vector3(pos1.x, pos1.y + cell.y/2, RIGHT);
						pos3 = new Vector3(cur.x, pos1.y + cell.y/2, DOWN);
					} else {
						pos1.z = DOWN;
						pos2 = new Vector3(pos1.x, pos1.y + cell.y/2, LEFT);
						pos3 = new Vector3(cur.x, pos1.y + cell.y/2, DOWN);
					}
				} else {
					ran = random.nextInt(2);
					ran = 1;
					if(pos1.x > cur.x) {
						if(ran == 0) {
							pos1.z = DOWN;
							pos2 = new Vector3(pos1.x, pos1.y + cell.y/2, RIGHT);
							pos3 = new Vector3(cur.x, pos1.y + cell.y/2, UP);
						} else {
							pos1.z = UP;
							pos2 = new Vector3(pos1.x, pos1.y - cell.y/2, RIGHT);
							pos3 = new Vector3(cur.x, pos1.y - cell.y/2, DOWN);
						}
					} else {
						if(ran == 0) {
							pos1.z = DOWN;
							pos2 = new Vector3(pos1.x, pos1.y + cell.y/2, LEFT);
							pos3 = new Vector3(cur.x, pos1.y + cell.y/2, UP);
						} else {
							pos1.z = UP;
							pos2 = new Vector3(pos1.x, pos1.y - cell.y/2, LEFT);
							pos3 = new Vector3(cur.x, pos1.y - cell.y/2, DOWN);
						}
					}
				}
				
				array.add(pos3);
				array.add(pos2);
				array.add(pos1);
				break;
			case 2:
				if(deslie.x == 1) {
					if(pos1.x > cur.x) {
						if(pos1.y > cur.y) {
							pos1.z = RIGHT;
							pos2 = new Vector3(cur.x, pos1.y, UP);
						} else {
							pos1.z = RIGHT;
							pos2 = new Vector3(cur.x, pos1.y, DOWN);
						}
					} else {
						if(pos1.y > cur.y) {
							pos1.z = LEFT;
							pos2 = new Vector3(cur.x, pos1.y, UP);
						} else {
							pos1.z = LEFT;
							pos2 = new Vector3(cur.x, pos1.y, DOWN);
						}
					}
				} else {
					if(pos1.x > cur.x) {
						if(pos1.y > cur.y) {
							pos1.z = UP;
							pos2 = new Vector3(pos1.x, cur.y, RIGHT);
						} else {
							pos1.z = DOWN;
							pos2 = new Vector3(pos1.x, cur.y, RIGHT);
						}
					} else {
						if(pos1.y > cur.y) {
							pos1.z = UP;
							pos2 = new Vector3(pos1.x, cur.y, LEFT);
						} else {
							pos1.z = DOWN;
							pos2 = new Vector3(pos1.x, cur.y, LEFT);
						}
					}
				}
				
				array.add(pos2);
				array.add(pos1);
				break;
			}
		}
	}
	
	public int changeOrientation(int orien) {
		int n;
		
		if(orien == LEFT)
			n = 0;
		else if(orien == RIGHT)
			n = 1;
		else
			n = 2;
		return n;
	}
	
	public Vector3 generalPos(int xCell, int yCell) {
		int delXL, delXR, delY, minDistance;
		
		delXL = (int) (des.x - xCell * cell.x);
		delXR = (int) ((xCell + 1) * cell.x - des.x);
		delY = (int) ((yCell + 1) * cell.y - des.y);
		minDistance = (delXL < delXR) ? delXL : delXR;
		minDistance = (minDistance < delY) ? minDistance : delY;
		if(minDistance == delXL) {
			o_action = RIGHT;
			pos = new Vector3(xCell * cell.x, (yCell + 0.5f) * cell.y, -1);
		}
		else if(minDistance == delXR) {
			o_action = LEFT;
			pos = new Vector3((xCell + 1) * cell.x, (yCell + 0.5f) * cell.y, -1);
		}
		else {
			o_action = DOWN;
			pos = new Vector3((xCell + 0.5f) * cell.x, (yCell + 1) * cell.y, -1);
		}
		
		return pos;
	}
}