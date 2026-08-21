# Re-Forestry — implemented features

What is **done and working** in the mod today.  
Update this file when a roadmap step lands. For how-to-build guidance, see `CLAUDE.md`. For the next work items, see **Next up** at the bottom.

Last updated: 2026-08-21 (EB2c: 27 Extra Bees metal/mineral/gem/nuclear + precious; 45 EB muts; cumulative 83 species / 101 EB muts + 34 FR. Next: EB2d)

---

## Phase 1 — Project shell

| ID | Feature | Notes |
|---|---|---|
| 1 | Project setup | Gradle / Loom / Fabric 26.2 stack; mod loads in-game |
| 2 | Mod entry point | `ReForestry` + client entrypoint; startup log message |

---

## Phase 2 — Core framework

| ID | Feature | Notes |
|---|---|---|
| 3 | Registration framework | `FeatureBlockGroup` etc. — multiply constructors across enums |
| 4 | Module on/off system | Config-gated modules; client handlers only run for loaded modules |
| CORE-E2 | Breeding tracker client sync | First Fabric play payload (`reforestry:genome_tracker_update`). Login + dimension change dump BEE/TREE/BUTTERFLY; incremental merge on discover/research. Client cache from `ClientBreedingHandler`, not a throwaway tracker. See `queries/core-e2-tracker-sync.md` |
| CORE-E3 | Alyzer product + mutation pages | Portable alyzer page 3 lists products/specialties (bee species + tree fruit allele). Page 4 mutation grid uses CORE-E2 tracker (`isDiscovered` / `isResearched`), drone/sapling icons, `?` + chance arrows from `portablealyzer.png`. See `queries/core-e3-alyzer-pages.md` |
| A-DISC1 | Apiarist tracker counts in GUI | Bee chest / apiarist bag idle header shows species found + queen/princess/drone counts from `IApiaristTracker`. `ApiaristTracker` persists CE NBT keys `QueensTotal` / `PrincessesTotal` / `DronesTotal` and syncs them on `reforestry:genome_tracker_update`. Hover still shows species name. See `queries/a-disc1.md` |
| A-DISC2 | Mutation discovery icons | Hovering an analyzed bee/tree in bee chest / apiarist bag / tree chest / arborist bag shows partner mutation icons (or chance-tier `?` from `apiaristinventory.png`). Shared `NaturalistSpeciesHover` on both screens. Idle header still `NaturalistBreedingStatistics`. No journal book. See `queries/a-disc2.md` |
| CORE-E4 | Chest + analyzer BER | Naturalist chests (`bee_chest` / `tree_chest` / `butterfly_chest`) animate lids; analyzer BER shows pedestal/towers + `SLOT_ANALYZE` specimen. Lid count via BE `PlayersUsing` update tag; specimen via `saveCustomOnly`. Item models use special TESR (not ForestryBewlr). See `queries/core-e4.md` |
| CORE-E1a | Escritoire block + empty GUI | `reforestry:escritoire` desk: BE + 12-slot inventory + menu/screen. Carpenter (500 mB seed_oil, planks). World/item BER: `CORE-E5`. See `queries/core-e1a.md` |
| CORE-E1b | Escritoire memory game | Token match game on bee/tree specimens; probe button; complexity via `GeneticsUtil.getResearchComplexity` (giant sequoia = 10). Menu buttons + `reforestry:escritoire_game_sync`. See `queries/core-e1b.md`, `queries/core-escritoire.md` |
| CORE-E1c | Escritoire research bounty | SUCCESS fills result slots: mutation `research_note` (chance `bountyLevel/16`, prefer unresearched via tracker) + bee products (specialties if bounty > 10). Trees notes-only. See `queries/core-e1c.md` |
| CORE-E5 | Escritoire BER | Desk BER (`RenderEscritoire` + `escritoire.png`); specimen on `SLOT_ANALYZE` via BE update tag / `sendBlockUpdated` (no `itemstack_display`). Item special TESR. See `queries/core-e5.md` |
| CORE-V1 | Arborist villager | Unemployed villager takes arborist job at `tree_chest`. Trades copy CE `ArboricultureVillagers` (planks/logs/sapling/pollen/`grafter_proven`). 26.2 `TradeSet` datapack + loot functions. Complexity = CE mutation-depth heuristic; giant sequoia forced to 10. See `queries/core-v1-arborist.md` |
| CORE-V2 | Beekeeper villager | Unemployed villager takes beekeeper job at `escritoire`. Trades copy CE `ApicultureVillagers` (combs/smoker/propolis drones/princess/frame/apiary/monastic/ended). 26.2 `TradeSet` datapack + loot functions. Apiarist houses under `structure/village/` + jigsaw inject. See `queries/core-v2-beekeeper.md` |

---

## Phase 3 — Simple content

| ID | Feature | Notes |
|---|---|---|
| 5 | Simple items | Honey products, wax, pollen, combs, core resources/foods, fuels |
| A1 | Core materials (gap) | `gear_iron`, `brick_ash`, `brick_wax`, `brick_refractory_wax`; recipes + `c:gears/*` / `c:dusts/wood`; still uses iron gear — see `queries/core-A1.md` |
| A2 | Survivalist bronze tools | Five tools + remnant on break via Fabric `CustomDamageHandler`; recipes + tool tags — see `queries/core-A2-bronze-tools.md` |
| A3 | Assembly kits | Five carton kits; right-click unpacks A2 tool; craft + carpenter — see `queries/core-A3-assembly-kits.md` |
| A4 | Wrench + pipette | `IToolPipette` API; wrench rotates `BlockMachine` facings; pipette fills/empties GUI tanks via Fabric transfer — see `queries/core-A4-wrench-pipette.md` |
| A5 | Naturalist helmet | `naturalist_helmet` / spectacles; `IArmorNaturalist` + `ISpectacleBlock`; Gizmo outlines for pollinated leaves, wild hives, creative multiblock refs — see `queries/core-A5-naturalist-helmet.md` |
| A6 | Research note | `research_note` + breeding tracker; structure-chest loot with random mutation data — see `queries/core-A6-research-note.md` |
| A7 | Portable alyzer | `portable_alyzer` item GUI; `IndividualItems` + `analyzed` component; bee/tree chromosome pages — see `queries/core-A7-portable-alyzer.md` |
| A8 | Forester's manual | Stub item + recipes; Patchouli book assets kept inert until 26.2 Patchouli exists — see `queries/core-A8-foresters-manual.md` |
| A9 | Naturalist chests + desk analyzer | `bee_chest` / `tree_chest` / `butterfly_chest` (125-slot paged GUI, species filters). Analyzer: honey + FE → analyze. Bag recipes craftable except lepidopterist (no butterfly-chest recipe until lepidopterology). Escritoire: `CORE-E1a` (empty GUI); game/notes/BER later. See `queries/core-naturalist-chests.md` |
| 6 | Simple blocks | 81 vanilla-wood fireproof wood blocks |
| 3C | Bee comb blocks | 17 `block_bee_comb_*` with dual-tint coloring |
| 3D | Core blocks | Ores, resource storage, peat, humus, bog earth (tick behavior) |

