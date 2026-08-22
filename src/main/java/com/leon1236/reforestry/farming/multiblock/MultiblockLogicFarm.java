package com.leon1236.reforestry.farming.multiblock;

import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.multiblock.IMultiblockLogicFarm;
import com.leon1236.reforestry.core.multiblock.MultiblockLogic;

public class MultiblockLogicFarm extends MultiblockLogic<IFarmControllerInternal> implements IMultiblockLogicFarm {
	public MultiblockLogicFarm() {
		super(IFarmControllerInternal.class);
	}

	@Override
	public IFarmControllerInternal getController() {
		return this.controller == null ? FakeFarmController.INSTANCE : this.controller;
	}

	@Override
	public IFarmControllerInternal createNewController(Level level) {
		return new FarmController(level);
	}
}
