package com.leon1236.reforestry.climatology.client;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.climatology.tiles.TileHabitatFormer;

public final class HabitatFormerTintSource implements BlockTintSource {
	@Override
	public int color(BlockState state) {
		return ARGB.opaque(0x912237);
	}

	@Override
	public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
		BlockEntity be = level.getBlockEntity(pos);
		if (be instanceof TileHabitatFormer former) {
			return ARGB.opaque(former.getTemperature().color);
		}
		return ARGB.opaque(0x912237);
	}
}
