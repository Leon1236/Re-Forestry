package com.leon1236.reforestry.gendustry.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import com.leon1236.reforestry.api.client.IClientModuleHandler;

@Environment(EnvType.CLIENT)
public class GendustryClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		GendustryFluidClientHandler.registerClient();
	}
}
