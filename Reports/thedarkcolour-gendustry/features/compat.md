# thedarkcolour-gendustry — compat

## Summary

Optional-integration layer for **Gendustry 1.0.5** on Forge **1.20.1**: hooks into **Forestry CE** (machine error registry) and **JEI** (producer recipes, fluid info, gene-sample subtypes, GUI click areas). **13 Java files, ~421 LOC** under `thedarkcolour.gendustry.compat` split into `forestry/` (2 classes + package-info) and `jei/` (+ `producers/` subpackage). No gameplay logic here — block entities in `blockentity` consume `GendustryError`; lang/sprites live in `data` and `assets`. For Re-Forestry this module is a **porting blueprint** for the future `reforestry:gendustry` addon: copy patterns into `com.leon1236.reforestry.gendustry.compat.*`, wire Fabric entrypoints, do **not** depend on the Gendustry jar.

## Player/API surface

| Surface | Detail |
|---|---|
| Forestry errors | 12 `GendustryError` enum constants (`NO_LABWARE` … `NO_PROTEIN`) shown on machine GUIs when `ErrorLogic.setCondition` fails |
| Error ids | `gendustry:<snake_case>` (e.g. `gendustry:no_mutagen`) |
| Error lang | `errors.gendustry.<name>.desc` (title) + `.help` (longer text) — generated in `data.English` |
| Error sprites | `gendustry:errors/<name>` → PNG under Forestry GUI atlas path |
| JEI plugin uid | `gendustry:jei` |
| JEI recipe types | `gendustry:mutagen_producer`, `gendustry:dna_extractor`, `gendustry:protein_liquefier` |
| JEI categories | Mutagen Producer, DNA Extractor, Protein Liquefier — one shared processor GUI layout |
| JEI catalysts | Respective machine block items per category |
| JEI fluid info | Mutagen, Liquid DNA, Protein (1000 mB stacks) with `info.gendustry.*` descriptions |
| JEI subtypes | `gene_sample` item — distinguishes written samples by species/chromosome/allele NBT |
| JEI click areas | Mutatron screen → all registered `MutationRecipe` types; Producer screen → machine-specific producer category |

No public API interfaces exported from this package; consumers are Forestry/JEI loaders and sibling `blockentity` tiles.

## Architecture

```
                    ┌─────────────────────────────────────┐
                    │ META-INF/services/                  │
                    │ forestry.api.plugin.IForestryPlugin │
                    └──────────────┬──────────────────────┘
                                   ▼
                    GendustryForestryPlugin.registerErrors()
                                   │
                                   ▼
                    GendustryError (enum, IError) ◄── ErrorLogic in blockentity/*
                    (12 conditions on 8 machine types)

                    ┌─────────────────────────────────────┐
                    │ @JeiPlugin GendustryJeiPlugin       │
                    └──────────────┬──────────────────────┘
           registerCategories ────┼──── registerRecipes
                    │              │              │
                    ▼              ▼              ▼
     ProducerRecipeCategory*   GRecipeTypes    ForgeTypes.FLUID_STACK
           │                   + RecipeUtils      ingredient info
     Mutagen / DNA / Protein
     RecipeCategory
           │
     registerGuiHandlers ──► MutatronScreen click → MutationRecipe[]
                         ──► ProducerGuiContainerHandler → producer RecipeType
     registerItemSubtypes ──► GeneSampleInterpreter on GItems.GENE_SAMPLE
```

**Forestry branch:** `GendustryForestryPlugin` is the only `IForestryPlugin` implementation; it registers every `GendustryError` via `IErrorRegistration.registerError`. Plugin id is `GendustryModule.MODULE_ID` (`gendustry:core`). Discovery is Java `ServiceLoader` file, not code in `GendustryModule`.

**JEI branch:** Single `IModPlugin` registers three producer categories sharing `ProducerRecipeCategory<T extends ProcessorRecipe>` — background from `textures/gui/processor.png`, animated arrow, fluid output tank slot helper. DNA Extractor adds labware input overlay + tooltip. `GendustryRecipeType` centralizes `RecipeType.create(Gendustry.ID, uid, class)` constants.

