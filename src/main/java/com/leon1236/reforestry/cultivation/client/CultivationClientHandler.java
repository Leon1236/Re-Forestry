package com.leon1236.reforestry.cultivation.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.screens.MenuScreens;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.cultivation.features.CultivationMenuTypes;

@Environment(EnvType.CLIENT)
public class CultivationClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		MenuScreens.register(CultivationMenuTypes.PLANTER.type(), ScreenPlanter::new);
	}
}
