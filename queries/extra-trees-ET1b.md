# ET1b — Extra Trees CE-modern wood kinds

**Date:** 2026-08-21  
**Status:** Implemented  
**Donor:** Binnie textures (stripped/trapdoor/sign/boat art reused from trunk/planks or arboriculture larch templates — no charcoal walls)

## Player exit

Same **30** `ExtraTreeWoodType.WITH_PRODUCTS` woods now have full modern kinds matching arboriculture:

| Kind | Fireproof | Notes |
|---|---|---|
| STRIPPED_LOG / WOOD / STRIPPED_WOOD | yes | Axe strip via `StrippableBlockRegistry`; bark→wood crafts |
| TRAPDOOR / BUTTON / PRESSURE_PLATE | no | Normal only (like arboriculture) |
| SIGN + WALL_SIGN | no | Wall drops sign item |
| HANGING_SIGN + WALL_HANGING_SIGN | no | Craft from stripped log + iron chain |
| BOAT / CHEST_BOAT | no | Entities `extra_tree_boat` / `extra_tree_chest_boat` |

Log-only bark variants + shrub stay LOG-only (not in the 30).

## Registration

- `ExtraTreesBlocks` mirrors `ArboricultureBlocks` ET1b groups + WoodAccess
- `BlockForestryTrapdoor|Button|PressurePlate|*Sign` now take `IWoodType` (same as door in ET1a)
- Signs reuse `ArboricultureTiles.SIGN` / vanilla hanging sign BE via `addValidBlock`
- Boats: `ExtraTreesItems` + `ExtraTreesEntities` + `ExtraTreesBoatRenderer`
- Arboriculture also got strip registry wiring (was missing)

## Assets

`python3 tools/generate_extra_trees_ET1b_assets.py`

- Stripped textures: copy Binnie trunk (`{wood}_log_top`) → `stripped_{wood}_log(_top)`
- Trapdoor: copy planks
- Sign/boat item + entity textures: copy larch templates (Binnie never shipped these)

## Remaps / gaps

| Item | Choice |
|---|---|
| Boat entity ids | `reforestry:extra_tree_boat` / `extra_tree_chest_boat` (arboriculture already owns `boat`) |
| Stripped / trapdoor / boat art | Reused textures — not unique Binnie/CE art |
| Charcoal walls | Explicitly out of scope |
| Vanilla fireproof strip for oak etc. | Not touched |

## Review (2026-08-21)

Full WoodBlockKind matrix vs arboriculture for all **30** `WITH_PRODUCTS` woods: kinds match (17 enum kinds; fireproof only where arboriculture does). Log-only / shrub stay LOG-only. No charcoal walls.

Must/Should fixed in review pass:

- Recipe-unlock advancements for all 30 product woods (mirror larch’s 22 recipes each).
- Hanging-sign unlock criteria: `minecraft:chain` → `minecraft:iron_chain` (MC 26.2 id; recipes already used iron chain).
- Boat renderer null-safe texture fallback.

## Next

**ET2** — fruits + ~88 species + mutations (`registerFruit`; overlap woods → `ForestryWoodType`) — **done** (`queries/extra-trees-ET2.md`).

## Next

**ET3** — growth features / worldgen.
