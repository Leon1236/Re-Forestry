package com.leon1236.reforestry.api.climate;

public interface IClimateManipulator {

	IClimateState getCurrent();

	IClimateState getStart();

	IClimateState getTarget();

	IClimateState getDefault();

	boolean allowsBackwards();

	ClimateType getType();

	IClimateState addChange(boolean simulated);

	IClimateState removeChange(boolean simulated);

	boolean canAdd();

	void finish();
}
