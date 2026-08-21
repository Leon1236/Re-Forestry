package com.leon1236.reforestry.api.plugin;

import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.lepidopterology.IButterflyCocoon;
import com.leon1236.reforestry.api.lepidopterology.IButterflyEffect;

public interface ILepidopterologyRegistration {
	IButterflySpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int serumColor, float rarity);

	default IButterflySpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, TextColor serumColor, float rarity) {
		return registerSpecies(id, genus, species, dominant, serumColor.getValue(), rarity);
	}

	void registerCocoon(Identifier id, IButterflyCocoon cocoon);

	void registerEffect(Identifier id, IButterflyEffect effect);
}
