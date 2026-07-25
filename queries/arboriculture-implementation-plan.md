# Arboriculture gap plan — Re-Forestry vs Forestry CE / MC

**Date:** 2026-07-25  
**Status tracker:** `files/implemented-features.md` (**Phase 5 done**; polish left: **11.9c**, deferred **11.9h**)  
**Primary reference:** `thedarkcolour-ForestryCE`  
**Secondary:** `ForestryMC-ForestryMC` (CE-dropped restore-later), `thedarkcolour-Immersive-Forestry` (boats still present; chest-boat capability wiring), local `queries/phase5-arboriculture-ce-research.md`

**How to use this doc:** Paste **one** `ARB-`* section into another agent session. Each section is self-contained.

---

## 1. Executive summary

**Phase 5 complete (11.1–11.9b + 11.9d–g + 11.9i):** shared genetics, 43 Forestry wood types × wood kinds (logs→signs, fireproof), tree chromosomes + 50 species + 40 mutations, sapling/leaves BEs, pollen mating, bee↔tree pollen on genetic leaves only, grafters + **vanilla/default grafter loot**, client leaf/sapling tinting, species worldgen metadata, default/decorative leaves, CE growth pipeline, **all 50 species Feature shapes**, wild `TreeDecorator` + Fabric biome hook + spawn config, boats, fruit pods, charcoal, recipes/tags/loot, creative/models/lang, **tree spawn admin commands**, integration pass (`queries/arb-11.9i-integration.md`).

**Remaining vs CE (post–Phase 5 polish):**

1. Bee↔tree pollen on vanilla/default leaves (11.9c).
2. Arborist villager (11.9h — deferred; no `tree_chest`).

---

## 2. Feature gap matrix


| Feature                                       | CE                    | MC 1.12         | Re-Forestry        | Notes                                                                     |
| --------------------------------------------- | --------------------- | --------------- | ------------------ | ------------------------------------------------------------------------- |
| Wood block families (43 types, doors/signs/…) | Yes                   | Smaller set     | **Done**           | `ArboricultureBlocks`                                                     |
| Vanilla fireproof wood                        | Yes                   | Yes             | **Done**           | Phase 3 + arbori groups                                                   |
| Tree genetics (10 chromosomes, 50 spp)        | Yes                   | 35 spp          | **Done**           | Genome alleles present                                                    |
| Sapling / genetic leaves lifecycle            | Yes                   | Yes             | **Done**           | Genetic fruit + default/decorative leaves (11.8b)                         |
| Leaf fruit pick / drops                       | Yes                   | Yes             | **Done**           | Genetic + default-fruit leaves                                            |
| Fruit pods (dates/papaya/coconut/cocoa)       | Yes                   | Yes             | **Done**           | 11.9a — `BlockFruitPod` / `TileFruitPod`; cocoa uses vanilla plant helper |
| Per-species growth shapes                     | Yes (~45 `Feature*`)  | `WorldGen*`     | **Done**           | All CE `setTreeFeature` bindings wired (11.8d+e)                          |
| Default / decorative leaves                   | Yes                   | Yes             | **Done**           | 11.8b                                                                     |
| Wild tree worldgen                            | `TreeDecorator`       | `TreeDecorator` | **Done**           | Fabric `BiomeModifications` + hive-style JSON                             |
| Species rarity / temp / humidity              | Yes                   | Climate growth  | **Done**           | 11.8a                                                                     |
| Boats + chest boats                           | Yes                   | No modern boats | **Done**           | 11.8g                                                                     |
| Grafter on genetic leaves                     | Yes                   | Yes             | **Done**           | Local drops + grafter modifier                                            |
| Grafter on vanilla leaves                     | `GrafterLootModifier` | Yes             | **Done**           | Fabric `MODIFY_DROPS`                                                     |
| Bee↔tree pollen (vanilla leaves)              | Yes + convert         | Yes             | **Partial**        | Genetic `TileLeaves` only (11.9c open)                                    |
| Charcoal / log pile / ash                     | `ModuleCharcoal`      | Yes             | **Done**           | 11.9d — enclose + ignite → ash                                            |
| Arborist villager                             | Yes                   | Yes             | **Deferred**       | 11.9h — needs arborist chest POI                                          |
| Tree commands                                 | Yes                   | Yes             | **Done**           | 11.9g — spawnTree / spawnForest                                           |
| Treealyzer / database UI                      | Dropped               | Yes             | **Restore-later**  | CE dropped `database`                                                     |
| Treekeeping modes                             | Dropped/commented     | Yes             | **Restore-later**  | CE unused                                                                 |
| Greenhouse / arboretum                        | Dropped / farming     | Yes             | **Restore-later**  | Not arbori core                                                           |
| Recipes wood family                           | Datagen               | Yes             | **Done**           | Wood + boats + grafter + charcoal + vanilla fireproof catch-up (11.9e)    |
| Client leaf/sapling models                    | Yes                   | Yes             | **Done**           | 11.7                                                                      |
| Config `treesSpawnNaturally`                  | Yes                   | TreeConfig      | **Done**           | `ForestryConfig` → `config/reforestry/server.properties`                  |


