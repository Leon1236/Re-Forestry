package com.leon1236.reforestry.core.multiblock;

import com.leon1236.reforestry.ReForestry;

import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.core.tiles.TileUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.LevelChunk;

import org.jetbrains.annotations.Nullable;
import java.util.*;

public abstract class MultiblockControllerBase implements IMultiblockControllerInternal {

	protected final Level level;

	private static final Random rand = new Random();
	private int tickCount = rand.nextInt(256);
	@Nullable
	private BlockPos destroyedCoord = null;

	protected enum AssemblyState {
		DISASSEMBLED, ASSEMBLED, PAUSED
	}

	protected AssemblyState assemblyState;

	protected HashSet<IMultiblockComponent> connectedParts;

	@Nullable
	private BlockPos referenceCoord;

	@Nullable
	private BlockPos minimumCoord;

	@Nullable
	private BlockPos maximumCoord;

	private boolean shouldCheckForDisconnections;

	@Nullable
	private MultiblockValidationException lastValidationException;

	protected MultiblockControllerBase(Level level) {
		this.level = level;
		this.connectedParts = new HashSet<>();

		this.referenceCoord = null;
		this.assemblyState = AssemblyState.DISASSEMBLED;

		this.minimumCoord = null;
		this.maximumCoord = null;

		this.shouldCheckForDisconnections = true;
		this.lastValidationException = null;
	}

	@Override

	public Collection<IMultiblockComponent> getComponents() {
		return Collections.unmodifiableCollection(this.connectedParts);
	}

	protected abstract void onAttachedPartWithMultiblockData(IMultiblockComponent part, CompoundTag data);

	@Override
	public void attachBlock(IMultiblockComponent part) {
		BlockPos coord = part.getCoordinates();

		if (!this.connectedParts.add(part)) {
			ReForestry.LOGGER.warn("[{}] Controller {} is double-adding part {} @ {}. This is unusual. " +
					"If you encounter odd behavior, please tear down the machine and rebuild it.",
                    this.level.isClientSide() ? "CLIENT" : "SERVER", hashCode(), part.hashCode(), coord);
		}

		MultiblockLogic<?> logic = (MultiblockLogic<?>) part.getMultiblockLogic();

		logic.setController(this);
		this.onBlockAdded(part);

		if (logic.hasMultiblockSaveData()) {
			CompoundTag savedData = logic.getMultiblockSaveData();
			onAttachedPartWithMultiblockData(part, savedData);
			logic.onMultiblockDataAssimilated();
		}

		if (this.referenceCoord == null) {
            this.referenceCoord = coord;
			logic.becomeMultiblockSaveDelegate();
		} else if (coord.compareTo(this.referenceCoord) < 0) {
			TileUtil.actOnTile(this.level, this.referenceCoord, IMultiblockComponent.class, tile -> {
				MultiblockLogic<?> teLogic = (MultiblockLogic<?>) tile.getMultiblockLogic();
				teLogic.forfeitMultiblockSaveDelegate();
			});

            this.referenceCoord = coord;
			logic.becomeMultiblockSaveDelegate();
		} else {
			logic.forfeitMultiblockSaveDelegate();
		}

		if (this.minimumCoord != null) {
			if (coord.getX() < this.minimumCoord.getX()) {
                this.minimumCoord = new BlockPos(coord.getX(), this.minimumCoord.getY(), this.minimumCoord.getZ());
			}
			if (coord.getY() < this.minimumCoord.getY()) {
                this.minimumCoord = new BlockPos(this.minimumCoord.getX(), coord.getY(), this.minimumCoord.getZ());
			}
			if (coord.getZ() < this.minimumCoord.getZ()) {
                this.minimumCoord = new BlockPos(this.minimumCoord.getX(), this.minimumCoord.getY(), coord.getZ());
			}
		}

		if (this.maximumCoord != null) {
			if (coord.getX() > this.maximumCoord.getX()) {
                this.maximumCoord = new BlockPos(coord.getX(), this.maximumCoord.getY(), this.maximumCoord.getZ());
			}
			if (coord.getY() > this.maximumCoord.getY()) {
                this.maximumCoord = new BlockPos(this.maximumCoord.getX(), coord.getY(), this.maximumCoord.getZ());
			}
			if (coord.getZ() > this.maximumCoord.getZ()) {
                this.maximumCoord = new BlockPos(this.maximumCoord.getX(), this.maximumCoord.getY(), coord.getZ());
			}
		}

		MultiblockRegistry.addDirtyController(this.level, this);
	}

