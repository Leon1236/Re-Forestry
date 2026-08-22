package com.leon1236.reforestry.extratrees.genetics;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.mutations.IMutationCondition;

public final class MutationConditionMinHeight implements IMutationCondition {
	private final int minY;

	public MutationConditionMinHeight(int minY) {
		this.minY = minY;
	}

	@Override
	public float modifyChance(Level level, BlockPos pos, IGenome first, IGenome second, float currentChance) {
		if (pos.getY() < minY) {
			return 0.0f;
		}
		return currentChance;
	}

	@Override
	public Component getDescription() {
		return Component.translatable("for.mutation.condition.minheight", minY);
	}
}
