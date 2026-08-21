# Storage B4 — naturalist backpacks (CE research)

**Date:** 2026-08-17  
**Clones:** CE 1.21.1 `MarkDown_Maker/Finished_github_clone/2026-08-17/thedarkcolour-ForestryCE`; CE20 same date `…/thedarkcolour-ForestryCE-1.20.1`

## Registry ids (use CE20 / Re-Forestry layout)

| Item | CE20 id | CE 1.21.1 renamed id |
|---|---|---|
| Apiarist | `apiarist_bag` | `apiarists_backpack` |
| Arborist | `arborist_bag` | `arborists_backpack` |
| Lepidopterist | `lepidopterist_bag` | `lepidopterists_backpack` |

Re-Forestry already uses `*_bag` in lang and normal backpacks (`miner_bag`). Prefer CE20 ids.

Chests (recipe center): CE20 / local assets `bee_chest`, `tree_chest`, `butterfly_chest`. CE 1.21.1: `apiarists_chest`, `arborists_chest`, `lepidopterists_chest`.

Menu type: `naturalist_backpack`.

## Species types (Re-Forestry)

`ForestrySpeciesTypes.BEE` / `TREE` / `BUTTERFLY` → `reforestry:bee_species`, `tree_species`, `butterfly_species`.

## Colors (BackpackDefinition)

| Bag | primary | secondary |
|---|---|---|
| APIARIST | `0xc4923d` | `0xffffff` |
| ARBORIST | `0x657e3a` | `0xffffff` |
| LEPIDOPTERIST | `0x995b31` | `0xffffff` |

## GUI / slots

- Not `ContainerBackpack.Size.APIARIST` — separate `ContainerNaturalistBackpack`.
- `SLOTS_BACKPACK_APIARIST = 125` = 5 pages × 25 (5×5 grid).
- Screen: `GuiNaturalistInventory` → `textures/gui/apiaristinventory.png`, size 196×202.
- Normal bags use `backpack.png` / `backpack_t2.png`.

## Recipes

Shaped crafting (not carpenter): `X#X / VYV / X#X` with wool, wooden rods, string, matching naturalist chest.

## API `forestry.api.storage`

Backpack-only: `IBackpackInterface`, `IBackpackDefinition`, `EnumBackpackType` (incl. NATURALIST), events. No crate types in this package.

## Local gap (closed 2026-08-17)

`ItemBackpackNaturalist` + `ContainerNaturalistBackpack` + `ScreenNaturalistInventory` landed. Page flip uses vanilla `clickMenuButton` (no Forge `PacketGuiSelectRequest`). Species info panel shows analyzed genome name or `for.gui.unknown`; mutation cycling / breeding-tracker stats wait on client tracker sync + chests. Recipes reference `bee_chest` / `tree_chest` / `butterfly_chest` (unregistered).
