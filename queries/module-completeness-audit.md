# Re-Forestry module completeness audit

**Superseded for remaining work:** use [`queries/remaining-work-stages.md`](remaining-work-stages.md) (2026-08-18). This file is kept for history; several rows below are stale (worktable, chests, bee effects, DummyBeeEffect, engine list).

**Date:** 2026-07-29  
**Primary parity target:** Forestry CE (`thedarkcolour-ForestryCE` / `ForestryModuleIds`)  
**Secondary:** ForestryMC 1.12 (`ForestryMC-ForestryMC`) — restore-later only  
**Status tracker:** [`files/implemented-features.md`](../files/implemented-features.md)  
**Item tracks:** [`queries/item-gap-implementation-plan.md`](item-gap-implementation-plan.md)  
**Sources:** graphify (`graphify-out/`), MCP CE + ForestryMC, parallel module audits

**Loaded today** ([`ReForestry.java`](../src/main/java/com/leon1236/reforestry/ReForestry.java)): `core`, `apiculture`, `arboriculture`, `factory` only.

---

## Snapshot matrix

| Module | CE id | Local status | Rough CE parity |
|--------|-------|--------------|-----------------|
| **core** | `core` (+ fluids id) | **Partial** — Track A items done; desk systems missing | ~70% |
| **fluids** | `fluids` | **Done in core** | ~100% (placement differs only as module id) |
| **apiculture** | `apiculture` | **Mostly done** — play loop + alveary; effects/jubilance/chests gaps | ~80% |
| **arboriculture** | `arboriculture` | **Done (Phase 5)** — polish deferred | ~95% |
| **charcoal** | `charcoal` | **Done inside arboriculture** | ~100% |
| **factory** | `factory` | **Done (Phase 6)** — 10 machines; F18 JEI (incl. Bottler + Rainmaker); fabricator recipes; GUI/data polish (hints/loot/pickaxe/JEI descriptions) | ~96% |
| **storage** | `storage` | **Done (Phase 7)** — B0–B6 backpacks + crates | ~95% |
| **mail** | `mail` | **Dropped** — not in this mod | — |
| **lepidopterology** | `lepidopterology` | **Not started** (Track D; `BUTTERFLY` id reserved) | 0% |
| **energy** | `energy` | **Not started** (debug FE only in core) | 0% |
| **farming** | `farming` | **Not started** | 0% |
| **cultivation** | `cultivation` | **Not started** (depends farming) | 0% |
| **sorting** | `sorting` | **Not started** | 0% |
| **worktable** | `worktable` | **Not started** | 0% |
| **curios** | `curios` | **Not started** (optional; spectacles already helmet) | 0% |
| **gendustry / extra_bees / extra_trees** | (addons) | **Not started** — [`files/addon-integration-mapping.md`](../files/addon-integration-mapping.md) | 0% |

---

## Implemented modules — remaining work

### Core — partial

**Done:** registration/modules; climate; multiblock framework; genetics engine; circuits; fluids+containers; Track A items (materials, bronze tools/kits, wrench/pipette, spectacles, research note, portable alyzer, manual stub).

| Priority | Gap | Notes |
|----------|-----|-------|
| P0 | Naturalist chests (`bee_chest` / `tree_chest` / `butterfly_chest`) | Unlocks discovery GUI + villagers (11.9h) |
| P0 | Desk `analyzer` + `escritoire` | Portable alyzer ≠ desk; escritoire produces research notes |
| P1 | Alyzer pages 3–4 real produce/mutations | Tracker wiring |
| P1 | Breeding tracker sync + register on mating | Apiculture also needs this |
| P2 | Decorative brick/waxstone/candle families | CE building set |
| P2 | JEI core plugin; Patchouli when 26.2 exists | A8 stub until then |

Fluids live under `core.fluids` (matches CE Java placement; separate CE module id is optional later).

### Apiculture — ~80% playable

**Done:** 69 species, 114 mutations, apiary/bee house, work gating, tools/frames/armor, wild hives+worldgen, alveary all 7 parts, ~11 live bee effects. Multiblock shared inventory survives Fabric chunk unload (`queries/multiblock-chunk-unload-save.md`, 2026-07-29).

