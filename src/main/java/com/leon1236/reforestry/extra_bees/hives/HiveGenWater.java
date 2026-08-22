package com.leon1236.reforestry.extra_bees.hives;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

import com.leon1236.reforestry.api.apiculture.hives.IHiveGen;
import com.leon1236.reforestry.api.core.ReforestryBiomeTags;

public class HiveGenWater implements IHiveGen {
	@Override
	@Nullable
	public BlockPos getPosForHive(WorldGenLevel level, int posX, int posZ) {
		return null;
	}

	@Override
	@Nullable
	public BlockPos getPosForHive(WorldGenLevel level, RandomSource rand, int posX, int posZ) {
		int topY = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, posX, posZ);
		int minY = level.getMinY();
		if (topY <= minY) {
			return null;
		}

		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(posX, topY, posZ);
		int range = topY - minY;
		for (int i = 0; i < 10; i++) {
			pos.setY(minY + rand.nextInt(range));
			if (isValidLocation(level, pos)) {
				return pos.immutable();
			}
		}
		return null;
	}

	@Override
	public boolean isValidLocation(WorldGenLevel world, BlockPos pos) {
		if (!world.getBlockState(pos).is(Blocks.WATER)) {
			return false;
		}
		return world.getBlockState(pos.below()).is(ReforestryBiomeTags.Blocks.WATER_HIVE_BELOW);
	}

	@Override
	public boolean canReplace(BlockState blockState, WorldGenLevel world, BlockPos pos) {
		return blockState.is(Blocks.WATER);
	}
}
