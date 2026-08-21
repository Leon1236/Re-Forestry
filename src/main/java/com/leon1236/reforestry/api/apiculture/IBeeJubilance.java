package com.leon1236.reforestry.api.apiculture;

import com.leon1236.reforestry.api.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.api.genetics.IGenome;

public interface IBeeJubilance {
	boolean isJubilant(IBeeSpecies species, IGenome genome, IBeeHousing housing);
}
