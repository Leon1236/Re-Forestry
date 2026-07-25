package com.leon1236.reforestry.api.plugin;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.api.apiculture.hives.IHiveDefinition;

public interface IApicultureRegistration {
    IBeeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int outlineColor);

    IHiveBuilder registerHive(Identifier id, IHiveDefinition definition);

    void registerBeeEffect(Identifier id, IBeeEffect effect);
}
