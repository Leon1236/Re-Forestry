package com.leon1236.reforestry.api.lepidopterology;

import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;

public interface ILepidopteristTracker extends IBreedingTracker {
	void registerCatch(IButterfly butterfly);
}
