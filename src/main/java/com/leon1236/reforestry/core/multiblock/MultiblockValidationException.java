package com.leon1236.reforestry.core.multiblock;

import net.minecraft.core.BlockPos;

import org.jetbrains.annotations.Nullable;

/**
 * An exception thrown when trying to validate a multiblock. Requires a string describing why the multiblock
 * could not assemble.
 *
 * @author Erogenous Beef
 */
public class MultiblockValidationException extends Exception {

	@Nullable
	private BlockPos position;

	public MultiblockValidationException(String reason) {
		super(reason);
	}

	public MultiblockValidationException(String reason, BlockPos position) {
		super(reason);
		this.position = position;
	}

	@Nullable
	public BlockPos getPosition() {
		return this.position;
	}
}
