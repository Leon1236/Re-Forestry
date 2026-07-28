package com.leon1236.reforestry.core.compat.jei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

public final class JeiRecipeSources {
	private JeiRecipeSources() {
	}

	public static <T> List<T> collect(Class<T> recipeClass) {
		Minecraft minecraft = Minecraft.getInstance();
		Level level = minecraft.level;
		if (level == null) {
			return List.of();
		}
		List<T> recipes = new ArrayList<>();
		for (RecipeHolder<?> holder : level.recipeAccess().getSynchronizedRecipes().recipes()) {
			if (recipeClass.isInstance(holder.value())) {
				recipes.add(recipeClass.cast(holder.value()));
			}
		}
		return recipes;
	}
}
