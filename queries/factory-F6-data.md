# Wave 9 Stage 6 — FACTORY-DATA

## F-COMB-BLOCK

17 CE-parity shaped recipes (`4× comb → 1 block`) via `tools/generate_comb_block_recipes.py`.

Matching recipe-unlock advancements under `advancement/recipes/building_blocks/block_bee_comb_*.json` (CE 1.21.1 no longer generates these; script authors them from CE 1.20.1 template).

## F-WAX-CAPSULE

`recipe/bog_earth_wax_capsule.json` — CE id preserved; uses `reforestry:capsule` with `fluid_container_contents` water (alias for CE `wax_capsule`).

Advancement copied by `extract_recipe_advancements.py`.

Book `core/bog_earth` page already references `reforestry:bog_earth_wax_capsule`.

## F-ADV-REM

Re-ran `python3 tools/extract_recipe_advancements.py --root . --apply` after new recipes landed (+7 unlocks including `bog_earth_wax_capsule`).

Remaining skips unchanged (mail, escritoire, CE-only ore blocks, `portable_analyzer`, etc.) — see `queries/f-adv.md`.
