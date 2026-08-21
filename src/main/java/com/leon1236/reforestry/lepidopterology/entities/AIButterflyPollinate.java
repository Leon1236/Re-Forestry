package com.leon1236.reforestry.lepidopterology.entities;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.genetics.pollen.IPollen;
import com.leon1236.reforestry.api.genetics.pollen.IPollenManager;

public class AIButterflyPollinate extends AIButterflyInteract {
	public AIButterflyPollinate(EntityButterfly entity) {
		super(entity);
	}

	@Override
	protected boolean canInteract() {
		return this.rest != null && IForestryApi.INSTANCE.getPollenManager().canPollinate(this.entity.level(), this.rest);
	}

	@Override
	public void tick() {
		if (canContinueToUse() && this.rest != null) {
			IPollenManager pollens = IForestryApi.INSTANCE.getPollenManager();
			IPollen butterflyPollen = this.entity.getPollen();

			if (butterflyPollen == null) {
				this.entity.setPollen(pollens.getPollen(this.entity.level(), this.rest));
				this.entity.changeExhaustion(-this.entity.getExhaustion());
			} else if (butterflyPollen.tryPollinate(this.entity.level(), this.rest)) {
				this.entity.setPollen(null);
			}
			setHasInteracted();
			this.entity.cooldownPollination = EntityButterfly.COOLDOWNS;
		}
	}
}
