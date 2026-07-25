package com.leon1236.reforestry.core.plugin;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.arboriculture.charcoal.CharcoalManager;
import com.leon1236.reforestry.core.ForestryApiImpl;

public final class PluginManager {
    private static final String PLUGIN_ENTRYPOINT_KEY = "reforestry:plugin";

    private static List<IForestryPlugin> cachedPlugins;

    private PluginManager() {
    }

    public static void runApicultureRegistration() {
        ApicultureRegistrationImpl registration = new ApicultureRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerApiculture(registration);
        }
        registration.finalizeEffects();
        ((ForestryApiImpl) ForestryApiImpl.get()).setHiveManager(registration.buildHiveManager());
    }

    public static void runArboricultureRegistration() {
        ArboricultureRegistrationImpl registration = new ArboricultureRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerArboriculture(registration);
        }
        CharcoalManager.setInstance(registration.getCharcoalManager());
    }

    private static List<IForestryPlugin> plugins() {
        if (cachedPlugins == null) {
            List<IForestryPlugin> plugins = new ArrayList<>();
            for (EntrypointContainer<IForestryPlugin> container : FabricLoader.getInstance()
                    .getEntrypointContainers(PLUGIN_ENTRYPOINT_KEY, IForestryPlugin.class)) {
                IForestryPlugin plugin = container.getEntrypoint();
                if (plugin.shouldLoad()) {
                    plugins.add(plugin);
                    ReForestry.LOGGER.info("Loaded Forestry plugin: {}", plugin.id());
                }
            }
            cachedPlugins = List.copyOf(plugins);
        }
        return cachedPlugins;
    }
}
