# SuperMartijn642-SuperMartijn642sCoreLib — block

## Summary

Small Forge **1.16.5** package (`com.supermartijn642.core.block`, 7 Java files, ~885 LOC) that gives dependent mods a shared base for blocks and block entities: fluent `BlockProperties`, a `BaseBlock` with optional item-stack BE persistence (`tileData`), a `BaseBlockEntity` with split save/client/item NBT writers plus `dataChanged()` sync, voxel `BlockShape` helpers, and thin renames of vanilla tickable / entity-holding APIs. No assets of its own; harvest logic is tag-driven and patched via `ForgeHooksMixin` outside this package.

## Player/API surface

Public types consumers are expected to extend or call:

| Type | Role |
|---|---|
| `BaseBlock` | `Block` subclass. Constructor `(boolean saveTileData, Properties\|BlockProperties)`. Opt-in BE ↔ item NBT. Hooks: `interact(...)` → `InteractionFeedback`, `appendItemInformation(...)`. Translation key `namespace.block.path` via `Registries.BLOCKS`. Tag-backed `isToolEffective` / `getHarvestTool` / `getHarvestLevel`. |
| `BaseBlockEntity` | Abstract `TileEntity`. Subclasses implement `writeData()` / `readData()`; may override `writeClientData()` / `writeItemStackData()` (default = full `writeData`). `dataChanged()` marks dirty + sends block update. |
| `BaseBlockEntityType<T>` | Thin `TileEntityType` factory: `create(Supplier<T>, Block... validBlocks)` (`datafixer` arg `null`). |
| `BlockProperties` | Fluent mirror of `AbstractBlock.Properties` (material, collision, sound, light, strength, tool requirement, friction/speed/jump, occlusion, air, redstone/suffocate predicates, loot table). `toUnderlying()` builds vanilla properties (marked `@Deprecated`). |
| `BlockShape` | Immutable wrapper around `VoxelShape` / AABB lists: create (incl. `/16` pixel coords), `or`, offset, grow/shrink, flip, 90° rotate, intersect, edge/corner iteration. `getUnderlying()` deprecated. |
| `EntityHoldingBlock` | Extends `ITileEntityProvider`; implement `createNewBlockEntity()`, default `newBlockEntity(level)`. |
| `TickableBlockEntity` | Extends `ITickableTileEntity`; implement `update()`, default `tick()` delegates. |

Nested API: `BaseBlock.InteractionFeedback` = `{ PASS, CONSUME, SUCCESS }` mapping to `ActionResultType`.

## Architecture

```
BlockProperties ──toUnderlying()──► AbstractBlock.Properties
        │                                ▲
        │                         BlockPropertiesAccessor (mixin)
        ▼
   BaseBlock(saveTileData) ◄──place/drop/pick──► BaseBlockEntity
        │                         writeData / readData
        │                         writeClientData → update packet/tag
        │                         writeItemStackData → item "tileData"
        ▼
 EntityHoldingBlock + BaseBlockEntityType.create(...)
 TickableBlockEntity (tick rename)

 BlockShape ──used by──► render.RenderUtils (outside package)
 BaseBlock ──instanceof──► mixin.ForgeHooksMixin.canHarvestBlock redirect
```

Graph communities (corelib graphify): `BaseBlock` ~15, `BaseBlockEntity` ~12, `BlockProperties` ~7, `BlockShape` ~32. Cross-package edges: `Registries` (description id), `RenderUtils.renderShape*`, `ForgeHooksMixin`, `BlockEntityCustomItemRenderer` / `BlockEntityBasePacket` / `BlockEntityBaseContainer` (related BE tooling, not defined here).

Flow of `saveTileData == true`:

1. Break / middle-click → `writeItemStackData()` → stack NBT compound `"tileData"`.
2. Place → `setPlacedBy` reads `"tileData"` → `BaseBlockEntity.readData`.
3. Chunk save → outer `"data"` compound via `save`/`load`.
4. Client sync → `getUpdateTag` / gated `getUpdatePacket` use `writeClientData`; `onDataPacket` → `readData`.

## Data & assets

- **No** blockstates, models, textures, or lang under this package.
- Runtime tag binds on `BaseBlock` (vanilla ids): `mineable/{axe,hoe,pickaxe,shovel}`, `needs_{diamond,iron,stone}_tool`.
- NBT contracts: chunk/client key `"data"`; item-stack key `"tileData"`.
- Translation pattern: `{namespace}.block.{path}` (not vanilla `block.namespace.path`).
- Loot: `BlockProperties.noLootTable` / `lootTable` / `lootTableFrom`; loot-table supplier copied via mixin accessor when converting from vanilla properties.

## Dependencies

**Within CoreLib**

- `com.supermartijn642.core.registry.Registries` — block id for description key.
- `com.supermartijn642.core.mixin.BlockPropertiesAccessor` — get/set `lootTableSupplier` on `AbstractBlock.Properties`.
- `com.supermartijn642.core.mixin.ForgeHooksMixin` — harvest-level redirect when block is `BaseBlock`.
- Consumers outside package: `render.RenderUtils` (`BlockShape`), `item.BaseBlockItem` (separate item base), `network.BlockEntityBasePacket`, `gui.BlockEntityBaseContainer`.

**External (this clone)**

- Minecraft / Forge **1.16.5** APIs: `Block` / `TileEntity` / `ITileEntityProvider` / `ITickableTileEntity`, `Material` / `MaterialColor`, `ToolType`, `SUpdateTileEntityPacket`, `LootParameters.BLOCK_ENTITY`, Forge NBT `Constants`.
- Guava `ImmutableSet` in `BaseBlockEntityType`.

