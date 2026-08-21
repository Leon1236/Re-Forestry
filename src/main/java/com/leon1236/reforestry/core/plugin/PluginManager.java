package com.leon1236.reforestry.core.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.resources.Identifier;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.plugin.IClientRegistration;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.arboriculture.charcoal.CharcoalManager;
import com.leon1236.reforestry.core.ForestryApiImpl;
import com.leon1236.reforestry.core.circuits.CircuitManager;
import com.leon1236.reforestry.core.client.ClientRegistrationImpl;
import com.leon1236.reforestry.core.errors.ErrorManager;
import com.leon1236.reforestry.core.genetics.FlowerTypeManager;
import com.leon1236.reforestry.core.genetics.GeneticManager;
import com.leon1236.reforestry.farming.farmlogic.FarmingManager;
import com.leon1236.reforestry.sorting.FilterManager;

public final class PluginManager {
    private static final String PLUGIN_ENTRYPOINT_KEY = "reforestry:plugin";

    private static List<IForestryPlugin> discoveredPlugins;
    private static boolean filtersRegistered;
    private static boolean farmingRegistered;

    private PluginManager() {
    }

    public static void runGeneticsRegistration() {
        GeneticRegistrationImpl registration = new GeneticRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerGenetics(registration);
        }
        FlowerTypeManager.INSTANCE.bootstrapVanilla();
        for (var entry : registration.flowerTypes().entrySet()) {
            FlowerTypeManager.INSTANCE.register(entry.getKey(), entry.getValue());
            if (entry.getValue() instanceof com.leon1236.reforestry.apiculture.genetics.IFlowerType geneticsType) {
                BeeChromosomes.FLOWER_TYPE.registerValue(entry.getKey(), geneticsType);
            }
        }
        GeneticManager geneticManager = ForestryApiImpl.get().getMutableGeneticManager();
        for (Map.Entry<Identifier, ISpeciesType<?, ?>> entry : registration.buildSpeciesTypes().entrySet()) {
            geneticManager.registerSpeciesType(entry.getValue());
        }
        if (!registration.definedTaxa().isEmpty()) {
            geneticManager.applyDatapackTaxa(registration.definedTaxa());
        }
    }

    public static void runErrorRegistration() {
        ErrorRegistrationImpl registration = new ErrorRegistrationImpl(
                (ErrorManager) ForestryApiImpl.get().getErrorManager());
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerErrors(registration);
        }
    }

    public static void runPollenRegistration() {
        PollenRegistrationImpl registration = new PollenRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerPollen(registration);
        }
    }

    public static void runLepidopterologyRegistration() {
        LepidopterologyRegistrationImpl registration = new LepidopterologyRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerLepidopterology(registration);
        }
    }

    public static void runClientRegistration() {
        ClientRegistrationImpl registration = new ClientRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerClient(consumer -> consumer.accept(registration));
        }
    }

    public static void runApicultureRegistration() {
        ApicultureRegistrationImpl registration = new ApicultureRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerApiculture(registration);
        }
        registration.finalizeEffects();
        ForestryApiImpl.get().setHiveManager(registration.buildHiveManager());
    }

    public static void runArboricultureRegistration() {
        ArboricultureRegistrationImpl registration = new ArboricultureRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerArboriculture(registration);
        }
        CharcoalManager.setInstance(registration.getCharcoalManager());
        ForestryApiImpl.get().setTreeManager(registration.buildTreeManager());
    }

    public static void runCircuitRegistration() {
        CircuitRegistrationImpl registration = new CircuitRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerCircuits(registration);
        }
        var layouts = registration.buildLayouts();
        ForestryApiImpl.get().setCircuitManager(new CircuitManager(
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
        ForestryApiImpl.get().setFilterManager(new FilterManager(registration.getFilterRuleTypes()));
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
        ForestryApiImpl.get().setFarmingManager(new FarmingManager(
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
