# SuperMartijn642's Core Lib — mixin

**Repo:** SuperMartijn642-SuperMartijn642sCoreLib (`corelib`)  
**Clone:** `MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/SuperMartijn642-SuperMartijn642sCoreLib`  
**Package:** `com.supermartijn642.core.mixin`  
**Target stack (clone):** Minecraft **1.16.5**, Forge **36.2.42**, Java 8, Mixin 0.8+  
**Scope:** This package only (12 Java files + `modid.mixins.json` registration).

---

## Summary

The `mixin` package is CoreLib’s **internal Forge hook layer**. It contains no public API: every class is a Mixin `@Mixin`, `@Accessor`, or implements an extension interface consumed only from other CoreLib packages. The mixins fall into five functional groups:

| Group | Mixins | Purpose |
|---|---|---|
| **GUI (client)** | `AbstractContainerScreenMixin`, `GameRendererMixin` | Custom slot hit-testing; deferred GLFW cursor application |
| **Render (client)** | `LevelRendererMixin` | Post-highlight world render callback (`RenderWorldEvent`) |
| **Registry / datagen** | `GameDataMixin`, `CraftingHelperMixin`, `DataGeneratorMixin`, `DatagenModLoaderAccessor` | `@RegistryEntryAcceptor` wiring; recipe-condition registry sync; datagen provider injection |
| **Block properties** | `BlockPropertiesAccessor` | Read/write vanilla `lootTableSupplier` when copying block properties |
| **Tags** | `TagBuilderMixin`, `TagCollectionReaderMixin`, `ForgeTagHandlerMixin`, `ForgeHooksMixin` (partial) | Custom tag entry JSON; registry context on load; optional/remove tag additions |

All mixins are **required** (`modid.mixins.json`: `"required": true`, `"defaultRequire": 1`). Seven run on both sides; three are **client-only**.

---

## Player / API surface

**None in this package.** Mod authors never import `com.supermartijn642.core.mixin.*`.

Observable behavior is exposed through sibling packages:

| Consumer-facing API | Enabled by mixin |
|---|---|
| `CustomSlot` + `WidgetContainerScreen` | `AbstractContainerScreenMixin` |
| `CursorTypes` | `GameRendererMixin` |
| `RenderWorldEvent` (Forge event bus) | `LevelRendererMixin` |
| `@RegistryEntryAcceptor` | `GameDataMixin` |
| `Registries.onRecipeConditionSerializerAdded` | `CraftingHelperMixin` |
| `GeneratorRegistrationHandler` + `ResourceGenerator` datagen | `DataGeneratorMixin`, `DatagenModLoaderAccessor` |
| `BlockProperties.fromVanilla` / `toVanilla` | `BlockPropertiesAccessor` |
| `CustomTagEntries`, `TagEntryAdapter`, custom tag serializers | `TagBuilderMixin`, `TagCollectionReaderMixin`, `ForgeTagHandlerMixin`, `ForgeHooksMixin` |

---

## Architecture

### Registration

`src/main/resources/modid.mixins.json` (package resolves to `com.supermartijn642.core.mixin` via Gradle placeholders):

```json
"mixins": [
  "BlockPropertiesAccessor", "CraftingHelperMixin", "DataGeneratorMixin",
  "DatagenModLoaderAccessor", "ForgeHooksMixin", "ForgeTagHandlerMixin",
  "GameDataMixin", "TagBuilderMixin", "TagCollectionReaderMixin"
],
"client": [
  "AbstractContainerScreenMixin", "GameRendererMixin", "LevelRendererMixin"
]
```

### Mixin inventory

