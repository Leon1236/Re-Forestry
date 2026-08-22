# thedarkcolour-ModKit — data

## Summary

Forge 1.20.1 **datagen library** (~2980 LOC, 19 Java files under `thedarkcolour.modkit.data` + nested `loot` / `model` / `recipe`). Facade is `DataHelper`: consumers call `createEnglish` / `createItemModels` / `createBlockModels` / `createRecipes` / `createTags` / `createDamageTypes` from a `GatherDataEvent` handler and pass callbacks instead of subclassing every vanilla/Forge provider. This is ModKit’s main reuse surface — Forestry CE, Immersive Forestry, and Gendustry all wire through it. Not player-facing; intended as a **compile/datagen-only** dependency so the mod JAR need not be installed to play.

## Player/API surface

| Audience | What they see |
|---|---|
| Players | Nothing from this package (no blocks/items/GUIs). |
| Mod authors (datagen) | `DataHelper` + `MK*` providers + `DirectTagAppender` + NBT recipe builders. |
| Runtime | Empty if GatherData lives in a separate class (documented contract: avoid loading datagen types outside `--all` / data runs). |

**Public entrypoints (`DataHelper`):**

- `createEnglish(boolean generateNames, Consumer<MKEnglishProvider>)` — client provider; optional auto-names from registry paths.
- `createModonomiconBooks(...)` — hook to register external book providers before English (no Modonomicon compile dep; callback only).
- `createItemModels(gen3dBlockItems, gen2dItems, genSpawnEggs, Consumer)` — client; optional mass auto-models.
- `createBlockModels(Consumer)` — client; auto-creates item models if missing; warn if item models registered first.
- `createRecipes(BiConsumer<Consumer<FinishedRecipe>, MKRecipeProvider>)` — server.
- `createDamageTypes(Consumer)` — server JSON codec provider for `damage_type`.
- `createTags(ResourceKey<? extends Registry<T>>, BiConsumer|Consumer)` — server; one provider per registry.

**Nested helpers used by authors:**

- `MKRecipeProvider` — shaped/shapeless (with optional result NBT), grids, wood set recipes, cooking, netherite smithing, Forge `ConditionalRecipe`, writer push/rename.
- `MKTagsProvider` / `DirectTagAppender` — `add(T)` / `Supplier` / keys; item←block `copy`; Kotlin-friendly `Function` on tags.
- `MKItemModelProvider` / `MKBlockModelProvider` — templates + `Safe*` builders that log and continue on missing textures/parents.
- `recipe.NbtShapedRecipeBuilder` / `NbtShapelessRecipeBuilder` / `NbtResultRecipe` — crafting builders that serialize `"nbt"` on the result object.
- `loot.MKLootProvider` — **stub only** (not wired to `DataHelper`; unused in-repo).

## Architecture

```
GatherDataEvent (mod bus, separate class)
        │
        ▼
   DataHelper(modid, event)
        │ create*() once each (checkNotCreated)
        ├─► MKEnglishProvider          (LanguageProvider + reflection into private `data` map)
        ├─► MKItemModelProvider        (ModelProvider<SafeItemModelBuilder>)
        ├─► MKBlockModelProvider       (BlockStateProvider → SafeBlockModelProvider)
        ├─► MKRecipeProvider           (RecipeProvider → Nbt* builders / ConditionalRecipe)
        ├─► MKTagsProvider<T>          (TagsProvider → DirectTagAppender; item copy from block)
        └─► MKDamageTypeProvider       (JsonCodecProvider<DamageType> + DamageTypeBuilder)
```

- Providers are registered with `event.includeClient()` / `includeServer()` and hold a **consumer** run inside the provider’s generate method (author logic stays in static methods on the consumer mod).
- Graph communities (modkit graphify): recipes ≈ community 0, tags ≈ 1, item models ≈ 2, block models ≈ 4, English ≈ 6, damage ≈ 7, `DataHelper` hub ≈ 10, Safe block models ≈ 15, NBT builders ≈ 5/8.
- Order contract: **block models before item models** (parents must exist); `DataHelper` logs a warning if reversed.
- Testmod consumer: `src/test/.../testmod/data/DataGen.java` — canonical usage; class has a static guard that throws if loaded outside datagen.

