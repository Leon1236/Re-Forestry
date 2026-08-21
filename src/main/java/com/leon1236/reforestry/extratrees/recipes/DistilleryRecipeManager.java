package com.leon1236.reforestry.extratrees.recipes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.level.material.Fluid;

public final class DistilleryRecipeManager {
	private static final int LEVELS = 3;
	private static final List<Map<Fluid, DistilleryRecipe>> BY_LEVEL = new ArrayList<>();
	private static final List<DistilleryRecipe> ALL = new ArrayList<>();

	static {
		for (int i = 0; i < LEVELS; i++) {
			BY_LEVEL.add(new HashMap<>());
		}
	}

	private DistilleryRecipeManager() {
	}

	public static void addRecipe(Fluid input, long inputAmount, Fluid output, long outputAmount, int level) {
		if (level < 0 || level >= LEVELS) {
			return;
		}
		DistilleryRecipe recipe = new DistilleryRecipe(input, inputAmount, output, outputAmount, level);
		BY_LEVEL.get(level).put(input, recipe);
		ALL.add(recipe);
	}

	@Nullable
	public static DistilleryRecipe getRecipe(@Nullable Fluid input, int level) {
		if (input == null || level < 0 || level >= LEVELS) {
			return null;
		}
		return BY_LEVEL.get(level).get(input);
	}

	public static boolean isValidInput(Fluid fluid) {
		for (int i = 0; i < LEVELS; i++) {
			if (BY_LEVEL.get(i).containsKey(fluid)) {
				return true;
			}
		}
		return false;
	}

	public static Collection<DistilleryRecipe> recipes() {
		return Collections.unmodifiableList(ALL);
	}

	public static Collection<DistilleryRecipe> recipes(int level) {
		if (level < 0 || level >= LEVELS) {
			return List.of();
		}
		return Collections.unmodifiableCollection(BY_LEVEL.get(level).values());
	}

	public record DistilleryRecipe(Fluid input, long inputAmount, Fluid output, long outputAmount, int level) {
	}
}
