# COMPREHENSIVE — SuperMartijn642's Core Lib

| Field | Value |
|---|---|
| Repo | SuperMartijn642-SuperMartijn642sCoreLib |
| Alias | `corelib` |
| Clone | `MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/SuperMartijn642-SuperMartijn642sCoreLib` |
| Stack in clone | **Forge 36.2.42 / Minecraft 1.16.5** — mod `supermartijn642corelib` **1.1.22** |
| Graph | `python3 tools/graphify_query.py corelib "…"` |
| Inventory | [`00-INVENTORY.md`](00-INVENTORY.md) |
| Features | [`features/`](features/) (11 reports) |
| Date | 2026-07-30 |
| Phase | 2 synthesizer |

## Verdict for Re-Forestry

**Pattern / reference library only.** This clone is Forge 1.16.5 shared helpers (GUI, datagen, registration). Re-Forestry must **not** add `supermartijn642corelib` to Gradle or `fabric.mod.json`. Forestry GUI and content behavior stay CE/IF → existing `ScreenForestry` / ledgers / `FeatureGroup`. Steal **ideas** (widget layering, datagen aggregation, BE item-persist split, packet lifecycle) only when a concrete RF gap appears; rewrite into `com.leon1236.reforestry.*` with Fabric 26.2 APIs.

**Highest-value skim:** [`features/gui.md`](features/gui.md) + [`features/generator.md`](features/generator.md) (+ [`features/data.md`](features/data.md) for conditions). Everything else is low priority or already covered by RF/Fabric.

---

## Module map

```text
                    ┌─────────────────────────────────────────┐
                    │  CoreLib (Forge 1.16.5) — reference only │
                    └─────────────────────────────────────────┘
   registry ──► block / item registration + client screens/BER
      │              │
      ├──────────────┼──► generator (datagen DSL) ◄── data (conditions/tags)
      │              │         ▲
      │              │         └── mixin (DataGenerator / tags / GUI hooks)
      │              │
      ├──► gui (widgets, containers, ScreenUtils) ◄── mixin (cursor, slots)
      ├──► render (BER/item adapters, overlays) ◄── mixin (world render)
      ├──► network (PacketChannel) — consumer API, unused in-tree
      ├──► extensions (TagLoaderExtension) — mixin duck interface
      └──► util (Either/Pair/Holder…) — leaf helpers
```

| # | Slug | Size | Role | RF priority | Feature report |
|---|---|---|---|---|---|
| 1 | **gui** | L (~28 files) | Widget tree, containers, CustomSlot, ScreenUtils, cursors | **High skim** — patterns only | [gui.md](features/gui.md) |
| 2 | **generator** | M (~14 files) | Fluent datagen: recipes, tags, models, loot, lang | **Medium** — aggregation ideas | [generator.md](features/generator.md) |
| 3 | **data** | M (~15 files) | Resource conditions, conditional recipes, custom tag entries | **Low code** — use Fabric conditions | [data.md](features/data.md) |
| 4 | **registry** | S–M (~6 files) | Deferred Forge registration + acceptor + datagen queue | **Skip** — RF has FeatureGroup | [registry.md](features/registry.md) |
| 5 | **mixin** | M (12 files) | Forge hooks enabling gui/data/generator/registry | **Skip wholesale** | [mixin.md](features/mixin.md) |
| 6 | **render** | M (~9 files) | Custom item/BER adapters, debug overlays, world event | **Low–medium** overlays only | [render.md](features/render.md) |
| 7 | **block** | S (~7 files) | BaseBlock / BE NBT split / BlockShape | **Optional** item-persist idea | [block.md](features/block.md) |
| 8 | **item** | S (~5 files) | BaseItem hooks, ItemProperties, creative tabs | **Skip** — FeatureItem covers | [item.md](features/item.md) |
| 9 | **network** | S (~6 files) | Typed PacketChannel + BE packets | **Pattern** for future payloads | [network.md](features/network.md) |
| 10 | **util** | S (~8 files) | Either, Pair, Holder, TriFunction… | **Skip** — JDK/Mojang enough | [util.md](features/util.md) |
| 11 | **extensions** | S (1 file) | TagLoaderExtension duck interface | **Skip** | [extensions.md](features/extensions.md) |

