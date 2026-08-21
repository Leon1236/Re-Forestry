package com.leon1236.reforestry.energy.tiles;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.fuels.EngineBronzeFuel;
import com.leon1236.reforestry.api.fuels.FuelManager;
import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.energy.EnergyConstants;
import com.leon1236.reforestry.energy.features.EnergyTiles;
import com.leon1236.reforestry.energy.gui.ContainerBiogasEngine;
import com.leon1236.reforestry.energy.inventory.InventoryEngineBiogas;

public class BiogasEngineBlockEntity extends EngineBlockEntity implements WorldlyContainer {
	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(EnergyConstants.ENGINE_TANK_CAPACITY_MB);
	private static final long BUCKET_DROPLETS = FluidUnits.mbToDroplets(1000);
	private static final long MB_DROPLETS = FluidUnits.mbToDroplets(1);
	private static final long DEFAULT_BURN_CAPACITY = BUCKET_DROPLETS;

	private final NonNullList<ItemStack> items = NonNullList.withSize(InventoryEngineBiogas.SLOT_COUNT, ItemStack.EMPTY);
	private final MultiFluidTank tanks;
	private boolean shutdown;

	public BiogasEngineBlockEntity(BlockPos pos, BlockState state) {
		super(EnergyTiles.BIOGAS_ENGINE.type(), pos, state, "engine.bronze",
				EnergyConstants.ENGINE_BRONZE_HEAT_MAX, EnergyConstants.ENGINE_BIOGAS_MAX_ENERGY);
		this.tanks = MultiFluidTank.builder(this::setChanged)
				.tank("Fuel", TANK_CAPACITY, this::isBiogasFuel)
				.tank("Heating", TANK_CAPACITY, variant -> variant.getFluid().defaultFluidState().is(FluidTags.LAVA), false)
				.tank("Burn", DEFAULT_BURN_CAPACITY, variant -> false, false)
				.build();
	}

	public MultiFluidTank getTankManager() {
		return this.tanks;
	}

	public FilteredFluidStorage getFuelTank() {
		return this.tanks.tank("Fuel");
	}

	public FilteredFluidStorage getHeatingTank() {
		return this.tanks.tank("Heating");
	}

	public FilteredFluidStorage getBurnTank() {
		return this.tanks.tank("Burn");
	}

	private boolean isBiogasFuel(FluidVariant variant) {
		return FuelManager.isBiogasEngineFuel(variant.getFluid());
	}

	@Override
	protected void serverTick(Level level, BlockPos pos, BlockState state) {
		super.serverTick(level, pos, state);
		if (!updateOnInterval(20)) {
			return;
		}

		FluidContainerHelper.drainIntoTank(this, InventoryEngineBiogas.SLOT_CAN, this.tanks);

		IErrorLogic errorLogic = getErrorLogic();
		boolean hasHeat = getHeatLevel() > 0.2 || getHeatingTank().getAmount() > 0;
		errorLogic.setCondition(!hasHeat, ForestryError.NO_HEAT);
		boolean hasFuel = getBurnTank().getAmount() > 0 || getFuelTank().getAmount() > 0;
		errorLogic.setCondition(!hasFuel, ForestryError.NO_FUEL);
	}

	@Override
	public void burn() {
		this.currentOutput = 0;
		if (!isRedstoneActivated() || (getFuelTank().getAmount() < BUCKET_DROPLETS && getBurnTank().getAmount() <= 0)) {
			return;
		}

		double heatStage = getHeatLevel();
		if (heatStage > 0.25 && this.shutdown) {
			shutdown(false);
		} else if (this.shutdown) {
			FilteredFluidStorage heatingTank = getHeatingTank();
			if (heatingTank.getAmount() > 0 && heatingTank.variant.getFluid() == Fluids.LAVA) {
				addHeat(EnergyConstants.ENGINE_HEAT_VALUE_LAVA);
				heatingTank.drainInternal(MB_DROPLETS);
			}
		}

		if (heatStage > 0.2) {
			FilteredFluidStorage burnTank = getBurnTank();
			if (burnTank.getAmount() > 0) {
				Fluid fluid = burnTank.variant.getFluid();
				if (burnTank.drainInternal(MB_DROPLETS) > 0) {
					this.currentOutput = determineFuelValue(fluid);
					generateEnergy(this.currentOutput);
					this.level.updateNeighbourForOutputSignal(this.worldPosition, getBlockState().getBlock());
				}
			} else {
				loadBurnTankFromFuel();
			}
		} else {
			shutdown(true);
		}
	}

