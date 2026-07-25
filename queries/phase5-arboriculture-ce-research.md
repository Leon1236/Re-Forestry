# Phase 5 research digest — CE arboriculture internals (July 2026)

Compiled from direct mods.db reads of `thedarkcolour-ForestryCE` + local `for textures only/` asset inspection.
This is the reference digest backing the Phase 5 plan; verify nothing here from memory — file paths are exact.

## Headline findings

1. **CE's complete datagen output exists locally** at `for textures only/thedarkcolour-ForestryCE/src/generated/resources/`:
   1,428 blockstates, 1,649 block models, 1,639 item models, 1,780 recipes, 1,422 loot tables, 241 tag files,
   plus `data/forestry/worldgen/{configured_feature,placed_feature}/tree.json`. The main `src/main/resources` has
   only 93 handwritten blockstates and **zero recipes** — everything wood-related is datagen-generated.
   → The port copies + renames + format-migrates these files (1.20.1 → 26.2 JSON formats) instead of writing generators.

2. **Wild tree worldgen is trivial data-side**: one dispatcher feature (`forestry:tree`, class `TreeDecorator`,
   registered as a vanilla `Feature`) + empty-config configured/placed feature JSONs; per-species `rarity` decides
   spawns inside the feature code. Biome wiring is a Forge `biome_modifier` JSON (`data/forestry/forge/biome_modifier/forestry.json`)
   → Fabric replacement: `BiomeModifications` API.

## Architecture map (CE class → role)

### Wood block system
- `api/arboriculture/WoodBlockKind.java` — enum: LOG, STRIPPED_LOG, STRIPPED_WOOD, WOOD, PLANKS, SLAB, FENCE, FENCE_GATE, STAIRS, DOOR, TRAPDOOR, SIGN, WALL_SIGN, HANGING_SIGN, WALL_HANGING_SIGN, BUTTON, PRESSURE_PLATE (17).
- `arboriculture/ForestryWoodType.java` — 43-value enum, each holding a `ForestryLeafType`, hardness (default 2.0; SEQUOIA 4.0, BALSA 1.0, GREENHEART 7.5, PINE 3.0, GIGANTEUM 4.0), its own `WoodType`+`BlockSetType`, per-type log block/item tags. `setDefaultLeaves(...)` picks LEAVES_DEFAULT vs LEAVES_DEFAULT_FRUIT by fruit chance roll.
- `arboriculture/features/ArboricultureBlocks.java` — all groups via a local `woodGroup(constructor, kind, fireproof, types)` helper → `.identifier((fireproof?"fireproof_":"")+kind, SUFFIX)`. Slabs/stairs take the matching planks block. Signs get 4 groups (sign/wall_sign/hanging_sign/wall_hanging_sign) with dedicated item classes. Every group also registers into `WoodAccess` (lookup: (IWoodType, WoodBlockKind, fireproof) → BlockState), used by generators (`TreeManager.woodAccess.getBlock(...)`).
- Genetics blocks: `SAPLING_GE` (`FeatureBlock`, no item — sapling item is separate), `LEAVES` (single genetic block + `ItemBlockLeaves`), `LEAVES_DEFAULT`/`LEAVES_DEFAULT_FRUIT`/`LEAVES_DECORATIVE` (× `ForestryLeafType.values()` = 50 each), `PODS` (× ForestryPodType: cocoa, dates, papaya, coconut).
- `blocks/ForestryLeafType.java` — NOT an enum: an extensible registry-ish class with 50 static instances, each holding a species `ResourceLocation`; `setSpecies()` is called after species registration (`TreeSpeciesType.onSpeciesRegistered`) to bind `IFruit` + default `ITree`.

### Genetics
- `api/genetics/alleles/TreeChromosomes.java` — 10 chromosomes: SPECIES (registry), HEIGHT/SAPLINGS/YIELD/SAPPINESS (float), FRUIT (registry `forestry:fruits`), EFFECT (registry `forestry:tree_effect`, unimplemented — all species use TREE_EFFECT_NONE), MATURATION/GIRTH (int), FIREPROOF (bool, shared constant with ButterflyChromosomes).
- Karyotype defaults + client tint/sprite wiring live in `apiimpl/plugin/PluginManager.java` (sapling item model auto-derived as `<ns>:item/<path>_sapling`; `TreeClientManager` holds per-species `ILeafSprite` + tint).
- `arboriculture/genetics/Tree.java` — the ITree individual. Key methods: `getSaplings` (drop chance = SAPLINGS × modifier; if mated → `SpeciesUtil.createOffspring` with two-slot mutation attempt, else copy), `getResilience`, `getRequiredMaturity` (MATURATION), `produceStacks` (delegates to FRUIT allele), `getTreeGenerator`.
- `arboriculture/TreeSpecies.java` — species impl; extra fields vs bees: generator, vanillaLeafStates, vanillaSaplingItems, decorativeLeaves stack, rarity, temperature/humidity. `setLeaves`/`setLogBlock` delegate to generator; tooltip shows S/M, H/G, Y/S letter codes.
- `arboriculture/genetics/TreeSpeciesType.java` — species-type singleton: maps vanilla leaf BlockStates + vanilla sapling items → default individuals (`getVanillaIndividual`), `plantSapling` (places SAPLING_GE then sets tree on TileSapling), `setFruitBlock` (pod placement with `getValidPodFacing` + log tag), binds ForestryLeafType→species after registration.
- `api/plugin/ITreeSpeciesBuilder.java` — `setTreeFeature(Function<ITreeGenData, Feature<NoneFeatureConfiguration>>)`, `setGenerator`, `addVanillaStates`, `addVanillaSapling`, `setDecorativeLeaves`, `setWoodType`, `setRarity`.
- `apiimpl/plugin/ArboricultureRegistration.java` — registerSpecies(id, genus, species, dominant, escritoireColor, woodType) + registerFruit/registerTreeEffect/(registerRefractoryWaxable/registerCharcoalPitWall — charcoal module, out of scope). After plugins run: `TreeChromosomes.EFFECT.populate(...)`, `TreeChromosomes.FRUIT.populate(...)`.

