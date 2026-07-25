package com.leon1236.reforestry.core.energy;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import com.leon1236.reforestry.core.features.CoreTiles;
import com.leon1236.reforestry.core.tiles.TileUtil;

public class BlockDebugPowered extends BaseEntityBlock {
    private final MapCodec<BlockDebugPowered> codec = simpleCodec(BlockDebugPowered::new);

    public BlockDebugPowered(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BlockDebugPowered> codec() {
        return this.codec;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return CoreTiles.DEBUG_POWERED.type().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        return createTickerHelper(type, CoreTiles.DEBUG_POWERED.type(), TileDebugPowered::serverTick);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        TileDebugPowered tile = TileUtil.getTile(level, pos, TileDebugPowered.class);
        if (tile == null || !tile.isUsableByPlayer(player)) {
            return InteractionResult.PASS;
        }
        player.sendSystemMessage(Component.literal(
                "Energy: " + tile.getEnergyManager().getAmount() + " / " + tile.getEnergyManager().getCapacity()));
        return InteractionResult.SUCCESS;
    }
}
