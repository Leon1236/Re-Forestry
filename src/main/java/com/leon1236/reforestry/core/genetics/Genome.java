package com.leon1236.reforestry.core.genetics;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.AllelePair;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

final class Genome implements IGenome {
    private final IKaryotype karyotype;
    private final ImmutableMap<IChromosome<?>, AllelePair<?>> chromosomes;

    Genome(IKaryotype karyotype, ImmutableMap<IChromosome<?>, AllelePair<?>> chromosomes) {
        this.karyotype = karyotype;
        this.chromosomes = chromosomes;
    }

    static Map<Identifier, AllelePair<IAllele>> toRaw(IGenome genome) {
        Map<Identifier, AllelePair<IAllele>> raw = new LinkedHashMap<>();
        for (Map.Entry<IChromosome<?>, AllelePair<?>> entry : genome.chromosomes().entrySet()) {
            @SuppressWarnings("unchecked")
            AllelePair<IAllele> pair = (AllelePair<IAllele>) entry.getValue();
            raw.put(entry.getKey().id(), pair);
        }
        return raw;
    }

    static IGenome fromRaw(IKaryotype karyotype, Map<Identifier, AllelePair<IAllele>> raw) {
        ImmutableMap.Builder<IChromosome<?>, AllelePair<?>> pairs = ImmutableMap.builder();
        for (IChromosome<?> chromosome : karyotype.chromosomes()) {
            AllelePair<IAllele> pair = raw.get(chromosome.id());
            if (pair == null) {
                if (chromosome.equals(karyotype.speciesChromosome())) {
                    throw new IllegalArgumentException("Genome for karyotype " + karyotype.id() + " is missing its species chromosome");
                }
                IAllele defaultAllele = karyotype.getDefault(chromosome);
                pair = new AllelePair<>(defaultAllele, defaultAllele);
            }
            pairs.put(chromosome, pair);
        }
        return new Genome(karyotype, pairs.build());
    }

    @Override
    public IKaryotype karyotype() {
        return karyotype;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <A extends IAllele> A getActiveAllele(IChromosome<A> chromosome) {
        return (A) requirePair(chromosome).active();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <A extends IAllele> A getInactiveAllele(IChromosome<A> chromosome) {
        return (A) requirePair(chromosome).inactive();
    }

    private AllelePair<?> requirePair(IChromosome<?> chromosome) {
        AllelePair<?> pair = chromosomes.get(chromosome);
        if (pair == null) {
            throw new IllegalArgumentException("Chromosome " + chromosome.id() + " is not part of this genome");
        }
        return pair;
    }

    @Override
    public ImmutableMap<IChromosome<?>, AllelePair<?>> chromosomes() {
        return chromosomes;
    }

    static final class Builder implements IGenomeBuilder {
        private final IKaryotype karyotype;
        private final Map<IChromosome<?>, IAllele> active = new LinkedHashMap<>();
        private final Map<IChromosome<?>, IAllele> inactive = new LinkedHashMap<>();

        Builder(IKaryotype karyotype) {
            this.karyotype = karyotype;
            for (IChromosome<?> chromosome : karyotype.chromosomes()) {
                if (chromosome.equals(karyotype.speciesChromosome())) {
                    continue;
                }
                IAllele defaultAllele = karyotype.getDefault(chromosome);
                active.put(chromosome, defaultAllele);
                inactive.put(chromosome, defaultAllele);
            }
        }

        @Override
        public <A extends IAllele> IGenomeBuilder set(IChromosome<A> chromosome, A allele) {
            return setActive(chromosome, allele).setInactive(chromosome, allele);
        }

        @Override
        public <A extends IAllele> IGenomeBuilder setActive(IChromosome<A> chromosome, A allele) {
            requireMember(chromosome);
            active.put(chromosome, allele);
            return this;
        }

        @Override
        public <A extends IAllele> IGenomeBuilder setInactive(IChromosome<A> chromosome, A allele) {
            requireMember(chromosome);
            inactive.put(chromosome, allele);
            return this;
        }

        private void requireMember(IChromosome<?> chromosome) {
            if (!karyotype.chromosomes().contains(chromosome)) {
                throw new IllegalArgumentException("Chromosome " + chromosome.id() + " is not part of karyotype " + karyotype.id());
            }
        }

        @Override
        public IGenome build() {
            ImmutableMap.Builder<IChromosome<?>, AllelePair<?>> pairs = ImmutableMap.builder();
            for (IChromosome<?> chromosome : karyotype.chromosomes()) {
                pairs.put(chromosome, AllelePair.create(active.get(chromosome), inactive.get(chromosome)));
            }
            return new Genome(karyotype, pairs.build());
        }
    }
}
