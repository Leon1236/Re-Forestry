package com.leon1236.reforestry.api.arboriculture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import com.leon1236.reforestry.api.genetics.IGenome;

public interface ITreeGenerator {
    Feature<NoneFeatureConfiguration> getTreeFeature(ITreeGenData tree);

    boolean setLogBlock(IGenome genome, LevelAccessor level, BlockPos pos, Direction facing);

    boolean setLeaves(IGenome genome, LevelAccessor level, BlockPos pos, RandomSource rand);
}
