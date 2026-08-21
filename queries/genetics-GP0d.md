# GP0d — Lepidopterology API shell

## Exit

`BUTTERFLY` species type is registered with karyotype and life stages. Zero butterfly items, species, or entities. 34 butterfly taxon JSON files sit next to the bee/tree taxa. `IForestryPlugin.registerLepidopterology` runs after genetics.

## Fabric vs CE

- Packages stay under `api.lepidopterology` (CE layout) mapped onto `com.leon1236.reforestry.lepidopterology`. No butterfly module content (`D1`–`D4`).
- Life-stage item ids match CE 1.21.1: `butterfly`, `butterfly_serum`, `caterpillar`, `cocoon`. `getItemForm()` is `Items.AIR` until Wave 6 registers those items.
- Species constants copy CE paths (`cabbage_white`, `citrus_swallow`, `mbluemorpho`, …) under `reforestry:`. No species are registered; `getDefaultSpecies()` would throw if called (`cabbage_white` is the stored default id).
- `IButterflySpeciesType` extends `ISpeciesType<IButterflySpecies, IButterfly>` (concrete bounds). `IBeeSpeciesType` / `ITreeSpeciesType` stay thin.
- `IButterflySpecies`, `IButterflyCocoon`, and `IButterflyEffect` extend `IRegistryAlleleValue` because our chromosomes are registry chromosomes. CE 1.21.1 uses ResourceLocation chromosomes and does not need that bound.
- Karyotype `reforestry:butterflies` has no default SPECIES allele (same Karyotype rule as bees/trees). SPEED/FERTILITY/tolerances/TOLERATES_RAIN reuse bee chromosomes; FIREPROOF reuses the tree chromosome; SIZE / butterfly_lifespan / METABOLISM / NEVER_SLEEPS / EFFECT / COCOON are new.
- `SIZE_SMALL` (0.5, recessive) intern-collides with tree `HEIGHT_SMALLER` if both are 0.5f recessive — same AlleleManager intern. Harmless.
- Cocoon/effect defaults are populated in `ButterflyChromosomes` static init so the karyotype can reference them. Plugin `registerCocoon`/`registerEffect` skip ids that are already present; `cocoon_silk` is appended from `registerLepidopterology`.
- `registerSpecies` returns a builder that is not finalized into a species. No D2 datapack species in this wave.
- Taxa: `python3 tools/copy_ce_taxa.py --butterflies` copies 34 JSON files. Parent `insecta` already exists in the bee/tree set (160 files total).
