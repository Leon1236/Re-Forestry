package com.leon1236.reforestry.core.plugin;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.apiculture.hives.IHive;
import com.leon1236.reforestry.api.apiculture.hives.IHiveDefinition;
import com.leon1236.reforestry.api.apiculture.hives.IHiveManager;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.api.plugin.IApicultureRegistration;
import com.leon1236.reforestry.api.plugin.IBeeSpeciesBuilder;
import com.leon1236.reforestry.api.plugin.IHiveBuilder;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.hives.HiveManager;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;

public final class ApicultureRegistrationImpl implements IApicultureRegistration {
    private final Map<Identifier, HiveBuilder> hives = new LinkedHashMap<>();
    private final Map<Identifier, IBeeEffect> beeEffects = new LinkedHashMap<>();

    @Override
    public IBeeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int outlineColor) {
        return ApicultureGenetics.registerSpecies(id, genus, species, dominant, outlineColor);
    }

    @Override
    public IHiveBuilder registerHive(Identifier id, IHiveDefinition definition) {
        if (hives.containsKey(id)) {
            throw new IllegalStateException("Hive already registered: " + id);
        }
        HiveBuilder builder = new HiveBuilder(definition);
        hives.put(id, builder);
        return builder;
    }

    @Override
    public void registerBeeEffect(Identifier id, IBeeEffect effect) {
        if (beeEffects.containsKey(id)) {
            throw new IllegalStateException("Bee effect already registered: " + id);
        }
        if (!id.equals(effect.id())) {
            throw new IllegalArgumentException("Bee effect id mismatch: registered " + id + " but effect reports " + effect.id());
        }
        beeEffects.put(id, effect);
    }

    public void finalizeEffects() {
        BeeChromosomes.EFFECT.populate(ImmutableMap.copyOf(beeEffects));
        ForestryAlleles.initBeeEffects();
    }

    public IHiveManager buildHiveManager() {
        ImmutableMap.Builder<Identifier, IHive> map = ImmutableMap.builder();
        for (Map.Entry<Identifier, HiveBuilder> entry : hives.entrySet()) {
            map.put(entry.getKey(), entry.getValue().build());
        }
        return new HiveManager(map.build());
    }
}
