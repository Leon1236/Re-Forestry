package com.leon1236.reforestry.apiculture;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.apiculture.IBeeHousingInventory;
import com.leon1236.reforestry.apiculture.items.ItemBeeGE;

public class InventoryBeeHousing implements Container, IBeeHousingInventory {
	public static final int SLOT_QUEEN = 0;
	public static final int SLOT_DRONE = 1;
	public static final int SLOT_PRODUCT_1 = 2;
	public static final int SLOT_PRODUCT_COUNT = 7;
	public static final int SLOT_COUNT = SLOT_PRODUCT_1 + SLOT_PRODUCT_COUNT;

	private final NonNullList<ItemStack> items;
	private final Runnable onChanged;

	public InventoryBeeHousing(int size, Runnable onChanged) {
		this.items = NonNullList.withSize(size, ItemStack.EMPTY);
		this.onChanged = onChanged;
	}

	public InventoryBeeHousing(Runnable onChanged) {
		this(SLOT_COUNT, onChanged);
	}

	@Override
	public ItemStack getQueen() {
		return getItem(SLOT_QUEEN);
	}

	@Override
	public ItemStack getDrone() {
		return getItem(SLOT_DRONE);
	}

	@Override
	public void setQueen(ItemStack stack) {
		setItem(SLOT_QUEEN, stack);
	}

	@Override
	public void setDrone(ItemStack stack) {
		setItem(SLOT_DRONE, stack);
	}

	@Override
	public boolean addProduct(ItemStack product) {
		for (int i = 0; i < SLOT_PRODUCT_COUNT; i++) {
			int slot = SLOT_PRODUCT_1 + i;
			ItemStack existing = items.get(slot);
			if (existing.isEmpty()) {
				items.set(slot, product.copy());
				setChanged();
				return true;
			}
			if (ItemStack.isSameItemSameComponents(existing, product)
					&& existing.getCount() + product.getCount() <= existing.getMaxStackSize()) {
				existing.grow(product.getCount());
				setChanged();
				return true;
			}
		}
		return false;
	}

	@Override
	public int getContainerSize() {
		return items.size();
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
	public void setChanged() {
		onChanged.run();
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	@Override
	public void clearContent() {
		items.clear();
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		if (!(stack.getItem() instanceof ItemBeeGE bee)) {
			return false;
		}
		if (slot == SLOT_QUEEN) {
			return "queen".equals(bee.lifeStage()) || "princess".equals(bee.lifeStage());
		}
		if (slot == SLOT_DRONE) {
			return "drone".equals(bee.lifeStage());
		}
		return false;
	}

	public void save(ValueOutput output) {
		ContainerHelper.saveAllItems(output, items);
	}

	public void load(ValueInput input) {
		items.clear();
		ContainerHelper.loadAllItems(input, items);
	}

	public void writeToNbt(ValueOutput output) {
		save(output);
	}

	public void readFromNbt(ValueInput input) {
		load(input);
	}
}
