package com.leon1236.reforestry.core.client.genetics;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.client.genetics.IAnalyzerPlugin;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.apiculture.client.BeeAnalyzerPlugin;
import com.leon1236.reforestry.arboriculture.client.TreeAnalyzerPlugin;

public final class GeneticClientManager {
	public static final GeneticClientManager INSTANCE = new GeneticClientManager();

	private final Map<Identifier, IAnalyzerPlugin> plugins = new HashMap<>();

	private GeneticClientManager() {
	}

	public void bootstrap() {
		plugins.put(ForestrySpeciesTypes.BEE, new BeeAnalyzerPlugin());
		plugins.put(ForestrySpeciesTypes.TREE, new TreeAnalyzerPlugin());
	}

	public void setAnalyzerPlugin(Identifier speciesTypeId, IAnalyzerPlugin plugin) {
		plugins.put(speciesTypeId, plugin);
	}

	@Nullable
	public IAnalyzerPlugin getAnalyzerPlugin(Identifier speciesTypeId) {
		return plugins.get(speciesTypeId);
	}
}
