# thedarkcolour-gendustry — block

## Summary

Minimal Forge **1.20.1** Forestry CE addon package (`thedarkcolour.gendustry.block`, 3 Java files, ~80 LOC). Defines one concrete machine block class and one enum that wires **ten** Gendustry machines to their block entities and server/client tickers. All placement, facing, GUI open, fluid interaction, inventory drop, and ticker dispatch come from Forestry CE `BlockBase`; this package only sets block material properties and binds each enum constant to a `FeatureTileType` + `MachineProperties`. Size: **S**. No assets or datagen live here — sibling `data` and `registry` packages consume `GendustryMachineType`.

## Player/API surface

| Surface | What players / other mods see |
|---|---|
| Block class | `GendustryMachineBlock` — one shared class for all machines (not per-machine subclasses) |
| Type enum | `GendustryMachineType` — internal wiring; implements Forestry `IBlockType` |
| Registry ids | `gendustry:{snake_case_enum}` via Forestry `FeatureBlockGroup` (see table below) |
| Items | Vanilla `BlockItem` per machine (registered in `registry/GBlocks`, not this package) |
| Interaction | Inherited CE behavior: right-click opens machine GUI; shift-click fluid handler; horizontal `facing` on place |
| Public addon API | **None in this package** — no interfaces or extension points for other mods |

### Machine constants → registry ids

| Enum constant | Registry id | Server ticker | Client ticker |
|---|---|---|---|
| `INDUSTRIAL_APIARY` | `gendustry:industrial_apiary` | yes | **yes** (only machine with client tick) |
| `MUTAGEN_PRODUCER` | `gendustry:mutagen_producer` | yes | — |
| `DNA_EXTRACTOR` | `gendustry:dna_extractor` | yes | — |
| `PROTEIN_LIQUEFIER` | `gendustry:protein_liquefier` | yes | — |
| `SAMPLER` | `gendustry:sampler` | yes | — |
| `MUTATRON` | `gendustry:mutatron` | yes | — |
| `ADVANCED_MUTATRON` | `gendustry:advanced_mutatron` | yes | — |
| `IMPRINTER` | `gendustry:imprinter` | yes | — |
| `GENETIC_TRANSPOSER` | `gendustry:genetic_transposer` | yes | — |
| `REPLICATOR` | `gendustry:replicator` | yes | — |

Serialized names come from `enum.name().toLowerCase(Locale.ENGLISH)` inside `MachineProperties.Builder`.

Player-facing display names are datagen overrides in `data/English.java` (e.g. DNA Extractor); most machines use default Forestry block translation from registry path.

## Architecture

```
registry/GBlocks
  └─ FeatureBlockGroup<GendustryMachineBlock, GendustryMachineType>
       .blockGroup(GendustryMachineBlock::new, GendustryMachineType.values())
       .item(BlockItem) → create()

GendustryMachineType (enum, IBlockType)
  ├─ per constant: MachineProperties.Builder<>(FeatureTileType, snake_name)
  │     .setServerTicker(BE::serverTick)
  │     [.setClientTicker(BE::clientTick)]  ← INDUSTRIAL_APIARY only
  │     .create()
  └─ getMachineProperties() / getSerializedName()

GendustryMachineBlock extends BlockBase<GendustryMachineType>
  └─ constructor: Properties (METAL, SAND map color, IRON_XYLOPHONE, requiresCorrectToolForDrops)
  └─ BlockBase (Forestry CE): strength 2.0, FACING, newBlockEntity, getTicker, use/GUI, onRemove drops

registry/GBlockEntities  ←── FeatureTileType refs wired back to GBlocks.MACHINE.get(type)
blockentity/*            ←── ticker method refs in enum constructors
data/BlockModels         ←── iterates GendustryMachineType.values()
data/BlockLoot           ←── iterates GBlocks.MACHINE.getBlocks()
data/ModTags             ←── pickaxe mineable on all MACHINE blocks
```

Graphify (`gendustry`, BFS from `GendustryMachineBlock` / `GendustryMachineType`): community ~19 for the enum, ~27 for the block class; primary outbound edges to `GBlocks`, `GBlockEntities`, each `*BlockEntity`, Forestry `BlockBase` / `MachineProperties` / `IBlockType` / `IForestryTicker`, and datagen (`BlockModels`, `BlockLoot`).

Circular compile-time coupling: `GendustryMachineType` imports `GBlockEntities` and blockentity classes; `GBlockEntities` references `GBlocks.MACHINE.get(GendustryMachineType.*)` — standard Forestry feature-registry pattern, resolved at static init.

