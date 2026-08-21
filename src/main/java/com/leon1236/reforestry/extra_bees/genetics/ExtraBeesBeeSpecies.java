package com.leon1236.reforestry.extra_bees.genetics;

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
import com.leon1236.reforestry.core.genetics.ForestryAlleles;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesItems;
import com.leon1236.reforestry.extra_bees.items.EnumExtraBeeComb;

public final class ExtraBeesBeeSpecies {
	private ExtraBeesBeeSpecies() {
	}

	public static void registerTaxa(IGeneticRegistration registration) {
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "aquapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "grecapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "infenapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "irrapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "pullapis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BEES, "vacapis");
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
