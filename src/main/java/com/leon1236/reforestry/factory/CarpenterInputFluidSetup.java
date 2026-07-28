package com.leon1236.reforestry.factory;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.api.predicates.CarpenterInputFluids;
import com.leon1236.reforestry.api.recipes.ICarpenterRecipe;

public final class CarpenterInputFluidSetup {
    private CarpenterInputFluidSetup() {
    }

    public static void reload(ServerLevel level) {
        Set<Fluid> fluids = new HashSet<>();
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof ICarpenterRecipe recipe && !recipe.getInputFluid().isBlank()) {
                fluids.add(recipe.getInputFluid().getFluid());
            }
        }
        CarpenterInputFluids.setAllowedFluids(fluids);
    }
}
