package com.leon1236.reforestry.core.genetics.mutations;

import java.util.List;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.mutations.IMutationCondition;

public record Mutation(Identifier firstParent, Identifier secondParent, Identifier result, float baseChance,
                        List<IMutationCondition> conditions, Map<IChromosome<?>, IAllele> specialAlleles)
        implements IMutation {
    @Override
    public float getChance() {
        return baseChance;
    }

    public float getChance(Level level, BlockPos pos, IGenome first, IGenome second) {
        float chance = baseChance;
        for (IMutationCondition condition : conditions) {
            chance = condition.modifyChance(level, pos, first, second, chance);
            if (chance <= 0f) {
                return 0f;
            }
        }
        return chance;
    }
}
