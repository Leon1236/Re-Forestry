package com.leon1236.reforestry.apiculture.multiblock;

import com.leon1236.reforestry.core.multiblock.IMultiblockSizeLimits;

public final class AlvearyMultiblockSizeLimits implements IMultiblockSizeLimits {
	public static final AlvearyMultiblockSizeLimits INSTANCE = new AlvearyMultiblockSizeLimits();

	private AlvearyMultiblockSizeLimits() {
	}

	@Override
	public int getMinimumNumberOfBlocksForAssembledMachine() {
		return 27;
	}

	@Override
	public int getMaximumXSize() {
		return 3;
	}

	@Override
	public int getMaximumZSize() {
		return 3;
	}

	@Override
	public int getMaximumYSize() {
		return 3;
	}

	@Override
	public int getMinimumXSize() {
		return 3;
	}

	@Override
	public int getMinimumYSize() {
		return 3;
	}

	@Override
	public int getMinimumZSize() {
		return 3;
	}
}
