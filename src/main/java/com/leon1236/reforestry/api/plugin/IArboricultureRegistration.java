package com.leon1236.reforestry.api.plugin;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.IWoodType;

public interface IArboricultureRegistration {
    ITreeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int escritoireColor, IWoodType woodType);

    void registerCharcoalPitWall(BlockState state, int charcoal);

    default void registerCharcoalPitWall(Block block, int charcoal) {
        for (BlockState state : block.getStateDefinition().getPossibleStates()) {
            registerCharcoalPitWall(state, charcoal);
        }
    }
}
