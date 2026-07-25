package com.leon1236.reforestry.core.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class BlockForestryFluid extends LiquidBlock {
    private final boolean freezing;
    private final boolean burning;

    public BlockForestryFluid(FlowingFluid fluid, ForestryFluidProperties properties, BlockBehaviour.Properties blockProperties) {
        super(fluid, blockProperties);
        this.freezing = properties.temperature() < 270;
        this.burning = properties.temperature() > 505;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity,
                                 InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if (this.freezing) {
            entity.setIsInPowderSnow(true);
        } else if (this.burning) {
            entity.igniteForSeconds(5.0F);
            entity.hurt(level.damageSources().lava(), 1.0F);
        }
    }
}
