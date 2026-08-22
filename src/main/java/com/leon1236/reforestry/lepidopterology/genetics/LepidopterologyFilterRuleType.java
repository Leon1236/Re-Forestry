package com.leon1236.reforestry.lepidopterology.genetics;

import java.util.Locale;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.filter.FilterData;
import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;

public enum LepidopterologyFilterRuleType implements IFilterRuleType {
	FLUTTER(ReForestry.id("analyzer/flutter")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return true;
		}
	},
	BUTTERFLY(ReForestry.id("analyzer/butterfly")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return "butterfly".equals(data.stage());
		}
	},
	SERUM(ReForestry.id("analyzer/serum")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return "serum".equals(data.stage());
		}
	},
	CATERPILLAR(ReForestry.id("analyzer/caterpillar")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return "caterpillar".equals(data.stage());
		}
	},
	COCOON(ReForestry.id("analyzer/cocoon")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return "cocoon".equals(data.stage());
		}
	};

	private final String id;
	private final Identifier sprite;

	LepidopterologyFilterRuleType(Identifier sprite) {
		this.sprite = sprite;
		this.id = "reforestry.lepidopterology." + name().toLowerCase(Locale.ENGLISH);
	}

	@Override
	public Identifier getSprite() {
		return this.sprite;
	}

	@Override
	public Identifier getSpeciesTypeId() {
		return ForestrySpeciesTypes.BUTTERFLY;
	}

	@Override
	public String getId() {
		return this.id;
	}
}
