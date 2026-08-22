package com.leon1236.reforestry.energy.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import team.reborn.energy.api.base.SimpleEnergyStorage;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.core.energy.EnergyHelper;
import com.leon1236.reforestry.core.tiles.IActivatable;
import com.leon1236.reforestry.core.tiles.IEnginePowerHandler;
import com.leon1236.reforestry.core.tiles.TemperatureState;
import com.leon1236.reforestry.core.tiles.TileBase;
import com.leon1236.reforestry.energy.EnergyConstants;
import com.leon1236.reforestry.energy.blocks.EngineBlock;

public abstract class EngineBlockEntity extends TileBase implements IActivatable, IEnginePowerHandler {
	private static final int CANT_SEND_ENERGY_TIME = 20;
	public static final int ERROR_SLOT_COUNT = 4;

	private boolean active;
	private int cantSendEnergyCountdown = CANT_SEND_ENERGY_TIME;
	public int stagePiston;
	public float pistonSpeedServer;
	protected int currentOutput;
	protected int heat;
	protected final int maxHeat;
	protected boolean forceCooldown;
	public float progress;
	protected final SimpleEnergyStorage energyStorage;
	private final String hintKey;
	private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
	private int syncedErrorCount;
	private int syncedCurrentOutput;
	private int syncedHeat;
	private int syncedEnergyStored;
	private int syncedEnergyCapacity;
	private int syncedForceCooldown;

	private final ContainerData engineData = new ContainerData() {
		@Override
		public int get(int index) {
			Level level = getLevel();
			boolean client = level != null && level.isClientSide();
			return switch (index) {
				case 0 -> client ? syncedCurrentOutput : getCurrentOutput();
				case 1 -> client ? syncedHeat : heat;
				case 2 -> client ? syncedEnergyStored : (int) energyStorage.amount;
				case 3 -> client ? syncedEnergyCapacity : (int) energyStorage.capacity;
				case 4 -> client ? syncedForceCooldown : (forceCooldown ? 1 : 0);
				default -> 0;
			};
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
				case 0 -> syncedCurrentOutput = value;
				case 1 -> syncedHeat = value;
				case 2 -> syncedEnergyStored = value;
				case 3 -> syncedEnergyCapacity = value;
				case 4 -> syncedForceCooldown = value;
				default -> {
				}
			}
		}

		@Override
		public int getCount() {
			return 5;
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

	protected EngineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, String hintKey, int maxHeat, int maxEnergy) {
		super(type, pos, state);
		this.hintKey = hintKey;
		this.maxHeat = maxHeat;
		this.energyStorage = new SimpleEnergyStorage(maxEnergy, 0, EnergyConstants.ENGINE_MAX_TRANSFER) {
			@Override
			protected void onFinalCommit() {
				setChanged();
			}
		};
	}

	public String getHintKey() {
		return this.hintKey;
	}

	public ContainerData getEngineData() {
		return this.engineData;
	}

	public ContainerData getErrorData() {
		return this.errorData;
	}

	protected void addHeat(int i) {
		this.heat += i;
		if (this.heat > this.maxHeat) {
			this.heat = this.maxHeat;
		}
	}

	protected abstract void dissipateHeat();

	protected abstract void generateHeat();

	protected boolean mayBurn() {
		return !this.forceCooldown;
	}

	protected abstract void burn();

	public static void clientTick(Level level, BlockPos pos, BlockState state, EngineBlockEntity tile) {
		tile.clientTick(level, pos, state);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, EngineBlockEntity tile) {
		tile.serverTick(level, pos, state);
	}

	protected void clientTick(Level level, BlockPos pos, BlockState state) {
		if (this.stagePiston != 0) {
			this.progress += this.pistonSpeedServer;
			if (this.progress > 1) {
				this.stagePiston = 0;
				this.progress = 0;
			}
		} else if (this.active) {
			this.stagePiston = 1;
		}
	}

