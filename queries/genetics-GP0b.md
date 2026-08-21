# GP0b — Plugin registerGenetics

## Fabric vs CE

- `IForestryPlugin.registerGenetics` runs from `ModuleCore.init` **before** apiculture/arboriculture module inits.
- `ISpeciesTypeBuilder.setKaryotype(IKaryotype)` is extra vs CE (CE only has `Consumer<IKaryotypeBuilder>`). Used so bee/tree types keep existing `BeeChromosomes.KARYOTYPE` / `TreeChromosomes.KARYOTYPE` without rebuilding them.
- `ISpeciesBuilder` is public for addons; `IBeeSpeciesBuilder` / `ITreeSpeciesBuilder` grow onto it in GP0c.
- No `registerFilter` / `IFilterManager` in this checkout (Wave 3 sorting is absent). `registerFilterRuleType` is not stubbed.
- Errors: `ModuleCore.registerErrors` still runs; plugin `registerErrors` dogfoods the same `ErrorManager` (`putIfAbsent`).
- Pollen: arboriculture still registers `TreePollenType`; plugin path is id-deduped. `runPollenRegistration` runs after all modules load.
- Client: `registerClient` is invoked from `ReForestryClient`. Analyzer plugins still bootstrap in `GeneticClientManager` (CE’s default plugin no longer sets bee models in Java).
- Flower types: `IFlowerType` promoted to `api.apiculture`; manager bootstraps the existing `FlowerType` enum.
- `IKaryotypeBuilder` stays under `api.genetics` (CE 1.21.1 moved it to `forestry.api.plugin`).
