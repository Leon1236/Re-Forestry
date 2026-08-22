package com.leon1236.reforestry.farming.items;

import java.util.function.Consumer;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

public class ItemBlockFarm extends BlockItem {
	public ItemBlockFarm(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
			Consumer<Component> tooltip, TooltipFlag flag) {
		if (isShiftDown()) {
			tooltip.accept(Component.translatable("block.reforestry.farm.tooltip").withStyle(ChatFormatting.GRAY));
		} else {
			tooltip.accept(Component.translatable("for.gui.tooltip.tmi").withStyle(ChatFormatting.GRAY));
		}
	}

	private static boolean isShiftDown() {
		if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) {
			return false;
		}
		return Minecraft.getInstance().hasShiftDown();
	}
}
