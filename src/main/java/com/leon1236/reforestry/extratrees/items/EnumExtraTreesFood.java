package com.leon1236.reforestry.extratrees.items;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumExtraTreesFood implements IItemSubtype {
	CRABAPPLE("crabapple", 2, 0.2f),
	ORANGE("orange", 4, 0.4f),
	KUMQUAT("kumquat", 2, 0.2f),
	LIME("lime", 2, 0.2f),
	WILD_CHERRY("wild_cherry", 2, 0.2f),
	SOUR_CHERRY("sour_cherry", 2, 0.2f),
	BLACK_CHERRY("black_cherry", 2, 0.2f),
	BLACKTHORN("blackthorn", 3, 0.3f),
	CHERRY_PLUM("cherry_plum", 3, 0.3f),
	ALMOND("almond", 1, 0.1f),
	APRICOT("apricot", 4, 0.4f),
	GRAPEFRUIT("grapefruit", 4, 0.4f),
	PEACH("peach", 4, 0.4f),
	SATSUMA("satsuma", 3, 0.3f),
	BUDDHA_HAND("buddha_hand", 3, 0.3f),
	CITRON("citron", 3, 0.3f),
	FINGER_LIME("finger_lime", 3, 0.3f),
	KEY_LIME("key_lime", 2, 0.2f),
	MANDERIN("manderin", 3, 0.3f),
	NECTARINE("nectarine", 3, 0.3f),
	POMELO("pomelo", 3, 0.3f),
	TANGERINE("tangerine", 3, 0.3f),
	PEAR("pear", 4, 0.4f),
	SAND_PEAR("sand_pear", 2, 0.2f),
	HAZELNUT("hazelnut", 2, 0.2f),
	BUTTERNUT("butternut", 1, 0.1f),
	BEECHNUT("beechnut", 0, 0.0f),
	PECAN("pecan", 0, 0.0f),
	BANANA("banana", 4, 0.4f),
	RED_BANANA("red_banana", 4, 0.4f),
	PLANTAIN("plantain", 2, 0.2f),
	BRAZIL_NUT("brazil_nut", 0, 0.0f),
	FIG("fig", 2, 0.2f),
	ACORN("acorn", 0, 0.0f),
	ELDERBERRY("elderberry", 1, 0.1f),
	OLIVE("olive", 1, 0.1f),
	GINGKO_NUT("gingko_nut", 1, 0.1f),
	COFFEE("coffee", 0, 0.0f),
	OSANGE_ORANGE("osange_orange", 1, 0.1f),
	CLOVE("clove", 0, 0.0f),
	BLACKCURRANT("blackcurrant", 2, 0.2f),
	REDCURRANT("redcurrant", 2, 0.2f),
	BLACKBERRY("blackberry", 2, 0.2f),
	RASPBERRY("raspberry", 2, 0.2f),
	BLUEBERRY("blueberry", 2, 0.2f),
	CRANBERRY("cranberry", 2, 0.2f),
	JUNIPER("juniper", 0, 0.0f),
	GOOSEBERRY("gooseberry", 2, 0.2f),
	GOLDEN_RASPBERRY("golden_raspberry", 2, 0.2f),
	COCONUT("coconut", 2, 0.2f),
	CASHEW("cashew", 0, 0.0f),
	AVOCADO("avocado", 2, 0.2f),
	NUTMEG("nutmeg", 0, 0.0f),
	ALLSPICE("allspice", 0, 0.0f),
	CHILLI("chilli", 2, 0.2f),
	STAR_ANISE("star_anise", 0, 0.0f),
	MANGO("mango", 4, 0.4f),
	STARFRUIT("starfruit", 2, 0.2f),
	CANDLENUT("candlenut", 0, 0.0f),
	PAPAYIMAR("papayimar", 8, 0.8f);

	public static final EnumExtraTreesFood[] VALUES = values();

	private final String serializedName;
	public final int nutrition;
	public final float saturationModifier;

	EnumExtraTreesFood(String serializedName, int nutrition, float saturationModifier) {
		this.serializedName = serializedName;
		this.nutrition = nutrition;
		this.saturationModifier = saturationModifier;
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}
}
