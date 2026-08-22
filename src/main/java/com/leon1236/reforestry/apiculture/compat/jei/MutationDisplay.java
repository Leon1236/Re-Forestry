package com.leon1236.reforestry.apiculture.compat.jei;

import java.util.List;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;

public final class MutationDisplay {
	final Mutation mutation;
	final ItemStack firstParent;
	final ItemStack secondParent;
	final ItemStack result;
	final List<ItemStack> firstInputs;
	final List<ItemStack> secondInputs;
	final List<ItemStack> resultOutputs;

	MutationDisplay(Mutation mutation) {
		this.mutation = mutation;
		this.firstParent = BeeJeiHelper.analyzedStack(ApicultureItems.BEE_DRONE, mutation.firstParent());
		this.secondParent = BeeJeiHelper.analyzedStack(ApicultureItems.BEE_PRINCESS, mutation.secondParent());
		this.result = BeeJeiHelper.analyzedStack(ApicultureItems.BEE_QUEEN, mutation.result());
		this.firstInputs = BeeJeiHelper.allLifeStages(mutation.firstParent());
		this.secondInputs = BeeJeiHelper.allLifeStages(mutation.secondParent());
		this.resultOutputs = BeeJeiHelper.allLifeStages(mutation.result());
	}
}
