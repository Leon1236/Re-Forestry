package com.leon1236.reforestry.core.climate;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.climate.ClimateType;
import com.leon1236.reforestry.api.climate.IClimateHousing;
import com.leon1236.reforestry.api.climate.IClimateManipulatorBuilder;
import com.leon1236.reforestry.api.climate.IClimateState;
import com.leon1236.reforestry.api.climate.IClimateTransformer;
import com.leon1236.reforestry.api.climate.IWorldClimateHolder;
import com.leon1236.reforestry.core.config.ForestryConfig;

public class ClimateTransformer implements IClimateTransformer {

	public static final int DEFAULT_RANGE = 10;
	public static final float AREA_COST_MODIFIER = 0.5F;
	public static final float AREA_SPEED_MODIFIER = 0.5F;

	private static final String CURRENT_STATE_KEY = "Current";
	private static final String TARGETED_STATE_KEY = "Target";
	private static final String CIRCULAR_KEY = "Circular";
	private static final String RANGE_KEY = "Range";

	protected final IClimateHousing housing;
	private IClimateState targetedState;
	private IClimateState currentState;
	private IClimateState defaultState;
	private int range;
	private int area;
	private boolean addedToWorld;
	private boolean circular;

	public ClimateTransformer(IClimateHousing housing) {
		this.housing = housing;
		this.currentState = ClimateStateHelper.INSTANCE.absent();
		this.defaultState = AbsentClimateState.INSTANCE;
		this.targetedState = AbsentClimateState.INSTANCE;
		setRange(ForestryConfig.habitatFormerRange());
		this.circular = true;
		this.addedToWorld = false;
	}

	@Override
	public IClimateHousing getHousing() {
		return housing;
	}

	@Override
	public void update() {
		if (!addedToWorld) {
			Level world = housing.getWorldObj();
			BlockPos pos = housing.getCoordinates();
			if (world != null) {
				defaultState = IForestryApi.get().getClimateManager().getExactBiomeState(world, pos);
			}
			if (!targetedState.isPresent()) {
				setCurrent(defaultState.copy());
				setTarget(defaultState);
			}
			if (world instanceof ServerLevel serverLevel) {
				IWorldClimateHolder worldClimate = IForestryApi.get().getClimateManager().getWorldClimate(serverLevel);
				worldClimate.updateTransformer(this);
			}
			addedToWorld = true;
		}
	}

	@Override
	public void removeTransformer() {
		addedToWorld = false;
		Level world = housing.getWorldObj();
		if (world instanceof ServerLevel serverLevel) {
			IWorldClimateHolder worldClimate = IForestryApi.get().getClimateManager().getWorldClimate(serverLevel);
			worldClimate.removeTransformer(this);
		}
	}

	public CompoundTag write(CompoundTag nbt) {
		nbt.put(CURRENT_STATE_KEY, ClimateStateHelper.INSTANCE.writeToNBT(new CompoundTag(), currentState));
		nbt.put(TARGETED_STATE_KEY, ClimateStateHelper.INSTANCE.writeToNBT(new CompoundTag(), targetedState));
		nbt.putBoolean(CIRCULAR_KEY, circular);
		nbt.putInt(RANGE_KEY, range);
		return nbt;
	}

	public void read(CompoundTag nbt) {
		currentState = ClimateStateHelper.INSTANCE.create(nbt.getCompoundOrEmpty(CURRENT_STATE_KEY));
		targetedState = ClimateStateHelper.INSTANCE.create(nbt.getCompoundOrEmpty(TARGETED_STATE_KEY));
		circular = nbt.getBooleanOr(CIRCULAR_KEY, true);
		range = nbt.getIntOr(RANGE_KEY, DEFAULT_RANGE);
		onAreaChange(range, circular);
	}

