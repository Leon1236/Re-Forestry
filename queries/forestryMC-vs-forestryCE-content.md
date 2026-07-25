# ForestryMC (1.12.2) vs ForestryCE (1.20.1) — content comparison

**Scope:** in-game content only (modules, blocks, items, species, tools, machines). Not Java architecture.

**Sources (verified, not invented):**
- MCP / `mods.db` repos: `ForestryMC-ForestryMC`, `thedarkcolour-ForestryCE`
- Project docs: `files/db-inventory-findings.md`, `files/registry-inventory-*.md`, `CLAUDE.md`
- Key ID lists: `ForestryModuleUids` / `ForestryModuleIds`, `BeeDefinition` / `ForestryBeeSpecies`, `TreeDefinition` / `ForestryTreeSpecies`, feature/block-type enums

**Repos compared:**
| | ForestryMC | ForestryCE |
|---|---|---|
| Minecraft | 1.12.2 | 1.20.1 |
| Loader | Forge | Forge |
| Role | Original Forestry | Community Edition continuation |

Immersive-Forestry (1.21.1 NeoForge) was spot-checked only for bee species count (**same 69** as CE). No separate content inventory here.

---

## 1. Modules — kept, dropped, renamed/merged

### Present in both (same gameplay area)

| Area | 1.12 module id | CE module id | Notes |
|---|---|---|---|
| Core | `core` | `core` | Shared foundation + many machines/items |
| Fluids | `fluids` | `fluids` | Still a module id in CE |
| Apiculture | `apiculture` | `apiculture` | Bees |
| Arboriculture | `arboriculture` | `arboriculture` | Trees / wood |
| Charcoal | `charcoal` | `charcoal` | CE comment: planned merge into arboriculture in 1.21 |
| Factory | `factory` | `factory` | Processing machines |
| Farming | `farming` | `farming` | Multiblock farms |
| Cultivation | `cultivation` | `cultivation` | Planters |
| Energy | `energy` | `energy` | Engines |
| Mail | `mail` | `mail` | Postal system |
| Lepidopterology | `lepidopterology` | `lepidopterology` | Butterflies / moths |
| Sorting | `sorting` | `sorting` | Genetic filter |
| Worktable | `worktable` | `worktable` | Crafting worktable |
| Storage | `backpacks` + `crates` | `storage` | **Renamed/merged** into one CE module |

### Only in ForestryMC (dropped by CE)

| Module id | What players lost (content) |
|---|---|
| `book` | Custom Forester’s Manual GUI system |
| `climatology` | Habitat Former block + Habitat Screen item (climate editing) |
| `greenhouse` | Greenhouse structure blocks + climatiser parts (heater/fan/hygro/humidifier/dehumidifier) + windows |
| `food` | Dedicated food module (items themselves mostly **moved** into apiculture in CE — see below) |
| `database` | Database machine block (species database UI) |
| `research` | Listed as a module uid in 1.12; no separate deep inventory done here |

Also only in 1.12 as **external mod plugins** (not base content): BuildCraft, IC2, Natura, HarvestCraft, Immersive Engineering, etc. CE instead has a small `compat` package (e.g. Curios module id `curios`).

### Only in ForestryCE (new / reorganized)

| Module id | What it is |
|---|---|
| `storage` | Replaces separate `backpacks` + `crates` |
| `curios` | Optional Curios integration module |

CE also has packages `apiimpl`, `compat`, `plugin` — framework/compat, not player “content modules” in the 1.12 sense.

### Guide book note

- **1.12:** custom `book` module.
- **CE:** no `book` package; depends on **Patchouli** for the guide. Core still registers a `foresters_manual` item.

---

## 2. Approximate scale (verified counts)

