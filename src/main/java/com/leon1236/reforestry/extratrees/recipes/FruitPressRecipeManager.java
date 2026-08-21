package com.leon1236.reforestry.extratrees.recipes;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.core.fluids.FluidUnits;

public final class FruitPressRecipeManager {
	private static final Map<Item, FruitPressRecipe> RECIPES = new LinkedHashMap<>();

	private FruitPressRecipeManager() {
	}

	public static void addRecipe(ItemStack input, Fluid output, int amountMb) {
		if (input.isEmpty() || output == null || amountMb <= 0) {
			return;
		}
		Item item = input.getItem();
		if (RECIPES.containsKey(item)) {
			return;
		}
		RECIPES.put(item, new FruitPressRecipe(item, output, FluidUnits.mbToDroplets(amountMb), amountMb));
	}

	public static boolean isInput(ItemStack stack) {
		return getRecipe(stack) != null;
	}

	@Nullable
	public static FruitPressRecipe getRecipe(ItemStack stack) {
		if (stack.isEmpty()) {
			return null;
		}
		return RECIPES.get(stack.getItem());
	}

	public static Collection<FruitPressRecipe> recipes() {
		return Collections.unmodifiableCollection(RECIPES.values());
	}

	public record FruitPressRecipe(Item input, Fluid output, long amountDroplets, int amountMb) {
		public ItemStack inputStack() {
			return new ItemStack(input);
		}

		public String id() {
			return BuiltInRegistries.ITEM.getKey(input).getPath();
		}
	}
}
