package com.leon1236.reforestry.energy.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.core.client.ForestryModelLayers;
import com.leon1236.reforestry.energy.features.EnergyMenus;
import com.leon1236.reforestry.energy.features.EnergyTiles;

@Environment(EnvType.CLIENT)
public class EnergyClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		ModelLayerRegistry.registerModelLayer(ForestryModelLayers.ENGINE_LAYER, RenderEngine::createBodyLayer);
		BlockEntityRenderers.register(EnergyTiles.PEAT_ENGINE.type(), ctx -> new RenderEngine(ctx, "engine_copper_"));
		BlockEntityRenderers.register(EnergyTiles.BIOGAS_ENGINE.type(), ctx -> new RenderEngine(ctx, "engine_bronze_"));
		BlockEntityRenderers.register(EnergyTiles.CLOCKWORK_ENGINE.type(), ctx -> new RenderEngine(ctx, "engine_clock_"));
		MenuScreens.register(EnergyMenus.ENGINE_PEAT.type(), ScreenPeatEngine::new);
		MenuScreens.register(EnergyMenus.ENGINE_BIOGAS.type(), ScreenBiogasEngine::new);
	}
}
