package com.leon1236.reforestry.farming.farmlogic;

import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.IFarmType;

public abstract class FarmLogicSoil extends FarmLogic {
	public FarmLogicSoil(IFarmType properties, boolean isManual) {
		super(properties, isManual);
	}

	protected boolean isAcceptedSoil(BlockState blockState) {
		return this.type.isAcceptedSoil(blockState);
	}
}
