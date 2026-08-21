package com.leon1236.reforestry.apiculture.genetics;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;
import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Codec;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.apiculture.genetics.BeeLifeStage;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeSpeciesType;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.core.genetics.SpeciesType;

public final class BeeSpeciesType extends SpeciesType<IBeeSpecies, Bee> implements IBeeSpeciesType {
	public static final BeeSpeciesType INSTANCE = new BeeSpeciesType();

	private BeeSpeciesType() {
		super(ForestrySpeciesTypes.BEE, BeeChromosomes.KARYOTYPE, BeeLifeStage.DRONE,
				ReForestry.id("bee_forest"), List.of(BeeLifeStage.values()));
	}

	@Override
	public ILifeStage getTypeForMutation(int position) {
		return switch (position) {
			case 0 -> BeeLifeStage.PRINCESS;
			case 1 -> BeeLifeStage.DRONE;
			case 2 -> BeeLifeStage.QUEEN;
			default -> getDefaultStage();
		};
	}

	@Override
	public boolean isDrone(ItemStack stack) {
		return getLifeStage(stack) == BeeLifeStage.DRONE;
	}

	@Override
	public boolean isMated(ItemStack stack) {
		if (getLifeStage(stack) != BeeLifeStage.QUEEN) {
			return false;
		}
		Bee bee = Bee.fromStack(stack);
		return bee != null && bee.getMate() != null;
	}

	@Override
	public String getBreedingTrackerFile(@Nullable GameProfile profile) {
		return "ApiaristTracker." + (profile == null || profile.id() == null ? "common" : profile.id());
	}

	@Override
	public boolean isMember(IIndividual individual) {
		return individual instanceof Bee;
	}

	@Override
	public Codec<? extends Bee> getIndividualCodec() {
		return Bee.CODEC;
	}

	@Override
	public float getResearchSuitability(IBeeSpecies species, ItemStack stack) {
		for (IBeeSpecies.Product product : species.products()) {
			if (stack.is(product.item())) {
				return 1.0f;
			}
		}
		for (IBeeSpecies.Product specialty : species.specialties()) {
			if (stack.is(specialty.item())) {
				return 1.0f;
			}
		}
		return super.getResearchSuitability(species, stack);
	}

	@Override
	public ImmutableMap<Identifier, IBeeSpecies> handleSpeciesRegistration(List<IForestryPlugin> plugins) {
		return ImmutableMap.copyOf(ApicultureGenetics.getSpeciesById());
	}

	@Override
	public IBreedingTracker getBreedingTracker(LevelAccessor level, @Nullable GameProfile profile) {
		return super.getBreedingTracker(level, profile);
	}
}
