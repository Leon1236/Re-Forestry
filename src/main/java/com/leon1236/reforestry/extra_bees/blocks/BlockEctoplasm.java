package com.leon1236.reforestry.extra_bees.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockEctoplasm extends WebBlock {
	public static final MapCodec<BlockEctoplasm> CODEC = simpleCodec(BlockEctoplasm::new);

	public BlockEctoplasm(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public MapCodec<BlockEctoplasm> codec() {
		return CODEC;
	}
}
