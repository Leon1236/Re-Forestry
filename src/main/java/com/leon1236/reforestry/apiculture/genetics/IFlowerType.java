package com.leon1236.reforestry.apiculture.genetics;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface IFlowerType extends IRegistryAlleleValue {
    boolean isAcceptableFlower(Level level, BlockPos pos);
}
