package com.leon1236.reforestry.lepidopterology;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.lepidopterology.client.LepidopterologyClientHandler;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyCreativeTabs;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyDataComponents;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyItems;

@ForestryModule(name = "Lepidopterology", description = "Butterflies. Pretty.")
public class ModuleLepidopterology implements IForestryModule {
	@Override
	public Identifier getId() {
		return ReForestry.id("lepidopterology");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(ReForestry.id("core"), ReForestry.id("arboriculture"));
	}

	@Override
	public void init() {
		LepidopterologyItems.init();
		LepidopterologyDataComponents.init();
		LepidopterologyCreativeTabs.init();
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new LepidopterologyClientHandler());
	}
}
