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
import com.leon1236.reforestry.extratrees.tiles.TilePress;

public class ContainerPress extends ContainerMachine<TilePress> implements IContainerEnergy, IContainerLiquidTanks {
	private static final int INVENTORY_Y = 84;
	private final SimpleContainerData tankData = new SimpleContainerData(2);

	public ContainerPress(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TilePress.class));
	}

	public ContainerPress(int containerId, Inventory playerInventory, TilePress tile) {
		super(ExtraTreesMenuTypes.PRESS.type(), containerId, playerInventory, tile, INVENTORY_Y);
		addDataSlots(tile.getProgressData());
		addDataSlots(tile.getEnergyData());
		addDataSlots(tankData);
	}

	@Override
	protected void addMachineSlots(TilePress tile) {
		addSlot(new Slot(tile, TilePress.SLOT_FRUIT, 24, 52) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return true;
			}
		});
		addSlot(new Slot(tile, TilePress.SLOT_CURRENT, 62, 52) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}

			@Override
			public boolean mayPickup(Player player) {
				return false;
			}
		});
	}

	public int getOutputAmountMb() {
		return tankData.get(0);
	}

	public int getTankCapacityMb() {
		return (int) FluidUnits.dropletsToMb(TilePress.TANK_CAPACITY);
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
		return slot == 0 ? tile.getOutputTank() : null;
	}

	@Override
	public void broadcastChanges() {
		tankData.set(0, (int) FluidUnits.dropletsToMb(tile.getOutputTank().getAmount()));
		tankData.set(1, tile.getOutputTank().getAmount() > 0 ? 1 : 0);
		super.broadcastChanges();
	}
}
