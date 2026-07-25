package com.leon1236.reforestry.arboriculture.blocks;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.IWoodTyped;

public class BlockForestryHangingSign extends CeilingHangingSignBlock implements IWoodTyped {
    private final ForestryWoodType woodType;

    public BlockForestryHangingSign(ForestryWoodType woodType, BlockBehaviour.Properties properties) {
        super(woodType.getVanillaWoodType(), properties.mapColor(MapColor.WOOD).forceSolidOn()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1f).ignitedByLava());
        this.woodType = woodType;
    }

    @Override
    public WoodBlockKind getBlockKind() {
        return WoodBlockKind.HANGING_SIGN;
    }

    @Override
    public boolean isFireproof() {
        return false;
    }

    @Override
    public ForestryWoodType getWoodType() {
        return woodType;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> actual) {
        return createTickerHelper(actual, BlockEntityTypes.HANGING_SIGN, HangingSignBlockEntity::tick);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new HangingSignBlockEntity(pos, state);
    }
}
