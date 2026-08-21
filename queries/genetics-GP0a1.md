# GP0a1 — Public species / individual types

## Fabric vs CE

- CE 1.21.1 moved genetics API to `forestry.api.core.genetics`. We keep `com.leon1236.reforestry.api.genetics` next to existing `IGenome`.
- `IBeeSpeciesType` / `ITreeSpeciesType` do not extend `ISpeciesType<?, ?>` (Java rejects wildcard bounds). Impl classes implement both.
- Life-stage item ids stay local (`bee_drone_ge`, `sapling`, `pollen_fertile`).
- No `CompoundTag` create/load; stacks use existing data components.
- `IMutation` stays Identifier-based; `IMutationManager` is the facade.

Exit: 69 bees and 50 trees implement `ISpecies`. `Bee` / `Tree` wrap current components.
