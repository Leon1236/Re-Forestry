package com.leon1236.reforestry.api.recipes;

import java.util.List;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import com.leon1236.reforestry.api.core.Product;

public interface ICentrifugeRecipe extends IForestryRecipe {
    Ingredient getInput();

    int getProcessingTime();

    List<ItemStack> getProducts(RandomSource random, double outputMult);

    List<Product> getAllProducts();
}
