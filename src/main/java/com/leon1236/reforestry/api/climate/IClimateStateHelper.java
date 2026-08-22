package com.leon1236.reforestry.api.climate;

import net.minecraft.nbt.CompoundTag;

public interface IClimateStateHelper {

	IClimateState create(float temperature, float humidity);

	IClimateState create(float temperature, float humidity, boolean mutable);

	IClimateState create(IClimateState climateState);

	IClimateState create(IClimateState climateState, boolean mutable);

	IClimateState create(ClimateType type, float value);

	IClimateState create(CompoundTag compound);

	IClimateState create(CompoundTag compound, boolean mutable);

	CompoundTag writeToNBT(CompoundTag compound, IClimateState state);

	IClimateState checkState(IClimateState climateState);

	IClimateState clamp(IClimateState climateState);

	IClimateState absent();

	IClimateState min();

	IClimateState max();

	IClimateState zero();

	IClimateState mutableZero();
}
