# Remaining work — agent-ready stages

**Date:** 2026-08-21  
**Parity target:** Forestry CE **1.21.1** (`thedarkcolour-ForestryCE`). Map packages onto our `com.leon1236.reforestry.{module}` layout — do not reshuffle to CE’s `core.content.`*.  
**Mail:** out of scope forever. See `queries/mail-dropped.md`. Do not implement mailbox, stamps, letters, catalogue, trade station, or stamp collector. Skip carpenter/mail recipes.  
**Status tracker after a stage lands:** `files/implemented-features.md`  
**Older audits:** `queries/module-completeness-audit.md` (July, stale on worktable/chests/effects) and `queries/item-gap-implementation-plan.md` (Tracks A–B–W mostly done; FE/G/S/D still useful but **engine list and package paths are CE20** — use this file + `queries/unstarted-modules-stages-ce121.md`).

## How to pick up a stage

Copy this into the implementing agent:

```
Implement Re-Forestry stage {ID} from queries/remaining-work-stages.md.
Follow .cursor/skills/reforestry-port/SKILL.md and reforestry-lookup.
Never invent registry ids — verify in CE 1.21.1 (graphify CE or clone MarkDown_Maker/Finished_github_clone/2026-08-17/thedarkcolour-ForestryCE).
Namespace forestry: → reforestry:. Keep our package layout.
Mail is out of scope.
DoD: .cursor/skills/reforestry-port/definition-of-done.md (D1–D13).
When done: update files/implemented-features.md and tick this stage.
```

**Size:** S = one short session · M = one focused session · L = split if it grows.  
**Order:** finish a wave’s “now” column before starting the next wave, except where the table says parallel.

Loaded today (`ReForestry.java`): `core`, `apiculture`, `arboriculture`, `lepidopterology`, `factory`, `energy`, `storage`, `worktable`, `sorting`, `farming`, `cultivation`.

---



## Snapshot (CE modules, mail excluded)


| Module                               | Status                           | Next stage              |
| ------------------------------------ | -------------------------------- | ----------------------- |
| core                                 | Playable; Wave 1b villagers done | —                       |
| apiculture                           | Playable (~95%)                  | —                       |
| arboriculture                        | Phase 5 done + arborist villager | —                       |
| factory                              | Playable                         | —                       |
| storage                              | Playable                         | butterfly chest after D |
| worktable                            | W0–W2 done                       | —                       |
| **energy**                           | FE2 biogas + clockwork playable  | —                       |
| **farming**                          | G4b done                         | —                       |
| **cultivation**                      | CU2 done                         | —                       |
| **sorting**                          | S1 + S2 done                     | —                       |
| **lepidopterology**                  | Wave 6 D0–D4 merged              | —                       |
| gendustry / extra bees / extra trees | GD0 + EB1 + GD1 + GD2 + GD3 + GD4 + GD5 + GD6 + GD7a done | `GD7b` |
| curios / Patchouli / 1.12 restore    | Optional                         | last                    |


CE 1.21.1 **dropped** solar + combustion engines (peat / biogas / clockwork only). Do not port them unless a later restore track says so.

---



## Wave 0 — tiny holes in existing modules (do first) — **done** (2026-08-20)

All **can-do-now**. No new modules.

### `A-CRAFT` — Apiary / bee house / honey foods (S) — **done** (2026-08-20)

**Player:** Survival craft for `apiary`, `bee_house`, `honeyed_slice`, `ambrosia`, `honey_pot`.  
Recipes in `data/reforestry/recipe/`. CE 1.21.1 apiary uses `impregnated_casing` (not `sturdy_machine`). `honey_pot` is a 1.12 restore — see `queries/a-craft-honey-pot.md`.

### `A-BREW` — Pollen brewing (S) — **done** (2026-08-20)

**Player:** Awkward potion + pollen → healing / regen as CE.  
Awkward + `pollen_cluster_normal` → `minecraft:healing`; awkward + `pollen_cluster_crystalline` → `minecraft:regeneration`. Registered from `PollenBrewingRecipes` via Fabric `FabricPotionBrewingBuilder.BUILD`.

### `A-SNIFF` — Sniffer amber drone (S) — **done** (2026-08-20)

**Player:** Sniffer digs can drop `amber_drone` (and `amber_sapling`, CE’s tree fossil).  
`minecraft:gameplay/sniffer_digging` (`BuiltInLootTables.SNIFFER_DIGGING`); existing pool edited with Fabric `modifyPools`. Arboriculture injects `amber_sapling` the same way. See `queries/a-sniff.md`.

### `F6-ALLOY` — Smelter alloy catch-up (S) — **done** (2026-08-20)

**Player:** Smelter datapack is the CE20 set (bronze plus intermod alloy/silicon tags), not only bronze.  
`tools/extract_smelter_recipes.py --root . --apply` wrote **12** JSON under `data/reforestry/recipe/smelter/` (CE20 generated folder has 12, not 10). Bronze was already local. Brass/invar/electrum/constantan/silicon stay as `c:` tags — not Re-Forestry items. See `queries/factory-F6.md`.