---

## 3. Copy-paste agent sections

---

### ARB-11.8a — Species worldgen metadata (rarity, climate, generator hooks)

**Goal:** Give each tree species the data CE uses for wild spawning and growth: rarity, temperature/humidity, tree feature factory, decorative leaves, vanilla leaf/sapling mappings. Without this, worldgen cannot pick the right tree for a biome.

**Prerequisites:** None (builds on 11.4).

**Scope**

- **In:** Extend `ITreeSpeciesBuilder` / `TreeSpeciesBuilder` / `ITreeSpecies` / `TreeSpecies`; wire fields from CE `DefaultTreeSpecies`; keep genome/mutations as today.
- **Out:** Actually placing trees in the world; Feature class bodies; boats.

**Reference sources (verified)**

- CE: `forestry/api/plugin/ITreeSpeciesBuilder.java` (`setRarity`, `setTreeFeature`, `setGenerator`, `addVanillaStates`, `addVanillaSapling`, `setDecorativeLeaves`)
- CE: `forestry/arboriculture/TreeSpecies.java` (`getRarity`, `getTemperature`, `getHumidity`, `getGenerator`)
- CE: `forestry/plugin/DefaultTreeSpecies.java` (all 50 species: `.setRarity(...)`, `.setTemperature(...)`, `.setHumidity(...)`, `.setTreeFeature(FeatureX::new)`)
- CE: `forestry/apiimpl/plugin/TreeSpeciesBuilder.java`

**Local touch points**

- `src/main/java/com/leon1236/reforestry/api/plugin/ITreeSpeciesBuilder.java` (today: authority/genome/mutations only)
- `src/main/java/com/leon1236/reforestry/arboriculture/genetics/TreeSpeciesBuilder.java`
- `src/main/java/com/leon1236/reforestry/arboriculture/genetics/ITreeSpecies.java` / `TreeSpecies.java`
- `src/main/java/com/leon1236/reforestry/arboriculture/genetics/DefaultTreeSpecies.java` (50 `registerSpecies` calls — no rarity/feature yet)
- Prefer extracting rarity/feature lines from CE via a `tools/` script rather than hand-copying.

**Acceptance criteria**

- Every species that CE marks with `setRarity` has the same float locally.
- Species climate matches CE for at least plum/willow/etc. (`setTemperature` / `setHumidity`).
- Builder accepts a tree-feature factory stub (can temporarily point all at `SimpleTreeGenerator` wrapper until ARB-11.8c/d).
- Unit/smoke: registry still loads 50 species; no crash on `PluginManager.runArboricultureRegistration()`.

**Suggested steps**

1. Read CE `ITreeSpeciesBuilder` + local builder; add matching methods (Fabric package names).
2. Expand `TreeSpecies` record/class with rarity, temp, humidity, generator, vanilla states, decorative stack.
3. Script-extract or carefully port `DefaultTreeSpecies` builder calls from CE.
4. Compile; confirm creative tab still lists 50 saplings.

**Fabric notes:** Climate types already exist as `com.leon1236.reforestry.api.core.TemperatureType` / `HumidityType` (used by bees). Reuse those — do not invent new enums.

**Estimated size:** M

---

### ARB-11.8b — Default, fruit-default, and decorative leaves

**Goal:** Add the three BE-less leaf block families CE uses for worldgen and shears/pick-block, so wild trees do not create hundreds of `TileLeaves` and players get decorative leaves.

**Prerequisites:** ARB-11.8a (species can bind decorative leaves + vanilla states).

**Scope**

- **In:** `ForestryLeafType`, `BlockDefaultLeaves`, `BlockDefaultLeavesFruit`, `BlockDecorativeLeaves`, registration groups, item classes, models/loot/tags, shear/pick-block from genetic leaves.
- **Out:** Pods; wild decorator; Feature trunk shapes.

**Reference sources (verified)**

- CE: `forestry/arboriculture/features/ArboricultureBlocks.java` — `LEAVES_DEFAULT`, `LEAVES_DEFAULT_FRUIT`, `LEAVES_DECORATIVE`
- CE: `forestry/arboriculture/blocks/ForestryLeafType.java`
- CE: `forestry/arboriculture/blocks/BlockDefaultLeaves.java`, `BlockDefaultLeavesFruit.java`, `BlockDecorativeLeaves.java`
- CE: `forestry/arboriculture/items/ItemBlockLeaves.java`, `ItemBlockDecorativeLeaves.java`
- CE: `forestry/arboriculture/worldgen/DefaultTreeGenerator.java` (worldgen path uses `woodType.setDefaultLeaves(...)`)

**Local touch points**

