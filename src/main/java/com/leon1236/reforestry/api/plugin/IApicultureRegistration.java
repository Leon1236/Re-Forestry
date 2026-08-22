package com.leon1236.reforestry.api.plugin;

import java.util.Map;
import java.util.function.Consumer;

import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import com.leon1236.reforestry.api.apiculture.IActivityType;
import com.leon1236.reforestry.api.apiculture.IBeeJubilance;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.api.apiculture.hives.IHiveDefinition;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public interface IApicultureRegistration {
	IBeeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int outlineColor);

	default IBeeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, TextColor outline) {
		return registerSpecies(id, genus, species, dominant, outline.getValue());
	}

	void modifySpecies(Identifier id, Consumer<IBeeSpeciesBuilder> action);

	IHiveBuilder registerHive(Identifier id, IHiveDefinition definition);

	void modifyHive(Identifier id, Consumer<IHiveBuilder> action);

	void registerBeeEffect(Identifier id, IBeeEffect effect);

	void registerBeeJubilance(Identifier id, IBeeJubilance jubilance);

	void registerActivityType(Identifier id, IActivityType type);

	void registerSwarmerMaterial(Item swarmItem, float swarmChance);

	void addVillageBee(Identifier speciesId, boolean rare, Map<IChromosome<?>, IAllele> alleles);

	default void addVillageBee(Identifier speciesId, boolean rare) {
		addVillageBee(speciesId, rare, Map.of());
	}
}
