package com.leon1236.reforestry.extratrees.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import com.leon1236.reforestry.api.gui.IContainerEnergy;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;
import com.leon1236.reforestry.extratrees.features.ExtraTreesMenuTypes;
import com.leon1236.reforestry.extratrees.recipes.LumbermillRecipeHelper;
import com.leon1236.reforestry.extratrees.tiles.TileLumbermill;

public class ContainerLumbermill extends ContainerMachine<TileLumbermill> implements IContainerEnergy, IContainerLiquidTanks {
	private static final int INVENTORY_X = 30;
	private static final int INVENTORY_Y = 108;
	private static final int FLUID_NONE = 0;
	private static final int FLUID_WATER = 1;

	private final SimpleContainerData tankData = new SimpleContainerData(2);

	public ContainerLumbermill(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TileLumbermill.class));
	}

	public ContainerLumbermill(int containerId, Inventory playerInventory, TileLumbermill tile) {
		super(ExtraTreesMenuTypes.LUMBERMILL.type(), containerId, playerInventory, tile, INVENTORY_X, INVENTORY_Y);
		addDataSlots(tile.getProgressData());
		addDataSlots(tile.getErrorData());
		addDataSlots(tile.getEnergyData());
		addDataSlots(tankData);
	}

	@Override
	protected void addMachineSlots(TileLumbermill tile) {
		addSlot(new FilteredSlot(tile, TileLumbermill.SLOT_LOG, 42, 43));
		addSlot(new OutputSlot(tile, TileLumbermill.SLOT_PLANKS, 148, 43));
		addSlot(new OutputSlot(tile, TileLumbermill.SLOT_BARK, 172, 28));
		addSlot(new OutputSlot(tile, TileLumbermill.SLOT_SAWDUST, 172, 58));
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

	public int getWaterAmountMb() {
		return tankData.get(0);
	}

	public int getWaterFluidType() {
		return tankData.get(1);
	}

	public int getTankCapacityMb() {
		return (int) FluidUnits.dropletsToMb(TileLumbermill.TANK_CAPACITY);
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

	@Override
	public SingleFluidStorage getTank(int slot) {
		return slot == 0 ? tile.getWaterTank() : null;
	}

	@Override
	public void broadcastChanges() {
		tankData.set(0, (int) FluidUnits.dropletsToMb(tile.getWaterTank().getAmount()));
		tankData.set(1, tile.getWaterTank().getAmount() > 0 ? FLUID_WATER : FLUID_NONE);
		super.broadcastChanges();
	}

	private static final class FilteredSlot extends Slot {
		private final TileLumbermill tile;

		FilteredSlot(TileLumbermill tile, int index, int x, int y) {
			super(tile, index, x, y);
			this.tile = tile;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return LumbermillRecipeHelper.isLog(stack);
		}
	}

	private static final class OutputSlot extends Slot {
		OutputSlot(TileLumbermill tile, int index, int x, int y) {
			super(tile, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
