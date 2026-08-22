# SuperMartijn642-SuperMartijn642sCoreLib — generator

## Summary

The `generator` package is CoreLib’s **Forge datagen DSL**: abstract `ResourceGenerator` subclasses plus nested builders that emit Minecraft JSON (recipes, tags, models, blockstates, loot tables, lang, advancements). Downstream mods subclass a generator, fill builders in `generate()`, and CoreLib writes files through a shared `ResourceCache` during the Forge data-run.

This clone is **Forge 1.16.5** / CoreLib **1.1.22** (`supermartijn642corelib`). The package is **M**-sized by inventory (~14 Java files, ~5700 LOC). Largest file: `RecipeGenerator` (~1593 LOC). Registration wiring lives outside the package (`registry.GeneratorRegistrationHandler`); the two-phase generate→save pipeline is enforced by `mixin.DataGeneratorMixin`.

| Area | Classes | Output roots |
|---|---|---|
| Core | `ResourceGenerator`, `ResourceCache`, `ResourceType` | path construction under `data/` / `assets/` |
| Aggregators | `ResourceAggregator`, `TranslationsAggregator` | multi-provider merge into one file |
| Content DSLs | Recipe / Tag / Model / BlockState / Loot / Language / Advancement | recipes, tags, models, blockstates, loot_tables, lang, advancements |
| Built-ins | `standard.CoreLibMiningTagGenerator`, `standard.CoreLibLanguageGenerator` | CoreLib’s own empty mining tags + one lang key |

## Player / API surface

**No in-game player UI.** Surface is for **mod authors** writing data generators.

### How a dependent mod uses it

1. `GeneratorRegistrationHandler.get(modid).addGenerator(cache -> new MyRecipeGenerator(modid, cache));` (registry package; before `GatherDataEvent`).
2. Subclass the matching abstract generator; implement `generate()`; call protected factory methods (`shaped(...)`, `blockTag(...)`, `model(...)`, etc.).
3. At data-run: mixin runs all `generate()` first (writes disabled), then vanilla providers’ `run` → each generator’s `save()`, then `ResourceCache.finish()` flushes aggregators and checks tracked files were written.

### Author-facing types (this package)

| Type | Role |
|---|---|
| `ResourceGenerator` | Abstract base: `modid`, `cache`, `generate()`, `save()`, `getName()`. Wraps as Forge `IDataProvider` via `createDataProvider`. |
| `ResourceCache` | Abstract IO: exist / track / save bytes or JSON / open existing / aggregator save. Impl: `HashCacheWrapper` (internal). |
| `ResourceType` | `DATA` → `data/`, `ASSET` → `assets/`. |
| `RecipeGenerator` | Shaped / shapeless / smelting(+blasting/smoking/campfire) / smithing / stonecutting; Forge + CoreLib conditions; auto recipe-unlock advancements. |
| `TagGenerator` | Block / item / entity / fluid / generic registry tags; mineable + needs_* helpers; optional/remove/reference; `CustomTagEntry`. |
| `ModelGenerator` | Parented models, cube/slab/stairs helpers, item generated/handheld, elements/faces/transforms; texture existence checks. |
| `BlockStateGenerator` | Variants + multipart; partial property states; model rotation/uvlock/weight. |
| `LootTableGenerator` | Block loot tables; `dropSelf` / silk-touch helpers; pools, rolls, conditions, item/tag/table entries. |
| `LanguageGenerator` | `xx_xx` lang map; `translation` / `item` / `block` / `itemGroup`; merged via `TranslationsAggregator`. |
| `AdvancementGenerator` | Display, criteria, requirements, rewards; Forge/CoreLib conditions on load. |
| `ResourceAggregator<S,T>` | `initialData` / `combine` / `write` — used when multiple generators target one path. |

Typical author pattern: extend one abstract class, override only `generate()`, leave `save()` as provided.

### Built-in CoreLib generators (`standard/`)

- `CoreLibMiningTagGenerator` — creates empty `minecraft` block tags: `mineable/{axe,hoe,pickaxe,shovel}`, `needs_{stone,iron,diamond}_tool` (so other mods’ tag aggregations have a target).
- `CoreLibLanguageGenerator` — `en_us` key `supermartijn642corelib.widgets.scrollbar.narration` → `"scroll bar"`.

Registered from `CoreLib` via `GeneratorRegistrationHandler.get("supermartijn642corelib")`.

## Architecture

```
Dependent mod init
  └─ GeneratorRegistrationHandler.addGenerator(cache → ResourceGenerator)
           │
Forge DataGenerator.run()  ← DataGeneratorMixin (mixin package)
  ├─ after DirectoryCache.keep:
  │    wrap ExistingFileHelper + DirectoryCache → ResourceCache
  │    allowWrites(false)
  │    registerProviders → each ResourceGenerator.createDataProvider
  ├─ before provider loop:
  │    for each DataProviderInstance: generate()   // track + build in-memory
  │    allowWrites(true)
  ├─ each provider.run(DirectoryCache):
  │    DataProviderInstance → save() → ResourceCache.save*
  └─ before purgeStaleAndWrite:
       HashCacheWrapper.finish()  // flush aggregators; assert all tracked paths written
```

