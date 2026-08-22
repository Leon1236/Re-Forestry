package com.leon1236.reforestry.energy.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;
import com.leon1236.reforestry.energy.EnergyConstants;
import com.leon1236.reforestry.energy.features.EnergyMenus;
import com.leon1236.reforestry.energy.inventory.InventoryEngineBiogas;
import com.leon1236.reforestry.energy.tiles.BiogasEngineBlockEntity;

public class ContainerBiogasEngine extends ContainerMachine<BiogasEngineBlockEntity> implements IEngineMenu, IContainerLiquidTanks {
	private static final int CAN_X = 143;
	private static final int CAN_Y = 40;
	private static final int INVENTORY_Y = 84;

	private final SimpleContainerData tankData = new SimpleContainerData(7);

	public ContainerBiogasEngine(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, BiogasEngineBlockEntity.class));
	}

	public ContainerBiogasEngine(int containerId, Inventory playerInventory, BiogasEngineBlockEntity tile) {
		super(EnergyMenus.ENGINE_BIOGAS.type(), containerId, playerInventory, tile, INVENTORY_Y);
		addDataSlots(tile.getEngineData());
		addDataSlots(tile.getErrorData());
		addDataSlots(tankData);
	}

	@Override
	protected void addMachineSlots(BiogasEngineBlockEntity tile) {
		addSlot(new CanSlot(tile, InventoryEngineBiogas.SLOT_CAN, CAN_X, CAN_Y));
	}

	@Override
	public int getCurrentOutput() {
		return tile.getEngineData().get(0);
	}

	@Override
	public int getHeat() {
		return tile.getEngineData().get(1);
	}

	@Override
	public int getEnergyStored() {
		return tile.getEngineData().get(2);
	}

	@Override
	public int getEnergyCapacity() {
		return tile.getEngineData().get(3);
	}

	@Override
	public int getErrorCount() {
		return tile.getErrorData().get(0);
	}

	@Override
	public short getErrorId(int index) {
		return (short) tile.getErrorData().get(index + 1);
	}

	@Override
	public String getHintKey() {
		return tile.getHintKey();
	}

	public int getFuelAmountMb() {
		return tankData.get(0);
	}

	public Fluid getFuelFluid() {
		return fluidOf(tankData.get(1));
	}

	public int getHeatingAmountMb() {
		return tankData.get(2);
	}

	public Fluid getHeatingFluid() {
		return fluidOf(tankData.get(3));
	}

	public int getBurnAmountMb() {
		return tankData.get(4);
	}

	public Fluid getBurnFluid() {
		return fluidOf(tankData.get(5));
	}

	public int getBurnCapacityMb() {
		return tankData.get(6);
	}

	public int getTankCapacityMb() {
		return EnergyConstants.ENGINE_TANK_CAPACITY_MB;
	}

	public int getOperatingTemperatureScaled(int i) {
		return (int) Math.round(getHeat() * i / (EnergyConstants.ENGINE_BRONZE_HEAT_MAX * 0.2));
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

	@Override
	public SingleFluidStorage getTank(int slot) {
		return switch (slot) {
			case 0 -> tile.getFuelTank();
			case 1 -> tile.getHeatingTank();
			default -> null;
		};
	}

	@Override
	public void broadcastChanges() {
		syncTank(tankData, 0, 1, tile.getFuelTank());
		syncTank(tankData, 2, 3, tile.getHeatingTank());
		syncTank(tankData, 4, 5, tile.getBurnTank());
		tankData.set(6, (int) FluidUnits.dropletsToMb(tile.getBurnTank().getTankCapacity()));
		super.broadcastChanges();
	}

	private static void syncTank(SimpleContainerData data, int amountIndex, int typeIndex, FilteredFluidStorage tank) {
		data.set(amountIndex, (int) FluidUnits.dropletsToMb(tank.getAmount()));
		data.set(typeIndex, BuiltInRegistries.FLUID.getId(tank.getResource().getFluid()));
	}

	private static Fluid fluidOf(int id) {
		Fluid fluid = BuiltInRegistries.FLUID.byId(id);
		return fluid == null ? Fluids.EMPTY : fluid;
	}

	private static final class CanSlot extends Slot {
		private final BiogasEngineBlockEntity tile;

		CanSlot(BiogasEngineBlockEntity tile, int index, int x, int y) {
			super(tile, index, x, y);
			this.tile = tile;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return tile.canPlaceItem(getContainerSlot(), stack);
		}
	}
}
