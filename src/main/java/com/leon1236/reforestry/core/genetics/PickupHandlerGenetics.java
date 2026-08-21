package com.leon1236.reforestry.core.genetics;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.capability.IIndividualHandlerItem;

public final class PickupHandlerGenetics {
	private PickupHandlerGenetics() {
	}

	public static void onItemPickup(Player player, ItemEntity entity) {
		ItemStack stack = entity.getItem();
		if (stack.isEmpty()) {
			return;
		}
		IIndividualHandlerItem.ifPresent(stack, individual -> {
			IBreedingTracker tracker = individual.getType().getBreedingTracker(entity.level(), player.getGameProfile());
			tracker.registerPickup(individual.getSpecies().id());
		});
	}
}