	protected void serverTick(Level level, BlockPos pos, BlockState state) {
		advanceTicks();

		TemperatureState energyState = getTemperatureState();
		if (energyState == TemperatureState.MELTING && this.heat > 0) {
			this.forceCooldown = true;
		} else if (this.forceCooldown && this.heat <= 0) {
			this.forceCooldown = false;
		}

		IErrorLogic errorLogic = getErrorLogic();
		errorLogic.setCondition(this.forceCooldown, ForestryError.FORCED_COOLDOWN);

		boolean enabledRedstone = isRedstoneActivated();
		errorLogic.setCondition(!enabledRedstone, ForestryError.NO_REDSTONE);

		Direction facing = getBlockState().getValue(EngineBlock.VERTICAL_FACING);
		BlockPos targetPos = pos.relative(facing);

		float newPistonSpeed = getPistonSpeed();
		if (newPistonSpeed != this.pistonSpeedServer) {
			this.pistonSpeedServer = newPistonSpeed;
			syncToClient();
		}

		if (this.stagePiston != 0) {
			this.progress += this.pistonSpeedServer;
			EnergyHelper.sendEnergy(this.energyStorage, level, targetPos, facing.getOpposite());
			if (this.progress > 0.25 && this.stagePiston == 1) {
				this.stagePiston = 2;
			} else if (this.progress >= 0.5) {
				this.progress = 0;
				this.stagePiston = 0;
			}
		} else if (enabledRedstone && EnergyHelper.isEnergyReceiverOrEngine(
				facing.getOpposite(),
				level.getBlockEntity(targetPos))) {
			if (EnergyHelper.canSendEnergy(this.energyStorage, level, targetPos, facing.getOpposite())) {
				this.stagePiston = 1;
				setActive(true);
				this.cantSendEnergyCountdown = CANT_SEND_ENERGY_TIME;
			} else if (isActive()) {
				this.cantSendEnergyCountdown--;
				if (this.cantSendEnergyCountdown <= 0) {
					setActive(false);
				}
			}
		} else {
			setActive(false);
		}

		dissipateHeat();
		generateHeat();
		if (mayBurn()) {
			burn();
		} else {
			drainEnergy(20);
		}

		syncErrors();
	}

	protected void syncErrors() {
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
	public boolean isActive() {
		return this.active;
	}

	@Override
	public void setActive(boolean active) {
		if (this.active == active) {
			return;
		}
		this.active = active;
		if (this.level != null && !this.level.isClientSide()) {
			syncToClient();
		}
	}

	protected void syncToClient() {
		Level level = getLevel();
		if (level != null && !level.isClientSide() && level.isLoaded(this.worldPosition)) {
			level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), 3);
		}
	}

	protected double getHeatLevel() {
		return (double) this.heat / (double) this.maxHeat;
	}

	protected abstract boolean isBurning();

	public int getBurnTimeRemainingScaled(int i) {
		return 0;
	}

	public boolean hasFuelMin(float percentage) {
		return false;
	}

	public int getCurrentOutput() {
		if (isBurning() && isRedstoneActivated()) {
			return this.currentOutput;
		}
		return 0;
	}

	public int getHeat() {
		Level level = getLevel();
		if (level != null && level.isClientSide()) {
			return this.syncedHeat;
		}
		return this.heat;
	}

	public TemperatureState getTemperatureState() {
		return TemperatureState.getState(this.heat, this.maxHeat);
	}

	protected float getPistonSpeed() {
		return switch (getTemperatureState()) {
			case COOL -> 0.03f;
			case WARMED_UP -> 0.04f;
			case OPERATING_TEMPERATURE -> 0.05f;
			case RUNNING_HOT -> 0.06f;
			case OVERHEATING -> 0.07f;
			case MELTING -> EnergyConstants.ENGINE_PISTON_SPEED_MAX;
			default -> 0;
		};
	}

	protected void generateEnergy(int amount) {
		this.energyStorage.amount = Math.min(this.energyStorage.capacity, this.energyStorage.amount + amount);
		setChanged();
	}

	protected void drainEnergy(int amount) {
		this.energyStorage.amount = Math.max(0, this.energyStorage.amount - amount);
		setChanged();
	}

	@Override
	public long forceReceiveEnergy(long maxReceive, boolean simulate) {
		long energyReceived = Math.min(this.energyStorage.capacity - this.energyStorage.amount, maxReceive);
		if (!simulate && energyReceived > 0) {
			this.energyStorage.amount += energyReceived;
			setChanged();
		}
		return energyReceived;
	}

	@Override
	public SimpleEnergyStorage getEnergyManager() {
		return this.energyStorage;
	}

	public int calculateRedstone() {
		if (this.energyStorage.capacity <= 0) {
			return 0;
		}
		return Mth.floor(((float) this.energyStorage.amount / (float) this.energyStorage.capacity) * 14.0F)
				+ (this.energyStorage.amount > 0 ? 1 : 0);
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		return saveCustomOnly(registries);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.energyStorage.amount = input.getLongOr("Energy", 0L);
		this.heat = input.getIntOr("EngineHeat", 0);
		this.progress = input.getFloatOr("EngineProgress", 0.0f);
		this.forceCooldown = input.getBooleanOr("ForceCooldown", false);
		this.active = input.getBooleanOr("Active", false);
		this.pistonSpeedServer = input.getFloatOr("PistonSpeed", 0.0f);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		output.putLong("Energy", this.energyStorage.amount);
		output.putInt("EngineHeat", this.heat);
		output.putFloat("EngineProgress", this.progress);
		output.putBoolean("ForceCooldown", this.forceCooldown);
		output.putBoolean("Active", this.active);
		output.putFloat("PistonSpeed", this.pistonSpeedServer);
	}
}