---

## Phase 4 — Bees (apiculture)

| ID | Feature | Notes |
|---|---|---|
| 7 | Genetics engine | Alleles, chromosomes, genomes, karyotypes, DataComponents |
| 8 | Bee species + items | 69 species; queen/drone/princess/larvae; per-species tinting |
| 9 | Bee housing | Apiary + Bee House; GUI; production; progress bar; bee particles |
| 10 | Breeding + mutations | Mating, lifespan, offspring, 114 mutations (discovery journal deferred) |
| 4.0 | Climate + housing APIs | `api.climate`, biome→temp/humidity manager, expanded `IBeeHousing` (modifiers/listeners/errors/climate), scoop tags, species ideal climate |
| 4.1 | Tools, frames, armor | Scoop (+proven), smoker, frames×4 (wear + production mods), apiarist armor (implements `IArmorApiarist`); recipes + creative tab |
| 4.2 | Wild beehives + worldgen | 12 `beehive_*` blocks + `TileHive`; scoop drops/silk; smoker calm + sting/armor; `HiveDecorator` via Fabric biome mods |
| 4.3 | Work gating | `BeekeepingLogic.canWork` climate/activity/rain/sky/flower pipeline; real `ActivityType` + `FlowerType` tags; `HasFlowersCache` spiral; wild hive activity+rain; GUI error icons |
| 4.4 | Bee effects (partial) | CE-shaped `IBeeEffect` (`doEffect`/`doFX`); plugin registration; aggressive/potion/snowing/exploration/heroic/misanthrope/glacial live; remaining effects stubbed as `DummyBeeEffect`; hakuna_matata/matata mob effects; armor reduces harmful effects |
| 4.5 | Multiblock core framework | `api.multiblock` + `core.multiblock` (registry, rectangular controller, tile base); Fabric level tick / chunk load / BE load / world unload hooks; `TestRectangularController` smoke subclass (alveary playable structure is G). **Chunk-unload save:** Fabric fires `CHUNK_UNLOAD` before `ChunkMap.save()` — save-delegate snapshots controller NBT into `cachedMultiblockData` before detach so shared inventory is not dropped — see `queries/multiblock-chunk-unload-save.md` |
| 4.6 | Alveary (all 7 parts) | 3×3×3 `AlvearyController` (plain roof/interior validation, wooden slabs on top, air around the entrances, ×2 territory, climate steps, bee FX + pollen dust); plain, stabiliser (no mutations), fan/heater (Team Reborn `EnergyStorage`, 2k buffer, ±1 temperature step), hygroregulator (`reforestry:hygroregulator` recipes + factory-off water/lava/ice fallback + tank insert filter, F-HYGRO), sieve (pollen from `IBeeListener.onPollenRetrieved`), swarmer (royal jelly → swarm hive via `HiveDefinitionSwarmer`); 4 menus + screens; recipes and item models. Shared bee inventory persists across chunk unload via 4.5 save-delegate snapshot (apiary/beehouse/factory tiles use normal `saveAdditional` + `ContainerHelper`) |
| 4.7 | Bee effects P0 + jubilance | Remaining dummy effects are real CE classes (radioactive, creeper, ignition, reanimation/resurrection, repulsion, fertile, mycophilic, sifter, glow berry, rejuvenation/chronophage, guardian, phasing, ascension, sculk). EASTER stays dummy. Specialties only if both species jubilant, then primary specialties × speed. Hermit jubilance on monastic/secluded/hermitic. `tryMate` registers the new queen. See `queries/apiculture-effects-p0.md` |
| 4.8 | Pristine / ignoble stock | Wild hive princesses roll CE ignoble chance; tooltip (genome required) + analyzer show Pristine/Ignoble Stock (pristine italic) and captivity generations; patriotic bees also produce random fireworks; `/reforestry bee give [species] [drone\|princess\|queen\|larvae] [player]` (path-only species, default forest drone). See `queries/apiculture-pristine-ignoble.md` |
| 4.9 | Wax blocks | CE 1.20.1 restore: `wax_block` / `wax_block_refractory` (3×3 wax ↔ 9 items). Yellow block is flammable 45/45; 26.2 has no `onCaughtFire` melt. Fabricator wax smelting skipped (no crafts consume wax fluid). Creative tab: after wild hives, before alveary. See `queries/apiculture-wax-blocks.md` |
| 4.10 | Bee JEI | Products + mutations categories (`reforestry:bee_species_products` / `bee_species_mutations`); species subtypes on all four bee items; creative-frame force-mutation subtype; frame/suit/scoop descriptions. Bees only — no tree/butterfly species-type loop. See `queries/apiculture-jei.md` |
| A-CRAFT | Survival craft recipes | Shaped crafts for existing `apiary`, `bee_house`, `honeyed_slice`, `ambrosia`. `honey_pot` restored from 1.12 (CE 1.21.1 dropped it). Apiary center is `impregnated_casing` (CE 1.21.1, not `sturdy_machine`). See `queries/a-craft-honey-pot.md` |
| A-BREW | Pollen brewing | Awkward potion + `pollen_cluster_normal` → healing; awkward + `pollen_cluster_crystalline` → regeneration. Fabric `FabricPotionBrewingBuilder.BUILD` (no Mixin). Holders: `minecraft:awkward`, `minecraft:healing`, `minecraft:regeneration` |
| A-SNIFF | Sniffer amber drone | `minecraft:gameplay/sniffer_digging` (`BuiltInLootTables.SNIFFER_DIGGING`): `amber_drone` and `amber_sapling` added to the existing pool via Fabric `modifyPools`. See `queries/a-sniff.md` |
| A-ALVID | Alveary registry ids | Blocks `alveary_block` / `alveary_hygroregulator` / `alveary_stabilizer` (CE 1.21.1). Enum constants stay PLAIN/HYGRO/STABILISER. No alias. Axe-mineable: apiary, bee_house, all seven alveary parts. See `queries/a-alvid.md` |

