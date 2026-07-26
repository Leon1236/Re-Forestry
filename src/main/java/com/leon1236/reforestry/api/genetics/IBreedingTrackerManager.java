package com.leon1236.reforestry.api.genetics;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.LevelAccessor;

public interface IBreedingTrackerManager {
	<T extends IBreedingTracker> T getTracker(Identifier speciesTypeId, LevelAccessor level, @Nullable GameProfile profile);
}
