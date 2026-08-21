package com.leon1236.reforestry.gendustry.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import com.leon1236.reforestry.core.blocks.BlockMachine;

public class GendustryMachineBlock extends BlockMachine<GendustryMachineType> {
	private final MapCodec<GendustryMachineBlock> codec;

	public GendustryMachineBlock(GendustryMachineType type, BlockBehaviour.Properties properties) {
		super(type, properties
				.sound(SoundType.METAL)
				.mapColor(MapColor.SAND)
				.instrument(NoteBlockInstrument.IRON_XYLOPHONE)
				.requiresCorrectToolForDrops());
		this.codec = simpleCodec(props -> new GendustryMachineBlock(type, props));
	}

	@Override
	protected MapCodec<? extends GendustryMachineBlock> codec() {
		return codec;
	}
}
