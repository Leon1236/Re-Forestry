package com.leon1236.reforestry.extratrees.recipes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.core.fluids.FluidUnits;

public final class BreweryRecipeManager {
	private static final List<BreweryRecipe> RECIPES = new ArrayList<>();
	public static final long BUCKET_DROPLETS = FluidUnits.mbToDroplets(1000);

	private BreweryRecipeManager() {
	}

	public static void addJuiceRecipe(Fluid input, Fluid output) {
		RECIPES.add(BreweryRecipe.juice(input, output, ItemStack.EMPTY));
	}

	public static void addJuiceRecipe(Fluid input, Fluid output, ItemStack yeast) {
		RECIPES.add(BreweryRecipe.juice(input, output, yeast));
	}

	public static void addGrainRecipe(TagKey<Item> grain, Fluid output, @Nullable TagKey<Item> ingredient, ItemStack yeast) {
		RECIPES.add(BreweryRecipe.grain(grain, output, ingredient, yeast));
	}

	@Nullable
	public static BreweryRecipe find(BreweryCrafting crafting) {
		if (crafting.inputFluid() == null || crafting.yeast().isEmpty()) {
			return null;
		}
		for (BreweryRecipe recipe : RECIPES) {
			if (recipe.matches(crafting)) {
				return recipe;
			}
		}
		return null;
	}

	public static boolean isValidGrain(ItemStack stack) {
		for (BreweryRecipe recipe : RECIPES) {
			if (recipe.isGrain(stack)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidYeast(ItemStack stack) {
		for (BreweryRecipe recipe : RECIPES) {
			if (ItemStack.isSameItem(recipe.requiredYeast(), stack)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidIngredient(ItemStack stack) {
		for (BreweryRecipe recipe : RECIPES) {
			if (recipe.isIngredient(stack)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidInput(Fluid fluid) {
		if (fluid == Fluids.WATER || fluid == Fluids.FLOWING_WATER) {
			for (BreweryRecipe recipe : RECIPES) {
				if (recipe.grainTag() != null) {
					return true;
				}
			}
		}
		for (BreweryRecipe recipe : RECIPES) {
			if (recipe.inputFluid() == fluid) {
				return true;
			}
		}
		return false;
	}

	public static Collection<BreweryRecipe> recipes() {
		return Collections.unmodifiableList(RECIPES);
	}

	public record BreweryCrafting(Fluid inputFluid, ItemStack ingredient, ItemStack[] grains, ItemStack yeast) {
	}

	public record BreweryRecipe(
			@Nullable Fluid inputFluid,
			Fluid outputFluid,
			ItemStack yeast,
			@Nullable TagKey<Item> grainTag,
			@Nullable TagKey<Item> ingredientTag
	) {
		public static BreweryRecipe juice(Fluid input, Fluid output, @Nullable ItemStack yeast) {
			ItemStack yeastStack = yeast == null || yeast.isEmpty()
					? ItemStack.EMPTY
					: yeast.copy();
			return new BreweryRecipe(input, output, yeastStack, null, null);
		}

		public static BreweryRecipe grain(TagKey<Item> grain, Fluid output, @Nullable TagKey<Item> ingredient, ItemStack yeast) {
			return new BreweryRecipe(Fluids.WATER, output, yeast.copy(), grain, ingredient);
		}

		public boolean matches(BreweryCrafting crafting) {
			if (crafting.yeast().isEmpty()) {
				return false;
			}
			ItemStack requiredYeast = yeast.isEmpty()
					? new ItemStack(com.leon1236.reforestry.extratrees.features.ExtraTreesItems.YEAST.item())
					: yeast;
			if (!ItemStack.isSameItem(requiredYeast, crafting.yeast())) {
				return false;
			}
			if (grainTag != null) {
				if (crafting.inputFluid() != Fluids.WATER && crafting.inputFluid() != Fluids.FLOWING_WATER) {
					return false;
				}
				int grainCount = 0;
				for (ItemStack stack : crafting.grains()) {
					if (stack != null && !stack.isEmpty() && stack.is(grainTag)) {
						grainCount++;
					}
				}
				if (grainCount < 2) {
					return false;
				}
				if (ingredientTag == null) {
					return crafting.ingredient().isEmpty();
				}
				return !crafting.ingredient().isEmpty() && crafting.ingredient().is(ingredientTag);
			}
			if (crafting.inputFluid() != inputFluid) {
				return false;
			}
			if (!crafting.ingredient().isEmpty()) {
				return false;
			}
			for (ItemStack stack : crafting.grains()) {
				if (stack != null && !stack.isEmpty()) {
					return false;
				}
			}
			return true;
		}

		public boolean isGrain(ItemStack stack) {
			return grainTag != null && !stack.isEmpty() && stack.is(grainTag);
		}

		public boolean isIngredient(ItemStack stack) {
			return ingredientTag != null && !stack.isEmpty() && stack.is(ingredientTag);
		}

		public ItemStack requiredYeast() {
			if (!yeast.isEmpty()) {
				return yeast;
			}
			return new ItemStack(com.leon1236.reforestry.extratrees.features.ExtraTreesItems.YEAST.item());
		}
	}
}
