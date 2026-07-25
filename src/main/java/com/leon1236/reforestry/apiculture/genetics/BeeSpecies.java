package com.leon1236.reforestry.apiculture.genetics;

import java.util.List;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

record BeeSpecies(Identifier id, String genus, String species, boolean dominant, int outlineColor, int bodyColor,
                   int stripesColor, boolean secret, boolean glint, String authority,
                   TemperatureType temperature, HumidityType humidity,
                   List<Product> products, List<Product> specialties) implements IBeeSpecies {
    @Override
    public TemperatureType getTemperature() {
        return temperature;
    }

    @Override
    public HumidityType getHumidity() {
        return humidity;
    }
}
