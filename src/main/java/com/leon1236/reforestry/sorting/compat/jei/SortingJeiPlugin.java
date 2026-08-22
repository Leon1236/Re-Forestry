package com.leon1236.reforestry.sorting.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.core.compat.jei.JeiDescriptions;
import com.leon1236.reforestry.sorting.features.SortingBlocks;

@JeiPlugin
public class SortingJeiPlugin implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("sorting");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if (!sortingLoaded()) {
			return;
		}
		JeiDescriptions.addDescription(registration, SortingBlocks.FILTER.block());
	}

	private static boolean sortingLoaded() {
		return IForestryApi.get().getModuleManager().isModuleEnabled(ReForestry.id("sorting"));
	}
}