### Fruits
- `api/arboriculture/genetics/IFruit.java` — registry allele value: isFruitLeaf, getFruitChance (uses YIELD), getRipeningPeriod, getFruits(genome, level, ripeningTime), getColour (callow→ripe lerp), getSprite (overlay texture), requiresFruitBlocks/trySpawnFruitBlock, getLogTag (pods).
- Impls: `Fruit` (base), `RipeningFruit` (leaf fruits: apple/cherry/chestnut/walnut/lemon/plum/pear/orange/feijoa/olive — color-lerped overlay sprite), `PodFruit` (cocoa via vanilla CocoaBlock plant; dates/papaya/coconut via Forestry pod blocks + per-type log tags), `DummyFruit` (NONE).
- 15 fruit ids in `api/arboriculture/ForestryFruits.java` (fruit_none, fruit_apple, fruit_cocoa, fruit_chestnut, fruit_walnut, fruit_cherry, fruit_dates, fruit_papaya, fruit_lemon, fruit_plum, fruit_pear, fruit_orange, fruit_coconut, fruit_feijoa, fruit_olive).

### Sapling + leaves lifecycle
- `blocks/BlockSapling.java` — Block + BonemealableBlock + EntityBlock, `randomTick` at 10% → `TileTreeContainer.onBlockTick`; drops the genetic sapling stack from BE; bonemeal: 45% success gate then `tryGrow(boneMealed=true)`.
- `tiles/TileTreeContainer.java` — abstract BE storing the `ITree` (NBT `ContainedTree` via species-type codec); network sync sends only species id.
- `tiles/TileSapling.java` — `timesTicked` counter vs `getRequiredMaturity()`; on maturity gets the species' `Feature` and calls `FeatureBase.place(genome, level, rand, pos, false)`; bonemeal fast-forwards counter first.
- `blocks/BlockForestryLeaves.java` + `BlockAbstractLeaves` + `BlockExtendedLeaves` — genetic leaves: EntityBlock + Bonemealable (ripens fruit +0.5); randomTick 10% → TileLeaves.onBlockTick; drops = saplings (`tree.getSaplings`) + ripe fruit; right-click with empty hands picks ripe fruit (ripeness ≥ 0.9); shears/pick-block give decorative leaves; willow has no collision (walk-through). FOLIAGE_COLOR_INDEX=0, FRUIT_COLOR_INDEX=2 tint indices.
- `tiles/TileLeaves.java` — stores: ripeningTime/ripeningPeriod, isFruitLeaf (rolled once from fruit chance), damage (caterpillar), maturationTime+caterpillar (butterfly nursery — lepidopterology, skip), pollination = `tree.getMate() != null`. Tick: sappiness-probability ripening + network color update (custom `PacketRipeningUpdate`; port can use plain BE sync), tree effect tick (all NONE in base). `setMate(pollen)` stores mate genome. Client model data: species/pollinated/fruit-overlay-sprite (Forge ModelData — needs Fabric rework).
- `blocks/BlockFruitPod.java`/`tiles/TileFruitPod.java` — pod BE holds genome + IFruit + yield; tick: `rand < yield` → addRipeness(0.5); visual age via vanilla `CocoaBlock.AGE` 0–2; pickFruit resets age to 0.
- `blocks/BlockDefaultLeaves` / `BlockDefaultLeavesFruit` / `BlockDecorativeLeaves` — BE-less per-species leaf blocks (worldgen fills default forms; decorative is the shear/creative form).

