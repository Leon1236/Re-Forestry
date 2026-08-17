package com.leon1236.reforestry.storage.gui;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.storage.features.StorageMenuTypes;
import com.leon1236.reforestry.storage.inventory.ItemInventoryBackpack;
import com.leon1236.reforestry.storage.items.ItemBackpack;

public class ContainerBackpack extends AbstractContainerMenu {
	public enum Size {
		DEFAULT(3, 5, 44, 19),
		T2(5, 9, 8, 8);

		final int rows;
		final int columns;
		final int startX;
		final int startY;

		Size(int rows, int columns, int startX, int startY) {
			this.rows = rows;
			this.columns = columns;
			this.startX = startX;
			this.startY = startY;
		}

		public int getSize() {
			return this.rows * this.columns;
		}
	}

	private final Size size;
	private final ItemInventoryBackpack inventory;
	private final int lockedHotbarSlot;

	public static ContainerBackpack fromNetwork(int windowId, Inventory playerInv, BackpackMenuData data) {
		Player player = playerInv.player;
		InteractionHand hand = data.mainHand() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
		Size size = data.woven() ? Size.T2 : Size.DEFAULT;
		return new ContainerBackpack(windowId, player, size, hand);
	}

	public ContainerBackpack(int windowId, Player player, Size size, InteractionHand hand) {
		super(StorageMenuTypes.BACKPACK.type(), windowId);
		this.size = size;
		this.inventory = new ItemInventoryBackpack(player, hand, size.getSize());
		this.lockedHotbarSlot = hand == InteractionHand.MAIN_HAND
				? player.getInventory().getSelectedSlot()
				: -1;

		for (int row = 0; row < size.rows; row++) {
			for (int column = 0; column < size.columns; column++) {
				int slot = column + row * size.columns;
				addSlot(new FilteredSlot(this.inventory, slot, size.startX + column * 18, size.startY + row * 18));
			}
		}
		addPlayerInventory(player.getInventory(), 8, 11 + size.startY + size.rows * 18);
	}

	public Size getSize() {
		return this.size;
	}

	private void addPlayerInventory(Inventory playerInventory, int left, int top) {
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlot(new Slot(playerInventory, column + row * 9 + 9, left + column * 18, top + row * 18));
			}
		}
		for (int column = 0; column < 9; column++) {
			if (column == this.lockedHotbarSlot && playerInventory.getItem(column).getItem() instanceof ItemBackpack) {
				addSlot(new LockedSlot(playerInventory, column, left + column * 18, top + 58));
			} else {
				addSlot(new Slot(playerInventory, column, left + column * 18, top + 58));
			}
		}
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack result = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot instanceof LockedSlot) {
			return ItemStack.EMPTY;
		}
		if (slot.hasItem()) {
			ItemStack stack = slot.getItem();
			result = stack.copy();
			int backpackSlots = this.size.getSize();
			if (index < backpackSlots) {
				if (!moveItemStackTo(stack, backpackSlots, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!moveItemStackTo(stack, 0, backpackSlots, false)) {
				return ItemStack.EMPTY;
			}
			if (stack.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return result;
	}

	@Override
	public boolean stillValid(Player player) {
		return this.inventory.stillValid(player);
	}

	private static final class FilteredSlot extends Slot {
		private final ItemInventoryBackpack inventory;

		FilteredSlot(ItemInventoryBackpack inventory, int slot, int x, int y) {
			super(inventory, slot, x, y);
			this.inventory = inventory;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return this.inventory.canSlotAccept(this.getContainerSlot(), stack);
		}
	}

	private static final class LockedSlot extends Slot {
		LockedSlot(Inventory inventory, int slot, int x, int y) {
			super(inventory, slot, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}

		@Override
		public boolean mayPickup(Player player) {
			return false;
		}

		@Override
		public ItemStack remove(int amount) {
			return ItemStack.EMPTY;
		}
	}
}
