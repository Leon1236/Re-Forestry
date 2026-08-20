package com.leon1236.reforestry.core.tiles;

public interface IEnginePowerHandler extends IPowerHandler {
	long forceReceiveEnergy(long maxReceive, boolean simulate);
}
