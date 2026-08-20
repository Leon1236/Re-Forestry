package com.leon1236.reforestry.core.escritoire;

import com.mojang.authlib.GameProfile;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.features.CoreTiles;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.network.PacketRegistry;
import com.leon1236.reforestry.core.network.packets.EscritoireGameSyncPayload;
import com.leon1236.reforestry.core.tiles.TileBase;

public class TileEscritoire extends TileBase implements WorldlyContainer {
	private final NonNullList<ItemStack> items = NonNullList.withSize(InventoryEscritoire.SLOT_COUNT, ItemStack.EMPTY);
	private final EscritoireGame game = new EscritoireGame();

	public TileEscritoire(BlockPos pos, BlockState state) {
		super(CoreTiles.ESCRITOIRE.type(), pos, state);
	}

	public EscritoireGame getGame() {
		return this.game;
	}

	public void choose(GameProfile gameProfile, int index) {
		this.game.choose(index);
		processTurnResult(gameProfile);
	}

	private void processTurnResult(GameProfile gameProfile) {
		if (getGame().getStatus() != EscritoireGame.Status.SUCCESS) {
			return;
		}
		Level level = this.level;
		if (level == null || level.isClientSide()) {
			return;
		}
		ItemStack specimen = getItem(InventoryEscritoire.SLOT_ANALYZE);
		for (ItemStack bounty : EscritoireResearch.getResearchBounty(
				specimen, level, gameProfile, this.game.getBountyLevel())) {
			InventoryUtil.addStack(
					this,
					bounty,
					InventoryEscritoire.SLOT_RESULTS_1,
					InventoryEscritoire.SLOTS_RESULTS_COUNT,
					true);
		}
	}

	private boolean areProbeSlotsFilled() {
		int filledSlots = 0;
		int required = this.game.getSampleSize(InventoryEscritoire.SLOTS_INPUT_COUNT);
		for (int i = InventoryEscritoire.SLOT_INPUT_1; i < InventoryEscritoire.SLOT_INPUT_1 + required; i++) {
			if (!getItem(i).isEmpty()) {
				filledSlots++;
			}
		}
		return filledSlots >= required;
	}

	public void probe() {
		if (this.level == null || this.level.isClientSide()) {
			return;
		}
		ItemStack analyze = getItem(InventoryEscritoire.SLOT_ANALYZE);
		if (!analyze.isEmpty() && areProbeSlotsFilled()) {
			this.game.probe(analyze, this, InventoryEscritoire.SLOT_INPUT_1, InventoryEscritoire.SLOTS_INPUT_COUNT);
		}
	}

	public void syncGameToPlayer(ServerPlayer player) {
		PacketRegistry.sendToPlayer(player, EscritoireGameSyncPayload.of(this.worldPosition, this.game));
	}

	@Override
	public int getContainerSize() {
		return InventoryEscritoire.SLOT_COUNT;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : this.items) {
			if (!stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.items.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		ItemStack result = ContainerHelper.removeItem(this.items, slot, amount);
		if (!result.isEmpty()) {
			setChanged();
			if (slot == InventoryEscritoire.SLOT_ANALYZE) {
				this.game.reset();
				syncToClient();
			}
		}
		return result;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(this.items, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		this.items.set(slot, stack);
		if (stack.getCount() > getMaxStackSize(stack)) {
			stack.setCount(getMaxStackSize(stack));
		}
		setChanged();
		if (slot == InventoryEscritoire.SLOT_ANALYZE) {
			ItemStack specimen = getItem(InventoryEscritoire.SLOT_ANALYZE);
			Level level = this.level;
			if (level != null && !level.isClientSide()) {
				this.game.initialize(specimen);
			}
			syncToClient();
		}
	}

	public ItemStack getIndividualOnDisplay() {
		return getItem(InventoryEscritoire.SLOT_ANALYZE);
	}

	private void syncToClient() {
		Level level = getLevel();
		if (level != null && !level.isClientSide() && level.isLoaded(this.worldPosition)) {
			level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), 3);
		}
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		return saveCustomOnly(registries);
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		if (InventoryEscritoire.isLocked(this, slot)) {
			return false;
		}
		return InventoryEscritoire.canSlotAccept(this, slot, stack);
	}

	@Override
	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return WorldlyAccessHelper.getSlotsForFace(this, InventoryEscritoire.SLOT_COUNT, direction);
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return WorldlyAccessHelper.canPlaceItemThroughFace(this, canPlaceItem(slot, stack), direction);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		boolean results = InventoryEscritoire.isSlotInRange(
				slot, InventoryEscritoire.SLOT_RESULTS_1, InventoryEscritoire.SLOTS_RESULTS_COUNT);
		return WorldlyAccessHelper.canTakeItemThroughFace(this, results, direction);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.items);
		this.game.write(output.child("Game"));
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.items.clear();
		ContainerHelper.loadAllItems(input, this.items);
		this.game.read(input.childOrEmpty("Game"));
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerEscritoire(containerId, playerInventory, this);
	}
}
