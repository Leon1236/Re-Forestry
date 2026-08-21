package com.leon1236.reforestry.farming.multiblock;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.entity.FakePlayer;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;

import com.leon1236.reforestry.api.ForestryTags;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.agriculture.ForestryFarmTypes;
import com.leon1236.reforestry.api.agriculture.HorizontalDirection;
import com.leon1236.reforestry.api.agriculture.IFarmListener;
import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.api.agriculture.IFarmable;
import com.leon1236.reforestry.api.circuits.ForestryCircuitSocketTypes;
import com.leon1236.reforestry.api.circuits.ICircuitBoard;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.multiblock.IFarmComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.core.config.ForestryConfig;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.multiblock.IMultiblockControllerInternal;
import com.leon1236.reforestry.core.multiblock.MultiblockValidationException;
import com.leon1236.reforestry.core.multiblock.RectangularMultiblockControllerBase;
import com.leon1236.reforestry.farming.blocks.EnumFarmBlockType;
import com.leon1236.reforestry.farming.farmlogic.FarmHelper;
import com.leon1236.reforestry.farming.farmlogic.FarmManager;
import com.leon1236.reforestry.farming.farmlogic.FarmTarget;
import com.leon1236.reforestry.farming.farmlogic.IFarmHousingInternal;
import com.leon1236.reforestry.farming.gui.IFarmLedgerDelegate;

public class FarmController extends RectangularMultiblockControllerBase implements IFarmControllerInternal, IFarmHousingInternal {
	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(10000);

	private int allowedExtent;
	private final Map<Direction, IFarmLogic> farmLogics = new EnumMap<>(Direction.class);
	@Nullable
	private Vec3i offset;
	@Nullable
	private Vec3i area;

	private final InventoryFarm inventory;
	private final FarmHydrationManager hydrationManager;
	private final FarmFertilizerManager fertilizerManager;
	private final FarmManager manager;
	private final MultiFluidTank tanks;
	private final NonNullList<ItemStack> sockets = NonNullList.withSize(1, ItemStack.EMPTY);
	private final Set<IFarmComponent.Active<?>> farmActiveComponents = new HashSet<>();
	private final Set<IFarmListener> farmListeners = new HashSet<>();
	private int noPowerTime;

	public FarmController(Level world) {
		super(world, FarmMultiblockSizeLimits.INSTANCE);
		this.inventory = new InventoryFarm(this, this::markChunkDirty);
		this.hydrationManager = new FarmHydrationManager(this);
		this.fertilizerManager = new FarmFertilizerManager(this.inventory);
		this.manager = new FarmManager(this);
		this.tanks = MultiFluidTank.builder(this::markChunkDirty)
				.tank("Resource", TANK_CAPACITY, FilteredFluidStorage.only(Fluids.WATER, Fluids.FLOWING_WATER))
				.build();
		for (Direction direction : HorizontalDirection.VALUES) {
			resetFarmLogic(direction);
		}
	}

	private void markChunkDirty() {
		BlockPos reference = getReferenceCoord();
		if (reference != null && this.level != null && !this.level.isClientSide()) {
			this.level.blockEntityChanged(reference);
		}
	}

	public BlockPos getTopCoord() {
		return getTopCenterCoord();
	}

	public FarmHydrationManager getHydrationManager() {
		return this.hydrationManager;
	}

	public FarmFertilizerManager getFertilizerManager() {
		return this.fertilizerManager;
	}

	public Collection<IFarmListener> getFarmListeners() {
		return this.farmListeners;
	}

	@Override
	public IFarmLedgerDelegate getFarmLedgerDelegate() {
		return this.hydrationManager;
	}

	@Override
	public InventoryFarm getFarmInventory() {
		return this.inventory;
	}

	@Override
	public Container getInternalInventory() {
		return isAssembled() ? this.inventory : FakeFarmController.INSTANCE.getInternalInventory();
	}

	@Override
	public FilteredFluidStorage getWaterTank() {
		return this.tanks.tank(0);
	}

