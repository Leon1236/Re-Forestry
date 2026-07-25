package com.leon1236.reforestry.api.plugin;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.arboriculture.IWoodType;

public interface IArboricultureRegistration {
    ITreeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int escritoireColor, IWoodType woodType);
}
