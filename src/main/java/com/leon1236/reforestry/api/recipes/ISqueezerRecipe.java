package com.leon1236.reforestry.api.recipes;

import java.util.List;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public interface ISqueezerRecipe extends IForestryRecipe {
    List<Ingredient> getInputs();

    int getProcessingTime();

    ItemStack getRemnants();

    float getRemnantsChance();

    FluidVariant getOutputFluid();

    long getOutputAmount();
}
