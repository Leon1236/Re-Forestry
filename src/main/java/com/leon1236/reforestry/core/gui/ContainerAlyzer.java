package com.leon1236.reforestry.core.gui;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.features.CoreMenuTypes;
import com.leon1236.reforestry.core.inventory.ItemInventoryAlyzer;
import com.leon1236.reforestry.core.items.ItemAlyzer;

public class ContainerAlyzer extends AbstractContainerMenu {
	private final ItemInventoryAlyzer inventory;
	private final int lockedHotbarSlot;

	public static ContainerAlyzer fromNetwork(int windowId, Inventory playerInv, Boolean mainHand) {
		InteractionHand hand = Boolean.TRUE.equals(mainHand) ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
		Player player = playerInv.player;
		return new ContainerAlyzer(windowId, new ItemInventoryAlyzer(player, hand), player);
	}

	public ContainerAlyzer(int windowId, ItemInventoryAlyzer inventory, Player player) {
		super(CoreMenuTypes.ALYZER.type(), windowId);
		this.inventory = inventory;
		this.lockedHotbarSlot = inventory.getHand() == InteractionHand.MAIN_HAND
				? player.getInventory().getSelectedSlot()
				: -1;

		final int xPosLeftSlots = 223;
		this.addSlot(new FilteredSlot(inventory, ItemInventoryAlyzer.SLOT_ENERGY, xPosLeftSlots, 8));
		this.addSlot(new FilteredSlot(inventory, ItemInventoryAlyzer.SLOT_SPECIMEN, xPosLeftSlots, 26));
		this.addSlot(new FilteredSlot(inventory, ItemInventoryAlyzer.SLOT_ANALYZE_1, xPosLeftSlots, 57));
		this.addSlot(new FilteredSlot(inventory, ItemInventoryAlyzer.SLOT_ANALYZE_2, xPosLeftSlots, 75));
		this.addSlot(new FilteredSlot(inventory, ItemInventoryAlyzer.SLOT_ANALYZE_3, xPosLeftSlots, 93));
		this.addSlot(new FilteredSlot(inventory, ItemInventoryAlyzer.SLOT_ANALYZE_4, xPosLeftSlots, 111));
		this.addSlot(new FilteredSlot(inventory, ItemInventoryAlyzer.SLOT_ANALYZE_5, xPosLeftSlots, 129));

		addPlayerInventory(player.getInventory(), 43, 156);
	}

	private void addPlayerInventory(Inventory playerInventory, int left, int top) {
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlot(new Slot(playerInventory, column + row * 9 + 9, left + column * 18, top + row * 18));
			}
		}
		for (int column = 0; column < 9; column++) {
			if (column == this.lockedHotbarSlot && playerInventory.getItem(column).getItem() instanceof ItemAlyzer) {
				addSlot(new LockedSlot(playerInventory, column, left + column * 18, top + 58));
			} else {
				addSlot(new Slot(playerInventory, column, left + column * 18, top + 58));
			}
		}
	}

	public ItemInventoryAlyzer getAlyzerInventory() {
		return inventory;
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
			int machineSlots = 7;
			if (index < machineSlots) {
				if (!moveItemStackTo(stack, machineSlots, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!moveItemStackTo(stack, 0, machineSlots, false)) {
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
		private final ItemInventoryAlyzer inventory;

		FilteredSlot(ItemInventoryAlyzer inventory, int slot, int x, int y) {
			super(inventory, slot, x, y);
			this.inventory = inventory;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return inventory.canSlotAccept(this.getContainerSlot(), stack);
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
