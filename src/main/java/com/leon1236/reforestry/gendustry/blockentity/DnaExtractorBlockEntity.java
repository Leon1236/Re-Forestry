package com.leon1236.reforestry.gendustry.blockentity;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.genetics.capability.IIndividualHandlerItem;
import com.leon1236.reforestry.gendustry.features.GBlockEntities;
import com.leon1236.reforestry.gendustry.fluids.GFluids;
import com.leon1236.reforestry.gendustry.recipe.DnaRecipe;
import com.leon1236.reforestry.gendustry.recipe.cache.DnaRecipeCache;

public class DnaExtractorBlockEntity extends ProducerBlockEntity<DnaRecipe> {
	private static final int ENERGY_PER_WORK_CYCLE = 80000;
	private static final int TICKS_PER_WORK_CYCLE = 50;

	public static final String HINTS_KEY = "gendustry.dna_extractor";

	public DnaExtractorBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.DNA_EXTRACTOR.type(), GFluids.LIQUID_DNA, true, pos, state);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, DnaExtractorBlockEntity tile) {
		ProducerBlockEntity.serverTick(level, pos, state, tile);
	}

	@Override
	public boolean isValidInput(ItemStack input) {
		return IIndividualHandlerItem.isIndividual(input);
	}

	@Nullable
	@Override
	public DnaRecipe getRecipe(ItemStack input) {
		ILifeStage stage = IIndividualHandlerItem.getLifeStage(input);
		return stage == null ? null : DnaRecipeCache.INSTANCE.getRecipe(stage);
	}

	@Override
	public void startWorking() {
		setTicksPerWorkCycle(TICKS_PER_WORK_CYCLE);
		setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
	}

	@Override
	public IError getNoInputError() {
		return ForestryError.NO_SPECIMEN;
	}

	@Override
	public String getHintsKey() {
		return HINTS_KEY;
	}
}
