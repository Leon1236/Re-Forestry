package com.leon1236.reforestry.arboriculture.blocks;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.IWoodTyped;

public class BlockForestryStairs extends StairBlock implements IWoodTyped {
    private final boolean fireproof;
    @Nullable
    private final IWoodType woodType;

    public BlockForestryStairs(BlockState baseState, BlockBehaviour.Properties properties) {
        super(baseState, properties);
        this.fireproof = false;
        this.woodType = null;
    }

    public BlockForestryStairs(BlockForestryPlank plank, BlockBehaviour.Properties properties) {
        super(plank.defaultBlockState(), BlockForestryPlank.createWoodProperties(plank.getWoodType(), properties));
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
        return WoodBlockKind.STAIRS;
    }
}
