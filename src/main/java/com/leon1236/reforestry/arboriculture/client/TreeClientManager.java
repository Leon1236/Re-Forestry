package com.leon1236.reforestry.arboriculture.client;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.ForestryTreeSpecies;
import com.leon1236.reforestry.api.arboriculture.ITreeSpecies;
import com.leon1236.reforestry.api.client.arboriculture.ILeafSprite;
import com.leon1236.reforestry.api.client.arboriculture.ILeafTint;

public final class TreeClientManager {
	public static final TreeClientManager INSTANCE = new TreeClientManager();

	private final Map<Identifier, ILeafSprite> leafSprites = new HashMap<>();
	private final Map<Identifier, ILeafTint> leafTints = new HashMap<>();
	private final Map<Identifier, SaplingModels> saplingModels = new HashMap<>();

	private record SaplingModels(Identifier blockModel, Identifier itemModel) {
	}

	private TreeClientManager() {
	}

	public void install(Map<Identifier, ILeafSprite> sprites, Map<Identifier, ILeafTint> tints,
			Map<Identifier, SaplingModelPair> models) {
		leafSprites.clear();
		leafTints.clear();
		saplingModels.clear();
		leafSprites.putAll(sprites);
		leafTints.putAll(tints);
		for (var entry : models.entrySet()) {
			saplingModels.put(entry.getKey(),
					new SaplingModels(entry.getValue().blockModel(), entry.getValue().itemModel()));
		}
	}

	@Nullable
	public ILeafSprite getLeafSprite(@Nullable ITreeSpecies species) {
		return species == null ? null : leafSprites.get(species.id());
	}

	public Collection<ILeafSprite> getAllLeafSprites() {
		return new HashSet<>(leafSprites.values());
	}

	public ILeafTint getTint(@Nullable ITreeSpecies species) {
		if (species == null) {
			return biomeColor -> biomeColor;
		}
		ILeafTint tint = leafTints.get(species.id());
		return tint != null ? tint : new EscritoireLeafTint(species.getEscritoireColor());
	}

	public SaplingModelPair getSaplingModels(ITreeSpecies species) {
		SaplingModels models = saplingModels.get(species.id());
		if (models != null) {
			return new SaplingModelPair(models.blockModel(), models.itemModel());
		}
		SaplingModels oak = saplingModels.get(ForestryTreeSpecies.OAK);
		if (oak != null) {
			return new SaplingModelPair(oak.blockModel(), oak.itemModel());
		}
		return new SaplingModelPair(ReForestry.id("block/oak_sapling"), ReForestry.id("item/oak_sapling"));
	}

	public record SaplingModelPair(Identifier blockModel, Identifier itemModel) {
	}
}