- Create under `src/main/java/com/leon1236/reforestry/arboriculture/blocks/`
- Register in `.../features/ArboricultureBlocks.java` (today only `SAPLING` + `LEAVES`)
- Client: `ArboricultureClientHandler` / leaf tint already handles genetic leaves — extend for default/decorative
- Assets: copy/rename from CE datagen (`default_leaves_*`, `decorative_leaves_*`); pods JSON already present but unused

**Acceptance criteria**

- 50×3 leaf variants register under `reforestry:` ids matching CE naming (`*_default_leaves`, etc.).
- Growing a genetic sapling can still place genetic `leaves`; worldgen path (once hooked) can place default leaves without BEs.
- Shears / creative pick on genetic leaves yields decorative stack for that species.
- No regression on fruit overlay for genetic fruit leaves.

**Suggested steps**

1. Port `ForestryLeafType` (50 statics bound after species registration, CE pattern).
2. Port three block classes + item wrappers; register via `FeatureBlockGroup`.
3. Bind species → leaf type in genetics finalize (CE: `TreeSpeciesType.onSpeciesRegistered`).
4. Wire shear/pick in `BlockForestryLeaves`.
5. Ensure blockstates/models/loot exist (migrate CE JSON with `tools/rename_namespace.py`).

**Fabric notes:** Prefer Fabric API / vanilla leaf properties; avoid Forge ModelData — local genetic leaves already use custom resolvers.

**Estimated size:** L

---

### ARB-11.8c — Growth engine (replace SimpleTreeGenerator)

**Goal:** Port CE’s shared growth pipeline so saplings use real trunk/leaf/pod placement logic (height, girth, leaf distance update), even before every species silhouette exists.

**Prerequisites:** ARB-11.8a; ideally ARB-11.8b for default-leaf placement during worldgen.

**Scope**

- **In:** `FeatureBase` (or local equivalent), `FeatureArboriculture`, `FeatureTree`, `FeatureHelper`, `TreeGrowthHelper`, `TreeBlockTypeLog` / `Leaf`, `TreeContour`, `DefaultTreeGenerator`, `TreeGenHelper`; wire `TileSapling.tryGrow` to species generator.
- **Out:** Full per-species `FeatureLarch` etc. (next sections); wild decorator.

**Reference sources (verified)**

- CE: `forestry/arboriculture/worldgen/FeatureArboriculture.java`
- CE: `forestry/arboriculture/worldgen/FeatureTree.java`
- CE: `forestry/arboriculture/worldgen/DefaultTreeGenerator.java`
- CE: `forestry/arboriculture/genetics/TreeGrowthHelper.java`
- CE: `forestry/core/worldgen/FeatureHelper.java`, `FeatureBase.java`
- CE: `forestry/arboriculture/commands/TreeGenHelper.java`
- Local: `.../worldgen/SimpleTreeGenerator.java` (replace callers, then delete or keep as fallback)

**Local touch points**

- `src/main/java/com/leon1236/reforestry/arboriculture/worldgen/` (expand)
- `src/main/java/com/leon1236/reforestry/arboriculture/tiles/TileSapling.java`
- Possibly `com.leon1236.reforestry.core.worldgen` for `FeatureHelper` / `FeatureBase`

**Acceptance criteria**

- Sapling maturity uses species generator, not hard-coded `SimpleTreeGenerator` for all.
- Girth×girth sapling square still required (parity with CE `TreeGrowthHelper`).
- Leaf `DISTANCE` updated after generation (CE `updateLeaves` logic).
- At least one species using `FeatureTree` base shape grows a trunk + canopy in-game.

**Suggested steps**

1. Port `FeatureHelper` + `FeatureBase` (strip Forge-only bits).
2. Port `FeatureArboriculture` / `FeatureTree` / block-type helpers.
3. Implement `DefaultTreeGenerator` using `WoodAccess` + genetic vs default leaves.
4. Point species builders’ `setTreeFeature` default to `FeatureTree` or `FeatureTreeVanilla`.
5. Update `TileSapling.tryGrow` to call generator/`TreeGenHelper`.
6. Keep `SimpleTreeGenerator` only as temporary fallback if a species has null generator.

**Fabric notes:** CE `IPlantable` / Forge `canSustainPlant` in related code → use Fabric/vanilla plantable checks already used by local saplings if present.

**Estimated size:** L

---

### ARB-11.8d — Per-species Feature shapes (batch A: vanilla + temperate)

**Goal:** Port the first half of CE’s hand-authored tree shapes so common temperate trees look like Forestry, not generic blobs.

**Prerequisites:** ARB-11.8c.

**Scope**

- **In:** ~15–20 Feature classes: `FeatureTreeVanilla`, `FeatureSilverLime`, `FeatureSourCherry`, `FeatureWalnut`, `FeatureChestnut`, `FeaturePear`, `FeaturePlum`, `FeatureMaple`, `FeatureBeech`, `FeatureElm`, `FeaturePoplar`, `FeatureWillow`, `FeatureCherryVanilla`, `FeatureDogwood`, `FeatureFeijoa`, plus any tiny helpers they need.
- **Out:** Tropical/giant/conifer batch; wild decorator.

