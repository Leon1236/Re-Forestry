package com.leon1236.reforestry.apiculture.blocks;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IBlockSubtype;

public enum BlockAlvearyType implements IBlockSubtype {
    PLAIN(false, "block"),
    SWARMER(true),
    FAN(true),
    HEATER(true),
    HYGRO(false, "hygroregulator"),
    STABILISER(false, "stabilizer"),
    SIEVE(false);

    public static final BlockAlvearyType[] VALUES = values();

    public final boolean activatable;

    private final String serializedName;

    BlockAlvearyType(boolean activatable) {
        this.activatable = activatable;
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
    }

    BlockAlvearyType(boolean activatable, String serializedName) {
        this.activatable = activatable;
        this.serializedName = serializedName;
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
