package com.leon1236.reforestry.arboriculture.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.IWoodTyped;

public class BlockForestryPlank extends Block implements IWoodTyped {
    public static BlockBehaviour.Properties createWoodProperties(IWoodType woodType, BlockBehaviour.Properties properties) {
        return properties.strength(woodType.getHardness(), woodType.getHardness() * 1.5F).sound(SoundType.WOOD);
    }

    private final boolean fireproof;
    private final IWoodType woodType;

    public BlockForestryPlank(boolean fireproof, IWoodType woodType, BlockBehaviour.Properties properties) {
        super(createWoodProperties(woodType, properties));
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
        return WoodBlockKind.PLANKS;
    }
}
