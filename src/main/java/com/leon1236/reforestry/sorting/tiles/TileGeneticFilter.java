package com.leon1236.reforestry.sorting.tiles;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.core.ILocationProvider;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.api.genetics.filter.FilterData;
import com.leon1236.reforestry.core.tiles.TileForestry;
import com.leon1236.reforestry.sorting.FilterLogic;
import com.leon1236.reforestry.sorting.features.SortingTiles;
import com.leon1236.reforestry.sorting.gui.ContainerGeneticFilter;

public class TileGeneticFilter extends TileForestry implements Container, ILocationProvider {
	private static final int TRANSFER_DELAY = 5;
	private static final int SLOT_COUNT = 6;

	private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
	private final FilterLogic logic;

	public TileGeneticFilter(BlockPos pos, BlockState state) {
		super(SortingTiles.GENETIC_FILTER.type(), pos, state);
		this.logic = new FilterLogic(this, (logic1, level, player) -> sendToPlayers(level, player));
	}

	public FilterLogic getLogic() {
		return this.logic;
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, TileGeneticFilter tile) {
		tile.advanceTicks();
		if (!tile.updateOnInterval(TRANSFER_DELAY)) {
			return;
		}
		for (Direction facing : Direction.values()) {
			ItemStack stack = tile.getItem(facing.get3DDataValue());
			if (stack.isEmpty()) {
				continue;
			}
			ItemStack transferredStack = tile.transferItem(stack, facing);
			int remaining = stack.getCount() - transferredStack.getCount();
			if (remaining > 0) {
				ItemStack leftover = stack.copy();
				leftover.setCount(remaining);
				ItemEntity entity = new ItemEntity(level, tile.worldPosition.getX(), tile.worldPosition.getY() + 0.5F,
						tile.worldPosition.getZ(), leftover);
				level.addFreshEntity(entity);
			}
			tile.setItem(facing.get3DDataValue(), ItemStack.EMPTY);
		}
	}

	public boolean isConnected(Direction facing) {
		Level level = this.level;
		if (level == null) {
			return false;
		}
		BlockPos neighborPos = this.worldPosition.relative(facing);
		if (!level.isLoaded(neighborPos)) {
			return false;
		}
		if (ItemStorage.SIDED.find(level, neighborPos, facing.getOpposite()) != null) {
			return true;
		}
		BlockEntity neighbor = level.getBlockEntity(neighborPos);
		return neighbor instanceof Container;
	}

	private ItemStack transferItem(ItemStack itemStack, Direction facing) {
		Level level = this.level;
		if (level == null) {
			return ItemStack.EMPTY;
		}
		BlockPos neighborPos = this.worldPosition.relative(facing);
		if (!level.isLoaded(neighborPos)) {
			return ItemStack.EMPTY;
		}
		Storage<ItemVariant> storage = ItemStorage.SIDED.find(level, neighborPos, facing.getOpposite());
		if (storage == null) {
			return ItemStack.EMPTY;
		}
		ItemVariant variant = ItemVariant.of(itemStack);
		try (Transaction simulate = Transaction.openOuter()) {
			long inserted = storage.insert(variant, itemStack.getCount(), simulate);
			if (inserted <= 0) {
				return ItemStack.EMPTY;
			}
		}
		try (Transaction actual = Transaction.openOuter()) {
			long inserted = storage.insert(variant, itemStack.getCount(), actual);
			if (inserted <= 0) {
				return ItemStack.EMPTY;
			}
			actual.commit();
			ItemStack transferred = itemStack.copy();
			transferred.setCount((int) inserted);
			return transferred;
		}
	}

	public List<Direction> getValidDirections(ItemStack stack, Direction from) {
		List<Direction> validFacings = new ArrayList<>();
		IndividualItems.filter(stack, (genome, stage) -> {
			var typeId = IndividualItems.getSpeciesTypeId(stack);
			if (typeId == null) {
				return false;
			}
			FilterData filterData = new FilterData(typeId, genome, stage);
			for (Direction facing : Direction.values()) {
				if (facing == from) {
					continue;
				}
				if (isValidFacing(facing, stack, filterData)) {
					validFacings.add(facing);
				}
			}
			return true;
		});
		return validFacings;
	}

	private boolean isValidFacing(Direction facing, ItemStack itemStack, FilterData filterData) {
		return isConnected(facing) && this.logic.isValid(facing, itemStack, filterData);
	}

	private void sendToPlayers(net.minecraft.server.level.ServerLevel server, Player filterChanger) {
		for (Player player : server.players()) {
			if (player != filterChanger && player.containerMenu instanceof ContainerGeneticFilter other
					&& filterChanger.containerMenu instanceof ContainerGeneticFilter changer
					&& changer.hasSameTile(other)) {
				other.setGuiNeedsUpdate(true);
			}
		}
	}

	@Override
	public BlockPos getCoordinates() {
		return this.worldPosition;
	}

	@Override
	@Nullable
	public Level getWorldObj() {
		return this.level;
	}

	@Override
	public int getContainerSize() {
		return SLOT_COUNT;
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
	}

	public void setSlotStack(int slot, ItemStack stack) {
		this.items.set(slot, stack);
		if (stack.getCount() > getMaxStackSize(stack)) {
			stack.setCount(getMaxStackSize(stack));
		}
	}

	@Override
	public boolean stillValid(Player player) {
		return isUsableByPlayer(player);
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.items);
		this.logic.write(output.child("Logic"));
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.items.clear();
		ContainerHelper.loadAllItems(input, this.items);
		this.logic.read(input.childOrEmpty("Logic"));
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerGeneticFilter(containerId, playerInventory, this);
	}
}
