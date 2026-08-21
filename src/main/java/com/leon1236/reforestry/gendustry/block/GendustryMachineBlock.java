package com.leon1236.reforestry.gendustry.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import com.leon1236.reforestry.core.blocks.BlockMachine;
import com.leon1236.reforestry.gendustry.blockentity.IndustrialApiaryBlockEntity;

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

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (!level.isClientSide()
				&& placer instanceof Player player
				&& level.getBlockEntity(pos) instanceof IndustrialApiaryBlockEntity apiary) {
			apiary.setOwner(player.getGameProfile());
		}
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide() && level.getBlockEntity(pos) instanceof Container container) {
			Containers.dropContents(level, pos, container);
		}
		return super.playerWillDestroy(level, pos, state, player);
	}
}
