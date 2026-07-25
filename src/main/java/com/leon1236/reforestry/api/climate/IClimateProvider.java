package com.leon1236.reforestry.api.climate;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public interface IClimateProvider {
    TemperatureType temperature();

    HumidityType humidity();
}
