package com.leon1236.reforestry.core.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.core.book.ForesterBookOpener;
import com.leon1236.reforestry.core.book.client.ForesterBookClient;
import com.leon1236.reforestry.core.client.genetics.GeneticClientManager;
import com.leon1236.reforestry.core.features.CoreMenuTypes;
import com.leon1236.reforestry.core.features.CoreTiles;
import com.leon1236.reforestry.core.genetics.root.ClientBreedingHandler;
import com.leon1236.reforestry.core.fluids.client.FluidClientHandler;
import com.leon1236.reforestry.core.multiblock.MultiblockClientEventHandler;
import com.leon1236.reforestry.core.escritoire.TileEscritoire;
import com.leon1236.reforestry.core.network.packets.EscritoireGameSyncPayload;

@Environment(EnvType.CLIENT)
public class CoreClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		ClientBreedingHandler.register();
		MultiblockClientEventHandler.register();
		FluidClientHandler.registerClient();
		SpectaclesHighlightRenderer.register();
		GeneticClientManager.INSTANCE.bootstrap();
		ForesterBookClient.register();
		ForesterBookOpener.setOpener(ForesterBookClient::openBook);
		ClientPlayNetworking.registerGlobalReceiver(EscritoireGameSyncPayload.TYPE, (payload, context) -> {
			context.client().execute(() -> {
				if (context.client().level == null) {
					return;
				}
				var blockEntity = context.client().level.getBlockEntity(payload.pos());
				if (blockEntity instanceof TileEscritoire tile) {
					payload.applyTo(tile.getGame());
				}
			});
		});

		ModelLayerRegistry.registerModelLayer(ForestryModelLayers.NATURALIST_CHEST_LAYER, RenderNaturalistChest::createBodyLayer);
		ModelLayerRegistry.registerModelLayer(ForestryModelLayers.ANALYZER_LAYER, RenderAnalyzer::createBodyLayer);
		ModelLayerRegistry.registerModelLayer(ForestryModelLayers.ESCRITOIRE_LAYER, RenderEscritoire::createBodyLayer);

		BlockEntityRenderers.register(CoreTiles.BEE_CHEST.type(), ctx -> new RenderNaturalistChest(ctx, "apiaristchest"));
		BlockEntityRenderers.register(CoreTiles.TREE_CHEST.type(), ctx -> new RenderNaturalistChest(ctx, "arbchest"));
		BlockEntityRenderers.register(CoreTiles.BUTTERFLY_CHEST.type(), ctx -> new RenderNaturalistChest(ctx, "lepichest"));
		BlockEntityRenderers.register(CoreTiles.ANALYZER.type(), RenderAnalyzer::new);
		BlockEntityRenderers.register(CoreTiles.ESCRITOIRE.type(), RenderEscritoire::new);

		MenuScreens.register(CoreMenuTypes.SOLDERING_IRON.type(), ScreenSolderingIron::new);
		MenuScreens.register(CoreMenuTypes.ALYZER.type(), ScreenPortableAnalyzer::new);
		MenuScreens.register(CoreMenuTypes.NATURALIST_CHEST.type(), ScreenNaturalistChest::new);
		MenuScreens.register(CoreMenuTypes.ANALYZER.type(), ScreenAnalyzer::new);
		MenuScreens.register(CoreMenuTypes.ESCRITOIRE.type(), ScreenEscritoire::new);
	}
}
