package com.leon1236.reforestry.apiculture.blocks;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IBlockSubtype;

public enum BlockAlvearyType implements IBlockSubtype {
    PLAIN(false),
    SWARMER(true),
    FAN(true),
    HEATER(true),
    HYGRO(false),
    STABILISER(false),
    SIEVE(false);

    public static final BlockAlvearyType[] VALUES = values();

    public final boolean activatable;

    private final String serializedName;

    BlockAlvearyType(boolean activatable) {
        this.activatable = activatable;
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    @Override
    public String toString() {
        return serializedName;
    }
}
