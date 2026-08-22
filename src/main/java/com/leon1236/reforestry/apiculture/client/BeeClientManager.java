package com.leon1236.reforestry.apiculture.client;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.ILifeStage;

public final class BeeClientManager {
	public static final BeeClientManager INSTANCE = new BeeClientManager();

	private final IdentityHashMap<ILifeStage, Identifier> defaultModels = new IdentityHashMap<>();
	private final IdentityHashMap<ILifeStage, Map<Identifier, Identifier>> customModels = new IdentityHashMap<>();

	private BeeClientManager() {
	}

	public void install(IdentityHashMap<ILifeStage, Identifier> defaults,
			IdentityHashMap<ILifeStage, Map<Identifier, Identifier>> customs) {
		defaultModels.clear();
		customModels.clear();
		defaultModels.putAll(defaults);
		customModels.putAll(customs);
	}

	public Identifier getModelLocation(ILifeStage stage, Identifier speciesId) {
		Map<Identifier, Identifier> customs = customModels.get(stage);
		if (customs != null) {
			Identifier custom = customs.get(speciesId);
			if (custom != null) {
				return custom;
			}
		}
		return Objects.requireNonNull(defaultModels.get(stage),
				"No default bee model registered for life stage " + stage.getSerializedName());
	}

	public Collection<Identifier> getAllModelLocations(ILifeStage stage) {
		LinkedHashSet<Identifier> locations = new LinkedHashSet<>();
		Identifier defaultModel = defaultModels.get(stage);
		if (defaultModel != null) {
			locations.add(defaultModel);
		}
		Map<Identifier, Identifier> customs = customModels.get(stage);
		if (customs != null) {
			locations.addAll(customs.values());
		}
		return locations;
	}
}
