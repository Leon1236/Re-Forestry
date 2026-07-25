package com.leon1236.reforestry.api.genetics.pollen;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;

public interface IPollenType {
    boolean canPollinate(Level level, BlockPos pos);

    Optional<IGenome> tryCollectPollen(Level level, BlockPos pos, RandomSource random);

    boolean tryPollinate(Level level, BlockPos pos, IGenome pollen, RandomSource random);
}
