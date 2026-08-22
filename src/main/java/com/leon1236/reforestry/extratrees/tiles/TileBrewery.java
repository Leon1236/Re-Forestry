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
import com.leon1236.reforestry.extratrees.gui.ContainerBrewery;
import com.leon1236.reforestry.extratrees.recipes.BreweryRecipeManager;

public class TileBrewery extends TilePowered implements WorldlyContainer {
	public static final int SLOT_GRAIN_0 = 0;
	public static final int SLOT_GRAIN_1 = 1;
	public static final int SLOT_GRAIN_2 = 2;
	public static final int SLOT_INPUT = 3;
	public static final int SLOT_YEAST = 4;
	public static final int SLOT_INVENTORY_START = 5;
	public static final int SLOT_INVENTORY_COUNT = 9;
	public static final int SLOT_COUNT = SLOT_INVENTORY_START + SLOT_INVENTORY_COUNT;
	private static final long ENERGY_CAPACITY = 10000;
	private static final long ENERGY_MAX_RECEIVE = 200;
	private static final int PROCESS_ENERGY = 16000;
	private static final int PROCESS_TIME = 800;
	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(5000);

	private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
	private final MultiFluidTank tanks;

	public TileBrewery(BlockPos pos, BlockState state) {
		super(ExtraTreesTiles.BREWERY.type(), pos, state, ENERGY_CAPACITY, ENERGY_MAX_RECEIVE);
		this.tanks = MultiFluidTank.builder(this::setChanged)
				.tank("Input", TANK_CAPACITY, FilteredFluidStorage.any())
				.tank("Output", TANK_CAPACITY, FilteredFluidStorage.any())
				.build();
		setTicksPerWorkCycle(PROCESS_TIME);
		setEnergyPerWorkCycle(PROCESS_ENERGY);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, TileBrewery tile) {
		tile.doWork(true);
	}

	public MultiFluidTank getTankManager() {
		return this.tanks;
	}

	public FilteredFluidStorage getInputTank() {
		return this.tanks.tank("Input");
	}

	public FilteredFluidStorage getOutputTank() {
		return this.tanks.tank("Output");
	}

	@Nullable
	private BreweryRecipeManager.BreweryRecipe findCurrentRecipe() {
		if (getInputTank().getAmount() < BreweryRecipeManager.BUCKET_DROPLETS) {
			return null;
		}
		FluidVariant variant = getInputTank().getResource();
		if (variant.isBlank()) {
			return null;
		}
		ItemStack[] grains = new ItemStack[]{
				items.get(SLOT_GRAIN_0),
				items.get(SLOT_GRAIN_1),
				items.get(SLOT_GRAIN_2)
		};
		return BreweryRecipeManager.find(new BreweryRecipeManager.BreweryCrafting(
				variant.getFluid(), items.get(SLOT_INPUT), grains, items.get(SLOT_YEAST)));
	}

	@Override
	public boolean hasWork() {
		BreweryRecipeManager.BreweryRecipe recipe = findCurrentRecipe();
		if (recipe == null) {
			return false;
		}
		FluidVariant output = FluidVariant.of(recipe.outputFluid());
		FilteredFluidStorage tank = getOutputTank();
		if (!tank.getResource().isBlank() && !tank.getResource().equals(output)) {
			return false;
		}
		try (Transaction transaction = Transaction.openOuter()) {
			return tank.insert(output, BreweryRecipeManager.BUCKET_DROPLETS, transaction) == BreweryRecipeManager.BUCKET_DROPLETS;
		}
	}

	@Override
	protected boolean workCycle() {
		BreweryRecipeManager.BreweryRecipe recipe = findCurrentRecipe();
		if (recipe == null) {
			return false;
		}
		FluidVariant inputVariant = getInputTank().getResource();
		FluidVariant output = FluidVariant.of(recipe.outputFluid());
		try (Transaction transaction = Transaction.openOuter()) {
			if (getInputTank().extract(inputVariant, BreweryRecipeManager.BUCKET_DROPLETS, transaction) != BreweryRecipeManager.BUCKET_DROPLETS) {
				return false;
			}
			if (getOutputTank().insert(output, BreweryRecipeManager.BUCKET_DROPLETS, transaction) != BreweryRecipeManager.BUCKET_DROPLETS) {
				return false;
			}
			transaction.commit();
		}
		if (recipe.grainTag() != null) {
			for (int slot : new int[]{SLOT_GRAIN_0, SLOT_GRAIN_1, SLOT_GRAIN_2}) {
				ItemStack stack = items.get(slot);
				if (!stack.isEmpty()) {
					stack.shrink(1);
					if (stack.isEmpty()) {
						items.set(slot, ItemStack.EMPTY);
					}
				}
			}
			if (recipe.ingredientTag() != null) {
				ItemStack ingredient = items.get(SLOT_INPUT);
				if (!ingredient.isEmpty()) {
					ingredient.shrink(1);
					if (ingredient.isEmpty()) {
						items.set(SLOT_INPUT, ItemStack.EMPTY);
					}
				}
			}
		}
		ItemStack yeast = items.get(SLOT_YEAST);
		if (!yeast.isEmpty()) {
			yeast.shrink(1);
			if (yeast.isEmpty()) {
				items.set(SLOT_YEAST, ItemStack.EMPTY);
			}
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
		return switch (slot) {
			case SLOT_GRAIN_0, SLOT_GRAIN_1, SLOT_GRAIN_2 -> BreweryRecipeManager.isValidGrain(stack);
			case SLOT_INPUT -> BreweryRecipeManager.isValidIngredient(stack);
			case SLOT_YEAST -> BreweryRecipeManager.isValidYeast(stack);
			default -> slot >= SLOT_INVENTORY_START;
		};
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
		return slot >= SLOT_INVENTORY_START;
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
		return new ContainerBrewery(containerId, playerInventory, this);
	}
}
