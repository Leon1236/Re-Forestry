# thedarkcolour-gendustry — data

**Repo:** `thedarkcolour-gendustry` (alias: `gendustry`)  
**Clone:** `MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry`  
**Package:** `thedarkcolour.gendustry.data`  
**MC / loader:** 1.20.1 Legacy Forge (NeoForged moddev)  
**Scope:** This report covers **only** the nine Java files in `thedarkcolour.gendustry.data` (~830 LOC). Runtime consumers of `TranslationKeys` are noted where they define the public contract.

---

## Summary

The `data` package is Gendustry’s **build-time datagen hub**. A single entry method, `Data.gatherData`, registers Forge `DataProvider`s through **ModKit’s `DataHelper`**, which emits:

- English language (`en_us.json`)
- Crafting + custom machine fluid recipes
- Block/item models (machines + fluid blocks)
- Block loot tables (machines drop themselves)
- Block and item tags

Nothing in this package runs in-game. It is wired from `GendustryModule.registerEvents` via `modBus.addListener(Data::gatherData)` and executed with the Gradle `data` run (`--mod gendustry --all --output src/generated/resources/`).

`Recipes.java` (~64% of package LOC) is the behavioral core: it encodes **all** Gendustry progression (fluids, components, upgrades, machines). `English.java` is the player-facing text layer (hints, errors, tooltips, JEI blurbs). `TranslationKeys.java` is the **only public class** and is imported at runtime by screens, items, and JEI.

Non-English locales (`zh_cn`, `es_mx`, `ru_ru`, `ja_jp`) live as **hand-maintained** files under `src/main/resources/assets/gendustry/lang/`; this package generates English only.

---

## Player / API surface

### What players see (generated content)

| Category | Source class | Player-visible result |
|---|---|---|
| Machine recipes | `Recipes` | Crafting tables produce all Gendustry machines, resources, upgrades |
| Fluid producer recipes | `Recipes` | JEI/datapack entries for Mutagen, Protein, Liquid DNA inputs/outputs |
| Genetic template combine | `Recipes` | Special crafting recipe (`genetic_template` serializer) |
| Pollen kit | `Recipes` | Shapeless labware + string + paper |
| Wipe recipes | `Recipes` | Smelting filled gene sample / genetic template → blank forms |
| Tooltips | `English` | Upgrade descriptions, blank template/sample hints |
| Machine hints | `English` | In-GUI hint ledger entries (`for.hints.{key}.tag` / `.desc`) |
| Error messages | `English` | Machine failure titles/help for `GendustryError` enum |
| JEI info | `English` | Mutagen / DNA / Protein descriptions; labware consumption chance |
| Creative tabs | `English` | `itemGroup.gendustry`, `itemGroup.gene_samples` |
| Name overrides | `English` | e.g. DNA Extractor block, Liquid DNA fluid, parameterized gene items |
| Tags | `ModTags` | `#gendustry:upgrades` groups all upgrade items; machines mineable with pickaxe |
| Models | `BlockModels` | Machine `cubeBottomTop` (_side/_bottom/_top textures); fluid particle-only blockstates |
| Loot | `BlockLoot` | All machine blocks drop themselves |

### Public runtime API from this package

Only **`TranslationKeys`** is public. Constants are consumed outside `data` by:

| Consumer | Keys used |
|---|---|
| `GendustryUpgradeItem` | `UPGRADE_ENERGY_COST`, `UPGRADE_STACK_LIMIT` |
| `GeneticTemplateItem` | `TEMPLATE_*` (missing allele, entry format, count) |
| `GendustryJeiPlugin` | `JEI_INFO_*`, `JEI_LABWARE_CHANCE` |
| Client screens (`ProducerScreen`, `ThreeInputScreen`, `ReplicatorScreen`, `IndustrialApiaryScreen`, `AbstractMutatronScreen`) | Various `HINT_*` keys (resolved via `for.hints.{hint}.tag/desc` in lang) |

Hint constants in `TranslationKeys` (e.g. `HINT_MUTAGEN_USAGE = "mutagen_usage"`) are **suffixes**, not full lang keys. `English.addHint` expands them to Forestry-style `for.hints.{suffix}.tag` / `.desc`.

