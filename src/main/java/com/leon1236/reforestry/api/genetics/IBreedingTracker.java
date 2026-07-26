package com.leon1236.reforestry.api.genetics;

import java.util.Collection;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

public interface IBreedingTracker {
	int getSpeciesBred();

	void registerBirth(Identifier speciesId);

	void registerPickup(Identifier speciesId);

	void registerSpecies(Identifier speciesId);

	void registerMutation(IMutation mutation);

	boolean isDiscovered(IMutation mutation);

	boolean isDiscovered(Identifier speciesId);

	Collection<Identifier> getDiscoveredSpecies();

	void researchMutation(IMutation mutation);

	boolean isResearched(IMutation mutation);

	void syncToPlayer(Player player);
}
