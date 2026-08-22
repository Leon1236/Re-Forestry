# Wave 3 — Sorting (`reforestry:sorting`)

**Date:** 2026-08-20  
**Source:** `queries/remaining-work-stages.md` Wave 3. CE 1.21.1 `forestry.core.content.sorting` (~24 Java) + `forestry.api.core.genetics.filter`.  
**Our package:** `com.leon1236.reforestry.sorting`  
**API package:** `com.leon1236.reforestry.api.genetics.filter` (not `api.core.genetics.filter`).  
**Mail** stays out of scope. **Do not commit.**  
**Do not** start farming `G0` or genetics `GP0a`. **S2** waits on Track D butterfly items.

## Registry ids (CE 1.21.1 contracts)

| Kind | Id |
|---|---|
| Block / tile / menu | `reforestry:genetic_filter` |
| Module | `reforestry:sorting` |
| C2S rule | `reforestry:filter_change_rule` |
| C2S genome | `reforestry:filter_change_genome` |
| S2C GUI | `reforestry:gui_update_filter` |
| Damage / other | none |

Leftover assets already use `genetic_filter` (blockstate, item model, `items/genetic_filter.json`, GUI `filter.png` / `filter_selection.png`, block textures). **Reuse them.** Do not invent a second id.

## Already present (do not redo)

- Blockstate multipart + filter models under `models/block/filter/`.
- Item model + 26.2 `items/genetic_filter.json`.
- Textures: `textures/block/genetic_filter.png`, `genetic_filter_particle.png`, `textures/gui/filter.png`, `filter_selection.png`.
- Analyzer rule icons under `textures/reforestry/atlas/gui/analyzer/{closed,anything,item,bee,…}.png`.
- Lang: `block.reforestry.genetic_filter`, `for.module.sorting.description`, `for.gui.filter.reforestry.default.*` / `.apiculture.*` / `.arboriculture.*` (and lepidopterology keys — leave unused until S2).
- Genetics access: `IndividualItems` (`IGenome` + life-stage **string** + species-type `Identifier`). Bee stages `"drone"` / `"princess"` / `"queen"` / `"larvae"`. Tree stages from `ItemGermlingGE`.
- Chromosomes: `BeeChromosomes.SPECIES` / `ACTIVITY` / `CAVE_DWELLING` / `TOLERATES_RAIN`. `IActivityType.NIGHT_TIME = 15000L`.
- Trackers: `IBreedingTracker.getDiscoveredSpecies()` via `BreedingTrackerManager`.
- Networking helpers: `PacketRegistry` + `WorktableRecipeRequestPayload` / `EscritoireGameSyncPayload` shape.
- Item transfer: `ItemStorage.SIDED` + `InventoryHelper.registerSided` (WorldlyContainer). Filter insert is **not** worldly-slot mapping — custom `Storage<ItemVariant>` per facing.
- `INbtReadable` / `INbtWritable` / `ILocationProvider` already in `api.core`.
- `TileForestry` + `updateOnInterval`.
- `IForestryPlugin` + `PluginManager` (apiculture / arboriculture / circuits only so far).
- Creative tab: `CoreCreativeTabs` (gate like engines).

## Fabric vs CE (must document in `queries/sorting-S0.md`)

| CE | Re-Forestry |
|---|---|
| `FilterData(ISpeciesType, IIndividual, ILifeStage)` | `FilterData(Identifier typeId, IGenome genome, String stage)` over `IndividualItems`. **Do not** invent `ISpecies` / `IIndividual` here — that is Wave 5 `GP0a1`. |
| `IFilterLogic` genome slots are `ISpecies<?>` | Store **species `Identifier`**. Compare with `equals`, not `==`. |
| `IGeneticRegistration.registerFilterRuleType` | Small `IFilterRegistration` + `IForestryPlugin.registerFilter` default. `GP0b` can absorb this later. |
| NeoForge `ForestryCapabilities.FILTER_LOGIC` | Logic lives on the tile. Packets look up `TileGeneticFilter` (or a tiny `IFilterLogicSource` on the BE). **No** Fabric `AttachmentType`. |
| NeoForge `Capabilities.ItemHandler` + `ItemHandlerFilter` | Fabric `ItemStorage.SIDED` + custom insert-only `Storage<ItemVariant>` per facing (CE routes insert to valid **output** faces, not into the input face slot). |
| `CompoundTag` tile NBT | `ValueInput` / `ValueOutput`. Nested `"Logic"` keeps CE keys `TypeFilter{i}` / `GenomeFilterS{i}-{j}-{0\|1}`. |
| GUI atlas `IForestryClientApi.getTextureManager().getSprite` | Blit analyzer PNGs already at `textures/reforestry/atlas/gui/analyzer/{name}.png`. Sprite id `reforestry:analyzer/closed` etc. Local sapling icon file is `sapling.png` (CE `tree_sapling.png`). |
| `SpeciesUtil.getAnySpecies` | Resolve bee via `ApicultureGenetics.getSpecies`, tree via arboriculture equivalent; unknown id → null. |
| Filter rule uid `forestry.default.closed` | `reforestry.default.closed` (lang already `for.gui.filter.reforestry.default.closed`). Same for `reforestry.apiculture.*` / `reforestry.arboriculture.*`. |
| Recipe tag `forestry:propolis` | Local item is `reforestry:propolis_normal`. Tag `reforestry:genetic_samples`. |
| Recipe tag fruits `forestry:cherry` | Local ids are PREFIX `fruit_cherry` etc. Tag `reforestry:forestry_fruits`. |

