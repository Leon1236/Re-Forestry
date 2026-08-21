package com.leon1236.reforestry.apiculture.genetics;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.IActivityType;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.filter.FilterData;
import com.leon1236.reforestry.api.genetics.filter.IFilterRule;
import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;
import com.leon1236.reforestry.core.genetics.GeneticItemHelper;
import com.leon1236.reforestry.sorting.DefaultFilterRuleType;

public enum ApicultureFilterRule implements IFilterRule {
	PURE_BREED(DefaultFilterRuleType.PURE_BREED) {
		@Override
		protected boolean isValid(FilterData data) {
			Identifier active = GeneticItemHelper.speciesId(data.genome(), data.typeId(), true);
			Identifier inactive = GeneticItemHelper.speciesId(data.genome(), data.typeId(), false);
			return active != null && active.equals(inactive);
		}
	},
	NOCTURNAL(DefaultFilterRuleType.NOCTURNAL) {
		@Override
		protected boolean isValid(FilterData data) {
			return data.genome().getActiveAllele(BeeChromosomes.ACTIVITY).value().equals(ActivityType.METATURNAL);
		}
	},
	PURE_NOCTURNAL(DefaultFilterRuleType.PURE_NOCTURNAL) {
		@Override
		protected boolean isValid(FilterData data) {
			IActivityType activity = data.genome().getActiveAllele(BeeChromosomes.ACTIVITY).value();
			return activity.isActive(0, IActivityType.NIGHT_TIME, BlockPos.ZERO);
		}
	},
	FLYER(DefaultFilterRuleType.FLYER) {
		@Override
		protected boolean isValid(FilterData data) {
			return data.genome().getActiveAllele(BeeChromosomes.TOLERATES_RAIN).value();
		}
	},
	PURE_FLYER(DefaultFilterRuleType.PURE_FLYER) {
		@Override
		protected boolean isValid(FilterData data) {
			return data.genome().getActiveAllele(BeeChromosomes.TOLERATES_RAIN).value()
					&& data.genome().getInactiveAllele(BeeChromosomes.TOLERATES_RAIN).value();
		}
	},
	CAVE(DefaultFilterRuleType.CAVE) {
		@Override
		protected boolean isValid(FilterData data) {
			return data.genome().getActiveAllele(BeeChromosomes.CAVE_DWELLING).value();
		}
	},
	PURE_CAVE(DefaultFilterRuleType.PURE_CAVE) {
		@Override
		protected boolean isValid(FilterData data) {
			return data.genome().getActiveAllele(BeeChromosomes.CAVE_DWELLING).value()
					&& data.genome().getInactiveAllele(BeeChromosomes.CAVE_DWELLING).value();
		}
	};

	ApicultureFilterRule(IFilterRuleType rule) {
		rule.addLogic(this);
	}

	public static void init() {
	}

	@Override
	public boolean isValid(ItemStack stack, FilterData data) {
		return ForestrySpeciesTypes.BEE.equals(data.typeId()) && isValid(data);
	}

	protected boolean isValid(FilterData data) {
		return false;
	}

	@Override
	public Identifier getSpeciesTypeId() {
		return ForestrySpeciesTypes.BEE;
	}
}
