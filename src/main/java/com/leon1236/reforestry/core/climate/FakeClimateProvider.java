package com.leon1236.reforestry.core.climate;

import com.leon1236.reforestry.api.climate.IClimateProvider;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public enum FakeClimateProvider implements IClimateProvider {
    INSTANCE;

    @Override
    public TemperatureType temperature() {
        return TemperatureType.NORMAL;
    }

    @Override
    public HumidityType humidity() {
        return HumidityType.NORMAL;
    }
}
