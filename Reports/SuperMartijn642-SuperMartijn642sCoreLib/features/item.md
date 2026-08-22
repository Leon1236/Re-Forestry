# SuperMartijn642-SuperMartijn642sCoreLib — item

**Repo:** [SuperMartijn642-SuperMartijn642sCoreLib](https://github.com/SuperMartijn642/SuperMartijn642sCoreLib)  
**Alias:** `corelib`  
**Clone:** `MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/SuperMartijn642-SuperMartijn642sCoreLib`  
**Package:** `com.supermartijn642.core.item`  
**Clone MC stack:** Forge **1.16.5** (Core Lib **1.1.22**) — not Fabric, not 26.x  
**Scope:** This document covers only the five Java types in `com.supermartijn642.core.item`.

---

## Summary

The `item` package is a thin **Forge 1.16 abstraction layer** over vanilla `Item` / `BlockItem`. It gives mod authors:

1. **Renamed interaction hooks** (`interact`, `interactWithBlock`, …) instead of overriding obfuscated Forge method names directly.
2. A **fluent `ItemProperties` builder** that can track **multiple creative tabs** (1.16 `ItemGroup`) while still converting to vanilla `Item.Properties`.
3. A **`CreativeItemGroup` helper** for custom tab icons, manual fillers, and sorting.
4. An **`ItemRarity` enum** mirroring vanilla `Rarity`.

There is **no registration logic**, **no assets**, and **no networking** in this package. Items are registered elsewhere via `RegistrationHandler.registerItem(...)`. The package is referenced only by CoreLib’s internal test mod in this clone; downstream SuperMartijn642 mods consume it as a library dependency.

---

## Player / API surface

### What players see

Players interact with CoreLib items **exactly like vanilla items**. This package does not add new items, textures, or behaviors by itself. Any visible behavior comes from subclasses in consuming mods (tooltips, right-click use, block placement, etc.).

### What mod authors subclass / call

| Type | Role | Key override / call sites |
|------|------|---------------------------|
| `BaseItem` | Non-block item base | `appendItemInformation`, `interact`, `interactWithBlockFirst`, `interactWithBlock`, `interactWithEntity`, `inventoryUpdate` |
| `BaseBlockItem` | Block item base | Same hooks as `BaseItem`; `interactWithBlock` delegates to `BlockItem.useOn` |
| `ItemProperties` | Fluent builder | `create()`, `maxStackSize`, `durability`, `craftRemainder`, `group`, `rarity`, `food`, `fireResistant`, `toUnderlying()` |
| `CreativeItemGroup` | Custom creative tab | `create(...)`, `filler(...)`, `sorter(...)`, `sortAlphabetically()` |
| `ItemRarity` | Rarity enum | `COMMON`, `UNCOMMON`, `RARE`, `EPIC` → `getUnderlying()` |

**Nested types on base items:**

- `ItemUseResult` — `pass`, `consume`, `success`, `fail` (+ deprecated `fromUnderlying` / `toUnderlying`)
- `InteractionFeedback` — `PASS`, `CONSUME`, `SUCCESS` (`BaseItem`); `BaseBlockItem` also has `FAIL` and `fromUnderlying(ActionResultType)`

**Example (test mod only in clone):**

```java
RegistrationHandler.get("corelibtestmod")
    .registerItem("test_item", () -> new BaseItem(ItemProperties.create()));
```

---

## Architecture

```text
                    RegistrationHandler.registerItem(...)
                                    │
                                    ▼
              ┌─────────────────────────────────────┐
              │  BaseItem / BaseBlockItem           │
              │  (override vanilla Forge methods)   │
              └─────────────────────────────────────┘
                     │                    │
         named hooks │                    │ optional ItemProperties
         interact*   │                    │ (multi-tab Set<ItemGroup>)
         inventoryUpdate                    │
         appendItemInformation              ▼
                              ItemProperties.toUnderlying()
                              → vanilla Item.Properties (1 tab max)
```

### Class relationships

- **`BaseItem extends Item`** — Holds optional `ItemProperties`. Two constructors: raw `Item.Properties` (properties field `null`) or `ItemProperties` (stored for creative-tab queries).
- **`BaseBlockItem extends BlockItem`** — Mirror of `BaseItem`; block-placement path rebuilds an `ItemUseContext` manually in `interactWithBlock`.
- **`ItemProperties`** — Private mutable builder; `groups` is a package-visible `HashSet<ItemGroup>`. Validates stack size vs durability mutual exclusion.
- **`CreativeItemGroup extends ItemGroup`** — Final class; factories build `{modid}.{name}` id and `{modid}.item_group.{name}` translation key. Optional custom `filler` bypasses default tab population; optional `sorter` runs after fill.
- **`ItemRarity`** — Thin enum over `net.minecraft.item.Rarity`.

### Forge override mapping (delegation pattern)

| Vanilla / Forge override | CoreLib hook |
|--------------------------|--------------|
| `use` | `interact` → `ItemUseResult.toUnderlying` |
| `onItemUseFirst` | `interactWithBlockFirst` |
| `useOn` | `interactWithBlock` |
| `interactLivingEntity` | `interactWithEntity` |
| `inventoryTick` | `inventoryUpdate` |
| `appendHoverText` | `appendItemInformation` (client-only path) |

`BaseItem` also overrides `getOrCreateDescriptionId()` to `{namespace}.item.{path}` using `Registries.ITEMS.getIdentifier(this)`, and `getCreativeTabs()` to return all groups from `ItemProperties` when present.

### Graphify / call graph (local index)

BFS from item symbols connects primarily to:

- `RegistrationHandler` / `Registries.ITEMS` (registration + description id lookup)
- `TextComponents` (`CreativeItemGroup` display name + alphabetical sort)
- `RegistryUtil` (namespace validation in `CreativeItemGroup.create`)
- `CommonUtils`, GUI/render packages (no direct item-package dependency beyond base items)

No other CoreLib Java file in this clone imports `BaseBlockItem` or `CreativeItemGroup`; only `TestMod` uses `BaseItem` + `ItemProperties`.

---

## Data & assets

**None in this package.**

- No JSON models, lang keys, or datapack entries ship from `com.supermartijn642.core.item`.
- **Translation conventions implied by code:**
  - Items using `BaseItem.getOrCreateDescriptionId()`: `{modid}.item.{path}`
  - `CreativeItemGroup`: `{modid}.item_group` or `{modid}.item_group.{name}`
- **Creative tab population:** Default path uses vanilla `ItemGroup.fillItemList` (items whose properties reference the tab). Custom path uses `filler(Consumer<Consumer<ItemStack>>)`.
- **Multi-tab items:** `ItemProperties.group(...)` can add many tabs; `toUnderlying()` only passes **the first** tab to vanilla `Item.Properties.tab(...)`. Full multi-tab behavior relies on `getCreativeTabs()` override in `BaseItem` / `BaseBlockItem` (Forge 1.16 API).

---

## Dependencies

### Inbound (this package uses)

| Dependency | Usage |
|------------|--------|
| `net.minecraft.item.*` | `Item`, `BlockItem`, `ItemGroup`, `ItemStack`, `Rarity`, `Food`, `ItemUseContext`, … |
| `net.minecraftforge.api.distmarker` | `@OnlyIn(Dist.CLIENT)` on tooltip override |
| `com.supermartijn642.core.registry.Registries` | `Registries.ITEMS.getIdentifier(this)` for description id |
| `com.supermartijn642.core.registry.RegistryUtil` | Valid namespace check in `CreativeItemGroup.create` |
| `com.supermartijn642.core.TextComponents` | Tab title and alphabetical stack sort |

### Outbound (who uses this package)

| Consumer | Usage |
|----------|--------|
| `core/test/TestMod.java` | Registers `new BaseItem(ItemProperties.create())` |
| Changelog references | Fixes/changes to `BaseBlockItem`, `ItemProperties`, `CreativeItemGroup`, `InteractionFeedback` across CoreLib releases |
| External SuperMartijn642 mods (not in clone) | Expected primary consumers via Maven/Curse dependency |

### Not present

- No Fabric API
- No data components / 1.20+ item API
- No mixin hooks in this package
- No `RegistrationHandler.registerCreativeTab` — tabs must be registered by the consuming mod through Forge’s creative-tab mechanism separately

---

## Notable algorithms / contracts

### 1. Client/server success normalization (`ItemUseResult.toUnderlying`)

When hook returns `SUCCESS`, CoreLib maps:

- **Client:** `ActionResultType.SUCCESS`
- **Server:** `ActionResultType.CONSUME`

This mirrors vanilla item-use semantics where “success” on server implies consumption. Other results pass through unchanged.

### 2. Stack size vs durability invariant (`ItemProperties`)

- Setting `maxStackSize > 1` after durability is set → `RuntimeException`.
- Calling `durability(...)` forces `maxStackSize = 1`.
- `maxStackSize < 1` → `IllegalArgumentException`.

### 3. Multi-tab creative storage

`ItemProperties.groups` is a `Set<ItemGroup>`. Conversion to vanilla properties is **lossy for tabs 2..n** at construction time; runtime tab listing uses the stored set via `getCreativeTabs()`.

### 4. `CreativeItemGroup.fillItemList`

```text
if filler == null → super.fillItemList(items)   // vanilla tab membership
else → filler.accept(items::add)                // manual stacks
if sorter != null → items.sort(sorter)
```

`makeIcon()` throws if icon supplier returns null/empty stack.

### 5. `BaseBlockItem.interactWithBlock`

Builds synthetic `BlockRayTraceResult` + `ItemUseContext` and delegates to `super.useOn(...)`, then wraps via `InteractionFeedback.fromUnderlying`. Changelog **1.1.22** documents a fix: incorrect `CONSUME` vs `FAIL` when placement fails.

### 6. Description id contract

`BaseItem.getOrCreateDescriptionId()` → `{namespace}.item.{path}` (not vanilla’s default `item.{namespace}.{path}`). Consuming mods must align lang files accordingly.

### 7. Known bug in `CreativeItemGroup.create(modid, name, ...)`

Lines 21–24 validate `modid` twice; the second check was likely intended for `name`. Invalid `name` strings are not rejected.

---

## Port relevance to Re-Forestry

**Verdict: reference only — low priority, pattern-level adoption.**

Re-Forestry (MC **26.2**, Fabric) already covers the same responsibilities with different types:

| CoreLib (1.16 Forge) | Re-Forestry today |
|----------------------|-------------------|
| `ItemProperties` + `toUnderlying()` | `FeatureItem.seededProperties(Identifier)` with `setId(ResourceKey.create(Registries.ITEM, …))` |
| `CreativeItemGroup` | `FeatureCreativeTab` + `CreativeModeTab.builder(...)` |
| `BaseItem` interaction renaming | Items extend vanilla `Item` / `BlockItem` directly; override `use`, `useOn`, `appendHoverText`, etc. |
| `Registries.ITEMS.getIdentifier` for lang | Registry id baked into `Item.Properties.setId`; lang uses standard `item.{namespace}.{path}` |
| `ItemRarity` enum | Vanilla `Rarity` directly |
| Multi-tab via `getCreativeTabs()` | 26.x uses `CreativeModeTab` registry + `Item.Properties` creative tab assignment (API differs; search tab is global) |

### What is worth borrowing (standalone adopt)

1. **Hook naming** — `appendItemInformation(stack, level, Consumer<Component>, advanced)` and split interact methods are clearer than overriding mixed vanilla names. Could become an internal `core.items` base class **if** a refactor is planned; not required for parity.
2. **Multi-tab builder pattern** — Re-Forestry block groups already centralize properties in `FeatureBlockGroup` / `FeatureItemGroup`; no gap for a separate `ItemProperties` type unless addon API demands multi-tab items.
3. **`CreativeItemGroup.filler/sorter`** — `FeatureCreativeTab` could gain optional custom fill/sort hooks if a tab needs non-default ordering (e.g. genetics items); mirror logic, not the class.

### What not to port

- Do **not** add a dependency on `supermartijn642corelib` (Forge-only, wrong loader/version).
- Do **not** copy `ItemUseResult` / `ActionResultType` bridging — 26.2 uses `InteractionResult` / `InteractionResultHolder`.
- Do **not** copy `getOrCreateDescriptionId()` override — conflicts with seeded-id convention and Forestry CE lang keys (`for.*`, `item.reforestry.*`).
- `@OnlyIn` tooltip split — 26.2 `appendHoverText` signature uses `TooltipContext`, `TooltipDisplay`, and `Consumer<Component>` on both sides; no equivalent client-only type split.

### CE alignment

Forestry CE items typically extend `Item` with module-specific logic (genetics tooltips, pipette fluids, wrenches). Re-Forestry follows CE behavior in concrete item classes (`ItemBeeGE`, `ItemWrench`, …), not via a shared CoreLib-style base. **CE is WHAT; CoreLib item package is optional HOW.**

---

## Source map

| File | Lines (approx.) | Responsibility |
|------|-----------------|----------------|
| `src/main/java/com/supermartijn642/core/item/BaseItem.java` | 190 | Non-block item base; interaction delegation; description id; creative tabs |
| `src/main/java/com/supermartijn642/core/item/BaseBlockItem.java` | 198 | Block item base; same hooks + placement delegation |
| `src/main/java/com/supermartijn642/core/item/ItemProperties.java` | 98 | Fluent item properties builder |
| `src/main/java/com/supermartijn642/core/item/CreativeItemGroup.java` | 155 | Custom creative tab factory + filler/sorter |
| `src/main/java/com/supermartijn642/core/item/ItemRarity.java` | 24 | Rarity enum wrapper |

**Related (outside package, cited by item code):**

| File | Relevance |
|------|-----------|
| `core/registry/Registries.java` | `ITEMS` registry wrapper; `getIdentifier(Item)` |
| `core/registry/RegistrationHandler.java` | `registerItem` entry point for mods |
| `core/registry/RegistryUtil.java` | Namespace validation |
| `core/TextComponents.java` | Translation/format helpers for tabs |
| `src/test/.../TestMod.java` | Minimal `BaseItem` registration example |

**Changelog anchors:** 1.1.0 (package introduced), 1.1.x (`ItemProperties#toUnderlying` durability fix, `CreativeItemGroup#get*` fix, `BaseBlockItem#useOn` result fix, `InteractionFeedback#pass` fix).

---

## Open questions / gaps

1. **Creative tab registration path** — How consuming SuperMartijn642 mods register `CreativeItemGroup` instances is **not defined in this package** (no `registerItemGroup` in `RegistrationHandler` in this clone). Unclear whether tabs use Forge deferred registers or manual `RegistryEvent` elsewhere in CoreLib versions for other MC versions.
2. **Clone age** — This clone is **1.16.5**; newer CoreLib branches for 1.18–1.21 may have different item/creative-tab APIs. Any adoption decision for Re-Forestry should be checked against a modern Fabric-relevant donor (or vanilla 26.2) rather than this file verbatim.
3. **`BaseItem` vs `BaseBlockItem` parity** — `BaseItem.InteractionFeedback` lacks `FAIL` and `fromUnderlying`; `BaseBlockItem` has both. Intentional or oversight?
4. **`CreativeItemGroup.create` validation bug** — Duplicate `modid` check; `name` not validated.
5. **Multi-tab + datagen** — With only the first tab passed to `Item.Properties.tab`, recipe book / datagen paths that read construction-time tab may miss secondary tabs (runtime-only visibility).
6. **No usage in clone beyond test mod** — Cannot document real SuperMartijn642 mod subclass patterns from this repo alone; would need MCP search in e.g. `SuperMartijn642sChunkLoaders` or similar if concrete adoption examples are required.

---

*Generated: 2026-07-30 — Phase 1 module report, `com.supermartijn642.core.item` only.*
