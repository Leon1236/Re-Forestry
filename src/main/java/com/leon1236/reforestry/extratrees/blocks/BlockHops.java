package com.leon1236.reforestry.extratrees.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import com.leon1236.reforestry.extratrees.features.ExtraTreesBlocks;

public class BlockHops extends CropBlock {
	public static final MapCodec<BlockHops> CODEC = simpleCodec(BlockHops::new);

	public BlockHops(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public MapCodec<? extends CropBlock> codec() {
		return CODEC;
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return ExtraTreesBlocks.HOPS.item();
	}

	@Override
	protected int getBonemealAgeIncrease(Level level) {
		return Math.max(1, super.getBonemealAgeIncrease(level) / 2);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (isMaxAge(state)) {
			if (!level.isClientSide()) {
				Block.popResource(level, pos, new ItemStack(getBaseSeedId(), 1 + level.getRandom().nextInt(2)));
				level.setBlock(pos, getStateForAge(0), Block.UPDATE_ALL);
			}
			return InteractionResult.SUCCESS;
		}
		return super.useWithoutItem(state, level, pos, player, hitResult);
	}

	@Override
	protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
		return new ItemStack(getBaseSeedId());
	}
}
