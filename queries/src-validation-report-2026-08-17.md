# Re-Forestry `src/` validation report

**Date:** 2026-08-17  
**Scope:** all of `src/` (726 Java files + datapack/assets)  
**Compile:** `./gradlew compileJava --rerun-tasks` → **BUILD SUCCESSFUL** (deprecated-API note, no errors)  
**Live world:** not run (minecraft-world MCP not connected this session)

Canonical older tracker: [`files/implemented-features.md`](../files/implemented-features.md) (last updated **2026-07-29**). This report is a fresh count from registration code, not a copy of that file.

---

## Verdict

The mod **loads five modules** and **compiles**. Bees, trees, and ten factory machines are implemented. Storage is a loaded empty shell. The biggest *code vs docs* miss: **smelter alloy recipes** — F6 claimed 10 extracted CE recipes; only **2 JSON files** are on disk.

---

## Stack

| Piece | Value |
|---|---|
| Minecraft | 26.2 |
| Fabric Loader | 0.19.3 |
| Loom | 1.17 |
| Fabric API | 0.155.2+26.2 (`gradle.properties`; local bump to 0.156.0 may be uncommitted) |
| Energy | Team Reborn 5.0.0 |
| JEI | 30.14.0.94 (soft suggest, not a hard depend) |
| Java | 25 |
| Mod id | `reforestry` |
| Entrypoints | `ReForestry`, `ReForestryClient`, datagen, `reforestry:plugin`, two JEI plugins |

Loaded in `ReForestry.onInitialize()`: **core, apiculture, arboriculture, factory, storage**.

---

## Counts

| Kind | Count | How counted |
|---|---:|---|
| Java files | 726 | `find src/main/java -name '*.java'` |
| Block registry ids | **1,431** | 1,421 from `*Blocks.java` + 10 fluid blocks |
| Dedicated items (not block-items) | **239** | Core 102 + Apiculture 46 + Arboriculture 91 |
| Factory machines | 10 | `FactoryBlocks` |
| Recipe JSON | 1,451 | `data/reforestry/recipe/` |
| Loot tables | 1,405 | mostly wood |
| Blockstates / models / textures | 1,474 / 3,368 / 1,667 | assets |
| Lang files | 11 | en, de, et, fr, ja, pl, pt_br, ru, uk, zh_cn, zh_tw |
| Mixins | 4 | 1 empty template + 3 client |

Java by package: core 211, arboriculture 158, api 144, apiculture 111, factory 70, modules 18, storage 7, mixin 4.

---

## Modules

| Module | Status | CE parity (this audit) |
|---|---|---|
| core | Partial — Track A items + fluids + circuits; no desk analyzer / chests | ~70% |
| apiculture | Playable — alveary complete; 17 dummy effects; jubilance missing | ~80% |
| arboriculture | Phase 5 done; 11.9c pollen + villager deferred | ~95% |
| factory | 10 machines; **smelter alloys incomplete** | ~90% (docs said 96%) |
| storage | B0 API + empty tab; backpacks throw | ~5% |
| mail, energy, farming, cultivation, sorting, worktable, lepidopterology, addons | Mail dropped (see `queries/mail-dropped.md`); others not in `src/` | 0% except mail = n/a |

---

## Machines

### Factory (all 10 registered)

