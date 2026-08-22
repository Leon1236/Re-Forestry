package com.leon1236.reforestry.api.genetics.filter;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableCollection;

public interface IFilterManager {
	ImmutableCollection<IFilterRuleType> getRules();

	IFilterRuleType getDefaultRule();

	@Nullable
	IFilterRuleType getRule(String uid);

	@Nullable
	IFilterRuleType getRule(int id);

	int getId(IFilterRuleType rule);

	default IFilterRuleType getRuleOrDefault(String uid) {
		IFilterRuleType rule = getRule(uid);
		return rule != null ? rule : getDefaultRule();
	}

	default IFilterRuleType getRuleOrDefault(int id) {
		IFilterRuleType rule = getRule(id);
		return rule != null ? rule : getDefaultRule();
	}
}
