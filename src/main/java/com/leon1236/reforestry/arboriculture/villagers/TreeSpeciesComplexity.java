package com.leon1236.reforestry.arboriculture.villagers;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;

final class TreeSpeciesComplexity {
    private static final Identifier GIANT_SEQUOIA = ReForestry.id("tree_giant_sequoia");

    private TreeSpeciesComplexity() {
    }

    static int get(ITreeSpecies species) {
        if (GIANT_SEQUOIA.equals(species.id())) {
            return 10;
        }
        return 1 + advancement(species.id(), new HashSet<>());
    }

    private static int advancement(Identifier species, Set<Identifier> exclude) {
        int highest = 0;
        exclude.add(species);
        for (Mutation mutation : ArboricultureGenetics.getAllMutations()) {
            if (!mutation.result().equals(species)) {
                continue;
            }
            highest = highestParent(mutation.firstParent(), highest, exclude);
            highest = highestParent(mutation.secondParent(), highest, exclude);
        }
        return 1 + highest;
    }

    private static int highestParent(Identifier parent, int highest, Set<Identifier> exclude) {
        if (exclude.contains(parent)) {
            return highest;
        }
        return Math.max(advancement(parent, exclude), highest);
    }
}
