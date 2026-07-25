package com.leon1236.reforestry.api.recipes;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

public interface IHygroregulatorRecipe extends IForestryRecipe {
    FluidVariant getInputFluid();

    long getInputFluidAmount();

    int getRetainTime();

    byte getHumiditySteps();

    byte getTemperatureSteps();
}