| Priority | Gap |
|----------|-----|
| P0 | Replace **16** `DummyBeeEffect`s (radioactive, creeper, ignition, reanimation, resurrection, repulsion, fertile, mycophilic, sifter, glow_berry_grow, rejuvenation/chronophage, guardian, phasing, ascension, sculk) |
| P0 | Jubilance + specialty product drops (`IBeeJubilance`) |
| P0 | Wire breeding tracker on mate/analyze (mutation discovery counts) |
| P1 | `wax_block` / `wax_block_refractory`; `bee_chest` (with core) |
| P1 | Escritoire path for research notes (core) |
| P2 | Beekeeper villager; bee admin commands; discovery journal UI; JEI |

Older [`queries/apiculture-gap-vs-ce.md`](apiculture-gap-vs-ce.md) is **stale** (alveary/hives done) — use this audit instead.

### Arboriculture — Phase 5 done (~95%)

**Done:** 50 species, woods, growth/features, worldgen, pods, charcoal, boats, grafter loot, commands, genetic-leaf pollen.

**Deferred polish:**

- **11.9c** — pollen on vanilla/default/decorative leaves
- **11.9h** — arborist villager (blocked on `tree_chest`)
- JEI charcoal; live play-smoke of plant→grow→pollen

### Factory — Phase 6 done (~96%)

**Done:** All 10 machines, FE consume, fluids, sockets, containers, hygroregulator recipes, play loop docs, **F18 JEI** (10 categories incl. Bottler dynamic fill/empty + Rainmaker; catalysts + GUI click areas + item descriptions), **~265 fabricator crafts** (tubes + fireproof wood), recipes ledger + ghost pattern fill. Bottler polish 2026-07-29: power/hint ledgers, fill-arrow sync, JEI recipes — see [`factory-F9.md`](factory-F9.md). Factory-wide GUI/data polish 2026-07-29 (hints, loot, pickaxe, JEI descriptions) — see [`factory-machine-gui-data-polish.md`](factory-machine-gui-data-polish.md).

| Priority | Gap |
|----------|-----|
| P1 | Carpenter/fabricator JEI **recipe transfer** packets (optional) |
| P1 | Carpenter recipes gated on escritoire (land with that block); storage crate recipes already in B5–B6 |
| P2 | Visual polish; hopper smoke; filled-container tint; smelter block texture |
| P2 | Confirm smelter alloy datapack count vs CE (~10) if any still missing |
| P3 | Factory recipe unlock advancements (optional; pickaxe tag done) |

**Dependency:** factory consumes FE but cannot generate it without **ModuleEnergy** or external power / debug blocks.

---

## Not-yet-ported CE modules

Ordered by recommended implementation sequence.

### 1. Storage — Track B (done)

- **CE:** `forestry.storage` — backpacks (normal/woven/naturalist) + `crated_*` bulk
- **Local:** B0–B6 landed; see `files/implemented-features.md` Phase 7
- **Remaining:** naturalist bag crafts blocked on chests; crate overlay model is a shared filled texture

### 2. Worktable — Track W

- **CE:** one `worktable` block + memorized recipes + packets (+ JEI transfer)
- **Deps:** core only
- **Size:** small–medium
- **Plan:** Track W in item-gap plan

### 3. Energy — Track F (FE generation)

- **CE:** peat / biogas / combustion / clockwork / solar engines + solar panel; `EngineBronzeFuel` / `EngineCopperFuel` in `FuelManager`
- **Fabric:** Team Reborn `EnergyStorage` (already used by factory/alveary)
- **Size:** medium (~37 Java files)
- **Must precede** farming gearbox play loop

### 4. Farming — Track G

- **CE:** farm blocks × materials; `FarmController`; crop logics; farm circuits
- **API first:** entire `api/farming`; extend multiblock for farm shape
- **Size:** large

### 5. Cultivation — after farming

- **CE:** managed/manual planters reusing farm logics
- **Deps:** hard depend on farming in CE
- **Size:** medium

### 6. Sorting — Track S

- **CE:** `genetic_filter` block + allele/species rules GUI
- **API first:** `api/genetics/filter`
- **Size:** medium

