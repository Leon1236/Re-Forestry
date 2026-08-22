# Implementation plan — close the item gap (agent-ready)

**Source inventory:** [`item-gap-vs-ce-mc.md`](item-gap-vs-ce-mc.md)  
**Status tracker when a stage lands:** `files/implemented-features.md`  
**Audience:** implementing agents — every stage lists **all** deliverables (API, Java, assets, lang, tab, recipes, tags, client, smoke). Do not skip a checklist row unless the stage explicitly marks it N/A.

## Hard rules (every stage)

1. **Never invent** registry ids, class names, texture paths, or lang keys — verify in MCP `thedarkcolour-ForestryCE` first (`search_code` → `get_file`).
2. Namespace: CE `forestry:` → our `reforestry:`. Packages: `forestry.{mod}.*` → `com.leon1236.reforestry.{mod}.*`.
3. **API-first:** if CE exposes an `forestry.api.*` type for the feature, port that interface under `com.leon1236.reforestry.api.*` **before** the implementation. Dogfood via `IForestryPlugin` / `reforestry:plugin` when CE does.
4. Prefer **Fabric API** over Mixins; Mixin only when no hook exists; keep minimal; note the choice in the stage’s `queries/` note if non-obvious.
5. **No code comments** in production Java (project convention).
6. Textures: copy from `for textures only/thedarkcolour-ForestryCE/...` into `src/main/resources/assets/reforestry/...` — mirror CE relative paths under `assets/forestry/` → `assets/reforestry/`. Never invent art.
7. Lang: **`en_us.json` is required**. Other locale files may already contain CE strings — add/update `en_us` keys for anything new; do not delete other locales. Keys: `item.reforestry.{id}`, tooltips `item.reforestry.{id}.tooltip`, tabs `itemGroup.reforestry.{tab}`, GUIs `gui.reforestry.*` as CE.
8. After the stage: update `files/implemented-features.md`, tick the checklist at the bottom of **this** file, write a short `queries/{stage-id}.md` only if you made a Fabric-vs-CE design choice worth reusing.

## Universal Definition of Done (DoD)

A stage is **not done** until every applicable box is true:

| # | Deliverable | Notes |
| --- | --- | --- |
| D1 | **API** | Public interfaces from CE `api/` for this feature exist under `api/` and are used by impl (or stage says N/A — plain items with no CE API) |
| D2 | **Registration** | `FeatureItem` / `FeatureItemGroup` / `FeatureItemTable` / blocks+BE+menus as needed; module gated if non-core |
| D3 | **Behavior** | Item use, GUI, filters, remnants, fluids, genetics — matches CE for the scoped stage |
| D4 | **Item model** | `assets/reforestry/models/item/{id}.json` for every new item id |
| D5 | **Textures** | PNGs present (item and/or block/entity/GUI as required); copied from reference, not invented |
| D6 | **Lang (en_us)** | Display name + CE tooltips/GUI strings used by the feature |
| D7 | **Creative tab** | Items appear in the correct module tab (`CoreCreativeTabs` or new `*CreativeTabs`) in CE-like order |
| D8 | **Recipes** | CE shaped/shapeless/carpenter/smelting recipes that produce or consume these items (extract; skip only if CE has none or outputs unregistered — document skips) |
| D9 | **Tags** | CE item/block tags that include these ids (`data/reforestry/tags/...` and common `c:` where CE uses them) |
| D10 | **Client** | Screens, BER, tint, armor renderer, entity renderer, spectacles overlay — as CE requires for this stage |
| D11 | **Data components / persistence** | Inventories, charges, letter NBT/components survive save/reload |
| D12 | **Smoke test** | Stage-specific checks below pass (creative give + in-world action) |
| D13 | **Docs** | `implemented-features.md` updated; orphan “model without Java” resolved for ids this stage owns |

**Order of tracks:** A → B → W / Parallel A–B → D; C (mail) is **dropped**. E optional. Do not start B until A exit criteria met (A7/A8 may finish after B0 if blocked, but A1–A6 must be done).

---

## Track A — Core leftovers (existing `core` module)

**Package:** `com.leon1236.reforestry.core`  
**Tab:** `CoreCreativeTabs.REFORESTRY`  
**No new module.** Broken bronze remnants already registered — wire tools to them.

