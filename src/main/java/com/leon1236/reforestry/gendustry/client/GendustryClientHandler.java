package com.leon1236.reforestry.gendustry.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.gendustry.features.GMenus;

@Environment(EnvType.CLIENT)
public class GendustryClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		GendustryFluidClientHandler.registerClient();
		MenuScreens.register(GMenus.PROCESSOR.type(), ScreenProducer::new);
		MenuScreens.register(GMenus.SAMPLER.type(), ScreenThreeInput::new);
	}
}
