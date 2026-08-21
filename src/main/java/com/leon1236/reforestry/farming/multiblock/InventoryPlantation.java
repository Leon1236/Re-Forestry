package com.leon1236.reforestry.farming.multiblock;

import java.util.ArrayDeque;
import java.util.List;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.agriculture.IFarmHousing;
import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.inventory.InventoryUtil;

public abstract class InventoryPlantation implements Container, IFarmInventoryInternal {
	protected final IFarmHousing housing;
	protected final InventoryConfig config;
	private final NonNullList<ItemStack> items;
	private final Runnable onChanged;
	protected final Container resourcesInventory;
	protected final Container germlingsInventory;
	protected final Container productInventory;
	protected final Container fertilizerInventory;

	public InventoryPlantation(IFarmHousing housing, InventoryConfig config, Runnable onChanged) {
		this.housing = housing;
		this.config = config;
		this.items = NonNullList.withSize(config.count, ItemStack.EMPTY);
		this.onChanged = onChanged;
		this.resourcesInventory = new InventorySlice(this, config.resourcesStart, config.resourcesCount);
		this.germlingsInventory = new InventorySlice(this, config.germlingsStart, config.germlingsCount);
		this.productInventory = new InventorySlice(this, config.productionStart, config.productionCount);
		this.fertilizerInventory = new InventorySlice(this, config.fertilizerStart, config.fertilizerCount);
	}

