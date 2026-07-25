package com.leon1236.reforestry.arboriculture.blocks;

import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.IWoodTyped;

public class BlockForestryPressurePlate extends PressurePlateBlock implements IWoodTyped {
    private final ForestryWoodType woodType;

    public BlockForestryPressurePlate(ForestryWoodType woodType, BlockBehaviour.Properties properties) {
        super(woodType.getBlockSetType(), properties.mapColor(MapColor.WOOD).forceSolidOn()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5f).ignitedByLava().pushReaction(PushReaction.DESTROY));
        this.woodType = woodType;
    }

    @Override
    public WoodBlockKind getBlockKind() {
        return WoodBlockKind.PRESSURE_PLATE;
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
