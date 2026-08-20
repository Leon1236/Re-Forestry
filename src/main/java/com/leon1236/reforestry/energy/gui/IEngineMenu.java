package com.leon1236.reforestry.energy.gui;

public interface IEngineMenu {
	int getCurrentOutput();

	int getHeat();

	int getEnergyStored();

	int getEnergyCapacity();

	int getErrorCount();

	short getErrorId(int index);

	String getHintKey();
}
