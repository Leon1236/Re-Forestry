package com.leon1236.reforestry.api.apiculture;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.IBeeSpecies;

public interface IBeeJubilance {
	boolean isJubilant(IBeeSpecies species, IGenome genome, IBeeHousing housing);
}
