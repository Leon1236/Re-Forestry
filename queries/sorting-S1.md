# S1 — Genetic filter block

**Date:** 2026-08-20  
**Stage:** Wave 3 `S1`. Playable `reforestry:genetic_filter`. No butterfly rules (`S2`).

## Item storage (not worldly mapping)

CE exposes a per-face NeoForge `ItemHandlerFilter`: one insert-only slot that routes into the 6 output buffers (`Direction.get3DDataValue`). Extraction is empty. The tile then pushes those buffers into adjacent inventories every 5 ticks.

Re-Forestry registers Fabric `ItemStorage.SIDED` on `TileGeneticFilter` with `FilterItemStorage` (same insert-only routing). **Do not** use `InventoryHelper.registerSided` / `ContainerStorage` — that maps hopper faces onto worldly slots, which is the wrong path for this block.

Push-out uses `ItemStorage.SIDED.find` on the neighbor. Leftovers drop as `ItemEntity` at CE’s coordinates (`x`, `y + 0.5`, `z`). `isConnected` is `ItemStorage.SIDED.find != null`, plus a `Container` BE fallback like CE’s second inventory lookup.

Logic NBT stays under `"Logic"` with CE keys `TypeFilter{i}` / `GenomeFilterS{i}-{j}-{0|1}`, written through `ValueInput` / `ValueOutput` children (not `CompoundTag` tile NBT).

Packets look up `TileGeneticFilter` at the `BlockPos`. No Fabric `AttachmentType`. `FilterLogic.sendToServer` uses `ILocationProvider.getCoordinates()` and a client `FilterPackets.Sender` (`ClientPlayNetworking`). Payload ids: `reforestry:filter_change_rule`, `reforestry:filter_change_genome`, `reforestry:gui_update_filter`. Types + server handlers are registered from `ModuleSorting.init()` (CE `registerPackets` on the sorting module), not from core `PacketRegistry`, so a disabled sorting module never decodes these payloads against a missing `IFilterManager`.

## Identifier species picker

CE species slots store `ISpecies`. Wave 5 `GP0a1` has not landed that type.

Picker entries are discovered **bee** and **tree** ids from `BreedingTrackerManager` / `IBreedingTracker.getDiscoveredSpecies()`. Icons are default drone / sapling stacks (`BeeStackHelper` + `ArboricultureGenetics.getDefaultGenome`). Carried bee/germling sets active (left) or inactive (right) via `IndividualItems` + `GeneticItemHelper.speciesId`. Right-click with an empty cursor clears.

Unknown ids are skipped (`getSpeciesSafe == null`). Compare species with `equals`, not `==`.

## Sprite blit (no CE atlas)

CE uses `IForestryClientApi.getTextureManager().getSprite`. We blit PNGs already at `assets/reforestry/textures/reforestry/atlas/gui/analyzer/{name}.png`. Sprite id `reforestry:analyzer/closed` maps to `textures/reforestry/atlas/gui/analyzer/closed.png`.

Local sapling icon file is `sapling.png` (CE `analyzer/tree_sapling`). `ArboricultureFilterRuleType.SAPLING` points at `reforestry:analyzer/sapling`.

GUI is 26.2 `ScreenForestry` (`extractRenderState` / `MouseButtonEvent` / `GuiGraphicsExtractor`). Widgets live under `sorting/client` only — not a second global GUI framework.

## Recipe tag remaps

| CE | Re-Forestry |
|---|---|
| `forestry:propolis` | `reforestry:propolis_normal` in tag `reforestry:genetic_samples` |
| `forestry:date` | `reforestry:fruit_dates` (`EnumFruit.DATES`) in tag `reforestry:forestry_fruits` |
| `forestry:cherry` etc. | PREFIX `fruit_cherry` … `fruit_feijoa` (verified against `assets/reforestry/items/`) |

Recipe output count is 2. Loot self-drops. `#minecraft:mineable/pickaxe`. Creative tab after analyzer/escritoire when sorting is loaded.

Bee stages are `"drone"` / `"princess"` / `"queen"` (`ItemBeeGE`). Tree stages are `"sapling"` / `"pollen"` (`ItemGermlingGE`). `ApicultureFilterRule.init()` loads the enum so constructors `addLogic` onto `DefaultFilterRuleType` containers. No `LepidopterologyFilterRuleType`.

CE `ITEM` / `ANYTHING` `isValid(FilterData)` return true, but hopper insert uses `getValidDirections` → `IndividualItems.filter` first, so dirt never builds `FilterData`. Matches CE.

Species picker scrollbar uses `ceil(size / 11) - 4` so a remainder row is reachable (CE integer division hid the last row).
