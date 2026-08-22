package com.leon1236.reforestry.api.agriculture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public interface IExtentCache {
	int getExtents(Direction direction, BlockPos pos);

	void setExtents(Direction direction, BlockPos pos, int extend);

	void cleanExtents(Direction direction);

	default int getValidExtent(Direction direction, BlockPos pos, int maxExtend) {
		int lastExtents = getExtents(direction, pos);
		if (lastExtents > maxExtend) {
			lastExtents = 0;
		}
		return lastExtents;
	}

	default BlockPos getValidPosition(Direction direction, BlockPos pos, int maxExtend, BlockPos baseLocation) {
		int extent = getValidExtent(direction, pos, maxExtend);
		return baseLocation.relative(direction, extent);
	}

	default int increaseExtent(Direction direction, BlockPos pos, int maxExtend) {
		int validExtent = getValidExtent(direction, pos, maxExtend);
		setExtents(direction, pos, ++validExtent);
		return validExtent;
	}
}
