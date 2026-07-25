package com.leon1236.reforestry.arboriculture.client;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.arboriculture.blocks.ForestryLeafType;

public final class DefaultLeafFoliageTintSource implements BlockTintSource {
    private final ForestryLeafType type;

    public DefaultLeafFoliageTintSource(ForestryLeafType type) {
        this.type = type;
    }

    @Override
    public int color(BlockState state) {
        if (type.getSpecies() == null) {
            return ARGB.opaque(0xffffff);
        }
        return ARGB.opaque(type.getSpecies().escritoireColor());
    }

    @Override
    public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
        return color(state);
    }
}
