package com.leon1236.reforestry.api.lepidopterology.genetics;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.lepidopterology.IButterflyCocoon;
import com.leon1236.reforestry.api.lepidopterology.IButterflyEffect;

public interface IButterflySpeciesType extends ISpeciesType<IButterflySpecies, IButterfly> {
	IButterflyCocoon getCocoon(Identifier id);

	@Nullable
	IButterflyCocoon getCocoonSafe(Identifier id);

	IButterflyEffect getButterflyEffect(Identifier id);

	@Nullable
	IButterflyEffect getButterflyEffectSafe(Identifier id);

	@Nullable
	PathfinderMob spawnButterflyInWorld(Level level, IButterfly butterfly, double x, double y, double z);

	@Nullable
	BlockPos plantCocoon(LevelAccessor level, BlockPos pos, IButterfly caterpillar, int age, boolean createNursery);

	boolean isMated(ItemStack stack);
}
