package com.leon1236.reforestry.extra_bees.hives;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.hives.IHiveGen;

public class HiveGenNether implements IHiveGen {
	@Override
	@Nullable
	public BlockPos getPosForHive(WorldGenLevel level, int posX, int posZ) {
		return null;
	}

	@Override
	@Nullable
	public BlockPos getPosForHive(WorldGenLevel level, RandomSource rand, int posX, int posZ) {
		if (!level.getBiome(new BlockPos(posX, level.getMinY(), posZ)).is(BiomeTags.IS_NETHER)) {
			return null;
		}

		int minY = level.getMinY();
		int height = level.getHeight();
		if (height <= 0) {
			return null;
		}

		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(posX, minY + height - 1, posZ);
		for (int i = 0; i < 10; i++) {
			pos.setY(minY + rand.nextInt(height));
			if (isValidLocation(level, pos)) {
				return pos.immutable();
			}
		}
		return null;
	}

	@Override
	public boolean isValidLocation(WorldGenLevel world, BlockPos pos) {
		if (!world.getBiome(pos).is(BiomeTags.IS_NETHER)) {
			return false;
		}
		if (!world.getBlockState(pos).is(Blocks.NETHERRACK)) {
			return false;
		}
		return embedInWall(world, pos);
	}

	@Override
	public boolean canReplace(BlockState state, WorldGenLevel world, BlockPos pos) {
		return state.is(Blocks.NETHERRACK);
	}

	private static boolean embedInWall(WorldGenLevel world, BlockPos pos) {
		if (!world.getBlockState(pos).is(Blocks.NETHERRACK)) {
			return false;
		}
		for (Direction facing : Direction.values()) {
			if (facing.getAxis() == Direction.Axis.Y && !world.getBlockState(pos.above()).is(Blocks.NETHERRACK)) {
				return false;
			}
			if (world.isEmptyBlock(pos.relative(facing))) {
				return true;
			}
		}
		return false;
	}
}
