package com.leon1236.reforestry.apiculture.multiblock;

import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.multiblock.IMultiblockLogicAlveary;
import com.leon1236.reforestry.core.multiblock.MultiblockLogic;

public class MultiblockLogicAlveary extends MultiblockLogic<IAlvearyControllerInternal> implements IMultiblockLogicAlveary {
	public MultiblockLogicAlveary() {
		super(IAlvearyControllerInternal.class);
	}

	@Override
	public IAlvearyControllerInternal getController() {
		return this.controller == null ? FakeAlvearyController.INSTANCE : this.controller;
	}

	@Override
	public IAlvearyControllerInternal createNewController(Level level) {
		return new AlvearyController(level);
	}
}
