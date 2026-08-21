package com.leon1236.reforestry.gendustry.plugin;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;

public class GendustryForestryPlugin implements IForestryPlugin {
	@Override
	public Identifier id() {
		return ReForestry.id("gendustry");
	}

	@Override
	public boolean shouldLoad() {
		return IForestryApi.get().getModuleManager().isModuleLoaded(ReForestry.id("gendustry"));
	}
}
