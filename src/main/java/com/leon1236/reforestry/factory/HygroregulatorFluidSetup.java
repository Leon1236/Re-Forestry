package com.leon1236.reforestry.factory;

import java.util.HashSet;
import java.util.Set;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.api.predicates.HygroregulatorInputFluids;
import com.leon1236.reforestry.api.recipes.IHygroregulatorRecipe;
import com.leon1236.reforestry.factory.recipes.HygroregulatorFallbackRecipes;

public final class HygroregulatorFluidSetup {
    private HygroregulatorFluidSetup() {
    }

    public static void reload(ServerLevel level) {
        Set<Fluid> fluids = new HashSet<>(HygroregulatorFallbackRecipes.fluids());
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof IHygroregulatorRecipe recipe) {
                addInput(fluids, recipe.getInputFluid());
            }
        }
        HygroregulatorInputFluids.setAllowedFluids(fluids);
    }

    private static void addInput(Set<Fluid> fluids, FluidVariant input) {
        if (!input.isBlank()) {
            fluids.add(input.getFluid());
        }
    }
}
