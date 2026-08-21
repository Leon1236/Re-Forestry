# Wave 6 — Lepidopterology (Fabric vs CE)

Playable CE lepidopterology on Fabric 26.2. Species are Java (same as bees/trees), not CE 1.21.1 datapack JSON.

## Ids kept local

- Chest `reforestry:butterfly_chest` (CE `lepidopterists_chest`)
- Bag `reforestry:lepidopterist_bag` (CE `lepidopterists_backpack`)
- GE items match CE 1.21.1: `butterfly`, `butterfly_serum`, `caterpillar`, `cocoon`
- Cocoon **block** is also `reforestry:cocoon` (separate block registry). No BlockItem — the GE item already owns that item id. Solid worldgen block: `reforestry:cocoon_solid`

## Genetics

- 35 species extracted from CE `DefaultButterflySpecies` via `tools/extract_butterfly_species.py`
- Default species `monarch` (GP0d had `cabbage_white`)
- One Java mutation: `latticed_heath × brimstone → bombyx_mori`. Local `MutationBuilder.build()` stores `chancePercent / 100f`, so the builder uses `7f` for CE 0.07
- Cocoon products: default string 2/1/3; silk `silk_wisp` 3@0.75 + 2@0.25
- Plugin `runLepidopterologyRegistration()` stays in core. `ModuleLepidopterology.init()` calls `LepidopterologyGenetics.finalizeRegistration()` after items. Plugin is not run twice
- No second genome engine. No `butterfly_mutation` recipe type. No taxon JSON allele maps

## World loop

- Cocoon worldgen off (`generateCocoons=false`). No spawn egg. No flutterlyzer. No non-none effects
- Leaf spawn: `ButterflySpawner` from `BlockForestryLeaves.randomTick` (CE `ILeafTickHandler` does not exist here). Gated by rarity × sappiness × yield and `lepidopterology.disable_butterfly_spawning` (default `false` = spawn on)
- 26.2: `GameRules.SPAWN_MOBS` is read from `ServerLevel.getGameRules()`, not `Level`
- Nursery is `TileLeaves` implementing `IButterflyNursery`. No vanilla-leaf convert
- Mating craft: serializer-only `reforestry:butterfly_mating` (RecipeType stays `crafting`). JSON `{ "type": "reforestry:butterfly_mating", "category": "misc" }`
- Tracker: `IBreedingTracker.registerPickup` already existed. `PickupHandlerGenetics` runs from the existing `ItemEntityMixin` `playerTouch` inject (CE `PickupHandlerCore`). `ILepidopteristTracker` has `registerCatch` only
- Scoop-catch does not cast the saved tracker to `ILepidopteristTracker` — `BreedingTrackerManager` always instantiates `BreedingTracker`

## Client / entity

- Re-flutter: no Fabric item-entity tick event; mixin `ItemEntity.tick` → `ItemButterflyGE.onEntityItemUpdate` (~80 ticks). See `queries/lepidopterology-D2.md`
- Item models: `reforestry:butterfly_species` select, not Forge `forestry:butterfly_ge`
- Attributes: `FabricDefaultAttributeRegistry`
- Dimension cancel: override `canTeleport` / `canUsePortal` (Fabric has no cancel-before event)
- Pollen AI uses existing `IPollen` / `TreePollenType`

## D4 leftovers (Should, not Must)

- Cocoon item age: CE stores `NBT_AGE` on the GE stack. Re-Forestry plants age 0 (no `cocoon_age` data component)
- Silk early/middle block textures are also missing in CE; only `cocoon_silk_late` is real — not invented
- Tag `forestry:genetic_samples` is not copied. Bees/trees have no equivalent here; CE uses it for `genetic_filter`, which this module does not ship
- Naturalist chest statistics ledger is not ported. `BreedingTracker.syncToPlayer` is a no-op, so a client ledger would always show 0. Server discovery still works (analyze, scoop-catch, pickup)
- No JEI mutation category (bees do not have one either)
- Research notes loot does not sample butterfly mutations
