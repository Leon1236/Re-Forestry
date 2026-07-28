package com.leon1236.reforestry.core.access;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;

import com.leon1236.reforestry.api.core.AccessMode;
import com.leon1236.reforestry.api.core.ISidedAccess;
import com.leon1236.reforestry.core.inventory.InventoryUtil;

public final class WorldlyAccessHelper {
	private WorldlyAccessHelper() {
	}

	public static int[] getSlotsForFace(ISidedAccess access, int slotCount, @Nullable Direction direction) {
		if (direction != null && access.getAccess(direction) == AccessMode.NONE) {
			return InventoryUtil.NO_SLOTS;
		}
		return InventoryUtil.contiguousSlots(slotCount);
	}

	public static int[] getSlotsForFace(ISidedAccess access, int[] slots, @Nullable Direction direction) {
		if (direction != null && access.getAccess(direction) == AccessMode.NONE) {
			return InventoryUtil.NO_SLOTS;
		}
		return slots;
	}

	public static boolean canPlaceItemThroughFace(
			ISidedAccess access,
			boolean allowed,
			@Nullable Direction direction
	) {
		if (!allowed) {
			return false;
		}
		if (direction == null) {
			return true;
		}
		return access.getAccess(direction).allowsInput();
	}

	public static boolean canTakeItemThroughFace(
			ISidedAccess access,
			boolean allowed,
			Direction direction
	) {
		if (!allowed) {
			return false;
		}
		return access.getAccess(direction).allowsOutput();
	}
}
