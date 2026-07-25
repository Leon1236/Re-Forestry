package com.leon1236.reforestry.core.genetics.mutations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.mutations.IMutationCondition;
import com.leon1236.reforestry.api.plugin.IMutationBuilder;

public final class MutationBuilder implements IMutationBuilder {
    private final Identifier firstParent;
    private final Identifier secondParent;
    private float chancePercent;
    private final List<IMutationCondition> conditions = new ArrayList<>();
    private final Map<IChromosome<?>, IAllele> specialAlleles = new LinkedHashMap<>();

    public MutationBuilder(Identifier firstParent, Identifier secondParent, float chancePercent) {
        this.firstParent = firstParent;
        this.secondParent = secondParent;
        this.chancePercent = chancePercent;
    }

    @Override
    public IMutationBuilder restrictBiomeType(TagKey<Biome> biomeTag) {
        conditions.add(new MutationConditionBiome(biomeTag));
        return this;
    }

    @Override
    public IMutationBuilder requireResource(BlockState... accepted) {
        conditions.add(new MutationConditionResource(List.of(accepted)));
        return this;
    }

    @Override
    public IMutationBuilder requireDay() {
        conditions.add(MutationConditionDaytime.DAY);
        return this;
    }

    @Override
    public IMutationBuilder requireNight() {
        conditions.add(MutationConditionDaytime.NIGHT);
        return this;
    }

    @Override
    public IMutationBuilder restrictDateRange(int startMonth, int startDay, int endMonth, int endDay) {
        conditions.add(new MutationConditionDateRange(startMonth, startDay, endMonth, endDay));
        return this;
    }

    @Override
    public IMutationBuilder addMutationCondition(IMutationCondition condition) {
        conditions.add(condition);
        return this;
    }

    @Override
    public <A extends IAllele> IMutationBuilder addSpecialAllele(IChromosome<A> chromosome, A allele) {
        specialAlleles.put(chromosome, allele);
        return this;
    }

    @Override
    public IMutationBuilder setChance(float chancePercent) {
        this.chancePercent = chancePercent;
        return this;
    }

    public Mutation build(Identifier result) {
        return new Mutation(firstParent, secondParent, result, chancePercent / 100f, List.copyOf(conditions), Map.copyOf(specialAlleles));
    }
}
