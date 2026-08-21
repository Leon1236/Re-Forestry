package com.leon1236.reforestry.core;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.api.agriculture.IFarmingManager;

public enum FakeFarmingManager implements IFarmingManager {
	INSTANCE;

	@Override
	public boolean isLoaded() {
		return false;
	}

	@Override
	public int getFertilizeValue(ItemStack stack) {
		return 0;
	}

	@Nullable
	@Override
	public IFarmType getFarmType(Identifier id) {
		return null;
	}
}
