package com.leon1236.reforestry.extratrees.fluids;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributeHandler;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.fluids.FeatureFluid;
import com.leon1236.reforestry.core.fluids.ForestryFluidProperties;

public enum ExtraTreesFluids {
	APPLE("juice_apple", 0xFFCA32),
	APRICOT("juice_apricot", 0xFFB51E),
	BANANA("juice_banana", 0xE9D483),
	CHERRY("juice_cherry", 0xC70B1F),
	ELDERBERRY("juice_elderberry", 0x402629),
	LEMON("juice_lemon", 0xDEDA52),
	LIME("juice_lime", 0xB9CE6F),
	ORANGE("juice_orange", 0xF9A22F),
	PEACH("juice_peach", 0xFAC55D),
	PLUM("juice_plum", 0xA11F11),
	CARROT("juice_carrot", 0xFB8E17),
	TOMATO("juice_tomato", 0xC2442E),
	CRANBERRY("juice_cranberry", 0xC52735),
	GRAPEFRUIT("juice_grapefruit", 0xF29255),
	OLIVE("juice_olive", 0xF4C900),
	PINEAPPLE("juice_pineapple", 0xE7C547),
	PEAR("juice_pear", 0xD8BF65),
	WHITE_GRAPE("juice_white_grape", 0xFBE379),
	RED_GRAPE("juice_red_grape", 0x952934),
	ALCOHOL_APPLE("cider_apple", 0xFABE3C),
	ALCOHOL_APRICOT("wine_apricot", 0xF0CF36),
	ALCOHOL_BANANA("wine_banana", 0xE4C84D),
	ALCOHOL_CHERRY("wine_cherry", 0xAB0416),
	ALCOHOL_ELDERBERRY("wine_elderberry", 0x950001),
	ALCOHOL_PEACH("cider_peach", 0xEA661B),
	ALCOHOL_PEAR("ciderpear", 0xE5D067),
	ALCOHOL_PLUM("wine_plum", 0xB81408),
	ALCOHOL_CARROT("wine_carrot", 0xF77D02),
	WHITE_WINE("wine_white", 0xEDDA95),
	RED_WINE("wine_red", 0x750B0B),
	SPARKLING_WINE("wine_sparkling", 0xFEF7BE),
	AGAVE("wine_agave", 0xD4AE64),
	POTATO("fermented_potatoes", 0xB78950),
	CITRUS("wine_citrus", 0xFFFF00),
	ALCOHOL_CRANBERRY("wine_cranberry", 0xB10002),
	ALCOHOL_PINEAPPLE("wine_pineapple", 0xE0AC36),
	ALCOHOL_TOMATO("wine_tomato", 0xBE1A19),
	FRUIT("alcohol_fruit", 0xFABE3C),
	ALE("beer_ale", 0xC63A21),
	LAGER("beer_lager", 0xE97C05),
	WHEAT_BEER("beer_wheat", 0xDB6E08),
	RYE_BEER("beer_rye", 0xA55827),
	CORN_BEER("beer_corn", 0xCCA424),
	STOUT("beer_stout", 0x592901),
	BARLEY("mash_grain", 0xC63A21),
	WHEAT("mash_wheat", 0xC63A21),
	RYE("mash_rye", 0xA55827),
	CORN("mash_corn", 0xCCA424),
	ALMOND("liqueur_almond", 0xE45D2F),
	LIQUEUR_ORANGE("liqueur_orange", 0xF98900),
	LIQUEUR_BANANA("liqueur_banana", 0xF8C200),
	CHOCOLATE("liqueur_chocolate", 0xC14B20),
	MINT("liqueur_mint", 0x29C67C),
	HAZELNUT("liqueur_hazelnut", 0xED982B),
	CINNAMON("liqueur_cinnamon", 0xE55000),
	COFFEE("liqueur_coffee", 0x964319),
	MELON("liqueur_melon", 0xB0C231),
	ANISE("liqueur_anise", 0xDAE1E9),
	LIQUEUR_PEACH("liqueur_peach", 0xFE9560),
	LIQUEUR_LEMON("liqueur_lemon", 0xF8E46D),
	HERBAL("liqueur_herbal", 0xFED501),
	LIQUEUR_CHERRY("liqueur_cherry", 0xD71901),
	BLACKCURRANT("liqueur_blackcurrant", 0x6A3D6D),
	BLACKBERRY("liqueur_blackberry", 0x68554D),
	RASPBERRY("liqueur_raspberry", 0x9B0300),
	NEUTRAL_SPIRIT("spirit_neutral", 0xFFFFFF),
	VODKA("vodka", 0xF4F5F7),
	WHITE_RUM("rum_white", 0xE6E5E4),
	DARK_RUM("rum_dark", 0xA82200),
	WHISKEY("whiskey", 0xCF6F00),
	CORN_WHISKEY("whiskey_corn", 0x961201),
	RYE_WHISKEY("whiskey_rye", 0xF57328),
	WHEAT_WHISKEY("whiskey_wheat", 0xE48612),
	FORTIFIED_WINE("wine_fortified", 0xED921F),
	TEQUILA("tequila", 0xF5E9C0),
	BRANDY("brandy_grape", 0xF79F20),
	APPLE_BRANDY("brandy_apple", 0xE4AA3E),
	PEAR_BRANDY("brandy_pear", 0xFEC633),
	APRICOT_BRANDY("brandy_apricot", 0xCB7F43),
	PLUM_BRANDY("brandy_plum", 0x912311),
	CHERRY_BRANDY("brandy_cherry", 0x830B1E),
	ELDERBERRY_BRANDY("brandy_elderberry", 0xBE2B47),
	CITRUS_BRANDY("brandy_citrus", 0xCB7F43),
	FRUIT_BRANDY("brandy_fruit", 0xE4AA3E),
	CACHACA("spirit_sugarcane", 0xE9F0CF),
	GIN("spirit_gin", 0xF6F6F6),
	APPLE_LIQUOR("liquor_apple", 0xCCCCCC),
	PEAR_LIQUOR("liquor_pear", 0xCCCCCC),
	CHERRY_LIQUOR("liquor_cherry", 0xCCCCCC),
	ELDERBERRY_LIQUOR("liquor_elderberry", 0xCCCCCC),
	APRICOT_LIQUOR("liquor_apricot", 0xCCCCCC),
	FRUIT_LIQUOR("liquor_fruit", 0xCCCCCC),
	CARBONATED_WATER("water_carbonated", 0xCCCCFF),
	TONIC_WATER("water_tonic", 0xCCCCFF),
	CREAM("cream", 0xEAEADE),
	GINGER_ALE("ginger_ale", 0xFFFFFF),
	MISC_COFFEE("coffee", 0x5A3105),
	SUGAR_SYRUP("syrup_simple", 0xF5F8F1),
	AGAVE_NECTAR("syrup_agave", 0xCF7E25),
	GRENADINE_SYRUP("syrup_grenadine", 0xF44965),
	SAP("sap", 0xBE7542),
	RESIN("resin", 0xC96800),
	LATEX("latex", 0xD8DBC9),
	TURPENTINE("turpentine", 0x79533A);

