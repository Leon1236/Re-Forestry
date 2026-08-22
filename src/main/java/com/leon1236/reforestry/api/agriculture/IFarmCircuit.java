package com.leon1236.reforestry.api.agriculture;

import com.leon1236.reforestry.api.circuits.ICircuit;

public interface IFarmCircuit extends ICircuit {
	IFarmType getProperties();

	boolean isManual();
}
