package com.leon1236.reforestry.apiculture.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.apiculture.blocks.BlockAlvearyType;

public class TileAlvearyFan extends TileAlvearyClimatiser {
	public TileAlvearyFan(BlockPos pos, BlockState state) {
		super(BlockAlvearyType.FAN, pos, state, (byte) -1);
	}
}