| Block id | Tile | GUI | Energy cap / recv | Recipes | Notes |
|---|---|---|---|---|---|
| `reforestry:centrifuge` | `TileCentrifuge` | yes | 40k / 800 | **18** | sockets; docs said 17 |
| `reforestry:smelter` | `TileSmelter` | yes | 40k / 1100 | **2** | **docs said 10 alloys** |
| `reforestry:still` | `TileStill` | yes | 80k / 1100 | 1 | biomass → ethanol 10:3 mB |
| `reforestry:squeezer` | `TileSqueezer` | yes | 40k / 1100 | 23 + 3 container | sockets |
| `reforestry:bottler` | `TileBottler` | yes | 40k / 1100 | runtime | no RecipeType |
| `reforestry:carpenter` | `TileCarpenter` | yes | 40k / 1100 | **32** | docs said 16 |
| `reforestry:fermenter` | `TileFermenter` | yes | **100k / 8000** | 19 | docs said 80k / 2000; 4200 FE/cycle |
| `reforestry:fabricator` | `TileFabricator` | yes | 3.3k / 1100 | 265 + 4 smelting | molten tank 8000 mB |
| `reforestry:moistener` | `TileMoistener` | yes | none | 4 | water + light |
| `reforestry:rainmaker` | `TileMillRainmaker` | **none** | none | fuel map | iodine / dissipation |

Recipe types: centrifuge, smelter, still, squeezer, squeezer_container, carpenter, fermenter, fabricator, fabricator_smelting, moistener, hygroregulator.

### Apiculture housing

| Block id | Tile | GUI |
|---|---|---|
| `apiary` | `TileBeeHousing` (frames) | `bee_housing` |
| `bee_house` | `TileBeeHousing` | `bee_housing` |
| `alveary_plain` | `TileAlvearyPlain` + `AlvearyController` | `alveary` |
| `alveary_fan` / `alveary_heater` | climatiser tiles, FE | alveary GUI |
| `alveary_hygro` | `TileAlvearyHygroregulator` | own GUI; 3 recipes |
| `alveary_sieve` | `TileAlvearySieve` | own GUI |
| `alveary_swarmer` | `TileAlvearySwarmer` | own GUI |
| `alveary_stabiliser` | `TileAlvearyStabiliser` | alveary GUI (no mutations) |
| `beehive_*` (12) | `TileHive` | none |

**Loot gap:** factory machines and alveary parts have `loot_table/blocks/*.json`. **`apiary` and `bee_house` do not.** Hives use custom `getDrops` (scoop).

### Core “machines”

- `debug_creative_energy` / `debug_powered` — FE test fixtures, no GUI
- `portable_alyzer` — item GUI (`alyzer`)
- soldering iron — item GUI
- **Missing vs CE:** block analyzer, escritoire

---

## Blocks (1,431)

### Core — 14 content + 10 fluids = 24

`bog_earth`, `peat` (no BlockItem), `humus`, `resource_storage_{apatite,tin,bronze,amber}`, `apatite_ore`, `deepslate_apatite_ore`, `tin_ore`, `deepslate_tin_ore`, `raw_tin_block`, `debug_creative_energy`, `debug_powered`.

Fluid blocks: `fluid_{bio_ethanol,biomass,glass,honey,ice,juice,milk,seed_oil,short_mead,wax}`.

### Apiculture — 38

- Housing: `apiary`, `bee_house`
- Hives (`beehive_<type>`): forest, meadows, desert, jungle, end, snow, swamp, savanna, lush, aquatic, nether, swarm
- Alveary (`alveary_<type>`): plain, swarmer, fan, heater, hygro, stabiliser, sieve
- Comb blocks (`block_bee_comb_<type>`): honey, cocoa, simmering, stringy, frozen, dripping, silky, parched, mysterious, powdery, wheaten, mossy, mellow, kaolin, vintage, sponge, sculken

### Factory — 10

See machines table.

### Arboriculture — 1,359

- Charcoal: `charcoal`, `log_pile`, `decorative_log_pile`, `ash_block`
- Genetic (no BlockItem): `sapling_ge`, `leaves`
- Pods: `pods_cocoa`, `pods_dates`, `pods_papaya`, `pods_coconut`
- Leaves: 50 species × (`_default_leaves`, `_default_leaves_fruit`, `_decorative_leaves`) = 150
- Vanilla fireproof: 9 woods × 9 kinds = 81 (`oak` … `pale_oak` × fireproof log/wood/planks/slab/fence/gate/stairs)
- Forestry wood: **43 × 26 = 1,118**

