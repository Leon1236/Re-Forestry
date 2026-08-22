# Wave 9 Stage 5 — GP-POLISH (genetics deferred Should)

Closes the Wave 5 "Deferred Should" gaps in `queries/genetics-wave5-report.md`.

## GP-TAXON — taxon allele maps

- `TaxonDefinition` codec parses optional `"alleles"` JSON object (requires `"type"` for karyotype).
- `GenomeCodecs` resolves per-chromosome boolean/int/float/tolerance/registry refs.
- `SpeciesGenomeHelper.createDefaultGenomeBuilder` walks genus ancestry via `IGeneticManager.getParentTaxa()` and applies taxon alleles before species defaults.
- `GeneticManager.buildTaxonAlleles()` + `rebuildDefaultGenomes()` on taxa reload (apiculture, arboriculture, lepidopterology).

Datapack shape (existing files): `"type": "reforestry:bee_species"` + `"alleles": { "reforestry:cave_dwelling": { "dominant": true, "value": true }, ... }`.

## GP-BIOME — `IBee.getSuitableBiomes`

- `Bee.getSuitableBiomes()` filters all biomes through `IForestryApi.getClimateManager()` + `ClimateHelper.isWithinLimits` (CE parity).

## GP-HIVE-DROP — allele-map overload

- `IHiveBuilder.addDrop(..., Map<IChromosome<?>, IAllele> alleles)`; existing overloads delegate with `Map.of()`.
- `HiveDrop.createGenome()` uses `species.createIndividual(alleles)` when the map is non-empty.

## GP-CLIENT — client model maps

- `ClientRegistrationImpl` stores bee custom models + sapling models (no longer discarded).
- `BeeClientManager` / `TreeClientManager` installed from `ModuleApiculture` / `ModuleArboriculture` via `IForestryModule.installClientManagers`.
- `ReforestryPlugin.registerClient()` registers default bee cube models (CE paths).

**Not in this stage:** per-species tinted bee item models (`ModelBee`); maps are wired for addon API use.

## Book

No new almanac pages. Copy refresh only if taxon/biome behaviour is referenced — no text changes required at landing.
