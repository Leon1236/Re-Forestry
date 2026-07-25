package com.leon1236.reforestry.apiculture.multiblock;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeHousingInventory;
import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.IBeekeepingLogic;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockController;
import com.leon1236.reforestry.apiculture.blocks.BlockAlveary;
import com.leon1236.reforestry.apiculture.blocks.BlockAlvearyType;
import com.leon1236.reforestry.apiculture.features.ApicultureTiles;
import com.leon1236.reforestry.apiculture.gui.ContainerAlveary;
import com.leon1236.reforestry.core.multiblock.MultiblockTileEntityForestry;

public class TileAlveary extends MultiblockTileEntityForestry<MultiblockLogicAlveary>
		implements IBeeHousing, IAlvearyComponent<MultiblockLogicAlveary>, Container, ExtendedMenuProvider<BlockPos> {
	public static final int ERROR_SLOT_COUNT = 8;

	private final BlockAlvearyType type;
	private int syncedProgress;
	private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
	private int syncedErrorCount;

	private final ContainerData progressData = new ContainerData() {
		@Override
		public int get(int index) {
			if (isClient()) {
				return syncedProgress;
			}
			return getBeekeepingLogic().getWorkProgressPercent();
		}

		@Override
		public void set(int index, int value) {
			syncedProgress = value;
		}

		@Override
		public int getCount() {
			return 1;
		}
	};

	private final ContainerData errorData = new ContainerData() {
		@Override
		public int get(int index) {
			if (index == 0) {
				if (!isClient()) {
					refreshErrors();
				}
				return syncedErrorCount;
			}
			int errorIndex = index - 1;
			return errorIndex >= 0 && errorIndex < syncedErrorIds.length ? syncedErrorIds[errorIndex] : -1;
		}

		@Override
		public void set(int index, int value) {
			if (index == 0) {
				syncedErrorCount = value;
				return;
			}
			int errorIndex = index - 1;
			if (errorIndex >= 0 && errorIndex < syncedErrorIds.length) {
				syncedErrorIds[errorIndex] = value;
			}
		}

		@Override
		public int getCount() {
			return ERROR_SLOT_COUNT + 1;
		}
	};

	public TileAlveary(BlockAlvearyType type, BlockPos pos, BlockState state) {
		this(ApicultureTiles.alvearyType(type), type, pos, state);
	}

	public TileAlveary(BlockEntityType<?> blockEntityType, BlockAlvearyType type, BlockPos pos, BlockState state) {
		super(blockEntityType, pos, state, new MultiblockLogicAlveary());
		this.type = type;
	}

	public BlockAlvearyType getAlvearyType() {
		return this.type;
	}

	public ContainerData getProgressData() {
		return this.progressData;
	}

	public ContainerData getErrorData() {
		return this.errorData;
	}

	private boolean isClient() {
		return this.level == null || this.level.isClientSide();
	}

	private void refreshErrors() {
		this.syncedErrorCount = 0;
		for (IError error : getErrorLogic().getErrors()) {
			if (this.syncedErrorCount >= ERROR_SLOT_COUNT) {
				break;
			}
			this.syncedErrorIds[this.syncedErrorCount++] = IForestryApi.INSTANCE.getErrorManager().getNumericId(error);
		}
		for (int i = this.syncedErrorCount; i < ERROR_SLOT_COUNT; i++) {
			this.syncedErrorIds[i] = -1;
		}
	}

	private IAlvearyControllerInternal getController() {
		return getMultiblockLogic().getController();
	}

	@Override
	public void onMachineAssembled(IMultiblockController multiblockController, BlockPos minCoord, BlockPos maxCoord) {
		updateBlockState();
	}

	@Override
	public void onMachineBroken() {
		updateBlockState();
		setChanged();
	}

	private void updateBlockState() {
		if (this.level == null) {
			return;
		}
		Block block = getBlockState().getBlock();
		if (block instanceof BlockAlveary alveary) {
			this.level.setBlockAndUpdate(getBlockPos(), alveary.getNewState(this));
		}
	}

	protected Container getInternalInventory() {
		return getController().getInternalInventory();
	}

	@Override
	public Holder<Biome> getBiome() {
		return getController().getBiome();
	}

	@Override
	public Iterable<IBeeModifier> getBeeModifiers() {
		return getController().getBeeModifiers();
	}

	@Override
	public Iterable<IBeeListener> getBeeListeners() {
		return getController().getBeeListeners();
	}

	@Override
	public IBeeHousingInventory beeInventory() {
		return getController().beeInventory();
	}

	@Override
	public IBeekeepingLogic getBeekeepingLogic() {
		return getController().getBeekeepingLogic();
	}

	@Override
	public Vec3 getBeeFXCoordinates() {
		return getController().getBeeFXCoordinates();
	}

	@Override
	public Level level() {
		return this.level;
	}

	@Override
	public BlockPos position() {
		return getBlockPos();
	}

	@Override
	public TemperatureType temperature() {
		return getController().temperature();
	}

	@Override
	public HumidityType humidity() {
		return getController().humidity();
	}

	@Override
	public int getBlockLightValue() {
		return getController().getBlockLightValue();
	}

	@Override
	public boolean canBlockSeeTheSky() {
		return getController().canBlockSeeTheSky();
	}

	@Override
	public boolean isRaining() {
		return getController().isRaining();
	}

	@Override
	public IErrorLogic getErrorLogic() {
		return getController().getErrorLogic();
	}

	@Override
	public int getContainerSize() {
		return getInternalInventory().getContainerSize();
	}

	@Override
	public boolean isEmpty() {
		return getInternalInventory().isEmpty();
	}

	@Override
	public ItemStack getItem(int slot) {
		return getInternalInventory().getItem(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		return getInternalInventory().removeItem(slot, amount);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return getInternalInventory().removeItemNoUpdate(slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		getInternalInventory().setItem(slot, stack);
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return getInternalInventory().canPlaceItem(slot, stack);
	}

	@Override
	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public void clearContent() {
		getInternalInventory().clearContent();
	}

	@Override
	public Component getDisplayName() {
		return getBlockState().getBlock().getName();
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerAlveary(containerId, playerInventory, this);
	}

	@Override
	public BlockPos getScreenOpeningData(ServerPlayer player) {
		return getBlockPos();
	}
}
