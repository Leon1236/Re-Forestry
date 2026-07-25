package com.leon1236.reforestry.core.genetics;

import java.util.Locale;

import net.minecraft.core.Vec3i;

import com.leon1236.reforestry.api.core.ToleranceType;
import com.leon1236.reforestry.api.genetics.alleles.IFloatAllele;
import com.leon1236.reforestry.api.genetics.alleles.IIntegerAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.api.genetics.alleles.IValueAllele;
import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;

public final class ForestryAlleles {
    public static final IFloatAllele SPEED_SLOWEST = speed(0.3f, true);
    public static final IFloatAllele SPEED_SLOWER = speed(0.6f, true);
    public static final IFloatAllele SPEED_SLOW = speed(0.8f, true);
    public static final IFloatAllele SPEED_NORMAL = speed(1.0f, false);
    public static final IFloatAllele SPEED_FAST = speed(1.2f, true);
    public static final IFloatAllele SPEED_FASTER = speed(1.4f, false);
    public static final IFloatAllele SPEED_FASTEST = speed(1.7f, false);

    public static final IIntegerAllele LIFESPAN_SHORTEST = lifespan(10, true);
    public static final IIntegerAllele LIFESPAN_SHORTER = lifespan(20, true);
    public static final IIntegerAllele LIFESPAN_SHORT = lifespan(30, true);
    public static final IIntegerAllele LIFESPAN_SHORTENED = lifespan(35, true);
    public static final IIntegerAllele LIFESPAN_NORMAL = lifespan(40, false);
    public static final IIntegerAllele LIFESPAN_ELONGATED = lifespan(45, true);
    public static final IIntegerAllele LIFESPAN_LONG = lifespan(50, false);
    public static final IIntegerAllele LIFESPAN_LONGER = lifespan(60, false);
    public static final IIntegerAllele LIFESPAN_LONGEST = lifespan(70, false);
    public static final IIntegerAllele LIFESPAN_IMMORTAL = lifespan(Integer.MAX_VALUE, false);

    public static final IIntegerAllele POLLINATION_SLOWEST = pollination(5, true);
    public static final IIntegerAllele POLLINATION_SLOWER = pollination(10, false);
    public static final IIntegerAllele POLLINATION_SLOW = pollination(15, false);
    public static final IIntegerAllele POLLINATION_AVERAGE = pollination(20, false);
    public static final IIntegerAllele POLLINATION_FAST = pollination(25, false);
    public static final IIntegerAllele POLLINATION_FASTER = pollination(30, false);
    public static final IIntegerAllele POLLINATION_FASTEST = pollination(35, false);
    public static final IIntegerAllele POLLINATION_MAXIMUM = pollination(99, true);

    public static final IIntegerAllele FERTILITY_0 = fertility(0, false);
    public static final IIntegerAllele FERTILITY_1 = fertility(1, false);
    public static final IIntegerAllele FERTILITY_2 = fertility(2, true);
    public static final IIntegerAllele FERTILITY_3 = fertility(3, false);
    public static final IIntegerAllele FERTILITY_4 = fertility(4, false);

    public static final IValueAllele<Vec3i> TERRITORY_AVERAGE = territory(9, 6, 9, false);
    public static final IValueAllele<Vec3i> TERRITORY_LARGE = territory(11, 8, 11, false);
    public static final IValueAllele<Vec3i> TERRITORY_LARGER = territory(13, 12, 13, false);
    public static final IValueAllele<Vec3i> TERRITORY_LARGEST = territory(15, 13, 15, false);

