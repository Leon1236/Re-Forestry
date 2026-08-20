package com.leon1236.reforestry.api.apiculture;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.IBreedingTracker;

public interface IApiaristTracker extends IBreedingTracker {
	void registerQueen(Identifier speciesId);

	int getQueenCount();

	void registerPrincess(Identifier speciesId);

	int getPrincessCount();

	void registerDrone(Identifier speciesId);

	int getDroneCount();
}
