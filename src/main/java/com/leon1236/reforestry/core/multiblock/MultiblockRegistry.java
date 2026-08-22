package com.leon1236.reforestry.core.multiblock;

import com.leon1236.reforestry.ReForestry;

import com.google.common.collect.ImmutableSet;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MultiblockRegistry {

	private static final Map<LevelAccessor, MultiblockWorldRegistry> registries = new HashMap<>();

	public static void tickStart(LevelAccessor world) {
		if (registries.containsKey(world)) {
			MultiblockWorldRegistry registry = registries.get(world);
			registry.processMultiblockChanges();
			registry.tickStart();
		}
	}

	public static void onChunkLoaded(LevelAccessor world, int chunkX, int chunkZ) {
		if (registries.containsKey(world)) {
			registries.get(world).onChunkLoaded(chunkX, chunkZ);
		}
	}

	public static void onPartAdded(Level world, IMultiblockComponent part) {
		MultiblockWorldRegistry registry = getOrCreateRegistry(world);
		registry.onPartAdded(part);
	}

	public static void onPartRemovedFromWorld(Level world, IMultiblockComponent part) {
		if (registries.containsKey(world)) {
			registries.get(world).onPartRemovedFromWorld(part);
		}

	}

	public static void onWorldUnloaded(LevelAccessor world) {
		if (registries.containsKey(world)) {
			registries.get(world).onWorldUnloaded();
			registries.remove(world);
		}
	}

	public static void addDirtyController(LevelAccessor world, IMultiblockControllerInternal controller) {
		if (registries.containsKey(world)) {
			registries.get(world).addDirtyController(controller);
		} else {
			throw new IllegalArgumentException("Adding a dirty controller to a world that has no registered controllers!");
		}
	}

	public static void addDeadController(LevelAccessor world, IMultiblockControllerInternal controller) {
		if (registries.containsKey(world)) {
			registries.get(world).addDeadController(controller);
		} else {
			ReForestry.LOGGER.warn("Controller {} in world {} marked as dead, but that world is not tracked! Controller is being ignored.", controller.hashCode(), world);
		}
	}

	public static Set<IMultiblockControllerInternal> getControllersFromWorld(LevelAccessor world) {
		if (registries.containsKey(world)) {
			return registries.get(world).getControllers();
		}
		return ImmutableSet.of();
	}

	private static MultiblockWorldRegistry getOrCreateRegistry(Level world) {
		if (registries.containsKey(world)) {
			return registries.get(world);
		} else {
			MultiblockWorldRegistry newRegistry = new MultiblockWorldRegistry(world);
			registries.put(world, newRegistry);
			return newRegistry;
		}
	}

}
