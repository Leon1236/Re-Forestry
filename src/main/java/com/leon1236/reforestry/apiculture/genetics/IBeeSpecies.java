package com.leon1236.reforestry.apiculture.genetics;

import java.util.List;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeJubilance;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IClimateSensitive;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenome;
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

    List<IProduct> products();

    List<IProduct> specialties();

    IBeeJubilance jubilance();

    default boolean isJubilant(IGenome genome, IBeeHousing housing) {
        return jubilance().isJubilant(this, genome, housing);
    }

    @Override
    TemperatureType getTemperature();

    @Override
    HumidityType getHumidity();
}
