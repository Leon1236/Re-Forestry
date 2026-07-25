package com.leon1236.reforestry.api.plugin;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.mutations.IMutationCondition;

public interface IMutationBuilder {
    IMutationBuilder restrictBiomeType(TagKey<Biome> biomeTag);

    IMutationBuilder requireResource(BlockState... accepted);

    IMutationBuilder requireDay();

    IMutationBuilder requireNight();

    IMutationBuilder restrictDateRange(int startMonth, int startDay, int endMonth, int endDay);

    IMutationBuilder addMutationCondition(IMutationCondition condition);

    <A extends IAllele> IMutationBuilder addSpecialAllele(IChromosome<A> chromosome, A allele);

    IMutationBuilder setChance(float chancePercent);
}
