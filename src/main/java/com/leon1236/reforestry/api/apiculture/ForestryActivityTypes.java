package com.leon1236.reforestry.api.apiculture;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;

public final class ForestryActivityTypes {
	public static final Identifier DIURNAL = ReForestry.id("activity_diurnal");
	public static final Identifier NOCTURNAL = ReForestry.id("activity_nocturnal");
	public static final Identifier CREPUSCULAR = ReForestry.id("activity_crepuscular");
	public static final Identifier METATURNAL = ReForestry.id("activity_metaturnal");
	public static final Identifier CATHEMERAL = ReForestry.id("activity_cathemeral");

	private ForestryActivityTypes() {
	}
}
