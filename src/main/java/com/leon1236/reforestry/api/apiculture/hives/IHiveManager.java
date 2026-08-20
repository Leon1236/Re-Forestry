package com.leon1236.reforestry.api.apiculture.hives;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.resources.Identifier;

public interface IHiveManager {
    List<IHive> getHives();

    List<IHiveDrop> getDrops(Identifier id);

    ImmutableList<VillageHive> getCommonVillageHives();

    ImmutableList<VillageHive> getRareVillageHives();
}
