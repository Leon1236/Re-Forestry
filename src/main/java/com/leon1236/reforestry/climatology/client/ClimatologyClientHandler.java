package com.leon1236.reforestry.climatology.client;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;

import net.minecraft.client.gui.screens.MenuScreens;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.climatology.features.ClimatologyBlocks;
import com.leon1236.reforestry.climatology.features.ClimatologyMenuTypes;

@Environment(EnvType.CLIENT)
public class ClimatologyClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		MenuScreens.register(ClimatologyMenuTypes.HABITAT_FORMER.type(), ScreenHabitatFormer::new);
		HabitatScreenPreviewHandler.init();
		BlockColorRegistry.register(List.of(new HabitatFormerTintSource()), ClimatologyBlocks.HABITAT_FORMER.block());
	}
}