**Reference sources (verified)**

- CE: `forestry/arboriculture/worldgen/Feature*.java` (listed in MCP `list_files` under that package)
- CE: `forestry/plugin/DefaultTreeSpecies.java` (which species → which Feature)

**Local touch points**

- Create `src/main/java/com/leon1236/reforestry/arboriculture/worldgen/Feature*.java`
- Wire in `DefaultTreeSpecies` via `setTreeFeature(...)`

**Acceptance criteria**

- Listed species grow distinct silhouettes vs `FeatureTree` defaults.
- Mutations/genome height/girth still affect size.
- No crash when bonemealing large-girth species.

**Suggested steps**

1. For each CE Feature file: MCP `get_file` → port package/imports → compile.
2. Register factory on matching species in `DefaultTreeSpecies`.
3. World-test 3–5 representative species (oak vanilla, sequoia later, willow hanging leaves).

**Fabric notes:** None special beyond growth engine.

**Estimated size:** L (split across sessions if needed; keep this ID for batch A)

---

### ARB-11.8e — Per-species Feature shapes (batch B: remaining)

**Goal:** Finish the remaining CE Feature classes (tropical, palm, conifer, giants) so all 50 species have CE-accurate generators.

**Prerequisites:** ARB-11.8d.

**Scope**

- **In:** Remaining CE features under `forestry/arboriculture/worldgen/` not done in 11.8d (e.g. `FeatureBaobab`, `FeatureSequoia`, `FeatureKapok`, `FeatureCoconut`, `FeatureDate`, `FeaturePapaya`, `FeatureMahogany`, `FeatureTeak`, `FeatureBalsa`, `FeatureJungle`, `FeatureGiganteum`, `FeaturePine`, `FeatureLarch`, `FeatureFir`, `FeatureSpruce`, … — verify against MCP file list before claiming complete).
- **Out:** Worldgen decorator; boats.

**Reference sources (verified)**

- CE: full `worldgen/` listing via MCP `list_files_tool` on `thedarkcolour-ForestryCE` path_prefix `src/main/java/forestry/arboriculture/worldgen`
- CE: `DefaultTreeSpecies.java` feature bindings

**Local touch points:** same as 11.8d.

**Acceptance criteria**

- Every CE species that calls `setTreeFeature` has a local class wired.
- Spot-check sequoia/baobab/coconut growth in creative.
- `SimpleTreeGenerator` no longer referenced by production growth path.

**Suggested steps**

1. Diff local `worldgen/` vs CE file list; port missing classes.
2. Finish `DefaultTreeSpecies` wiring.
3. Delete or quarantine `SimpleTreeGenerator`.

**Estimated size:** L

---

### ARB-11.8f — Wild tree worldgen (`TreeDecorator` + Fabric biome hook)

**Goal:** Forestry trees appear naturally in matching climate biomes, like wild beehives already do.

**Prerequisites:** ARB-11.8a (rarity/climate), ARB-11.8c (generator that can place without saplings, `forced=true` via `TreeGenHelper`).

**Scope**

- **In:** `TreeDecorator` Feature; `ArboricultureFeatures`; configured/placed feature JSON; `BiomeModifications`; config `treesSpawnNaturally`; optional `custom_tree` datapack feature.
- **Out:** Charcoal; boats; villagers.

**Reference sources (verified)**

- CE: `forestry/arboriculture/worldgen/TreeDecorator.java`
- CE: `forestry/arboriculture/features/ArboricultureFeatures.java` (`tree`, `custom_tree`)
- CE: `src/generated/resources/data/forestry/worldgen/configured_feature/tree.json` → `{"type":"forestry:tree","config":{}}`
- CE: `src/main/resources/data/forestry/forge/biome_modifier/forestry.json` (Forge wires `tree`)
- CE: `forestry/core/config/ForestryConfig.java` field `treesSpawnNaturally`
- Local hive pattern: `com.leon1236.reforestry.apiculture.features.ApicultureFeatures` + `HiveDecorator` + `data/reforestry/worldgen/*/hive.json`

**Local touch points**

- Create `.../arboriculture/worldgen/TreeDecorator.java`
- Create `.../arboriculture/features/ArboricultureFeatures.java`
- Call `init()` from `ModuleArboriculture`
- Add `data/reforestry/worldgen/configured_feature/tree.json` + `placed_feature/tree.json`
- Config class under core (mirror hive/bee configs if present)
- Empty `data/reforestry/forge/biome_modifier/` can stay unused — do **not** rely on Forge biome modifiers on Fabric

**Acceptance criteria**

- New chunks in temperate biomes occasionally spawn Forestry trees with rarity>0.
- Config `0` disables all natural tree spawns.
- Species only appear when biome temp/humidity match species (CE cache logic).
- No sapling items left behind; uses default leaves when genome is default (if 11.8b done).

**Suggested steps**

