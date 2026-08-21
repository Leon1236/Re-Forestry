package com.leon1236.reforestry.api.client.plugin;

import java.util.function.IntUnaryOperator;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.client.arboriculture.ILeafSprite;
import com.leon1236.reforestry.api.client.arboriculture.ILeafTint;
import com.leon1236.reforestry.api.client.genetics.IAnalyzerPlugin;
import com.leon1236.reforestry.api.genetics.ILifeStage;

public interface IClientRegistration {
	IClientHelper getHelper();

	void setDefaultBeeModel(ILifeStage stage, Identifier modelLocation);

	void setCustomBeeModel(Identifier speciesId, ILifeStage stage, Identifier model);

	void setSaplingModel(Identifier speciesId, Identifier blockModel, Identifier itemModel);

	void setLeafSprite(Identifier speciesId, ILeafSprite sprite);

	void setLeafTint(Identifier speciesId, ILeafTint tint);

	void setButterflySprites(Identifier speciesId, Identifier itemTexture, Identifier entityTexture);

	void setAnalyzerPlugin(Identifier speciesTypeId, IAnalyzerPlugin plugin);
}
