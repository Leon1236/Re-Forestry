package com.leon1236.reforestry.api.core;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IArmorNaturalist {
	boolean canSeePollination(Player player, ItemStack armor, boolean doSee);

	static boolean hasNaturalistEye(Player player) {
		ItemStack armor = player.getItemBySlot(EquipmentSlot.HEAD);
		if (armor.isEmpty()) {
			return false;
		}
		if (armor.getItem() instanceof IArmorNaturalist naturalist) {
			return naturalist.canSeePollination(player, armor, true);
		}
		return false;
	}
}
