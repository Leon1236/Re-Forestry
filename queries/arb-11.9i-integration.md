# ARB-11.9i — Integration pass

**Date:** 2026-07-25

## Play loop (RCON / dedicated server)

No client player online — item use / `spawnTree` / creative-tab clicks not exercised by hand. Command + block probes used instead.

| Check | Result |
|---|---|
| Wild tree / command spawn | `/reforestry tree spawnTree\|spawnForest` registered; needs a player (look-dir). Worldgen hook already from 11.8f. |
| Blocks: leaves, default/fruit/decorative, pods, woods, charcoal | Placeable (`reforestry:leaves`, `tree_oak_*_leaves`, `pods_*`, `larch_*`, `log_pile`, `ash_block`, `charcoal`, …) |
| Boats | `summon reforestry:boat` / `chest_boat` OK |
| Charcoal | Fire adjacent → `active=true`; fully enclosed pile → `ash_block` after tick sprint. Air gaps in the shell turn piles to fire (CE-like). |
| Recipes on disk | Grafter, larch boat, log pile, pale_oak fireproof present |
| Creative tab | Code lists grafters, 50× sapling/pollen, woods, leaves, pods, boats, charcoal (11.9f) |
| Pollen / grafter / plant-grow | Not live-clicked; genetic leaf pollen + grafter loot paths already in Java (11.6 / 11.9b). |

## Bugs fixed this pass

1. **`pale_pale_oak_*` datapack ids** — `tools/import_arb_11_9e_datapack.py` `substitute_wood` matched `oak_fireproof` inside `pale_oak_fireproof`. Fixed with boundary-aware regex; repaired 18 recipe/loot/tag files.
2. **Item `#minecraft:leaves` + `reforestry:leaves`** — genetic leaves are block-only (`FeatureBlock(..., null)`). Removing the item-tag entry stops cascading vanilla `leaf_litter` recipe/advancement failures. Block `#minecraft:leaves` still includes `reforestry:leaves`.

## Still open (not Phase 5 blockers)

- **11.9c** — bee/pollen on vanilla + default leaves (genetic leaves only today).
- **11.9h** — arborist villager deferred (no `tree_chest`).

## Acceptance

Phase 5 core loop has no known crash on the probed paths; datapack startup clean after the two fixes above.
