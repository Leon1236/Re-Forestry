package com.leon1236.reforestry.arboriculture.loot;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;

import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;

import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;

public final class SnifferAmberSaplingLoot {
    private SnifferAmberSaplingLoot() {
    }

    public static void init() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (!BuiltInLootTables.SNIFFER_DIGGING.equals(key)) {
                return;
            }
            tableBuilder.modifyPools(pool ->
                    pool.add(LootItem.lootTableItem(ArboricultureItems.AMBER_SAPLING.item())));
        });
    }
}
