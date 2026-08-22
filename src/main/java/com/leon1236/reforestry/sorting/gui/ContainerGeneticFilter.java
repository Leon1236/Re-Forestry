package com.leon1236.reforestry.sorting.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.core.network.PacketRegistry;
import com.leon1236.reforestry.sorting.features.SortingMenus;
import com.leon1236.reforestry.sorting.network.packets.GuiUpdateFilterPayload;
import com.leon1236.reforestry.sorting.tiles.TileGeneticFilter;

public class ContainerGeneticFilter extends AbstractContainerMenu {
	private final TileGeneticFilter tile;
	private boolean guiNeedsUpdate = true;

	public ContainerGeneticFilter(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos));
	}

	public ContainerGeneticFilter(int containerId, Inventory playerInventory, TileGeneticFilter tile) {
		super(SortingMenus.GENETIC_FILTER.type(), containerId);
		this.tile = tile;
		addInventory(playerInventory, 26, 140);
	}

	private static TileGeneticFilter resolveTile(Inventory playerInventory, BlockPos pos) {
		BlockEntity blockEntity = playerInventory.player.level().getBlockEntity(pos);
		if (blockEntity instanceof TileGeneticFilter filter) {
			return filter;
		}
		throw new IllegalStateException("No TileGeneticFilter block entity at " + pos);
	}

	protected void addInventory(Inventory playerInventory, int xInv, int yInv) {
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlot(new SlotGeneticFilter(playerInventory, column + row * 9 + 9, xInv + column * 18, yInv + row * 18));
			}
		}
		for (int column = 0; column < 9; column++) {
			addSlot(new SlotGeneticFilter(playerInventory, column, xInv + column * 18, yInv + 58));
		}
		for (int x = 0; x < 6; x++) {
			addSlot(new SlotFilterFacing(this.tile, x, 8, 18 + x * 18));
		}
	}

	public TileGeneticFilter getTile() {
		return this.tile;
	}

	public void setGuiNeedsUpdate(boolean guiNeedsUpdate) {
		this.guiNeedsUpdate = guiNeedsUpdate;
	}

	@Override
	public void broadcastChanges() {
		super.broadcastChanges();
		if (this.guiNeedsUpdate) {
			if (this.tile.getLevel() instanceof ServerLevel serverLevel) {
				GuiUpdateFilterPayload payload = GuiUpdateFilterPayload.of(this.tile.getBlockPos(), this.tile.getLogic());
				for (ServerPlayer player : serverLevel.players()) {
					if (player.containerMenu == this) {
						PacketRegistry.sendToPlayer(player, payload);
					}
				}
			}
			this.guiNeedsUpdate = false;
		}
	}

	public boolean hasSameTile(ContainerGeneticFilter openContainer) {
		return this.tile == openContainer.tile;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack result = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack stack = slot.getItem();
			result = stack.copy();
			int playerStart = 0;
			int playerEnd = 36;
			if (index >= playerEnd) {
				if (!moveItemStackTo(stack, playerStart, playerEnd, true)) {
					return ItemStack.EMPTY;
				}
			} else {
				return ItemStack.EMPTY;
			}
			if (stack.isEmpty()) {
				slot.setByPlayer(ItemStack.EMPTY);
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
}
