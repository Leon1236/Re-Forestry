package com.leon1236.reforestry.arboriculture.blocks;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.IWoodTyped;

public class BlockForestryDoor extends DoorBlock implements IWoodTyped {
    private final ForestryWoodType woodType;

    public BlockForestryDoor(ForestryWoodType woodType, BlockBehaviour.Properties properties) {
        super(woodType.getBlockSetType(), properties
                .strength(woodType.getHardness(), woodType.getHardness() * 1.5F).sound(SoundType.WOOD).noOcclusion());
        this.woodType = woodType;
    }

    @Override
    public WoodBlockKind getBlockKind() {
        return WoodBlockKind.DOOR;
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
