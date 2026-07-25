package com.leon1236.reforestry.factory;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.api.predicates.FabricatorMoltenFluids;
import com.leon1236.reforestry.api.recipes.IFabricatorRecipe;
import com.leon1236.reforestry.api.recipes.IFabricatorSmeltingRecipe;
import com.leon1236.reforestry.factory.recipes.FabricatorRecipe;
import com.leon1236.reforestry.factory.recipes.FabricatorSmeltingRecipe;

public final class FabricatorMoltenFluidSetup {
    private FabricatorMoltenFluidSetup() {
    }

    public static void reload(ServerLevel level) {
        Set<Fluid> fluids = new HashSet<>();
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof IFabricatorSmeltingRecipe smelting) {
                fluids.add(smelting.getResultFluid().getFluid());
            } else if (holder.value() instanceof FabricatorSmeltingRecipe smelting) {
                fluids.add(smelting.getResultFluid().getFluid());
            } else if (holder.value() instanceof IFabricatorRecipe fabricator) {
                fluids.add(fabricator.getResultFluid().getFluid());
            } else if (holder.value() instanceof FabricatorRecipe fabricator) {
                fluids.add(fabricator.getResultFluid().getFluid());
            }
        }
        FabricatorMoltenFluids.setAllowedFluids(fluids);
    }
}
