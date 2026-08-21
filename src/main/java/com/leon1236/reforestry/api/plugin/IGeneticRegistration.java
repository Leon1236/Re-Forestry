package com.leon1236.reforestry.api.plugin;

import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.apiculture.IFlowerType;

public interface IGeneticRegistration {
	void defineTaxon(String parent, String name);

	void defineTaxon(String parent, String name, Consumer<ITaxonBuilder> action);

	void registerFlowerType(Identifier id, IFlowerType type);

	ISpeciesTypeBuilder registerSpeciesType(Identifier id, ISpeciesTypeFactory typeFactory);

	void modifySpeciesType(Identifier id, Consumer<ISpeciesTypeBuilder> action);
}
