package com.leon1236.reforestry.farming.multiblock;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.core.IErrorLogicSource;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.multiblock.IFarmComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockController;
import com.leon1236.reforestry.core.circuits.ISocketable;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.multiblock.MultiblockTileEntityForestry;
import com.leon1236.reforestry.core.tiles.TileUtil;
import com.leon1236.reforestry.farming.blocks.EnumFarmBlockType;
import com.leon1236.reforestry.farming.blocks.FarmBlock;
import com.leon1236.reforestry.farming.gui.ContainerFarm;

public class TileFarm extends MultiblockTileEntityForestry<MultiblockLogicFarm>
		implements IFarmComponent<MultiblockLogicFarm>, IErrorLogicSource, WorldlyContainer, ISocketable,
		ExtendedMenuProvider<BlockPos> {
	public static final int ERROR_SLOT_COUNT = 8;
	private static final int[] AUTOMATION_SLOTS = InventoryUtil.contiguousSlots(InventoryFarm.CONFIG.count);

	private int syncedFertilizer;
	private int syncedTankAmountMb;
	private int syncedTankFluidId;
	private int syncedHydrationTemp;
	private int syncedHydrationHumid;
	private int syncedHydrationRain;
	private int syncedDrought;
	private int syncedTemperatureOrdinal;
	private int syncedHumidityOrdinal;
	private int syncedErrorCount;
	private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];

	private final ContainerData farmData = new ContainerData() {
		@Override
		public int get(int index) {
			if (isClient()) {
				return switch (index) {
					case 0 -> syncedFertilizer;
					case 1 -> syncedTankAmountMb;
					case 2 -> syncedTankFluidId;
					case 3 -> syncedHydrationTemp;
					case 4 -> syncedHydrationHumid;
					case 5 -> syncedHydrationRain;
					case 6 -> syncedDrought;
					default -> 0;
				};
			}
			IFarmControllerInternal controller = getController();
			FilteredFluidStorage tank = controller.getWaterTank();
			var delegate = controller.getFarmLedgerDelegate();
			return switch (index) {
				case 0 -> controller.getStoredFertilizerScaled(16);
				case 1 -> (int) FluidUnits.dropletsToMb(tank.getAmount());
				case 2 -> BuiltInRegistries.FLUID.getId(tank.getResource().getFluid());
				case 3 -> Math.round(delegate.getHydrationTempModifier() * 1000);
				case 4 -> Math.round(delegate.getHydrationHumidModifier() * 1000);
				case 5 -> Math.round(delegate.getHydrationRainfallModifier() * 1000);
				case 6 -> (int) Math.round(delegate.getDrought() * 10);
				default -> 0;
			};
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
				case 0 -> syncedFertilizer = value;
				case 1 -> syncedTankAmountMb = value;
				case 2 -> syncedTankFluidId = value;
				case 3 -> syncedHydrationTemp = value;
				case 4 -> syncedHydrationHumid = value;
				case 5 -> syncedHydrationRain = value;
				case 6 -> syncedDrought = value;
				default -> {
				}
			}
		}

		@Override
		public int getCount() {
			return 7;
		}
	};

	private final ContainerData climateData = new ContainerData() {
		@Override
		public int get(int index) {
			if (isClient()) {
				return switch (index) {
					case 0 -> syncedTemperatureOrdinal;
					case 1 -> syncedHumidityOrdinal;
					default -> 0;
				};
			}
			IFarmControllerInternal controller = getController();
			return switch (index) {
				case 0 -> controller.temperature().ordinal();
				case 1 -> controller.humidity().ordinal();
				default -> 0;
			};
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
				case 0 -> syncedTemperatureOrdinal = value;
				case 1 -> syncedHumidityOrdinal = value;
				default -> {
				}
			}
		}

		@Override
		public int getCount() {
			return 2;
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

	public TileFarm(BlockEntityType<?> tileEntityType, BlockPos pos, BlockState state) {
		super(tileEntityType, pos, state, new MultiblockLogicFarm());
	}

	public EnumFarmBlockType getFarmType() {
		if (getBlockState().getBlock() instanceof FarmBlock farmBlock) {
			return farmBlock.getType();
		}
		return EnumFarmBlockType.PLAIN;
	}

	public ContainerData getFarmData() {
		return this.farmData;
	}

	public ContainerData getClimateData() {
		return this.climateData;
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

	private IFarmControllerInternal getController() {
		return getMultiblockLogic().getController();
	}

	private Container getInternalInventory() {
		return getController().getInternalInventory();
	}

	@Override
	public void onMachineAssembled(IMultiblockController multiblockController, BlockPos minCoord, BlockPos maxCoord) {
		if (this.level != null) {
			this.level.updateNeighborsAt(getBlockPos(), getBlockState().getBlock());
		}
		setChanged();
	}

	@Override
	public void onMachineBroken() {
		if (this.level != null) {
			this.level.updateNeighborsAt(getBlockPos(), getBlockState().getBlock());
		}
		setChanged();
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

	public boolean allowsAutomation() {
		return false;
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return allowsAutomation() ? AUTOMATION_SLOTS : InventoryUtil.NO_SLOTS;
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return allowsAutomation() && canPlaceItem(slot, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return allowsAutomation() && InventoryFarm.isProductSlot(slot);
	}

	@Override
	public boolean stillValid(Player player) {
		return TileUtil.isUsableByPlayer(player, this);
	}

	@Override
	public void clearContent() {
		getInternalInventory().clearContent();
	}

	@Override
	public int getSocketCount() {
		return getController().getSocketCount();
	}

	@Override
	public ItemStack getSocket(int slot) {
		return getController().getSocket(slot);
	}

	@Override
	public void setSocket(int slot, ItemStack stack) {
		getController().setSocket(slot, stack);
	}

	@Override
	public Identifier getSocketType() {
		return getController().getSocketType();
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("for.gui.farm.title");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerFarm(containerId, playerInventory, this);
	}

	@Override
	public BlockPos getScreenOpeningData(ServerPlayer player) {
		return getBlockPos();
	}

	public int getSyncedFertilizerScaled() {
		return this.farmData.get(0);
	}

	public int getSyncedTankAmountMb() {
		return this.farmData.get(1);
	}

	public int getSyncedTankFluidId() {
		return this.farmData.get(2);
	}

	public float getSyncedHydrationTemp() {
		return this.farmData.get(3) / 1000.0f;
	}

	public float getSyncedHydrationHumid() {
		return this.farmData.get(4) / 1000.0f;
	}

	public float getSyncedHydrationRain() {
		return this.farmData.get(5) / 1000.0f;
	}

	public double getSyncedDrought() {
		return this.farmData.get(6) / 10.0;
	}

	public TemperatureType getSyncedTemperature() {
		int ordinal = this.climateData.get(0);
		if (ordinal < 0 || ordinal >= TemperatureType.VALUES.size()) {
			return TemperatureType.NORMAL;
		}
		return TemperatureType.VALUES.get(ordinal);
	}

	public HumidityType getSyncedHumidity() {
		int ordinal = this.climateData.get(1);
		if (ordinal < 0 || ordinal >= HumidityType.VALUES.size()) {
			return HumidityType.NORMAL;
		}
		return HumidityType.VALUES.get(ordinal);
	}
}
