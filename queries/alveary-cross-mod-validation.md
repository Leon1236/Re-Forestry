# Alveary cross-mod validation

**Date:** 2026-07-30  
**Mod:** Re-Forestry (`reforestry`) — Fabric port of Forestry for Minecraft 26.2  
**Gameplay contract:** Forestry CE numbers and behavior. Immersive Forestry (IF) is treated as a CE-aligned NeoForge port. ForestryMC 1.12.2 is the classic baseline only — do not reintroduce 1.12-only systems unless explicitly listed as optional later.

This report compares how **Apiary**, **Bee House**, and **Alveary** work for the player across four codebases, then lists every confirmed gap (G1–G17) between Re-Forestry and CE.

---

## Implementation status (2026-07-30, updated)

**All gaps G1–G17 resolved.** P0–P2 core parity plus polish pass landed: power/climate ledgers on alveary and bee housing GUIs (G13), orphan entrance item assets removed (G15), BreedingTracker wired (G16), alveary client flower scan for bee FX (G17). `./gradlew compileJava` succeeded after the final pass.

---

## How this research was done

Three tools were used together so nothing was guessed from memory:

1. **Parallel research agents** — One agent per reference mod (Forestry CE, Immersive Forestry, ForestryMC 1.12) plus local Re-Forestry graph walks. Findings were merged into the plan at  
   `/home/ivan/.cursor/plans/alveary_validation_report_e586b654.plan.md`.

2. **MCP `user-minecraft-mods`** — Primary source for exact reference file text:
   - `search_code_tool` — find classes like `AlvearyController`, `BeeHousingModifier`, `TileApiary`
   - `get_file_tool` — read full Java sources from `thedarkcolour-ForestryCE`, `thedarkcolour-Immersive-Forestry`, `ForestryMC-ForestryMC`
   - Re-Forestry paths were read directly under  
     `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/src/main/java/com/leon1236/reforestry/`

3. **Graphify** — Local call graphs in `graphify-out/` and reference graphs via  
   `python3 tools/graphify_query.py CE "AlvearyController"` (and similar) to see what calls what before opening files.

**No new tools were required** for this validation pass.

---

## Architecture — one bee pipeline, three housings

Apiary, Bee House, and Alveary are **not** replacements for each other. They are three different blocks that all implement the same **`IBeeHousing`** interface and run the same **`BeekeepingLogic`** tick loop (queen work, products, mating, death → offspring).

```
Player places housing
        │
        ▼
   IBeeHousing  ──►  getBeeModifiers()  ──►  IBeeModifier stack
        │                    │                      (apiary / bee house /
        │                    │                       alveary / frames /
        ▼                    │                       stabiliser / …)
 BeekeepingLogic ◄───────────┘
        │
        ├── canWork()  — climate, flowers, queen+drone, errors
        └── doWork()   — pollination, products, aging, frame wear
```

**Alveary-specific layer:** a **3×3×3 multiblock** managed by `AlvearyController`  
(`src/main/java/com/leon1236/reforestry/apiculture/multiblock/AlvearyController.java`).  
Special blocks (heater, fan, hygro, swarmer, sieve, stabiliser) attach as **components** and register modifiers or listeners on the controller when the structure forms.

**Key Re-Forestry files**

| Role | Path |
|------|------|
| Shared bee tick logic | `src/main/java/com/leon1236/reforestry/apiculture/genetics/BeekeepingLogic.java` |
| Apiary + Bee House tile | `src/main/java/com/leon1236/reforestry/apiculture/tiles/TileBeeHousing.java` |
| Alveary controller | `src/main/java/com/leon1236/reforestry/apiculture/multiblock/AlvearyController.java` |
| Housing modifier aggregator | `src/main/java/com/leon1236/reforestry/apiculture/BeeHousingModifier.java` |
| Apiary speed modifier | `src/main/java/com/leon1236/reforestry/apiculture/ApiaryBeeModifier.java` |
| Bee House modifier | `src/main/java/com/leon1236/reforestry/apiculture/BeehouseBeeModifier.java` |
| Alveary territory modifier | `src/main/java/com/leon1236/reforestry/apiculture/AlvearyBeeModifier.java` |

**CE reference:**  
`forestry/apiculture/BeeHousingModifier.java` in `thedarkcolour-ForestryCE` — loops all housing modifiers for mutation, aging, production, pollination, genetic decay, territory, sealed/sky/hellish. Re-Forestry port: `BeeHousingModifier.java` (G1 **done**).

---

## Player interaction — side-by-side

What the **player** actually does in each version when using these blocks.

