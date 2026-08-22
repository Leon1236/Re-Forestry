package com.leon1236.reforestry.api.climate;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IErrorLogicSource;
import com.leon1236.reforestry.api.core.ILocationProvider;
import com.leon1236.reforestry.api.core.TemperatureType;

public interface IClimateHousing extends IErrorLogicSource, ILocationProvider, IClimateProvider {

	TemperatureType getTemperature();

	HumidityType getHumidity();

	float getExactTemperature();

	float getExactHumidity();

	IClimateTransformer getTransformer();

	float getChangeForState(ClimateType type, IClimateManipulator manipulator);

	void markNetworkUpdate();
}
