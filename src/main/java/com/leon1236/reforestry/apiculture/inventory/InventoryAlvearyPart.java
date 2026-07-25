package com.leon1236.reforestry.apiculture.inventory;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class InventoryAlvearyPart extends SimpleContainer {
	@FunctionalInterface
	public interface SlotFilter {
		boolean canPlace(int slot, ItemStack stack);
	}

	private final Runnable onChanged;
	private final SlotFilter filter;

	public InventoryAlvearyPart(int size, Runnable onChanged, SlotFilter filter) {
		super(size);
		this.onChanged = onChanged;
		this.filter = filter;
	}

	@Override
	public void setChanged() {
		super.setChanged();
		this.onChanged.run();
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return this.filter.canPlace(slot, stack);
	}

	public boolean hasFreeSlot(int firstSlot, int slotCount) {
		for (int slot = firstSlot; slot < firstSlot + slotCount; slot++) {
			if (getItem(slot).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public boolean storeInFirstEmptySlot(int firstSlot, int slotCount, ItemStack stack) {
		for (int slot = firstSlot; slot < firstSlot + slotCount; slot++) {
			if (getItem(slot).isEmpty()) {
				setItem(slot, stack);
				return true;
			}
		}
		return false;
	}
}