All other classes are package-private datagen helpers with no runtime surface.

---

## Architecture

### Bootstrap flow

```
GendustryModule.registerEvents
  └─ modBus.addListener(Data::gatherData)   // try/catch ClassCastException for non-datagen env
       └─ DataHelper(Gendustry.ID, event)
            ├─ createEnglish(true, English::addTranslations)
            ├─ createRecipes(Recipes::addRecipes)
            ├─ createBlockModels(BlockModels::addBlockModels)
            ├─ createItemModels(true, true, false, null)   // auto 3D block items + 2D items
            ├─ createTags(BLOCK, ModTags::addBlockTags)
            ├─ createTags(ITEM, ModTags::addItemTags)
            └─ generator.addProvider(LootProvider)         // manual, not via DataHelper
```

### Class roles

| File | Visibility | Role |
|---|---|---|
| `Data.java` | public | Orchestrator; sole GatherDataEvent handler |
| `Recipes.java` | package | All recipe definitions; delegates to `MKRecipeProvider` + custom `*FinishedRecipe` |
| `English.java` | package | Custom translations layered on ModKit auto-generated registry names |
| `TranslationKeys.java` | **public** | Stable string constants for runtime UI |
| `BlockModels.java` | package | Machine + fluid blockstate/model generation |
| `ModTags.java` | package | `#gendustry:upgrades`, `minecraft:mineable/pickaxe` for machines |
| `BlockLoot.java` | package | `dropSelf` for every `GendustryMachineBlock` |
| `LootProvider.java` | package | Thin `LootTableProvider` wrapper around `BlockLoot` |
| `package-info.java` | — | MC nullability defaults |

### Design patterns

1. **ModKit delegation** — Gendustry does not subclass vanilla `RecipeProvider` / `LanguageProvider` directly; it passes static method references into `DataHelper`.
2. **Enum-driven generation** — Recipes iterate `GendustryMachineType`, `GendustryUpgradeType`, `EliteGendustryUpgradeType`, `GendustryResourceType`, and `GFluids` rather than hard-coding registry ids.
3. **Custom finished recipes** — Fluid producer recipes bypass shaped/shapeless helpers; they emit `MutagenFinishedRecipe`, `ProteinFinishedRecipe`, `DnaFinishedRecipe` with JSON shaped for Gendustry serializers (`GRecipeTypes.*`).
4. **Forestry genetics at datagen time** — DNA recipes call `IForestryApi.INSTANCE.getGeneticManager().getSpeciesType(...)` so species type ids in JSON match live registry.
5. **TranslationKeys decoupling** — Runtime code never embeds lang strings; datagen owns the text, runtime owns the key constants.

---

## Data & assets

### Recipe inventory (`Recipes.addRecipes`)

| Group | Count (approx.) | Notes |
|---|---|---|
| Genetic template special | 1 | `GRecipeTypes.GENETIC_TEMPLATE_SERIALIZER` |
| Mutagen producer | 4 | redstone, glowstone dust/block, glowstone block; **TODO** yellorium/uranium |
| Protein liquefier | 8 | pork, beef, rabbit, cod, salmon, pufferfish, tropical fish |
| DNA extractor | 10 | bee (drone/princess/queen/larvae), tree (sapling/pollen), butterfly (4 stages) |
| Resource shaped | 9 | labware, frames, climate/power/genetics/environmental processors, blanks, receptacle |
| Resource smelting | 2 | wipe gene sample / genetic template (renamed ids with `_wipe_dna` suffix) |
| Standard upgrades | 16 | all `GendustryUpgradeType` values |
| Elite upgrades | 6 | all `EliteGendustryUpgradeType` values |
| Machines | 10 | all `GendustryMachineType` values (Advanced Mutatron recycles base Mutatron) |
| Pollen kit | 1 | shapeless |

**DNA yield values (mB):** drone 100, princess 500, queen 600, larvae 300; sapling 100, pollen 400; butterfly 200, serum 800, caterpillar/cocoon 1000.

**Mutagen yields:** redstone 100, glowstone dust 200, glowstone block 800, redstone block 900.