	protected abstract void onBlockAdded(IMultiblockComponent newPart);

	protected abstract void onBlockRemoved(IMultiblockComponent oldPart);

	protected void onMachineAssembled() {

	}

	protected void onMachineRestored() {

	}

	protected void onMachinePaused() {

	}

	protected void onMachineDisassembled() {

	}

	private void onDetachBlock(IMultiblockComponent part) {

		MultiblockLogic<?> logic = (MultiblockLogic<?>) part.getMultiblockLogic();
		logic.setController(null);
		this.onBlockRemoved(part);
		logic.forfeitMultiblockSaveDelegate();

        this.minimumCoord = this.maximumCoord = null;

		if (this.referenceCoord != null && this.referenceCoord.equals(part.getCoordinates())) {
            this.referenceCoord = null;
		}

        this.shouldCheckForDisconnections = true;
	}

	@Override
	public void detachBlock(IMultiblockComponent part, boolean chunkUnloading) {
		if (chunkUnloading && this.assemblyState == AssemblyState.ASSEMBLED) {
			this.assemblyState = AssemblyState.PAUSED;
			this.onMachinePaused();
		}

		BlockPos oldReference = this.referenceCoord;

		onDetachBlock(part);
		if (!this.connectedParts.remove(part)) {
			BlockPos partCoords = part.getCoordinates();
			ReForestry.LOGGER.warn("[{}] Double-removing part ({}) @ {}, {}, {}, this is unexpected and may cause problems. " +
					"If you encounter anomalies, please tear down the reactor and rebuild it.",
                    this.level.isClientSide() ? "CLIENT" : "SERVER", part.hashCode(), partCoords.getX(), partCoords.getY(), partCoords.getZ());
		}

		if (this.connectedParts.isEmpty()) {

			MultiblockRegistry.addDeadController(this.level, this);

			this.destroyedCoord = oldReference;
			return;
		}

		MultiblockRegistry.addDirtyController(this.level, this);

		if (this.referenceCoord == null) {
			selectNewReferenceCoord();
		}
	}

	@Override
	public String getLastValidationError() {
		if (this.lastValidationException == null) {
			return null;
		}
		return this.lastValidationException.getMessage();
	}

	@Override
	public void reassemble() {
		MultiblockRegistry.addDirtyController(this.level, this);
	}

	protected abstract void isMachineWhole() throws MultiblockValidationException;

	@Override
	public void checkIfMachineIsWhole() {
		AssemblyState oldState = this.assemblyState;
		boolean isWhole;
        this.lastValidationException = null;
		try {
			isMachineWhole();
			isWhole = true;
		} catch (MultiblockValidationException e) {
            this.lastValidationException = e;
			isWhole = false;
		}

		if (isWhole) {

			assembleMachine(oldState);
		} else if (oldState == AssemblyState.ASSEMBLED) {

			disassembleMachine();
		}

	}

	private void assembleMachine(AssemblyState oldState) {
		this.assemblyState = AssemblyState.ASSEMBLED;

		for (IMultiblockComponent part : this.connectedParts) {
			part.onMachineAssembled(this, getMinimumCoord(), getMaximumCoord());
		}

		if (oldState == AssemblyState.PAUSED) {
			onMachineRestored();
		} else {
			onMachineAssembled();
		}
	}

	private void disassembleMachine() {
		this.assemblyState = AssemblyState.DISASSEMBLED;

		for (IMultiblockComponent part : this.connectedParts) {
			part.onMachineBroken();
		}

		onMachineDisassembled();
	}

