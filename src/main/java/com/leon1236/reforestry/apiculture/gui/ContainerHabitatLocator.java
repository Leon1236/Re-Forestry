package com.leon1236.reforestry.apiculture.gui;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.apiculture.features.ApicultureMenuTypes;
import com.leon1236.reforestry.apiculture.inventory.ItemInventoryHabitatLocator;
import com.leon1236.reforestry.apiculture.items.ItemHabitatLocator;

public class ContainerHabitatLocator extends AbstractContainerMenu {
	private final ItemInventoryHabitatLocator inventory;
	private final int lockedHotbarSlot;

	public static ContainerHabitatLocator fromNetwork(int windowId, Inventory playerInv, Boolean mainHand) {
		InteractionHand hand = Boolean.TRUE.equals(mainHand) ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
		return new ContainerHabitatLocator(windowId, new ItemInventoryHabitatLocator(playerInv.player, hand), playerInv.player);
	}

	public ContainerHabitatLocator(int windowId, ItemInventoryHabitatLocator inventory, Player player) {
		super(ApicultureMenuTypes.HABITAT_LOCATOR.type(), windowId);
		this.inventory = inventory;
		this.lockedHotbarSlot = inventory.getHand() == InteractionHand.MAIN_HAND
				? player.getInventory().getSelectedSlot()
				: -1;
		addSlot(new FilteredSlot(inventory, ItemInventoryHabitatLocator.SLOT_ENERGY, 152, 8));
		addSlot(new FilteredSlot(inventory, ItemInventoryHabitatLocator.SLOT_SPECIMEN, 152, 32));
		addSlot(new OutputSlot(inventory, ItemInventoryHabitatLocator.SLOT_ANALYZED, 152, 75));
		addPlayerInventory(player.getInventory(), 8, 102);
	}

	private void addPlayerInventory(Inventory playerInventory, int left, int top) {
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlot(new Slot(playerInventory, column + row * 9 + 9, left + column * 18, top + row * 18));
			}
		}
		for (int column = 0; column < 9; column++) {
			if (column == lockedHotbarSlot && playerInventory.getItem(column).getItem() instanceof ItemHabitatLocator) {
				addSlot(new LockedSlot(playerInventory, column, left + column * 18, top + 58));
			} else {
				addSlot(new Slot(playerInventory, column, left + column * 18, top + 58));
			}
		}
	}

	public ItemInventoryHabitatLocator getInventory() {
		return inventory;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean stillValid(Player player) {
		return inventory.stillValid(player);
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
	}

	private static final class FilteredSlot extends Slot {
		private final ItemInventoryHabitatLocator inventory;

		FilteredSlot(ItemInventoryHabitatLocator inventory, int slot, int x, int y) {
			super(inventory, slot, x, y);
			this.inventory = inventory;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return inventory.canSlotAccept(getContainerSlot(), stack);
		}
	}

	private static final class OutputSlot extends Slot {
		OutputSlot(ItemInventoryHabitatLocator inventory, int slot, int x, int y) {
			super(inventory, slot, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}

	private static final class LockedSlot extends Slot {
		LockedSlot(Inventory inventory, int slot, int x, int y) {
			super(inventory, slot, x, y);
		}

		@Override
		public boolean mayPickup(Player player) {
			return false;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
