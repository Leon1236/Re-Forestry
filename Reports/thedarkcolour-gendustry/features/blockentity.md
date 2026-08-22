# thedarkcolour-gendustry — blockentity

## Summary

Core gameplay package for Gendustry: **10 machine block entities** plus shared base classes, per-machine inventories, and bee-upgrade logic. All tiles extend Forestry CE machine bases (`TilePowered`, `TileBase`) and implement the genetics / apiculture / fluid / energy contracts expected by Forestry GUIs and automation.

Size: **L** — 24 Java files, ~2035 LOC in-package (per inventory). Registered in sibling `registry/GBlockEntities.java`; tick wiring in `block/GendustryMachineType.java`; menus in `menu/`, screens in `client/screen/`.

**Inheritance tree (machine tiles only):**

```
TileBase
 └─ IndustrialApiaryBlockEntity          (powered bee housing; no work-cycle machine)

TilePowered
 ├─ SamplerBlockEntity
 ├─ ImprinterBlockEntity
 ├─ GeneticTransposerBlockEntity
 └─ PoweredTankBlockEntity
     ├─ ProducerBlockEntity<*, ProcessorRecipe>
     │   ├─ MutagenProducerBlockEntity
     │   ├─ DnaExtractorBlockEntity
     │   └─ ProteinLiquefierBlockEntity
     ├─ AbstractMutatronBlockEntity
     │   ├─ MutatronBlockEntity
     │   └─ AdvancedMutatronBlockEntity
     └─ ReplicatorBlockEntity
```

**Non-tile helpers in package:** `IHintTile`, `ApiaryModifiers` (dead legacy), `IndustrialApiaryBeeModifier`, seven `*Inventory` adapters.

## Player/API surface

### Block entity registry ids (`gendustry:*`)

| Registry id | Class | Player name (en_us) | Role |
|---|---|---|---|
| `gendustry:industrial_apiary` | `IndustrialApiaryBlockEntity` | Industrial Apiary | Powered bee housing with 4 upgrade slots (no frames) |
| `gendustry:mutagen_producer` | `MutagenProducerBlockEntity` | Mutagen Producer | Item → mutagen fluid |
| `gendustry:dna_extractor` | `DnaExtractorBlockEntity` | DNA Extractor | Genetics specimen → liquid DNA (+ labware) |
| `gendustry:protein_liquefier` | `ProteinLiquefierBlockEntity` | Protein Liquefier | Food item → protein fluid |
| `gendustry:sampler` | `SamplerBlockEntity` | Sampler | Random allele → gene sample |
| `gendustry:mutatron` | `MutatronBlockEntity` | Mutatron | Two parents + mutagen → random mutation offspring |
| `gendustry:advanced_mutatron` | `AdvancedMutatronBlockEntity` | Advanced Mutatron | Same as mutatron; player picks mutation |
| `gendustry:imprinter` | `ImprinterBlockEntity` | Imprinter | Specimen + genetic template → edited individual |
| `gendustry:genetic_transposer` | `GeneticTransposerBlockEntity` | Genetic Transposer | Copies gene samples / genetic templates |
| `gendustry:replicator` | `ReplicatorBlockEntity` | Replicator | Complete template + DNA + protein → new individual |

### Capabilities exposed (Forge)

| Tile group | Capability | Notes |
|---|---|---|
| `PoweredTankBlockEntity` (+ subclasses) | `ForgeCapabilities.FLUID_HANDLER` | Via `TankManager`; gated with `!remove` |
| `IndustrialApiaryBlockEntity` | `ForgeCapabilities.ENERGY` | `ForestryEnergyStorage` (100k buffer, 1M capacity) |
| All `TilePowered` machines | Energy | Inherited from CE `TilePowered` / `ForestryEnergyStorage` (not re-declared in this package) |

### GUI / menu wiring (out of package, player-visible)

