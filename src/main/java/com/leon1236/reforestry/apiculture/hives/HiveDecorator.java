package com.leon1236.reforestry.apiculture.hives;

import com.mojang.serialization.Codec;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.hives.IHive;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public class HiveDecorator extends Feature<NoneFeatureConfiguration> {
    public static final float WILD_HIVE_SPAWN_RATE = 1.0f;

    public HiveDecorator(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public HiveDecorator() {
        this(NoneFeatureConfiguration.CODEC);
    }

    public static boolean tryGenHive(WorldGenLevel world, RandomSource rand, int posX, int posZ, IHive hive) {
        BlockPos hivePos = hive.getPosForHive(world, rand, posX, posZ);
        if (hivePos == null) {
            return false;
        }
        if (!hive.canReplace(world, hivePos)) {
            return false;
        }

        Holder<Biome> biome = world.getBiome(hivePos);
        TemperatureType temperature = IForestryApi.INSTANCE.getClimateManager().getTemperature(biome);
        HumidityType humidity = IForestryApi.INSTANCE.getClimateManager().getHumidity(biome);
        if (!hive.isGoodBiome(biome) || !hive.isGoodTemperature(temperature) || !hive.isGoodHumidity(humidity)) {
            return false;
        }
        if (!hive.isValidLocation(world, hivePos)) {
            return false;
        }
        return setHive(world, rand, hivePos, hive);
    }

    private static boolean setHive(WorldGenLevel level, RandomSource rand, BlockPos pos, IHive hive) {
        BlockState hiveState = hive.getHiveBlockState();
        Block hiveBlock = hiveState.getBlock();
        boolean placed = level.setBlock(pos, hiveState, Block.UPDATE_CLIENTS);
        if (!placed) {
            return false;
        }
        BlockState state = level.getBlockState(pos);
        if (hiveBlock != state.getBlock()) {
            return false;
        }
        hive.postGen(level, rand, pos);
        return true;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockPos pos = context.origin();

        ObjectArrayList<IHive> hives = new ObjectArrayList<>(IForestryApi.INSTANCE.getHiveManager().getHives());
        if (hives.isEmpty()) {
            return false;
        }

        int numTries = (int) Math.ceil(hives.size() / 2f);
        double baseChance = WILD_HIVE_SPAWN_RATE * hives.size() / 8.0;
        Util.shuffle(hives, rand);

        for (int tries = 0; tries < numTries; tries++) {
            for (IHive hive : hives) {
                if (hive.genChance() * baseChance >= rand.nextFloat() * 100.0f) {
                    int posX = pos.getX() + rand.nextInt(16);
                    int posZ = pos.getZ() + rand.nextInt(16);
                    if (tryGenHive(level, rand, posX, posZ, hive)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
