package com.leon1236.reforestry.api.genetics.mutations;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;

public interface IMutationCondition {
    float modifyChance(Level level, BlockPos pos, IGenome first, IGenome second, float currentChance);

    Component getDescription();
}
