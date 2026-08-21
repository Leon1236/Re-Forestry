# Extra Bees / Extra Trees — MCP inventory

**Repo:** `ACGaming-Binnie` only (Forge legacy, MC `[1.12.2,1.13)`).  
**Indexed via:** `user-minecraft-mods` MCP (`list_mods`, `list_files`, `search_code`, `get_file`).  
**Date:** 2026-07-26.

## 1. Repos available

| Repo | Role |
|------|------|
| **ACGaming-Binnie** | Sole source: Extra Bees, Extra Trees, Botany, Genetics, Binnie Core, Design |
| *(none)* | No separate Extra Bees / Extra Trees repos indexed |
| thedarkcolour-ForestryCE | Mentions only (comments) — **no EB/ET code** |
| thedarkcolour-Immersive-Forestry | `docs/ADDON_AUDIT.md`: Binnie/Extra Bees **not audited / not imported** |

Gradle modules (from `build.gradle`): `core`, `botany`, `botany-api`, `design`, `design-api`, `extrabees`, `extratrees`, `extratrees-api`, `genetics`, `genetics-api`, `all`.

Modids (`core/.../Constants.java`, `mcmod.info`): `binniecore`, `extrabees`, `extratrees`, `botany`, `genetics`, `binniedesign`.

## 2. Package structure

### Extra Bees — `binnie.extrabees.*`
Root: `extrabees/src/main/java/binnie/extrabees/`

| Package | Purpose |
|---------|---------|
| (root) `ExtraBees.java` | `@Mod` entry |
| `blocks`, `blocks.type` | Hives, ectoplasm |
| `circuit` | Stimulator circuits |
| `client` | Bee models |
| `genetics`, `genetics.effect`, `genetics.gui.*` | Species, effects, analyst/database UI |
| `gui` | Database pages |
| `init` | Block/Item/Recipe register |
| `items`, `items.types` | Combs, drops, frames, misc |
| `machines.*` | Alveary modules |
| `modules` | ForestryModule plugins |
| `proxy`, `utils`, `worldgen` | Proxies, config, hive worldgen |

### Extra Trees — `binnie.extratrees.*`
Root: `extratrees/src/main/java/binnie/extratrees/`

| Package | Purpose |
|---------|---------|
| (root) `ExtraTrees.java` | `@Mod` entry |
| `alcohol`, `alcohol.drink` | Cocktails / glassware |
| `blocks`, `blocks.wood`, `blocks.decor`, `blocks.property` | Wood family, hedges, fences, hops |
| `carpentry` | Design system |
| `gen` | WorldGen tree shapes (~25 classes) |
| `genetics`, `genetics.fruits`, `genetics.gui.analyst` | Trees, fruit alleles, moths, UI |
| `items` | Food, hammers, misc |
| `liquid` | Juices / resins |
| `machines.*` | Lumbermill, press, brewery, distillery, designer |
| `modules` | ForestryModule plugins |
| `wood`, `wood.planks` | Log/plank enums |
| `village` | Hope-field village |

API: `extratrees-api/.../binnie.extratrees.api` (+ `recipes`).

## 3. Approximate counts

| Content | Count | Primary path |
|---------|------:|--------------|
| Bee species (lang keys) | **~126** | `extrabees/.../lang/en_US.lang` `extrabees.species.*.name` |
| Bee branches | **~30** | `ExtraBeeBranchDefinition.java` |
| Bee mutations | **~150–200** (est.) | Per-species `registerMutation` in `ExtraBeeDefinition.java` (71 KB) |
| Tree species | **~100–110** | `ETTreeDefinition.java` (111 KB) |
| Tree mutations | **~95** | `ExtraTreeMutation.init()` |
| Fruit alleles | **~55** | `AlleleETFruitDefinition.java` |
| Butterfly/moth species | **22** | `ButterflySpecies.java` (mutations empty) |
| Effect alleles | **24** | `ExtraBeesEffect.java` |
| Flower alleles | **11** | `ExtraBeesFlowers.java` |
| Honey comb metas | **~80 enum / ~70 active** | `EnumHoneyComb.java` |
| Misc bee items | **~30** | `ExtraBeeItems.java` |
| Food items | **~60** | `Food.java` |
| ET log types | **40** | `EnumETLog.java` |
| ET plank types | **36** | `ExtraTreePlanks.java` |
| Wild hives | **4** | `EnumHiveType` WATER/ROCK/NETHER/MARBLE |
| EB alveary machines | **7** | `ExtraBeeMachines` |
| ET machines | **7 registered / ~5 active** | `ExtraTreeMachine` (Nursery stub) |
| Stimulator circuits | **9** | lang `for.binnie.circuit.stimulator.*` |

## 4. Key registration / entry classes

