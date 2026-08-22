package com.leon1236.reforestry.sorting;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.filter.FilterData;
import com.leon1236.reforestry.api.genetics.filter.IFilterRule;
import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;

public enum DefaultFilterRuleType implements IFilterRuleType {
	CLOSED(false, ReForestry.id("analyzer/closed")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return false;
		}
	},
	ANYTHING(false, ReForestry.id("analyzer/anything")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return true;
		}
	},
	ITEM(false, ReForestry.id("analyzer/item")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return true;
		}
	},
	PURE_BREED(ReForestry.id("analyzer/pure_breed")),
	NOCTURNAL(ReForestry.id("analyzer/nocturnal")),
	PURE_NOCTURNAL(ReForestry.id("analyzer/pure_nocturnal")),
	FLYER(ReForestry.id("analyzer/flyer")),
	PURE_FLYER(ReForestry.id("analyzer/pure_flyer")),
	CAVE(ReForestry.id("analyzer/cave")),
	PURE_CAVE(ReForestry.id("analyzer/pure_cave")),
	;

	private final String id;
	private final Set<IFilterRule> logic;
	private final boolean isContainer;
	private final Identifier sprite;

	DefaultFilterRuleType(Identifier sprite) {
		this(true, sprite);
	}

	DefaultFilterRuleType(boolean isContainer, Identifier sprite) {
		this.sprite = sprite;
		this.id = "reforestry.default." + name().toLowerCase(Locale.ENGLISH);
		this.logic = new HashSet<>();
		this.isContainer = isContainer;
	}

	@Override
	public boolean isValid(ItemStack stack, FilterData data) {
		for (IFilterRule logic : this.logic) {
			if (logic.isValid(stack, data)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addLogic(IFilterRule logic) {
		if (logic == this) {
			throw new IllegalArgumentException();
		}
		this.logic.add(logic);
	}

	@Override
	public boolean isContainer() {
		return this.isContainer;
	}

	@Override
	public Identifier getSprite() {
		return this.sprite;
	}

	@Override
	public String getId() {
		return this.id;
	}
}