**Out of inventory scope (root helpers):** `CoreLib`, `ClientUtils`, `CommonUtils`, `CoreSide`, `EnergyFormat`, `TextComponents` — bootstrap / shared utils, not feature packages.

---

## GUI takeaways (primary)

Source of truth for Forestry UI remains **CE → `ScreenForestry` + ledgers** (`GuiPowerLedger`, `GuiClimateLedger`, `GuiHintLedger`, …). CoreLib GUI is a **different design** (single root `Widget` tree). Do not replace RF screens with CoreLib’s hierarchy.

### Worth remembering

| Pattern | Why it matters | RF action |
|---|---|---|
| Layered widget render (`background` → `render` → `foreground` → `overlay` → tooltips) | Clear z-order for complex UIs | Keep ledger layering; optionally document the same order if adding scroll panels |
| Object validate-or-close (`ObjectBase*` / BE / held item) | Close GUI when TE gone or item invalid | Prefer `stillValid` / existing menu checks; renew loop only if item-GUIs need it |
| `CustomSlot` builder (size, show/hide, filter, active/move) | Variable-size / ghost-friendly slots | Do **not** port Forge `IItemHandler`; RF already has CE slots + Fabric transfer |
| Premade TextField / Scrollbar / Scissor | Analyzer / config / long lists | Reference only; implement with 26.2 `GuiGraphics` / vanilla widgets if needed |
| `ScreenUtils` 9-slice + scissor | Shared draw helpers | Optional util rewrite if RF needs more than current helpers — **no** 1.16 Tessellator/`RenderSystem` copy |
| GLFW pending cursor + mixin | Polish | Usually skip |

### Do not port

- Full `WidgetScreen` / `WidgetContainerScreen` stack
- `BaseContainerType` PacketBuffer factory → RF uses Fabric `ExtendedScreenHandlerType` / codecs
- Forge `GuiContainerEvent` re-posts
- Upstream bug: `ObjectBaseWidget.left()` returns `width` — fix if ever copying

**Assets note:** CoreLib ships five GUI textures under `assets/supermartijn642corelib/textures/gui/` (background, buttons, slot, scrollbar). Do not depend on them; RF has Forestry-style assets.

---

## Datagen takeaways (secondary)

RF today: `ReForestryDataGenerator` stub; most data/assets hand-authored. Growing datagen is optional polish. Prefer **Fabric Loom + Kaupenjoe / Fabric API 26.2 providers**, not CoreLib’s Forge mixin pipeline.

### Worth remembering

| Pattern | Why it matters | RF action |
|---|---|---|
| Two-phase `generate()` (track, no writes) → `save()` | Cross-provider “file will exist” checks | Useful if many interdependent providers; Fabric packs already often suffice |
| Tag / lang **aggregators** (merge same path) | Multi-module Forestry tags/lang pain | Medium–high idea: merge carefully or one owner per file |
| Fluent recipe/model/blockstate DSL | Author ergonomics | Low — vanilla/Fabric providers cover; 1.16 shapes ≠ 26.2 |
| Empty mining-tag seeding | Gives aggregators a target | Low — RF should emit **real** mineable tags |
| Conditional recipes via Forge `ICondition` | Optional datapack content | Use `fabric-resource-conditions-api-v1` on the recipe JSON — **no** nested `corelib:conditional` type |
| `NamespaceTagEntry` (“all ids in ns”) | Bulk tags | Optional niche; prefer datagen enumeration over SPI+mixins |

### Do not port

- `DataGeneratorMixin` / `GeneratorRegistrationHandler` Forge orchestration
- `CraftingHelper` / custom tag-entry mixins (`TagBuilderMixin`, `ForgeHooksMixin`, …)
- `TagPopulatedResourceCondition` as-is (looks inverted / unfinished TODOs)

---

## Other modules — one-line stance