---

## Phase 5 — Trees (arboriculture) — **done**

| ID | Feature | Notes |
|---|---|---|
| 11.1 | Shared genetics refactor | Mating/mutations usable by trees, not only bees |
| 11.2 | Forestry wood blocks | 43 wood types × ~26 kinds (~1,118 blocks), fireproof variants, signs |
| 11.3 | Tree genetics engine | 10 chromosomes, fruits, `TREE_GENOME` component |
| 11.4 | Tree species + germlings | 50 species, 40 mutations; sapling/pollen items; grafters |
| 11.5 | Sapling/leaves + growth | Plant → tick → logs+leaves; girth checks; CE growth pipeline (FeatureTree) |
| 11.6 | Tree breeding + pollination | Pollen mating; leaf drops; bee↔tree pollen on **genetic** leaves |
| 11.7 | Client rendering | Per-species leaf/sapling models, foliage tint, fruit overlay, sign BER |
| 11.8a | Species worldgen metadata | Rarity, temp/humidity, tree-feature factory, decorative/vanilla leaf hooks on all 50 species |
| 11.8b | Default / decorative leaves | 50×3 BE-less leaf families; shear/pick → decorative; wood `setDefaultLeaves` for worldgen |
| 11.8c | Growth engine | `FeatureBase`/`FeatureHelper`/`FeatureArboriculture`/`FeatureTree`/`TreeGrowthHelper` |
| 11.8d | Feature shapes batch A | Temperate/vanilla silhouettes wired in `DefaultTreeSpecies` |
| 11.8e | Feature shapes batch B | Remaining CE Features; all 50 species wired; `SimpleTreeGenerator` removed |
| 11.8f | Wild TreeDecorator + Fabric biome mods | Biome VEGETAL_DECORATION; `trees.tree_spawn_chance_modifier`; `custom_tree` feature type |
| 11.8g | Wood boats / chest boats | Shared boat entities + wood synched data; 43+43 items, recipes, dispenser, tab, renderer |
| 11.9a | Fruit pods | `pods_*` + BE; dates/papaya/coconut on log faces; cocoa → vanilla cocoa |
| 11.9b | Grafter vanilla loot | Fabric `MODIFY_DROPS` + vanilla leaf→genome map; grafter craft recipe |
| 11.9c | Vanilla/default leaf pollen | Bees and `pollen_fertile` pollinate mapped oak/default leaves, not only `TileLeaves`. `bees.pollinate_vanilla_leaves` default true. Persistent/decorative skipped. See `queries/arb-11.9c.md` |
| 11.9d | Charcoal pile | Log pile → ash with wall yields; charcoal block fuel 16000; recipes + `c:storage_blocks/charcoal` |
| 11.9e | Recipes / tags / loot catch-up | Vanilla fireproof recipes+loot+tags (incl. mangrove/pale_oak); pod/leaves loot; ash recipes; common tags |
| 11.9f | Creative tabs / models / lang | Tab lists leaves/pods/boats/charcoal; MC 26.2 item defs; lang fallbacks |
| 11.9g | Tree admin commands | `/reforestry tree spawnTree\|spawnForest <species>` (op; needs player look-dir) |
| 11.9i | Integration pass | Play-loop probes + docs; fixed `pale_pale_oak_*` datapack ids and item `#minecraft:leaves` ↔ block-only `reforestry:leaves` — see `queries/arb-11.9i-integration.md` |
| ARB-JEI | Tree/charcoal JEI | Sapling + `pollen_fertile` species subtypes; grafter / `grafter_proven` descriptions; charcoal pile category `reforestry:charcoal.pile` from `CharcoalManager.getWalls()` (catalyst `log_pile`). No tree mutation/product categories (CE has none). |

**Deferred / polish (not blocking Phase 5):** none. Arborist villager is **CORE-V1** (done).

---

## Phase 6 — Factory (machines) — **done**

Play loop: `queries/factory-play-loop.md`. Polish: `queries/factory-F17.md`, `queries/factory-machine-gui-data-polish.md`.

### Playable checklist