### `F-HYGRO` — Hygro ice fallback + tank filter (S) — **done** (2026-08-20)

**Player:** Ice recipe works when Factory is off; hygro tank only accepts recipe fluids.  
Fallback uses `ForestryFluids.ICE` (`reforestry:ice`, not `crushed_ice`); tank `insert` filtered via `HygroregulatorFluidSetup` + always-unioned water/lava/ice. See `queries/f-hygro.md`.

### `ARB-JEI` — Tree/charcoal JEI (S) — **done** (2026-08-20)

**Player:** Charcoal pile walls in JEI; sapling/`pollen_fertile` species subtypes; grafter + proven grafter descriptions.  
`ArboricultureJeiPlugin` + `CharcoalPileWallCategory`; recipe type `reforestry:charcoal.pile`. No tree mutation/product categories (CE has none).

### `STOR-JEI` — Backpack item descriptions (S) — **done** (2026-08-20)

**Player:** JEI describes backpacks like CE `StorageJeiPlugin` (plus `brewer_bag`).  
No `arborist_bag` description (CE has no key). Crate overlay JEI is `STOR-CRATE`, not this stage.

### `A-ALVID` — Alveary registry ids (S–M) — **done** (2026-08-20)

**Player/addons:** Ids match CE 1.21.1 contracts.  
**CE:** `alveary_block`, `alveary_hygroregulator`, `alveary_stabilizer`.  
**Local:** renamed from `alveary_plain` / `alveary_hygro` / `alveary_stabiliser`. No alias. See `queries/a-alvid.md`.

---



## Wave 1 — polish on loaded modules — **done** (2026-08-20)



### Tracker / analyzer / discovery



### `CORE-E2` — Breeding tracker client sync (M) — **done** (2026-08-20)

**Player:** Discovered species/mutations still show after quit/rejoin.  
Fabric payload `reforestry:genome_tracker_update`; `syncToPlayer` + incremental merge; login `ServerPlayConnectionEvents.JOIN` and `ServerEntityLevelChangeEvents.AFTER_PLAYER_CHANGE_LEVEL`. See `queries/core-e2-tracker-sync.md`.

### `CORE-E3` — Alyzer product pages (M) — **done** (2026-08-20)

**Player:** Portable alyzer page 3 lists real products/specialties; page 4 lists mutation icons from the client tracker.  
`drawProductList` + `drawMutationsPage` on `IAnalyzerGraphics`. Bee page 3 uses species products/specialties (drones haploid). Tree page 3 uses active `IFruit` products. Mutation UVs from CE `AnalyzerScreenGraphics`. See `queries/core-e3-alyzer-pages.md`.

### `A-DISC1` — Apiarist tracker counts in GUI (M) — **done** (2026-08-20)

**Player:** Bee chest / apiarist bag header shows species found + queen/princess/drone counts.  
`IApiaristTracker` + `ApiaristTracker`; mate/offspring call `registerQueen` / `registerPrincess` / `registerDrone` (those call `registerBirth`). Shared `NaturalistBreedingStatistics` on chest + bag. See `queries/a-disc1.md`.

### `A-DISC2` — Mutation discovery icons (M) — **done** (2026-08-20)

**Player:** Hovering an analyzed bee/tree in bee chest / apiarist bag / tree chest / arborist bag shows partner mutation icons (or chance-tier `?` if undiscovered).  
Shared `NaturalistSpeciesHover` on chest + bag. Idle header stays `NaturalistBreedingStatistics`. Unknown icons from `apiaristinventory.png` CE UVs. No journal book. See `queries/a-disc2.md`.

### `CORE-E4` — Chest + analyzer BER (M) — **done** (2026-08-20)

**Player:** Naturalist chests animate lids; analyzer shows pedestal/specimen.  
BER for `bee_chest` / `tree_chest` / `butterfly_chest` + `analyzer`. Lid `numPlayersUsing` on `getUpdateTag`; specimen is `SLOT_ANALYZE` via `saveCustomOnly`. Ids stay CE20 (`bee_chest`, not `apiarists_chest`). See `queries/core-e4.md`.

### Arboriculture leftovers



### `ARB-11.9c` — Vanilla / default leaf pollen (M) — **done** (2026-08-20)

**Player:** Bees can pollinate vanilla and default Forestry leaves, not only genetic `TileLeaves`.  
**Lookup:** CE `TreePollenType`, `TreeUtil.getOrCreateLeaves`, config `pollinateVanillaLeaves`. Local `ArboricultureGenetics.getVanillaIndividual` + grafter vanilla map already exist. See `queries/arb-11.9c.md`.  
**Smoke:** Apiary next to oak leaves produces oak pollen / converts leaves per CE.  
**Prompt:** Stage `ARB-11.9c`. Do not invent a new pollen item id.

