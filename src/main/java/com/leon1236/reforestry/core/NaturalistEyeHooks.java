package com.leon1236.reforestry.core;

import java.util.function.Predicate;

import net.minecraft.world.entity.player.Player;

public final class NaturalistEyeHooks {
	private static Predicate<Player> trinketsCheck = player -> false;

	private NaturalistEyeHooks() {
	}

	public static void registerTrinketsCheck(Predicate<Player> check) {
		trinketsCheck = check;
	}

	public static boolean hasTrinketsNaturalistEye(Player player) {
		return trinketsCheck.test(player);
	}
}
