package com.leon1236.reforestry.farming.farmlogic;

public enum FarmingStage {
	CULTIVATE, HARVEST;

	public FarmingStage next() {
		if (this == CULTIVATE) {
			return HARVEST;
		}
		return CULTIVATE;
	}
}
