package com.leon1236.reforestry.core.multiblock;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockLogic;

public abstract class MultiblockLogic<T extends IMultiblockControllerInternal> implements IMultiblockLogic {
	private final Class<T> controllerClass;
	private boolean visited;
	private boolean saveMultiblockData;
	@Nullable
	private CompoundTag cachedMultiblockData;
	@Nullable
	protected T controller;

	protected MultiblockLogic(Class<T> controllerClass) {
		this.controllerClass = controllerClass;
		clearController();
		this.visited = false;
		this.saveMultiblockData = false;
		this.cachedMultiblockData = null;
	}

	public void setController(@Nullable IMultiblockControllerInternal controller) {
		if (controller == null) {
			clearController();
		} else if (this.controllerClass.isAssignableFrom(controller.getClass())) {
			this.controller = this.controllerClass.cast(controller);
		}
	}

	private void clearController() {
		this.controller = absent();
	}

	@Nullable
	private static <X> X absent() {
		return null;
	}

	public Class<T> getControllerClass() {
		return this.controllerClass;
	}

	@Override
	public abstract T getController();

	public abstract T createNewController(Level level);

	@Override
	public void validate(Level world, IMultiblockComponent part) {
		MultiblockRegistry.onPartAdded(world, part);
	}

	@Override
	public final void invalidate(Level world, IMultiblockComponent part) {
		detachSelf(world, part, false);
	}

	@Override
	public final void onChunkUnload(Level world, IMultiblockComponent part) {
		detachSelf(world, part, true);
	}

	protected void detachSelf(Level world, IMultiblockComponent part, boolean chunkUnloading) {
		if (chunkUnloading) {
			snapshotMultiblockDataForUnload(world);
		}
		if (this.controller != null) {
			this.controller.detachBlock(part, chunkUnloading);
			clearController();
		}
		MultiblockRegistry.onPartRemovedFromWorld(world, part);
	}

	private void snapshotMultiblockDataForUnload(Level world) {
		if (!isMultiblockSaveDelegate() || this.controller == null) {
			return;
		}
		CompoundTag multiblockData = new CompoundTag();
		this.controller.write(multiblockData, world.registryAccess());
		this.cachedMultiblockData = multiblockData;
	}

	@Override
	public void readFromNBT(CompoundTag data) {
		data.getCompound("multiblockData").ifPresent(tag -> this.cachedMultiblockData = tag);
	}

	@Override
	public void read(ValueInput input) {
		input.read("multiblockData", CompoundTag.CODEC).ifPresent(tag -> this.cachedMultiblockData = tag);
	}

	@Override
	public void write(ValueOutput output, HolderLookup.Provider registries) {
		CompoundTag multiblockData = getMultiblockDataToSave(registries);
		if (multiblockData != null) {
			output.store("multiblockData", CompoundTag.CODEC, multiblockData);
		}
	}

	@Override
	public CompoundTag write(CompoundTag data, HolderLookup.Provider registries) {
		CompoundTag multiblockData = getMultiblockDataToSave(registries);
		if (multiblockData != null) {
			data.put("multiblockData", multiblockData);
		}
		return data;
	}

	@Nullable
	private CompoundTag getMultiblockDataToSave(HolderLookup.Provider registries) {
		if (isMultiblockSaveDelegate() && this.controller != null) {
			CompoundTag multiblockData = new CompoundTag();
			this.controller.write(multiblockData, registries);
			return multiblockData;
		}
		return this.cachedMultiblockData;
	}

	public final void assertDetached(IMultiblockComponent part) {
		if (this.controller != null) {
			BlockPos coords = part.getCoordinates();
			ReForestry.LOGGER.info(
					"[assert] Part @ ({}, {}, {}) should be detached already, but detected that it was not. This is not a fatal error, and will be repaired, but is unusual.",
					coords.getX(), coords.getY(), coords.getZ());
			clearController();
		}
	}

	@Override
	public final boolean isConnected() {
		return this.controller != null;
	}

	public void becomeMultiblockSaveDelegate() {
		this.saveMultiblockData = true;
	}

	public void forfeitMultiblockSaveDelegate() {
		this.saveMultiblockData = false;
	}

	public final boolean isMultiblockSaveDelegate() {
		return this.saveMultiblockData;
	}

	public final void setUnvisited() {
		this.visited = false;
	}

	public final void setVisited() {
		this.visited = true;
	}

	public final boolean isVisited() {
		return this.visited;
	}

	public final boolean hasMultiblockSaveData() {
		return this.cachedMultiblockData != null;
	}

	@Nullable
	public final CompoundTag getMultiblockSaveData() {
		return this.cachedMultiblockData;
	}

	public final void onMultiblockDataAssimilated() {
		this.cachedMultiblockData = null;
	}

	@Override
	public void encodeDescriptionPacket(CompoundTag packetData) {
		if (this.isMultiblockSaveDelegate() && this.controller != null) {
			CompoundTag tag = new CompoundTag();
			this.controller.formatDescriptionPacket(tag);
			packetData.put("multiblockData", tag);
		}
	}

	@Override
	public void decodeDescriptionPacket(CompoundTag packetData) {
		packetData.getCompound("multiblockData").ifPresent(tag -> {
			if (this.controller != null) {
				this.controller.decodeDescriptionPacket(tag);
			} else {
				this.cachedMultiblockData = tag;
			}
		});
	}
}
