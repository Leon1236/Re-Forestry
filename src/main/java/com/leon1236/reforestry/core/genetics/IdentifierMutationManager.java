package com.leon1236.reforestry.core.genetics;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;

import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.api.genetics.IMutationManager;

public final class IdentifierMutationManager implements IMutationManager {
	private final List<IMutation> allMutations;

	public IdentifierMutationManager(List<? extends IMutation> mutations) {
		this.allMutations = List.copyOf(mutations);
	}

	@Override
	public List<IMutation> getMutationsFrom(Identifier speciesId) {
		List<IMutation> found = new ArrayList<>();
		for (IMutation mutation : allMutations) {
			if (mutation.isPartner(speciesId)) {
				found.add(mutation);
			}
		}
		return found;
	}

	@Override
	public List<IMutation> getMutationsInto(Identifier speciesId) {
		List<IMutation> found = new ArrayList<>();
		for (IMutation mutation : allMutations) {
			if (mutation.result().equals(speciesId)) {
				found.add(mutation);
			}
		}
		return found;
	}

	@Override
	public List<IMutation> getCombinations(Identifier firstParent, Identifier secondParent) {
		List<IMutation> found = new ArrayList<>();
		for (IMutation mutation : allMutations) {
			if (mutation.isPartner(firstParent) && mutation.getPartner(firstParent).equals(secondParent)) {
				found.add(mutation);
			}
		}
		return found;
	}

	@Override
	public List<IMutation> getCombinationsShuffled(Identifier firstParent, Identifier secondParent, RandomSource rand) {
		List<IMutation> combinations = new ArrayList<>(getCombinations(firstParent, secondParent));
		Util.shuffle(combinations, rand);
		return combinations;
	}

	@Override
	public List<IMutation> getAllMutations() {
		return allMutations;
	}
}
