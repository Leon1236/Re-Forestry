package com.leon1236.reforestry.arboriculture.tiles;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

public interface SpeciesRenderData {
    @Nullable
    Identifier species();

    record Simple(@Nullable Identifier species) implements SpeciesRenderData {
    }
}
