package com.leon1236.reforestry.api.multiblock;

import java.util.Collection;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;

public interface IMultiblockController {
	boolean isAssembled();

	void reassemble();

	@Nullable
	String getLastValidationError();

	Collection<IMultiblockComponent> getComponents();

	default void onDestroyed(BlockPos lastPos) {
	}
}
