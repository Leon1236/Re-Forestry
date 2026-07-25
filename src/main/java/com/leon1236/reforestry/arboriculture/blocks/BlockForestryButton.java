package com.leon1236.reforestry.arboriculture.blocks;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.IWoodTyped;

public class BlockForestryButton extends ButtonBlock implements IWoodTyped {
    private final ForestryWoodType woodType;

    public BlockForestryButton(ForestryWoodType woodType, BlockBehaviour.Properties properties) {
        super(woodType.getBlockSetType(), 30, properties.noCollision().strength(0.5f).pushReaction(PushReaction.DESTROY));
        this.woodType = woodType;
    }

    @Override
    public WoodBlockKind getBlockKind() {
        return WoodBlockKind.BUTTON;
    }

    @Override
    public boolean isFireproof() {
        return false;
    }

    @Override
    public IWoodType getWoodType() {
        return woodType;
    }
}
