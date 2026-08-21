package com.leon1236.reforestry.apiculture.genetics;

import net.minecraft.world.level.Level;

public interface IActivityType extends com.leon1236.reforestry.api.apiculture.IActivityType {
	static long getBeeDayTime(Level level) {
		return com.leon1236.reforestry.api.apiculture.IActivityType.getBeeDayTime(level);
	}
}
