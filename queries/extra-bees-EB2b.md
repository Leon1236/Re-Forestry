# EB2b — Extra Bees historic/refined/farm/sugarcane/boggy/frozen/energetic (+ GLOWSTONE)

**Date:** 2026-08-21  
**Donor:** `ACGaming-Binnie` `ExtraBeeDefinition` / `ExtraBeeBranchDefinition` (+ Forestry `BeeBranchDefinition` for agrarian/boggy/frozen)  
**Extracts:** `queries/extra-bees-species.json`, `queries/extra-bees-mutations.json`, `queries/extra-bees-id-map.json`  
**Generator:** `python3 tools/generate_extra_bees_species.py --batch EB2b --apply --lang`  
**Package:** `com.leon1236.reforestry.extra_bees.genetics.ExtraBeesBeeSpecies`

## Player exit

- **31** breedable Extra Bees species in this batch (56 cumulative with EB2a)
- **34** EB mutations whose parents already exist (56 cumulative; FR `modifySpecies` unchanged at 34 from EB2a)
- Remaps kept: `bee_eb_primeval`, `bee_eb_relic`, `bee_eb_boggy`; spelling `bee_artic`
- GLOWSTONE now breedable (TEMPERED × EXCITED) after energetic parents land

## Species (31)

| Branch | Genus taxon | Species |
|---|---|---|
| HISTORIC | `priscapis` (new) | ancient, eb_primeval, prehistoric, eb_relic |
| FOSSILIZED | `fosiapis` (new) | coal, resin, oil |
| REFINED | `petrapis` (new) | distilled, fuel, creosote, latex |
| AGRARIAN | `rustapis` (CE shared) | growing, farm, thriving, blooming |
| FARMING | `agriapis` (new) | alcohol, milk, coffee |
| SACCHARINE | `sacchapis` (new) | sweet, sugar, ripening, fruit |
| BOGGY | `paludapis` (CE shared) | swamp, eb_boggy, fungal |
| ENERGETIC | `incitapis` (new) | excited, energetic, ecstatic |
| FROZEN | `coagapis` (CE shared) | artic, freezing |
| VOLCANIC | `irrapis` (EB2a) | glowstone (deferred from EB2a) |

FOSSILIZED is shipped with REFINED so distilled/fuel/creosote/latex parents exist in the same batch.

## Mutations

- **34** EB `addMutations` on EB2b results (no biome gates in this batch)
- Parents may be Forestry CE, EB2a, or earlier EB2b species in the same register pass
- FR `modifySpecies` not re-emitted for EB2b (still only the EB2a WATER/ROCK/BASALT/MARBLE set)

## Wiring

- Generator is **cumulative**: `--batch EB2b` rewrites `ExtraBeesBeeSpecies` with EB2a ∪ EB2b
- Shared CE genera skipped in `registerTaxa`: `monapis`, `rustapis`, `paludapis`, `coagapis`
- Sugar products → `Items.SUGAR`; alcohol effect → `ForestryAlleles.EFFECT_DRUNKARD`
- Lang: Binnie `extrabees.species.*.name` / `.desc` → `allele.reforestry.bee_species.{id}` (37 keys)

## Gaps / next

- **INK** still waits on PRIMARY dye parent (`bee_black`) — EB2c+
- Remaining dye / metal / nuclear / quantum / etc. batches → EB2c–e
- Hive worldgen/loot still EB4

**Next stage:** `EB2c`
