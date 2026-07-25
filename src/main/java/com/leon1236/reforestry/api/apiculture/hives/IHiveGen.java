package com.leon1236.reforestry.api.apiculture.hives;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;

public interface IHiveGen {
    @Nullable
    BlockPos getPosForHive(WorldGenLevel level, int posX, int posZ);

    @Nullable
    default BlockPos getPosForHive(WorldGenLevel level, RandomSource rand, int posX, int posZ) {
        return getPosForHive(level, posX, posZ);
    }

    boolean isValidLocation(WorldGenLevel world, BlockPos pos);

    boolean canReplace(BlockState blockState, WorldGenLevel world, BlockPos pos);

    static boolean isTreeBlock(BlockState state) {
        return state.is(BlockTags.LEAVES) || state.is(BlockTags.LOGS);
    }
}