---

### A1 — Simple materials (`gear_iron`, `brick_ash`, `brick_wax`, `brick_refractory_wax`)

#### Research (before code)
- MCP: `CoreItems.java` — field names and constructors.
- CE recipes mentioning these ids (crafting JSON / carpenter).
- CE textures: `assets/forestry/textures/item/gear_iron.png`, `brick_ash.png`, `brick_wax.png`, `brick_refractory_wax.png` (confirm exact names).

#### API
- **N/A** — plain `Item` materials (no CE api type).

#### Implementation checklist
- [x] Register four `FeatureItem`s on `CoreItems` with **exact** CE registry path ids.
- [x] Item models + textures under `assets/reforestry/`.
- [x] `en_us`: `item.reforestry.gear_iron`, `brick_ash`, `brick_wax`, `brick_refractory_wax` (+ tooltips if CE has them).
- [x] Add all four to `CoreCreativeTabs` near existing gears / wax / ash.
- [x] Port CE recipes (likely gear from iron + tin gear pattern; bricks from ash/wax).
- [x] Tags: if CE puts `gear_iron` in gear tags (`c:gears/iron` or forestry tags), mirror them.
- [ ] Smoke: creative tab shows all four; craft at least `gear_iron` and one brick. (needs `runServer` + minecraft-world MCP)

#### Done when
DoD D2–D9, D12–D13. D1/D10/D11 N/A.

---

### A2 — Survivalist bronze tools (5)

**Ids:** `bronze_pickaxe`, `bronze_shovel`, `bronze_axe`, `bronze_sword`, `bronze_hoe`

#### Research
- CE: `CoreItems` tool registrations, `HasRemnants`, `ToolTier` / survivalist tier values.
- MCP `Minecraft-26.2`: current tool item constructors / `ToolMaterial` (do **not** copy deprecated 1.20 signatures).
- Remnant mapping: pickaxe→`broken_bronze_pickaxe`, shovel→`broken_bronze_shovel`, axe→`broken_axe`, sword→`broken_sword`, hoe→`broken_hoe`.

#### API
- Prefer a small public hook if CE exposes remnant behavior for addons; otherwise:
  - [ ] Add `api/items/IItemWithRemnants.java` (or CE’s exact name if present) **only if** CE has an equivalent API type — verify first.
  - If CE has **no** API, implement as internal class; note “no CE API” in `queries/core-A2-bronze-tools.md`.

#### Implementation checklist
- [x] Tool material / tier matching CE durability, speed, attack.
- [x] On break → give remnant stack (CE rules: creative mode, etc.).
- [x] Models (handheld transforms if CE has them) + textures (reference folder; some `bronze_*` textures may already exist).
- [x] Lang names + any tooltips.
- [x] Creative tab next to broken remnants.
- [x] Crafting recipes from CE.
- [x] Tool tags: `minecraft:pickaxes`, `shovels`, `axes`, `swords`, `hoes` / `mineable/*` as appropriate for 26.2.
- [ ] Smoke: mine/attack with each; break one tool and receive correct broken item. (needs `runServer` + minecraft-world MCP)

#### Done when
All five tools functional with remnants; DoD complete (API only if CE has one).

---

### A3 — Assembly kits (5)

**Ids:** `kit_pickaxe`, `kit_shovel`, `axe_kit`, `sword_kit`, `hoe_kit` (CE id spellings — do not “fix” asymmetry)

#### Research
- CE `ItemAssemblyKit` — use action (right-click unpack vs craft-only).

#### API
- N/A unless CE defines an interface (verify). Kits are thin wrappers around A2 tools.

#### Implementation checklist
- [x] Register five items; unpack/use yields matching A2 tool (durability full per CE).
- [x] Models/textures/lang/tab/recipes/tags from CE.
- [ ] Smoke: each kit → correct tool once; kit consumed. (needs `runServer` + minecraft-world MCP)

#### Depends
A2.

---

### A4 — Wrench + pipette

**Ids:** `wrench`, `pipette`

#### Research
- CE `ItemWrench`, `ItemPipette` — which blocks rotate; fluid fill/empty amounts; filled pipette model/state.
- Local: models/lang for wrench often already present — **still register Java** and verify texture.

