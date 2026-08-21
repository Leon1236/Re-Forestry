package com.leon1236.reforestry.extratrees.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import com.leon1236.reforestry.core.blocks.BlockMachine;

public class ExtraTreesMachineBlock extends BlockMachine<ExtraTreeMachineType> {
	private final MapCodec<ExtraTreesMachineBlock> codec;

	public ExtraTreesMachineBlock(ExtraTreeMachineType type, BlockBehaviour.Properties properties) {
		super(type, properties
				.sound(SoundType.METAL)
				.mapColor(MapColor.WOOD)
				.instrument(NoteBlockInstrument.BASS)
				.requiresCorrectToolForDrops());
		this.codec = simpleCodec(props -> new ExtraTreesMachineBlock(type, props));
	}

	@Override
	protected MapCodec<? extends ExtraTreesMachineBlock> codec() {
		return codec;
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide() && level.getBlockEntity(pos) instanceof Container container) {
			Containers.dropContents(level, pos, container);
		}
		return super.playerWillDestroy(level, pos, state, player);
	}
}
