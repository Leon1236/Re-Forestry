package com.leon1236.reforestry.arboriculture.blocks;

import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.IWoodTyped;

public class BlockForestryFence extends FenceBlock implements IWoodTyped {
    private final boolean fireproof;
    private final IWoodType woodType;

    public BlockForestryFence(boolean fireproof, IWoodType woodType, BlockBehaviour.Properties properties) {
        super(BlockForestryPlank.createWoodProperties(woodType, properties));
        this.fireproof = fireproof;
        this.woodType = woodType;
    }

    @Override
    public boolean isFireproof() {
        return fireproof;
    }

    @Override
    public IWoodType getWoodType() {
        return woodType;
    }

    @Override
    public WoodBlockKind getBlockKind() {
        return WoodBlockKind.FENCE;
    }
}
