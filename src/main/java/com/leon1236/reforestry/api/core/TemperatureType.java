package com.leon1236.reforestry.api.core;

import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;

import com.leon1236.reforestry.ReForestry;

public enum TemperatureType {
    ICY(ReforestryBiomeTags.ICY_TEMPERATURE, ReForestry.id("gui/habitats/snow"), 0xaafff0),
    COLD(ReforestryBiomeTags.COLD_TEMPERATURE, ReForestry.id("gui/habitats/taiga"), 0x72ddf7),
    NORMAL(ReforestryBiomeTags.NORMAL_TEMPERATURE, ReForestry.id("gui/habitats/plains"), 0xffd013),
    WARM(ReforestryBiomeTags.WARM_TEMPERATURE, ReForestry.id("gui/habitats/jungle"), 0xfb8a24),
    HOT(ReforestryBiomeTags.HOT_TEMPERATURE, ReForestry.id("gui/habitats/desert"), 0xd61439),
    HELLISH(ReforestryBiomeTags.HELLISH_TEMPERATURE, ReForestry.id("gui/habitats/nether"), 0x81032d);

    public static final List<TemperatureType> VALUES = List.of(values());

    public final TagKey<Biome> tag;
    public final Identifier iconTexture;
    public final int color;

    TemperatureType(TagKey<Biome> tag, Identifier iconSprite, int color) {
        this.tag = tag;
        this.iconTexture = iconSprite;
        this.color = color;
    }

    public TemperatureType up() {
        return up(1);
    }

    public TemperatureType up(int steps) {
        return VALUES.get(Mth.clamp(ordinal() + steps, 0, 5));
    }

    public TemperatureType down() {
        return down(1);
    }

    public TemperatureType down(int steps) {
        return VALUES.get(Mth.clamp(ordinal() - steps, 0, 5));
    }

    public boolean isWarmerOrEqual(TemperatureType other) {
        return ordinal() >= other.ordinal();
    }

    public boolean isCoolerOrEqual(TemperatureType other) {
        return ordinal() <= other.ordinal();
    }

    public static TemperatureType getFromValue(float baseTemperature) {
        if (baseTemperature > 1.00f) {
            return HOT;
        } else if (baseTemperature > 0.85f) {
            return WARM;
        } else if (baseTemperature > 0.35f) {
            return NORMAL;
        } else if (baseTemperature > 0.0f) {
            return COLD;
        } else {
            return ICY;
        }
    }
}
