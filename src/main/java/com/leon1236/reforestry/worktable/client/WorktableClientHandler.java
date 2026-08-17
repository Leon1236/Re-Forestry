package com.leon1236.reforestry.worktable.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.screens.MenuScreens;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.worktable.features.WorktableMenuTypes;

@Environment(EnvType.CLIENT)
public class WorktableClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		MenuScreens.register(WorktableMenuTypes.WORKTABLE.type(), ScreenWorktable::new);
	}
}
