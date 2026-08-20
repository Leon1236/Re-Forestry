package com.leon1236.reforestry.core.escritoire;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.apiculture.BeeStackHelper;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.apiculture.items.EnumHoneyComb;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.core.config.ForestryConfig;
import com.leon1236.reforestry.core.genetics.GeneticsUtil;
import com.leon1236.reforestry.core.genetics.ItemResearchNote;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;
import com.leon1236.reforestry.core.genetics.root.BreedingTrackerManager;

public final class EscritoireResearch {
	private EscritoireResearch() {
	}

	public static boolean isSupportedSpecimen(ItemStack specimen) {
		Identifier typeId = IndividualItems.getSpeciesTypeId(specimen);
		return ForestrySpeciesTypes.BEE.equals(typeId) || ForestrySpeciesTypes.TREE.equals(typeId);
	}

	public static int getTokenCount(ItemStack specimen) {
		IGenome genome = IndividualItems.getGenome(specimen);
		Identifier typeId = IndividualItems.getSpeciesTypeId(specimen);
		if (genome == null || typeId == null) {
			return 0;
		}
		int complexity;
		if (ForestrySpeciesTypes.BEE.equals(typeId)) {
			IBeeSpecies active = genome.getActiveAllele(BeeChromosomes.SPECIES).value();
			IBeeSpecies inactive = genome.getInactiveAllele(BeeChromosomes.SPECIES).value();
			complexity = GeneticsUtil.getResearchComplexity(active) + GeneticsUtil.getResearchComplexity(inactive);
		} else if (ForestrySpeciesTypes.TREE.equals(typeId)) {
			ITreeSpecies active = genome.getActiveAllele(TreeChromosomes.SPECIES).value();
			ITreeSpecies inactive = genome.getInactiveAllele(TreeChromosomes.SPECIES).value();
			complexity = GeneticsUtil.getResearchComplexity(active) + GeneticsUtil.getResearchComplexity(inactive);
		} else {
			return 0;
		}
		if (complexity % 2 != 0) {
			complexity = Math.round(complexity / 2.0f) * 2;
		}
		return Math.clamp(complexity, EscritoireGameBoard.TOKEN_COUNT_MIN, EscritoireGameBoard.TOKEN_COUNT_MAX);
	}

	@Nullable
	public static Identifier pickRandomSpeciesId(Identifier typeId, RandomSource random) {
		if (ForestrySpeciesTypes.BEE.equals(typeId)) {
			var ids = ApicultureGenetics.getAllSpeciesIds().toArray(Identifier[]::new);
			if (ids.length == 0) {
				return null;
			}
			return ids[random.nextInt(ids.length)];
		}
		if (ForestrySpeciesTypes.TREE.equals(typeId)) {
			var ids = ArboricultureGenetics.getAllSpeciesIds().toArray(Identifier[]::new);
			if (ids.length == 0) {
				return null;
			}
			return ids[random.nextInt(ids.length)];
		}
		return null;
	}

	public static ItemStack createTokenStack(Identifier typeId, Identifier speciesId) {
		if (ForestrySpeciesTypes.BEE.equals(typeId)) {
			return BeeStackHelper.createBeeStack(
					ApicultureItems.BEE_DRONE.item(),
					ApicultureGenetics.getDefaultGenome(speciesId),
					true,
					0);
		}
		if (ForestrySpeciesTypes.TREE.equals(typeId)) {
			ItemStack stack = new ItemStack(ArboricultureItems.SAPLING.item());
			stack.set(ArboricultureDataComponents.TREE_GENOME.type(), ArboricultureGenetics.getDefaultGenome(speciesId));
			return stack;
		}
		return ItemStack.EMPTY;
	}

	public static int getEscritoireColor(Identifier typeId, Identifier speciesId) {
		if (ForestrySpeciesTypes.BEE.equals(typeId)) {
			IBeeSpecies species = ApicultureGenetics.getSpeciesSafe(speciesId);
			return species == null ? 0xffffff : species.outlineColor();
		}
		if (ForestrySpeciesTypes.TREE.equals(typeId)) {
			ITreeSpecies species = ArboricultureGenetics.getSpeciesSafe(speciesId);
			return species == null ? 0xffffff : species.escritoireColor();
		}
		return 0xffffff;
	}

