package com.leon1236.reforestry.arboriculture.blocks;

import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.IWoodTyped;

public class BlockForestryTrapdoor extends TrapDoorBlock implements IWoodTyped {
    private final ForestryWoodType woodType;

    public BlockForestryTrapdoor(ForestryWoodType woodType, BlockBehaviour.Properties properties) {
        super(woodType.getBlockSetType(), properties.mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS)
                .strength(3f).noOcclusion().ignitedByLava());
        this.woodType = woodType;
    }

    @Override
    public WoodBlockKind getBlockKind() {
        return WoodBlockKind.TRAPDOOR;
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