**Two-phase contract**

1. **`generate()`** — build in-memory maps/builders; call `cache.trackToBeGeneratedResource(...)` for every path that will be written (so cross-generator `doesResourceExist` sees promised files). Writes are **disallowed**.
2. **`save()`** — serialize builders to JSON/bytes via `cache.saveResource` / `saveJsonResource` / aggregator overload.

**Aggregation** — Tags and lang share paths across generators: `TagGenerator` uses an anonymous `ResourceAggregator` that merges `TagBuilder`s; `LanguageGenerator` uses `TranslationsAggregator.INSTANCE` (conflict on same key with different value → throw).

**Related packages (not in this module)**

| Package | Role |
|---|---|
| `registry.GeneratorRegistrationHandler` | Per-modid queue of generators / raw `IDataProvider`s |
| `mixin.DataGeneratorMixin` (+ accessors) | Injects shared cache + generate-before-save |
| `data.condition` / `data.recipe.ConditionalRecipeSerializer` | Recipe/advancement conditions |
| `data.tag.CustomTagEntry` | Extended tag entry types |
| `registry.Registries` / `RegistryUtil` | Id lookups, namespace validation |
| `util.Either` / `util.Pair` | Registration list + multipart pairs |

## Data & assets

Generators **produce** JSON under the data-run output folder (clone has samples in `src/generated/resources/`):

| Generator | ResourceType | Relative path pattern |
|---|---|---|
| Recipe | DATA | `data/<ns>/recipes/<path>[_{blasting,smoking,campfire}].json` |
| Tag | DATA | `data/<ns>/tags/<blocks\|items\|fluids\|entity_types>/<path>.json` |
| Advancement | DATA | `data/<ns>/advancements/<path>.json` |
| Loot | DATA | `data/<ns>/loot_tables/<path>.json` (block helper → `blocks/<block_id>`) |
| Model | ASSET | `assets/<ns>/models/<path>.json` |
| BlockState | ASSET | `assets/<ns>/blockstates/<block_id>.json` |
| Language | ASSET | `assets/<ns>/lang/<langCode>.json` |

**Validation at save time (examples)**

- Model parent + non-`#` textures must exist (generated or on classpath via `ExistingFileHelper`).
- Recipe pattern keys must be defined and unused keys forbidden.
- Tag references must resolve to another builder or an existing tag JSON.
- Advancement parent / reward recipes / loot / background textures checked when present.
- Tracked-but-never-written paths → `RuntimeException` in `finish()`.
- Duplicate non-aggregated writes → throw.

**Not authored by hand in this package** — no static JSON under `generator/`. Generated mining tags and CoreLib lang are the only checked-in outputs from this stack in the clone.

## Dependencies

**External (Forge 1.16.5 stack)**

| Dependency | Use |
|---|---|
| Minecraft data APIs | `IDataProvider`, `DirectoryCache`, `DataGenerator`, loot/advancement/criterion types |
| Forge | `ExistingFileHelper`, `GatherDataEvent` / DatagenModLoader, `ICondition`, `ModList` / `ModLoadingContext` |
| Guava | `Hashing` / `HashCode`, `Stopwatch` (mixin) |
| Gson | Pretty JSON emit |

**Intra-repo**

| Module | Relationship |
|---|---|
| `registry` | Handler API; `Registries` for serializers/items/blocks |
| `mixin` | Orchestrates shared `ResourceCache` lifecycle |
| `data` | Conditions, conditional recipe wrap, custom tag entries |
| `util` | `Either`, `Pair` |

**Does not depend on** gui / render / network / block / item feature packages (aside from vanilla `Block`/`Item` types in builder APIs).

## Notable algorithms / contracts

1. **Shared cache across providers** — One `HashCacheWrapper` per data run; SHA-1 hash skip if on-disk bytes match; integrates with Forge `DirectoryCache` old/new maps.
2. **Track-then-write integrity** — `trackToBeGeneratedResource` must pair with a later write (or aggregator flush); unfinished tracks fail the run.
3. **Write gate** — `allowWrites(false)` during all `generate()` calls prevents accidental IO before the full set of tracks exists.
4. **Smelting multi-emit** — One `SmeltingRecipeBuilder` can emit multiple JSON files (`""`, `_blasting`, `_smoking`, `_campfire`) with scaled cooking times.
5. **Recipe unlock advancements** — Nested `Advancements` helper inside `RecipeGenerator` builds criterion advancements and copies recipe conditions onto them.
6. **Tag merge** — Same tag id from multiple generators: later builders `addAll` into the first; `replace` / `remove` serialized into Forge-style tag JSON (`values` + `remove`).
7. **Lang merge** — Same key, different string → hard fail; same string OK.
8. **Namespace hygiene** — Invalid modid / lang code / texture refs throw early; active-mod vs declared-modid mismatch logs a warning.

## Port relevance to Re-Forestry

**Do not depend on CoreLib as a library** (standalone rule). Ideas only.

