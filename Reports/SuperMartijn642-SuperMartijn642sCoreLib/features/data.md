# SuperMartijn642-SuperMartijn642sCoreLib — data

| Field | Value |
|---|---|
| Repo / alias | SuperMartijn642-SuperMartijn642sCoreLib / `corelib` |
| Package | `com.supermartijn642.core.data` |
| Clone | `MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/SuperMartijn642-SuperMartijn642sCoreLib` |
| Loader snapshot | Forge 1.16.5 / mod `supermartijn642corelib` 1.1.22 |
| Size | 15 Java files, ~815 LOC (inventory: **M**) |
| Subpackages | `condition/`, `recipe/`, `tag/`, `tag/entries/` |
| Graph | `python3 tools/graphify_query.py corelib "…"` |

## Summary

The **data** module is CoreLib’s datapack/runtime JSON helpers for three jobs: (1) a thin **resource-condition** API that wraps Forge `ICondition` / `CraftingHelper`, (2) a **conditional recipe serializer** that nests a real recipe under `conditions` + `recipe`, and (3) **custom tag entry** types (typed JSON objects in tag files) with one built-in entry that adds every registry id in a namespace.

Downstream CoreLib **generator** / **registry** packages are the main consumers (`RecipeGenerator.condition(…)`, `AdvancementGenerator`, `TagGenerator.addOptional(CustomTagEntry)`, `RegistrationHandler.registerResourceConditionSerializer`). Mixins in **mixin** + **extensions** wire custom tag JSON into vanilla/Forge tag loading. The package does **not** own datagen writers or GUI; those live elsewhere.

## Player/API surface

**Public types mods/generators call**

| Surface | Role |
|---|---|
| `ResourceCondition` | Public condition contract: `test(context)`, `getSerializer()`, fluent `negate()` / `or()` / `and()`, plus `createForgeCondition(…)` → Forge `ICondition` |
| `ResourceConditionSerializer<T>` | JSON serde for a condition; `createForgeConditionSerializer(id, …)` wraps into Forge `IConditionSerializer` |
| `ResourceConditionContext` | Empty context object (package-private ctor); reserved for future tag/registry lookups |
| `ModLoadedResourceCondition` | `modid` → `ModList.get().isLoaded` |
| `NotResourceCondition` / `AndResourceCondition` / `OrResourceCondition` | Boolean combinators; nested conditions serialize via `CraftingHelper` |
| `TagPopulatedResourceCondition` | Registry + tag id; maps items/blocks/entity_types/fluids via `Registries` + vanilla `*Tags.getAllTags()` |
| `ConditionalRecipeSerializer` | `IRecipeSerializer` singleton; `wrapRecipe` / `wrapRecipeWithForgeConditions` for datagen JSON |
| `CustomTagEntry` + `CustomTagEntrySerializer` | Extensible tag-entry SPI; `createVanillaEntry` → `ITag.ITagEntry` |
| `CustomTagEntries.potentiallyDeserialize` | Detect typed `{ "type": "…" }` JSON and build a `TagEntryAdapter` (or return null) |
| `TagEntryAdapter` | Adapts `CustomTagEntry` to `ITag.ITagEntry` (`build`, `serializeTo`) |
| `NamespaceTagEntry` | Built-in: include all elements whose id namespace matches |

**Registration IDs (from `CoreLib` constructor)**

| Kind | Registry path / id |
|---|---|
| Recipe serializer | `supermartijn642corelib:conditional` |
| Conditions | `supermartijn642corelib:mod_loaded`, `:not`, `:or`, `:and`, `:tag_populated` |
| Custom tag entry | `supermartijn642corelib:namespace` |
| Internal maps | `Registries.RECIPE_CONDITION_SERIALIZERS` (backed by Forge `CraftingHelper.conditions`), `Registries.CUSTOM_TAG_ENTRY_SERIALIZERS` (`supermartijn642corelib:custom_tag_entries`) |

**Addon registration helpers (outside package, used by this API)** — `RegistrationHandler.registerResourceConditionSerializer*`, `registerCustomTagEntrySerializer*`.

**Not player-facing:** package-private `ResourceConditions` wrappers (`ConditionWrapper` / `ConditionSerializerWrapper`).

## Architecture