	@Override
	public void assimilate(IMultiblockControllerInternal other) {
		BlockPos otherReferenceCoord = other.getReferenceCoord();
		BlockPos referenceCoord = getReferenceCoord();
		if (otherReferenceCoord != null && referenceCoord != null && referenceCoord.compareTo(otherReferenceCoord) >= 0) {
			throw new IllegalArgumentException("The controller with the lowest minimum-coord value must consume the one with the higher coords");
		}

		Set<IMultiblockComponent> partsToAcquire = new HashSet<>(other.getComponents());

		other._onAssimilated(this);

		for (IMultiblockComponent acquiredPart : partsToAcquire) {

			if (isInvalid(acquiredPart)) {
				continue;
			}

            this.connectedParts.add(acquiredPart);
			MultiblockLogic<?> logic = (MultiblockLogic<?>) acquiredPart.getMultiblockLogic();
			logic.setController(this);
			this.onBlockAdded(acquiredPart);
		}

		this.onAssimilate(other);
		other.onAssimilated(this);
	}

	@Override
	public void _onAssimilated(IMultiblockControllerInternal otherController) {
		if (this.referenceCoord != null) {
			if (this.level.getChunkSource().hasChunk(this.referenceCoord.getX() >> 4, this.referenceCoord.getZ() >> 4)) {
				TileUtil.actOnTile(this.level, this.referenceCoord, IMultiblockComponent.class, part -> {
					MultiblockLogic<?> logic = (MultiblockLogic<?>) part.getMultiblockLogic();
					logic.forfeitMultiblockSaveDelegate();
				});
			}
			this.referenceCoord = null;
		}

        this.connectedParts.clear();
	}

	protected abstract void onAssimilate(IMultiblockControllerInternal assimilated);

	@Override
	public final void updateMultiblockEntity() {
        this.tickCount++;

		if (this.connectedParts.isEmpty()) {

			MultiblockRegistry.addDeadController(this.level, this);
			return;
		}

		if (this.assemblyState != AssemblyState.ASSEMBLED) {

			return;
		}

		if (this.level.isClientSide()) {
			clientTick(this.tickCount);
		} else if (serverTick(this.tickCount)) {
			if (this.minimumCoord != null && this.maximumCoord != null) {
				int minChunkX = this.minimumCoord.getX() >> 4;
				int minChunkZ = this.minimumCoord.getZ() >> 4;
				int maxChunkX = this.maximumCoord.getX() >> 4;
				int maxChunkZ = this.maximumCoord.getZ() >> 4;

				if (areChunksLoaded(minChunkX, minChunkZ, maxChunkX, maxChunkZ)) {
					for (int x = minChunkX; x <= maxChunkX; x++) {
						for (int z = minChunkZ; z <= maxChunkZ; z++) {
							LevelChunk chunkToSave = this.level.getChunkSource().getChunkNow(x, z);
							if (chunkToSave != null) {
								chunkToSave.markUnsaved();
							}
						}
					}
				}
			}
		}

	}

	protected abstract boolean serverTick(int tickCount);

	protected int getTickCount() {
		return this.tickCount;
	}

	protected abstract void clientTick(int tickCount);

	protected final boolean updateOnInterval(int tickInterval) {
		return this.tickCount % tickInterval == 0;
	}

	protected void isBlockGoodForExteriorLevel(int level, Level world, BlockPos pos) throws MultiblockValidationException {
		Block block = world.getBlockState(pos).getBlock();
		throw new MultiblockValidationException(Component.translatable("for.multiblock.error.invalid.interior", block).getString(), pos);
	}

	protected void isBlockGoodForInterior(Level world, BlockPos pos) throws MultiblockValidationException {
		Block block = world.getBlockState(pos).getBlock();
		throw new MultiblockValidationException(Component.translatable("for.multiblock.error.invalid.interior", block).getString(), pos);
	}

