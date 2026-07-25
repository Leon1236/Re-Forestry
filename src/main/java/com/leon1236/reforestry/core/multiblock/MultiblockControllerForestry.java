package com.leon1236.reforestry.core.multiblock;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.core.ILocationProvider;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.core.owner.IOwnerHandler;
import com.leon1236.reforestry.core.owner.OwnerHandler;

public abstract class MultiblockControllerForestry extends MultiblockControllerBase implements ILocationProvider {
	private final OwnerHandler ownerHandler;
	private final IErrorLogic errorLogic;

	protected MultiblockControllerForestry(Level world) {
		super(world);
		this.ownerHandler = new OwnerHandler();
		this.errorLogic = IForestryApi.INSTANCE.getErrorManager().createErrorLogic();
	}

	@Override
	public IOwnerHandler getOwnerHandler() {
		return this.ownerHandler;
	}

	@Override
	public IErrorLogic getErrorLogic() {
		return this.errorLogic;
	}

	@Override
	public Level getWorldObj() {
		return this.level;
	}

	@Override
	public BlockPos getCoordinates() {
		BlockPos reference = getReferenceCoord();
		return reference != null ? reference : BlockPos.ZERO;
	}

	@Override
	protected void onMachineAssembled() {
		super.onMachineAssembled();

		if (this.level.isClientSide()) {
			return;
		}

		Multiset<GameProfile> owners = HashMultiset.create();
		for (IMultiblockComponent part : this.connectedParts) {
			GameProfile owner = part.getOwner();
			if (owner != null) {
				owners.add(owner);
			}
		}

		GameProfile owner = null;
		int max = 0;
		for (Multiset.Entry<GameProfile> entry : owners.entrySet()) {
			int count = entry.getCount();
			if (count > max) {
				max = count;
				owner = entry.getElement();
			}
		}

		if (owner != null) {
			getOwnerHandler().setOwner(owner);
		}
	}

	@Override
	public CompoundTag write(CompoundTag data, HolderLookup.Provider registries) {
		this.ownerHandler.write(data, registries);
		return data;
	}

	@Override
	public void read(CompoundTag data, HolderLookup.Provider registries) {
		this.ownerHandler.read(data, registries);
	}
}
