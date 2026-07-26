package com.leon1236.reforestry.core.genetics.mutations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.mutations.IMutationCondition;

public record Mutation(Identifier typeId, Identifier firstParent, Identifier secondParent, Identifier result,
		float baseChance, List<IMutationCondition> conditions, Map<IChromosome<?>, IAllele> specialAlleles)
		implements IMutation {
	@Override
	public float getChance() {
		return baseChance;
	}

	@Override
	public List<IMutationCondition> getConditions() {
		return conditions;
	}

	@Override
	public List<Component> getSpecialConditions() {
		List<Component> special = new ArrayList<>(conditions.size());
		for (IMutationCondition condition : conditions) {
			special.add(condition.getDescription());
		}
		return special;
	}

	@Override
	public boolean isPartner(Identifier speciesId) {
		return firstParent.equals(speciesId) || secondParent.equals(speciesId);
	}

	@Override
	public Identifier getPartner(Identifier speciesId) {
		if (firstParent.equals(speciesId)) {
			return secondParent;
		}
		if (secondParent.equals(speciesId)) {
			return firstParent;
		}
		throw new IllegalArgumentException("Tried to get partner for species that is not part of this mutation.");
	}

	@Override
	public boolean isSecret() {
		return false;
	}

	public float getChance(Level level, BlockPos pos, IGenome first, IGenome second) {
		float chance = baseChance;
		for (IMutationCondition condition : conditions) {
			chance = condition.modifyChance(level, pos, first, second, chance);
			if (chance <= 0f) {
				return 0f;
			}
		}
		return chance;
	}
}
