package com.leon1236.reforestry.gendustry.blockentity;

import java.util.Map;

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

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.capability.IIndividualHandlerItem;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.tiles.TilePowered;
import com.leon1236.reforestry.gendustry.errors.GendustryError;
import com.leon1236.reforestry.gendustry.features.GBlockEntities;
import com.leon1236.reforestry.gendustry.features.GItems;
import com.leon1236.reforestry.gendustry.item.GendustryResourceType;
import com.leon1236.reforestry.gendustry.item.GeneticTemplateItem;
import com.leon1236.reforestry.gendustry.item.SpeciesTypeItem;
import com.leon1236.reforestry.gendustry.menu.ThreeInputMenu;

public class ImprinterBlockEntity extends TilePowered implements WorldlyContainer, IGendustryHintTile {
	private static final int ENERGY_PER_WORK_CYCLE = 100000;
	private static final int TICKS_PER_WORK_CYCLE = 80;
	private static final long ENERGY_CAPACITY = 1000000L;
	private static final long ENERGY_MAX_RECEIVE = 10000L;

	public static final String HINTS_KEY = "gendustry.imprinter";
	public static final int ERROR_SLOT_COUNT = ThreeInputMenu.ERROR_SLOT_COUNT;

	public static final int SLOT_INPUT = ThreeInputMenu.SLOT_INPUT;
	public static final int SLOT_TEMPLATE = ThreeInputMenu.SLOT_TEMPLATE_OR_BLANK;
	public static final int SLOT_LABWARE = ThreeInputMenu.SLOT_LABWARE;
	public static final int SLOT_OUTPUT = ThreeInputMenu.SLOT_OUTPUT;
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

	public ImprinterBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.IMPRINTER.type(), pos, state, ENERGY_CAPACITY, ENERGY_MAX_RECEIVE);
		setTicksPerWorkCycle(TICKS_PER_WORK_CYCLE);
		setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, ImprinterBlockEntity tile) {
		tile.doWork(true);
		tile.syncErrors();
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
		ItemStack organismStack = getItem(SLOT_INPUT);
		ItemStack template = getItem(SLOT_TEMPLATE);
		boolean noSpecimen = errors.setCondition(organismStack.isEmpty(), ForestryError.NO_SPECIMEN);
		boolean noTemplate = errors.setCondition(template.isEmpty() || GeneticTemplateItem.getAlleles(template).isEmpty(),
				GendustryError.NO_TEMPLATE);
		boolean noLabware = errors.setCondition(getItem(SLOT_LABWARE).isEmpty(), GendustryError.NO_LABWARE);
		boolean incompatible = false;
		if (!organismStack.isEmpty() && !template.isEmpty() && !GeneticTemplateItem.getAlleles(template).isEmpty()) {
			IIndividual individual = IIndividualHandlerItem.getIndividual(organismStack);
			ISpeciesType<?, ?> templateType = SpeciesTypeItem.getSpeciesType(template);
			incompatible = individual == null || templateType == null || individual.getType() != templateType;
		}
		errors.setCondition(incompatible, GendustryError.INCOMPATIBLE_SPECIES);
		return !noTemplate && !noLabware && !noSpecimen && !incompatible;
	}

	@Override
	protected boolean workCycle() {
		if (!getItem(SLOT_OUTPUT).isEmpty()) {
			return false;
		}

		ItemStack organismStack = getItem(SLOT_INPUT);
		IIndividual individual = IIndividualHandlerItem.getIndividual(organismStack);
		ILifeStage stage = IIndividualHandlerItem.getLifeStage(organismStack);
		if (individual == null || stage == null) {
			return false;
		}

		ItemStack template = getItem(SLOT_TEMPLATE);
		Map<IChromosome<?>, IAllele> alleles = GeneticTemplateItem.getAlleles(template);
		ISpeciesType<?, ?> templateType = SpeciesTypeItem.getSpeciesType(template);
		if (alleles.isEmpty() || templateType == null || individual.getType() != templateType) {
			return false;
		}

		removeItem(SLOT_INPUT, 1);
		removeItem(SLOT_LABWARE, 1);

		IGenome newGenome = individual.getGenome().copyWith(alleles);
		IIndividual newIndividual = individual.copyWithGenome(newGenome);
		if (individual.getMate() != null) {
			newIndividual.setMate(newGenome);
		}
		setItem(SLOT_OUTPUT, newIndividual.createStack(stage));
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
			case SLOT_INPUT -> {
				if (!IIndividualHandlerItem.isIndividual(stack)) {
					yield false;
				}
				ItemStack template = getItem(SLOT_TEMPLATE);
				if (template.isEmpty() || GeneticTemplateItem.getAlleles(template).isEmpty()) {
					yield true;
				}
				IIndividual individual = IIndividualHandlerItem.getIndividual(stack);
				ISpeciesType<?, ?> templateType = SpeciesTypeItem.getSpeciesType(template);
				yield individual != null && templateType != null && individual.getType() == templateType;
			}
			case SLOT_TEMPLATE -> {
				if (!stack.is(GItems.GENETIC_TEMPLATE.item()) || GeneticTemplateItem.getAlleles(stack).isEmpty()) {
					yield false;
				}
				ItemStack organism = getItem(SLOT_INPUT);
				if (organism.isEmpty()) {
					yield true;
				}
				IIndividual individual = IIndividualHandlerItem.getIndividual(organism);
				ISpeciesType<?, ?> templateType = SpeciesTypeItem.getSpeciesType(stack);
				yield individual != null && templateType != null && individual.getType() == templateType;
			}
			case SLOT_LABWARE -> stack.is(GItems.RESOURCE.item(GendustryResourceType.LABWARE));
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
		return ThreeInputMenu.imprinter(containerId, playerInventory, this);
	}
}
