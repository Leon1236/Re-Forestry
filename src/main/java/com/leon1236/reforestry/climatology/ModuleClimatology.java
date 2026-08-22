package com.leon1236.reforestry.climatology;

import java.util.List;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.climatology.client.ClimatologyClientHandler;
import com.leon1236.reforestry.climatology.features.ClimatologyBlocks;
import com.leon1236.reforestry.climatology.features.ClimatologyCreativeTabs;
import com.leon1236.reforestry.climatology.features.ClimatologyItems;
import com.leon1236.reforestry.climatology.features.ClimatologyMenuTypes;
import com.leon1236.reforestry.climatology.features.ClimatologyTiles;
import com.leon1236.reforestry.core.features.ClimatologyDataComponents;
import com.leon1236.reforestry.climatology.network.SelectClimateTargetPayload;
import com.leon1236.reforestry.core.network.PacketRegistry;

@ForestryModule(name = "Climatology", description = "Habitat Former and Habitat Screen.")
public class ModuleClimatology implements IForestryModule {
	@Override
	public Identifier getId() {
		return ReForestry.id("climatology");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(ReForestry.id("core"));
	}

	@Override
	public void init() {
		ClimatologyDataComponents.init();
		ClimatologyBlocks.init();
		ClimatologyTiles.init();
		ClimatologyItems.init();
		ClimatologyMenuTypes.init();
		ClimatologyCreativeTabs.init();
		PacketRegistry.registerServerbound(SelectClimateTargetPayload.TYPE, SelectClimateTargetPayload.STREAM_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(SelectClimateTargetPayload.TYPE, SelectClimateTargetPayload::handle);
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new ClimatologyClientHandler());
	}
}
