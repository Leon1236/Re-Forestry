# A-DISC1 — Apiarist tracker counts

CE has `IBee`; Re-Forestry does not expose a public `IBee`. `IApiaristTracker.registerQueen/Princess/Drone` take `Identifier speciesId`, matching `IBreedingTracker.registerBirth(Identifier)`.

CE NBT keys are unchanged: `QueensTotal`, `PrincessesTotal`, `DronesTotal`. Codec uses `optionalFieldOf(..., 0)` so older bee tracker saves without those ints still load.

Network: the E2 payload `reforestry:genome_tracker_update` now includes those ints via `BreedingTracker.writeUpdateData` / `readUpdateData`. Full `syncToPlayer` dumps them. Count-only incremental packets fire when a caste is registered for an already-discovered species (CE only attached counts to a new-species packet).

`register*` calls `registerBirth`. `BeekeepingLogic` no longer also calls `registerBirth` / `onSpeciesDiscovered` on mate or offspring, so a first-seen species is counted once.
