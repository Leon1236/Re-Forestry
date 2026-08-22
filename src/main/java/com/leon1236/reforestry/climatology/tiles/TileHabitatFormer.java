package com.leon1236.reforestry.climatology.tiles;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
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

import team.reborn.energy.api.base.SimpleEnergyStorage;

import com.leon1236.reforestry.api.climate.ClimateType;
import com.leon1236.reforestry.api.climate.IClimateHousing;
import com.leon1236.reforestry.api.climate.IClimateManipulator;
import com.leon1236.reforestry.api.climate.IClimateState;
import com.leon1236.reforestry.api.climate.IClimateTransformer;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.predicates.HygroregulatorInputFluids;
import com.leon1236.reforestry.api.recipes.IHygroregulatorRecipe;
import com.leon1236.reforestry.climatology.features.ClimatologyTiles;
import com.leon1236.reforestry.climatology.gui.ContainerHabitatFormer;
import com.leon1236.reforestry.core.climate.ClimateTransformer;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.recipes.RecipeUtils;
import com.leon1236.reforestry.core.tiles.TilePowered;

public class TileHabitatFormer extends TilePowered implements IClimateHousing, WorldlyContainer {
	public static final int SLOT_INPUT = 0;
	public static final int SLOT_COUNT = 1;
	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(10000);
	private static final String TRANSFORMER_KEY = "Transformer";

	private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
	private final MultiFluidTank tanks;
	private final ClimateTransformer transformer;
	@Nullable
	private FluidVariant cachedFluid = FluidVariant.blank();

	private final ContainerData habitatData = new ContainerData() {
		@Override
		public int get(int index) {
			IClimateState current = transformer.getCurrent();
			IClimateState target = transformer.getTarget();
			IClimateState defaults = transformer.getDefault();
			return switch (index) {
				case 0 -> (int) FluidUnits.dropletsToMb(getResourceTank().getAmount());
				case 1 -> Float.floatToIntBits(current.getTemperature());
				case 2 -> Float.floatToIntBits(current.getHumidity());
				case 3 -> Float.floatToIntBits(target.getTemperature());
				case 4 -> Float.floatToIntBits(target.getHumidity());
				case 5 -> Float.floatToIntBits(defaults.getTemperature());
				case 6 -> Float.floatToIntBits(defaults.getHumidity());
				case 7 -> transformer.getRange();
				case 8 -> transformer.isCircular() ? 1 : 0;
				default -> 0;
			};
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
				case 7 -> transformer.setRange(value);
				case 8 -> transformer.setCircular(value == 1);
				default -> {
				}
			}
		}

