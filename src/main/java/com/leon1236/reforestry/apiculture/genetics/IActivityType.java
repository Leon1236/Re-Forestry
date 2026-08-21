package com.leon1236.reforestry.apiculture.genetics;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public interface IActivityType extends com.leon1236.reforestry.api.apiculture.IActivityType {
	static long getBeeDayTime(Level level) {
		return com.leon1236.reforestry.api.apiculture.IActivityType.getBeeDayTime(level);
	}

	static long getBeeDayTime(LevelAccessor level) {
		return com.leon1236.reforestry.api.apiculture.IActivityType.getBeeDayTime(level);
	}
}
