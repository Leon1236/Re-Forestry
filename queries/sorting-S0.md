# S0 — Filter API + sorting module

**Date:** 2026-08-20  
**Stage:** Wave 3 `S0`. No genetic filter block.

## FilterData over IndividualItems

CE `FilterData` is `(ISpeciesType, IIndividual, ILifeStage)`. Wave 5 `GP0a1` has not landed those types.

Re-Forestry uses `FilterData(Identifier typeId, IGenome genome, String stage)` on top of `IndividualItems`:

- `typeId` is `IndividualItems.getSpeciesTypeId` (`reforestry:bee_species` / `reforestry:tree_species`)
- `genome` is the item’s `IGenome`
- `stage` is the life-stage string (`drone` / `princess` / `queen` / `larvae`, or germling stages)

Species slots on `IFilterLogic` are species `Identifier`s, not `ISpecies`. Compare with `equals`, not `==`. Active/inactive ids come from `GeneticItemHelper.speciesId` (`BeeChromosomes.SPECIES` / `TreeChromosomes.SPECIES`). Do not add a second `IndividualItems`.

## No Fabric attachments

CE exposes filter logic as NeoForge `ForestryCapabilities.FILTER_LOGIC` on the block entity. Packets then look up the capability.

S0 has no tile. S1 packets talk to `TileGeneticFilter` (or a tiny `IFilterLogicSource` on the BE). Do **not** add a Fabric `AttachmentType` for filter logic.

S1 `sendToServer` is live: `FilterPackets` + client `ClientPlayNetworking` from `SortingClientHandler`. Nested NBT keys stay CE: `TypeFilter{i}` / `GenomeFilterS{i}-{j}-{0|1}`. GUI buffers use `FriendlyByteBuf.writeIdentifier` (26.2 name for CE `writeResourceLocation`). Filter payload types are registered from `ModuleSorting.init()`, matching CE module `registerPackets`.

## Plugin `registerFilter` vs CE `IGeneticRegistration`

CE registers rule types on `IGeneticRegistration.registerFilterRuleType` during `IForestryPlugin.registerGenetics`. `PluginManager` then builds `FilterManager` from `GeneticRegistration.getFilterRuleTypes()`.

Re-Forestry has no `IGeneticRegistration` yet (`GP0b`). S0 adds a small `IFilterRegistration` plus `IForestryPlugin.registerFilter` (default empty). `PluginManager.runFilterRegistration()` collects types and sets `IForestryApi.getFilterManager()`. `ReforestryPlugin` seeds `DefaultFilterRuleType.values()`. `GP0b` can fold this into genetics registration later.

Rule uids are `reforestry.default.closed` (lowercase), not registry ids. Sprites are `reforestry:analyzer/closed` matching files already under `assets/reforestry/textures/reforestry/atlas/gui/analyzer/`.

S1 `sendToServer` uses `ILocationProvider.getCoordinates()` (our name for CE `getBlockPos()`). `FilterLogic` keeps `locatable` for that.

`IFilterSlotDelegate` is CE’s general inventory slot-accept API (`ItemInventory` / `SlotFiltered`), not `genetics.filter`. `ItemInventory` implements it (`isLocked` default false). Genetic-filter facing slots are `SlotFilterFacing` (S1). CE `SlotFiltered` is a shared GUI helper for other inventories, not the genetic filter. `IInventoryAdapter` is still the older core inventory gap, not this stage.