	@Override
	@Nullable
	public BlockPos getReferenceCoord() {
		if (this.referenceCoord == null) {
			return selectNewReferenceCoord();
		}
		return this.referenceCoord;
	}

	public int getNumConnectedBlocks() {
		return this.connectedParts.size();
	}

	@Override
	public void recalculateMinMaxCoords() {
        this.minimumCoord = new BlockPos(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.maximumCoord = new BlockPos(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

		for (IMultiblockComponent part : this.connectedParts) {
			BlockPos partCoords = part.getCoordinates();
			int minX = this.minimumCoord.getX();
			int minY = this.minimumCoord.getY();
			int minZ = this.minimumCoord.getZ();
			int maxX = this.maximumCoord.getX();
			int maxY = this.maximumCoord.getY();
			int maxZ = this.maximumCoord.getZ();
			if (partCoords.getX() < this.minimumCoord.getX()) {
				minX = partCoords.getX();
			}
			if (partCoords.getX() > this.maximumCoord.getX()) {
				maxX = partCoords.getX();
			}
			if (partCoords.getY() < this.minimumCoord.getY()) {
				minY = partCoords.getY();
			}
			if (partCoords.getY() > this.maximumCoord.getY()) {
				maxY = partCoords.getY();
			}
			if (partCoords.getZ() < this.minimumCoord.getZ()) {
				minZ = partCoords.getZ();
			}
			if (partCoords.getZ() > this.maximumCoord.getZ()) {
				maxZ = partCoords.getZ();
			}
            this.minimumCoord = new BlockPos(minX, minY, minZ);
            this.maximumCoord = new BlockPos(maxX, maxY, maxZ);
		}
	}

	protected BlockPos getMinimumCoord() {
		if (this.minimumCoord == null) {
			recalculateMinMaxCoords();
		}
		return new BlockPos(this.minimumCoord);
	}

	protected BlockPos getMaximumCoord() {
		if (this.maximumCoord == null) {
			recalculateMinMaxCoords();
		}
		return new BlockPos(this.maximumCoord);
	}

	protected final BlockPos getCenterCoord() {
		BlockPos minCoord = getMinimumCoord();
		BlockPos maxCoord = getMaximumCoord();

		return new BlockPos(
			(minCoord.getX() + maxCoord.getX()) / 2,
			(minCoord.getY() + maxCoord.getY()) / 2,
			(minCoord.getZ() + maxCoord.getZ()) / 2
		);
	}

	protected final BlockPos getTopCenterCoord() {
		BlockPos minCoord = getMinimumCoord();
		BlockPos maxCoord = getMaximumCoord();

		return new BlockPos(
			(minCoord.getX() + maxCoord.getX()) / 2,
			maxCoord.getY(),
			(minCoord.getZ() + maxCoord.getZ()) / 2
		);
	}

	protected final boolean isCoordInMultiblock(int x, int y, int z) {
		if (this.minimumCoord == null || this.maximumCoord == null) {
			return false;
		}
		return x >= this.minimumCoord.getX() && x <= this.maximumCoord.getX() && y >= this.minimumCoord.getY() && y <= this.maximumCoord.getY() && z >= this.minimumCoord.getZ() && z <= this.maximumCoord.getZ();
	}

	@Override
	public boolean hasNoParts() {
		return this.connectedParts.isEmpty();
	}

	@Override
	public boolean shouldConsume(IMultiblockControllerInternal otherController) {
		if (!otherController.getClass().equals(getClass())) {
			throw new IllegalArgumentException("Attempting to merge two multiblocks with different master classes - this should never happen!");
		}

		if (otherController == this) {
			return false;
		}

		int res = _shouldConsume(otherController);
		if (res < 0) {
			return true;
		} else if (res > 0) {
			return false;
		} else {

			ReForestry.LOGGER.warn("[{}] Encountered two controllers with the same reference coordinate. Auditing connected parts and retrying.", this.level.isClientSide() ? "CLIENT" : "SERVER");
			auditParts();
			otherController.auditParts();

			res = _shouldConsume(otherController);
			if (res < 0) {
				return true;
			} else if (res > 0) {
				return false;
			} else {
				ReForestry.LOGGER.error("My Controller ({}): size ({}), parts: {}", hashCode(), this.connectedParts.size(), getPartsListString());
				ReForestry.LOGGER.error("Other Controller ({}): size ({}), coords: {}", otherController.hashCode(), otherController.getComponents().size(), otherController.getPartsListString());
				throw new IllegalArgumentException("[" + (this.level.isClientSide() ? "CLIENT" : "SERVER") + "] " +
					"Two controllers with the same reference coord that somehow both have valid parts - this should never happen!");
			}

		}
	}

	private int _shouldConsume(IMultiblockControllerInternal otherController) {
		BlockPos myCoord = getReferenceCoord();
		BlockPos theirCoord = otherController.getReferenceCoord();

		if (theirCoord == null || myCoord == null) {
			return -1;
		} else {
			return myCoord.compareTo(theirCoord);
		}
	}

	@Override
	public String getPartsListString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (IMultiblockComponent part : this.connectedParts) {
			if (!first) {
				sb.append(", ");
			}
			BlockPos partCoord = part.getCoordinates();
			sb.append(String.format("(%d: %d, %d, %d)", part.hashCode(), partCoord.getX(), partCoord.getY(), partCoord.getZ()));
			first = false;
		}

		return sb.toString();
	}

	@Override
	public void auditParts() {
		HashSet<IMultiblockComponent> deadParts = new HashSet<>();
		for (IMultiblockComponent part : this.connectedParts) {
			BlockPos partCoord = part.getCoordinates();
			if (isInvalid(part) || TileUtil.getTile(this.level, partCoord) != part) {
				onDetachBlock(part);
				deadParts.add(part);
			}
		}

        this.connectedParts.removeAll(deadParts);
		ReForestry.LOGGER.warn("[{}] Controller found {} dead parts during an audit, {} parts remain attached", this.level.isClientSide() ? "CLIENT" : "SERVER", deadParts.size(), this.connectedParts.size());
	}

	@Override

	public Set<IMultiblockComponent> checkForDisconnections() {
		if (!this.shouldCheckForDisconnections) {
			return Collections.emptySet();
		}

		if (hasNoParts()) {
			MultiblockRegistry.addDeadController(this.level, this);
			return Collections.emptySet();
		}

		ChunkSource chunkProvider = this.level.getChunkSource();

        this.referenceCoord = null;

		Set<IMultiblockComponent> deadParts = new HashSet<>();
		BlockPos c;
		IMultiblockComponent referencePart = null;

		for (IMultiblockComponent part : this.connectedParts) {

			BlockPos partCoord = part.getCoordinates();
			if (chunkProvider.getChunkNow(partCoord.getX() >> 4, partCoord.getZ() >> 4) == null || isInvalid(part)) {
				deadParts.add(part);
				onDetachBlock(part);
				continue;
			}

			if (TileUtil.getTile(this.level, partCoord) != part) {
				deadParts.add(part);
				onDetachBlock(part);
				continue;
			}

			MultiblockLogic<?> logic = (MultiblockLogic<?>) part.getMultiblockLogic();

			logic.setUnvisited();
			logic.forfeitMultiblockSaveDelegate();

			c = part.getCoordinates();
			if (this.referenceCoord == null) {
                this.referenceCoord = c;
				referencePart = part;
			} else if (c.compareTo(this.referenceCoord) < 0) {
                this.referenceCoord = c;
				referencePart = part;
			}
		}

        this.connectedParts.removeAll(deadParts);
		deadParts.clear();

		if (referencePart == null || hasNoParts()) {

            this.shouldCheckForDisconnections = false;
			MultiblockRegistry.addDeadController(this.level, this);
			return Collections.emptySet();
		} else {
			MultiblockLogic<?> logic = (MultiblockLogic<?>) referencePart.getMultiblockLogic();
			logic.becomeMultiblockSaveDelegate();
		}

		IMultiblockComponent part;
		LinkedList<IMultiblockComponent> partsToCheck = new LinkedList<>();

		partsToCheck.add(referencePart);

		while (!partsToCheck.isEmpty()) {
			part = partsToCheck.removeFirst();
			MultiblockLogic<?> partLogic = (MultiblockLogic<?>) part.getMultiblockLogic();
			partLogic.setVisited();

			List<IMultiblockComponent> nearbyParts = MultiblockUtil.getNeighboringParts(this.level, part);
			for (IMultiblockComponent nearbyPart : nearbyParts) {

				MultiblockLogic<?> nearbyPartLogic = (MultiblockLogic<?>) nearbyPart.getMultiblockLogic();
				if (nearbyPartLogic.getController() != this) {
					continue;
				}

				if (!nearbyPartLogic.isVisited()) {
					nearbyPartLogic.setVisited();
					partsToCheck.add(nearbyPart);
				}
			}
		}

		Set<IMultiblockComponent> removedParts = new HashSet<>();
		for (IMultiblockComponent orphanCandidate : this.connectedParts) {
			MultiblockLogic<?> logic = (MultiblockLogic<?>) orphanCandidate.getMultiblockLogic();
			if (!logic.isVisited()) {
				deadParts.add(orphanCandidate);
				onDetachBlock(orphanCandidate);
				removedParts.add(orphanCandidate);
			}
		}

        this.connectedParts.removeAll(deadParts);

		deadParts.clear();

		if (this.referenceCoord == null) {
			selectNewReferenceCoord();
		}

        this.shouldCheckForDisconnections = false;

		return removedParts;
	}

	@Override

	public Set<IMultiblockComponent> detachAllBlocks() {
		ChunkSource chunkProvider = this.level.getChunkSource();
		for (IMultiblockComponent part : this.connectedParts) {
			BlockPos partCoord = part.getCoordinates();
			if (chunkProvider.getChunkNow(partCoord.getX() >> 4, partCoord.getZ() >> 4) != null) {
				onDetachBlock(part);
			}
		}

		Set<IMultiblockComponent> detachedParts = this.connectedParts;
        this.connectedParts = new HashSet<>();
		return detachedParts;
	}

	@Override
	public boolean isAssembled() {
		return this.assemblyState == AssemblyState.ASSEMBLED;
	}

	@Nullable
	private BlockPos selectNewReferenceCoord() {
		ChunkSource chunkProvider = this.level.getChunkSource();
		IMultiblockComponent theChosenOne = null;
        this.referenceCoord = null;

		for (IMultiblockComponent part : this.connectedParts) {
			BlockPos partCoord = part.getCoordinates();
			if (isInvalid(part) || chunkProvider.getChunkNow(partCoord.getX() >> 4, partCoord.getZ() >> 4) == null) {

				continue;
			}

			if (this.referenceCoord == null || this.referenceCoord.compareTo(partCoord) > 0) {
                this.referenceCoord = part.getCoordinates();
				theChosenOne = part;
			}
		}

		if (theChosenOne != null) {
			MultiblockLogic<?> logic = (MultiblockLogic<?>) theChosenOne.getMultiblockLogic();
			logic.becomeMultiblockSaveDelegate();
		}

		return this.referenceCoord;
	}

	private boolean areChunksLoaded(int minChunkX, int minChunkZ, int maxChunkX, int maxChunkZ) {
		for (int x = minChunkX; x <= maxChunkX; x++) {
			for (int z = minChunkZ; z <= maxChunkZ; z++) {
				if (!this.level.hasChunk(x, z)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isInvalid(IMultiblockComponent part) {
		return part instanceof BlockEntity && ((BlockEntity) part).isRemoved();
	}

	@Nullable
	public BlockPos getDestroyedCoord() {
		return this.destroyedCoord;
	}

	@Override
	public void formatDescriptionPacket(CompoundTag data) {
		write(data, this.level.registryAccess());
	}

	@Override
	public void decodeDescriptionPacket(CompoundTag data) {
		read(data, this.level.registryAccess());
	}
}
