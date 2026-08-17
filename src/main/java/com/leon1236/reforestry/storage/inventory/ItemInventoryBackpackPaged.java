package com.leon1236.reforestry.storage.inventory;

import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ItemInventoryBackpackPaged extends ItemInventoryBackpack {
	private final Identifier typeId;

	public ItemInventoryBackpackPaged(Player player, InteractionHand hand, int size, Identifier typeId) {
		super(player, hand, size);
		this.typeId = typeId;
	}

	public Identifier getTypeId() {
		return this.typeId;
	}

	public ItemStack parentStack() {
		return getParent();
	}
}
