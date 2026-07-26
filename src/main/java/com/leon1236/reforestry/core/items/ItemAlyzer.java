package com.leon1236.reforestry.core.items;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.core.gui.ContainerAlyzer;
import com.leon1236.reforestry.core.inventory.ItemInventoryAlyzer;

public class ItemAlyzer extends Item {
	public ItemAlyzer(Properties properties) {
		super(properties.stacksTo(1).component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
			boolean mainHand = hand == InteractionHand.MAIN_HAND;
			serverPlayer.openMenu(new AlyzerMenuProvider(mainHand));
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
		int charges = 0;
		ItemContainerContents contents = stack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY);
		net.minecraft.core.NonNullList<ItemStack> slots = net.minecraft.core.NonNullList.withSize(1, ItemStack.EMPTY);
		contents.copyInto(slots);
		ItemStack energy = slots.get(0);
		if (ItemInventoryAlyzer.isAlyzingFuel(energy)) {
			charges = energy.getCount();
		}
		tooltip.accept(Component.translatable(getDescriptionId() + ".charges", charges).withStyle(ChatFormatting.GOLD));
	}

	private record AlyzerMenuProvider(boolean mainHand) implements ExtendedMenuProvider<Boolean> {
		@Override
		public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
			InteractionHand hand = mainHand ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
			return new ContainerAlyzer(containerId, new ItemInventoryAlyzer(player, hand), player);
		}

		@Override
		public Component getDisplayName() {
			return Component.translatable("item.reforestry.portable_alyzer");
		}

		@Override
		public Boolean getScreenOpeningData(ServerPlayer player) {
			return mainHand;
		}
	}
}
