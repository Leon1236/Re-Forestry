package com.leon1236.reforestry.core.tiles;

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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.features.CoreDataComponents;
import com.leon1236.reforestry.core.features.CoreTiles;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.ForestryFluids;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.gui.ContainerAnalyzer;
import com.leon1236.reforestry.core.inventory.InventoryUtil;

public class TileAnalyzer extends TilePowered implements WorldlyContainer {
	public static final int SLOT_ANALYZE = 0;
	public static final int SLOT_CAN = 1;
	public static final int SLOT_INPUT_1 = 2;
	public static final int SLOT_INPUT_COUNT = 6;
	public static final int SLOT_OUTPUT_1 = 8;
	public static final int SLOT_OUTPUT_COUNT = 4;
	public static final int SLOT_COUNT = SLOT_OUTPUT_1 + SLOT_OUTPUT_COUNT;
	public static final int ERROR_SLOT_COUNT = 4;

	private static final int TIME_TO_ANALYZE = 125;
	private static final int HONEY_REQUIRED_MB = 100;
	private static final long HONEY_REQUIRED = FluidUnits.mbToDroplets(HONEY_REQUIRED_MB);
	private static final int ANALYZER_ENERGY_PER_WORK = 20320;
	private static final long CAPACITY = 40000;
	private static final long MAX_RECEIVE = 800;
	private static final long TANK_CAPACITY_MB = 10000;
	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(TANK_CAPACITY_MB);

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

	public TileAnalyzer(BlockPos pos, BlockState state) {
		super(CoreTiles.ANALYZER.type(), pos, state, CAPACITY, MAX_RECEIVE);
		this.tanks = MultiFluidTank.builder(this::setChanged)
				.tank("Resource", TANK_CAPACITY, FilteredFluidStorage.only(
						ForestryFluids.HONEY.getFluid(), ForestryFluids.HONEY.getFlowing()))
				.build();
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, TileAnalyzer tile) {
		tile.advanceTicks();
		if (tile.updateOnInterval(20)) {
			FluidContainerHelper.drainIntoTank(tile, SLOT_CAN, tile.getResourceTank());
		}
		tile.doWork(false);
		tile.syncErrors();
	}

	public MultiFluidTank getTankManager() {
		return this.tanks;
	}

	public FilteredFluidStorage getResourceTank() {
		return this.tanks.tank("Resource");
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
			short id = IForestryApi.INSTANCE.getErrorManager().getNumericId(error);
			syncedErrorIds[syncedErrorCount++] = id;
		}
		for (int i = syncedErrorCount; i < ERROR_SLOT_COUNT; i++) {
			syncedErrorIds[i] = -1;
		}
	}

	@Override
	protected boolean workCycle() {
		ItemStack stackToAnalyze = getItem(SLOT_ANALYZE);
		if (stackToAnalyze.isEmpty() || !IndividualItems.isIndividual(stackToAnalyze)) {
			return false;
		}

		if (!IndividualItems.isAnalyzed(stackToAnalyze)) {
			if (!consumeHoney()) {
				return false;
			}
			stackToAnalyze.set(CoreDataComponents.ANALYZED.type(), true);
		}

		boolean added = InventoryUtil.tryAddStack(this, stackToAnalyze, SLOT_OUTPUT_1, SLOT_OUTPUT_COUNT, true);
		if (!added) {
			return false;
		}

		setItem(SLOT_ANALYZE, ItemStack.EMPTY);
		return true;
	}

	@Override
	public boolean hasWork() {
		moveSpecimenToAnalyzeSlot();

		ItemStack specimen = getItem(SLOT_ANALYZE);
		boolean hasSpecimen = !specimen.isEmpty();
		boolean hasResource = true;
		boolean hasSpace = true;

		if (hasSpecimen) {
			hasSpace = InventoryUtil.tryAddStack(this, specimen, SLOT_OUTPUT_1, SLOT_OUTPUT_COUNT, true, false);
			if (IndividualItems.isIndividual(specimen) && !IndividualItems.isAnalyzed(specimen)) {
				hasResource = hasHoney();
			}
		}

		IErrorLogic errorLogic = getErrorLogic();
		errorLogic.setCondition(!hasSpecimen, ForestryError.NO_SPECIMEN);
		errorLogic.setCondition(!hasResource, ForestryError.NO_RESOURCE_LIQUID);
		errorLogic.setCondition(!hasSpace, ForestryError.NO_SPACE_INVENTORY);

		return hasSpecimen && hasResource && hasSpace;
	}

	private void moveSpecimenToAnalyzeSlot() {
		if (!getItem(SLOT_ANALYZE).isEmpty()) {
			return;
		}

		Integer slotIndex = getInputSlotIndex();
		if (slotIndex == null) {
			return;
		}

		ItemStack inputStack = getItem(slotIndex);
		if (inputStack.isEmpty() || !IndividualItems.isIndividual(inputStack)) {
			return;
		}

		setItem(SLOT_ANALYZE, inputStack);
		setItem(slotIndex, ItemStack.EMPTY);

		if (IndividualItems.isAnalyzed(inputStack)) {
			setTicksPerWorkCycle(1);
			setEnergyPerWorkCycle(0);
		} else {
			setTicksPerWorkCycle(TIME_TO_ANALYZE);
			setEnergyPerWorkCycle(ANALYZER_ENERGY_PER_WORK);
		}
	}

	@Nullable
	private Integer getInputSlotIndex() {
		for (int slotIndex = 0; slotIndex < SLOT_INPUT_COUNT; slotIndex++) {
			ItemStack stack = getItem(SLOT_INPUT_1 + slotIndex);
			if (IndividualItems.isIndividual(stack)) {
				return SLOT_INPUT_1 + slotIndex;
			}
		}
		return null;
	}

	private boolean hasHoney() {
		FilteredFluidStorage tank = getResourceTank();
		FluidVariant resource = tank.getResource();
		if (resource.isBlank() || !ForestryFluids.HONEY.is(resource.getFluid())) {
			return false;
		}
		try (Transaction transaction = Transaction.openOuter()) {
			return tank.extract(resource, HONEY_REQUIRED, transaction) == HONEY_REQUIRED;
		}
	}

	private boolean consumeHoney() {
		FilteredFluidStorage tank = getResourceTank();
		FluidVariant resource = tank.getResource();
		if (resource.isBlank() || !ForestryFluids.HONEY.is(resource.getFluid())) {
			return false;
		}
		try (Transaction transaction = Transaction.openOuter()) {
			long extracted = tank.extract(resource, HONEY_REQUIRED, transaction);
			if (extracted != HONEY_REQUIRED) {
				return false;
			}
			transaction.commit();
			return true;
		}
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
		if (slot >= SLOT_INPUT_1 && slot < SLOT_INPUT_1 + SLOT_INPUT_COUNT) {
			return IndividualItems.isIndividual(stack);
		}
		if (slot == SLOT_CAN) {
			return FluidContainerHelper.isFilledContainer(stack)
					&& FluidContainerHelper.canTankAccept(getResourceTank(), FluidContainerHelper.fluidIn(stack));
		}
		return false;
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
		return WorldlyAccessHelper.canTakeItemThroughFace(this,
				slot >= SLOT_OUTPUT_1 && slot < SLOT_OUTPUT_1 + SLOT_OUTPUT_COUNT, direction);
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
		return new ContainerAnalyzer(containerId, playerInventory, this);
	}
}