**Cross-module error usage (outside compat, cited for contract):**

| Error | Machine(s) |
|---|---|
| `NO_LABWARE` | Producer, Mutatron, Sampler, Genetic Transposer, Imprinter |
| `NO_SAMPLES` | Sampler |
| `NO_MUTAGEN`, `NO_MATES`, `INCOMPATIBLE_SPECIES`, `NO_MUTATIONS` | Mutatron (via `AbstractMutatronBlockEntity`) |
| `NO_SELECTION` | Advanced Mutatron |
| `NO_BLANK`, `NO_SOURCE` | Genetic Transposer |
| `NO_TEMPLATE` | Replicator, Imprinter |
| `NO_DNA`, `NO_PROTEIN` | Replicator |

Graphify (alias `gendustry`): `GendustryError` community ~18; `GendustryJeiPlugin` ~16 with edges to registry (`GItems`, `GFluids`, `GRecipeTypes`), client screens, and producer categories.

## Data & assets

**Error sprites** (13 PNG, 16×16-style icons):

`src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/`

- `no_labware.png`, `no_samples.png`, `incompatible_species.png`, `no_mutations.png`, `no_mates.png`, `no_mutagen.png`, `no_template.png`, `no_selection.png`, `no_blank.png`, `no_source.png`, `no_dna.png`, `no_protein.png`

**JEI GUI atlas:**

- `assets/gendustry/textures/gui/processor.png` — category background (151×60 crop), arrow animation strip (176,60), tank overlay (176,0), labware slot (176,78)

**Lang (owned by `data`, consumed here):**

| Key prefix | Used by |
|---|---|
| `errors.gendustry.*.desc` / `.help` | `GendustryError` + datagen |
| `info.gendustry.mutagen` / `.dna` / `.protein` | JEI fluid info |
| `gendustry.for.chance` | Intended labware tooltip (see gaps) |
| Block translation keys | Category titles via `GBlocks.MACHINE.get(type).getTranslationKey()` |

No recipes/datapack JSON in this package — JEI pulls live recipes from `GRecipeTypes` at runtime.

## Dependencies

**Within Gendustry**

| Dep | Role |
|---|---|
| `Gendustry.loc` / `Gendustry.ID` | Error ids, JEI uid, recipe type namespace |
| `GendustryModule.MODULE_ID` | Forestry plugin id |
| `registry.GBlocks`, `GItems`, `GFluids`, `GRecipeTypes` | Catalysts, fluids, recipe type keys |
| `recipe.*` (`MutagenRecipe`, `DnaRecipe`, `ProteinRecipe`, `ProcessorRecipe`) | JEI category generics |
| `block.GendustryMachineType` | Machine metadata for categories |
| `item.GendustryResourceType` | Labware stack in DNA Extractor layout |
| `client.screen.MutatronScreen`, `ProducerScreen` | GUI handler targets |
| `blockentity.*` (BE types in `ProducerGuiContainerHandler`) | Resolve which click area to expose |
| `data.TranslationKeys` | JEI info string constants |

**Forestry CE (compile + runtime)**

- `forestry.api.core.IError`
- `forestry.api.plugin.IForestryPlugin`, `IErrorRegistration`
- `forestry.core.recipes.jei.ForestryRecipeCategory`
- `forestry.core.utils.RecipeUtils`, `forestry.core.ClientsideCode`
- `forestry.apiculture.compat.MutationRecipe` (Mutatron JEI link only)

**JEI (optional at runtime, compilePresent)**

- `IModPlugin`, `@JeiPlugin`, category/catalyst/recipe/GUI/subtype registration APIs
- `ForgeTypes.FLUID_STACK` (Forge fluid ingredient type)
- `VanillaTypes.ITEM_STACK`

**Forge / NeoForged**

