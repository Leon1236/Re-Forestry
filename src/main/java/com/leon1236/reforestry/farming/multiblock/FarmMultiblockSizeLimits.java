package com.leon1236.reforestry.farming.multiblock;

import com.leon1236.reforestry.core.multiblock.IMultiblockSizeLimits;

public final class FarmMultiblockSizeLimits implements IMultiblockSizeLimits {
	public static final FarmMultiblockSizeLimits INSTANCE = new FarmMultiblockSizeLimits();

	private FarmMultiblockSizeLimits() {
	}

	@Override
	public int getMinimumNumberOfBlocksForAssembledMachine() {
		return 36;
	}

	@Override
	public int getMaximumXSize() {
		return 5;
	}

	@Override
	public int getMaximumZSize() {
		return 5;
	}

	@Override
	public int getMaximumYSize() {
		return 4;
	}

	@Override
	public int getMinimumXSize() {
		return 3;
	}

	@Override
	public int getMinimumYSize() {
		return 4;
	}

	@Override
	public int getMinimumZSize() {
		return 3;
	}
}
