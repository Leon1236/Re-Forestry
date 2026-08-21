# GP0a3 — Individual item handler

## Fabric vs CE

- `IIndividualHandlerItem` is static helpers over data components. No Fabric Transfer for genomes.
- `ItemBeeGE` / `ItemGermlingGE` implement `IIndividualItem`; `getLifeStage()` returns the enum.
- `IndividualItems` remains as a wrapper for analyzer/filter/chest code.
- Vanilla saplings: `getIndividual` falls back to `ITreeSpeciesType.getVanillaIndividual`.
- Genome dispatch stays `bee_genome` / `tree_genome` (not CE’s single `GENOME` component).