## Data & assets

Nothing under `block/` itself. Assets and generated data are driven by the enum + `GBlocks.MACHINE`:

| Kind | Location / contract |
|---|---|
| Textures | `src/main/resources/assets/gendustry/textures/block/{machine}_side.png`, `_bottom.png`, `_top.png` (10 machines × 3 faces) |
| Block models | Datagen `data/BlockModels`: `cubeBottomTop` per machine, texture path from block name |
| Blockstates | `src/generated/resources/assets/gendustry/blockstates/{machine}.json` — single variant, no facing in model (facing is blockstate property from CE `BlockBase`) |
| Loot | `data/BlockLoot`: `dropSelf` for every `GendustryMachineBlock` |
| Tags | `data/ModTags`: all machines → `minecraft:mineable/pickaxe` |
| Lang | Mostly auto; explicit override example: `DNA Extractor` in `data/English.java` |
| Creative tab | `registry/GCreativeTabs`: all `GBlocks.MACHINE` blocks; tab icon = industrial apiary |

Fluids (`mutagen`, `liquid_dna`, `protein`) are **not** part of this package — only machine blocks are enumerated here.

## Dependencies

**Within Gendustry (this clone)**

| Package / class | Role |
|---|---|
| `registry/GBlocks` | Registers `FeatureBlockGroup` from enum values |
| `registry/GBlockEntities` | `FeatureTileType` targets referenced in enum constructors |
| `blockentity/*` | Static ticker method references (`::serverTick`, `::clientTick`) |
| `data/BlockModels`, `BlockLoot`, `ModTags`, `English` | Consume enum or registered blocks |

**Forestry CE (compile-time, mandatory mod)**

| Type | Role |
|---|---|
| `forestry.core.blocks.BlockBase` | Machine block superclass: BE creation, tickers, GUI, drops, FACING |
| `forestry.core.blocks.IBlockType` | Enum contract |
| `forestry.core.blocks.IMachineProperties` / `MachineProperties` | TE type, tickers, serialized name, shape |
| `forestry.core.tiles.IForestryTicker` / `TileForestry` | Ticker typing |
| `forestry.modules.features.FeatureTileType` | Deferred BE registration handle |

**Minecraft / Forge 1.20.1**

- `Block.Properties`: `SoundType.METAL`, `MapColor.SAND`, `NoteBlockInstrument.IRON_XYLOPHONE`, `requiresCorrectToolForDrops()`
- Inherited from CE `BlockBase`: Forge `FluidUtil`, horizontal placement facing

**Not a player dependency for Re-Forestry** — adopt by copy/adapt into `com.leon1236.reforestry.gendustry.*` when the addon ships; never depend on the Gendustry jar (`reforestry-standalone-adopt`).

## Notable algorithms/contracts

1. **Single block class, enum-driven instances** — One `GendustryMachineBlock` constructor arg selects behavior via `blockType.getMachineProperties()`; no per-machine block subclasses.
2. **Name derivation** — Enum constant `DNA_EXTRACTOR` → registry path `dna_extractor` via `Locale.ENGLISH` lowercasing of `name()`, not a separate string field (contrast Re-Forestry `BlockTypeFactoryPlain`, which takes an explicit serialized name).
3. **Ticker wiring at enum init** — Method references to `blockentity` static tickers are stored in `MachineProperties` and selected in `BlockBase.getTicker` by side (`level.isClientSide`).
4. **Client tick exception** — Only `INDUSTRIAL_APIARY` registers `IndustrialApiaryBlockEntity::clientTick`; all other machines are server-tick-only at the block layer (any client animation would need BER/TESR elsewhere).
5. **Block properties stack** — Subclass passes METAL + tool requirement; CE `BlockBase` constructor adds `strength(2.0f)` and registers default `FACING = NORTH`.
6. **Break behavior** — CE `BlockBase.onRemove` drops container contents, calls `TileForestry.onDropContents`, socket drops — machines inherit full Forestry machine teardown without overrides in this package.

## Port relevance to Re-Forestry

**High structural match — pattern already exists in RF core/factory.** Re-Forestry ships the same Forestry-style machine block stack:

