package com.leon1236.reforestry.core.escritoire;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.features.CoreMenuTypes;
import com.leon1236.reforestry.core.gui.ContainerMachine;

public class ContainerEscritoire extends ContainerMachine<TileEscritoire> {
	public static final int BUTTON_PROBE = -1;
	public static final int TOKEN_BUTTON_MAX = EscritoireGameBoard.TOKEN_COUNT_MAX;

	private static final int INVENTORY_X = 34;
	private static final int INVENTORY_Y = 153;

	private long lastUpdate;
	private boolean gameSynced;

	public ContainerEscritoire(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TileEscritoire.class));
	}

	public ContainerEscritoire(int containerId, Inventory playerInventory, TileEscritoire tile) {
		super(CoreMenuTypes.ESCRITOIRE.type(), containerId, playerInventory, tile, INVENTORY_X, INVENTORY_Y);
		this.lastUpdate = tile.getGame().getLastUpdate();
	}

	@Override
	protected void addMachineSlots(TileEscritoire tile) {
		addSlot(new AnalyzeSlot(tile, InventoryEscritoire.SLOT_ANALYZE, 97, 67));

		for (int i = 0; i < InventoryEscritoire.SLOTS_INPUT_COUNT; i++) {
			addSlot(new FilteredSlot(tile, InventoryEscritoire.SLOT_INPUT_1 + i, 17, 49 + i * 18));
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				addSlot(new OutputSlot(tile, InventoryEscritoire.SLOT_RESULTS_1 + i * 2 + j, 177 + j * 18, 85 + i * 18));
			}
		}
	}

	@Override
	public void broadcastChanges() {
		super.broadcastChanges();
		long gameLastUpdate = this.tile.getGame().getLastUpdate();
		if (!this.gameSynced || this.lastUpdate != gameLastUpdate) {
			this.gameSynced = true;
			this.lastUpdate = gameLastUpdate;
			if (this.tile.getLevel() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
				for (ServerPlayer player : serverLevel.players()) {
					if (player.containerMenu == this) {
						this.tile.syncGameToPlayer(player);
					}
				}
			}
		}
	}

	@Override
	public boolean clickMenuButton(Player player, int id) {
		if (super.clickMenuButton(player, id)) {
			return true;
		}
		EscritoireGame.Status status = this.tile.getGame().getStatus();
		if (status != EscritoireGame.Status.PLAYING) {
			return false;
		}
		if (id == BUTTON_PROBE) {
			if (player instanceof ServerPlayer) {
				this.tile.probe();
			}
			return true;
		}
		if (id >= 0 && id < TOKEN_BUTTON_MAX) {
			if (player instanceof ServerPlayer) {
				this.tile.choose(player.getGameProfile(), id);
			} else {
				this.tile.getGame().choose(id);
			}
			return true;
		}
		return false;
	}

	private static final class AnalyzeSlot extends Slot {
		private final TileEscritoire tile;

		AnalyzeSlot(TileEscritoire tile, int index, int x, int y) {
			super(tile, index, x, y);
			this.tile = tile;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return this.tile.canPlaceItem(getContainerSlot(), stack);
		}

		@Override
		public int getMaxStackSize() {
			return 1;
		}

		@Override
		public int getMaxStackSize(ItemStack stack) {
			return 1;
		}
	}

	private static final class FilteredSlot extends Slot {
		private final TileEscritoire tile;

		FilteredSlot(TileEscritoire tile, int index, int x, int y) {
			super(tile, index, x, y);
			this.tile = tile;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return this.tile.canPlaceItem(getContainerSlot(), stack);
		}
	}

	private static final class OutputSlot extends Slot {
		OutputSlot(TileEscritoire tile, int index, int x, int y) {
			super(tile, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
