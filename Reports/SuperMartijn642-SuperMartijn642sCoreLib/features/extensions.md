# SuperMartijn642-SuperMartijn642sCoreLib — extensions

| Field | Value |
|---|---|
| Repo / alias | SuperMartijn642-SuperMartijn642sCoreLib / `corelib` |
| Module slug | `extensions` |
| Package | `com.supermartijn642.core.extensions` |
| Clone | Forge 1.16.5 / `supermartijn642corelib` 1.1.22 |
| Size | **S** — 1 Java file (12 lines) |
| Graph community | ~30 (clustered with tag mixins) |

## Summary

The `extensions` package is a **Mixin duck-type interface** package, not a player-facing feature. It contains a single interface, `TagLoaderExtension`, whose only method attaches a vanilla `Registry` and/or Forge `ForgeRegistry` to a `TagCollectionReader` instance before tags are built.

That registry context is required so CoreLib’s custom tag entries (`TagEntryAdapter` / `CustomTagEntry` in the **data** module) can call `getAllElements()` / `getAllIdentifiers()` while resolving (e.g. namespace-wide tag entries). Without this hook, adapters would not know which registry they are resolving against.

**Verdict for Re-Forestry:** do **not** port this package as-is. It is Forge 1.16 tag-loader plumbing. The *idea* (extension interface implemented by a mixin so other code can cast the target class) is a reusable Mixin pattern; the specific tag-registry attachment is irrelevant on Fabric 26.2 where tag loading and registries differ.

## Player/API surface

| Surface | Present? | Notes |
|---|---|---|
| Player-visible UI / items / commands | No | Internal loader hook only |
| Public mod API for other mods | Effectively no | Package is not documented as API; method is an implementation detail for CoreLib mixins + `TagEntryAdapter` |
| Method contract | `void supermartijn642corelibSetRegistry(Registry<?> registry, ForgeRegistry<?> forgeRegistry)` | Prefixed with mod id to avoid Mixin/interface clash with other mods |
| Callers | `ForgeTagHandlerMixin` (sets Forge registry for custom tag types); `TagCollectionReaderMixin` (implements interface, stores fields, applies to adapters on `load`) | Both under `com.supermartijn642.core.mixin` |
| Consumers of the stored registries | `TagEntryAdapter.setRegistry` → `TagEntryResolutionContext.getAllElements` / `getAllIdentifiers` | **data** module |

No lang keys, recipes, blocks, or creative tabs belong to this module.

## Architecture

```
ForgeTagHandler.createCustomTagTypeReaders()  [RETURN inject]
        │
        ▼
((TagLoaderExtension) TagCollectionReader).supermartijn642corelibSetRegistry(null, forgeRegistry)
        │
        ▼
TagCollectionReaderMixin  implements TagLoaderExtension
  · stores registry / forgeRegistry (@Unique)
  · on load(Map): if both null → map directory string → vanilla Registry
  · for each ITag.Builder entry: if TagEntryAdapter → setRegistry(...)
        │
        ▼
TagEntryAdapter.build(...) → CustomTagEntry.resolve(context with full registry enumeration)
```

**Design pattern:** Mixin *extension interface* — the mixin class implements a CoreLib interface; other mixins/code cast the vanilla/Forge object to that interface instead of using accessors for a one-shot setter.

**Two registry paths:**

1. **Custom Forge tag types** — `ForgeTagHandlerMixin` passes `RegistryManager.ACTIVE.getRegistry(registryName)` as `forgeRegistry` (`registry` = null).
2. **Vanilla directories** — if neither was set by load time, `TagCollectionReaderMixin` shadows `directory` and maps:
   - `tags/blocks` → `Registry.BLOCK`
   - `tags/items` → `Registry.ITEM`
   - `tags/fluids` → `Registry.FLUID`
   - `tags/entity_types` → `Registry.ENTITY_TYPE`

Registered in `modid.mixins.json` as `ForgeTagHandlerMixin` and `TagCollectionReaderMixin` (common mixins, not client-only).

**Package boundary:** `extensions` owns only the interface. Behavior lives in **mixin** + **data.tag**. Graphify links confirm community 30 ties `TagLoaderExtension` ↔ both mixins ↔ `TagEntryAdapter`.

## Data & assets

| Kind | Owned by `extensions`? |
|---|---|
| Java sources | Yes — `TagLoaderExtension.java` only |
| JSON / datapack / textures / lang | No |
| Mixin config | No — listed in root `modid.mixins.json` for sibling mixin classes |
| Generated tags under `src/generated` | Unrelated (mining tags from **generator**); do not belong to this module |

## Dependencies

