package com.leon1236.reforestry.core.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.plugin.IClientRegistration;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.arboriculture.charcoal.CharcoalManager;
import com.leon1236.reforestry.core.ForestryApiImpl;
import com.leon1236.reforestry.core.circuits.CircuitManager;
import com.leon1236.reforestry.core.client.ClientRegistrationImpl;
import com.leon1236.reforestry.core.errors.ErrorManager;
import com.leon1236.reforestry.core.genetics.FlowerTypeManager;
import com.leon1236.reforestry.core.genetics.GeneticManager;

public final class PluginManager {
    private static final String PLUGIN_ENTRYPOINT_KEY = "reforestry:plugin";

    private static List<IForestryPlugin> cachedPlugins;

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
        }
        GeneticManager geneticManager = ((ForestryApiImpl) ForestryApiImpl.get()).getMutableGeneticManager();
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
        ((ForestryApiImpl) ForestryApiImpl.get()).setHiveManager(registration.buildHiveManager());
    }

    public static void runArboricultureRegistration() {
        ArboricultureRegistrationImpl registration = new ArboricultureRegistrationImpl();
        for (IForestryPlugin plugin : plugins()) {
            plugin.registerArboriculture(registration);
        }
        CharcoalManager.setInstance(registration.getCharcoalManager());
        ((ForestryApiImpl) ForestryApiImpl.get()).setTreeManager(registration.buildTreeManager());
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
