package com.leon1236.reforestry.extratrees.recipes;

import java.util.List;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.worktable.recipes.WorktableRecipeLookup;

public final class LumbermillRecipeHelper {
	private LumbermillRecipeHelper() {
	}

	public static ItemStack getPlankProduct(Level level, ItemStack logStack) {
		if (level == null || logStack.isEmpty() || !logStack.is(ItemTags.LOGS)) {
			return ItemStack.EMPTY;
		}
		CraftingInput input = CraftingInput.of(1, 1, List.of(logStack.copyWithCount(1)));
		for (RecipeHolder<CraftingRecipe> holder : WorktableRecipeLookup.findMatching(level, input)) {
			ItemStack result = holder.value().assemble(input);
			if (!result.isEmpty() && result.is(ItemTags.PLANKS)) {
				return result;
			}
		}
		return ItemStack.EMPTY;
	}

	public static boolean isLog(ItemStack stack) {
		return !stack.isEmpty() && stack.is(ItemTags.LOGS);
	}
}