**Protein yields:** large meat 500, small meat/fish 250.

### Tags (`ModTags`)

- `#gendustry:upgrades` — all items from `GItems.UPGRADE` and `GItems.ELITE_UPGRADE` feature groups.
- `#minecraft:mineable/pickaxe` — all `GBlocks.MACHINE` blocks.

### Models (`BlockModels`)

- **Machines:** `cubeBottomTop` using `{name}_side`, `{name}_bottom`, `{name}_top` textures (via Forestry `ForestryBlockStateProvider.path`).
- **Fluids:** particle-only model from fluid texture resource `[0]`; no full fluid model geometry in this package.

### Loot (`BlockLoot` + `LootProvider`)

- Copied pattern from ExDeorum (comment cites source).
- Tracks `added` blocks because `getKnownBlocks()` must list generated entries.
- Only machine blocks; fluids/resources rely on default item drops or other providers.

### Language (`English`)

- **16 machine hints** covering every major machine and fluid loop.
- **11 `GendustryError` entries** (labware, samples, species, mutations, mates, mutagen, template, selection, blanks, source, DNA, protein).
- **16 standard + 6 elite upgrade tooltips**.
- **2 resource tooltips** (blank template, blank sample).
- Energy tooltip uses **"RF"** string (`UPGRADE_ENERGY_COST`).
- Error lang path: `errors.{namespace}.{path}.desc` / `.help` matching Forestry error UI convention.

### Expected generated output paths (when datagen run)

| Provider | Output (typical) |
|---|---|
| MKRecipeProvider | `data/gendustry/recipes/*.json` |
| MKEnglishProvider | `assets/gendustry/lang/en_us.json` |
| MKBlockModelProvider / MKItemModelProvider | `assets/gendustry/models/**`, `assets/gendustry/blockstates/**` |
| MKTagsProvider | `data/gendustry/tags/**` |
| LootProvider | `data/gendustry/loot_tables/blocks/*.json` |

The clone’s `src/generated/` directory is empty in the workspace snapshot; assets are produced on developer `runData` / CI datagen.

---

## Dependencies

### Inbound (what `data` imports)

| Dependency | Usage |
|---|---|
| **ModKit** (`thedarkcolour.modkit.data.*`) | `DataHelper`, `MKRecipeProvider`, `MKBlockModelProvider`, `MKEnglishProvider`, `MKTagsProvider` |
| **Forge datagen** | `GatherDataEvent`, `DataGenerator`, `PackOutput`, `FinishedRecipe`, loot providers |
| **Forge registries** | `RegistryObject`, `ForgeRegistries`, `Tags` (common tags) |
| **Forestry CE API** | `ForestryConstants`, `ForestryTags`, `IForestryApi`, life stages, species types, `FeatureItem`, `FeatureBlock`, `IError`, `ForestryBlockStateProvider.path` |
| **Gendustry impl** | `Gendustry`, registries (`GBlocks`, `GItems`, `GFluids`, `GRecipeTypes`), enums, `*FinishedRecipe`, `GendustryError`, `GendustryTags` |
| **Vanilla** | items, blocks, tags, recipe categories, ingredients |

### Outbound (what imports `data`)

| Consumer | Import |
|---|---|
| `GendustryModule` | `Data.gatherData` |
| `GendustryUpgradeItem`, `GeneticTemplateItem` | `TranslationKeys` |
| `GendustryJeiPlugin` | `TranslationKeys` |
| Six client screen classes | `TranslationKeys` |

No other modules depend on package-private datagen classes.

### External mod content referenced in recipes

Forestry items via `ForestryConstants` / `ForestryTags`: silk wisp, beeswax, royal jelly, pollen cluster, sturdy machine casing, tin/bronze ingots & gears. Vanilla items/blocks throughout. No optional-mod recipe gates in this package.

---

## Notable algorithms / contracts

### Custom recipe JSON shape

**Mutagen / Protein** (`MutagenFinishedRecipe`, `ProteinFinishedRecipe`):

```json
{ "ingredient": "<ingredient>", "amount": <int mB> }
```

Ids: `gendustry:mutagen/<item_path>`, `gendustry:protein/<item_path>`.

