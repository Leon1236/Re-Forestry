package com.leon1236.reforestry.worktable.compat.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.types.IRecipeType;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.core.network.packets.WorktableRecipeRequestPayload;
import com.leon1236.reforestry.worktable.features.WorktableMenuTypes;
import com.leon1236.reforestry.worktable.gui.ContainerWorktable;

public class WorktableRecipeTransferHandler implements IRecipeTransferHandler<ContainerWorktable, RecipeHolder<CraftingRecipe>> {
	@Override
	public Class<? extends ContainerWorktable> getContainerClass() {
		return ContainerWorktable.class;
	}

	@Override
	public Optional<MenuType<ContainerWorktable>> getMenuType() {
		return Optional.of(WorktableMenuTypes.WORKTABLE.type());
	}

	@Override
	public IRecipeType<RecipeHolder<CraftingRecipe>> getRecipeType() {
		return RecipeTypes.CRAFTING;
	}

	@Nullable
	@Override
	public IRecipeTransferError transferRecipe(
			ContainerWorktable container,
			RecipeHolder<CraftingRecipe> recipe,
			IRecipeSlotsView recipeSlots,
			Player player,
			boolean maxTransfer,
			boolean doTransfer
	) {
		if (!doTransfer) {
			return null;
		}
		ClientPlayNetworking.send(WorktableRecipeRequestPayload.of(
				container.getTile().getBlockPos(),
				ghostStacks(recipeSlots)));
		return null;
	}

	private static List<ItemStack> ghostStacks(IRecipeSlotsView recipeSlots) {
		List<IRecipeSlotView> inputs = recipeSlots.getSlotViews(RecipeIngredientRole.INPUT);
		List<ItemStack> stacks = new ArrayList<>(WorktableRecipeRequestPayload.SLOT_COUNT);
		int count = Math.min(inputs.size(), WorktableRecipeRequestPayload.SLOT_COUNT);
		for (int i = 0; i < count; i++) {
			ItemStack stack = inputs.get(i).getDisplayedItemStack().orElse(ItemStack.EMPTY);
			stacks.add(stack.isEmpty() ? ItemStack.EMPTY : stack.copy());
		}
		while (stacks.size() < WorktableRecipeRequestPayload.SLOT_COUNT) {
			stacks.add(ItemStack.EMPTY);
		}
		return stacks;
	}
}