```
Datagen (generator.*)
  RecipeBuilder.condition(ResourceCondition|ICondition)
       │
       ▼
ConditionalRecipeSerializer.wrapRecipe*(json, conditions)
  → { type: corelib:conditional, conditions: [...], recipe: {...} }
       │  load-time
       ▼
ConditionalRecipeSerializer.fromJson
  → resolve each condition via RECIPE_CONDITION_SERIALIZERS / CraftingHelper
  → if all pass: RecipeManager.fromJson(inner recipe); else null
  (fromNetwork/toNetwork are no-ops)

ResourceCondition ──wrap──► Forge ICondition  (ResourceConditions)
ResourceConditionSerializer ──wrap──► IConditionSerializer
  registered through RegistrationHandler → CraftingHelper
  CraftingHelperMixin mirrors Forge registrations into Registries

CustomTagEntry ──wrap──► TagEntryAdapter (ITag.ITagEntry)
  TagBuilderMixin / ForgeHooksMixin: parse typed JSON early
  TagCollectionReaderMixin (+ TagLoaderExtension): inject Registry/ForgeRegistry
    so NamespaceTagEntry can enumerate ids via TagEntryResolutionContext
```

Three layers stay distinct:

1. **condition** — loader-facing predicate + JSON type registry (Forge-shaped).
2. **recipe** — one serializer that applies conditions then delegates to the real recipe type.
3. **tag** — SPI + adapter + mixins so tag JSON can carry custom `type` objects, not only strings/`#tag` refs.

## Data & assets

- **No resources** live under this Java package. Condition/recipe/tag types are code-registered only.
- Emitted datapack shapes (conceptual):
  - Conditional recipe: `type`, `conditions[]` (`type` + payload), nested `recipe` object.
  - Condition payloads: `modid` (mod_loaded); `condition` object (not); `conditions` array (and/or); `registry` + `tag` (tag_populated).
  - Custom tag entry object: `type` (serializer id) + type-specific fields (`namespace` for NamespaceTagEntry); may appear in normal / optional / remove tag arrays.
- CoreLib’s own GUI textures / `mods.toml` / mixins JSON are **out of this module**; tag/condition behavior depends on mixins elsewhere (`TagBuilderMixin`, `ForgeHooksMixin`, `TagCollectionReaderMixin`, `CraftingHelperMixin`).

## Dependencies

| Depends on | Why |
|---|---|
| Forge `ICondition` / `IConditionSerializer` / `CraftingHelper` | Condition bridge and JSON serde of nested conditions |
| Forge `ModList` | `ModLoadedResourceCondition` |
| Forge registries / `IForgeRegistryEntry` | `ConditionalRecipeSerializer` registry name API |
| Vanilla recipe / tag APIs (`IRecipeSerializer`, `RecipeManager`, `ITag`, `*Tags`) | Load recipes; resolve tags |
| Gson | All serializers |
| `com.supermartijn642.core.registry.Registries` / `RegistryUtil` / `RegistrationHandler` | Condition + custom-tag serializer registries; identifier validation |
| Mixins + `TagLoaderExtension` (sibling packages) | Custom tag parse + registry injection |

**Does not depend on:** gui, block, item, network, render, or generator (generators depend *on* data).

## Notable algorithms/contracts

1. **Forge bridge map** — `ResourceConditions` keeps `ResourceConditionSerializer → IConditionSerializer`. Wrapped conditions look up their Forge id from that map; serializers must be registered before wrap/test.
2. **Conditional recipe load** — Manually walks `conditions` array (does not use Forge’s top-level recipe condition strip alone). Failed / unmet condition → `fromJson` returns **`null`** (recipe omitted). Network methods intentionally empty (server rebuilds from JSON).
3. **Custom tag detect** — `potentiallyDeserialize` returns null unless JSON is an object with a registered `type` id; otherwise vanilla/Forge parsing continues.
4. **Namespace resolve** — `NamespaceTagEntry.resolve` filters `getAllIdentifiers()` by namespace, then `getElement` each id (needs registry set on adapter before build).
5. **TagPopulated test (suspicious)** — `test` returns `getTagOrEmpty(tag).getValues().isEmpty()` (true when **empty**). Class/id name implies “populated”; source has `TODO properly do tags` / `TODO this is stupid`. Treat as likely inverted or unfinished.
6. **Combinators store Forge `ICondition`s** — And/Or/Not accept either CoreLib or raw Forge conditions; fluent `and`/`or` mutate the list in place.
7. **Registration order** — `Registries` ties recipe serializers after recipe-condition + custom-tag-entry serializers so conditions exist when recipes register.

## Port relevance to Re-Forestry

