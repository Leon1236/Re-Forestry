package com.leon1236.reforestry.core.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class BlockCoreMachine extends BlockMachine<BlockTypeCore> {
	private final MapCodec<BlockCoreMachine> codec;

	public BlockCoreMachine(BlockTypeCore type, BlockBehaviour.Properties properties) {
		super(type, properties.noOcclusion());
		this.codec = simpleCodec(props -> new BlockCoreMachine(type, props));
	}

	@Override
	protected MapCodec<? extends BlockCoreMachine> codec() {
		return codec;
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide() && level.getBlockEntity(pos) instanceof net.minecraft.world.Container container) {
			Containers.dropContents(level, pos, container);
		}
		return super.playerWillDestroy(level, pos, state, player);
	}
}
