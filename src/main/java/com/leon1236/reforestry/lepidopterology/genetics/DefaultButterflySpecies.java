package com.leon1236.reforestry.lepidopterology.genetics;

import net.minecraft.network.chat.TextColor;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.lepidopterology.ForestryButterflySpecies;
import com.leon1236.reforestry.api.plugin.ILepidopterologyRegistration;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;

import static com.leon1236.reforestry.api.genetics.ForestryTaxa.*;

public final class DefaultButterflySpecies {
	private DefaultButterflySpecies() {
	}

	public static void register(ILepidopterologyRegistration butterflies) {

		butterflies.registerSpecies(ForestryButterflySpecies.CABBAGE_WHITE, GENUS_PIERIS, SPECIES_CABBAGE_WHITE, true, TextColor.fromRgb(0xccffee), 1.0f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_AVERAGE);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.BRIMSTONE, GENUS_GONEPTERYX, SPECIES_BRIMSTONE, true, TextColor.fromRgb(0xf0ee38), 1.0f);

		butterflies.registerSpecies(ForestryButterflySpecies.AURORA, GENUS_ANTHOCHARIS, SPECIES_AURORA, true, TextColor.fromRgb(0xe34f05), 0.5f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_SMALLER);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.CLOUDED_YELLOW, GENUS_COLIAS, SPECIES_CLOUDED_YELLOW, true, TextColor.fromRgb(0xd77e04), 0.5f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.PALAENO_SULPHUR, GENUS_COLIAS, SPECIES_PALAENO_SULPHUR, true, TextColor.fromRgb(0xf8fba3), 0.4f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.RESEDA, GENUS_PONTIA, SPECIES_RESEDA, true, TextColor.fromRgb(0x747d48), 0.3f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.SPRING_AZURE, GENUS_CELASTRINA, SPECIES_SPRING_AZURE, true, TextColor.fromRgb(0xb8cae2), 0.3f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_SMALLER);
				genome.set(ButterflyChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);

			});

		butterflies.registerSpecies(ForestryButterflySpecies.GOZORA_AZURE, GENUS_CELASTRINA, SPECIES_GOZORA_AZURE, true, TextColor.fromRgb(0x6870e7), 0.2f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_SMALLER);
				genome.set(ButterflyChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.CITRUS_SWALLOWTAIL, GENUS_PAPILIO, SPECIES_CITRUS_SWALLOWTAIL, false, TextColor.fromRgb(0xeae389), 1.0f)
			.setTemperature(TemperatureType.WARM)
			.setHumidity(HumidityType.DAMP)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_LARGE);
				genome.set(ButterflyChromosomes.FERTILITY, ForestryAlleles.FERTILITY_10);
				genome.set(ButterflyChromosomes.METABOLISM, ForestryAlleles.METABOLISM_FASTER);
				genome.set(ButterflyChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTER);
				genome.set(ButterflyChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
				genome.set(ButterflyChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.EMERALD_PEACOCK, GENUS_PAPILIO, SPECIES_EMERALD_PEACOCK, true, TextColor.fromRgb(0x7cfe80), 0.1f)
			.setTemperature(TemperatureType.WARM)
			.setHumidity(HumidityType.DAMP)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_LARGE);
				genome.set(ButterflyChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
				genome.set(ButterflyChromosomes.FERTILITY, ForestryAlleles.FERTILITY_5);
				genome.set(ButterflyChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
				genome.set(ButterflyChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.THOAS_SWALLOWTAIL, GENUS_PAPILIO, SPECIES_THOAS_SWALLOWTAIL, false, TextColor.fromRgb(0xeac783), 0.2f)
			.setTemperature(TemperatureType.WARM)
			.setHumidity(HumidityType.DAMP)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_LARGE);
				genome.set(ButterflyChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTEST);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.SPICEBUSH_SWALLOWTAIL, GENUS_PAPILIO, SPECIES_SPICEBUSH_SWALLOWTAIL, true, TextColor.fromRgb(0xeefeff), 0.5f)
			.setTemperature(TemperatureType.WARM)
			.setHumidity(HumidityType.DAMP)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_AVERAGE);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.BLACK_SWALLOWTAIL, GENUS_PAPILIO, SPECIES_BLACK_SWALLOWTAIL, true, TextColor.fromRgb(0xeac783), 1.0f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_LARGE);
				genome.set(ButterflyChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTER);
				genome.set(ButterflyChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
				genome.set(ButterflyChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.ZEBRA_SWALLOWTAIL, GENUS_PROTOGRAPHIUM, SPECIES_ZEBRA_SWALLOWTAIL, true, TextColor.fromRgb(0xeafeff), 0.5f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_AVERAGE);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.GLASSWING, GENUS_GRETA, SPECIES_GLASSWING, true, TextColor.fromRgb(0x583732), 0.1f)
			.setTemperature(TemperatureType.WARM)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_SMALLER);
				genome.set(ButterflyChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
				genome.set(ButterflyChromosomes.FERTILITY, ForestryAlleles.FERTILITY_5);
				genome.set(ButterflyChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.SPECKLED_WOOD, GENUS_PARARGE, SPECIES_SPECKLED_WOOD, true, TextColor.fromRgb(0x947245), 1.0f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.FERTILITY, ForestryAlleles.FERTILITY_2);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.MADEIRAN_SPECKLED_WOOD, GENUS_PARARGE, SPECIES_MADEIRAN_SPECKLED_WOOD, true, TextColor.fromRgb(0x402919), 0.5f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.FERTILITY, ForestryAlleles.FERTILITY_2);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.CANARY_SPECKLED_WOOD, GENUS_PARARGE, SPECIES_CANARY_SPECKLED_WOOD, true, TextColor.fromRgb(0x51372a), 0.5f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.FERTILITY, ForestryAlleles.FERTILITY_2);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.MENELAUS_BLUE_MORPHO, GENUS_MORPHO, SPECIES_MENELAUS_BLUE_MORPHO, true, TextColor.fromRgb(0x72e1fd), 0.5f)
			.setTemperature(TemperatureType.WARM)
			.setHumidity(HumidityType.DAMP)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_LARGER);
				genome.set(ButterflyChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTEST);
				genome.set(ButterflyChromosomes.FERTILITY, ForestryAlleles.FERTILITY_2);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.PELEIDES_BLUE_MORPHO, GENUS_MORPHO, SPECIES_PELEIDES_BLUE_MORPHO, true, TextColor.fromRgb(0x6ecce8), 0.25f)
			.setTemperature(TemperatureType.WARM)
			.setHumidity(HumidityType.DAMP)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_LARGER);
				genome.set(ButterflyChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTEST);
				genome.set(ButterflyChromosomes.FERTILITY, ForestryAlleles.FERTILITY_2);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.RHETENOR_BLUE_MORPHO, GENUS_MORPHO, SPECIES_RHETENOR_BLUE_MORPHO, true, TextColor.fromRgb(0x00bef8), 0.1f)
			.setTemperature(TemperatureType.WARM)
			.setHumidity(HumidityType.DAMP)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_LARGER);
				genome.set(ButterflyChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTEST);
				genome.set(ButterflyChromosomes.FERTILITY, ForestryAlleles.FERTILITY_2);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.COMMA, GENUS_POLYGONIA, SPECIES_COMMA, true, TextColor.fromRgb(0xf89505), 0.3f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.BATESIA, GENUS_BATESIA, SPECIES_BATESIA, true, TextColor.fromRgb(0xfe7763), 0.3f)
			.setTemperature(TemperatureType.WARM)
			.setHumidity(HumidityType.DAMP)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_LARGE);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.BLUE_WING, GENUS_MYSCELIA, SPECIES_BLUE_WING, true, TextColor.fromRgb(0x3a93cc), 0.3f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_AVERAGE);
				genome.set(ButterflyChromosomes.METABOLISM, ForestryAlleles.METABOLISM_NORMAL);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.MONARCH, GENUS_DANAUS, SPECIES_MONARCH, true, TextColor.fromRgb(0xffa722), 0.2f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_AVERAGE);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.BLUE_DUKE, GENUS_BASSARONA, SPECIES_BLUE_DUKE, true, TextColor.fromRgb(0x304240), 0.5f)
			.setTemperature(TemperatureType.COLD)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.GLASSY_TIGER, GENUS_PARANTICA, SPECIES_GLASSY_TIGER, true, TextColor.fromRgb(0x5b3935), 0.3f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_AVERAGE);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.POSTMAN, GENUS_HELICONIUS, SPECIES_POSTMAN, true, TextColor.fromRgb(0xf7302d), 0.3f);

		butterflies.registerSpecies(ForestryButterflySpecies.MALACHITE, GENUS_SIPROETA, SPECIES_MALACHITE, true, TextColor.fromRgb(0xbdff53), 0.5f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_AVERAGE);
				genome.set(ButterflyChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
				genome.set(ButterflyChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.LEOPARD_LACEWING, GENUS_CETHOSIA, SPECIES_LEOPARD_LACEWING, true, TextColor.fromRgb(0xfb8a06), 0.7f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
				genome.set(ButterflyChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.DIANA_FRITILLARY, GENUS_SPEYERIA, SPECIES_DIANA_FRITILLARY, true, TextColor.fromRgb(0xffac05), 0.6f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_SMALLER);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.BRIMSTONE_MOTH, GENUS_OPISTHOGRAPTIS, SPECIES_BRIMSTONE_MOTH, true, TextColor.fromRgb(0xffea40), 1.0f);

		butterflies.registerSpecies(ForestryButterflySpecies.LATTICED_HEATH, GENUS_CHIASMIA, SPECIES_LATTICED_HEATH, true, TextColor.fromRgb(0xf2f0be), 0.5f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_SMALLEST);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.ATLAS, GENUS_ATTACUS, SPECIES_ATLAS, false, TextColor.fromRgb(0xd96e3d), 0.1f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_LARGEST);
			});

		butterflies.registerSpecies(ForestryButterflySpecies.BOMBYX_MORI, GENUS_BOMBYX, SPECIES_BOMBYX_MORI, false, TextColor.fromRgb(0xDADADA), 0.1f)
			.setGenome(genome -> {
				genome.set(ButterflyChromosomes.SIZE, ForestryAlleles.SIZE_SMALLEST);
				genome.set(ButterflyChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
				genome.set(ButterflyChromosomes.METABOLISM, ForestryAlleles.METABOLISM_SLOW);
				genome.set(ButterflyChromosomes.COCOON, AlleleManager.INSTANCE.registryAllele(ButterflyChromosomes.SILK_COCOON, true));
			})
			.setAuthority("Nedelosk")
			.addMutations(mutations -> mutations.add(ForestryButterflySpecies.LATTICED_HEATH, ForestryButterflySpecies.BRIMSTONE, 7f));
	}
}
