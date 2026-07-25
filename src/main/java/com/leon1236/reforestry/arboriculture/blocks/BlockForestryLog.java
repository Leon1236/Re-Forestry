package com.leon1236.reforestry.arboriculture.blocks;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.IWoodTyped;

public class BlockForestryLog extends RotatedPillarBlock implements IWoodTyped {
    private final WoodBlockKind kind;
    private final boolean fireproof;
    private final IWoodType woodType;

    public BlockForestryLog(WoodBlockKind kind, boolean fireproof, IWoodType woodType, BlockBehaviour.Properties properties) {
        super(BlockForestryPlank.createWoodProperties(woodType, properties));
        this.kind = kind;
        this.fireproof = fireproof;
        this.woodType = woodType;
    }

    @Override
    public WoodBlockKind getBlockKind() {
        return kind;
    }

    @Override
    public boolean isFireproof() {
        return fireproof;
    }

    @Override
    public IWoodType getWoodType() {
        return woodType;
    }
}
