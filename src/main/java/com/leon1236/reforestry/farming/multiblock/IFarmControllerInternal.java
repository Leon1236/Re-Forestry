package com.leon1236.reforestry.farming.multiblock;

import net.minecraft.world.Container;

import com.leon1236.reforestry.api.multiblock.IFarmController;
import com.leon1236.reforestry.core.circuits.ISocketable;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.multiblock.IMultiblockControllerInternal;
import com.leon1236.reforestry.farming.gui.IFarmLedgerDelegate;

public interface IFarmControllerInternal extends IFarmController, IMultiblockControllerInternal, ISocketable {
	IFarmLedgerDelegate getFarmLedgerDelegate();

	Container getInternalInventory();

	FilteredFluidStorage getWaterTank();
}
