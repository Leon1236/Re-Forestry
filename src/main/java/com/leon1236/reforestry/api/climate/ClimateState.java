package com.leon1236.reforestry.api.climate;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public record ClimateState(TemperatureType temperature, HumidityType humidity) implements IClimateProvider {
}
