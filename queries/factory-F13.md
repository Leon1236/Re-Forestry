# Factory F13 — Moistener

Stage complete 2026-07-25. Polish follow-up 2026-07-29.

## Scope

- `IMoistenerRecipe` API + `MoistenerRecipe` MapCodec/StreamCodec (`reforestry:moistener`)
- `TileMoistener` extends **`TileBase`** (not `TilePowered`) — no FE; water tank + light-level speed
- 12-slot inventory (stash / reservoir / working / product / resource); wheat-chain via `FuelManager.moistenerResource`
- `ContainerMoistener` / `ScreenMoistener` — CE slot layout (176×166), water tank + consumption/production meters
- `BlockTypeFactoryPlain.MOISTENER`, creative tab, shaped craft recipe
- `FuelManager.moistenerResource` seeded in `ModuleFactory.setupApi()` (CE values)
- `FluidStorage.SIDED` + `ItemStorage.SIDED` — no `EnergyHelper`
- 4 CE moistener recipes via `tools/extract_moistener_recipes.py`

**Not in F13 (at ship):** Rainmaker (F14), TESR tank blockstate, JEI category (landed in F18).

## Smoke paths

### Wheat chain (moistener fuels, no recipe)

| Stage | Input | Output | moistenerValue |
|---|---|---|---|
| 0 | `minecraft:wheat` | `reforestry:mouldy_wheat` | 300 |
| 1 | `reforestry:mouldy_wheat` | `reforestry:decaying_wheat` | 600 |
| 2 | `reforestry:decaying_wheat` | `reforestry:mulch` | 900 |

1. Craft and place `reforestry:moistener` in a **dark** spot (block light above ≤ 11; not broad daylight).
2. Add water to the internal tank (water bucket in the product/can slot, or pipe in).
3. Put wheat in the top-left stash slots (6 slots).
4. Wait — machine auto-rotates wheat → working slot, consumes 1 mB water/tick while active, outputs mouldy/decaying/mulch into reservoir/stash.
5. **No power required.**

Darker = faster (speed 1–4 ticks per game tick based on light 9/7/5).

### Mycelium recipe — `reforestry:moistener/mycelium`

| Input | Amount |
|---|---|
| **Resource slot** | 1× `minecraft:wheat_seeds` |
| **Water** | 1 mB per active tick from tank |
| **Light** | Dark (≤ 11) |
| **Energy** | none |
| **Output** | 1× `minecraft:mycelium` (product slot) |
| **Time** | 5000 moistener ticks (faster in darker light) |

1. Same dark placement + water as above.
2. Put wheat seeds in the top-right resource slot.
3. Collect mycelium from the product slot below it.

## CE parity notes

| CE | Re-Forestry |
|---|---|
| `TileMoistener` extends `TileBase` | same |
| No FE | same |
| Water tank 10000 mB, 1 mB/tick while working | same |
| Light gating + speed tiers | same |
| Wheat → mouldy → decaying → mulch fuels | same Preference values |
| 4 moistener JSON recipes | extracted 1:1 |
| Shaped craft: copper gear + glass + sturdy machine | `gear_copper` + glass tag + sturdy machine |

## Follow-up polish (2026-07-29)

| Item | Status |
|---|---|
| Hint ledger (`setHintKey("moistener")`) | Wired |
| Runtime hints (`data/reforestry/hints.properties`) | `moistener=nopowerrequired;pipette;moistenerproducts;` |
| Block loot self-drop | `loot_table/blocks/moistener.json` |
| `#minecraft:mineable/pickaxe` | Included with all factory machines |
| JEI item description | `for.jei.description.moistener` + `JeiDescriptions` |
| Lang modern keys | All locales |
| Recipe unlock advancement | Deferred (no factory-wide advancement set yet) |

Same polish applied factory-wide: `queries/factory-machine-gui-data-polish.md`.

## Files touched

| Area | Paths |
|---|---|
| API | `api/recipes/IMoistenerRecipe.java` |
| Core | `core/recipes/RecipeUtils.java` |
| Factory | `factory/recipes/MoistenerRecipe.java`, `factory/tiles/TileMoistener.java`, `factory/gui/ContainerMoistener.java`, `factory/client/ScreenMoistener.java`, `factory/ModuleFactory.java` |
| Features | `FactoryRecipeTypes`, `FactoryTiles`, `FactoryMenuTypes`, `BlockTypeFactoryPlain`, `FactoryCreativeTabs`, `FactoryClientHandler` |
| Data | `data/reforestry/recipe/moistener.json`, `data/reforestry/recipe/moistener/*.json` (4 recipes), loot + hints + pickaxe tag |
| Tools | `tools/extract_moistener_recipes.py` |

## Compile

```
./gradlew compileJava
```

BUILD SUCCESSFUL (2026-07-25; polish recompiled 2026-07-29).

## Blockers for F14 Rainmaker

- Done in F14 — see `queries/factory-F14.md`.
