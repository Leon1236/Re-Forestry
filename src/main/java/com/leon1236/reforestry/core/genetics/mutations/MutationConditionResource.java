package com.leon1236.reforestry.core.genetics.mutations;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.mutations.IMutationCondition;

public final class MutationConditionResource implements IMutationCondition {
    private final List<BlockState> accepted;

    public MutationConditionResource(List<BlockState> accepted) {
        this.accepted = accepted;
    }

    @Override
    public float modifyChance(Level level, BlockPos pos, IGenome first, IGenome second, float currentChance) {
        BlockPos.MutableBlockPos cursor = pos.mutable();
        while (cursor.getY() > level.getMinY()) {
            cursor.move(Direction.DOWN);
            BlockState state = level.getBlockState(cursor);
            if (!state.isAir()) {
                return accepted.contains(state) ? currentChance : 0f;
            }
        }
        return 0f;
    }

    @Override
    public Component getDescription() {
        return Component.translatable("mutation.condition.reforestry.resource");
    }
}
