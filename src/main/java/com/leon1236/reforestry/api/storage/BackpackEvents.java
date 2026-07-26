package com.leon1236.reforestry.api.storage;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class BackpackEvents {
	public static final Event<StowCallback> STOW = EventFactory.createArrayBacked(StowCallback.class, callbacks ->
			(player, backpackDefinition, backpackInventory, stackToStow) -> {
				for (StowCallback callback : callbacks) {
					if (callback.onStow(player, backpackDefinition, backpackInventory, stackToStow)) {
						return true;
					}
				}
				return false;
			});

	public static final Event<ResupplyCallback> RESUPPLY = EventFactory.createArrayBacked(ResupplyCallback.class, callbacks ->
			(player, backpackDefinition, backpackInventory) -> {
				for (ResupplyCallback callback : callbacks) {
					if (callback.onResupply(player, backpackDefinition, backpackInventory)) {
						return true;
					}
				}
				return false;
			});

	private BackpackEvents() {
	}

	@FunctionalInterface
	public interface StowCallback {
		boolean onStow(Player player, IBackpackDefinition backpackDefinition, Container backpackInventory, ItemStack stackToStow);
	}

	@FunctionalInterface
	public interface ResupplyCallback {
		boolean onResupply(Player player, IBackpackDefinition backpackDefinition, Container backpackInventory);
	}
}
