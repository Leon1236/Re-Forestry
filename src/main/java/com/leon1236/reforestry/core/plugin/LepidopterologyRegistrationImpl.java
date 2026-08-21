package com.leon1236.reforestry.core.plugin;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.lepidopterology.IButterflyCocoon;
import com.leon1236.reforestry.api.lepidopterology.IButterflyEffect;
import com.leon1236.reforestry.api.plugin.IButterflySpeciesBuilder;
import com.leon1236.reforestry.api.plugin.ILepidopterologyRegistration;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes;
import com.leon1236.reforestry.lepidopterology.genetics.LepidopterologyGenetics;

public final class LepidopterologyRegistrationImpl implements ILepidopterologyRegistration {
	@Override
	public IButterflySpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int serumColor, float rarity) {
		return LepidopterologyGenetics.registerSpecies(id, genus, species, dominant, serumColor, rarity);
	}

	@Override
	public void registerCocoon(Identifier id, IButterflyCocoon cocoon) {
		if (!id.equals(cocoon.id())) {
			throw new IllegalArgumentException("Cocoon id mismatch: registered " + id + " but cocoon reports " + cocoon.id());
		}
		if (ButterflyChromosomes.COCOON.getSafe(id).isEmpty()) {
			ButterflyChromosomes.COCOON.registerValue(id, cocoon);
			AlleleManager.INSTANCE.registryAllele(cocoon, cocoon.isDominant());
		}
	}

	@Override
	public void registerEffect(Identifier id, IButterflyEffect effect) {
		if (!id.equals(effect.id())) {
			throw new IllegalArgumentException("Butterfly effect id mismatch: registered " + id + " but effect reports " + effect.id());
		}
		if (ButterflyChromosomes.EFFECT.getSafe(id).isEmpty()) {
			ButterflyChromosomes.EFFECT.registerValue(id, effect);
			AlleleManager.INSTANCE.registryAllele(effect, effect.isDominant());
		}
	}
}
