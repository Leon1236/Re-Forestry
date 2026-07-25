package com.leon1236.reforestry.arboriculture.features;

import net.minecraft.network.codec.ByteBufCodecs;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.modules.features.FeatureDataComponent;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ArboricultureDataComponents {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("arboriculture"));

    public static final FeatureDataComponent<IGenome> TREE_GENOME = REGISTRY.dataComponent("tree_genome",
            builder -> builder.persistent(TreeChromosomes.KARYOTYPE.genomeCodec())
                    .networkSynchronized(ByteBufCodecs.fromCodecWithRegistries(TreeChromosomes.KARYOTYPE.genomeCodec())));

    public static void init() {
    }
}
