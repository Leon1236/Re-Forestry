package com.leon1236.reforestry.api.climate;

import com.leon1236.reforestry.api.core.ILocationProvider;

public interface IClimateTransformer extends ILocationProvider {

	void update();

	void removeTransformer();

	IClimateHousing getHousing();

	int getRange();

	void setRange(int range);

	void setCircular(boolean circular);

	boolean isCircular();

	int getArea();

	float getAreaModifier();

	float getCostModifier();

	float getSpeedModifier();

	IClimateState getTarget();

	void setTarget(IClimateState target);

	IClimateState getCurrent();

	void setCurrent(IClimateState state);

	IClimateState getDefault();

	IClimateManipulatorBuilder createManipulator(ClimateType type);
}
