package com.leon1236.reforestry.api.plugin;

import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;

public interface IFilterRegistration {
	void registerFilterRuleType(IFilterRuleType ruleType);

	default void registerFilterRuleTypes(IFilterRuleType[] ruleTypes) {
		for (IFilterRuleType ruleType : ruleTypes) {
			registerFilterRuleType(ruleType);
		}
	}
}
