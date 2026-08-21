# Wave 5 — Genetics public API (agent-ready)

Wave 4 farming is out of this checkout. Wave 5 adds Forestry CE’s public genetics facade over the existing bee/tree engine. No second genome. No mail. No D0–D4 butterfly content. No S2. No Gendustry.

**Packages:** new types live under `com.leon1236.reforestry.api.genetics` next to existing `IGenome`. Do not mass-move to `api.core.genetics`. `IFruitBearer` in `api.core.genetics` stays put.

**Ids kept local:** `bee_drone_ge` / `bee_princess_ge` / `bee_queen_ge` / `bee_larvae_ge`, `sapling`, `pollen_fertile`, `reforestry:bee_forest`, `reforestry:tree_oak`. Two genome components (`bee_genome`, `tree_genome`). Java `DefaultBeeSpecies` / `DefaultTreeSpecies`.

Stages: GP0a1 → GP0a2 → GP0a3 → GP0b → GP0c → GP0d. All coded.
