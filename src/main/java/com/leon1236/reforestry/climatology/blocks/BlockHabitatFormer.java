package com.leon1236.reforestry.climatology.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.core.blocks.BlockMachine;

public class BlockHabitatFormer extends BlockMachine<ClimatologyBlockType> {
	private final MapCodec<BlockHabitatFormer> codec;

	public BlockHabitatFormer(ClimatologyBlockType type, BlockBehaviour.Properties properties) {
		super(type, properties.strength(1.0F).sound(SoundType.METAL));
		this.codec = simpleCodec(props -> new BlockHabitatFormer(type, props));
	}

	@Override
	protected MapCodec<? extends BlockHabitatFormer> codec() {
		return this.codec;
	}
}
