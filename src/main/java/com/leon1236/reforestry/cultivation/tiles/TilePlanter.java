package com.leon1236.reforestry.cultivation.tiles;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.entity.FakePlayer;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.agriculture.IFarmListener;
import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.api.agriculture.IFarmable;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.config.ForestryConfig;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.owner.IOwnedTile;
import com.leon1236.reforestry.core.owner.IOwnerHandler;
import com.leon1236.reforestry.core.owner.OwnerHandler;
import com.leon1236.reforestry.core.tiles.TilePowered;
import com.leon1236.reforestry.cultivation.blocks.BlockPlanter;
import com.leon1236.reforestry.cultivation.blocks.BlockTypePlanter;
import com.leon1236.reforestry.cultivation.gui.ContainerPlanter;
import com.leon1236.reforestry.cultivation.inventory.InventoryPlanter;
import com.leon1236.reforestry.farming.farmlogic.FarmHelper;
import com.leon1236.reforestry.farming.farmlogic.FarmManager;
import com.leon1236.reforestry.farming.farmlogic.FarmTarget;
import com.leon1236.reforestry.farming.farmlogic.IFarmHousingInternal;
import com.leon1236.reforestry.farming.gui.IFarmLedgerDelegate;
import com.leon1236.reforestry.farming.multiblock.FarmFertilizerManager;
import com.leon1236.reforestry.farming.multiblock.FarmHydrationManager;
import com.leon1236.reforestry.farming.multiblock.IFarmInventoryInternal;

public abstract class TilePlanter extends TilePowered implements IFarmHousingInternal, IOwnedTile, WorldlyContainer {
	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(10000);
	public static final int ERROR_SLOT_COUNT = 8;
	private static final int[] AUTOMATION_SLOTS = InventoryUtil.contiguousSlots(InventoryPlanter.CONFIG.count);

	private final InventoryPlanter inventory;
	private final OwnerHandler ownerHandler = new OwnerHandler();
	private final FarmHydrationManager hydrationManager;
	private final FarmFertilizerManager fertilizerManager;
	private final FarmManager manager;
	private final MultiFluidTank tanks;
	private final IFarmType properties;

