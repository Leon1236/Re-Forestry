package com.leon1236.reforestry.extra_bees;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.extra_bees.alveary.AlvearyMutationHandler;
import com.leon1236.reforestry.extra_bees.client.ExtraBeesClientHandler;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesBlocks;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesCreativeTabs;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesItems;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesMenuTypes;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesTiles;

@ForestryModule(name = "Extra Bees", description = "Extra Bees combs, drops, frames, hives, and alveary parts.")
public class ModuleExtraBees implements IForestryModule {
	@Override
	public Identifier getId() {
		return ReForestry.id("extra_bees");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(ReForestry.id("core"), ReForestry.id("apiculture"));
	}

	@Override
	public void init() {
		ExtraBeesItems.init();
		ExtraBeesBlocks.init();
		ExtraBeesTiles.init();
		ExtraBeesMenuTypes.init();
		ExtraBeesCreativeTabs.init();
		AlvearyMutationHandler.registerMutationItems();
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new ExtraBeesClientHandler());
	}
}
