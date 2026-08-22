package com.leon1236.reforestry.sorting;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.core.network.PacketRegistry;
import com.leon1236.reforestry.core.plugin.PluginManager;
import com.leon1236.reforestry.sorting.client.SortingClientHandler;
import com.leon1236.reforestry.sorting.features.SortingBlocks;
import com.leon1236.reforestry.sorting.features.SortingMenus;
import com.leon1236.reforestry.sorting.features.SortingTiles;
import com.leon1236.reforestry.sorting.network.packets.FilterChangeGenomePayload;
import com.leon1236.reforestry.sorting.network.packets.FilterChangeRulePayload;
import com.leon1236.reforestry.sorting.network.packets.GuiUpdateFilterPayload;

@ForestryModule(name = "Sorting", description = "Adds a filter for sorting item with genetic material")
public class ModuleSorting implements IForestryModule {
	@Override
	public Identifier getId() {
		return ReForestry.id("sorting");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(ReForestry.id("core"));
	}

	@Override
	public void init() {
		PluginManager.runFilterRegistration();
		SortingBlocks.init();
		SortingTiles.init();
		SortingMenus.init();
		PacketRegistry.registerClientbound(GuiUpdateFilterPayload.TYPE, GuiUpdateFilterPayload.STREAM_CODEC);
		PacketRegistry.registerServerbound(FilterChangeRulePayload.TYPE, FilterChangeRulePayload.STREAM_CODEC);
		PacketRegistry.registerServerbound(FilterChangeGenomePayload.TYPE, FilterChangeGenomePayload.STREAM_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(FilterChangeRulePayload.TYPE, FilterChangeRulePayload::handle);
		ServerPlayNetworking.registerGlobalReceiver(FilterChangeGenomePayload.TYPE, FilterChangeGenomePayload::handle);
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new SortingClientHandler());
	}
}
