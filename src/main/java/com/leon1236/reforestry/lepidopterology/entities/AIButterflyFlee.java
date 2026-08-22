package com.leon1236.reforestry.lepidopterology.entities;

import java.util.EnumSet;

import net.minecraft.world.entity.player.Player;

import com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes;

public class AIButterflyFlee extends AIButterflyMovement {
	public AIButterflyFlee(EntityButterfly entity) {
		super(entity);
		setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
	}

	@Override
	public boolean canUse() {
		float flightDistance = this.entity.getButterfly().getGenome()
				.getActiveAllele(ButterflyChromosomes.SPECIES).value().getFlightDistance();
		Player player = this.entity.level().getNearestPlayer(this.entity, flightDistance);

		if (player == null || player.isShiftKeyDown()) {
			return false;
		}

		if (!this.entity.getSensing().hasLineOfSight(player)) {
			return false;
		}

		this.flightTarget = getRandomDestination();
		if (this.flightTarget == null) {
			return false;
		}

		if (player.distanceToSqr(this.flightTarget.x, this.flightTarget.y, this.flightTarget.z) < player.distanceTo(this.entity)) {
			return false;
		}

		this.entity.setDestination(this.flightTarget);
		this.entity.setState(EnumButterflyState.FLYING);
		return true;
	}
}
