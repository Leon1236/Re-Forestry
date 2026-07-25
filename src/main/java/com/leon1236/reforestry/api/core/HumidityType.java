package com.leon1236.reforestry.api.core;

import java.util.List;

import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;

public enum HumidityType {
    ARID(ReforestryBiomeTags.ARID_HUMIDITY, 0xaad0db),
    NORMAL(ReforestryBiomeTags.NORMAL_HUMIDITY, 0x4b7bff),
    DAMP(ReforestryBiomeTags.DAMP_HUMIDITY, 0x6e56b3);

    public static final List<HumidityType> VALUES = List.of(values());

    public final TagKey<Biome> tag;
    public final int color;

    HumidityType(TagKey<Biome> tag, int color) {
        this.tag = tag;
        this.color = color;
    }

    public HumidityType up() {
        return up(1);
    }

    public HumidityType up(int steps) {
        return VALUES.get(Mth.clamp(ordinal() + steps, 0, 2));
    }

    public HumidityType down() {
        return down(1);
    }

    public HumidityType down(int steps) {
        return VALUES.get(Mth.clamp(ordinal() - steps, 0, 2));
    }

    public boolean isWetterOrEqual(HumidityType other) {
        return ordinal() >= other.ordinal();
    }

    public boolean isDrierOrEqual(HumidityType other) {
        return ordinal() <= other.ordinal();
    }

    public static HumidityType getFromValue(float downfall) {
        if (downfall > 0.85f) {
            return DAMP;
        } else if (downfall >= 0.3f) {
            return NORMAL;
        } else {
            return ARID;
        }
    }
}
