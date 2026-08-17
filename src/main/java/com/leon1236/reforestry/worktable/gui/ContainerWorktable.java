package com.leon1236.reforestry.worktable.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;

import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.core.gui.PhantomSlotHelper;
import com.leon1236.reforestry.core.gui.SlotGhostCrafting;
import com.leon1236.reforestry.worktable.features.WorktableMenuTypes;
import com.leon1236.reforestry.worktable.inventory.WorktableInventory;
import com.leon1236.reforestry.worktable.tiles.TileWorktable;

public class ContainerWorktable extends ContainerMachine<TileWorktable> {
	private static final int STORAGE_X = 8;
	private static final int STORAGE_Y = 90;
	private static final int CRAFT_X = 11;
	private static final int CRAFT_Y = 20;
	private static final int SLOT_GAP = 18;
	private static final int RESULT_X = 77;
	private static final int RESULT_Y = 38;
	private static final int INVENTORY_Y = 136;

	private static final ThreadLocal<Player> OPENING_PLAYER = new ThreadLocal<>();

	public ContainerWorktable(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TileWorktable.class));
	}

	public ContainerWorktable(int containerId, Inventory playerInventory, TileWorktable tile) {
		super(WorktableMenuTypes.WORKTABLE.type(), containerId, playerInventory, bindPlayer(playerInventory.player, tile), INVENTORY_Y);
		OPENING_PLAYER.remove();
		tile.updateCurrentRecipeFromDisplay();
	}

	private static TileWorktable bindPlayer(Player player, TileWorktable tile) {
		OPENING_PLAYER.set(player);
		return tile;
	}

	@Override
	protected void addMachineSlots(TileWorktable tile) {
		Player player = OPENING_PLAYER.get();
		for (int row = 0; row < 2; row++) {
			for (int column = 0; column < 9; column++) {
				int index = WorktableInventory.SLOT_INVENTORY_1 + column + row * 9;
				addSlot(new Slot(tile, index, STORAGE_X + column * SLOT_GAP, STORAGE_Y + row * SLOT_GAP));
			}
		}
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				int index = column + row * 3;
				addSlot(new CraftMatrixSlot(tile, index, CRAFT_X + column * SLOT_GAP, CRAFT_Y + row * SLOT_GAP));
			}
		}
		addSlot(new SlotWorktableResult(player, tile, tile.getResultDisplay(), 0, RESULT_X, RESULT_Y));
	}

	@Override
	public void clicked(int slotIndex, int button, ContainerInput input, Player player) {
		if (slotIndex >= 0 && slotIndex < slots.size() && slots.get(slotIndex) instanceof SlotGhostCrafting ghost) {
			PhantomSlotHelper.clickGhost(ghost, button, input, player);
			this.tile.updateCurrentRecipeFromDisplay();
			return;
		}
		super.clicked(slotIndex, button, input, player);
	}

	@Override
	public boolean clickMenuButton(Player player, int id) {
		if (super.clickMenuButton(player, id)) {
			return true;
		}
		if (id >= WorktableButtons.RECALL_BASE && id < WorktableButtons.RECALL_BASE + 9) {
			this.tile.chooseRecipeMemory(id - WorktableButtons.RECALL_BASE);
			return true;
		}
		if (id >= WorktableButtons.LOCK_BASE && id < WorktableButtons.LOCK_BASE + 9) {
			this.tile.toggleMemoryLock(id - WorktableButtons.LOCK_BASE);
			return true;
		}
		if (id == WorktableButtons.CLEAR) {
			this.tile.clearCraftMatrix();
			return true;
		}
		if (id == WorktableButtons.PREV_CONFLICT) {
			this.tile.choosePreviousConflictRecipe();
			return true;
		}
		if (id == WorktableButtons.NEXT_CONFLICT) {
			this.tile.chooseNextConflictRecipe();
			return true;
		}
		return false;
	}

	private static final class CraftMatrixSlot extends SlotGhostCrafting {
		private final TileWorktable tile;

		CraftMatrixSlot(TileWorktable tile, int index, int x, int y) {
			super(tile.getCraftingDisplay(), index, x, y);
			this.tile = tile;
		}

		@Override
		public void setChanged() {
			super.setChanged();
			this.tile.updateCurrentRecipeFromDisplay();
		}
	}
}
