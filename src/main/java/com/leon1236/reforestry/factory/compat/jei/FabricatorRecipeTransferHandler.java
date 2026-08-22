package com.leon1236.reforestry.factory.compat.jei;

import java.util.List;
import java.util.Optional;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.api.gui.IContainerRecipeBook;
import com.leon1236.reforestry.api.gui.MachineRecipeEntry;
import com.leon1236.reforestry.api.recipes.IFabricatorRecipe;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.gui.ContainerFabricator;

public class FabricatorRecipeTransferHandler implements IRecipeTransferHandler<ContainerFabricator, IFabricatorRecipe> {
	@Override
	public Class<? extends ContainerFabricator> getContainerClass() {
		return ContainerFabricator.class;
	}

	@Override
	public Optional<MenuType<ContainerFabricator>> getMenuType() {
		return Optional.of(FactoryMenuTypes.FABRICATOR.type());
	}

	@Override
	public IRecipeType<IFabricatorRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.FABRICATOR;
	}

	@Nullable
	@Override
	public IRecipeTransferError transferRecipe(
			ContainerFabricator container,
			IFabricatorRecipe recipe,
			IRecipeSlotsView recipeSlots,
			Player player,
			boolean maxTransfer,
			boolean doTransfer
	) {
		if (!doTransfer) {
			return null;
		}

		ItemStack result = recipe.getResultStack();
		List<MachineRecipeEntry> recipes = container.getGuiRecipes();
		for (int i = 0; i < recipes.size(); i++) {
			if (!ItemStack.isSameItemSameComponents(recipes.get(i).result(), result)) {
				continue;
			}
			Minecraft minecraft = Minecraft.getInstance();
			MultiPlayerGameMode gameMode = minecraft.gameMode;
			if (gameMode != null) {
				gameMode.handleInventoryButtonClick(
						container.containerId,
						IContainerRecipeBook.recipeButtonId(i));
			}
			return null;
		}
		return null;
	}
}