	@Override
	public IClimateManipulatorBuilder createManipulator(ClimateType type) {
		return new ClimateManipulator.Builder()
				.setDefault(defaultState)
				.setCurrent(currentState)
				.setTarget(targetedState)
				.setChangeSupplier(housing::getChangeForState)
				.setType(type)
				.setOnFinish(this::setCurrent);
	}

	@Override
	public IClimateState getCurrent() {
		return currentState;
	}

	@Override
	public void setCurrent(IClimateState state) {
		state = ClimateStateHelper.INSTANCE.clamp(state.toImmutable());
		if (!state.equals(currentState)) {
			this.currentState = state;
			housing.markNetworkUpdate();
			if (addedToWorld) {
				Level world = getWorldObj();
				if (world instanceof ServerLevel serverLevel) {
					IWorldClimateHolder worldClimate = IForestryApi.get().getClimateManager().getWorldClimate(serverLevel);
					worldClimate.updateTransformer(this);
				}
			}
		}
	}

	@Override
	public IClimateState getTarget() {
		return targetedState;
	}

	@Override
	public void setTarget(IClimateState target) {
		this.targetedState = ClimateStateHelper.INSTANCE.clamp(target.toImmutable());
		housing.markNetworkUpdate();
	}

	@Override
	public IClimateState getDefault() {
		return defaultState;
	}

	@Override
	public void setCircular(boolean value) {
		if (this.circular != value) {
			this.circular = value;
			onAreaChange(range, value);
			housing.markNetworkUpdate();
			if (addedToWorld) {
				Level world = getWorldObj();
				if (world instanceof ServerLevel serverLevel) {
					IWorldClimateHolder worldClimate = IForestryApi.get().getClimateManager().getWorldClimate(serverLevel);
					worldClimate.updateTransformer(this);
				}
			}
		}
	}

	@Override
	public boolean isCircular() {
		return circular;
	}

	@Override
	public void setRange(int value) {
		if (value != range) {
			this.range = Mth.clamp(value, 1, 16);
			onAreaChange(this.range, circular);
			housing.markNetworkUpdate();
			if (addedToWorld) {
				Level world = getWorldObj();
				if (world instanceof ServerLevel serverLevel) {
					IWorldClimateHolder worldClimate = IForestryApi.get().getClimateManager().getWorldClimate(serverLevel);
					worldClimate.updateTransformer(this);
				}
			}
		}
	}

	private void onAreaChange(int range, boolean circular) {
		int prevArea = area;
		this.area = computeArea(range, circular);
		if (addedToWorld && area != prevArea) {
			int areaDelta = Math.abs(area - prevArea);
			float speedDelta = calculateSpeedModifier(areaDelta);
			IClimateState deltaState = currentState.subtract(defaultState);
			IClimateState scaledDelta = deltaState.multiply(area > prevArea ? (1.0F / speedDelta) : speedDelta);
			setCurrent(scaledDelta.add(defaultState));
		}
	}

	private static int computeArea(int range, boolean circular) {
		return circular
				? Math.round((range + 0.5F) * (range + 0.5F) * 2.0F * (float) Math.PI)
				: (range * 2 + 1) * (range * 2 + 1);
	}

	@Override
	public float getAreaModifier() {
		return calculateAreaModifier(area);
	}

	@Override
	public float getCostModifier() {
		return 1.0F + (getAreaModifier() * ForestryConfig.habitatFormerAreaCostModifier());
	}

	@Override
	public float getSpeedModifier() {
		return calculateSpeedModifier(area);
	}

	private static float calculateSpeedModifier(float area) {
		return 1.0F + (calculateAreaModifier(area) * ForestryConfig.habitatFormerAreaSpeedModifier());
	}

	private static float calculateAreaModifier(float area) {
		return area / 36.0F;
	}

	@Override
	public int getArea() {
		return area;
	}

	@Override
	public int getRange() {
		return range;
	}

	@Override
	public BlockPos getCoordinates() {
		return housing.getCoordinates();
	}

	@Override
	@Nullable
	public Level getWorldObj() {
		return housing.getWorldObj();
	}
}
