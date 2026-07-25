package com.leon1236.reforestry.api.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;

public interface IForestryRecipe extends Recipe<RecipeInput> {
    @Deprecated
    @Override
    default boolean matches(RecipeInput input, Level level) {
        return false;
    }

    @Deprecated
    @Override
    default ItemStack assemble(RecipeInput input) {
        return ItemStack.EMPTY;
    }

    @Deprecated
    @Override
    default boolean isSpecial() {
        return true;
    }

    @Deprecated
    @Override
    default boolean showNotification() {
        return false;
    }

    @Deprecated
    @Override
    default String group() {
        return "reforestry";
    }

    @Deprecated
    @Override
    default PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Deprecated
    @Override
    default RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }
}
