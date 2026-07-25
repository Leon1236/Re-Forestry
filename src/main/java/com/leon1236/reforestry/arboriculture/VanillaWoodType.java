package com.leon1236.reforestry.arboriculture;

import java.util.Locale;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.blocks.ForestryLeafType;

public enum VanillaWoodType implements IWoodType {
    OAK(WoodType.OAK, ForestryLeafType.OAK),
    SPRUCE(WoodType.SPRUCE, ForestryLeafType.SPRUCE),
    BIRCH(WoodType.BIRCH, ForestryLeafType.BIRCH),
    JUNGLE(WoodType.JUNGLE, ForestryLeafType.JUNGLE),
    ACACIA(WoodType.ACACIA, ForestryLeafType.ACACIA_VANILLA),
    DARK_OAK(WoodType.DARK_OAK, ForestryLeafType.DARK_OAK),
    CHERRY(WoodType.CHERRY, ForestryLeafType.CHERRY_VANILLA),
    MANGROVE(WoodType.MANGROVE, null),
    PALE_OAK(WoodType.PALE_OAK, null);

    public static final float DEFAULT_HARDNESS = 2.0f;
    public static final VanillaWoodType[] VALUES = values();

    public final String serializedName;
    public final WoodType vanillaWoodType;
    @Nullable
    private final ForestryLeafType leafType;

    VanillaWoodType(WoodType vanillaWoodType, @Nullable ForestryLeafType leafType) {
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
        this.vanillaWoodType = vanillaWoodType;
        this.leafType = leafType;
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

    @Override
    public boolean setDefaultLeaves(LevelAccessor level, BlockPos pos, IGenome genome, RandomSource rand, @Nullable GameProfile owner) {
        if (this.leafType == null) {
            return false;
        }
        return ForestryWoodType.setDefaultLeavesImpl(level, pos, genome, rand, this.leafType);
    }
}
