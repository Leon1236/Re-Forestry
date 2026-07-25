package com.leon1236.reforestry.api.recipes;

import java.util.List;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.recipes.IngredientStack;

public interface ISmelterRecipe extends IForestryRecipe {
    int getProcessingTime();

    List<IngredientStack> getInputs();

    ItemStack getOutput();

    boolean matches(int processingTime, List<IngredientStack> inputs, IngredientStack output);
}
