package com.leon1236.reforestry.factory.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import com.leon1236.reforestry.core.blocks.BlockMachine;
import com.leon1236.reforestry.core.tiles.TileUtil;
import com.leon1236.reforestry.factory.tiles.TileMillRainmaker;

public class BlockFactoryTesr extends BlockMachine<BlockTypeFactoryTesr> {
    private final MapCodec<BlockFactoryTesr> codec;

    public BlockFactoryTesr(BlockTypeFactoryTesr type, BlockBehaviour.Properties properties) {
        super(type, properties.noOcclusion());
        this.codec = simpleCodec(props -> new BlockFactoryTesr(type, props));
    }

    @Override
    protected MapCodec<? extends BlockFactoryTesr> codec() {
        return codec;
    }

    @Override
    protected InteractionResult useItemOn(
            ItemStack stack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hitResult
    ) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        TileMillRainmaker tile = TileUtil.getTile(level, pos, TileMillRainmaker.class);
        if (tile == null || !tile.isUsableByPlayer(player)) {
            return InteractionResult.PASS;
        }

        if (tile.tryCharge(player, player.getItemInHand(hand))) {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
