package com.leon1236.reforestry.worktable.recipes;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.worktable.inventory.WorktableCraftingContainer;

public final class WorktableRecipeLookup {
	private WorktableRecipeLookup() {
	}

	@SuppressWarnings("unchecked")
	public static List<RecipeHolder<CraftingRecipe>> findMatching(Level level, CraftingInput input) {
		if (level == null || input.isEmpty()) {
			return List.of();
		}
		if (level instanceof ServerLevel serverLevel) {
			return serverLevel.recipeAccess().getAllMatches(RecipeType.CRAFTING, input, serverLevel).toList();
		}
		List<RecipeHolder<CraftingRecipe>> matches = new ArrayList<>();
		for (RecipeHolder<?> holder : level.recipeAccess().getSynchronizedRecipes().recipes()) {
			if (holder.value() instanceof CraftingRecipe crafting && crafting.matches(input, level)) {
				matches.add((RecipeHolder<CraftingRecipe>) holder);
			}
		}
		return matches;
	}

	@SuppressWarnings("unchecked")
	@Nullable
	public static RecipeHolder<CraftingRecipe> byId(Level level, ResourceKey<Recipe<?>> id) {
		if (level == null) {
			return null;
		}
		if (level instanceof ServerLevel serverLevel) {
			RecipeHolder<?> holder = serverLevel.recipeAccess().byKey(id).orElse(null);
			if (holder != null && holder.value() instanceof CraftingRecipe) {
				return (RecipeHolder<CraftingRecipe>) holder;
			}
			return null;
		}
		for (RecipeHolder<?> holder : level.recipeAccess().getSynchronizedRecipes().recipes()) {
			if (holder.id().equals(id) && holder.value() instanceof CraftingRecipe) {
				return (RecipeHolder<CraftingRecipe>) holder;
			}
		}
		return null;
	}

	@Nullable
	public static WorktableCraftingContainer getUsedMatrix(
			WorktableCraftingContainer originalMatrix,
			NonNullList<ItemStack> availableItems,
			Level level,
			CraftingRecipe recipe
	) {
		CraftingInput input = originalMatrix.asCraftInput();
		if (!recipe.matches(input, level)) {
			return null;
		}

		ItemStack expectedOutput = recipe.assemble(input);
		if (expectedOutput.isEmpty()) {
			return null;
		}

		WorktableCraftingContainer usedMatrix = new WorktableCraftingContainer();
		List<ItemStack> stockCopy = condenseStacks(availableItems);

		for (int slot = 0; slot < originalMatrix.getContainerSize(); slot++) {
			ItemStack stack = originalMatrix.getItem(slot);
			if (stack.isEmpty()) {
				continue;
			}
			ItemStack equivalent = getCraftingEquivalent(stockCopy, originalMatrix, slot, level, recipe, expectedOutput);
			if (equivalent.isEmpty()) {
				return null;
			}
			usedMatrix.setItem(slot, equivalent);
		}

		CraftingInput usedInput = usedMatrix.asCraftInput();
		if (recipe.matches(usedInput, level) && ItemStack.matches(recipe.assemble(usedInput), expectedOutput)) {
			return usedMatrix;
		}
		return null;
	}

	private static ItemStack getCraftingEquivalent(
			List<ItemStack> stockCopy,
			WorktableCraftingContainer originalMatrix,
			int slot,
			Level level,
			CraftingRecipe recipe,
			ItemStack expectedOutput
	) {
		ItemStack originalStack = originalMatrix.getItem(slot);
		for (ItemStack stockStack : stockCopy) {
			if (stockStack.isEmpty()) {
				continue;
			}
			ItemStack trial = stockStack.copyWithCount(1);
			originalMatrix.setItem(slot, trial);
			CraftingInput trialInput = originalMatrix.asCraftInput();
			if (recipe.matches(trialInput, level) && ItemStack.matches(recipe.assemble(trialInput), expectedOutput)) {
				originalMatrix.setItem(slot, originalStack);
				return stockStack.split(1);
			}
		}
		originalMatrix.setItem(slot, originalStack);
		return ItemStack.EMPTY;
	}

	public static boolean deleteExactSet(Container inventory, NonNullList<ItemStack> required) {
		List<ItemStack> condensedRequired = condenseStacks(required);
		List<ItemStack> condensedOffered = condenseStacks(InventoryUtil.getStacks(inventory));
		for (ItemStack req : condensedRequired) {
			if (!containsExactStack(req, condensedOffered)) {
				return false;
			}
		}
		for (ItemStack itemStack : condensedRequired) {
			deleteExactStack(inventory, itemStack);
		}
		return true;
	}

	public static void deepCopy(Container source, Container destination) {
		int size = Math.min(source.getContainerSize(), destination.getContainerSize());
		for (int i = 0; i < size; i++) {
			ItemStack stack = source.getItem(i);
			destination.setItem(i, stack.isEmpty() ? ItemStack.EMPTY : stack.copy());
		}
	}

	public static boolean equalSets(NonNullList<ItemStack> a, NonNullList<ItemStack> b) {
		if (a.size() != b.size()) {
			return false;
		}
		for (int i = 0; i < a.size(); i++) {
			if (!ItemStack.matches(a.get(i), b.get(i))) {
				return false;
			}
		}
		return true;
	}

	private static boolean containsExactStack(ItemStack req, List<ItemStack> condensedOffered) {
		for (ItemStack offer : condensedOffered) {
			if (offer.getCount() >= req.getCount() && ItemStack.isSameItemSameComponents(req, offer)) {
				return true;
			}
		}
		return false;
	}

	private static void deleteExactStack(Container inventory, ItemStack itemStack) {
		int count = itemStack.getCount();
		for (int j = 0; j < inventory.getContainerSize() && count > 0; j++) {
			ItemStack stackInSlot = inventory.getItem(j);
			if (stackInSlot.isEmpty() || !ItemStack.isSameItemSameComponents(itemStack, stackInSlot)) {
				continue;
			}
			ItemStack removed = inventory.removeItem(j, count);
			count -= removed.getCount();
		}
	}

	private static List<ItemStack> condenseStacks(List<ItemStack> stacks) {
		List<ItemStack> condensed = new ArrayList<>();
		for (ItemStack stack : stacks) {
			if (stack.isEmpty()) {
				continue;
			}
			boolean merged = false;
			for (ItemStack existing : condensed) {
				if (ItemStack.isSameItemSameComponents(existing, stack)) {
					existing.grow(stack.getCount());
					merged = true;
					break;
				}
			}
			if (!merged) {
				condensed.add(stack.copy());
			}
		}
		return condensed;
	}
}
