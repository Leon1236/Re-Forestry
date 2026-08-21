package com.leon1236.reforestry.api.lepidopterology;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.api.climate.IClimateProvider;
import com.leon1236.reforestry.api.core.ILocationProvider;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;

public interface IButterflyNursery extends ILocationProvider, IClimateProvider {
	@Nullable
	IButterfly getCaterpillar();

	@Nullable
	IIndividual getNanny();

	void setCaterpillar(@Nullable IButterfly caterpillar);

	boolean canNurse(IButterfly caterpillar);
}
