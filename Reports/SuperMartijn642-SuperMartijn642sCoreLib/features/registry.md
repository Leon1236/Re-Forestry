# SuperMartijn642-SuperMartijn642sCoreLib — registry

**Repo:** SuperMartijn642-SuperMartijn642sCoreLib (`corelib`)  
**Clone:** `MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/SuperMartijn642-SuperMartijn642sCoreLib`  
**Package:** `com.supermartijn642.core.registry`  
**Clone MC version:** Forge **1.16.5** (Java 8) — APIs below are Forge/FML-shaped; newer CoreLib branches exist for later MC but this clone is 1.16-era.  
**Scope:** This package only (6 Java files, ~2,210 LOC).

---

## Summary

The `registry` package is CoreLib’s **mod-author registration layer** for Forge mods. It replaces scattered `RegistryEvent` listeners and client setup boilerplate with four coordinated helpers:

1. **`RegistrationHandler`** — defer server/common registry entries (blocks, items, fluids, menu types, recipe serializers, custom tag entry serializers, etc.) until the matching Forge `RegistryEvent.Register` fires.
2. **`ClientRegistrationHandler`** — defer client-only setup (models, model overwrites, entity/BER/item renderers, container screens, block render layers, texture-atlas sprites) until Forge client lifecycle events.
3. **`GeneratorRegistrationHandler`** — queue datagen providers/generators per modid; resolved by a mixin on `DataGenerator.run()`.
4. **`Registries` + `RegistryUtil`** — a unified `Registry<T>` façade over vanilla registries, Forge registries, and two custom map-backed registries; plus identifier validation.

A fifth piece, **`@RegistryEntryAcceptor`**, injects registered objects into static fields or single-arg static methods after registration (Forge `@ObjectHolder` alternative), driven by mod classpath scanning and a `GameData` mixin hook.

Nothing here is player-visible. It is library infrastructure consumed by SuperMartijn642 mods (and CoreLib itself) at mod construction time.

---

## Player / API surface

| Audience | What they see / use |
|---|---|
| **Players** | Nothing directly — registered blocks, items, screens, etc. appear because dependent mods used these helpers. |
| **Mod authors (public API)** | `RegistrationHandler.get(modid)`, `ClientRegistrationHandler.get(modid)`, `GeneratorRegistrationHandler.get(modid)`, `Registries.*` constants, `RegistryUtil.isValid*()`, `@RegistryEntryAcceptor`. |
| **CoreLib internals** | `GeneratorRegistrationHandler.hasHandlerForModid`, `GeneratorRegistrationHandler.registerProviders`, `RegistryEntryAcceptor.Handler.*`, `Registries.fromUnderlying`, `Registries.onRecipeConditionSerializerAdded`. |

Typical consumer pattern (from CoreLib + test mod):

```java
RegistrationHandler handler = RegistrationHandler.get("mymod");
handler.registerItem("foo", () -> new BaseItem(...));
handler.registerBlockCallback(helper -> helper.register("bar", new MyBlock(...)));

ClientRegistrationHandler.get("mymod")
    .registerContainerScreen(() -> MY_MENU, MyScreen::new)
    .registerBlockModelCutoutRenderType(() -> MY_BLOCK);

GeneratorRegistrationHandler.get("mymod")
    .addGenerator(cache -> new MyTagGenerator("mymod", cache));

@RegistryEntryAcceptor(namespace = "mymod", identifier = "foo", registry = RegistryEntryAcceptor.Registry.ITEMS)
public static Item FOO;
```

**RegistrationHandler** exposes, for each supported registry type: `registerX(String id, Supplier<T>)`, `registerX(String id, T)`, `registerXOverride(namespace, id, …)`, and `registerXCallback(Consumer<Helper<T>>)`. Callbacks receive a `Helper` that can register additional entries *during* the registry event (after queued suppliers run).

**ClientRegistrationHandler** groups: model registration (`registerModel`, `registerSpecialModel`, `registerModelOverwrite`, block/item model overwrites), renderers (`registerEntityRenderer`, `registerBlockEntityRenderer`, `registerCustomBlockEntityRenderer`, `registerItemRenderer` / `registerCustomItemRenderer`), UI (`registerContainerScreen`), visuals (`registerBlockModel*RenderType`, `registerAtlasSprite`).

---

## Architecture

