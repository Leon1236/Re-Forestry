# Comprehensive — mezz-JustEnoughItems (JEI)

| Field | Value |
|---|---|
| **Repo** | mezz-JustEnoughItems |
| **Alias** | JEI |
| **Clone** | `MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/mezz-JustEnoughItems` |
| **Branch / HEAD** | `26.2` @ `758de67` |
| **JEI version** | `30.15.0` (MC **26.2**, Java 25) |
| **Inventory** | [00-INVENTORY.md](00-INVENTORY.md) |
| **Phase 2 date** | 2026-07-30 |

**Verdict for Re-Forestry:** Treat JEI as an **optional Fabric interop dependency**. Implement `mezz.jei.api.IModPlugin`, register classes under Fabric entrypoint key `jei_mod_plugin`, and compile against **CommonApi** (+ **FabricApi** only when you need Fabric fluid ingredient types). Do **not** copy or depend on NeoForge modules, JEI’s internal engine (`Library` / `Gui` / `Common`), or `Debug`.

---

## 1. Module map

Dependency shape (high level):

```text
Fabric loader jar  ──►  Gui + Library  ──►  Common  ──►  CommonApi
        │                                      ▲
        └── FabricApi (beside CommonApi) ──────┘
NeoForge loader jar ──►  Gui + Library  ──►  Common  ──►  CommonApi
        └── NeoForgeApi (beside CommonApi)
```

| # | Module | Gradle | Role | Re-Forestry? |
|---|---|---|---|---|
| 1 | [common-api](features/common-api.md) | `CommonApi/` | Public addon surface: `IModPlugin`, registration, recipe/gui/ingredient APIs | **USE** — primary compile/API surface |
| 2 | [common](features/common.md) | `Common/` | Shared impl: config, codecs, network helpers, platform services, assets | **IGNORE** — JEI internals |
| 3 | [library](features/library.md) | `Library/` | Engine: `PluginLoader`, vanilla plugins, recipe layouts, ingredient manager | **IGNORE** — runtime only (JEI owns it) |
| 4 | [gui](features/gui.md) | `Gui/` | Client UI: overlay, bookmarks, recipe screens, filters | **IGNORE** — interact via CommonApi GUI handlers |
| 5 | [fabric-loader](features/fabric-loader.md) | `Fabric/` | Fabric mod jar: entrypoints, mixins, `FabricPluginFinder`, platform | **REFERENCE** — discovery contract only |
| 6 | [fabric-api](features/fabric-api.md) | `FabricApi/` | Fabric extras: `FabricTypes.FLUID_STACK`, `IJeiFluidIngredient` / `FluidVariant` | **USE when needed** — fluid slots / Fabric fluid ingredients |
| 7 | [neoforge-loader](features/neoforge-loader.md) | `NeoForge/` | NeoForge mod jar: events, `@JeiPlugin` scan (`ForgePluginFinder`) | **IGNORE** — wrong loader |
| 8 | [neoforge-api](features/neoforge-api.md) | `NeoForgeApi/` | `NeoForgeTypes.FLUID_STACK` (`FluidStack`) | **IGNORE** — Forge fluid type |
| 9 | [debug](features/debug.md) | `Debug/` | Optional debug ingredients / test categories | **IGNORE** |

Out of scope (inventory): `Changelog/`, `buildSrc/`, CI, root docs.

---

## 2. Fabric addon contract (what addons implement)

### 2.1 Discovery

| Loader | How JEI finds plugins |
|---|---|
| **Fabric** | `FabricPluginFinder.getModPlugins()` → `FabricLoader.getEntrypointContainers("jei_mod_plugin", IModPlugin.class)` |
| **NeoForge** | `ForgePluginFinder` scans classpath for `@JeiPlugin` and reflects no-arg constructors |

**Re-Forestry (Fabric) must:**

1. List plugin class FQCNs under `fabric.mod.json` → `entrypoints.jei_mod_plugin`.
2. Implement `mezz.jei.api.IModPlugin` with a **public no-arg constructor**.
3. Keep `@JeiPlugin` on the class (API docs require it; NeoForge needs it; Fabric entrypoint is what actually loads).

JEI’s own Fabric jar registers built-in plugins the same way:

