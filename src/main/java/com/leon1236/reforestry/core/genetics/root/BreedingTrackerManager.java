package com.leon1236.reforestry.core.genetics.root;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IBreedingTrackerManager;
import com.leon1236.reforestry.apiculture.genetics.ApiaristTracker;
import com.leon1236.reforestry.core.genetics.BreedingTracker;

public enum BreedingTrackerManager implements IBreedingTrackerManager {
	INSTANCE;

	@Nullable
	private static volatile ClientAccess clientAccess;

	public static void setClientAccess(ClientAccess access) {
		clientAccess = access;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends IBreedingTracker> T getTracker(Identifier speciesTypeId, LevelAccessor level,
			@Nullable GameProfile profile) {
		if (level instanceof Level world && world.isClientSide()) {
			ClientAccess access = clientAccess;
			if (access != null) {
				return (T) access.getTracker(speciesTypeId);
			}
			return (T) createTracker(speciesTypeId);
		}

		if (!(level instanceof ServerLevel serverLevel)) {
			return (T) createTracker(speciesTypeId);
		}

		MinecraftServer server = serverLevel.getServer();
		BreedingTracker tracker;
		if (ForestrySpeciesTypes.BEE.equals(speciesTypeId)) {
			tracker = server.getDataStorage().computeIfAbsent(ApiaristTracker.typeFor(profile));
		} else {
			tracker = server.getDataStorage().computeIfAbsent(BreedingTracker.typeFor(speciesTypeId, profile));
		}
		tracker.setUsername(profile);
		tracker.setLevel(serverLevel);
		return (T) tracker;
	}

	public static BreedingTracker createTracker(Identifier speciesTypeId) {
		if (ForestrySpeciesTypes.BEE.equals(speciesTypeId)) {
			return new ApiaristTracker();
		}
		return new BreedingTracker(speciesTypeId);
	}

	public interface ClientAccess {
		IBreedingTracker getTracker(Identifier speciesTypeId);
	}
}
