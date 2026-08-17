package com.leon1236.reforestry.worktable.recipes;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.worktable.inventory.WorktableCraftingContainer;

public class MemorizedRecipe {
	private WorktableCraftingContainer craftMatrix = new WorktableCraftingContainer();
	private List<RecipeHolder<CraftingRecipe>> recipes = new ArrayList<>();
	private final List<ResourceKey<Recipe<?>>> recipeIds = new ArrayList<>();
	private int selectedRecipe;
	private long lastUsed;
	private boolean locked;

	public MemorizedRecipe() {
	}

	public MemorizedRecipe(Container craftMatrix, List<RecipeHolder<CraftingRecipe>> recipes) {
		WorktableRecipeLookup.deepCopy(craftMatrix, this.craftMatrix);
		this.recipes = new ArrayList<>(recipes);
		for (RecipeHolder<CraftingRecipe> recipe : recipes) {
			this.recipeIds.add(recipe.id());
		}
	}

	public WorktableCraftingContainer getCraftMatrix() {
		return this.craftMatrix;
	}

	public void setCraftMatrix(WorktableCraftingContainer usedMatrix) {
		this.craftMatrix = usedMatrix;
	}

	public void incrementRecipe() {
		if (getRecipes().isEmpty()) {
			return;
		}
		this.selectedRecipe++;
		if (this.selectedRecipe >= this.recipes.size()) {
			this.selectedRecipe = 0;
		}
	}

	public void decrementRecipe() {
		if (getRecipes().isEmpty()) {
			return;
		}
		this.selectedRecipe--;
		if (this.selectedRecipe < 0) {
			this.selectedRecipe = this.recipes.size() - 1;
		}
	}

	public boolean hasRecipeConflict() {
		return getRecipes().size() > 1;
	}

	public void removeRecipeConflicts() {
		RecipeHolder<CraftingRecipe> recipe = getSelectedRecipe();
		this.recipes.clear();
		this.recipeIds.clear();
		if (recipe != null) {
			this.recipes.add(recipe);
			this.recipeIds.add(recipe.id());
		}
		this.selectedRecipe = 0;
	}

	public ItemStack getOutputIcon(Level level) {
		RecipeHolder<CraftingRecipe> selected = getSelectedRecipe(level);
		if (selected != null) {
			ItemStack output = selected.value().assemble(this.craftMatrix.asCraftInput());
			if (!output.isEmpty()) {
				return output;
			}
		}
		return ItemStack.EMPTY;
	}

	public ItemStack getCraftingResult(CraftingInput input, Level level) {
		RecipeHolder<CraftingRecipe> selected = getSelectedRecipe(level);
		if (selected == null) {
			return ItemStack.EMPTY;
		}
		CraftingRecipe recipe = selected.value();
		if (recipe.matches(input, level)) {
			ItemStack output = recipe.assemble(input);
			if (!output.isEmpty()) {
				return output;
			}
		}
		return ItemStack.EMPTY;
	}

	public boolean hasRecipes() {
		return !this.recipes.isEmpty() || !this.recipeIds.isEmpty();
	}

	public boolean hasSelectedRecipe() {
		return hasRecipes() && this.selectedRecipe >= 0 && this.recipeIds.size() > this.selectedRecipe;
	}

	public List<RecipeHolder<CraftingRecipe>> getRecipes() {
		return getRecipes(null);
	}

	public List<RecipeHolder<CraftingRecipe>> getRecipes(@Nullable Level level) {
		if (this.recipes.isEmpty() && !this.recipeIds.isEmpty() && level != null) {
			for (ResourceKey<Recipe<?>> key : this.recipeIds) {
				RecipeHolder<CraftingRecipe> recipe = WorktableRecipeLookup.byId(level, key);
				if (recipe != null) {
					this.recipes.add(recipe);
				}
			}
			if (this.selectedRecipe >= this.recipes.size()) {
				this.selectedRecipe = 0;
			}
		}
		return this.recipes;
	}

	@Nullable
	public RecipeHolder<CraftingRecipe> getSelectedRecipe() {
		return getSelectedRecipe(null);
	}

	@Nullable
	public RecipeHolder<CraftingRecipe> getSelectedRecipe(@Nullable Level level) {
		List<RecipeHolder<CraftingRecipe>> resolved = getRecipes(level);
		if (resolved.isEmpty() || this.selectedRecipe < 0 || this.selectedRecipe >= resolved.size()) {
			return null;
		}
		return resolved.get(this.selectedRecipe);
	}

	public boolean hasRecipe(@Nullable RecipeHolder<CraftingRecipe> recipe) {
		return recipe != null && getRecipes().contains(recipe);
	}

	public void updateLastUse(long lastUsed) {
		this.lastUsed = lastUsed;
	}

	public long getLastUsed() {
		return this.lastUsed;
	}

	public void toggleLock() {
		this.locked = !this.locked;
	}

	public boolean isLocked() {
		return this.locked;
	}

	public void save(ValueOutput output) {
		NonNullList<ItemStack> items = InventoryUtil.getStacks(this.craftMatrix);
		ContainerHelper.saveAllItems(output.child("inventory"), items);
		output.putLong("LastUsed", this.lastUsed);
		output.putBoolean("Locked", this.locked);
		output.putInt("SelectedRecipe", this.selectedRecipe);
		ValueOutput.TypedOutputList<Identifier> recipesOut = output.list("Recipes", Identifier.CODEC);
		for (ResourceKey<Recipe<?>> recipeId : this.recipeIds) {
			recipesOut.add(recipeId.identifier());
		}
	}

	public void load(ValueInput input) {
		NonNullList<ItemStack> items = NonNullList.withSize(this.craftMatrix.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(input.childOrEmpty("inventory"), items);
		for (int i = 0; i < items.size(); i++) {
			this.craftMatrix.setItem(i, items.get(i));
		}
		this.lastUsed = input.getLongOr("LastUsed", 0L);
		this.locked = input.getBooleanOr("Locked", false);
		this.selectedRecipe = input.getIntOr("SelectedRecipe", 0);
		this.recipes.clear();
		this.recipeIds.clear();
		for (Identifier recipeId : input.listOrEmpty("Recipes", Identifier.CODEC)) {
			this.recipeIds.add(ResourceKey.create(Registries.RECIPE, recipeId));
		}
		if (this.selectedRecipe >= this.recipeIds.size()) {
			this.selectedRecipe = 0;
		}
	}
}