	private void loadBurnTankFromFuel() {
		FilteredFluidStorage fuelTank = getFuelTank();
		FluidVariant fuelVariant = fuelTank.variant;
		if (fuelVariant.isBlank() || fuelTank.getAmount() < BUCKET_DROPLETS) {
			return;
		}
		try (Transaction transaction = Transaction.openOuter()) {
			long drained = fuelTank.extract(fuelVariant, BUCKET_DROPLETS, transaction);
			if (drained != BUCKET_DROPLETS) {
				return;
			}
			transaction.commit();
		}
		int burnTime = determineBurnTime(fuelVariant.getFluid());
		FilteredFluidStorage burnTank = getBurnTank();
		burnTank.setCapacity(FluidUnits.mbToDroplets(burnTime));
		if (burnTime > 0) {
			burnTank.setFluid(fuelVariant, FluidUnits.mbToDroplets(burnTime));
		} else {
			burnTank.setFluid(FluidVariant.blank(), 0);
		}
	}

	private void shutdown(boolean val) {
		this.shutdown = val;
	}

	@Override
	public void dissipateHeat() {
		if (this.heat <= 0) {
			return;
		}

		int loss = 1;
		if (!isBurning()) {
			loss++;
		}

		double heatStage = getHeatLevel();
		if (heatStage > 0.55) {
			loss++;
		}

		FilteredFluidStorage fuelTank = getFuelTank();
		if (fuelTank.getAmount() > 0 && !fuelTank.variant.isBlank()) {
			EngineBronzeFuel fuel = FuelManager.getBiogasEngineFuel(fuelTank.variant.getFluid());
			if (fuel != null) {
				loss = loss * fuel.dissipationMultiplier();
			}
		}

		this.heat -= loss;
	}

	@Override
	public void generateHeat() {
		if (!mayBurn()) {
			return;
		}
		int generate = 0;
		if (isRedstoneActivated() && getBurnTank().getAmount() > 0) {
			double heatStage = getHeatLevel();
			if (heatStage >= 0.75) {
				generate += EnergyConstants.ENGINE_BRONZE_HEAT_GENERATION_ENERGY * 3;
			} else if (heatStage > 0.24) {
				generate += EnergyConstants.ENGINE_BRONZE_HEAT_GENERATION_ENERGY * 2;
			} else if (heatStage > 0.2) {
				generate += EnergyConstants.ENGINE_BRONZE_HEAT_GENERATION_ENERGY;
			}
		}
		addHeat(generate);
	}

	private static int determineFuelValue(@Nullable Fluid fluid) {
		EngineBronzeFuel fuel = FuelManager.getBiogasEngineFuel(fluid);
		return fuel == null ? 0 : fuel.powerPerCycle();
	}

	private static int determineBurnTime(@Nullable Fluid fluid) {
		EngineBronzeFuel fuel = FuelManager.getBiogasEngineFuel(fluid);
		return fuel == null ? 0 : fuel.burnDuration();
	}

	@Override
	protected boolean isBurning() {
		return mayBurn() && getBurnTank().getAmount() > 0;
	}

	@Override
	public int getBurnTimeRemainingScaled(int i) {
		FilteredFluidStorage burnTank = getBurnTank();
		long capacity = burnTank.getTankCapacity();
		if (capacity == 0) {
			return 0;
		}
		return (int) (burnTank.getAmount() * i / capacity);
	}

	public int getOperatingTemperatureScaled(int i) {
		return (int) Math.round(this.heat * i / (this.maxHeat * 0.2));
	}

	@Override
	public int getContainerSize() {
		return InventoryEngineBiogas.SLOT_COUNT;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : this.items) {
			if (!stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.items.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		ItemStack result = ContainerHelper.removeItem(this.items, slot, amount);
		if (!result.isEmpty()) {
			setChanged();
		}
		return result;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(this.items, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		this.items.set(slot, stack);
		if (stack.getCount() > getMaxStackSize(stack)) {
			stack.setCount(getMaxStackSize(stack));
		}
		setChanged();
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return InventoryEngineBiogas.canSlotAccept(slot, stack, this.tanks);
	}

	@Override
	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return WorldlyAccessHelper.getSlotsForFace(this, InventoryEngineBiogas.SLOT_COUNT, direction);
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return WorldlyAccessHelper.canPlaceItemThroughFace(this, canPlaceItem(slot, stack), direction);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return WorldlyAccessHelper.canTakeItemThroughFace(this, InventoryEngineBiogas.canTakeItemThroughFace(slot), direction);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.items.clear();
		ContainerHelper.loadAllItems(input, this.items);
		this.shutdown = input.getBooleanOr("shutdown", false);
		getBurnTank().setCapacity(input.getLongOr("BurnCapacity", DEFAULT_BURN_CAPACITY));
		this.tanks.readValue(input);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.items);
		output.putBoolean("shutdown", this.shutdown);
		output.putLong("BurnCapacity", getBurnTank().getTankCapacity());
		this.tanks.writeValue(output);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerBiogasEngine(containerId, playerInventory, this);
	}
}
