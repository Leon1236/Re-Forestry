package com.leon1236.reforestry.lepidopterology.entities;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.phys.Vec3;

public abstract class AIButterflyMovement extends AIButterflyBase {
	@Nullable
	protected Vec3 flightTarget;

	protected AIButterflyMovement(EntityButterfly entity) {
		super(entity);
	}

	@Override
	public boolean canContinueToUse() {
		if (this.entity.getState() != EnumButterflyState.FLYING) {
			return false;
		}
		if (this.flightTarget == null) {
			return false;
		}
		if (this.entity.getDestination() == null || !this.entity.getDestination().equals(this.flightTarget)) {
			return false;
		}
		if (this.entity.getDestination().distanceToSqr(this.entity.position()) > 2.0f) {
			return true;
		}

		this.entity.setDestination(null);
		return false;
	}

	@Override
	public void tick() {
		if (this.entity.isInWater()) {
			this.flightTarget = getRandomDestinationUpwards();
		} else if (this.entity.horizontalCollision || this.entity.verticalCollision) {
			this.flightTarget = this.entity.getRandom().nextBoolean() ? getRandomDestination() : null;
		} else if (this.entity.level().getRandom().nextInt(300) == 0) {
			this.flightTarget = getRandomDestination();
		}
		this.entity.setDestination(this.flightTarget);
		this.entity.changeExhaustion(1);
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
		this.flightTarget = null;
	}
}