	@Override
	public int getContainerSize() {
		return this.items.size();
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

	@Override
	public void setChanged() {
		this.onChanged.run();
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		if (isInRange(slot, this.config.fertilizerStart, this.config.fertilizerCount)) {
			return acceptsAsFertilizer(stack);
		}
		if (isInRange(slot, this.config.germlingsStart, this.config.germlingsCount)) {
			return acceptsAsSeedling(stack);
		}
		if (isInRange(slot, this.config.resourcesStart, this.config.resourcesCount)) {
			return acceptsAsResource(stack);
		}
		if (isInRange(slot, this.config.canStart, this.config.canCount)) {
			return acceptsAsCan(stack);
		}
		return false;
	}

	private static boolean isInRange(int slot, int start, int count) {
		return slot >= start && slot < start + count;
	}

	@Override
	public boolean hasResources(List<ItemStack> resources) {
		for (ItemStack required : resources) {
			int found = 0;
			for (int i = 0; i < this.resourcesInventory.getContainerSize(); i++) {
				ItemStack have = this.resourcesInventory.getItem(i);
				if (ItemStack.isSameItemSameComponents(have, required)) {
					found += have.getCount();
				}
			}
			if (found < required.getCount()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void removeResources(List<ItemStack> resources) {
		for (ItemStack required : resources) {
			int remaining = required.getCount();
			for (int i = 0; i < this.resourcesInventory.getContainerSize() && remaining > 0; i++) {
				ItemStack have = this.resourcesInventory.getItem(i);
				if (!ItemStack.isSameItemSameComponents(have, required)) {
					continue;
				}
				int take = Math.min(remaining, have.getCount());
				this.resourcesInventory.removeItem(i, take);
				remaining -= take;
			}
		}
	}

	@Override
	public boolean acceptsAsSeedling(ItemStack stack) {
		if (stack.isEmpty() || isReservedFarmItem(stack)) {
			return false;
		}
		boolean foundReal = false;
		for (IFarmLogic logic : this.housing.getFarmLogics()) {
			if (logic == FakeFarmLogic.INSTANCE) {
				continue;
			}
			foundReal = true;
			if (logic.getType().isAcceptedSeedling(stack)) {
				return true;
			}
		}
		return !foundReal;
	}

	@Override
	public boolean acceptsAsResource(ItemStack stack) {
		if (stack.isEmpty() || isReservedFarmItem(stack)) {
			return false;
		}
		boolean foundReal = false;
		for (IFarmLogic logic : this.housing.getFarmLogics()) {
			if (logic == FakeFarmLogic.INSTANCE) {
				continue;
			}
			foundReal = true;
			if (logic.getType().isAcceptedResource(stack)) {
				return true;
			}
		}
		return !foundReal;
	}

	private boolean isReservedFarmItem(ItemStack stack) {
		return IForestryApi.get().getCircuitManager().isCircuitBoard(stack)
				|| acceptsAsFertilizer(stack)
				|| acceptsAsCan(stack);
	}

	@Override
	public boolean acceptsAsFertilizer(ItemStack stack) {
		if (stack.isEmpty()) {
			return false;
		}
		return IForestryApi.get().getFarmingManager().getFertilizeValue(stack) > 0;
	}

	@Override
	public Container getProductInventory() {
		return this.productInventory;
	}

	@Override
	public Container getGermlingsInventory() {
		return this.germlingsInventory;
	}

	@Override
	public Container getResourcesInventory() {
		return this.resourcesInventory;
	}

	@Override
	public Container getFertilizerInventory() {
		return this.fertilizerInventory;
	}

	public void drainCan(FilteredFluidStorage tank) {
		FluidContainerHelper.drainFromSlotToTank(this, this.config.canStart, tank);
	}

	public boolean acceptsAsCan(ItemStack stack) {
		if (stack.isEmpty() || !FluidContainerHelper.isFilledContainer(stack)) {
			return false;
		}
		FluidVariant fluid = FluidContainerHelper.fluidIn(stack);
		return fluid.getFluid() == Fluids.WATER
				|| fluid.getFluid() == Fluids.FLOWING_WATER;
	}

	@Override
	public void stowProducts(Iterable<ItemStack> harvested, ArrayDeque<ItemStack> pendingProduce) {
		for (ItemStack harvest : harvested) {
			int added = InventoryUtil.addStack(this.productInventory, harvest, 0,
					this.productInventory.getContainerSize(), true);
			harvest.shrink(added);
			if (!harvest.isEmpty()) {
				pendingProduce.push(harvest);
			}
		}
	}

	@Override
	public boolean tryAddPendingProduce(ArrayDeque<ItemStack> pendingProduce) {
		ItemStack next = pendingProduce.peek();
		if (next == null || next.isEmpty()) {
			return false;
		}
		boolean added = InventoryUtil.tryAddStack(this.productInventory, next, 0,
				this.productInventory.getContainerSize(), true, true);
		if (added) {
			pendingProduce.pop();
		}
		return added;
	}

	@Override
	public int getFertilizerValue() {
		ItemStack fertilizerStack = getItem(this.config.fertilizerStart);
		if (fertilizerStack.isEmpty()) {
			return 0;
		}
		return Math.max(0, IForestryApi.get().getFarmingManager().getFertilizeValue(fertilizerStack));
	}

	@Override
	public boolean useFertilizer() {
		ItemStack fertilizer = getItem(this.config.fertilizerStart);
		if (acceptsAsFertilizer(fertilizer)) {
			removeItem(this.config.fertilizerStart, 1);
			return true;
		}
		return false;
	}

	public void save(ValueOutput output) {
		ContainerHelper.saveAllItems(output, this.items);
	}

	public void load(ValueInput input) {
		this.items.clear();
		ContainerHelper.loadAllItems(input, this.items);
	}

	public static class InventoryConfig {
		public final int resourcesStart;
		public final int resourcesCount;
		public final int germlingsStart;
		public final int germlingsCount;
		public final int productionStart;
		public final int productionCount;
		public final int fertilizerStart;
		public final int fertilizerCount;
		public final int canStart;
		public final int canCount;
		public final int count;

		public InventoryConfig(
				int resourcesStart, int resourcesCount,
				int germlingsStart, int germlingsCount,
				int productionStart, int productionCount,
				int fertilizerStart, int fertilizerCount,
				int canStart, int canCount
		) {
			this.resourcesStart = resourcesStart;
			this.resourcesCount = resourcesCount;
			this.germlingsStart = germlingsStart;
			this.germlingsCount = germlingsCount;
			this.productionStart = productionStart;
			this.productionCount = productionCount;
			this.fertilizerStart = fertilizerStart;
			this.fertilizerCount = fertilizerCount;
			this.canStart = canStart;
			this.canCount = canCount;
			this.count = resourcesCount + germlingsCount + productionCount + fertilizerCount + canCount;
		}
	}

	private static final class InventorySlice implements Container {
		private final Container parent;
		private final int start;
		private final int size;

		InventorySlice(Container parent, int start, int size) {
			this.parent = parent;
			this.start = start;
			this.size = size;
		}

		@Override
		public int getContainerSize() {
			return this.size;
		}

		@Override
		public boolean isEmpty() {
			for (int i = 0; i < this.size; i++) {
				if (!getItem(i).isEmpty()) {
					return false;
				}
			}
			return true;
		}

		@Override
		public ItemStack getItem(int slot) {
			return this.parent.getItem(this.start + slot);
		}

		@Override
		public ItemStack removeItem(int slot, int amount) {
			return this.parent.removeItem(this.start + slot, amount);
		}

		@Override
		public ItemStack removeItemNoUpdate(int slot) {
			return this.parent.removeItemNoUpdate(this.start + slot);
		}

		@Override
		public void setItem(int slot, ItemStack stack) {
			this.parent.setItem(this.start + slot, stack);
		}

		@Override
		public void setChanged() {
			this.parent.setChanged();
		}

		@Override
		public boolean stillValid(Player player) {
			return this.parent.stillValid(player);
		}

		@Override
		public void clearContent() {
			for (int i = 0; i < this.size; i++) {
				this.parent.setItem(this.start + i, ItemStack.EMPTY);
			}
		}
	}
}