| Playable today | Notes |
|---|---|
| All 10 factory blocks craftable | Sturdy casing + per-machine shaped recipes; smelter uses furnace center |
| Fluid containers + buckets | Craft can/capsule/refractory; filled stacks tint contents (`F-TINT`); nine Forestry buckets |
| Centrifuge + 17 comb recipes | Socket upgrades (speed/efficiency/fortune) |
| Smelter + 12 CE20 recipes | Socket speed/power; bronze local; other alloys/silicon are intermod `c:` tags (F6-ALLOY). **Block texture missing** (see F17 deferrals) |
| Still → ethanol | Biomass → bio_ethanol **10:3 mB** per work cycle (matches JEI); `time` = duration/energy only — see `queries/factory-F7.md` |
| Squeezer + 26 item recipes + container drain | Socket upgrades; CE parity + honey_drop; JEI excludes container stubs; tank uses real fluid colors |
| Bottler fill/empty | Auto fluid dump; power/hint ledgers; JEI fill+empty recipes; real tank fluid tint |
| Carpenter + 16 safe recipes + F15 circuits | Bulk CE extract; mail recipes skipped (module out of scope); escritoire landed with `CORE-E1a` |
| Fermenter + 19 recipes + fermenter fuels | Biomass / short mead path |
| **Fabricator — fully tested** | Heat + molten glass smelt; ~265 crafts (tubes + fireproof + flexible casing); ghost grid, recipes ledger, JEI category; CE craft parity (hardened casing stays carpenter like Immersive/1.12). Reopen only if a bug is reported. |
| Moistener + 4 recipes | Wheat chain, no FE; hint ledger + loot + JEI description |
| Rainmaker | Iodine/dissipation charges (carpenter); JEI description; no GUI |
| Hygroregulator recipes | Water/lava/ice datapack + factory-off fallback (`reforestry:ice`); tank insert filtered to recipe fluids |
| Machine circuits | Boards, tubes, soldering iron; centrifuge/squeezer/smelter sockets |
| GUI hints / loot / pickaxe | All GUI machines: `setHintKey` + `data/reforestry/hints.properties`; all 10 self-drop loot; `#minecraft:mineable/pickaxe` |
| JEI item descriptions | All 10 machines via `JeiDescriptions` + `for.jei.description.*` langs |
| Hopper item I/O | Documented sidedness; not hopper smoke-tested |
| External FE | Peat, biogas, and clockwork engines push FE into machines. `debug_creative_energy` still works. |

