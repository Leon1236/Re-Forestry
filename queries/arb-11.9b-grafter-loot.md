# ARB-11.9b — Grafter loot for vanilla / default leaves

**Date:** 2026-07-25  
**Status:** Implemented

## What landed

- `ArboricultureGenetics` builds an `IdentityHashMap<BlockState, IGenome>` from each species’ `getVanillaLeafStates()` (CE `TreeSpeciesType.vanillaIndividuals`).
- `GrafterLootHandler` registers Fabric `LootTableEvents.MODIFY_DROPS` (no Forge GLM).
- When tool is `IToolGrafter`, block is `#leaves`, breaker is a player, and drops have no `#saplings` item: roll Forestry sapling from mapped genome (grafter modifier 100 → near-certain), pick fruit from `TileLeaves`, and force ripe fruit from `BlockDefaultLeavesFruit`.
- Extra durability hit on the grafter in the loot hook (same as CE `doApply`; `ItemGrafter.mineBlock` also damages).
- Crafting recipe `data/reforestry/recipe/grafter.json` (bronze + sticks). CE has **no** proven-grafter craft recipe (loot/chest only).

## Acceptance check

- Grafter + oak leaves → apple-oak (`tree_oak`) sapling when oak states are mapped (they are).
- Bare hand / axe on oak → vanilla loot unchanged (handler requires `IToolGrafter`).
- Grafter loses durability.

## Playtest

Give `reforestry:grafter`, break vanilla oak leaves until a sapling drops; should be Forestry sapling with oak genome. Craft with bronze ingot + sticks.
