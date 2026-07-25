package com.leon1236.reforestry.api.arboriculture;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import com.leon1236.reforestry.api.core.IBlockSubtype;

public interface IWoodType extends IBlockSubtype {
    float getHardness();

    WoodType getVanillaWoodType();

    BlockSetType getBlockSetType();
}
