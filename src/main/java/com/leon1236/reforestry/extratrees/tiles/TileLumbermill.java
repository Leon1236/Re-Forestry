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
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.tiles.TilePowered;
import com.leon1236.reforestry.extratrees.features.ExtraTreesItems;
import com.leon1236.reforestry.extratrees.features.ExtraTreesTiles;
import com.leon1236.reforestry.extratrees.gui.ContainerLumbermill;
import com.leon1236.reforestry.extratrees.recipes.LumbermillRecipeHelper;

public class TileLumbermill extends TilePowered implements WorldlyContainer {
	public static final int SLOT_LOG = 0;
	public static final int SLOT_PLANKS = 1;
	public static final int SLOT_BARK = 2;
	public static final int SLOT_SAWDUST = 3;
	public static final int SLOT_COUNT = 4;
	public static final int ERROR_SLOT_COUNT = 4;

	private static final long ENERGY_CAPACITY = 10000;
	private static final long ENERGY_MAX_RECEIVE = 200;
	private static final int PROCESS_ENERGY = 900;
	private static final int PROCESS_TIME = 30;
	private static final long TANK_CAPACITY_MB = 10000;
	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(TANK_CAPACITY_MB);
	private static final long WATER_PER_CYCLE = FluidUnits.mbToDroplets(300);

	private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
	private final MultiFluidTank tanks;
	private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
	private int syncedErrorCount;

	private final ContainerData errorData = new ContainerData() {
		@Override
		public int get(int index) {
			if (index == 0) {
				return syncedErrorCount;
			}
			int errorIndex = index - 1;
			return errorIndex >= 0 && errorIndex < syncedErrorIds.length ? syncedErrorIds[errorIndex] : -1;
		}

		@Override
		public void set(int index, int value) {
			if (index == 0) {
				syncedErrorCount = value;
				return;
			}
			int errorIndex = index - 1;
			if (errorIndex >= 0 && errorIndex < syncedErrorIds.length) {
				syncedErrorIds[errorIndex] = value;
			}
		}

		@Override
		public int getCount() {
			return ERROR_SLOT_COUNT + 1;
		}
	};

	public TileLumbermill(BlockPos pos, BlockState state) {
		super(ExtraTreesTiles.LUMBERMILL.type(), pos, state, ENERGY_CAPACITY, ENERGY_MAX_RECEIVE);
		this.tanks = MultiFluidTank.builder(this::setChanged)
				.tank("Water", TANK_CAPACITY, FilteredFluidStorage.only(Fluids.WATER))
				.build();
		setTicksPerWorkCycle(PROCESS_TIME);
		setEnergyPerWorkCycle(PROCESS_ENERGY);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, TileLumbermill tile) {
		tile.doWork(true);
		tile.syncErrors();
	}

	public MultiFluidTank getTankManager() {
		return this.tanks;
	}

	public FilteredFluidStorage getWaterTank() {
		return this.tanks.tank("Water");
	}

	public ContainerData getErrorData() {
		return errorData;
	}

	private void syncErrors() {
		syncedErrorCount = 0;
		for (var error : getErrorLogic().getErrors()) {
			if (syncedErrorCount >= ERROR_SLOT_COUNT) {
				break;
			}
			short id = IForestryApi.get().getErrorManager().getNumericId(error);
			syncedErrorIds[syncedErrorCount++] = id;
		}
		for (int i = syncedErrorCount; i < ERROR_SLOT_COUNT; i++) {
			syncedErrorIds[i] = -1;
		}
	}

	@Override
	public boolean hasWork() {
		IErrorLogic errorLogic = getErrorLogic();
		ItemStack log = getItem(SLOT_LOG);
		boolean hasLog = LumbermillRecipeHelper.isLog(log);
		errorLogic.setCondition(!hasLog, ForestryError.NO_RESOURCE);
		if (!hasLog) {
			return false;
		}

		ItemStack planks = LumbermillRecipeHelper.getPlankProduct(getLevel(), log);
		boolean hasRecipe = !planks.isEmpty();
		errorLogic.setCondition(!hasRecipe, ForestryError.NO_RECIPE);
		if (!hasRecipe) {
			return false;
		}

		boolean hasWater = getWaterTank().getAmount() >= WATER_PER_CYCLE;
		errorLogic.setCondition(!hasWater, ForestryError.NO_RESOURCE_LIQUID);

		ItemStack bark = new ItemStack(ExtraTreesItems.BARK.item(), 2);
		ItemStack sawdust = new ItemStack(ExtraTreesItems.SAWDUST.item(), 2);
		boolean canOutput = canFit(SLOT_PLANKS, planks) && canFit(SLOT_BARK, bark) && canFit(SLOT_SAWDUST, sawdust);
		errorLogic.setCondition(!canOutput, ForestryError.NO_SPACE_INVENTORY);

		return hasWater && canOutput;
	}

	private boolean canFit(int slot, ItemStack stack) {
		ItemStack existing = getItem(slot);
		if (existing.isEmpty()) {
			return true;
		}
		if (!ItemStack.isSameItemSameComponents(existing, stack)) {
			return false;
		}
		return existing.getCount() + stack.getCount() <= existing.getMaxStackSize();
	}

	@Override
	protected boolean workCycle() {
		ItemStack log = getItem(SLOT_LOG);
		ItemStack planks = LumbermillRecipeHelper.getPlankProduct(getLevel(), log);
		if (log.isEmpty() || planks.isEmpty()) {
			return false;
		}

		FilteredFluidStorage water = getWaterTank();
		try (Transaction transaction = Transaction.openOuter()) {
			long drained = water.extract(FluidVariant.of(Fluids.WATER), WATER_PER_CYCLE, transaction);
			if (drained != WATER_PER_CYCLE) {
				return false;
			}
			transaction.commit();
		}

		removeItem(SLOT_LOG, 1);
		InventoryUtil.tryAddStack(this, planks, SLOT_PLANKS, 1, false);
		InventoryUtil.tryAddStack(this, new ItemStack(ExtraTreesItems.BARK.item(), 2), SLOT_BARK, 1, false);
		InventoryUtil.tryAddStack(this, new ItemStack(ExtraTreesItems.SAWDUST.item(), 2), SLOT_SAWDUST, 1, false);
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
		return slot == SLOT_LOG && LumbermillRecipeHelper.isLog(stack);
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
		return WorldlyAccessHelper.canTakeItemThroughFace(this, slot != SLOT_LOG, direction);
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
		return new ContainerLumbermill(containerId, playerInventory, this);
	}
}
