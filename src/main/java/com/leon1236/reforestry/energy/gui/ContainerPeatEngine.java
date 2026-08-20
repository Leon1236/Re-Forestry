package com.leon1236.reforestry.energy.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.energy.features.EnergyMenus;
import com.leon1236.reforestry.energy.inventory.InventoryEnginePeat;
import com.leon1236.reforestry.energy.tiles.PeatEngineBlockEntity;

public class ContainerPeatEngine extends ContainerMachine<PeatEngineBlockEntity> implements IEngineMenu {
	private static final int INVENTORY_Y = 84;

	public ContainerPeatEngine(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, PeatEngineBlockEntity.class));
	}

	public ContainerPeatEngine(int containerId, Inventory playerInventory, PeatEngineBlockEntity tile) {
		super(EnergyMenus.ENGINE_PEAT.type(), containerId, playerInventory, tile, INVENTORY_Y);
		addDataSlots(tile.getEngineData());
		addDataSlots(tile.getErrorData());
		addDataSlots(tile.getBurnData());
	}

	@Override
	protected void addMachineSlots(PeatEngineBlockEntity tile) {
		addSlot(new FilteredSlot(tile, InventoryEnginePeat.SLOT_FUEL, 44, 46));
		addSlot(new OutputSlot(tile, 1, 98, 35));
		addSlot(new OutputSlot(tile, 2, 98, 53));
		addSlot(new OutputSlot(tile, 3, 116, 35));
		addSlot(new OutputSlot(tile, 4, 116, 53));
	}

	public int getCurrentOutput() {
		return tile.getEngineData().get(0);
	}

	public int getHeat() {
		return tile.getEngineData().get(1);
	}

	public int getEnergyStored() {
		return tile.getEngineData().get(2);
	}

	public int getEnergyCapacity() {
		return tile.getEngineData().get(3);
	}

	public int getErrorCount() {
		return tile.getErrorData().get(0);
	}

	public short getErrorId(int index) {
		return (short) tile.getErrorData().get(index + 1);
	}

	public boolean isBurning() {
		return tile.getBurnData().get(0) > 0 && tile.getEngineData().get(4) == 0;
	}

	public int getBurnTimeRemainingScaled(int pixels) {
		return tile.getBurnTimeRemainingScaled(pixels);
	}

	public String getHintKey() {
		return tile.getHintKey();
	}

	private static final class FilteredSlot extends Slot {
		private final PeatEngineBlockEntity tile;

		FilteredSlot(PeatEngineBlockEntity tile, int index, int x, int y) {
			super(tile, index, x, y);
			this.tile = tile;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return tile.canPlaceItem(getContainerSlot(), stack);
		}
	}

	private static final class OutputSlot extends Slot {
		OutputSlot(PeatEngineBlockEntity tile, int index, int x, int y) {
			super(tile, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