### `CORE-V1` — Arborist villager (M) — **done** (2026-08-20)

**Player:** Unemployed villager takes arborist job at `tree_chest`; trades wood/saplings/grafter/pollen.  
CE `ArboricultureVillagers` listings copied. 26.2 has no `VillagerTrades.TRADES` / FAPI `TradeOfferHelper` — profession holds `TradeSet` keys; datapack `villager_trade` + loot functions roll random Forestry wood and `TREE_GENOME`. Complexity is CE `GeneticsUtil.getResearchComplexity` (mutation depth), not a public `ITreeSpecies.getComplexity()`. Giant sequoia stays 10. No arborist house NBT. See `queries/core-v1-arborist.md`.

### Worktable / factory / storage polish



### `W2` — Worktable JEI transfer (M) — **done** (2026-08-20)

**Player:** JEI “+” fills the worktable ghost/memory.  
C2S payload `reforestry:worktable_recipe_request` (BlockPos + 9 ghost stacks). Vanilla `minecraft:crafting` transfer cannot use `clickMenuButton` (many recipes share results). `WorktableJeiPlugin` + `WorktableRecipeTransferHandler`. See `queries/worktable-W1.md`.

### `F-FAB-XFER` — Fabricator JEI transfer (M) — **done** (2026-08-20)

**Player:** JEI “+” fills the fabricator ghost grid.  
Matches carpenter: `recipe.getResultStack()` vs `container.getGuiRecipes()`, then `handleInventoryButtonClick` / `IContainerRecipeBook.recipeButtonId`. No CE `PacketRecipeTransferRequest`. See `queries/f-fab-xfer.md`.

### `STOR-CRATE` — Crate content overlay (S–M) — **done** (2026-08-20)

**Player:** Each `crated_`* shows the packed item on the crate, not one shared `crate-filled` texture.  
83 layered `item/generated` models (`layer0` crate-filled + CE texture-path overlays). Empty crate stays `crate`. No NeoForge `forestry:filled_crate` loader. See `queries/stor-crate.md`.

### `F-TINT` — Filled can/capsule/refractory tint (M) — **done** (2026-08-20)

**Player:** Cans, capsules, and refractory look filled with the fluid color.  
Layered `item/generated` models (`layer0` bottle, `layer1` contents) plus `reforestry:fluid_container` ItemTintSource on `FluidContainerContents`. Ids stay `can` / `capsule` / `refractory`. No NeoForge `forestry:fluid_container` loader. See `queries/f-tint.md`.

### `F-ADV` — Recipe-unlock advancements (M, optional UX) — **done** (2026-08-20)

**Player:** Recipe book unlocks factory (and other existing) crafts as in CE.  
1070 JSON under `data/reforestry/advancement/recipes/` from CE 1.21.1 (singular `advancement/`, parent `minecraft:recipes/root`). 139 remapped onto local recipe ids (`thermionic_fabricator`→`fabricator`, `*_backpack`→`*_bag`, CE 1.21.1 wood names→CE20 enum ids, …). `peat_engine` / `biogas_engine` / `clockwork_engine` copied in FE1–FE2. Remaining skips when no local recipe (mail 0, escritoire 0, `wax_capsule`, `portable_analyzer`). No CE20 gameplay advancements. See `queries/f-adv.md`.

---



## Wave 1b — escritoire chain (core desk) ✅ DONE

Validated 2026-08-20. Assets under `assets/reforestry/.../escritoire*`. Carpenter recipe with the block. **Next up: farming** `G2b`**.** Sorting S0/S1 and farming G0/G1/G2a are done; `S2` waits on butterfly items.

### `CORE-E1a` ~~— Escritoire block + empty GUI (M)~~ ✅

**Done:** Placeable `reforestry:escritoire`, empty desk GUI, inventory save/reload, carpenter recipe (500 mB seed_oil). `#minecraft:mineable/axe`. See `queries/core-e1a.md`.

### `CORE-E1b` ~~— Escritoire memory game (M)~~ ✅

**Done:** Token memory game + probe on bee/tree specimens; shared `GeneticsUtil.getResearchComplexity`; menu-button clicks + `reforestry:escritoire_game_sync`. See `queries/core-e1b.md`, `queries/core-escritoire.md`.

### `CORE-E1c` ~~— Research notes from escritoire (M)~~ ✅

**Done:** SUCCESS fills result slots with mutation research notes (prefer unresearched via `IBreedingTracker`) and bee products/specialties. Trees notes-only. See `queries/core-e1c.md`.

### `CORE-E5` ~~— Escritoire BER (M)~~ ✅

**Done:** Desk BER + specimen on analyze slot via BE update tag; item special TESR. See `queries/core-e5.md`.

### `CORE-V2` ~~— Beekeeper villager (M)~~ ✅