| ID | Feature | Notes |
|---|---|---|
| F1–F3b | Factory module shell, `BlockTypeFactoryPlain`/`FactoryBlocks.PLAIN` group, `MachineProperties`/`BlockMachine`, `TilePowered`/`IPowerHandler`/`EnergyHelper`, creative FE test fixture (`reforestry:debug_creative_energy`, `reforestry:debug_powered`) | See `queries/factory-F1.md` … `factory-F3b.md` |
| F5 | **Centrifuge** — first playable machine | `reforestry:centrifuge` block/tile/menu/screen; `ICentrifugeRecipe`/`CentrifugeRecipe` (`data/reforestry/recipe/centrifuge/*.json`, 17 recipes extracted from CE); energy 40000 cap / 800 recv / 3200 per work cycle; no sockets, no fluids. Also fixed a latent F3b bug where `TilePowered`'s own work-cycle energy consumption was blocked by the same `maxExtract=0` meant for external neighbors — see `queries/factory-F5.md` |
| F6 | **Smelter** — alloy machine | `reforestry:smelter` block/tile/menu/screen; `ISmelterRecipe`/`SmelterRecipe` + `IngredientStack`; energy 40000 cap / 1100 recv / 2000 per 10-tick reference (200× recipe time); 3×3 input + product slot; `ContainerStorage`/`ItemStorage.SIDED` on centrifuge + smelter; no sockets, no fluids, no temperature — see `queries/factory-F6.md` |
| F6-ALLOY | **Smelter alloy catch-up** | CE20 extract via `tools/extract_smelter_recipes.py`: **12** JSON under `data/reforestry/recipe/smelter/` (10 alloys + 2 silicon). Bronze was already local (overwrite matched). Brass/invar/electrum/constantan/silicon are `c:` tags, not Re-Forestry items. CE 1.21.1 dropped generated `recipe/smelter/` copies. See `queries/factory-F6.md` |
| F7 | **Still** — fluid distillation | `reforestry:still` block/tile/menu/screen; `IStillRecipe`/`StillRecipe` + `RecipeFluidAmount`; dual `MultiFluidTank` (any-fluid filters; recipe gates work); CE ethanol recipe (`still/ethanol.json` 10→3 mB); energy 80000 / 1100 / 200×`time` FE per work cycle; one recipe unit per cycle (JEI-aligned; CE batches `time` units); `IRenderableTile` TESR tanks (no CE blockstate fill levels); GUI + JEI category — see `queries/factory-F7.md` |
| F8 | **Squeezer** — item→fluid + container drain | `reforestry:squeezer` block/tile/menu/screen; `ISqueezerRecipe`/`ISqueezerContainerRecipe` + serializers; single product tank (10000 mB); 9× input + remnant + can slots; **26** CE recipes (`data/reforestry/recipe/squeezer/` including `honey_drop`); energy 40000 cap / 1100 recv / 200× recipe time; JEI filters out container recipes (empty input/fluid stubs); GUI tank tint via `RenderUtil.getFluidColor`; local tweaks: cactus 5% mulch, seeds 1% mulch, coconut 50 mB milk — see `queries/factory-F8.md` |
| F9 | **Bottler** — container fill/empty | `reforestry:bottler` block/tile/menu/screen; runtime `BottlerRecipe` (no RecipeType/JSON); single resource tank (10000 mB); 6 container slots; energy 40000 / 1100; fill viscosity-scaled, empty **0 FE**; neighbor dump; GUI power + pipette hint; fluid-id tank sync; emptying ctor matches CE; JEI category (dynamic fill/empty) — see `queries/factory-F9.md` |
| F10 | **Carpenter** — shaped craft + fluid/box | `reforestry:carpenter` block/tile/menu/screen; `ICarpenterRecipe`/`CarpenterRecipe` + embedded crafting recipe codec; 3×3 grid + 2×9 storage + box + resource tank (10000 mB) + product; energy 40000 cap / 1100 recv / 2040 FE per 10-tick ref × recipe time; 3 CE recipes (`bog_earth`, `humus`, `impregnated_casing`); smoke = water + dirt/sand/mulch → 8 bog earth — see `queries/factory-F10.md` |
| F11 | **Fermenter** — item+fluid → biomass | `reforestry:fermenter` block/tile/menu/screen; `IFermenterRecipe`/`FermenterRecipe` + `IVariableFermentable`; dual tanks (resource/product 10000 mB) + item resource/fuel/can slots; energy 80000 cap / 2000 recv / 4200 FE per work cycle; `FuelManager.fermenterFuel` seeded (compost/mulch/fertilizer); 19 CE recipes; smoke = sugar cane + water + compost → biomass — see `queries/factory-F11.md` |
| F12a | **Fabricator smelting** — **fully tested** | `reforestry:fabricator` heat + molten tank; `IFabricatorSmeltingRecipe`; 8000 mB molten; energy 3300 / 1100 / 200 FE per cycle; heat 5000 max; 4 glass smelting recipes; smoke = glass → liquid glass — see `queries/factory-F12a.md` |
| F12b | **Fabricator craft** — **fully tested** | `IFabricatorRecipe` plan + ghost 3×3 + result + storage; molten filter; CE GUI; ~265 crafts (tubes, flexible casing, fireproof ×50 woods ×5 kinds); JEI layout fixed (3×3 expanded pattern). Done until a bug is reported — see `queries/factory-F12b.md`, `queries/fabricator-recipes-ledger.md` |
| F13 | **Moistener** — water + light, no FE | `reforestry:moistener` block/tile/menu/screen; `IMoistenerRecipe`/`MoistenerRecipe`; `TileBase` (not powered); water tank 10000 mB; wheat-chain fuels + 4 CE recipes; light speed tiers; hint ledger (`nopowerrequired;pipette;moistenerproducts`); loot + pickaxe tag; JEI description; smoke = wheat → mulch chain or wheat_seeds → mycelium — see `queries/factory-F13.md` |
| F14 | **Rainmaker** — weather substrates, no FE/GUI | `reforestry:rainmaker` on `FactoryBlocks.TESR`; `TileMill`/`TileMillRainmaker`; `FuelManager.rainSubstrate` (iodine/dissipation); right-click charge → mill cycle → rain/stop via `WeatherData`; placeholder block model (no animated BER); smoke = iodine on clear day → rain, dissipation while raining → clear — see `queries/factory-F14.md` |
| F15 | **Machine circuits / sockets** | `IMachineUpgradable` on `TilePowered` (speed/power/output multipliers); `ISocketable` on centrifuge/squeezer/smelter + GUI socket slot; circuit boards/tubes/soldering iron + `CircuitMachineUpgrade` (blaze/gold/amber); minimal carpenter recipes; smoke path in `queries/factory-F15.md` |
| F16 | **Hygroregulator RecipeType** (alveary) | `IHygroregulatorRecipe`/`HygroregulatorRecipe` (`reforestry:hygroregulator` on Factory types); water/lava/ice datapack; `TileAlvearyHygroregulator` recipe lookup via `RecipeUtils`; factory-off fallback — see `queries/factory-F16.md` |
| F-HYGRO | **Hygro ice fallback + tank filter** | Fallback ice matches datapack (+2 humidity, −2 temperature, 10 ticks); `HygroregulatorFluidSetup` unions datapack fluids with water/lava/ice; `FilteredFluidStorage` rejects other inserts; ice bucket / recipe-fluid containers accepted. Fluid id stays `reforestry:ice` (not CE `crushed_ice`). See `queries/f-hygro.md` |
| F4 | **Fluids foundation** (core) | All 9 `ForestryFluids` + buckets; `FluidUnits` mB↔droplets; `FilteredFluidStorage` / `MultiFluidTank` Fabric facade — see `queries/factory-F4.md`, `queries/factory-fluids-fabric.md` |
| F4c | **Fluid containers** | `reforestry:can` / `capsule` / `refractory` + `FluidStorage.ITEM`; craft recipes added F17 — see `queries/factory-F4c.md` |
| F17 | **Integration / polish** | Sturdy casing + container crafts; 16 safe carpenter recipes; factory creative tab; fermenter/squeezer models; play-loop + hopper docs; later GUI/data polish (hints/loot/pickaxe/JEI descriptions for all machines) — see `queries/factory-F17.md`, `queries/factory-machine-gui-data-polish.md` |
| F18 | **Factory JEI + fabricator recipes** | `FactoryJeiPlugin` categories/catalysts/click areas for **10** machines (incl. Bottler + Rainmaker); `JeiDescriptions` for all 10; recipes ledger → JEI; ghost slots; ~265 fabricator crafts. Squeezer JEI skips container stubs. Bottler JEI is dynamic (no RecipeType) via `BottlerRecipeMaker`. — see `queries/factory-F18.md`, `queries/fabricator-recipes-ledger.md` |
| F-FAB-XFER | **Fabricator JEI transfer** | JEI “+” fills the fabricator ghost grid. Same as carpenter: match `getResultStack()` to `getGuiRecipes()`, then `handleInventoryButtonClick` / `clickMenuButton`. No CE `PacketRecipeTransferRequest`. See `queries/f-fab-xfer.md` |
| F-TINT | **Filled can/capsule/refractory tint** | `layer0` bottle + `layer1` contents; `reforestry:fluid_container` ItemTintSource from `FluidContainerContents` + `RenderUtil.getFluidColor`. Empty is opaque white. Ids stay `can` / `capsule` / `refractory` (not CE `wax_capsule`). No NeoForge loader. Glass/jar models had a leftover loader and are not registered items — see `queries/f-tint.md` |
| F-ADV | **Recipe-unlock advancements** | 1070 CE 1.21.1 recipe-book unlocks under `data/reforestry/advancement/recipes/` (parent `minecraft:recipes/root`). 139 remapped when the local recipe uses a different id (`fabricator`, `*_bag`, CE20 wood names, …). `peat_engine` / `biogas_engine` / `clockwork_engine` copied in FE1–FE2. Remaining skips: `wax_capsule`, comb/ore blocks, `portable_analyzer`. No CE20 gameplay advancements. See `queries/f-adv.md` |

**Deferred (not blocking factory play):** smelter block texture (datagen assets not in reference folder), animated machine/rainmaker BER + squeezer `tank_product_fill_level` blockstate variants (CE fill animation), 19 carpenter recipes with unregistered outputs.

---

## Phase 7 — Storage — **done**

