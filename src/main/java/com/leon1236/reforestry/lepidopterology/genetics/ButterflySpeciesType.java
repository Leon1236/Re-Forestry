package com.leon1236.reforestry.lepidopterology.genetics;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;
import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.lepidopterology.ForestryButterflySpecies;
import com.leon1236.reforestry.api.lepidopterology.IButterflyCocoon;
import com.leon1236.reforestry.api.lepidopterology.IButterflyEffect;
import com.leon1236.reforestry.api.lepidopterology.ILepidopteristTracker;
import com.leon1236.reforestry.api.lepidopterology.genetics.ButterflyLifeStage;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpeciesType;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.core.genetics.BreedingTracker;
import com.leon1236.reforestry.core.genetics.SpeciesType;

public final class ButterflySpeciesType extends SpeciesType<IButterflySpecies, IButterfly> implements IButterflySpeciesType {
	public static final ButterflySpeciesType INSTANCE = new ButterflySpeciesType();

	private ButterflySpeciesType() {
		super(ForestrySpeciesTypes.BUTTERFLY, ButterflyChromosomes.KARYOTYPE, ButterflyLifeStage.BUTTERFLY,
				ForestryButterflySpecies.CABBAGE_WHITE, List.of(ButterflyLifeStage.values()));
	}

	@Override
	public String getBreedingTrackerFile(@Nullable GameProfile profile) {
		return "LepidopteristTracker." + (profile == null || profile.id() == null ? "common" : profile.id());
	}

	@Override
	public boolean isMember(IIndividual individual) {
		return individual instanceof IButterfly;
	}

	@Override
	public Codec<? extends IButterfly> getIndividualCodec() {
		return Butterfly.CODEC;
	}

	@Override
	public ImmutableMap<Identifier, IButterflySpecies> handleSpeciesRegistration(List<IForestryPlugin> plugins) {
		ImmutableMap<Identifier, IButterflySpecies> empty = ImmutableMap.of();
		onSpeciesRegistered(empty);
		return empty;
	}

	@Override
	public IBreedingTracker createBreedingTracker() {
		return new LepidopteristTracker(id());
	}

	@Override
	public IButterflyCocoon getCocoon(Identifier id) {
		IButterflyCocoon cocoon = getCocoonSafe(id);
		if (cocoon == null) {
			throw new IllegalArgumentException("Unknown butterfly cocoon: " + id);
		}
		return cocoon;
	}

	@Override
	@Nullable
	public IButterflyCocoon getCocoonSafe(Identifier id) {
		return ButterflyChromosomes.COCOON.getSafe(id).orElse(null);
	}

	@Override
	public IButterflyEffect getButterflyEffect(Identifier id) {
		IButterflyEffect effect = getButterflyEffectSafe(id);
		if (effect == null) {
			throw new IllegalArgumentException("Unknown butterfly effect: " + id);
		}
		return effect;
	}

	@Override
	@Nullable
	public IButterflyEffect getButterflyEffectSafe(Identifier id) {
		return ButterflyChromosomes.EFFECT.getSafe(id).orElse(null);
	}

	@Override
	@Nullable
	public PathfinderMob spawnButterflyInWorld(Level level, IButterfly butterfly, double x, double y, double z) {
		return null;
	}

	@Override
	@Nullable
	public BlockPos plantCocoon(LevelAccessor level, BlockPos pos, IButterfly caterpillar, int age, boolean createNursery) {
		return null;
	}

	@Override
	public boolean isMated(ItemStack stack) {
		return false;
	}

	private static final class LepidopteristTracker extends BreedingTracker implements ILepidopteristTracker {
		private LepidopteristTracker(Identifier typeId) {
			super(typeId);
		}

		@Override
		public void registerCatch(com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly butterfly) {
			registerSpecies(butterfly.getSpecies().id());
		}
	}
}