| Content | ForestryMC (1.12) | ForestryCE (1.20.1) |
|---|---|---|
| Bee species | **44** (`BeeDefinition`) | **69** (`ForestryBeeSpecies`) |
| Tree species | **35** (`TreeDefinition`) | **50** (`ForestryTreeSpecies`) |
| Forestry wood types | **28** (`EnumForestryWoodType`) | **43** (`ForestryWoodType`) |
| Vanilla wood types used | **6** | **7** (+ cherry) |
| Butterflies | **31** (`ButterflyDefinition`) | **31** (butterfly entries in `ForestryButterflySpecies`) |
| Moths | **4** (`MothDefinition`) | **4** (moth entries in same CE file) |
| Wild hive types | **8** (incl. swarm) | **12** (incl. swarm) |
| Alveary part types | **7** | **7** (same set) |
| Factory machines | **10** | **10** (set differs — see Factory) |
| Energy engines | **5** types | **5** engine types + separate solar panel block |

Arboriculture block explosion in CE (~1,337 blocks in project inventory docs) is mostly “same idea, more wood types + modern block kinds (doors, signs, hanging signs, etc.)”, not a totally different game mode.

---

## 3. By content area

### Apiculture (bees)

**In both**
- Life stages: queen / drone / princess / larvae (`bee_*_ge`)
- Housing: apiary, bee house
- Alveary multiblock parts: plain, swarmer, fan, heater, hygro, stabiliser, sieve
- Frames: untreated, impregnated, proven
- Tools: scoop, smoker
- Armor: full apiarist set
- Products: honey drop, honeydew, royal jelly, propolis, pollen/comb families
- Comb blocks (CE inventory: 17 comb block variants)

**Only ForestryMC**
- `habitat_locator`
- `imprinter`
- Minecart bee house (`cart.beehouse`)
- `wax_cast`
- Hive set limited to: forest, meadows, desert, jungle, end, snow, swamp, swarm

**Only ForestryCE**
- **+25 bee species** beyond the shared 44, including modern-biome lines, e.g.:
  - Savanna / Argil / Pride
  - Lush / Verdant / Luxuriant / Kleptoplastic / Photosynthetic / Autotrophic
  - Primeval / Anachrone / Relic
  - Aquatic / Pirate / Prismatic / Abyssal / Shulking
  - Embittered / Spiteful / Seething / Warped
  - Zombified / Sculk / Vanilla
  - Patriotic (holiday-adjacent; also Merry/Tipsy/Tricky/Leporine exist in both)
- Extra wild hives: **savanna, lush, aquatic, nether**
- Extra items: `scoop_proven`, `frame_creative`, `amber_drone`, `experience_drop`
- Food items **moved here** from the old food module: `honeyed_slice`, `ambrosia`, `honey_pot`

**Uncertain:** 1.12 lang lists a few bee names (`darkened`, `omega`, `reddened`) that are **not** in `BeeDefinition`’s 44 constants — likely stale/leftover lang, not confirmed playable species.

---

### Arboriculture (trees / wood / grafters / boats)

**In both**
- Genetic saplings + pollen
- Grafters (basic + proven in CE; 1.12 also had grafter tools — same role)
- Fruit-bearing trees, leaves, pods concept
- Charcoal pile / charcoal block module
- Large custom wood set (logs/planks/etc.)

**Only ForestryMC (relative)**
- Smaller tree/wood set (**35** species, **28** Forestry woods, **6** vanilla woods)
- No cherry as a vanilla wood type yet (cherry existed as a Forestry tree species)

**Only ForestryCE**
- **+15 tree species** vs 1.12’s 35 (net; some renames)
  - Clear additions include: elm, fir, coconut, beech, feijoa, dogwood, ginkgo, jacaranda, pewen, macrocarpa, olive, orange, pear, kauri
  - Vanilla cherry blossom species separate from hill/sour cherry
- **+15 Forestry wood types** (43 vs 28)
- Vanilla wood coverage adds **cherry**
- Modern wood furniture kinds (doors, trapdoors, buttons, pressure plates, signs, hanging signs) — confirmed in CE registry inventory
- Boat + chest boat items × Forestry wood types (**86** boat-related items in CE arboriculture inventory)

---

### Factory / machines