## Data & assets

| Generates | Path / notes |
|---|---|
| `en_us` lang | Auto from `path.replace('_',' ')` + WordUtils.capitalize; overrides via `add` / `add(Object,name)`. |
| Item / block models | JSON under generated resources; optional bulk from registry scan (`MKUtils.forModRegistry`). |
| Recipes + unlock advancements | Standard recipe JSON; shaped/shapeless may emit result `"nbt"`. |
| Tags | Per-registry tag JSON; optional block→item copy. |
| Damage types | `data/<modid>/damage_type/*.json` via codec. |
| Loot | **Not produced** by this package in practice (`MKLootProvider` incomplete). |

Shipped library assets are out of scope for this module (inventory: root resources / generated). ModKit’s own `ModKitDataGen` only demos English + item/block models for the infinite-power block.

## Dependencies

| Dep | Role |
|---|---|
| Minecraft 1.20.1 + Forge `1.20.1-47.0.3` | Host; `GatherDataEvent`, `ExistingFileHelper`, `RecipeProvider`, `TagsProvider`, `ConditionalRecipe`, `JsonCodecProvider`. |
| Forge registries / `RegistryObject` | Registry iteration and DeferredRegister-friendly APIs. |
| fastutil (`Pair`, `ObjectIntPair`, `IntObjectPair`, `CharOpenHashSet`) | Recipe ingredient count pairs; shaped key validation. |
| Apache Commons Lang `WordUtils` | English auto-names. |
| SLF4J | Per-mod logger `modkit/<modid>`. |
| Modonomicon | **Not a Gradle dep** — optional callback registration only. |
| JEI / Fabric / Energy | None. |

Runtime soft-load note (consumers): keep GatherData in a class that is not referenced from `@Mod` init so missing ModKit does not crash players (testmod documents this; CE still uses `@Mod.EventBusSubscriber` on `Data` — they treat ModKit as a hard datagen dep).

## Notable algorithms/contracts

1. **Once-per-helper** — `checkNotCreated` throws if the same `create*` is called twice on one `DataHelper`.
2. **Auto English** — after optional manual `addNames`, scan configured registries for this modid; skip keys already present; log override/duplicate on `add`.
3. **Auto item models** — classify `BlockItem` → 3D parent `block/<path>`; tools → `item/handheld`; spawn eggs → template; else `item/generated` layer0; `exclude(...)` skips auto.
4. **Safe models** — `SafeItemModelBuilder` / `SafeBlockModelBuilder` catch missing-texture / missing-parent errors, log, still write JSON (reduces datagen flakiness when textures lag).
5. **Flexible shapeless API** — `Object...` ingredients: `ItemLike`, `TagKey`, `Ingredient`, `RegistryObject`, or FastUtil int-pairs for counts; expands pairs; auto `unlockedByHaving` from first concrete item/tag/ingredient when criterion omitted.
6. **`unlockedByHaving`** — reflectively calls `unlockedBy` + inventory-change criterion for items/tags; for multi-item ingredients walks values.
7. **`pushWriter` / `conditional` / `renameRecipes`** — temporarily swap the `FinishedRecipe` consumer (Forge conditional wrapper; id remapping for name collisions).
8. **NBT results** — `NbtResultRecipe.serializeResult` writes `"nbt": "<CompoundTag string>"` on the result object (1.20.1 item NBT era).
9. **Tag `add(T)`** — `DirectTagAppender` resolves `ResourceKey` via registry-specific `keyGetter` (built-in holders for block/item/fluid/entity/game_event; else `RegistryAccess` lookup).
10. **Item tag copy** — on item provider `createContentsProvider`, combine with block tag contents and copy entries for registered `copy(blockTag, itemTag)` pairs.
11. **`MKLootProvider`** — extends `LootTableProvider` with mutable `providers` list and `super(output, Set.of(), null)`; **never registered** by `DataHelper`; no call sites in ModKit (WIP).

## Port relevance to Re-Forestry

**Priority: high as a map of CE datagen behavior; low as a library to depend on.**

