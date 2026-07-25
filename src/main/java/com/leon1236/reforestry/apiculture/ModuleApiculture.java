package com.leon1236.reforestry.apiculture;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.apiculture.client.ApicultureClientHandler;
import com.leon1236.reforestry.apiculture.features.ApicultureBlocks;
import com.leon1236.reforestry.apiculture.features.ApicultureCreativeTabs;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.features.ApicultureEffects;
import com.leon1236.reforestry.apiculture.features.ApicultureFeatures;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.features.ApicultureMenuTypes;
import com.leon1236.reforestry.apiculture.features.ApicultureParticles;
import com.leon1236.reforestry.apiculture.features.ApicultureTiles;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.core.plugin.PluginManager;

@ForestryModule(name = "Apiculture", description = "Beekeeping, apiaries, hives, bee genetics.")
public class ModuleApiculture implements IForestryModule {
    @Override
    public Identifier getId() {
        return ReForestry.id("apiculture");
    }

    @Override
    public List<Identifier> getModuleDependencies() {
        return List.of(ReForestry.id("core"));
    }

    @Override
    public void init() {
        ApicultureItems.init();
        ApicultureDataComponents.init();
        ApicultureEffects.init();
        ApicultureBlocks.init();
        PluginManager.runApicultureRegistration();
        ApicultureGenetics.finalizeRegistration();
        ApicultureTiles.init();
        ApicultureMenuTypes.init();
        ApicultureParticles.init();
        ApicultureFeatures.init();
        ApicultureCreativeTabs.init();
    }

    @Override
    public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
        registrar.accept(new ApicultureClientHandler());
    }
}