### Growth features (worldgen/)
- `FeatureArboriculture` (base, extends CE's own `FeatureBase` which extends vanilla `Feature<NoneFeatureConfiguration>`): place() = preGenerate (height = base + rand(variation), × HEIGHT modifier; girth = GIRTH) → getValidGrowthPos (`TreeGrowthHelper.getGrowthPos`: girth×girth sapling square of same species + room check) → clearSaplings → generateTrunk → generateLeaves → generateExtras (pods if fruit.requiresFruitBlocks) → vanilla-style leaf DISTANCE update pass.
- `FeatureTree` — standard shape (4 leaf cylinders); ~45 per-species subclasses (FeatureLarch, FeatureSequoia, FeatureBaobab...; `FeatureTreeVanilla`/`FeatureGiganteum` are tiny). Helpers: `FeatureHelper` (core.worldgen — trunk/cylinder/pods generators), `TreeBlockTypeLog`/`TreeBlockTypeLeaf` (place via WoodAccess/`species.setLeaves`), `TreeContour`.
- `DefaultTreeGenerator` — ITreeGenerator: feature factory + wood type; setLogBlock reads FIREPROOF chromosome → WoodAccess lookup; setLeaves: default genome during worldgen → BE-less default leaves, else genetic LEAVES + TileLeaves.
- `worldgen/TreeDecorator` (`forestry:tree` Feature) — wild tree spawning using species rarity; `feature/ForestryTreeFeature` (`custom_tree`) — datapack-usable configured tree feature.
- `TileSapling.tryGrow` falls back to plain `generator.place(FeaturePlaceContext)` for non-Forestry features.

### Items
- `ItemGermlingGE` (sapling + pollen_fertile life stages; `TreeLifeStage` enum): sapling right-click plants via `TreeSpeciesType.plantSapling`; pollen right-click mates target leaves (`TreeUtil.getOrCreateLeaves(level, pos, true)` converts vanilla/default leaves to TileLeaves first). Fermentation modifier = SAPPINESS×10 (factory module — skip for now). Burn time 100.
- `ItemGrafter` (durability item; proper tool for leaves; getSaplingModifier=100f → guaranteed sapling drop). Drop boost implemented as Forge `GrafterLootModifier` (global loot modifier on BlockTags.LEAVES: adds `tree.getSaplings(..., saplingModifier)` when no sapling dropped, picks fruit, damages tool) — Fabric port: fold into our own leaves' getDrops + a Fabric loot API hook only for VANILLA leaves.
- Boats: `ForestryBoat`/`ForestryChestBoat` entities (2 entity types, wood type as entity data), `ItemForestryBoat`, dispenser behavior, `ForestryBoatRenderer` with per-type layer definitions.

### Bee ↔ tree pollination bridge
- `genetics/TreePollenType.java` (IPollenType `forestry:tree`): canPollinate (TileLeaves, or non-persistent non-decorative known vanilla/default leaf states), tryCollectPollen (tree from TileLeaves or vanillaIndividual), tryPollinate (converts vanilla leaves if config allows, then `TreeUtil.tryMate`). Bees call this during their work cycle (pollination chromosome).

### Client
- `ArboricultureClientHandler` — Sheets.addWoodType per wood type (sign atlases), custom baked models for LEAVES/decorative/default/default-fruit + `SaplingModelLoader` (species-specific sapling block model via Forge geometry loader), cutout-mipped render layers, boat renderers + layers, sign/hanging-sign BE renderers reuse vanilla.
- Leaf tint: `IForestryClientApi.getTreeManager().getTint(species)` → `BiomeLeafTint`/`FixedLeafTint`; fruit overlay tinted via `TileLeaves.getFruitColour()` (ripeness lerp).
- Leaf textures: `textures/block/leaves/<species>[_pollinated][_fast].png` — 66 files exist but NOT per 50 species (shared/fallback sprites); sapling item textures ×45; pod textures `pods/{coconut,dates,papaya}.N.png` (cocoa uses vanilla).

## Species data (for generation scripts)
- `forestry/plugin/DefaultTreeSpecies.java` (59,911 chars) — all 50 species: registerSpecies(id, GENUS_*, SPECIES_*, dominant, TextColor, woodType) + setTreeFeature + setDecorativeLeaves + addVanillaStates + [addVanillaSapling] + [setRarity] + setGenome + [addMutations]. Latin names from `ForestryTaxa`. Mutation conditions on trees are simpler than bees (mostly plain chances; verify per-species during extraction).
- Alleles: `ForestryAlleles` float/int alleles are value-interned (e.g. `SAPPINESS_AVERAGE = floatAllele(0.4f, true)`) — same shape our AlleleManager already supports from step 8.

## What CE itself does NOT implement (don't port)
- Tree EFFECT behaviors (all species TREE_EFFECT_NONE; only Dummy/Blossoming exist, unused by base species).
- Treekeeping modes (commented out in CE source: sappiness/maturation modifiers).
- Charcoal (ModuleCharcoal — separate module), villagers, commands — CE has them but they're separate concerns (charcoal/villagers deferred; commands optional).
- Butterfly nursery fields on TileLeaves (lepidopterology — Phase 6 territory).
