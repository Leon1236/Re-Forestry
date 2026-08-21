# Gendustry Wave 7 full review (GD0–GD8)

**Date:** 2026-08-21  
**Branch:** `cursor/wave-7-full-port-6593`  
**Donor:** `MarkDown_Maker/github_clone/thedarkcolour-gendustry`

## Counts

| Content | Count |
|---|---|
| Machines | 10 |
| Upgrades | 23 (17 + 6 elite) |
| Mutagen recipes | 4 |
| Protein recipes | 7 |
| DNA recipes | 10 |

## Verified locked decisions

- Energy ctor: local `TilePowered(capacity, maxReceive)` (donor args reversed)
- Industrial apiary: `new BeekeepingLogic(this)`
- Youth tooltip: mutation −20%/stack (honest; not lifespan lie)
- No `gendustry` / donor `depends` in `fabric.mod.json`

## Review fixes this pass

- **Should:** recipe unlock advancements for all donor gendustry crafts (were only `industrial_apiary`)
- **Should:** Imprinter `setMate(newGenome)` when specimen was mated (donor parity)

## Documented skip

- Owner ledger on industrial apiary screen: Re-Forestry `ScreenForestry` has no owner ledger widget yet (project-wide; power/climate/hint ledgers work).
