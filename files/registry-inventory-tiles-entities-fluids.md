# Registry inventory — tiles, entities, fluids (apiculture + arboriculture)

Same source and method as the blocks/items inventories. Unlike blocks, tile/entity registration here doesn't multiply per species — each is a single named type, `TYPE_ONLY` naming (the string passed directly to `REGISTRY.tile(...)`/`REGISTRY.entity(...)`).

## Apiculture — 9 block entity types
`hive`, `apiary`, `bee_house`, `alveary` (the PLAIN alveary specifically — note it's *not* named `alveary_plain`), `alveary_sieve`, `alveary_swarmer`, `alveary_hygroregulator`, `alveary_stabiliser`, `alveary_fan`, `alveary_heater`.

That's 9 types against 21 blocks that need one (2 BASE + 12 BEEHIVE... wait — BEEHIVE is 12 blocks but all 12 share the single `hive` tile type, and all 7 ALVEARY blocks map to their own individual tile types 1:1). No entity types and no fluids registered in apiculture.

## Arboriculture — 5 block entity types, 2 entity types, 0 fluids
Tiles: `sapling` (the genetic `sapling_ge` block), `leaves` (the genetic `leaves` block), `pods` (fruit pods), `sign` (shared by both the `sign` and `wall_sign` block groups — one tile class handles both), `hanging_sign` (shared by `hanging_sign` and `wall_hanging_sign`).

Entities: `boat`, `chest_boat` — both `MobCategory.MISC`, sized 1.375×0.5625, client tracking range 10. One entity type each covers all 43 wood-type boat *items* found earlier — same "few types, data-driven variants" pattern as the genetics items, not the "one registration per variant" pattern the blocks use.

No fluids registered directly in either module — Forestry's fluids (honey, biomass, etc., if the base mod has any beyond what factory/core add) live elsewhere; out of scope for the current milestone.

## Pattern summary across all four inventories done so far
- **Blocks**: multiply hard per wood/comb/hive type (1,337 total) — this is the register-per-variant pattern.
- **Items**: mostly don't multiply — genetics-bearing items are a handful of life-stage types carrying species as data (136 total, of which 129 are boats/combs/pollen/propolis groups and only a few are truly 1:1 per-species).
- **Tiles/entities**: never multiply — always one type per distinct behavior, shared across every block/item variant that needs it (14 total across both modules).
- **Fluids**: none in these two modules.

Worth carrying this pattern forward into apiimpl/compat/plugin and the remaining modules — it's a reliable predictor of which registrations will be large mechanical lists (blocks) versus small hand-portable ones (tiles/entities).
