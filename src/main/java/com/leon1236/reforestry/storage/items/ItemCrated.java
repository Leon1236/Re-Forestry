package com.leon1236.reforestry.storage.items;

import java.util.function.Supplier;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemCrated extends Item {
	private final Supplier<ItemStack> contained;

	public ItemCrated(Supplier<ItemStack> contained, Properties properties) {
		super(properties);
		this.contained = contained;
	}

	public ItemStack getContained() {
		return this.contained.get();
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack heldItem = player.getItemInHand(hand);
		ItemStack contained = this.contained.get();
		if (contained.isEmpty() || heldItem.isEmpty()) {
			return InteractionResult.PASS;
		}
		if (!level.isClientSide()) {
			heldItem.shrink(1);
			ItemStack dropStack = contained.copy();
			dropStack.setCount(9);
			ItemEntity entity = new ItemEntity(
					level,
					player.getX() - player.getBbWidth() / 2.0,
					player.getY(),
					player.getZ() - player.getBbWidth() / 2.0,
					dropStack);
			entity.setPickUpDelay(40);
			level.addFreshEntity(entity);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public Component getName(ItemStack stack) {
		ItemStack contained = this.contained.get();
		if (contained.isEmpty()) {
			return Component.translatable("item.reforestry.crate");
		}
		return Component.translatable("for.item.crated.grammar", contained.getHoverName());
	}
}
