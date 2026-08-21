package com.leon1236.reforestry.api.genetics;

import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;

public interface IMutationManager {
	List<IMutation> getMutationsFrom(Identifier speciesId);

	List<IMutation> getMutationsInto(Identifier speciesId);

	List<IMutation> getCombinations(Identifier firstParent, Identifier secondParent);

	List<IMutation> getCombinationsShuffled(Identifier firstParent, Identifier secondParent, RandomSource rand);

	List<IMutation> getAllMutations();
}
