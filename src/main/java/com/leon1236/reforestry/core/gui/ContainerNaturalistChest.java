package com.leon1236.reforestry.core.gui;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.features.CoreMenuTypes;
import com.leon1236.reforestry.core.tiles.TileNaturalistChest;
import com.leon1236.reforestry.core.tiles.TileUtil;

public class ContainerNaturalistChest extends AbstractContainerMenu implements INaturalistMenu {
	private final TileNaturalistChest tile;
	private final int currentPage;

	public static ContainerNaturalistChest fromNetwork(int windowId, Inventory playerInv, NaturalistChestMenuData data) {
		TileNaturalistChest tile = TileUtil.getTile(playerInv.player.level(), data.pos(), TileNaturalistChest.class);
		if (tile == null) {
			throw new IllegalStateException("No naturalist chest at " + data.pos());
		}
		return new ContainerNaturalistChest(windowId, playerInv, tile, data.page());
	}

	public ContainerNaturalistChest(int windowId, Inventory playerInventory, TileNaturalistChest tile, int page) {
		super(CoreMenuTypes.NATURALIST_CHEST.type(), windowId);
		this.tile = tile;
		this.currentPage = Mth.clamp(page, 0, NaturalistInventoryLayout.MAX_PAGE - 1);

		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				int slot = y + this.currentPage * NaturalistInventoryLayout.SLOTS_PER_PAGE + x * 5;
				addSlot(new FilteredSlot(tile, slot, 100 + y * 18, 21 + x * 18));
			}
		}
		addPlayerInventory(playerInventory);
		if (!playerInventory.player.level().isClientSide()) {
			tile.onMenuOpened();
		}
	}

	private void addPlayerInventory(Inventory playerInventory) {
		int left = NaturalistInventoryLayout.PLAYER_INV_X;
		int top = NaturalistInventoryLayout.PLAYER_INV_Y;
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlot(new Slot(playerInventory, column + row * 9 + 9, left + column * 18, top + row * 18));
			}
		}
		for (int column = 0; column < 9; column++) {
			addSlot(new Slot(playerInventory, column, left + column * 18, top + 58));
		}
	}

	public TileNaturalistChest getTile() {
		return this.tile;
	}

	@Override
	public Identifier getSpeciesTypeId() {
		return this.tile.getSpeciesTypeId();
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
		this.tile.flipPage(serverPlayer, id);
		return true;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack result = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
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
		return this.tile.isUsableByPlayer(player);
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		if (!player.level().isClientSide()) {
			this.tile.onMenuClosed();
		}
	}

	private static final class FilteredSlot extends Slot {
		private final TileNaturalistChest tile;

		FilteredSlot(TileNaturalistChest tile, int slot, int x, int y) {
			super(tile, slot, x, y);
			this.tile = tile;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return this.tile.canAccept(stack);
		}
	}
}
