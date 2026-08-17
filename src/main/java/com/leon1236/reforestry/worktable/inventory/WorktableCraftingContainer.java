package com.leon1236.reforestry.worktable.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;

import com.leon1236.reforestry.core.recipes.CraftingInputHelper;

public class WorktableCraftingContainer extends SimpleContainer {
	public WorktableCraftingContainer() {
		super(9);
	}

	public WorktableCraftingContainer(Container source) {
		this();
		copyFrom(source);
	}

	public void copyFrom(Container source) {
		int size = Math.min(getContainerSize(), source.getContainerSize());
		for (int slot = 0; slot < size; slot++) {
			ItemStack stack = source.getItem(slot);
			setItem(slot, stack.isEmpty() ? ItemStack.EMPTY : stack.copy());
		}
	}

	public WorktableCraftingContainer copy() {
		return new WorktableCraftingContainer(this);
	}

	public CraftingInput asCraftInput() {
		return CraftingInputHelper.fromGrid(this, 0);
	}
}
