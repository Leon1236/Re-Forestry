package com.leon1236.reforestry.api.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public interface IMoistenerRecipe extends IForestryRecipe {
    int getTimePerItem();

    Ingredient getInput();

    ItemStack getProduct();
}
