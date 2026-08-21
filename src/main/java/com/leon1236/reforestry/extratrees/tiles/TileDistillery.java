package com.leon1236.reforestry.extratrees.tiles;

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
import com.leon1236.reforestry.extratrees.gui.ContainerDistillery;

public class TileDistillery extends TilePowered implements WorldlyContainer {
	public static final int SLOT_COUNT = 0;
	private static final long ENERGY_CAPACITY = 10000;
	private static final long ENERGY_MAX_RECEIVE = 200;
	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(5000);

	private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
	private final MultiFluidTank tanks;

	public TileDistillery(BlockPos pos, BlockState state) {
		super(ExtraTreesTiles.DISTILLERY.type(), pos, state, ENERGY_CAPACITY, ENERGY_MAX_RECEIVE);
		this.tanks = MultiFluidTank.builder(this::setChanged)
				.tank("Input", TANK_CAPACITY, FilteredFluidStorage.any())
				.tank("Output", TANK_CAPACITY, FilteredFluidStorage.any(), false)
				.build();
		setTicksPerWorkCycle(0);
		setEnergyPerWorkCycle(0);
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

	@Override
	public boolean hasWork() {
		return false;
	}

	@Override
	protected boolean workCycle() {
		return false;
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
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.tanks.readValue(input.childOrEmpty("Tanks"));
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerDistillery(containerId, playerInventory, this);
	}
}
