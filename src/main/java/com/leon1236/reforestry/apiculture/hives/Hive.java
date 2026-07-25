package com.leon1236.reforestry.apiculture.hives;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.hives.IHive;
import com.leon1236.reforestry.api.apiculture.hives.IHiveDefinition;
import com.leon1236.reforestry.api.apiculture.hives.IHiveDrop;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public final class Hive implements IHive {
    private final IHiveDefinition hiveDescription;
    private final List<IHiveDrop> drops;
    private final float generationChance;

    public Hive(IHiveDefinition definition, float generationChance, List<IHiveDrop> drops) {
        this.hiveDescription = definition;
        this.generationChance = generationChance;
        this.drops = drops;
    }

    @Override
    public IHiveDefinition getDefinition() {
        return hiveDescription;
    }

    @Override
    public BlockState getHiveBlockState() {
        return hiveDescription.getBlockState();
    }

    @Override
    public List<IHiveDrop> getDrops() {
        return drops;
    }

    @Override
    public float genChance() {
        return generationChance;
    }

    @Override
    public void postGen(WorldGenLevel world, RandomSource rand, BlockPos pos) {
        hiveDescription.postGen(world, rand, pos);
    }

    @Override
    public boolean isGoodBiome(Holder<Biome> biome) {
        return hiveDescription.isGoodBiome(biome);
    }

    @Override
    public boolean isGoodHumidity(HumidityType humidity) {
        return hiveDescription.isGoodHumidity(humidity);
    }

    @Override
    public boolean isGoodTemperature(TemperatureType temperature) {
        return hiveDescription.isGoodTemperature(temperature);
    }

    @Override
    public boolean isValidLocation(WorldGenLevel world, BlockPos pos) {
        return hiveDescription.getHiveGen().isValidLocation(world, pos);
    }

    @Override
    public boolean canReplace(WorldGenLevel world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return hiveDescription.getHiveGen().canReplace(blockState, world, pos);
    }

    @Nullable
    @Override
    public BlockPos getPosForHive(WorldGenLevel level, int posX, int posZ) {
        return hiveDescription.getHiveGen().getPosForHive(level, posX, posZ);
    }

    @Nullable
    @Override
    public BlockPos getPosForHive(WorldGenLevel level, RandomSource rand, int posX, int posZ) {
        return hiveDescription.getHiveGen().getPosForHive(level, rand, posX, posZ);
    }

    @Override
    public String toString() {
        return hiveDescription + " hive";
    }
}