Play notes: `queries/storage-B1-b3-backpacks.md`, `queries/storage-B4-naturalist-backpacks.md`, `queries/storage-B5-b6-crates.md`, `queries/stor-crate.md`.

| ID | Feature | Notes |
|---|---|---|
| B0 | Storage module shell + API | `ModuleStorage` (`reforestry:storage`); `api.storage` + `api.ForestryTags` backpack allow/reject tags; Fabric `BackpackEvents`; `IBackpackInterface.createBackpack` takes `Item.Properties` (MC 26.2 `setId`); see `queries/storage-B0-api.md`, `queries/storage-B1-b3-backpacks.md` |
| B1–B3 | Filter backpacks (7 + 7 woven) | Held-bag GUI (15 / 45 slots); mode component (neutral/locked/receive/resupply); pickup stow + chest transfer + resupply; tags/recipes/models from CE; Traveler’s Backpack source adopted into `com.leon1236.reforestry.storage` (no TB dependency) |
| B4 | Naturalist backpacks (3) | `apiarist_bag` / `arborist_bag` / `lepidopterist_bag`; 125-slot paged GUI (`apiaristinventory.png`); species-type filters (`bee_species` / `tree_species` / `butterfly_species`). Shaped recipes need `bee_chest` / `tree_chest` / `butterfly_chest` (not registered yet — `/give` until Parallel B chests). See `queries/storage-B4-naturalist-backpacks.md` |
| B5–B6 | Empty crate + 83 `crated_*` | Carpenter pack (9 + empty crate + 100 mB water) / unpack (9 items); right-click filled crate drops 9. Comb crate ids follow CE 1.21.1 (`crated_honey_comb`, not CE20 `crated_bee_comb_*`). Overlays: `STOR-CRATE`. See `queries/storage-B5-b6-crates.md` |
| STOR-JEI | Backpack JEI descriptions | Grouped CE keys for miner/digger/forester/hunter/adventurer/builder bags (normal+woven); `apiarist_bag` / `lepidopterist_bag` individually; also `brewer_bag` (lang exists). No `arborist_bag` (CE has no key). No crate JEI. |
| STOR-CRATE | Crate content overlay | 83 `crated_*` layered `item/generated` models: `layer0` `crate-filled` + contained item/block texture (`layer2` for comb/pollen). Empty crate stays `crate`. No NeoForge `forestry:filled_crate` loader. See `queries/stor-crate.md` |

---

## Phase 8 — Worktable — **W0–W2 done**

| ID | Feature | Notes |
|---|---|---|
| W0–W1 | Worktable module | `reforestry:worktable`: 3×3 ghost craft from 18-slot storage; 9 memorized recipes; recall/lock/clear/conflict via `clickMenuButton` (no Forge packets). Memory persists `ValueInput`/`ValueOutput` + BE sync. Module tab `itemGroup.worktable`. See `queries/worktable-W1.md` |
| W2 | Worktable JEI transfer | JEI “+” on vanilla `minecraft:crafting` fills the ghost grid. C2S `reforestry:worktable_recipe_request` (BlockPos + 9 stacks); clickMenuButton is not enough (shared results). `WorktableJeiPlugin` + transfer handler. See `queries/worktable-W1.md` |

---

## Phase 9 — Energy — **FE2 done**

| ID | Feature | Notes |
|---|---|---|
| FE0 | Energy module + fuels | `reforestry:energy` toggles in module config. `EngineBronzeFuel` / `EngineCopperFuel` on existing `api.fuels`. Biogas + peat maps seeded from CE `setupApi` (no ethanol). See `queries/energy-FE0.md` |
| FE1 | Peat engine | `reforestry:peat_engine` (not `engine_peat`). Place, feed peat, redstone on, face a centrifuge → centrifuge runs without `debug_creative_energy`. Ash every 7500 burn ticks. See `queries/energy-FE1.md` |
| FE2 | Biogas + clockwork | `reforestry:biogas_engine` burns fluid fuel (needs heat / lava in the heating tank) and pushes FE. `reforestry:clockwork_engine` has no menu; right-click winds, overwind deals `reforestry:clockwork` damage. See `queries/energy-FE2.md` |

## Wave 3 — Sorting

| ID | Feature | Notes |
|---|---|---|
| S0 | Filter API + sorting module | `reforestry:sorting` toggles in module config. `IForestryApi.getFilterManager()` holds `DefaultFilterRuleType` (CLOSED default). `FilterData` is `(Identifier, IGenome, String)` over `IndividualItems` — no `ISpecies`/`IIndividual`. `IFilterSlotDelegate` on `ItemInventory`. No genetic_filter block. See `queries/sorting-S0.md` |
| S1 | Genetic filter block | Place `reforestry:genetic_filter`. GUI rules + discovered bee/tree species picker (Identifier, not `ISpecies`). Hopper in → matching faces out. Custom `ItemStorage.SIDED` insert routing (not worldly `InventoryHelper`). Bee/tree rules only. See `queries/sorting-S1.md` |

## Wave 4 — Farming

