package com.leon1236.reforestry.storage.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.screens.MenuScreens;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.storage.features.StorageMenuTypes;

@Environment(EnvType.CLIENT)
public class StorageClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		MenuScreens.register(StorageMenuTypes.BACKPACK.type(), ScreenBackpack::new);
		MenuScreens.register(StorageMenuTypes.NATURALIST_BACKPACK.type(), ScreenNaturalistInventory::new);
	}
}
