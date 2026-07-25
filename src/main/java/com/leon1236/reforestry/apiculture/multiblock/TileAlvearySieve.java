package com.leon1236.reforestry.apiculture.multiblock;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.apiculture.blocks.BlockAlvearyType;
import com.leon1236.reforestry.apiculture.gui.ContainerAlvearySieve;
import com.leon1236.reforestry.apiculture.inventory.InventoryAlvearyPart;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.core.items.EnumCraftingMaterial;

public class TileAlvearySieve extends TileAlveary
		implements IAlvearyComponent.BeeListener<MultiblockLogicAlveary>, IMultiblockComponent.HasInventory {
	public static final int SLOT_POLLEN_1 = 0;
	public static final int SLOT_POLLEN_COUNT = 4;
	public static final int SLOT_SIEVE = 4;

	private final InventoryAlvearyPart inventory = new InventoryAlvearyPart(5, this::setChanged,
			(slot, stack) -> slot == SLOT_SIEVE && isWovenSilk(stack));
	private final IBeeListener beeListener = new SieveBeeListener();

	public TileAlvearySieve(BlockPos pos, BlockState state) {
		super(BlockAlvearyType.SIEVE, pos, state);
	}

	@Override
	public Container getInternalInventory() {
		return this.inventory;
	}

	@Override
	public IBeeListener getBeeListener() {
		return this.beeListener;
	}

	private static boolean isWovenSilk(ItemStack stack) {
		return stack.is(CoreItems.CRAFTING_MATERIALS.get(EnumCraftingMaterial.WOVEN_SILK).item());
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
		return new ContainerAlvearySieve(containerId, playerInventory, this);
	}

	private class SieveBeeListener implements IBeeListener {
		@Override
		public boolean onPollenRetrieved(IGenome pollen) {
			InventoryAlvearyPart inventory = TileAlvearySieve.this.inventory;
			if (inventory.getItem(SLOT_SIEVE).isEmpty() || !inventory.hasFreeSlot(SLOT_POLLEN_1, SLOT_POLLEN_COUNT)) {
				return false;
			}

			ItemStack pollenStack = new ItemStack(ArboricultureItems.POLLEN_FERTILE.item());
			pollenStack.set(ArboricultureDataComponents.TREE_GENOME.type(), pollen);
			return inventory.storeInFirstEmptySlot(SLOT_POLLEN_1, SLOT_POLLEN_COUNT, pollenStack);
		}
	}
}