| ID | Feature | Notes |
|---|---|---|
| G0 | Agriculture API + farming module | `reforestry:farming` toggles in module config. `IForestryApi.getFarmingManager()` is a no-op (`isLoaded` false) until the module loads, then a real `FarmingManager`. `fertilizer_compound` = 500. No farm blocks, tiles, menus, or circuits. See `queries/farming-G0.md` |
| G1 | Farm structure blocks | 55 placeable, survival-craftable multifarm blocks (`{material}_farm_{part}`, including `stone_brick_farm_block`). Agriculture tab. No BEs/GUI. Vanilla overlay models (not `neoforge:composite`). See `queries/farming-G1.md` |
| G2a | Farm multiblock assemble | Valid 3–5×4×3–5 farm forms (min 36, plain interior, plain `dy==2` band, ≥1 gearbox). Right-click overlay errors; no GUI. Rectangular controller, not CE `FarmPattern`. See `queries/farming-G2a.md` |
| G2b | Farm inventory + GUI | Assembled farm opens `reforestry:farm`. Inventory/socket/tank on the controller (alveary NBT + drop). ContainerData not PacketGuiStream. Layouts `reforestry.farms.managed` / `manual` only. See `queries/farming-G2b.md` |
| G2c | Farm hatch / valve / gearbox IO | Gearbox `EnergyStorage.SIDED` (10000 / 200 insert-only). Valve `FluidStorage.SIDED` → controller water tank. Hatch `ItemStorage.SIDED` + product export to inventories below. Control redstone `cancelTask` (dust does not visually connect — 26.2 has no `canConnectRedstone`). No crop logics. See `queries/farming-G2c.md` |
| G3 | Crops farm logic | `reforestry:crops` (wheat/potato/carrot/beetroot). Bronze tube → managed + manual crops circuits. `FarmManager` plants farmland, wheat, harvests mature crops, spends water (droplets) + fertilizer. `Level.isLoaded` not `hasChunkAt`. JEI `reforestry:farming`. See `queries/farming-G3.md` |
| G4a | Other crop-like farm types | `gourd` / `shroom` / `poales` / `succulentes` / `infernal` from CE `DefaultFarms`. Circuits match CE plugin (obsidian gourd both, apatite shroom both, diamond poales manual, gold succulentes manual, blaze infernal managed). JEI lists the new **manual** circuits (not infernal). `de_de`/`zh_tw` look up `succulentes`/`poales`. See `queries/farming-G4a.md` |
| G4b | Arboreal / peat / orchard / cocoa / ender | CE `DefaultFarms` remaining types. Circuits: copper arboreal managed + orchard manual, tin peat both, iron ender both, lapis cocoa manual. Empty farm sides default to managed arboretum. Soft arboriculture for `FarmableGE` / orchard fruits. `FarmableInfo` feeds peat/cocoa/orchard into JEI. See `queries/farming-G4b.md` |
| CU0 | Cultivation module shell | `reforestry:cultivation` toggles in module config. Depends `core` + `farming`; `ModuleManager` skips it if farming is off. Empty `CultivationClientHandler`. No planter blocks, tiles, menus, or circuits. CE has no separate cultivation plugin. See `queries/farming-CU0.md` |
| CU1 | Crops planter | All 14 `{type}_managed` / `{type}_manual` ids (`IdentifierType.SUFFIX`). `farm_crops_*` farms wheat with water + FE + fertilizer. Menu `reforestry:planter`. Craft tubes stay CE-exact (arboretum craft tube is gold, not copper). See `queries/farming-CU1.md` |
| CU2 | Remaining planters | Arboretum / mushroom / gourd / nether / ender / peat bog use G4 farm logics (managed + manual). 21 recipes, 14 loot, 21 unlocks. Craft tubes stay CE-exact (gold arboretum ≠ copper circuit). See `queries/farming-CU2.md` |

## Out of scope

| ID | Feature | Notes |
|---|---|---|
| Mail | Forestry postal system | Intentionally not ported. No mailbox, stamps, letters, catalogue, trade station, or stamp collector. See `queries/mail-dropped.md`. |

## Next up (not implemented yet)

**Next up: Wave 7 `EB2d`.** EB2c metals/minerals/gems/nuclear (+ precious) landed. Locked remaining order: see `queries/wave7-plan.md` (EB2d–e → EB4…). No solar/combustion.

**Wave 7 addons:** GD0 + EB1 + GD1–GD8 + EB5 + EB6 + EB-FLOWERS+EB3 + EB2a + EB2b + EB2c done; Extra Trees still extract-only until ET1a.

