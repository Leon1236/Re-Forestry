package com.leon1236.reforestry.arboriculture.genetics;

import java.util.Locale;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;

public enum TreeEffect implements ITreeEffect {
    NONE,
    BLOSSOMING;

    private final Identifier id = ReForestry.id("tree_effect_" + name().toLowerCase(Locale.ENGLISH));

    @Override
    public Identifier id() {
        return id;
    }
}
