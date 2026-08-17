package com.leon1236.reforestry.core.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.features.CoreItems;

@JeiPlugin
public class CoreJeiPlugin implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("core");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		JeiDescriptions.addDescription(registration, CoreItems.COMPOST);
		JeiDescriptions.addDescription(registration, CoreItems.MULCH);
		JeiDescriptions.addDescription(registration, CoreItems.FERTILIZER_COMPOUND);
	}
}
