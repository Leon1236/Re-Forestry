package com.leon1236.reforestry.gendustry.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.genetics.pollen.IPollen;

public class PollenKitItem extends Item {
	public PollenKitItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext ctx) {
		Level level = ctx.getLevel();

		if (!level.isClientSide()) {
			Player player = ctx.getPlayer();
			BlockPos pos = ctx.getClickedPos();
			IPollen pollen = IForestryApi.get().getPollenManager().getPollen(level, pos);

			if (pollen != null && player != null) {
				ItemStack stack = pollen.createStack();
				if (!stack.isEmpty()) {
					if (!player.getInventory().add(stack)) {
						player.drop(stack, false);
					}
					ctx.getItemInHand().shrink(1);
				}
			}

			return InteractionResult.CONSUME;
		}

		return InteractionResult.SUCCESS;
	}
}