#### API (required)
Port CE equivalents if present; minimum Fabric-facing surface:

- [x] `api/items/IWrenchable.java` (or CE name) — block/tile opt-in for wrench rotate **if CE uses this pattern**; else document block-class checks.
- [x] Pipette: reuse existing fluid transfer helpers; if CE has `IFluidHandlerItem`-style API, expose Fabric transfer equivalent on the item (`FluidStorage.ITEM` or project’s `FilteredFluidStorage` pattern from factory).
- [x] Do **not** leave pipette as a dead item with only a texture.

#### Implementation checklist
- [x] Register both items with durability/stack rules from CE.
- [x] Wrench: shift-use rotates allowed facings on factory/alveary/core blocks CE supports; play sound/feedback if CE does.
- [x] Pipette: fill from tank / empty into tank; filled appearance (`pipette_filled` model if CE has it — wire item property/component).
- [x] Models + textures (empty + filled).
- [x] Lang + tooltips (wrench tooltip often already in `en_us`).
- [x] Creative tab.
- [x] Recipes from CE.
- [x] Client: any special rendering CE uses for filled pipette.
- [ ] Smoke: rotate a centrifuge/alveary part; move biomass with pipette between bottler/still tank and pipette. (needs `runServer` + minecraft-world MCP)

#### Done when
Both behaviors work in-world; API/transfer surface usable by later machines.

---

### A5 — Naturalist helmet (`naturalist_helmet`)

#### Research
- CE `ItemSpectacles` — armor slot, material, client overlay for pollinated leaves + multiblock errors.

#### API (required)
- [x] Port CE spectacles/naturalist visibility hook if any (`IArmorNaturalist` / similar — **verify name in MCP**).
- [x] Client code must query the API (“is player wearing naturalist helmet?”), not hardcode the item class in unrelated modules.

#### Implementation checklist
- [x] Helmet item (armor equipment slot); repair ingredient if CE has one.
- [x] Client overlay / outline for pollinated genetic leaves and multiblock validation errors — **only** what CE ties to spectacles.
- [x] Model (armor + inventory), textures, lang, tooltip, tab, recipe.
- [x] Equipment / armor tags as CE/26.2 require.
- [ ] Smoke: wear helmet near pollinated leaf + incomplete alveary; confirm hints appear; remove helmet → hints gone. (needs `runServer` + minecraft-world MCP)

---

### A6 — Research note (`research_note`)

#### Research
- CE `ItemResearchNote` — NBT/components, use behavior, breeding tracker integration.

#### API (required)
- [x] Port CE research-note / breeding-tracker write API pieces used by the item (`IBreedingTracker` already exists in genetics — wire note to it rather than inventing parallel storage).
- [x] If discovery journal is still deferred, stub **only** the journal UI call; still apply tracker knowledge CE would apply — document stub in `queries/core-A6-research-note.md`.

#### Implementation checklist
- [x] Item + DataComponent/NBT for note contents (species/mutation knowledge per CE).
- [x] Use-in-hand consumes/applies knowledge.
- [x] Model/texture/lang (dynamic `%s's Notes` if CE uses player name — match CE).
- [x] Tab; how notes are obtained (loot/carpenter) — port CE sources that don’t need missing modules. *(CE: escritoire only — deferred; Re-Forestry: structure-chest loot with filled mutation data)*
- [ ] Smoke: give a note for a known bee mutation; use it; tracker/alyzer reflects unlock if CE would.

---

### A7 — Portable alyzer (`portable_alyzer`) — split A7a / A7b

#### Research
- CE `ItemAlyzer`, containers/screens, charge system, `forestry.api.genetics.alyzer.*`.

#### API (required — before GUI polish)
- [x] Port `api/genetics/alyzer/*` CE types used by the portable alyzer (`IAlleleDisplayHelper`, etc. — verify full set needed).
- [x] Item must analyze through genetics API (`IIndividual` / species type), not special-case only bees in a closed class forever — design for bees + trees now, butterflies in Track D.

#### A7a — Item + menu shell
- [x] Register item, charges component, open empty/skeleton GUI.
- [x] Models/textures/lang/tooltip/charges string/tab/recipe.
- [ ] Smoke: open GUI with bee in hand/slot without crash.

