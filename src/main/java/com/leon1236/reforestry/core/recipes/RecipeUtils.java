package com.leon1236.reforestry.core.recipes;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

import com.leon1236.reforestry.api.recipes.ICarpenterRecipe;
import com.leon1236.reforestry.api.recipes.IFabricatorRecipe;
import com.leon1236.reforestry.api.recipes.IFabricatorSmeltingRecipe;
import com.leon1236.reforestry.api.recipes.IFermenterRecipe;
import com.leon1236.reforestry.api.recipes.IHygroregulatorRecipe;
import com.leon1236.reforestry.api.recipes.IMoistenerRecipe;
import com.leon1236.reforestry.api.recipes.ISqueezerContainerRecipe;
import com.leon1236.reforestry.api.recipes.ISqueezerRecipe;
import com.leon1236.reforestry.factory.recipes.FabricatorRecipe;
import com.leon1236.reforestry.factory.recipes.FabricatorSmeltingRecipe;
import com.leon1236.reforestry.factory.recipes.FermenterRecipe;
import com.leon1236.reforestry.factory.recipes.HygroregulatorFallbackRecipes;
import com.leon1236.reforestry.factory.recipes.HygroregulatorRecipe;
import com.leon1236.reforestry.factory.recipes.MoistenerRecipe;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.factory.recipes.CarpenterRecipe;
import com.leon1236.reforestry.factory.recipes.SqueezerRecipe;

public final class RecipeUtils {
    private RecipeUtils() {
    }

    public static boolean isSqueezerIngredient(ServerLevel level, ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof SqueezerRecipe recipe) {
                for (Ingredient ingredient : recipe.getInputs()) {
                    if (ingredient.test(stack)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    public static ISqueezerContainerRecipe getSqueezerContainerRecipe(ServerLevel level, ItemStack stack) {
        if (!FluidContainerHelper.isDrainableFilledContainer(stack)) {
            return null;
        }
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof ISqueezerContainerRecipe recipe
                    && ItemStack.isSameItem(recipe.getEmptyContainer(), stack)) {
                return recipe;
            }
        }
        return null;
    }

    @Nullable
    public static ISqueezerRecipe getSqueezerRecipe(ServerLevel level, List<ItemStack> inputs) {
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof SqueezerRecipe recipe
                    && InventoryUtil.canConsumeIngredients(inputs, recipe.getInputs())) {
                return recipe;
            }
        }
        return null;
    }

    @Nullable
    public static ICarpenterRecipe getCarpenterRecipe(ServerLevel level, FluidVariant fluid, long fluidAmount,
            ItemStack boxStack, Container craftingGrid) {
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof CarpenterRecipe recipe
                    && recipe.matches(fluid, fluidAmount, boxStack, craftingGrid, level)) {
                return recipe;
            }
        }
        return null;
    }

    @Nullable
    public static IFermenterRecipe getFermenterRecipe(ServerLevel level, ItemStack inputItem, FluidVariant inputFluid,
            long inputFluidAmount) {
        if (inputItem.isEmpty() || inputFluid.isBlank() || inputFluidAmount <= 0) {
            return null;
        }
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof FermenterRecipe recipe
                    && recipe.matches(inputItem, inputFluid, inputFluidAmount)) {
                return recipe;
            }
        }
        return null;
    }

    public static boolean isFermenterInput(ServerLevel level, ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof FermenterRecipe recipe && recipe.getInputItem().test(stack)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static IHygroregulatorRecipe getHygroregulatorRecipe(ServerLevel level, FluidVariant variant, long amount) {
        if (!variant.isBlank() && amount > 0) {
            for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
                if (holder.value() instanceof HygroregulatorRecipe recipe && recipe.matches(variant, amount)) {
                    return recipe;
                }
            }
        }
        return HygroregulatorFallbackRecipes.match(variant, amount);
    }

    @Nullable
    public static IMoistenerRecipe getMoistenerRecipe(ServerLevel level, ItemStack stack) {
        if (stack.isEmpty()) {
            return null;
        }
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof MoistenerRecipe recipe && recipe.getInput().test(stack)) {
                return recipe;
            }
        }
        return null;
    }

    public static boolean isMoistenerInput(ServerLevel level, ItemStack stack) {
        return getMoistenerRecipe(level, stack) != null;
    }

    public static boolean isCarpenterBox(ServerLevel level, ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof ICarpenterRecipe recipe
                    && recipe.getBox() != null
                    && recipe.getBox().test(stack)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static IFabricatorSmeltingRecipe getFabricatorMeltingRecipe(ServerLevel level, ItemStack stack) {
        if (stack.isEmpty()) {
            return null;
        }
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof FabricatorSmeltingRecipe recipe && recipe.getInput().test(stack)) {
                return recipe;
            }
        }
        return null;
    }

    public static boolean isFabricatorSmeltingInput(ServerLevel level, ItemStack stack) {
        return getFabricatorMeltingRecipe(level, stack) != null;
    }

    @Nullable
    public static IFabricatorRecipe getFabricatorRecipe(ServerLevel level, FluidVariant liquid, long liquidAmount,
            ItemStack planStack, Container craftingGrid) {
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof FabricatorRecipe recipe
                    && recipe.matches(level, liquid, liquidAmount, planStack, craftingGrid)) {
                return recipe;
            }
        }
        return null;
    }

    public static boolean isFabricatorPlan(ServerLevel level, ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        for (RecipeHolder<?> holder : level.recipeAccess().getRecipes()) {
            if (holder.value() instanceof FabricatorRecipe recipe
                    && recipe.getPlan().map(plan -> plan.test(stack)).orElse(false)) {
                return true;
            }
        }
        return false;
    }
}
