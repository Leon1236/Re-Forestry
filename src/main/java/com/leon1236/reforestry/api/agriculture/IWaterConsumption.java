package com.leon1236.reforestry.api.agriculture;

@FunctionalInterface
public interface IWaterConsumption {
	int get(IFarmHousing housing, float hydrationModifier);
}
