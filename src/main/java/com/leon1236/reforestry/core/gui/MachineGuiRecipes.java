package com.leon1236.reforestry.core.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.leon1236.reforestry.api.gui.MachineRecipeEntry;
import com.leon1236.reforestry.core.recipes.CraftingPatternHelper;
import com.leon1236.reforestry.factory.recipes.CarpenterRecipe;
import com.leon1236.reforestry.factory.recipes.FabricatorRecipe;

public final class MachineGuiRecipes {
	private MachineGuiRecipes() {
	}

	public static List<MachineRecipeEntry> fabricator(Level level) {
		List<MachineRecipeEntry> entries = new ArrayList<>();
		for (RecipeHolder<?> holder : recipes(level)) {
			if (holder.value() instanceof FabricatorRecipe recipe) {
				ItemStack result = recipe.getResultStack();
				if (result.isEmpty()) {
					continue;
				}
				entries.add(MachineRecipeEntry.of(result,
						CraftingPatternHelper.patternSlotsFromCraftingRecipe(recipe.getCraftingGridRecipe()),
						recipe.molten().variant(),
						recipe.molten().amountMb()));
			}
		}
		return mergeByResultItem(entries);
	}

	public static List<MachineRecipeEntry> carpenter(Level level) {
		List<MachineRecipeEntry> entries = new ArrayList<>();
		for (RecipeHolder<?> holder : recipes(level)) {
			if (holder.value() instanceof CarpenterRecipe recipe) {
				ItemStack result = recipe.getResultStack();
				if (result.isEmpty()) {
					continue;
				}
				List<List<ItemStack>> pattern = CraftingPatternHelper.patternSlotsFromCraftingRecipe(recipe.getCraftingGridRecipe());
				if (recipe.liquid().isPresent()) {
					var liquid = recipe.liquid().get();
					entries.add(MachineRecipeEntry.of(result, pattern, liquid.variant(), liquid.amountMb()));
				} else {
					entries.add(MachineRecipeEntry.of(result, pattern));
				}
			}
		}
		return mergeByResultItem(entries);
	}

	private static List<MachineRecipeEntry> mergeByResultItem(List<MachineRecipeEntry> entries) {
		Map<Item, MachineRecipeEntry> merged = new LinkedHashMap<>();
		for (MachineRecipeEntry entry : entries) {
			Item key = entry.result().getItem();
			MachineRecipeEntry existing = merged.get(key);
			if (existing == null) {
				merged.put(key, entry);
			} else {
				merged.put(key, mergeEntries(existing, entry));
			}
		}
		return List.copyOf(merged.values());
	}

	private static MachineRecipeEntry mergeEntries(MachineRecipeEntry first, MachineRecipeEntry second) {
		ItemStack result = first.result();
		OptionalFluid liquid = preferLiquid(first, second);
		if (!first.hasPattern() && !second.hasPattern()) {
			return liquid.present()
					? MachineRecipeEntry.of(result, liquid.variant(), liquid.amountMb())
					: MachineRecipeEntry.of(result);
		}
		List<List<ItemStack>> pattern = mergePatterns(first, second);
		return liquid.present()
				? MachineRecipeEntry.of(result, pattern, liquid.variant(), liquid.amountMb())
				: MachineRecipeEntry.of(result, pattern);
	}

	private static List<List<ItemStack>> mergePatterns(MachineRecipeEntry first, MachineRecipeEntry second) {
		List<List<ItemStack>> merged = new ArrayList<>(9);
		for (int slot = 0; slot < 9; slot++) {
			List<ItemStack> alternatives = new ArrayList<>();
			appendUnique(alternatives, first.hasPattern() ? first.pattern().get(slot) : List.of());
			appendUnique(alternatives, second.hasPattern() ? second.pattern().get(slot) : List.of());
			merged.add(List.copyOf(alternatives));
		}
		return merged;
	}

	private static void appendUnique(List<ItemStack> into, List<ItemStack> from) {
		for (ItemStack stack : from) {
			if (stack.isEmpty()) {
				continue;
			}
			boolean exists = false;
			for (ItemStack existing : into) {
				if (ItemStack.isSameItemSameComponents(existing, stack)) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				into.add(stack.copyWithCount(1));
			}
		}
	}

	private static OptionalFluid preferLiquid(MachineRecipeEntry first, MachineRecipeEntry second) {
		if (first.hasLiquid()) {
			return new OptionalFluid(first.liquid().orElse(null), first.liquidAmountMb());
		}
		if (second.hasLiquid()) {
			return new OptionalFluid(second.liquid().orElse(null), second.liquidAmountMb());
		}
		return OptionalFluid.NONE;
	}

	private static Collection<RecipeHolder<?>> recipes(Level level) {
		return level.recipeAccess().getSynchronizedRecipes().recipes();
	}

	private record OptionalFluid(FluidVariant variant, int amountMb) {
		private static final OptionalFluid NONE = new OptionalFluid(null, 0);

		private boolean present() {
			return variant != null && !variant.isBlank() && amountMb > 0;
		}
	}
}
