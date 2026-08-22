package com.leon1236.reforestry.lepidopterology.genetics;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.alleles.IBooleanAllele;
import com.leon1236.reforestry.api.genetics.filter.FilterData;
import com.leon1236.reforestry.api.genetics.filter.IFilterRule;
import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;
import com.leon1236.reforestry.sorting.DefaultFilterRuleType;

public enum LepidopterologyFilterRule implements IFilterRule {
	PURE_BREED(DefaultFilterRuleType.PURE_BREED) {
		@Override
		protected boolean isValid(FilterData data) {
			return data.genome().getActiveAllele(ButterflyChromosomes.SPECIES)
					.equals(data.genome().getInactiveAllele(ButterflyChromosomes.SPECIES));
		}
	},
	NOCTURNAL(DefaultFilterRuleType.NOCTURNAL) {
		@Override
		protected boolean isValid(FilterData data) {
			return data.genome().getActiveAllele(ButterflyChromosomes.NEVER_SLEEPS).value();
		}
	},
	PURE_NOCTURNAL(DefaultFilterRuleType.PURE_NOCTURNAL) {
		@Override
		protected boolean isValid(FilterData data) {
			IBooleanAllele active = data.genome().getActiveAllele(ButterflyChromosomes.NEVER_SLEEPS);
			IBooleanAllele inactive = data.genome().getInactiveAllele(ButterflyChromosomes.NEVER_SLEEPS);
			return active.value() && active.equals(inactive);
		}
	},
	FLYER(DefaultFilterRuleType.FLYER) {
		@Override
		protected boolean isValid(FilterData data) {
			return data.genome().getActiveAllele(ButterflyChromosomes.TOLERATES_RAIN).value();
		}
	},
	PURE_FLYER(DefaultFilterRuleType.PURE_FLYER) {
		@Override
		protected boolean isValid(FilterData data) {
			IBooleanAllele active = data.genome().getActiveAllele(ButterflyChromosomes.TOLERATES_RAIN);
			IBooleanAllele inactive = data.genome().getInactiveAllele(ButterflyChromosomes.TOLERATES_RAIN);
			return active.value() && active.equals(inactive);
		}
	};

	LepidopterologyFilterRule(IFilterRuleType rule) {
		rule.addLogic(this);
	}

	public static void init() {
	}

	@Override
	public boolean isValid(ItemStack stack, FilterData data) {
		return ForestrySpeciesTypes.BUTTERFLY.equals(data.typeId()) && isValid(data);
	}

	protected boolean isValid(FilterData data) {
		return false;
	}

	@Override
	public Identifier getSpeciesTypeId() {
		return ForestrySpeciesTypes.BUTTERFLY;
	}
}
