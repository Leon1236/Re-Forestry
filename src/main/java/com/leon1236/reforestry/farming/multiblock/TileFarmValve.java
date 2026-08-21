package com.leon1236.reforestry.farming.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.farming.features.FarmingTiles;

public class TileFarmValve extends TileFarm {
	public TileFarmValve(BlockPos pos, BlockState state) {
		super(FarmingTiles.VALVE.type(), pos, state);
	}

	public FilteredFluidStorage getTank() {
		return getMultiblockLogic().getController().getWaterTank();
	}
}
