package com.leon1236.reforestry.gendustry.blockentity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.UUIDUtil;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeHousingInventory;
import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.IBeekeepingLogic;
import com.leon1236.reforestry.api.climate.IClimateProvider;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.apiculture.InventoryBeeHousing;
import com.leon1236.reforestry.apiculture.genetics.BeekeepingLogic;
import com.leon1236.reforestry.apiculture.items.ItemBeeGE;
import com.leon1236.reforestry.core.access.WorldlyAccessHelper;
import com.leon1236.reforestry.core.energy.EnergyHelper;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.tiles.TilePowered;
import com.leon1236.reforestry.gendustry.api.GendustryTags;
import com.leon1236.reforestry.gendustry.features.GBlockEntities;
import com.leon1236.reforestry.gendustry.menu.IndustrialApiaryMenu;

public class IndustrialApiaryBlockEntity extends TilePowered
		implements WorldlyContainer, IBeeHousing, IBeeHousingInventory, IGendustryHintTile {
	public static final String HINTS_KEY = "gendustry.industrial_apiary";
	public static final int BASE_ENERGY = 200;
	public static final long ENERGY_CAPACITY = 1000000L;
	public static final long ENERGY_MAX_RECEIVE = 100000L;
	public static final int ERROR_SLOT_COUNT = 8;

	public static final int SLOT_QUEEN = 0;
	public static final int SLOT_DRONE = 1;
	public static final int UPGRADE_SLOT_START = 2;
	public static final int UPGRADE_SLOT_COUNT = 4;
	public static final int OUTPUT_SLOT_START = 6;
	public static final int OUTPUT_SLOT_COUNT = 9;
	public static final int SLOT_COUNT = OUTPUT_SLOT_START + OUTPUT_SLOT_COUNT;

	private static final IBeeModifier IDENTITY_MODIFIER = new IBeeModifier() {
	};

	private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
	private final IBeekeepingLogic beeLogic = new BeekeepingLogic(this);
	private IClimateProvider climate = IForestryApi.get().getClimateManager().createDummyClimateProvider();
	@Nullable
	private GameProfile owner;
	private int energyConsumption = BASE_ENERGY;
	private int workProgressPercent;
	private int syncedEnergyUsage;
	private int clientTicks;
	private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
	private int syncedErrorCount;
	private int syncedTemperatureOrdinal;
	private int syncedHumidityOrdinal;

	private final ContainerData progressData = new ContainerData() {
		@Override
		public int get(int index) {
			return workProgressPercent;
		}

		@Override
		public void set(int index, int value) {
			workProgressPercent = value;
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

	private final ContainerData climateData = new ContainerData() {
		@Override
		public int get(int index) {
			Level level = getLevel();
			if (level != null && level.isClientSide()) {
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

	public IndustrialApiaryBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.INDUSTRIAL_APIARY.type(), pos, state, ENERGY_CAPACITY, ENERGY_MAX_RECEIVE);
	}

	@Override
	public void setLevel(Level level) {
		super.setLevel(level);
		this.climate = IForestryApi.get().getClimateManager().createClimateProvider(level, getBlockPos());
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, IndustrialApiaryBlockEntity tile) {
		tile.advanceTicks();
		IErrorLogic errors = tile.getErrorLogic();
		boolean disabled = tile.isRedstoneActivated();

		if (disabled) {
			errors.clearErrors();
			errors.setCondition(true, ForestryError.DISABLED_BY_REDSTONE);
			tile.syncedEnergyUsage = 0;
		} else if (tile.beeLogic.canWork()) {
			boolean hasEnergy = EnergyHelper.consumeEnergyToDoWork(
					tile.getEnergyManager(), 1, tile.energyConsumption);
			errors.setCondition(!hasEnergy, ForestryError.NO_POWER);
			if (hasEnergy) {
				tile.beeLogic.doWork();
				tile.syncedEnergyUsage = tile.energyConsumption;
			} else {
				tile.syncedEnergyUsage = 0;
			}
		} else {
			tile.syncedEnergyUsage = 0;
		}

		tile.workProgressPercent = tile.beeLogic.getWorkProgressPercent();
		tile.syncErrors();
		if ((level.getGameTime() & 63L) == 0L) {
			tile.climate = IForestryApi.get().getClimateManager().createClimateProvider(level, pos);
		}
	}

	public static void clientTick(Level level, BlockPos pos, BlockState state, IndustrialApiaryBlockEntity tile) {
		tile.clientTicks++;
		if (tile.clientTicks % 10 == 0) {
			tile.beeLogic.doBeeFX();
		}
	}

	public void onIndustrialGuiOpened() {
		Level level = getLevel();
		if (level == null || level.isClientSide()) {
			return;
		}
		this.beeLogic.onGuiOpened();
		if (isRedstoneActivated()) {
			IErrorLogic errors = getErrorLogic();
			errors.clearErrors();
			errors.setCondition(true, ForestryError.DISABLED_BY_REDSTONE);
		}
		syncErrors();
	}

	private void syncErrors() {
		syncedErrorCount = 0;
		for (var error : getErrorLogic().getErrors()) {
			if (syncedErrorCount >= ERROR_SLOT_COUNT) {
				break;
			}
			short id = IForestryApi.get().getErrorManager().getNumericId(error);
			syncedErrorIds[syncedErrorCount++] = id;
		}
		for (int i = syncedErrorCount; i < ERROR_SLOT_COUNT; i++) {
			syncedErrorIds[i] = -1;
		}
	}

	@Override
	public boolean hasWork() {
		return false;
	}

	@Override
	protected boolean workCycle() {
		return false;
	}

	@Override
	public int getCurrentEnergyUsage() {
		return syncedEnergyUsage;
	}

	@Override
	public ContainerData getProgressData() {
		return this.progressData;
	}

	@Override
	public ContainerData getErrorData() {
		return this.errorData;
	}

	public ContainerData getClimateData() {
		return this.climateData;
	}

	@Override
	public String getHintsKey() {
		return HINTS_KEY;
	}

	public int getHealthScaled(int pixels) {
		return (this.workProgressPercent * pixels) / 100;
	}

	@Override
	public Level level() {
		return getLevel();
	}

	@Override
	public BlockPos position() {
		return getBlockPos();
	}

	@Override
	public IBeeHousingInventory beeInventory() {
		return this;
	}

	@Override
	public Iterable<IBeeModifier> getBeeModifiers() {
		return Collections.singleton(IDENTITY_MODIFIER);
	}

	@Override
	public Iterable<IBeeListener> getBeeListeners() {
		return List.of();
	}

	@Override
	public IBeekeepingLogic getBeekeepingLogic() {
		return this.beeLogic;
	}

	@Override
	public TemperatureType temperature() {
		return this.climate.temperature();
	}

	@Override
	public HumidityType humidity() {
		return this.climate.humidity();
	}

	@Override
	public Holder<Biome> getBiome() {
		Level level = getLevel();
		if (level == null) {
			throw new IllegalStateException("Industrial apiary has no level");
		}
		return level.getBiome(getBlockPos());
	}

	@Override
	public int getBlockLightValue() {
		Level level = getLevel();
		return level == null ? 0 : level.getMaxLocalRawBrightness(getBlockPos().above());
	}

	@Override
	public boolean canBlockSeeTheSky() {
		Level level = getLevel();
		return level != null && level.getBrightness(LightLayer.SKY, getBlockPos().above()) >= 10;
	}

	@Override
	public boolean isRaining() {
		Level level = getLevel();
		return level != null && level.isRaining() && level.getBrightness(LightLayer.SKY, getBlockPos().above()) > 7;
	}

	@Nullable
	@Override
	public GameProfile getOwner() {
		return this.owner;
	}

	public void setOwner(GameProfile owner) {
		this.owner = owner;
		setChanged();
	}

	@Override
	public Vec3 getBeeFXCoordinates() {
		BlockPos pos = getBlockPos();
		return new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
	}

	@Override
	public ItemStack getQueen() {
		return getItem(SLOT_QUEEN);
	}

	@Override
	public ItemStack getDrone() {
		return getItem(SLOT_DRONE);
	}

	@Override
	public void setQueen(ItemStack stack) {
		setItem(SLOT_QUEEN, stack);
	}

	@Override
	public void setDrone(ItemStack stack) {
		setItem(SLOT_DRONE, stack);
	}

	@Override
	public boolean addProduct(ItemStack product) {
		if (getQueen().isEmpty()
				&& product.getItem() instanceof ItemBeeGE bee
				&& "princess".equals(bee.lifeStage())) {
			setQueen(product);
			return true;
		}
		return InventoryUtil.tryAddStack(this, product, OUTPUT_SLOT_START, OUTPUT_SLOT_COUNT, true);
	}

	@Override
	public int getContainerSize() {
		return SLOT_COUNT;
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
	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		if (slot == SLOT_QUEEN || slot == SLOT_DRONE) {
			return InventoryBeeHousing.canAcceptBee(slot, stack);
		}
		if (slot >= UPGRADE_SLOT_START && slot < UPGRADE_SLOT_START + UPGRADE_SLOT_COUNT) {
			if (!stack.is(GendustryTags.Items.UPGRADES)) {
				return false;
			}
			for (int i = UPGRADE_SLOT_START; i < UPGRADE_SLOT_START + UPGRADE_SLOT_COUNT; i++) {
				if (i == slot) {
					continue;
				}
				Item current = getItem(i).getItem();
				if (stack.is(current)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return WorldlyAccessHelper.getSlotsForFace(this, SLOT_COUNT, direction);
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		if (slot >= OUTPUT_SLOT_START) {
			return false;
		}
		return WorldlyAccessHelper.canPlaceItemThroughFace(this, canPlaceItem(slot, stack), direction);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		boolean output = slot >= OUTPUT_SLOT_START && slot < OUTPUT_SLOT_START + OUTPUT_SLOT_COUNT;
		return WorldlyAccessHelper.canTakeItemThroughFace(this, output, direction);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.items);
		if (this.owner != null) {
			if (this.owner.id() != null) {
				output.store("owner_uuid", UUIDUtil.CODEC, this.owner.id());
			}
			output.putString("owner_name", this.owner.name());
		}
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.items.clear();
		ContainerHelper.loadAllItems(input, this.items);
		input.getString("owner_name").ifPresent(name -> {
			UUID id = input.read("owner_uuid", UUIDUtil.CODEC).orElse(null);
			this.owner = new GameProfile(id, name);
		});
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new IndustrialApiaryMenu(containerId, playerInventory, this);
	}
}
