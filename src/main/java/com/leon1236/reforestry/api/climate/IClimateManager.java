package com.leon1236.reforestry.api.climate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public interface IClimateManager {
    TemperatureType getTemperature(Holder<Biome> biome);

    TemperatureType getTemperature(ResourceKey<Biome> biome);

    HumidityType getHumidity(Holder<Biome> holder);

    HumidityType getHumidity(ResourceKey<Biome> holder);

    ClimateState getState(ServerLevel level, BlockPos pos);

    ClimateState getBiomeState(LevelReader level, BlockPos pos);

    @Deprecated
    default IClimateProvider getDefaultClimate(LevelReader level, BlockPos pos) {
        return createClimateProvider(level, pos);
    }

    IClimateProvider createDummyClimateProvider();

    IClimateProvider createClimateProvider(LevelReader level, BlockPos pos);
}