Shared machines (same player-facing machines, sometimes different render buckets in code):

| Machine | 1.12 | CE |
|---|---|---|
| Bottler | yes | yes |
| Carpenter | yes | yes |
| Centrifuge | yes | yes |
| Fermenter | yes | yes |
| Moistener | yes | yes |
| Squeezer | yes | yes |
| Still | yes | yes |
| Rainmaker | yes | yes |
| Fabricator | yes | yes |

**Only ForestryMC:** Rain Tank (`raintank`) — CE has lang leftovers mentioning it, but **no Java machine type** in CE factory enums.

**Only ForestryCE:** Smelter (`smelter`)

Net count stays **10** machines each, with a 1-for-1 swap (raintank ↔ smelter).

---

### Farming / farms

**In both**
- Multiblock farm with material skins (stone brick, brick, sandstone, nether brick, quartz, etc.)
- Farm block roles around gearbox / hatch / valve (+ control in practice)
- Cultivation planters (same planter type list in both):
  - Arboretum, farm crops, mushroom, gourd, nether, ender, peat bog

**Diffs**
- 1.12 farm block types: `PLAIN`, `BAND`, `GEARBOX`, `HATCH`, `VALVE` (and recipes also reference control)
- CE farm block types: `PLAIN`, `GEARBOX`, `HATCH`, `VALVE`, `CONTROL` — **`BAND` removed**, `CONTROL` explicit in the enum
- CE materials listed in `EnumFarmMaterial` (11 skins)

---

### Mail

**In both (same three block roles)**
- Mailbox
- Trade station
- Stamp collector / philatelist

CE renames the third type to `STAMP_COLLETOR` (spelling as in CE source). Same content idea.

---

### Storage / backpacks / crates

**In both**
- Miner / digger / forester / hunter / adventurer / builder backpacks (normal + woven/T2)
- Apiarist + lepidopterist naturalist bags
- Crates (filled crate items)

**Only ForestryCE**
- `arborist_bag` (tree naturalist backpack)
- Brewer backpack + woven variant (`brewer_bag`, `brewer_bag_woven`)

Module rename: 1.12 `backpacks`+`crates` → CE `storage`.

---

### Energy / engines

| Engine / block | 1.12 | CE |
|---|---|---|
| Peat engine | yes | yes |
| Biogas engine | yes | yes |
| Clockwork engine | yes | yes |
| Electrical engine | yes | **no** |
| Generator | yes | **no** |
| Combustion engine | no | **yes** |
| Solar engine | no | **yes** |
| Solar panel block | no | **yes** (`solar_panel`) |

Also: 1.12 energy package included BuildCraft MJ / Tesla compat wrappers — integration, not base content. CE is Forge Energy oriented.

---

### Cultivation

Same planter set in both enums (`ARBORETUM`, `FARM_CROPS`, `FARM_MUSHROOM`, `FARM_GOURD`, `FARM_NETHER`, `FARM_ENDER`, `PEAT_POG`). Treat as **content-parity** for this comparison.

---

### Lepidopterology (butterflies)

**In both**
- Butterfly breeding / butterfly items / fluttery wildlife content
- **31** butterflies + **4** moths (same totals)

Naming drifts exist (example: 1.12 `Postillion` vs CE `CLOUDED_YELLOW`) — same slot in the set, not a huge content expansion.

Extra Trees’ moths (Binnie) are **addon** content, not base ForestryMC/CE — see `files/addon-integration-mapping.md`.

---

### Climate / greenhouse / climatology (1.12 only)

Dropped entirely by CE as modules:

**Climatology**
- Habitat Former (block + GUI)
- Habitat Screen (item)

**Greenhouse**
- Greenhouse blocks: plain, border, border center, gearbox, control, screen
- Climatisers: hygro, heater, fan, humidifier, dehumidifier
- Greenhouse window + roof window

CE still has climate *API types* in places (for genetics gating), but not these player-built climate machines.

---

### Food / database / guidebook

