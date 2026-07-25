package com.leon1236.reforestry.arboriculture;

import java.util.Locale;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import com.leon1236.reforestry.api.arboriculture.IWoodType;

public enum VanillaWoodType implements IWoodType {
    OAK(WoodType.OAK),
    SPRUCE(WoodType.SPRUCE),
    BIRCH(WoodType.BIRCH),
    JUNGLE(WoodType.JUNGLE),
    ACACIA(WoodType.ACACIA),
    DARK_OAK(WoodType.DARK_OAK),
    CHERRY(WoodType.CHERRY),
    MANGROVE(WoodType.MANGROVE),
    PALE_OAK(WoodType.PALE_OAK);

    public static final float DEFAULT_HARDNESS = 2.0f;
    public static final VanillaWoodType[] VALUES = values();

    public final String serializedName;
    public final WoodType vanillaWoodType;

    VanillaWoodType(WoodType vanillaWoodType) {
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
        this.vanillaWoodType = vanillaWoodType;
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    @Override
    public float getHardness() {
        return DEFAULT_HARDNESS;
    }

    @Override
    public WoodType getVanillaWoodType() {
        return vanillaWoodType;
    }

    @Override
    public BlockSetType getBlockSetType() {
        return vanillaWoodType.setType();
    }
}
