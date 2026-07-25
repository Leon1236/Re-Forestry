# Registry inventory — items (apiculture + arboriculture)

Full lists in `apiculture_items_full.csv` (45 rows) and `arboriculture_items_full.csv` (91 rows) alongside this file. Same source (`thedarkcolour-ForestryCE`) and naming method as the blocks inventory.

## Key architectural difference from blocks
Blocks multiplied hard (1,335 entries) because every wood species is its own registered block. Items don't — genetics-bearing items are a handful of general-purpose types, with the actual species encoded as data on the item rather than as separate registry entries:

- `ItemBeeGE` × 4 life stages → `bee_queen_ge`, `bee_drone_ge`, `bee_princess_ge`, `bee_larvae_ge`. One Java class, one item per life stage, all 69 bee species live inside these four as data.
- `ItemGermlingGE` × 2 life stages → `sapling`, `pollen_fertile`. Same idea — all 50 tree species live inside these two.

This matters for the Fabric port: the item registration itself is trivial (6 items total for the genetics-bearing types), but the data model carried on those items — genome/species/chromosome data — is the real design problem, and it's a Forge-NBT-vs-Fabric-DataComponent question rather than a registry-naming one.

## Apiculture — 45 items
22 individual items (bee life stages, hive frames, honey/comb resources, apiarist armor, tools) + 3 variant groups: PROPOLIS (4: normal/pulsating/silky/volcanic), POLLEN_CLUSTER (2: normal/crystalline), BEE_COMBS (17, matching the comb block variants).

## Arboriculture — 91 items
5 individual items (sapling, pollen_fertile, grafter, grafter_proven, amber_sapling) + BOAT and CHEST_BOAT groups, each × all 43 Forestry wood types = 86.

## Genetics system — scoped, not yet extracted
This is the next real target, and it's bigger than the registry work so far:

- **69 bee species** (`ForestryBeeSpecies`) and **50 tree species** (`ForestryTreeSpecies`) — each currently just a `ResourceLocation` id; the actual genome/trait data is built separately via a `BeeSpeciesBuilder`-style API (see `forestry.apiimpl.plugin.ApicultureRegistration`, `forestry.api.genetics.SpeciesDefinition`, `forestry.api.plugin.IMutationBuilder`).
- A whole polymorphic **bee "effect" system** — `apiculture/genetics/` contains individual effect classes (AggressiveBeeEffect, GlowBerryGrowEffect, CreeperBeeEffect, GuardianBeeEffect, and more) — these are gameplay behaviors tied to specific bee species, not just stat blocks.
- Mutation trees (which species breed into which, and under what conditions) are a separate data structure again.

This is a data-model design problem more than a registry-naming one, and worth its own dedicated pass rather than folding into this file.
