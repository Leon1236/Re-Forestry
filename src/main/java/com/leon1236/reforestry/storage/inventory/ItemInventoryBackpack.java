package com.leon1236.reforestry.storage.inventory;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.storage.IBackpackDefinition;
import com.leon1236.reforestry.core.inventory.ItemInventory;
import com.leon1236.reforestry.storage.items.ItemBackpack;

public class ItemInventoryBackpack extends ItemInventory {
	private final IBackpackDefinition backpackDefinition;

	public ItemInventoryBackpack(Player player, InteractionHand hand, int size) {
		super(player, hand, size);
		ItemStack parent = getParent();
		if (!(parent.getItem() instanceof ItemBackpack backpack)) {
			throw new IllegalArgumentException("Parent must be a backpack.");
		}
		this.backpackDefinition = backpack.getDefinition();
	}

	public ItemInventoryBackpack(Player player, int size, ItemStack parent) {
		super(player, size, parent);
		if (!(parent.getItem() instanceof ItemBackpack backpack)) {
			throw new IllegalArgumentException("Parent must be a backpack.");
		}
		this.backpackDefinition = backpack.getDefinition();
	}

	@Override
	public boolean canSlotAccept(int slotIndex, ItemStack stack) {
		if (stack.getItem() instanceof ItemBackpack) {
			return false;
		}
		return this.backpackDefinition.getFilter().test(stack);
	}
}
