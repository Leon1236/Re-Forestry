package com.leon1236.reforestry.api.genetics;

import java.util.Collection;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

public interface IGeneticManager {
	ITaxon getTaxon(String name);

	@Nullable
	ITaxon getTaxonSafe(String name);

	ITaxon[] getParentTaxa(String name);

	IMutationManager getMutations(ISpeciesType<?, ?> speciesType);

	default IMutationManager getMutations(Identifier speciesTypeId) {
		return getMutations(getSpeciesType(speciesTypeId));
	}

	ISpeciesType<?, ?> getSpeciesType(Identifier speciesTypeId);

	@Nullable
	ISpeciesType<?, ?> getSpeciesTypeSafe(Identifier speciesTypeId);

	default <T extends ISpeciesType<?, ?>> T getSpeciesType(Identifier speciesTypeId, Class<T> typeClass) {
		return typeClass.cast(getSpeciesType(speciesTypeId));
	}

	default <I extends IIndividual> I createDefaultIndividual(Identifier typeId) {
		ISpeciesType<?, I> type = getSpeciesType(typeId).cast();
		return type.getDefaultSpecies().createIndividual();
	}

	Collection<ISpeciesType<?, ?>> getSpeciesTypes();
}
