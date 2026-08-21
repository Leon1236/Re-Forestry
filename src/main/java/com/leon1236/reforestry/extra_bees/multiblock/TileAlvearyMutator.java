package com.leon1236.reforestry.extra_bees.multiblock;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.apiculture.inventory.InventoryAlvearyPart;
import com.leon1236.reforestry.apiculture.multiblock.MultiblockLogicAlveary;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.extra_bees.alveary.AlvearyMutationHandler;
import com.leon1236.reforestry.extra_bees.blocks.BlockExtraBeeAlvearyType;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesTiles;
import com.leon1236.reforestry.extra_bees.gui.ContainerAlvearyMutator;

public class TileAlvearyMutator extends TileExtraBeeAlveary
		implements IAlvearyComponent.BeeModifier<MultiblockLogicAlveary>,
		IAlvearyComponent.BeeListener<MultiblockLogicAlveary>,
		IMultiblockComponent.HasInventory,
		ExtendedMenuProvider<BlockPos>,
		IBeeModifier,
		IBeeListener,
		WorldlyContainer {
	public static final int SLOT_MUTATOR = 0;

	private final InventoryAlvearyPart inventory = new InventoryAlvearyPart(1, this::setChanged,
			(slot, stack) -> AlvearyMutationHandler.isMutationItem(stack));

	public TileAlvearyMutator(BlockPos pos, BlockState state) {
		super(ExtraBeesTiles.ALVEARY_MUTATOR.type(), BlockExtraBeeAlvearyType.MUTATOR, pos, state);
	}

	@Override
	public Container getInternalInventory() {
		return this.inventory;
	}

	@Override
	public IBeeModifier getBeeModifier() {
		return this;
	}

	@Override
	public IBeeListener getBeeListener() {
		return this;
	}

	@Override
	public float modifyMutationChance(IGenome genome, IGenome mate, IMutation mutation, float currentChance) {
		float mult = AlvearyMutationHandler.getMutationMult(this.inventory.getItem(SLOT_MUTATOR));
		return Math.min(mult * currentChance, 15f);
	}

	@Override
	public void onQueenDeath() {
		this.inventory.removeItem(SLOT_MUTATOR, 1);
	}

	@Override
	public int getContainerSize() {
		return this.inventory.getContainerSize();
	}

	@Override
	public boolean isEmpty() {
		return this.inventory.isEmpty();
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.inventory.getItem(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		return this.inventory.removeItem(slot, amount);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return this.inventory.removeItemNoUpdate(slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		this.inventory.setItem(slot, stack);
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return this.inventory.canPlaceItem(slot, stack);
	}

	@Override
	public void clearContent() {
		this.inventory.clearContent();
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return InventoryUtil.contiguousSlots(this.inventory.getContainerSize());
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return this.inventory.canPlaceItem(slot, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		NonNullList<ItemStack> items = NonNullList.withSize(this.inventory.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(input, items);
		for (int slot = 0; slot < items.size(); slot++) {
			this.inventory.setItem(slot, items.get(slot));
		}
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.inventory.getItems());
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerAlvearyMutator(containerId, playerInventory, this);
	}

	@Override
	public BlockPos getScreenOpeningData(ServerPlayer player) {
		return getBlockPos();
	}
}
