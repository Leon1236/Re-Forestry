# ARB-11.9e — Recipes, tags, loot catch-up

Closed datapack gaps for arboriculture content already registered in Java.

## Already present (from earlier steps)

- Grafter, boats/chest boats, charcoal block, log pile / decorative conversions
- Forestry wood-family recipes/loot/tags (~43 woods)
- Minecraft `#boats` / `#chest_boats` / `#leaves` (block) membership
- `c:storage_blocks/charcoal`

## Added this step

Script: `tools/import_arb_11_9e_datapack.py` (reuse `transform_recipe` from `import_ce_generated.py`).

| Area | What |
|---|---|
| Vanilla fireproof recipes | oak/spruce/birch/jungle/acacia/dark_oak/cherry (+ synthesized mangrove/pale_oak) planks→stairs/fence/… |
| Vanilla fireproof loot | Matching block loot tables (self-drop) |
| `fireproof_*_logs` tags | Item + block tags for those woods |
| Fireproof → vanilla doors | `reforestry:<wood>_door` recipes craft `minecraft:<wood>_door` |
| Pods / genetic leaves loot | CE-shaped tables (no pools; drops come from Java `getDrops`) |
| Ash recipes | Smelt peat→ash; compost_ash; fertilizer_ash; fertilizer_apatite |
| Common tags | `c:dusts/ash`, `c:gems/apatite` |
| Tag fix | (reverted in 11.9i) do **not** add block-only `reforestry:leaves` to item `#minecraft:leaves` |
| Cleanup | Removed empty `data/reforestry/forge/biome_modifier/` |

## Intentional empties

- `ash_block` loot stays empty-pooled — `BlockAsh.getDrops` supplies charcoal + ash.
- Pod / genetic leaf tables have no pools — same as CE; BE/`getDrops` handle fruit/sapling.

## Acceptance

- Grafter / boat / log pile craftable (pre-existing recipes).
- New fireproof vanilla blocks drop themselves; pods have loot table ids.
- Pod log tags (`palm_logs` / `papaya_logs` / `coconut_logs`) already resolved.
