package com.leon1236.reforestry.api.core;

import net.minecraft.world.item.ItemStack;

public interface IFilterSlotDelegate {
	boolean canSlotAccept(int slotIndex, ItemStack stack);

	boolean isLocked(int slotIndex);
}
