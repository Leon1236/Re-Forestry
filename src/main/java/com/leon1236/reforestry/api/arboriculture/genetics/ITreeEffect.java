package com.leon1236.reforestry.api.arboriculture.genetics;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.apiculture.genetics.IEffect;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface ITreeEffect extends IEffect, IRegistryAlleleValue {
	boolean isDominant();

	@Override
	default boolean isCombinable() {
		return false;
	}

	@Override
	default IEffectData validateStorage(IEffectData storedData) {
		return storedData;
	}

	default IEffectData doEffect(IGenome genome, IEffectData storedData, Level level, BlockPos pos) {
		return storedData;
	}

	default void doAnimationEffect(IGenome genome, Level level, BlockPos pos, RandomSource rand) {
	}
}