**Not a dependency of Re-Forestry** — adopt ideas by copy/adapt only; never add CoreLib as a player dep (`reforestry-standalone-adopt`).

## Notable algorithms/contracts

1. **Item BE persistence** — only when `saveTileData`; only writes if `writeItemStackData()` non-empty; only stamps stacks whose `BlockItem.getBlock() == this`.
2. **Update packet gate** — `dataChanged` starts `true` (Create contraptions / place-back); `getUpdatePacket()` returns a packet once then clears the flag; null if unchanged.
3. **Client vs disk vs item** — one `readData`; three writers so mods can strip inventory/energy from items or shrink client payloads without forking save format.
4. **Harvest from tags** — tool effectiveness / harvest tool / harvest level (0–3 / -1) derived from mineable + needs_* tags; mixin lets multi-tool items take best matching harvest level for `BaseBlock`s.
5. **BlockShape transforms** — rotate/flip operate on AABB list then rebuild shape (not native VoxelShape rotate); `intersects` uses axis-aligned bounds of the shapes, not full voxel boolean ops.
6. **`toUnderlying` quirk** — calls `properties.strength(explosionResistance)` then separately assigns `destroyTime` (explosion resistance and destroy time are set independently after).

## Port relevance to Re-Forestry

| Idea | Verdict for RF (Fabric 26.2) |
|---|---|
| Depend on / ship CoreLib | **No** — standalone adopt only. |
| `BaseBlock` / `BlockProperties` Material + Forge `ToolType` API | **Skip as-is** — 26.2 uses `BlockBehaviour.Properties`, tag-only mining; no `ToolType` / `getHarvestLevel` overrides. |
| `saveTileData` / `"tileData"` round-trip | **Optional pattern** — useful if a machine should keep config when broken/picked; RF already has CE-style `TileForestry` / `TileBase` / per-tile `saveAdditional`/`loadAdditional` (`ValueInput`/`ValueOutput`). Only adopt if a specific machine needs item-carried state. |
| Split `writeData` / `writeClientData` / `writeItemStackData` | **Useful contract idea** — RF sync is often ad hoc; a small internal helper on `TileForestry` could mirror this without copying CoreLib types. Prefer Fabric/vanilla `ClientboundBlockEntityDataPacket` / `getUpdateTag` equivalents. |
| `TickableBlockEntity` / `EntityHoldingBlock` | **Obsolete wrappers** — modern MC uses `BlockEntityTicker` + `EntityBlock.newBlockEntity`; RF already does this (e.g. hives, signs). |
| `BlockShape` rotate/flip/pixel create | **Low priority** — vanilla `Shapes` / VoxelShape utilities usually enough; only worth a local util if alveary/machine collision needs shared rotate-by-facing helpers. |
| Translation `ns.block.path` | **Do not copy** — RF/CE use standard `block.reforestry.*` lang keys. |
| Mixin harvest / loot-table accessor | **Forge-only** — not applicable on Fabric 26.2. |

Net: treat this module as a **reference for small BE sync/item-persist patterns**, not as something to port wholesale. Primary Forestry logic stays CE/IF.

## Source map

Clone root: `MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/SuperMartijn642-SuperMartijn642sCoreLib`

| File | Lines | Notes |
|---|---|---|
| `src/main/java/com/supermartijn642/core/block/BaseBlock.java` | ~203 | Block base, tileData, interact, harvest tags |
| `src/main/java/com/supermartijn642/core/block/BaseBlockEntity.java` | ~109 | NBT + sync |
| `src/main/java/com/supermartijn642/core/block/BaseBlockEntityType.java` | ~28 | Type factory |
| `src/main/java/com/supermartijn642/core/block/BlockProperties.java` | ~271 | Fluent properties → vanilla |
| `src/main/java/com/supermartijn642/core/block/BlockShape.java` | ~234 | VoxelShape wrapper |
| `src/main/java/com/supermartijn642/core/block/EntityHoldingBlock.java` | ~21 | TE provider rename |
| `src/main/java/com/supermartijn642/core/block/TickableBlockEntity.java` | ~19 | Tick rename |

Related (other modules, cited only):

- `.../mixin/BlockPropertiesAccessor.java`
- `.../mixin/ForgeHooksMixin.java` (`BaseBlock` harvest branch)
- `.../render/RenderUtils.java` (`BlockShape` rendering)
- `.../network/BlockEntityBasePacket.java`
- `.../gui/BlockEntityBaseContainer.java`
- `.../item/BaseBlockItem.java`

Evidence: graphify alias `corelib`; MCP repo `SuperMartijn642-SuperMartijn642sCoreLib`; inventory `Reports/SuperMartijn642-SuperMartijn642sCoreLib/00-INVENTORY.md`.

## Open questions/gaps

1. This clone is **Forge 1.16.5 / CoreLib 1.1.22** only — newer CurseForge/modrinth builds may retarget Fabric or modern Yarn/Mojmap APIs; not verified here.
2. No in-repo production subclasses of `BaseBlock` / `BaseBlockEntity` (only test client uses `BlockShape`) — real call sites live in other SuperMartijn642 mods not in this clone.
3. Whether Create-contraption `dataChanged = true` initial behavior is still needed on modern loaders is untested for RF.
4. Exact 26.2 replacements for `getPickBlock` / loot `BLOCK_ENTITY` / update packets should be confirmed in `Minecraft-26.2` before any adopt, if RF ever implements item-carried BE data.
5. `BlockShape.intersects` is AABB-bounds based; callers needing true voxel intersection must use `VoxelShape` boolean ops instead — gap if RF copied the helper blindly.
