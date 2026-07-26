package com.leon1236.reforestry.core.genetics.alyzer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.IGeneticTooltipProvider;
import com.leon1236.reforestry.api.genetics.alyzer.IAlleleDisplayHelper;

public final class AlleleDisplayHelper implements IAlleleDisplayHelper {
	public static final AlleleDisplayHelper INSTANCE = new AlleleDisplayHelper();

	private final Map<Identifier, List<Entry>> tooltips = new HashMap<>();
	private final Map<Identifier, List<Entry>> alyzer = new HashMap<>();

	private AlleleDisplayHelper() {
	}

	@Override
	public void addTooltip(IGeneticTooltipProvider provider, Identifier speciesTypeId, int ordering) {
		addTooltip(provider, speciesTypeId, ordering, stage -> true);
	}

	@Override
	public void addTooltip(IGeneticTooltipProvider provider, Identifier speciesTypeId, int ordering, Predicate<String> lifeStageFilter) {
		tooltips.computeIfAbsent(speciesTypeId, id -> new ArrayList<>())
				.add(new Entry(provider, ordering, lifeStageFilter));
	}

	@Override
	public void addAlyzer(IGeneticTooltipProvider provider, Identifier speciesTypeId, int ordering) {
		alyzer.computeIfAbsent(speciesTypeId, id -> new ArrayList<>())
				.add(new Entry(provider, ordering, stage -> true));
	}

	public List<IGeneticTooltipProvider> getTooltipProviders(Identifier speciesTypeId, String lifeStage) {
		return providersFor(tooltips.get(speciesTypeId), lifeStage);
	}

	public List<IGeneticTooltipProvider> getAlyzerProviders(Identifier speciesTypeId) {
		return providersFor(alyzer.get(speciesTypeId), "");
	}

	private static List<IGeneticTooltipProvider> providersFor(@Nullable List<Entry> entries, String lifeStage) {
		if (entries == null || entries.isEmpty()) {
			return List.of();
		}
		return entries.stream()
				.filter(entry -> lifeStage.isEmpty() || entry.lifeStageFilter().test(lifeStage))
				.sorted(Comparator.comparingInt(Entry::ordering))
				.map(Entry::provider)
				.toList();
	}

	private record Entry(IGeneticTooltipProvider provider, int ordering, Predicate<String> lifeStageFilter) {
	}
}
