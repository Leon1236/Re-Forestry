package com.leon1236.reforestry.gendustry.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import com.leon1236.reforestry.api.gui.IContainerEnergy;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;
import com.leon1236.reforestry.gendustry.blockentity.ReplicatorBlockEntity;
import com.leon1236.reforestry.gendustry.features.GMenus;

public class ReplicatorMenu extends ContainerMachine<ReplicatorBlockEntity>
		implements IContainerLiquidTanks, IContainerEnergy {
	private static final int INVENTORY_Y = 94;

	private final SimpleContainerData tankData = new SimpleContainerData(4);

	public ReplicatorMenu(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, ReplicatorBlockEntity.class));
	}

	public ReplicatorMenu(int containerId, Inventory playerInventory, ReplicatorBlockEntity tile) {
		super(GMenus.REPLICATOR.type(), containerId, playerInventory, tile, INVENTORY_Y);
		addDataSlots(tile.getProgressData());
		addDataSlots(tile.getErrorData());
		addDataSlots(tile.getEnergyData());
		addDataSlots(this.tankData);
	}

	@Override
	protected void addMachineSlots(ReplicatorBlockEntity tile) {
		addSlot(new FilteredSlot(tile, ReplicatorBlockEntity.SLOT_TEMPLATE, 80, 23));
		addSlot(new FilteredSlot(tile, ReplicatorBlockEntity.SLOT_DNA_CAN_INPUT, 11, 71));
		addSlot(new FilteredSlot(tile, ReplicatorBlockEntity.SLOT_PROTEIN_CAN_INPUT, 31, 71));
		addSlot(new OutputSlot(tile, ReplicatorBlockEntity.SLOT_OUTPUT, 124, 47));
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

	public int getDnaAmountMb() {
		return this.tankData.get(0);
	}

	public int getDnaFluidId() {
		return this.tankData.get(1);
	}

	public int getProteinAmountMb() {
		return this.tankData.get(2);
	}

	public int getProteinFluidId() {
		return this.tankData.get(3);
	}

	public Fluid getDnaFluid() {
		return BuiltInRegistries.FLUID.byId(getDnaFluidId());
	}

	public Fluid getProteinFluid() {
		return BuiltInRegistries.FLUID.byId(getProteinFluidId());
	}

	public int getTankCapacityMb() {
		return (int) FluidUnits.dropletsToMb(ReplicatorBlockEntity.TANK_CAPACITY);
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
			case 0 -> tile.getDnaTank();
			case 1 -> tile.getProteinTank();
			default -> null;
		};
	}

	@Override
	public void broadcastChanges() {
		this.tankData.set(0, (int) FluidUnits.dropletsToMb(tile.getDnaTank().getAmount()));
		this.tankData.set(1, BuiltInRegistries.FLUID.getId(tile.getDnaTank().getResource().getFluid()));
		this.tankData.set(2, (int) FluidUnits.dropletsToMb(tile.getProteinTank().getAmount()));
		this.tankData.set(3, BuiltInRegistries.FLUID.getId(tile.getProteinTank().getResource().getFluid()));
		super.broadcastChanges();
	}

	private static final class FilteredSlot extends Slot {
		private final ReplicatorBlockEntity tile;
		private final int slotIndex;

		FilteredSlot(ReplicatorBlockEntity tile, int index, int x, int y) {
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
		OutputSlot(ReplicatorBlockEntity tile, int index, int x, int y) {
			super(tile, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
