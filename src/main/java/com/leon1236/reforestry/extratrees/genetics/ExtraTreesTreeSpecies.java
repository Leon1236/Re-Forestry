package com.leon1236.reforestry.extratrees.genetics;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.plugin.IArboricultureRegistration;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.VanillaWoodType;
import com.leon1236.reforestry.arboriculture.genetics.DefaultFruits;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.extratrees.ExtraTreeWoodType;
import com.leon1236.reforestry.extratrees.worldgen.FeatureButternut;
import com.leon1236.reforestry.extratrees.worldgen.FeatureMango;
import com.leon1236.reforestry.extratrees.worldgen.FeatureSycamore;
import com.leon1236.reforestry.extratrees.worldgen.FeatureSallow;
import com.leon1236.reforestry.extratrees.worldgen.FeaturePecan;
import com.leon1236.reforestry.extratrees.worldgen.FeatureHornbeam;
import com.leon1236.reforestry.extratrees.worldgen.FeatureHazel;
import com.leon1236.reforestry.extratrees.worldgen.FeatureHawthorn;
import com.leon1236.reforestry.extratrees.worldgen.FeatureEtElm;
import com.leon1236.reforestry.extratrees.worldgen.FeatureElder;
import com.leon1236.reforestry.extratrees.worldgen.FeatureAcornOak;
import com.leon1236.reforestry.extratrees.worldgen.FeatureSweetgum;
import com.leon1236.reforestry.extratrees.worldgen.FeatureLocust;
import com.leon1236.reforestry.extratrees.worldgen.FeatureIroko;
import com.leon1236.reforestry.extratrees.worldgen.FeatureClove;
import com.leon1236.reforestry.extratrees.worldgen.FeatureBox;
import com.leon1236.reforestry.extratrees.worldgen.FeatureWhitebeam;
import com.leon1236.reforestry.extratrees.worldgen.FeatureRowan;
import com.leon1236.reforestry.extratrees.worldgen.FeatureShrub;
import com.leon1236.reforestry.extratrees.worldgen.FeatureAspen;
import com.leon1236.reforestry.extratrees.worldgen.FeatureRedMaple;
import com.leon1236.reforestry.extratrees.worldgen.FeatureLazyTree;
import com.leon1236.reforestry.extratrees.worldgen.FeatureRosewood;
import com.leon1236.reforestry.extratrees.worldgen.FeaturePurpleheart;
import com.leon1236.reforestry.extratrees.worldgen.FeatureOsangeOrange;
import com.leon1236.reforestry.extratrees.worldgen.FeatureOldFustic;
import com.leon1236.reforestry.extratrees.worldgen.FeatureLogwood;
import com.leon1236.reforestry.extratrees.worldgen.FeatureCoffee;
import com.leon1236.reforestry.extratrees.worldgen.FeatureBrazilwood;
import com.leon1236.reforestry.extratrees.worldgen.FeatureBrazilNut;
import com.leon1236.reforestry.extratrees.worldgen.FeatureHolly;
import com.leon1236.reforestry.extratrees.worldgen.FeatureSilverFir;
import com.leon1236.reforestry.extratrees.worldgen.FeatureDouglasFir;
import com.leon1236.reforestry.extratrees.worldgen.FeatureSwampGum;
import com.leon1236.reforestry.extratrees.worldgen.FeatureRoseGum;
import com.leon1236.reforestry.extratrees.worldgen.FeatureRainbowGum;
import com.leon1236.reforestry.extratrees.worldgen.FeatureYew;
import com.leon1236.reforestry.extratrees.worldgen.FeatureWesternHemlock;
import com.leon1236.reforestry.extratrees.worldgen.FeatureLoblollyPine;
import com.leon1236.reforestry.extratrees.worldgen.FeatureCypress;
import com.leon1236.reforestry.extratrees.worldgen.FeatureCedar;
import com.leon1236.reforestry.extratrees.worldgen.FeatureCopperBeech;
import com.leon1236.reforestry.extratrees.worldgen.FeatureBanana;
import com.leon1236.reforestry.extratrees.worldgen.FeatureCommonAsh;
import com.leon1236.reforestry.extratrees.worldgen.FeatureSweetCrabapple;
import com.leon1236.reforestry.extratrees.worldgen.FeaturePrairieCrabapple;
import com.leon1236.reforestry.extratrees.worldgen.FeatureOrchardApple;
import com.leon1236.reforestry.extratrees.worldgen.FeatureFloweringCrabapple;
import com.leon1236.reforestry.extratrees.worldgen.FeatureCommonAlder;
import com.leon1236.reforestry.extratrees.worldgen.FeaturePinkIvory;
import com.leon1236.reforestry.arboriculture.worldgen.FeaturePlum;
import com.leon1236.reforestry.arboriculture.worldgen.FeatureLemon;

