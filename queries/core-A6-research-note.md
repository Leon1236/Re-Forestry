# A6 — Research note

## CE behaviour (source of truth)

`forestry.core.genetics.ItemResearchNote`:
- Stores researcher profile + mutation encoding (`type`, parent0, parent1, result).
- Display name: `%s's Notes` (default researcher **Sengir** if unset).
- Tooltip lists mutation parents, chance band (`EnumMutateChance`), result, special conditions.
- Right-click (server): looks up mutation → `IBreedingTracker.isResearched` → if new, registers three species + `researchMutation`, chat messages, consumes stack.
- **No discovery-journal / escritoire UI** is opened from this item in CE.

Notes are **produced** by Escritoire research bounty (`SpeciesType.getResearchBounty` → `IMutation.getMutationNote` / `ItemResearchNote.createMutationNoteStack`). There is **no craft recipe**.

## Fabric / Re-Forestry adaptations

| CE | Ours |
|---|---|
| NBT (`RES` / `INN` / `P0`/`P1`/`RS`/`ROT`) | Data component `reforestry:research_note` (`ResearchNoteContents`) |
| `ISpecies` / `ISpeciesType.getBreedingTracker` | Species ids are `Identifier`; trackers keyed by `ForestrySpeciesTypes.BEE` / `TREE` via `BreedingTrackerManager` |
| Forge `SavedData` string filename | MC 26.2 `SavedDataType` id `reforestry:breeding_tracker/<type>/<playerUuid>` on server data storage |
| `syncToPlayer` packet | **Stub** (empty) — client sync for alyzer/journal later |
| Escritoire bounty source | **Done (`CORE-E1c`)** — `EscritoireResearch.getResearchBounty` + result slots |
| Discovery journal UI | **N/A for this item** in CE; journal module remains deferred |

`IBreedingTracker` is ported under `api.genetics` and uses `Identifier` species ids (we still lack CE’s full `ISpecies` / `ISpeciesType` stack). Mutation API expanded with parents/result/`typeId` so the note and tracker share one representation.

## Acquisition

**CE / primary path:** win the escritoire memory game (`CORE-E1c`). Chance `bountyLevel/16` for a mutation note from the specimen species (prefer unresearched). Bees also drop products (+ specialties if bounty > 10). Using the note still registers knowledge on the breeding tracker.

**Interim source (kept):** filled notes are also injected into vanilla **structure chests** (dungeons, mineshafts, strongholds, temples, shipwrecks, bastions, ancient cities, trial chambers, etc. — not village house chests). Chance ~20% per chest (~45% in stronghold libraries). The `fill_research_note` loot function picks a random bee/tree mutation so the note is usable immediately.

Factory: `ItemResearchNote.createMutationNoteStack(...)`.

## Smoke

1. Creative give empty `research_note` → error tooltip.
2. Win escritoire with a bee/sapling → result slots may contain a filled note; use it → chat memorized.
3. Find a note in a dungeon chest (or create via factory) with mutation tooltip.
4. Second use of same mutation → “already memorized”; stack consumed on success.
