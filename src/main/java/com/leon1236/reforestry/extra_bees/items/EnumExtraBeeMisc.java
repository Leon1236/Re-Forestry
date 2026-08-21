package com.leon1236.reforestry.extra_bees.items;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumExtraBeeMisc implements IItemSubtype {
	SCENTED_GEAR("scented_gear"),
	DIAMOND_SHARD("diamond_shard"),
	EMERALD_SHARD("emerald_shard"),
	RUBY_SHARD("ruby_shard"),
	SAPPHIRE_SHARD("sapphire_shard"),
	LAPIS_SHARD("lapis_shard"),
	IRON_DUST("iron_dust"),
	GOLD_DUST("gold_dust"),
	SILVER_DUST("silver_dust"),
	PLATINUM_DUST("platinum_dust"),
	COPPER_DUST("copper_dust"),
	TIN_DUST("tin_dust"),
	NICKEL_DUST("nickel_dust"),
	LEAD_DUST("lead_dust"),
	ZINC_DUST("zinc_dust"),
	TITANIUM_DUST("titanium_dust"),
	TUNGSTEN_DUST("tungsten_dust"),
	URANIUM_DUST("radioactive_dust"),
	COAL_DUST("coal_dust"),
	RED_DYE("dye_red"),
	YELLOW_DYE("dye_yellow"),
	BLUE_DYE("dye_blue"),
	GREEN_DYE("dye_green"),
	WHITE_DYE("dye_white"),
	BLACK_DYE("dye_black"),
	BROWN_DYE("dye_brown"),
	CLAY_DUST("clay_dust"),
	YELLORIUM_DUST("yellorium_dust"),
	BLUTONIUM_DUST("blutonium_dust"),
	CYANITE_DUST("cyanite_dust");

	public static final EnumExtraBeeMisc[] VALUES = values();

	private final String serializedName;

	EnumExtraBeeMisc(String serializedName) {
		this.serializedName = serializedName;
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}
}