public final class ExtraTreesTreeSpecies {
	private ExtraTreesTreeSpecies() {
	}

	public static void register(IArboricultureRegistration registration) {
		registration.registerSpecies(ReForestry.id("tree_orchard_apple"), "malus", "domestica", true, 0x09e67e, ExtraTreeWoodType.APPLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureOrchardApple::new, ExtraTreeWoodType.APPLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.APPLE, DefaultFruits.APPLE.isDominant()));
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGHER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_oak"), ReForestry.id("tree_hill_cherry"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_sweet_crabapple"), "malus", "coronaria", true, 0x7a9953, ExtraTreeWoodType.APPLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureSweetCrabapple::new, ExtraTreeWoodType.APPLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.CRABAPPLE, ExtraTreesFruits.CRABAPPLE.isDominant()));
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_orchard_apple"), ReForestry.id("tree_maple"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_flowering_crabapple"), "malus", "hopa", true, 0x7a9953, ExtraTreeWoodType.APPLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureFloweringCrabapple::new, ExtraTreeWoodType.APPLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.CRABAPPLE, ExtraTreesFruits.CRABAPPLE.isDominant()));
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_orchard_apple"), ReForestry.id("tree_sweet_crabapple"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_prairie_crabapple"), "malus", "ioensis", true, 0x7a9953, ExtraTreeWoodType.APPLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeaturePrairieCrabapple::new, ExtraTreeWoodType.APPLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.CRABAPPLE, ExtraTreesFruits.CRABAPPLE.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_orchard_apple"), ReForestry.id("tree_birch"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_blackthorn"), "prunus", "spinosa", true, 0x6d8f1e, ForestryWoodType.PLUM)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeaturePlum::new, ForestryWoodType.PLUM))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.BLACKTHORN, ExtraTreesFruits.BLACKTHORN.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_plum"), ReForestry.id("tree_orchard_apple"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_cherry_plum"), "prunus", "cerasifera", true, 0x6d8f1e, ForestryWoodType.PLUM)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeaturePlum::new, ForestryWoodType.PLUM))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.CHERRY_PLUM, ExtraTreesFruits.CHERRY_PLUM.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOWER);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_plum"), ReForestry.id("tree_hill_cherry"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_peach"), "prunus", "persica", true, 0x6d8f1e, ForestryWoodType.PLUM)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeaturePlum::new, ForestryWoodType.PLUM))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.PEACH, ExtraTreesFruits.PEACH.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_plum"), ReForestry.id("tree_chestnut"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_nectarine"), "prunus", "nectarina", true, 0x6d8f1e, ForestryWoodType.PLUM)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeaturePlum::new, ForestryWoodType.PLUM))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.NECTARINE, ExtraTreesFruits.NECTARINE.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_plum"), ReForestry.id("tree_peach"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_apricot"), "prunus", "armeniaca", true, 0x6d8f1e, ForestryWoodType.PLUM)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeaturePlum::new, ForestryWoodType.PLUM))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.APRICOT, ExtraTreesFruits.APRICOT.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_plum"), ReForestry.id("tree_peach"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_almond"), "prunus", "amygdalus", true, 0x6d8f1e, ForestryWoodType.PLUM)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeaturePlum::new, ForestryWoodType.PLUM))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.ALMOND, ExtraTreesFruits.ALMOND.isDominant()));
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOWER);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_plum"), ReForestry.id("tree_walnut"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_wild_cherry"), "prunus", "avium", true, 0x6d8f1e, ExtraTreeWoodType.ET_CHERRY)
				.setAuthority("Binnie")
				.setRarity(0.0015f)
				.setGenerator(new ExtraTreesTreeGenerator(FeaturePlum::new, ExtraTreeWoodType.ET_CHERRY))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.WILD_CHERRY, ExtraTreesFruits.WILD_CHERRY.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOWER);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_lime"), ReForestry.id("tree_hill_cherry"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_black_cherry"), "prunus", "serotina", true, 0x6d8f1e, ExtraTreeWoodType.ET_CHERRY)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeaturePlum::new, ExtraTreeWoodType.ET_CHERRY))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.BLACK_CHERRY, ExtraTreesFruits.BLACK_CHERRY.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_ebony"), ReForestry.id("tree_hill_cherry"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_manderin"), "citrus", "reticulata", true, 0x88af54, ForestryWoodType.CITRUS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLemon::new, ForestryWoodType.CITRUS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.MANDERIN, ExtraTreesFruits.MANDERIN.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_pomelo"), ReForestry.id("tree_hill_cherry"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_satsuma"), "citrus", "unshiu", true, 0x88af54, ForestryWoodType.CITRUS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLemon::new, ForestryWoodType.CITRUS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.SATSUMA, ExtraTreesFruits.SATSUMA.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_kumquat"), ReForestry.id("tree_manderin"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_tangerine"), "citrus", "tangerina", true, 0x88af54, ForestryWoodType.CITRUS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLemon::new, ForestryWoodType.CITRUS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.TANGERINE, ExtraTreesFruits.TANGERINE.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_kumquat"), ReForestry.id("tree_manderin"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_et_lime"), "citrus", "latifolia", true, 0x88af54, ForestryWoodType.CITRUS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLemon::new, ForestryWoodType.CITRUS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.LIME, ExtraTreesFruits.LIME.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_pomelo"), ReForestry.id("tree_key_lime"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_key_lime"), "citrus", "aurantifolia", true, 0x88af54, ForestryWoodType.CITRUS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLemon::new, ForestryWoodType.CITRUS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.KEY_LIME, ExtraTreesFruits.KEY_LIME.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_hill_cherry"), ReForestry.id("tree_lemon"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_finger_lime"), "citrus", "australasica", true, 0x88af54, ForestryWoodType.CITRUS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLemon::new, ForestryWoodType.CITRUS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.FINGER_LIME, ExtraTreesFruits.FINGER_LIME.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_key_lime"), ReForestry.id("tree_lemon"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_pomelo"), "citrus", "maxima", true, 0x88af54, ForestryWoodType.CITRUS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLemon::new, ForestryWoodType.CITRUS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.POMELO, ExtraTreesFruits.POMELO.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_hill_cherry"), ReForestry.id("tree_lemon"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_grapefruit"), "citrus", "paradisi", true, 0x88af54, ForestryWoodType.CITRUS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLemon::new, ForestryWoodType.CITRUS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.GRAPEFRUIT, ExtraTreesFruits.GRAPEFRUIT.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_pomelo"), ReForestry.id("tree_orange"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_kumquat"), "citrus", "margarita", true, 0x88af54, ForestryWoodType.CITRUS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLemon::new, ForestryWoodType.CITRUS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.KUMQUAT, ExtraTreesFruits.KUMQUAT.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_manderin"), ReForestry.id("tree_hill_cherry"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_citron"), "citrus", "medica", true, 0x88af54, ForestryWoodType.CITRUS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLemon::new, ForestryWoodType.CITRUS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.CITRON, ExtraTreesFruits.CITRON.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_pomelo"), ReForestry.id("tree_lemon"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_buddha_hand"), "citrus", "sarcodactylus", true, 0x88af54, ForestryWoodType.CITRUS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLemon::new, ForestryWoodType.CITRUS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.BUDDHA_HAND, ExtraTreesFruits.BUDDHA_HAND.isDominant()));
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_citron"), ReForestry.id("tree_manderin"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_banana"), "musa", "sinensis", true, 0xa1cd8e, ExtraTreeWoodType.BANANA)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureBanana::new, ExtraTreeWoodType.BANANA))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.BANANA, ExtraTreesFruits.BANANA.isDominant()));
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_balsa"), ReForestry.id("tree_jungle"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_red_banana"), "musa", "rubra", true, 0xa1cd8e, ExtraTreeWoodType.BANANA)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureBanana::new, ExtraTreeWoodType.BANANA))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.RED_BANANA, ExtraTreesFruits.RED_BANANA.isDominant()));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_banana"), ReForestry.id("tree_kapok"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_plantain"), "musa", "paradisiaca", true, 0xa1cd8e, ExtraTreeWoodType.BANANA)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureBanana::new, ExtraTreeWoodType.BANANA))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.PLANTAIN, ExtraTreesFruits.PLANTAIN.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_banana"), ReForestry.id("tree_teak"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_butternut"), "juglans", "cinerea", true, 0x82b58c, ExtraTreeWoodType.BUTTERNUT)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureButternut::new, ExtraTreeWoodType.BUTTERNUT))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.BUTTERNUT, ExtraTreesFruits.BUTTERNUT.isDominant()));
					genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_walnut"), ReForestry.id("tree_hill_cherry"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_rowan"), "sorbus", "aucuparia", true, 0x9ec79b, ExtraTreeWoodType.ROWAN)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureRowan::new, ExtraTreeWoodType.ROWAN))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGER);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_aspen"), ReForestry.id("tree_alder"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_hemlock"), "tsuga", "heterophylla", true, 0x5cac72, ExtraTreeWoodType.HEMLOCK)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureWesternHemlock::new, ExtraTreeWoodType.HEMLOCK))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWER);
					genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_pine"), ReForestry.id("tree_et_fir"), 10.0f)
							.addMutationCondition(new MutationConditionMinHeight(80));
				});
		registration.registerSpecies(ReForestry.id("tree_ash"), "fraxinus", "excelsior", true, 0x488e2b, ExtraTreeWoodType.ASH)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureCommonAsh::new, ExtraTreeWoodType.ASH))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_lime"), ReForestry.id("tree_spruce"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_alder"), "alnus", "glutinosa", true, 0x698a33, ExtraTreeWoodType.ALDER)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureCommonAlder::new, ExtraTreeWoodType.ALDER))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_birch"), ReForestry.id("tree_beech"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_copper_beech"), "fagus", "purpurea", true, 0x801318, ForestryWoodType.BEECH)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureCopperBeech::new, ForestryWoodType.BEECH))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.BEECHNUT, ExtraTreesFruits.BEECHNUT.isDominant()));
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_beech"), ReForestry.id("tree_spruce"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_aspen"), "populus", "tremula", true, 0x8acc37, ForestryWoodType.POPLAR)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureAspen::new, ForestryWoodType.POPLAR))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_alder"), ReForestry.id("tree_beech"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_yew"), "taxus", "baccata", true, 0x948a4d, ExtraTreeWoodType.YEW)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureYew::new, ExtraTreeWoodType.YEW))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_larch"), ReForestry.id("tree_spruce"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_cypress"), "chamaecyparis", "lawsoniana", true, 0x89c9a7, ExtraTreeWoodType.CYPRESS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureCypress::new, ExtraTreeWoodType.CYPRESS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGER);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_pine"), ReForestry.id("tree_spruce"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_douglas_fir"), "pseudotsuga", "menziesii", true, 0x99b582, ForestryWoodType.FIR)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureDouglasFir::new, ForestryWoodType.FIR))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGER);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_et_fir"), ReForestry.id("tree_spruce"), 10.0f)
							.addMutationCondition(new MutationConditionMinHeight(60));
				});
		registration.registerSpecies(ReForestry.id("tree_hazel"), "Corylus", "avellana", true, 0x9bb552, ExtraTreeWoodType.HAZEL)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureHazel::new, ExtraTreeWoodType.HAZEL))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.HAZELNUT, ExtraTreesFruits.HAZELNUT.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_beech"), ReForestry.id("tree_aspen"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_sycamore"), "ficus", "sycomorus", true, 0xa0a52f, ExtraTreeWoodType.FIG)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureSycamore::new, ExtraTreeWoodType.FIG))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.FIG, ExtraTreesFruits.FIG.isDominant()));
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWEST);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_ash"), ReForestry.id("tree_maple"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_whitebeam"), "sorbus", "aria", true, 0xbace99, ExtraTreeWoodType.WHITEBEAM)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureWhitebeam::new, ExtraTreeWoodType.WHITEBEAM))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_ash"), ReForestry.id("tree_birch"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_hawthorn"), "crataegus", "monogyna", true, 0x6ba84a, ExtraTreeWoodType.HAWTHORN)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureHawthorn::new, ExtraTreeWoodType.HAWTHORN))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_beech"), ReForestry.id("tree_rowan"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_pecan"), "carya", "illinoinensis", true, 0x85b674, ExtraTreeWoodType.HICKORY)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeaturePecan::new, ExtraTreeWoodType.HICKORY))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.PECAN, ExtraTreesFruits.PECAN.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_beech"), ReForestry.id("tree_birch"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_et_elm"), "ulmus", "procera", true, 0x7c9048, ForestryWoodType.ELM)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureEtElm::new, ForestryWoodType.ELM))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_ash"), ReForestry.id("tree_pine"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_elder"), "sambucus", "nigra", true, 0xaeb873, ExtraTreeWoodType.ELDER)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureElder::new, ExtraTreeWoodType.ELDER))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.ELDERBERRY, ExtraTreesFruits.ELDERBERRY.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_alder"), ReForestry.id("tree_aspen"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_holly"), "ilex", "aquifolium", true, 0x254b4c, ExtraTreeWoodType.HOLLY)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureHolly::new, ExtraTreeWoodType.HOLLY))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_alder"), ReForestry.id("tree_rowan"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_hornbeam"), "carpinus", "betulus", true, 0x96a71b, ExtraTreeWoodType.HORNBEAM)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureHornbeam::new, ExtraTreeWoodType.HORNBEAM))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_ash"), ReForestry.id("tree_larch"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_sallow"), "salix", "caprea", true, 0xaeb323, ForestryWoodType.WILLOW)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureSallow::new, ForestryWoodType.WILLOW))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_willow"), ReForestry.id("tree_aspen"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_acorn_oak"), "quercus", "robur", true, 0x66733e, VanillaWoodType.OAK)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureAcornOak::new, VanillaWoodType.OAK))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.ACORN, ExtraTreesFruits.ACORN.isDominant()));
					genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_walnut"), ReForestry.id("tree_oak"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_et_fir"), "abies", "alba", true, 0x6f7c20, ForestryWoodType.FIR)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureSilverFir::new, ForestryWoodType.FIR))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_pine"), ReForestry.id("tree_fir"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_cedar"), "cedrus", "libani", true, 0x95a370, ExtraTreeWoodType.CEDAR)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureCedar::new, ExtraTreeWoodType.CEDAR))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_et_fir"), ReForestry.id("tree_larch"), 10.0f)
							.addMutationCondition(new MutationConditionMinHeight(60));
				});
		registration.registerSpecies(ReForestry.id("tree_red_maple"), "acer", "ubrum", true, 0xe82e17, ForestryWoodType.MAPLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureRedMaple::new, ForestryWoodType.MAPLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_HIGH);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_maple"), ReForestry.id("tree_lime"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_loblolly_pine"), "pinus", "taeda", true, 0x6f8a47, ForestryWoodType.PINE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLoblollyPine::new, ForestryWoodType.PINE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_pine"), ReForestry.id("tree_spruce"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_sweetgum"), "liquidambar", "styraciflua", true, 0x8b8762, ExtraTreeWoodType.SWEETGUM)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureSweetgum::new, ExtraTreeWoodType.SWEETGUM))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_HIGH);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_maple"), ReForestry.id("tree_larch"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_locust"), "robinia", "pseudoacacia", true, 0x887300, ExtraTreeWoodType.LOCUST)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLocust::new, ExtraTreeWoodType.LOCUST))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_balsa"), ReForestry.id("tree_lime"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_osange_orange"), "maclura", "pomifera", true, 0x687a50, ExtraTreeWoodType.MACLURA)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureOsangeOrange::new, ExtraTreeWoodType.MACLURA))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.OSANGE_ORANGE, ExtraTreesFruits.OSANGE_ORANGE.isDominant()));
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_old_fustic"), ReForestry.id("tree_kapok"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_old_fustic"), "maclura", "tinctoria", true, 0x687a50, ExtraTreeWoodType.MACLURA)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureOldFustic::new, ExtraTreeWoodType.MACLURA))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_ebony"), ReForestry.id("tree_teak"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_brazilwood"), "caesalpinia", "echinata", true, 0x607459, ExtraTreeWoodType.BRAZILWOOD)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureBrazilwood::new, ExtraTreeWoodType.BRAZILWOOD))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOWER);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_ebony"), ReForestry.id("tree_teak"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_logwood"), "haematoxylum", "campechianum", true, 0x889f6b, ExtraTreeWoodType.LOGWOOD)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLogwood::new, ExtraTreeWoodType.LOGWOOD))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_rosewood"), ReForestry.id("tree_kapok"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_rosewood"), "dalbergia", "latifolia", true, 0x879b22, ExtraTreeWoodType.ROSEWOOD)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureRosewood::new, ExtraTreeWoodType.ROSEWOOD))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWEST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_ebony"), ReForestry.id("tree_teak"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_purpleheart"), "peltogyne", "spp", true, 0x778f55, ExtraTreeWoodType.PURPLEHEART)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeaturePurpleheart::new, ExtraTreeWoodType.PURPLEHEART))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_brazilwood"), ReForestry.id("tree_kapok"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_iroko"), "milicia", "excelsa", true, 0xafc86c, ExtraTreeWoodType.IROKO)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureIroko::new, ExtraTreeWoodType.IROKO))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_balsa"), ReForestry.id("tree_teak"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_brazilnut"), "bertholletia", "excelsa", true, 0x7c8f7b, VanillaWoodType.JUNGLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureBrazilNut::new, VanillaWoodType.JUNGLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.BRAZIL_NUT, ExtraTreesFruits.BRAZIL_NUT.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGER);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_beech"), ReForestry.id("tree_jungle"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_rose_gum"), "eucalyptus", "grandis", true, 0x9ca258, ExtraTreeWoodType.EUCALYPTUS)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureRoseGum::new, ExtraTreeWoodType.EUCALYPTUS))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOWER);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWEST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_balsa"), ReForestry.id("tree_jungle"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_swamp_gum"), "eucalyptus", "grandis", true, 0xa2c686, ExtraTreeWoodType.EUCALYPTUS2)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureSwampGum::new, ExtraTreeWoodType.EUCALYPTUS2))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWEST);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_rose_gum"), ReForestry.id("tree_mahogany"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_box"), "boxus", "sempervirens", true, 0x72996d, ExtraTreeWoodType.BOX)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureBox::new, ExtraTreeWoodType.BOX))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_holly"), ReForestry.id("tree_alder"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_clove"), "syzygium", "aromaticum", true, 0x7a821f, ExtraTreeWoodType.SYZGIUM)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureClove::new, ExtraTreeWoodType.SYZGIUM))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.CLOVE, ExtraTreesFruits.CLOVE.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_coffee"), ReForestry.id("tree_teak"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_coffee"), "coffea", "arabica", true, 0x6f9065, VanillaWoodType.JUNGLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureCoffee::new, VanillaWoodType.JUNGLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.COFFEE, ExtraTreesFruits.COFFEE.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_hill_cherry"), ReForestry.id("tree_jungle"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_rainbow_gum"), "eucalyptus", "deglupta", true, 0xb7f025, ExtraTreeWoodType.EUCALYPTUS3)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureRainbowGum::new, ExtraTreeWoodType.EUCALYPTUS3))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOWER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_rose_gum"), ReForestry.id("tree_balsa"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_pink_ivory"), "berchemia", "zeyheri", true, 0x7c9159, ExtraTreeWoodType.PINK_IVORY)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeaturePinkIvory::new, ExtraTreeWoodType.PINK_IVORY))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_rose_gum"), ReForestry.id("tree_brazilwood"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_blackcurrant"), "ribes", "nigrum", true, 0xa6da5c, ExtraTreeWoodType.SHRUB)
				.setAuthority("Binnie")
				.setRarity(0.0025f)
				.setGenerator(new ExtraTreesTreeGenerator(FeatureShrub::new, ExtraTreeWoodType.SHRUB))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.BLACKCURRANT, ExtraTreesFruits.BLACKCURRANT.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_black_cherry"), ReForestry.id("tree_redcurrant"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_redcurrant"), "ribes", "rubrum", true, 0x74ac00, ExtraTreeWoodType.SHRUB)
				.setAuthority("Binnie")
				.setRarity(0.0025f)
				.setGenerator(new ExtraTreesTreeGenerator(FeatureShrub::new, ExtraTreeWoodType.SHRUB))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.REDCURRANT, ExtraTreesFruits.REDCURRANT.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_hill_cherry"), ReForestry.id("tree_elder"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_blackberry"), "rubus", "fruticosus", true, 0x92c15b, ExtraTreeWoodType.SHRUB)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureShrub::new, ExtraTreeWoodType.SHRUB))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.BLACKBERRY, ExtraTreesFruits.BLACKBERRY.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_black_cherry"), ReForestry.id("tree_raspberry"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_raspberry"), "rubus", "idaeus", true, 0x83b96e, ExtraTreeWoodType.SHRUB)
				.setAuthority("Binnie")
				.setRarity(0.0025f)
				.setGenerator(new ExtraTreesTreeGenerator(FeatureShrub::new, ExtraTreeWoodType.SHRUB))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.RASPBERRY, ExtraTreesFruits.RASPBERRY.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_hill_cherry"), ReForestry.id("tree_elder"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_blueberry"), "vaccinium", "corymbosum", true, 0x72c750, ExtraTreeWoodType.SHRUB)
				.setAuthority("Binnie")
				.setRarity(0.0025f)
				.setGenerator(new ExtraTreesTreeGenerator(FeatureShrub::new, ExtraTreeWoodType.SHRUB))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.BLUEBERRY, ExtraTreesFruits.BLUEBERRY.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_blackberry"), ReForestry.id("tree_raspberry"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_cranberry"), "vaccinium", "oxycoccos", true, 0x96d179, ExtraTreeWoodType.SHRUB)
				.setAuthority("Binnie")
				.setRarity(0.0025f)
				.setGenerator(new ExtraTreesTreeGenerator(FeatureShrub::new, ExtraTreeWoodType.SHRUB))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.CRANBERRY, ExtraTreesFruits.CRANBERRY.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_blackberry"), ReForestry.id("tree_cherry_plum"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_juniper"), "juniperus", "communis", true, 0x90b149, ExtraTreeWoodType.SHRUB)
				.setAuthority("Binnie")
				.setRarity(0.0025f)
				.setGenerator(new ExtraTreesTreeGenerator(FeatureShrub::new, ExtraTreeWoodType.SHRUB))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.JUNIPER, ExtraTreesFruits.JUNIPER.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_raspberry"), ReForestry.id("tree_et_fir"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_gooseberry"), "ribes", "grossularia", true, 0x79bb00, ExtraTreeWoodType.SHRUB)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureShrub::new, ExtraTreeWoodType.SHRUB))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.GOOSEBERRY, ExtraTreesFruits.GOOSEBERRY.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_HIGH);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_raspberry"), ReForestry.id("tree_et_lime"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_golden_raspberry"), "rubus", "occidentalis", true, 0x83b96e, ExtraTreeWoodType.SHRUB)
				.setAuthority("Binnie")
				.setRarity(0.0025f)
				.setGenerator(new ExtraTreesTreeGenerator(FeatureShrub::new, ExtraTreeWoodType.SHRUB))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.GOLDEN_RASPBERRY, ExtraTreesFruits.GOLDEN_RASPBERRY.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTEST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_raspberry"), ReForestry.id("tree_orange"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_cinnamon"), "cinnamomum", "cassia", true, 0x738e0b, ExtraTreeWoodType.CINNAMON)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLazyTree::new, ExtraTreeWoodType.CINNAMON))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_teak"), ReForestry.id("tree_rosewood"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_cashew"), "anacardium", "occidentale", true, 0xabb962, VanillaWoodType.JUNGLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLazyTree::new, VanillaWoodType.JUNGLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.CASHEW, ExtraTreesFruits.CASHEW.isDominant()));
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_teak"), ReForestry.id("tree_oak"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_avocado"), "persea", "americana", true, 0x96a375, VanillaWoodType.JUNGLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLazyTree::new, VanillaWoodType.JUNGLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.AVOCADO, ExtraTreesFruits.AVOCADO.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_wenge"), ReForestry.id("tree_oak"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_nutmeg"), "myristica", "fragrans", true, 0x488d4c, VanillaWoodType.JUNGLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLazyTree::new, VanillaWoodType.JUNGLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.NUTMEG, ExtraTreesFruits.NUTMEG.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_teak"), ReForestry.id("tree_clove"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_allspice"), "pimenta", "dioica", true, 0x7c9724, VanillaWoodType.JUNGLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLazyTree::new, VanillaWoodType.JUNGLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.ALLSPICE, ExtraTreesFruits.ALLSPICE.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_teak"), ReForestry.id("tree_clove"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_chilli"), "capsicum", "annuum", true, 0x2a9f01, VanillaWoodType.JUNGLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLazyTree::new, VanillaWoodType.JUNGLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.CHILLI, ExtraTreesFruits.CHILLI.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGHER);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_hazel"), ReForestry.id("tree_ginkgo"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_star_anise"), "illicium", "verum", true, 0x7fc409, VanillaWoodType.JUNGLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLazyTree::new, VanillaWoodType.JUNGLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.STAR_ANISE, ExtraTreesFruits.STAR_ANISE.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_allspice"), ReForestry.id("tree_clove"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_mango"), "mangifera", "indica", true, 0x87b574, VanillaWoodType.JUNGLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureMango::new, VanillaWoodType.JUNGLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.MANGO, ExtraTreesFruits.MANGO.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_jungle"), ReForestry.id("tree_orange"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_starfruit"), "averrhoa", "carambola", true, 0x6da92d, VanillaWoodType.JUNGLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLazyTree::new, VanillaWoodType.JUNGLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.STARFRUIT, ExtraTreesFruits.STARFRUIT.isDominant()));
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_star_anise"), ReForestry.id("tree_mango"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_candlenut"), "aleurites", "moluccana", true, 0x8aa36c, VanillaWoodType.JUNGLE)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureLazyTree::new, VanillaWoodType.JUNGLE))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.CANDLENUT, ExtraTreesFruits.CANDLENUT.isDominant()));
					genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWEST);
					genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_hazel"), ReForestry.id("tree_ginkgo"), 10.0f);
				});
		registration.registerSpecies(ReForestry.id("tree_dwarf_hazel"), "Corylus", "americana", true, 0x9bb552, ExtraTreeWoodType.HAZEL)
				.setAuthority("Binnie")
				.setGenerator(new ExtraTreesTreeGenerator(FeatureShrub::new, ExtraTreeWoodType.HAZEL))
				.setGenome(genome -> {
					genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(ExtraTreesFruits.HAZELNUT, ExtraTreesFruits.HAZELNUT.isDominant()));
					genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
					genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
					genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("tree_hazel"), ReForestry.id("tree_alder"), 10.0f);
				});

		registration.modifySpecies(ReForestry.id("tree_orange"), species -> species.addMutations(mutations -> {
				mutations.add(ReForestry.id("tree_pomelo"), ReForestry.id("tree_manderin"), 10.0f);
				}));
		registration.modifySpecies(ReForestry.id("tree_hill_cherry"), species -> species.addMutations(mutations -> {
				mutations.add(ReForestry.id("tree_willow"), ReForestry.id("tree_hill_cherry"), 10.0f);
				}));
		registration.modifySpecies(ReForestry.id("tree_beech"), species -> species.addMutations(mutations -> {
				mutations.add(ReForestry.id("tree_birch"), ReForestry.id("tree_oak"), 10.0f);
				}));
		registration.modifySpecies(ReForestry.id("tree_fir"), species -> species.addMutations(mutations -> {
				mutations.add(ReForestry.id("tree_larch"), ReForestry.id("tree_alder"), 10.0f);
				}));
		registration.modifySpecies(ReForestry.id("tree_olive"), species -> species.addMutations(mutations -> {
				mutations.add(ReForestry.id("tree_hill_cherry"), ReForestry.id("tree_alder"), 10.0f);
				}));
		registration.modifySpecies(ReForestry.id("tree_pear"), species -> species.addMutations(mutations -> {
				mutations.add(ReForestry.id("tree_orchard_apple"), ReForestry.id("tree_birch"), 10.0f);
				}));
		registration.modifySpecies(ReForestry.id("tree_ginkgo"), species -> species.addMutations(mutations -> {
				mutations.add(ReForestry.id("tree_wenge"), ReForestry.id("tree_lime"), 10.0f);
				}));
		registration.modifySpecies(ReForestry.id("tree_pewen"), species -> species.addMutations(mutations -> {
				mutations.add(ReForestry.id("tree_hemlock"), ReForestry.id("tree_jungle"), 10.0f);
				}));
		registration.modifySpecies(ReForestry.id("tree_coconut"), species -> species.addMutations(mutations -> {
				mutations.add(ReForestry.id("tree_balsa"), ReForestry.id("tree_brazilnut"), 10.0f);
				}));
	}
}
