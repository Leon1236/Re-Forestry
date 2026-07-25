package com.leon1236.reforestry.arboriculture.genetics;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.core.genetics.Mating;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;

public final class TreeMating {
    private TreeMating() {
    }

    public record MatingResult(IGenome genome, Optional<Mutation> mutation) {
    }

    public static MatingResult resolveOffspringGenome(IGenome parent1, IGenome parent2, Level level, BlockPos pos, RandomSource random) {
        Mating.MatingResult result = Mating.resolveOffspringGenome(
                TreeChromosomes.SPECIES,
                ArboricultureGenetics::getDefaultGenome,
                ArboricultureGenetics::getMutations,
                parent1, parent2, level, pos, random);
        return new MatingResult(result.genome(), result.mutation());
    }
}
