package com.leon1236.reforestry.arboriculture;

import java.util.Locale;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.IWoodType;

public enum ForestryWoodType implements IWoodType {
    LARCH,
    TEAK,
    ACACIA_DESERT,
    LIME,
    CHESTNUT,
    WENGE,
    BAOBAB,
    SEQUOIA(4.0f),

    KAPOK,
    EBONY,
    ELM,
    MAHOGANY,
    BALSA(1.0f),
    WILLOW,
    WALNUT,
    GREENHEART(7.5f),
    HILL_CHERRY,

    MAHOE,
    POPLAR,
    PALM,
    PAPAYA,
    PINE(3.0f),
    PLUM,
    MAPLE,
    CITRUS,

    GIGANTEUM(4.0f),
    IPE,
    PADAUK,
    COCOBOLO,
    FIR,
    COCONUT,
    BEECH,
    FEIJOA,
    DOGWOOD,
    GINKGO,
    JACARANDA,
    PEWEN,
    MACROCARPA,
    OLIVE,
    ORANGE,
    PEAR,
    KAURI,
    ZEBRAWOOD;

    public static final float DEFAULT_HARDNESS = 2.0f;
    public static final ForestryWoodType[] VALUES = values();

    public final String serializedName;
    private final float hardness;
    private final BlockSetType blockSetType;
    private final WoodType woodType;

    ForestryWoodType() {
        this(DEFAULT_HARDNESS);
    }

    ForestryWoodType(float hardness) {
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
        this.hardness = hardness;
        this.blockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(ReForestry.id(this.serializedName));
        this.woodType = WoodTypeBuilder.copyOf(WoodType.OAK).register(ReForestry.id(this.serializedName), this.blockSetType);
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    @Override
    public float getHardness() {
        return hardness;
    }

    @Override
    public WoodType getVanillaWoodType() {
        return woodType;
    }

    @Override
    public BlockSetType getBlockSetType() {
        return blockSetType;
    }

    @Override
    public String toString() {
        return serializedName;
    }
}