- `mezz.jei.library.plugins.vanilla.VanillaPlugin`
- `mezz.jei.library.plugins.jei.JeiInternalPlugin`
- `mezz.jei.fabric.plugins.fabric.FabricGuiPlugin`
- `mezz.jei.gui.plugins.JeiGuiPlugin`

### 2.2 Soft dependency shape

- Re-Forestry: `suggests.jei` (already) — do **not** hard-`depends` on JEI unless you intentionally break without it.
- Plugin classes live under `compat/jei` and only load when JEI is present (entrypoint containers are only resolved if the entrypoint key is requested by a loaded mod that owns that key — JEI’s Fabric loader).
- Compile against JEI API jars (CommonApi / FabricApi); never ship JEI sources inside Re-Forestry.

### 2.3 `IModPlugin` lifecycle hooks

Required:

| Method | Purpose |
|---|---|
| `Identifier getPluginUid()` | Unique id; namespace = your modid (e.g. `reforestry:factory`) |

Optional defaults (call order is owned by JEI `PluginLoader`; register categories before recipes):

| Hook | Registration type | Typical Re-Forestry use |
|---|---|---|
| `configureJei` | `IJeiFeatures` | Rare (disable JEI GUI features early) |
| `registerItemSubtypes` | `ISubtypeRegistration` | Bee/genome item subtypes if JEI collapses variants |
| `registerFluidSubtypes` | `ISubtypeRegistration` + `IPlatformFluidHelper` | Fluid variants with components |
| `registerIngredients` | `IModIngredientRegistration` | Custom ingredient types (unlikely) |
| `registerExtraIngredients` | `IExtraIngredientRegistration` | Creative-missing stacks / special fluids |
| `registerIngredientAliases` | `IIngredientAliasRegistration` | Search aliases |
| `registerAdvancedSearch` | `IAdvancedSearchRegistration` | Custom search (rare) |
| `registerModInfo` | `IModInfoRegistration` | Mod aliases |
| `registerCategories` | `IRecipeCategoryRegistration` | Machine recipe categories |
| `registerVanillaCategoryExtensions` | `IVanillaCategoryExtensionRegistration` | Custom crafting/smithing shapes |
| `registerRecipes` | `IRecipeRegistration` | Recipes + `addIngredientInfo` descriptions |
| `registerRecipeTransferHandlers` | `IRecipeTransferRegistration` | “+” move into machine GUIs |
| `registerRecipeCatalysts` | `IRecipeCatalystRegistration` | Crafting stations / catalysts |
| `registerGuiHandlers` | `IGuiHandlerRegistration` | Click areas, exclusion areas, ghost handlers |
| `registerAdvanced` | `IAdvancedRegistration` | Recipe manager plugins (rare) |
| `registerRuntime` | `IRuntimeRegistration` | Override runtime pieces (rare) |
| `onRuntimeAvailable` / `onRuntimeUnavailable` | `IJeiRuntime` | Late lookups |
| `onConfigManagerAvailable` | `IJeiConfigManager` | Config hooks (rare) |

Package map for registration APIs: `mezz.jei.api.registration.*` ([common-api](features/common-api.md)).

### 2.4 Fluids on Fabric

Prefer **loader-agnostic** helpers when possible:

- `IJeiHelpers.getPlatformFluidHelper()` → `IPlatformFluidHelper<T>`
- Categories take `IPlatformFluidHelper` and stay portable

When you need Fabric-specific ingredient types:

- `mezz.jei.api.fabric.constants.FabricTypes.FLUID_STACK`
- `IJeiFluidIngredient` / `JeiFluidIngredient(FluidVariant, long)` ([fabric-api](features/fabric-api.md))

Never use `mezz.jei.api.neoforge.NeoForgeTypes` / NeoForge `FluidStack` on Fabric.

### 2.5 Recipe UI building blocks (CommonApi)

| Area | Key types |
|---|---|
| Categories | `IRecipeCategory`, `AbstractRecipeCategory`, `IRecipeType` / `IRecipeHolderType` |
| Layout | `IRecipeLayoutBuilder`, `IRecipeSlotBuilder`, `RecipeIngredientRole` |
| Drawables | `IGuiHelper`, `IDrawable`, `IDrawableAnimated` |
| Transfer | `IRecipeTransferHandler`, `IRecipeTransferInfo` |
| GUI glue | `IGuiHandlerRegistration` (click areas, container handlers, ghost) |
| Constants | `VanillaTypes.ITEM_STACK`, `RecipeTypes.*`, `Tags.HIDDEN_FROM_RECIPE_VIEWERS` |

