package com.leon1236.reforestry.apiculture.multiblock;

import net.minecraft.world.Container;

import com.leon1236.reforestry.api.multiblock.IAlvearyController;
import com.leon1236.reforestry.core.multiblock.IMultiblockControllerInternal;

public interface IAlvearyControllerInternal extends IAlvearyController, IMultiblockControllerInternal {
	Container getInternalInventory();

	int getHealthScaled(int scale);
}
