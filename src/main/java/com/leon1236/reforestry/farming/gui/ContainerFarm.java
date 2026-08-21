package com.leon1236.reforestry.farming.gui;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.gui.IContainerClimate;
import com.leon1236.reforestry.core.circuits.ItemSolderingIron;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;
import com.leon1236.reforestry.core.gui.SlotCircuitSocket;
import com.leon1236.reforestry.farming.features.FarmingMenuTypes;
import com.leon1236.reforestry.farming.multiblock.FarmController;
import com.leon1236.reforestry.farming.multiblock.InventoryFarm;
import com.leon1236.reforestry.farming.multiblock.TileFarm;

public class ContainerFarm extends AbstractContainerMenu implements IContainerLiquidTanks, IContainerClimate, IFarmLedgerDelegate {
	private final TileFarm tile;
	private final int farmSlotCount;

	public ContainerFarm(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos));
	}

	public ContainerFarm(int containerId, Inventory playerInventory, TileFarm tile) {
		super(FarmingMenuTypes.FARM.type(), containerId);
		this.tile = tile;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				this.addSlot(new FilteredSlot(this.tile, InventoryFarm.CONFIG.resourcesStart + j + i * 2,
						123 + j * 18, 22 + i * 18));
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				this.addSlot(new FilteredSlot(this.tile, InventoryFarm.CONFIG.germlingsStart + j + i * 2,
						164 + j * 18, 22 + i * 18));
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				this.addSlot(new OutputSlot(this.tile, InventoryFarm.CONFIG.productionStart + j + i * 2,
						123 + j * 18, 86 + i * 18));
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				this.addSlot(new OutputSlot(this.tile, InventoryFarm.CONFIG.productionStart + 4 + j + i * 2,
						164 + j * 18, 86 + i * 18));
			}
		}
		this.addSlot(new FilteredSlot(this.tile, InventoryFarm.CONFIG.fertilizerStart, 63, 95));
		this.addSlot(new FilteredSlot(this.tile, InventoryFarm.CONFIG.canStart, 15, 95));
		this.addSlot(new SlotCircuitSocket(this.tile, 0, 69, 40));
		this.farmSlotCount = this.slots.size();

		addStandardInventorySlots(playerInventory, 28, 138);
		addDataSlots(tile.getFarmData());
		addDataSlots(tile.getClimateData());
		addDataSlots(tile.getErrorData());
	}

	public TileFarm getTile() {
		return this.tile;
	}

	public int getErrorCount() {
		return this.tile.getErrorData().get(0);
	}

	public short getErrorId(int index) {
		return (short) this.tile.getErrorData().get(index + 1);
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
		return (int) FluidUnits.dropletsToMb(FarmController.TANK_CAPACITY);
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
	public boolean clickMenuButton(Player player, int id) {
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
		return slot == 0 ? this.tile.getMultiblockLogic().getController().getWaterTank() : null;
	}

	@Override
	public void clicked(int slotIndex, int button, ContainerInput clickType, Player player) {
		if (slotIndex >= 0 && slotIndex < this.slots.size()) {
			Slot slot = this.slots.get(slotIndex);
			if (slot instanceof SlotCircuitSocket socketSlot) {
				ItemStack carried = getCarried();
				if (!carried.isEmpty() && carried.getItem() instanceof ItemSolderingIron) {
					if (socketSlot.tryRemoveWithSolderingIron(player, carried)) {
						broadcastChanges();
						return;
					}
				}
			}
		}
		super.clicked(slotIndex, button, clickType, player);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack result = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack stack = slot.getItem();
			result = stack.copy();
			if (index < this.farmSlotCount) {
				if (!moveItemStackTo(stack, this.farmSlotCount, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!moveItemStackTo(stack, 0, this.farmSlotCount, false)) {
				return ItemStack.EMPTY;
			}
			if (stack.isEmpty()) {
				slot.setByPlayer(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return result;
	}

	@Override
	public boolean stillValid(Player player) {
		return this.tile.stillValid(player);
	}

	private static TileFarm resolveTile(Inventory playerInventory, BlockPos pos) {
		BlockEntity blockEntity = playerInventory.player.level().getBlockEntity(pos);
		if (blockEntity instanceof TileFarm farm) {
			return farm;
		}
		throw new IllegalStateException("No farm block entity at " + pos);
	}

	private static final class FilteredSlot extends Slot {
		private final TileFarm tile;

		FilteredSlot(TileFarm tile, int index, int x, int y) {
			super(tile, index, x, y);
			this.tile = tile;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return this.tile.canPlaceItem(getContainerSlot(), stack);
		}
	}

	private static final class OutputSlot extends Slot {
		OutputSlot(TileFarm tile, int index, int x, int y) {
			super(tile, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
