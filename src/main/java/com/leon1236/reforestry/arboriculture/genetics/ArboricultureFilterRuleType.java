package com.leon1236.reforestry.arboriculture.genetics;

import java.util.Locale;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.filter.FilterData;
import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;

public enum ArboricultureFilterRuleType implements IFilterRuleType {
	TREE(ReForestry.id("analyzer/tree")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return true;
		}
	},
	SAPLING(ReForestry.id("analyzer/sapling")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return "sapling".equals(data.stage());
		}
	},
	POLLEN(ReForestry.id("analyzer/pollen")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return "pollen".equals(data.stage());
		}
	};

	private final String id;
	private final Identifier sprite;

	ArboricultureFilterRuleType(Identifier sprite) {
		this.sprite = sprite;
		this.id = "reforestry.arboriculture." + name().toLowerCase(Locale.ENGLISH);
	}

	@Override
	public Identifier getSprite() {
		return this.sprite;
	}

	@Override
	public Identifier getSpeciesTypeId() {
		return ForestrySpeciesTypes.TREE;
	}

	@Override
	public String getId() {
		return this.id;
	}
}