    public static final IValueAllele<ToleranceType> TOLERANCE_NONE = tolerance(ToleranceType.NONE);
    public static final IValueAllele<ToleranceType> TOLERANCE_BOTH_1 = tolerance(ToleranceType.BOTH_1);
    public static final IValueAllele<ToleranceType> TOLERANCE_BOTH_2 = tolerance(ToleranceType.BOTH_2);
    public static final IValueAllele<ToleranceType> TOLERANCE_BOTH_3 = tolerance(ToleranceType.BOTH_3);
    public static final IValueAllele<ToleranceType> TOLERANCE_BOTH_4 = tolerance(ToleranceType.BOTH_4);
    public static final IValueAllele<ToleranceType> TOLERANCE_BOTH_5 = tolerance(ToleranceType.BOTH_5);
    public static final IValueAllele<ToleranceType> TOLERANCE_UP_1 = tolerance(ToleranceType.UP_1);
    public static final IValueAllele<ToleranceType> TOLERANCE_UP_2 = tolerance(ToleranceType.UP_2);
    public static final IValueAllele<ToleranceType> TOLERANCE_UP_3 = tolerance(ToleranceType.UP_3);
    public static final IValueAllele<ToleranceType> TOLERANCE_UP_4 = tolerance(ToleranceType.UP_4);
    public static final IValueAllele<ToleranceType> TOLERANCE_UP_5 = tolerance(ToleranceType.UP_5);
    public static final IValueAllele<ToleranceType> TOLERANCE_DOWN_1 = tolerance(ToleranceType.DOWN_1);
    public static final IValueAllele<ToleranceType> TOLERANCE_DOWN_2 = tolerance(ToleranceType.DOWN_2);
    public static final IValueAllele<ToleranceType> TOLERANCE_DOWN_3 = tolerance(ToleranceType.DOWN_3);
    public static final IValueAllele<ToleranceType> TOLERANCE_DOWN_4 = tolerance(ToleranceType.DOWN_4);
    public static final IValueAllele<ToleranceType> TOLERANCE_DOWN_5 = tolerance(ToleranceType.DOWN_5);

    public static IRegistryAllele<IBeeEffect> EFFECT_NONE;
    public static IRegistryAllele<IBeeEffect> EFFECT_AGGRESSIVE;
    public static IRegistryAllele<IBeeEffect> EFFECT_HEROIC;
    public static IRegistryAllele<IBeeEffect> EFFECT_BEATIFIC;
    public static IRegistryAllele<IBeeEffect> EFFECT_MIASMIC;
    public static IRegistryAllele<IBeeEffect> EFFECT_MISANTHROPE;
    public static IRegistryAllele<IBeeEffect> EFFECT_GLACIAL;
    public static IRegistryAllele<IBeeEffect> EFFECT_RADIOACTIVE;
    public static IRegistryAllele<IBeeEffect> EFFECT_CREEPER;
    public static IRegistryAllele<IBeeEffect> EFFECT_IGNITION;
    public static IRegistryAllele<IBeeEffect> EFFECT_EXPLORATION;
    public static IRegistryAllele<IBeeEffect> EFFECT_EASTER;
    public static IRegistryAllele<IBeeEffect> EFFECT_SNOWING;
    public static IRegistryAllele<IBeeEffect> EFFECT_DRUNKARD;
    public static IRegistryAllele<IBeeEffect> EFFECT_REANIMATION;
    public static IRegistryAllele<IBeeEffect> EFFECT_RESURRECTION;
    public static IRegistryAllele<IBeeEffect> EFFECT_REPULSION;
    public static IRegistryAllele<IBeeEffect> EFFECT_FERTILE;
    public static IRegistryAllele<IBeeEffect> EFFECT_MYCOPHILIC;
    public static IRegistryAllele<IBeeEffect> EFFECT_SIFTER;
    public static IRegistryAllele<IBeeEffect> EFFECT_HAKUNA_MATATA;
    public static IRegistryAllele<IBeeEffect> EFFECT_GLOW_BERRY_GROW;
    public static IRegistryAllele<IBeeEffect> EFFECT_REJUVENATION;
    public static IRegistryAllele<IBeeEffect> EFFECT_CHRONOPHAGE;
    public static IRegistryAllele<IBeeEffect> EFFECT_GUARDIAN;
    public static IRegistryAllele<IBeeEffect> EFFECT_PHASING;
    public static IRegistryAllele<IBeeEffect> EFFECT_ASCENSION;
    public static IRegistryAllele<IBeeEffect> EFFECT_SCULK;
    public static IRegistryAllele<IBeeEffect> EFFECT_DARKNESS;

    public static final IFloatAllele HEIGHT_SMALLEST = height(0.25f, false);
    public static final IFloatAllele HEIGHT_SMALLER = height(0.5f, false);
    public static final IFloatAllele HEIGHT_SMALL = height(0.75f, false);
    public static final IFloatAllele HEIGHT_AVERAGE = height(1.0f, false);
    public static final IFloatAllele HEIGHT_LARGE = height(1.25f, false);
    public static final IFloatAllele HEIGHT_LARGER = height(1.5f, false);
    public static final IFloatAllele HEIGHT_LARGEST = height(1.75f, false);
    public static final IFloatAllele HEIGHT_GIGANTIC = height(2.0f, false);

