package com.leon1236.reforestry.apiculture.genetics;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.apiculture.LightPreference;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface IActivityType extends IRegistryAlleleValue {
    long NIGHT_TIME = 15000L;

    boolean isActive(long gameTime, long dayTime, BlockPos pos);

    IError getInactiveError(long gameTime, long dayTime, BlockPos pos);

    LightPreference getLightPreference();

    static long getBeeDayTime(Level level) {
        return level.dimensionType().hasSkyLight() ? level.getDefaultClockTime() : NIGHT_TIME;
    }
}