**43 wood types:** larch, teak, acacia_desert, lime, chestnut, wenge, baobab, sequoia, kapok, ebony, elm, mahogany, balsa, willow, walnut, greenheart, hill_cherry, mahoe, poplar, palm, papaya, pine, plum, maple, citrus, giganteum, ipe, padauk, cocobolo, fir, coconut, beech, feijoa, dogwood, ginkgo, jacaranda, pewen, macrocarpa, olive, orange, pear, kauri, zebrawood

**26 kinds:** log, stripped_log, wood, stripped_wood, planks, slab, fence, fence_gate, stairs, door, trapdoor, button, pressure_plate, sign, wall_sign, hanging_sign, wall_hanging_sign, plus fireproof variants of the first nine (no fireproof door/sign/button).

Wall signs / wall hanging signs have **no item** (placed by the standing/hanging item).

### Storage — 0 blocks

---

## Items (239 dedicated)

Species are **data components**, not extra registry ids.

- Bees: 4 items (`bee_queen_ge`, `bee_drone_ge`, `bee_princess_ge`, `bee_larvae_ge`) × 69 species in the creative tab
- Trees: 2 items (`sapling`, `pollen_fertile`) × 50 species
- Boats: 86 real items (43 + 43)

### Core (102)

Manual stub, beeswax, apatite, raw tin, amber, tin/bronze ingots, 4 gears, 4 casings, carton, 5 bronze tools + 5 remnants + 5 kits, moistener wheat chain, iodine/dissipation, waxes, ash, 3 bricks, peat/bituminous peat, 3 fertilizers, soldering iron, wrench, pipette, naturalist helmet, research note, portable alyzer.

Groups: 9 crafting materials, 12 fruits (`fruit_*`), 4 circuit boards, 13 electron tubes, 3 empty fluid containers, 10 buckets.

### Apiculture (46)

Honey drop/dew, royal jelly, experience_drop (plain), amber_drone (plain), 3 foods, 4 germlings, 4 frames, 4 apiarist armor, scoop + proven, smoker, 2 pollen clusters, 17 combs, 4 propolis.

### Arboriculture (91)

Sapling, pollen_fertile, grafter + proven, amber_sapling (plain), 86 boats.

### Factory / Storage

No dedicated items. Storage `createBackpack` / `createNaturalistBackpack` throw `UnsupportedOperationException`.

### 69 bee species

bee_forest, meadows, common, cultivated, noble, majestic, imperial, diligent, unweary, industrious, sinister, fiendish, demonic, modest, frugal, austere, tropical, exotic, edenic, wintry, icy, glacial, marshy, miry, boggy, savanna, agrarian, rural, farmerly, ended, spectral, phantasmal, heroic, valiant, steadfast, monastic, secluded, hermitic, merry, tipsy, leporine, tricky, anachrone, relic, primeval, abyssal, sculk, shulking, argil, pride, patriotic, vanilla, lush, verdant, luxuriant, aquatic, pirate, prismatic, autotrophic, kleptoplastic, photosynthetic, warped, embittered, spiteful, seething, zombified, vindictive, vengeful, avenging.

### 50 tree species

tree_oak, birch, lime, hill_cherry, walnut, chestnut, pear, plum, feijoa, larch, teak, desert_acacia, wenge, baobab, sequoia, kapok, ebony, mahogany, balsa, willow, sipiri, mahoe, poplar, palm, date, papaya, pine, maple, lemon, ipe, padauk, cocobolo, zebrawood, dogwood, ginkgo, jacaranda, pewen, macrocarpa, olive, orange, kauri, beech, fir, giant_sequoia, coconut, elm, spruce, acacia, jungle, dark_oak, cherry.

---

## Genetics, worldgen, other systems

