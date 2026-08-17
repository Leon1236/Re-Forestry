package com.leon1236.reforestry.apiculture.genetics;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeJubilance;
import com.leon1236.reforestry.api.genetics.IGenome;

public enum DefaultBeeJubilance implements IBeeJubilance {
	INSTANCE;

	@Override
	public boolean isJubilant(IBeeSpecies species, IGenome genome, IBeeHousing housing) {
		return housing.temperature() == species.getTemperature() && housing.humidity() == species.getHumidity();
	}
}
