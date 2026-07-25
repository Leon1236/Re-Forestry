package com.leon1236.reforestry.api.arboriculture;

import java.util.Locale;

public enum WoodBlockKind {
    LOG, STRIPPED_LOG, STRIPPED_WOOD, WOOD, PLANKS, SLAB, FENCE, FENCE_GATE, STAIRS, DOOR, TRAPDOOR,
    SIGN, WALL_SIGN, HANGING_SIGN, WALL_HANGING_SIGN, BUTTON, PRESSURE_PLATE;

    private final String serializedName;

    WoodBlockKind() {
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
    }

    public String getSerializedName() {
        return serializedName;
    }

    @Override
    public String toString() {
        return serializedName;
    }
}