| File | Target (vanilla / Forge) | Technique | Callback / contract |
|---|---|---|---|
| `AbstractContainerScreenMixin` | `ContainerScreen` | `@Inject` HEAD, cancellable | If `Slot` is `CustomSlot`, use `getWidth()`/`getHeight()` for hover instead of 16×16 |
| `GameRendererMixin` | `GameRenderer` | `@Inject` TAIL of `render` | Calls `CursorTypes.applyPending()` when not `noRender` |
| `LevelRendererMixin` | `WorldRenderer` | `@ModifyVariable` HEAD ×2 + `@Inject` with `@Slice` | Captures `MatrixStack` + partial ticks; posts `RenderWorldEvent` on Forge event bus after block highlight, before matrix push |
| `BlockPropertiesAccessor` | `AbstractBlock.Properties` | `@Accessor` (remap=false) | `get/setLootTableSupplier()` |
| `CraftingHelperMixin` | `CraftingHelper` | `@Inject` TAIL of `register` (remap=false) | Notifies `Registries.onRecipeConditionSerializerAdded` |
| `DataGeneratorMixin` | `DataGenerator` | Three `@Inject` points in `run()` | (1) After `DirectoryCache.keep`: register mod providers via `GeneratorRegistrationHandler`; (2) Before vanilla providers: run `ResourceGenerator.DataProviderInstance.generate()`; (3) Before `purgeStaleAndWrite`: `ResourceCache.finish()` |
| `DatagenModLoaderAccessor` | `DatagenModLoader` | Static `@Accessor` (remap=false) | `getDataGeneratorConfig()`, `getExistingFileHelper()` |
| `GameDataMixin` | `GameData` | `@ModifyVariable` STORE + `@Inject` (remap=false) | Captures `RegistryEvent.Register<?>`; calls `RegistryEntryAcceptor.Handler.onRegisterEvent` before `ObjectHolderRegistry.applyObjectHolders` |
| `ForgeHooksMixin` | `ForgeHooks` | `@Redirect` + `@Inject` HEAD (remap=false) | (1) `canHarvestBlock`: for `BaseBlock`, pick best harvest level across effective tool types; (2) `deserializeTagAdditions`: peel custom entries from `optional`/`remove` JSON arrays via `CustomTagEntries.potentiallyDeserialize` |
| `ForgeTagHandlerMixin` | `ForgeTagHandler` | `@Inject` RETURN (remap=false) | On custom tag readers, call `TagLoaderExtension.supermartijn642corelibSetRegistry` |
| `TagBuilderMixin` | `Tag.Builder` | `@Inject` HEAD of `parseEntry`, cancellable | Delegate to `CustomTagEntries.potentiallyDeserialize` when JSON has `"type"` |
| `TagCollectionReaderMixin` | `TagCollectionReader` | Implements `TagLoaderExtension`; `@Inject` HEAD of `load` | Infer registry from `directory` path; call `TagEntryAdapter.setRegistry` for each custom entry |

### Call-flow sketches

**Custom slot hover (client)**

```
ContainerScreen.isHovering(Slot, mouse)
  → AbstractContainerScreenMixin @ HEAD
  → if slot instanceof CustomSlot → isHovering(x, y, width, height, mouse) [shadow]
  → else vanilla
```

**Datagen pipeline**

```
DataGenerator.run()
  → [after DirectoryCache.keep] DataGeneratorMixin.runHead
      → per mod with GeneratorRegistrationHandler: ResourceCache.wrap + registerProviders
  → [after Stopwatch.createUnstarted] DataGeneratorMixin.runBeforeGenerators
      → foreach ResourceGenerator.DataProviderInstance: generate() + log timing
      → allowWrites(true) on ResourceCache
  → … vanilla providers …
  → [before purgeStaleAndWrite] DataGeneratorMixin.runTail → resourceCache.finish()
```

**Custom tag entries**

```
Tag JSON {"type": "namespace:serializer", ...}
  → TagBuilderMixin.parseEntry OR ForgeHooksMixin.deserializeTagAdditions
  → CustomTagEntries.potentiallyDeserialize → TagEntryAdapter
  → TagCollectionReaderMixin.load → TagEntryAdapter.setRegistry(registry, forgeRegistry)
```

**Registry field injection**