---

## 3. Use vs ignore (Re-Forestry checklist)

### Use

- **CommonApi** as the only required JEI compile surface for plugins.
- **Fabric entrypoint** `jei_mod_plugin` + `IModPlugin` + `@JeiPlugin`.
- **FabricApi** when registering/rendering Fabric `FluidVariant` ingredients explicitly.
- Patterns already mirrored in Re-Forestry:
  - `CoreJeiPlugin` — descriptions via `IRecipeRegistration`
  - `FactoryJeiPlugin` — categories, recipes, catalysts, carpenter transfer, screen click areas / generic `ScreenForestry` handler
- Soft `suggests: jei` in `fabric.mod.json`.

### Ignore / do not port

| Area | Why |
|---|---|
| `NeoForge/`, `NeoForgeApi/` | Wrong loader; annotation-scan discovery ≠ Fabric entrypoints |
| `Common/`, `Library/`, `Gui/` internals | Engine + UI owned by JEI at runtime |
| `Debug/` | Dev-only stress content |
| JEI mixins, network, Amecs keybinds, chat link handlers | Loader/platform concerns |
| Copying vanilla recipe plugins | JEI already registers them |
| Hard `depends` on JEI | Breaks standalone play without JEI |

### Reference-only

| Area | Why look |
|---|---|
| [fabric-loader](features/fabric-loader.md) `FabricPluginFinder` | Confirms entrypoint key and error handling |
| JEI’s own `fabric.mod.json` | Example of multi-plugin `jei_mod_plugin` list |
| `IPlatformFluidHelper` | Correct cross-loader fluid handling for factory machines |

---

## 4. Re-Forestry status vs JEI surface

Already wired (as of this synthesis):

| Piece | Location |
|---|---|
| Entrypoints | `fabric.mod.json` → `CoreJeiPlugin`, `FactoryJeiPlugin` |
| Core descriptions | `core.compat.jei.CoreJeiPlugin` |
| Factory categories / recipes / catalysts / transfer / GUI | `factory.compat.jei.FactoryJeiPlugin` + recipe categories |
| Fluid-aware categories | Use `IPlatformFluidHelper` from `IJeiHelpers` |

Likely future hooks (when content needs them — not inventing work):

| Hook | When |
|---|---|
| `registerItemSubtypes` | Genetics / bee / sapling items that need NBT/component identity in JEI |
| Extra transfer handlers | More machines beyond carpenter |
| Apiculture / arboriculture plugins | Separate `IModPlugin` + entrypoint entries (same pattern as factory/core) |
| `FabricTypes` | If categories must accept typed Fabric fluid ingredients directly |
| `Tags.HIDDEN_FROM_RECIPE_VIEWERS` | Hide technical items from JEI |

---

## 5. Feature report links

| Module | Report |
|---|---|
| Inventory | [00-INVENTORY.md](00-INVENTORY.md) |
| common-api | [features/common-api.md](features/common-api.md) |
| common | [features/common.md](features/common.md) |
| library | [features/library.md](features/library.md) |
| gui | [features/gui.md](features/gui.md) |
| fabric-loader | [features/fabric-loader.md](features/fabric-loader.md) |
| fabric-api | [features/fabric-api.md](features/fabric-api.md) |
| neoforge-loader | [features/neoforge-loader.md](features/neoforge-loader.md) |
| neoforge-api | [features/neoforge-api.md](features/neoforge-api.md) |
| debug | [features/debug.md](features/debug.md) |

Graph follow-ups: `python3 tools/graphify_query.py JEI "…"`.

---

## 6. Gaps / caveats

- Phase 1 feature markdowns are inventory-style (file lists + skim); this synthesis pulls the **addon contract** from CommonApi + FabricPluginFinder (and contrasts NeoForge scan).
- Fabric discovery is **entrypoint-only**; relying on `@JeiPlugin` alone is insufficient on Fabric.
- JEI internal call order for hooks is implemented in `Library` `PluginLoader` — treat CommonApi method docs as the contract; do not depend on private load order beyond “categories before recipes.”
- Do not confuse Re-Forestry’s `reforestry:plugin` entrypoint (Forestry plugins) with JEI’s `jei_mod_plugin`.
