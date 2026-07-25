package com.leon1236.reforestry.core.genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.google.common.collect.ImmutableMap;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.AllelePair;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;

public final class Mating {
    private Mating() {
    }

    public record MatingResult(IGenome genome, Optional<Mutation> mutation) {
    }

    public static <S extends IRegistryAlleleValue> MatingResult resolveOffspringGenome(
            IRegistryChromosome<S> speciesChromosome,
            Function<Identifier, IGenome> defaultGenomeLookup,
            BiFunction<Identifier, Identifier, List<Mutation>> mutationsLookup,
            IGenome parent1, IGenome parent2, Level level, BlockPos pos, RandomSource random) {
        IKaryotype karyotype = parent1.karyotype();

        Identifier active1 = parent1.getActiveAllele(speciesChromosome).value().id();
        Identifier active2 = parent2.getActiveAllele(speciesChromosome).value().id();
        Optional<Mutation> mutation = attemptMutation(mutationsLookup, active1, active2, level, pos, parent1, parent2, random);
        if (mutation.isEmpty()) {
            Identifier inactive1 = parent1.getInactiveAllele(speciesChromosome).value().id();
            Identifier inactive2 = parent2.getInactiveAllele(speciesChromosome).value().id();
            mutation = attemptMutation(mutationsLookup, inactive1, inactive2, level, pos, parent1, parent2, random);
        }

        ImmutableMap.Builder<IChromosome<?>, AllelePair<?>> resultChromosomes = ImmutableMap.builder();
        if (mutation.isPresent()) {
            Mutation m = mutation.get();
            IGenome template = defaultGenomeLookup.apply(m.result());
            for (IChromosome<?> chromosome : karyotype.chromosomes()) {
                IAllele special = m.specialAlleles().get(chromosome);
                if (special != null) {
                    resultChromosomes.put(chromosome, specialPair(special));
                } else if (karyotype.isWeaklyInherited(chromosome)) {
                    resultChromosomes.put(chromosome, inheritChromosome(chromosome, parent1, parent2, random));
                } else {
                    resultChromosomes.put(chromosome, template.chromosomes().get(chromosome));
                }
            }
        } else {
            for (IChromosome<?> chromosome : karyotype.chromosomes()) {
                resultChromosomes.put(chromosome, inheritChromosome(chromosome, parent1, parent2, random));
            }
        }

        IGenomeBuilder builder = karyotype.genomeBuilder();
        for (Map.Entry<IChromosome<?>, AllelePair<?>> entry : resultChromosomes.build().entrySet()) {
            setPairUnchecked(builder, entry.getKey(), entry.getValue());
        }
        return new MatingResult(builder.build(), mutation);
    }

    private static Optional<Mutation> attemptMutation(BiFunction<Identifier, Identifier, List<Mutation>> mutationsLookup,
                                                        Identifier firstSpecies, Identifier secondSpecies, Level level,
                                                        BlockPos pos, IGenome parent1, IGenome parent2, RandomSource random) {
        List<Mutation> candidates = new ArrayList<>(mutationsLookup.apply(firstSpecies, secondSpecies));
        shuffle(candidates, random);
        for (Mutation candidate : candidates) {
            float chance = candidate.getChance(level, pos, parent1, parent2);
            if (chance > 0f && random.nextFloat() < chance) {
                return Optional.of(candidate);
            }
        }
        return Optional.empty();
    }

    private static <T> void shuffle(List<T> list, RandomSource random) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    @SuppressWarnings("unchecked")
    private static <A extends IAllele> AllelePair<A> inheritChromosome(IChromosome<A> chromosome, IGenome parent1, IGenome parent2, RandomSource random) {
        AllelePair<A> pair1 = (AllelePair<A>) parent1.chromosomes().get(chromosome);
        AllelePair<A> pair2 = (AllelePair<A>) parent2.chromosomes().get(chromosome);
        return pair1.inheritOther(random, pair2);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static AllelePair<?> specialPair(IAllele allele) {
        return new AllelePair(allele, allele);
    }

    @SuppressWarnings("unchecked")
    private static <A extends IAllele> void setPairUnchecked(IGenomeBuilder builder, IChromosome<A> chromosome, AllelePair<?> pair) {
        builder.setPair(chromosome, (AllelePair<A>) pair);
    }
}
