package com.leon1236.reforestry.extratrees.plugin;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.plugin.IArboricultureRegistration;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.extratrees.genetics.ExtraTreesFruits;
import com.leon1236.reforestry.extratrees.genetics.ExtraTreesTreeSpecies;

public class ExtraTreesForestryPlugin implements IForestryPlugin {
	@Override
	public Identifier id() {
		return ReForestry.id("extra_trees");
	}

	@Override
	public boolean shouldLoad() {
		return IForestryApi.get().getModuleManager().isModuleEnabled(ReForestry.id("extra_trees"));
	}

	@Override
	public void registerArboriculture(IArboricultureRegistration registration) {
		ExtraTreesFruits.register(registration);
		ExtraTreesTreeSpecies.register(registration);
	}
}
