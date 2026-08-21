package com.leon1236.reforestry.gendustry.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.gendustry.features.GMenus;

@Environment(EnvType.CLIENT)
public class GendustryClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		GendustryFluidClientHandler.registerClient();
		MenuScreens.register(GMenus.INDUSTRIAL_APIARY.type(), ScreenIndustrialApiary::new);
		MenuScreens.register(GMenus.PROCESSOR.type(), ScreenProducer::new);
		MenuScreens.register(GMenus.SAMPLER.type(), ScreenThreeInput::new);
		MenuScreens.register(GMenus.IMPRINTER.type(), ScreenThreeInput::new);
		MenuScreens.register(GMenus.GENETIC_TRANSPOSER.type(), ScreenThreeInput::new);
		MenuScreens.register(GMenus.MUTATRON.type(), ScreenMutatron::new);
		MenuScreens.register(GMenus.ADVANCED_MUTATRON.type(), ScreenAdvancedMutatron::new);
		MenuScreens.register(GMenus.REPLICATOR.type(), ScreenReplicator::new);
	}
}
