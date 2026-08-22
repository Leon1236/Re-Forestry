package com.leon1236.reforestry.core.book.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.book.BookLoader;

@Environment(EnvType.CLIENT)
public final class ForesterBookClient {
	private static final Identifier RELOAD_ID = ReForestry.id("forester_book");

	private ForesterBookClient() {
	}

	public static void register() {
		ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public Identifier getFabricId() {
				return RELOAD_ID;
			}

			@Override
			public void onResourceManagerReload(net.minecraft.server.packs.resources.ResourceManager manager) {
				BookLoader.get().reload(manager);
				Minecraft client = Minecraft.getInstance();
				BookRecipeCache.rebuild(client.level);
			}
		});
	}

	public static void openBook() {
		Minecraft client = Minecraft.getInstance();
		BookRecipeCache.rebuild(client.level);
		client.gui.setScreen(new ScreenForesterBookCategories(true));
	}
}
