# GD1 — Gendustry fluids

**Date:** 2026-08-21  
**Donor:** `thedarkcolour-gendustry` (`GFluids`)

## Shipped

- Fluids `mutagen`, `liquid_dna`, `protein` via `FeatureFluid` (same shape as `ForestryFluids`)
- Fluid blocks `fluid_mutagen`, `fluid_liquid_dna`, `fluid_protein`
- Buckets `bucket_mutagen`, `bucket_liquid_dna`, `bucket_protein`
- Creative tab lists the three buckets
- Still/flowing textures + mcmeta copied from donor; bucket item textures from donor
- Lang: `fluid_type.reforestry.*`, `block.reforestry.fluid_*`, `item.reforestry.bucket_*`
- Client: `GendustryFluidClientHandler` (still + flowing materials, untinted — textures are pre-colored)
- Connected GD0 crafts: `mutation_elite_upgrade`, `fertility_elite_upgrade` (`c:dusts/redstone`)

## Registry ids

| Kind | Id |
|---|---|
| Fluid | `reforestry:mutagen`, `reforestry:liquid_dna`, `reforestry:protein` |
| Flowing | `reforestry:mutagen_flowing`, `reforestry:liquid_dna_flowing`, `reforestry:protein_flowing` |
| Block | `reforestry:fluid_mutagen`, `reforestry:fluid_liquid_dna`, `reforestry:fluid_protein` |
| Bucket | `reforestry:bucket_mutagen`, `reforestry:bucket_liquid_dna`, `reforestry:bucket_protein` |

## Choices

- Module id `reforestry:gendustry` → `FeatureFluid` namespace stays `reforestry:` (matches donor `gendustry:` → our namespace rewrite).
- No donor `fabric.mod.json` depends; code under `com.leon1236.reforestry.gendustry`.
- Default fluid properties (no particle tint) — donor used pre-colored sprites.

## Player checks

1. Creative tab **Gendustry** shows the three buckets
2. `/give @s reforestry:bucket_mutagen` / `bucket_liquid_dna` / `bucket_protein`
3. Craft `mutation_elite_upgrade` and `fertility_elite_upgrade`
