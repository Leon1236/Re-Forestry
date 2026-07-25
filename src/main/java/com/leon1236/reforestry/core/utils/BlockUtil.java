package com.leon1236.reforestry.core.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public final class BlockUtil {
    private BlockUtil() {
    }

    public static boolean canReplace(BlockState blockState, LevelAccessor world, BlockPos pos) {
        return world.getBlockState(pos).canBeReplaced() && blockState.getFluidState().isEmpty();
    }
}
