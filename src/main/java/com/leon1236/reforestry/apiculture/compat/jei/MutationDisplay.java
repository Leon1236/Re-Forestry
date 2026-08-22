package com.leon1236.reforestry.apiculture.compat.jei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.core.compat.jei.GeneticsJeiHelper;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;

public final class MutationDisplay {
	final Mutation mutation;
	final ISpeciesType<?, ?> speciesType;
	final ItemStack firstParent;
	final ItemStack secondParent;
	final ItemStack result;
	final List<ItemStack> firstInputs;
	final List<ItemStack> secondInputs;
	final List<ItemStack> resultOutputs;

	public MutationDisplay(Mutation mutation, ISpeciesType<?, ?> speciesType) {
		this.mutation = mutation;
		this.speciesType = speciesType;
		ILifeStage firstStage = speciesType.getTypeForMutation(0);
		ILifeStage secondStage = speciesType.getTypeForMutation(1);
		ILifeStage resultStage = speciesType.getTypeForMutation(2);
		IGenome resultGenome = GeneticsJeiHelper.mutatedGenome(mutation, speciesType);
		this.firstParent = GeneticsJeiHelper.createAnalyzedStack(speciesType, firstStage, mutation.firstParent(), null);
		this.secondParent = GeneticsJeiHelper.createAnalyzedStack(speciesType, secondStage, mutation.secondParent(), null);
		this.result = GeneticsJeiHelper.createAnalyzedStack(speciesType, resultStage, mutation.result(), resultGenome);
		this.firstInputs = new ArrayList<>();
		this.secondInputs = new ArrayList<>();
		this.resultOutputs = new ArrayList<>();
		for (ILifeStage stage : speciesType.getLifeStages()) {
			firstInputs.add(GeneticsJeiHelper.createAnalyzedStack(speciesType, stage, mutation.firstParent(), null));
			secondInputs.add(GeneticsJeiHelper.createAnalyzedStack(speciesType, stage, mutation.secondParent(), null));
			resultOutputs.add(GeneticsJeiHelper.createAnalyzedStack(speciesType, stage, mutation.result(), resultGenome));
		}
	}

	Identifier firstParentId() {
		return mutation.firstParent();
	}

	Identifier secondParentId() {
		return mutation.secondParent();
	}

	Identifier resultId() {
		return mutation.result();
	}
}
