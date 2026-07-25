package com.leon1236.reforestry.api.recipes;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

public interface IStillRecipe extends IForestryRecipe {
    int getCyclesPerUnit();

    FluidVariant getInputFluid();

    long getInputAmount();

    FluidVariant getOutputFluid();

    long getOutputAmount();

    boolean matches(FluidVariant fluid, long amount);
}
