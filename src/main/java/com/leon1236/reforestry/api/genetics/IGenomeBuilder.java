package com.leon1236.reforestry.api.genetics;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public interface IGenomeBuilder {
    <A extends IAllele> IGenomeBuilder set(IChromosome<A> chromosome, A allele);

    <A extends IAllele> IGenomeBuilder setActive(IChromosome<A> chromosome, A allele);

    <A extends IAllele> IGenomeBuilder setInactive(IChromosome<A> chromosome, A allele);

    default <A extends IAllele> IGenomeBuilder setPair(IChromosome<A> chromosome, AllelePair<A> pair) {
        return setActive(chromosome, pair.active()).setInactive(chromosome, pair.inactive());
    }

    IGenome build();
}