| Dependency | Role |
|---|---|
| `net.minecraft.util.registry.Registry` | Vanilla registry passed into the setter / used as fallback |
| `net.minecraftforge.registries.ForgeRegistry` | Forge registry for custom tag types |
| Mixin (`TagCollectionReader`, `ForgeTagHandler`) | Implements / invokes the interface |
| `com.supermartijn642.core.data.tag.TagEntryAdapter` | Downstream consumer of registry context |
| Indirect: `CustomTagEntry`, `NamespaceTagEntry`, `Registries.CUSTOM_TAG_ENTRY_SERIALIZERS` | Why the registry must be known at resolve time |

No Fabric APIs. No network / GUI / energy deps. Hard Forge 1.16.5 types throughout.

## Notable algorithms/contracts

1. **Setter before build** — Registry must be attached before (or at HEAD of) `TagCollectionReader.load`; otherwise adapters resolve with null registries (NPE risk on `getAllIdentifiers` for namespace entries).
2. **Either-or registries** — `TagEntryAdapter` prefers `registry` when non-null; else uses `forgeRegistry.getValues()` / `getKeys()`. Contract: at least one should be non-null by resolve time for custom entries that enumerate the registry.
3. **Directory fallback** — Hard-coded 1.16 tag folder strings; not extensible for arbitrary registries without the Forge custom-tag path.
4. **Collision-safe method name** — `supermartijn642corelibSetRegistry` follows Mixin best practice for interface injection namespacing.
5. **Always returns true from adapter `build`** — resolution failure is not signaled via boolean; empty/null collections just add nothing (see **data** module for full resolve semantics).

## Port relevance to Re-Forestry

| Aspect | Relevance | Guidance |
|---|---|---|
| Copy `TagLoaderExtension` / Forge tag mixins | **None** | Re-Forestry is Fabric 26.2 standalone; Forge `TagCollectionReader` / `ForgeTagHandler` do not exist in this form |
| Custom tag entry types needing full-registry scan | **Low** | Re-Forestry uses normal `TagKey` / datapack tags (`ReforestryBiomeTags`, flower tags, etc.). No CoreLib-style `{"type":"…namespace…"}` entries |
| Mixin extension-interface pattern | **Optional pattern only** | If a future Fabric mixin needs a typed setter on a vanilla class, the same duck-interface approach is fine — implement in `com.leon1236.reforestry`, do not depend on CoreLib |
| Adopt as Gradle / `fabric.mod.json` dependency | **Forbidden** | Standalone-adopt rule: reference only; no player dependency on `supermartijn642corelib` |

**Bottom line:** skip for porting. Deeper tag-customization study belongs in the **data** / **mixin** module reports, not here.

## Source map

| Path | Role |
|---|---|
| `src/main/java/com/supermartijn642/core/extensions/TagLoaderExtension.java` | Sole module type — extension interface |
| `src/main/java/com/supermartijn642/core/mixin/TagCollectionReaderMixin.java` | Implements interface; directory fallback; pushes registry into adapters |
| `src/main/java/com/supermartijn642/core/mixin/ForgeTagHandlerMixin.java` | Invokes setter for custom Forge tag loaders |
| `src/main/java/com/supermartijn642/core/data/tag/TagEntryAdapter.java` | Stores registry; builds resolution context |
| `src/main/java/com/supermartijn642/core/data/tag/CustomTagEntry.java` | Resolve API (`getAllElements` / `getAllIdentifiers`) |
| `src/main/java/com/supermartijn642/core/data/tag/entries/NamespaceTagEntry.java` | Example consumer needing full identifier set |
| `src/main/resources/modid.mixins.json` | Registers the two mixin classes above |

Evidence: graphify `corelib` query on `TagLoaderExtension` / `extensions`; MCP `search_code` on repo `SuperMartijn642-SuperMartijn642sCoreLib`; direct reads of clone sources.

## Open questions/gaps

1. **Newer CoreLib branches** — This clone is 1.16.5 / 1.1.22. Later MC ports (1.18+) may rename `TagCollectionReader` → `TagLoader` and reshape or drop this package; not verified in this tree.
2. **Fabric edition of CoreLib** — Upstream ships multi-loader versions; whether Fabric builds keep an `extensions` package with different targets was not checked against this Forge-only clone.
3. **Failure mode if both registries stay null** — Adapter `getAllElements` casts `forgeRegistry.getValues()` when `registry == null`; for vanilla paths that somehow skip the directory map, behavior is undefined / NPE. Unclear if that can happen for non-standard directories in 1.16.5.
4. **Overlap with Phase 1 `data` / `mixin` reports** — Avoid duplicating custom-tag serializer/datagen detail there; this report intentionally stops at the extension hook.
