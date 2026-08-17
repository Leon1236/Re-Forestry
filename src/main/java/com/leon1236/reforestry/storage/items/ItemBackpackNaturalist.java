package com.leon1236.reforestry.storage.items;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.storage.EnumBackpackType;
import com.leon1236.reforestry.api.storage.IBackpackDefinition;
import com.leon1236.reforestry.storage.gui.ContainerNaturalistBackpack;
import com.leon1236.reforestry.storage.gui.NaturalistBackpackMenuData;

public class ItemBackpackNaturalist extends ItemBackpack {
	private final Identifier typeId;

	public ItemBackpackNaturalist(Identifier typeId, IBackpackDefinition definition, Properties properties) {
		super(definition, EnumBackpackType.NATURALIST, properties);
		this.typeId = typeId;
	}

	public Identifier getSpeciesTypeId() {
		return this.typeId;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack held = player.getItemInHand(hand);
		if (player.isShiftKeyDown()) {
			return super.use(level, player, hand);
		}
		if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
			boolean mainHand = hand == InteractionHand.MAIN_HAND;
			serverPlayer.openMenu(new NaturalistBackpackMenuProvider(0, this.typeId, mainHand, getDefinition().getName(held)));
		}
		return InteractionResult.SUCCESS;
	}

	public static final class NaturalistBackpackMenuProvider implements ExtendedMenuProvider<NaturalistBackpackMenuData> {
		private final int page;
		private final Identifier typeId;
		private final boolean mainHand;
		private final Component title;

		public NaturalistBackpackMenuProvider(int page, Identifier typeId, boolean mainHand, Component title) {
			this.page = page;
			this.typeId = typeId;
			this.mainHand = mainHand;
			this.title = title;
		}

		@Override
		public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
			InteractionHand hand = this.mainHand ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
			return new ContainerNaturalistBackpack(containerId, player, hand, this.page, this.typeId);
		}

		@Override
		public Component getDisplayName() {
			return this.title;
		}

		@Override
		public NaturalistBackpackMenuData getScreenOpeningData(ServerPlayer player) {
			return new NaturalistBackpackMenuData(this.page, this.typeId, this.mainHand);
		}
	}
}
