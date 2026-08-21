package com.leon1236.reforestry.api.apiculture.genetics;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IIndividualLiving;
import com.leon1236.reforestry.api.genetics.pollen.IPollen;

public interface IBee extends IIndividualLiving {
	boolean isPristine();

	int getGeneration();

	void setPristine(boolean flag);

	IEffectData[] doEffect(IEffectData[] storedData, IBeeHousing housing);

	IEffectData[] doFX(IEffectData[] storedData, IBeeHousing housing);

	Set<IError> getCanWork(IBeeHousing housing);

	List<Holder.Reference<Biome>> getSuitableBiomes(Registry<Biome> registry);

	List<ItemStack> getProduceList();

	List<ItemStack> getSpecialtyList();

	List<ItemStack> produceStacks(IBeeHousing housing);

	@Nullable
	IBee spawnPrincess(IBeeHousing housing);

	List<IBee> spawnDrones(IBeeHousing housing);

	@Nullable
	BlockPos plantFlowerRandom(IBeeHousing housing, List<BlockState> potentialFlowers);

	@Nullable
	IPollen retrievePollen(IBeeHousing housing);

	boolean pollinateRandom(IBeeHousing housing, IPollen pollen);

	Iterator<BlockPos.MutableBlockPos> getAreaIterator(IBeeHousing housing);

	@Override
	IBee copy();

	@Override
	IBee copyWithGenome(IGenome newGenome);

	@Override
	IBeeSpecies getSpecies();

	@Override
	IBeeSpecies getInactiveSpecies();
}