1. Port `TreeDecorator` (replace Forge `IPlantable` sustain check with local plantable helper).
2. Register Feature + ResourceKeys like `ApicultureFeatures`.
3. `BiomeModifications.addFeature(BiomeSelectors.all(), VEGETAL_DECORATION, PLACED_TREE)`.
4. Add JSON twin of hive worldgen files.
5. Add config double; gate in `place()`.
6. Test: `/locate` not needed — fly new chunks; set rarity high temporarily for smoke.

**Fabric notes:** Mirror hive worldgen exactly. CE’s custom Forge biome_modifier type is irrelevant on Fabric.

**Estimated size:** M

---

### ARB-11.8g — Forestry wood boats + chest boats

**Goal:** Players can craft and ride boats (and chest boats) for every Forestry wood type — CE content already textured/lang-copied locally but never registered.

**Prerequisites:** None hard (wood planks exist from 11.2). Soft: creative tab update after items exist.

**Scope**

- **In:** Entity types, boat/chest-boat entities, items ×43, dispenser behavior, client renderer/models, recipes, creative tab entries.
- **Out:** Raft variants; charcoal.

**Reference sources (verified)**

- CE: `forestry/arboriculture/entities/ForestryBoat.java`, `ForestryChestBoat.java`
- CE: `forestry/arboriculture/features/ArboricultureEntities.java`, `ArboricultureItems.java` (`BOAT`, `CHEST_BOAT` groups)
- CE: `forestry/arboriculture/items/ItemForestryBoat.java`, `ForestryBoatDispenserBehavior.java`
- CE: `forestry/arboriculture/client/ForestryBoatRenderer.java`
- Immersive: same paths under `thedarkcolour-Immersive-Forestry` (chest boat `InvWrapper` capability — use Fabric transfer if needed)
- Vanilla 26.2: `net/minecraft/world/entity/vehicle/boat/AbstractBoat.java`, `Boat.java`, `ChestBoat.java`, `BoatItem.java`, `BoatDispenseItemBehavior.java`

**Local touch points**

- Create `.../arboriculture/entities/`, `ArboricultureEntities.java`
- Extend `ArboricultureItems` with boat groups
- Client: `ArboricultureClientHandler`
- Assets: `textures/item/*_boat.png` / `*_chest_boat.png` exist; **item models missing** (0 files); entity boat textures under `textures/entity/boat/`
- Lang keys already in `en_us.json` (`for.boat.grammar`, `entity.reforestry.boat`, per-wood item names in zh etc.)

**Acceptance criteria**

- 43 boat + 43 chest boat items register (`reforestry:<wood>_boat` / `_chest_boat`).
- Crafting: planks pattern like vanilla boats.
- Place in water, paddle, break drops correct boat; chest boat has inventory.
- Dispenser places boat.
- Creative tab lists them.

**Suggested steps**

1. Read vanilla 26.2 `AbstractBoat` / `BoatItem` — **do not** copy CE 1.20 `Boat` subclass blindly (package + constructor changed: `dropItem` Supplier, `ValueInput`/`ValueOutput`).
2. Design `ForestryBoat` extending `AbstractBoat` or `Boat` with wood-type synched data (CE pattern adapted).
3. Register entities + item group + dispenser.
4. Generate item models pointing at existing textures; register layered entity models like CE renderer.
5. Add recipes (script from CE datagen if available).
6. In-game test one wood type end-to-end, then bulk.

**Fabric notes:** Chest inventory: CE/Immersive use Forge capabilities; on Fabric use `Inventory`/`Container` on entity + Fabric transfer API if other mods need access. Prefer vanilla `AbstractChestBoat` patterns from `Minecraft-26.2`.

**Estimated size:** L

---

### ARB-11.9a — Fruit pods (blocks + spawn wiring)

**Goal:** Dates/papaya/coconut (and cocoa) grow as pods on trunks like CE, instead of silently failing.

**Prerequisites:** ARB-11.8c (extras generation calls `trySpawnFruitBlock`); wood log tags for palm/papaya/coconut.

**Scope**

- **In:** `ForestryPodType`, `BlockFruitPod`, `TileFruitPod`, register `PODS` group, implement `PodFruit.trySpawnFruitBlock`, CE log tags, ripening/pick.
- **Out:** Leaf ripening fruits (already partial); butterflies on leaves.

**Reference sources (verified)**

- CE: `forestry/arboriculture/PodFruit.java`
- CE: `forestry/arboriculture/blocks/BlockFruitPod.java`, `ForestryPodType.java`
- CE: `forestry/arboriculture/tiles/TileFruitPod.java`
- CE: `ArboricultureBlocks.PODS`
- Local stub: `com.leon1236.reforestry.arboriculture.genetics.PodFruit` (`trySpawnFruitBlock` returns `false`)
- Local assets: `blockstates/pods_*.json` already present

**Local touch points**

- `PodFruit.java`, `ArboricultureBlocks.java`, `ArboricultureTiles.java`
- New block/tile classes under arboriculture packages
- Tags: CE `ForestryTags.Blocks.PALM_LOGS` etc. → `reforestry` tags

