package com.leon1236.reforestry.core.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.arboriculture.charcoal.CharcoalManager;
import com.leon1236.reforestry.core.ForestryApiImpl;
import com.leon1236.reforestry.core.circuits.CircuitManager;
import com.leon1236.reforestry.farming.farmlogic.FarmingManager;
import com.leon1236.reforestry.sorting.FilterManager;

public final class PluginManager {
    private static final String PLUGIN_ENTRYPOINT_KEY = "reforestry:plugin";

    private static List<IForestryPlugin> discoveredPlugins;
    private static boolean filtersRegistered;
    private static boolean farmingRegistered;

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

    public static void runCircuitRegistration() {
        CircuitRegistrationImpl registration = new CircuitRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerCircuits(registration);
        }
        var layouts = registration.buildLayouts();
        ((ForestryApiImpl) ForestryApiImpl.get()).setCircuitManager(new CircuitManager(
                registration.buildCircuitHolders(layouts),
                layouts,
                registration.buildCircuits()));
    }

    public static void runFilterRegistration() {
        if (filtersRegistered) {
            return;
        }
        filtersRegistered = true;
        FilterRegistrationImpl registration = new FilterRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerFilter(registration);
        }
        ((ForestryApiImpl) ForestryApiImpl.get()).setFilterManager(new FilterManager(registration.getFilterRuleTypes()));
    }

    public static void runFarmingRegistration(Consumer<FarmingRegistrationImpl> afterPlugins) {
        if (farmingRegistered) {
            return;
        }
        farmingRegistered = true;
        FarmingRegistrationImpl registration = new FarmingRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            try {
                plugin.registerFarming(registration);
            } catch (Throwable t) {
                throw new RuntimeException("An error was thrown by plugin " + plugin.id() + " during IForestryPlugin.registerFarming", t);
            }
        }
        afterPlugins.accept(registration);
        ((ForestryApiImpl) ForestryApiImpl.get()).setFarmingManager(new FarmingManager(
                new Object2IntOpenHashMap<>(registration.getFertilizers()),
                registration.buildFarmTypes()));
    }

    private static List<IForestryPlugin> plugins() {
        if (discoveredPlugins == null) {
            List<IForestryPlugin> discovered = new ArrayList<>();
            for (EntrypointContainer<IForestryPlugin> container : FabricLoader.getInstance()
                    .getEntrypointContainers(PLUGIN_ENTRYPOINT_KEY, IForestryPlugin.class)) {
                IForestryPlugin plugin = container.getEntrypoint();
                discovered.add(plugin);
                ReForestry.LOGGER.info("Discovered Forestry plugin: {}", plugin.id());
            }
            discoveredPlugins = List.copyOf(discovered);
        }
        List<IForestryPlugin> loaded = new ArrayList<>();
        for (IForestryPlugin plugin : discoveredPlugins) {
            if (plugin.shouldLoad()) {
                loaded.add(plugin);
            }
        }
        return loaded;
    }
}
