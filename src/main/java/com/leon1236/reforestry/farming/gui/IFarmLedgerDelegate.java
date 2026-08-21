package com.leon1236.reforestry.farming.gui;

public interface IFarmLedgerDelegate {
	float getHydrationModifier();

	float getHydrationTempModifier();

	float getHydrationHumidModifier();

	float getHydrationRainfallModifier();

	double getDrought();
}