| Gendustry (Forge CE addon) | Re-Forestry (Fabric 26.2) |
|---|---|
| `GendustryMachineBlock` extends CE `BlockBase` | `BlockFactoryPlain` extends `BlockMachine` |
| `GendustryMachineType` enum + `MachineProperties.Builder` | `BlockTypeFactoryPlain` enum + same builder pattern |
| `FeatureBlockGroup` via `ModFeatureRegistry` | `FeatureBlockGroup` via `FeatureGroup` / module registry |
| `FeatureTileType` | `FeatureBlockEntityType` / `FactoryTiles.*` |
| Forge `FluidUtil` in CE `BlockBase` | `FluidHelper` in RF `BlockMachine` |

When porting the **Gendustry addon** into Re-Forestry (`reforestry:gendustry` module, not started in `src/` yet):

| Action | Verdict |
|---|---|
| Copy enum + thin block class shape | **Yes** — mirror as e.g. `BlockGendustryMachine` + `BlockTypeGendustry` under `com.leon1236.reforestry.gendustry.blocks` |
| Keep registry ids | **Yes** — CE/Gendustry ids are contracts (`gendustry:*` → `reforestry:*` or keep `gendustry` subdomain per addon mapping) |
| Depend on Gendustry mod | **No** |
| Reuse CE `BlockBase` verbatim | **No** — extend RF `BlockMachine`; add `MapCodec` like `BlockFactoryPlain` (26.2 requirement) |
| `noOcclusion()` on factory-style machines | **Evaluate per machine** — RF factory uses it for TESR/BER visibility; Gendustry clone does not set it on `GendustryMachineBlock` |
| Industrial apiary client ticker | **Port with blockentity** — only enum entry with `setClientTicker`; confirm RF apiculture client tick needs when `IndustrialApiary` tile is ported |
| Explicit serialized name in enum | **Optional** — RF factory uses explicit strings; Gendustry derives from enum — either works if naming stays stable |

Net: this module is **~80 lines of wiring**, not gameplay logic. Porting effort for blocks is low once `blockentity`, `menu`, and registry modules exist; the real work is elsewhere. Treat this report as the blueprint for the enum ↔ tile ↔ ticker table above.

## Source map

Clone root: `MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry`

| Path | Lines | Role |
|---|---|---|
| `src/main/java/thedarkcolour/gendustry/block/GendustryMachineBlock.java` | 13 | Thin `BlockBase` subclass; block properties only |
| `src/main/java/thedarkcolour/gendustry/block/GendustryMachineType.java` | 64 | Ten-machine enum; `MachineProperties` + ticker binding |
| `src/main/java/thedarkcolour/gendustry/block/package-info.java` | 3 | MC nullness annotations |

Related (outside package, cited for wiring/assets only):

| Path | Role |
|---|---|
| `.../registry/GBlocks.java` | `FeatureBlockGroup` registration |
| `.../registry/GBlockEntities.java` | BE types referenced by enum |
| `.../registry/GCreativeTabs.java` | Creative tab lists all machines |
| `.../data/BlockModels.java` | Model datagen loop over enum |
| `.../data/BlockLoot.java` | Loot for all machine blocks |
| `.../data/ModTags.java` | Pickaxe mineable tag |
| `.../data/English.java` | Display name overrides |
| CE `forestry/core/blocks/BlockBase.java` | Inherited machine behavior (MCP / ForestryCE) |

Re-Forestry analogs: `core/blocks/BlockMachine.java`, `factory/blocks/BlockFactoryPlain.java`, `factory/blocks/BlockTypeFactoryPlain.java`, `factory/features/FactoryBlocks.java`.

Evidence: graphify alias `gendustry`; MCP `thedarkcolour-gendustry`; inventory `Reports/thedarkcolour-gendustry/00-INVENTORY.md`.

## Open questions/gaps

1. **Facing in models** — Generated blockstates are single-variant; confirm in-game whether CE `BlockBase` FACING affects collision/GUI only or if models should rotate (not defined in this package).
2. **Client tick scope** — Why only industrial apiary gets `clientTicker` at block level; other machines may rely purely on server logic + static models — verify in `blockentity` / `client` modules.
3. **26.2 codec** — Gendustry clone predates RF `MapCodec` on machine blocks; addon port must add `codec()` on the RF block class.
4. **Addon namespace** — Final ids under `reforestry:gendustry/*` vs historical `gendustry:*` need a decision in addon registration docs before datagen.
5. **Occlusion / render shape** — No overrides here; if any Gendustry machine needs BER (like RF rainmaker), properties may need `noOcclusion()` or custom shape on the RF port — not inferable from this package alone.
6. **No RF gendustry code yet** — Port relevance is forward-looking; blockentity/recipe/item modules must land before this wiring is useful.
