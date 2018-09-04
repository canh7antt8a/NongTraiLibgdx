package com.leaptechjsc.game.happyfarm.object;

import com.leaptechjsc.game.happyfarm.nature.SeedNature;

public class Flower extends Seed {
	
	public Flower(int level, int id, int kind, boolean fresh) {
		super(id);
		type = 0;
		this.kind = kind;		
		value = SeedNature.getflowerValue(kind);
		experience = SeedNature.getflowerExperience(kind);
		growth_period = SeedNature.getflowerNature(kind);
		if(level > 0) {
			changeGR_period(level);
		}
		
		if(fresh) {
			needWatering = random.nextBoolean();
			if(needWatering) {
				int time = 0;
				for(int i = 0; i < 2; i++) {
					time += growth_period[0];
				}
				wTime = 2 + random.nextInt((int) growth_period[0]);
				cTime = (float) (time * 0.7);				
			}
		}
		
		initialAnimation("flower/" + SeedNature.flower[kind]);
	}
}