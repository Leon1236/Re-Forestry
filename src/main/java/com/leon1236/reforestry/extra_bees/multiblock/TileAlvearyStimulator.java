package com.leon1236.reforestry.extra_bees.multiblock;

import java.util.ArrayList;
import java.util.List;

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
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import team.reborn.energy.api.base.SimpleEnergyStorage;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.circuits.ICircuit;
import com.leon1236.reforestry.api.circuits.ICircuitBoard;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.apiculture.inventory.InventoryAlvearyPart;
import com.leon1236.reforestry.apiculture.multiblock.MultiblockLogicAlveary;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.extra_bees.blocks.BlockExtraBeeAlvearyType;
import com.leon1236.reforestry.extra_bees.circuit.StimulatorCircuit;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesTiles;
import com.leon1236.reforestry.extra_bees.gui.ContainerAlvearyStimulator;

public class TileAlvearyStimulator extends TileExtraBeeAlveary
		implements IAlvearyComponent.BeeModifier<MultiblockLogicAlveary>,
		IAlvearyComponent.BeeListener<MultiblockLogicAlveary>,
		IAlvearyComponent.Active<MultiblockLogicAlveary>,
		IMultiblockComponent.HasInventory,
		ExtendedMenuProvider<BlockPos>,
		IBeeModifier,
		IBeeListener,
		WorldlyContainer {
	public static final int SLOT_CIRCUIT = 0;
	public static final long CAPACITY = 1000;
	public static final long MAX_INSERT = 2000;

	private final InventoryAlvearyPart inventory = new InventoryAlvearyPart(1, this::setChanged,
			(slot, stack) -> IForestryApi.get().getCircuitManager().isCircuitBoard(stack));
	private final SimpleEnergyStorage energyStorage;
	private final List<StimulatorCircuit> activeCircuits = new ArrayList<>();
	private int powerUsage;
	private boolean powered;
	private int syncedEnergyStored;
	private int syncedEnergyCapacity;
	private int syncedPowerUsage;

	private final ContainerData energyData = new ContainerData() {
		@Override
		public int get(int index) {
			if (level != null && level.isClientSide()) {
				return switch (index) {
					case 0 -> syncedEnergyStored;
					case 1 -> syncedEnergyCapacity;
					case 2 -> syncedPowerUsage;
					default -> 0;
				};
			}
			return switch (index) {
				case 0 -> (int) energyStorage.amount;
				case 1 -> (int) CAPACITY;
				case 2 -> powerUsage;
				default -> 0;
			};
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
				case 0 -> syncedEnergyStored = value;
				case 1 -> syncedEnergyCapacity = value;
				case 2 -> syncedPowerUsage = value;
				default -> {
				}
			}
		}

		@Override
		public int getCount() {
			return 3;
		}
	};

	public TileAlvearyStimulator(BlockPos pos, BlockState state) {
		super(ExtraBeesTiles.ALVEARY_STIMULATOR.type(), BlockExtraBeeAlvearyType.STIMULATOR, pos, state);
		this.energyStorage = new SimpleEnergyStorage(CAPACITY, MAX_INSERT, 0) {
			@Override
			protected void onFinalCommit() {
				setChanged();
			}
		};
	}

	public SimpleEnergyStorage getEnergyStorage() {
		return this.energyStorage;
	}

	public ContainerData getEnergyData() {
		return this.energyData;
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
	public void updateServer(int tickCount) {
		refreshCircuits();
	}

	@Override
	public void updateClient(int tickCount) {
	}

	private void refreshCircuits() {
		this.activeCircuits.clear();
		this.powerUsage = 0;
		ICircuitBoard board = IForestryApi.get().getCircuitManager().getCircuitBoard(this.inventory.getItem(SLOT_CIRCUIT));
		if (board != null) {
			for (ICircuit circuit : board.getCircuits()) {
				if (circuit instanceof StimulatorCircuit stimulatorCircuit) {
					this.activeCircuits.add(stimulatorCircuit);
					this.powerUsage += stimulatorCircuit.getPowerUsage();
				}
			}
		}
		this.powered = this.powerUsage > 0 && this.energyStorage.amount >= this.powerUsage;
	}

	@Override
	public void wearOutEquipment(int amount) {
		if (this.powerUsage <= 0 || this.energyStorage.amount < this.powerUsage) {
			return;
		}
		this.energyStorage.amount -= this.powerUsage;
		setChanged();
		refreshCircuits();
	}

	private boolean isPowered() {
		return this.powered;
	}

	@Override
	public Vec3i modifyTerritory(IGenome genome, Vec3i currentModifier) {
		if (!isPowered()) {
			return currentModifier;
		}
		Vec3i result = currentModifier;
		for (StimulatorCircuit circuit : this.activeCircuits) {
			result = circuit.getType().modifyTerritory(genome, result);
		}
		return result;
	}

	@Override
	public float modifyMutationChance(IGenome genome, IGenome mate, IMutation mutation, float currentChance) {
		if (!isPowered()) {
			return currentChance;
		}
		float result = currentChance;
		for (StimulatorCircuit circuit : this.activeCircuits) {
			result = circuit.getType().modifyMutationChance(genome, mate, mutation, result);
		}
		return result;
	}

	@Override
	public float modifyAging(IGenome genome, @Nullable IGenome mate, float currentAging) {
		if (!isPowered()) {
			return currentAging;
		}
		float result = currentAging;
		for (StimulatorCircuit circuit : this.activeCircuits) {
			result = circuit.getType().modifyAging(genome, mate, result);
		}
		return result;
	}

	@Override
	public float modifyProductionSpeed(IGenome genome, float currentSpeed) {
		if (!isPowered()) {
			return currentSpeed;
		}
		float result = currentSpeed;
		for (StimulatorCircuit circuit : this.activeCircuits) {
			result = circuit.getType().modifyProductionSpeed(genome, result);
		}
		return result;
	}

	@Override
	public float modifyPollination(IGenome genome, float currentPollination) {
		if (!isPowered()) {
			return currentPollination;
		}
		float result = currentPollination;
		for (StimulatorCircuit circuit : this.activeCircuits) {
			result = circuit.getType().modifyPollination(genome, result);
		}
		return result;
	}

	@Override
	public float modifyGeneticDecay(IGenome genome, float currentDecay) {
		if (!isPowered()) {
			return currentDecay;
		}
		float result = currentDecay;
		for (StimulatorCircuit circuit : this.activeCircuits) {
			result = circuit.getType().modifyGeneticDecay(genome, result);
		}
		return result;
	}

	@Override
	public boolean isSealed() {
		if (!isPowered()) {
			return false;
		}
		for (StimulatorCircuit circuit : this.activeCircuits) {
			if (circuit.getType().isSealed()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isAlwaysActive(IGenome genome) {
		if (!isPowered()) {
			return false;
		}
		for (StimulatorCircuit circuit : this.activeCircuits) {
			if (circuit.getType().isAlwaysActive(genome)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isSunlightSimulated() {
		if (!isPowered()) {
			return false;
		}
		for (StimulatorCircuit circuit : this.activeCircuits) {
			if (circuit.getType().isSunlightSimulated()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isHellish() {
		if (!isPowered()) {
			return false;
		}
		for (StimulatorCircuit circuit : this.activeCircuits) {
			if (circuit.getType().isHellish()) {
				return true;
			}
		}
		return false;
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
		this.energyStorage.amount = Math.min(CAPACITY, Math.max(0L, input.getLongOr("Energy", 0L)));
		refreshCircuits();
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.inventory.getItems());
		output.putLong("Energy", this.energyStorage.amount);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerAlvearyStimulator(containerId, playerInventory, this);
	}

	@Override
	public BlockPos getScreenOpeningData(ServerPlayer player) {
		return getBlockPos();
	}
}
