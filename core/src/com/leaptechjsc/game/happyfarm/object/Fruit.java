package com.leaptechjsc.game.happyfarm.object;

import com.leaptechjsc.game.happyfarm.nature.SeedNature;

public class Fruit extends Seed{

	public Fruit(int level, int id, int kind, boolean fresh) {
		super(id);
		type = 1;
		this.kind = kind;		
		value = SeedNature.getfruitValue(kind);
		experience = SeedNature.getfruitExperience(kind);
		growth_period = SeedNature.getfruitNature(kind);
		if(level > 0) {
			changeGR_period(level);
		}
		
		if(fresh) {
			needWatering = random.nextBoolean();
			if(needWatering) {
				int time = (int) (2 * growth_period[0]);
				wTime = 2 + random.nextInt((int) growth_period[0]);
				cTime = (float) (time * 0.7);
			}
		}
		
		initialAnimation("fruit/" + SeedNature.fruit[kind]);
	}
}