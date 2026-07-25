package com.leon1236.reforestry.arboriculture.blocks;

import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.IWoodTyped;

public class BlockForestryFenceGate extends FenceGateBlock implements IWoodTyped {
    private final boolean fireproof;
    private final IWoodType woodType;

    public BlockForestryFenceGate(boolean fireproof, IWoodType woodType, BlockBehaviour.Properties properties) {
        super(woodType.getVanillaWoodType(), BlockForestryPlank.createWoodProperties(woodType, properties));
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
        return WoodBlockKind.FENCE_GATE;
    }
}
