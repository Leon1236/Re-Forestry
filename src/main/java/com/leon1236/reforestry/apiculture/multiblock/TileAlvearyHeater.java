package com.leon1236.reforestry.apiculture.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.apiculture.blocks.BlockAlvearyType;

public class TileAlvearyHeater extends TileAlvearyClimatiser {
	public TileAlvearyHeater(BlockPos pos, BlockState state) {
		super(BlockAlvearyType.HEATER, pos, state, (byte) 1);
	}
}