```
GameData lambda postRegistryEventDispatch
  → GameDataMixin captures Register<?> event
  → before ObjectHolderRegistry.applyObjectHolders
  → RegistryEntryAcceptor.Handler.onRegisterEvent
      → static fields/methods annotated @RegistryEntryAcceptor get registry values
```

---

## Data & assets

| Asset | Role |
|---|---|
| `modid.mixins.json` | Mixin config; splits common vs client mixins |
| `META-INF/accesstransformer.cfg` | Project-wide AT (not defined inside `mixin/`; mixins use `@Accessor` where needed) |
| Refmap | `${mod_id}.mixins.refmap.json` (build artifact) |

No textures, lang keys, or data files live in the mixin package.

---

## Dependencies

### Hard (direct imports in mixin sources)

| Dependency | Used by |
|---|---|
| **SpongePowered Mixin** | All classes |
| **Minecraft** | `ContainerScreen`, `DataGenerator`, `GameRenderer`, `WorldRenderer`, `AbstractBlock.Properties`, `TagCollectionReader`, `Tag.Builder`, tag/registry types |
| **Forge** | `GameData`, `ForgeHooks`, `ForgeTagHandler`, `CraftingHelper`, `DatagenModLoader`, `GatherDataEvent`, `RegistryEvent`, `RegistryManager`, `ForgeRegistry`, `MinecraftForge` |
| **Guava** | `Stopwatch` (`DataGeneratorMixin`) |
| **Gson** | `JsonElement`, `JsonObject`, `JsonArray` (`ForgeHooksMixin`, `TagBuilderMixin`) |

### Internal CoreLib (call targets)

| Package / type | Mixins using it |
|---|---|
| `gui.CustomSlot` | `AbstractContainerScreenMixin` |
| `gui.CursorTypes` | `GameRendererMixin` |
| `ClientUtils` | `GameRendererMixin` |
| `render.RenderWorldEvent` | `LevelRendererMixin` |
| `registry.RegistryEntryAcceptor` | `GameDataMixin` |
| `registry.Registries` | `CraftingHelperMixin` |
| `registry.GeneratorRegistrationHandler` | `DataGeneratorMixin` |
| `generator.ResourceCache`, `ResourceGenerator` | `DataGeneratorMixin` |
| `block.BaseBlock` | `ForgeHooksMixin` |
| `data.tag.CustomTagEntries`, `TagEntryAdapter` | `TagBuilderMixin`, `TagCollectionReaderMixin`, `ForgeHooksMixin` |
| `extensions.TagLoaderExtension` | `TagCollectionReaderMixin`, `ForgeTagHandlerMixin` |
| `block.BlockProperties` (via accessor) | `BlockPropertiesAccessor` consumers only |

---

## Notable algorithms / contracts

1. **Custom slot hit box** — Vanilla `ContainerScreen.isHovering(Slot, …)` assumes 16×16 slots. Mixin short-circuits when `slot instanceof CustomSlot`, delegating to the private `isHovering(int x, int y, int w, int h, …)` overload with `CustomSlot.getWidth()` / `getHeight()`. Contract: any `Slot` implementing `CustomSlot` gets non-standard hover geometry without overriding the screen class.

2. **Deferred cursor** — `CursorTypes` sets `pendingCursor` during GUI/widget code; `GameRendererMixin` applies it at end of each frame via `GLFW.glfwSetCursor`. Avoids mid-render cursor flicker. Contract: cursor changes take effect **after** the current render pass.

3. **RenderWorldEvent injection point** — `LevelRendererMixin` uses a precise `@Slice` between block-ray highlight and `RenderSystem.pushMatrix()`. Fires once per world render at “after highlight, before extra matrix work”. Test mod (`TestModClient`) subscribes to draw debug overlays.

