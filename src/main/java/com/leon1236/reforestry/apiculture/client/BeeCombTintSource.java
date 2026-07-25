package com.leon1236.reforestry.apiculture.client;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.apiculture.blocks.BlockHoneyComb;

public final class BeeCombTintSource implements BlockTintSource {
    private final boolean primary;

    public BeeCombTintSource(boolean primary) {
        this.primary = primary;
    }

    private int colorFor(BlockState state) {
        if (!(state.getBlock() instanceof BlockHoneyComb comb)) {
            return 0xffffff;
        }
        return primary ? comb.getType().primaryColor : comb.getType().secondaryColor;
    }

    @Override
    public int color(BlockState state) {
        return ARGB.opaque(colorFor(state));
    }

    @Override
    public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
        return color(state);
    }
}
