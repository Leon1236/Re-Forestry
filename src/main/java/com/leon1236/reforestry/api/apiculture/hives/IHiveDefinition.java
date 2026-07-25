package com.leon1236.reforestry.api.apiculture.hives;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public interface IHiveDefinition {
    IHiveGen getHiveGen();

    BlockState getBlockState();

    boolean isGoodBiome(Holder<Biome> biome);

    boolean isGoodHumidity(HumidityType humidity);

    boolean isGoodTemperature(TemperatureType temperature);

    float getGenChance();

    void postGen(WorldGenLevel level, RandomSource rand, BlockPos pos);
}