4. **Registry acceptor timing** — `GameDataMixin` hooks a **named Forge lambda** (`lambda$postRegistryEventDispatch$15`). Fragile across Forge refactors but guarantees `@RegistryEntryAcceptor` runs **after** mod registration, **before** object holders. Stores event in static field on mixin class (single-threaded load phase).

5. **Datagen two-phase writes** — `ResourceCache.HashCacheWrapper.allowWrites(false)` during custom `ResourceGenerator` pass prevents premature disk writes; re-enabled before vanilla providers; `finish()` flushes at end. Contract: CoreLib generators run **before** vanilla `IDataProvider`s in the same `DataGenerator.run()`.

6. **Custom tag entry protocol** — JSON object with string `"type"` key = registered `CustomTagEntrySerializer` id. `TagBuilderMixin` handles inline entries; `ForgeHooksMixin` handles Forge’s `optional` / `remove` arrays in tag addition files. `TagCollectionReaderMixin` binds `Registry` / `ForgeRegistry` so adapters resolve ids at load time (with fallback mapping from `directory` string for blocks/items/fluids/entity_types).

7. **BaseBlock harvest redirect** — For `BaseBlock` states, iterates `stack.getToolTypes()`, keeps max harvest level among **tool-effective** types; falls back to original tool type if none match. Fixes multi-tool harvest level edge cases on Forge.

8. **Accessor remap=false** — Forge-only and package-private fields (`lootTableSupplier`, `DatagenModLoader` statics) accessed without MCP remapping.

---

## Port relevance to Re-Forestry

Re-Forestry targets **Fabric 26.2** and follows **“Fabric API hooks over Mixins”** (project convention). This entire package is **Forge 1.16.5–specific**; do **not** copy mixins or add a CoreLib dependency. Map each concern to Fabric/vanilla equivalents:

| CoreLib mixin concern | Re-Forestry approach | Priority |
|---|---|---|
| **Custom slot hover** | `ScreenForestry` already uses manual `isHovering(x, y, w, h, …)` for tank regions and ghost slots; no mixin needed unless adding variable-size `Slot` subclasses | Low — pattern already inlined |
| **Cursor types** | GLFW / `Minecraft.getInstance().getWindow().setCursor(…)` from screen `render` or Fabric client tick; no `GameRenderer` tail inject required | Low |
| **World render callback** | `WorldRenderEvents` (Fabric API) or `LevelRenderer` mixin only if no hook exists — prefer event | Medium if world overlays needed |
| **@RegistryEntryAcceptor** | Not applicable — Re-Forestry uses direct `Registry.register` at mod init / `FeatureGroup` registration | Skip |
| **Recipe condition registry hook** | Fabric conditional recipes / datapack conditions — different pipeline; no `CraftingHelper` | Skip |
| **Datagen orchestration** | Fabric Loom datagen + mod-specific `DataGenerator` entrypoints; no `DatagenModLoader` | Skip (use Fabric datagen) |
| **BlockProperties loot supplier** | MC 26.2 `BlockBehaviour.Properties` — check Yarn/MCP for equivalent field or builder API; likely no accessor mixin | Low — only if porting `BlockProperties` helper |
| **Custom tag entry types** | Fabric tag JSON is standard; extended tag entries would use data components or custom reload listeners, not Forge `ITag.ITagEntry` mixins | Low unless CE tag extensions need parity |
| **Forge tag additions (optional/remove)** | Fabric / vanilla tag conventions differ; evaluate CE tag data, not this Forge hook | Context-dependent |

**Existing Re-Forestry mixins** (`src/main/java/com/leon1236/reforestry/mixin/`) are unrelated client/render tweaks (`SpecialModelRenderersMixin`, `ItemTintSourcesMixin`, etc.) — orthogonal to CoreLib GUI/registry/tag mixins.

**Standalone-adopt rule:** If borrowing *ideas* (custom hover regions, deferred cursor, post-highlight render), reimplement in `com.leon1236.reforestry.*` using Fabric hooks — never ship CoreLib mixin class names or Forge imports.

---

## Source map

