package com.leon1236.reforestry.core.client;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.client.arboriculture.ILeafSprite;
import com.leon1236.reforestry.api.client.arboriculture.ILeafTint;
import com.leon1236.reforestry.api.client.genetics.IAnalyzerPlugin;
import com.leon1236.reforestry.api.client.plugin.IClientHelper;
import com.leon1236.reforestry.api.client.plugin.IClientRegistration;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.arboriculture.client.TreeClientManager;
import com.leon1236.reforestry.core.client.genetics.GeneticClientManager;
import com.leon1236.reforestry.lepidopterology.client.ButterflyClientManager;

public final class ClientRegistrationImpl implements IClientRegistration {
	private final IdentityHashMap<ILifeStage, Identifier> defaultBeeModels = new IdentityHashMap<>();
	private final IdentityHashMap<ILifeStage, Map<Identifier, Identifier>> customBeeModels = new IdentityHashMap<>();
	private final Map<Identifier, ILeafSprite> leafSprites = new HashMap<>();
	private final Map<Identifier, ILeafTint> leafTints = new HashMap<>();
	private final Map<Identifier, TreeClientManager.SaplingModelPair> saplingModels = new HashMap<>();

	@Override
	public IClientHelper getHelper() {
		return ClientHelperImpl.INSTANCE;
	}

	@Override
	public void setDefaultBeeModel(ILifeStage stage, Identifier modelLocation) {
		defaultBeeModels.put(stage, modelLocation);
	}

	@Override
	public void setCustomBeeModel(Identifier speciesId, ILifeStage stage, Identifier model) {
		customBeeModels.computeIfAbsent(stage, ignored -> new HashMap<>()).put(speciesId, model);
	}

	@Override
	public void setSaplingModel(Identifier speciesId, Identifier blockModel, Identifier itemModel) {
		saplingModels.put(speciesId, new TreeClientManager.SaplingModelPair(blockModel, itemModel));
	}

	@Override
	public void setLeafSprite(Identifier speciesId, ILeafSprite sprite) {
		leafSprites.put(speciesId, sprite);
	}

	@Override
	public void setLeafTint(Identifier speciesId, ILeafTint tint) {
		leafTints.put(speciesId, tint);
	}

	@Override
	public void setButterflySprites(Identifier speciesId, Identifier itemTexture, Identifier entityTexture) {
		ButterflyClientManager.INSTANCE.setSprites(speciesId, itemTexture, entityTexture);
	}

	@Override
	public void setAnalyzerPlugin(Identifier speciesTypeId, IAnalyzerPlugin plugin) {
		GeneticClientManager.INSTANCE.setAnalyzerPlugin(speciesTypeId, plugin);
	}

	public IdentityHashMap<ILifeStage, Identifier> getDefaultBeeModels() {
		return defaultBeeModels;
	}

	public IdentityHashMap<ILifeStage, Map<Identifier, Identifier>> getCustomBeeModels() {
		return customBeeModels;
	}

	public Map<Identifier, ILeafSprite> getLeafSprites() {
		return leafSprites;
	}

	public Map<Identifier, ILeafTint> getLeafTints() {
		return leafTints;
	}

	public Map<Identifier, TreeClientManager.SaplingModelPair> getSaplingModels() {
		return saplingModels;
	}
}
