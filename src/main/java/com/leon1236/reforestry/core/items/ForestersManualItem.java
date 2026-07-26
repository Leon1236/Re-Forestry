package com.leon1236.reforestry.core.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ForestersManualItem extends Item {
	public ForestersManualItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (!level.isClientSide()) {
			player.sendSystemMessage(Component.translatable("item.reforestry.foresters_manual.stub"));
		}
		return InteractionResult.SUCCESS;
	}
}