| Machine | Menu class | Screen class |
|---|---|---|
| Industrial Apiary | `IndustrialApiaryMenu` | `IndustrialApiaryScreen` |
| Producers (3) | `ProducerMenu` | `ProducerScreen` |
| Sampler / Imprinter / Genetic Transposer | `ThreeInputMenu.*` | `ThreeInputScreen` |
| Mutatron / Advanced Mutatron | `MutatronMenu` / `AdvancedMutatronMenu` | `AbstractMutatronScreen` (+ advanced subclass) |
| Replicator | `ReplicatorMenu` | `ReplicatorScreen` |

### Hint ledger keys (`IHintTile` / `HINTS_KEY` constants)

Screens map these keys to `for.hints.*` lang entries (see `client/screen/*Screen.java` static `HINTS` blocks):

| Key constant | Typical hint lang ids |
|---|---|
| `gendustry.industrial_apiary` | `industrial_apiary`, `industrial_apiary_upgrades` |
| `gendustry.mutagen_producer` | `mutagen_usage`, `mutagen_ingredients` |
| `gendustry.dna_extractor` | `dna_usage`, `dna_ingredients` |
| `gendustry.protein_liquefier` | `protein_usage`, `protein_ingredients` |
| `gendustry.sampler` | `sample_selection`, `sample_usage`, `sample_reuse` |
| `gendustry.mutatron` | `mutatron_usage`, `advanced_mutatron` |
| `gendustry.imprinter` | `imprinter_usage` |
| `gendustry.genetic_transposer` | `transposer_usage` |
| `gendustry.replicator` | `replicator_usage` |

`IndustrialApiaryBlockEntity` implements Forestry `IGuiBeeHousingDelegate.getHintKey()` instead of `IHintTile`.

### Machine-by-machine contracts

#### Industrial Apiary (`IndustrialApiaryBlockEntity`)

- **Interfaces:** `IBeeHousing`, `IOwnedTile`, `IClimateProvider`, `IGuiBeeHousingDelegate`, `IStreamableGui`, `IPowerHandler`, `IBeeListener`.
- **Energy:** Base 200 FE/tick when bee logic runs; `BASE_ENERGY + upgrade costs` from `IndustrialApiaryBeeModifier.recalculate`.
- **Inventory (15 slots):** queen (0), drone (1), upgrades 2–5 (one per upgrade *type*), unused 6, outputs 6–14 (9 slots; indices 6–14).
- **Tick behavior:** Redstone disables work (`ForestryError.DISABLED_BY_REDSTONE`). Consumes energy only when `beeLogic.canWork()`. Climate refreshed every 64 ticks and on upgrade change. Client runs bee FX when `canDoBeeFX()`.
- **Automation upgrade:** On queen death, `recycleQueen` flag moves princess/drones from outputs back to queen/drone slots next tick.
- **Fertility upgrade:** Spawns extra drones on queen death via `queen.spawnDrones`.
- **Sieve upgrade:** `onPollenRetrieved` routes pollen stacks into output via `addProduct`.
- **Bee modifier:** Single `IndustrialApiaryBeeModifier` instance; work throttle `max(5, 550 - modifier.throttle)`.

#### Mutagen Producer (`MutagenProducerBlockEntity`)

- **Input:** Any item matching `MutagenRecipeCache` (redstone, glowstone, etc.).
- **Output tank:** Mutagen fluid, 10k capacity; auto-fills empty containers every 20 ticks.
- **Work:** 200 ticks/cycle, 100k FE/cycle. No labware slot (`usesLabware=false`).
- **Error when idle:** `ForestryError.NO_RECIPE`.

#### DNA Extractor (`DnaExtractorBlockEntity`)

- **Input:** Any stack with `IIndividualHandlerItem` (bees, trees, butterflies, …).
- **Recipe lookup:** By life stage via `DnaRecipeCache`.
- **Output tank:** Liquid DNA. Labware slot; 10% chance to consume labware per cycle (inherited from `ProducerBlockEntity`).
- **Work:** 50 ticks/cycle, 80k FE/cycle.
- **Error when idle:** `ForestryError.NO_SPECIMEN`.

