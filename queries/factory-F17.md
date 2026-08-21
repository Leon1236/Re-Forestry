# F17 — Factory integration / polish

Stage complete 2026-07-25. JEI (F18) landed 2026-07-27 — see `queries/factory-F18.md`.

## Done

| Area | Change |
|---|---|
| **Machine crafts** | Added missing `sturdy_machine`, `can`, `capsule`, `refractory` shaped recipes (CE parity). All 10 factory block recipes were already present; smelter correctly uses furnace center (not sturdy casing). |
| **Carpenter bulk** | Extended `tools/extract_carpenter_recipes.py` with `--safe` + `SAFE` set; applied **16** CE carpenter recipes (registered outputs only). Skipped recipes for escritoire and (permanently) mail. |
| **Creative tab** | `FactoryCreativeTabs` now lists sturdy/hardened casing, fermenter fuels, rainmaker charges, fluid containers, and all nine Forestry buckets alongside the 10 machines. |
| **Block models** | Fixed fermenter particle (`fermenter.0`); squeezer cube (`squeezer.0` instead of concrete placeholder). |
| **Docs** | `queries/factory-play-loop.md`, Phase 6 checklist in `files/implemented-features.md`. |
| **GUI / data polish (2026-07-29)** | Hint ledgers + runtime `data/reforestry/hints.properties`, self-drop loot for all 10 machines, `#minecraft:mineable/pickaxe`, JEI item descriptions — see `queries/factory-machine-gui-data-polish.md`. |

## Hopper sidedness

Documented in `factory-play-loop.md` — no code changes (CE-aligned `WorldlyContainer` rules already on all item-slot machines). Not live-tested with hopper entities this pass.

## Deferred → later (F18 JEI done)

| Item | Reason |
|---|---|
| **Smelter block texture** | `reforestry:block/smelter.0` missing — CE uses datagen `machines/smelter/*` not present in `for textures only/`. Purple/black block until assets copied into allowed reference folder. |
| **Animated machine BER** | CE `base_machine` + tank overlay models; we use cube/particle placeholders where textures exist. |
| **Carpenter recipes (remaining)** | Outputs not registered (escritoire, …). Mail stamp/letter recipes are never extracted. |
| **Container filled tint models** | F4c note — placeholder item models. |
| **Hygroregulator tank filter from recipe registry** | F16 note. |
| **Ice hygro recipe** | Needs ice fluid. |
| **JEI recipe transfer** | Optional follow-up to F18 (categories already live). |
| **Recipe unlock advancements** | Optional; no factory-wide advancement pack yet. |
| **ModuleEnergy** | No in-mod FE generators; debug creative energy only. |
| **Rainmaker mill BER** | F14 placeholder. |

## Compile

```
./gradlew compileJava
```

Run after edits — see parent return for result.

## Files touched (summary)

- `tools/extract_carpenter_recipes.py` — `--safe` / `SAFE` set
- `factory/features/FactoryCreativeTabs.java`
- `data/reforestry/recipe/{sturdy_machine,can,capsule,refractory}.json`
- `data/reforestry/recipe/carpenter/*.json` — 11 new + 5 refreshed from CE
- `assets/reforestry/models/block/{fermenter,squeezer}.json`
- `queries/factory-play-loop.md`, `queries/factory-F17.md`, `queries/factory-machine-gui-data-polish.md`, `files/implemented-features.md`