**Done:** Beekeeper POI on `escritoire`; CE `ApicultureVillagers` trades via 26.2 TradeSet datapack; apiarist houses under `structure/village/` + jigsaw inject. See `queries/core-v2-beekeeper.md`.

---



## Wave 2 — Energy (`reforestry:energy`) — **FE2 done**

CE impl: `forestry.core.content.energy` (~22 Java). **Only** peat, biogas, clockwork. Our package: `com.leon1236.reforestry.energy`. Extend existing `api.fuels` (do not add a second fuels API). Team Reborn `EnergyStorage` already used by factory/alveary. Engine textures already under `textures/block/engine_`*.

### `FE0` — Module shell + fuels (S) — **done** (2026-08-20)

**Player:** Module toggles; peat/biogas fuels registered (no block yet).  
**Done:** `ModuleEnergy` (`reforestry:energy`); `EngineBronzeFuel` / `EngineCopperFuel` on `api.fuels`; maps seeded from CE `setupApi` (Forestry milk, no ethanol). See `queries/energy-FE0.md`.

### `FE1` — Peat engine (M) — **done** (2026-08-20)

**Player:** Place `peat_engine`, burn peat, power a centrifuge **without** `debug_creative_energy`.  
**Done:** `reforestry:peat_engine` block/tile/menu/screen/BER. Pushes FE along facing. See `queries/energy-FE1.md`.

### `FE2` — Biogas + clockwork (M) — **done** (2026-08-20)

**Player:** `biogas_engine` burns fluid fuel (needs heat / lava); `clockwork_engine` winds, no menu, outputs FE. Overwind deals `reforestry:clockwork` damage.  
**Done:** `reforestry:biogas_engine` + `reforestry:clockwork_engine`. No solar/combustion. See `queries/energy-FE2.md`.

---



## Wave 3 — Sorting (parallel with Wave 2)

CE impl: `forestry.core.content.sorting` (~24 Java). API: `forestry.api.core.genetics.filter` → `com.leon1236.reforestry.api.genetics.filter`. Our module: `com.leon1236.reforestry.sorting`.

### `S0` — Filter API + module (S) — **done** (2026-08-20)

**Exit:** Module toggles; `IFilterManager` holds default rules.  
**Done:** `ModuleSorting` (`reforestry:sorting`); `FilterData(Identifier, IGenome, String)` over `IndividualItems`; `PluginManager.runFilterRegistration` + `IFilterRegistration` (not CE `IGeneticRegistration`). No block, no Fabric attachments. See `queries/sorting-S0.md`.

### `S1` — Genetic filter block (M) — **done** (2026-08-20)

**Player:** Place `genetic_filter`; GUI rules accept bee/tree species A, reject dirt / species B.  
**Done:** Block/tile/menu `reforestry:genetic_filter`. Custom `ItemStorage.SIDED` insert routing. Bee + tree filter rules. Recipe/tags/loot/tab. See `queries/sorting-S1.md`.

### `S2` — Butterfly rules (S) — **done** (2026-08-21)

**Player:** Genetic filter rules for flutter/butterfly/serum/caterpillar/cocoon; moths appear in picker too.  
**Done:** `LepidopterologyFilterRuleType` + `LepidopterologyFilterRule`; register via `IFilterRegistration`; species picker discovers `ForestrySpeciesTypes.BUTTERFLY` (CE + ET6 `moth_*`). See `queries/sorting-S2.md`.

---



## Wave 4 — Farming then cultivation — **done** (2026-08-21)

CE impl: `src/farms/java/forestry/agriculture` (~103 Java). API: `forestry.api.agriculture` (not `api.farming`). Module ids still `farming` / `cultivation`. Our packages: `farming` + `cultivation`. Soft energy for gearbox.

**Skippable leftover (not a later stage):** CONTROL farm blocks cannot visually connect redstone dust. CE uses NeoForge `FarmBlock.canConnectRedstone`; 26.2 vanilla + Fabric API have no equivalent without `isSignalSource` (which would make the farm emit power). Control still cancels the matching side from `Level.getSignal`. Farm shape uses rectangular hooks, not CE `FarmPattern`. See `queries/farming-wave4-plan.md`.

### `G0` — Agriculture API + farming module (S) — **done** (2026-08-20)

**Exit:** Module toggles; `IForestryApi.getFarmingManager()` exists. No farm blocks.  
**Done:** `api.agriculture` + farm multiblock next to alveary; `ModuleFarming` (`reforestry:farming`); fake manager when farming is off; `fertilizer_compound` = 500. No blocks/tiles/menus/circuits. See `queries/farming-G0.md`.

### `G1` — Farm structure blocks (M) — **done** (2026-08-20)

**Exit:** All 55 farm blocks in the agriculture tab; placeable; survival-craftable.  
**Done:** `FeatureBlockTable` + `FarmingBlocks` (not EntityBlock). Vanilla overlay models instead of `neoforge:composite`. 55 recipes/loot/unlocks; tube PREFIX remap. See `queries/farming-G1.md`.

