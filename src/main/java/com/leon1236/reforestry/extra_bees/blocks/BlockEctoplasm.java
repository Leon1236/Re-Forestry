package com.leon1236.reforestry.extra_bees.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockEctoplasm extends Block {
	public static final MapCodec<BlockEctoplasm> CODEC = simpleCodec(BlockEctoplasm::new);

	public BlockEctoplasm(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends BlockEctoplasm> codec() {
		return CODEC;
	}
}
