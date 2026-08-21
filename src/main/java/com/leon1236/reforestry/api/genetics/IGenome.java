package com.leon1236.reforestry.api.genetics;

import java.util.Map;

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

    @SuppressWarnings("unchecked")
    default IGenome copyWith(Map<IChromosome<?>, IAllele> alleles) {
        IGenomeBuilder builder = karyotype().genomeBuilder();
        for (Map.Entry<IChromosome<?>, AllelePair<?>> entry : chromosomes().entrySet()) {
            IChromosome<IAllele> chromosome = (IChromosome<IAllele>) entry.getKey();
            AllelePair<?> pair = entry.getValue();
            builder.setActive(chromosome, (IAllele) pair.active());
            builder.setInactive(chromosome, (IAllele) pair.inactive());
        }
        for (Map.Entry<IChromosome<?>, IAllele> entry : alleles.entrySet()) {
            builder.set((IChromosome<IAllele>) entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    @SuppressWarnings("unchecked")
    default IGenome copyWithPairs(Map<IChromosome<?>, AllelePair<?>> allelePairs) {
        IGenomeBuilder builder = karyotype().genomeBuilder();
        for (Map.Entry<IChromosome<?>, AllelePair<?>> entry : chromosomes().entrySet()) {
            IChromosome<IAllele> chromosome = (IChromosome<IAllele>) entry.getKey();
            AllelePair<?> pair = entry.getValue();
            builder.setActive(chromosome, (IAllele) pair.active());
            builder.setInactive(chromosome, (IAllele) pair.inactive());
        }
        for (Map.Entry<IChromosome<?>, AllelePair<?>> entry : allelePairs.entrySet()) {
            builder.setPair((IChromosome<IAllele>) entry.getKey(), (AllelePair<IAllele>) entry.getValue());
        }
        return builder.build();
    }
}
