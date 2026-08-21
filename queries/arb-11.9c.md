# ARB-11.9c — Vanilla / default leaf pollen

**Date:** 2026-08-20  
**Status:** Implemented

## IPollenType choice

CE `IPollenType.tryPollinate` takes a nullable `pollinator` object. `TreePollenType` converts vanilla/default leaves only when `pollinator instanceof IBee` **and** `ForestryConfig.SERVER.pollinateVanillaLeaves` (`bees.pollinate_vanilla_leaves`, default true).

Our `IPollenType` has no pollinator parameter (genomes, not CE `IPollen`). Added a default method:

`tryPollinate(level, pos, pollen, random, boolean convertVanilla)`

`convertVanilla` stands in for “this pollinator may convert vanilla leaves.” `TreePollenType` still ANDs that flag with `ForestryConfig.pollinateVanillaLeaves()`. The 4-arg overload keeps `convertVanilla=false` so other callers cannot convert by accident.

Did **not** add a pollinator `Object` — no other pollen types exist yet, and bees/player are the only two call sites.

## Vanilla conversion rule (bee vs player)

Verified CE 1.21.1 (`TreeItem.onItemRightClickPollen`, not a separate `ItemPollenGE`):

| Caller | CE | Re-Forestry |
|---|---|---|
| Bee (`Bee` / `BeekeepingLogic`) | `TreePollenType.tryPollinate(..., this)` → convert if bee **and** config | `tryPollinate(..., convertVanilla=true)` → convert if config |
| Player `pollen_fertile` | Bypasses `TreePollenType`. `canMate` first, then `getOrCreateLeaves(pos, true)` — always converts, ignores config | `canMate` first (no same-species convert), then `tryPollinate(..., true)` — conversion is **config-gated** like bees |

Player pollen still pollinates existing `TileLeaves` when the config is off. That matches `for.config.genetics.pollinate.vanilla.trees.comment` (vanilla must be analyzed / already genetic first). Gating the player path also matches the config’s purpose (leaf builds). CE `TreeItem` converting with the config off is the only intentional deviation.

Persistent leaves (`LeavesBlock.PERSISTENT`) and decorative stacks (`ITreeSpecies.getDecorativeLeaves()`) are skipped in `canPollinate`, same as CE. Default / default-fruit / vanilla mapped states come from `ArboricultureGenetics.getVanillaIndividual`. Item ids stay `pollen_fertile` / `sapling`.

Chunk check in `TreeUtil.getTreeSafe` is `Level.isLoaded(pos)` (not CE `hasChunkAt`). Butterfly nursery helpers were not ported (lepidopterology unstarted).