	@Override
	protected void onAttachedPartWithMultiblockData(IMultiblockComponent part, CompoundTag data) {
		read(data, this.level.registryAccess());
	}

	@Override
	protected void onBlockAdded(IMultiblockComponent newPart) {
		if (newPart instanceof IFarmComponent.Listener<?> listenerPart) {
			this.farmListeners.add(listenerPart.getFarmListener());
		}
		if (newPart instanceof IFarmComponent.Active<?> active) {
			this.farmActiveComponents.add(active);
		}
	}

	@Override
	protected void onBlockRemoved(IMultiblockComponent oldPart) {
		if (oldPart instanceof IFarmComponent.Listener<?> listenerPart) {
			this.farmListeners.remove(listenerPart.getFarmListener());
		}
		if (oldPart instanceof IFarmComponent.Active<?> active) {
			this.farmActiveComponents.remove(active);
		}
	}

	@Override
	protected void onAssimilate(IMultiblockControllerInternal assimilated) {
	}

	@Override
	public void onAssimilated(IMultiblockControllerInternal assimilator) {
	}

	@Override
	public void onDestroyed(BlockPos lastPos) {
		if (!this.level.isClientSide()) {
			Containers.dropContents(this.level, lastPos, this.inventory);
			Containers.dropContents(this.level, lastPos, new SimpleContainer(this.sockets.get(0)));
		}
	}

	@Override
	protected boolean serverTick(int tickCount) {
		boolean hasPower = false;
		for (IFarmComponent.Active<?> activeComponent : this.farmActiveComponents) {
			if (activeComponent instanceof TileFarmGearbox gearbox) {
				hasPower |= gearbox.getEnergyStorage().amount > 0;
			}
			activeComponent.updateServer(tickCount);
		}
		this.hydrationManager.updateServer();
		if (updateOnInterval(20)) {
			this.inventory.drainCan(getWaterTank());
		}
		if (hasPower) {
			this.noPowerTime = 0;
			getErrorLogic().setCondition(false, ForestryError.NO_POWER);
		} else {
			if (this.noPowerTime <= 4) {
				this.noPowerTime++;
			} else {
				getErrorLogic().setCondition(true, ForestryError.NO_POWER);
			}
		}
		return true;
	}

	@Override
	protected void clientTick(int tickCount) {
		for (IFarmComponent.Active<?> activeComponent : this.farmActiveComponents) {
			activeComponent.updateClient(tickCount);
		}
	}

	@Override
	protected void onMachineAssembled() {
		super.onMachineAssembled();
		BlockPos max = getMaximumCoord();
		BlockPos min = getMinimumCoord();
		int sizeNorthSouth = Math.abs(max.getZ() - min.getZ()) + 1;
		int sizeEastWest = Math.abs(max.getX() - min.getX()) + 1;
		this.allowedExtent = Math.max(sizeNorthSouth, sizeEastWest) * ForestryConfig.multiblockFarmSize() + 1;
		this.area = null;
		this.offset = null;
	}

	@Override
	protected void onMachineDisassembled() {
		super.onMachineDisassembled();
		this.manager.clearTargets();
	}

	@Override
	protected void isMachineWhole() throws MultiblockValidationException {
		super.isMachineWhole();
		boolean hasGearbox = false;
		for (IMultiblockComponent part : this.connectedParts) {
			if (part instanceof TileFarmGearbox
					|| part instanceof TileFarm tile && tile.getFarmType() == EnumFarmBlockType.GEARBOX) {
				hasGearbox = true;
				break;
			}
		}
		if (!hasGearbox) {
			throw new MultiblockValidationException(
					Component.translatable("for.multiblock.farm.error.needGearbox").getString());
		}
	}

	@Override
	protected void isGoodForExteriorLevel(IMultiblockComponent part, int level) throws MultiblockValidationException {
		if (level == 2 && !(part instanceof TileFarmPlain)) {
			throw new MultiblockValidationException(
					Component.translatable("for.multiblock.farm.error.needPlainBand").getString());
		}
	}

