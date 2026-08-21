package com.leon1236.reforestry.gendustry.recipe.cache;

import java.util.function.Consumer;

import com.google.common.collect.ImmutableSet;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.RecipeManager;

public final class RecipeCacheRegistry {
	private final ImmutableSet<IRecipeCache> caches;

	public RecipeCacheRegistry(Consumer<Consumer<IRecipeCache>> registerCaches) {
		ImmutableSet.Builder<IRecipeCache> builder = ImmutableSet.builder();
		registerCaches.accept(builder::add);
		this.caches = builder.build();

		ServerLifecycleEvents.SERVER_STARTED.register(this::reload);
		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> {
			if (success) {
				reload(server);
			}
		});
		ServerLifecycleEvents.SERVER_STOPPING.register(server -> unload());
	}

	private void reload(MinecraftServer server) {
		RecipeManager recipes = server.getRecipeManager();
		for (IRecipeCache cache : this.caches) {
			cache.reload(recipes);
		}
	}

	private void unload() {
		for (IRecipeCache cache : this.caches) {
			cache.unload();
		}
	}
}
