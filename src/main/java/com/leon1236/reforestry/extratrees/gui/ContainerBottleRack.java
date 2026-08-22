package com.leon1236.reforestry.extratrees.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;
import com.leon1236.reforestry.extratrees.features.ExtraTreesMenuTypes;
import com.leon1236.reforestry.extratrees.tiles.TileBottleRack;

public class ContainerBottleRack extends ContainerMachine<TileBottleRack> implements IContainerLiquidTanks {
	private static final int INVENTORY_Y = 140;
	private static final int TANK_DATA_COUNT = TileBottleRack.TANK_COUNT * 2;
	private final SimpleContainerData tankData = new SimpleContainerData(TANK_DATA_COUNT);

	public ContainerBottleRack(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TileBottleRack.class));
	}

	public ContainerBottleRack(int containerId, Inventory playerInventory, TileBottleRack tile) {
		super(ExtraTreesMenuTypes.BOTTLE_RACK.type(), containerId, playerInventory, tile, INVENTORY_Y);
		addDataSlots(tankData);
	}

	@Override
	protected void addMachineSlots(TileBottleRack tile) {
	}

	public int getTankAmountMb(int tankIndex) {
		return tankData.get(tankIndex * 2);
	}

	public Fluid getTankFluid(int tankIndex) {
		return BuiltInRegistries.FLUID.byId(tankData.get(tankIndex * 2 + 1));
	}

	public int getTankCapacityMb() {
		return (int) FluidUnits.dropletsToMb(TileBottleRack.TANK_CAPACITY);
	}

	@Override
	public boolean clickMenuButton(Player player, int id) {
		if (super.clickMenuButton(player, id)) {
			return true;
		}
		if (id < 0 || id >= TileBottleRack.TANK_COUNT || getTank(id) == null
				|| !PipetteTankHelper.canHandleClick(player.containerMenu.getCarried())) {
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
		if (slot < 0 || slot >= TileBottleRack.TANK_COUNT) {
			return null;
		}
		return tile.getTank(slot);
	}

	@Override
	public void broadcastChanges() {
		for (int i = 0; i < TileBottleRack.TANK_COUNT; i++) {
			var tank = tile.getTank(i);
			tankData.set(i * 2, (int) FluidUnits.dropletsToMb(tank.getAmount()));
			tankData.set(i * 2 + 1, BuiltInRegistries.FLUID.getId(tank.getResource().getFluid()));
		}
		super.broadcastChanges();
	}
}