    public static final IFloatAllele SAPLINGS_LOWEST = saplings(0.01f, true);
    public static final IFloatAllele SAPLINGS_LOWER = saplings(0.025f, true);
    public static final IFloatAllele SAPLINGS_LOW = saplings(0.035f, true);
    public static final IFloatAllele SAPLINGS_AVERAGE = saplings(0.05f, true);
    public static final IFloatAllele SAPLINGS_HIGH = saplings(0.1f, true);
    public static final IFloatAllele SAPLINGS_HIGHER = saplings(0.2f, true);
    public static final IFloatAllele SAPLINGS_HIGHEST = saplings(0.3f, true);

    public static final IFloatAllele YIELD_LOWEST = yieldAllele(0.025f, true);
    public static final IFloatAllele YIELD_LOWER = yieldAllele(0.05f, true);
    public static final IFloatAllele YIELD_LOW = yieldAllele(0.1f, true);
    public static final IFloatAllele YIELD_AVERAGE = yieldAllele(0.2f, true);
    public static final IFloatAllele YIELD_HIGH = yieldAllele(0.3f, false);
    public static final IFloatAllele YIELD_HIGHER = yieldAllele(0.35f, false);
    public static final IFloatAllele YIELD_HIGHEST = yieldAllele(0.4f, false);

    public static final IFloatAllele SAPPINESS_LOWEST = sappiness(0.1f, true);
    public static final IFloatAllele SAPPINESS_LOWER = sappiness(0.2f, true);
    public static final IFloatAllele SAPPINESS_LOW = sappiness(0.3f, true);
    public static final IFloatAllele SAPPINESS_AVERAGE = sappiness(0.4f, true);
    public static final IFloatAllele SAPPINESS_HIGH = sappiness(0.6f, true);
    public static final IFloatAllele SAPPINESS_HIGHER = sappiness(0.8f, false);
    public static final IFloatAllele SAPPINESS_HIGHEST = sappiness(1.0f, false);

    public static final IIntegerAllele MATURATION_SLOWEST = maturation(10, true);
    public static final IIntegerAllele MATURATION_SLOWER = maturation(7, false);
    public static final IIntegerAllele MATURATION_SLOW = maturation(5, true);
    public static final IIntegerAllele MATURATION_AVERAGE = maturation(4, false);
    public static final IIntegerAllele MATURATION_FAST = maturation(3, false);
    public static final IIntegerAllele MATURATION_FASTER = maturation(2, false);
    public static final IIntegerAllele MATURATION_FASTEST = maturation(1, false);

    public static final IIntegerAllele GIRTH_1 = girth(1);
    public static final IIntegerAllele GIRTH_2 = girth(2);
    public static final IIntegerAllele GIRTH_3 = girth(3);
    public static final IIntegerAllele GIRTH_4 = girth(4);
    public static final IIntegerAllele GIRTH_5 = girth(5);
    public static final IIntegerAllele GIRTH_6 = girth(6);
    public static final IIntegerAllele GIRTH_7 = girth(7);
    public static final IIntegerAllele GIRTH_8 = girth(8);
    public static final IIntegerAllele GIRTH_9 = girth(9);
    public static final IIntegerAllele GIRTH_10 = girth(10);

    private ForestryAlleles() {
    }

    private static IFloatAllele speed(float value, boolean dominant) {
        return AlleleManager.INSTANCE.floatAllele(value, dominant);
    }

    private static IIntegerAllele lifespan(int value, boolean dominant) {
        return AlleleManager.INSTANCE.integerAllele(value, dominant);
    }

    private static IIntegerAllele pollination(int value, boolean dominant) {
        return AlleleManager.INSTANCE.integerAllele(value, dominant);
    }

    private static IIntegerAllele fertility(int value, boolean dominant) {
        return AlleleManager.INSTANCE.integerAllele(value, dominant);
    }

    private static IValueAllele<Vec3i> territory(int x, int y, int z, boolean dominant) {
        return AlleleManager.INSTANCE.valueAllele(x + "_" + y + "_" + z, new Vec3i(x, y, z), dominant);
    }

    private static IValueAllele<ToleranceType> tolerance(ToleranceType type) {
        boolean dominant = type == ToleranceType.BOTH_1 || type == ToleranceType.UP_1 || type == ToleranceType.DOWN_1;
        return AlleleManager.INSTANCE.valueAllele("tolerance_" + type.name().toLowerCase(Locale.ENGLISH), type, dominant);
    }

