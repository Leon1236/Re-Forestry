package com.leon1236.reforestry.api.core;

import net.minecraft.world.entity.player.Player;

public interface ISpectacleBlock {
	default boolean isHighlighted(Player player) {
		return true;
	}
}