#### Protein Liquefier (`ProteinLiquefierBlockEntity`)

- **Input:** Items in `ProteinRecipeCache` (raw meats).
- **Output tank:** Protein fluid. No labware.
- **Work:** 100 ticks/cycle, 20k FE/cycle.

#### Sampler (`SamplerBlockEntity`)

- **Slots:** specimen (0), blank gene sample (1), labware (2), output (3).
- **Work:** 20 ticks/cycle, 20k FE/cycle.
- **Algorithm:** Picks random chromosome entry from genome, random active/inactive allele, writes `GeneSampleItem.createStack(type, chromosome, allele)`.
- **Consumes:** 1 specimen, 1 labware, 1 blank sample per cycle.
- **Errors:** `GendustryError.NO_SAMPLES`, `NO_LABWARE`, `ForestryError.NO_SPECIMEN`.

#### Mutatron (`MutatronBlockEntity`)

- **Shared base:** `AbstractMutatronBlockEntity`.
- **Slots:** primary mate (0), secondary mate (1), labware (2), mutagen container in (3), result (4).
- **Mutagen tank:** 10k; requires ≥1000 mB to work; drains 1000 mB per cycle.
- **Mate validation:** Same species type; stages must match `getTypeForMutation(0/1)`; mutations from `IForestryApi.getGeneticManager().getMutations(type).getCombinations(...)`.
- **Selection:** Random index among possible mutations.
- **Work:** 40 ticks/cycle, 100k FE/cycle.
- **Output:** Consumes both parents + labware; creates individual with `createMutatedGenome(mutation)` → `copyWithGenome` → stack at mutation stage 2; registers mutation in opener's `IBreedingTracker`.
- **Container drain:** Every 20 ticks, `FluidHelper.drainContainers` for mutagen cans.

#### Advanced Mutatron (`AdvancedMutatronBlockEntity`)

- Extends mutatron logic; **`hasMutation()`** true when possibilities list non-empty (not when one is chosen).
- **`hasWork()`** additionally requires `getCurrentMutation() != null` or sets `GendustryError.NO_SELECTION`.
- **`onMutationsUpdated`:** Stores full possibilities list; preserves `lastChoice` if still valid.
- GUI exposes `getPossibilities()` for player selection (handled in `menu` / `client` modules).

#### Imprinter (`ImprinterBlockEntity`)

- **Slots:** specimen (0), genetic template (1), labware (2), output (3) — indices aligned with `SamplerInventory` for shared GUI layout.
- **Work:** 80 ticks/cycle, 100k FE/cycle.
- **Algorithm:** Reads alleles from `GeneticTemplateItem.getAlleles(template)`; `genome.copyWith(alleles)`; preserves mate genome on copy.
- **Consumes:** specimen + labware (template not consumed).

#### Genetic Transposer (`GeneticTransposerBlockEntity`)

- **Slots:** blank template/sample (0), filled source (1), labware (2), output (3).
- **Work:** 20 ticks/cycle, 50k FE/cycle.
- **Algorithm:** Copies one item from source stack; blank slot must match source type (blank template ↔ genetic template, blank sample ↔ gene sample).
- **Labware:** 20% consume chance per cycle.
- **Output:** Stacks up to max stack size if compatible.

#### Replicator (`ReplicatorBlockEntity`)

- **Tanks:** Liquid DNA + protein (10k each); ≥1000 mB each required; 1000 mB drained per cycle.
- **Slots:** genetic template (0), DNA can in (1), protein can in (2), output (3). Template must be **complete** (`GeneticTemplateItem.isComplete`).
- **Work:** 50 ticks/cycle, 200k FE/cycle.
- **Output:** Creates individual from species allele + template alleles; bees forced **ignoble** (`setPristine(false)`).
- **Container drain:** DNA and protein cans every 20 ticks.
- **Does not implement `IHintTile`**; screen uses `HINTS_KEY` constant directly.

