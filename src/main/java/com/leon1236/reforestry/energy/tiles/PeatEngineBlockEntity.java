package com.leon1236.reforestry.energy.tiles;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.fuels.EngineCopperFuel;
import com.leon1236.reforestry.api.fuels.FuelManager;
import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.core.tiles.TemperatureState;
import com.leon1236.reforestry.energy.EnergyConstants;
import com.leon1236.reforestry.energy.features.EnergyTiles;
import com.leon1236.reforestry.energy.gui.ContainerPeatEngine;
import com.leon1236.reforestry.energy.inventory.InventoryEnginePeat;

public class PeatEngineBlockEntity extends EngineBlockEntity implements WorldlyContainer {
	private final NonNullList<ItemStack> items = NonNullList.withSize(InventoryEnginePeat.SLOT_COUNT, ItemStack.EMPTY);
	private ItemStack fuel = ItemStack.EMPTY;
	private int burnTime;
	private int totalBurnTime;
	private int ashProduction;
	private int syncedBurnTime;
	private int syncedTotalBurnTime;

	private final ContainerData burnData = new ContainerData() {
		@Override
		public int get(int index) {
			Level level = getLevel();
			boolean client = level != null && level.isClientSide();
			return switch (index) {
				case 0 -> client ? syncedBurnTime : burnTime;
				case 1 -> client ? syncedTotalBurnTime : totalBurnTime;
				default -> 0;
			};
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
				case 0 -> syncedBurnTime = value;
				case 1 -> syncedTotalBurnTime = value;
				default -> {
				}
			}
		}

