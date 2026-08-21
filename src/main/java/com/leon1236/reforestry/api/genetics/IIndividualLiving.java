package com.leon1236.reforestry.api.genetics;

import net.minecraft.world.level.Level;

public interface IIndividualLiving extends IIndividual {
	int getHealth();

	void setHealth(int health);

	int getMaxHealth();

	void age(Level level, float ageStep);

	boolean isAlive();

	@Override
	IIndividualLiving copy();
}