    private static IFloatAllele height(float value, boolean dominant) {
        return AlleleManager.INSTANCE.floatAllele(value, dominant);
    }

    private static IFloatAllele saplings(float value, boolean dominant) {
        return AlleleManager.INSTANCE.floatAllele(value, dominant);
    }

    private static IFloatAllele yieldAllele(float value, boolean dominant) {
        return AlleleManager.INSTANCE.floatAllele(value, dominant);
    }

    private static IFloatAllele sappiness(float value, boolean dominant) {
        return AlleleManager.INSTANCE.floatAllele(value, dominant);
    }

    private static IIntegerAllele maturation(int value, boolean dominant) {
        return AlleleManager.INSTANCE.integerAllele(value, dominant);
    }

    private static IIntegerAllele girth(int value) {
        return AlleleManager.INSTANCE.integerAllele(value, false);
    }

    public static void initBeeEffects() {
        EFFECT_NONE = beeEffect(ForestryBeeEffects.NONE);
        EFFECT_AGGRESSIVE = beeEffect(ForestryBeeEffects.AGGRESSIVE);
        EFFECT_HEROIC = beeEffect(ForestryBeeEffects.HEROIC);
        EFFECT_BEATIFIC = beeEffect(ForestryBeeEffects.BEATIFIC);
        EFFECT_MIASMIC = beeEffect(ForestryBeeEffects.MIASMIC);
        EFFECT_MISANTHROPE = beeEffect(ForestryBeeEffects.MISANTHROPE);
        EFFECT_GLACIAL = beeEffect(ForestryBeeEffects.GLACIAL);
        EFFECT_RADIOACTIVE = beeEffect(ForestryBeeEffects.RADIOACTIVE);
        EFFECT_CREEPER = beeEffect(ForestryBeeEffects.CREEPER);
        EFFECT_IGNITION = beeEffect(ForestryBeeEffects.IGNITION);
        EFFECT_EXPLORATION = beeEffect(ForestryBeeEffects.EXPLORATION);
        EFFECT_EASTER = beeEffect(ForestryBeeEffects.EASTER);
        EFFECT_SNOWING = beeEffect(ForestryBeeEffects.SNOWING);
        EFFECT_DRUNKARD = beeEffect(ForestryBeeEffects.DRUNKARD);
        EFFECT_REANIMATION = beeEffect(ForestryBeeEffects.REANIMATION);
        EFFECT_RESURRECTION = beeEffect(ForestryBeeEffects.RESURRECTION);
        EFFECT_REPULSION = beeEffect(ForestryBeeEffects.REPULSION);
        EFFECT_FERTILE = beeEffect(ForestryBeeEffects.FERTILE);
        EFFECT_MYCOPHILIC = beeEffect(ForestryBeeEffects.MYCOPHILIC);
        EFFECT_SIFTER = beeEffect(ForestryBeeEffects.SIFTER);
        EFFECT_HAKUNA_MATATA = beeEffect(ForestryBeeEffects.HAKUNA_MATATA);
        EFFECT_GLOW_BERRY_GROW = beeEffect(ForestryBeeEffects.GLOW_BERRY_GROW);
        EFFECT_REJUVENATION = beeEffect(ForestryBeeEffects.REJUVENATION);
        EFFECT_CHRONOPHAGE = beeEffect(ForestryBeeEffects.CHRONOPHAGE);
        EFFECT_GUARDIAN = beeEffect(ForestryBeeEffects.GUARDIAN);
        EFFECT_PHASING = beeEffect(ForestryBeeEffects.PHASING);
        EFFECT_ASCENSION = beeEffect(ForestryBeeEffects.ASCENSION);
        EFFECT_SCULK = beeEffect(ForestryBeeEffects.SCULK);
        EFFECT_DARKNESS = beeEffect(ForestryBeeEffects.DARKNESS);
    }

    private static IRegistryAllele<IBeeEffect> beeEffect(net.minecraft.resources.Identifier id) {
        IBeeEffect effect = BeeChromosomes.EFFECT.getSafe(id)
                .orElseThrow(() -> new IllegalStateException("Missing bee effect: " + id));
        return AlleleManager.INSTANCE.registryAllele(effect, effect.isDominant());
    }
}
