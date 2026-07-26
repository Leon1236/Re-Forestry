package com.leon1236.reforestry.core.items;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;

import com.leon1236.reforestry.api.core.IArmorNaturalist;
import com.leon1236.reforestry.core.CoreArmorMaterials;

public class ItemSpectacles extends Item implements IArmorNaturalist {
	public ItemSpectacles(Properties properties) {
		super(properties.humanoidArmor(CoreArmorMaterials.NATURALIST, ArmorType.HELMET).durability(100));
	}

	@Override
	public boolean canSeePollination(Player player, ItemStack armor, boolean doSee) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
			Consumer<Component> tooltip, TooltipFlag flag) {
		tooltip.accept(Component.translatable("item.reforestry.naturalist_helmet.tooltip").withStyle(ChatFormatting.GRAY));
	}
}
