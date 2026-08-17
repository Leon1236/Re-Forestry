package com.leon1236.reforestry.worktable.tiles;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.recipes.CraftingInputHelper;
import com.leon1236.reforestry.core.tiles.TileBase;
import com.leon1236.reforestry.worktable.features.WorktableTiles;
import com.leon1236.reforestry.worktable.gui.ContainerWorktable;
import com.leon1236.reforestry.worktable.inventory.WorktableCraftingContainer;
import com.leon1236.reforestry.worktable.inventory.WorktableInventory;
import com.leon1236.reforestry.worktable.recipes.MemorizedRecipe;
import com.leon1236.reforestry.worktable.recipes.RecipeMemory;
import com.leon1236.reforestry.worktable.recipes.WorktableRecipeLookup;

public class TileWorktable extends TileBase implements WorldlyContainer, ICrafterWorktable {
	private final NonNullList<ItemStack> items = NonNullList.withSize(WorktableInventory.SLOT_INVENTORY_COUNT, ItemStack.EMPTY);
	private final SimpleContainer craftingDisplay = new SimpleContainer(9);
	private final SimpleContainer resultDisplay = new SimpleContainer(1);
	private RecipeMemory memory = new RecipeMemory();
	@Nullable
	private MemorizedRecipe currentRecipe;

	public TileWorktable(BlockPos pos, BlockState state) {
		super(WorktableTiles.WORKTABLE.type(), pos, state);
	}

	public RecipeMemory getMemory() {
		return this.memory;
	}

	public Container getCraftingDisplay() {
		return this.craftingDisplay;
	}

	public Container getResultDisplay() {
		return this.resultDisplay;
	}

	public boolean hasRecipeConflict() {
		return this.currentRecipe != null && this.currentRecipe.hasRecipeConflict();
	}

	public void chooseNextConflictRecipe() {
		if (this.currentRecipe != null) {
			this.currentRecipe.incrementRecipe();
			setChangedAndSync();
		}
	}

	public void choosePreviousConflictRecipe() {
		if (this.currentRecipe != null) {
			this.currentRecipe.decrementRecipe();
			setChangedAndSync();
		}
	}

	@Override
	public ItemStack getResult(CraftingInput input, Level level) {
		if (this.currentRecipe != null) {
			return this.currentRecipe.getCraftingResult(input, level);
		}
		return ItemStack.EMPTY;
	}

	public ItemStack getResult(Level level) {
		return getResult(CraftingInputHelper.fromGrid(this.craftingDisplay, 0), level);
	}

	@Override
	public boolean mayPickup(int craftingSlotIndex) {
		return canCraftCurrentRecipe();
	}

	private boolean canCraftCurrentRecipe() {
		return craftRecipe(true);
	}

	@Override
	public boolean onCraftingStart(Player player) {
		return craftRecipe(false);
	}

	private boolean craftRecipe(boolean simulate) {
		if (this.currentRecipe == null || this.level == null) {
			return false;
		}

		RecipeHolder<CraftingRecipe> selectedRecipe = this.currentRecipe.getSelectedRecipe(this.level);
		if (selectedRecipe == null) {
			return false;
		}

		NonNullList<ItemStack> inventoryStacks = InventoryUtil.getStacks(this);
		WorktableCraftingContainer usedMatrix = WorktableRecipeLookup.getUsedMatrix(
				this.currentRecipe.getCraftMatrix(), inventoryStacks, this.level, selectedRecipe.value());
		if (usedMatrix == null) {
			return false;
		}

		NonNullList<ItemStack> recipeItems = InventoryUtil.getStacks(usedMatrix);
		Container inventory;
		if (simulate) {
			inventory = new SimpleContainer(getContainerSize());
			WorktableRecipeLookup.deepCopy(this, inventory);
		} else {
			inventory = this;
		}

		if (!WorktableRecipeLookup.deleteExactSet(inventory, recipeItems)) {
			return false;
		}

		if (!simulate) {
			this.currentRecipe.setCraftMatrix(usedMatrix);
			setCurrentRecipe(this.currentRecipe);
		}
		return true;
	}

