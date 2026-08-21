package com.leon1236.reforestry.gendustry;

import java.util.List;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.gendustry.features.GCreativeTabs;
import com.leon1236.reforestry.gendustry.features.GItems;

@ForestryModule(name = "Gendustry", description = "Industrial genetics machines, upgrades, and pollen tools.")
public class ModuleGendustry implements IForestryModule {
	@Override
	public Identifier getId() {
		return ReForestry.id("gendustry");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(ReForestry.id("core"));
	}

	@Override
	public void init() {
		GItems.init();
		GCreativeTabs.init();
	}
}