#### A7b — Real readout
- [x] Display chromosomes/alleles for bee + sapling genomes we already store.
- [x] Consume charges per CE.
- [x] Client screen layout matching CE (textures from reference GUI atlas).
- [ ] Smoke: analyze princess + sapling; values match genetics data; empty charge blocks analyze.

#### Done when
A7a+A7b DoD; butterflies can be “unsupported species” message until D1.

---

### A8 — Forester’s manual (`foresters_manual`)

#### Research
- CE `ForestersManualItem` + Patchouli book assets under `assets/forestry/patchouli_books/...`.

#### API
- N/A for item itself; book content is data.

#### Implementation checklist
- [x] **Decision note** `queries/core-A8-foresters-manual.md`: Patchouli on Fabric 26.2 **or** stub item. Do not invent a full custom book UI silently.
- [x] If Patchouli: depend in `fabric.mod.json` / gradle; port book datapack with namespace `reforestry`; item opens book. *(N/A — no Patchouli 26.2; book assets already present for later)*
- [x] If stub: item + chat/screen “Guide not yet ported”; still register id, model, texture, lang, tab, recipe.
- [ ] Smoke: item obtainable; opens book or clear stub.

#### Done when
Stub or Patchouli DoD; butterfly craft recipe deferred to Track D.

---

### Track A exit criteria
All 20 core gap ids registered with DoD; wrench/pipette/alyzer/spectacles have real behavior or documented stubs only where allowed (A6 journal, A8 book).

---

## Track B — Storage module (backpacks + crates)

**New module** `reforestry:storage` · package `com.leon1236.reforestry.storage`  
**CE API package to mirror:** `forestry.api.storage` → `com.leon1236.reforestry.api.storage`

---

### B0 — Module shell + API skeleton

#### API (required — first)
Port CE storage API (verify each file in MCP, then implement):

- [x] `api/storage/EnumBackpackType.java`
- [x] `api/storage/IBackpackDefinition.java`
- [x] `api/storage/IBackpackInterface.java`
- [x] `api/storage/BackpackEvents.java` — Fabric `Event` for stow/resupply (not Forge `Event` subclasses) — see `queries/storage-B0-api.md`

#### Implementation checklist
- [x] `ModuleStorage` + config flag in `ModuleConfig`.
- [x] Register module in `ReForestry` module list; depends on `core`.
- [x] Empty `StorageItems`, `StorageMenuTypes` placeholders, `StorageCreativeTabs` (chest icon until B1 miner bag).
- [x] Lang: `itemGroup.storage` (already present).
- [x] Client handler no-op registered only when module loaded.
- [ ] Smoke: game loads with storage on and off.

---

### B1 — Prototype backpack (`miner_bag`)

#### Depends
B0 API.

#### Implementation checklist
- [x] `IBackpackDefinition` instance for miner (accept predicate from CE — extract, don’t guess).
- [x] Item implements backpack via `IBackpackInterface` registration.
- [x] DataComponent inventory; slot count = CE **NORMAL**.
- [x] Menu + screen (GUI texture from reference).
- [x] Model/texture/lang/tooltip/tab/recipe.
- [x] Stow/pick-up behavior CE has (hotkey/pickup events) — port minimum: open GUI + manual move; note deferred pickup if large.
- [ ] Smoke: put cobble in bag, quit/rejoin, still there; reject a non-miner item if CE filters.

---

### B2 — Remaining normal backpacks (6)

**Ids:** `digger_bag`, `forester_bag`, `hunter_bag`, `adventurer_bag`, `builder_bag`, `brewer_bag`

#### Checklist
- [x] One definition + item each; **filters extracted from CE** (tags/item lists).
- [x] Models/textures/lang/tab/recipes for all six.
- [ ] Smoke: each opens; each rejects one documented invalid stack.

---

### B3 — Woven backpacks (7)

**Ids:** `miner_bag_woven`, `digger_bag_woven`, `forester_bag_woven`, `hunter_bag_woven`, `adventurer_bag_woven`, `builder_bag_woven`, `brewer_bag_woven`

#### Checklist
- [x] `EnumBackpackType.WOVEN` slot count; upgrade recipes (often carpenter + woven silk — verify).
- [x] Assets/lang/tab for all seven.
- [ ] Smoke: woven miner has more slots than normal; craft one woven bag end-to-end.