```mermaid
flowchart TB
  subgraph modInit [Mod constructor / static init]
    RH[RegistrationHandler.get]
    CRH[ClientRegistrationHandler.get]
    GRH[GeneratorRegistrationHandler.get]
    REA[@RegistryEntryAcceptor fields scanned]
  end

  subgraph forgeCommon [Forge common lifecycle]
    RE[RegistryEvent.Register per registry]
    RH -->|queues entries + callbacks| RE
    RE -->|handleRegistry| REG[Registries.Registry.register]
    GM[GameDataMixin] -->|after register event| REA_H[RegistryEntryAcceptor.Handler.onRegisterEvent]
    REA_H --> REA
  end

  subgraph forgeClient [Forge client lifecycle]
    MRE[ModelRegistryEvent]
    MBE[ModelBakeEvent]
    FCS[FMLClientSetupEvent]
    TSE[TextureStitchEvent.Pre]
    CRH --> MRE & MBE & FCS & TSE
  end

  subgraph datagen [Datagen run]
    DGM[DataGeneratorMixin.runHead]
    GRH --> DGM
    DGM -->|registerProviders| DG[DataGenerator.addProvider]
  end

  REG --> VR[VanillaRegistryWrapper]
  REG --> FR[ForgeRegistryWrapper]
  REG --> MR[MapBackedRegistry / RecipeConditionSerializerRegistry]
```

### Class roles

| Class | Lines | Role |
|---|---:|---|
| `RegistryUtil` | 45 | Regex validation for namespace `[a-z0-9_.-]*`, path `[a-z0-9_./-]*`, and combined identifiers. |
| `Registries` | 527 | Static registry constants + `Registry<T>` interface; three backing implementations. |
| `RegistrationHandler` | 550 | Per-modid deferred common registration; one handler instance per modid. |
| `ClientRegistrationHandler` | 712 | Per-modid deferred client registration; uses reflection on `Item.ister` for custom item renderers. |
| `GeneratorRegistrationHandler` | 155 | Per-modid datagen queue (`Either<ResourceGenerator factory, IDataProvider factory>`). |
| `RegistryEntryAcceptor` | 221 | Runtime annotation + `Handler` that scans `ModList.getAllScanData()` at construct time. |

### `Registries.Registry<T>` implementations

| Implementation | Backing | Used for |
|---|---|---|
| `VanillaRegistryWrapper` | `net.minecraft.util.registry.Registry` | `RECIPE_TYPES` (no Forge registry) |
| `ForgeRegistryWrapper` | Forge `IForgeRegistry` + optional vanilla mirror | Blocks, items, fluids, entities, menus, recipe serializers, etc. |
| `RecipeConditionSerializerRegistry` | Reflects into `CraftingHelper.conditions` map | `RECIPE_CONDITION_SERIALIZERS` — hooks Forge recipe conditions |
| `MapBackedRegistry` | Internal `HashMap` | `CUSTOM_TAG_ENTRY_SERIALIZERS` (`supermartijn642corelib:custom_tag_entries`) |

### Registration ordering for non-Forge registries

`REGISTRATION_ORDER_MAP` ensures dependent registries are populated when a “parent” Forge event fires:

| Trigger registry event | Also applies `@RegistryEntryAcceptor` + deferred entries for |
|---|---|
| `POTIONS` | `RECIPE_TYPES` |
| `RECIPE_SERIALIZERS` | `RECIPE_CONDITION_SERIALIZERS`, `CUSTOM_TAG_ENTRY_SERIALIZERS` |

This matters because recipe types and condition serializers have no dedicated Forge register event in this MC version.

### Lifecycle gates (fail-fast)

Each handler tracks boolean “phase passed” flags. Registering after the corresponding event throws `IllegalStateException` / `RuntimeException`:

- **RegistrationHandler:** `encounteredEvents` per registry — no new entries/callbacks after that registry’s event.
- **ClientRegistrationHandler:** `passedModelRegistry`, `passedModelBake`, `passedClientSetup`, `passedTextureStitch`.
- **GeneratorRegistrationHandler:** `hasEventBeenFired` after `registerProviders`.

Duplicate identifiers within a handler are rejected at queue time; duplicate renderer/screen registrations are rejected at apply time.

### External wiring (outside this package, required for full behavior)

