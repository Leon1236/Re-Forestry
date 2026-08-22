package com.leon1236.reforestry.apiculture.client;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;

public final class HabitatLocatorClient {
	@Nullable
	private static BlockPos target;

	private HabitatLocatorClient() {
	}

	public static void setTarget(@Nullable BlockPos pos) {
		target = pos;
	}

	@Nullable
	public static BlockPos getTarget() {
		return target;
	}
}
