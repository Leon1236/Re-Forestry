package com.leon1236.reforestry.core.plugin;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.plugin.IArboricultureRegistration;
import com.leon1236.reforestry.api.plugin.ITreeSpeciesBuilder;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;

public final class ArboricultureRegistrationImpl implements IArboricultureRegistration {
    @Override
    public ITreeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int escritoireColor, IWoodType woodType) {
        return ArboricultureGenetics.registerSpecies(id, genus, species, dominant, escritoireColor, woodType);
    }
}