### Shared powered-machine defaults

Most `TilePowered` subclasses constructed here use **10k FE/tick receive, 100k–1M internal storage** (constructors vary: sampler/imprinter use 100k cap; producers/mutatrons/replicator use 1M).

## Architecture

```
GBlockEntities (registry)
  └─ FeatureTileType → *BlockEntity::new
GendustryMachineType (block enum)
  └─ MachineProperties serverTicker (+ clientTicker for apiary)
       └─ *BlockEntity::serverTick [/ clientTick]

PoweredTankBlockEntity
  ├─ TankManager + FilteredTank(s)
  ├─ NBT + FriendlyByteBuf tank sync
  └─ FLUID_HANDLER capability

ProducerBlockEntity
  ├─ output FilteredTank + ProducerInventory
  ├─ hasWork: recipe match + optional labware
  ├─ workCycle: consume input/labware → fill tank
  └─ serverTick (20): FluidHelper.fillContainers

AbstractMutatronBlockEntity
  ├─ mutagen FilteredTank + MutatronInventory
  ├─ hasWork: mutagen, labware, mates, mutation resolution
  ├─ workCycle: consume mates/labware/mutagen → result individual
  └─ createMutatedGenome (static; marked todo for Forestry API)

IndustrialApiaryBlockEntity
  ├─ ForestryEnergyStorage + beeLogic (HiveManager.createBeekeepingLogic)
  ├─ IndustrialApiaryInventory (IBeeHousingInventory)
  ├─ IndustrialApiaryBeeModifier (IBeeModifier)
  ├─ OwnerHandler + climate provider
  └─ serverTick: power-gated beeLogic.doWork(); upgrade/climate refresh
```

**Graphify (`gendustry`, BFS from machine tiles):** ~86 nodes at depth 2; strong edges to `GBlockEntities`, `GendustryMachineType`, `ThreeInputMenu`/`Screen`, `ProducerMenu`, `IndustrialApiaryMenu`, CE types `TilePowered`, `TileBase`, `FilteredTank`, `ForestryEnergyStorage`, genetics APIs (`IMutation`, `IIndividualHandlerItem`, `IBeeHousing`).

**Error integration:** Machine tiles use CE `IErrorLogic.setCondition` with `ForestryError.*` and `GendustryError.*` (defined in sibling `compat/forestry`).

## Data & assets

Nothing generated inside `blockentity/`; machines rely on sibling modules for recipes/items/fluids. Relevant **generated** assets (context for tile behavior):

| Kind | Paths |
|---|---|
| Blockstates / block models | `src/generated/resources/assets/gendustry/blockstates/{machine}.json`, `models/block/{machine}.json` |
| Loot (preserve inventory) | `src/generated/resources/data/gendustry/loot_tables/blocks/{machine}.json` — all 10 machines |
| Lang (names + errors + hints) | `src/generated/resources/assets/gendustry/lang/en_us.json` |
| Fluids tied to tanks | `gendustry:liquid_dna`, `gendustry:mutagen`, `gendustry:protein` |

GUI textures referenced from screens (not in this package): `assets/gendustry/textures/gui/processor.png`, mutatron layouts, etc.

## Dependencies

| Dependency | Role in this package |
|---|---|
| **Forestry CE** (`forestry.core.tiles.*`, `forestry.api.*`) | Base tiles, error logic, genetics, apiculture, fluids, inventories, energy |
| **Forge 1.20.1** | `ForgeCapabilities.FLUID_HANDLER` / `ENERGY`, `LazyOptional`, `FluidUtil` |
| **Sibling `registry`** | `GBlockEntities`, `GFluids`, `GItems`, `FeatureTileType` |
| **Sibling `recipe` + caches** | `ProcessorRecipe`, `MutagenRecipe`, `DnaRecipe`, `ProteinRecipe` |
| **Sibling `item`** | `GeneSampleItem`, `GeneticTemplateItem`, upgrades, `GendustryResourceType.LABWARE` |
| **Sibling `menu` / `client.screen`** | Containers, hint ledgers, progress widgets |
| **Sibling `compat.forestry`** | `GendustryError` enum |
| **Sibling `api`** | `GendustryTags.Items.UPGRADES` (apiary inventory) |

