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
import com.leon1236.reforestry.gendustry.blockentity.ProducerBlockEntity;
import com.leon1236.reforestry.gendustry.features.GMenus;

public class ProducerMenu extends ContainerMachine<ProducerBlockEntity<?>> implements IContainerLiquidTanks, IContainerEnergy {
	private static final int INPUT_X = 14;
	private static final int INPUT_Y = 41;
	private static final int CAN_INPUT_X = 147;
	private static final int CAN_INPUT_Y = 25;
	private static final int CAN_OUTPUT_X = 147;
	private static final int CAN_OUTPUT_Y = 61;
	private static final int LABWARE_X = 64;
	private static final int LABWARE_Y = 19;
	private static final int INVENTORY_Y = 84;

	private final SimpleContainerData tankData = new SimpleContainerData(2);

	public ProducerMenu(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, ProducerBlockEntity.class));
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public ProducerMenu(int containerId, Inventory playerInventory, ProducerBlockEntity tile) {
		super(GMenus.PROCESSOR.type(), containerId, playerInventory, tile, INVENTORY_Y);
		addDataSlots(tile.getProgressData());
		addDataSlots(tile.getErrorData());
		addDataSlots(tile.getEnergyData());
		addDataSlots(tankData);
	}

	@Override
	protected void addMachineSlots(ProducerBlockEntity<?> tile) {
		addSlot(new FilteredSlot(tile, ProducerBlockEntity.SLOT_INPUT, INPUT_X, INPUT_Y));
		addSlot(new FilteredSlot(tile, ProducerBlockEntity.SLOT_CAN_INPUT, CAN_INPUT_X, CAN_INPUT_Y));
		addSlot(new OutputSlot(tile, ProducerBlockEntity.SLOT_CAN_OUTPUT, CAN_OUTPUT_X, CAN_OUTPUT_Y));
		if (tile.usesLabware) {
			addSlot(new FilteredSlot(tile, ProducerBlockEntity.SLOT_LABWARE, LABWARE_X, LABWARE_Y));
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

	public int getProductAmountMb() {
		return tankData.get(0);
	}

	public int getProductFluidId() {
		return tankData.get(1);
	}

	public Fluid getProductFluid() {
		return BuiltInRegistries.FLUID.byId(getProductFluidId());
	}

	public int getTankCapacityMb() {
		return (int) FluidUnits.dropletsToMb(ProducerBlockEntity.TANK_CAPACITY);
	}

	public boolean usesLabware() {
		return tile.usesLabware;
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
		return slot == 0 ? tile.getProductTank() : null;
	}

	@Override
	public void broadcastChanges() {
		tankData.set(0, (int) FluidUnits.dropletsToMb(tile.getProductTank().getAmount()));
		tankData.set(1, BuiltInRegistries.FLUID.getId(tile.getProductTank().getResource().getFluid()));
		super.broadcastChanges();
	}

	private static final class FilteredSlot extends Slot {
		private final ProducerBlockEntity<?> tile;
		private final int slotIndex;

		FilteredSlot(ProducerBlockEntity<?> tile, int index, int x, int y) {
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
		OutputSlot(ProducerBlockEntity<?> tile, int index, int x, int y) {
			super(tile, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
