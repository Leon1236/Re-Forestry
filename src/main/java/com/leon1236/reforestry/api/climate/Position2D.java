package com.leon1236.reforestry.api.climate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;

public final class Position2D implements Comparable<Position2D> {
	public static final Position2D NULL_POSITION = new Position2D(0, 0);

	private final int x;
	private final int z;

	public Position2D(Vec3i pos) {
		this(pos.getX(), pos.getZ());
	}

	public Position2D(BlockPos pos) {
		this(pos.getX(), pos.getZ());
	}

	public Position2D(Position2D pos) {
		this(pos.getX(), pos.getZ());
	}

	public Position2D(int xIn, int zIn) {
		this.x = xIn;
		this.z = zIn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o instanceof Vec3i position) {
			return this.getX() == position.getX() && this.getZ() == position.getZ();
		} else if (!(o instanceof Position2D position)) {
			return false;
		} else {
			return this.getX() == position.getX() && this.getZ() == position.getZ();
		}
	}

	@Override
	public int hashCode() {
		return this.getZ() + this.getX() * 31;
	}

	@Override
	public int compareTo(Position2D o) {
		return this.getZ() == o.getZ() ? this.getX() - o.getX() : this.getZ() - o.getZ();
	}

	public int getX() {
		return this.x;
	}

	public int getZ() {
		return this.z;
	}

	public double getDistance(int xIn, int zIn) {
		double d0 = (double) (this.getX() - xIn);
		double d2 = (double) (this.getZ() - zIn);
		return Math.sqrt(d0 * d0 + d2 * d2);
	}

	public double getDistance(Position2D pos) {
		return getDistance(pos.getX(), pos.getZ());
	}

	public double getDistance(BlockPos pos) {
		return getDistance(pos.getX(), pos.getZ());
	}
}
