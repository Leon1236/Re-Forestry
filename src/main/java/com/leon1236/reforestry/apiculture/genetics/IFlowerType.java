package com.leon1236.reforestry.apiculture.genetics;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface IFlowerType extends com.leon1236.reforestry.api.apiculture.IFlowerType {
	@Override
	boolean isAcceptableFlower(Level level, BlockPos pos);
}