| Mixin / caller | Package hook |
|---|---|
| `GameDataMixin` | Calls `RegistryEntryAcceptor.Handler.onRegisterEvent` after each `RegistryEvent.Register`, before Forge object holders. |
| `CraftingHelperMixin` | Calls `Registries.onRecipeConditionSerializerAdded` when conditions register. |
| `DataGeneratorMixin` | Calls `GeneratorRegistrationHandler.registerProviders` during datagen. |
| `CoreLib` constructor | Demonstrates handler usage; calls `RegistryEntryAcceptor.Handler.gatherAnnotatedFields()` on `FMLConstructModEvent`. |

---

## Data & assets

This package registers **no assets itself**. It orchestrates when other systems load or bind assets:

| ClientRegistrationHandler API | Asset / rendering effect |
|---|---|
| `registerModel` | JSON models via `ModelLoader.addSpecialModel` |
| `registerSpecialModel` / `registerModelOverwrite` | Injects or replaces baked models at `ModelBakeEvent` |
| `registerAtlasSprite` | Adds sprites to an atlas at `TextureStitchEvent.Pre` |
| `registerBlockModel*RenderType` | Sets block render layer (`RenderTypeLookup.setRenderLayer`) |
| Renderer / screen registration | Binds Java classes to registry objects at client setup |

Downstream **generator** package (`TagGenerator`, `ModelGenerator`, etc.) uses `Registries.*` for identifier lookup when emitting JSON tags/recipes — not part of this package but a primary consumer of `Registries` constants.

---

## Dependencies

### Inbound (this package imports)

| Dependency | Usage |
|---|---|
| **Forge FML** | `FMLJavaModLoadingContext`, `ModLoadingContext`, `RegistryEvent`, `FMLClientSetupEvent`, `GatherDataEvent` (via mixin caller) |
| **Forge registries** | `IForgeRegistry`, `IForgeRegistryEntry`, `ForgeRegistries`, `GameData` (via mixin) |
| **Forge client** | `ModelRegistryEvent`, `ModelBakeEvent`, `TextureStitchEvent`, `ModelLoader`, `ClientRegistry`, `RenderTypeLookup`, `ScreenManager` |
| **Forge recipe conditions** | `IConditionSerializer`, `CraftingHelper` |
| **Forge datagen** | `ExistingFileHelper`, `IDataProvider`, `DataGenerator` |
| **ASM scan** | `ModList`, `ModFileScanData`, `ModAnnotation` for `@RegistryEntryAcceptor` |
| **CoreLib internal** | `CoreLib.LOGGER`, `CommonUtils` (indirect), `CustomTagEntrySerializer`, `ResourceConditionSerializer`, `ResourceGenerator`, `ResourceCache`, `Either`, `Pair`, `TriFunction`, `CustomBlockEntityRenderer`, `CustomItemRenderer`, `ClientUtils` |

### Outbound (other CoreLib code importing this package)

| Consumer | Uses |
|---|---|
| `CoreLib` | All three handlers + `@RegistryEntryAcceptor` init |
| `BaseBlock`, `BaseItem` | `Registries.BLOCKS/ITEMS.getIdentifier(this)` |
| `CustomTagEntries` | `Registries.CUSTOM_TAG_ENTRY_SERIALIZERS`, `RegistryUtil` |
| `generator/*` | `Registries.*` for tag/recipe/model identifier resolution |
| `mixin/GameDataMixin`, `CraftingHelperMixin`, `DataGeneratorMixin` | Handler/acceptor callbacks |
| `test/TestMod` | Full registration example |

No Gradle dependency from Re-Forestry to CoreLib is implied or recommended.

---

## Notable algorithms / contracts

### Per-modid singleton handlers

`RegistrationHandler.get(modid)`, `ClientRegistrationHandler.get(modid)`, and `GeneratorRegistrationHandler.get(modid)` each maintain a `Map<String, Handler>`. First call for a modid constructs the handler and registers Forge event listeners (common/client handlers). Mismatched active mod namespace logs a warning but still returns the requested handler.

### Identifier validation (`RegistryUtil`)

All user-supplied namespaces/paths are validated before queueing. Invalid characters fail fast with `IllegalArgumentException`. This mirrors vanilla identifier rules closely and is the one piece portable without Forge.

### Deferred entry map

