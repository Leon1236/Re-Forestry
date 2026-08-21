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
import com.leon1236.reforestry.gendustry.recipe.MutagenRecipe;
import com.leon1236.reforestry.gendustry.recipe.cache.MutagenRecipeCache;

public class MutagenProducerBlockEntity extends ProducerBlockEntity<MutagenRecipe> {
	private static final int ENERGY_PER_WORK_CYCLE = 100000;
	private static final int TICKS_PER_WORK_CYCLE = 200;

	public static final String HINTS_KEY = "gendustry.mutagen_producer";

	public MutagenProducerBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.MUTAGEN_PRODUCER.type(), GFluids.MUTAGEN, false, pos, state);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, MutagenProducerBlockEntity tile) {
		ProducerBlockEntity.serverTick(level, pos, state, tile);
	}

	@Override
	public boolean isValidInput(ItemStack input) {
		return MutagenRecipeCache.INSTANCE.getRecipe(input) != null;
	}

	@Nullable
	@Override
	public MutagenRecipe getRecipe(ItemStack input) {
		return MutagenRecipeCache.INSTANCE.getRecipe(input);
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
