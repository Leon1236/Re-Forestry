package com.leon1236.reforestry.core.items;

import java.util.function.Supplier;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.item.v1.CustomDamageHandler;

public final class ItemRemnants {
	private ItemRemnants() {
	}

	public static CustomDamageHandler give(Supplier<ItemStack> remnants) {
		return (stack, amount, entity, slot, breakCallback) -> {
			if (stack.getDamageValue() + amount >= stack.getMaxDamage() && entity instanceof Player player) {
				ItemStack remnant = remnants.get();
				breakCallback.run();
				if (!player.getInventory().add(remnant)) {
					player.drop(remnant, false);
				}
				return 0;
			}
			return amount;
		};
	}
}
