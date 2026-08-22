package com.leon1236.reforestry.apiculture.hives;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.apiculture.hives.IHive;
import com.leon1236.reforestry.api.apiculture.hives.IHiveDrop;
import com.leon1236.reforestry.api.apiculture.hives.IHiveManager;
import com.leon1236.reforestry.api.apiculture.hives.VillageHive;

public class HiveManager implements IHiveManager {
    private final ImmutableMap<Identifier, IHive> registry;
    private final ImmutableList<VillageHive> commonVillageHives;
    private final ImmutableList<VillageHive> rareVillageHives;

    public HiveManager(
            ImmutableMap<Identifier, IHive> registry,
            ImmutableList<VillageHive> commonVillageHives,
            ImmutableList<VillageHive> rareVillageHives) {
        this.registry = registry;
        this.commonVillageHives = commonVillageHives;
        this.rareVillageHives = rareVillageHives;
    }

    @Override
    public List<IHive> getHives() {
        return registry.values().asList();
    }

    @Override
    public List<IHiveDrop> getDrops(Identifier id) {
        IHive hive = registry.get(id);
        if (hive == null) {
            return List.of();
        }
        return hive.getDrops();
    }

    @Override
    public ImmutableList<VillageHive> getCommonVillageHives() {
        return commonVillageHives;
    }

    @Override
    public ImmutableList<VillageHive> getRareVillageHives() {
        return rareVillageHives;
    }
}
