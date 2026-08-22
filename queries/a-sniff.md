# A-SNIFF — sniffer amber drone

CE 1.21.1 `ModuleApiculture.modifySnifferLoot` takes `LootTableLoadEvent`, finds pool `"main"`, and appends `ApicultureItems.AMBER_DRONE` to that pool’s entries.

Minecraft 26.2 still uses `BuiltInLootTables.SNIFFER_DIGGING` (`minecraft:gameplay/sniffer_digging`). Vanilla gift loot builds one pool with `torchflower_seeds` and `pitcher_pod` (default weight 1 each). Pool names are gone, so CE’s `getPool("main")` has no 26.2 equivalent.

Fabric `LootTableEvents.MODIFY` + `LootTable.Builder.modifyPools` adds `amber_drone` (default weight 1) to the existing pool. That matches CE’s in-place extra weighted dig result. A new `withPool` would roll amber drone in addition to the vanilla seed, which CE does not do.

CE `ModuleArboriculture.modifySnifferLoot` also appends `amber_sapling_fossil` to the same pool. Local item id stays `amber_sapling` (CE20 path). `SnifferAmberSaplingLoot` in arboriculture does the same `modifyPools` inject so a disabled apiculture module still drops the sapling fossil.
