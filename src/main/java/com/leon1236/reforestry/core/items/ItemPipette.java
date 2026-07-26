package com.leon1236.reforestry.core.items;

import java.util.function.Consumer;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import com.leon1236.reforestry.api.core.IToolPipette;
import com.leon1236.reforestry.core.fluids.PipetteContents;
import com.leon1236.reforestry.core.features.FluidsItems;

public class ItemPipette extends Item implements IToolPipette {
	public ItemPipette(Properties properties) {
		super(properties.stacksTo(1).component(FluidsItems.FLUID_CONTENTS.type(), FluidContainerContents.EMPTY));
	}

	@Override
	public boolean canPipette(ItemStack itemstack) {
		PipetteContents contained = PipetteContents.create(itemstack);
		return contained == null || !contained.isFull();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
			Consumer<Component> tooltip, TooltipFlag flag) {
		PipetteContents contained = PipetteContents.create(stack);
		if (contained != null) {
			contained.addTooltip(tooltip);
		}
	}
}
