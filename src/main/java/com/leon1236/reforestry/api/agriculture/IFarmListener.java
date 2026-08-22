package com.leon1236.reforestry.api.agriculture;

import java.util.Collection;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

public interface IFarmListener {
	default boolean beforeCropHarvest(ICrop crop) {
		return false;
	}

	default void afterCropHarvest(List<ItemStack> harvested, ICrop crop) {
	}

	default void hasCollected(List<ItemStack> collected, IFarmLogic logic) {
	}

	default void hasCultivated(IFarmLogic logic, BlockPos pos, Direction direction, int extent) {
	}

	default void hasScheduledHarvest(Collection<ICrop> harvested, IFarmLogic logic, BlockPos pos, Direction direction, int extent) {
	}

	default boolean cancelTask(IFarmLogic logic, Direction direction) {
		return false;
	}
}
