package com.leon1236.reforestry.apiculture.genetics;

import java.util.Locale;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.filter.FilterData;
import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;

public enum ApicultureFilterRuleType implements IFilterRuleType {
	BEE(ReForestry.id("analyzer/bee")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return true;
		}
	},
	DRONE(ReForestry.id("analyzer/drone")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return "drone".equals(data.stage());
		}
	},
	PRINCESS(ReForestry.id("analyzer/princess")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return "princess".equals(data.stage());
		}
	},
	QUEEN(ReForestry.id("analyzer/queen")) {
		@Override
		public boolean isValid(ItemStack stack, FilterData data) {
			return "queen".equals(data.stage());
		}
	};

	private final String id;
	private final Identifier sprite;

	ApicultureFilterRuleType(Identifier sprite) {
		this.id = "reforestry.apiculture." + name().toLowerCase(Locale.ENGLISH);
		this.sprite = sprite;
	}

	@Override
	public Identifier getSprite() {
		return this.sprite;
	}

	@Override
	public Identifier getSpeciesTypeId() {
		return ForestrySpeciesTypes.BEE;
	}

	@Override
	public String getId() {
		return this.id;
	}
}
