# GP0c — Promote bee/tree API + registration parity

## Exit

Extra Bees can `modifySpecies`. Extra Trees can `registerFruit`. An industrial apiary can hold `IBee`. `IForestryApi.getTreeManager()` works.

## Fabric vs CE

- Packages stay under `api.apiculture.genetics` / `api.arboriculture` (CE 1.21.1 layout for `ITreeSpecies`) mapped onto our tree. No mass-move to `api.core.genetics`.
- `IBeeSpeciesType` / `ITreeSpeciesType` stay thin (they do not extend `ISpeciesType`). `ISpeciesBuilder` therefore drops CE’s `T extends ISpeciesType<S, ?>` bound so `IBeeSpeciesBuilder extends ISpeciesBuilder<IBeeSpeciesType, …>` compiles.
- `IBee.getType()` / `ITree.getType()` are not covariantly overridden to the thin types; `getSpecies()` returns `IBeeSpecies` / `ITreeSpecies`.
- Species ids stay local: `reforestry:bee_forest`, `reforestry:tree_oak`. `ForestryBeeSpecies` / `ForestryTreeSpecies` point at those. CE aliases kept where they match existing local ids (`SOUR_CHERRY` → hill cherry, `GREENHEART` → sipiri, `ZEBRANO` → zebrawood, `CAMELTHORN` → desert acacia). No invented `chronofuge` for `bee_relic`.
- Genome components stay `bee_genome` + `tree_genome`.
- `BeeChromosomes` / `TreeChromosomes` remain implemented in the module packages; `api.genetics.alleles.{Bee,Tree}Chromosomes` re-export the same fields for addons. Species chromosome fields use an unchecked cast because impl maps still use the package type-alias `IBeeSpecies` / `ITreeSpecies`.
- Color APIs keep **int** overloads (`DefaultBeeSpecies` / `DefaultTreeSpecies`) and add `TextColor` defaults (`getValue()`).
- `registerActivityType` / `registerFruit` / `registerTreeEffect` append via `IRegistryChromosome.registerValue` after the enum/default populate in static init (CE populates once after all plugins).
- `ITreeManager` wraps existing `WoodAccess` + `CharcoalManager` plus a refractory-wax `Block → Block` map. Forestry and vanilla planks are registered as waxable into their fireproof counterparts.
- Swarmer materials: royal jelly `0.01` is registered through `registerSwarmerMaterial` instead of a hardcoded item check.
- `IFruit` / `ITreeEffect` / `IActivityType` live in the API packages. The old same-package `IFruit` / `ITreeEffect` aliases were removed so addon implementations of the API type can sit on those chromosomes. `IBeeSpecies` / `ITreeSpecies` aliases remain in the impl packages so existing call sites compile.
- `setFactory` is stored on the builder; default species still build `BeeSpecies` / `TreeSpecies` records. Custom factories are available to `modifySpecies` addons that call `createSpeciesFactory()`.
- `IFruit.getFruitChance` takes `LevelAccessor` like CE (one-arg default kept for local call sites). `getSpecialty()` is the deprecated CE name; `getSpecialties()` delegates to it. Extra Trees can implement `IFruit` without our nested `Product` record.
- `Tree.getProducts()` / `getSpecialties()` read the fruit chromosome, not the empty species defaults.
- `api.genetics.alleles.ForestryAlleles` re-exports the constant alleles Extra Bees uses in `setGenome`. Effect fields stay on the impl class because they are filled after plugin registration.
- `IBeeSpeciesType` / `ITreeSpeciesType` stay thin (no `ISpeciesType` bound) but now expose CE lookups: bee effect / activity / jubilance, fruit / tree effect / `getTree(IGenome)`.
- `IBeeSpeciesBuilder.addSpecialty(ItemStack)` keeps stack count.
- `IBee.getSuitableBiomes` returns an empty list (climate biome scan is not wired). Production, pollen, flowers, and `getCanWork` delegate to existing `BeeCanWork` / effect alleles / `PollenManager` / `IFlowerType`.
