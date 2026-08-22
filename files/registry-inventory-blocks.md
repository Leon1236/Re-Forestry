# Registry inventory — blocks (apiculture + arboriculture)

Source: `thedarkcolour-ForestryCE`. Full computed lists are in `apiculture_blocks_full.csv` (38 rows) and `arboriculture_blocks_full.csv` (1,335 rows) alongside this file.

## How Forestry's own naming system works

Forestry (all three codebases) doesn't call `DeferredRegister.register("name", ...)` directly per block. It goes through its own `forestry.modules.features` framework:

```java
REGISTRY.blockGroup(Constructor, EnumType.values())
    .item(...)                       // optional BlockItem factory
    .identifier("some_id")           // optional — omitting this uses IdentifierType.TYPE_ONLY
    .create();
```

The final registry name for each block in the group is computed from `IdentifierType`:
- **TYPE_ONLY** (default, no `.identifier()` call): name = the enum constant's own `getSerializedName()`.
- **PREFIX** (`.identifier("x")`, one-arg): name = `x_` + `type.getSerializedName()`.
- **SUFFIX** (`.identifier("x", IdentifierType.SUFFIX)`, two-arg): name = `type.getSerializedName()` + `_x`.

`getSerializedName()` is, in every enum checked so far, the Java constant name lowercased (`name().toLowerCase()`) — this is assumed consistent across the remaining enums rather than individually re-verified for each one; flag if a specific block's actual in-game name doesn't match what's computed here.

This single mechanism is why a handful of source lines expand into hundreds of actual blocks — worth understanding before writing the Fabric side, since the Fabric registration code should probably use the same group-multiplication idea rather than hand-writing each block.

## Apiculture — 38 blocks

| Group | Identifier type | Count | Pattern |
|---|---|---|---|
| BASE | TYPE_ONLY | 2 | `bee_house`, `apiary` |
| BEEHIVE | PREFIX "beehive" | 12 | `beehive_<species>` (forest, meadows, desert, jungle, end, snow, swamp, savanna, lush, aquatic, nether, swarm) |
| BEE_COMB | PREFIX "block_bee_comb" | 17 | `block_bee_comb_<comb_type>` |
| ALVEARY | PREFIX "alveary" | 7 | `alveary_<part>` (plain, swarmer, fan, heater, hygro, stabiliser, sieve) |

## Arboriculture — 1,337 blocks

All wood/leaf/pod blocks go through a local `woodGroup()` helper that always uses **SUFFIX** ordering: `<wood_type>_[fireproof_]<kind>`.

Correction from the original pass: the extraction regex only matched `FeatureBlockGroup<...>` fields and missed two singular `FeatureBlock<...>` registrations — `sapling_ge` and `leaves`, the actual genetic sapling and leaf blocks (as opposed to the BE-less `default_leaves`/`decorative_leaves`/`default_leaves_fruit` forms already counted below, which exist specifically so most leaves on a tree don't need genetic data attached). Both are now appended to the CSV. Total is 1,335 + 2 = 1,337.

| Category | Wood-type set | Block kinds covered | Count |
|---|---|---|---|
| Vanilla wood, fireproof only | VanillaWoodType (7: oak, spruce, birch, jungle, acacia, dark_oak, cherry) | log, wood, stripped_log, stripped_wood, planks, slab, fence, fence_gate, stairs (9 kinds) | 63 |
| Forestry's own 43 wood species | ForestryWoodType (43 custom species — larch, teak, mahogany, ebony, etc.) | log, stripped_log, wood, stripped_wood, planks, slab, fence, fence_gate, stairs — each in normal + fireproof (16 kinds), plus door, trapdoor, button, pressure_plate, sign, wall_sign, hanging_sign, wall_hanging_sign — normal only (8 kinds) | 1,032 |
| Leaves | ForestryLeafType (50 — one per wood species, vanilla + Forestry's own) | default_leaves, default_leaves_fruit, decorative_leaves | 150 |
| Fruit pods | ForestryPodType (4: cocoa, dates, papaya, coconut) | pods | 4 |

(Numbers above plus the 2-block correction sum to the 1,337 total in the CSV — see the CSV for the literal per-block breakdown.)

## Not yet covered
Recipe types/serializers and menu types for these two modules — plus the same pass for Immersive-Forestry (expected to match closely, per the earlier core/api comparison) and eventually the original 1.12.2 codebase for anything CE dropped. Items, block entities, and fluids are now covered in `registry-inventory-items.md` and `registry-inventory-tiles-entities-fluids.md`.
