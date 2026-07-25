package com.leon1236.reforestry.core.climate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.climate.IBiomeProvider;
import com.leon1236.reforestry.api.climate.IClimateProvider;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public final class ClimateProvider implements IClimateProvider, IBiomeProvider {
    private final Holder<Biome> biome;

    public ClimateProvider(LevelReader level, BlockPos pos) {
        this.biome = level.getBiome(pos);
    }

    @Override
    public Holder<Biome> getBiome() {
        return biome;
    }

    @Override
    public TemperatureType temperature() {
        return IForestryApi.INSTANCE.getClimateManager().getTemperature(biome);
    }

    @Override
    public HumidityType humidity() {
        return IForestryApi.INSTANCE.getClimateManager().getHumidity(biome);
    }
}
