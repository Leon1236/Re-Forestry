package com.leon1236.reforestry.core.multiblock;

import java.util.Set;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.climate.IClimateProvider;
import com.leon1236.reforestry.api.core.IErrorLogicSource;
import com.leon1236.reforestry.api.core.INbtReadable;
import com.leon1236.reforestry.api.core.INbtWritable;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockController;
import com.leon1236.reforestry.core.owner.IOwnedTile;

public interface IMultiblockControllerInternal extends IMultiblockController, INbtWritable, INbtReadable, IOwnedTile, IErrorLogicSource, IClimateProvider {
	void attachBlock(IMultiblockComponent part);

	void detachBlock(IMultiblockComponent part, boolean chunkUnloading);

	void checkIfMachineIsWhole();

	void assimilate(IMultiblockControllerInternal other);

	void _onAssimilated(IMultiblockControllerInternal otherController);

	void onAssimilated(IMultiblockControllerInternal assimilator);

	void updateMultiblockEntity();

	@Nullable
	BlockPos getReferenceCoord();

	@Nullable
	BlockPos getDestroyedCoord();

	void recalculateMinMaxCoords();

	void formatDescriptionPacket(CompoundTag data);

	void decodeDescriptionPacket(CompoundTag data);

	Level getWorldObj();

	boolean hasNoParts();

	boolean shouldConsume(IMultiblockControllerInternal otherController);

	String getPartsListString();

	String getUnlocalizedType();

	void auditParts();

	Set<IMultiblockComponent> checkForDisconnections();

	Set<IMultiblockComponent> detachAllBlocks();
}
