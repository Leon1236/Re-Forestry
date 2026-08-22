package com.leon1236.reforestry.extra_bees.hives;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

import com.leon1236.reforestry.api.apiculture.hives.IHiveGen;

public class HiveGenRock implements IHiveGen {
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
		if (!isStoneReplaceable(world.getBlockState(pos))) {
			return false;
		}
		return hasAirOnOneSide(world, pos);
	}

	@Override
	public boolean canReplace(BlockState blockState, WorldGenLevel world, BlockPos pos) {
		return isStoneReplaceable(blockState);
	}

	private static boolean isStoneReplaceable(BlockState state) {
		return state.is(BlockTags.STONE_ORE_REPLACEABLES);
	}

	private static boolean hasAirOnOneSide(WorldGenLevel world, BlockPos pos) {
		for (Direction facing : Direction.Plane.HORIZONTAL) {
			BlockPos sidePos = pos.relative(facing);
			if (isChunkLoaded(world, sidePos) && world.isEmptyBlock(sidePos)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isChunkLoaded(WorldGenLevel world, BlockPos pos) {
		return world.getChunkSource().hasChunk(
				SectionPos.blockToSectionCoord(pos.getX()),
				SectionPos.blockToSectionCoord(pos.getZ()));
	}
}