No direct JEI / Patchouli / Curios references in this package.

## Notable algorithms/contracts

1. **Producer fluid loop** — `hasWork()` binds `currentRecipe` when input changes and calls `startWorking()` to set per-recipe energy/tick costs. `workCycle()` fills internal tank; every 20 ticks `FluidHelper.fillContainers` empties tank into output containers.
2. **Labware RNG** — Producers (DNA only): 10% destroy chance. Genetic Transposer: 20% destroy chance. Mutatron: always consumes 1 labware per cycle.
3. **Mutation genome builder** — `AbstractMutatronBlockEntity.createMutatedGenome` copies mutation result alleles into karyotype builder chromosome-by-chromosome (local copy of logic CE lacks as API).
4. **Breeding tracker attribution** — Last player opening mutatron menu (`lastPlayer` UUID) gets `registerMutation` on successful cycle.
5. **Advanced mutatron state** — `hasMutation()` overridden to mean “possibilities computed”, not “mutation chosen”; extra `NO_SELECTION` gate before work.
6. **Industrial apiary upgrades** — Hardcoded switch on `GendustryUpgradeType` / `EliteGendustryUpgradeType` in `IndustrialApiaryBeeModifier.recalculate`; comment says “If you want an API, open an Issue”.
7. **Climate override** — Nether upgrade → hellish/arid base; heater/cooler/humidifier/dryer shift effective climate via `ClimateState.up()`.
8. **Inventory slot aliasing** — `ImprinterInventory` / `GeneticTransposerInventory` reuse `SamplerInventory` slot index constants so `ThreeInputMenu` layout is shared.
9. **Input-change reset (mutatron)** — `hasWork()` compares primary/secondary stacks by reference (`!=`); duplicate empty check on secondary (lines 98–99) looks like copy-paste; may miss content changes if stack references reused.
10. **`ApiaryModifiers`** — bdew-era POJO; **not referenced** anywhere in the clone; superseded by `IndustrialApiaryBeeModifier`.

## Port relevance to Re-Forestry

**High — future `reforestry:gendustry` addon.** No Gendustry code exists in Re-Forestry yet; this package is the primary gameplay surface to adopt after `item`, `recipe`, and `registry`.

| Gendustry (Forge) | Re-Forestry target |
|---|---|
| `TilePowered` / `TileBase` | `com.leon1236.reforestry.core.tiles.TilePowered` / `TileBase` (already ported; Team Reborn `SimpleEnergyStorage`) |
| `ForestryEnergyStorage` + `ForgeCapabilities.ENERGY` | `EnergyStorage.SIDED` registration on block entities |
| `TankManager` + `ForgeCapabilities.FLUID_HANDLER` | Fabric Transfer API fluid storages (pattern in `factory/tiles/*`, `FluidContainerHelper`) |
| `IIndividualHandlerItem` | Re-Forestry genetics data components / individual handlers |
| `InventoryAdapterTile` | `InventoryAdapter` / `WorldlyContainer` patterns in core + apiculture |
| `IndustrialApiaryBlockEntity` | Closest analogue: `TileBeeHousing` + `BeekeepingLogic`, but needs upgrade slots replacing frames, power gate, and custom `IBeeModifier` |
| Hint keys `gendustry.*` → `for.hints.*` | Re-Forestry already has `GuiHintLedger` using `for.hints.{key}.desc` — reuse same lang shape under `reforestry` namespace |
| `FriendlyByteBuf` GUI sync | Replace with Fabric `ContainerData` / extended menu sync (MC 26.2 `ValueInput`/`ValueOutput` on tiles) |
| `LazyOptional` capabilities | Drop; use Fabric lookups + sided containers |

