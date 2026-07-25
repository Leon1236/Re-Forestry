package com.leon1236.reforestry.core;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.BeeManager;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.apiculture.ArmorApiaristHelper;
import com.leon1236.reforestry.arboriculture.worldgen.TreeDecorator;
import com.leon1236.reforestry.core.client.CoreClientHandler;
import com.leon1236.reforestry.core.climate.ForestryClimateManager;
import com.leon1236.reforestry.core.config.ForestryConfig;
import com.leon1236.reforestry.core.errors.ErrorManager;
import com.leon1236.reforestry.core.features.CoreBlocks;
import com.leon1236.reforestry.core.features.CoreCreativeTabs;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.core.multiblock.MultiblockEventHandler;

@ForestryModule(name = "Core", description = "Shared foundation the other modules build on.")
public class ModuleCore implements IForestryModule {
    @Override
    public Identifier getId() {
        return ReForestry.id("core");
    }

    @Override
    public boolean isCore() {
        return true;
    }

    @Override
    public void init() {
        registerErrors();
        ForestryConfig.init();
        CoreItems.init();
        CoreBlocks.init();
        CoreCreativeTabs.init();
        BeeManager.armorApiaristHelper = new ArmorApiaristHelper();
        MultiblockEventHandler.register();
        registerClimateReloadHooks();
    }

    @Override
    public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
        registrar.accept(new CoreClientHandler());
    }

    private static void registerErrors() {
        ErrorManager errorManager = (ErrorManager) IForestryApi.INSTANCE.getErrorManager();
        for (ForestryError error : ForestryError.values()) {
            errorManager.register(error);
        }
    }

    private static void registerClimateReloadHooks() {
        CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> {
            if (!client) {
                reloadBiomes(registries.lookupOrThrow(Registries.BIOME));
            }
        });
        ServerLifecycleEvents.SERVER_STARTED.register(server -> reloadBiomes(server.registryAccess().lookupOrThrow(Registries.BIOME)));
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> {
            if (success) {
                reloadBiomes(server.registryAccess().lookupOrThrow(Registries.BIOME));
            }
        });
    }

    private static void reloadBiomes(net.minecraft.core.Registry<net.minecraft.world.level.biome.Biome> registry) {
        ForestryClimateManager climateManager = ((ForestryApiImpl) IForestryApi.INSTANCE).getForestryClimateManager();
        climateManager.onBiomesReloaded(registry);
        TreeDecorator.clearBiomeCache();
    }
}
