package com.leon1236.reforestry.storage.gui;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.gui.INaturalistMenu;
import com.leon1236.reforestry.core.gui.NaturalistInventoryLayout;
import com.leon1236.reforestry.storage.features.StorageMenuTypes;
import com.leon1236.reforestry.storage.inventory.ItemInventoryBackpackPaged;
import com.leon1236.reforestry.storage.items.ItemBackpack;
import com.leon1236.reforestry.storage.items.ItemBackpackNaturalist;

public class ContainerNaturalistBackpack extends AbstractContainerMenu implements INaturalistMenu {
	private final ItemInventoryBackpackPaged inventory;
	private final int currentPage;
	private final Identifier typeId;
	private final boolean mainHand;

	public static ContainerNaturalistBackpack fromNetwork(int windowId, Inventory playerInv, NaturalistBackpackMenuData data) {
		Player player = playerInv.player;
		InteractionHand hand = data.mainHand() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
		return new ContainerNaturalistBackpack(windowId, player, hand, data.page(), data.typeId());
	}

	public ContainerNaturalistBackpack(int windowId, Player player, InteractionHand hand, int page, Identifier typeId) {
		super(StorageMenuTypes.NATURALIST_BACKPACK.type(), windowId);
		this.inventory = new ItemInventoryBackpackPaged(player, hand, ItemBackpack.SLOTS_BACKPACK_APIARIST, typeId);
		this.currentPage = page;
		this.typeId = typeId;
		this.mainHand = hand == InteractionHand.MAIN_HAND;
		int lockedHotbarSlot = this.mainHand ? player.getInventory().getSelectedSlot() : -1;

		int selectedPage = Mth.clamp(page, 0, NaturalistInventoryLayout.MAX_PAGE - 1);
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				int slot = y + selectedPage * NaturalistInventoryLayout.SLOTS_PER_PAGE + x * 5;
				addSlot(new FilteredSlot(this.inventory, slot, 100 + y * 18, 21 + x * 18));
			}
		}
		addPlayerInventory(player.getInventory(), lockedHotbarSlot);
	}

	private void addPlayerInventory(Inventory playerInventory, int lockedHotbarSlot) {
		int left = NaturalistInventoryLayout.PLAYER_INV_X;
		int top = NaturalistInventoryLayout.PLAYER_INV_Y;
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlot(new Slot(playerInventory, column + row * 9 + 9, left + column * 18, top + row * 18));
			}
		}
		for (int column = 0; column < 9; column++) {
			if (column == lockedHotbarSlot && playerInventory.getItem(column).getItem() instanceof ItemBackpack) {
				addSlot(new LockedSlot(playerInventory, column, left + column * 18, top + 58));
			} else {
				addSlot(new Slot(playerInventory, column, left + column * 18, top + 58));
			}
		}
	}

	@Override
	public Identifier getSpeciesTypeId() {
		return this.typeId;
	}

	@Override
	public int getCurrentPage() {
		return this.currentPage;
	}

	@Override
	public boolean clickMenuButton(Player player, int id) {
		if (!(player instanceof ServerPlayer serverPlayer)) {
			return false;
		}
		if (id < 0 || id >= NaturalistInventoryLayout.MAX_PAGE) {
			return false;
		}
		ItemStack backpack = this.inventory.parentStack();
		if (backpack.isEmpty()) {
			return false;
		}
		serverPlayer.openMenu(new ItemBackpackNaturalist.NaturalistBackpackMenuProvider(
				id, this.typeId, this.mainHand, backpack.getHoverName()));
		return true;
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
			int pageSlots = NaturalistInventoryLayout.SLOTS_PER_PAGE;
			if (index < pageSlots) {
				if (!moveItemStackTo(stack, pageSlots, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!moveItemStackTo(stack, 0, pageSlots, false)) {
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
		private final ItemInventoryBackpackPaged inventory;

		FilteredSlot(ItemInventoryBackpackPaged inventory, int slot, int x, int y) {
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
