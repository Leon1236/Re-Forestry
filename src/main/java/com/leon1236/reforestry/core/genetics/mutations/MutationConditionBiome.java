package com.leon1236.reforestry.core.genetics.mutations;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.mutations.IMutationCondition;

public final class MutationConditionBiome implements IMutationCondition {
    private final TagKey<Biome> biomeTag;

    public MutationConditionBiome(TagKey<Biome> biomeTag) {
        this.biomeTag = biomeTag;
    }

    @Override
    public float modifyChance(Level level, BlockPos pos, IGenome first, IGenome second, float currentChance) {
        return level.getBiome(pos).is(biomeTag) ? currentChance : 0f;
    }

    @Override
    public Component getDescription() {
        return Component.translatable("mutation.condition.reforestry.biome");
    }
}
