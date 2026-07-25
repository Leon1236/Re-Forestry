package com.leon1236.reforestry.core.genetics;

import java.util.Optional;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.AllelePair;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

final class Karyotype implements IKaryotype {
    private final Identifier id;
    private final IChromosome<?> speciesChromosome;
    private final ImmutableMap<IChromosome<?>, IAllele> defaults;
    private final ImmutableSet<IChromosome<?>> chromosomes;
    private final ImmutableMap<Identifier, IChromosome<?>> chromosomesById;
    private final ImmutableSet<IChromosome<?>> weaklyInherited;
    private final Codec<IGenome> genomeCodec;

    Karyotype(Identifier id, IChromosome<?> speciesChromosome, ImmutableMap<IChromosome<?>, IAllele> defaults,
               ImmutableSet<IChromosome<?>> weaklyInherited) {
        this.id = id;
        this.speciesChromosome = speciesChromosome;
        this.defaults = defaults;
        this.weaklyInherited = weaklyInherited;

        ImmutableSet.Builder<IChromosome<?>> allChromosomes = ImmutableSet.builder();
        allChromosomes.add(speciesChromosome);
        allChromosomes.addAll(defaults.keySet());
        this.chromosomes = allChromosomes.build();

        ImmutableMap.Builder<Identifier, IChromosome<?>> byId = ImmutableMap.builder();
        for (IChromosome<?> chromosome : chromosomes) {
            byId.put(chromosome.id(), chromosome);
        }
        this.chromosomesById = byId.build();

        this.genomeCodec = Codec.unboundedMap(Identifier.CODEC, AllelePair.CODEC)
                .xmap(raw -> Genome.fromRaw(this, raw), Genome::toRaw);
    }

    @Override
    public Identifier id() {
        return id;
    }

    @Override
    public IChromosome<?> speciesChromosome() {
        return speciesChromosome;
    }

    @Override
    public ImmutableSet<IChromosome<?>> chromosomes() {
        return chromosomes;
    }

    @Override
    public Optional<IChromosome<?>> getChromosome(Identifier chromosomeId) {
        return Optional.ofNullable(chromosomesById.get(chromosomeId));
    }

    @Override
    public IAllele getDefault(IChromosome<?> chromosome) {
        if (chromosome.equals(speciesChromosome)) {
            throw new IllegalStateException("The species chromosome of karyotype " + id + " has no default allele - it must be set explicitly on every genome");
        }
        IAllele allele = defaults.get(chromosome);
        if (allele == null) {
            throw new IllegalArgumentException("Chromosome " + chromosome.id() + " is not part of karyotype " + id);
        }
        return allele;
    }

    @Override
    public boolean isWeaklyInherited(IChromosome<?> chromosome) {
        return weaklyInherited.contains(chromosome);
    }

    @Override
    public Codec<IGenome> genomeCodec() {
        return genomeCodec;
    }

    @Override
    public IGenomeBuilder genomeBuilder() {
        return new Genome.Builder(this);
    }
}
