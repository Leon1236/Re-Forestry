package com.leon1236.reforestry.core.genetics.mutations;

import java.util.Locale;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.mutations.IMutationCondition;

public enum MutationConditionDaytime implements IMutationCondition {
    DAY {
        @Override
        public float modifyChance(Level level, BlockPos pos, IGenome first, IGenome second, float currentChance) {
            return level.isBrightOutside() ? currentChance : 0f;
        }
    },
    NIGHT {
        @Override
        public float modifyChance(Level level, BlockPos pos, IGenome first, IGenome second, float currentChance) {
            return level.isDarkOutside() ? currentChance : 0f;
        }
    };

    @Override
    public Component getDescription() {
        return Component.translatable("mutation.condition.reforestry." + name().toLowerCase(Locale.ENGLISH));
    }
}