- Re-Forestry must stay standalone Fabric (`reforestry-standalone-adopt`): **do not** add ModKit to `depends` / Gradle. Copy ideas into `com.leon1236.reforestry` datagen helpers if useful.
- CE / IF / Gendustry recipe, tag, English, and damage-type providers are written against these APIs (`forestry.core.data.Data`, `ForestryRecipeProvider`, etc.). Reading ModKit explains CE’s terse `recipes.slab(...)`, `ingredient(...)`, `path(...)`, and multi-registry `createTags` — essential when porting CE data to Fabric.
- Re-Forestry today: empty `ReForestryDataGenerator` (`FabricDataGenerator` entrypoint). Fabric parallels: `FabricRecipeProvider`, `FabricTagProvider`, `FabricLanguageProvider`, `FabricModelProvider`, block loot providers (see Kaupenjoe 26.X tutorial patterns) — not Forge `ExistingFileHelper`.
- **Worth adopting (as local helpers):** wood-set / grid / cooking recipe macros; flexible ingredient varargs; auto-unlock criteria; optional auto lang from registry paths; block→item tag copy pattern.
- **Do not port as-is:** Forge `ConditionalRecipe`, `RegistryObject`, reflection into `LanguageProvider`, NBT-on-result crafting (MC 26.2 uses **data components**), Modonomicon hook, Safe* ExistingFileHelper tricks (Fabric model gen differs).
- **Loot:** ignore ModKit stub; follow CE’s separate `ForestryLootTableProvider` when porting loot, via Fabric loot datagen.
- **Genetics / bees / machines:** recipe NBT helpers matter only where CE encoded NBT on craft results — remap to component-aware recipe serializers / datagen on 26.2.

## Source map

| Path | Role |
|---|---|
| `.../data/DataHelper.java` | Facade; registers all providers on `GatherDataEvent`. |
| `.../data/MKRecipeProvider.java` | Largest file (~966 LOC); recipe DSL + static `ingredient`/`path`/`unlockedByHaving`. |
| `.../data/MKTagsProvider.java` | Generic tags + block→item copy. |
| `.../data/DirectTagAppender.java` | Fluent appender with object/`Supplier` overloads + Forge remove/replace. |
| `.../data/MKEnglishProvider.java` | en_us + auto-names + translation handlers. |
| `.../data/MKItemModelProvider.java` | Item model templates + registry auto-gen. |
| `.../data/MKBlockModelProvider.java` | Blockstate/model helpers; delegates models to Safe provider. |
| `.../data/MKDamageTypeProvider.java` | Damage type JSON + `DamageTypeBuilder`. |
| `.../data/model/SafeItemModelBuilder.java` | Tolerant item model builder + overrides. |
| `.../data/model/SafeBlockModelProvider.java` | Block model provider that does not double-run; tolerant parents. |
| `.../data/model/SafeBlockModelBuilder.java` | Tolerant texture() for blocks. |
| `.../data/recipe/NbtResultRecipe.java` | Shared shaped/shapeless NBT result base. |
| `.../data/recipe/NbtShapedRecipeBuilder.java` | Shaped + optional result NBT. |
| `.../data/recipe/NbtShapelessRecipeBuilder.java` | Shapeless + optional result NBT. |
| `.../data/loot/MKLootProvider.java` | Incomplete loot table provider stub. |
| `.../data/**/package-info.java` | Nullness annotations only. |
| *(consumer examples, not library)* `src/test/.../testmod/data/*` | Full GatherData demo. |
| *(root)* `ModKitDataGen.java` | Tiny in-mod demo (English + models). |

Clone root: `MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-ModKit`. Graph alias: `modkit`.

## Open questions/gaps

1. Will `MKLootProvider` ever gain `DataHelper.createLoot` / subprovider registration, or should consumers always roll their own (as CE does)?
2. Is the Modonomicon callback still used by any thedarkcolour mod, or dead API surface?
3. For Re-Forestry: invest in a small shared Fabric datagen utility package now, or wait until CE recipe/tag ports force duplication?
4. Which CE craft results still rely on ModKit’s **result NBT** serialization vs plain items — needed before designing 26.2 component-aware recipe datagen.
5. CE loads `Data` via `@Mod.EventBusSubscriber` despite ModKit’s “separate class / optional runtime” guidance — confirm whether Re-Forestry datagen should mirror CE structure or the safer testmod split.
