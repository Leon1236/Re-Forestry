package com.leon1236.reforestry.arboriculture.villagers;

import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.core.genetics.GeneticsUtil;

final class TreeSpeciesComplexity {
	private TreeSpeciesComplexity() {
	}

	static int get(ITreeSpecies species) {
		return GeneticsUtil.getResearchComplexity(species);
	}
}
