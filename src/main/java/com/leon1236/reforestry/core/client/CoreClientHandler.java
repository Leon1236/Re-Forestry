package com.leon1236.reforestry.core.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.core.multiblock.MultiblockClientEventHandler;

@Environment(EnvType.CLIENT)
public class CoreClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		MultiblockClientEventHandler.register();
	}
}
