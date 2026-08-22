package com.leon1236.reforestry.worktable.recipes;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class RecipeMemory {
	private static final int CAPACITY = 9;

	private final List<MemorizedRecipe> memorizedRecipes = new ArrayList<>(CAPACITY);
	private long lastUpdate;

	public long getLastUpdate() {
		return this.lastUpdate;
	}

	public void memorizeRecipe(long worldTime, MemorizedRecipe recipe, Level world) {
		RecipeHolder<CraftingRecipe> selectedRecipe = recipe.getSelectedRecipe(world);
		if (selectedRecipe == null) {
			return;
		}

		this.lastUpdate = worldTime;
		recipe.updateLastUse(this.lastUpdate);

		if (recipe.hasRecipeConflict()) {
			recipe.removeRecipeConflicts();
		}

		MemorizedRecipe memory = getExistingMemorizedRecipe(selectedRecipe, world);
		if (memory != null) {
			updateExistingRecipe(memory, recipe);
			return;
		}

		if (this.memorizedRecipes.size() < CAPACITY) {
			this.memorizedRecipes.add(recipe);
			return;
		}

		MemorizedRecipe oldest = getOldestUnlockedRecipe();
		if (oldest == null) {
			oldest = getOldestRecipe();
			if (oldest != null && oldest.isLocked()) {
				oldest.toggleLock();
			}
		}
		if (oldest != null) {
			this.memorizedRecipes.remove(oldest);
			this.memorizedRecipes.add(recipe);
		}
	}

	private void updateExistingRecipe(MemorizedRecipe existingRecipe, MemorizedRecipe updatedRecipe) {
		if (existingRecipe.isLocked() != updatedRecipe.isLocked()) {
			updatedRecipe.toggleLock();
		}
		int index = this.memorizedRecipes.indexOf(existingRecipe);
		this.memorizedRecipes.set(index, updatedRecipe);
	}

	@Nullable
	private MemorizedRecipe getOldestUnlockedRecipe() {
		MemorizedRecipe oldest = null;
		for (MemorizedRecipe existing : this.memorizedRecipes) {
			if (oldest != null && oldest.getLastUsed() < existing.getLastUsed()) {
				continue;
			}
			if (!existing.isLocked()) {
				oldest = existing;
			}
		}
		return oldest;
	}

	@Nullable
	private MemorizedRecipe getOldestRecipe() {
		MemorizedRecipe oldest = null;
		for (MemorizedRecipe existing : this.memorizedRecipes) {
			if (oldest == null || existing.getLastUsed() < oldest.getLastUsed()) {
				oldest = existing;
			}
		}
		return oldest;
	}

	@Nullable
	public MemorizedRecipe getRecipe(int recipeIndex) {
		if (recipeIndex < 0 || recipeIndex >= this.memorizedRecipes.size()) {
			return null;
		}
		return this.memorizedRecipes.get(recipeIndex);
	}

	public ItemStack getRecipeDisplayOutput(Level level, int recipeIndex) {
		MemorizedRecipe recipe = getRecipe(recipeIndex);
		if (recipe == null) {
			return ItemStack.EMPTY;
		}
		return recipe.getOutputIcon(level);
	}

	public boolean isLocked(int recipeIndex) {
		MemorizedRecipe recipe = getRecipe(recipeIndex);
		return recipe != null && recipe.isLocked();
	}

	public void toggleLock(long worldTime, int recipeIndex) {
		this.lastUpdate = worldTime;
		if (this.memorizedRecipes.size() > recipeIndex) {
			this.memorizedRecipes.get(recipeIndex).toggleLock();
		}
	}

	@Nullable
	private MemorizedRecipe getExistingMemorizedRecipe(@Nullable RecipeHolder<CraftingRecipe> recipe, Level world) {
		if (recipe == null) {
			return null;
		}
		for (MemorizedRecipe memorizedRecipe : this.memorizedRecipes) {
			if (memorizedRecipe.getRecipes(world).contains(recipe)) {
				return memorizedRecipe;
			}
		}
		return null;
	}

	public void save(ValueOutput output) {
		ValueOutput.ValueOutputList list = output.childrenList("RecipeMemory");
		for (MemorizedRecipe recipe : this.memorizedRecipes) {
			if (recipe != null && recipe.hasSelectedRecipe()) {
				recipe.save(list.addChild());
			}
		}
	}

	public void load(ValueInput input) {
		this.memorizedRecipes.clear();
		for (ValueInput recipeInput : input.childrenListOrEmpty("RecipeMemory")) {
			MemorizedRecipe recipe = new MemorizedRecipe();
			recipe.load(recipeInput);
			if (recipe.hasSelectedRecipe()) {
				this.memorizedRecipes.add(recipe);
			}
		}
	}
}
