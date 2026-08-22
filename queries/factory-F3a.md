# Stage F3a — Machine scaffold (core)

## Files created

- `core/tiles/TickHelper.java` — 1:1 port of `forestry/api/util/TickHelper.java` (tick counter + interval check).
- `core/tiles/TileForestry.java` — slimmed `forestry/core/tiles/TileForestry.java`. Keeps: error logic (`IErrorLogicSource`, `IForestryApi.INSTANCE.getErrorManager().createErrorLogic()`, same as `TileHive`), tick interval helper (`updateOnInterval`, calls `TickHelper.onTick()` itself since there's no global `ForestryTicker` in this port), player usability (`isUsableByPlayer` → `TileUtil.isUsableByPlayer`), title/display name, and the menu provider hook via `ExtendedMenuProvider<BlockPos>` (`getScreenOpeningData` returns `getBlockPos()`; `createMenu` stays abstract via `MenuProvider`, same "mirror `ApicultureMenuTypes`/`TileBeeHousing`" pattern already used elsewhere in this codebase).
- `core/tiles/TileBase.java` — slimmed `forestry/core/tiles/TileBase.java`. `openGui(Player)` calls `player.openMenu(this)` when `hasGui()` (Fabric's `Player.openMenu(MenuProvider)` replaces CE's `NetworkHooks.openScreen`; no `InteractionHand`/`BlockPos` params needed).
- `core/inventory/InventoryUtil.java` — slim subset of `forestry/core/utils/InventoryUtil.java`: `contiguousSlots`/`NO_SLOTS` (building blocks for a future `WorldlyContainer.getSlotsForFace`), `isEmpty`, `getStacks`, `addStack`/`tryAddStack` (stack-merging helpers, adapted to `ItemStack.isSameItemSameComponents` since 26.2 uses data components instead of NBT tags).
- `core/blocks/IBlockType.java`, `IMachineProperties.java`, `MachineProperties.java` — ported from `forestry/core/blocks/{IBlockType,IMachineProperties,MachineProperties}.java`. Dropped `getShape`/`IShapeProvider` and `getTankLayout` (fluids/shape polish out of scope per stage rules) — ticker plumbing (`getClientTicker`/`getServerTicker`) kept since it's required for `BlockMachine.getTicker` to compile and dispatch.
- `core/blocks/BlockMachine.java` — slimmed `forestry/core/blocks/BlockBase.java`. Kept: block↔tile ticker wiring, `newBlockEntity`, and the interact handler (`useWithoutItem` → `tile.openGui(player)` when `TileUtil.isUsableByPlayer`). Dropped `FACING`/rotation, `FluidUtil` interaction, and the `Container`/`onRemove` drop-contents wiring (no fluids, no inventory yet, no playable machine to test drops with) — left `codec()` abstract, same as local `BlockStructure`, for the concrete subclass to implement.
- `core/gui/ContainerMachine.java` — base container, mirrors `ContainerAlvearyPart`/`ContainerBeeHousing`: `addMachineSlots(T)` abstract hook, `resolveTile` static helper, standard `quickMoveStack`/`stillValid` wiring. No concrete machine container yet — F5 will add e.g. `ContainerCentrifuge extends ContainerMachine<TileCentrifuge>`.
- `factory/blocks/BlockTypeFactoryPlain.java` — **empty enum** (`implements IBlockType` with placeholder-throwing bodies that are never invoked since there are zero constants). Mirrors CE's `BlockTypeFactoryPlain` shape but with all 9 machine entries deferred.
- `factory/blocks/BlockFactoryPlain.java` — `extends BlockMachine<BlockTypeFactoryPlain>`, only adds the `MapCodec` (mirrors CE's `BlockFactoryPlain extends BlockBase<BlockTypeFactoryPlain>`, minus its per-type `VoxelShape`/tank-level blockstate logic, which doesn't apply yet since there are no types).

## Files modified

- `factory/features/FactoryBlocks.java` — added `PLAIN = REGISTRY.blockGroup(BlockFactoryPlain::new, BlockTypeFactoryPlain.values()).item(BlockItem::new).create()`, matching CE's `FactoryBlocks.PLAIN` registration call shape 1:1 (just with a currently-empty type array). Registers zero blocks/items right now — safe no-op until F5 adds enum constants.

## What was slimmed vs CE (and why)

- **No `IStreamable`/packet sync, no `AdjacentTileCache`, no `IFilterSlotDelegate`, no `WorldlyContainer` on `TileForestry`.** CE's `TileForestry` directly implements `WorldlyContainer` by delegating every method to an `IInventoryAdapter`. That's a lot of surface for a stage with no actual inventory yet. Concrete machine tiles (starting with Centrifuge in F5) can implement `Container`/`WorldlyContainer` directly on themselves, the same way `TileBeeHousing` already does in this codebase, using the new `InventoryUtil` helpers for slot math — no adapter class needed.
- **No `IShapeProvider`/`getShape`, no tank layout (`ForestryBlockStateProvider.TankLayout`).** Both are about rendering/fluids polish that only matters once a real machine with a custom hitbox or fluid tank exists. `BlockMachine` defaults to `RenderShape.MODEL` (full block); a real machine block can override `getShape` itself once it needs a custom hitbox.
- **No `FACING`/rotation on `BlockMachine`.** CE's `BlockBase` always adds a rotatable `FACING` property (used for machine I/O sides). Skipped here since sided-slot logic (`InventoryUtil.contiguousSlots`/`NO_SLOTS`) doesn't require it — `WorldlyContainer.getSlotsForFace(Direction)` works off world-space `Direction` regardless of block facing. F5 can add `FACING` directly to `BlockFactoryPlain` (or promote it to `BlockMachine`) once Centrifuge actually needs directional I/O.
- **`openGui` takes `Player`, not `ServerPlayer`+`InteractionHand`+`BlockPos`.** CE needs those because `NetworkHooks.openScreen` is a Forge network hook. Fabric's `Player.openMenu(MenuProvider)` (used the same way by the existing `BlockBeeHousing`) already handles the server-only check and the screen-opening packet, using `ExtendedMenuProvider.getScreenOpeningData` for the payload.
- **No sockets/circuits, no FE, no fluids** — out of scope per stage rules (F3b/F5 own those).

## How F5 will add the first PLAIN machine type (Centrifuge)

F5 adds one enum constant to `BlockTypeFactoryPlain` (`CENTRIFUGE(FactoryTiles.CENTRIFUGE, "centrifuge", TileCentrifuge::serverTick)`) whose body calls `new MachineProperties.Builder<>(FactoryTiles.CENTRIFUGE, "centrifuge").setServerTicker(TileCentrifuge::serverTick).create()`, and adds `FactoryTiles.CENTRIFUGE = REGISTRY.blockEntityType("centrifuge", TileCentrifuge::new, ...)` beforehand so the builder has a `FeatureBlockEntityType` to reference. `FactoryBlocks.PLAIN` needs no changes — `BlockTypeFactoryPlain.values()` will pick up the new constant automatically and the existing `blockGroup(BlockFactoryPlain::new, ...).item(BlockItem::new).create()` call registers the block/item under `reforestry:centrifuge`.

## Verified via MCP (`thedarkcolour-ForestryCE`)

- `forestry/core/tiles/TileForestry.java`, `TileBase.java`
- `forestry/core/blocks/{MachineProperties,IMachineProperties,IBlockType,BlockBase}.java`
- `forestry/factory/blocks/{BlockFactoryPlain,BlockTypeFactoryPlain}.java`
- `forestry/factory/features/FactoryBlocks.java`
- `forestry/core/inventory/{InventoryAdapter,InvSlot,IInventoryAdapter,InventoryAdapterTile}.java`, `forestry/core/utils/InventoryUtil.java`
- `forestry/api/util/TickHelper.java`

Also checked `Minecraft-26.2` (`MenuProvider`, `MenuConstructor`, `AbstractContainerMenu.addStandardInventorySlots`) and `FabricMC-fabric-api` (`ExtendedMenuProvider`, `ExtendedMenuType`) to confirm the Fabric menu-opening pattern already used by `TileBeeHousing`/`TileAlveary`/`BlockBeeHousing` carries over unchanged.

## DoD status

- `./gradlew compileJava` — **BUILD SUCCESSFUL**.
- Zero playable factory machine: `BlockTypeFactoryPlain` has no enum constants, so `FactoryBlocks.PLAIN` registers no blocks/items and `FactoryTiles`/`FactoryMenuTypes` remain the empty F1 shells.

## Blockers for F3b

None. F3b (`TilePowered`/energy) can extend `TileBase` the same way CE's `TilePowered extends TileBase` does, adding the Team Reborn `EnergyStorage` adapter on top of what's here. No FE/energy surface was added in this stage to conflict with it.
