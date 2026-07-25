package com.leon1236.reforestry.core.blocks;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.core.tiles.TileForestry;

public interface IMachineProperties<T extends TileForestry> extends StringRepresentable {
    BlockEntityType<? extends T> getTeType();

    BlockEntity createTileEntity(BlockPos pos, BlockState state);

    @Nullable
    BlockEntityTicker<T> getClientTicker();

    @Nullable
    BlockEntityTicker<T> getServerTicker();

    void setBlock(Block block);

    @Nullable
    Block getBlock();
}
