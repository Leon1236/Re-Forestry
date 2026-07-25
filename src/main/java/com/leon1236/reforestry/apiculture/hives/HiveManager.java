package com.leon1236.reforestry.apiculture.hives;

import java.util.List;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.apiculture.hives.IHive;
import com.leon1236.reforestry.api.apiculture.hives.IHiveDrop;
import com.leon1236.reforestry.api.apiculture.hives.IHiveManager;

public class HiveManager implements IHiveManager {
    private final ImmutableMap<Identifier, IHive> registry;

    public HiveManager(ImmutableMap<Identifier, IHive> registry) {
        this.registry = registry;
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
}
