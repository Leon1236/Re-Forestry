package com.leon1236.reforestry.gendustry.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.gendustry.item.data.GeneSampleInfo;
import com.leon1236.reforestry.gendustry.item.data.GeneticTemplateInfo;
import com.leon1236.reforestry.modules.features.FeatureDataComponent;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class GendustryDataComponents {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("gendustry"));

	public static final FeatureDataComponent<GeneSampleInfo> GENE_SAMPLE = REGISTRY.dataComponent("gene_sample",
			builder -> builder.persistent(GeneSampleInfo.CODEC)
					.networkSynchronized(GeneSampleInfo.STREAM_CODEC));

	public static final FeatureDataComponent<GeneticTemplateInfo> GENETIC_TEMPLATE = REGISTRY.dataComponent("genetic_template",
			builder -> builder.persistent(GeneticTemplateInfo.CODEC)
					.networkSynchronized(GeneticTemplateInfo.STREAM_CODEC));

	public static void init() {
	}
}
