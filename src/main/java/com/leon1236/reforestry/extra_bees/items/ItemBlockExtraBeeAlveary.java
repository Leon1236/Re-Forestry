package com.leon1236.reforestry.extra_bees.items;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.extra_bees.blocks.BlockExtraBeeAlveary;
import com.leon1236.reforestry.extra_bees.blocks.BlockExtraBeeAlvearyType;

public class ItemBlockExtraBeeAlveary extends BlockItem {
	public ItemBlockExtraBeeAlveary(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
			Consumer<Component> tooltip, TooltipFlag flag) {
		tooltip.accept(Component.translatable("block.reforestry.alveary_tooltip"));
		if (getBlock() instanceof BlockExtraBeeAlveary alveary) {
			tooltip.accept(Component.translatable(infoKey(alveary.getType())).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
		}
	}

	private static String infoKey(BlockExtraBeeAlvearyType type) {
		return switch (type) {
			case MUTATOR -> "extrabees.alveary.mutator.info";
			case FRAME -> "extrabees.alveary.frame.info";
			case RAIN_SHIELD -> "extrabees.alveary.rainshield.info";
			case LIGHTING -> "extrabees.alveary.lighting.info";
			case STIMULATOR -> "extrabees.alveary.stimulator.info";
			case HATCHERY -> "extrabees.alveary.hatchery.info";
			case TRANSMISSION -> "extrabees.alveary.transmission.info";
		};
	}
}
