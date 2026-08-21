package com.leon1236.reforestry.extra_bees.plugin;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;

public class ExtraBeesForestryPlugin implements IForestryPlugin {
	@Override
	public Identifier id() {
		return ReForestry.id("extra_bees");
	}

	@Override
	public boolean shouldLoad() {
		return IForestryApi.get().getModuleManager().isModuleLoaded(ReForestry.id("extra_bees"));
	}
}
