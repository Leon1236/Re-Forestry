package com.leon1236.reforestry.core.genetics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;

public final class GeneticsUtil {
	private static final Identifier GIANT_SEQUOIA = ReForestry.id("tree_giant_sequoia");

	private GeneticsUtil() {
	}

	public static int getResearchComplexity(IBeeSpecies species) {
		return 1 + getGeneticAdvancement(ForestrySpeciesTypes.BEE, species.id(), new HashSet<>());
	}

	public static int getResearchComplexity(ITreeSpecies species) {
		if (GIANT_SEQUOIA.equals(species.id())) {
			return 10;
		}
		return 1 + getGeneticAdvancement(ForestrySpeciesTypes.TREE, species.id(), new HashSet<>());
	}

	private static int getGeneticAdvancement(Identifier typeId, Identifier species, Set<Identifier> exclude) {
		int highest = 0;
		exclude.add(species);
		for (Mutation mutation : mutationsFor(typeId)) {
			if (!mutation.result().equals(species)) {
				continue;
			}
			highest = highestParent(typeId, mutation.firstParent(), highest, exclude);
			highest = highestParent(typeId, mutation.secondParent(), highest, exclude);
		}
		return 1 + highest;
	}

	private static int highestParent(Identifier typeId, Identifier parent, int highest, Set<Identifier> exclude) {
		if (exclude.contains(parent)) {
			return highest;
		}
		return Math.max(getGeneticAdvancement(typeId, parent, exclude), highest);
	}

	private static List<Mutation> mutationsFor(Identifier typeId) {
		if (ForestrySpeciesTypes.TREE.equals(typeId)) {
			return ArboricultureGenetics.getAllMutations();
		}
		return ApicultureGenetics.getAllMutations();
	}
}