		@Override
		public int getCount() {
			return 9;
		}
	};

	public TileHabitatFormer(BlockPos pos, BlockState state) {
		super(ClimatologyTiles.HABITAT_FORMER.type(), pos, state, 10000, 1200);
		this.transformer = new ClimateTransformer(this);
		this.tanks = MultiFluidTank.builder(this::setChanged)
				.tank("Resource", TANK_CAPACITY, HygroregulatorInputFluids::test)
				.build();
		setTicksPerWorkCycle(10);
		setEnergyPerWorkCycle(0);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, TileHabitatFormer tile) {
		tile.transformer.update();
		tile.doWork(true);
		if (tile.updateOnInterval(20)) {
			FluidContainerHelper.drainIntoTank(tile, SLOT_INPUT, tile.getResourceTank());
		}
	}

	public FilteredFluidStorage getResourceTank() {
		return this.tanks.tank("Resource");
	}

	public MultiFluidTank getTankManager() {
		return this.tanks;
	}

	public ContainerData getHabitatData() {
		return this.habitatData;
	}

	@Override
	public IClimateTransformer getTransformer() {
		return this.transformer;
	}

	@Override
	public boolean hasWork() {
		return true;
	}

	@Override
	protected boolean workCycle() {
		IErrorLogic errorLogic = getErrorLogic();
		IClimateState currentState = transformer.getCurrent();
		IClimateState changedState = transformer.getTarget().subtract(currentState);
		IClimateState difference = transformer.getTarget().subtract(transformer.getDefault());
		this.cachedFluid = FluidVariant.blank();
		if (difference.getHumidity() != 0.0F) {
			updateHumidity(errorLogic, changedState);
		}
		if (difference.getTemperature() != 0.0F) {
			updateTemperature(errorLogic, changedState);
		}
		return true;
	}

	private void updateHumidity(IErrorLogic errorLogic, IClimateState changedState) {
		IClimateManipulator manipulator = transformer.createManipulator(ClimateType.HUMIDITY).build();
		if (manipulator.canAdd()) {
			errorLogic.setCondition(false, ForestryError.WRONG_RESOURCE);
			int currentCost = getFluidCost(changedState);
			if (canDrain(currentCost)) {
				IClimateState simulatedState = changedState.toImmutable().add(manipulator.addChange(true));
				int fluidCost = getFluidCost(simulatedState);
				if (canDrain(fluidCost)) {
					this.cachedFluid = drainMb(fluidCost);
					manipulator.addChange(false);
				} else {
					this.cachedFluid = drainMb(currentCost);
				}
				errorLogic.setCondition(false, ForestryError.NO_RESOURCE_LIQUID);
			} else {
				manipulator.removeChange(false);
				errorLogic.setCondition(true, ForestryError.NO_RESOURCE_LIQUID);
			}
		} else if (getResourceTank().getAmount() <= 0) {
			errorLogic.setCondition(true, ForestryError.NO_RESOURCE_LIQUID);
		} else {
			errorLogic.setCondition(true, ForestryError.WRONG_RESOURCE);
			errorLogic.setCondition(false, ForestryError.NO_RESOURCE_LIQUID);
		}
		manipulator.finish();
	}

	private void updateTemperature(IErrorLogic errorLogic, IClimateState changedState) {
		IClimateManipulator manipulator = transformer.createManipulator(ClimateType.TEMPERATURE).setAllowBackwards().build();
		int currentCost = getEnergyCost(changedState);
		if (extractEnergy(currentCost, true) > 0) {
			IClimateState simulatedState = manipulator.addChange(true);
			int energyCost = getEnergyCost(simulatedState);
			if (extractEnergy(energyCost, true) > 0) {
				extractEnergy(energyCost, false);
				manipulator.addChange(false);
			} else {
				extractEnergy(currentCost, false);
			}
			errorLogic.setCondition(false, ForestryError.NO_POWER);
		} else {
			manipulator.removeChange(false);
			errorLogic.setCondition(true, ForestryError.NO_POWER);
		}
		manipulator.finish();
	}

	private long extractEnergy(int amount, boolean simulate) {
		if (amount <= 0) {
			return 0;
		}
		SimpleEnergyStorage energy = getEnergyManager();
		if (simulate) {
			return Math.min(energy.amount, amount);
		}
		try (Transaction transaction = Transaction.openOuter()) {
			long extracted = energy.extract(amount, transaction);
			if (extracted > 0) {
				transaction.commit();
			}
			return extracted;
		}
	}

	private boolean canDrain(int mb) {
		return mb > 0 && getResourceTank().getAmount() >= FluidUnits.mbToDroplets(mb);
	}

	private FluidVariant drainMb(int mb) {
		long droplets = FluidUnits.mbToDroplets(mb);
		FilteredFluidStorage tank = getResourceTank();
		FluidVariant variant = tank.getResource();
		if (variant.isBlank() || droplets <= 0) {
			return FluidVariant.blank();
		}
		try (Transaction transaction = Transaction.openOuter()) {
			long drained = tank.extract(variant, droplets, transaction);
			if (drained > 0) {
				transaction.commit();
				return variant;
			}
		}
		return FluidVariant.blank();
	}

	private int getFluidCost(IClimateState state) {
		FilteredFluidStorage tank = getResourceTank();
		FluidVariant fluid = tank.getResource();
		if (fluid.isBlank() || !(getLevel() instanceof ServerLevel serverLevel)) {
			return 0;
		}
		IHygroregulatorRecipe recipe = RecipeUtils.getHygroregulatorRecipe(serverLevel, fluid, tank.getAmount());
		if (recipe == null) {
			return 0;
		}
		long recipeMb = FluidUnits.dropletsToMb(recipe.getInputFluidAmount());
		return Math.round((1.0F + Mth.abs(state.getHumidity())) * transformer.getCostModifier() * recipeMb);
	}

	private int getEnergyCost(IClimateState state) {
		return Math.round((1.0F + Mth.abs(state.getTemperature())) * transformer.getCostModifier());
	}

	@Override
	public float getChangeForState(ClimateType type, IClimateManipulator manipulator) {
		if (type == ClimateType.HUMIDITY) {
			FilteredFluidStorage tank = getResourceTank();
			FluidVariant fluid = tank.getResource();
			if (!fluid.isBlank() && getLevel() instanceof ServerLevel serverLevel) {
				IHygroregulatorRecipe recipe = RecipeUtils.getHygroregulatorRecipe(serverLevel, fluid, tank.getAmount());
				if (recipe != null) {
					return (recipe.getHumiditySteps() * 0.01F) / transformer.getSpeedModifier();
				}
			}
		}
		float fluidChange = 0.0F;
		if (this.cachedFluid != null && !this.cachedFluid.isBlank() && getLevel() instanceof ServerLevel serverLevel) {
			IHygroregulatorRecipe recipe = RecipeUtils.getHygroregulatorRecipe(serverLevel, this.cachedFluid, Long.MAX_VALUE);
			if (recipe != null) {
				fluidChange = Math.abs(recipe.getTemperatureSteps() * 0.005F);
			}
		}
		return (0.05F + fluidChange) * 0.5F / transformer.getSpeedModifier();
	}

	@Override
	public TemperatureType getTemperature() {
		return TemperatureType.getFromValue(getExactTemperature());
	}

	@Override
	public HumidityType getHumidity() {
		return HumidityType.getFromValue(getExactHumidity());
	}

	@Override
	public TemperatureType temperature() {
		return getTemperature();
	}

	@Override
	public HumidityType humidity() {
		return getHumidity();
	}

	@Override
	public float getExactTemperature() {
		return transformer.getCurrent().getTemperature();
	}

	@Override
	public float getExactHumidity() {
		return transformer.getCurrent().getHumidity();
	}

	@Override
	public BlockPos getCoordinates() {
		return getBlockPos();
	}

	@Override
	@Nullable
	public Level getWorldObj() {
		return getLevel();
	}

	@Override
	public void markNetworkUpdate() {
		setChanged();
	}

	@Override
	public void setRemoved() {
		transformer.removeTransformer();
		super.setRemoved();
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.items);
		this.tanks.writeValue(output.child("Tanks"));
		output.store(TRANSFORMER_KEY, CompoundTag.CODEC, transformer.write(new CompoundTag()));
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		ContainerHelper.loadAllItems(input, this.items);
		this.tanks.readValue(input.childOrEmpty("Tanks"));
		input.read(TRANSFORMER_KEY, CompoundTag.CODEC).ifPresent(transformer::read);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerHabitatFormer(containerId, playerInventory, this);
	}

	@Override
	public int getContainerSize() {
		return SLOT_COUNT;
	}

	@Override
	public boolean isEmpty() {
		return this.items.get(SLOT_INPUT).isEmpty();
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.items.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		ItemStack stack = ContainerHelper.removeItem(this.items, slot, amount);
		if (!stack.isEmpty()) {
			setChanged();
		}
		return stack;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(this.items, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		this.items.set(slot, stack);
		setChanged();
	}

	@Override
	public boolean stillValid(Player player) {
		return isUsableByPlayer(player);
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return FluidContainerHelper.isFilledContainer(stack);
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return new int[]{SLOT_INPUT};
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction side) {
		return canPlaceItem(slot, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
		return true;
	}
}
