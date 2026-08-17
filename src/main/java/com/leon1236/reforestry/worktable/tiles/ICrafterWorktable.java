package com.leon1236.reforestry.worktable.tiles;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.level.Level;

public interface ICrafterWorktable {
	ItemStack getResult(CraftingInput input, Level level);

	boolean mayPickup(int slotIndex);

	boolean onCraftingStart(Player player);

	void onCraftingComplete(Player player);
}
