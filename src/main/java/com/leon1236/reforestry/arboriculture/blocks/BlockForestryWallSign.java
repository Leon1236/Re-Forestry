package com.leon1236.reforestry.arboriculture.blocks;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.IWoodTyped;
import com.leon1236.reforestry.arboriculture.features.ArboricultureTiles;

public class BlockForestryWallSign extends WallSignBlock implements IWoodTyped {
    private final ForestryWoodType woodType;

    public BlockForestryWallSign(ForestryWoodType woodType, BlockBehaviour.Properties properties) {
        super(woodType.getVanillaWoodType(), properties.forceSolidOn().instrument(NoteBlockInstrument.BASS)
                .noCollision().strength(1.0F).ignitedByLava());
        this.woodType = woodType;
    }

    @Override
    public WoodBlockKind getBlockKind() {
        return WoodBlockKind.WALL_SIGN;
    }

    @Override
    public boolean isFireproof() {
        return false;
    }

    @Override
    public IWoodType getWoodType() {
        return woodType;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> actual) {
        return createTickerHelper(actual, ArboricultureTiles.SIGN.type(), SignBlockEntity::tick);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SignBlockEntity(ArboricultureTiles.SIGN.type(), pos, state);
    }
}