**Acceptance criteria**

- Growing a dates/papaya/coconut genome tree places pod blocks on valid log faces.
- Cocoa uses vanilla cocoa planting helper.
- Pods ripen and can be harvested for fruit items.
- Creative can place pods without crash.

**Suggested steps**

1. Port pod type enum + block/tile.
2. Register feature group `pods`.
3. Implement `trySpawnFruitBlock` mirroring CE (cocoa vs `setFruitBlock`).
4. Ensure growth `generateExtras` calls fruit spawn.
5. Test three pod species + cocoa apple-oak hybrid genome if applicable.

**Estimated size:** M

---

### ARB-11.9b — Grafter loot for vanilla / default leaves

**Goal:** Using a grafter on vanilla (and default) leaves can drop Forestry saplings when those leaves map to a species — CE’s global loot modifier behavior.

**Prerequisites:** ARB-11.8a (vanilla state → species map); ARB-11.8b helpful for default-fruit leaves.

**Scope**

- **In:** Fabric-friendly loot hook equivalent to CE `GrafterLootModifier`; damage grafter; fruit pick from bearers.
- **Out:** Changing genetic-leaf drop logic (already mostly done in `BlockForestryLeaves.getDrops`).

**Reference sources (verified)**

- CE: `forestry/arboriculture/loot/GrafterLootModifier.java`
- Local: `ItemGrafter.java`, `BlockForestryLeaves.getDrops`, `api.arboriculture.IToolGrafter`

**Local touch points**

- New class under `arboriculture/loot/` or Fabric event listener
- Possibly `fabric.mod.json` / loot API registration
- Recipes for grafter (currently **no** `grafter*.json` under `data/reforestry/recipe/`) — include here or in 11.9e

**Acceptance criteria**

- Grafter + oak leaves can yield apple-oak sapling when mapping exists.
- Without grafter, vanilla oak behavior unchanged.
- Grafter takes durability.

**Suggested steps**

1. Port logic from `GrafterLootModifier.doApply` / `handleLoot`.
2. Use Fabric loot modify API (or break-block callback) — no Forge GLM.
3. Add crafting recipes for grafter + proven grafter from CE.

**Fabric notes:** Forge `IGlobalLootModifier` has no direct twin; Fabric API loot events / `LootTableEvents.MODIFY` are the intended hook.

**Estimated size:** M

---

### ARB-11.9c — Bee ↔ tree pollen bridge (vanilla / default leaves)

**Goal:** Bees can collect pollen from and pollinate vanilla/default Forestry leaves, optionally converting them to genetic leaves — matching CE `TreePollenType`.

**Prerequisites:** ARB-11.8a/b (vanilla individual lookup + convert helper).

**Scope**

- **In:** Expand local `TreePollenType`; `TreeUtil.getOrCreateLeaves` equivalent; config `pollinateVanillaLeaves` if CE has it.
- **Out:** Butterfly caterpillar fields on `TileLeaves` (lepidopterology).

**Reference sources (verified)**

- CE: `forestry/arboriculture/genetics/TreePollenType.java`
- CE: config `ForestryConfig.SERVER.pollinateVanillaLeaves` (referenced in that file)
- Local: `TreePollenType.java` (genetic `TileLeaves` only)

**Local touch points**

- `src/main/java/com/leon1236/reforestry/arboriculture/genetics/TreePollenType.java`
- Helper util (create if missing) for converting leaf blocks to `TileLeaves`

**Acceptance criteria**

- Bee housing near oak leaves can retrieve tree pollen when mapping exists.
- Pollination can mate vanilla-mapped leaves when config allows conversion.
- Persistent/decorative leaves skipped (CE rules).

**Suggested steps**

1. Port canPollinate / tryCollect / tryPollinate branches for non-BE leaves.
2. Add convert helper + config flag.
3. Test with alveary sieve pollen path if bees already produce tree pollen.

**Estimated size:** M

---

### ARB-11.9d — Charcoal module (log pile / ash / charcoal block)

**Goal:** Port CE’s charcoal pile gameplay (ignite enclosed log piles → ash with charcoal yield based on wall materials). Separate CE module (`charcoal`) planned to merge into arboriculture.

**Prerequisites:** None for blocks; JEI category optional later.

**Scope**

- **In:** `ModuleCharcoal` or fold into arboriculture; `CharcoalBlocks` (`charcoal`, `log_pile`, `decorative_log_pile`, `ash_block`); `LogPileBlock` tick logic; `CharcoalManager` / pile walls; recipes; flammability.
- **Out:** Full JEI plugin polish (can stub); loam if not already in core.

**Reference sources (verified)**

