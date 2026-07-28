package com.leon1236.reforestry.core.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.core.Holder;
import net.minecraft.recipebook.PlaceRecipeHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;

public final class CraftingPatternHelper {
	private CraftingPatternHelper() {
	}

	public static List<List<ItemStack>> patternSlotsFromCraftingRecipe(CraftingRecipe recipe) {
		@SuppressWarnings("unchecked")
		List<ItemStack>[] ghost = new List[9];
		Arrays.fill(ghost, List.of());

		if (recipe instanceof ShapedRecipe shaped) {
			List<Optional<Ingredient>> cells = shaped.getIngredients();
			PlaceRecipeHelper.placeRecipe(3, 3, shaped.getWidth(), shaped.getHeight(), cells,
					(optional, gridIndex, gridX, gridY) -> ghost[gridIndex] = stacksFromOptional(optional));
		} else {
			List<Ingredient> ingredients = recipe.placementInfo().ingredients();
			PlaceRecipeHelper.placeRecipe(3, 3, recipe, ingredients,
					(ingredient, gridIndex, gridX, gridY) -> ghost[gridIndex] = stacksFromIngredient(ingredient));
		}

		List<List<ItemStack>> result = new ArrayList<>(9);
		for (List<ItemStack> slot : ghost) {
			result.add(List.copyOf(slot));
		}
		return result;
	}

	public static List<ItemStack> patternFromCraftingRecipe(CraftingRecipe recipe) {
		List<List<ItemStack>> slots = patternSlotsFromCraftingRecipe(recipe);
		List<ItemStack> result = new ArrayList<>(9);
		for (List<ItemStack> slot : slots) {
			result.add(slot.isEmpty() ? ItemStack.EMPTY : slot.getFirst().copy());
		}
		return result;
	}

	public static ItemStack firstStack(Ingredient ingredient) {
		List<ItemStack> stacks = stacksFromIngredient(ingredient);
		return stacks.isEmpty() ? ItemStack.EMPTY : stacks.getFirst().copy();
	}

	public static List<ItemStack> stacksFromIngredient(Ingredient ingredient) {
		if (ingredient == null || ingredient.isEmpty()) {
			return List.of();
		}
		List<ItemStack> stacks = matchingItems(ingredient)
				.map(ItemStack::new)
				.filter(stack -> !stack.isEmpty())
				.map(stack -> stack.copyWithCount(1))
				.toList();
		return stacks;
	}

	private static List<ItemStack> stacksFromOptional(Optional<Ingredient> cell) {
		if (cell == null || cell.isEmpty()) {
			return List.of();
		}
		return stacksFromIngredient(cell.get());
	}

	@SuppressWarnings("deprecation")
	private static Stream<Holder<Item>> matchingItems(Ingredient ingredient) {
		return ingredient.items();
	}
}
