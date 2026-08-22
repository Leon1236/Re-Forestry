package com.leon1236.reforestry.farming.compat.jei;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.agriculture.IFarmCircuit;
import com.leon1236.reforestry.api.circuits.CircuitHolder;
import com.leon1236.reforestry.core.circuits.EnumCircuitBoardType;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.core.features.CoreItems;

@JeiPlugin
public class FarmingJeiPlugin implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("farming");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		if (!farmingLoaded()) {
			return;
		}
		IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
		registration.addRecipeCategories(new FarmingInfoRecipeCategory(guiHelper));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if (!farmingLoaded()) {
			return;
		}
		registration.addRecipes(ReforestryJeiRecipeTypes.FARMING, getRecipes());
	}

	public static List<FarmingInfoRecipe> getRecipes() {
		ArrayList<FarmingInfoRecipe> info = new ArrayList<>();
		for (CircuitHolder holder : IForestryApi.get().getCircuitManager().getCircuitHolders()) {
			if (holder.circuit() instanceof IFarmCircuit circuit && circuit.isManual()) {
				info.add(new FarmingInfoRecipe(holder.stack(), circuit.getProperties(), circuit));
			}
		}
		return info;
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		if (!farmingLoaded()) {
			return;
		}
		registration.addCraftingStation(
				ReforestryJeiRecipeTypes.FARMING,
				new ItemStack(CoreItems.CIRCUITBOARDS.item(EnumCircuitBoardType.INTRICATE))
		);
	}

	private static boolean farmingLoaded() {
		return IForestryApi.get().getModuleManager().isModuleEnabled(ReForestry.id("farming"));
	}
}
