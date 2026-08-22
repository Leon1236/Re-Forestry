package com.leon1236.reforestry.farming.farmlogic;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.api.agriculture.HorizontalDirection;
import com.leon1236.reforestry.api.agriculture.ICrop;
import com.leon1236.reforestry.api.agriculture.IExtentCache;
import com.leon1236.reforestry.api.agriculture.IFarmListener;
import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.farming.multiblock.IFarmInventoryInternal;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

public class FarmManager implements IExtentCache {
	private static final FluidVariant WATER = FluidVariant.of(Fluids.WATER);

	private final Map<Direction, List<FarmTarget>> targets = new EnumMap<>(Direction.class);
	private final Map<Direction, Map<BlockPos, Integer>> lastExtents = new EnumMap<>(Direction.class);
	private final IFarmHousingInternal housing;
	@Nullable
	private IFarmLogic harvestProvider;
	private final List<ICrop> pendingCrops = new LinkedList<>();
	private final ArrayDeque<ItemStack> pendingProduce = new ArrayDeque<>();

	private FarmingStage stage = FarmingStage.CULTIVATE;
	private int farmWorkTicks = 0;

	public FarmManager(IFarmHousingInternal housing) {
		this.housing = housing;
		for (Direction direction : HorizontalDirection.VALUES) {
			this.lastExtents.put(direction, new HashMap<>());
		}
	}

	public boolean doWork() {
		this.farmWorkTicks++;
		if (this.targets.isEmpty() || this.farmWorkTicks % 20 == 0) {
			this.housing.setUpFarmlandTargets(this.targets);
		}

		IErrorLogic errorLogic = this.housing.getErrorLogic();
		IFarmInventoryInternal inventory = this.housing.getFarmInventory();

		if (!this.pendingProduce.isEmpty()) {
			boolean added = inventory.tryAddPendingProduce(this.pendingProduce);
			errorLogic.setCondition(!added, ForestryError.NO_SPACE_INVENTORY);
			return added;
		}

		boolean hasFertilizer = this.housing.getFertilizerManager().maintainFertilizer();
		if (errorLogic.setCondition(!hasFertilizer, ForestryError.NO_FERTILIZER)) {
			return false;
		}

		if (!this.pendingCrops.isEmpty() && this.harvestProvider != null) {
			ICrop first = this.pendingCrops.get(0);
			if (cullCrop(first, this.harvestProvider)) {
				this.pendingCrops.remove(0);
				return true;
			}
			return false;
		}

		FarmWorkStatus farmWorkStatus = new FarmWorkStatus();

		Level level = this.housing.getWorldObj();
		if (level == null) {
			return false;
		}
		ObjectArrayList<Direction> farmDirections = new ObjectArrayList<>(HorizontalDirection.VALUES);
		Util.shuffle(farmDirections, level.getRandom());
		for (Direction farmSide : farmDirections) {
			IFarmLogic logic = this.housing.getFarmLogic(farmSide);
			List<FarmTarget> farmTargets = this.targets.get(farmSide);
			if (farmTargets == null) {
				farmTargets = List.of();
			}

			if (this.stage == FarmingStage.CULTIVATE) {
				for (FarmTarget target : farmTargets) {
					if (target.getExtent() > 0) {
						farmWorkStatus.hasFarmland = true;
						break;
					}
				}
			}

			if (FarmHelper.isCycleCanceledByListeners(logic, farmSide, this.housing.getFarmListeners())) {
				continue;
			}

			if (collectWindfall(logic)) {
				farmWorkStatus.didWork = true;
			}

			if (this.stage == FarmingStage.HARVEST) {
				Collection<ICrop> harvested = FarmHelper.harvestTargets(level, this.housing, farmTargets, logic, this.housing.getFarmListeners());
				farmWorkStatus.didWork = !harvested.isEmpty();
				if (!harvested.isEmpty()) {
					this.pendingCrops.addAll(harvested);
					this.pendingCrops.sort(FarmHelper.TOP_DOWN_COMPARATOR);
					this.harvestProvider = logic;
				}
			} else if (this.stage == FarmingStage.CULTIVATE) {
				cultivateTargets(farmWorkStatus, farmTargets, logic, farmSide);
			}

			if (farmWorkStatus.didWork) {
				break;
			}
		}

		if (this.stage == FarmingStage.CULTIVATE) {
			errorLogic.setCondition(!farmWorkStatus.hasFarmland, ForestryError.NO_FARMLAND);
			errorLogic.setCondition(!farmWorkStatus.hasFertilizer, ForestryError.NO_FERTILIZER);
			errorLogic.setCondition(!farmWorkStatus.hasLiquid, ForestryError.NO_LIQUID_FARM);
		}

		this.stage = this.stage.next();

		return farmWorkStatus.didWork;
	}

