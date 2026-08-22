package com.leon1236.reforestry.api.genetics.filter;

import net.minecraft.resources.Identifier;

public interface IFilterRuleType extends IFilterRule {
	default void addLogic(IFilterRule logic) {
	}

	default boolean isContainer() {
		return false;
	}

	String getId();

	Identifier getSprite();
}
