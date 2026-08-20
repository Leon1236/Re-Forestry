package com.leon1236.reforestry.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import com.leon1236.reforestry.api.genetics.filter.IFilterManager;
import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;

public class FilterManager implements IFilterManager {
	private static final Comparator<IFilterRuleType> FILTER_COMPARATOR = (f, s) -> f.getId().compareToIgnoreCase(s.getId());

	private final ImmutableMap<String, IFilterRuleType> filterRules;
	private final Object2IntOpenHashMap<String> filterIdByName;
	private final Int2ObjectOpenHashMap<IFilterRuleType> filterById;

	public FilterManager(List<IFilterRuleType> registeredRuleTypes) {
		List<IFilterRuleType> sorted = new ArrayList<>(registeredRuleTypes);
		sorted.sort(FILTER_COMPARATOR);

		int size = sorted.size();
		ImmutableMap.Builder<String, IFilterRuleType> filterRules = ImmutableMap.builderWithExpectedSize(size);
		Object2IntOpenHashMap<String> filterIdByName = new Object2IntOpenHashMap<>(size);
		Int2ObjectOpenHashMap<IFilterRuleType> filterById = new Int2ObjectOpenHashMap<>(size);

		for (int i = 0; i < size; i++) {
			IFilterRuleType ruleType = sorted.get(i);
			filterRules.put(ruleType.getId(), ruleType);
			filterIdByName.put(ruleType.getId(), i);
			filterById.put(i, ruleType);
		}

		this.filterRules = filterRules.build();
		this.filterIdByName = filterIdByName;
		this.filterById = filterById;
	}

	@Override
	public ImmutableCollection<IFilterRuleType> getRules() {
		return this.filterRules.values();
	}

	@Override
	public IFilterRuleType getDefaultRule() {
		return DefaultFilterRuleType.CLOSED;
	}

	@Nullable
	@Override
	public IFilterRuleType getRule(String uid) {
		return this.filterRules.get(uid);
	}

	@Override
	public int getId(IFilterRuleType rule) {
		return this.filterIdByName.getInt(rule.getId());
	}

	@Nullable
	@Override
	public IFilterRuleType getRule(int id) {
		return this.filterById.get(id);
	}
}
