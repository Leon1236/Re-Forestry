package com.leon1236.reforestry.core.tiles;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.gui.ContainerNaturalistChest;
import com.leon1236.reforestry.core.gui.NaturalistChestMenuData;
import com.leon1236.reforestry.core.gui.NaturalistInventoryLayout;

public abstract class TileNaturalistChest extends TileBase implements WorldlyContainer {
	public static final int SLOT_COUNT = NaturalistInventoryLayout.MAX_PAGE * NaturalistInventoryLayout.SLOTS_PER_PAGE;

	private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
	private final Identifier speciesTypeId;
	private int numPlayersUsing;
	private boolean flippingPage;

	protected TileNaturalistChest(BlockEntityType<?> type, BlockPos pos, BlockState state, Identifier speciesTypeId) {
		super(type, pos, state);
		this.speciesTypeId = speciesTypeId;
	}

	public Identifier getSpeciesTypeId() {
		return this.speciesTypeId;
	}

	@Override
	public void openGui(Player player) {
		if (player instanceof ServerPlayer serverPlayer) {
			openMenu(serverPlayer, 0);
		}
	}

	public void flipPage(ServerPlayer player, int page) {
		this.flippingPage = true;
		openMenu(player, page);
	}

	private void openMenu(ServerPlayer player, int page) {
		player.openMenu(new PagedMenuProvider(page));
	}

	public void onMenuOpened() {
		if (this.flippingPage) {
			this.flippingPage = false;
			return;
		}
		increaseNumPlayersUsing();
	}

	public void onMenuClosed() {
		if (this.flippingPage) {
			return;
		}
		decreaseNumPlayersUsing();
	}

	private void increaseNumPlayersUsing() {
		Level level = getLevel();
		if (this.numPlayersUsing == 0 && level != null) {
			playLidSound(level, true);
		}
		this.numPlayersUsing++;
	}

	private void decreaseNumPlayersUsing() {
		this.numPlayersUsing--;
		if (this.numPlayersUsing < 0) {
			this.numPlayersUsing = 0;
		}
		Level level = getLevel();
		if (this.numPlayersUsing == 0 && level != null) {
			playLidSound(level, false);
		}
	}

	private void playLidSound(Level level, boolean open) {
		level.playSound(null, getBlockPos(), open ? SoundEvents.CHEST_OPEN : SoundEvents.CHEST_CLOSE,
				SoundSource.BLOCKS, 0.5F, level.getRandom().nextFloat() * 0.1F + 0.9F);
	}

	public boolean canAccept(ItemStack stack) {
		Identifier typeId = IndividualItems.getSpeciesTypeId(stack);
		return typeId != null && this.speciesTypeId.equals(typeId);
	}

	@Override
	public int getContainerSize() {
		return SLOT_COUNT;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : items) {
			if (!stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
		return items.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		ItemStack result = ContainerHelper.removeItem(items, slot, amount);
		if (!result.isEmpty()) {
			setChanged();
		}
		return result;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(items, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		items.set(slot, stack);
		if (stack.getCount() > getMaxStackSize(stack)) {
			stack.setCount(getMaxStackSize(stack));
		}
		setChanged();
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return canAccept(stack);
	}

	@Override
	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public void clearContent() {
		items.clear();
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return WorldlyAccessHelper.getSlotsForFace(this, SLOT_COUNT, direction);
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return WorldlyAccessHelper.canPlaceItemThroughFace(this, canPlaceItem(slot, stack), direction);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return WorldlyAccessHelper.canTakeItemThroughFace(this, true, direction);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, items);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		items.clear();
		ContainerHelper.loadAllItems(input, items);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerNaturalistChest(containerId, playerInventory, this, 0);
	}

	private final class PagedMenuProvider implements ExtendedMenuProvider<NaturalistChestMenuData> {
		private final int page;

		private PagedMenuProvider(int page) {
			this.page = page;
		}

		@Override
		public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
			return new ContainerNaturalistChest(containerId, playerInventory, TileNaturalistChest.this, this.page);
		}

		@Override
		public Component getDisplayName() {
			return TileNaturalistChest.this.getDisplayName();
		}

		@Override
		public NaturalistChestMenuData getScreenOpeningData(ServerPlayer player) {
			return new NaturalistChestMenuData(getBlockPos(), this.page);
		}
	}
}
