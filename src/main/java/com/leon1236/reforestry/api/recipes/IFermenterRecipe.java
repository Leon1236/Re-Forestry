package com.leon1236.reforestry.api.recipes;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;

public interface IFermenterRecipe extends IForestryRecipe {
    Ingredient getInputItem();

    FluidVariant getInputFluid();

    long getInputFluidAmount();

    int getFermentationValue();

    float getModifier();

    Fluid getOutput();

    boolean matches(ItemStack inputItem, FluidVariant inputFluid, long inputFluidAmount);
}
