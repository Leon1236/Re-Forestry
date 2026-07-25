package com.leon1236.reforestry.api.core;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;

public enum ForestryError implements IError {
    INVALID("invalid", "unknown"),

    TOO_HOT("too_hot"),
    TOO_COLD("too_cold"),
    TOO_HUMID("too_humid"),
    TOO_ARID("too_arid"),
    IS_RAINING("is_raining"),
    NOT_GLOOMY("not_gloomy"),
    NOT_BRIGHT("not_lucid"),
    NOT_DAY("not_day"),
    NOT_NIGHT("not_night"),
    NOT_TWILIGHT("not_twilight"),
    NO_FLOWER("no_flower"),
    NO_QUEEN("no_queen"),
    NO_DRONE("no_drone"),
    NO_SKY("no_sky"),
    SLEEPY("sleepy"),

    NO_RESOURCE("no_resource"),
    NO_RESOURCE_INVENTORY("no_resource_inventory", "no_resource"),
    NO_RESOURCE_LIQUID("no_resource_liquid", "no_liquid"),
    NO_RECIPE("no_recipe"),
    NO_SPACE_INVENTORY("no_space"),
    NO_SPACE_TANK("no_space_tank"),
    NO_POWER("no_power"),
    NO_REDSTONE("no_redstone", "disabled"),
    DISABLED_BY_REDSTONE("disabled_redstone", "disabled"),
    NOT_DARK("not_dark", "not_gloomy"),

    NO_HONEY("no_honey"),
    NO_SPECIMEN("no_specimen"),

    FORCED_COOLDOWN("forced_cooldown"),
    NO_FUEL("no_fuel"),
    NO_HEAT("no_heat"),
    NO_ENERGY_NET("no_energy_net"),

    NO_STAMPS("no_stamps"),
    NO_PAPER("no_paper"),
    NO_SUPPLIES("no_supplies", "no_resource"),
    NO_TRADE("no_trade", "no_resource"),

    NOT_ALPHANUMERIC("not_alpha_numeric"),
    NOT_UNIQUE("not_unique"),

    NOT_POST_PAID("not_postpaid", "no_stamps"),
    NO_RECIPIENT("no_recipient"),

    NO_CIRCUIT_BOARD("no_circuit_board"),
    NO_CIRCUIT_LAYOUT("no_circuit_layout"),
    CIRCUIT_MISMATCH("circuit_mismatch"),

    NO_FERTILIZER("no_fertilizer"),
    NO_FARMLAND("no_farmland"),
    NO_LIQUID_FARM("no_liquid");

    private final Identifier id;
    private final Identifier sprite;
    private final String descriptionKey;
    private final String helpKey;

    ForestryError(String id) {
        this(id, id);
    }

    ForestryError(String id, String iconName) {
        this.id = ReForestry.id(id);
        this.sprite = ReForestry.id("errors/" + iconName);
        String idDotted = this.id.getNamespace() + '.' + this.id.getPath();
        this.descriptionKey = "errors." + idDotted + ".desc";
        this.helpKey = "errors." + idDotted + ".help";
    }

    @Override
    public String getDescriptionTranslationKey() {
        return descriptionKey;
    }

    @Override
    public String getHelpTranslationKey() {
        return helpKey;
    }

    @Override
    public Identifier getSprite() {
        return sprite;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    public Identifier getTexture() {
        return ReForestry.id("textures/reforestry/atlas/gui/" + sprite.getPath() + ".png");
    }
}
