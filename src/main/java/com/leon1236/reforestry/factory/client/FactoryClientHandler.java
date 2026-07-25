package com.leon1236.reforestry.factory.client;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.core.client.ForestryModelLayers;
import com.leon1236.reforestry.core.client.RenderMachine;
import com.leon1236.reforestry.core.client.RenderMill;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.features.FactoryTiles;

public class FactoryClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		ModelLayerRegistry.registerModelLayer(ForestryModelLayers.MACHINE_LAYER, RenderMachine::createBodyLayer);
		ModelLayerRegistry.registerModelLayer(ForestryModelLayers.MILL_LAYER, RenderMill::createBodyLayer);

		BlockEntityRenderers.register(FactoryTiles.BOTTLER.type(), ctx -> new RenderMachine(ctx, "bottler_"));
		BlockEntityRenderers.register(FactoryTiles.CARPENTER.type(), ctx -> new RenderMachine(ctx, "carpenter_"));
		BlockEntityRenderers.register(FactoryTiles.CENTRIFUGE.type(), ctx -> new RenderMachine(ctx, "centrifuge_"));
		BlockEntityRenderers.register(FactoryTiles.FERMENTER.type(), ctx -> new RenderMachine(ctx, "fermenter_"));
		BlockEntityRenderers.register(FactoryTiles.MOISTENER.type(), ctx -> new RenderMachine(ctx, "moistener_"));
		BlockEntityRenderers.register(FactoryTiles.SQUEEZER.type(), ctx -> new RenderMachine(ctx, "squeezer_"));
		BlockEntityRenderers.register(FactoryTiles.STILL.type(), ctx -> new RenderMachine(ctx, "still_"));
		BlockEntityRenderers.register(FactoryTiles.RAINMAKER.type(), ctx -> new RenderMill(ctx, "rainmaker_"));

		MenuScreens.register(FactoryMenuTypes.CENTRIFUGE.type(), ScreenCentrifuge::new);
		MenuScreens.register(FactoryMenuTypes.SMELTER.type(), ScreenSmelter::new);
		MenuScreens.register(FactoryMenuTypes.STILL.type(), ScreenStill::new);
		MenuScreens.register(FactoryMenuTypes.SQUEEZER.type(), ScreenSqueezer::new);
		MenuScreens.register(FactoryMenuTypes.BOTTLER.type(), ScreenBottler::new);
		MenuScreens.register(FactoryMenuTypes.CARPENTER.type(), ScreenCarpenter::new);
		MenuScreens.register(FactoryMenuTypes.FERMENTER.type(), ScreenFermenter::new);
		MenuScreens.register(FactoryMenuTypes.FABRICATOR.type(), ScreenFabricator::new);
		MenuScreens.register(FactoryMenuTypes.MOISTENER.type(), ScreenMoistener::new);
	}
}