### `G2` — Multiblock + GUI + IO (L) — split

**Player:** Valid farm forms; GUI opens; hatch/valve/gearbox accept items/fluids/FE (FE optional if energy off).  
**Copy:** `FarmController`, `MultiblockLogicFarm`, inventories, hydration/fertilizer, `ContainerFarm`/`GuiFarm`.

### `G2a` — Multiblock assemble (M) — **done** (2026-08-20)

**Player:** A valid farm forms. Invalid shapes show `needPlainBand` / `needPlainInterior` / `needGearbox` on right-click.  
**Done:** `FarmBlock` is `BlockStructure`; tiles `reforestry:plain`/`gearbox`/`hatch`/`valve`/`control`; `FarmController extends RectangularMultiblockControllerBase` (not CE `FarmPattern`); BAND on `maxY-1`; stub `IFarmHousing`. No GUI. See `queries/farming-G2a.md`.

### `G2b` — Inventory + GUI (M) — **done** (2026-08-20)

**Player:** Assembled farm opens the farm GUI.  
**Done:** `ContainerFarm` / `ScreenFarm`, controller inventory + WATER tank + FARM socket. Layouts only. See `queries/farming-G2b.md`.

### `G2c` — Hatch / valve / energy (M) — **done** (2026-08-20)

**Player:** Hatch moves items; valve takes fluid; gearbox accepts FE (optional if energy off).  
**Done:** Gearbox `SimpleEnergyStorage` + `EnergyStorage.SIDED`; hatch product export + `ItemStorage.SIDED`; valve `FluidStorage.SIDED` to controller water tank; control `cancelTask`. See `queries/farming-G2c.md`.

### `G3` — Crops logic end-to-end (M) — **done** (2026-08-20)

**Player:** Farm with crops circuit plants/harvests wheat with water + fertilizer.  
**Done:** `reforestry:crops` (wheat/potato/carrot/beetroot), bronze managed + manual circuits, `FarmManager` plant/harvest, water droplets, `Level.isLoaded`, JEI `reforestry:farming`. See `queries/farming-G3.md`.

### `G4a` — Farm types batch A (M) — **done** (2026-08-20)

**Types:** gourd, shroom, poales, succulentes, infernal.  
**Done:** CE `DefaultFarms` soils/farmables + CE circuit map (obsidian gourd both, apatite shroom both, diamond poales manual, gold succulentes manual, blaze infernal managed). See `queries/farming-G4a.md`.

### `G4b` — Farm types batch B (M) — **done** (2026-08-21)

**Types:** ender, arboreal, peat, orchard, cocoa. Soft arboriculture.  
**Done:** CE `DefaultFarms` remaining types. See `queries/farming-G4b.md`.

### `CU0` — Cultivation module shell (S) — **done** (2026-08-21)

**Deps:** farming module present.  
**Done:** `ModuleCultivation` (`reforestry:cultivation`) depends `core` + `farming`; empty `CultivationClientHandler`; no blocks. See `queries/farming-CU0.md`.

### `CU1` — One planter playable (M) — **done** (2026-08-21)

**Player:** Managed `farm_crops` planter farms wheat.  
**Ids:** `{type}_managed` / `{type}_manual` (`FeatureGroup.IdentifierType.SUFFIX`, not the default PREFIX) for `arboretum`, `farm_crops`, `farm_mushroom`, `farm_gourd`, `farm_nether`, `farm_ender`, `peat_bog`. Examples: `reforestry:farm_crops_managed`, `reforestry:arboretum_manual`. Register all blocks; wire crops first.  
**Done:** All 14 ids registered SUFFIX. Crops managed/manual use `TilePlanter` + energy/fluid/item. See `queries/farming-CU1.md`.

### `CU2` — Remaining planters (M) — **done** (2026-08-21)

**Player:** All seven planter kinds farm (managed + manual).  
**Done:** Remaining six kinds use G4 farm logics. 21 recipes / 14 loot / 21 unlocks. Craft tubes stay CE-exact (arboretum gold ≠ copper circuit). See `queries/farming-CU2.md`.

---



## Wave 5 — Genetics public API (blocker for butterflies + addons)

Bee/tree **engine works**. Public CE facade is missing: no `ISpecies` / `IIndividual` / `IGeneticManager` in `api/`. `IForestryPlugin` already has apiculture / arboriculture / circuits / filter / farming.

### `GP0a1` — Public species/individual types (M)

**Outcome:** `api` has `ISpecies`, `IIndividual`, `IIndividualLiving`, `ISpeciesType`, `ILifeStage`, `BeeLifeStage`, `TreeLifeStage`. Existing bee/tree classes implement them (move or adapter — do not fork a second genome).  
**Lookup:** CE `api/core/genetics/ISpecies.java` etc.  
**Prompt:** Stage `GP0a1`. Facade over live registries. No new species.

### `GP0a2` — Genetic manager + taxonomy (M)

