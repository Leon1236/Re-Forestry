package com.leon1236.reforestry.core.multiblock;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.core.ILocationProvider;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.core.errors.FakeErrorLogic;
import com.leon1236.reforestry.core.owner.FakeOwnerHandler;
import com.leon1236.reforestry.core.owner.IOwnerHandler;

public interface FakeMultiblockController extends IMultiblockControllerInternal, ILocationProvider {
	@Override
	default void attachBlock(IMultiblockComponent part) {
	}

	@Override
	default void detachBlock(IMultiblockComponent part, boolean chunkUnloading) {
	}

	@Override
	default void checkIfMachineIsWhole() {
	}

	@Override
	default void assimilate(IMultiblockControllerInternal other) {
	}

	@Override
	default void _onAssimilated(IMultiblockControllerInternal otherController) {
	}

	@Override
	default void onAssimilated(IMultiblockControllerInternal assimilator) {
	}

	@Override
	default void updateMultiblockEntity() {
	}

	@Override
	default BlockPos getReferenceCoord() {
		return BlockPos.ZERO;
	}

	@Override
	default BlockPos getDestroyedCoord() {
		return null;
	}

	@Override
	default void recalculateMinMaxCoords() {
	}

	@Override
	default void formatDescriptionPacket(CompoundTag data) {
	}

	@Override
	default void decodeDescriptionPacket(CompoundTag data) {
	}

	@Override
	default Level getWorldObj() {
		return null;
	}

	@Override
	default boolean hasNoParts() {
		return true;
	}

	@Override
	default boolean shouldConsume(IMultiblockControllerInternal otherController) {
		return false;
	}

	@Override
	default String getPartsListString() {
		return "";
	}

	@Override
	default String getUnlocalizedType() {
		return "";
	}

	@Override
	default void auditParts() {
	}

	@Override
	default Set<IMultiblockComponent> checkForDisconnections() {
		return Collections.emptySet();
	}

	@Override
	default Set<IMultiblockComponent> detachAllBlocks() {
		return Collections.emptySet();
	}

	@Override
	default boolean isAssembled() {
		return false;
	}

	@Override
	default void reassemble() {
	}

	@Override
	default String getLastValidationError() {
		return null;
	}

	@Override
	default Collection<IMultiblockComponent> getComponents() {
		return Collections.emptyList();
	}

	@Override
	default void read(CompoundTag compoundNBT, HolderLookup.Provider registries) {
	}

	@Override
	default CompoundTag write(CompoundTag compoundNBT, HolderLookup.Provider registries) {
		return compoundNBT;
	}

	@Override
	default IOwnerHandler getOwnerHandler() {
		return FakeOwnerHandler.INSTANCE;
	}

	@Override
	default TemperatureType temperature() {
		return TemperatureType.NORMAL;
	}

	@Override
	default HumidityType humidity() {
		return HumidityType.NORMAL;
	}

	@Override
	default IErrorLogic getErrorLogic() {
		return FakeErrorLogic.INSTANCE;
	}

	@Override
	default BlockPos getCoordinates() {
		return BlockPos.ZERO;
	}
}
