# Apiculture gap — Re-Forestry vs ForestryCE / ForestryMC

**Date:** 2026-07-24  
**Primary reference:** `thedarkcolour-ForestryCE` (`ApicultureBlocks`, `ApicultureItems`, `ApicultureTiles`, `ApicultureMenuTypes`, `ApicultureFeatures`, `ModuleApiculture`)  
**Secondary:** `ForestryMC-ForestryMC` (`ItemRegistryApiculture`) for 1.12-only content  
**Local:** `com.leon1236.reforestry.apiculture.*`, `files/implemented-features.md`

---

## Verdict

Phase 4 covers the **indoor breeding loop** (genetics, apiary/bee house, production, mating, mutations).  
CE’s **outdoor + upgrade loop** is mostly still missing: wild hives, scoop/smoker, alveary, frames, armor, climate-gated work, effect behaviors.

---

## Present in Re-Forestry (CE-aligned)

| Feature | Evidence |
|---|---|
| Genetics engine | Alleles / chromosomes / genomes |
| 69 bee species | Matches CE species set |
| Life stages | queen / drone / princess / larvae |
| Apiary + bee house | Blocks, tiles, GUI, progress, particles |
| Breeding + mutations | Mating, lifespan, 114 mutations |
| Comb blocks (17) | `block_bee_comb_*` |
| Honey products + foods | drops, combs, pollen, propolis, honeyed_slice / honey_pot / ambrosia |
| CE-only item ids | `experience_drop`, `amber_drone` registered |

---

## Missing or partial vs ForestryCE

### Blocks / world

| Feature | Status | CE source |
|---|---|---|
| Wild beehives (12 types: forest…nether + swarm) | **Missing** | `ApicultureBlocks.BEEHIVE` / `BlockHiveType` |
| Hive worldgen | **Missing** | `ApicultureFeatures` + `hives/HiveDecorator` |
| Alveary multiblock (7 parts) | **Missing** (core framework **4.5** ready) | `ApicultureBlocks.ALVEARY` / `BlockAlvearyType` |
| Alveary tiles (8) + menus (4) | **Missing** | `ApicultureTiles`, `ApicultureMenuTypes` |
| `wax_block`, `wax_block_refractory` | **Missing** | `ApicultureBlocks` |

### Items / tools

| Feature | Status | Notes |
|---|---|---|
| Frames (untreated / impregnated / proven / creative) | **Done (4.1)** | Frame items + apiary modifiers/wear |
| Scoop + scoop_proven | **Done (4.1)** | Mines `mineable/scoop`; scoops vanilla bees → `bee_vanilla` drone |
| Smoker | **Done (4.1)** | Calms `IHiveTile` (hives land in C) |
| Apiarist armor (helmet/chest/legs/boots) | **Done (4.1)** | Item implements `IArmorApiarist`; repairs with woven silk |

### Systems / behavior

| Feature | Status | Notes |
|---|---|---|
| Bee effect implementations | **Partial** | `BeeEffect` enum exists; CE has ~25 effect classes under `genetics/effects/` |
| Climate gating (temp/humidity) | **Done (4.3)** | `BeeCanWork` vs species ideal + tolerance chromosomes; rain/sky/light |
| Activity / flower requirements | **Done (4.3)** | Real `ActivityType`; `FlowerType` tags + end/photosynthesis; `HasFlowersCache` spiral; GUI error icons |
| Apiarist villager + village house | **Missing** | `ApicultureVillagers`, `ApiaristPoolElement` |
| Mob effects `hakuna_matata` / `matata` | **Missing** | `ApicultureEffects` |
| Pollen brewing recipes | **Missing** | Awkward + pollen → healing / regeneration |
| Sniffer loot → amber_drone | **Missing** | Item exists; loot inject in CE `ModuleApiculture` |
| Bee commands | **Missing** | `CommandBee` |
| Discovery journal | **Missing** | Deferred in `implemented-features.md` |
| Crafting recipes / chest loot for apiculture | **Missing** | Lang strings exist for many unregistered ids |

---

## Only in ForestryMC 1.12 (dropped by CE — not CE parity gaps)

| Feature | Notes |
|---|---|
| `habitat_locator` | Not present in CE Java (0 hits) |
| `imprinter` | Genome cheater tool |
| `cart.beehouse` | Minecart bee house |
| `wax_cast` | Casting item |

These match `queries/forestryMC-vs-forestryCE-content.md` § Apiculture “Only ForestryMC”. Treat as restore-later, not “missing vs CE.”

---

## Suggested CE catch-up order

1. Wild hives + worldgen  
2. Scoop (+ smoker)  
3. Frame items + modifiers (slots already exist)  
4. Climate / activity / flower work checks  
5. Apiarist armor  
6. Alveary multiblock + parts  
7. Real bee effect behaviors  
8. Villager / brewing / sniffer loot polish  

---

## Local registration snapshot (what is wired today)

**Blocks:** `apiary`, `bee_house`, `block_bee_comb_*`  
**Tiles:** `apiary`, `bee_house`  
**Menus:** bee housing only  
**Items:** bee stages + honey products/foods/groups; scoop/smoker/frames/armor
