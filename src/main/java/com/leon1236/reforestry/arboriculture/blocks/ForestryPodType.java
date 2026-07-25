package com.leon1236.reforestry.arboriculture.blocks;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IBlockSubtype;
import com.leon1236.reforestry.arboriculture.genetics.DefaultFruits;
import com.leon1236.reforestry.arboriculture.genetics.IFruit;

public enum ForestryPodType implements IBlockSubtype {
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
}
