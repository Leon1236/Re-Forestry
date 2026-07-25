package com.leon1236.reforestry.api.plugin;

import java.util.function.Consumer;

import net.minecraft.world.item.Item;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;

public interface IBeeSpeciesBuilder {
    IBeeSpeciesBuilder setBodyColor(int color);

    IBeeSpeciesBuilder setStripesColor(int color);

    IBeeSpeciesBuilder setSecret(boolean secret);

    IBeeSpeciesBuilder setGlint(boolean glint);

    IBeeSpeciesBuilder setAuthority(String authority);

    IBeeSpeciesBuilder setTemperature(TemperatureType temperature);

    IBeeSpeciesBuilder setHumidity(HumidityType humidity);

    IBeeSpeciesBuilder addProduct(Item item, float chance);

    IBeeSpeciesBuilder addProduct(Item item, int count, float chance);

    IBeeSpeciesBuilder addSpecialty(Item item, float chance);

    IBeeSpeciesBuilder setGenome(Consumer<IGenomeBuilder> genome);

    IBeeSpeciesBuilder addMutations(Consumer<IMutationsRegistration> mutations);
}
