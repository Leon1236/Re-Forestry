package com.leon1236.reforestry.gendustry.blockentity;

import java.util.Map;

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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.genetics.IBee;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.alleles.IValueAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.gendustry.errors.GendustryError;
import com.leon1236.reforestry.gendustry.features.GBlockEntities;
import com.leon1236.reforestry.gendustry.features.GItems;
import com.leon1236.reforestry.gendustry.fluids.GFluids;
import com.leon1236.reforestry.gendustry.item.GeneticTemplateItem;
import com.leon1236.reforestry.gendustry.item.SpeciesTypeItem;
import com.leon1236.reforestry.gendustry.menu.ReplicatorMenu;

public class ReplicatorBlockEntity extends PoweredTankBlockEntity implements WorldlyContainer, IGendustryHintTile {
	private static final int ENERGY_PER_WORK_CYCLE = 200000;
	private static final int TICKS_PER_WORK_CYCLE = 50;
	private static final long TANK_CAPACITY_MB = 10000;
	private static final long FLUID_PER_CYCLE_MB = 1000;

	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(TANK_CAPACITY_MB);
	public static final long FLUID_PER_CYCLE = FluidUnits.mbToDroplets(FLUID_PER_CYCLE_MB);

	public static final String HINTS_KEY = "gendustry.replicator";
	public static final int ERROR_SLOT_COUNT = 4;

	public static final int SLOT_TEMPLATE = 0;
	public static final int SLOT_DNA_CAN_INPUT = 1;
	public static final int SLOT_PROTEIN_CAN_INPUT = 2;
	public static final int SLOT_OUTPUT = 3;
	public static final int SLOT_COUNT = 4;

	private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
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

	public ReplicatorBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.REPLICATOR.type(), pos, state);
		this.tankManager = MultiFluidTank.builder(this::setChanged)
				.tank("Dna", TANK_CAPACITY, FilteredFluidStorage.only(GFluids.LIQUID_DNA.getFluid()), true)
				.tank("Protein", TANK_CAPACITY, FilteredFluidStorage.only(GFluids.PROTEIN.getFluid()), true)
				.build();
		setTicksPerWorkCycle(TICKS_PER_WORK_CYCLE);
		setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, ReplicatorBlockEntity tile) {
		tile.doWork(true);
		if (tile.updateOnInterval(20)) {
			FluidContainerHelper.drainIntoTank(tile, SLOT_DNA_CAN_INPUT, tile.getDnaTank());
			FluidContainerHelper.drainIntoTank(tile, SLOT_PROTEIN_CAN_INPUT, tile.getProteinTank());
		}
		tile.syncErrors();
	}

	public FilteredFluidStorage getDnaTank() {
		return this.tankManager.tank("Dna");
	}

	public FilteredFluidStorage getProteinTank() {
		return this.tankManager.tank("Protein");
	}

	@Override
	public ContainerData getErrorData() {
		return this.errorData;
	}

	@Override
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
		boolean noDna = errors.setCondition(getDnaTank().getAmount() < FLUID_PER_CYCLE, GendustryError.NO_DNA);
		boolean noProtein = errors.setCondition(getProteinTank().getAmount() < FLUID_PER_CYCLE, GendustryError.NO_PROTEIN);
		boolean noTemplate = errors.setCondition(getItem(SLOT_TEMPLATE).isEmpty(), GendustryError.NO_TEMPLATE);
		return !noDna && !noProtein && !noTemplate;
	}

	@Override
	protected boolean workCycle() {
		if (!getItem(SLOT_OUTPUT).isEmpty()) {
			return false;
		}

		ItemStack template = getItem(SLOT_TEMPLATE);
		ISpeciesType<?, ?> speciesType = SpeciesTypeItem.getSpeciesType(template);
		Map<IChromosome<?>, IAllele> alleles = GeneticTemplateItem.getAlleles(template);
		if (speciesType == null || !GeneticTemplateItem.isComplete(template)) {
			return false;
		}

		IAllele speciesAlleleRaw = alleles.get(speciesType.getKaryotype().speciesChromosome());
		if (!(speciesAlleleRaw instanceof IValueAllele<?> speciesAllele)) {
			return false;
		}
		Object speciesValue = speciesAllele.value();
		if (!(speciesValue instanceof ISpecies<?> species)) {
			return false;
		}

		FilteredFluidStorage dnaTank = getDnaTank();
		FilteredFluidStorage proteinTank = getProteinTank();
		if (dnaTank.getAmount() < FLUID_PER_CYCLE || proteinTank.getAmount() < FLUID_PER_CYCLE) {
			return false;
		}

		try (Transaction transaction = Transaction.openOuter()) {
			long drainedDna = dnaTank.extract(dnaTank.getResource(), FLUID_PER_CYCLE, transaction);
			long drainedProtein = proteinTank.extract(proteinTank.getResource(), FLUID_PER_CYCLE, transaction);
			if (drainedDna != FLUID_PER_CYCLE || drainedProtein != FLUID_PER_CYCLE) {
				return false;
			}
			transaction.commit();
		}

		IIndividual individual = species.createIndividual(alleles);
		if (individual instanceof IBee bee) {
			bee.setPristine(false);
		}
		setItem(SLOT_OUTPUT, individual.createStack(speciesType.getTypeForMutation(2)));
		return true;
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
			case SLOT_TEMPLATE -> stack.is(GItems.GENETIC_TEMPLATE.item()) && GeneticTemplateItem.isComplete(stack);
			case SLOT_DNA_CAN_INPUT -> {
				Fluid fluid = FluidContainerHelper.fluidIn(stack).getFluid();
				yield GFluids.LIQUID_DNA.is(fluid);
			}
			case SLOT_PROTEIN_CAN_INPUT -> {
				Fluid fluid = FluidContainerHelper.fluidIn(stack).getFluid();
				yield GFluids.PROTEIN.is(fluid);
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
		return WorldlyAccessHelper.canTakeItemThroughFace(this, slot == SLOT_OUTPUT, direction);
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
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ReplicatorMenu(containerId, playerInventory, this);
	}
}
