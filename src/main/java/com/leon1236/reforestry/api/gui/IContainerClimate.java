package com.leon1236.reforestry.api.gui;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public interface IContainerClimate {
	TemperatureType getTemperature();

	HumidityType getHumidity();
}