**DNA** (`DnaFinishedRecipe`):

```json
{ "species_type": "<resource location>", "stage": "<serialized life stage>", "amount": <int mB> }
```

Ids: `gendustry:dna/<stage_name>`. Serializer type resolved from `GRecipeTypes.DNA`.

### Genetic template special recipe

Registered via `recipes.special("combine_genetic_template", GRecipeTypes.GENETIC_TEMPLATE_SERIALIZER)` — crafting-table combination of blank template + gene samples (logic in `GeneticTemplateRecipe`, not in this package).

### Smelting rename trick

`recipes.renameRecipes(oldId -> oldId.withSuffix("_wipe_dna"), ...)` wraps smelting recipes so gene wipe recipes get distinct ids from any conflicting defaults.

### Hint key contract

```
TranslationKeys.HINT_*  →  suffix string
English.addHint(lang, suffix, title, desc)
  → for.hints.{suffix}.tag = title
  → for.hints.{suffix}.desc = desc
```

Screens pass `TranslationKeys.HINT_*` to Forestry hint UI; they must stay in sync with `English` entries.

### Error key contract

```
GendustryError.getId() → ResourceLocation
English.addError → errors.{namespace}.{path}.desc / .help
```

### Block loot tracking

`BlockLoot` overrides `add` to accumulate blocks in `added` list because parent `BlockLootSubProvider` requires explicit `getKnownBlocks()` — pattern for mods that only drop-self a dynamic machine set.

---

## Port relevance to Re-Forestry

**Target addon namespace:** `reforestry:gendustry` (per project conventions). Gendustry content should ship as a Re-Forestry addon module, not a separate mod dependency.

### Current Re-Forestry state

- `ReForestryDataGenerator` exists as an **empty** Fabric `DataGeneratorEntrypoint`.
- Core Re-Forestry already has hint infrastructure (`ForestryHints`, `GuiHintLedger`, `for.hints.*` in `en_us.json`) — **compatible** with Gendustry’s hint key pattern.
- No Gendustry datagen or recipes exist in Re-Forestry yet.

### Port strategy (recommended order)

1. **`TranslationKeys`** — Copy/adapt first; rename namespace `gendustry` → `reforestry` where keys are mod-scoped (`item.gendustry.*` → `item.reforestry.gendustry.*` or unified reforestry keys). Keep hint suffix constants stable if screens already reference them.
2. **Language** — Port `English` content into a Fabric `FabricLanguageProvider` subclass (or JSON template + datagen). Merge into `assets/reforestry/lang/en_us.json` under the gendustry addon section. Re-Forestry already maintains many locales manually — same split as CE (datagen EN, hand-translate others).
3. **Recipes** — Port `Recipes` logic to Fabric `RecipeProvider` + Re-Forestry registries. Replace:
   - `MKRecipeProvider` → project recipe helpers or inline Fabric API
   - `ForestryTags` / `ForestryConstants` → `reforestry` equivalents
   - `RegistryObject.create(ForestryConstants...)` → direct Re-Forestry item references
   - `Tags.Items.*` Forge tags → Fabric `c:*` or Re-Forestry tag conventions
   - `*FinishedRecipe` classes live in `recipe` package — must exist before datagen runs
4. **Tags** — `GendustryTags.Items.UPGRADES` → Re-Forestry tag id; register in `FabricTagProvider`.
5. **Models** — Port `BlockModels` to Fabric model provider; fluid blocks may use Re-Forestry fluid rendering conventions instead of particle-only stub.
6. **Loot** — Port `BlockLoot`/`LootProvider` to Fabric loot table provider.
7. **Orchestrator** — Replace `Data.gatherData` + ModKit with `ReForestryDataGenerator.onInitializeDataGenerator` registering Fabric providers (optionally gated to `reforestry:gendustry` feature flag).

### Do not port as-is

| Gendustry pattern | Re-Forestry replacement |
|---|---|
| ModKit `DataHelper` | Fabric datagen providers per type |
| Forge `GatherDataEvent` on mod bus | `DataGeneratorEntrypoint` |
| `RegistryObject` / `FeatureItem.get()` | Re-Forestry `FeatureGroup` / direct registry holders |
| Forge `Tags.Items` | Fabric convention tags |
| RF in tooltips | Re-Forestry energy unit (FE / project standard) |
| `IForestryApi` at datagen | Re-Forestry genetics API / static species type ids if API unavailable at datagen |

