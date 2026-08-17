package com.leon1236.reforestry.storage;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.storage.BackpackEvents;
import com.leon1236.reforestry.core.inventory.ItemInventory;
import com.leon1236.reforestry.storage.inventory.ItemInventoryBackpack;
import com.leon1236.reforestry.storage.items.ItemBackpack;

public final class BackpackResupplyHandler {
	private BackpackResupplyHandler() {
	}

	private static NonNullList<ItemStack> getBackpacks(Inventory playerInventory) {
		NonNullList<ItemStack> backpacks = NonNullList.create();
		for (int i = 0; i < playerInventory.getContainerSize(); i++) {
			ItemStack itemStack = playerInventory.getItem(i);
			if (itemStack.getItem() instanceof ItemBackpack) {
				backpacks.add(itemStack);
			}
		}
		return backpacks;
	}

	public static void resupply(Player player) {
		if (!(player.containerMenu instanceof InventoryMenu)) {
			return;
		}
		for (ItemStack backpack : getBackpacks(player.getInventory())) {
			if (ItemBackpack.getMode(backpack) != BackpackMode.RESUPPLY) {
				continue;
			}
			ItemBackpack backpackItem = (ItemBackpack) backpack.getItem();
			ItemInventory backpackInventory = new ItemInventoryBackpack(player, backpackItem.getBackpackSize(), backpack);
			if (BackpackEvents.RESUPPLY.invoker().onResupply(player, backpackItem.getDefinition(), backpackInventory)) {
				continue;
			}
			for (int i = 0; i < backpackInventory.getContainerSize(); i++) {
				ItemStack itemStack = backpackInventory.getItem(i);
				if (topOffPlayerInventory(player, itemStack)) {
					backpackInventory.setItem(i, itemStack);
					break;
				}
			}
		}
	}

	private static boolean topOffPlayerInventory(Player player, ItemStack itemstack) {
		if (itemstack.isEmpty()) {
			return false;
		}
		Inventory playerInventory = player.getInventory();
		for (int i = 0; i < playerInventory.getContainerSize(); i++) {
			ItemStack inventoryStack = playerInventory.getItem(i);
			if (inventoryStack.isEmpty() || inventoryStack.getCount() >= inventoryStack.getMaxStackSize()) {
				continue;
			}
			if (!ItemStack.isSameItemSameComponents(inventoryStack, itemstack)) {
				continue;
			}
			inventoryStack.grow(1);
			itemstack.shrink(1);
			return true;
		}
		return false;
	}
}