**Outcome:** `IGeneticManager`, `IMutationManager`, `ITaxon`, `ForestryTaxa`, `TaxonomicRank`; `IForestryApi.getGeneticManager()`.  
**Deps:** `GP0a1`.  
**Prompt:** Stage `GP0a2`.

### `GP0a3` — Individual item handler (M)

**Outcome:** CE-shaped `IIndividualHandlerItem` (`isIndividual`, `get`, `filter`) wrapping local `IndividualItems`. Gendustry cannot use a string stage + genome blob.  
**Lookup:** CE `capability/IIndividualHandlerItem.java`.  
**Prompt:** Stage `GP0a3`.

### `GP0b` — Plugin `registerGenetics` (M)

**Outcome:** `IForestryPlugin.registerGenetics(IGeneticRegistration)` with taxon / species-type / flower-type / filter-rule hooks. Builders: `ISpeciesBuilder`, `ISpeciesTypeBuilder`, `ITaxonBuilder`, `IChromosomeBuilder`.  
**Deps:** `GP0a`*.  
**Prompt:** Stage `GP0b`. Default no-op methods so existing plugins compile.

### `GP0c` — Promote bee/tree API + registration parity (M)

**Outcome:** `IBee` / `IBeeSpecies` / `ITree` / `IFruit` live under `api`; `IApicultureRegistration` / `IArboricultureRegistration` grow CE methods addons need (`modifySpecies`, `registerFruit`, activity/jubilance/swarmer, …).  
**Prompt:** Stage `GP0c`. Verify each method in CE before adding.

### `GP0d` — Lepidopterology API shell (M)

**Outcome:** `registerLepidopterology`, `ILepidopterologyRegistration`, butterfly types + `ButterflyLifeStage` / `ButterflyChromosomes`; `BUTTERFLY` species type registered (empty species OK).  
**Prompt:** Stage `GP0d`. API only — content is Track D.

`registerFarming` is done (G0). Also add empty defaults when needed: `registerErrors`, `registerPollen`, `registerClient`.

---



## Wave 6 — Lepidopterology (`reforestry:lepidopterology`)

CE: `src/butterflies/java/forestry/lepidopterology` (~61 Java). **Item ids (CE 1.21.1):** `butterfly`, `butterfly_serum`, `caterpillar`, `cocoon` — **not** `*_ge`. 35 species JSON. Deps: arboriculture + `GP0d`.

### `D0` — Research spike (S)

**Deliverable:** `queries/lepido-ce-inventory.md` — species list, chromosomes, entity/cocoon/nursery, Fabric entity notes, D1–D4 file map. No Java content.  
**Prompt:** Stage `D0`. Read-only research. Mandatory before D1.

### `D1` — Module + life-stage items (M)

**Player:** `/give` a butterfly item with genome; alyzer accepts or shows unsupported-until-pages.  
**Prompt:** Stage `D1`. API already from `GP0d`. Ids as CE 1.21.1.

### `D2` — Species + mutations extract (M)

**Player:** 35 species in tab; mutations load.  
**Do:** `tools/` extract from CE datapack — no hand-typed tables.  
**Prompt:** Stage `D2`.

### `D3` — Entity, cocoons, spawn (L)

**Player:** Release → entity → catch; place cocoon.  
**Ids:** entity `butterfly`; blocks `cocoon`, `cocoon_solid`. Split D3a entity+renderer, D3b cocoons/spawn if needed.  
**Prompt:** Stage `D3`.

### `D4` — Breeding + chest/bag (M)

**Player:** Mate → offspring; `butterfly_chest` recipe; lepidopterist bag works. Enables `S2`.  
**Prompt:** Stage `D4`. This unblocks `CORE-A9b` butterfly chest craft.

---



## Wave 7 — Addons (after GP0c; moths after D)

Config-toggle modules. Ids: `reforestry:gendustry`, `reforestry:extra_bees`, `reforestry:extra_trees`. Standalone — copy into our packages. See `files/addon-integration-mapping.md` and locked order in `queries/wave7-plan.md`.

### Gendustry (modern port from `thedarkcolour-gendustry`)