- `net.minecraftforge.fluids.FluidStack` in `ProducerRecipeCategory.addFluidTank`
- Service file under `META-INF/services/`

**Not in this package:** Patchouli, Curios, ModKit — no compat classes for them in Gendustry.

## Notable algorithms/contracts

1. **Error registration:** Enum constructor builds id (`gendustry:<lowercase>`), sprite (`gendustry:errors/<name>`), and translation keys (`errors.gendustry.<name>.desc|help`) from constant name — mirrors CE `ForestryError` shape.
2. **`setCondition` polarity:** Call sites pass `errors.setCondition(problemIsTrue, error)` — error active when condition is **true** (missing input). Same contract as Re-Forestry `ErrorLogic`.
3. **Producer JEI layout:** Input item slot (1,23); fluid output tank (109,1) with 10000 mB renderer cap; animated arrow at (35,23). Subclasses only differ in ingredient vs default species stack + labware overlay (DNA).
4. **DNA Extractor display:** Shows default species stack for recipe's `speciesType` + stage; labware slot with 10% consumption tooltip.
5. **Gene sample subtypes:** For `UidContext.Recipe`, all samples collapse to `"written"`; otherwise NBT keys `speciesType`, `chromosome`, `allele` concatenated; empty → `NONE`.
6. **Mutatron click area:** Rectangle (68,38,55,18) opens **every** JEI `RecipeType` whose recipe class is `MutationRecipe` — relies on Forestry/apiculture registering mutation types separately.
7. **Producer click area:** Shared handler inspects open BE class → maps to one of three `GendustryRecipeType` constants at (48,40,55,18).

## Port relevance to Re-Forestry

| Component | Verdict for RF (Fabric 26.2, `reforestry:gendustry`) |
|---|---|
| `GendustryError` + sprites + lang | **Port with addon** — implement enum implementing `com.leon1236.reforestry.api.core.IError`; register in gendustry module `init()` via `ErrorManager` (same pattern as `ModuleCore.registerErrors()`). Re-Forestry `IForestryPlugin` has **no** `registerErrors` hook yet — either extend API or register directly in `ModuleGendustry`. Copy 12 PNGs to `assets/reforestry/.../errors/` (or `reforestry:gendustry` namespace per addon rules). |
| `GendustryForestryPlugin` | **Rewrite discovery** — replace `META-INF/services/forestry.api.plugin.IForestryPlugin` with Fabric `reforestry:plugin` entrypoint **or** inline registration in module init. |
| `GendustryJeiPlugin` | **High-value port** — add `jei_mod_plugin` entry under gendustry package when machines/recipes exist. Mirror `FactoryJeiPlugin` structure: separate plugin uid `reforestry:gendustry`, use `JeiRecipeSources` instead of `ClientsideCode.getRecipeManager()`. |
| `ForestryRecipeCategory` base | **Already ported** — `com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory` (26.2 uses `GuiGraphicsExtractor`, explicit width/height). Subclass like factory categories; drop CE `getBackground()` override style. |
| `ForgeTypes.FLUID_STACK` / `FluidStack` | **Replace** — use `IPlatformFluidHelper` + Fabric transfer fluid stacks (see `CarpenterRecipeCategory`, `FermenterRecipeCategory`). |
| `ProducerRecipeCategory.addFluidTank` | **Adapt** — same slot layout idea; fluid renderer API differs on Fabric JEI 26.2. |
| `GeneSampleInterpreter` | **Port when gene sample item exists** — rewrite NBT reads to **data components** / genetics API types; keep subtype split for recipe vs ingredient focus. |
| Mutatron → `MutationRecipe` click area | **Depends on apiculture JEI** — RF must expose mutation recipe types (CE `MutationRecipe` equivalent) before this handler works; coordinate with apiculture compat module. |
| `ProducerGuiContainerHandler` | **Port with screens/BEs** — map tile types to recipe types; RF screen/menu class names will differ. |

