package com.leon1236.reforestry.storage.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;

public final class BackpackInventoryHelper {
	private BackpackInventoryHelper() {
	}

	public static ItemStack insertItemStacked(Container inventory, ItemStack stack) {
		if (stack.isEmpty()) {
			return ItemStack.EMPTY;
		}
		ItemStack remaining = stack.copy();
		if (remaining.isStackable()) {
			for (int i = 0; i < inventory.getContainerSize(); i++) {
				if (remaining.isEmpty()) {
					break;
				}
				ItemStack slot = inventory.getItem(i);
				if (slot.isEmpty() || !ItemStack.isSameItemSameComponents(slot, remaining)) {
					continue;
				}
				remaining = insertIntoSlot(inventory, i, remaining);
			}
		}
		if (!remaining.isEmpty()) {
			for (int i = 0; i < inventory.getContainerSize(); i++) {
				if (remaining.isEmpty()) {
					break;
				}
				if (inventory.getItem(i).isEmpty()) {
					remaining = insertIntoSlot(inventory, i, remaining);
				}
			}
		}
		return remaining;
	}

	private static ItemStack insertIntoSlot(Container inventory, int slot, ItemStack stack) {
		if (!inventory.canPlaceItem(slot, stack)) {
			return stack;
		}
		ItemStack existing = inventory.getItem(slot);
		int max = Math.min(inventory.getMaxStackSize(), stack.getMaxStackSize());
		if (existing.isEmpty()) {
			int toInsert = Math.min(stack.getCount(), max);
			inventory.setItem(slot, stack.copyWithCount(toInsert));
			ItemStack remaining = stack.copy();
			remaining.shrink(toInsert);
			return remaining.isEmpty() ? ItemStack.EMPTY : remaining;
		}
		if (!ItemStack.isSameItemSameComponents(existing, stack)) {
			return stack;
		}
		int space = max - existing.getCount();
		if (space <= 0) {
			return stack;
		}
		int toInsert = Math.min(stack.getCount(), space);
		existing.grow(toInsert);
		inventory.setItem(slot, existing);
		ItemStack remaining = stack.copy();
		remaining.shrink(toInsert);
		return remaining.isEmpty() ? ItemStack.EMPTY : remaining;
	}
}
