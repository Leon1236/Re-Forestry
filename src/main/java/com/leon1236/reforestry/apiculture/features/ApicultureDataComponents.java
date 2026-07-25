package com.leon1236.reforestry.apiculture.features;

import com.mojang.serialization.Codec;

import net.minecraft.network.codec.ByteBufCodecs;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.modules.features.FeatureDataComponent;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ApicultureDataComponents {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("apiculture"));

    public static final FeatureDataComponent<IGenome> BEE_GENOME = REGISTRY.dataComponent("bee_genome",
            builder -> builder.persistent(BeeChromosomes.KARYOTYPE.genomeCodec())
                    .networkSynchronized(ByteBufCodecs.fromCodecWithRegistries(BeeChromosomes.KARYOTYPE.genomeCodec())));

    public static final FeatureDataComponent<IGenome> BEE_MATE_GENOME = REGISTRY.dataComponent("bee_mate_genome",
            builder -> builder.persistent(BeeChromosomes.KARYOTYPE.genomeCodec())
                    .networkSynchronized(ByteBufCodecs.fromCodecWithRegistries(BeeChromosomes.KARYOTYPE.genomeCodec())));

    public static final FeatureDataComponent<Integer> BEE_LIFE_USED = REGISTRY.dataComponent("bee_life_used",
            builder -> builder.persistent(Codec.INT)
                    .networkSynchronized(ByteBufCodecs.VAR_INT));

    public static void init() {
    }
}
