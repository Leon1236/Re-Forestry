package com.leon1236.reforestry.gendustry.errors;

import java.util.Locale;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.IError;

public enum GendustryError implements IError {
	NO_LABWARE,
	NO_SAMPLES,
	INCOMPATIBLE_SPECIES,
	NO_MUTATIONS,
	NO_MATES,
	NO_MUTAGEN,
	NO_TEMPLATE,
	NO_SELECTION,
	NO_BLANK,
	NO_SOURCE,
	NO_DNA,
	NO_PROTEIN;

	private final Identifier id;
	private final Identifier sprite;
	private final String descriptionKey;
	private final String helpKey;

	GendustryError() {
		String name = name().toLowerCase(Locale.ENGLISH);
		this.id = ReForestry.id(name);
		this.sprite = ReForestry.id("errors/" + name);
		String idDotted = ReForestry.MOD_ID + '.' + name;
		this.descriptionKey = "errors." + idDotted + ".desc";
		this.helpKey = "errors." + idDotted + ".help";
	}

	@Override
	public String getDescriptionTranslationKey() {
		return this.descriptionKey;
	}

	@Override
	public String getHelpTranslationKey() {
		return this.helpKey;
	}

	@Override
	public Identifier getSprite() {
		return this.sprite;
	}

	@Override
	public Identifier getId() {
		return this.id;
	}
}