| File | Lines (approx.) | Side |
|---|---|---|
| `AbstractContainerScreenMixin.java` | 39 | Client |
| `BlockPropertiesAccessor.java` | 22 | Common |
| `CraftingHelperMixin.java` | 26 | Common |
| `DataGeneratorMixin.java` | 107 | Common |
| `DatagenModLoaderAccessor.java` | 25 | Common |
| `ForgeHooksMixin.java` | 81 | Common |
| `ForgeTagHandlerMixin.java` | 30 | Common |
| `GameDataMixin.java` | 38 | Common |
| `GameRendererMixin.java` | 26 | Client |
| `LevelRendererMixin.java` | 47 | Client |
| `TagBuilderMixin.java` | 29 | Common |
| `TagCollectionReaderMixin.java` | 69 | Common |

**Related (outside package, required to understand mixins):**

| Path | Relation |
|---|---|
| `extensions/TagLoaderExtension.java` | Interface implemented by `TagCollectionReaderMixin` |
| `gui/CustomSlot.java`, `gui/CustomSlotImpl.java`, `gui/WidgetContainerScreen.java` | Custom slot system |
| `gui/CursorTypes.java` | Cursor deferral target |
| `render/RenderWorldEvent.java` | Event posted by `LevelRendererMixin` |
| `registry/RegistryEntryAcceptor.java` | Registry injection handler |
| `registry/Registries.java` | Recipe condition + registry maps |
| `registry/GeneratorRegistrationHandler.java` | Datagen registration |
| `generator/ResourceCache.java`, `generator/ResourceGenerator.java` | Datagen cache/generators |
| `block/BlockProperties.java` | `BlockPropertiesAccessor` consumer |
| `block/BaseBlock.java` | Harvest-level redirect target |
| `data/tag/CustomTagEntries.java`, `data/tag/TagEntryAdapter.java` | Tag entry (de)serialization |
| `src/main/resources/modid.mixins.json` | Mixin manifest |
| `src/test/.../TestModClient.java` | Example `RenderWorldEvent` subscriber |

---

## Open questions / gaps

1. **Forge lambda fragility** — `GameDataMixin` targets `lambda$postRegistryEventDispatch$15`; name and ordinal will change across Forge versions (already irrelevant for Fabric port, but explains why this clone is pinned to 1.16.5 FML).

2. **No Fabric / multi-version branch in this clone** — `gradle.properties` shows `minecraft_version=1.16.5` only. Newer CoreLib branches (1.18+, 1.20+) may rename targets (`AbstractContainerScreen`, `LevelRenderer`, tag APIs). This report reflects **this clone only**; cross-version mixin diffs not analyzed.

3. **`TagCollectionReaderMixin` registry fallback** — Only four directory paths get implicit `Registry.*` assignment; custom registries rely entirely on `ForgeTagHandlerMixin` wiring. Behavior for modded registries without Forge custom tag readers is unclear from mixin code alone.

4. **`ForgeHooksMixin.deserializeTagAdditions`** — Mutates `JsonArray` while iterating (remove + decrement index); works but order-dependent. No unit tests in clone.

5. **Accessor vs AT** — `BlockPropertiesAccessor` uses Mixin accessor instead of AT entry; port to 26.2 requires verifying whether `lootTableSupplier` still exists on `BlockBehaviour.Properties` and whether it is accessible without mixin.

6. **Client mixin ordering** — `GameRendererMixin` TAIL vs other mods’ render mixins: potential cursor fight if multiple mods defer GLFW cursor; no documented priority in mixin config.

7. **Graphify coverage** — Local graph traversal from `Mixin`/`CoreLib` surfaces links to `RegistrationHandler`, `CustomSlot`, `TagCollectionReaderMixin`, but does not enumerate injection targets; confirm against Forge 1.16.5 sources if reproducing behavior.

---

*Generated: Phase 1 module report — `com.supermartijn642.core.mixin` — clone dated 2026-07-28.*
