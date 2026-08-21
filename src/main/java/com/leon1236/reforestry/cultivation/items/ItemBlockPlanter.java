package com.leon1236.reforestry.cultivation.items;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import com.leon1236.reforestry.cultivation.blocks.BlockPlanter;

public class ItemBlockPlanter extends BlockItem {
	public ItemBlockPlanter(BlockPlanter block, Item.Properties properties) {
		super(block, properties);
	}

	@Override
	public BlockPlanter getBlock() {
		return (BlockPlanter) super.getBlock();
	}

	@Override
	public Component getName(ItemStack stack) {
		BlockPlanter block = getBlock();
		return Component.translatable("block.reforestry.planter." + (block.isManual() ? "manual" : "managed"),
				Component.translatable("block.reforestry." + block.blockType.getSerializedName()));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
			Consumer<Component> tooltip, TooltipFlag flag) {
		String tooltipKey = "block.reforestry." + getBlock().blockType.getSerializedName() + ".tooltip";
		if (Language.getInstance().has(tooltipKey)) {
			tooltip.accept(Component.translatable(tooltipKey).withStyle(ChatFormatting.GRAY));
		}
	}
}
