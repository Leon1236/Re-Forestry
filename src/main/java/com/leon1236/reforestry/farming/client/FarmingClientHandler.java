package com.leon1236.reforestry.farming.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.screens.MenuScreens;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.farming.features.FarmingMenuTypes;

@Environment(EnvType.CLIENT)
public class FarmingClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		MenuScreens.register(FarmingMenuTypes.FARM.type(), ScreenFarm::new);
	}
}
