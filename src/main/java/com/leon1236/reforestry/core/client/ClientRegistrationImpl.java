package com.leon1236.reforestry.core.client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.client.arboriculture.ILeafSprite;
import com.leon1236.reforestry.api.client.arboriculture.ILeafTint;
import com.leon1236.reforestry.api.client.genetics.IAnalyzerPlugin;
import com.leon1236.reforestry.api.client.plugin.IClientHelper;
import com.leon1236.reforestry.api.client.plugin.IClientRegistration;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.core.client.genetics.GeneticClientManager;
import com.leon1236.reforestry.lepidopterology.client.ButterflyClientManager;

public final class ClientRegistrationImpl implements IClientRegistration {
	private final Map<ILifeStage, Identifier> defaultBeeModels = new HashMap<>();
	private final Map<Identifier, ILeafSprite> leafSprites = new HashMap<>();
	private final Map<Identifier, ILeafTint> leafTints = new HashMap<>();

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
	}

	@Override
	public void setSaplingModel(Identifier speciesId, Identifier blockModel, Identifier itemModel) {
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
}
