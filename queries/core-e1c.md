# CORE-E1c — Research notes from escritoire

**Date:** 2026-08-20  
**Status:** Done. Winning the memory game drops CE-parity research bounty into result slots.

## What landed

- `EscritoireResearch.getResearchBounty` mirrors CE `SpeciesType.getResearchBounty` + `BeeSpeciesType` override (no full `ISpeciesType` stack yet).
- On `EscritoireGame.Status.SUCCESS`, `TileEscritoire.processTurnResult` fills `SLOT_RESULTS_1`…`+5` via `InventoryUtil.addStack`.
- Mutation note chance: `bountyLevel / 16`. Prefer unresearched mutations from the specimen species (`IBreedingTracker.isResearched`); else any mutation from that species. Note via `ItemResearchNote.createMutationNoteStack` — tracker registration still happens on **use** (A6).
- Bees: always roll products; specialties only if `bountyLevel > 10`. Product count rolls use `genetics.escritoire_bounty_multiplier` (CE default `1.0`).
- Trees: base mutation-note path only (CE has no `TreeSpeciesType` override).
- Butterflies / serum: still unsupported specimens (Track D).

## Not in scope here

- Discovery journal book (CE note item never opens one).

## Smoke

1. Place escritoire, put a bee with known mutations in the center, win without probing (bounty 16) → result slots often get a filled `research_note` plus comb/product stacks.
2. Probe many times (lower bounty) → fewer products; note chance drops with bounty.
3. Sapling win → mutation note only (no fruit product stacks).
4. Use the note → tracker memorized; second identical note → already memorized.
