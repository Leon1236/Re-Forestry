package com.leon1236.reforestry.factory.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.core.blocks.BlockMachine;

public class BlockFactoryPlain extends BlockMachine<BlockTypeFactoryPlain> {
	private final MapCodec<BlockFactoryPlain> codec;

	public BlockFactoryPlain(BlockTypeFactoryPlain type, BlockBehaviour.Properties properties) {
		super(type, usesMachineRenderer(type) ? properties.noOcclusion() : properties);
		this.codec = simpleCodec(props -> new BlockFactoryPlain(type, props));
	}

	@Override
	protected MapCodec<? extends BlockFactoryPlain> codec() {
		return codec;
	}

	private static boolean usesMachineRenderer(BlockTypeFactoryPlain type) {
		return type != BlockTypeFactoryPlain.FABRICATOR && type != BlockTypeFactoryPlain.SMELTER;
	}
}
