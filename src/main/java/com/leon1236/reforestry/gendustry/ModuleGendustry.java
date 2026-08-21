package com.leon1236.reforestry.gendustry;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.gendustry.client.GendustryClientHandler;
import com.leon1236.reforestry.gendustry.features.GCreativeTabs;
import com.leon1236.reforestry.gendustry.features.GItems;
import com.leon1236.reforestry.gendustry.fluids.GFluids;

@ForestryModule(name = "Gendustry", description = "Industrial genetics machines, upgrades, and pollen tools.")
public class ModuleGendustry implements IForestryModule {
	@Override
	public Identifier getId() {
		return ReForestry.id("gendustry");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(ReForestry.id("core"), ReForestry.id("arboriculture"));
	}

	@Override
	public void init() {
		GFluids.init();
		GItems.init();
		GCreativeTabs.init();
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new GendustryClientHandler());
	}
}
