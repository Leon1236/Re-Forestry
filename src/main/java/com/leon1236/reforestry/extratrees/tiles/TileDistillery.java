package com.leon1236.reforestry.extratrees.tiles;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.tiles.TilePowered;
import com.leon1236.reforestry.extratrees.features.ExtraTreesTiles;
import com.leon1236.reforestry.extratrees.gui.ContainerDistillery;
import com.leon1236.reforestry.extratrees.recipes.DistilleryRecipeManager;

public class TileDistillery extends TilePowered implements WorldlyContainer {
	private static final long ENERGY_CAPACITY = 10000;
	private static final long ENERGY_MAX_RECEIVE = 200;
	private static final int PROCESS_ENERGY = 16000;
	private static final int BASE_PROCESS_TIME = 2000;
	private static final int LEVEL_PROCESS_TIME = 800;
	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(5000);

	private final MultiFluidTank tanks;
	private int level;

	public TileDistillery(BlockPos pos, BlockState state) {
		super(ExtraTreesTiles.DISTILLERY.type(), pos, state, ENERGY_CAPACITY, ENERGY_MAX_RECEIVE);
		this.tanks = MultiFluidTank.builder(this::setChanged)
				.tank("Input", TANK_CAPACITY, FilteredFluidStorage.any())
				.tank("Output", TANK_CAPACITY, FilteredFluidStorage.any())
				.build();
		updateProcessTiming();
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, TileDistillery tile) {
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

	public int getDistillLevel() {
		return level;
	}

	public void setDistillLevel(int level) {
		this.level = Math.floorMod(level, 3);
		updateProcessTiming();
		setChanged();
	}

	public void cycleLevel() {
		setDistillLevel(this.level + 1);
	}

	private void updateProcessTiming() {
		setTicksPerWorkCycle(BASE_PROCESS_TIME + LEVEL_PROCESS_TIME * this.level);
		setEnergyPerWorkCycle(PROCESS_ENERGY);
	}

	@Nullable
	private DistilleryRecipeManager.DistilleryRecipe findCurrentRecipe() {
		FluidVariant variant = getInputTank().getResource();
		if (variant.isBlank()) {
			return null;
		}
		DistilleryRecipeManager.DistilleryRecipe recipe = DistilleryRecipeManager.getRecipe(variant.getFluid(), level);
		if (recipe == null) {
			return null;
		}
		if (getInputTank().getAmount() < recipe.inputAmount()) {
			return null;
		}
		return recipe;
	}

	@Override
	public boolean hasWork() {
		DistilleryRecipeManager.DistilleryRecipe recipe = findCurrentRecipe();
		if (recipe == null) {
			return false;
		}
		FluidVariant output = FluidVariant.of(recipe.output());
		FilteredFluidStorage tank = getOutputTank();
		if (!tank.getResource().isBlank() && !tank.getResource().equals(output)) {
			return false;
		}
		try (Transaction transaction = Transaction.openOuter()) {
			return tank.insert(output, recipe.outputAmount(), transaction) == recipe.outputAmount();
		}
	}

	@Override
	protected boolean workCycle() {
		DistilleryRecipeManager.DistilleryRecipe recipe = findCurrentRecipe();
		if (recipe == null) {
			return false;
		}
		FluidVariant input = getInputTank().getResource();
		FluidVariant output = FluidVariant.of(recipe.output());
		try (Transaction transaction = Transaction.openOuter()) {
			if (getInputTank().extract(input, recipe.inputAmount(), transaction) != recipe.inputAmount()) {
				return false;
			}
			if (getOutputTank().insert(output, recipe.outputAmount(), transaction) != recipe.outputAmount()) {
				return false;
			}
			transaction.commit();
		}
		return true;
	}

	@Override
	public int getContainerSize() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ItemStack.EMPTY;
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return false;
	}

	@Override
	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public void clearContent() {
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return new int[0];
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return false;
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return false;
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		this.tanks.writeValue(output.child("Tanks"));
		output.putByte("DistillLevel", (byte) level);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.tanks.readValue(input.childOrEmpty("Tanks"));
		this.level = input.getByteOr("DistillLevel", (byte) 0);
		updateProcessTiming();
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerDistillery(containerId, playerInventory, this);
	}
}
