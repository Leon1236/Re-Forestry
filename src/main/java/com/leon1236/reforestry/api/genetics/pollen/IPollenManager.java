package com.leon1236.reforestry.api.genetics.pollen;

import java.util.Collection;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;

public interface IPollenManager {
	boolean canPollinate(Level level, BlockPos pos);

	@Nullable
	IPollen getPollen(Level level, BlockPos pos);

	@Nullable
	IPollen getPollenOfType(Level level, BlockPos pos, Set<Identifier> pollenTypes);

	@Nullable
	default IPollen getPollenOfType(Level level, BlockPos pos, Identifier pollenType) {
		return getPollenOfType(level, pos, Set.of(pollenType));
	}

	@Nullable
	IPollenType getPollenType(Identifier id);

	Collection<IPollenType> getAllPollenTypes();
}