---

### B4 — Naturalist backpacks (3)

**Ids:** `apiarist_bag`, `arborist_bag`, `lepidopterist_bag`

#### API
- [ ] Wire to genetics species types (`ForestrySpeciesTypes` / local equivalent) via `IBackpackDefinition` — CE `naturalistBackpack(...)`.

#### Checklist
- [ ] Filters: bees only / tree germlings only / butterflies only (lepido: accept none or bees until D1 — **document**).
- [ ] Full assets/lang/tab/recipes.
- [ ] Smoke: apiarist holds queen; rejects dirt; arborist holds sapling.

---

### B5 — Crate base (`crate`)

#### API (required)
- [ ] If CE has crate API types, port them; else add minimal `api/storage/ICrate.java` / packing helper **only if** needed for plugins — prefer matching CE. Verify MCP `ItemCrated` + any api references.

#### Checklist
- [ ] Empty `crate` item; pack/unpack matching CE (crafting or use).
- [ ] Model/texture/lang/tab/recipe for empty crate.
- [ ] Smoke: crate + peat → `crated_peat` (may implement peat only in B5, rest in B6).

---

### B6 — All `crated_*` (~84)

#### Tools
- [ ] Add/extend `tools/extract_crate_items.py` (or similar) from CE `CrateItems.java` — **no hand-typed list**.

#### Checklist
- [ ] Register every CE crated id (including comb crates).
- [ ] Models: generate or copy; crated items often share crate + overlay — match CE model approach.
- [ ] Lang: `item.reforestry.crated_*` (many may exist in locale dumps — ensure `en_us` complete).
- [ ] Creative tab: empty crate + all crated (or CE submenu pattern).
- [ ] Packing recipes/interactions for each.
- [ ] Tags if CE has crate tags.
- [ ] Smoke: peat, `crated_bee_comb_honey`, `crated_oak_log`, `crated_cookie`, `crated_bronze` pack/unpack.

---

### Track B exit criteria
Storage module toggles; all backpack + crate ids from the gap doc registered with DoD; `api.storage` usable by addons for definitions.

---

## Track C — Mail module — **dropped**

Not in Re-Forestry. Do not implement C0–C4. See [`mail-dropped.md`](mail-dropped.md).

---

## Track D — Lepidopterology

**New module** `reforestry:lepidopterology`  
**CE API:** `forestry.api.lepidopterology` (+ `genetics` butterfly chromosomes) → `api/lepidopterology` + genetics alleles already patterned on bees/trees.

Butterflies need the **same completeness as early bees**: items alone are insufficient.

---

### D0 — Research spike (mandatory before D1 code)

#### Deliverable
`queries/lepido-ce-inventory.md` containing:

- Species/moth counts and id list source file
- Chromosomes (`ButterflyChromosomes`) vs what we already have in genetics
- Entity, cocoon blocks, nursery, effects (`IButterflyEffect`)
- Menu/chest/alyzer integration points
- Proposed D1–D4 file map under `com.leon1236.reforestry.lepidopterology`
- Fabric notes (entity attributes, spawn, rendering)

#### Done when
Human/agent can implement D1 without further CE archaeology for scope.

---

### D1 — Module shell + API + life-stage items

#### API (required — first)
- [ ] `ButterflyLifeStage`, `IButterfly`, `IButterflySpecies`, `IButterflySpeciesType`
- [ ] `IEntityButterfly`, `IButterflyCocoon`, `IButterflyNursery`, `IButterflyEffect`, `ILepidopteristTracker`
- [ ] `ForestryButterflySpecies`, `ForestryCocoons`, `ForestryButterflyEffects` id holders
- [ ] Plugin registration hooks (`ILepidopterologyRegistration` or CE’s plugin interface — verify)
- [ ] Register species type on genetics manager / `ForestrySpeciesTypes`

#### Checklist
- [ ] Module + config + creative tab.
- [ ] Items: `butterfly_ge`, `serum_ge`, `caterpillar_ge`, `cocoon_ge` with genome DataComponents (mirror bee item pattern).
- [ ] Models/textures/lang (tint provider if CE tints by species — client hook ready even if one placeholder species).
- [ ] Alyzer accepts butterfly items (message or readout).
- [ ] Smoke: `/give` butterfly item; no crash; component present.

