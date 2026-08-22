package com.leon1236.reforestry.arboriculture;

import java.util.List;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.arboriculture.client.ArboricultureClientHandler;
import com.leon1236.reforestry.arboriculture.client.TreeClientManager;
import com.leon1236.reforestry.core.client.ClientRegistrationImpl;
import com.leon1236.reforestry.arboriculture.commands.CommandTree;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.features.ArboricultureCreativeTabs;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureEntities;
import com.leon1236.reforestry.arboriculture.features.ArboricultureFeatures;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.features.ArboricultureTiles;
import com.leon1236.reforestry.arboriculture.features.CharcoalBlocks;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.TreePollenType;
import com.leon1236.reforestry.arboriculture.loot.GrafterLootHandler;
import com.leon1236.reforestry.arboriculture.loot.SnifferAmberSaplingLoot;
import com.leon1236.reforestry.arboriculture.villagers.ArboricultureVillagers;
import com.leon1236.reforestry.core.commands.ReforestryCommands;
import com.leon1236.reforestry.core.genetics.pollen.PollenTypes;
import com.leon1236.reforestry.core.plugin.PluginManager;

@ForestryModule(name = "Arboriculture", description = "Tree growing, genetics, sawmill, and related content.")
public class ModuleArboriculture implements IForestryModule {
    public static boolean doSelfPollination = false;

    @Override
    public Identifier getId() {
        return ReForestry.id("arboriculture");
    }

    @Override
    public List<Identifier> getModuleDependencies() {
        return List.of(ReForestry.id("core"));
    }

    @Override
    public void init() {
        ArboricultureBlocks.init();
        CharcoalBlocks.init();
        ArboricultureTiles.init();
        ArboricultureEntities.init();
        ArboricultureItems.init();
        ArboricultureDataComponents.init();
        PluginManager.runArboricultureRegistration();
        ArboricultureGenetics.finalizeRegistration();
        ArboricultureCreativeTabs.init();
        ArboricultureFeatures.init();
        ArboricultureVillagers.init();
        GrafterLootHandler.init();
        SnifferAmberSaplingLoot.init();
        PollenTypes.register(TreePollenType.INSTANCE);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                ReforestryCommands.registerSubcommand(dispatcher, CommandTree.register()));
    }

    @Override
    public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
        registrar.accept(new ArboricultureClientHandler());
    }

    @Override
    public void installClientManagers(ClientRegistrationImpl registration) {
        TreeClientManager.INSTANCE.install(registration.getLeafSprites(), registration.getLeafTints(),
                registration.getSaplingModels());
    }
}
