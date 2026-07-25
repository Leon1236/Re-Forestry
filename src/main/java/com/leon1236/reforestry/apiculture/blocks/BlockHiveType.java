package com.leon1236.reforestry.apiculture.blocks;

import java.util.Locale;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.IBlockSubtype;

public enum BlockHiveType implements IBlockSubtype {
    FOREST("bee_forest"),
    MEADOWS("bee_meadows"),
    DESERT("bee_modest"),
    JUNGLE("bee_tropical"),
    END("bee_ended"),
    SNOW("bee_wintry"),
    SWAMP("bee_marshy"),
    SAVANNA("bee_savanna"),
    LUSH("bee_lush"),
    AQUATIC("bee_aquatic"),
    NETHER("bee_embittered"),
    SWARM("none");

    private final Identifier speciesId;
    private final String serializedName;

    BlockHiveType(String speciesPath) {
        this.speciesId = ReForestry.id(speciesPath);
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
    }

    public Identifier getSpeciesId() {
        return speciesId;
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }
}
