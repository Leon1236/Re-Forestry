package com.leon1236.reforestry.core.genetics;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.IKaryotypeBuilder;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;

public final class KaryotypeBuilder implements IKaryotypeBuilder {
    private final ImmutableMap.Builder<IChromosome<?>, IAllele> defaults = ImmutableMap.builder();
    private final ImmutableSet.Builder<IChromosome<?>> weaklyInherited = ImmutableSet.builder();
    private IChromosome<?> speciesChromosome;

    public KaryotypeBuilder() {
    }

    @Override
    public <A extends IAllele> IKaryotypeBuilder set(IChromosome<A> chromosome, A defaultAllele) {
        defaults.put(chromosome, defaultAllele);
        return this;
    }

    @Override
    public IKaryotypeBuilder setSpecies(IChromosome<?> speciesChromosome) {
        this.speciesChromosome = speciesChromosome;
        return this;
    }

    @Override
    public IKaryotypeBuilder setWeaklyInherited(IChromosome<?> chromosome) {
        weaklyInherited.add(chromosome);
        return this;
    }

    @Override
    public IKaryotype create(Identifier id) {
        if (speciesChromosome == null) {
            throw new IllegalStateException("Karyotype " + id + " has no species chromosome set");
        }
        Karyotype karyotype = new Karyotype(id, speciesChromosome, defaults.build(), weaklyInherited.build());
        AlleleManager.INSTANCE.registerKaryotype(karyotype);
        return karyotype;
    }
}