`RegistrationHandler` stores `Map<Registry<?>, Map<ResourceLocation, Supplier<?>>>` (linked hash map per registry for stable iteration order). On event: `supplier.get()` then `registry.register(id, object)`. Null suppliers/objects throw.

### Callback `Helper` vs queued registration

Callbacks run **after** queued suppliers for the same registry event. `Helper.register` writes directly to the live registry (and also records in `entryMap`) — intended for entries that depend on objects registered earlier in the same event.

### `@RegistryEntryAcceptor` scan + apply

1. **Gather** (`gatherAnnotatedFields`): iterate all mod scan data; for each `@RegistryEntryAcceptor` on a static non-final field or static single-arg method, validate namespace/path/type; store in `FIELDS` / `METHODS` maps keyed by `Registries.Registry` + `ResourceLocation`.
2. **Apply** (`onRegisterEvent` / `onIdRemapping`): when registry contains the id, `field.set(null, value)` or `method.invoke(null, value)`. Missing ids log warnings; type mismatches log warnings.
3. **Remapping**: `FMLModIdMappingEvent` re-applies all known fields/methods.

Requires **`GameDataMixin`** injection point — fragile across Forge versions.

### Custom recipe-condition registry

`RecipeConditionSerializerRegistry.register` delegates to `CraftingHelper.register` and enforces `object.getID()` equals the given `ResourceLocation`. The registry map is the live Forge `CraftingHelper.conditions` field (via reflection). `CraftingHelperMixin` keeps CoreLib’s `objectToIdentifier` map in sync.

### Client item renderer injection

`ClientRegistrationHandler` reads/writes private `Item.ister` (`Supplier<ItemStackTileEntityRenderer>`) via reflection — Forge 1.16 pattern; breaks easily across mappings/editions.

---

## Port relevance to Re-Forestry

**Policy:** Re-Forestry is standalone Fabric. CoreLib is **reference only** — adopt ideas into `com.leon1236.reforestry.*`, never add CoreLib as a player dependency ([`reforestry-standalone-adopt.mdc`](../../../.cursor/rules/reforestry-standalone-adopt.mdc)).

### What Re-Forestry already has (no gap)

Re-Forestry uses **direct `Registry.register` / `Registry.registerForHolder`** at mod init via the `modules.features` system:

| CoreLib concept | Re-Forestry equivalent |
|---|---|
| `RegistrationHandler.registerBlock/registerItem` | `FeatureBlock`, `FeatureItem`, `FeatureBlockGroup`, `FeatureItemGroup` → `BuiltInRegistries.*` |
| Enum-driven bulk registration | `IFeatureRegistry.blockGroup(...).types(Enum...)` with `IdentifierType` PREFIX/SUFFIX/TYPE_ONLY |
| Per-module registry classes | `ApicultureBlocks`, `FactoryTiles`, `ArboricultureItems`, etc. |
| Client screens / BER / entity renderers | Module `*ClientHandler` classes using Fabric/vanilla registries (`MenuScreens`, `BlockEntityRendererFactories`, `EntityRendererRegistry`, …) |
| Datagen | Gradle datagen / JSON in repo (not runtime handler queue) |

The Forestry port **does not need** Forge-style deferred registration — Fabric registries are open during `ModInitializer` and client entrypoints ([`files/forge-fabric-mapping.md`](../../../files/forge-fabric-mapping.md)).

### Adoptable pieces (small, targeted)

| Piece | Verdict | Fabric adaptation |
|---|---|---|
| **`RegistryUtil.isValid*`** | **Low effort, optional** | Copy into `core.util` if validating dynamic ids (commands, config, network). Vanilla 26.2 has `Identifier.isValidResourceLocation` — prefer MCP vanilla API first. |
| **`Registries.Registry<T>` unified lookup** | **Skip** | Re-Forestry uses typed `BuiltInRegistries` / `Registry<T>` directly; no Forge dual-registry problem. |
| **`RegistrationHandler` deferral** | **Skip** | No `RegistryEvent` on Fabric; deferral adds complexity without benefit. |
| **`ClientRegistrationHandler` API shape** | **Pattern reference only** | A thin `ClientRegistrationHelper` *could* batch screen/BER/render-layer registration per module, but Re-Forestry module client handlers already work. Model overwrite / special model APIs differ on 26.2 (resource reload / model loaders). |
| **`GeneratorRegistrationHandler`** | **Skip** | Re-Forestry datagen is not routed through a CoreLib-style mixin pipeline. |
| **`@RegistryEntryAcceptor`** | **Skip** | Depends on Forge classpath scanning + registry events. On Fabric, use `public static final` fields assigned at registration time (current pattern) or explicit holder records. |