| Module | Stance |
|---|---|
| **registry** | Skip. RF `FeatureGroup` / direct `Registry.register` already fit Fabric. No `@RegistryEntryAcceptor`. |
| **mixin** | Skip wholesale. Prefer Fabric API hooks; RF mixins stay for Forestry-specific client needs only. |
| **block** | Optional idea: split `writeData` / `writeClientData` / `writeItemStackData` + pick-block `tileData`. RF has `TileForestry` / `ValueInput`/`ValueOutput`. Skip Material/`ToolType` harvest API. |
| **item** | Skip. `FeatureItem` / `FeatureCreativeTab` + vanilla overrides. Do not copy `{ns}.item.{path}` description ids. |
| **network** | Pattern only when RF adds custom payloads: verify → handle, main-thread queue, BE-at-pos lookup. Use Fabric `CustomPayload` / `StreamCodec`, not index-multiplexed `SimpleChannel`. |
| **render** | RF already has `SpecialModelRenderer` + BERs. Optional: shape overlay math via Fabric `WorldRenderEvents`. No ISTER/`Item.ister` reflection. |
| **util** | Skip. Use `Optional`, Mojang `Pair`, records, `AtomicReference`. |
| **extensions** | Skip. Forge tag-loader duck interface only. |

---

## Cross-cutting dependency graph (within CoreLib)

```text
util ──► (leaf)
registry ──uses──► util, data serializers, generator types
generator ──uses──► registry, data conditions/tags, util
data ──uses──► registry; enabled by mixin + extensions
gui ──uses──► util.Holder, render.RenderUtils (scissor flush); enabled by mixin
render ──uses──► block.BlockShape; wired by registry client + LevelRendererMixin
block / item ──uses──► registry (description ids); harvest/loot via mixin
network ──uses──► RegistryUtil, ClientUtils; no in-tree senders
```

Root bootstrap (`CoreLib`) registers CoreLib’s own conditions, tag entry, generators, and gathers `@RegistryEntryAcceptor` fields.

---

## Standalone adopt checklist

- [ ] **No** `supermartijn642corelib` in `fabric.mod.json` / Gradle deps
- [ ] **No** `com.supermartijn642.*` imports in `src/`
- [ ] Any copied idea rewritten under `com.leon1236.reforestry.*` with MC **26.2** / Fabric APIs
- [ ] GUI behavior still mirrors **CE**, not CoreLib widgets
- [ ] Datagen uses Fabric providers / resource conditions, not Forge mixins
- [ ] Prefer Fabric events over new mixins (project convention)

---

## Known upstream quirks (do not copy blindly)

| Issue | Where |
|---|---|
| `ObjectBaseWidget.left()` returns `width` | gui |
| Smoking/campfire recipe serialize may reuse wrong JSON object | generator |
| `TagPopulatedResourceCondition` empty ⇒ true + TODOs | data |
| `PacketChannel` index bounds `size < index` off-by-one | network |
| `CreativeItemGroup.create` validates `modid` twice, not `name` | item |
| `registerBlockModelRenderType` two-arg overload self-recursion | registry |
| `GameDataMixin` targets fragile Forge lambda name | mixin |

---

## Gaps / version caveats

1. **Clone is 1.16.5 Forge only.** Newer CoreLib (Fabric / 1.18–1.21+) may differ; re-read upstream before any literal adopt.
2. **No in-repo production consumers** for most APIs (test mod only) — real call sites live in other SuperMartijn642 mods, not analyzed here.
3. **README “Config Lib” branding** — this tree has **no** top-level `config` package; modules above are the feature map.
4. Interaction of a wholesale CoreLib-style `ContainerScreen.render` override with RF **JEI** ghost overlays was not tested — another reason not to replace `ScreenForestry`.

---

## Quick links

| Doc | Path |
|---|---|
| Inventory | [00-INVENTORY.md](00-INVENTORY.md) |
| GUI | [features/gui.md](features/gui.md) |
| Generator | [features/generator.md](features/generator.md) |
| Data | [features/data.md](features/data.md) |
| Registry | [features/registry.md](features/registry.md) |
| Mixin | [features/mixin.md](features/mixin.md) |
| Render | [features/render.md](features/render.md) |
| Block | [features/block.md](features/block.md) |
| Item | [features/item.md](features/item.md) |
| Network | [features/network.md](features/network.md) |
| Util | [features/util.md](features/util.md) |
| Extensions | [features/extensions.md](features/extensions.md) |
| Standalone rule | `.cursor/rules/reforestry-standalone-adopt.mdc` |
| Forge→Fabric map | `files/forge-fabric-mapping.md` |
