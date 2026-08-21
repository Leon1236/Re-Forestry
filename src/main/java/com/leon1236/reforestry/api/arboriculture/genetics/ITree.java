package com.leon1236.reforestry.api.arboriculture.genetics;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import com.leon1236.reforestry.api.arboriculture.ITreeSpecies;
import com.leon1236.reforestry.api.core.IProductProducer;
import com.leon1236.reforestry.api.core.ISpecialtyProducer;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IIndividual;

public interface ITree extends IIndividual, IProductProducer, ISpecialtyProducer {
	IEffectData[] doEffect(IEffectData[] storedData, Level level, BlockPos pos);

	IEffectData[] doFX(IEffectData[] storedData, Level level, BlockPos pos);

	List<ITree> getSaplings(Level level, BlockPos pos, @Nullable GameProfile playerProfile, float modifier);

	List<ItemStack> produceStacks(Level level, BlockPos pos, int ripeningTime);

	boolean canStay(BlockGetter level, BlockPos pos);

	boolean hasFruitLeaves();

	int getRequiredMaturity();

	int getResilience();

	Feature<NoneFeatureConfiguration> getTreeGenerator(WorldGenLevel level, BlockPos pos, boolean wasBonemealed);

	@Override
	ITree copy();

	@Override
	ITreeSpecies getSpecies();

	@Override
	ITreeSpecies getInactiveSpecies();
}
