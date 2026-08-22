# GD8 — Gendustry errors + JEI

**Date:** 2026-08-21  
**Donor:** `thedarkcolour-gendustry` (`GendustryError`, `GendustryJeiPlugin`, producer categories)

## Shipped

- Error sprites for all 12 `GendustryError` values under `assets/reforestry/textures/reforestry/atlas/gui/errors/` (copied from donor `textures/forestry/atlas/gui/errors/`, remapped to our atlas path)
- `registerErrors` was already wired in `GendustryForestryPlugin` (GD5/GD6); lang keys already present
- Soft `jei_mod_plugin` entry: `GendustryJeiPlugin`
- JEI categories: mutagen producer / protein liquefier / DNA extractor (Fabric `IPlatformFluidHelper`, droplet amounts via `FluidUnits.mbToDroplets`)
- Catalysts, recipe collect via `JeiRecipeSources`, producer GUI click areas
- Gene-sample subtype interpreter (`type|chromosome|allele`; Recipe context → `"written"`)
- Fluid ingredient info: `info.reforestry.mutagen` / `dna` / `protein`
- Mutatron + advanced mutatron progress bar → `MutationDisplay` recipe types (bee, tree, and butterfly categories)
- **Wave 9 Stage 3:** `registerDescriptions` — all 10 `GendustryMachineType` blocks + grouped `gendustry_upgrade` / `gendustry_elite_upgrade` items via `JeiDescriptions.addDescription`

## Choices

- Mirror local Factory/Apiculture JEI APIs (`IRecipeType`, `addCraftingStation`, `GuiGraphicsExtractor`) — not Forge `ForgeTypes.FLUID_STACK`
- DNA labware chance tooltip uses existing `ChanceTooltipCallback(0.1f)` / `for.jei.chance`
- No donor mod dependency; JEI stays soft via `suggests` + entrypoint only
- JEI registration gated on `gendustry` module (same pattern as `FarmingJeiPlugin`) so a disabled module does not load `GItems`/`GBlocks` via JEI
- Advanced mutatron progress bar also opens mutation JEI (donor only wired MutatronScreen)

## Review fixes

- Matched donor `errors.reforestry.incompatible_species.help` English text

## Gaps / next

- Owner ledger still missing on industrial apiary screen (pre-GD8 polish)
- **Next:** EB5

## Player checks

1. Open mutatron/sampler/etc. with a known error → left ledger shows icon + desc/help
2. JEI: mutagen / protein / DNA recipe tabs with machine catalysts
3. JEI: different gene samples are distinct ingredients
4. Click mutatron arrow → bee mutation recipes