| Role | Path |
|------|------|
| Extra Bees `@Mod` | `extrabees/.../ExtraBees.java` |
| Extra Trees `@Mod` | `extratrees/.../ExtraTrees.java` |
| EB core module | `extrabees/.../modules/ModuleCore.java` (`@ForestryModule`) |
| EB alveary module | `.../ModuleAlveary.java` |
| EB frames module | `.../ModuleFrames.java` |
| ET core | `extratrees/.../modules/ModuleCore.java` |
| ET wood | `.../ModuleWood.java` (large) |
| ET machines | `.../ModuleMachine.java` |
| ET alcohol / carpentry / kitchen / databases | `ModuleAlcohol`, `ModuleCarpentry`, `ModuleKitchen`, `ModuleTreeDatabase`, `ModuleMothDatabase` |
| Bee species + mutations | `genetics/ExtraBeeDefinition.java` |
| Tree species | `genetics/ETTreeDefinition.java` |
| Tree mutations | `genetics/ExtraTreeMutation.java` |
| Species register hook | `ModuleCore.onRegisterSpecies(AlleleSpeciesRegisterEvent)` |

## 5. Assets layout (sample paths)

Namespace: `assets/extrabees/`, `assets/extratrees/`.

```
extrabees/src/main/resources/assets/extrabees/
  blockstates/{alveary,ectoplasm,hive}.json
  lang/{en_US,en_GB,ru_RU,zh_CN}.lang
  models/item/{honey_comb,honey_drop,propolis,dictionary}.json
  models/item/frames/hive_frame.*.json
  models/item/misc/{iron_dust,diamond_shard,...}.json
  textures/blocks/liquids/{acid,poison,liquidnitrogen}.*   # PNGs often not in MCP index

extratrees/src/main/resources/assets/extratrees/
  blockstates/{germlings,hops,machine,shrub_log}.json
  blockstates/glassware/*.json
  blockstates/pods/{banana,brazil,coconut,...}.json
  lang/{en_US,...}.lang
  models/block/saplings/tree_{default,fruit,jungle,palm,...}.json
  models/item/foods/{apple,mango,...}.json
  models/item/{arborist_database,carpentry_hammer,door}.json
```

Runtime texture refs (from Java):  
`extratrees:blocks/logs/<uid>_trunk|_bark`, `extratrees:blocks/planks/<Name>`, `extrabutterflies` textures under `butterflies/`.

## 6. Java-defined vs data-driven

**Almost entirely Java-defined (1.12 era):**
- Species, genomes, mutations, alleles
- Recipes via `RecipeUtil.addRecipe`, `RecipeManagers.centrifuge/squeezer/carpenter/still`, custom managers
- OreDict registration in code
- No `data/*/recipes/*.json` content pack (Forge 1.12 style)

**JSON assets only:** blockstates, item/block models, lang (`.lang` not JSON).

## 7. Forestry / genetics dependency

- **Hard:** Forestry (`mcmod.info` / `required-after` via Binnie Core)
- **APIs used:** `forestry.api.apiculture.*`, `arboriculture.*`, `lepidopterology.*`, `genetics.*`, `circuits.*`, `modules.ForestryModule`, `recipes.RecipeManagers`, `fuels.FuelManager`
- Species registered onto Forestry bee/tree roots (`BeeManager`, `TreeManager`, `ButterflyManager`)
- Extra Bees extends Forestry alveary via machine group + `IBeeModifier`/`IBeeListener`
- Soft deps: IC2, Tech Reborn, Botania, Big Reactors (oredict / optional comb products)
- Also depends on **Binnie Core** GUI/machines/genetics helpers and optionally **Binnie Genetics** analyst plugins / **Design** for carpentry

## 8. Major subsystems to port

1. **Genetics data extract** — bees (`ExtraBeeDefinition` + branches + mutations), trees (`ETTreeDefinition` + `ExtraTreeMutation`), fruit alleles, effects, flowers  
2. **Products** — combs/centrifuge, drops, propolis, food juices/oils, misc dusts/dyes  
3. **Wood family** — ModuleWood: logs, planks, slabs, fences, doors, leaves, saplings, shrub logs (~36 plank × many block kinds)  
4. **Worldgen** — hive gen (`ExtraBeesWorldGenerator`), tree `WorldGen*` classes, village hops field  
5. **Alveary machines** — mutator, frame, rain shield, lighting, stimulator (+ circuits), hatchery, transmission  
6. **Kitchen / alcohol** — lumbermill, fruit press, brewery, distillery, glassware, liquids  
7. **Carpentry / design** — woodworker/panelworker/glassworker (needs design module)  
8. **UI** — Binnie database/analyst pages, machine windows (heavy Binnie Core GUI dependency)  
9. **Frames + dictionary** — hive frames, apiarist database item  
10. **Optional later** — butterflies (22 spp, no mutations), Botany/Genetics sibling mods  

## ForestryCE / Immersive Forestry

- ForestryCE: comment in `GeneticsUtil.java` about Extra Bees translation keys; comment in `DefaultTreeSpecies` about Extra Trees girth — **not integration**.  
- Immersive: `docs/ADDON_AUDIT.md` explicitly lists Binnie / Extra Bees as **not audited, do not import**.
