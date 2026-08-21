# Stage F1 — Factory module shell

## Files created

- `factory/ModuleFactory.java` — `@ForestryModule` id `reforestry:factory`, deps `[reforestry:core]` only (matches CE `BlankForestryModule.getModuleDependencies()`).
- `factory/features/FactoryBlocks.java`, `FactoryTiles.java`, `FactoryMenuTypes.java` — empty shells holding a `IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("factory"))` (mirrors `ApicultureBlocks`/`ApicultureTiles` pattern) with a no-op `init()`, ready for F2 to add feature declarations.
- `factory/client/FactoryClientHandler.java` — empty `registerClient()` (CE's version only registers `MenuScreens`/TESR render layers for machines that don't exist yet).
- `api/fuels/FuelManager.java`, `FermenterFuel.java`, `MoistenerFuel.java`, `RainSubstrate.java` — ported 1:1 from CE (`forestry/api/fuels/*`), minus the engine fuel maps (`biogasEngineFuel`, `peatEngineFuel`, `combustionEngineFuel`, `combustionEngineCoolant`) since those belong to `ModuleEnergy`, not Factory, per the inventory doc.

## Files modified

- `ReForestry.java` — appended `new ModuleFactory()` to the `ModuleManager.INSTANCE.load(...)` list.

## Non-obvious choices

- **`FuelManager` maps use plain `HashMap`, not CE's `ItemStackMap`.** CE uses a custom `ItemStackMap` (stack-content-aware key equality) so `Preferences`-driven refills don't duplicate entries; we haven't ported that utility yet. Since F1 only needs the maps to exist and be empty, `HashMap<ItemStack, ...>` is a safe placeholder — whichever stage actually populates these maps (fermenter/moistener/rainmaker, later in the F-series) should decide then whether `ItemStack` identity/`HashMap` equality is good enough or whether `ItemStackMap` needs porting.
- **No engine fuel maps.** CE's `FuelManager` also declares `biogasEngineFuel`/`peatEngineFuel`/`combustionEngineFuel`/`combustionEngineCoolant`, but those are populated and consumed by `ModuleEnergy` (engines), not `ModuleFactory`. Left out per task scope ("fuel placeholder types if CE has them and they are needed for empty maps" — these aren't needed by Factory).
- **No packets yet.** CE's `ModuleFactory.registerPackets` registers `PacketRecipeTransferRequest`/`PacketRecipeTransferUpdate` (JEI recipe-transfer support). Skipped for F1 since there's no networking scaffold call site in the local `IForestryModule` yet and no recipes/menus exist to transfer into. Revisit when Carpenter/Fabricator (later F-stage) needs JEI transfer.
- `setupApi()` is a private static method called from `ModuleFactory.init()` (local `IForestryModule` has no separate `setupApi()` hook like CE's `BlankForestryModule`), matching how `ModuleCore`/`ModuleApiculture` call helper methods from `init()`.

## Verified via MCP (`thedarkcolour-ForestryCE`)

- `forestry/factory/ModuleFactory.java`
- `forestry/api/fuels/FuelManager.java`, `FermenterFuel.java`, `MoistenerFuel.java`, `RainSubstrate.java`
- `forestry/factory/client/FactoryClientHandler.java`

## DoD status

- `./gradlew compileJava` — **BUILD SUCCESSFUL**.
- `ModuleFactory.getId()` returns `reforestry:factory`; `ModuleConfig` writes `module.getId() + "=" + enabled`, so `config/reforestry/modules.properties` will contain `reforestry:factory=true` once the game boots.
- `ModuleFactory` is in `ReForestry.onInitialize()`'s load list, after Core/Apiculture/Arboriculture.

## Blockers for F2

None. F2 (first machine — per the inventory doc's suggested port order, Centrifuge is simplest: items-only, one recipe type) can build directly on this shell: add block/tile/menu declarations to `FactoryBlocks`/`FactoryTiles`/`FactoryMenuTypes`, wire GUI registration into `FactoryClientHandler`. `TilePowered` + `ForestryEnergyStorage`/Team Reborn Energy adapter should be confirmed to exist in core before F2 starts (see inventory doc §10 "Core dependencies").
