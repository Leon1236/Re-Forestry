package com.leon1236.reforestry.core.multiblock;

import com.leon1236.reforestry.ReForestry;

import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockLogic;
import com.leon1236.reforestry.core.tiles.TileUtil;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.ChunkSource;

import java.util.*;

public class MultiblockWorldRegistry {
	private final Level world;

	private final Set<IMultiblockControllerInternal> controllers;
	private final Set<IMultiblockControllerInternal> dirtyControllers;
	private final Set<IMultiblockControllerInternal> deadControllers;

	private Set<IMultiblockComponent> orphanedParts;

	private final Set<IMultiblockComponent> detachedParts;

	private final Long2ObjectMap<Set<IMultiblockComponent>> partsAwaitingChunkLoad;

	private final Object partsAwaitingChunkLoadMutex;
	private final Object orphanedPartsMutex;

	public MultiblockWorldRegistry(Level world) {
		this.world = world;

		this.controllers = new HashSet<>();
		this.deadControllers = new HashSet<>();
		this.dirtyControllers = new HashSet<>();

		this.detachedParts = new HashSet<>();
		this.orphanedParts = new HashSet<>();

		this.partsAwaitingChunkLoad = new Long2ObjectOpenHashMap<>();
		this.partsAwaitingChunkLoadMutex = new Object();
		this.orphanedPartsMutex = new Object();
	}

	public void tickStart() {
		if (!this.controllers.isEmpty()) {
			for (IMultiblockControllerInternal controller : this.controllers) {
				if (controller.getWorldObj() == this.world && controller.getWorldObj().isClientSide() == this.world.isClientSide()) {
					if (controller.hasNoParts()) {

                        this.deadControllers.add(controller);
					} else {

						controller.updateMultiblockEntity();
					}
				}
			}
		}
	}