**Suggested port order:** (1) fluid producers (`PoweredTankBlockEntity` + `ProducerBlockEntity`), (2) three-input genetics machines (`Sampler`/`Imprinter`/`GeneticTransposer`), (3) mutatron pair, (4) replicator, (5) industrial apiary (largest cross-cutting piece).

**Standalone-adopt:** Copy/adapt into `com.leon1236.reforestry.gendustry.*`; do not depend on Gendustry jar. Registry ids should stay `reforestry:*` contract equivalents (`reforestry:mutatron`, etc.) per conventions.

## Source map

| Path | Role |
|---|---|
| `blockentity/PoweredTankBlockEntity.java` | Abstract powered tile + single `TankManager` + fluid capability |
| `blockentity/ProducerBlockEntity.java` | Shared processor logic for 3 fluid producers |
| `blockentity/ProducerInventory.java` | Input / can / labware slots for producers |
| `blockentity/MutagenProducerBlockEntity.java` | Mutagen fluid producer |
| `blockentity/DnaExtractorBlockEntity.java` | DNA fluid producer (labware) |
| `blockentity/ProteinLiquefierBlockEntity.java` | Protein fluid producer |
| `blockentity/AbstractMutatronBlockEntity.java` | Mutagen tank, mating, mutation work cycle, genome builder |
| `blockentity/MutatronBlockEntity.java` | Random mutation pick |
| `blockentity/AdvancedMutatronBlockEntity.java` | Player-selected mutation |
| `blockentity/MutatronInventory.java` | Mate / labware / mutagen can / result slots |
| `blockentity/SamplerBlockEntity.java` | Random allele sampling |
| `blockentity/SamplerInventory.java` | Specimen / blank / labware / output |
| `blockentity/ImprinterBlockEntity.java` | Template imprint onto specimen |
| `blockentity/ImprinterInventory.java` | Shared slot layout with sampler |
| `blockentity/GeneticTransposerBlockEntity.java` | Duplicate samples/templates |
| `blockentity/GeneticTransposerInventory.java` | Blank + source pairing rules |
| `blockentity/ReplicatorBlockEntity.java` | Template + DNA + protein → individual |
| `blockentity/ReplicatorInventory.java` | Template / fluid cans / output |
| `blockentity/IndustrialApiaryBlockEntity.java` | Powered bee housing (largest tile) |
| `blockentity/IndustrialApiaryInventory.java` | Queen, drone, upgrades, outputs |
| `blockentity/IndustrialApiaryBeeModifier.java` | Upgrade effects + energy cost sum |
| `blockentity/ApiaryModifiers.java` | Unused bdew legacy struct |
| `blockentity/IHintTile.java` | Hint ledger key provider |
| `blockentity/package-info.java` | Nullness annotations |
| `registry/GBlockEntities.java` | All 10 `FeatureTileType` registrations |
| `block/GendustryMachineType.java` | Server/client tick method refs |

## Open questions/gaps

1. **`ApiaryModifiers` dead code** — Safe to omit on port, or keep for addon API compatibility?
2. **Mutatron stack reference check** — `hasWork()` uses `!=` on `ItemStack` references when invalidating mutation; confirm whether CE/inventory always replaces stack objects on change (NBT edits could stale mutation state).
3. **Duplicate `currentSecondary.isEmpty()`** in `AbstractMutatronBlockEntity.hasWork()` (lines 98–99) — typo for primary, or harmless redundancy?
4. **`createMutatedGenome` todo** — Does Re-Forestry genetics API already expose equivalent, or must port include this helper?
5. **Producer hint interface** — Subclasses implement `getHintsKey()` but not `IHintTile`; port should unify if using a shared hint contract.
6. **Replicator ignoble-only** — Intentional balance; any CE/IF divergence to verify before port?
7. **Graphify** — Does not model Forge capability edges or runtime recipe cache contents; recipe amounts/energy costs live in sibling `recipe` module (not deep-dived here).