| System | Status |
|---|---|
| Bee karyotype `reforestry:bees` | 13 chromosomes; 69 species; **114** mutations |
| Tree karyotype `reforestry:trees` | 10 chromosomes; 50 species; **40** mutations |
| Live bee effects (11) | aggressive, heroic, beatific, miasmic, misanthrope, glacial, exploration, snowing, drunkard, hakuna_matata, darkness |
| Dummy bee effects (17) | radioactive, creeper, ignition, easter, reanimation, resurrection, repulsion, fertile, mycophilic, sifter, glow_berry_grow, rejuvenation, chronophage, guardian, phasing, ascension, sculk |
| Jubilance | **not present** (`IBeeJubilance` grep empty) |
| Breeding tracker | **wired** on bee mate (`BeekeepingLogic`) and leaf pollen (`TileLeaves`) — 29 Jul “Next up” is stale |
| Worldgen | `HiveDecorator` + `TreeDecorator` via Fabric biome mods; `custom_tree` feature |
| Fluids | 10 Forestry fluids + buckets + can/capsule/refractory |
| Circuits | boards + tubes + soldering iron; sockets on centrifuge/squeezer/smelter |
| JEI | Factory 10 categories + core item descriptions; no bee/tree mutation JEI |
| Networking | no custom payloads; vanilla BE + ContainerData |
| Commands | `/reforestry tree spawnTree\|spawnForest` |
| Entities | `boat`, `chest_boat` (no bee entities) |
| Mixins | `ExampleMixin` empty; 3 client mixins for tints/models |

---

## Validation findings

1. **Compile OK.** Forced `compileJava` succeeded. Deprecation note only.
2. **Smelter recipes missing.** `queries/factory-F6.md` listed 10 alloys (bronze/brass/constantan/electrum/invar × ingots/raw). Disk has only `bronze_from_ingots.json` and `bronze_from_raw_materials.json`.
3. **Apiary / bee house have no loot tables.** Breaking them likely drops nothing.
4. **Dummy effects are 17, not 16** (easter is dummy; 29 Jul list omitted it).
5. **Storage loads but is empty.** Module audit header that said “four modules only” is outdated.
6. **Breeding tracker is implemented** (contradicts 29 Jul Next-up; matches 30 Jul alveary validation).
7. **Docs drift:** carpenter 32 vs 16; centrifuge 18 vs 17; fermenter energy 100k/8000 vs 80k/2000.
8. **Leftovers:** empty `ExampleMixin`; `fabric.mod.json` still has the example-mod description/author.
9. **No `TODO`/`FIXME` in Java.** Stubs are explicit (`ForestersManualItem`, `DummyBeeEffect`, storage throws).
10. **No in-game smoke this session.**

---

## Not implemented (unchanged from 29 Jul roadmap)

Storage backpacks/crates; naturalist chests; desk analyzer + escritoire; remaining bee effects + jubilance; energy engines; worktable; farming/cultivation; sorting; butterflies; Gendustry / Extra Bees / Extra Trees; arborist villager; vanilla-leaf pollen (11.9c); Patchouli manual. (Mail was on this list in Jul; it is now out of scope — `queries/mail-dropped.md`.)

---

## Prior reports read

- `files/implemented-features.md` (2026-07-29) — canonical done-list
- `queries/module-completeness-audit.md` (2026-07-29)
- `queries/item-gap-implementation-plan.md`, `item-gap-vs-ce-mc.md` (Track A stale)
- `queries/factory-F6.md` … `F18.md`, `factory-play-loop.md`
- `queries/alveary-cross-mod-validation.md` (2026-07-30) — breeding tracker already claimed fixed
- `queries/storage-B0-api.md`
- `Reports/` — **reference-mod inventories** (CE, IF, JEI, GeckoLib, …), not Re-Forestry progress
- Agent transcripts: merge from old PC + `src/` commit; no earlier full src audit

**Authority for “done” after this pass:** this file for counts; `implemented-features.md` still for roadmap IDs, but update smelter/storage/tracker notes when that tracker is edited.
