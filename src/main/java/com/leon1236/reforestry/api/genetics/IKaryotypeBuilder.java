package com.leon1236.reforestry.api.genetics;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public interface IKaryotypeBuilder {
    <A extends IAllele> IKaryotypeBuilder set(IChromosome<A> chromosome, A defaultAllele);

    IKaryotypeBuilder setSpecies(IChromosome<?> speciesChromosome);

    IKaryotypeBuilder setWeaklyInherited(IChromosome<?> chromosome);

    IKaryotype create(Identifier id);
}
