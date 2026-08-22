# CU1 — Crops planter playable

**Date:** 2026-08-21  
**Stage:** Wave 4 `CU1`. One playable planter (`farm_crops_managed` / `farm_crops_manual`); all 14 planter ids registered. Next: `CU2`.

## Registry ids (CE SUFFIX, never PREFIX)

`FeatureGroup.IdentifierType.SUFFIX` with `"managed"` / `"manual"`. Using the default PREFIX helper would invent `managed_farm_crops`.

| Kind | Managed | Manual | Tile |
|---|---|---|---|
| arboretum | `reforestry:arboretum_managed` | `reforestry:arboretum_manual` | `reforestry:arboretum` |
| farm_crops | `reforestry:farm_crops_managed` | `reforestry:farm_crops_manual` | `reforestry:crops` |
| farm_mushroom | `reforestry:farm_mushroom_managed` | `reforestry:farm_mushroom_manual` | `reforestry:mushroom` |
| farm_gourd | `reforestry:farm_gourd_managed` | `reforestry:farm_gourd_manual` | `reforestry:gourd` |
| farm_nether | `reforestry:farm_nether_managed` | `reforestry:farm_nether_manual` | `reforestry:nether` |
| farm_ender | `reforestry:farm_ender_managed` | `reforestry:farm_ender_manual` | `reforestry:ender` |
| peat_bog | `reforestry:peat_bog_managed` | `reforestry:peat_bog_manual` | `reforestry:bog` |

Menu: `reforestry:planter`. Module: `reforestry:cultivation`. Enum constant `PEAT_POG` still serializes as `peat_bog` (CE typo, keep it).

## Craft tubes ≠ farm circuits

Shaped managed recipes use **craft** tubes from CE cultivation JSON, remapped SUFFIX → PREFIX (`bronze_electron_tube` → `electron_tube_bronze`). These are not the multifarm circuit tubes:

| Planter | Craft tube | Multifarm circuit tube (G3/G4) |
|---|---|---|
| farm_crops | bronze | bronze |
| arboretum | **gold** | copper (arboreal) |
| farm_mushroom | apatite | apatite |
| farm_gourd | lapis | obsidian |
| farm_nether | blaze (`blazing` → `blaze`) | blaze |
| farm_ender | ender | iron |
| peat_bog | obsidian | tin |

Circuit boards: `basic_circuit_board` → `circuit_board_basic`. Extractor: `tools/extract_planter_recipes.py`. 21 recipes (7 shaped + 14 shapeless convert pairs), 14 loot tables, 21 recipe unlocks. All 14 ids go in `minecraft:mineable/pickaxe` (CE datagen omitted planters).

## Fabric vs CE

| CE | Re-Forestry |
|---|---|
| `PacketGuiStream` | Menu `ContainerData` (progress + energy + farm tank/fertilizer/hydration + climate + errors) |
| `CompoundTag` tile NBT | `ValueInput` / `ValueOutput` |
| NeoForge energy/item/fluid caps | `EnergyStorage.SIDED` + `ItemStorage.SIDED` + `FluidStorage.SIDED` |
| `hasChunkAt` | `Level.isLoaded` (already on `FarmHelper` / crop logics) |
| `ItemBlockRenderTypes.cutoutMipped` | `farm_parent.json` `"render_type": "minecraft:cutout_mipped"` |

`TilePowered.doWork()` was renamed to `doWork(boolean)` so `TilePlanter` can implement `IFarmHousing.doWork()` (`boolean`, always false). Factory energy tiles call `doWork(true)`. `TileMoistener` extends `TileBase`, not `TilePowered` — its `doWork()` stays no-arg.

`FarmManager` now takes `IFarmHousingInternal` so the same cultivate/harvest loop runs on `FarmController` and `TilePlanter`. Planter platform is `y = planterY - 2`, square layout, `canPlantSoil = !manual`. Energy 150 / 1500, 10 FE/cycle, 2 ticks/cycle, `hasWork()` always true, `workCycle()` returns false (startup energy, then farms continuously). Water tank 10000 mB.

Config (already in `server.properties`): `farms.legacy_farms_planter_rings=4`, `farms.legacy_farms_use_rings=true`, `farms.legacy_farms_ring_size=4`.

## GUI

`planter.png` 202×192. Tank 178,44 16×58. Inventory slots 21,110. Fertilizer bar 101,21. Ghost overlay UV 206,0.

Ghosts and NESW labels use **menu** `Slot.index` 0–13. Do not use `getContainerSlot()` alone: player-inventory slots also report 0–35, so the first 14 player slots would get wheat-seed ghosts.

Right-side ledgers (closed height 24, 28px stride): power at 8, climate at 36, hydration at 64. Access ledger stays at the bottom (`imageHeight`). ScreenFarm has no power ledger, so its hydration tab stays at 36.

CE `GhostItemStackWidget` direction labels use `>= productionStart \|\| < productionStart+count`, which is always true, so NESW never shows. Intended: labels on resource + germling 0–7. That is what `ScreenPlanter` does.

`ItemBlockPlanter` shows `block.reforestry.{type}.tooltip` when the key exists (arboretum, farm_crops, gourd, nether, ender, peat_bog). CE has no `farm_mushroom.tooltip`.

`TilePlanter` reads `BlockPlanter.isManual()` from the block state in the constructor (world load uses the BE factory, not `newBlockEntity`). NBT `manual` still overrides.

Agriculture tab icon is the arboretum managed planter (CE). Planters are appended from `ModuleCultivation.init()` so farming-on + cultivation-off does not classload planter blocks.

## Other six kinds

CE uses one `TilePlanter` plus seven thin subclasses. Same here: arboretum / mushroom / gourd / nether / ender / peat tiles have CE ghost stacks and the matching `ForestryFarmTypes` id, so they will farm if supplied. CU2 is in-world verification of those six, not new farm logics.

## Smoke (server was not up)

- `/give @p reforestry:farm_crops_managed` and `farm_crops_manual`
- Place on a solid platform at planter y−2 (dirt/stone under the work area)
- Dirt in the four soil slots, wheat seeds in germlings, `fertilizer_compound`, water (bucket in the can slot or a pipe), FE
- Managed planter should till/plant wheat; manual needs pre-placed farmland
- GUI shows tank, fertilizer bar, wheat-seed ghosts, NESW on soil/seed slots
