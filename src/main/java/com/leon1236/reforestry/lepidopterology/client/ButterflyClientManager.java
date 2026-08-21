package com.leon1236.reforestry.lepidopterology.client;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

public final class ButterflyClientManager {
	public static final ButterflyClientManager INSTANCE = new ButterflyClientManager();

	private final Map<Identifier, Identifier> itemTextures = new HashMap<>();
	private final Map<Identifier, Identifier> entityTextures = new HashMap<>();

	private ButterflyClientManager() {
	}

	public void setSprites(Identifier speciesId, Identifier itemTexture, Identifier entityTexture) {
		itemTextures.put(speciesId, itemTexture);
		entityTextures.put(speciesId, entityTexture);
	}

	@Nullable
	public Identifier getItemTexture(Identifier speciesId) {
		return itemTextures.get(speciesId);
	}

	@Nullable
	public Identifier getEntityTexture(Identifier speciesId) {
		return entityTextures.get(speciesId);
	}
}