### Extraction vs runtime generation

All recipe **values** (DNA mB amounts, mutagen tiers, shaped patterns) are authoritative in `Recipes.java`. For Re-Forestry, prefer **datagen over hand-written JSON** so balance changes stay in one Java file, matching Gendustry’s approach.

### Standalone adopt checklist

- No ModKit or Gendustry jar dependency at runtime or in `fabric.mod.json`.
- Copy recipe definitions and lang into `com.leon1236.reforestry.gendustry.data` (or addon path).
- Generated assets land under `assets/reforestry` and `data/reforestry` with gendustry-prefixed ids.

---

## Source map

| File | Lines | Responsibility |
|---|---:|---|
| `data/Data.java` | 30 | Datagen entry; ModKit provider registration |
| `data/Recipes.java` | 530 | All recipes (custom fluids + crafting + smelting) |
| `data/English.java` | 122 | Lang: hints, errors, tooltips, JEI, overrides |
| `data/TranslationKeys.java` | 33 | Public runtime key constants |
| `data/BlockModels.java` | 37 | Machine + fluid block models |
| `data/ModTags.java` | 21 | Upgrade + mineable tags |
| `data/BlockLoot.java` | 40 | Machine self-drop loot |
| `data/LootProvider.java` | 14 | Loot table provider shell |
| `data/package-info.java` | 3 | Nullability annotations |

**Related code outside package (not documented here, needed for port):**

| Path | Relevance |
|---|---|
| `GendustryModule.java` | Wires `Data::gatherData` |
| `recipe/MutagenFinishedRecipe.java`, `ProteinFinishedRecipe.java`, `DnaFinishedRecipe.java` | Custom recipe JSON emitters |
| `registry/GRecipeTypes.java` | Serializer/type ids for generated recipes |
| `api/GendustryTags.java` | Tag id targeted by `ModTags` |
| `compat/forestry/GendustryError.java` | Error enum referenced in `English` |
| `item/*Type.java` enums | Iteration sources for recipes |
| ModKit `DataHelper` + providers | Datagen framework to replace on Fabric |

**Graphify:** `Data.gatherData` ← `GendustryModule`; `Recipes` reachable from `Data` via shared `Gendustry` mod id node. Downstream recipe consumers (block entities, JEI caches) sit outside this package.

---

## Open questions / gaps

1. **Incomplete mutagen recipes** — `Recipes` has `// todo Yellorium and Uranium`; confirm whether Re-Forestry should add equivalent mod-compat entries or omit.
2. **Generated assets missing from clone** — `src/generated/resources` is empty; verify datagen in upstream CI before treating JSON as reference.
3. **Non-English locales** — Four hand-maintained lang files exist; port workflow must decide whether gendustry addon strings are datagen-EN-only or synced across Re-Forestry’s locale set.
4. **Energy unit** — Tooltips say RF; Re-Forestry uses a different energy API — string and conversion need a project decision.
5. **Environmental processor** — Has a crafting recipe but no tooltip in `English`; intentional or oversight?
6. **`ENVIRONMENTAL_PROCESSOR` usage** — Crafted in datagen; confirm machine/recipe usage elsewhere before porting recipe without gameplay role.
7. **Fluid block models** — Particle-only models may differ from Re-Forestry fluid rendering; may need client-side fluid handler instead of datagen blockstate.
8. **Datagen-time genetics API** — `DnaFinishedRecipe` calls live `IForestryApi`; Fabric port must ensure species type ids are stable at datagen (static constants vs runtime lookup).
9. **Item model customization** — `createItemModels(true, true, false, null)` auto-generates most item models; gene sample/template parameterized names need `English` overrides — confirm Re-Forestry item model strategy for dynamic NBT items.
10. **Feature gating** — Should gendustry datagen register only when the `reforestry:gendustry` addon module is enabled?

---

*Phase 1 module report — generated from clone snapshot 2026-07-24. Package-only scope.*
