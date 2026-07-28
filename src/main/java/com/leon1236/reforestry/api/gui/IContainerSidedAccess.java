package com.leon1236.reforestry.api.gui;

import net.minecraft.core.Direction;

import com.leon1236.reforestry.api.core.AccessMode;

public interface IContainerSidedAccess {
	int ACCESS_BUTTON_BASE = 200;

	static int accessButtonId(Direction direction) {
		return ACCESS_BUTTON_BASE + direction.get3DDataValue();
	}

	static Direction directionFromButtonId(int buttonId) {
		int index = buttonId - ACCESS_BUTTON_BASE;
		if (index < 0 || index >= Direction.values().length) {
			return null;
		}
		return Direction.from3DDataValue(index);
	}

	AccessMode getAccess(Direction direction);
}
