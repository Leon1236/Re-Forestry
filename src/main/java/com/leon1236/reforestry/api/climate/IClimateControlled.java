package com.leon1236.reforestry.api.climate;

public interface IClimateControlled {
    void addTemperatureChange(byte steps);

    void addHumidityChange(byte steps);
}