---

### D2 — Species + alleles + mutations data

#### Tools
- [ ] Extract script under `tools/` from CE species definitions — no hand typing.

#### Checklist
- [ ] All CE butterfly + moth species registered.
- [ ] Mutations extracted + registered.
- [ ] Creative tab: one of each species (or spawn egg pattern CE uses).
- [ ] Lang for species names.
- [ ] Smoke: count matches CE; two species differ in alyzer.

---

### D3 — Entity, spawn, cocoons, effects

#### Checklist
- [ ] Butterfly entity + attributes + renderer + textures/layers.
- [ ] Release from item / catch to item per CE.
- [ ] Cocoon blocks + BE + models; nursery API wired.
- [ ] `IButterflyEffect` implementations CE ships (or stub list documented).
- [ ] Worldgen/spawn if CE has it (biome modifiers) — can be D3b if large.
- [ ] Smoke: release → see entity → catch; cocoon places.

---

### D4 — Breeding + integration

#### Checklist
- [ ] Mating/breeding logic using shared genetics engine.
- [ ] Lepidopterist chest / housing if CE has it (blocks+GUI+assets).
- [ ] `lepidopterist_bag` filter fully active (Track B).
- [ ] Tracker + research note interaction if applicable.
- [ ] Smoke: breed → offspring item; bag accepts butterfly.

---

### Track D exit criteria
Lepido module playable at “species + entity + basic breeding” level; API complete for addons (Extra Bees/Trees later).

---

## Track W — Worktable

**New module** `reforestry:worktable` · `com.leon1236.reforestry.worktable`  
**CE:** `forestry.worktable` — no dedicated public API package.  
**Deps:** `core` only. Can parallel Track B.

### W0 — Module shell

- [ ] `ModuleWorktable` + config; register in `ReForestry`.
- [ ] `WorktableBlocks` / `WorktableTiles` / `WorktableMenuTypes` placeholders.
- [ ] Creative tab (or put block on core tab if CE does — verify MCP).
- [ ] Client handler gate.
- [ ] Smoke: load with worktable on/off.

### W1 — Block + memorized crafting

#### Research
- CE `TileWorktable`, recipe memory, request packets — map Forge packets → Fabric networking (`queries/worktable-W1.md`).

#### Checklist
- [ ] `worktable` block + BE + menu + screen (GUI texture from reference).
- [ ] Memorize crafted recipes; recall / clear per CE.
- [ ] Model/blockstate/lang/recipe/tags.
- [ ] Smoke: craft → memory shows recipe → re-craft from memory.

### W2 — JEI / recipe transfer (optional; factory F18 categories already live)

- [ ] JEI transfer into worktable (factory categories landed in F18; worktable transfer still TODO).
- [ ] Smoke: transfer a vanilla shaped recipe into the grid.

### Track W exit criteria
Worktable playable for memorized crafting; DoD D2–D12.

---

## Track F-energy — Energy engines

**New module** `reforestry:energy` · `com.leon1236.reforestry.energy`  
**CE:** `forestry.energy` · `ModuleEnergy`  
**Deps:** `core` (fluids/items). Soft: factory consumes FE.  
**Fabric:** Team Reborn `EnergyStorage` (already used by factory/alveary).

### FE0 — Module shell + fuel API

#### API (required — first)
- [ ] Extend `api/fuels`: `EngineBronzeFuel`, `EngineCopperFuel` + `FuelManager` maps (`biogasEngineFuel`, `peatEngineFuel`, `combustionEngineFuel`, `combustionEngineCoolant`) — verify CE names.
- [ ] Seed fuels in `ModuleEnergy.setupApi` matching CE (biomass, milk, seed oil, honey, juice, ethanol, water, ice, peat, bituminous peat).

#### Checklist
- [ ] `ModuleEnergy` + config; register in `ReForestry`; depends `core`.
- [ ] Client handler placeholder.
- [ ] Note FE mapping in `queries/energy-FE0.md`.
- [ ] Smoke: module toggles; fuels registered (unit or log).

### FE1 — First engine (peat or clockwork)

