package com.leon1236.reforestry.gendustry.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.gui.IContainerEnergy;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.gendustry.blockentity.SamplerBlockEntity;
import com.leon1236.reforestry.gendustry.features.GMenus;

public class ThreeInputMenu extends ContainerMachine<SamplerBlockEntity> implements IContainerEnergy {
	private static final int INVENTORY_Y = 84;

	public ThreeInputMenu(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, SamplerBlockEntity.class));
	}

	public ThreeInputMenu(int containerId, Inventory playerInventory, SamplerBlockEntity tile) {
		super(GMenus.SAMPLER.type(), containerId, playerInventory, tile, INVENTORY_Y);
		addDataSlots(tile.getProgressData());
		addDataSlots(tile.getErrorData());
		addDataSlots(tile.getEnergyData());
	}

	@Override
	protected void addMachineSlots(SamplerBlockEntity tile) {
		addSlot(new FilteredSlot(tile, SamplerBlockEntity.SLOT_INPUT, 32, 49));
		addSlot(new FilteredSlot(tile, SamplerBlockEntity.SLOT_BLANK_SAMPLE, 65, 28));
		addSlot(new FilteredSlot(tile, SamplerBlockEntity.SLOT_LABWARE, 89, 28));
		addSlot(new OutputSlot(tile, SamplerBlockEntity.SLOT_OUTPUT, 128, 49));
	}

	public int getProgressPercent() {
		return tile.getProgressData().get(0);
	}

	public int getErrorCount() {
		return tile.getErrorData().get(0);
	}

	public short getErrorId(int index) {
		return (short) tile.getErrorData().get(index + 1);
	}

	public String getHintsKey() {
		return tile.getHintsKey();
	}

	@Override
	public int getEnergyStored() {
		return tile.getEnergyData().get(0);
	}

	@Override
	public int getEnergyCapacity() {
		return tile.getEnergyData().get(1);
	}

	@Override
	public int getEnergyMaxReceive() {
		return tile.getEnergyData().get(2);
	}

	@Override
	public int getEnergyUsage() {
		return tile.getEnergyData().get(3);
	}

	private static final class FilteredSlot extends Slot {
		private final SamplerBlockEntity tile;
		private final int slotIndex;

		FilteredSlot(SamplerBlockEntity tile, int index, int x, int y) {
			super(tile, index, x, y);
			this.tile = tile;
			this.slotIndex = index;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return tile.canPlaceItem(slotIndex, stack);
		}
	}

	private static final class OutputSlot extends Slot {
		OutputSlot(SamplerBlockEntity tile, int index, int x, int y) {
			super(tile, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
