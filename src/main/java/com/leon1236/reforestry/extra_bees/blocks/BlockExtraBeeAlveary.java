package com.leon1236.reforestry.extra_bees.blocks;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;

import com.leon1236.reforestry.apiculture.multiblock.IAlvearyControllerInternal;
import com.leon1236.reforestry.core.blocks.BlockStructure;
import com.leon1236.reforestry.core.tiles.TileUtil;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyFrameHousing;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyHatchery;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyLighting;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyMutator;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyRainShield;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyStimulator;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyTransmission;
import com.leon1236.reforestry.extra_bees.multiblock.TileExtraBeeAlveary;

public class BlockExtraBeeAlveary extends BlockStructure {
	private final BlockExtraBeeAlvearyType type;
	private final MapCodec<BlockExtraBeeAlveary> codec;

	public BlockExtraBeeAlveary(BlockExtraBeeAlvearyType type, BlockBehaviour.Properties properties) {
		super(properties);
		this.type = type;
		this.codec = simpleCodec(props -> new BlockExtraBeeAlveary(type, props));
	}

	@Override
	protected MapCodec<? extends BlockExtraBeeAlveary> codec() {
		return codec;
	}

	public BlockExtraBeeAlvearyType getType() {
		return type;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return switch (type) {
			case MUTATOR -> new TileAlvearyMutator(pos, state);
			case FRAME -> new TileAlvearyFrameHousing(pos, state);
			case RAIN_SHIELD -> new TileAlvearyRainShield(pos, state);
			case LIGHTING -> new TileAlvearyLighting(pos, state);
			case STIMULATOR -> new TileAlvearyStimulator(pos, state);
			case HATCHERY -> new TileAlvearyHatchery(pos, state);
			case TRANSMISSION -> new TileAlvearyTransmission(pos, state);
		};
	}

	@Override
	protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
			@Nullable Orientation orientation, boolean movedByPiston) {
		TileUtil.actOnTile(level, pos, TileExtraBeeAlveary.class, tile -> {
			IAlvearyControllerInternal alveary = tile.getMultiblockLogic().getController();
			alveary.reassemble();
		});
	}
}
