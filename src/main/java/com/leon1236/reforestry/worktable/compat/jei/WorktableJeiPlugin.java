package com.leon1236.reforestry.worktable.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.compat.jei.JeiDescriptions;
import com.leon1236.reforestry.worktable.client.ScreenWorktable;
import com.leon1236.reforestry.worktable.features.WorktableBlocks;

@JeiPlugin
public class WorktableJeiPlugin implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("worktable");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registry) {
		JeiDescriptions.addDescription(registry, WorktableBlocks.WORKTABLE.block());
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registry) {
		registry.addRecipeTransferHandler(new WorktableRecipeTransferHandler(), RecipeTypes.CRAFTING);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registry) {
		registry.addRecipeClickArea(ScreenWorktable.class, 65, 43, 10, 6, RecipeTypes.CRAFTING);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
		registry.addCraftingStation(RecipeTypes.CRAFTING, WorktableBlocks.WORKTABLE.block());
	}
}
