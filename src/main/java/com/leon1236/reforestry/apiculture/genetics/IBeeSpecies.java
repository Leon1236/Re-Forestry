package com.leon1236.reforestry.apiculture.genetics;

import java.util.List;

import net.minecraft.world.item.Item;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IClimateSensitive;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface IBeeSpecies extends IRegistryAlleleValue, IClimateSensitive {
    String genus();

    String species();

    boolean dominant();

    int outlineColor();

    int bodyColor();

    int stripesColor();

    boolean secret();

    boolean glint();

    String authority();

    List<Product> products();

    List<Product> specialties();

    @Override
    TemperatureType getTemperature();

    @Override
    HumidityType getHumidity();

    record Product(Item item, int count, float chance) {
        public Product(Item item, float chance) {
            this(item, 1, chance);
        }
    }
}
