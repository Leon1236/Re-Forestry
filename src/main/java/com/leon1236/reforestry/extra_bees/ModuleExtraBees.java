package com.leon1236.reforestry.extra_bees;

import java.util.List;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesBlocks;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesCreativeTabs;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesItems;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesTiles;

@ForestryModule(name = "Extra Bees", description = "Extra Bees combs, drops, frames, and hives.")
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
		ExtraBeesCreativeTabs.init();
	}
}
