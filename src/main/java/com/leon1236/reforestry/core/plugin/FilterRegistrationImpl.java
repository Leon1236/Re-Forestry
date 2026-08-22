package com.leon1236.reforestry.core.plugin;

import java.util.ArrayList;

import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;
import com.leon1236.reforestry.api.plugin.IFilterRegistration;

public final class FilterRegistrationImpl implements IFilterRegistration {
	private final ArrayList<IFilterRuleType> ruleTypes = new ArrayList<>();

	@Override
	public void registerFilterRuleType(IFilterRuleType ruleType) {
		this.ruleTypes.add(ruleType);
	}

	public ArrayList<IFilterRuleType> getFilterRuleTypes() {
		return this.ruleTypes;
	}
}
