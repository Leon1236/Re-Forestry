package com.leon1236.reforestry.api.core;

import net.minecraft.core.Direction;

public interface ISidedAccess {
	AccessMode getAccess(Direction direction);

	void setAccess(Direction direction, AccessMode mode);

	void cycleAccess(Direction direction);
}