| Interaction | Forestry CE / IF | ForestryMC 1.12 | Re-Forestry |
|-------------|------------------|-----------------|-------------|
| **Open GUI** | Right-click block → `ContainerBeeHousing` (apiary/bee house) or alveary / part menus (swarmer, sieve, hygro) | Same pattern; older GUI classes (`GuiBeeHousing`) | Right-click → `ContainerBeeHousing` or `ContainerAlveary*` menus; screens under `apiculture/client/` |
| **Insert queen / drone** | Central slots in housing inventory | Same | Queen slot 0, drone slot 1 (`TileBeeHousing`) |
| **Collect products** | Product slots (7 in CE apiary inventory) | Same idea | Product slots 2–8 |
| **Frames** | Apiary only — 3 frame slots; frames wear each work cycle | Apiary only | Apiary has 3 frame slots when `hasFrames=true`; bee house and alveary have **no** frames (matches CE) |
| **Automation (hoppers/pipes)** | Apiary + alveary plain expose item capability; **bee house disables automation** | Bee house: `beeInventory.disableAutomation()` | Apiary + alveary plain: Fabric `ItemStorage` registered (G9 **done**); bee house automation blocked (G10 **done**) |
| **Climate control** | Alveary only — heater/fan (FE), hygro (fluids), integer temp/humidity steps | 1.12 used **float** climate with decay (different model) | Heater/fan/hygro implemented; integer steps like CE |
| **Multiblock feedback** | Chat/error on bad shape (slabs, plain top, air ring) | Same rules in 1.12 alveary | Same validation messages in `AlvearyController.isMachineWhole()` |
| **Alveary parts GUI** | Right-click swarmer / sieve / hygro block in formed structure | Same | `TileAlvearySwarmer`, `TileAlvearySieve`, `TileAlvearyHygroregulator` each open their own menu |
| **Hints (?) button** | Hint ledger keys per machine | Present in older forestry | Apiary, bee house, and alveary keys in `hints.properties` (G12 **done**) |
| **Power display** | CE climate blocks accept FE; GUI shows activatable ON state | N/A for apiary/bee house | Heater/fan ON/OFF blockstate + **power ledger** (aggregated FE) and **climate ledger** on alveary GUI; climate ledger on bee housing (G13 **done**) |

**Immersive Forestry:** Player-facing behavior matches CE for alveary/apiary/bee house; port differences are loader/API only, not gameplay numbers.

---

## Multiblock formation rules

All three modern trees (CE, IF, Re-Forestry) share the same alveary shape. Rules live in `AlvearyController.isMachineWhole()` and related hooks.

| Rule | Detail |
|------|--------|
| **Size** | Fixed **3×3×3** (27 blocks minimum). Limits in `AlvearyMultiblockSizeLimits.java`: min/max X/Y/Z = 3. |
| **Roof** | Layer **above** the top Y must be **wooden slabs** on every column of the 3×3 footprint (`BlockTags.WOODEN_SLABS`). |
| **Air ring** | At the **top Y** of the cube, the 8 positions **outside** the 3×3 (but within the 5×5 ring) must **not** be solid blocks — bees need “entrance” space. |
| **Top layer (Y max)** | Only **`alveary_block`** blocks — no heater/fan/etc. on the roof layer. |
| **Interior** | Only **`alveary_block`** — special parts must sit on **faces** of the cube, not inside. |
| **Controller inventory** | Queen/drone/products live on the controller; breaking the structure drops contents (`Containers.dropContents`). |

**Entrance visuals:** CE uses blockstate variants on plain alveary for entrance facing. Re-Forestry uses blockstate-only entrance on plain alveary; orphan `alveary_entrance` item/model assets removed (G15 **done**).

---

## Mechanics — Apiary vs Bee House vs Alveary

Intended **CE** behavior and Re-Forestry **today** (after P0–P2 implementation).

| Mechanic | Apiary | Bee House | Alveary | RF matches CE? |
|----------|--------|-----------|---------|----------------|
| **Production speed** | ×0.1 | ×0.25 | ×1.0 (full genome speed) | Yes — `BeeHousingModifier.modifyProductionSpeed` |
| **Mutation chance** | Normal | **0** (blocked) | Normal; **Stabiliser → 0** | Yes — wired in `Mating.attemptMutation` via housing modifiers (G2 **done**) |
| **Queen aging** | Normal (+1 life/work) | **~3× longer** (`aging / 3`) | Normal | Yes — `ageQueen` uses `modifyAging` (G3 **done**) |
| **Pollination** | ×1 | ×3 | ×1 | Yes — aggregator applies pollination modifier (G1 **done**) |
| **Genetic decay** | Frames reduce decay | **0** decay | Normal + swarmer **ignoble** princesses | Yes — `bee_pristine`, `bee_generation`, swarmer ignoble (G4 **done**) |
| **Territory** | ×1 | ×1 | **×2** (`AlvearyBeeModifier`) | Yes — modifier registered on controller |
| **Frames** | 3 slots, wear per cycle | None | None | Yes |
| **Work throttle** | 550 ticks between work | 550 | 550 | Yes (`WORK_THROTTLE = 550`) |
| **Second princess on queen death** | CE: **0%** | CE: **0%** | CE: **0%** | Yes — `SECOND_PRINCESS_CHANCE = 0` (G5 **done**) |
| **Climate** | Biome only | Biome only | Biome + heater/fan/hygro steps | Yes (integer step model) |
| **Automation** | Items in/out | **Blocked** | Items in/out on plain | Yes — G9 + G10 **done** |

