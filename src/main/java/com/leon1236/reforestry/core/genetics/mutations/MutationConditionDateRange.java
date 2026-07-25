package com.leon1236.reforestry.core.genetics.mutations;

import java.time.LocalDate;
import java.time.MonthDay;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.mutations.IMutationCondition;

public final class MutationConditionDateRange implements IMutationCondition {
    private final MonthDay start;
    private final MonthDay end;

    public MutationConditionDateRange(int startMonth, int startDay, int endMonth, int endDay) {
        this.start = MonthDay.of(startMonth, startDay);
        this.end = MonthDay.of(endMonth, endDay);
    }

    @Override
    public float modifyChance(Level level, BlockPos pos, IGenome first, IGenome second, float currentChance) {
        MonthDay today = MonthDay.from(LocalDate.now());
        boolean inRange = start.compareTo(end) <= 0
                ? !today.isBefore(start) && !today.isAfter(end)
                : !today.isBefore(start) || !today.isAfter(end);
        return inRange ? currentChance : 0f;
    }

    @Override
    public Component getDescription() {
        return Component.translatable("mutation.condition.reforestry.date_range");
    }
}