#### Research
- CE engine block types, tiles, GUI, animation BER — pick **peat** or **clockwork** as first playable (verify MCP `EnergyBlocks`).

#### Checklist
- [ ] Engine block(s) + tile + menu/screen for the chosen type.
- [ ] Output FE to neighbors via Team Reborn.
- [ ] Models/textures/lang/tab/recipes.
- [ ] Smoke: fuel engine → power a centrifuge without `debug_creative_energy`.

### FE2 — Remaining engines + solar

**Ids (verify CE):** peat, biogas, combustion, clockwork, solar (+ `solar_panel` if separate).

#### Checklist
- [ ] All CE engine types + fuels/coolants wired.
- [ ] Engine upgrade circuits if CE has them on energy module.
- [ ] Assets/lang/recipes for all.
- [ ] Smoke: each engine type produces FE once.

### Track F-energy exit criteria
Factory playable without debug FE; DoD for all engine ids.

---

## Track G — Farming (+ Cultivation)

**New module** `reforestry:farming` · `com.leon1236.reforestry.farming`  
**CE API:** `forestry.api.farming` → `com.leon1236.reforestry.api.farming`  
**Deps:** `core` (multiblock, circuits); soft **energy** (gearbox FE); soft arboriculture.

### G0 — Farm API + module shell

#### API (required — first)
Port CE `api/farming` (verify each file): `IFarmHousing`, `IFarmLogic`, `IFarmable`, `IFarmType`, `IFarmInventory`, `IFarmListener`, `IFarmingManager`, `ICrop`, `IExtentCache`, `IFarmCircuit`, `IFarmableFactory`, `IWaterConsumption`, `ForestryFarmTypes`, `Soil`, `HorizontalDirection`.

#### Checklist
- [ ] `ModuleFarming` + config; register; depends `core`.
- [ ] Multiblock farm extension notes in `queries/farming-G0.md` (reuse `core.multiblock`).
- [ ] Creative tab placeholder.
- [ ] Smoke: module toggles.

### G1 — Farm multiblock structure

#### Checklist
- [ ] Farm blocks (plain/gearbox/hatch/valve/control × materials — extract counts from CE).
- [ ] `FarmController` validation + FE/gearbox.
- [ ] Basic GUI shell.
- [ ] Models/lang/recipes for structure blocks.
- [ ] Smoke: build valid farm; incomplete structure shows spectacle errors if CE does.

### G2 — Farm logics (batch)

#### Checklist
- [ ] Port CE logics: crops, gourd, shroom, infernal, poales, succulent, ender, arboreal, peat, orchard, cocoa (extract; don’t invent).
- [ ] Farm circuits / control wiring.
- [ ] Smoke: at least crops + arboreal plant/harvest once.

### G3 — Cultivation module

**New module** `reforestry:cultivation` · depends **farming**.

#### Checklist
- [ ] `ModuleCultivation`; planters (managed + manual) reusing farm logics — CE block ids.
- [ ] Assets/lang/recipes.
- [ ] Smoke: one managed planter farms wheat end-to-end.

### Track G exit criteria
Multiblock farm + at least one cultivation planter playable; `api.farming` dogfooded.

---

## Track S — Sorting (genetic filter)

**New module** `reforestry:sorting` · `com.leon1236.reforestry.sorting`  
**CE API:** `forestry.api.genetics.filter` (no `api.sorting`)  
**Deps:** soft genetics (bees/trees enough).

### S0 — Filter API + module shell

#### API (required — first)
- [ ] Port `api/genetics/filter` CE types (`IFilterLogic`, `IFilterRule`, `IFilterManager`, … — verify MCP list).
- [ ] Fabric capability/API attachment for filter logic on the block (document vs Forge caps in `queries/sorting-S0.md`).

#### Checklist
- [ ] `ModuleSorting` + config; register; depends `core`.
- [ ] Client handler gate.
- [ ] Smoke: module toggles.

### S1 — Genetic filter block

#### Checklist
- [ ] `genetic_filter` block + BE + menu + screen + packets (Fabric networking).
- [ ] Rules UI for allele/species (bees + trees).
- [ ] Models/lang/recipe/tags.
- [ ] Smoke: filter accepts queen of species A, rejects dirt / wrong species.

### S2 — Butterfly rules (after Track D)

