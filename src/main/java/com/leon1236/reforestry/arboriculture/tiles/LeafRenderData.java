package com.leon1236.reforestry.arboriculture.tiles;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

public record LeafRenderData(@Nullable Identifier species, boolean pollinated, boolean hasFruit,
                              @Nullable Identifier fruitSprite, int fruitColor) implements SpeciesRenderData {
}
