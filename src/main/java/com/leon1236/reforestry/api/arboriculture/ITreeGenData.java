package com.leon1236.reforestry.api.arboriculture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

import com.leon1236.reforestry.api.genetics.IGenome;

public interface ITreeGenData {
    int getGirth(IGenome genome);

    float getHeightModifier(IGenome genome);

    @Nullable
    BlockPos getGrowthPos(IGenome genome, LevelAccessor level, BlockPos pos, int expectedGirth, int expectedHeight);

    boolean setLeaves(IGenome genome, LevelAccessor level, BlockPos pos, RandomSource random, boolean convertBlockEntity);

    boolean setLogBlock(IGenome genome, LevelAccessor level, BlockPos pos, Direction facing);

    boolean trySpawnFruitBlock(IGenome genome, LevelAccessor level, RandomSource rand, BlockPos pos);

    IGenome getDefaultGenome();
}
