package com.leon1236.reforestry.core.genetics.root;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;

import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IBreedingTrackerManager;
import com.leon1236.reforestry.core.genetics.BreedingTracker;

public enum BreedingTrackerManager implements IBreedingTrackerManager {
	INSTANCE;

	@Override
	@SuppressWarnings("unchecked")
	public <T extends IBreedingTracker> T getTracker(Identifier speciesTypeId, LevelAccessor level,
			@Nullable GameProfile profile) {
		if (!(level instanceof ServerLevel serverLevel)) {
			return (T) new BreedingTracker(speciesTypeId);
		}

		MinecraftServer server = serverLevel.getServer();
		BreedingTracker tracker = server.getDataStorage().computeIfAbsent(BreedingTracker.typeFor(speciesTypeId, profile));
		tracker.setUsername(profile);
		tracker.setLevel(serverLevel);
		return (T) tracker;
	}
}
