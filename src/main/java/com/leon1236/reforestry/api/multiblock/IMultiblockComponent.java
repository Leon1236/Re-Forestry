package com.leon1236.reforestry.api.multiblock;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;

public interface IMultiblockComponent {
	BlockPos getCoordinates();

	@Nullable
	GameProfile getOwner();

	IMultiblockLogic getMultiblockLogic();

	void onMachineAssembled(IMultiblockController multiblockController, BlockPos minCoord, BlockPos maxCoord);

	void onMachineBroken();

	interface HasInventory {
		Container getInternalInventory();
	}
}
