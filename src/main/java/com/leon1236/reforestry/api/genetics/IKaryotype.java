package com.leon1236.reforestry.api.genetics;

import java.util.Optional;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public interface IKaryotype {
    Identifier id();

    IChromosome<?> speciesChromosome();

    ImmutableSet<IChromosome<?>> chromosomes();

    Optional<IChromosome<?>> getChromosome(Identifier chromosomeId);

    IAllele getDefault(IChromosome<?> chromosome);

    boolean isWeaklyInherited(IChromosome<?> chromosome);

    Codec<IGenome> genomeCodec();

    IGenomeBuilder genomeBuilder();
}
