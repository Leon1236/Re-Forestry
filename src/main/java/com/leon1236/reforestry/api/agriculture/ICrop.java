package com.leon1236.reforestry.api.agriculture;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

public interface ICrop {
	@Nullable
	List<ItemStack> harvest();

	BlockPos getPosition();
}
