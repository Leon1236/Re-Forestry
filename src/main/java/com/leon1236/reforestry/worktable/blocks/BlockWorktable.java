package com.leon1236.reforestry.worktable.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.core.blocks.BlockMachine;
import com.leon1236.reforestry.core.tiles.TileUtil;
import com.leon1236.reforestry.worktable.tiles.TileWorktable;

public class BlockWorktable extends BlockMachine<WorktableBlockType> {
	private final MapCodec<BlockWorktable> codec;

	public BlockWorktable(WorktableBlockType type, BlockBehaviour.Properties properties) {
		super(type, properties.sound(SoundType.WOOD));
		this.codec = simpleCodec(props -> new BlockWorktable(type, props));
	}

	@Override
	protected MapCodec<? extends BlockWorktable> codec() {
		return this.codec;
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide()) {
			TileWorktable tile = TileUtil.getTile(level, pos, TileWorktable.class);
			if (tile != null) {
				Containers.dropContents(level, pos, tile);
			}
		}
		return super.playerWillDestroy(level, pos, state, player);
	}
}
