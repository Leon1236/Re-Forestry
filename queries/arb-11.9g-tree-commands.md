# ARB-11.9g — Tree admin commands

**Date:** 2026-07-25

## What shipped

- Package `arboriculture/commands/`: `CommandTree`, `CommandTreeSpawn`, `TreeSpawner`, `ForestSpawner`, `ITreeSpawner`.
- Reuses existing `arboriculture.worldgen.TreeGenHelper` (not duplicated under commands).
- Registered from `ModuleArboriculture.init()` via Fabric `CommandRegistrationCallback` as `/reforestry tree …`.

## Commands (op / gamemaster)

```
/reforestry tree spawnTree <species>
/reforestry tree spawnForest <species>
```

Example: `/reforestry tree spawnTree reforestry:tree_oak`

- `spawnTree` — one tree ~3 blocks along look direction at player Y.
- `spawnForest` — 16 trees in a scatter ~16 blocks along look.

Species arg is vanilla `IdentifierArgument` + suggestions from `ArboricultureGenetics.getAllSpeciesIds()` (avoids custom ArgumentType registration on Fabric).

## Not ported (out of scope / missing core)

- CE `CommandSaveStats` / `GiveSpeciesCommand` / `ModifyGenomeCommand` (need `core/commands` package).
- Treekeeping mode commands (MC-only; CE unused).

## MC 26.2 note

CE used `sender.hasPermission(2)`. Local uses `Commands.hasPermission(Commands.LEVEL_GAMEMASTERS)`.
