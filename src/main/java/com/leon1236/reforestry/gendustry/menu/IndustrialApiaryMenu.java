package com.leon1236.reforestry.gendustry.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.gui.IContainerClimate;
import com.leon1236.reforestry.api.gui.IContainerEnergy;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.gendustry.blockentity.IndustrialApiaryBlockEntity;
import com.leon1236.reforestry.gendustry.features.GMenus;

public class IndustrialApiaryMenu extends ContainerMachine<IndustrialApiaryBlockEntity>
		implements IContainerEnergy, IContainerClimate {
	private static final int INVENTORY_Y = 84;

	public IndustrialApiaryMenu(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, IndustrialApiaryBlockEntity.class));
	}

	public IndustrialApiaryMenu(int containerId, Inventory playerInventory, IndustrialApiaryBlockEntity tile) {
		super(GMenus.INDUSTRIAL_APIARY.type(), containerId, playerInventory, tile, INVENTORY_Y);
		addDataSlots(tile.getProgressData());
		addDataSlots(tile.getErrorData());
		addDataSlots(tile.getEnergyData());
		addDataSlots(tile.getClimateData());
		tile.getBeekeepingLogic().onGuiOpened();
	}

	@Override
	protected void addMachineSlots(IndustrialApiaryBlockEntity tile) {
		addSlot(new FilteredSlot(tile, IndustrialApiaryBlockEntity.SLOT_QUEEN, 26, 29));
		addSlot(new FilteredSlot(tile, IndustrialApiaryBlockEntity.SLOT_DRONE, 26, 52));

		for (int i = 0; i < IndustrialApiaryBlockEntity.UPGRADE_SLOT_COUNT; i++) {
			int x = 62 + (i % 2) * 18;
			int y = 43 + (i / 2) * 18;
			addSlot(new FilteredSlot(tile, IndustrialApiaryBlockEntity.UPGRADE_SLOT_START + i, x, y));
		}

		for (int i = 0; i < IndustrialApiaryBlockEntity.OUTPUT_SLOT_COUNT; i++) {
			int x = 116 + (i % 3) * 18;
			int y = 25 + (i / 3) * 18;
			addSlot(new OutputSlot(tile, IndustrialApiaryBlockEntity.OUTPUT_SLOT_START + i, x, y));
		}
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

	public int getHealthScaled(int pixels) {
		return tile.getHealthScaled(pixels);
	}

	public String getHintsKey() {
		return tile.getHintsKey();
	}

	@Override
	public TemperatureType getTemperature() {
		return TemperatureType.VALUES.get(tile.getClimateData().get(0));
	}

	@Override
	public HumidityType getHumidity() {
		return HumidityType.VALUES.get(tile.getClimateData().get(1));
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
		private final WorldlyContainer container;
		private final int slotIndex;

		FilteredSlot(WorldlyContainer container, int index, int x, int y) {
			super(container, index, x, y);
			this.container = container;
			this.slotIndex = index;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return container.canPlaceItem(slotIndex, stack);
		}
	}

	private static final class OutputSlot extends Slot {
		OutputSlot(WorldlyContainer container, int index, int x, int y) {
			super(container, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