| Content | ForestryMC | ForestryCE |
|---|---|---|
| Honeyed slice / ambrosia / honey pot | `food` module items | Registered under **apiculture items** (food module gone) |
| Database machine | `database` module block | **Dropped** |
| Guide book | Custom `book` module | **Patchouli** + `foresters_manual` item in core |

---

### Fluids, ores, resources, decorative core blocks

**Fluids**

| Fluid | 1.12 | CE |
|---|---|---|
| Bio ethanol | yes | yes |
| Biomass | yes | yes |
| Glass | yes | yes |
| Honey (`FOR_HONEY` / `HONEY`) | yes | yes (renamed) |
| Ice | yes | yes |
| Juice | yes | yes |
| Seed oil | yes | yes |
| Short mead | yes | yes |
| Milk (Forestry fluid) | yes | **no** (vanilla milk covers the need) |
| Wax | no | **yes** |

**Ores / resource storage**
- Both: apatite + tin ores, resource storage blocks, humus, bog earth, analyzer, escritoire
- 1.12 resource storage included **copper**; CE storage enum is apatite / tin / bronze / **amber** (copper is vanilla in 1.20+)
- CE adds: deepslate apatite/tin ores, raw tin block, dedicated peat block registration pattern

**Large CE-only decorative / building sets in core** (not present as this family in 1.12 inventory):
- Burn barrel
- Turf / turf block
- Plywood sheet + plywood block, cork
- Ash brick set (stairs/slab/wall/chiseled)
- Wax brick / refractory wax brick sets
- Waxstone / honeystone families (many stair/slab/wall/cobbled/polished/chiseled variants)

These are a real CE content expansion beyond “port the 1.12 list.”

**Charcoal**
- Both have charcoal block + charcoal pile wall system (`ModuleCharcoal`).

---

### Sorting / worktable / other shared machines

- **Sorting** and **worktable** modules exist in both — treat as kept.
- Core still has analyzer / escritoire / circuits / tubes / bronze tools / fertilizers in both lineages (exact item id lists differ in naming modernization: e.g. tubes/chipsets wording).

---

## 4. One-page “what changed for a player?”

### CE dropped (big missing 1.12 toys)
1. Habitat Former + Habitat Screen (climatology)
2. Greenhouse + climatiser multiblock pieces
3. Database machine
4. Custom book UI (replaced by Patchouli manual)
5. Rain Tank
6. Electrical engine + generator
7. Habitat locator, imprinter, beehouse minecart, wax cast
8. Farm `BAND` block type

### CE added (noticeable new toys)
1. **Much bigger bee roster** (44 → 69) + new wild hives (savanna/lush/aquatic/nether)
2. **More trees/woods** (35→50 species, 28→43 Forestry woods) + boats for Forestry woods
3. Smelter machine
4. Combustion + solar engines / solar panel
5. Arborist + brewer backpacks
6. Proven scoop, creative frame, amber drone, experience drop
7. Huge decorative wax/honey/ash/plywood building set
8. Wax fluid; deepslate ores / raw tin block / amber storage

### Mostly same
Factory staples, alveary, farms/planters, mail trio, butterfly+moth counts, charcoal, backpacks (core six), bee housing loop.

---

## 5. Relevance to Re-Forestry

Per `CLAUDE.md` / roadmap: CE is the port base; ForestryMC is the source for **restoring dropped modules** later (guide book / climatology / greenhouse / food-as-module / database). Addon content (Gendustry / Extra Bees / Extra Trees) is separate from this MC↔CE base comparison.

---

## 6. Confidence notes

- Species / wood / hive / machine / engine lists: **high** (read from enums / ID holders).
- Full exhaustive item-by-item dump of every core decorative block id: **not fully listed** above for length; CE `CoreBlocks` is large — section summarizes families.
- Whether every 1.12 fruit/item meta still exists 1:1 under a new id in CE: **not fully audited**; genetics species counts are solid, item meta migrations may have edge cases.
- Immersive-Forestry: not a third full column; bee species count matches CE.
