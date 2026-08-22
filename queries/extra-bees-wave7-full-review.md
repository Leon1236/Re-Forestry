# Extra Bees Wave 7 — independent full review

**Date:** 2026-08-21  
**Branch:** `cursor/wave-7-full-port-6593`  
**Verdict:** **PASS** (after Should fixes below)

## Verified counts

| Scope | Extract / claim | Code | Match |
|---|---:|---:|:---:|
| Giveable items | 142 | 74 comb + 24 drop + 4 propolis + 30 misc + 5 frame + 1 ectoplasm + 4 hive | yes |
| Alveary parts | 7 | 7 blocks + tiles + recipes + axe tag | yes |
| Centrifuge | 74 | 74 `recipe/centrifuge/extra_bees/*` | yes |
| Squeezer | 21 | 20 drops + 1 propolis_water | yes |
| Flower types | 11 | 11 registered | yes |
| Effects | 25 | 25 `registerBeeEffect` | yes |
| Species | 116 | 116 `registerSpecies` (ids exact) | yes |
| Mutations | 168 | 168 `mutations.add` (134 EB + 34 FR `modifySpecies`) | yes |
| Wild hives | 4 | water/rock/`beehive_eb_nether`/marble + loot 0.8/0.03 ignoble 0.5 | yes |
| Remaps | id-map | `bee_eb_primeval/relic/boggy`, `bee_artic`, `bee_effect_eb_radioactive`, `beehive_eb_nether` | yes |
| Donor depends | none | `fabric.mod.json` only Fabric stack; no Binnie import | yes |

## Must fix

- None found after review.

## Should fix (implemented this pass)

1. **Ectoplasm cobweb stickiness** — Binnie `BlockEctoplasm extends BlockWeb`; local was a plain `Block` with cobweb *properties* only, so players walked through it. Copied `WebBlock.entityInside` (26.2 cannot subclass `WebBlock` with a covariant `codec()`).
2. **Ectoplasm placement floor** — effect used `isSolidRender()`; Binnie uses normal-cube. Switched to `isCollisionShapeFullBlock`.

## Nice / documented gaps (not blocking)

- JADED `setIsNotCounted` — no CE builder API (`queries/extra-bees-EB2e.md`)
- `VanillaComb.QUARTZ` → `Items.QUARTZ` (CE dropped quartz comb)
- Soft-skipped centrifuge OreDict/IC2 lines and missing fluids (EB6)
- Marble hives need `#c:stones/marble` filled by another mod/datapack
- Industrial frames / dictionary / honey crystal Wave 7 skips

## Notes

- Species names: 116/116 match Binnie `en_US.lang`; 31/31 Binnie `.desc` keys present.
- Genome branch+override chromosomes: 0 mismatches vs extract (NEVER_SLEEPS → METATURNAL).
- Mutation biome restricts: 6/6 present.
- No `@Deprecated` `hasChunkAt` / mutating `BoundingBox` in Extra Bees package.