	@Override
	public void onCraftingComplete(Player player) {
		if (this.currentRecipe == null || this.level == null) {
			return;
		}
		RecipeHolder<CraftingRecipe> selectedRecipe = this.currentRecipe.getSelectedRecipe(this.level);
		if (selectedRecipe == null) {
			return;
		}

		WorktableCraftingContainer craftMatrix = this.currentRecipe.getCraftMatrix();
		NonNullList<ItemStack> remainingItems = selectedRecipe.value().getRemainingItems(craftMatrix.asCraftInput());
		for (ItemStack remainingItem : remainingItems) {
			if (remainingItem.isEmpty()) {
				continue;
			}
			if (!InventoryUtil.tryAddStack(this, remainingItem, 0, getContainerSize(), true)) {
				player.drop(remainingItem, false);
			}
		}

		if (!this.level.isClientSide()) {
			this.memory.memorizeRecipe(this.level.getGameTime(), this.currentRecipe, this.level);
			setChangedAndSync();
		}
	}

	public void chooseRecipeMemory(int recipeIndex) {
		setCurrentRecipe(this.memory.getRecipe(recipeIndex));
		setChangedAndSync();
	}

	public void clearCraftMatrix() {
		for (int slot = 0; slot < this.craftingDisplay.getContainerSize(); slot++) {
			this.craftingDisplay.setItem(slot, ItemStack.EMPTY);
		}
		this.currentRecipe = null;
		setChangedAndSync();
	}

	public void toggleMemoryLock(int recipeIndex) {
		if (this.level == null) {
			return;
		}
		this.memory.toggleLock(this.level.getGameTime(), recipeIndex);
		setChangedAndSync();
	}

	public void updateCurrentRecipeFromDisplay() {
		setCurrentRecipe(this.craftingDisplay);
	}

	public void setCurrentRecipe(Container crafting) {
		if (this.level == null) {
			return;
		}
		CraftingInput input = CraftingInputHelper.fromGrid(crafting, 0);
		List<RecipeHolder<CraftingRecipe>> recipes = WorktableRecipeLookup.findMatching(this.level, input);
		MemorizedRecipe recipe = recipes.isEmpty() ? null : new MemorizedRecipe(crafting, recipes);

		if (this.currentRecipe != null && recipe != null && recipe.hasRecipe(this.currentRecipe.getSelectedRecipe(this.level))) {
			NonNullList<ItemStack> stacks = InventoryUtil.getStacks(crafting);
			NonNullList<ItemStack> currentStacks = InventoryUtil.getStacks(this.currentRecipe.getCraftMatrix());
			if (WorktableRecipeLookup.equalSets(stacks, currentStacks)) {
				return;
			}
		}

		setCurrentRecipe(recipe);
	}

	@Nullable
	public MemorizedRecipe getCurrentRecipe() {
		return this.currentRecipe;
	}

	public void setCurrentRecipe(@Nullable MemorizedRecipe recipe) {
		this.currentRecipe = recipe;
		if (this.currentRecipe != null) {
			setCraftingDisplay(this.currentRecipe.getCraftMatrix());
		}
	}

	private void setCraftingDisplay(Container craftMatrix) {
		for (int slot = 0; slot < this.craftingDisplay.getContainerSize(); slot++) {
			ItemStack stack = slot < craftMatrix.getContainerSize() ? craftMatrix.getItem(slot) : ItemStack.EMPTY;
			this.craftingDisplay.setItem(slot, stack.isEmpty() ? ItemStack.EMPTY : stack.copy());
		}
	}

	private void setChangedAndSync() {
		setChanged();
		if (this.level != null && !this.level.isClientSide() && this.level.isLoaded(this.worldPosition)) {
			this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), 3);
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
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.items);
		NonNullList<ItemStack> craftItems = InventoryUtil.getStacks(this.craftingDisplay);
		ContainerHelper.saveAllItems(output.child("CraftItems"), craftItems);
		this.memory.save(output);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.items.clear();
		ContainerHelper.loadAllItems(input, this.items);
		NonNullList<ItemStack> craftItems = NonNullList.withSize(this.craftingDisplay.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(input.childOrEmpty("CraftItems"), craftItems);
		for (int i = 0; i < craftItems.size(); i++) {
			this.craftingDisplay.setItem(i, craftItems.get(i));
		}
		this.memory = new RecipeMemory();
		this.memory.load(input);
	}

	@Override
	public int getContainerSize() {
		return WorktableInventory.SLOT_INVENTORY_COUNT;
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
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return true;
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
		return WorldlyAccessHelper.getSlotsForFace(this, WorktableInventory.SLOT_INVENTORY_COUNT, direction);
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return WorldlyAccessHelper.canPlaceItemThroughFace(this, true, direction);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return WorldlyAccessHelper.canTakeItemThroughFace(this, true, direction);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerWorktable(containerId, playerInventory, this);
	}
}