	public void processMultiblockChanges() {
		ChunkSource chunkProvider = this.world.getChunkSource();
		BlockPos coord;

		List<Set<IMultiblockControllerInternal>> mergePools = null;
		if (!this.orphanedParts.isEmpty()) {
			Set<IMultiblockComponent> orphansToProcess = null;

			synchronized (this.orphanedPartsMutex) {
				if (!this.orphanedParts.isEmpty()) {
					orphansToProcess = this.orphanedParts;
                    this.orphanedParts = new HashSet<>();
				}
			}

			if (orphansToProcess != null && !orphansToProcess.isEmpty()) {
				Set<IMultiblockControllerInternal> compatibleControllers;

				for (IMultiblockComponent orphan : orphansToProcess) {
					coord = orphan.getCoordinates();
					if (!chunkProvider.hasChunk(coord.getX() >> 4, coord.getZ() >> 4)) {
						continue;
					}

					if (orphan instanceof BlockEntity entity && entity.isRemoved()) {
						continue;
					}

					if (TileUtil.getTile(this.world, coord) != orphan) {

						continue;
					}

					compatibleControllers = attachToNeighbors(orphan);
					if (compatibleControllers.isEmpty()) {

						MultiblockLogic<?> logic = (MultiblockLogic<?>) orphan.getMultiblockLogic();
						IMultiblockControllerInternal newController = logic.createNewController(this.world);
						newController.attachBlock(orphan);
						this.controllers.add(newController);
					} else if (compatibleControllers.size() > 1) {
						if (mergePools == null) {
							mergePools = new ArrayList<>();
						}

						List<Set<IMultiblockControllerInternal>> candidatePools = new ArrayList<>();
						for (Set<IMultiblockControllerInternal> candidatePool : mergePools) {
							if (!Collections.disjoint(candidatePool, compatibleControllers)) {

								candidatePools.add(candidatePool);
							}
						}

						if (candidatePools.isEmpty()) {

							mergePools.add(compatibleControllers);
						} else if (candidatePools.size() == 1) {

							candidatePools.get(0).addAll(compatibleControllers);
						} else {

							Set<IMultiblockControllerInternal> masterPool = candidatePools.get(0);
							Set<IMultiblockControllerInternal> consumedPool;
							for (int i = 1; i < candidatePools.size(); i++) {
								consumedPool = candidatePools.get(i);
								masterPool.addAll(consumedPool);
								mergePools.remove(consumedPool);
							}
							masterPool.addAll(compatibleControllers);
						}
					}
				}
			}
		}

		if (mergePools != null && !mergePools.isEmpty()) {

			for (Set<IMultiblockControllerInternal> mergePool : mergePools) {

				IMultiblockControllerInternal newMaster = null;
				for (IMultiblockControllerInternal controller : mergePool) {
					if (newMaster == null || controller.shouldConsume(newMaster)) {
						newMaster = controller;
					}
				}

				if (newMaster == null) {
					ReForestry.LOGGER.error("Multiblock system checked a merge pool of size {}, found no master candidates. This should never happen.", mergePool.size());
				} else {

					addDirtyController(newMaster);
					for (IMultiblockControllerInternal controller : mergePool) {
						if (controller != newMaster) {
							newMaster.assimilate(controller);
							addDeadController(controller);
							addDirtyController(newMaster);
						}
					}
				}
			}
		}

		if (!this.dirtyControllers.isEmpty()) {
			for (IMultiblockControllerInternal controller : this.dirtyControllers) {
				if (controller == null) {
					continue;
				}

				Set<IMultiblockComponent> newlyDetachedParts = controller.checkForDisconnections();

				if (!controller.hasNoParts()) {
					controller.recalculateMinMaxCoords();
					controller.checkIfMachineIsWhole();
				} else {
					addDeadController(controller);
				}

				if (!newlyDetachedParts.isEmpty()) {

                    this.detachedParts.addAll(newlyDetachedParts);
				}
			}

            this.dirtyControllers.clear();
		}

		if (!this.deadControllers.isEmpty()) {
			for (IMultiblockControllerInternal controller : this.deadControllers) {

				if (!controller.hasNoParts()) {
					ReForestry.LOGGER.error("Found a non-empty controller. Forcing it to shed its blocks and die. This should never happen!");
                    this.detachedParts.addAll(controller.detachAllBlocks());
				}

				BlockPos destroyedCoord = controller.getDestroyedCoord();
				if (destroyedCoord != null) {
					controller.onDestroyed(destroyedCoord);
				}
				this.controllers.remove(controller);
			}

            this.deadControllers.clear();
		}

		for (IMultiblockComponent part : this.detachedParts) {

			MultiblockLogic<?> logic = (MultiblockLogic<?>) part.getMultiblockLogic();
			logic.assertDetached(part);
		}

		addAllOrphanedPartsThreadsafe(this.detachedParts);
        this.detachedParts.clear();
	}

	private Set<IMultiblockControllerInternal> attachToNeighbors(IMultiblockComponent part) {
		Set<IMultiblockControllerInternal> controllers = new HashSet<>();
		IMultiblockControllerInternal bestController = null;

		MultiblockLogic<?> logic = (MultiblockLogic<?>) part.getMultiblockLogic();
		Class<?> controllerClass = logic.getControllerClass();

		List<IMultiblockComponent> partsToCheck = MultiblockUtil.getNeighboringParts(this.world, part);
		for (IMultiblockComponent neighborPart : partsToCheck) {
			IMultiblockLogic neighborLogic = neighborPart.getMultiblockLogic();
			if (neighborLogic.isConnected()) {
				IMultiblockControllerInternal candidate = (IMultiblockControllerInternal) neighborLogic.getController();
				if (!controllerClass.isAssignableFrom(candidate.getClass())) {

					continue;
				}

				if (!controllers.contains(candidate) && (bestController == null || candidate.shouldConsume(bestController))) {
					bestController = candidate;
				}

				controllers.add(candidate);
			}
		}

		if (bestController != null) {

			bestController.attachBlock(part);
		}

		return controllers;
	}

