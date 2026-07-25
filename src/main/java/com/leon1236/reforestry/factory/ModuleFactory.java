package com.leon1236.reforestry.factory;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import com.leon1236.reforestry.api.fuels.FermenterFuel;
import com.leon1236.reforestry.api.fuels.FuelManager;
import com.leon1236.reforestry.api.fuels.MoistenerFuel;
import com.leon1236.reforestry.api.fuels.RainSubstrate;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.factory.client.FactoryClientHandler;
import com.leon1236.reforestry.factory.features.FactoryBlocks;
import com.leon1236.reforestry.factory.features.FactoryCreativeTabs;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;
import com.leon1236.reforestry.factory.features.FactoryTiles;

@ForestryModule(name = "Factory", description = "Processing machines: centrifuge, squeezer, still, and more.")
public class ModuleFactory implements IForestryModule {
    private static boolean fuelsSeeded;

    @Override
    public Identifier getId() {
        return ReForestry.id("factory");
    }

    @Override
    public List<Identifier> getModuleDependencies() {
        return List.of(ReForestry.id("core"));
    }

    @Override
    public void init() {
        setupApi();
        FactoryRecipeTypes.init();
        FactoryBlocks.init();
        FactoryTiles.init();
        FactoryMenuTypes.init();
        FactoryCreativeTabs.init();
        CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> seedFuels());
        ServerLifecycleEvents.SERVER_STARTED.register(ModuleFactory::reloadFabricatorMoltenFluids);
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> {
            if (success) {
                reloadFabricatorMoltenFluids(server);
            }
        });
    }

    private static void reloadFabricatorMoltenFluids(MinecraftServer server) {
        FabricatorMoltenFluidSetup.reload(server.overworld());
    }

    @Override
    public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
        registrar.accept(new FactoryClientHandler());
    }

    private static void setupApi() {
        FuelManager.fermenterFuel = new HashMap<>();
        FuelManager.moistenerResource = new HashMap<>();
        FuelManager.rainSubstrate = new HashMap<>();
    }

    private static void seedFuels() {
        if (fuelsSeeded) {
            return;
        }
        fuelsSeeded = true;

        ItemStack fertilizerCompound = new ItemStack(CoreItems.FERTILIZER_COMPOUND.item());
        FuelManager.fermenterFuel.put(fertilizerCompound, new FermenterFuel(fertilizerCompound, 56, 200));

        ItemStack compost = new ItemStack(CoreItems.COMPOST.item());
        ItemStack mulch = new ItemStack(CoreItems.MULCH.item());
        FuelManager.fermenterFuel.put(compost, new FermenterFuel(compost, 48, 250));
        FuelManager.fermenterFuel.put(mulch, new FermenterFuel(mulch, 48, 250));

        ItemStack wheat = new ItemStack(Items.WHEAT);
        ItemStack mouldyWheat = new ItemStack(CoreItems.MOULDY_WHEAT.item());
        ItemStack decayingWheat = new ItemStack(CoreItems.DECAYING_WHEAT.item());
        FuelManager.moistenerResource.put(wheat, new MoistenerFuel(wheat, mouldyWheat, 0, 300));
        FuelManager.moistenerResource.put(mouldyWheat, new MoistenerFuel(mouldyWheat, decayingWheat, 1, 600));
        FuelManager.moistenerResource.put(decayingWheat, new MoistenerFuel(decayingWheat, mulch, 2, 900));

        ItemStack iodineCharge = new ItemStack(CoreItems.IODINE_CHARGE.item());
        ItemStack dissipationCharge = new ItemStack(CoreItems.DISSIPATION_CHARGE.item());
        FuelManager.rainSubstrate.put(iodineCharge, new RainSubstrate(iodineCharge, 10000, 0.075f));
        FuelManager.rainSubstrate.put(dissipationCharge, new RainSubstrate(dissipationCharge, 0.01f));
    }
}
