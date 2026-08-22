# STOR-CRATE — layered crate overlays (26.2 vs CE loader)

**Date:** 2026-08-20  
**CE:** 1.21.1 `ForestryItemModelProvider` + `FilledCrateModelBuilder` + `FilledCrateModel`  
**Extract:** `python3 tools/generate_crate_data.py --apply`

## Player-facing

Each of the 83 `crated_*` items shows the packed item on `crate-filled`. Empty `reforestry:crate` still uses `reforestry:item/crate` (not filled).

## Why not the CE loader

CE ships a NeoForge geometry loader `forestry:filled_crate`. Generated JSON looks like:

```json
{
  "loader": "forestry:filled_crate",
  "textures": {
    "layer1": "minecraft:item/wheat"
  }
}
```

The loader draws `forestry:item/filled_crate` as the crate, then insets `layer1` (and optional `layer2`) in a window (`3,4` → `11,12`). Comb/pollen overlays use `IColoredItem` on `ItemCrated` (tint index 0 is the crate / white; later indices delegate to the contained item).

Minecraft 26.2 item definitions have no NeoForge loaders. Porting `FilledCrateModel` would be a custom special renderer. This stage uses vanilla layered `item/generated` models instead:

```json
{
  "parent": "item/generated",
  "textures": {
    "layer0": "reforestry:item/crate-filled",
    "layer1": "minecraft:item/wheat"
  }
}
```

Comb/pollen add `layer2`. Item definitions copy contained-item `minecraft:constant` tints, shifted by one, with `-1` on layer 0 so the crate is not dyed (CE `getColorFromItemStack` renderPass 0).

## Texture path rules (CE `ForestryItemModelProvider`)

Unpack carpenter recipes give crate id → contained item (pack recipes often use tags, so they are not used for textures).

| Contained | Overlay |
|---|---|
| Bee comb | `reforestry:item/bee_combs.0` + `bee_combs.1` |
| Pollen cluster | `reforestry:item/pollen.0` + `pollen.1` |
| Propolis | `reforestry:item/propolis.0` |
| `BlockItem` and not `ItemNameBlockItem` | `{ns}:block/{path}` |
| Everything else | `{ns}:item/{path}` |

Manual CE overrides kept: cactus → `block/cactus_side`, mycelium → `block/mycelium_side`, grass_block → `block/grass_block_top`.

Examples: wheat → `minecraft:item/wheat`; oak log → `minecraft:block/oak_log`; honey comb → comb dual layers.

Item vs block classification is the CE `instanceof BlockItem && !(ItemNameBlockItem)` set from `CrateItems` (logs, cubes, saplings, humus, bog earth, …). Sugar cane / seeds / nether wart / redstone stay item textures.

## Inset window

CE’s loader shrinks the overlay into the crate opening. Vanilla `item/generated` stacks full 16×16 sprites. Item textures with transparency still show crate around the icon; opaque block textures cover more of the crate. No special renderer in this stage.

## Not this stage

`F-TINT` (fluid can/capsule/refractory). Empty crate model. Crate registry ids. JEI.
