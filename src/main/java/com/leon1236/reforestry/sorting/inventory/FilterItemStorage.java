package com.leon1236.reforestry.sorting.inventory;

import java.util.Iterator;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.base.SnapshotParticipant;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.sorting.tiles.TileGeneticFilter;

public class FilterItemStorage extends SnapshotParticipant<NonNullList<ItemStack>> implements Storage<ItemVariant> {
	private final TileGeneticFilter filter;
	private final Direction facing;

	public FilterItemStorage(TileGeneticFilter filter, Direction facing) {
		this.filter = filter;
		this.facing = facing;
	}

	@Override
	public boolean supportsInsertion() {
		return true;
	}

	@Override
	public boolean supportsExtraction() {
		return false;
	}

	@Override
	public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
		if (resource.isBlank() || maxAmount <= 0) {
			return 0;
		}
		int count = (int) Math.min(maxAmount, resource.getItem().getDefaultMaxStackSize());
		ItemStack stack = resource.toStack(count);
		ItemStack remaining = stack.copy();
		boolean changed = false;
		for (Direction output : this.filter.getValidDirections(stack, this.facing)) {
			if (remaining.isEmpty()) {
				break;
			}
			int slot = output.get3DDataValue();
			ItemStack current = this.filter.getItem(slot);
			if (current.isEmpty()) {
				if (!changed) {
					updateSnapshots(transaction);
					changed = true;
				}
				this.filter.setSlotStack(slot, remaining.copy());
				remaining = ItemStack.EMPTY;
				break;
			}
			if (!ItemStack.isSameItemSameComponents(current, remaining)) {
				continue;
			}
			int max = Math.min(current.getMaxStackSize(), remaining.getMaxStackSize());
			int space = max - current.getCount();
			if (space <= 0) {
				continue;
			}
			if (!changed) {
				updateSnapshots(transaction);
				changed = true;
			}
			int moved = Math.min(space, remaining.getCount());
			current.grow(moved);
			this.filter.setSlotStack(slot, current);
			remaining.shrink(moved);
		}
		return stack.getCount() - remaining.getCount();
	}

	@Override
	public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
		return 0;
	}

	@Override
	public Iterator<StorageView<ItemVariant>> iterator() {
		return java.util.Collections.emptyIterator();
	}

	@Override
	protected NonNullList<ItemStack> createSnapshot() {
		NonNullList<ItemStack> copy = NonNullList.withSize(this.filter.getContainerSize(), ItemStack.EMPTY);
		for (int i = 0; i < copy.size(); i++) {
			copy.set(i, this.filter.getItem(i).copy());
		}
		return copy;
	}

	@Override
	protected void readSnapshot(NonNullList<ItemStack> snapshot) {
		for (int i = 0; i < snapshot.size(); i++) {
			this.filter.setSlotStack(i, snapshot.get(i).copy());
		}
	}

	@Override
	protected void onFinalCommit() {
		this.filter.setChanged();
	}
}
