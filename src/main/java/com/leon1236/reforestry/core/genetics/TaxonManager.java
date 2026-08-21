package com.leon1236.reforestry.core.genetics;

import java.util.Map;

import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import com.leon1236.reforestry.api.IForestryApi;

public final class TaxonManager extends SimpleJsonResourceReloadListener<TaxonDefinition> {
	public TaxonManager() {
		super(TaxonDefinition.CODEC, FileToIdConverter.json("taxon"));
	}

	@Override
	protected void apply(Map<Identifier, TaxonDefinition> object, ResourceManager resourceManager,
			ProfilerFiller profiler) {
		if (IForestryApi.INSTANCE.getGeneticManager() instanceof GeneticManager manager) {
			manager.applyDatapackTaxa(object.values());
		}
	}
}
