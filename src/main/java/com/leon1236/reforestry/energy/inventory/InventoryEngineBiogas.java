package com.leon1236.reforestry.energy.inventory;

import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;

import com.leon1236.reforestry.core.fluids.FluidContainerHelper;

public final class InventoryEngineBiogas {
	public static final int SLOT_CAN = 0;
	public static final int SLOT_COUNT = 1;

	private InventoryEngineBiogas() {
	}

	public static boolean canSlotAccept(int slotIndex, ItemStack stack, Storage<FluidVariant> tanks) {
		if (slotIndex != SLOT_CAN || stack.isEmpty()) {
			return false;
		}
		return FluidContainerHelper.isFilledContainer(stack)
				&& FluidContainerHelper.canTankAccept(tanks, FluidContainerHelper.fluidIn(stack));
	}

	public static boolean canTakeItemThroughFace(int slotIndex) {
		return slotIndex == SLOT_CAN;
	}
}
