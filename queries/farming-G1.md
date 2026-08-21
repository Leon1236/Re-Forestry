# G1 — Farm structure blocks

**Date:** 2026-08-20  
**Stage:** Wave 4 `G1`. 55 placeable, craftable multifarm blocks. No block entities, GUI, or multiblock.

## FeatureBlockTable

CE `FeatureBlockTable` was missing locally. Added `modules/features/FeatureBlockTable` plus `IFeatureRegistry.blockTable(constructor, rows, columns)` (same default-method shape as `blockGroup`).

- Rows: `EnumFarmBlockType` (PLAIN, GEARBOX, HATCH, VALVE, CONTROL)
- Columns: `EnumFarmMaterial` (11 CE values, `getBase()` vanilla blocks + `ChatFormatting`)
- Constructor: `(row, column, Properties) -> FarmBlock` with `FeatureBlock.seededProperties` / seeded BlockItem properties
- `.identifier(BiFunction)` uses the CE `FarmingBlocks` lambda (STONE_BRICK+PLAIN → `stone_brick_farm_block`, not `stone_brick_farm_plain`)
- `getRowBlocks(row)` and `getItems()` match CE `FeatureBlockTable` (G2a tiles use row blocks)

`FarmingBlocks.FARM` registers all 55 under `reforestry:{material}_farm_{part}`.

## Not EntityBlock (G1 only)

G1 `FarmBlock` was a normal `Block` (strength 1.0f). PLAIN gets `BooleanProperty BAND` like CE. Other parts have no extra state. No GUI, no tiles. **G2a** changed `FarmBlock` to `BlockStructure` and added the five tiles.

CONTROL `canConnectRedstone` was skipped: Minecraft 26.2 `Block` / Fabric API have no equivalent of the NeoForge hook (vanilla only has `isSignalSource` / `getSignal`, which would make the farm emit power). Redstone input for G2 still works via neighbor updates; the wire may not visually connect.

## Models — vanilla overlay, not `neoforge:composite`

CE generated models use `"loader": "neoforge:composite"` (material cube + farm overlay). Fabric 26.2 cannot load that.

`tools/generate_farm_block_models.py` writes vanilla JSON: two overlapping 16³ elements (material texture from vanilla 26.2 block models + overlay `reforestry:block/farm/{plain,gearbox,hatch,valve,control}` / `top` / `band`), with `"parent": "minecraft:block/block"` so inventory/hand transforms match a normal cube (CE inherited those from the vanilla material parent). Overlay textures were already at `assets/reforestry/textures/block/farm/`. CE 1.21.1 models do not reference `reverse.png` (leftover file kept, unused). Do not use leftover `farm_parent.json` (old planter shape).

Plain blockstates: `band=false` / `band=true` like CE. Item models parent the block model. Then `python3 tools/generate_item_model_definitions.py --root . --namespace reforestry --apply` (script now fills missing `items/{id}.json` when `--tints` is omitted).

## Recipes / tubes / tags

`tools/extract_farm_recipes.py` copies only the 55 `_farm_` recipes from CE `resources_farms` (skips `*_managed` / `*_manual` planters).

- `forestry:` → `reforestry:`
- CE SUFFIX tubes (`tin_electron_tube`, `golden_electron_tube`) → local PREFIX (`electron_tube_tin`, `electron_tube_gold`). Fancy CE names: golden→gold, diamantine→diamond, apatine→apatite, blazing→blaze
- `forge:` → `c:` (CE 1.21.1 farm recipes already used `c:` tags, including `c:gears/tin`)
- 55 recipe-unlock advancements from CE `resources_farms` `advancement/recipes/misc`
- Self-drop loot like CE
- `#minecraft:mineable/pickaxe` merged (existing machines kept, 55 farm ids appended)
- `reforestry:valid_farm_base` from CE `forestry:valid_farm_base`

## Lang

CE 1.21.1 generated `en_us.json` **has** unique keys for all 55 ids (`block.forestry.brick_farm_plain=Brick Farm Plain`, `block.forestry.stone_brick_farm_block=Stone Brick Farm Block`, …). Those were copied to `block.reforestry.*`. `ItemBlockFarm` does **not** override `getName()` / `getDescriptionId()`. Leftover generic keys (`block.reforestry.farm_plain`, `farm_gearbox`, …) stay for older strings. Tooltip uses leftover `block.reforestry.farm.tooltip` plus `for.gui.tooltip.tmi` on unshifted hover.

## Creative tab

`FarmingCreativeTabs` registers `reforestry:agriculture` (lang `itemGroup.agriculture`). Icon: `stone_brick_farm_block` (CE uses the arboretum managed planter — skipped until cultivation registers planters). Display: all 55, CE order (material then part). CE also dumps wrench/tubes/engines/factory machines onto this tab; Re-Forestry keeps those on core/energy/factory tabs. Tab is on the farming module, so it only exists when farming is loaded.

## Counts

11 materials × 5 parts = 55. `reforestry:stone_brick_farm_block` exists; `reforestry:stone_brick_farm_plain` does not.

## D12 smoke (listed)

minecraft-world MCP was not available during G1 review. Manual checks:

- `/give @p reforestry:stone_brick_farm_block` and place it. `/give @p reforestry:stone_brick_farm_plain` must fail.
- Place one of each part (plain, gearbox, hatch, valve, control). Hover: unshifted `for.gui.tooltip.tmi`; shift `block.reforestry.farm.tooltip`.
- Agriculture tab shows all 55 when farming is on; the tab is missing if farming is disabled.
- Survival craft `stone_brick_farm_gearbox` with `c:gears/tin`. Pickaxe mine → self-drop.
- Plain `band` is false until the farm assembles (G2a).

Next: **G2b** GUI/inventory. G2a assemble is done (`queries/farming-G2a.md`).