	public void onPartAdded(IMultiblockComponent part) {
		BlockPos worldLocation = part.getCoordinates();

		if (!this.world.getChunkSource().hasChunk(worldLocation.getX() >> 4, worldLocation.getZ() >> 4)) {

			Set<IMultiblockComponent> partSet;
			long chunkHash = ChunkPos.pack(worldLocation.getX() >> 4, worldLocation.getZ() >> 4);
			synchronized (this.partsAwaitingChunkLoadMutex) {
				if (!this.partsAwaitingChunkLoad.containsKey(chunkHash)) {
					partSet = new HashSet<>();
                    this.partsAwaitingChunkLoad.put(chunkHash, partSet);
				} else {
					partSet = this.partsAwaitingChunkLoad.get(chunkHash);
				}

				partSet.add(part);
			}
		} else {

			addOrphanedPartThreadsafe(part);
		}
	}

	public void onPartRemovedFromWorld(IMultiblockComponent part) {
		BlockPos coord = part.getCoordinates();
		long hash = ChunkPos.pack(coord.getX() >> 4, coord.getZ() >> 4);

		if (this.partsAwaitingChunkLoad.containsKey(hash)) {
			synchronized (this.partsAwaitingChunkLoadMutex) {
				if (this.partsAwaitingChunkLoad.containsKey(hash)) {
                    this.partsAwaitingChunkLoad.get(hash).remove(part);
					if (this.partsAwaitingChunkLoad.get(hash).size() <= 0) {
                        this.partsAwaitingChunkLoad.remove(hash);
					}
				}
			}
		}

        this.detachedParts.remove(part);
		if (this.orphanedParts.contains(part)) {
			synchronized (this.orphanedPartsMutex) {
                this.orphanedParts.remove(part);
			}
		}

		MultiblockLogic<?> logic = (MultiblockLogic<?>) part.getMultiblockLogic();
		logic.assertDetached(part);
	}

	public void onWorldUnloaded() {
        this.controllers.clear();
        this.deadControllers.clear();
        this.dirtyControllers.clear();

        this.detachedParts.clear();

		synchronized (this.partsAwaitingChunkLoadMutex) {
            this.partsAwaitingChunkLoad.clear();
		}

		synchronized (this.orphanedPartsMutex) {
            this.orphanedParts.clear();
		}
	}

	public void onChunkLoaded(int chunkX, int chunkZ) {
		long chunkHash = ChunkPos.pack(chunkX, chunkZ);
		if (this.partsAwaitingChunkLoad.containsKey(chunkHash)) {
			synchronized (this.partsAwaitingChunkLoadMutex) {
				if (this.partsAwaitingChunkLoad.containsKey(chunkHash)) {
					addAllOrphanedPartsThreadsafe(this.partsAwaitingChunkLoad.get(chunkHash));
                    this.partsAwaitingChunkLoad.remove(chunkHash);
				}
			}
		}
	}

	public void addDeadController(IMultiblockControllerInternal deadController) {
		this.deadControllers.add(deadController);
	}

	public void addDirtyController(IMultiblockControllerInternal dirtyController) {
		this.dirtyControllers.add(dirtyController);
	}

	public Set<IMultiblockControllerInternal> getControllers() {
		return Collections.unmodifiableSet(this.controllers);
	}

	private void addOrphanedPartThreadsafe(IMultiblockComponent part) {
		synchronized (this.orphanedPartsMutex) {
            this.orphanedParts.add(part);
		}
	}

	private void addAllOrphanedPartsThreadsafe(Collection<? extends IMultiblockComponent> parts) {
		if (parts.isEmpty()) {
			return;
		}
		synchronized (this.orphanedPartsMutex) {
            this.orphanedParts.addAll(parts);
		}
	}
}
