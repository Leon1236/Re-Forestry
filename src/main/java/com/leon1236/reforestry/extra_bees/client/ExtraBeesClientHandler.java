package com.leon1236.reforestry.extra_bees.client;

import net.minecraft.client.gui.screens.MenuScreens;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesMenuTypes;

public class ExtraBeesClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		MenuScreens.register(ExtraBeesMenuTypes.ALVEARY_MUTATOR.type(), ScreenAlvearyMutator::new);
		MenuScreens.register(ExtraBeesMenuTypes.ALVEARY_FRAME.type(), ScreenAlvearyFrame::new);
		MenuScreens.register(ExtraBeesMenuTypes.ALVEARY_HATCHERY.type(), ScreenAlvearyHatchery::new);
		MenuScreens.register(ExtraBeesMenuTypes.ALVEARY_STIMULATOR.type(), ScreenAlvearyStimulator::new);
	}
}
