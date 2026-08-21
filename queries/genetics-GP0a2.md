# GP0a2 — Genetic manager + taxonomy

## Fabric vs CE

- Taxa are datapack JSON (126 bee/tree files) under `data/reforestry/taxon/`. Copy via `tools/copy_ce_taxa.py`.
- Reload uses Fabric `ResourceLoader.get(PackType.SERVER_DATA)` (`net.fabricmc.fabric.api.resource.v1.ResourceLoader`), not `ResourceManagerHelper`.
- No `TaxonSyncPacket`: analyzer taxonomy page does not walk `ITaxon` parents yet.
- `IGeneticManager.genomeComponent()` omitted: we keep two genome components.
- `defineTaxon` from plugins merges through `GeneticManager.applyDatapackTaxa`.
- Species stay Java (`DefaultBeeSpecies` / `DefaultTreeSpecies`).
