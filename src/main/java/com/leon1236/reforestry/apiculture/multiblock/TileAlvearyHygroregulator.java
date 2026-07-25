package com.leon1236.reforestry.apiculture.multiblock;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.climate.IClimateControlled;
import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.api.recipes.IHygroregulatorRecipe;
import com.leon1236.reforestry.apiculture.blocks.BlockAlvearyType;
import com.leon1236.reforestry.apiculture.gui.ContainerAlvearyHygroregulator;
import com.leon1236.reforestry.apiculture.inventory.InventoryAlvearyPart;
import com.leon1236.reforestry.core.recipes.RecipeUtils;

public class TileAlvearyHygroregulator extends TileAlveary
		implements IAlvearyComponent.Climatiser<MultiblockLogicAlveary>, IMultiblockComponent.HasInventory {
	public static final int SLOT_INPUT = 0;
	public static final long TANK_CAPACITY = FluidConstants.BUCKET * 10;

	private final SingleFluidStorage tank = SingleFluidStorage.withFixedCapacity(TANK_CAPACITY, this::setChanged);
	private final InventoryAlvearyPart inventory = new InventoryAlvearyPart(1, this::setChanged,
			(slot, stack) -> isFluidContainer(stack));

	@Nullable
	private IHygroregulatorRecipe currentRecipe;
	private int heatTicks;

	public TileAlvearyHygroregulator(BlockPos pos, BlockState state) {
		super(BlockAlvearyType.HYGRO, pos, state);
	}

	public SingleFluidStorage getTank() {
		return this.tank;
	}

	@Override
	public Container getInternalInventory() {
		return this.inventory;
	}

	@Override
	public void changeClimate(int tickCount, IClimateControlled climateControlled) {
		if (this.heatTicks <= 0) {
			this.currentRecipe = resolveRecipe();
			if (this.currentRecipe != null && drain(this.currentRecipe.getInputFluidAmount())) {
				this.heatTicks = workDuration(this.currentRecipe);
			} else {
				this.currentRecipe = null;
			}
		}

		if (this.heatTicks > 0) {
			if (this.currentRecipe == null) {
				this.heatTicks = 0;
			} else {
				this.heatTicks--;
				climateControlled.addHumidityChange(this.currentRecipe.getHumiditySteps());
				climateControlled.addTemperatureChange(this.currentRecipe.getTemperatureSteps());
			}
		}

		if (tickCount % 20 == 0) {
			drainContainer();
		}
	}

	private static int workDuration(IHygroregulatorRecipe recipe) {
		int retainTime = recipe.getRetainTime();
		return retainTime > 0 ? retainTime : 20;
	}

	@Nullable
	private IHygroregulatorRecipe resolveRecipe() {
		if (this.level instanceof ServerLevel serverLevel) {
			return RecipeUtils.getHygroregulatorRecipe(serverLevel, this.tank.variant, this.tank.amount);
		}
		return null;
	}

	private boolean drain(long amount) {
		if (this.tank.amount < amount) {
			return false;
		}
		try (Transaction transaction = Transaction.openOuter()) {
			if (this.tank.extract(this.tank.variant, amount, transaction) == amount) {
				transaction.commit();
				return true;
			}
		}
		return false;
	}

	private void drainContainer() {
		ItemStack stack = this.inventory.getItem(SLOT_INPUT);
		Fluid fluid = bucketFluid(stack);
		if (fluid == null) {
			return;
		}
		try (Transaction transaction = Transaction.openOuter()) {
			if (this.tank.insert(FluidVariant.of(fluid), FluidConstants.BUCKET, transaction) == FluidConstants.BUCKET) {
				transaction.commit();
				this.inventory.setItem(SLOT_INPUT, new ItemStack(Items.BUCKET));
			}
		}
	}

	@Nullable
	private static Fluid bucketFluid(ItemStack stack) {
		if (stack.is(Items.WATER_BUCKET)) {
			return Fluids.WATER;
		}
		if (stack.is(Items.LAVA_BUCKET)) {
			return Fluids.LAVA;
		}
		return null;
	}

	private static boolean isFluidContainer(ItemStack stack) {
		return bucketFluid(stack) != null;
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.tank.readValue(input.childOrEmpty("Tank"));
		this.heatTicks = input.getIntOr("TransferTime", 0);
		this.currentRecipe = resolveRecipe();

		NonNullList<ItemStack> items = NonNullList.withSize(this.inventory.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(input, items);
		for (int slot = 0; slot < items.size(); slot++) {
			this.inventory.setItem(slot, items.get(slot));
		}
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		this.tank.writeValue(output.child("Tank"));
		output.putInt("TransferTime", this.heatTicks);
		ContainerHelper.saveAllItems(output, this.inventory.getItems());
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerAlvearyHygroregulator(containerId, playerInventory, this);
	}
}
