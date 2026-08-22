package com.leon1236.reforestry.extra_bees.genetics;

import net.minecraft.world.item.Items;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.ForestryTaxa;
import com.leon1236.reforestry.api.plugin.IApicultureRegistration;
import com.leon1236.reforestry.api.plugin.IGeneticRegistration;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ActivityType;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.FlowerType;
import com.leon1236.reforestry.apiculture.items.EnumHoneyComb;
import com.leon1236.reforestry.apiculture.items.EnumPollenCluster;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesItems;
import com.leon1236.reforestry.extra_bees.items.EnumExtraBeeComb;

public final class ExtraBeesBeeSpecies {
	private ExtraBeesBeeSpecies() {
	}

	public static void registerTaxa(IGeneticRegistration registration) {
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "agriapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "aquapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "botaniapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "eftebeapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "fosiapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "gemmapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "grecapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "incitapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "infenapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "irrapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "lamminapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "levapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "metalapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "morbapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "niphapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "petrapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "pluriapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "primapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "priscapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "pullapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "quantapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "sacchapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "secapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "tertiapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "vacapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "virapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "viscapis");
	}

	public static void register(IApicultureRegistration registration) {
		registration.registerSpecies(ReForestry.id("bee_arid"), "vacapis", "aridus", true, 0xbee854)
				.setBodyColor(0xbee854)
				.setStripesColor(0xcbe374)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HOT)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.DEAD, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_frugal"), 10.0f);
					mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_frugal"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_barren"), "vacapis", "infelix", true, 0xe0d263)
				.setBodyColor(0xe0d263)
				.setStripesColor(0xcbe374)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HOT)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.DEAD, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_arid"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_desolate"), "vacapis", "desolo", false, 0xd1b890)
				.setBodyColor(0xd1b890)
				.setStripesColor(0xcbe374)
				.setAuthority("Binnie")
				.setGlint(true)
				.setTemperature(TemperatureType.HOT)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.DEAD, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.HUNGER).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_arid"), ReForestry.id("bee_barren"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_decomposing"), "vacapis", "aegrus", true, 0x523711)
				.setBodyColor(0x523711)
				.setStripesColor(0xffffff)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HOT)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.3f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.COMPOST), 0.08f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.DEAD, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_barren"), 15.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_gnawing"), "vacapis", "apica", true, 0xe874b0)
				.setBodyColor(0xe874b0)
				.setStripesColor(0xcbe374)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HOT)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.25f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.SAWDUST), 0.25f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.WOOD, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_barren"), 15.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_rotten"), "infenapis", "caries", true, 0xbfe0b6)
				.setBodyColor(0xbfe0b6)
				.setStripesColor(0xcbe374)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HOT)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.3f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.ROTTEN), 0.1f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.DEAD, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.SPAWN_ZOMBIE).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_desolate"), 15.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_bone"), "infenapis", "os", true, 0xe9ede8)
				.setBodyColor(0xe9ede8)
				.setStripesColor(0xcbe374)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HOT)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.3f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BONE), 0.1f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.DEAD, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.SPAWN_SKELETON).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_desolate"), 15.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_creeper"), "infenapis", "erepo", true, 0x2ce615)
				.setBodyColor(0x2ce615)
				.setStripesColor(0xcbe374)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HOT)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.3f)
				.addSpecialty(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.POWDERY).item(), 0.08f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.DEAD, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.SPAWN_CREEPER).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_desolate"), 15.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_rock"), "monapis", "saxum", true, 0xa8a8a8)
				.setBodyColor(0xa8a8a8)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
				});

		registration.registerSpecies(ReForestry.id("bee_stone"), "monapis", "lapis", false, 0x757575)
				.setBodyColor(0x757575)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_diligent"), ReForestry.id("bee_rock"), 12.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_granite"), "monapis", "granum", true, 0x695555)
				.setBodyColor(0x695555)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_unweary"), ReForestry.id("bee_stone"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_mineral"), "monapis", "minerale", true, 0x6e757d)
				.setBodyColor(0x6e757d)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_industrious"), ReForestry.id("bee_granite"), 6.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_water"), "aquapis", "aqua", true, 0x94a2ff)
				.setBodyColor(0x94a2ff)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setHumidity(HumidityType.DAMP)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.WATER), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWEST);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.WATER, false));
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.WATER).orElseThrow(), false));
				});

		registration.registerSpecies(ReForestry.id("bee_river"), "aquapis", "flumen", true, 0x83b3d4)
				.setBodyColor(0x83b3d4)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setHumidity(HumidityType.DAMP)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.WATER), 0.3f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.CLAY), 0.2f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWEST);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.WATER, false));
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.WATER).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_diligent"), ReForestry.id("bee_water"), 10.0f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_RIVER);
				});

		registration.registerSpecies(ReForestry.id("bee_ocean"), "aquapis", "mare", false, 0x1d2ead)
				.setBodyColor(0x1d2ead)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setHumidity(HumidityType.DAMP)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.WATER), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWEST);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.WATER, false));
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.WATER).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_diligent"), ReForestry.id("bee_water"), 10.0f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_OCEAN);
				});

		registration.registerSpecies(ReForestry.id("bee_marble"), "grecapis", "marbla", true, 0xd6c9cf)
				.setBodyColor(0xd6c9cf)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				});

		registration.registerSpecies(ReForestry.id("bee_roman"), "grecapis", "roman", true, 0xad8bb0)
				.setBodyColor(0xad8bb0)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_marble"), ReForestry.id("bee_heroic"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_greek"), "grecapis", "greco", false, 0x854c8a)
				.setBodyColor(0x854c8a)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_roman"), ReForestry.id("bee_marble"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_classical"), "grecapis", "classica", false, 0x831d8c)
				.setBodyColor(0x831d8c)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setGlint(true)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.3f)
				.addSpecialty(ApicultureItems.ROYAL_JELLY.item(), 0.25f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_greek"), ReForestry.id("bee_roman"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_basalt"), "irrapis", "aceri", true, 0x8c6969)
				.setBodyColor(0x8c6969)
				.setStripesColor(0x9a2323)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HELLISH)
				.setHumidity(HumidityType.ARID)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SIMMERING).item(), 0.25f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_2);
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.NETHER, false));
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_AGGRESSIVE);
				});

		registration.registerSpecies(ReForestry.id("bee_tempered"), "irrapis", "iratus", false, 0x8a4848)
				.setBodyColor(0x8a4848)
				.setStripesColor(0x9a2323)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HELLISH)
				.setHumidity(HumidityType.ARID)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SIMMERING).item(), 0.25f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_2);
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.NETHER, false));
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.METEOR).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_fiendish"), ReForestry.id("bee_basalt"), 30.0f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_NETHER);
				});

		registration.registerSpecies(ReForestry.id("bee_volcanic"), "irrapis", "volcano", true, 0x4d0c0c)
				.setBodyColor(0x4d0c0c)
				.setStripesColor(0x9a2323)
				.setAuthority("Binnie")
				.setGlint(true)
				.setTemperature(TemperatureType.HELLISH)
				.setHumidity(HumidityType.ARID)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SIMMERING).item(), 0.25f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BLAZE), 0.1f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_2);
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.NETHER, false));
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.METEOR).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_demonic"), ReForestry.id("bee_tempered"), 20.0f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_NETHER);
				});

		registration.registerSpecies(ReForestry.id("bee_shadow"), "pullapis", "shadowa", false, 0x595959)
				.setBodyColor(0x595959)
				.setStripesColor(0x333333)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HELLISH)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.SHADOW), 0.05f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_2);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.NETHER, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.BLINDNESS).orElseThrow(), false));
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.NOCTURNAL, false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_sinister"), ReForestry.id("bee_rock"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_darkened"), "pullapis", "darka", true, 0x332e33)
				.setBodyColor(0x332e33)
				.setStripesColor(0x333333)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HELLISH)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.SHADOW), 0.1f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_2);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.NETHER, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.BLINDNESS).orElseThrow(), false));
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.NOCTURNAL, false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_shadow"), ReForestry.id("bee_rock"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_abyss"), "pullapis", "abyssba", true, 0x210821)
				.setBodyColor(0x210821)
				.setStripesColor(0x333333)
				.setAuthority("Binnie")
				.setGlint(true)
				.setTemperature(TemperatureType.HELLISH)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.SHADOW), 0.25f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_2);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.NETHER, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.WITHER).orElseThrow(), false));
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.NOCTURNAL, false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_shadow"), ReForestry.id("bee_darkened"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_ancient"), "priscapis", "antiquus", true, 0xf2db8f)
				.setBodyColor(0xf2db8f)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.OLD), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_ELONGATED);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_noble"), ReForestry.id("bee_diligent"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_eb_primeval"), "priscapis", "priscus", true, 0xb3a67b)
				.setBodyColor(0xb3a67b)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.OLD), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONG);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_secluded"), ReForestry.id("bee_ancient"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_prehistoric"), "priscapis", "pristinus", false, 0x6e5a40)
				.setBodyColor(0x6e5a40)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.OLD), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGER);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_eb_primeval"), ReForestry.id("bee_ancient"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_eb_relic"), "priscapis", "sapiens", true, 0x4d3e16)
				.setBodyColor(0x4d3e16)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setGlint(true)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.OLD), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGEST);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_imperial"), ReForestry.id("bee_prehistoric"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_coal"), "fosiapis", "carbo", true, 0x7a7648)
				.setBodyColor(0x7a7648)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.OLD), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.COAL), 0.08f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_eb_primeval"), ReForestry.id("bee_growing"), 8.0f);
					mutations.add(ReForestry.id("bee_rural"), ReForestry.id("bee_eb_primeval"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_resin"), "fosiapis", "lacrima", false, 0xa6731b)
				.setBodyColor(0xa6731b)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.OLD), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.RESIN), 0.05f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_miry"), ReForestry.id("bee_eb_primeval"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_oil"), "fosiapis", "lubricus", true, 0x574770)
				.setBodyColor(0x574770)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.OLD), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.OIL), 0.05f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_ocean"), ReForestry.id("bee_eb_primeval"), 8.0f);
					mutations.add(ReForestry.id("bee_frugal"), ReForestry.id("bee_eb_primeval"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_distilled"), "petrapis", "distilli", false, 0x356356)
				.setBodyColor(0x356356)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.OLD), 0.1f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_industrious"), ReForestry.id("bee_oil"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_fuel"), "petrapis", "refina", true, 0xffc003)
				.setBodyColor(0xffc003)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setGlint(true)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.OIL), 0.1f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.FUEL), 0.04f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_distilled"), ReForestry.id("bee_oil"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_creosote"), "petrapis", "creosota", true, 0x979e13)
				.setBodyColor(0x979e13)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setGlint(true)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.COAL), 0.1f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.CREOSOTE), 0.07f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_distilled"), ReForestry.id("bee_coal"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_latex"), "petrapis", "latex", true, 0x494a3e)
				.setBodyColor(0x494a3e)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setGlint(true)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.RESIN), 0.1f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.LATEX), 0.05f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_distilled"), ReForestry.id("bee_resin"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_growing"), "rustapis", "tyrelli", true, 0x5bebd8)
				.setBodyColor(0x5bebd8)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.35f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_3);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.LEAVES, false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_diligent"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_farm"), "rustapis", "ager", true, 0x75db60)
				.setBodyColor(0x75db60)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.WHEATEN).item(), 0.3f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.SEED), 0.1f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_farmerly"), ReForestry.id("bee_meadows"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_thriving"), "rustapis", "thriva", true, 0x34e37d)
				.setBodyColor(0x34e37d)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.35f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_3);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_FAST);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.LEAVES, false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_unweary"), ReForestry.id("bee_growing"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_blooming"), "rustapis", "blooma", true, 0x0abf34)
				.setBodyColor(0x0abf34)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.35f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_3);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_FASTEST);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.SAPLING, false));
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.BONEMEAL_SAPLING).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_industrious"), ReForestry.id("bee_thriving"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_sweet"), "sacchapis", "mellitus", true, 0xfc51f1)
				.setBodyColor(0xfc51f1)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.4f)
				.addProduct(Items.SUGAR, 0.1f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.SUGAR, false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_valiant"), ReForestry.id("bee_diligent"), 15.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_sugar"), "sacchapis", "dulcis", true, 0xe6d3e0)
				.setBodyColor(0xe6d3e0)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.4f)
				.addProduct(Items.SUGAR, 0.2f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.SUGAR, false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_rural"), ReForestry.id("bee_sweet"), 15.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_ripening"), "sacchapis", "ripa", true, 0xb2c75d)
				.setBodyColor(0xb2c75d)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.3f)
				.addProduct(Items.SUGAR, 0.1f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.FRUIT), 0.1f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.FRUIT, false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_sweet"), ReForestry.id("bee_growing"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_fruit"), "sacchapis", "pomum", true, 0xdb5876)
				.setBodyColor(0xdb5876)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setGlint(true)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.3f)
				.addProduct(Items.SUGAR, 0.15f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.FRUIT), 0.2f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.SUGAR, false));
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.BONEMEAL_FRUIT).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_sweet"), ReForestry.id("bee_thriving"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_alcohol"), "agriapis", "vinum", false, 0xe88a61)
				.setBodyColor(0xe88a61)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.WHEATEN).item(), 0.3f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.ALCOHOL), 0.1f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_DRUNKARD);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_farmerly"), ReForestry.id("bee_meadows"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_milk"), "agriapis", "lacteus", true, 0xe3e8e8)
				.setBodyColor(0xe3e8e8)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.WHEATEN).item(), 0.3f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.MILK), 0.1f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_farmerly"), ReForestry.id("bee_water"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_coffee"), "agriapis", "arabica", true, 0x8c5e30)
				.setBodyColor(0x8c5e30)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.WHEATEN).item(), 0.3f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.COFFEE), 0.08f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_farmerly"), ReForestry.id("bee_tropical"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_swamp"), "paludapis", "paludis", true, 0x356933)
				.setBodyColor(0x356933)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setHumidity(HumidityType.DAMP)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MOSSY).item(), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.SLOW).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_miry"), ReForestry.id("bee_water"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_eb_boggy"), "paludapis", "lama", false, 0x785c29)
				.setBodyColor(0x785c29)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MOSSY).item(), 0.3f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.SLOW).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_boggy"), ReForestry.id("bee_swamp"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_fungal"), "paludapis", "boletus", true, 0xd16200)
				.setBodyColor(0xd16200)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setGlint(true)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MOSSY).item(), 0.3f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.FUNGAL), 0.15f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.BONEMEAL_MUSHROOM).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_boggy"), ReForestry.id("bee_miry"), 8.0f);
					mutations.add(ReForestry.id("bee_boggy"), ReForestry.id("bee_swamp"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_glowstone"), "irrapis", "glowia", true, 0xe0c61b)
				.setBodyColor(0xe0c61b)
				.setStripesColor(0x9a2323)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HELLISH)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.GLOWSTONE), 0.15f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_2);
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.NETHER, false));
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_AGGRESSIVE);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_tempered"), ReForestry.id("bee_excited"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_excited"), "incitapis", "excita", true, 0xff4545)
				.setBodyColor(0xff4545)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.REDSTONE), 0.1f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.REDSTONE, false));
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.LIGHTNING).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_valiant"), ReForestry.id("bee_cultivated"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_energetic"), "incitapis", "energia", false, 0xe835c7)
				.setBodyColor(0xe835c7)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.REDSTONE), 0.12f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.REDSTONE, false));
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.LIGHTNING).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_diligent"), ReForestry.id("bee_excited"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_ecstatic"), "incitapis", "ecstatica", true, 0xaf35e8)
				.setBodyColor(0xaf35e8)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setGlint(true)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.REDSTONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.IC2ENERGY), 0.08f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.REDSTONE, false));
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.POWER).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_excited"), ReForestry.id("bee_energetic"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_artic"), "coagapis", "artica", true, 0xade0e0)
				.setBodyColor(0xade0e0)
				.setStripesColor(0xdaf5f3)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.ICY)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.FROZEN).item(), 0.25f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_4);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_wintry"), ReForestry.id("bee_diligent"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_freezing"), "coagapis", "glacia", true, 0x7be3e3)
				.setBodyColor(0x7be3e3)
				.setStripesColor(0xdaf5f3)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.ICY)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.FROZEN).item(), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.GLACIAL), 0.1f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_4);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_ocean"), ReForestry.id("bee_artic"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_copper"), "lamminapis", "cuprous", true, 0xd16308)
				.setBodyColor(0xd16308)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.COPPER), 0.06f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_wintry"), ReForestry.id("bee_mineral"), 5.0f);
					mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_mineral"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_tin"), "lamminapis", "stannus", true, 0xbdb1bd)
				.setBodyColor(0xbdb1bd)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.TIN), 0.06f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_mineral"), 5.0f);
					mutations.add(ReForestry.id("bee_tropical"), ReForestry.id("bee_mineral"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_iron"), "lamminapis", "ferrous", false, 0xa87058)
				.setBodyColor(0xa87058)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.IRON), 0.05f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_mineral"), 5.0f);
					mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_mineral"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_lead"), "lamminapis", "plumbous", true, 0xad8bab)
				.setBodyColor(0xad8bab)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.LEAD), 0.05f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_mineral"), 5.0f);
					mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_mineral"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_zinc"), "metalapis", "spelta", true, 0xedebff)
				.setBodyColor(0xedebff)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.ZINC), 0.05f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_wintry"), ReForestry.id("bee_mineral"), 5.0f);
					mutations.add(ReForestry.id("bee_tropical"), ReForestry.id("bee_mineral"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_titanium"), "metalapis", "titania", true, 0xb0aae3)
				.setBodyColor(0xb0aae3)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.TITANIUM), 0.02f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_cultivated"), ReForestry.id("bee_mineral"), 3.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_tungstate"), "metalapis", "wolfram", true, 0x131214)
				.setBodyColor(0x131214)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.TUNGSTEN), 0.01f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_mineral"), 3.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_nickel"), "metalapis", "claro", true, 0xffdefc)
				.setBodyColor(0xffdefc)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.NICKEL), 0.05f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_mineral"), 5.0f);
					mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_mineral"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_gold"), "pluriapis", "aureus", true, 0xe6cc0b)
				.setBodyColor(0xe6cc0b)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.GOLD), 0.02f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_majestic"), ReForestry.id("bee_iron"), 2.0f);
					mutations.add(ReForestry.id("bee_majestic"), ReForestry.id("bee_copper"), 2.0f);
					mutations.add(ReForestry.id("bee_majestic"), ReForestry.id("bee_nickel"), 2.0f);
					mutations.add(ReForestry.id("bee_majestic"), ReForestry.id("bee_tungstate"), 2.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_silver"), "pluriapis", "argentus", false, 0x43455b)
				.setBodyColor(0x43455b)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.SILVER), 0.02f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_majestic"), ReForestry.id("bee_zinc"), 2.0f);
					mutations.add(ReForestry.id("bee_majestic"), ReForestry.id("bee_tin"), 2.0f);
					mutations.add(ReForestry.id("bee_majestic"), ReForestry.id("bee_lead"), 2.0f);
					mutations.add(ReForestry.id("bee_majestic"), ReForestry.id("bee_titanium"), 2.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_platinum"), "pluriapis", "platina", false, 0xdbdbdb)
				.setBodyColor(0xdbdbdb)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.PLATINUM), 0.01f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_gold"), ReForestry.id("bee_silver"), 2.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_lapis"), "niphapis", "lazuli", true, 0x3d2cdb)
				.setBodyColor(0x3d2cdb)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.LAPIS), 0.05f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_water"), ReForestry.id("bee_mineral"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_sodalite"), "niphapis", "soda", false, 0x154fed)
				.setBodyColor(0x154fed)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.SODALITE), 0.04f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_lapis"), ReForestry.id("bee_diligent"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_pyrite"), "niphapis", "pyrus", false, 0xe3a739)
				.setBodyColor(0xe3a739)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.PYRITE), 0.05f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_iron"), ReForestry.id("bee_sinister"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_bauxite"), "niphapis", "bauxus", false, 0x9c6500)
				.setBodyColor(0x9c6500)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BAUXITE), 0.04f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_mineral"), ReForestry.id("bee_diligent"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_cinnabar"), "niphapis", "cinna", false, 0x47320b)
				.setBodyColor(0x47320b)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.CINNABAR), 0.04f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_mineral"), ReForestry.id("bee_sinister"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_sphalerite"), "niphapis", "spahleri", false, 0xdbd51d)
				.setBodyColor(0xdbd51d)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.SPHALERITE), 0.04f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_tin"), ReForestry.id("bee_sinister"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_emerald"), "gemmapis", "emerala", true, 0x1cff03)
				.setBodyColor(0x1cff03)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.EMERALD), 0.04f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_lapis"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_ruby"), "gemmapis", "ruba", true, 0xd60000)
				.setBodyColor(0xd60000)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.RUBY), 0.03f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_lapis"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_sapphire"), "gemmapis", "saphhira", true, 0x0a47ff)
				.setBodyColor(0x0a47ff)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.SAPPHIRE), 0.03f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_water"), ReForestry.id("bee_lapis"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_diamond"), "gemmapis", "diama", true, 0x7fbdfa)
				.setBodyColor(0x7fbdfa)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.STONE), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.DIAMOND), 0.01f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_cultivated"), ReForestry.id("bee_lapis"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_unstable"), "levapis", "levis", false, 0x3e8c34)
				.setBodyColor(0x3e8c34)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.2f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTEST);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.RADIOACTIVE).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_prehistoric"), ReForestry.id("bee_mineral"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_nuclear"), "levapis", "nucleus", false, 0x41cc2f)
				.setBodyColor(0x41cc2f)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.2f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTEST);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.RADIOACTIVE).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_unstable"), ReForestry.id("bee_iron"), 5.0f);
					mutations.add(ReForestry.id("bee_unstable"), ReForestry.id("bee_copper"), 5.0f);
					mutations.add(ReForestry.id("bee_unstable"), ReForestry.id("bee_tin"), 5.0f);
					mutations.add(ReForestry.id("bee_unstable"), ReForestry.id("bee_zinc"), 5.0f);
					mutations.add(ReForestry.id("bee_unstable"), ReForestry.id("bee_nickel"), 5.0f);
					mutations.add(ReForestry.id("bee_unstable"), ReForestry.id("bee_lead"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_radioactive"), "levapis", "fervens", false, 0x1eff00)
				.setBodyColor(0x1eff00)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.setGlint(true)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.URANIUM), 0.02f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTEST);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.RADIOACTIVE).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_nuclear"), ReForestry.id("bee_gold"), 5.0f);
					mutations.add(ReForestry.id("bee_nuclear"), ReForestry.id("bee_silver"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_yellorium"), "levapis", "yellori", true, 0xd5ed00)
				.setBodyColor(0xd5ed00)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.YELLORIUM), 0.02f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTEST);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.RADIOACTIVE).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_frugal"), ReForestry.id("bee_nuclear"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_cyanite"), "levapis", "cyanita", true, 0x0086ed)
				.setBodyColor(0x0086ed)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.CYANITE), 0.01f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTEST);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.RADIOACTIVE).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_nuclear"), ReForestry.id("bee_yellorium"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_blutonium"), "levapis", "caruthus", true, 0x1b00e6)
				.setBodyColor(0x1b00e6)
				.setStripesColor(0x999999)
				.setAuthority("Binnie")
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BARREN), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BLUTONIUM), 0.01f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.ROCK, false));
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTEST);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.RADIOACTIVE).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_cyanite"), ReForestry.id("bee_yellorium"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_malicious"), "virapis", "acerbus", true, 0x782a77)
				.setBodyColor(0x782a77)
				.setStripesColor(0x069764)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.WARM)
				.setHumidity(HumidityType.DAMP)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.25f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.JUNGLE, false));
					genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_MIASMIC);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_sinister"), ReForestry.id("bee_tropical"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_infectious"), "virapis", "contagio", true, 0xb82eb5)
				.setBodyColor(0xb82eb5)
				.setStripesColor(0x069764)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.25f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.JUNGLE, false));
					genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_MIASMIC);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_tropical"), ReForestry.id("bee_malicious"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_virulent"), "virapis", "morbus", false, 0xf013ec)
				.setBodyColor(0xf013ec)
				.setStripesColor(0x069764)
				.setAuthority("Binnie")
				.setGlint(true)
				.setTemperature(TemperatureType.WARM)
				.setHumidity(HumidityType.DAMP)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.25f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.VENOMOUS), 0.12f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.JUNGLE, false));
					genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_MIASMIC);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_malicious"), ReForestry.id("bee_infectious"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_viscous"), "viscapis", "liquidus", true, 0x09470e)
				.setBodyColor(0x09470e)
				.setStripesColor(0x069764)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.WARM)
				.setHumidity(HumidityType.DAMP)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.25f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.JUNGLE, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.ECTOPLASM).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_exotic"), ReForestry.id("bee_water"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_glutinous"), "viscapis", "glutina", true, 0x1d8c27)
				.setBodyColor(0x1d8c27)
				.setStripesColor(0x069764)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.WARM)
				.setHumidity(HumidityType.DAMP)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.25f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.JUNGLE, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_NORMAL);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.ECTOPLASM).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_exotic"), ReForestry.id("bee_viscous"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_sticky"), "viscapis", "lentesco", true, 0x17e328)
				.setBodyColor(0x17e328)
				.setStripesColor(0x069764)
				.setAuthority("Binnie")
				.setGlint(true)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.25f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.SLIME), 0.12f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.JUNGLE, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_FAST);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.ECTOPLASM).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_viscous"), ReForestry.id("bee_glutinous"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_corrosive"), "morbapis", "corrumpo", false, 0x4a5c0b)
				.setBodyColor(0x4a5c0b)
				.setStripesColor(0x069764)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.WARM)
				.setHumidity(HumidityType.DAMP)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.2f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.JUNGLE, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_FAST);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.ACID).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_malicious"), ReForestry.id("bee_viscous"), 10.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_caustic"), "morbapis", "torrens", true, 0x84a11d)
				.setBodyColor(0x84a11d)
				.setStripesColor(0x069764)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.WARM)
				.setHumidity(HumidityType.DAMP)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.25f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.ACIDIC), 0.03f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.JUNGLE, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_FAST);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.ACID).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_fiendish"), ReForestry.id("bee_corrosive"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_acidic"), "morbapis", "acidus", true, 0xc0f016)
				.setBodyColor(0xc0f016)
				.setStripesColor(0x069764)
				.setAuthority("Binnie")
				.setGlint(true)
				.setTemperature(TemperatureType.WARM)
				.setHumidity(HumidityType.DAMP)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.ACIDIC), 0.16f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.JUNGLE, false));
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_FAST);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.ACID).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_corrosive"), ReForestry.id("bee_caustic"), 4.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_ink"), "aquapis", "atramentum", true, 0x0e1447)
				.setBodyColor(0x0e1447)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setHumidity(HumidityType.DAMP)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.WATER), 0.3f)
				.addSpecialty(Items.INK_SAC, 0.1f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWEST);
					genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.WATER, false));
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.WATER).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_black"), ReForestry.id("bee_ocean"), 8.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_red"), "primapis", "rubra", true, 0xff0000)
				.setBodyColor(0xff0000)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.RED), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_valiant"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_yellow"), "primapis", "fulvus", true, 0xffdd00)
				.setBodyColor(0xffdd00)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.YELLOW), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_valiant"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_blue"), "primapis", "caeruleus", true, 0x0022ff)
				.setBodyColor(0x0022ff)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BLUE), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_valiant"), ReForestry.id("bee_water"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_green"), "primapis", "prasinus", true, 0x009900)
				.setBodyColor(0x009900)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.GREEN), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_tropical"), ReForestry.id("bee_valiant"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_black"), "primapis", "niger", true, 0x575757)
				.setBodyColor(0x575757)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BLACK), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_valiant"), ReForestry.id("bee_rock"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_white"), "primapis", "albus", true, 0xffffff)
				.setBodyColor(0xffffff)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.WHITE), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_wintry"), ReForestry.id("bee_valiant"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_brown"), "primapis", "fuscus", true, 0x5c350f)
				.setBodyColor(0x5c350f)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.BROWN), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_valiant"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_orange"), "secapis", "flammeus", true, 0xff9d00)
				.setBodyColor(0xff9d00)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.ORANGE), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_red"), ReForestry.id("bee_yellow"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_cyan"), "secapis", "cyana", true, 0x00ffe5)
				.setBodyColor(0x00ffe5)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.CYAN), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_green"), ReForestry.id("bee_blue"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_purple"), "secapis", "purpureus", true, 0xae00ff)
				.setBodyColor(0xae00ff)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.PURPLE), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_red"), ReForestry.id("bee_blue"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_gray"), "secapis", "ravus", true, 0xbababa)
				.setBodyColor(0xbababa)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.GRAY), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_black"), ReForestry.id("bee_white"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_lightblue"), "secapis", "aqua", true, 0x009dff)
				.setBodyColor(0x009dff)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.LIGHTBLUE), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_blue"), ReForestry.id("bee_white"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_pink"), "secapis", "rosaceus", true, 0xff80df)
				.setBodyColor(0xff80df)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.PINK), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_red"), ReForestry.id("bee_white"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_limegreen"), "secapis", "lima", true, 0x00ff08)
				.setBodyColor(0x00ff08)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.LIMEGREEN), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_green"), ReForestry.id("bee_white"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_magenta"), "tertiapis", "fuchsia", true, 0xff00cc)
				.setBodyColor(0xff00cc)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.MAGENTA), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_purple"), ReForestry.id("bee_pink"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_lightgray"), "tertiapis", "canus", true, 0xc9c9c9)
				.setBodyColor(0xc9c9c9)
				.setStripesColor(0x8cff00)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.75f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.LIGHTGRAY), 0.25f)
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_gray"), ReForestry.id("bee_white"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_celebratory"), "festapis", "celeba", true, 0xfa0a6a)
				.setBodyColor(0xfa0a6a)
				.setStripesColor(0xd40000)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.ICY)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.FIREWORKS).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_austere"), ReForestry.id("bee_excited"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_jaded"), "eftebeapis", "jadeca", true, 0xfa0a6a)
				.setBodyColor(0xfa0a6a)
				.setStripesColor(0xdc8aeb)
				.setAuthority("Binnie")
				.setGlint(true)
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.3f)
				.addSpecialty(ApicultureItems.POLLEN_CLUSTER.get(EnumPollenCluster.NORMAL).item(), 0.2f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.PURPLE), 0.15f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
					genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_BEATIFIC);
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_4);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_MAXIMUM);
					genome.set(BeeChromosomes.TERRITORY, ForestryAlleles.TERRITORY_LARGEST);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_ended"), ReForestry.id("bee_eb_relic"), 2.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_hazardous"), "modapis", "infensus", true, 0xb06c28)
				.setBodyColor(0xb06c28)
				.setStripesColor(0xffdc16)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.HOT)
				.setHumidity(HumidityType.ARID)
				.addProduct(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.SALTPETER), 0.12f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWEST);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGER);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_2);
					genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_CREEPER);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_austere"), ReForestry.id("bee_desolate"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_quantum"), "quantapis", "quanta", true, 0x37c5db)
				.setBodyColor(0x37c5db)
				.setStripesColor(0xd50fdb)
				.setAuthority("Binnie")
				.addProduct(Items.QUARTZ, 0.25f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.CERTUS), 0.15f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.ENDERPEARL), 0.15f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_2);
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWEST);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTER);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_NONE);
					genome.set(BeeChromosomes.TERRITORY, ForestryAlleles.TERRITORY_AVERAGE);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.VANILLA, false));
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.DIURNAL, false));
					genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_NONE);
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_spectral"), ReForestry.id("bee_spatial"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_unusual"), "quantapis", "daniella", true, 0x59a4ba)
				.setBodyColor(0x59a4ba)
				.setStripesColor(0xbaa2eb)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.COLD)
				.addProduct(Items.QUARTZ, 0.25f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGER);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.TERRITORY, ForestryAlleles.TERRITORY_LARGE);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.END, false));
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.GRAVITY).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_secluded"), ReForestry.id("bee_ended"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_spatial"), "quantapis", "spatia", true, 0x4c1be0)
				.setBodyColor(0x4c1be0)
				.setStripesColor(0xa44ecc)
				.setAuthority("Binnie")
				.setTemperature(TemperatureType.COLD)
				.addProduct(Items.QUARTZ, 0.25f)
				.addSpecialty(ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.CERTUS), 0.05f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGER);
					genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
					genome.set(BeeChromosomes.TERRITORY, ForestryAlleles.TERRITORY_LARGE);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.END, false));
					genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
					genome.set(BeeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.GRAVITY).orElseThrow(), false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_hermitic"), ReForestry.id("bee_unusual"), 5.0f);
				});

		registration.registerSpecies(ReForestry.id("bee_mystical"), "botaniapis", "mystica", true, 0x46a722)
				.setBodyColor(0x46a722)
				.setStripesColor(0xffffff)
				.setAuthority("Binnie")
				.addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.DRIPPING).item(), 0.2f)
				.setGenome(genome -> {
					genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
					genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
					genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
					genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.MYSTICAL, false));
				})
				.addMutations(mutations -> {
					mutations.add(ReForestry.id("bee_noble"), ReForestry.id("bee_monastic"), 5.0f);
				});

		registration.modifySpecies(ReForestry.id("bee_common"), species -> species.addMutations(mutations -> {
			mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_water"), 15.0f);
			mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_water"), 15.0f);
			mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_water"), 15.0f);
			mutations.add(ReForestry.id("bee_tropical"), ReForestry.id("bee_water"), 15.0f);
			mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_water"), 15.0f);
			mutations.add(ReForestry.id("bee_wintry"), ReForestry.id("bee_water"), 15.0f);
			mutations.add(ReForestry.id("bee_rock"), ReForestry.id("bee_water"), 15.0f);
			mutations.add(ReForestry.id("bee_basalt"), ReForestry.id("bee_water"), 15.0f);
			mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_rock"), 15.0f);
			mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_rock"), 15.0f);
			mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_rock"), 15.0f);
			mutations.add(ReForestry.id("bee_tropical"), ReForestry.id("bee_rock"), 15.0f);
			mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_rock"), 15.0f);
			mutations.add(ReForestry.id("bee_wintry"), ReForestry.id("bee_rock"), 15.0f);
			mutations.add(ReForestry.id("bee_basalt"), ReForestry.id("bee_rock"), 15.0f);
			mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_basalt"), 15.0f);
			mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_basalt"), 15.0f);
			mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_basalt"), 15.0f);
			mutations.add(ReForestry.id("bee_tropical"), ReForestry.id("bee_basalt"), 15.0f);
			mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_basalt"), 15.0f);
			mutations.add(ReForestry.id("bee_wintry"), ReForestry.id("bee_basalt"), 15.0f);
			mutations.add(ReForestry.id("bee_water"), ReForestry.id("bee_marble"), 15.0f);
			mutations.add(ReForestry.id("bee_rock"), ReForestry.id("bee_marble"), 15.0f);
			mutations.add(ReForestry.id("bee_marble"), ReForestry.id("bee_forest"), 15.0f);
			mutations.add(ReForestry.id("bee_marble"), ReForestry.id("bee_meadows"), 15.0f);
			mutations.add(ReForestry.id("bee_marble"), ReForestry.id("bee_modest"), 15.0f);
			mutations.add(ReForestry.id("bee_marble"), ReForestry.id("bee_tropical"), 15.0f);
			mutations.add(ReForestry.id("bee_marble"), ReForestry.id("bee_marshy"), 15.0f);
		}));

		registration.modifySpecies(ReForestry.id("bee_cultivated"), species -> species.addMutations(mutations -> {
			mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_water"), 12.0f);
			mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_rock"), 12.0f);
			mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_basalt"), 12.0f);
			mutations.add(ReForestry.id("bee_marble"), ReForestry.id("bee_common"), 12.0f);
		}));

		registration.modifySpecies(ReForestry.id("bee_fiendish"), species -> species.addMutations(mutations -> {
			mutations.add(ReForestry.id("bee_sinister"), ReForestry.id("bee_basalt"), 40.0f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_NETHER);
		}));

		registration.modifySpecies(ReForestry.id("bee_sinister"), species -> species.addMutations(mutations -> {
			mutations.add(ReForestry.id("bee_cultivated"), ReForestry.id("bee_basalt"), 60.0f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_NETHER);
		}));
	}
}
