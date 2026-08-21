package com.leon1236.reforestry.gendustry.blockentity;

import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

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
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.capability.IIndividualHandlerItem;
import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;
import com.leon1236.reforestry.gendustry.errors.GendustryError;
import com.leon1236.reforestry.gendustry.features.GItems;
import com.leon1236.reforestry.gendustry.fluids.GFluids;
import com.leon1236.reforestry.gendustry.item.GendustryResourceType;

public abstract class AbstractMutatronBlockEntity extends PoweredTankBlockEntity implements WorldlyContainer {
	private static final int ENERGY_PER_WORK_CYCLE = 100000;
	private static final int TICKS_PER_WORK_CYCLE = 40;
	private static final long TANK_CAPACITY_MB = 10000;
	private static final long MUTAGEN_PER_CYCLE_MB = 1000;

	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(TANK_CAPACITY_MB);
	public static final long MUTAGEN_PER_CYCLE = FluidUnits.mbToDroplets(MUTAGEN_PER_CYCLE_MB);

	public static final String HINTS_KEY = "gendustry.mutatron";
	public static final int ERROR_SLOT_COUNT = 4;

	public static final int SLOT_PRIMARY = 0;
	public static final int SLOT_SECONDARY = 1;
	public static final int SLOT_LABWARE = 2;
	public static final int SLOT_CAN_INPUT = 3;
	public static final int SLOT_RESULT = 4;
	public static final int SLOT_COUNT = 5;

	private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
	private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
	private int syncedErrorCount;

	@Nullable
	private IMutation currentMutation;
	protected ItemStack currentPrimary = ItemStack.EMPTY;
	protected ItemStack currentSecondary = ItemStack.EMPTY;
	@Nullable
	private UUID lastPlayer;

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