		@Override
		public int getCount() {
			return 2;
		}
	};

	public PeatEngineBlockEntity(BlockPos pos, BlockState state) {
		super(EnergyTiles.PEAT_ENGINE.type(), pos, state, "engine.copper",
				EnergyConstants.ENGINE_COPPER_HEAT_MAX, EnergyConstants.ENGINE_PEAT_MAX_ENERGY);
	}

	public ContainerData getBurnData() {
		return this.burnData;
	}

	private int getFuelSlot() {
		if (getItem(InventoryEnginePeat.SLOT_FUEL).isEmpty()) {
			return -1;
		}
		if (determineFuelValue(getItem(InventoryEnginePeat.SLOT_FUEL)) > 0) {
			return InventoryEnginePeat.SLOT_FUEL;
		}
		return -1;
	}

	private int getFreeWasteSlot() {
		for (int i = InventoryEnginePeat.SLOT_WASTE_1; i < InventoryEnginePeat.SLOT_COUNT; i++) {
			ItemStack waste = getItem(i);
			if (waste.isEmpty()) {
				return i;
			}
			if (!waste.is(CoreItems.ASH.item())) {
				continue;
			}
			if (waste.getCount() < waste.getMaxStackSize()) {
				return i;
			}
		}
		return -1;
	}

	@Override
	protected void serverTick(Level level, BlockPos pos, BlockState state) {
		super.serverTick(level, pos, state);
		if (updateOnInterval(40)) {
			int fuelSlot = getFuelSlot();
			boolean hasFuel = fuelSlot >= 0 && determineBurnDuration(getItem(fuelSlot)) > 0;
			getErrorLogic().setCondition(!hasFuel, ForestryError.NO_FUEL);
		}
	}

	@Override
	public void burn() {
		this.currentOutput = 0;
		if (this.burnTime > 0) {
			this.burnTime--;
			addAsh(1);
			if (isRedstoneActivated()) {
				this.currentOutput = determineFuelValue(this.fuel);
				generateEnergy(this.currentOutput);
				this.level.updateNeighbourForOutputSignal(this.worldPosition, getBlockState().getBlock());
			}
		} else if (isRedstoneActivated()) {
			int fuelSlot = getFuelSlot();
			int wasteSlot = getFreeWasteSlot();
			if (fuelSlot >= 0 && wasteSlot >= 0) {
				ItemStack fuelStack = getItem(fuelSlot);
				this.burnTime = this.totalBurnTime = determineBurnDuration(fuelStack);
				if (this.burnTime > 0 && !fuelStack.isEmpty()) {
					this.fuel = fuelStack.copy();
					removeItem(fuelSlot, 1);
				}
			}
		}
	}

	@Override
	public void dissipateHeat() {
		if (this.heat <= 0) {
			return;
		}
		int loss = 0;
		if (!isBurning()) {
			loss += 1;
		}
		TemperatureState tempState = getTemperatureState();
		if (tempState == TemperatureState.OVERHEATING || tempState == TemperatureState.OPERATING_TEMPERATURE) {
			loss += 1;
		}
		this.heat -= loss;
	}

	@Override
	public void generateHeat() {
		int heatToAdd = 0;
		if (isBurning()) {
			heatToAdd++;
			if ((double) this.energyStorage.amount / (double) this.energyStorage.capacity > 0.5) {
				heatToAdd++;
			}
		}
		addHeat(heatToAdd);
	}

	private void addAsh(int amount) {
		this.ashProduction += amount;
		if (this.ashProduction < EnergyConstants.ENGINE_COPPER_ASH_FOR_ITEM) {
			return;
		}
		int wasteSlot = getFreeWasteSlot();
		if (wasteSlot < 0) {
			return;
		}
		ItemStack wasteStack = getItem(wasteSlot);
		if (wasteStack.isEmpty()) {
			setItem(wasteSlot, new ItemStack(CoreItems.ASH.item()));
		} else {
			wasteStack.grow(1);
		}
		this.ashProduction = 0;
	}

	private static int determineFuelValue(ItemStack fuel) {
		EngineCopperFuel copperFuel = FuelManager.getPeatEngineFuel(fuel);
		return copperFuel == null ? 0 : copperFuel.powerPerCycle();
	}

	private static int determineBurnDuration(ItemStack fuel) {
		EngineCopperFuel copperFuel = FuelManager.getPeatEngineFuel(fuel);
		return copperFuel == null ? 0 : copperFuel.burnDuration();
	}

	@Override
	public boolean isBurning() {
		return mayBurn() && this.burnTime > 0;
	}

	@Override
	public int getBurnTimeRemainingScaled(int i) {
		int total = this.totalBurnTime;
		Level level = getLevel();
		if (level != null && level.isClientSide()) {
			total = this.syncedTotalBurnTime;
		}
		if (total == 0) {
			return 0;
		}
		int remaining = this.burnTime;
		if (level != null && level.isClientSide()) {
			remaining = this.syncedBurnTime;
		}
		return remaining * i / total;
	}

	@Override
	public boolean hasFuelMin(float percentage) {
		int fuelSlot = getFuelSlot();
		if (fuelSlot < 0) {
			return false;
		}
		ItemStack fuelStack = getItem(fuelSlot);
		return (float) fuelStack.getCount() / (float) fuelStack.getMaxStackSize() > percentage;
	}

	@Override
	public int getContainerSize() {
		return InventoryEnginePeat.SLOT_COUNT;
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
		return InventoryEnginePeat.canSlotAccept(slot, stack);
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
		return WorldlyAccessHelper.getSlotsForFace(this, InventoryEnginePeat.SLOT_COUNT, direction);
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return WorldlyAccessHelper.canPlaceItemThroughFace(this, canPlaceItem(slot, stack), direction);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return WorldlyAccessHelper.canTakeItemThroughFace(this, InventoryEnginePeat.canTakeItemThroughFace(slot), direction);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.items.clear();
		ContainerHelper.loadAllItems(input, this.items);
		this.fuel = input.read("EngineFuelItemStack", ItemStack.CODEC).orElse(ItemStack.EMPTY);
		this.burnTime = input.getIntOr("EngineBurnTime", 0);
		this.totalBurnTime = input.getIntOr("EngineTotalTime", 0);
		this.ashProduction = input.getIntOr("AshProduction", 0);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.items);
		if (!this.fuel.isEmpty()) {
			output.store("EngineFuelItemStack", ItemStack.CODEC, this.fuel);
		}
		output.putInt("EngineBurnTime", this.burnTime);
		output.putInt("EngineTotalTime", this.totalBurnTime);
		output.putInt("AshProduction", this.ashProduction);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerPeatEngine(containerId, playerInventory, this);
	}
}
