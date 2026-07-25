package com.leon1236.reforestry.apiculture.multiblock;

import java.util.ArrayDeque;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.apiculture.blocks.BlockAlveary;
import com.leon1236.reforestry.apiculture.blocks.BlockAlvearyType;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.gui.ContainerAlvearySwarmer;
import com.leon1236.reforestry.apiculture.hives.Hive;
import com.leon1236.reforestry.apiculture.hives.HiveDecorator;
import com.leon1236.reforestry.apiculture.hives.HiveDefinitionSwarmer;
import com.leon1236.reforestry.apiculture.inventory.InventoryAlvearyPart;
import com.leon1236.reforestry.apiculture.items.ItemBeeGE;
import com.leon1236.reforestry.core.tiles.IActivatable;

public class TileAlvearySwarmer extends TileAlveary implements IActivatable,
		IAlvearyComponent.Active<MultiblockLogicAlveary>, IMultiblockComponent.HasInventory {
	public static final int SLOT_COUNT = 4;

	private static final float ROYAL_JELLY_CHANCE = 0.01f;
	private static final int SPAWN_RANGE = 40;

	private final InventoryAlvearyPart inventory = new InventoryAlvearyPart(SLOT_COUNT, this::setChanged,
			(slot, stack) -> isInducer(stack));
	private final ArrayDeque<ItemStack> pendingSpawns = new ArrayDeque<>();

	public TileAlvearySwarmer(BlockPos pos, BlockState state) {
		super(BlockAlvearyType.SWARMER, pos, state);
	}

	@Override
	public Container getInternalInventory() {
		return this.inventory;
	}

	@Override
	public void updateServer(int tickCount) {
		if (!this.pendingSpawns.isEmpty()) {
			setActive(true);
			if (tickCount % 300 == 0) {
				trySpawnSwarm();
			}
		} else {
			setActive(false);
		}

		if (tickCount % 500 != 0) {
			return;
		}

		IGenome queenGenome = getMatedQueenGenome();
		if (queenGenome == null) {
			return;
		}

		float chance = consumeInducerAndGetChance();
		if (chance == 0f || this.level == null || this.level.getRandom().nextFloat() >= chance) {
			return;
		}

		ItemStack princess = new ItemStack(ApicultureItems.BEE_PRINCESS.item());
		princess.set(ApicultureDataComponents.BEE_GENOME.type(), queenGenome);
		this.pendingSpawns.push(princess);
	}

	@Override
	public void updateClient(int tickCount) {
	}

	@Nullable
	private IGenome getMatedQueenGenome() {
		ItemStack queenStack = getMultiblockLogic().getController().beeInventory().getQueen();
		if (!(queenStack.getItem() instanceof ItemBeeGE beeItem) || !"queen".equals(beeItem.lifeStage())) {
			return null;
		}
		if (queenStack.get(ApicultureDataComponents.BEE_MATE_GENOME.type()) == null) {
			return null;
		}
		return queenStack.get(ApicultureDataComponents.BEE_GENOME.type());
	}

	private float consumeInducerAndGetChance() {
		for (int slot = 0; slot < this.inventory.getContainerSize(); slot++) {
			if (isInducer(this.inventory.getItem(slot))) {
				this.inventory.removeItem(slot, 1);
				return ROYAL_JELLY_CHANCE;
			}
		}
		return 0f;
	}

	private static boolean isInducer(ItemStack stack) {
		return stack.is(ApicultureItems.ROYAL_JELLY.item());
	}

	private void trySpawnSwarm() {
		if (!(this.level instanceof ServerLevel serverLevel)) {
			return;
		}

		ItemStack toSpawn = this.pendingSpawns.peek();
		if (toSpawn == null) {
			return;
		}

		HiveDefinitionSwarmer definition = new HiveDefinitionSwarmer(List.of(toSpawn));
		Hive hive = new Hive(definition, definition.getGenChance(), List.of());

		RandomSource random = serverLevel.getRandom();
		int x = getBlockPos().getX() + random.nextInt(SPAWN_RANGE * 2) - SPAWN_RANGE;
		int z = getBlockPos().getZ() + random.nextInt(SPAWN_RANGE * 2) - SPAWN_RANGE;

		if (HiveDecorator.tryGenHive(serverLevel, random, x, z, hive)) {
			this.pendingSpawns.pop();
			setChanged();
		}
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);

		NonNullList<ItemStack> items = NonNullList.withSize(this.inventory.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(input, items);
		for (int slot = 0; slot < items.size(); slot++) {
			this.inventory.setItem(slot, items.get(slot));
		}

		this.pendingSpawns.clear();
		for (ItemStack stack : input.listOrEmpty("PendingSpawns", ItemStack.CODEC)) {
			this.pendingSpawns.add(stack);
		}
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.inventory.getItems());

		ValueOutput.TypedOutputList<ItemStack> spawns = output.list("PendingSpawns", ItemStack.CODEC);
		for (ItemStack stack : this.pendingSpawns) {
			spawns.add(stack);
		}
	}

	@Override
	public boolean isActive() {
		return getBlockState().getValue(BlockAlveary.STATE) == BlockAlveary.State.ON;
	}

	@Override
	public void setActive(boolean active) {
		if (this.level != null && isActive() != active) {
			this.level.setBlockAndUpdate(getBlockPos(),
					getBlockState().setValue(BlockAlveary.STATE, active ? BlockAlveary.State.ON : BlockAlveary.State.OFF));
		}
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerAlvearySwarmer(containerId, playerInventory, this);
	}
}
