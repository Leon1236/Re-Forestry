package com.leon1236.reforestry.core.genetics;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.items.ItemBeeGE;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.items.ItemGermlingGE;
import com.leon1236.reforestry.core.features.CoreDataComponents;
import com.leon1236.reforestry.core.genetics.root.BreedingTrackerManager;

public final class GeneticItemHelper implements IndividualItems.Access {
	public static final GeneticItemHelper INSTANCE = new GeneticItemHelper();

	private GeneticItemHelper() {
	}

	public static void bootstrap() {
		IndividualItems.setAccess(INSTANCE);
	}

	@Override
	public boolean isIndividual(ItemStack stack) {
		return getGenome(stack) != null;
	}

	@Override
	@Nullable
	public IGenome getGenome(ItemStack stack) {
		if (stack.isEmpty()) {
			return null;
		}
		if (stack.getItem() instanceof ItemBeeGE) {
			return stack.get(ApicultureDataComponents.BEE_GENOME.type());
		}
		if (stack.getItem() instanceof ItemGermlingGE) {
			return stack.get(ArboricultureDataComponents.TREE_GENOME.type());
		}
		return null;
	}

	@Override
	@Nullable
	public Identifier getSpeciesTypeId(ItemStack stack) {
		if (stack.getItem() instanceof ItemBeeGE) {
			return ForestrySpeciesTypes.BEE;
		}
		if (stack.getItem() instanceof ItemGermlingGE) {
			return ForestrySpeciesTypes.TREE;
		}
		return null;
	}

	@Override
	@Nullable
	public String getLifeStage(ItemStack stack) {
		if (stack.getItem() instanceof ItemBeeGE bee) {
			return bee.lifeStage();
		}
		if (stack.getItem() instanceof ItemGermlingGE germling) {
			return germling.lifeStage();
		}
		return null;
	}

	@Override
	public boolean isAnalyzed(ItemStack stack) {
		return Boolean.TRUE.equals(stack.get(CoreDataComponents.ANALYZED.type()));
	}

	@Override
	public boolean analyze(ItemStack stack, Player player) {
		if (!isIndividual(stack) || isAnalyzed(stack)) {
			return false;
		}
		IGenome genome = getGenome(stack);
		Identifier typeId = getSpeciesTypeId(stack);
		if (genome == null || typeId == null) {
			return false;
		}
		stack.set(CoreDataComponents.ANALYZED.type(), true);
		IBreedingTracker tracker = BreedingTrackerManager.INSTANCE.getTracker(typeId, player.level(), player.getGameProfile());
		tracker.registerSpecies(speciesId(genome, typeId, true));
		tracker.registerSpecies(speciesId(genome, typeId, false));
		return true;
	}

	private static Identifier speciesId(IGenome genome, Identifier typeId, boolean active) {
		if (typeId.equals(ForestrySpeciesTypes.TREE)) {
			return alleleSpeciesId(genome, TreeChromosomes.SPECIES, active);
		}
		return alleleSpeciesId(genome, BeeChromosomes.SPECIES, active);
	}

	private static <V extends IRegistryAlleleValue> Identifier alleleSpeciesId(
			IGenome genome, IRegistryChromosome<V> chromosome, boolean active) {
		V value = active
				? genome.getActiveAllele(chromosome).value()
				: genome.getInactiveAllele(chromosome).value();
		return value.id();
	}
}
