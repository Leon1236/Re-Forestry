package com.leon1236.reforestry.api.gui;

import java.util.List;

import net.minecraft.world.entity.player.Player;

public interface IContainerRecipeBook {
	int RECIPE_BUTTON_BASE = 1000;

	List<MachineRecipeEntry> getGuiRecipes();

	boolean selectRecipe(int index, Player player);

	static boolean isRecipeButton(int id) {
		return id >= RECIPE_BUTTON_BASE;
	}

	static int recipeIndex(int id) {
		return id - RECIPE_BUTTON_BASE;
	}

	static int recipeButtonId(int index) {
		return RECIPE_BUTTON_BASE + index;
	}
}
