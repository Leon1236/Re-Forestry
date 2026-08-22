package com.leon1236.reforestry.energy.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.core.compat.jei.JeiDescriptions;
import com.leon1236.reforestry.energy.blocks.EngineBlockType;
import com.leon1236.reforestry.energy.features.EnergyBlocks;

@JeiPlugin
public class EnergyJeiPlugin implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("energy");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if (!energyLoaded()) {
			return;
		}
		JeiDescriptions.addDescription(registration, EnergyBlocks.ENGINES.get(EngineBlockType.PEAT).block());
		JeiDescriptions.addDescription(registration, EnergyBlocks.ENGINES.get(EngineBlockType.BIOGAS).block());
		JeiDescriptions.addDescription(registration, EnergyBlocks.ENGINES.get(EngineBlockType.CLOCKWORK).block());
	}

	private static boolean energyLoaded() {
		return IForestryApi.get().getModuleManager().isModuleEnabled(ReForestry.id("energy"));
	}
}