## Stage S0 — Filter API + module (S)

**Exit:** Module toggles in `config/reforestry/modules.properties`. `IForestryApi.getFilterManager()` holds **default** rules. **No block.**

- API: `FilterData`, `IFilterLogic`, `IFilterManager`, `IFilterRule`, `IFilterRuleType` under `api.genetics.filter`.
- Optional tiny `IFilterSlotDelegate` under `api.core` if slots need it (CE has it).
- `DefaultFilterRuleType` enum in `sorting` (CLOSED / ANYTHING / ITEM / PURE_BREED / NOCTURNAL / PURE_NOCTURNAL / FLYER / PURE_FLYER / CAVE / PURE_CAVE). CLOSED/ANYTHING/ITEM are non-containers; the rest are empty containers until S1 adds bee logic.
- `FilterManager` + `FilterLogic` + `AlleleFilter` (Identifier species).
- `IFilterRegistration` (`registerFilterRuleType` / `registerFilterRuleTypes`). `PluginManager.runFilterRegistration()`. `ReforestryPlugin.registerFilter` seeds `DefaultFilterRuleType.values()`.
- `IForestryApi.getFilterManager()` + setter on `ForestryApiImpl`.
- `ModuleSorting` `@ForestryModule` name/description from CE lang (`for.module.sorting.description` already exists). Id `reforestry:sorting`, depends `core`. `init()` may construct FilterManager if plugins already ran; otherwise PluginManager builds it. Empty `SortingClientHandler`. Register in `ReForestry.onInitialize()`.
- `queries/sorting-S0.md` (FilterData + no attachments + plugin split).
- Update `files/implemented-features.md` + tick remaining-work-stages S0.

## Stage S1 — Genetic filter block (M)

**Player exit:** Place `reforestry:genetic_filter`. GUI: set a side to accept bee/tree species A, reject dirt / species B. Hopper (or adjacent inventory) in → matching items out the configured faces. Save/reload keeps rules.

- `BlockGeneticFilter`: 6 boolean face properties, CE voxel/AABB, `updateShape` from `isConnected`, `useWithoutItem` opens menu, ticker `TileGeneticFilter::serverTick` via existing tick helper (`updateOnInterval(5)`).
- `SortingBlocks` / `SortingTiles` / `SortingMenus` Feature* id `genetic_filter`.
- `TileGeneticFilter`: 6-slot inventory, `FilterLogic`, push to adjacent `ItemStorage` every 5 ticks, drop leftovers as entities (CE), `getValidDirections` via `IndividualItems.filter`. Custom sided item storage (not `InventoryHelper.registerSided` worldly map).
- Menu `ContainerGeneticFilter` + slots (`SlotGeneticFilter` player, `SlotFilterFacing` 6 sides). Screen `ScreenGeneticFilter` extends `ScreenForestry` (26.2 `extractRenderState` / `MouseButtonEvent`, not CE `GuiGraphics.blit` 1.21). Widgets: RuleWidget, SpeciesWidget, SelectionWidget, scrollbar — port under `sorting/client` (minimal widget helper; do not invent a second GUI framework). Species picker = **discovered** bee+tree ids from trackers; click with a bee/sapling in cursor to set; right-click clears.
- Packets via `PacketRegistry` (serverbound change rule/genome, clientbound GUI update). Open menus on other players refresh like CE `guiNeedsUpdate`.
- Bee rules: `ApicultureFilterRuleType` (BEE / DRONE / PRINCESS / QUEEN) + `ApicultureFilterRule` attaching to default containers. Tree: `ArboricultureFilterRuleType` (TREE / SAPLING / POLLEN). Register from `ReforestryPlugin.registerFilter` when those modules are loaded (or from module `init` if plugin already ran — pick one, document). **No** lepidopterology types (S2).
- PURE_NOCTURNAL: `activity.isActive(0, IActivityType.NIGHT_TIME, BlockPos.ZERO)` (CE). PURE_BREED: same-allele species pair (`AllelePair` / active id equals inactive id). NOCTURNAL: active activity equals `ActivityType.METATURNAL`. FLYER / CAVE: `TOLERATES_RAIN` / `CAVE_DWELLING` booleans (pure = both active and inactive).
- Recipe from CE `genetic_filter.json` (2 output). Tags `reforestry:genetic_samples` (`propolis_normal`) and `reforestry:forestry_fruits` (`fruit_cherry` … `fruit_feijoa`). Loot self-drop. `#minecraft:mineable/pickaxe`. Core tab when sorting loaded. F-ADV `recipes/misc/genetic_filter.json`. Tooltip if CE has one.
- `queries/sorting-S1.md` (item storage + species Identifier picker + sprite blit).

## Stage S2 — Butterfly rules (skip)

**Deps:** `D1+`. Do not register `LepidopterologyFilterRuleType`. Lang keys already exist; leave them.

## Validation

After each stage: full review (Must / Should / Nice — implement, do not park). After S1: one more full Wave 3 review + `./gradlew classes`.
