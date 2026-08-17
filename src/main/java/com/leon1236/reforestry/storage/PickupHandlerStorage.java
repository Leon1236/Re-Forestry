package com.leon1236.reforestry.storage;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.storage.IBackpackDefinition;
import com.leon1236.reforestry.storage.gui.ContainerBackpack;
import com.leon1236.reforestry.storage.items.ItemBackpack;

public final class PickupHandlerStorage {
	private PickupHandlerStorage() {
	}

	public static boolean onItemPickup(Player player, ItemEntity entityItem) {
		ItemStack itemstack = entityItem.getItem();
		if (itemstack.isEmpty()) {
			return false;
		}
		if (player.containerMenu instanceof ContainerBackpack) {
			return false;
		}
		topOffPlayerInventory(player, itemstack);
		Inventory inventory = player.getInventory();
		for (int i = 0; i < inventory.getContainerSize(); i++) {
			ItemStack pack = inventory.getItem(i);
			if (itemstack.isEmpty()) {
				break;
			}
			if (pack.isEmpty() || !(pack.getItem() instanceof ItemBackpack backpack)) {
				continue;
			}
			IBackpackDefinition backpackDefinition = backpack.getDefinition();
			if (backpackDefinition.getFilter().test(itemstack)) {
				ItemBackpack.tryStowing(player, pack, itemstack);
			}
		}
		return itemstack.isEmpty();
	}

	private static void topOffPlayerInventory(Player player, ItemStack itemstack) {
		for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
			ItemStack inventoryStack = player.getInventory().getItem(i);
			if (inventoryStack.isEmpty()) {
				continue;
			}
			if (inventoryStack.getCount() >= inventoryStack.getMaxStackSize()) {
				continue;
			}
			if (ItemStack.isSameItemSameComponents(inventoryStack, itemstack)) {
				int space = inventoryStack.getMaxStackSize() - inventoryStack.getCount();
				if (space > itemstack.getCount()) {
					inventoryStack.grow(itemstack.getCount());
					itemstack.setCount(0);
				} else {
					inventoryStack.setCount(inventoryStack.getMaxStackSize());
					itemstack.shrink(space);
				}
			}
		}
	}
}
