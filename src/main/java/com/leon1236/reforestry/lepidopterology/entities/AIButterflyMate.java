package com.leon1236.reforestry.lepidopterology.entities;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.api.lepidopterology.IButterflyNursery;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes;

public class AIButterflyMate extends AIButterflyInteract {
	@Nullable
	private EntityButterfly targetMate;

	public AIButterflyMate(EntityButterfly entity) {
		super(entity);
	}

	@Override
	protected boolean canInteract() {
		if (this.entity.getButterfly().getMate() == null && this.entity.canMate()) {
			return true;
		}
		if (this.entity.cooldownEgg > 0) {
			return false;
		}

		if (this.entity.getButterfly().getMate() == null) {
			return false;
		}

		if (EntityButterfly.isMaxButterflyCluster(this.entity.position(), this.entity.level())) {
			return false;
		}

		return this.rest != null && ButterflyNurseryHelper.canNurse(this.entity.getButterfly(), this.entity.level(), this.rest);
	}

	@Override
	public void tick() {
		if (canContinueToUse()) {
			if (this.entity.getButterfly().getMate() == null && this.targetMate != null) {
				if (this.entity.cooldownMate <= 0 && this.entity.distanceTo(this.targetMate) < 9.0D) {
					this.entity.getButterfly().setMate(this.targetMate.getButterfly().getGenome());
					this.targetMate.getButterfly().setMate(this.entity.getButterfly().getGenome());
					this.entity.cooldownMate = EntityButterfly.COOLDOWNS;
				}
			} else if (this.rest != null) {
				IButterflyNursery nursery = ButterflyNurseryHelper.getOrCreateNursery(this.entity.level(), this.rest, false);
				if (nursery != null && nursery.canNurse(this.entity.getButterfly())) {
					nursery.setCaterpillar(this.entity.getButterfly().spawnCaterpillar(nursery));
					if (this.entity.getRandom().nextFloat() < 1.0f / this.entity.getButterfly().getGenome()
							.getActiveAllele(ButterflyChromosomes.FERTILITY).value()) {
						this.entity.setHealth(0);
					}
				}
				setHasInteracted();
				this.entity.cooldownEgg = EntityButterfly.COOLDOWNS;
			}
		}
	}

	@Override
	public boolean canUse() {
		if (!super.canUse()) {
			return false;
		}
		if (this.entity.getButterfly().getMate() == null) {
			if (!this.entity.canMate()) {
				return false;
			}
			this.targetMate = getNearbyMate();
			return this.targetMate != null;
		}
		return true;
	}

	@Override
	public boolean canContinueToUse() {
		if (!super.canContinueToUse()) {
			return false;
		}
		if (this.entity.getButterfly().getMate() == null) {
			return this.targetMate != null && this.targetMate.isAlive() && this.targetMate.canMate();
		}
		return true;
	}

	@Override
	public void stop() {
		super.stop();
		this.targetMate = null;
	}

	@Nullable
	private EntityButterfly getNearbyMate() {
		float f = 8.0F;
		List<EntityButterfly> nextButterflies = this.entity.level().getEntitiesOfClass(EntityButterfly.class,
				this.entity.getBoundingBox().expandTowards(f, f, f));
		double d0 = Double.MAX_VALUE;
		EntityButterfly nextButterfly = null;

		for (EntityButterfly butterfly : nextButterflies) {
			if (this.entity.canMateWith(butterfly) && this.entity.distanceTo(butterfly) < d0) {
				nextButterfly = butterfly;
				d0 = this.entity.distanceTo(butterfly);
			}
		}

		return nextButterfly;
	}
}