- [ ] Wire butterfly filter rules when lepido exists.
- [ ] Smoke: butterfly item passes/fails per rule.

### Track S exit criteria
Genetic filter sorts bees/trees; API usable by addons.

---

## Track E — Polish / later

### E1 — Fruit id alignment
- Document intentional `fruit_*` prefix **or** add CE aliases with migration note.
- Update gap doc; recipes/tags that assume CE bare ids.
- DoD: no silent world break.

### E2 — Orphan asset cleanup
- After B/D register real items, delete or stop shipping `models/item` without Java registration for those modules.
- Re-run gap mental model: models ⇔ registry.

### E3 — ForestryMC-only items
Separate mini-plan each: `habitat_locator`, `imprinter`, `wax_cast`, minecart houses, camouflage, `habitat_screen`, `infuser`.  
Each needs its own API check (often **none** in CE), assets from `ForestryMC-ForestryMC`, and an explicit “restore” decision — **not** CE parity.

---

## Suggested execution order

```
A1 → A2 → A3 → A4 → A5 → A6 → A7a → A7b → A8   (code done; smoke pending)
B0 (API!) → B1 → B2 → B3 → B4 → B5 → B6
W0 → W1 (→ W2 with JEI)          # parallel OK
FE0 → FE1 → FE2
G0 → G1 → G2 → G3
S0 → S1 (→ S2 after D)
Genetics P0 → D0 → D1 (API!) → D2 → D3 → D4
E1 / E2 as needed; E3 last
```

Mail (old Track C) is **dropped** — [`mail-dropped.md`](mail-dropped.md).

**Next session:** Escritoire (remaining Parallel B) / Energy engines FE0 / Worktable W2 JEI.  
Full module matrix: [`queries/module-completeness-audit.md`](module-completeness-audit.md).

---

## Progress checklist

Tick only when **Universal DoD** for that stage is satisfied and `implemented-features.md` is updated.

### Track A
- [ ] A1 materials (code done; smoke pending — needs `runServer` + minecraft-world MCP)
- [ ] A2 bronze tools (code done; smoke pending)
- [ ] A3 kits (code done; smoke pending)
- [ ] A4 wrench + pipette (+ API/transfer) (code done; smoke pending)
- [ ] A5 naturalist helmet (+ API) (code done; smoke pending)
- [ ] A6 research note (+ tracker wiring) (code done; smoke pending)
- [ ] A7a alyzer shell (code done; smoke pending)
- [ ] A7b alyzer readout (+ alyzer API) (code done; smoke pending)
- [ ] A8 foresters manual (stub code done; smoke pending; Patchouli when 26.2 exists)

### Track B
- [x] B0 storage module + `api.storage` (code; smoke pending — toggle module on/off)
- [x] B1 miner_bag prototype (code; smoke pending)
- [x] B2 normal bags (code; smoke pending)
- [x] B3 woven bags (code; smoke pending)
- [x] B4 naturalist bags (code; recipes wait on naturalist chests; smoke pending)
- [x] B5 crate (code; smoke pending)
- [x] B6 crated_* bulk (scripted; smoke pending)

### Track C — Mail (**dropped**)
- [x] Out of scope — do not implement (`queries/mail-dropped.md`)

### Track D
- [ ] D0 research note written
- [ ] D1 module + lepido API + 4 items
- [ ] D2 species/mutations extracted
- [ ] D3 entity/cocoons/effects
- [ ] D4 breeding + bag/chest integration

### Track E
- [ ] E1 fruit ids
- [ ] E2 orphan assets
- [ ] E3 MC-only (optional)

### Track W — Worktable
- [x] W0 module shell
- [x] W1 block + memorized crafting (code; smoke pending)
- [ ] W2 JEI transfer (optional)

### Track F-energy
- [ ] FE0 module + fuel API
- [ ] FE1 first engine
- [ ] FE2 remaining engines + solar

### Track G — Farming / Cultivation
- [ ] G0 farm API + module shell
- [ ] G1 farm multiblock
- [ ] G2 farm logics
- [ ] G3 cultivation planters

### Track S — Sorting
- [ ] S0 filter API + module shell
- [ ] S1 genetic_filter block
- [ ] S2 butterfly rules (after D)
