package com.leon1236.reforestry.api.apiculture.hives;

import java.util.List;

import net.minecraft.resources.Identifier;

public interface IHiveManager {
    List<IHive> getHives();

    List<IHiveDrop> getDrops(Identifier id);
}
