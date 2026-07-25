package com.leon1236.reforestry.arboriculture.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;

public record ForestryTreeFeatureConfig(IGenome genome) implements FeatureConfiguration {
    public static final Codec<ForestryTreeFeatureConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            TreeChromosomes.KARYOTYPE.genomeCodec().fieldOf("genome").forGetter(ForestryTreeFeatureConfig::genome)
    ).apply(builder, ForestryTreeFeatureConfig::new));
}
