package com.leon1236.reforestry.gendustry.blockentity;

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
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.gendustry.errors.GendustryError;
import com.leon1236.reforestry.gendustry.features.GItems;
import com.leon1236.reforestry.gendustry.fluids.GFluids;
import com.leon1236.reforestry.gendustry.item.GendustryResourceType;
import com.leon1236.reforestry.gendustry.menu.ProducerMenu;
import com.leon1236.reforestry.gendustry.recipe.ProcessorRecipe;

public abstract class ProducerBlockEntity<R extends ProcessorRecipe> extends PoweredTankBlockEntity implements WorldlyContainer {
	public static final int SLOT_INPUT = 0;
	public static final int SLOT_CAN_INPUT = 1;
	public static final int SLOT_CAN_OUTPUT = 2;
	public static final int SLOT_LABWARE = 3;

	private static final float CONSUME_LABWARE_CHANCE = 0.1f;
	private static final long TANK_CAPACITY_MB = 10000;
	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(TANK_CAPACITY_MB);
	public static final int ERROR_SLOT_COUNT = 4;

	public final boolean usesLabware;
	private final GFluids resultFluid;
	private final int slotCount;
	private final NonNullList<ItemStack> items;
	private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
	private int syncedErrorCount;

	@Nullable
	protected ProcessorRecipe currentRecipe;

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

	protected ProducerBlockEntity(BlockEntityType<?> type, GFluids result, boolean usesLabware, BlockPos pos, BlockState state) {
		super(type, pos, state);
		this.usesLabware = usesLabware;
		this.resultFluid = result;
		this.slotCount = usesLabware ? 4 : 3;
		this.items = NonNullList.withSize(this.slotCount, ItemStack.EMPTY);
		this.tankManager = MultiFluidTank.builder(this::setChanged)
				.tank("Product", TANK_CAPACITY, FilteredFluidStorage.only(result.getFluid()), true)
				.build();
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, ProducerBlockEntity<?> tile) {
		tile.doWork(true);
		if (tile.updateOnInterval(20)) {
			FilteredFluidStorage productTank = tile.getProductTank();
			if (productTank.getAmount() > 0) {
				FluidContainerHelper.fillContainers(
						productTank,
						tile,
						SLOT_CAN_INPUT,
						SLOT_CAN_OUTPUT,
						productTank.getResource(),
						true);
			}
		}
		tile.syncErrors();
	}

	public FilteredFluidStorage getProductTank() {
		return this.tankManager.tank("Product");
	}

	public ContainerData getErrorData() {
		return this.errorData;
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
		R matchingRecipe = getRecipe(getItem(SLOT_INPUT));
		if (this.currentRecipe != matchingRecipe) {
			this.currentRecipe = matchingRecipe;
			if (this.currentRecipe != null) {
				startWorking();
			}
		}

		IErrorLogic errorLogic = getErrorLogic();
		boolean hasInput = !errorLogic.setCondition(this.currentRecipe == null, getNoInputError());

		if (this.usesLabware) {
			ItemStack labware = getItem(SLOT_LABWARE);
			boolean hasLabware = !errorLogic.setCondition(labware.isEmpty(), GendustryError.NO_LABWARE);
			return hasInput && hasLabware;
		}

		return hasInput;
	}

	@Override
	protected boolean workCycle() {
		if (this.currentRecipe == null) {
			return false;
		}

		long resultAmount = FluidUnits.mbToDroplets(this.currentRecipe.getAmount());
		FluidVariant resultVariant = FluidVariant.of(this.resultFluid.getFluid());
		FilteredFluidStorage productTank = getProductTank();

		try (Transaction transaction = Transaction.openOuter()) {
			if (productTank.insert(resultVariant, resultAmount, transaction) != resultAmount) {
				return false;
			}
		}

		ItemStack input = getItem(SLOT_INPUT);
		if (!this.currentRecipe.isIngredient(input)) {
			return false;
		}

		if (this.usesLabware) {
			ItemStack labware = getItem(SLOT_LABWARE);
			if (!labware.is(GItems.RESOURCE.item(GendustryResourceType.LABWARE))) {
				return false;
			}
			if (this.level != null && this.level.getRandom().nextFloat() < CONSUME_LABWARE_CHANCE) {
				removeItem(SLOT_LABWARE, 1);
			}
		}

		removeItem(SLOT_INPUT, 1);

		try (Transaction transaction = Transaction.openOuter()) {
			if (productTank.insert(resultVariant, resultAmount, transaction) != resultAmount) {
				return false;
			}
			transaction.commit();
		}
		return true;
	}

	public abstract boolean isValidInput(ItemStack input);

	@Nullable
	public abstract R getRecipe(ItemStack input);

	public abstract void startWorking();

	public abstract IError getNoInputError();

	public abstract String getHintsKey();

	@Override
	public int getContainerSize() {
		return this.slotCount;
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
		return switch (slot) {
			case SLOT_INPUT -> isValidInput(stack);
			case SLOT_LABWARE -> this.usesLabware && stack.is(GItems.RESOURCE.item(GendustryResourceType.LABWARE));
			case SLOT_CAN_INPUT -> FluidContainerHelper.isEmptyContainer(stack);
			default -> false;
		};
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
		return WorldlyAccessHelper.getSlotsForFace(this, this.slotCount, direction);
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return WorldlyAccessHelper.canPlaceItemThroughFace(this, canPlaceItem(slot, stack), direction);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return WorldlyAccessHelper.canTakeItemThroughFace(this, slot == SLOT_CAN_OUTPUT, direction);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.items);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.items.clear();
		ContainerHelper.loadAllItems(input, this.items);
		this.currentRecipe = null;
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ProducerMenu(containerId, playerInventory, this);
	}
}
