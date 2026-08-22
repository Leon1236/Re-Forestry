package com.leon1236.reforestry.arboriculture.blocks;

import java.util.Locale;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.api.arboriculture.IFruitPodType;
import com.leon1236.reforestry.arboriculture.genetics.DefaultFruits;
import com.leon1236.reforestry.api.arboriculture.genetics.IFruit;

public enum ForestryPodType implements IFruitPodType {
    COCOA,
    DATES,
    PAPAYA,
    COCONUT;

    public static final ForestryPodType[] VALUES = values();

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public IFruit getFruit() {
        return switch (this) {
            case COCOA -> DefaultFruits.COCOA;
            case DATES -> DefaultFruits.DATES;
            case PAPAYA -> DefaultFruits.PAPAYA;
            case COCONUT -> DefaultFruits.COCONUT;
        };
    }

    @Override
    public TagKey<Block> logTag() {
        return getFruit().getLogTag();
    }

    @Override
    public boolean useSmallAabb() {
        return this == COCONUT;
    }
}