| Idea | Relevance | Guidance |
|---|---|---|
| Resource conditions for optional recipes / loot | **High idea, low code reuse** | Fabric already has `fabric-resource-conditions-api-v1` (`ResourceCondition` / `ResourceConditions`). Prefer Fabric defaults (`mod_loaded`, `and`/`or`/`not`, tags, etc.) over copying CoreLib’s Forge wrappers. |
| Conditional recipe serializer wrapper | **Low** | On Fabric, put `"fabric:load_conditions"` (or current Fabric key) on the recipe JSON itself; no need for a nested `corelib:conditional` recipe type. |
| Mod-loaded / boolean combinators | **Pattern only** | Same semantics as Fabric conditions; do not adopt Forge `CraftingHelper` bridge. |
| `TagPopulatedResourceCondition` | **Avoid as-is** | Logic looks wrong; unfinished TODOs. If needed, use Fabric tag conditions or write a small local condition against 26.2 registries. |
| `NamespaceTagEntry` (“all ids in namespace”) | **Optional niche** | Useful for bulk tags (e.g. all `reforestry:*` combs). On 26.2 Fabric/vanilla tags, prefer datagen enumeration or Fabric hooks; full SPI+mixins is heavy and 1.16.5-specific. |
| Standalone adopt | **N/A as dependency** | Re-Forestry must **not** depend on CoreLib. Copy only if a gap remains after Fabric API; rewrite into `com.leon1236.reforestry.*`. |

**Verdict for Phase 2:** Treat **data** as a reference for *what* CoreLib offers dependents (conditions + conditional recipes + namespace tag entries), not as a port target. Re-Forestry should use Fabric resource conditions for optional datapack content; skip CoreLib’s Forge adapter and conditional recipe serializer.

## Source map

| Path | Purpose |
|---|---|
| `…/data/condition/ResourceCondition.java` | Public condition interface + fluent combinators + Forge wrap helper |
| `…/data/condition/ResourceConditionSerializer.java` | Condition JSON serde + Forge serializer wrap helper |
| `…/data/condition/ResourceConditionContext.java` | Empty evaluation context (stub) |
| `…/data/condition/ResourceConditions.java` | Package-private Forge `ICondition` / serializer adapters |
| `…/data/condition/ModLoadedResourceCondition.java` | Mod-present condition |
| `…/data/condition/NotResourceCondition.java` | Negation |
| `…/data/condition/AndResourceCondition.java` | Conjunction |
| `…/data/condition/OrResourceCondition.java` | Disjunction |
| `…/data/condition/TagPopulatedResourceCondition.java` | Tag emptiness/population check (see gaps) |
| `…/data/recipe/ConditionalRecipeSerializer.java` | Nested conditional recipe type + wrap helpers |
| `…/data/tag/CustomTagEntry.java` | Custom tag entry SPI + resolution context |
| `…/data/tag/CustomTagEntrySerializer.java` | Entry JSON serde |
| `…/data/tag/CustomTagEntries.java` | Serialize / detect-deserialize / wrap |
| `…/data/tag/TagEntryAdapter.java` | `ITag.ITagEntry` adapter + registry injection |
| `…/data/tag/entries/NamespaceTagEntry.java` | Built-in “whole namespace” entry |
| *Related (not in package)* | `CoreLib.java` (registers types); `registry/RegistrationHandler.java`, `Registries.java`; `generator/RecipeGenerator.java`, `AdvancementGenerator.java`, `TagGenerator.java`; mixins `TagBuilderMixin`, `ForgeHooksMixin`, `TagCollectionReaderMixin`, `CraftingHelperMixin`; `extensions/TagLoaderExtension.java` |

## Open questions/gaps

1. Is `TagPopulatedResourceCondition.test` intentionally inverted (empty ⇒ true), or a bug? Unresolved TODOs in-file.
2. `ResourceConditionContext` is empty — planned tag/registry accessors never shipped in this 1.16.5 snapshot; later CoreLib branches may differ (not verified here).
3. Custom tag SPI requires mixins + Forge tag optional/remove arrays; exact Fabric 26.2 equivalent (if any) not mapped in this module pass.
4. MCP `search_code` for package text returned empty for this repo snapshot; analysis used clone + graphify + `list_files` (15 files confirmed).
5. Whether SuperMartijn’s **newer** multi-loader CoreLib keeps the same `data` API shape is out of scope for this Forge 1.16.5 inventory clone.
