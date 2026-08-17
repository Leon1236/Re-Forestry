package com.leon1236.reforestry.factory.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.core.blocks.BlockMachine;

public class BlockFactoryPlain extends BlockMachine<BlockTypeFactoryPlain> {
	private final MapCodec<BlockFactoryPlain> codec;

	public BlockFactoryPlain(BlockTypeFactoryPlain type, BlockBehaviour.Properties properties) {
		super(type, properties.noOcclusion());
		this.codec = simpleCodec(props -> new BlockFactoryPlain(type, props));
	}

	@Override
	protected MapCodec<? extends BlockFactoryPlain> codec() {
		return codec;
	}
}
