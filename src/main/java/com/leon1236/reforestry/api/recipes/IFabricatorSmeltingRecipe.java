package com.leon1236.reforestry.api.recipes;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.world.item.crafting.Ingredient;

public interface IFabricatorSmeltingRecipe extends IForestryRecipe {
    Ingredient getInput();

    int getMeltingPoint();

    FluidVariant getResultFluid();

    long getResultFluidAmount();
}