Standalone-adopt: all code lives under `com.leon1236.reforestry.gendustry.compat`; JEI remains optional (`FabricLoader.isModLoaded("jei")` is handled by loader via entrypoint). No Gendustry or Forestry CE jar dependency for players.

**Priority:** After `blockentity`, `recipe`, and `item` — compat is quick wiring once machines and fluids exist. Errors can land incrementally as each machine is ported.

## Source map

Clone root: `MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry`

| File | Lines | Role |
|---|---|---|
| `.../compat/forestry/GendustryError.java` | 59 | 12 machine error enum (`IError`) |
| `.../compat/forestry/GendustryForestryPlugin.java` | 22 | Registers errors via Forestry plugin API |
| `.../compat/forestry/package-info.java` | 3 | Nullness defaults |
| `.../compat/jei/GendustryJeiPlugin.java` | 83 | JEI plugin entry — categories, recipes, catalysts, GUI, subtypes |
| `.../compat/jei/GendustryRecipeType.java` | 17 | Three `RecipeType` constants |
| `.../compat/jei/GeneSampleInterpreter.java` | 26 | Gene sample JEI subtype UID |
| `.../compat/jei/package-info.java` | 3 | Nullness defaults |
| `.../compat/jei/producers/ProducerRecipeCategory.java` | 58 | Abstract processor JEI layout + fluid tank helper |
| `.../compat/jei/producers/MutagenRecipeCategory.java` | 33 | Mutagen producer category |
| `.../compat/jei/producers/DNAExtractorRecipeCategory.java` | 52 | DNA extractor + labware overlay/tooltip |
| `.../compat/jei/producers/ProteinProducerRecipeCategory.java` | 33 | Protein liquefier category |
| `.../compat/jei/producers/ProducerGuiContainerHandler.java` | 29 | Producer screen recipe click areas |
| `.../compat/jei/producers/package-info.java` | 3 | Nullness defaults |

Related (other modules, cited only):

- `src/main/resources/META-INF/services/forestry.api.plugin.IForestryPlugin` — plugin discovery
- `src/main/java/thedarkcolour/gendustry/data/English.java` L57–68, L109–115 — error translations
- `src/main/java/thedarkcolour/gendustry/blockentity/*` — `GendustryError` consumers
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/*.png` — error icons
- `src/main/resources/assets/gendustry/textures/gui/processor.png` — JEI category GUI

Re-Forestry analogues already in tree:

- `core/compat/jei/ForestryRecipeCategory.java`, `FactoryJeiPlugin.java`, `ReforestryJeiRecipeTypes.java`
- `api/core/IError.java`, `core/errors/ErrorLogic.java`, `api/core/ForestryError.java`
- `fabric.mod.json` → `jei_mod_plugin`, `reforestry:plugin` entrypoints

## Open questions/gaps

1. **Labware tooltip key bug:** `DNAExtractorRecipeCategory` uses `"gen.for.chance"` but datagen defines `gendustry.for.chance` (`TranslationKeys.JEI_LABWARE_CHANCE`). Tooltip may show raw key in game — fix when porting (use constant).
2. **`GeneSampleInterpreter` NBT keys** — assumes legacy tag names; confirm against final gene sample storage in RF (likely data components + genetics ids).
3. **Re-Forestry plugin error hook** — CE/Gendustry use `IForestryPlugin.registerErrors`; RF plugin API lacks this — decide whether gendustry addon extends `IForestryPlugin` or registers errors only in module init.
4. **Mutation JEI integration** — Gendustry defers mutation categories to Forestry; verify RF apiculture JEI registers compatible mutation `RecipeType`s before wiring Mutatron click area.
5. **JEI on Fabric 26.2** — confirm `IIngredientSubtypeInterpreter` / `UidContext.Recipe` behavior unchanged for written gene samples.
6. **Scope boundary** — no EMI/Jade/REI compat in upstream Gendustry; optional Phase 2 if RF wants parity beyond JEI.
