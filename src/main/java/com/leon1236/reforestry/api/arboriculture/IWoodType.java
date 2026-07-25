package com.leon1236.reforestry.api.arboriculture;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import com.leon1236.reforestry.api.core.IBlockSubtype;
import com.leon1236.reforestry.api.genetics.IGenome;

public interface IWoodType extends IBlockSubtype {
    float getHardness();

    WoodType getVanillaWoodType();

    BlockSetType getBlockSetType();

    boolean setDefaultLeaves(LevelAccessor level, BlockPos pos, IGenome genome, RandomSource rand, @Nullable GameProfile owner);
}
