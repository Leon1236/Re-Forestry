package com.leon1236.reforestry.core.inventory;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

public abstract class ItemInventory implements Container {
	protected final Player player;
	private final InteractionHand hand;
	private final Item parentItem;
	private final NonNullList<ItemStack> inventoryStacks;

	protected ItemInventory(Player player, InteractionHand hand, int size) {
		this.player = player;
		this.hand = hand;
		ItemStack parent = player.getItemInHand(hand);
		this.parentItem = parent.getItem();
		this.inventoryStacks = NonNullList.withSize(size, ItemStack.EMPTY);
		ItemContainerContents contents = parent.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY);
		contents.copyInto(this.inventoryStacks);
	}

	protected ItemStack getParent() {
		ItemStack held = this.player.getItemInHand(this.hand);
		if (held.isEmpty() || held.getItem() != this.parentItem) {
			return ItemStack.EMPTY;
		}
		return held;
	}

	public InteractionHand getHand() {
		return this.hand;
	}

	protected void writeToParent() {
		ItemStack parent = getParent();
		if (parent.isEmpty()) {
			return;
		}
		List<ItemStack> copy = new ArrayList<>(this.inventoryStacks.size());
		for (ItemStack stack : this.inventoryStacks) {
			copy.add(stack.copy());
		}
		parent.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(copy));
		onWriteParent(parent);
	}

	protected void onWriteParent(ItemStack parent) {
	}

	@Override
	public int getContainerSize() {
		return this.inventoryStacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : this.inventoryStacks) {
			if (!stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.inventoryStacks.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		ItemStack result = ContainerHelper.removeItem(this.inventoryStacks, slot, amount);
		if (!result.isEmpty()) {
			setChanged();
		}
		return result;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(this.inventoryStacks, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		this.inventoryStacks.set(slot, stack);
		if (stack.getCount() > getMaxStackSize()) {
			stack.setCount(getMaxStackSize());
		}
		setChanged();
	}

	@Override
	public void setChanged() {
		writeToParent();
	}

	@Override
	public boolean stillValid(Player player) {
		return player == this.player && !getParent().isEmpty();
	}

	@Override
	public void clearContent() {
		this.inventoryStacks.clear();
		setChanged();
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return canSlotAccept(slot, stack);
	}

	public boolean canSlotAccept(int slot, ItemStack stack) {
		return true;
	}
}
