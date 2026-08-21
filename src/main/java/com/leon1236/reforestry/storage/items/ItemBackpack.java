package com.leon1236.reforestry.storage.items;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.item.ContainerStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.storage.BackpackEvents;
import com.leon1236.reforestry.api.storage.EnumBackpackType;
import com.leon1236.reforestry.api.storage.IBackpackDefinition;
import com.leon1236.reforestry.core.config.ForestryConfig;
import com.leon1236.reforestry.core.inventory.ItemInventory;
import com.leon1236.reforestry.storage.BackpackMode;
import com.leon1236.reforestry.storage.features.StorageDataComponents;
import com.leon1236.reforestry.storage.gui.BackpackMenuData;
import com.leon1236.reforestry.storage.gui.ContainerBackpack;
import com.leon1236.reforestry.storage.inventory.BackpackInventoryHelper;
import com.leon1236.reforestry.storage.inventory.ItemInventoryBackpack;

public class ItemBackpack extends Item {
	public static final int SLOTS_BACKPACK_DEFAULT = 15;
	public static final int SLOTS_BACKPACK_WOVEN = 45;
	public static final int SLOTS_BACKPACK_APIARIST = 125;

	private final IBackpackDefinition definition;
	private final EnumBackpackType type;

	public ItemBackpack(IBackpackDefinition definition, EnumBackpackType type, Properties properties) {
		super(properties.stacksTo(1)
				.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)
				.component(StorageDataComponents.BACKPACK_MODE.type(), BackpackMode.NEUTRAL));
		this.definition = definition;
		this.type = type;
	}

	public IBackpackDefinition getDefinition() {
		return this.definition;
	}

	public EnumBackpackType getType() {
		return this.type;
	}

	public int getBackpackSize() {
		return getSlotsForType(this.type);
	}

	public static int getSlotsForType(EnumBackpackType type) {
		return switch (type) {
			case NATURALIST -> SLOTS_BACKPACK_APIARIST;
			case WOVEN -> SLOTS_BACKPACK_WOVEN;
			case NORMAL -> SLOTS_BACKPACK_DEFAULT;
		};
	}

	public static BackpackMode getMode(ItemStack backpack) {
		if (!(backpack.getItem() instanceof ItemBackpack)) {
			return BackpackMode.NEUTRAL;
		}
		return backpack.getOrDefault(StorageDataComponents.BACKPACK_MODE.type(), BackpackMode.NEUTRAL);
	}

	public static void tryStowing(Player player, ItemStack backpackStack, ItemStack stack) {
		if (getMode(backpackStack) == BackpackMode.LOCKED) {
			return;
		}
		if (!(backpackStack.getItem() instanceof ItemBackpack backpack)) {
			return;
		}
		ItemInventoryBackpack inventory = new ItemInventoryBackpack(player, backpack.getBackpackSize(), backpackStack);
		if (BackpackEvents.STOW.invoker().onStow(player, backpack.getDefinition(), inventory, stack)) {
			return;
		}
		if (stack.isEmpty()) {
			return;
		}
		ItemStack remainder = BackpackInventoryHelper.insertItemStacked(inventory, stack);
		stack.setCount(remainder.getCount());
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack held = player.getItemInHand(hand);
		if (player.isShiftKeyDown()) {
			switchMode(held);
			return InteractionResult.SUCCESS;
		}
		if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
			boolean woven = this.type == EnumBackpackType.WOVEN;
			boolean mainHand = hand == InteractionHand.MAIN_HAND;
			serverPlayer.openMenu(new BackpackMenuProvider(woven, mainHand, this.definition.getName(held)));
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		if (player == null || !player.isShiftKeyDown()) {
			return InteractionResult.PASS;
		}
		Level level = context.getLevel();
		if (transferWithBlock(player, context.getItemInHand(), level, context.getClickedPos(), context.getClickedFace())) {
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	private boolean transferWithBlock(Player player, ItemStack stack, Level level, BlockPos pos, Direction side) {
		if (getMode(stack) == BackpackMode.LOCKED) {
			return false;
		}
		Storage<ItemVariant> target = ItemStorage.SIDED.find(level, pos, side);
		if (target == null) {
			return false;
		}
		if (level.isClientSide()) {
			return true;
		}
		ItemInventoryBackpack backpackInventory = new ItemInventoryBackpack(player, getBackpackSize(), stack);
		ContainerStorage backpackStorage = ContainerStorage.of(backpackInventory, null);
		try (Transaction transaction = Transaction.openOuter()) {
			if (getMode(stack) == BackpackMode.RECEIVE) {
				StorageUtil.move(target, backpackStorage, variant -> this.definition.getFilter().test(variant.toStack()), Long.MAX_VALUE, transaction);
			} else {
				StorageUtil.move(backpackStorage, target, variant -> true, Long.MAX_VALUE, transaction);
			}
			transaction.commit();
		}
		return true;
	}

	private static void switchMode(ItemStack stack) {
		BackpackMode mode = getMode(stack);
		int next = mode.ordinal() + 1;
		if (!ForestryConfig.enableBackpackResupply() && next == BackpackMode.RESUPPLY.ordinal()) {
			next++;
		}
		next %= BackpackMode.VALUES.length;
		stack.set(StorageDataComponents.BACKPACK_MODE.type(), BackpackMode.VALUES[next]);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
		int occupied = ItemInventory.getOccupiedSlotCount(stack, getBackpackSize());
		BackpackMode mode = getMode(stack);
		String infoKey = mode.getTranslationKey();
		if (infoKey != null) {
			tooltip.accept(Component.translatable(infoKey).withStyle(ChatFormatting.GRAY));
		}
		tooltip.accept(Component.translatable("for.gui.slots", String.valueOf(occupied), String.valueOf(getBackpackSize())).withStyle(ChatFormatting.GRAY));
	}

	private record BackpackMenuProvider(boolean woven, boolean mainHand, Component title) implements ExtendedMenuProvider<BackpackMenuData> {
		@Override
		public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
			InteractionHand hand = this.mainHand ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
			ContainerBackpack.Size size = this.woven ? ContainerBackpack.Size.T2 : ContainerBackpack.Size.DEFAULT;
			return new ContainerBackpack(containerId, player, size, hand);
		}

		@Override
		public Component getDisplayName() {
			return this.title;
		}

		@Override
		public BackpackMenuData getScreenOpeningData(ServerPlayer player) {
			return new BackpackMenuData(this.woven, this.mainHand);
		}
	}
}
