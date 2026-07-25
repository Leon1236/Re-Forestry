package com.leon1236.reforestry.api.multiblock;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.core.INbtWritable;

public interface IMultiblockLogic extends INbtWritable {
	boolean isConnected();

	IMultiblockController getController();

	void validate(Level world, IMultiblockComponent part);

	void invalidate(Level world, IMultiblockComponent part);

	void onChunkUnload(Level world, IMultiblockComponent part);

	void encodeDescriptionPacket(CompoundTag packetData);

	void decodeDescriptionPacket(CompoundTag packetData);

	void readFromNBT(CompoundTag data);

	void read(ValueInput input);

	void write(ValueOutput output, HolderLookup.Provider registries);

	@Override
	CompoundTag write(CompoundTag data, HolderLookup.Provider registries);
}
