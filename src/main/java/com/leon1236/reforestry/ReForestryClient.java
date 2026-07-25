package com.leon1236.reforestry;

import net.fabricmc.api.ClientModInitializer;

import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.modules.ModuleManager;

public class ReForestryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (IForestryModule module : ModuleManager.INSTANCE.getLoadedModules()) {
            module.registerClientHandler(handler -> handler.registerClient());
        }
    }
}
