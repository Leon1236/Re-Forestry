package com.leon1236.reforestry.extratrees.items;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.extratrees.blocks.ExtraTreesMachineBlock;

public class ItemBlockExtraTreesMachine extends BlockItem {
	public ItemBlockExtraTreesMachine(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
			Consumer<Component> tooltip, TooltipFlag flag) {
		if (getBlock() instanceof ExtraTreesMachineBlock machine) {
			String key = "extratrees.machine." + machine.blockType.getSerializedName() + ".info";
			tooltip.accept(Component.translatable(key).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
		}
	}
}
