package com.leon1236.reforestry.api.core;

import com.leon1236.reforestry.core.NaturalistEyeHooks;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IArmorNaturalist {
	boolean canSeePollination(Player player, ItemStack armor, boolean doSee);

	static boolean hasNaturalistEye(Player player) {
		ItemStack armor = player.getItemBySlot(EquipmentSlot.HEAD);
		if (!armor.isEmpty() && armor.getItem() instanceof IArmorNaturalist naturalist) {
			if (naturalist.canSeePollination(player, armor, true)) {
				return true;
			}
		}
		return NaturalistEyeHooks.hasTrinketsNaturalistEye(player);
	}
}
