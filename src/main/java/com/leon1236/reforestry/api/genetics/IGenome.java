package com.leon1236.reforestry.api.genetics;

import com.google.common.collect.ImmutableMap;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public interface IGenome {
    IKaryotype karyotype();

    <A extends IAllele> A getActiveAllele(IChromosome<A> chromosome);

    <A extends IAllele> A getInactiveAllele(IChromosome<A> chromosome);

    ImmutableMap<IChromosome<?>, AllelePair<?>> chromosomes();

    default boolean isSameAlleles(IGenome other) {
        return chromosomes().equals(other.chromosomes());
    }
}