| ID | Status | Notes |
|---|---|---|
| `GD0` | Done | `ModuleGendustry` + plugin; depends arboriculture; 10 resource parts + 17 upgrades + 6 elite + `pollen_kit`; tag `reforestry:upgrades`; crafts; `IPollen.createStack` via `TreePollenType`. Upgrades wired in GD7b. See `queries/gendustry-GD0.md` |
| `GD1` | Done | Fluids `mutagen` / `liquid_dna` / `protein` + fluid blocks + buckets; tab; textures/lang; GD0 elite crafts unblocked. See `queries/gendustry-GD1.md` |
| `GD2` | Done | Recipe types `reforestry:mutagen` / `protein` / `dna`; caches; 4+7+10 datapack recipes; species ids remapped. Genetic template waits for GD4. See `queries/gendustry-GD2.md` |
| `GD3` | Done | `mutagen_producer` / `protein_liquefier` / `dna_extractor`; shared `processor` menu/screen; FE ctor swap (1M/10k); labware 10% consume; crafts/loot/tags/hints; tab machines-first; fluid tank colors; inventory drops. See `queries/gendustry-GD3.md` |
| `GD4` | Done | `sampler` BE+GUI; `gene_sample` / `genetic_template` DataComponents; wipe smelts; combine recipe; `gene_samples` tab (best-effort allele dump). See `queries/gendustry-GD4.md` |
| `GD5` | Done | `mutatron` / `advanced_mutatron`; mutagen tank; mutation errors + selection GUI; FE ctor swap. See `queries/gendustry-GD5.md` |
| `GD6` | Done | `imprinter` / `genetic_transposer` / `replicator`; dual DNA+protein tanks; blank/source/dna/protein/template errors; FE ctor swap. See `queries/gendustry-GD6.md` |
| `GD7a` | Done | `industrial_apiary` as `IBeeHousing` + `new BeekeepingLogic(this)`; FE (1M/100k, BASE_ENERGY 200); menu/GUI; upgrade slots accept items with identity modifiers. See `queries/gendustry-GD7a.md` |
| `GD7b` | Done | `IndustrialApiaryBeeModifier`; upgrade energy + climate/throttle; fertility drones; automation recycle; youth mutation −20%/stack (honest tooltip); sieve pollen; `IBeekeepingLogic.setWorkThrottle`. See `queries/gendustry-GD7b.md` |
| `GD8` | Done | 12 `GendustryError` sprites + lang; `GendustryJeiPlugin` soft entry; mutagen/protein/DNA JEI categories + catalysts; gene-sample subtypes; fluid info; mutatron→mutation click. See `queries/gendustry-GD8.md` |
| `EB1` | Done | `ModuleExtraBees` + plugin; depends apiculture; 74 combs, 24 drops, 4 propolis, 5 frames, 30 misc, ectoplasm, 4 hives (`beehive_eb_nether`); frame crafts + carpenter `scented_gear` + dust/shard recipes. Worldgen/loot EB4. See `queries/extra-bees-EB1.md` |
| `EB5` | Done | 7 alveary parts (`alveary_mutator|frame|rain_shield|lighting|stimulator|hatchery|transmission`); multiblock `IAlvearyComponent`; 7 crafts; 9 stimulator circuits + FE; GUIs for mutator/frame/hatchery/stimulator. See `queries/extra-bees-EB5.md` |
| `EB6` | Done | 74 centrifuge + 21 squeezer datapack recipes from `extra-bees-items.json` via `tools/generate_extra_bees_machine_recipes.py`; soft-skip IC2/OreDict/missing fluids. See `queries/extra-bees-EB6.md` |
| `EB-FLOWERS` + `EB3` | Done | 11 flower types + 25 effects (`bee_effect_eb_radioactive`); soft Botania MYSTICAL; FX particles; lang. See `queries/extra-bees-EB3.md` |
| `EB2a` | Done | 25 species (barren/rocky/hostile/volcanic/shadow/aquatic/classical; hive WATER/ROCK/BASALT/MARBLE); 22 EB mutations; 34 `modifySpecies` FR mutations; taxa + lang; generator `tools/generate_extra_bees_species.py`. Deferred INK/GLOWSTONE. See `queries/extra-bees-EB2a.md` |
| `EB2b` | Done | 31 species (historic/fossilized/refined/agrarian/farming/saccharine/boggy/frozen/energetic + GLOWSTONE); 34 EB mutations; remaps `bee_eb_primeval`/`bee_eb_relic`/`bee_eb_boggy` + `bee_artic`; cumulative generator. See `queries/extra-bees-EB2b.md` |
| `EB2c` | Done | 27 species (metallic/metallic2/precious/mineral/gemstone/nuclear); 45 EB mutations; ore comb specialties; radioactive effect; cumulative generator. See `queries/extra-bees-EB2c.md` |
| `EB0` | Extract done | `tools/extract_extra_bees.py` + `queries/extra-bees-*.json` — 116 species, 168 mutations, 25 effects. |
| `ET0` | Extract done | `tools/extract_extra_trees.py` + `queries/extra-trees-extract/` — 97 species (skip 9 binomials), 59 fruits, 36 planks (skip 6 → 30 new + shrub log), 97 mutations, 22 moths. No Java woods/module. |
| `W7-INT` | Merged | Local 1b–4 + origin/main Wave 5 + Wave 6 lepidopterology. Farming/energy/filter kept. |

## Wave 5 — Genetics public API

| Stage | Status | Notes |
|---|---|---|
| GP0a1 Public species / individual | done | `ISpecies` / `IIndividual` / `ILifeStage`; Bee/Tree wrappers |
| GP0a2 Genetic manager + taxonomy | done | `IGeneticManager`; 126 taxon JSON; Fabric reload |
| GP0a3 Individual item handler | done | `IIndividualHandlerItem` + `IIndividualItem` on GE items |
| GP0b Plugin registerGenetics | done | genetics-first plugin order; flower/pollen/error/client hooks |
| GP0c Promote bee/tree API | done | `IBee`/`ITree`/`IFruit`; `modifySpecies`; `ITreeManager` |
| GP0d Lepidopterology API shell | done | empty `BUTTERFLY` type; 34 taxa; items landed in Wave 6 D0 |

## Wave 6 — Lepidopterology

| Stage | Status | Notes |
|---|---|---|
| D0 Module + GE items | done | `butterfly` / `butterfly_serum` / `caterpillar` / `cocoon`; chest recipe; tab |
| D1 35 species + mutation + analyzer | done | 35 Java species; silk moth mutation; analyzer pages |
| D2 Entity + renderer + item model | done | Entity `reforestry:butterfly`; AI; scoop; serum on kill |
| D3 Cocoons + leaf nursery/spawn + mating | done | `cocoon` / `cocoon_solid`; `TileLeaves` nursery; mating recipe |
| D4 Remaining CE surface + docs | done | `foresters_manual_butterfly`; scoop on lepidopterology tab |

**Agent-ready stages:** [`queries/remaining-work-stages.md`](../queries/remaining-work-stages.md). Mail stays out of scope.

Older (partially stale): [`queries/item-gap-implementation-plan.md`](../queries/item-gap-implementation-plan.md), [`queries/module-completeness-audit.md`](../queries/module-completeness-audit.md). Energy engine list + CE package paths: [`queries/unstarted-modules-stages-ce121.md`](../queries/unstarted-modules-stages-ce121.md).

| Order | ID | Feature | Exit / notes |
|---|---|---|---|
| 1 | `GP0a1–d` | Species-type / plugin genetics façade | **done** (merged from GitHub) |
| 2 | `D0→D4` | **Lepidopterology** | **done** (merged from GitHub). Unlocks `butterfly_chest` recipe and sorting `S2` |
| 3 | Addons | Gendustry → Extra Bees → Extra Trees | Config modules; **GD0…GD8 + EB1 + EB5 + EB6 + EB-FLOWERS+EB3 + EB2a + EB2b + EB2c done**. Next: **EB2d** |
| 4 | Restore | Greenhouse, climatology, database, guide book (CE-dropped 1.12) | Optional |
| — | `TR1` | Optional Trinkets spectacles slot | Helmet already works (A5) |

---

## How to update this file

When you finish a step:

1. Move it from **Next up** into the matching phase table (or add a new row).
2. Keep the note short — what the player/dev can rely on, not a full verification diary.
3. Bump **Last updated**.
4. Point agents at this file for “what’s done,” not at `CLAUDE.md`.
