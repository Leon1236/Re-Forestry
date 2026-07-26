package com.leon1236.reforestry.api.genetics.alyzer;

import java.util.function.Predicate;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.IGeneticTooltipProvider;

public interface IAlleleDisplayHelper {
	void addTooltip(IGeneticTooltipProvider provider, Identifier speciesTypeId, int ordering);

	void addTooltip(IGeneticTooltipProvider provider, Identifier speciesTypeId, int ordering, Predicate<String> lifeStageFilter);

	void addAlyzer(IGeneticTooltipProvider provider, Identifier speciesTypeId, int ordering);
}
