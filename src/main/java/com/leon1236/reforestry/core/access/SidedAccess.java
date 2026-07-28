package com.leon1236.reforestry.core.access;

import java.util.EnumMap;
import java.util.Map;

import net.minecraft.core.Direction;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.core.AccessMode;
import com.leon1236.reforestry.api.core.ISidedAccess;

public final class SidedAccess implements ISidedAccess {
	private final EnumMap<Direction, AccessMode> access = new EnumMap<>(Direction.class);

	public SidedAccess() {
		for (Direction direction : Direction.values()) {
			this.access.put(direction, AccessMode.BOTH);
		}
	}

	@Override
	public AccessMode getAccess(Direction direction) {
		return this.access.getOrDefault(direction, AccessMode.BOTH);
	}

	@Override
	public void setAccess(Direction direction, AccessMode mode) {
		this.access.put(direction, mode);
	}

	@Override
	public void cycleAccess(Direction direction) {
		setAccess(direction, getAccess(direction).next());
	}

	public void write(ValueOutput output) {
		for (Map.Entry<Direction, AccessMode> entry : this.access.entrySet()) {
			output.putString(entry.getKey().getSerializedName(), entry.getValue().name());
		}
	}

	public void read(ValueInput input) {
		for (Direction direction : Direction.values()) {
			String name = input.getStringOr(direction.getSerializedName(), AccessMode.BOTH.name());
			try {
				setAccess(direction, AccessMode.valueOf(name));
			} catch (IllegalArgumentException ignored) {
				setAccess(direction, AccessMode.BOTH);
			}
		}
	}
}
