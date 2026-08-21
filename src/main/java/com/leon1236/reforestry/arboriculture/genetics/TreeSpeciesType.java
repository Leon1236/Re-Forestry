package com.leon1236.reforestry.arboriculture.genetics;

import java.util.IdentityHashMap;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;
import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Codec;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.genetics.ITree;
import com.leon1236.reforestry.api.arboriculture.genetics.ITreeSpeciesType;
import com.leon1236.reforestry.api.arboriculture.genetics.TreeLifeStage;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.core.genetics.SpeciesType;

public final class TreeSpeciesType extends SpeciesType<ITreeSpecies, ITree> implements ITreeSpeciesType {
	public static final TreeSpeciesType INSTANCE = new TreeSpeciesType();

	private final IdentityHashMap<Item, ITree> vanillaItems = new IdentityHashMap<>();

	private TreeSpeciesType() {
		super(ForestrySpeciesTypes.TREE, TreeChromosomes.KARYOTYPE, TreeLifeStage.SAPLING,
				ReForestry.id("tree_oak"), List.of(TreeLifeStage.values()));
	}

	@Override
	public void onSpeciesRegistered(ImmutableMap<Identifier, ITreeSpecies> allSpecies) {
		super.onSpeciesRegistered(allSpecies);
		vanillaItems.clear();
		for (ITreeSpecies species : allSpecies.values()) {
			ITree individual = species.createIndividual();
			for (Item item : species.getVanillaSaplingItems()) {
				vanillaItems.put(item, individual);
			}
		}
	}

	@Override
	@Nullable
	public ITree getVanillaIndividual(BlockState state) {
		IGenome genome = ArboricultureGenetics.getVanillaIndividual(state);
		return genome == null ? null : new Tree(genome);
	}

	@Override
	@Nullable
	public ITree getVanillaIndividual(Item item) {
		return vanillaItems.get(item);
	}

	@Override
	public String getBreedingTrackerFile(@Nullable GameProfile profile) {
		return "ArboristTracker." + (profile == null || profile.id() == null ? "common" : profile.id());
	}

	@Override
	public boolean isMember(IIndividual individual) {
		return individual instanceof ITree;
	}

	@Override
	public Codec<? extends ITree> getIndividualCodec() {
		return Tree.CODEC;
	}

	@Override
	public ImmutableMap<Identifier, ITreeSpecies> handleSpeciesRegistration(List<IForestryPlugin> plugins) {
		return ImmutableMap.copyOf(ArboricultureGenetics.getSpeciesById());
	}
}
