package com.leon1236.reforestry.factory.compat.jei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import mezz.jei.api.runtime.IIngredientManager;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.core.features.FluidsItems;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.ForestryFluids;
import com.leon1236.reforestry.core.items.definitions.EnumContainerType;
import com.leon1236.reforestry.factory.recipes.BottlerRecipe;

public final class BottlerRecipeMaker {
	private BottlerRecipeMaker() {
	}

	public static List<BottlerRecipe> getRecipes(IIngredientManager ingredientManager) {
		List<BottlerRecipe> recipes = new ArrayList<>();
		Set<String> seen = new LinkedHashSet<>();
		List<Fluid> fluids = sourceFluids();

		for (ItemStack stack : candidateStacks(ingredientManager)) {
			if (stack.isEmpty()) {
				continue;
			}

			if (FluidContainerHelper.isFilledContainer(stack)) {
				BottlerRecipe emptying = BottlerRecipe.createEmptyingRecipe(stack);
				if (emptying != null) {
					addUnique(recipes, seen, emptying);
				}
			}

			if (!FluidContainerHelper.isFillableContainerWithRoom(stack)) {
				continue;
			}
			for (Fluid fluid : fluids) {
				BottlerRecipe filling = BottlerRecipe.createFillingRecipe(fluid, stack);
				if (filling != null) {
					addUnique(recipes, seen, filling);
				}
			}
		}

		return recipes;
	}

	private static Collection<ItemStack> candidateStacks(IIngredientManager ingredientManager) {
		List<ItemStack> stacks = new ArrayList<>(ingredientManager.getAllItemStacks());

		for (EnumContainerType type : EnumContainerType.values()) {
			stacks.add(new ItemStack(FluidsItems.CONTAINERS.item(type)));
			for (ForestryFluids fluid : ForestryFluids.values()) {
				stacks.add(FluidsItems.createFilled(type, fluid));
			}
			stacks.add(FluidsItems.createFilled(type, Fluids.WATER));
			stacks.add(FluidsItems.createFilled(type, Fluids.LAVA));
		}

		stacks.add(new ItemStack(Items.BUCKET));
		stacks.add(new ItemStack(Items.WATER_BUCKET));
		stacks.add(new ItemStack(Items.LAVA_BUCKET));
		stacks.add(new ItemStack(Items.POWDER_SNOW_BUCKET));

		for (ForestryFluids fluid : ForestryFluids.values()) {
			stacks.add(new ItemStack(fluid.getBucket()));
		}

		return stacks;
	}

	private static List<Fluid> sourceFluids() {
		List<Fluid> fluids = new ArrayList<>();
		for (Fluid fluid : BuiltInRegistries.FLUID) {
			if (fluid == Fluids.EMPTY) {
				continue;
			}
			if (!fluid.defaultFluidState().isSource()) {
				continue;
			}
			fluids.add(fluid);
		}
		return fluids;
	}

	private static void addUnique(List<BottlerRecipe> recipes, Set<String> seen, BottlerRecipe recipe) {
		String key = recipeKey(recipe);
		if (seen.add(key)) {
			recipes.add(recipe);
		}
	}

	private static String recipeKey(BottlerRecipe recipe) {
		return (recipe.fillRecipe ? "fill|" : "empty|")
				+ ItemStack.hashItemAndComponents(recipe.inputStack)
				+ '|'
				+ BuiltInRegistries.FLUID.getKey(recipe.fluid.getFluid())
				+ '|'
				+ recipe.fluidAmount
				+ '|'
				+ ItemStack.hashItemAndComponents(recipe.outputStack);
	}
}