	private final FeatureFluid feature;

	ExtraTreesFluids(String pathId, int particleColor) {
		this.feature = FeatureFluid.create(
				ReForestry.id("extra_trees"),
				pathId,
				ForestryFluidProperties.builder().particleColor(particleColor).build());
	}

	public FeatureFluid getFeature() {
		return this.feature;
	}

	public String pathId() {
		return this.feature.getName();
	}

	public Fluid getFluid() {
		return this.feature.source();
	}

	public Fluid getFlowing() {
		return this.feature.flowing();
	}

	public BucketItem getBucket() {
		return this.feature.bucket();
	}

	public boolean is(@Nullable Fluid fluid) {
		return fluid != null && (getFluid() == fluid || getFlowing() == fluid);
	}

	public static ExtraTreesFluids byPathId(String pathId) {
		for (ExtraTreesFluids fluid : values()) {
			if (fluid.pathId().equals(pathId)) {
				return fluid;
			}
		}
		throw new IllegalArgumentException("Unknown Extra Trees fluid: " + pathId);
	}

	public static void init() {
		for (ExtraTreesFluids fluid : values()) {
			ForestryFluidProperties properties = fluid.feature.properties();
			FluidVariantAttributeHandler handler = new FluidVariantAttributeHandler() {
				@Override
				public int getTemperature(FluidVariant variant) {
					return properties.temperature();
				}

				@Override
				public int getViscosity(FluidVariant variant, @Nullable Level level) {
					return Math.max(1, properties.viscosity());
				}
			};
			FluidVariantAttributes.register(fluid.getFluid(), handler);
			FluidVariantAttributes.register(fluid.getFlowing(), handler);
		}
	}
}