- CE: `forestry/arboriculture/ModuleCharcoal.java`
- CE: `forestry/arboriculture/features/CharcoalBlocks.java`
- CE: `forestry/arboriculture/blocks/LogPileBlock.java`, `DecorativeLogPileBlock.java`, `BlockAsh.java`, `BlockCharcoal.java`
- CE: `forestry/arboriculture/charcoal/CharcoalManager.java`, `CharcoalPileWall.java`
- Local assets already: `blockstates/charcoal.json`, `log_pile.json`, `decorative_log_pile.json`, `ash_block*.json` + lang/config strings

**Local touch points**

- New module or register under arboriculture features
- `files/implemented-features.md` note when done
- Config keys already in lang (`for.config.tweaks.charcoal.*`)

**Acceptance criteria**

- Build log pile enclosure, light with fire, ages to ash, drops charcoal amount from walls.
- Decorative pile does not cook.
- Charcoal block burns as fuel (CE burn time 16000).

**Suggested steps**

1. Port blocks + manager API (`ICharcoalManager` if in CE api).
2. Register walls for common blocks (dirt, stone, etc. — extract from CE registration).
3. Recipes + creative tab.
4. World-test one full cook cycle.

**Fabric notes:** CE `isFlammable` Forge hooks → Fabric `FlammableBlockRegistry` (already used for Forestry wood).

**Estimated size:** L

---

### ARB-11.9e — Recipes, tags, loot catch-up

**Goal:** Close data-pack gaps so new content is craftable/tag-correct (boats, grafters, charcoal, pods, leaves).

**Prerequisites:** Whatever content sections landed before this.

**Scope**

- **In:** Missing recipes (grafter, boats, charcoal, fireproof conversions if any missing); tag membership for new blocks; loot tables for new blocks; remove/ignore empty Forge biome_modifier leftovers.
- **Out:** Rewriting all 875 existing recipes.

**Reference sources (verified)**

- CE generated data under CE repo / local `for textures only/thedarkcolour-ForestryCE/src/generated/resources/`
- Local: `src/main/resources/data/reforestry/recipe/` (wood recipes present; **no** boat/grafter/charcoal recipes found)

**Local touch points**

- `src/main/resources/data/reforestry/recipe/`
- `src/main/resources/data/reforestry/tags/`
- `src/main/resources/data/reforestry/loot_table/`
- Prefer `tools/` scripts + `tools/rename_namespace.py`

**Acceptance criteria**

- Grafter craftable; one boat craftable; charcoal pile craftable.
- New blocks have loot tables (no empty drops).
- Tags used by pods/worldgen resolve.

**Suggested steps**

1. Inventory missing recipe ids vs CE datagen.
2. Bulk copy+rename JSON.
3. Validate with `/reload` and recipe book.

**Estimated size:** M

---

### ARB-11.9f — Creative tabs, item models, lang polish

**Goal:** Make new arboriculture items discoverable and correctly named/modeled in inventory and JEI.

**Prerequisites:** Boats (11.8g) and any new blocks.

**Scope**

- **In:** Update `ArboricultureCreativeTabs`; generate missing item models (boats currently 0 models); fix lang if ids diverge; optional CE `ArboricultureJeiPlugin` if JEI present.
- **Out:** Full Patchouli rewrite.

**Reference sources (verified)**

- CE: `forestry/arboriculture/compat/ArboricultureJeiPlugin.java`
- Local: `ArboricultureCreativeTabs.java`; lang already has boat grammar + entity names

**Local touch points**

- `ArboricultureCreativeTabs.java`
- `assets/reforestry/models/item/`
- `assets/reforestry/lang/en_us.json`

**Acceptance criteria**

- Creative arboriculture tab shows boats, new leaves, pods, charcoal if implemented.
- No missing-model purple/black items for registered content.
- English names readable.

**Estimated size:** S–M

---

### ARB-11.9g — Tree admin commands (optional CE parity)

**Goal:** Dev/admin commands to spawn Forestry trees for testing (CE `CommandTree` / `CommandTreeSpawn`).

**Prerequisites:** ARB-11.8c generators.

**Scope**

- **In:** Port command package under arboriculture; register with Fabric command API.
- **Out:** Treekeeping mode commands (MC-only).

**Reference sources (verified)**

- CE: `forestry/arboriculture/commands/CommandTree.java`, `CommandTreeSpawn.java`, `ForestSpawner.java`, `TreeSpawner.java`, `TreeGenHelper.java`

**Local touch points:** new `arboriculture/commands/`; module init registration.

**Acceptance criteria:** Operator can spawn a named species tree at look position.

**Estimated size:** S

---

### ARB-11.9h — Arborist villager (optional; depends on chests) — **DEFERRED 2026-07-25**

**Goal:** CE arborist profession trades planks/logs/saplings/pollen/grafter.

**Prerequisites:** Naturalist/arborist chest block (CE uses `CoreBlocks.NATURALIST_CHEST` ARBORIST type) — **absent in Re-Forestry Java**; orphaned assets/lang/textures only. Entire section deferred — see `queries/arb-11.9h-arborist-villager.md`.

**Scope**

- **In:** POI + profession + trades from CE `ArboricultureVillagers`.
- **Out:** Village structure injection unless CE datapack already copied. Local `structures/village/` has **apiarist** houses only (no arborist NBT).