| ID     | Size | Outcome | Deps |
| ------ | ---- | ------- | ---- |
| `GD0`  | S    | Module shell, tab, tags, config, crafts for 10 parts + 23 upgrades + pollen kit | **done** |
| `GD1`  | S    | Fluids `mutagen`, `liquid_dna`, `protein` + buckets | **done** |
| `GD2`  | M    | Recipe types/caches + mutagen/protein/**DNA** datapack (bee/tree/butterfly) | **done** |
| `GD3`  | M    | Mutagen producer, protein liquefier, **DNA extractor** | **done** |
| `GD4`  | M    | Sampler + gene sample/template (components, wipe, gene_samples tab) | **done** |
| `GD5`  | M    | Mutatron + advanced mutatron | **done** |
| `GD6`  | M    | Imprinter, transposer, replicator | **done** |
| `GD7a` | M–L  | Industrial apiary (`IBeeHousing` + FE, no upgrade modifiers yet) | **done** |
| `GD7b` | M    | Upgrade modifiers (fertility drones, youth mutation −20%) | **done** |
| `GD8`  | S    | 12 errors + JEI (producers + gene-sample subtypes) | **done** |


**Prompt pattern:** `Implement stage {GDn} from queries/wave7-plan.md. Port from thedarkcolour-gendustry into com.leon1236.reforestry.gendustry. No gendustry mod dependency.`

Skip Binnie Genetics serums/isolator (Gendustry is the modern line).

### Extra Bees (extract Binnie data — do not translate 1.12 Java)


| ID    | Size | Outcome | Deps |
| ----- | ---- | ------- | ---- |
| `EB0` | M    | Extract — **done** (2026-08-21) | none |
| `EB1` | M    | Module + items (combs, drops, frames, ectoplasm, hive blocks) + crafts — **done** | EB0 |
| `EB5` | L    | 7 alveary parts + crafts + stimulator circuits — **done** | EB1, alveary |
| `EB6` | M    | Centrifuge/squeezer datapack (soft-skip missing fluids) — **done** | EB1, factory |
| `EB-FLOWERS` + `EB3` | M | 11 flower types + 25 effects (**before** species) — **done** | EB1, GP0b |
| `EB2a` | L | 25 species + 22 EB + 34 FR `modifySpecies` — **done** | EB-FLOWERS+EB3 |
| `EB2b` | L | 31 species + 34 EB mutations (historic…energetic + GLOWSTONE) — **done** | EB2a |
| `EB2c` | L | 27 species + 45 EB mutations (metallic…nuclear + precious) — **done** | EB2b |
| `EB2d` | L | 9 species + 9 EB mutations (viscous/caustic/virulent) — **done** | EB2c |
| `EB2e` | L | 24 species + 24 EB mutations (dye/quantum/festive/FTB/Botania + INK) — **done** (116 / 168) | EB2d |
| `EB4` | M | Hives water/rock/nether/marble + worldgen + loot — **done** | EB2a |


### Extra Trees (extract + reimplement; designer deferred)


| ID     | Size | Outcome | Deps |
| ------ | ---- | ------- | ---- |
| `ET0`  | M    | Extract — **done** (2026-08-21) | none |
| `ET1a` | L    | 30 new woods + shrub log (`ExtraTreeWoodType`) — **done** | ET0 |
| `ET1b` | L    | Stripped/boats/signs/trapdoor/button/plate (no charcoal walls) — **done** | ET1a |
| `ET2`  | L    | Fruit alleles + ~88 species + mutations — **done** | GP0c, ET1a |
| `ET3`  | L    | Growth features / worldgen — **done** | ET2 |
| `ET4`  | L    | **done** — Lumbermill / press / brewery / distillery (not designer) | factory patterns |
| `ET5`  | L    | Foods / juices / alcohol / hops — **done** | ET4 |
| `ET6`  | M    | 22 moths (`moth_*`) — **done** | Wave 6 |
| `S2`   | S    | Genetic filter butterfly/moth rules — **done** | S1, ET6 |
| `ET-D` | L    | Designer / stained glass / patterns | **deferred** |
| `ET-K` | S    | Bottle rack | **deferred** (never shipped in Binnie) |


---



## Wave 8 — optional (do not start until Waves 0–4 are playable)


| ID            | Size | Now?          | Notes                                                                                                           |
| ------------- | ---- | ------------- | --------------------------------------------------------------------------------------------------------------- |
| `CORE-P1`     | S    | **Superseded** | Replaced by Wave 9 BOOK-0 native almanac (`queries/core-A8-foresters-manual.md`). |
| `TR1`         | S    | Yes           | Optional Trinkets head slot for spectacles. Soft `compat/` + `isModLoaded`. Helmet already works (A5).          |
| `OR3`         | S    | Optional      | 1.12 habitat locator. CE dropped it. Texture leftovers only.                                                    |
| `OR2`         | M    | Late          | 1.12 species database machine.                                                                                  |
| `OR0`         | L    | Late          | 1.12 Habitat Former / Habitat Screen (climatology).                                                             |
| `OR1`         | L    | After OR0     | 1.12 greenhouse multiblock.                                                                                     |
| `CORE-D1..D3` | L    | Only if asked | CE **1.20.1** decorative brick/waxstone/candle families. **Dropped in CE 1.21.1.** Restore-only.                |


---



## Wave 9 — post–Wave 7 polish (plan: `.cursor/plans/remaining_implementation_map_146cd7da.plan.md`)

| Stage | ID | Status | Notes |
|---|---|---|---|
| 0 | DATA-FIX | **done** | Analyzer advancement fix, portable alyzer unlock, root advancement + grant_guide loot |
| 1 | BOOK-0 | **done** | Native Forester's Almanac framework — see `queries/core-A8-foresters-manual.md` |
| 2 | HYGIENE | **done** | Mail lang + ForestryError cleanup; orphan infuser/raintank assets deleted; stub manual lang removed |
| 3 | JEI-CORE + BOOK-CORE | **done** | Core/energy/sorting/extra_bees/gendustry JEI descriptions; 17 core almanac entries |
| 4 | GEN-JEI + BOOK-GENETICS + BOOK-LEPIDO | **done** | Tree/butterfly JEI; mutatron opens all mutation types; genetics/filter + 6 lepidopterology almanac pages |
| 5 | GP-POLISH | **done** | Taxon alleles, getSuitableBiomes, hive-drop overload, client model maps — see `queries/genetics-GP0-polish.md` |
| 6 | FACTORY-DATA | **done** | 17 comb block recipes + advancements; `bog_earth_wax_capsule` alias; advancement re-extract — see `queries/factory-F6-data.md` |
| 7 | FACTORY-VIS + BOOK-MACHINES | **done** | Owner ledger, smelter almanac page, rainmaker client FX; squeezer BER unchanged — see `queries/wave9-stage7.md` |
| 8 | ADDON-POLISH + BOOK-ADDON | **done** | EB centrifuge fallbacks, ET GUI art + liqueurs, Addons almanac category — see `queries/wave9-stage8.md` |
| 9 | CORE-UX + BOOK-FARM-ARBOR | **done** | Farm redstone vis, alyzer taxonomy/climate, 13 farm/arbor pages verified — see `queries/wave9-stage9.md` |
| 10 | BOOK-BEE | **done** | 19 bee/alveary pages + `tools/validate_book.py` — see `queries/wave9-stage10.md` |
| 11 | TR1 | **done** | Optional Trinkets spectacles slot — see `queries/wave9-stage11-tr1.md` |
| 12 | WAVE8-RESTORE | **skipped** | Optional OR0/greenhouse/database — not started (confirm before port) |
| 13 | DOC-SYNC | **done** | Status docs + `queries/wave9-implementation-report.md` |

---

## Suggested next sessions (human order)

1. Wave 9 Stages **0–11 + 13** are **done**; Stage **12** (Wave 8 restore) remains optional.  
2. Optional: Stage 12 OR0 climatology / greenhouse / decorative blocks if desired.  
3. Post-Wave-9 polish: fix remaining almanac recipe refs flagged by `validate_book.py` (core resource-storage CE recipes never ported).

---



## Explicitly not remaining (do not re-port)

- Mail (dropped).  
- DummyBeeEffect for live CE effects (only `NONE` + `EASTER`, same as CE).  
- Alveary 7 parts, wax blocks, bee JEI, pristine/ignoble, bee give command.  
- A-CRAFT survival recipes (`apiary`, `bee_house`, `honeyed_slice`, `ambrosia`, `honey_pot`).  
- A-BREW pollen brewing and A-SNIFF sniffer `amber_drone` loot.  
- A-ALVID alveary ids (`alveary_block`, `alveary_hygroregulator`, `alveary_stabilizer`; no alias).  
- ARB-JEI charcoal pile / grafter JEI (no tree mutation/product categories).  
- ARB-11.9c vanilla/default leaf pollen (`bees.pollinate_vanilla_leaves`).  
- STOR-JEI backpack descriptions (no crate overlay; that is `STOR-CRATE`).  
- F6-ALLOY smelter datapack (CE20 extract; brass/invar/electrum/constantan/silicon are tags, not Re-Forestry items).  
- Naturalist chests + desk analyzer + escritoire block/GUI/game/notes/BER (`CORE-E1a`–`E1c`, `CORE-E5`).  
- CORE-E2 tracker sync, CORE-E3 alyzer pages, A-DISC1/A-DISC2 discovery GUI, CORE-E4 chest/analyzer BER (`bee_chest` ids).  
- CORE-V1 arborist villager (`tree_chest` POI).  
- CORE-V2 beekeeper villager (`escritoire` POI + apiarist houses).  
- Worktable W0–W2. Fabricator JEI transfer (`F-FAB-XFER`; carpenter-style `clickMenuButton`, no transfer packet). Backpacks + crates (`STOR-CRATE` overlay). Apiarist/arborist bag recipes.  
- F-TINT filled can/capsule/refractory. F-ADV recipe-book unlocks (id remaps; engines `peat_engine`/`biogas_engine`/`clockwork_engine` copied in FE1–FE2; remaining skips `wax_capsule`/`portable_analyzer`).  
- Smelter **block texture** (fixed). Carpenter bulk including escritoire; mail still never extracted.  
- Solar / combustion engines (gone in CE 1.21.1).  
- 1.12 habitat_locator / imprinter / wax_cast / minecart beehouse (CE dropped).  
- Decorative ash/wax/waxstone/candle **blocks** vs CE 1.21.1 (dropped; items `brick_ash` etc. still exist).

