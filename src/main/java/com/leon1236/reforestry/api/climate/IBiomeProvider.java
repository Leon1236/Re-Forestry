package com.leon1236.reforestry.api.climate;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;

public interface IBiomeProvider {
    Holder<Biome> getBiome();
}
