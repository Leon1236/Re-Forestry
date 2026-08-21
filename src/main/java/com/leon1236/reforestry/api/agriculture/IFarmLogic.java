package com.leon1236.reforestry.api.agriculture;

import java.util.Collection;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IFarmLogic {
	default List<ItemStack> collect(Level level, IFarmHousing farmHousing) {
		return List.of();
	}

	default boolean cultivate(Level level, IFarmHousing farmHousing, BlockPos pos, Direction direction, int extent) {
		return false;
	}

	Collection<ICrop> harvest(Level level, IFarmHousing housing, Direction direction, int extent, BlockPos pos);

	IFarmType getType();

	boolean isManual();
}
