package com.leon1236.reforestry.extra_bees.multiblock;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
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

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.hives.IHiveFrame;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.inventory.InventoryAlvearyPart;
import com.leon1236.reforestry.apiculture.multiblock.MultiblockLogicAlveary;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.extra_bees.blocks.BlockExtraBeeAlvearyType;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesTiles;
import com.leon1236.reforestry.extra_bees.gui.ContainerAlvearyFrame;

public class TileAlvearyFrameHousing extends TileExtraBeeAlveary
		implements IAlvearyComponent.BeeModifier<MultiblockLogicAlveary>,
		IAlvearyComponent.BeeListener<MultiblockLogicAlveary>,
		IMultiblockComponent.HasInventory,
		ExtendedMenuProvider<BlockPos>,
		IBeeModifier,
		IBeeListener,
		WorldlyContainer {
	public static final int SLOT_FRAME = 0;
	private static final int WEAR_MULTIPLIER = 5;

	private final InventoryAlvearyPart inventory = new InventoryAlvearyPart(1, this::setChanged,
			(slot, stack) -> stack.getItem() instanceof IHiveFrame);

	public TileAlvearyFrameHousing(BlockPos pos, BlockState state) {
		super(ExtraBeesTiles.ALVEARY_FRAME.type(), BlockExtraBeeAlvearyType.FRAME, pos, state);
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

	@Nullable
	private IHiveFrame getHiveFrame() {
		ItemStack stack = this.inventory.getItem(SLOT_FRAME);
		if (!stack.isEmpty() && stack.getItem() instanceof IHiveFrame frame) {
			return frame;
		}
		return null;
	}

	@Nullable
	private IBeeModifier frameModifier() {
		ItemStack stack = this.inventory.getItem(SLOT_FRAME);
		IHiveFrame frame = getHiveFrame();
		return frame == null ? null : frame.getBeeModifier(stack);
	}

	@Override
	public void wearOutEquipment(int amount) {
		IHiveFrame hiveFrame = getHiveFrame();
		if (hiveFrame == null) {
			return;
		}
		IBeeHousing housing = getMultiblockLogic().getController();
		ItemStack queenStack = housing.beeInventory().getQueen();
		IGenome queen = queenStack.get(ApicultureDataComponents.BEE_GENOME.type());
		if (queen == null) {
			return;
		}
		ItemStack frame = this.inventory.getItem(SLOT_FRAME);
		ItemStack frameUsed = hiveFrame.frameUsed(housing, frame, queen, amount * WEAR_MULTIPLIER);
		this.inventory.setItem(SLOT_FRAME, frameUsed);
	}

	@Override
	public Vec3i modifyTerritory(IGenome genome, Vec3i currentModifier) {
		IBeeModifier modifier = frameModifier();
		return modifier == null ? currentModifier : modifier.modifyTerritory(genome, currentModifier);
	}

	@Override
	public float modifyMutationChance(IGenome genome, IGenome mate, IMutation mutation, float currentChance) {
		IBeeModifier modifier = frameModifier();
		return modifier == null ? currentChance : modifier.modifyMutationChance(genome, mate, mutation, currentChance);
	}

	@Override
	public float modifyAging(IGenome genome, @Nullable IGenome mate, float currentAging) {
		IBeeModifier modifier = frameModifier();
		return modifier == null ? currentAging : modifier.modifyAging(genome, mate, currentAging);
	}

	@Override
	public float modifyProductionSpeed(IGenome genome, float currentSpeed) {
		IBeeModifier modifier = frameModifier();
		return modifier == null ? currentSpeed : modifier.modifyProductionSpeed(genome, currentSpeed);
	}

	@Override
	public float modifyPollination(IGenome genome, float currentPollination) {
		IBeeModifier modifier = frameModifier();
		return modifier == null ? currentPollination : modifier.modifyPollination(genome, currentPollination);
	}

	@Override
	public float modifyGeneticDecay(IGenome genome, float currentDecay) {
		IBeeModifier modifier = frameModifier();
		return modifier == null ? currentDecay : modifier.modifyGeneticDecay(genome, currentDecay);
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
		return new ContainerAlvearyFrame(containerId, playerInventory, this);
	}

	@Override
	public BlockPos getScreenOpeningData(ServerPlayer player) {
		return getBlockPos();
	}
}
