package com.leon1236.reforestry.arboriculture.blocks;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.IWoodTyped;

public class BlockForestrySlab extends SlabBlock implements IWoodTyped {
    private final boolean fireproof;
    private final IWoodType woodType;

    public BlockForestrySlab(BlockForestryPlank plank, BlockBehaviour.Properties properties) {
        super(BlockForestryPlank.createWoodProperties(plank.getWoodType(), properties));
        this.fireproof = plank.isFireproof();
        this.woodType = plank.getWoodType();
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
        return WoodBlockKind.SLAB;
    }
}
