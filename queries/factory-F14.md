# Factory F14 — Rainmaker + rain substrates

Stage complete 2026-07-25.

## Scope

- `TileMill` (core) — charge/progress/stage mill animation loop; `hasGui()` false
- `TileMillRainmaker` — right-click substrate → mill charges → `activate()` sets world weather via `ServerLevel.getWeatherData()`
- `FuelManager.rainSubstrate` seeded in `ModuleFactory.setupApi()` (CE Preference values)
- `BlockTypeFactoryTesr.RAINMAKER` + `FactoryBlocks.TESR` group (CE TESR path; **no animated BER** — placeholder block model only)
- `BlockFactoryTesr.useItemOn` → `TileMillRainmaker.tryCharge` (no menu, no FE, no RecipeType)
- Shaped craft recipe, creative tab, loot table

**Not in F14:** animated `RenderMill` BER, pipe/inventory insertion (`InventoryRainmaker`), JEI rainmaker category, advancement unlock.

## Block registration choice

CE registers rainmaker on `FactoryBlocks.TESR` / `BlockTypeFactoryTesr` with `RenderMill` BER. Re-Forestry mirrors the **TESR block group** (`BlockFactoryTesr`, `BlockTypeFactoryTesr`, `FactoryBlocks.TESR`) but keeps `RenderShape.MODEL` and the existing placeholder `assets/reforestry/models/block/rainmaker.json` (particle texture only). Animated mill BER deferred.

## Rain substrates (CE values)

| Item | Registry id | duration | speed | reverse | Effect |
|---|---|---:|---:|---|---|
| Iodine capsule | `reforestry:iodine_capsule` | 10000 ticks | 0.075 | false | Start rain (~8.3 min) when **not** already raining |
| Dissipation charge | `reforestry:dissipation_charge` | 0 | 0.01 | true | Stop rain when **is** raining |

Weather API: MC 26.2 `WeatherData` via `ServerLevel.getWeatherData()` (`setRaining`, `setRainTime`) — not deprecated `LevelData` / `ServerLevelData`.

## Smoke paths

### Make it rain

1. Craft `reforestry:rainmaker` (tin gear + glass + hardened casing — CE pattern).
2. Place in overworld on a **clear** day (not raining).
3. Hold `reforestry:iodine_capsule`, right-click rainmaker.
4. Mill animates (~7 charge steps at speed 0.075); rain starts when cycle completes.
5. Expect `/weather query` → raining; rain timer ≈ 10000 ticks.

### Stop rain

1. During rain, hold `reforestry:dissipation_charge`, right-click rainmaker.
2. Mill animates (speed 0.01, slower); rain stops when cycle completes.

**Gating:** iodine rejected while raining; dissipation rejected while clear — matches CE `InventoryRainmaker.canSlotAccept`.

## CE parity notes

| CE | Re-Forestry |
|---|---|
| `TileMillRainmaker` extends `TileMill` → `TileBase` | same |
| No FE, no menu | same |
| Right-click substrate from `FuelManager.rainSubstrate` | `BlockFactoryTesr.useItemOn` |
| `BlockTypeFactoryTesr` / `FactoryBlocks.TESR` | same group shape |
| `RenderMill` animated BER | deferred — static placeholder model |
| Pipe insert via `InventoryRainmaker` | deferred |
| Craft: tin gear + glass + hardened machine | `gear_tin` + `#c:glass_blocks/colorless` + `hardened_machine` |

## Files touched

| Area | Paths |
|---|---|
| Core | `core/tiles/TileMill.java` |
| Factory | `factory/tiles/TileMillRainmaker.java`, `factory/blocks/BlockTypeFactoryTesr.java`, `factory/blocks/BlockFactoryTesr.java`, `factory/ModuleFactory.java` |
| Features | `FactoryBlocks`, `FactoryTiles`, `FactoryCreativeTabs` |
| Data | `data/reforestry/recipe/rainmaker.json`, `data/reforestry/loot_table/blocks/rainmaker.json` |

## Compile

```
./gradlew compileJava
```

BUILD SUCCESSFUL (2026-07-25).

## Blockers for F15 (circuits)

- Rainmaker is **not** `TilePowered` / `IMachineUpgradable` — circuits apply to Centrifuge, Squeezer, Smelter only (CE `CircuitMachineUpgrade`).
- F15 can proceed independently; no rainmaker dependency.
- Optional later: pipe substrate insert (`InventoryRainmaker`), animated BER, JEI rainmaker entries from `FuelManager.rainSubstrate.values()`.
