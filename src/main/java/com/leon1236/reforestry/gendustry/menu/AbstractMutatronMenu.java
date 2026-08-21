package com.leon1236.reforestry.gendustry.menu;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
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
import com.leon1236.reforestry.gendustry.blockentity.AbstractMutatronBlockEntity;

public abstract class AbstractMutatronMenu<T extends AbstractMutatronBlockEntity> extends ContainerMachine<T>
		implements IContainerLiquidTanks, IContainerEnergy {
	protected static final int INVENTORY_Y = 94;

	private final SimpleContainerData tankData = new SimpleContainerData(2);

	protected AbstractMutatronMenu(MenuType<?> menuType, int containerId, Inventory playerInventory, T tile) {
		super(menuType, containerId, playerInventory, tile, INVENTORY_Y);
		addDataSlots(tile.getProgressData());
		addDataSlots(tile.getErrorData());
		addDataSlots(tile.getEnergyData());
		addDataSlots(this.tankData);
	}

	@Override
	protected void addMachineSlots(T tile) {
		addSlot(new FilteredSlot(tile, AbstractMutatronBlockEntity.SLOT_PRIMARY, 41, 26));
		addSlot(new FilteredSlot(tile, AbstractMutatronBlockEntity.SLOT_SECONDARY, 41, 49));
		addSlot(new FilteredSlot(tile, AbstractMutatronBlockEntity.SLOT_LABWARE, 84, 20));
		addSlot(new FilteredSlot(tile, AbstractMutatronBlockEntity.SLOT_CAN_INPUT, 11, 71));
		addSlot(new OutputSlot(tile, AbstractMutatronBlockEntity.SLOT_RESULT, 133, 39));
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

	public int getMutagenAmountMb() {
		return this.tankData.get(0);
	}

	public int getMutagenFluidId() {
		return this.tankData.get(1);
	}

	public Fluid getMutagenFluid() {
		return BuiltInRegistries.FLUID.byId(getMutagenFluidId());
	}

	public int getTankCapacityMb() {
		return (int) FluidUnits.dropletsToMb(AbstractMutatronBlockEntity.TANK_CAPACITY);
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
		return slot == 0 ? tile.getMutagenTank() : null;
	}

	@Override
	public void broadcastChanges() {
		this.tankData.set(0, (int) FluidUnits.dropletsToMb(tile.getMutagenTank().getAmount()));
		this.tankData.set(1, BuiltInRegistries.FLUID.getId(tile.getMutagenTank().getResource().getFluid()));
		super.broadcastChanges();
	}

	protected static final class FilteredSlot extends Slot {
		private final AbstractMutatronBlockEntity tile;
		private final int slotIndex;

		FilteredSlot(AbstractMutatronBlockEntity tile, int index, int x, int y) {
			super(tile, index, x, y);
			this.tile = tile;
			this.slotIndex = index;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return tile.canPlaceItem(slotIndex, stack);
		}
	}

	protected static final class OutputSlot extends Slot {
		OutputSlot(AbstractMutatronBlockEntity tile, int index, int x, int y) {
			super(tile, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
