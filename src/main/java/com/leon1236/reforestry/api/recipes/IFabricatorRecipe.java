package com.leon1236.reforestry.api.recipes;

import java.util.Optional;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public interface IFabricatorRecipe extends IForestryRecipe {
    FluidVariant getResultFluid();

    long getResultFluidAmount();

    Optional<Ingredient> getPlan();

    CraftingRecipe getCraftingGridRecipe();

    ItemStack getResultStack();

    boolean matches(Level level, FluidVariant liquid, long liquidAmount, ItemStack planStack, Container craftingGrid);
}
