package com.leon1236.reforestry.core.multiblock;

public interface IMultiblockSizeLimits {

	int getMinimumNumberOfBlocksForAssembledMachine();

	int getMaximumXSize();

	int getMaximumZSize();

	int getMaximumYSize();

	int getMinimumXSize();

	int getMinimumYSize();

	int getMinimumZSize();
}
