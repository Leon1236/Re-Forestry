package com.leon1236.reforestry.api.core;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface ILocationProvider {
	BlockPos getCoordinates();

	@Nullable
	Level getWorldObj();
}
