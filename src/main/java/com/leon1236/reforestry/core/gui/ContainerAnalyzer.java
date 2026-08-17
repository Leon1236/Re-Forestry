package com.leon1236.reforestry.core.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import com.leon1236.reforestry.api.gui.IContainerEnergy;
import com.leon1236.reforestry.core.features.CoreMenuTypes;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.ForestryFluids;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.tiles.TileAnalyzer;

public class ContainerAnalyzer extends ContainerMachine<TileAnalyzer> implements IContainerLiquidTanks, IContainerEnergy {
	private static final int INVENTORY_Y = 94;
	private static final int FLUID_NONE = 0;
	private static final int FLUID_HONEY = 1;

	private final SimpleContainerData tankData = new SimpleContainerData(2);

	public ContainerAnalyzer(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TileAnalyzer.class));
	}

	public ContainerAnalyzer(int containerId, Inventory playerInventory, TileAnalyzer tile) {
		super(CoreMenuTypes.ANALYZER.type(), containerId, playerInventory, tile, INVENTORY_Y);
		addDataSlots(tile.getProgressData());
		addDataSlots(tile.getErrorData());
		addDataSlots(tile.getEnergyData());
		addDataSlots(tankData);
	}

	@Override
	protected void addMachineSlots(TileAnalyzer tile) {
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 2; k++) {
				addSlot(new FilteredSlot(tile, TileAnalyzer.SLOT_INPUT_1 + i * 2 + k, 8 + k * 18, 28 + i * 18));
			}
		}
		addSlot(new WorkingSlot(tile, TileAnalyzer.SLOT_ANALYZE, 73, 59));
		addSlot(new FilteredSlot(tile, TileAnalyzer.SLOT_CAN, 143, 24));
		for (int i = 0; i < 2; i++) {
			for (int k = 0; k < 2; k++) {
				addSlot(new OutputSlot(tile, TileAnalyzer.SLOT_OUTPUT_1 + i * 2 + k, 134 + k * 18, 48 + i * 18));
			}
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

	public int getResourceAmountMb() {
		return tankData.get(0);
	}

	public int getResourceFluidType() {
		return tankData.get(1);
	}

	public int getTankCapacityMb() {
		return (int) FluidUnits.dropletsToMb(TileAnalyzer.TANK_CAPACITY);
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
		if (slot == 0) {
			return tile.getResourceTank();
		}
		return null;
	}

	@Override
	public void broadcastChanges() {
		tankData.set(0, (int) FluidUnits.dropletsToMb(tile.getResourceTank().getAmount()));
		tankData.set(1, ForestryFluids.HONEY.is(tile.getResourceTank().getResource().getFluid()) ? FLUID_HONEY : FLUID_NONE);
		super.broadcastChanges();
	}

	private static final class FilteredSlot extends Slot {
		private final TileAnalyzer tile;

		FilteredSlot(TileAnalyzer tile, int index, int x, int y) {
			super(tile, index, x, y);
			this.tile = tile;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return tile.canPlaceItem(getContainerSlot(), stack);
		}
	}

	private static final class WorkingSlot extends Slot {
		WorkingSlot(TileAnalyzer tile, int index, int x, int y) {
			super(tile, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}

		@Override
		public boolean mayPickup(Player player) {
			return false;
		}

		@Override
		public boolean isFake() {
			return true;
		}
	}

	private static final class OutputSlot extends Slot {
		OutputSlot(TileAnalyzer tile, int index, int x, int y) {
			super(tile, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