	private void cultivateTargets(FarmWorkStatus farmWorkStatus, List<FarmTarget> farmTargets, IFarmLogic logic, Direction farmSide) {
		Level level = this.housing.getWorldObj();

		if (farmWorkStatus.hasFarmland && !FarmHelper.isCycleCanceledByListeners(logic, farmSide, this.housing.getFarmListeners())) {
			final float hydrationModifier = this.housing.getHydrationManager().getHydrationModifier();
			final int fertilizerConsumption = Math.round(logic.getType().getFertilizerConsumption(this.housing));
			final int liquidConsumptionMb = logic.getType().getWaterConsumption(this.housing, hydrationModifier);
			final long liquidDroplets = waterDroplets(liquidConsumptionMb);

			for (FarmTarget target : farmTargets) {
				if (!this.housing.getFertilizerManager().hasFertilizer(fertilizerConsumption)) {
					farmWorkStatus.hasFertilizer = false;
					continue;
				}

				if (liquidDroplets > 0 && !this.housing.hasLiquid(WATER, liquidDroplets)) {
					farmWorkStatus.hasLiquid = false;
					continue;
				}

				if (FarmHelper.cultivateTarget(level, this.housing, target, logic, this.housing.getFarmListeners())) {
					this.housing.getFertilizerManager().removeFertilizer(fertilizerConsumption);
					this.housing.removeLiquid(WATER, liquidDroplets);

					farmWorkStatus.didWork = true;
				}
			}
		}
	}

	private boolean collectWindfall(IFarmLogic logic) {
		List<ItemStack> collected = logic.collect(this.housing.getWorldObj(), this.housing);
		if (collected.isEmpty()) {
			return false;
		}

		for (IFarmListener listener : this.housing.getFarmListeners()) {
			listener.hasCollected(collected, logic);
		}

		this.housing.getFarmInventory().stowProducts(collected, this.pendingProduce);
		return true;
	}

	private boolean cullCrop(ICrop crop, IFarmLogic provider) {
		for (IFarmListener listener : this.housing.getFarmListeners()) {
			if (listener.beforeCropHarvest(crop)) {
				return true;
			}
		}

		int fertilizerConsumption = provider.getType().getFertilizerConsumption(this.housing);
		IErrorLogic errorLogic = this.housing.getErrorLogic();

		boolean hasFertilizer = this.housing.getFertilizerManager().hasFertilizer(fertilizerConsumption);
		if (errorLogic.setCondition(!hasFertilizer, ForestryError.NO_FERTILIZER)) {
			return false;
		}

		float hydrationModifier = this.housing.getHydrationManager().getHydrationModifier();
		int waterConsumptionMb = provider.getType().getWaterConsumption(this.housing, hydrationModifier);
		long requiredLiquid = waterDroplets(waterConsumptionMb);
		boolean hasLiquid = requiredLiquid == 0 || this.housing.hasLiquid(WATER, requiredLiquid);

		if (errorLogic.setCondition(!hasLiquid, ForestryError.NO_LIQUID_FARM)) {
			return false;
		}

		List<ItemStack> harvested = crop.harvest();
		if (harvested != null) {
			this.housing.getFertilizerManager().removeFertilizer(fertilizerConsumption);
			this.housing.removeLiquid(WATER, requiredLiquid);

			for (IFarmListener listener : this.housing.getFarmListeners()) {
				listener.afterCropHarvest(harvested, crop);
			}

			this.housing.getFarmInventory().stowProducts(harvested, this.pendingProduce);
		}
		return true;
	}

	private static long waterDroplets(int millibuckets) {
		return millibuckets <= 0 ? 0 : FluidUnits.mbToDroplets(millibuckets);
	}

	public void clearTargets() {
		this.targets.clear();
	}

	public void addPendingProduct(ItemStack stack) {
		this.pendingProduce.add(stack);
	}

	public BlockPos getFarmCorner(Direction direction) {
		List<FarmTarget> targetList = this.targets.get(direction);
		if (targetList == null || targetList.isEmpty()) {
			return this.housing.getCoords();
		}
		FarmTarget target = targetList.get(0);
		return target.getStart().relative(direction.getOpposite());
	}

	@Override
	public int getExtents(Direction direction, BlockPos pos) {
		Integer stored = this.lastExtents.get(direction).get(pos);
		if (stored == null) {
			this.lastExtents.get(direction).put(pos, 0);
			return 0;
		}
		return stored;
	}

	@Override
	public void setExtents(Direction direction, BlockPos pos, int extend) {
		this.lastExtents.get(direction).put(pos, extend);
	}

	@Override
	public void cleanExtents(Direction direction) {
		this.lastExtents.get(direction).clear();
	}
}
