package com.leon1236.reforestry.worktable.gui;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.worktable.tiles.TileWorktable;

public class SlotWorktableResult extends Slot {
	private final TileWorktable tile;
	private final Player player;
	private int amountCrafted;

	public SlotWorktableResult(Player player, TileWorktable tile, Container display, int slot, int x, int y) {
		super(display, slot, x, y);
		this.tile = tile;
		this.player = player;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}

	@Override
	protected void onQuickCraft(ItemStack stack, int amount) {
		this.amountCrafted += amount;
		checkTakeAchievements(stack);
	}

	@Override
	protected void checkTakeAchievements(ItemStack stack) {
		if (this.amountCrafted > 0) {
			stack.onCraftedBy(this.player, this.amountCrafted);
		}
		this.amountCrafted = 0;
	}

	@Override
	public ItemStack remove(int amount) {
		if (!hasItem()) {
			return ItemStack.EMPTY;
		}
		return getItem();
	}

	@Override
	public boolean mayPickup(Player player) {
		return this.tile.mayPickup(getContainerSlot());
	}

	@Override
	public ItemStack getItem() {
		return this.tile.getResult(this.player.level());
	}

	@Override
	public boolean hasItem() {
		return !getItem().isEmpty() && this.tile.mayPickup(getContainerSlot());
	}

	@Override
	public void onTake(Player player, ItemStack stack) {
		if (this.tile.onCraftingStart(this.player)) {
			checkTakeAchievements(stack);
			this.tile.onCraftingComplete(this.player);
		}
	}
}
