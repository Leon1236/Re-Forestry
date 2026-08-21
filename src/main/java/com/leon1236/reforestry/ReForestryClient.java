package com.leon1236.reforestry;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.PictureInPictureRendererRegistry;

import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.core.client.AccessMachinePipRenderer;
import com.leon1236.reforestry.core.plugin.PluginManager;
import com.leon1236.reforestry.modules.ModuleManager;

public class ReForestryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PictureInPictureRendererRegistry.register(ctx -> new AccessMachinePipRenderer());
        PluginManager.runClientRegistration();
        for (IForestryModule module : ModuleManager.INSTANCE.getLoadedModules()) {
            module.registerClientHandler(handler -> handler.registerClient());
        }
    }
}
