package com.leon1236.reforestry.core.compat.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.RecipeIngredientRole;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;

import com.leon1236.reforestry.core.recipes.CraftingPatternHelper;

public final class JeiRecipeLayoutHelper {
	private JeiRecipeLayoutHelper() {
	}

	public static List<IRecipeSlotBuilder> layoutSlotGrid(
			IRecipeLayoutBuilder builder,
			RecipeIngredientRole role,
			int width,
			int height,
			int xOffset,
			int yOffset,
			int slotSpacing
	) {
		List<IRecipeSlotBuilder> slots = new ArrayList<>();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				slots.add(builder.addSlot(role, xOffset + x * slotSpacing, yOffset + y * slotSpacing));
			}
		}
		return slots;
	}

	public static void setCraftingItems(
			List<IRecipeSlotBuilder> craftingSlots,
			CraftingRecipe craftingGridRecipe,
			ICraftingGridHelper craftingGridHelper
	) {
		int width = 0;
		int height = 0;
		List<List<ItemStack>> inputs = new ArrayList<>();

		if (craftingGridRecipe instanceof ShapedRecipe shaped) {
			width = shaped.getWidth();
			height = shaped.getHeight();
			for (Optional<Ingredient> cell : shaped.getIngredients()) {
				inputs.add(stacksFromOptional(cell));
			}
		} else {
			for (Ingredient ingredient : craftingGridRecipe.placementInfo().ingredients()) {
				inputs.add(stacksFromIngredient(ingredient));
			}
		}

		craftingGridHelper.setInputs(craftingSlots, VanillaTypes.ITEM_STACK, inputs, width, height);
	}

	private static List<ItemStack> stacksFromOptional(Optional<Ingredient> cell) {
		if (cell.isEmpty()) {
			return List.of();
		}
		return stacksFromIngredient(cell.get());
	}

	private static List<ItemStack> stacksFromIngredient(Ingredient ingredient) {
		if (ingredient == null || ingredient.isEmpty()) {
			return List.of();
		}
		List<ItemStack> stacks = ingredient.items()
				.map(ItemStack::new)
				.filter(stack -> !stack.isEmpty())
				.toList();
		if (!stacks.isEmpty()) {
			return stacks;
		}
		ItemStack fallback = CraftingPatternHelper.firstStack(ingredient);
		return fallback.isEmpty() ? List.of() : List.of(fallback);
	}
}