---

## Component numbers — heater, fan, hygro, swarmer, sieve, stabiliser

Values from CE (`TileAlvearyClimatiser`, hygro recipes, swarmer tile) and Re-Forestry counterparts.

### Heater & fan (`TileAlvearyClimatiser`)

| Constant | CE | Re-Forestry |
|----------|-----|-------------|
| Temperature step per active tick | Heater **+1**, fan **−1** | Same |
| FE per operation | 50 | 50 |
| Max working time (ticks) | 20 | 20 |
| Energy storage capacity | **1000** FE | **1000** FE |
| Max FE insert per tick | **2000** FE | **2000** FE |
| Blockstate | ON while working | ON while working |

Re-Forestry matches CE for energy (G6 **present**).

### Hygroregulator (`TileAlvearyHygroregulator`)

| Fluid | Humidity steps | Temperature steps | Work duration |
|-------|----------------|-------------------|---------------|
| Water | +1 | −1 | 20 ticks (RF: recipe `time: 0` → default 20) |
| Lava | −1 | +1 | 20 ticks |
| Ice (`reforestry:ice`) | +2 | −2 | 10 ticks in recipe → 20 ticks active window in tile |

| Constant | Value |
|----------|-------|
| Tank capacity | 10 buckets |
| Bucket input slot | 1 (water/lava buckets; ice via fluid tank) |

Ice recipe exists at `src/main/resources/data/reforestry/recipe/hygroregulator/ice.json` (G7 **present**).

### Swarmer (`TileAlvearySwarmer`)

| Constant | CE / RF |
|----------|---------|
| Royal jelly slots | 4 |
| Royal jelly consumption chance | 1% per 500-tick cycle |
| Princess queue spawn attempt | Every **300** ticks when pending |
| Spawn search radius | **40** blocks |
| Ignoble princesses | CE marks ignoble; RF marks ignoble via `bee_pristine` / generation (G4 **done**) |

### Sieve (`TileAlvearySieve`)

| Slot | Purpose |
|------|---------|
| 0–3 | Pollen storage (capture from `onPollenRetrieved`) |
| 4 | Woven silk (required for capture) |

**CE `onTake` behavior:** removing silk clears pollen slots; removing pollen clears silk. Re-Forestry implements mutual clear on take (G8 **done**).

### Stabiliser (`TileAlvearyStabiliser`)

| Effect | Value |
|--------|-------|
| `modifyMutationChance` | **0.0** (blocks mutations when in structure) |

Effective — G2 wiring applies housing modifiers in mating.

---

## Gap inventory G1–G17

Complete list from the validation plan. Status reflects the Re-Forestry tree **as of 2026-07-30** — all G1–G17 gaps resolved after P0–P3 implementation.

### B1 — Critical gameplay

| ID | Gap | CE behavior | Re-Forestry today | Status |
|----|-----|-------------|-------------------|--------|
| **G1** | `BeeHousingModifier` aggregator | Single class loops all `IBeeModifier` methods for the housing | `BeeHousingModifier.java` aggregates all housing modifiers | **Present / Fixed** |
| **G2** | Mutation chance wiring | Housing `modifyMutationChance` applied in mating | `Mating.attemptMutation` applies housing modifier stack | **Present / Fixed** |
| **G3** | Aging wiring | Queen life cost scaled by `modifyAging` | `ageQueen` uses `BeeHousingModifier.modifyAging` | **Present / Fixed** |
| **G4** | Genetic decay + pristine/ignoble | Non-pristine queens decay; bee house decay 0; swarmer spawns ignoble | `bee_pristine`, `bee_generation`; swarmer ignoble princesses | **Present / Fixed** |
| **G5** | Second princess chance | **0%** | `SECOND_PRINCESS_CHANCE = 0` | **Present / Fixed** |
| **G6** | Climatiser energy buffer | Store 1000, receive 2000 | `TileAlvearyClimatiser`: CAPACITY 1000, MAX_INSERT 2000 | **Present** |
| **G7** | Hygro ice recipe | Ice fluid → +2 humidity, −2 temp | `data/reforestry/recipe/hygroregulator/ice.json` exists | **Present** |

