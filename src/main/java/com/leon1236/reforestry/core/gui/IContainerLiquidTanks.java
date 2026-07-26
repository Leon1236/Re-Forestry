package com.leon1236.reforestry.core.gui;

import org.jetbrains.annotations.Nullable;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

public interface IContainerLiquidTanks {
	void handlePipetteClick(int slot, ServerPlayer player);

	@Nullable
	SingleFluidStorage getTank(int slot);

	default void handlePipetteClickClient(int slot, Player player) {
	}
}
