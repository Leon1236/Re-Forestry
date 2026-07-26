package com.leon1236.reforestry.core.items;

import java.util.function.Supplier;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemAssemblyKit extends Item {
	private final Supplier<ItemStack> assembled;

	public ItemAssemblyKit(Properties properties, Supplier<ItemStack> assembled) {
		super(properties.stacksTo(24));
		this.assembled = assembled;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack heldItem = player.getItemInHand(hand);
		if (!level.isClientSide()) {
			heldItem.shrink(1);
			ItemEntity entity = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), this.assembled.get());
			level.addFreshEntity(entity);
		}
		return InteractionResult.SUCCESS;
	}
}
