package com.leon1236.reforestry.gendustry.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;

import com.leon1236.reforestry.gendustry.blockentity.MutatronBlockEntity;
import com.leon1236.reforestry.gendustry.features.GMenus;

public class MutatronMenu extends AbstractMutatronMenu<MutatronBlockEntity> {
	public MutatronMenu(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, MutatronBlockEntity.class));
	}

	public MutatronMenu(int containerId, Inventory playerInventory, MutatronBlockEntity tile) {
		super(GMenus.MUTATRON.type(), containerId, playerInventory, tile);
	}
}