	public static float getResearchSuitability(ItemStack specimen, ItemStack sample) {
		if (sample.isEmpty() || specimen.isEmpty()) {
			return 0f;
		}
		IGenome genome = IndividualItems.getGenome(specimen);
		Identifier typeId = IndividualItems.getSpeciesTypeId(specimen);
		if (genome == null || typeId == null) {
			return 0f;
		}
		float base = baseMaterialSuitability(sample.getItem());
		if (ForestrySpeciesTypes.BEE.equals(typeId)) {
			IBeeSpecies species = genome.getActiveAllele(BeeChromosomes.SPECIES).value();
			for (IProduct product : species.products()) {
				if (sample.is(product.item())) {
					return 1.0f;
				}
			}
			for (IProduct product : species.specialties()) {
				if (sample.is(product.item())) {
					return 1.0f;
				}
			}
			return base;
		}
		if (ForestrySpeciesTypes.TREE.equals(typeId)) {
			IFruit fruit = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
			for (IFruit.Product product : fruit.getProducts()) {
				if (sample.is(product.item())) {
					return 1.0f;
				}
			}
			for (IFruit.Product product : fruit.getSpecialties()) {
				if (sample.is(product.item())) {
					return 1.0f;
				}
			}
			return base;
		}
		return 0f;
	}

	private static float baseMaterialSuitability(Item item) {
		if (item == ApicultureItems.HONEY_DROP.item()) {
			return 0.5f;
		}
		if (item == ApicultureItems.HONEYDEW.item()) {
			return 0.7f;
		}
		if (item == ApicultureItems.BEE_COMBS.item(EnumHoneyComb.HONEY)) {
			return 0.4f;
		}
		return 0f;
	}

	public static List<ItemStack> getResearchBounty(ItemStack specimen, Level level, GameProfile researcher,
			int bountyLevel) {
		IGenome genome = IndividualItems.getGenome(specimen);
		Identifier typeId = IndividualItems.getSpeciesTypeId(specimen);
		if (genome == null || typeId == null) {
			return List.of();
		}
		if (ForestrySpeciesTypes.BEE.equals(typeId)) {
			IBeeSpecies species = genome.getActiveAllele(BeeChromosomes.SPECIES).value();
			return getBeeResearchBounty(species, level, researcher, bountyLevel);
		}
		if (ForestrySpeciesTypes.TREE.equals(typeId)) {
			ITreeSpecies species = genome.getActiveAllele(TreeChromosomes.SPECIES).value();
			return getBaseResearchBounty(ForestrySpeciesTypes.TREE, species.id(), level, researcher, bountyLevel);
		}
		return List.of();
	}

	private static List<ItemStack> getBeeResearchBounty(IBeeSpecies species, Level level, GameProfile researcher,
			int bountyLevel) {
		List<ItemStack> bounty = new ArrayList<>(getBaseResearchBounty(
				ForestrySpeciesTypes.BEE, species.id(), level, researcher, bountyLevel));
		RandomSource random = level.getRandom();
		if (bountyLevel > 10) {
			for (IProduct product : species.specialties()) {
				bounty.add(formBountyStack(product, bountyLevel, random));
			}
		}
		for (IProduct product : species.products()) {
			bounty.add(formBountyStack(product, bountyLevel, random));
		}
		return bounty;
	}

	private static List<ItemStack> getBaseResearchBounty(Identifier typeId, Identifier speciesId, Level level,
			GameProfile researcher, int bountyLevel) {
		RandomSource random = level.getRandom();
		if (random.nextFloat() >= bountyLevel / 16f) {
			return List.of();
		}
		List<Mutation> mutationsFrom = mutationsFrom(typeId, speciesId);
		if (mutationsFrom.isEmpty()) {
			return List.of();
		}
		IBreedingTracker tracker = BreedingTrackerManager.INSTANCE.getTracker(typeId, level, researcher);
		List<IMutation> unresearched = new ArrayList<>();
		for (Mutation mutation : mutationsFrom) {
			if (!tracker.isResearched(mutation)) {
				unresearched.add(mutation);
			}
		}
		IMutation chosen;
		if (!unresearched.isEmpty()) {
			chosen = unresearched.get(random.nextInt(unresearched.size()));
		} else {
			chosen = mutationsFrom.get(random.nextInt(mutationsFrom.size()));
		}
		return List.of(ItemResearchNote.createMutationNoteStack(researcher, chosen));
	}

	private static List<Mutation> mutationsFrom(Identifier typeId, Identifier speciesId) {
		if (ForestrySpeciesTypes.BEE.equals(typeId)) {
			return ApicultureGenetics.getMutationsFrom(speciesId);
		}
		if (ForestrySpeciesTypes.TREE.equals(typeId)) {
			return ArboricultureGenetics.getMutationsFrom(speciesId);
		}
		return List.of();
	}

	private static ItemStack formBountyStack(IProduct product, int bountyLevel, RandomSource random) {
		double productGenChance = product.chance() * ForestryConfig.escritoireBountyMultiplier();
		int successes = 0;
		for (int i = 0; i < bountyLevel; i++) {
			if (random.nextDouble() < productGenChance) {
				successes++;
			}
		}
		ItemStack stack = product.createRandomStack(random);
		stack.setCount(Math.min(successes, stack.getMaxStackSize()));
		return stack;
	}
}
