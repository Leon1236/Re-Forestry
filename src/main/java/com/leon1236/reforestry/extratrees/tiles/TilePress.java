package com.leon1236.reforestry.extratrees.tiles;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.tiles.TilePowered;
import com.leon1236.reforestry.extratrees.features.ExtraTreesTiles;
import com.leon1236.reforestry.extratrees.gui.ContainerPress;
import com.leon1236.reforestry.extratrees.recipes.FruitPressRecipeManager;

public class TilePress extends TilePowered implements WorldlyContainer {
	public static final int SLOT_FRUIT = 0;
	public static final int SLOT_CURRENT = 1;
	public static final int SLOT_COUNT = 2;
	private static final long ENERGY_CAPACITY = 10000;
	private static final long ENERGY_MAX_RECEIVE = 200;
	private static final int PROCESS_ENERGY = 1000;
	private static final int PROCESS_TIME = 50;
	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(5000);

	private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
	private final MultiFluidTank tanks;

	public TilePress(BlockPos pos, BlockState state) {
		super(ExtraTreesTiles.PRESS.type(), pos, state, ENERGY_CAPACITY, ENERGY_MAX_RECEIVE);
		this.tanks = MultiFluidTank.builder(this::setChanged)
				.tank("Output", TANK_CAPACITY, FilteredFluidStorage.any())
				.build();
		setTicksPerWorkCycle(PROCESS_TIME);
		setEnergyPerWorkCycle(PROCESS_ENERGY);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, TilePress tile) {
		tile.restockCurrent();
		tile.doWork(true);
	}

	public MultiFluidTank getTankManager() {
		return this.tanks;
	}

	public FilteredFluidStorage getOutputTank() {
		return this.tanks.tank("Output");
	}

	private void restockCurrent() {
		if (!items.get(SLOT_CURRENT).isEmpty()) {
			return;
		}
		ItemStack input = items.get(SLOT_FRUIT);
		if (input.isEmpty() || !FruitPressRecipeManager.isInput(input)) {
			return;
		}
		items.set(SLOT_CURRENT, input.split(1));
		if (input.isEmpty()) {
			items.set(SLOT_FRUIT, ItemStack.EMPTY);
		}
		setChanged();
	}

	@Override
	public boolean hasWork() {
		ItemStack current = items.get(SLOT_CURRENT);
		FruitPressRecipeManager.FruitPressRecipe recipe = FruitPressRecipeManager.getRecipe(current);
		if (recipe == null) {
			return false;
		}
		FilteredFluidStorage tank = getOutputTank();
		FluidVariant output = FluidVariant.of(recipe.output());
		if (!tank.getResource().isBlank() && !tank.getResource().equals(output)) {
			return false;
		}
		try (Transaction transaction = Transaction.openOuter()) {
			return tank.insert(output, recipe.amountDroplets(), transaction) == recipe.amountDroplets();
		}
	}

	@Override
	protected boolean workCycle() {
		ItemStack current = items.get(SLOT_CURRENT);
		FruitPressRecipeManager.FruitPressRecipe recipe = FruitPressRecipeManager.getRecipe(current);
		if (recipe == null) {
			return false;
		}
		FluidVariant output = FluidVariant.of(recipe.output());
		FilteredFluidStorage tank = getOutputTank();
		try (Transaction transaction = Transaction.openOuter()) {
			if (tank.insert(output, recipe.amountDroplets(), transaction) != recipe.amountDroplets()) {
				return false;
			}
			transaction.commit();
		}
		current.shrink(1);
		if (current.isEmpty()) {
			items.set(SLOT_CURRENT, ItemStack.EMPTY);
		}
		setChanged();
		return true;
	}

	@Override
	public int getContainerSize() {
		return SLOT_COUNT;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : items) {
			if (!stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
		return items.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		ItemStack result = ContainerHelper.removeItem(items, slot, amount);
		if (!result.isEmpty()) {
			setChanged();
		}
		return result;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(items, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		items.set(slot, stack);
		if (stack.getCount() > getMaxStackSize(stack)) {
			stack.setCount(getMaxStackSize(stack));
		}
		setChanged();
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return slot == SLOT_FRUIT && FruitPressRecipeManager.isInput(stack);
	}

	@Override
	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public void clearContent() {
		items.clear();
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return WorldlyAccessHelper.getSlotsForFace(this, SLOT_COUNT, direction);
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return WorldlyAccessHelper.canPlaceItemThroughFace(this, canPlaceItem(slot, stack), direction);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return false;
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, items);
		this.tanks.writeValue(output.child("Tanks"));
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		items.clear();
		ContainerHelper.loadAllItems(input, items);
		this.tanks.readValue(input.childOrEmpty("Tanks"));
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerPress(containerId, playerInventory, this);
	}
}
