package com.leon1236.reforestry.core.gui;

import java.util.Optional;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SlotGhostCrafting extends Slot {
	public SlotGhostCrafting(Container container, int index, int x, int y) {
		super(container, index, x, y);
	}

	@Override
	public boolean mayPickup(Player player) {
		return false;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}

	@Override
	public int getMaxStackSize(ItemStack stack) {
		return 1;
	}

	@Override
	public ItemStack remove(int amount) {
		return ItemStack.EMPTY;
	}

	@Override
	public Optional<ItemStack> tryRemove(int amount, int maxAmount, Player player) {
		return Optional.empty();
	}

	@Override
	public ItemStack safeTake(int amount, int maxAmount, Player player) {
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack safeInsert(ItemStack stack) {
		return stack;
	}

	@Override
	public ItemStack safeInsert(ItemStack stack, int amount) {
		return stack;
	}

	@Override
	public boolean allowModification(Player player) {
		return false;
	}

	@Override
	public boolean isFake() {
		return true;
	}

	public void setGhost(ItemStack stack) {
		if (stack.isEmpty()) {
			set(ItemStack.EMPTY);
			return;
		}
		set(stack.copyWithCount(1));
	}
}
