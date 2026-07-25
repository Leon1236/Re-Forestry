package com.leon1236.reforestry.api.apiculture.hives;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public interface IHive {
    IHiveDefinition getDefinition();

    BlockState getHiveBlockState();

    List<IHiveDrop> getDrops();

    float genChance();

    void postGen(WorldGenLevel world, RandomSource rand, BlockPos pos);

    boolean isGoodBiome(Holder<Biome> biome);

    boolean isGoodHumidity(HumidityType humidity);

    boolean isGoodTemperature(TemperatureType temperature);

    boolean isValidLocation(WorldGenLevel world, BlockPos pos);

    boolean canReplace(WorldGenLevel world, BlockPos pos);

    @Nullable
    BlockPos getPosForHive(WorldGenLevel level, int posX, int posZ);

    @Nullable
    default BlockPos getPosForHive(WorldGenLevel level, RandomSource rand, int posX, int posZ) {
        return getPosForHive(level, posX, posZ);
    }
}
