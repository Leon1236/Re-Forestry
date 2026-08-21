package com.leon1236.reforestry.extratrees.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SimpleContainerData;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import com.leon1236.reforestry.api.gui.IContainerEnergy;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;
import com.leon1236.reforestry.extratrees.features.ExtraTreesMenuTypes;
import com.leon1236.reforestry.extratrees.tiles.TileDistillery;

public class ContainerDistillery extends ContainerMachine<TileDistillery> implements IContainerEnergy, IContainerLiquidTanks {
	private static final int INVENTORY_Y = 84;
	private final SimpleContainerData tankData = new SimpleContainerData(4);

	public ContainerDistillery(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TileDistillery.class));
	}

	public ContainerDistillery(int containerId, Inventory playerInventory, TileDistillery tile) {
		super(ExtraTreesMenuTypes.DISTILLERY.type(), containerId, playerInventory, tile, INVENTORY_Y);
		addDataSlots(tile.getProgressData());
		addDataSlots(tile.getEnergyData());
		addDataSlots(tankData);
	}

	@Override
	protected void addMachineSlots(TileDistillery tile) {
	}

	public int getInputAmountMb() {
		return tankData.get(0);
	}

	public int getOutputAmountMb() {
		return tankData.get(2);
	}

	public int getTankCapacityMb() {
		return (int) FluidUnits.dropletsToMb(TileDistillery.TANK_CAPACITY);
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
		return switch (slot) {
			case 0 -> tile.getInputTank();
			case 1 -> tile.getOutputTank();
			default -> null;
		};
	}

	@Override
	public void broadcastChanges() {
		tankData.set(0, (int) FluidUnits.dropletsToMb(tile.getInputTank().getAmount()));
		tankData.set(1, tile.getInputTank().getAmount() > 0 ? 1 : 0);
		tankData.set(2, (int) FluidUnits.dropletsToMb(tile.getOutputTank().getAmount()));
		tankData.set(3, tile.getOutputTank().getAmount() > 0 ? 1 : 0);
		super.broadcastChanges();
	}
}
