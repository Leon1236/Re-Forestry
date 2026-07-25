package com.leon1236.reforestry.apiculture.genetics;

import java.util.Optional;

import net.minecraft.util.RandomSource;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.core.genetics.Mating;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;

public final class BeeMating {
    private BeeMating() {
    }

    public record MatingResult(IGenome genome, Optional<Mutation> mutation) {
    }

    public static MatingResult resolveOffspringGenome(IGenome parent1, IGenome parent2, IBeeHousing housing, RandomSource random) {
        Mating.MatingResult result = Mating.resolveOffspringGenome(
                BeeChromosomes.SPECIES,
                ApicultureGenetics::getDefaultGenome,
                ApicultureGenetics::getMutations,
                parent1, parent2, housing.level(), housing.position(), random);
        return new MatingResult(result.genome(), result.mutation());
    }
}
