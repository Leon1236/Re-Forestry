package com.leon1236.reforestry.api.lepidopterology.genetics;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IIndividualLiving;
import com.leon1236.reforestry.api.lepidopterology.IButterflyCocoon;
import com.leon1236.reforestry.api.lepidopterology.IButterflyNursery;
import com.leon1236.reforestry.api.lepidopterology.IEntityButterfly;

public interface IButterfly extends IIndividualLiving {
	boolean canSpawn(Level level, double x, double y, double z);

	boolean canTakeFlight(Level level, double x, double y, double z);

	default boolean isAcceptedEnvironment(Level level, double x, double y, double z) {
		return isAcceptedEnvironment(level, BlockPos.containing(x, y, z));
	}

	boolean isAcceptedEnvironment(Level world, BlockPos pos);

	@Nullable
	IButterfly spawnCaterpillar(IButterflyNursery nursery);

	List<ItemStack> getLootDrop(IEntityButterfly entity, boolean playerKill, int lootLevel);

	List<ItemStack> getCaterpillarDrop(IButterflyNursery nursery, boolean playerKill, int lootLevel);

	List<ItemStack> getCocoonDrop(boolean includeButterfly, IButterflyCocoon cocoon);

	@Override
	IButterfly copy();

	@Override
	IButterflySpeciesType getType();

	@Override
	IButterflySpecies getSpecies();

	@Override
	IButterflySpecies getInactiveSpecies();

	Component getDisplayName();
}