### Risk if copied wholesale

- Hard dependency on Forge types (`RegistryEvent`, `IForgeRegistryEntry`, `ResourceLocation` 1.16 names, `TileEntity`, `ContainerType`, etc.).
- Mixins on `GameData`, `CraftingHelper`, `DataGenerator` — conflicts with Fabric-first rule (“prefer Fabric API hooks over Mixins”).
- Reflection on `Item.ister` and `CraftingHelper.conditions` — version-fragile.

### Recommended stance

| Priority | Action |
|---|---|
| **Now** | None — existing `FeatureGroup` registration is sufficient. |
| **Optional polish** | If dynamic identifier validation is needed outside registries, mirror `RegistryUtil` or use vanilla `Identifier` validation. |
| **Do not** | Add CoreLib dependency; port `RegistrationHandler` / `@RegistryEntryAcceptor` as a framework. |

---

## Source map

**Root:** `src/main/java/com/supermartijn642/core/registry/`

| File | ~LOC | Public entry points |
|---|---:|---|
| `RegistryUtil.java` | 45 | `isValidNamespace`, `isValidPath`, `isValidIdentifier` (×2 overloads) |
| `Registries.java` | 527 | `Registry<T>` interface; constants `BLOCKS` … `CUSTOM_TAG_ENTRY_SERIALIZERS`; `getRegistry`, `fromUnderlying` (deprecated) |
| `RegistrationHandler.java` | 550 | `get(modid)`; `register*` / `register*Override` / `register*Callback` per registry type; inner `Helper<T>` |
| `ClientRegistrationHandler.java` | 712 | `get(modid)`; model/renderer/screen/render-layer/atlas registration methods |
| `GeneratorRegistrationHandler.java` | 155 | `get(modid)`; `addGenerator` / `addProvider` overloads |
| `RegistryEntryAcceptor.java` | 221 | `@interface RegistryEntryAcceptor`; enum `Registry`; `Handler.gatherAnnotatedFields`, `onRegisterEvent`, `onIdRemapping` |

**Test reference:** `src/test/java/com/supermartijn642/core/test/TestMod.java` — item registration + `@RegistryEntryAcceptor` + custom item renderer.

**Graphify:** `python3 tools/graphify_query.py corelib "com.supermartijn642.core.registry"` — hub nodes: `RegistrationHandler`, `ClientRegistrationHandler`, `Registries`, `GeneratorRegistrationHandler`, with edges to `CoreLib`, generator package, and mixins.

---

## Open questions / gaps

1. **Clone age:** This tree is **MC 1.16.5 / Forge 36**. Modern CoreLib (1.18–1.21+) may have renamed types (`ResourceLocation` → `Identifier`, `TileEntity` → `BlockEntity`, datagen API changes). Any future adoption must be re-read from a current CoreLib branch, not this clone verbatim.

2. **Fabric 26.2 model/client APIs:** `ClientRegistrationHandler` targets Forge 1.16 model events and `RenderTypeLookup`. Re-Forestry 26.2 client registration paths were not compared line-by-line; a dedicated client-init map would be needed before borrowing any client helper API.

3. **`registerBlockModelRenderType(Supplier, RenderType)` overload:** The two-arg overload at line 504 calls itself recursively (`this.registerBlockModelRenderType(block, renderType)`), which would stack-overflow if invoked — likely a bug in the donor (should delegate to the `Supplier<RenderType>` overload). Not relevant unless copying client handler code.

4. **Whether Re-Forestry wants a client registration batcher:** Module `*ClientHandler` classes work today; consolidating into one helper is stylistic only — no functional gap identified.

5. **Cross-mod `@RegistryEntryAcceptor`:** Handler warns and skips missing ids; load-order bugs surface as runtime warnings, not compile errors — same class of problem as Forge `@ObjectHolder`.

6. **No Fabric CoreLib in clone set:** If SuperMartijn642 ships Fabric CoreLib separately, this report does not cover it — this package is Forge-only in the examined clone.
