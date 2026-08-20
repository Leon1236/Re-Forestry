package com.leon1236.reforestry.energy.inventory;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.fuels.FuelManager;

public final class InventoryEnginePeat {
	public static final int SLOT_FUEL = 0;
	public static final int SLOT_WASTE_1 = 1;
	public static final int SLOT_WASTE_COUNT = 4;
	public static final int SLOT_COUNT = SLOT_WASTE_1 + SLOT_WASTE_COUNT;

	private InventoryEnginePeat() {
	}

	public static boolean canSlotAccept(int slotIndex, ItemStack stack) {
		return slotIndex == SLOT_FUEL && FuelManager.getPeatEngineFuel(stack) != null;
	}

	public static boolean canTakeItemThroughFace(int slotIndex) {
		return slotIndex >= SLOT_WASTE_1 && slotIndex < SLOT_WASTE_1 + SLOT_WASTE_COUNT;
	}

	public static boolean isWasteSlot(int slotIndex) {
		return canTakeItemThroughFace(slotIndex);
	}
}
