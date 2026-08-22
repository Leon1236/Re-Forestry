# Factory machine GUI / data polish (2026-07-29)

Parity pass after Moistener validation: apply the same polish to **all** factory machines.

## What landed

| Area | Change |
|---|---|
| **Hint ledger** | Every factory screen with a GUI calls `setHintKey(...)`. Runtime list is `data/reforestry/hints.properties` (loaded by `ForestryHints`). Config copy under `config/reforestry/hints.properties` kept in sync for humans. |
| **Loot** | Self-drop loot tables for all 10 machines under `data/reforestry/loot_table/blocks/`. Added carpenter, fabricator, fermenter (others already present). |
| **Mineable** | `data/minecraft/tags/block/mineable/pickaxe.json` lists all 10 factory blocks. |
| **JEI item descriptions** | `FactoryJeiPlugin` registers `JeiDescriptions` for all PLAIN machines + rainmaker. Lang keys `for.jei.description.<machine>` in every locale. |
| **Moistener extras** | Hint key + hints row + loot + pickaxe + JEI description (was the template for this pass). |

## Hint keys (runtime)

```
bottler=pipette;
carpenter=pipette;crating;
centrifuge=energyunit;
fabricator=energyunit;
fermenter=pipette;shortmead;
moistener=nopowerrequired;pipette;moistenerproducts;
smelter=energyunit;
squeezer=pipette;
still=pipette;
```

CE leaves centrifuge/fabricator empty; Re-Forestry uses `energyunit` for powered machines so the ledger is useful. Moistener remains no-power.

## Not in this pass

- Recipe unlock advancements (still no project-wide factory advancement set)
- Carpenter/fabricator JEI recipe-transfer packets
- Rainmaker has no screen → no hint ledger
- Smelter block texture / animated BER (unchanged F17 deferrals)

## Docs updated

- `files/implemented-features.md` Phase 6
- `queries/factory-F13.md`, `factory-F18.md`, `factory-F9.md`, `factory-F17.md`
- `queries/module-completeness-audit.md`

## Compile

```
./gradlew compileJava
```

BUILD SUCCESSFUL (2026-07-29).
