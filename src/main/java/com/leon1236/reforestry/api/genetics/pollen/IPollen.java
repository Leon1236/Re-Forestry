package com.leon1236.reforestry.api.genetics.pollen;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;

public interface IPollen {
	IPollenType getType();

	IGenome getPollen();

	ItemStack createStack();

	default boolean tryPollinate(Level level, BlockPos pos) {
		return getType().tryPollinate(level, pos, getPollen(), level.getRandom());
	}
}
