package com.leon1236.reforestry.core.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.core.client.genetics.GeneticClientManager;
import com.leon1236.reforestry.core.features.CoreMenuTypes;
import com.leon1236.reforestry.core.fluids.client.FluidClientHandler;
import com.leon1236.reforestry.core.multiblock.MultiblockClientEventHandler;

@Environment(EnvType.CLIENT)
public class CoreClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		MultiblockClientEventHandler.register();
		FluidClientHandler.registerClient();
		SpectaclesHighlightRenderer.register();
		GeneticClientManager.INSTANCE.bootstrap();
		MenuScreens.register(CoreMenuTypes.SOLDERING_IRON.type(), ScreenSolderingIron::new);
		MenuScreens.register(CoreMenuTypes.ALYZER.type(), ScreenPortableAnalyzer::new);
	}
}