| Idea | Relevance | Notes for Fabric 26.2 |
|---|---|---|
| Two-phase generate / track / save | Medium | Fabric already has `FabricDataGenerator` + providers; the “track promised outputs so cross-provider existence checks work” idea is useful if Re-Forestry grows many interdependent providers. |
| Aggregators for tags/lang | Medium–High | Multiple modules writing one tag/lang file is a real Forestry pain; Fabric tag providers usually own one file — an aggregator pattern (or careful packaging) still helps. |
| Fluent recipe/model/blockstate DSL | Low–Medium | Vanilla/Fabric `RecipeProvider`, `ModelProvider`, etc. already cover this; copying CoreLib’s 1.16 builders wholesale would fight MC 26.2 APIs (smithing shape, loot codecs, model loaders). Prefer Fabric/vanilla providers + small helpers. |
| Conditional recipes via Forge `ICondition` | Low as-is | Map to Fabric recipe conditions / datapack conditions if needed; CoreLib’s wrap lives in `data` module. |
| Empty mining-tag seeding | Low | Re-Forestry should emit real mineable tags for its blocks via Fabric tag datagen, not empty placeholders. |
| Re-Forestry status today | — | `ReForestryDataGenerator` is a stub `DataGeneratorEntrypoint`; most assets/data are hand-authored under `src/main/resources`. Growing datagen is optional polish, not blocked on CoreLib. |

**Recommended adopt stance:** skim for **cache aggregation + existence-tracking** patterns if/when Re-Forestry consolidates datagen; **do not** port the Forge mixin registration path or 1.16 JSON shapes. Prefer Kaupenjoe / Fabric API datagen examples for MC 26.2 wiring.

## Source map

| Path | Lines (approx) | Notes |
|---|---|---|
| `src/main/java/com/supermartijn642/core/generator/ResourceGenerator.java` | 103 | Base + `DataProviderInstance` |
| `src/main/java/com/supermartijn642/core/generator/ResourceCache.java` | 275 | Abstract API + `HashCacheWrapper` |
| `src/main/java/com/supermartijn642/core/generator/ResourceType.java` | 19 | DATA / ASSET |
| `src/main/java/com/supermartijn642/core/generator/RecipeGenerator.java` | 1593 | Largest DSL |
| `src/main/java/com/supermartijn642/core/generator/ModelGenerator.java` | 982 | Models + nested builders |
| `src/main/java/com/supermartijn642/core/generator/BlockStateGenerator.java` | 711 | Variants / multipart |
| `src/main/java/com/supermartijn642/core/generator/TagGenerator.java` | 700 | Tags + aggregator |
| `src/main/java/com/supermartijn642/core/generator/LootTableGenerator.java` | 574 | Loot DSL |
| `src/main/java/com/supermartijn642/core/generator/AdvancementGenerator.java` | 539 | Advancement DSL |
| `src/main/java/com/supermartijn642/core/generator/LanguageGenerator.java` | 79 | Lang + aggregator use |
| `src/main/java/com/supermartijn642/core/generator/aggregator/ResourceAggregator.java` | 16 | Interface |
| `src/main/java/com/supermartijn642/core/generator/aggregator/TranslationsAggregator.java` | 48 | Lang merge |
| `src/main/java/com/supermartijn642/core/generator/standard/CoreLibMiningTagGenerator.java` | 35 | Empty mining tags |
| `src/main/java/com/supermartijn642/core/generator/standard/CoreLibLanguageGenerator.java` | 19 | Scrollbar narration |
| *Related (out of module)* `registry/GeneratorRegistrationHandler.java` | ~160 | Author registration API |
| *Related* `mixin/DataGeneratorMixin.java` | ~120 | Generate/save orchestration |

Clone root: `MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/SuperMartijn642-SuperMartijn642sCoreLib`.

## Open questions / gaps

1. **Multi-version CoreLib** — README advertises shared helpers across older MC versions; this clone is pinned to **1.16.5**. Newer CoreLib branches may have updated generators (render types, smithing templates, 1.21 tags paths) not covered here.
2. **Fluid tags** — `TAG_DIRECTORIES` includes fluids, but there is no dedicated `fluidTag(...)` helper (only generic `tag(Registries.FLUIDS, …)`); confirm intended author API.
3. **Smoking serialization bug?** — In `RecipeGenerator.save()`, blasting builds a fresh `recipeJson`, but smoking/campfire call `serializeCookingRecipe(json, …)` on the primary `json` object in the inspected clone — worth verifying before trusting cooking multi-emit as a reference implementation.
4. **Face `emissivity`** — Written as a model face property; behavior is loader/Forge-era specific and may not match vanilla 26.2 model JSON.
5. **Fabric port of mixin orchestration** — No Fabric equivalent studied in this report; Re-Forestry should use Fabric’s provider pack API rather than inventing a DataGenerator mixin.
6. **Sibling `data` module** — Full condition / `CustomTagEntry` / `ConditionalRecipeSerializer` contracts are deferred to the **data** Phase 1 report; generators only consume them.
