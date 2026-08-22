package com.leon1236.reforestry.cultivation.gui;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.gui.IContainerClimate;
import com.leon1236.reforestry.api.gui.IContainerEnergy;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;
import com.leon1236.reforestry.cultivation.features.CultivationMenuTypes;
import com.leon1236.reforestry.cultivation.inventory.InventoryPlanter;
import com.leon1236.reforestry.cultivation.tiles.TilePlanter;
import com.leon1236.reforestry.farming.gui.IFarmLedgerDelegate;

public class ContainerPlanter extends ContainerMachine<TilePlanter>
		implements IContainerLiquidTanks, IContainerClimate, IContainerEnergy, IFarmLedgerDelegate {
	public ContainerPlanter(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TilePlanter.class));
	}

	public ContainerPlanter(int containerId, Inventory playerInventory, TilePlanter tile) {
		super(CultivationMenuTypes.PLANTER.type(), containerId, playerInventory, tile, 21, 110);
		addDataSlots(tile.getProgressData());
		addDataSlots(tile.getErrorData());
		addDataSlots(tile.getEnergyData());
		addDataSlots(tile.getFarmData());
		addDataSlots(tile.getClimateData());
	}

	@Override
	protected void addMachineSlots(TilePlanter tile) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				addSlot(new FilteredSlot(tile, InventoryPlanter.CONFIG.resourcesStart + j + i * 2, 11 + j * 18, 65 + i * 18));
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				addSlot(new FilteredSlot(tile, InventoryPlanter.CONFIG.germlingsStart + j + i * 2, 71 + j * 18, 65 + i * 18));
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				addSlot(new OutputSlot(tile, InventoryPlanter.CONFIG.productionStart + j + i * 2, 131 + j * 18, 65 + i * 18));
			}
		}
		addSlot(new FilteredSlot(tile, InventoryPlanter.CONFIG.fertilizerStart, 83, 22));
		addSlot(new FilteredSlot(tile, InventoryPlanter.CONFIG.canStart, 178, 18));
	}

	public int getErrorCount() {
		return tile.getErrorData().get(0);
	}

	public short getErrorId(int index) {
		return (short) tile.getErrorData().get(index + 1);
	}

	public int getFertilizerScaled() {
		return this.tile.getSyncedFertilizerScaled();
	}

	public int getTankAmountMb() {
		return this.tile.getSyncedTankAmountMb();
	}

	public Fluid getTankFluid() {
		return BuiltInRegistries.FLUID.byId(this.tile.getSyncedTankFluidId());
	}

	public int getTankCapacityMb() {
		return (int) FluidUnits.dropletsToMb(TilePlanter.TANK_CAPACITY);
	}

	@Override
	public TemperatureType getTemperature() {
		return this.tile.getSyncedTemperature();
	}

	@Override
	public HumidityType getHumidity() {
		return this.tile.getSyncedHumidity();
	}

	@Override
	public float getHydrationModifier() {
		return getHydrationTempModifier() * getHydrationHumidModifier() * getHydrationRainfallModifier();
	}

	@Override
	public float getHydrationTempModifier() {
		return this.tile.getSyncedHydrationTemp();
	}

	@Override
	public float getHydrationHumidModifier() {
		return this.tile.getSyncedHydrationHumid();
	}

	@Override
	public float getHydrationRainfallModifier() {
		return this.tile.getSyncedHydrationRain();
	}

	@Override
	public double getDrought() {
		return this.tile.getSyncedDrought();
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

	@Override
	public boolean clickMenuButton(Player player, int id) {
		if (super.clickMenuButton(player, id)) {
			return true;
		}
		if (getTank(id) == null || !PipetteTankHelper.canHandleClick(player.containerMenu.getCarried())) {
			return false;
		}
		if (player instanceof ServerPlayer serverPlayer) {
			handlePipetteClick(id, serverPlayer);
		}
		return true;
	}

	@Override
	public void handlePipetteClick(int slot, ServerPlayer player) {
		SingleFluidStorage tank = getTank(slot);
		if (tank != null) {
			PipetteTankHelper.handlePipetteClick(tank, player, this);
		}
	}

	@Nullable
	@Override
	public SingleFluidStorage getTank(int slot) {
		return slot == 0 ? this.tile.getWaterTank() : null;
	}

	private static final class FilteredSlot extends Slot {
		private final TilePlanter tile;

		FilteredSlot(TilePlanter tile, int index, int x, int y) {
			super(tile, index, x, y);
			this.tile = tile;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return this.tile.canPlaceItem(getContainerSlot(), stack);
		}
	}

	private static final class OutputSlot extends Slot {
		OutputSlot(TilePlanter tile, int index, int x, int y) {
			super(tile, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
