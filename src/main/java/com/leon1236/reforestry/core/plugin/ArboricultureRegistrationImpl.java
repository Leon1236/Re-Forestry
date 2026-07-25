package com.leon1236.reforestry.core.plugin;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.plugin.IArboricultureRegistration;
import com.leon1236.reforestry.api.plugin.ITreeSpeciesBuilder;
import com.leon1236.reforestry.arboriculture.charcoal.CharcoalManager;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;

public final class ArboricultureRegistrationImpl implements IArboricultureRegistration {
    private final CharcoalManager charcoalPitWalls = new CharcoalManager();

    @Override
    public ITreeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int escritoireColor, IWoodType woodType) {
        return ArboricultureGenetics.registerSpecies(id, genus, species, dominant, escritoireColor, woodType);
    }

    @Override
    public void registerCharcoalPitWall(BlockState state, int charcoal) {
        this.charcoalPitWalls.addWall(state, charcoal);
    }

    public CharcoalManager getCharcoalManager() {
        return this.charcoalPitWalls;
    }
}
