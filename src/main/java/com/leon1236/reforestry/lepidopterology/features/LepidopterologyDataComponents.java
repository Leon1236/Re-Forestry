package com.leon1236.reforestry.lepidopterology.features;

import net.minecraft.network.codec.ByteBufCodecs;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes;
import com.leon1236.reforestry.modules.features.FeatureDataComponent;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class LepidopterologyDataComponents {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("lepidopterology"));

	public static final FeatureDataComponent<IGenome> BUTTERFLY_GENOME = REGISTRY.dataComponent("butterfly_genome",
			builder -> builder.persistent(ButterflyChromosomes.KARYOTYPE.genomeCodec())
					.networkSynchronized(ByteBufCodecs.fromCodecWithRegistries(ButterflyChromosomes.KARYOTYPE.genomeCodec())));

	public static final FeatureDataComponent<IGenome> BUTTERFLY_MATE_GENOME = REGISTRY.dataComponent("butterfly_mate_genome",
			builder -> builder.persistent(ButterflyChromosomes.KARYOTYPE.genomeCodec())
					.networkSynchronized(ByteBufCodecs.fromCodecWithRegistries(ButterflyChromosomes.KARYOTYPE.genomeCodec())));

	public static void init() {
	}
}