### 7. Lepidopterology — Track D (after genetics P0)

- **CE:** ~31 butterflies + moths; entities; cocoons; nursery; mating; leaf spawn
- **Blocker:** shared species-type / plugin genetics façade — do **not** fork a third closed registry without closing genetics API P0
- **Deps:** arboriculture (done); soft storage bag + sorting filters

### 8. Curios — optional last

- **CE:** spectacles in Curios head slot
- **Fabric:** Trinkets or skip — helmet spectacles already work (A5)

### Dropped — Mail (old Track C)

CE has mailbox / trade station / stamps / letters / catalogue. **Re-Forestry will not port it.** See [`mail-dropped.md`](mail-dropped.md).

---

## Genetics / plugin API (cross-cutting blocker)

Bees+trees **engine works**; public API is thinner than CE.

**P0 before butterflies / Extra Bees–style addons:**

1. Species identity surface (`ISpecies` / `IIndividual` / `ISpeciesType` / `IGeneticManager`) **or** documented Fabric equivalent plugins can register
2. `IForestryPlugin.registerGenetics` (+ life stages, pluggable `IndividualItems`)
3. Mutation temp/humidity conditions; `IMutationManager`
4. Then `api/lepidopterology` + `registerLepidopterology`

**P1:** pollen manager, filter API, type-specific trackers, promote bee/tree species types into `api/`

Local `IForestryPlugin` today: `registerApiculture` / `registerArboriculture` / `registerCircuits` only.

---

## CE-dropped ForestryMC (restore-later — Track E / Phase 12+)

| Module | Content | Notes |
|--------|---------|-------|
| greenhouse | Multiblock + climatisers | Large |
| climatology | Habitat Former / Habitat Screen | Partial climate ideas already in `api.climate` |
| database | Species database machine | Needs naturalist chests |
| food | Dedicated module | Foods mostly in apiculture already |
| book | Custom guide | A8 stub / Patchouli later |
| research | Folded into CE genetics | — |

Also Track E candidates: habitat_locator, imprinter, wax_cast, minecarts, camouflage (see item-gap Track E).

Detail: [`queries/forestryMC-vs-forestryCE-content.md`](forestryMC-vs-forestryCE-content.md).

---

## Addon modules (after base CE modules mature)

From [`files/addon-integration-mapping.md`](../files/addon-integration-mapping.md):

| Addon | Approach | Prerequisite |
|-------|----------|--------------|
| **Gendustry** | Near-mechanical port from `thedarkcolour-gendustry` (10 machines) | Genetics plugin API P0 + factory/energy patterns |
| **Extra Bees** | Extract data from Binnie 1.12; reimplement CE-style | Apiculture complete + genetics P0 |
| **Extra Trees** | Extract data; reimplement; ~40 woods × kinds | Arboriculture + genetics P0; designer system deferred |

Ids under one namespace: `reforestry:gendustry`, `reforestry:extra_bees`, `reforestry:extra_trees`.

---

## Recommended roadmap

| Phase | Work | Exit criteria |
|-------|------|---------------|
| **Now** | Storage B0–B6 **done** | Backpacks + crates playable |
| **Parallel A** | Apiculture P0 (effects + jubilance + tracker wire) | No DummyBeeEffect for CE-real effects; specialties drop |
| **Parallel B** | Core chests + escritoire/analyzer | Discovery path + research notes CE-like |
| **W** | Worktable anytime after core | Memorized crafting |
| **Power** | ModuleEnergy | Engines feed factory without debug block |
| **Farms** | Farming → Cultivation | Multiblock farm + planters |
| **Filter** | Sorting | Genetic filter works on bees/trees |
| **Genetics P0** | Species-type API + plugin hooks | Third species type registerable |
| **D** | Lepidopterology | Butterflies spawn/breed/alyze |
| **Polish** | 11.9c/h; bees/trees JEI; factory recipe-transfer packets | UX parity |
| **Addons** | Gendustry → Extra Bees → Extra Trees | Config-toggle modules |
| **Restore** | Greenhouse/climatology/database | Optional 1.12 nostalgia |
