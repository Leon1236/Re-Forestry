package com.leon1236.reforestry.extratrees.genetics;

import net.minecraft.network.chat.TextColor;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.ForestryTaxa;
import com.leon1236.reforestry.api.plugin.IGeneticRegistration;
import com.leon1236.reforestry.api.plugin.ILepidopterologyRegistration;

public final class ExtraTreesMothSpecies {
	private ExtraTreesMothSpecies() {
	}

	public static void registerTaxa(IGeneticRegistration registration) {
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "limenitis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "apatura");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "vanessa");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "aglais");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "inachis");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "lasiommata");
		registration.defineTaxon(ForestryTaxa.FAMILY_SWALLOWTAIL_BUTTERFLIES, "atrophaneura");
		registration.defineTaxon(ForestryTaxa.FAMILY_SWALLOWTAIL_BUTTERFLIES, "teinopalpus");
		registration.defineTaxon(ForestryTaxa.FAMILY_SWALLOWTAIL_BUTTERFLIES, "troides");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "euphydryas");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "boloria");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "issoria");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "erebia");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "pyronia");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "maniola");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "coenonympha");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "aphantopus");
		registration.defineTaxon(ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES, "melanargia");
	}

	public static void register(ILepidopterologyRegistration butterflies) {
		butterflies.registerSpecies(ReForestry.id("moth_white_admiral"), "limenitis", "camilla", true, TextColor.fromRgb(0xfafafa), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_purple_emperor"), "apatura", "iris", true, TextColor.fromRgb(0x4232c6), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_red_admiral"), "vanessa", "atalanta", true, TextColor.fromRgb(0xe66f44), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_painted_lady"), "vanessa", "cardui", true, TextColor.fromRgb(0xeda048), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_small_tortoiseshell"), "aglais", "urticae", true, TextColor.fromRgb(0xea750b), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_camberwell_beauty"), "aglais", "antiopa", true, TextColor.fromRgb(0x95a2cc), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_peacock"), "inachis", "io", true, TextColor.fromRgb(0xd33802), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_wall"), "lasiommata", "megera", true, TextColor.fromRgb(0xefae1e), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_crimson_rose"), "atrophaneura", "hector", true, TextColor.fromRgb(0xff627b), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_kaiser_i_hind"), "teinopalpus", "imperialis", true, TextColor.fromRgb(0x77a040), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_golden_birdwing"), "troides", "aeacus", true, TextColor.fromRgb(0xf9dc1e), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_marsh_fritillary"), "euphydryas", "aurinia", true, TextColor.fromRgb(0xff8c00), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_pearl_bordered_fritillary"), "boloria", "euphrosyne", true, TextColor.fromRgb(0xff8b03), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_queen_of_spain_fritillary"), "issoria", "lathonia", true, TextColor.fromRgb(0xffd13f), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_speckled_wood"), "pararge", "aegeria", true, TextColor.fromRgb(0xf5f88d), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_scotch_angus"), "erebia", "aethiops", true, TextColor.fromRgb(0xc25423), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_gatekeeper"), "pyronia", "tithonus", true, TextColor.fromRgb(0xfac32a), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_meadow_brown"), "maniola", "jurtina", true, TextColor.fromRgb(0xe39519), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_small_heath"), "coenonympha", "pamphilus", true, TextColor.fromRgb(0xffa632), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_ringlet"), "aphantopus", "hyperantus", true, TextColor.fromRgb(0x975d37), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_monarch"), "danaus", "plexippus", true, TextColor.fromRgb(0xffb206), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");

		butterflies.registerSpecies(ReForestry.id("moth_marbled_white"), "melanargia", "galathea", true, TextColor.fromRgb(0xececec), 0.5f)
			.setMoth(true)
			.setAuthority("Binnie");
	}
}