### B2 — Component / inventory parity

| ID | Gap | CE behavior | Re-Forestry today | Status |
|----|-----|-------------|-------------------|--------|
| **G8** | Sieve silk/pollen clear | `onTake` clears paired slots | Mutual clear on silk/pollen take | **Present / Fixed** |
| **G9** | Item automation | Apiary + alveary plain: item I/O | Fabric `ItemStorage` on apiary + alveary plain | **Present / Fixed** |
| **G10** | Bee house automation block | No hopper I/O | Bee house disables automation | **Present / Fixed** |

### B3 — UX / assets / data

| ID | Gap | CE / expected | Re-Forestry today | Status |
|----|-----|---------------|-------------------|--------|
| **G11** | Block + GUI textures | Full alveary PNG set | Textures pulled from CE GitHub into `assets/reforestry/textures/` | **Present / Fixed** |
| **G12** | Alveary hints | Hint ledger key | Alveary key in `hints.properties` | **Present / Fixed** |
| **G13** | Power feedback heater/fan | FE + visible climate/power context | `GuiPowerLedger` (aggregated heater/fan FE) + `GuiClimateLedger` on alveary GUI; climate ledger on bee housing | **Present / Fixed** |
| **G14** | Alveary loot tables | Self-drop on break | `data/reforestry/loot_table/blocks/alveary_*.json` | **Present / Fixed** |
| **G15** | Orphan `alveary_entrance` assets | Entrance is plain blockstate | Orphan item/model assets deleted; block model kept | **Present / Fixed** |
| **G16** | Discovery stubs | Breeding book hooks | `BreedingTracker` wired in `BeekeepingLogic` + `TileLeaves` | **Present / Fixed** |
| **G17** | Client flower FX on alveary | Apiary scans flowers for FX | `AlvearyController` client flower scan feeds `doBeeFX` | **Present / Fixed** |

### Summary counts

| Status | IDs |
|--------|-----|
| **Present / Fixed** | G1–G17 |
| **Partial / polish / deferred** | — |
| **Missing** | — |

---

## Out of scope — 1.12 items (do not port)

These existed in ForestryMC 1.12 but were **dropped or replaced** in CE. Re-Forestry follows CE, not 1.12, for alveary work.

| 1.12 feature | Why out of scope |
|--------------|------------------|
| **Float climate + ×0.95 decay** | CE/RF use integer humidity/temperature **steps** |
| **Swarmer spawn interval 1000 ticks** | CE/RF use **300** |
| **Minecart apiary / bee house** | Removed in modern Forestry |
| **BuildCraft triggers** on apiary | CE optional BC; not part of Fabric port |
| **Village apiarist house** | Separate feature track |
| **Beekeeping difficulty modes** | Commented out even in CE |

---

## Textures — source of truth for G11 (done)

G11 resolved via fresh CE GitHub pull. For future texture updates, **do not** copy from the stale local folder `for textures only/`.

**Workflow used:**

1. **Fresh GitHub pull** of `thedarkcolour-ForestryCE` (e.g. via `python3 tools/graphify_clone.py` into  
   `MarkDown_Maker/Finished_github_clone/<date>/thedarkcolour-ForestryCE`).
2. **Copy from CE tree:**  
   `…/src/main/resources/assets/forestry/textures/block/apiculture/alveary*.png`  
   `…/assets/forestry/textures/gui/alveary.png`, `hygroregulator.png`, `sieve.png`, `swarmer.png`.
3. **Paste into Re-Forestry:**  
   `src/main/resources/assets/reforestry/textures/` — same relative paths; only namespace changes (`forestry` → `reforestry`).

---

## In-world verification (optional)

All documented gaps are resolved. Suggested smoke tests: formation, stabiliser mutation block, bee house hopper block, sieve onTake, swarmer ignoble, power/climate ledgers, breeding discovery, alveary bee FX.

Interactive scorecard:  
`/home/ivan/.cursor/projects/home-ivan-Documents-Kodiranje-Fabric-Forestry-26-2/canvases/alveary-cross-mod-validation.canvas.tsx`

---

## Primary source index

| Source | Repo / path |
|--------|-------------|
| Forestry CE | MCP `thedarkcolour-ForestryCE` — `AlvearyController.java`, `BeeHousingModifier.java`, `TileAlvearyClimatiser.java`, `InventoryAlvearySieve.java` |
| Immersive Forestry | MCP `thedarkcolour-Immersive-Forestry` — same class names, CE-aligned |
| ForestryMC 1.12 | MCP `ForestryMC-ForestryMC` — `TileApiary.java`, `TileBeeHouse.java` |
| Re-Forestry | `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/src/main/java/com/leon1236/reforestry/apiculture/` |
