package com.leon1236.reforestry.core.genetics.mutations;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.mutations.IMutationCondition;

public final class MutationConditionCaveDwelling implements IMutationCondition {
    @Override
    public float modifyChance(Level level, BlockPos pos, IGenome first, IGenome second, float currentChance) {
        int surfaceHeight = level.getHeight(Heightmap.Types.MOTION_BLOCKING, pos.getX(), pos.getZ());
        return pos.getY() < surfaceHeight ? currentChance : 0f;
    }

    @Override
    public Component getDescription() {
        return Component.translatable("mutation.condition.reforestry.cave_dwelling");
    }
}
