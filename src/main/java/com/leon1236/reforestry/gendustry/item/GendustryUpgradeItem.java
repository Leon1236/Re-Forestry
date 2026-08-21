package com.leon1236.reforestry.gendustry.item;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

public class GendustryUpgradeItem extends Item {
	private final IGendustryUpgradeType type;
	private String descriptionKey;

	public GendustryUpgradeItem(IGendustryUpgradeType type, Properties properties) {
		super(properties.stacksTo(type.maxStackSize()));
		this.type = type;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
			Consumer<Component> tooltip, TooltipFlag flag) {
		if (this.descriptionKey == null) {
			this.descriptionKey = getDescriptionId() + ".tooltip";
		}
		tooltip.accept(Component.translatable(this.descriptionKey).withStyle(ChatFormatting.GRAY));
		tooltip.accept(Component.empty());
		tooltip.accept(Component.translatable("item.reforestry.upgrade.energy_cost",
				Component.literal(String.valueOf(this.type.energyCost())).withStyle(ChatFormatting.YELLOW))
				.withStyle(ChatFormatting.GRAY));
		tooltip.accept(Component.translatable("item.reforestry.upgrade.max_count",
				Component.literal(String.valueOf(this.type.maxStackSize())).withStyle(ChatFormatting.YELLOW))
				.withStyle(ChatFormatting.GRAY));
	}

	public IGendustryUpgradeType getType() {
		return this.type;
	}
}