	@Override
	protected void isGoodForInterior(IMultiblockComponent part) throws MultiblockValidationException {
		if (!(part instanceof TileFarmPlain)) {
			throw new MultiblockValidationException(
					Component.translatable("for.multiblock.farm.error.needPlainInterior").getString());
		}
	}

	@Override
	public String getUnlocalizedType() {
		return "for.multiblock.farm.type";
	}

	@Override
	public BlockPos getCoords() {
		BlockPos reference = getReferenceCoord();
		return reference != null ? getCenterCoord() : BlockPos.ZERO;
	}

	@Override
	public Vec3i getArea() {
		if (this.area == null) {
			this.area = new Vec3i(7 + this.allowedExtent * 2, 13, 7 + this.allowedExtent * 2);
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
		return this.manager.doWork();
	}

	public void setUpFarmlandTargets(Map<Direction, List<FarmTarget>> targets) {
		BlockPos targetStart = getCoords();
		BlockPos max = getMaximumCoord();
		BlockPos min = getMinimumCoord();
		int sizeNorthSouth = Math.abs(max.getZ() - min.getZ()) + 1;
		int sizeEastWest = Math.abs(max.getX() - min.getX()) + 1;
		this.allowedExtent = Math.max(sizeNorthSouth, sizeEastWest) * ForestryConfig.multiblockFarmSize() + 1;
		FarmHelper.createTargets(this.level, this, targets, targetStart, this.allowedExtent, sizeNorthSouth, sizeEastWest, min, max);
		FarmHelper.setExtents(this.level, this, targets);
	}

	@Override
	public boolean hasLiquid(FluidVariant variant, long amount) {
		FilteredFluidStorage tank = getWaterTank();
		return !variant.isBlank() && tank.getResource().equals(variant) && tank.getAmount() >= amount;
	}

	@Override
	public void removeLiquid(FluidVariant variant, long amount) {
		getWaterTank().drainInternal(amount);
	}

	@Override
	public boolean plantGermling(IFarmable farmable, Level world, BlockPos pos, Direction direction) {
		if (!(world instanceof ServerLevel serverLevel)) {
			return false;
		}
		GameProfile owner = getOwnerHandler().getOwner();
		Player player = owner != null ? FakePlayer.get(serverLevel, owner) : FakePlayer.get(serverLevel);
		return this.inventory.plantGermling(farmable, player, pos);
	}

	@Override
	public boolean isValidPlatform(Level world, BlockPos pos) {
		return world.getBlockState(pos).is(ForestryTags.Blocks.VALID_FARM_BASE);
	}

	@Override
	public boolean isSquare() {
		return ForestryConfig.squareMultiblockFarms();
	}

	@Override
	public boolean canPlantSoil(boolean manual) {
		return true;
	}

	@Override
	public void addPendingProduct(ItemStack stack) {
		this.manager.addPendingProduct(stack);
	}

	@Override
	public void setFarmLogic(Direction direction, IFarmLogic logic) {
		this.farmLogics.put(direction, logic != null ? logic : FakeFarmLogic.INSTANCE);
		cleanExtents(direction);
	}

	@Override
	public void resetFarmLogic(Direction direction) {
		IFarmType type = IForestryApi.INSTANCE.getFarmingManager().getFarmType(ForestryFarmTypes.ARBOREAL);
		IFarmLogic logic = type != null ? type.getLogic(false) : FakeFarmLogic.INSTANCE;
		if (logic == null) {
			logic = FakeFarmLogic.INSTANCE;
		}
		setFarmLogic(direction, logic);
	}

	@Override
	public IFarmLogic getFarmLogic(Direction direction) {
		IFarmLogic logic = this.farmLogics.get(direction);
		return logic != null ? logic : FakeFarmLogic.INSTANCE;
	}

	@Override
	public Collection<IFarmLogic> getFarmLogics() {
		return this.farmLogics.values();
	}

	@Override
	public int getStoredFertilizerScaled(int scale) {
		return this.fertilizerManager.getStoredFertilizerScaled(scale);
	}

	@Override
	public BlockPos getFarmCorner(Direction direction) {
		return this.manager.getFarmCorner(direction);
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
	public TemperatureType temperature() {
		BlockPos coords = getReferenceCoord();
		if (coords == null) {
			return TemperatureType.NORMAL;
		}
		return IForestryApi.INSTANCE.getClimateManager().getTemperature(this.level.getBiome(coords));
	}

	@Override
	public HumidityType humidity() {
		BlockPos coords = getReferenceCoord();
		if (coords == null) {
			return HumidityType.NORMAL;
		}
		return IForestryApi.INSTANCE.getClimateManager().getHumidity(this.level.getBiome(coords));
	}

	@Override
	public int getSocketCount() {
		return this.sockets.size();
	}

	@Override
	public ItemStack getSocket(int slot) {
		return slot == 0 ? this.sockets.get(0) : ItemStack.EMPTY;
	}

	@Override
	public void setSocket(int slot, ItemStack stack) {
		if (slot != 0) {
			return;
		}
		if (!stack.isEmpty() && !IForestryApi.INSTANCE.getCircuitManager().isCircuitBoard(stack)) {
			return;
		}
		ItemStack existing = this.sockets.get(0);
		if (!existing.isEmpty()) {
			ICircuitBoard oldBoard = IForestryApi.INSTANCE.getCircuitManager().getCircuitBoard(existing);
			if (oldBoard != null) {
				oldBoard.onRemoval(this);
			}
		}
		ItemStack placed = stack.isEmpty() ? ItemStack.EMPTY : stack.copyWithCount(1);
		this.sockets.set(0, placed);
		refreshFarmLogics();
		if (!placed.isEmpty()) {
			ICircuitBoard newBoard = IForestryApi.INSTANCE.getCircuitManager().getCircuitBoard(placed);
			if (newBoard != null) {
				newBoard.onInsertion(this);
			}
		}
		markChunkDirty();
	}

	@Override
	public Identifier getSocketType() {
		return ForestryCircuitSocketTypes.FARM;
	}

	private void refreshFarmLogics() {
		for (Direction direction : HorizontalDirection.VALUES) {
			resetFarmLogic(direction);
		}
		ItemStack chip = this.sockets.get(0);
		if (chip.isEmpty()) {
			return;
		}
		ICircuitBoard chipset = IForestryApi.INSTANCE.getCircuitManager().getCircuitBoard(chip);
		if (chipset != null) {
			chipset.onLoad(this);
		}
	}

	@Override
	public CompoundTag write(CompoundTag data, HolderLookup.Provider registries) {
		super.write(data, registries);
		this.hydrationManager.write(data);
		this.fertilizerManager.write(data);
		TagValueOutput output = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, registries);
		this.inventory.save(output);
		data.put("Items", output.buildResult());
		TagValueOutput tankOutput = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, registries);
		this.tanks.writeValue(tankOutput);
		data.put("Tanks", tankOutput.buildResult());
		if (!this.sockets.get(0).isEmpty()) {
			TagValueOutput socketOutput = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, registries);
			socketOutput.store("Socket", ItemStack.CODEC, this.sockets.get(0));
			data.put("Sockets", socketOutput.buildResult());
		}
		return data;
	}

	@Override
	public void read(CompoundTag data, HolderLookup.Provider registries) {
		super.read(data, registries);
		this.hydrationManager.read(data);
		this.fertilizerManager.read(data);
		CompoundTag items = data.getCompoundOrEmpty("Items");
		ValueInput input = TagValueInput.create(ProblemReporter.DISCARDING, registries, items);
		this.inventory.load(input);
		ValueInput tankInput = TagValueInput.create(ProblemReporter.DISCARDING, registries, data.getCompoundOrEmpty("Tanks"));
		this.tanks.readValue(tankInput);
		CompoundTag socketsTag = data.getCompoundOrEmpty("Sockets");
		ValueInput socketInput = TagValueInput.create(ProblemReporter.DISCARDING, registries, socketsTag);
		this.sockets.set(0, socketInput.read("Socket", ItemStack.CODEC).orElse(ItemStack.EMPTY));
		refreshFarmLogics();
	}
}