**Reference sources (verified)**

- CE: `forestry/arboriculture/villagers/ArboricultureVillagers.java`
- Local note: `queries/arb-11.9h-arborist-villager.md`

**Acceptance criteria:** Arborist villager appears with arborist chest workstation; trades function.

**Fabric notes:** Use Fabric villager/POI registration APIs instead of Forge `VillagerTradesEvent`.

**Estimated size:** M (or skip if chest absent) — **skipped**

---

### ARB-11.9i — Integration pass + docs — **DONE 2026-07-25**

**Goal:** Play the full tree loop end-to-end and update the status tracker so future agents know Phase 5 is complete.

**Shipped:** RCON play-loop probes + docs. Surprises fixed: `pale_pale_oak_*` datapack ids; item `#minecraft:leaves` must not list block-only `reforestry:leaves`. Details: `queries/arb-11.9i-integration.md`.

**Acceptance:** Phase 5 marked done in `files/implemented-features.md`; 11.9c / 11.9h called out as polish/deferred.

---

### ARB-LATER-a — Treealyzer / genetics database (MC-only restore)

**Goal:** Restore 1.12 analyzer UI for trees when the broader database/analyzer framework is ported. **CE dropped the `database` module.**

**Prerequisites:** Core analyzer/database framework (not arboriculture-only).

**Scope / refs**

- MC: `forestry/arboriculture/genetics/TreeAlyzerPlugin.java`, `TreeDatabaseTab.java`
- Do **not** treat as Phase 5 / 11.8–11.9 work.

**Estimated size:** L (cross-module)

---

### ARB-LATER-b — Treekeeping modes (MC-only; CE unused)

**Goal:** Global yield/sappiness/maturation/mutation difficulty multipliers from 1.12.

**Refs:** MC `forestry/arboriculture/genetics/TreekeepingMode.java`; CE research notes say modes are commented/unused.

**Estimated size:** S–M (if ever desired)

---

### ARB-LATER-c — Greenhouse / arboretum (MC / farming)

**Goal:** Greenhouse multiblock and arboretum farm logic are **not** CE arboriculture core; belong to greenhouse/farming restore.

**Refs:** MC greenhouse module; farm grammar `for.farm.arboretum` in lang; CE farming module separately.

**Estimated size:** L+

---

## 4. Recommended implementation order

Checklist for agents (do in order unless a section says optional):

1. [x] **ARB-11.8a** — Species metadata (rarity, climate, generator hooks)
2. [x] **ARB-11.8b** — Default / decorative / fruit-default leaves
3. [x] **ARB-11.8c** — Growth engine (FeatureArboriculture + helpers)
4. [x] **ARB-11.8d** — Feature shapes batch A
5. [x] **ARB-11.8e** — Feature shapes batch B
6. [x] **ARB-11.8f** — Wild `TreeDecorator` + Fabric biome mods + config
7. [x] **ARB-11.8g** — Boats + chest boats
8. [x] **ARB-11.9a** — Fruit pods
9. [x] **ARB-11.9b** — Grafter vanilla loot + grafter recipes
10. [ ] **ARB-11.9c** — Pollen bridge completeness (post–Phase 5 polish)
11. [x] **ARB-11.9d** — Charcoal module (can parallelize after 11.8g if desired)
12. [x] **ARB-11.9e** — Recipes/tags/loot catch-up
13. [x] **ARB-11.9f** — Creative tab / models / lang
14. [x] **ARB-11.9g** — Tree commands (optional)
15. [~] **ARB-11.9h** — Arborist villager — deferred (no `tree_chest` block; see `queries/arb-11.9h-arborist-villager.md`)
16. [x] **ARB-11.9i** — Integration pass + `implemented-features.md`
17. [ ] **ARB-LATER-*** — Only when restoring CE-dropped systems

**Parallelism note:** **ARB-11.8g (boats)** can start in parallel with 11.8b–11.8e (does not need worldgen). **ARB-11.9d (charcoal)** is independent of boats/worldgen.

---

## 5. Research limitations / uncertainties

- Immersive Forestry boats match CE closely; 26.2 vanilla boat hierarchy (`AbstractBoat`, new packages, `ValueInput`) means **CE boat code cannot be pasted as-is** — verify against `Minecraft-26.2` during 11.8g.
- Exact count of CE Feature classes (~45) should be re-diffed against MCP file list when starting 11.8d/e; do not assume from memory.
- Arborist villager deferred: no `NATURALIST_CHEST` / `tree_chest` Java registration (`queries/arb-11.9h-arborist-villager.md`).
- Treealyzer/database/greenhouse explicitly CE-dropped or other-module; listed only as LATER.
- Integration (11.9i): pale_oak fireproof datapack must not double-prefix (`pale_pale_oak_*`); genetic `reforestry:leaves` is block-only — keep it out of item `#minecraft:leaves` (`queries/arb-11.9i-integration.md`).

