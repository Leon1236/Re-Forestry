package com.leon1236.reforestry.apiculture.genetics;

import java.util.List;
import java.util.Locale;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.apiculture.LightPreference;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IError;

public enum ActivityType implements IActivityType {
    DIURNAL(0, 12000, ForestryError.NOT_DAY, LightPreference.ANY),
    NOCTURNAL(12000, 24000, ForestryError.NOT_NIGHT, LightPreference.DARK),
    CREPUSCULAR(-1, -1, ForestryError.NOT_TWILIGHT, LightPreference.ANY) {
        @Override
        public boolean isActive(long gameTime, long dayTime, BlockPos pos) {
            int time = (int) (dayTime % 24000);
            return (0 <= time && time < 2000) || (10000 <= time && time < 15000) || 21000 <= time;
        }
    },
    METATURNAL(0, 24000, ForestryError.INVALID, LightPreference.ANY),
    CATHEMERAL(-1, -1, ForestryError.SLEEPY, LightPreference.ANY) {
        @Override
        public boolean isActive(long gameTime, long dayTime, BlockPos pos) {
            long adjustedTime = gameTime + cathemeralOffset(pos);
            return adjustedTime % 24000L < 12000L;
        }
    };

    private static final PerlinNoise CATHEMERAL_NOISE = PerlinNoise.create(RandomSource.create(13L), List.of(5, 3, 6));

    private final Identifier id = ReForestry.id("activity_" + name().toLowerCase(Locale.ENGLISH));
    private final int startTick;
    private final int endTick;
    private final IError inactiveError;
    private final LightPreference lightPreference;

    ActivityType(int startTick, int endTick, IError inactiveError, LightPreference lightPreference) {
        this.startTick = startTick;
        this.endTick = endTick;
        this.inactiveError = inactiveError;
        this.lightPreference = lightPreference;
    }

    @Override
    public Identifier id() {
        return id;
    }

    @Override
    public boolean isActive(long gameTime, long dayTime, BlockPos pos) {
        int time = (int) (dayTime % 24000);
        return startTick <= time && time < endTick;
    }

    @Override
    public IError getInactiveError(long gameTime, long dayTime, BlockPos pos) {
        return inactiveError;
    }

    @Override
    public LightPreference getLightPreference() {
        return lightPreference;
    }

    private static long cathemeralOffset(BlockPos pos) {
        return (long) (CATHEMERAL_NOISE.getValue(pos.getX() / 40.0, pos.getY() / 1000.0, pos.getZ() / 40.0) * 24000L);
    }
}