	protected AbstractMutatronBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		this.tankManager = MultiFluidTank.builder(this::setChanged)
				.tank("Mutagen", TANK_CAPACITY, FilteredFluidStorage.only(GFluids.MUTAGEN.getFluid()), true)
				.build();
		setTicksPerWorkCycle(TICKS_PER_WORK_CYCLE);
		setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, AbstractMutatronBlockEntity tile) {
		tile.doWork(true);
		if (tile.updateOnInterval(20)) {
			FluidContainerHelper.drainIntoTank(tile, SLOT_CAN_INPUT, tile.getMutagenTank());
		}
		tile.syncErrors();
	}

	public FilteredFluidStorage getMutagenTank() {
		return this.tankManager.tank("Mutagen");
	}

	public ContainerData getErrorData() {
		return this.errorData;
	}

	public String getHintsKey() {
		return HINTS_KEY;
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
		IErrorLogic errors = getErrorLogic();

		boolean noMutagen = getMutagenTank().getAmount() < MUTAGEN_PER_CYCLE;
		errors.setCondition(noMutagen, GendustryError.NO_MUTAGEN);

		boolean noLabware = getItem(SLOT_LABWARE).isEmpty();
		errors.setCondition(noLabware, GendustryError.NO_LABWARE);

		if (hasMutation()) {
			if (getItem(SLOT_PRIMARY) != this.currentPrimary
					|| getItem(SLOT_SECONDARY) != this.currentSecondary
					|| this.currentPrimary.isEmpty()
					|| this.currentSecondary.isEmpty()) {
				onMutationsUpdated(List.of(), ItemStack.EMPTY, ItemStack.EMPTY);
			}
		}

		if (!hasMutation()) {
			ItemStack primaryStack = getItem(SLOT_PRIMARY);
			ItemStack secondaryStack = getItem(SLOT_SECONDARY);
			IIndividual primary = IIndividualHandlerItem.getIndividual(primaryStack);
			IIndividual secondary = IIndividualHandlerItem.getIndividual(secondaryStack);

			boolean noMates = primary == null || secondary == null;
			errors.setCondition(noMates, GendustryError.NO_MATES);
			if (noMates) {
				return false;
			}

			boolean incompatible = primary.getType() != secondary.getType();
			errors.setCondition(incompatible, GendustryError.INCOMPATIBLE_SPECIES);
			if (incompatible) {
				return false;
			}

			List<IMutation> mutations = IForestryApi.get().getGeneticManager()
					.getMutations(primary.getType())
					.getCombinations(primary.getSpecies().id(), secondary.getSpecies().id());
			boolean noMutations = mutations.isEmpty();
			errors.setCondition(noMutations, GendustryError.NO_MUTATIONS);
			if (noMutations) {
				return false;
			}

			onMutationsUpdated(mutations, primaryStack, secondaryStack);
		}

		return !noMutagen && !noLabware;
	}

	protected boolean hasMutation() {
		return this.currentMutation != null;
	}

	protected abstract void onMutationsUpdated(List<IMutation> mutations, ItemStack primaryStack, ItemStack secondaryStack);

	@Override
	protected boolean workCycle() {
		if (!getItem(SLOT_RESULT).isEmpty() || this.currentMutation == null) {
			return false;
		}
		if (getItem(SLOT_PRIMARY).isEmpty() || getItem(SLOT_SECONDARY).isEmpty() || getItem(SLOT_LABWARE).isEmpty()) {
			return false;
		}

		FilteredFluidStorage mutagenTank = getMutagenTank();
		if (mutagenTank.getAmount() < MUTAGEN_PER_CYCLE) {
			return false;
		}

		ItemStack primary = removeItem(SLOT_PRIMARY, 1);
		removeItem(SLOT_SECONDARY, 1);
		removeItem(SLOT_LABWARE, 1);

		try (Transaction transaction = Transaction.openOuter()) {
			long drained = mutagenTank.extract(mutagenTank.getResource(), MUTAGEN_PER_CYCLE, transaction);
			if (drained != MUTAGEN_PER_CYCLE) {
				return false;
			}
			transaction.commit();
		}

		IIndividualHandlerItem.ifPresent(primary, individual -> {
			ISpeciesType<?, ?> speciesType = individual.getType();
			IGenome genome = createMutatedGenome(this.currentMutation, speciesType);
			IIndividual newIndividual = individual.copyWithGenome(genome);
			newIndividual.setMate(genome);
			ItemStack result = newIndividual.createStack(speciesType.getTypeForMutation(2));
			setItem(SLOT_RESULT, result);

			if (this.lastPlayer != null && this.level != null) {
				Player player = this.level.getPlayerByUUID(this.lastPlayer);
				if (player == null) {
					this.lastPlayer = null;
				} else {
					IBreedingTracker tracker = speciesType.getBreedingTracker(this.level, player.getGameProfile());
					tracker.registerMutation(this.currentMutation);
				}
			}
		});

		setCurrentMutation(null, ItemStack.EMPTY, ItemStack.EMPTY);
		return true;
	}

	public static IGenome createMutatedGenome(IMutation mutation, ISpeciesType<?, ?> speciesType) {
		ISpecies<?> resultSpecies = speciesType.getSpecies(mutation.result());
		IGenome template = resultSpecies.getDefaultGenome();
		if (mutation instanceof Mutation concrete && !concrete.specialAlleles().isEmpty()) {
			return template.copyWith(concrete.specialAlleles());
		}
		return template;
	}

	public static ItemStack createMutationIcon(IMutation mutation, ISpeciesType<?, ?> speciesType) {
		ISpecies<?> resultSpecies = speciesType.getSpecies(mutation.result());
		IGenome genome = createMutatedGenome(mutation, speciesType);
		IIndividual individual = resultSpecies.createIndividual(genome);
		return individual.createStack(speciesType.getTypeForMutation(2));
	}

	public void setCurrentMutation(@Nullable IMutation mutation, ItemStack primary, ItemStack secondary) {
		this.currentMutation = mutation;
		this.currentPrimary = primary;
		this.currentSecondary = secondary;
	}

	@Nullable
	public IMutation getCurrentMutation() {
		return this.currentMutation;
	}

	public void trackPlayer(Player player) {
		this.lastPlayer = player.getUUID();
	}

	@Override
	public int getContainerSize() {
		return SLOT_COUNT;
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
			case SLOT_PRIMARY -> IIndividualHandlerItem.filter(stack,
					(individual, stage) -> stage == individual.getType().getTypeForMutation(0));
			case SLOT_SECONDARY -> IIndividualHandlerItem.filter(stack,
					(individual, stage) -> stage == individual.getType().getTypeForMutation(1));
			case SLOT_LABWARE -> stack.is(GItems.RESOURCE.item(GendustryResourceType.LABWARE));
			case SLOT_CAN_INPUT -> {
				Fluid fluid = FluidContainerHelper.fluidIn(stack).getFluid();
				yield GFluids.MUTAGEN.is(fluid);
			}
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
		return WorldlyAccessHelper.getSlotsForFace(this, SLOT_COUNT, direction);
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return WorldlyAccessHelper.canPlaceItemThroughFace(this, canPlaceItem(slot, stack), direction);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return WorldlyAccessHelper.canTakeItemThroughFace(this, slot == SLOT_RESULT, direction);
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
		this.currentMutation = null;
		this.currentPrimary = ItemStack.EMPTY;
		this.currentSecondary = ItemStack.EMPTY;
	}

	@Nullable
	@Override
	public abstract AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player);
}