	private boolean manual;
	private IFarmLogic logic;
	@Nullable
	private Vec3i offset;
	@Nullable
	private Vec3i area;

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
			FilteredFluidStorage tank = getWaterTank();
			IFarmLedgerDelegate delegate = getFarmLedgerDelegate();
			return switch (index) {
				case 0 -> getStoredFertilizerScaled(16);
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
			return switch (index) {
				case 0 -> temperature().ordinal();
				case 1 -> humidity().ordinal();
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

	protected TilePlanter(BlockEntityType<?> type, BlockPos pos, BlockState state, Identifier farmTypeId) {
		super(type, pos, state, 150, 1500);
		this.properties = Objects.requireNonNull(
				IForestryApi.get().getFarmingManager().getFarmType(farmTypeId), farmTypeId.toString());
		this.inventory = new InventoryPlanter(this);
		this.hydrationManager = new FarmHydrationManager(this);
		this.fertilizerManager = new FarmFertilizerManager(this.inventory);
		this.manager = new FarmManager(this);
		this.tanks = MultiFluidTank.builder(this::setChanged)
				.tank("Resource", TANK_CAPACITY, FilteredFluidStorage.only(Fluids.WATER, Fluids.FLOWING_WATER))
				.build();
		setEnergyPerWorkCycle(10);
		setTicksPerWorkCycle(2);
		if (state.getBlock() instanceof BlockPlanter planter) {
			setManual(planter.isManual());
		} else {
			setManual(false);
		}
	}

	public void setManual(boolean manual) {
		this.manual = manual;
		this.logic = this.properties.getLogic(manual);
	}

	public boolean isManual() {
		return this.manual;
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, TilePlanter tile) {
		tile.doWork(true);
		tile.hydrationManager.updateServer();
		if (tile.updateOnInterval(20)) {
			tile.inventory.drainCan(tile.getWaterTank());
		}
	}

	@Override
	public Component getDisplayName() {
		String name = getBlockTypeName();
		return Component.translatable("block.reforestry.planter." + (this.manual ? "manual" : "managed"),
				Component.translatable("block.reforestry." + name));
	}

	@Override
	public Component getTitle() {
		return getDisplayName();
	}

	private String getBlockTypeName() {
		if (getBlockState().getBlock() instanceof BlockPlanter planter) {
			return planter.blockType.getSerializedName();
		}
		return BlockTypePlanter.ARBORETUM.getSerializedName();
	}

	@Override
	public boolean hasWork() {
		return true;
	}

	@Override
	protected boolean workCycle() {
		this.manager.doWork();
		return true;
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		this.manager.clearTargets();
	}

	@Override
	public void setUpFarmlandTargets(Map<Direction, List<FarmTarget>> targets) {
		BlockPos targetStart = getCoords();
		BlockPos minPos = this.worldPosition;
		BlockPos maxPos = this.worldPosition;
		int size = 1;
		int extend = ForestryConfig.legacyFarmsPlanterRings();

		if (ForestryConfig.legacyFarmsUseRings()) {
			int ringSize = ForestryConfig.legacyFarmsRingSize();
			minPos = this.worldPosition.offset(-ringSize, 0, -ringSize);
			maxPos = this.worldPosition.offset(ringSize, 0, ringSize);
			size = 1 + ringSize * 2;
			extend--;
		}

		FarmHelper.createTargets(this.level, this, targets, targetStart, extend, size, size, minPos, maxPos);
		FarmHelper.setExtents(this.level, this, targets);
	}

	@Override
	public BlockPos getCoords() {
		return this.worldPosition;
	}

	@Override
	public BlockPos getTopCoord() {
		return this.worldPosition;
	}

	@Override
	@Nullable
	public Level getWorldObj() {
		return this.level;
	}

	@Override
	public Vec3i getArea() {
		if (this.area == null) {
			int basisArea = 5;
			if (ForestryConfig.legacyFarmsUseRings()) {
				basisArea = basisArea + 1 + ForestryConfig.legacyFarmsRingSize() * 2;
			}
			this.area = new Vec3i(
					basisArea + ForestryConfig.legacyFarmsPlanterRings(),
					13,
					basisArea + ForestryConfig.legacyFarmsPlanterRings());
		}
		return this.area;
	}

	@Override
	public Vec3i getOffset() {
		if (this.offset == null) {
			Vec3i farmArea = getArea();
			this.offset = new Vec3i(-farmArea.getX() / 2, -2, -farmArea.getZ() / 2);
		}
		return this.offset;
	}

	@Override
	public boolean doWork() {
		return false;
	}

	@Override
	public boolean hasLiquid(FluidVariant variant, long amount) {
		FilteredFluidStorage tank = getWaterTank();
		if (variant.isBlank() || tank.getAmount() < amount) {
			return false;
		}
		return fluidMatches(tank.getResource(), variant);
	}

	@Override
	public void removeLiquid(FluidVariant variant, long amount) {
		FilteredFluidStorage tank = getWaterTank();
		if (!fluidMatches(tank.getResource(), variant)) {
			return;
		}
		tank.drainInternal(amount);
	}

	private static boolean fluidMatches(FluidVariant stored, FluidVariant requested) {
		if (stored.isBlank() || requested.isBlank()) {
			return false;
		}
		if (stored.equals(requested)) {
			return true;
		}
		return isWater(stored) && isWater(requested);
	}

	private static boolean isWater(FluidVariant variant) {
		return variant.getFluid() == Fluids.WATER || variant.getFluid() == Fluids.FLOWING_WATER;
	}

	@Override
	public boolean plantGermling(IFarmable farmable, Level world, BlockPos pos, Direction direction) {
		if (!(world instanceof ServerLevel serverLevel)) {
			return false;
		}
		GameProfile owner = getOwnerHandler().getOwner();
		Player player = owner != null ? FakePlayer.get(serverLevel, owner) : FakePlayer.get(serverLevel);
		return this.inventory.plantGermling(farmable, player, pos, direction);
	}

	@Override
	public boolean isValidPlatform(Level world, BlockPos pos) {
		return pos.getY() == getBlockPos().getY() - 2;
	}

	@Override
	public boolean isSquare() {
		return true;
	}

	@Override
	public boolean canPlantSoil(boolean manual) {
		return !this.manual;
	}

	@Override
	public IFarmInventoryInternal getFarmInventory() {
		return this.inventory;
	}

	@Override
	public void addPendingProduct(ItemStack stack) {
		this.manager.addPendingProduct(stack);
	}

	@Override
	public void setFarmLogic(Direction direction, IFarmLogic logic) {
	}

	@Override
	public void resetFarmLogic(Direction direction) {
	}

	@Override
	public IFarmLogic getFarmLogic(Direction direction) {
		return getFarmLogic();
	}

	public IFarmLogic getFarmLogic() {
		return this.logic;
	}

	@Override
	public Collection<IFarmLogic> getFarmLogics() {
		return Collections.singleton(this.logic);
	}

	@Override
	public Collection<IFarmListener> getFarmListeners() {
		return List.of();
	}

	@Override
	public int getStoredFertilizerScaled(int scale) {
		return this.fertilizerManager.getStoredFertilizerScaled(scale);
	}

	@Override
	public BlockPos getFarmCorner(Direction direction) {
		return this.worldPosition.below(2);
	}

	@Override
	public int getExtents(Direction direction, BlockPos pos) {
		return this.manager.getExtents(direction, pos);
	}

	@Override
	public void setExtents(Direction direction, BlockPos pos, int extend) {
		this.manager.setExtents(direction, pos, extend);
	}

	@Override
	public void cleanExtents(Direction direction) {
		this.manager.cleanExtents(direction);
	}

	@Override
	public FarmHydrationManager getHydrationManager() {
		return this.hydrationManager;
	}

	@Override
	public FarmFertilizerManager getFertilizerManager() {
		return this.fertilizerManager;
	}

	public IFarmLedgerDelegate getFarmLedgerDelegate() {
		return this.hydrationManager;
	}

	public FilteredFluidStorage getWaterTank() {
		return this.tanks.tank(0);
	}

	@Override
	public TemperatureType temperature() {
		if (this.level == null) {
			return TemperatureType.NORMAL;
		}
		return IForestryApi.get().getClimateManager().getTemperature(this.level.getBiome(this.worldPosition));
	}

	@Override
	public HumidityType humidity() {
		if (this.level == null) {
			return HumidityType.NORMAL;
		}
		return IForestryApi.get().getClimateManager().getHumidity(this.level.getBiome(this.worldPosition));
	}

	@Override
	public IOwnerHandler getOwnerHandler() {
		return this.ownerHandler;
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

	public int getSyncedFertilizerScaled() {
		return this.syncedFertilizer;
	}

	public int getSyncedTankAmountMb() {
		return this.syncedTankAmountMb;
	}

	public int getSyncedTankFluidId() {
		return this.syncedTankFluidId;
	}

	public TemperatureType getSyncedTemperature() {
		TemperatureType[] values = TemperatureType.values();
		int ordinal = this.syncedTemperatureOrdinal;
		return ordinal >= 0 && ordinal < values.length ? values[ordinal] : TemperatureType.NORMAL;
	}

	public HumidityType getSyncedHumidity() {
		HumidityType[] values = HumidityType.values();
		int ordinal = this.syncedHumidityOrdinal;
		return ordinal >= 0 && ordinal < values.length ? values[ordinal] : HumidityType.NORMAL;
	}

	public float getSyncedHydrationTemp() {
		return this.syncedHydrationTemp / 1000.0f;
	}

	public float getSyncedHydrationHumid() {
		return this.syncedHydrationHumid / 1000.0f;
	}

	public float getSyncedHydrationRain() {
		return this.syncedHydrationRain / 1000.0f;
	}

	public double getSyncedDrought() {
		return this.syncedDrought / 10.0;
	}

	public abstract List<ItemStack> createGermlingStacks();

	public abstract List<ItemStack> createResourceStacks();

	public abstract List<ItemStack> createProductionStacks();

	private boolean isClient() {
		return this.level == null || this.level.isClientSide();
	}

	private void refreshErrors() {
		this.syncedErrorCount = 0;
		for (IError error : getErrorLogic().getErrors()) {
			if (this.syncedErrorCount >= ERROR_SLOT_COUNT) {
				break;
			}
			this.syncedErrorIds[this.syncedErrorCount++] = IForestryApi.get().getErrorManager().getNumericId(error);
		}
		for (int i = this.syncedErrorCount; i < ERROR_SLOT_COUNT; i++) {
			this.syncedErrorIds[i] = -1;
		}
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
	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public void clearContent() {
		this.inventory.clearContent();
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return this.inventory.canPlaceItem(slot, stack);
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return WorldlyAccessHelper.getSlotsForFace(this, AUTOMATION_SLOTS, direction);
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return WorldlyAccessHelper.canPlaceItemThroughFace(this, canPlaceItem(slot, stack), direction);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return WorldlyAccessHelper.canTakeItemThroughFace(this,
				slot >= InventoryPlanter.CONFIG.productionStart
						&& slot < InventoryPlanter.CONFIG.productionStart + InventoryPlanter.CONFIG.productionCount,
				direction);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		this.hydrationManager.save(output);
		this.fertilizerManager.save(output);
		this.inventory.save(output);
		this.tanks.writeValue(output.child("Tanks"));
		output.putBoolean("manual", this.manual);
		GameProfile owner = this.ownerHandler.getOwner();
		if (owner != null) {
			if (owner.id() != null) {
				output.store("owner_uuid", UUIDUtil.CODEC, owner.id());
			}
			output.putString("owner_name", owner.name());
		}
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.hydrationManager.load(input);
		this.fertilizerManager.load(input);
		this.inventory.load(input);
		this.tanks.readValue(input.childOrEmpty("Tanks"));
		setManual(input.getBooleanOr("manual", this.manual));
		input.getString("owner_name").ifPresent(name -> {
			UUID id = input.read("owner_uuid", UUIDUtil.CODEC).orElse(null);
			this.ownerHandler.setOwner(new GameProfile(id, name));
		});
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerPlanter(containerId, playerInventory, this);
	}
}
