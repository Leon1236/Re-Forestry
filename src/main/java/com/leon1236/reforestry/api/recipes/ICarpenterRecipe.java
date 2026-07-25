package com.leon1236.reforestry.api.recipes;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public interface ICarpenterRecipe extends IForestryRecipe {
    int getPackagingTime();

    CraftingRecipe getCraftingGridRecipe();

    @Nullable
    Ingredient getBox();

    FluidVariant getInputFluid();

    long getInputFluidAmount();

    ItemStack getResultStack();

    boolean matches(FluidVariant fluid, long fluidAmount, ItemStack boxStack, Container craftingGrid, Level level);
}
