package com.leon1236.reforestry.apiculture.genetics;

import com.google.common.collect.ImmutableMap;

import net.minecraft.core.Vec3i;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.api.core.ToleranceType;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.alleles.IBooleanAllele;
import com.leon1236.reforestry.api.genetics.alleles.IFloatAllele;
import com.leon1236.reforestry.api.genetics.alleles.IIntegerAllele;
import com.leon1236.reforestry.api.genetics.alleles.IValueAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;
import com.leon1236.reforestry.apiculture.genetics.effects.DummyBeeEffect;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;

public final class BeeChromosomes {
    public static final IRegistryChromosome<IBeeSpecies> SPECIES = AlleleManager.INSTANCE.registryChromosome(ReForestry.id("bee_species"));
    public static final IChromosome<IFloatAllele> SPEED = AlleleManager.INSTANCE.floatChromosome(ReForestry.id("speed"));
    public static final IChromosome<IIntegerAllele> LIFESPAN = AlleleManager.INSTANCE.integerChromosome(ReForestry.id("lifespan"));
    public static final IChromosome<IIntegerAllele> FERTILITY = AlleleManager.INSTANCE.integerChromosome(ReForestry.id("fertility"));
    public static final IChromosome<IValueAllele<ToleranceType>> TEMPERATURE_TOLERANCE = AlleleManager.INSTANCE.valueChromosome(ReForestry.id("temperature_tolerance"));
    public static final IChromosome<IValueAllele<ToleranceType>> HUMIDITY_TOLERANCE = AlleleManager.INSTANCE.valueChromosome(ReForestry.id("humidity_tolerance"));
    public static final IRegistryChromosome<IActivityType> ACTIVITY = AlleleManager.INSTANCE.registryChromosome(ReForestry.id("activity"));
    public static final IChromosome<IBooleanAllele> CAVE_DWELLING = AlleleManager.INSTANCE.booleanChromosome(ReForestry.id("cave_dwelling"));
    public static final IChromosome<IBooleanAllele> TOLERATES_RAIN = AlleleManager.INSTANCE.booleanChromosome(ReForestry.id("tolerates_rain"));
    public static final IRegistryChromosome<IFlowerType> FLOWER_TYPE = AlleleManager.INSTANCE.registryChromosome(ReForestry.id("flower_type"));
    public static final IRegistryChromosome<IBeeEffect> EFFECT = AlleleManager.INSTANCE.registryChromosome(ReForestry.id("bee_effect"));
    public static final IChromosome<IIntegerAllele> POLLINATION = AlleleManager.INSTANCE.integerChromosome(ReForestry.id("pollination"));
    public static final IChromosome<IValueAllele<Vec3i>> TERRITORY = AlleleManager.INSTANCE.valueChromosome(ReForestry.id("territory"));

    public static final IKaryotype KARYOTYPE;

    static {
        populateActivity();
        populateFlowerType();

        KARYOTYPE = AlleleManager.INSTANCE.karyotypeBuilder()
                .setSpecies(SPECIES)
                .set(SPEED, ForestryAlleles.SPEED_SLOWEST)
                .set(LIFESPAN, ForestryAlleles.LIFESPAN_SHORTER)
                .set(FERTILITY, ForestryAlleles.FERTILITY_2)
                .set(TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_NONE)
                .set(HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_NONE)
                .set(ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.DIURNAL, true))
                .set(CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(false, true))
                .set(TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(false, true))
                .set(FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.VANILLA, true))
                .set(EFFECT, AlleleManager.INSTANCE.registryAllele(DummyBeeEffect.NONE, true))
                .set(POLLINATION, ForestryAlleles.POLLINATION_SLOWEST)
                .set(TERRITORY, ForestryAlleles.TERRITORY_AVERAGE)
                .create(ReForestry.id("bees"));
    }

    private BeeChromosomes() {
    }

    private static void populateActivity() {
        ImmutableMap.Builder<Identifier, IActivityType> values = ImmutableMap.builder();
        for (ActivityType type : ActivityType.values()) {
            values.put(type.id(), type);
        }
        ACTIVITY.populate(values.build());
    }

    private static void populateFlowerType() {
        ImmutableMap.Builder<Identifier, IFlowerType> values = ImmutableMap.builder();
        for (FlowerType type : FlowerType.values()) {
            values.put(type.id(), type);
        }
        FLOWER_TYPE.populate(values.build());
    }
}
