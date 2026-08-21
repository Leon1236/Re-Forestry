package com.leon1236.reforestry.api.plugin;

import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.ISpeciesType;

public interface ISpeciesBuilder<T extends ISpeciesType<S, ?>, S extends ISpecies<?>, B extends ISpeciesBuilder<T, S, B>> {
	B setDominant(boolean dominant);

	B setGenome(Consumer<IGenomeBuilder> genome);

	B setGlint(boolean glint);

	B setTemperature(TemperatureType temperature);

	B setHumidity(HumidityType humidity);

	B setComplexity(int complexity);

	B setEscritoireColor(int color);

	B setSecret(boolean secret);

	B setAuthority(String authority);

	B setFactory(ISpeciesFactory<T, S, B> factory);

	String getGenus();

	String getSpecies();

	boolean isDominant();

	IGenome buildGenome(IGenomeBuilder builder);

	boolean hasGlint();

	TemperatureType getTemperature();

	HumidityType getHumidity();

	int getComplexity();

	int getEscritoireColor();

	boolean isSecret();

	String getAuthority();

	ISpeciesFactory<T, S, B> createSpeciesFactory();

	@FunctionalInterface
	interface ISpeciesFactory<T extends ISpeciesType<S, ?>, S extends ISpecies<?>, B extends ISpeciesBuilder<T, S, B>> {
		S create(Identifier id, T speciesType, IGenome defaultGenome, B builder);
	}
}
