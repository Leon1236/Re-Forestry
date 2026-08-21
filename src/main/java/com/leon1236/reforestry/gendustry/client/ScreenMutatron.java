package com.leon1236.reforestry.gendustry.client;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import com.leon1236.reforestry.gendustry.menu.MutatronMenu;

public class ScreenMutatron extends AbstractMutatronScreen<MutatronMenu> {
	public ScreenMutatron(MutatronMenu menu, Inventory inventory, Component title) {
		super(GendustryGuiTextures.MUTATRON, menu, inventory, title);
	}
}
