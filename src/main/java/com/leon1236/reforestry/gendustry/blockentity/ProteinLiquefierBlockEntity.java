package com.leon1236.reforestry.gendustry.blockentity;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.gendustry.features.GBlockEntities;
import com.leon1236.reforestry.gendustry.fluids.GFluids;
import com.leon1236.reforestry.gendustry.recipe.ProteinRecipe;
import com.leon1236.reforestry.gendustry.recipe.cache.ProteinRecipeCache;

public class ProteinLiquefierBlockEntity extends ProducerBlockEntity<ProteinRecipe> {
	private static final int ENERGY_PER_WORK_CYCLE = 20000;
	private static final int TICKS_PER_WORK_CYCLE = 100;

	public static final String HINTS_KEY = "gendustry.protein_liquefier";

	public ProteinLiquefierBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.PROTEIN_LIQUEFIER.type(), GFluids.PROTEIN, false, pos, state);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, ProteinLiquefierBlockEntity tile) {
		ProducerBlockEntity.serverTick(level, pos, state, tile);
	}

	@Override
	public boolean isValidInput(ItemStack input) {
		return ProteinRecipeCache.INSTANCE.getRecipe(input) != null;
	}

	@Nullable
	@Override
	public ProteinRecipe getRecipe(ItemStack input) {
		return ProteinRecipeCache.INSTANCE.getRecipe(input);
	}

	@Override
	public void startWorking() {
		setTicksPerWorkCycle(TICKS_PER_WORK_CYCLE);
		setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
	}

	@Override
	public IError getNoInputError() {
		return ForestryError.NO_RECIPE;
	}

	@Override
	public String getHintsKey() {
		return HINTS_KEY;
	}
}
