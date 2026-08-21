# Stage F3b — Powered base + creative FE

## Files created

- `core/tiles/IPowerHandler.java` — 1:1 port of `forestry/core/tiles/IPowerHandler.java`. CE's version returns `ForestryEnergyStorage`; ours returns Team Reborn's `SimpleEnergyStorage` since that's the concrete storage type `TilePowered` owns.
- `core/tiles/TilePowered.java` — slimmed `forestry/core/tiles/TilePowered.java`, `extends TileBase implements IPowerHandler`.
  - `SimpleEnergyStorage` (capacity, maxReceive, **maxExtract = 0** — receive-only, matches `EnergyTransferMode.RECEIVE` in CE and the local `TileAlvearyClimatiser` pattern). `onFinalCommit` calls `setChanged()`.
  - `WORK_TICK_INTERVAL = 5`, `hasWork()` (abstract), `workCycle()` (abstract, protected), `doWork()` — 1:1 port of CE's `TilePowered.serverTick` body: interval gate → `DISABLED_BY_REDSTONE` check → `hasWork()` → consume energy for the cycle via `EnergyHelper.consumeEnergyToDoWork` → `NO_POWER` error after 4 consecutive starved ticks → `workCycle()` once `workCounter >= ticksPerWorkCycle`.
  - `getProgressScaled(pixels)`, NBT round-trip via `loadAdditional`/`saveAdditional` with key `"Energy"` (matches CE's `ForestryEnergyStorage.write`/`read` key), same as `TileAlvearyClimatiser`.
  - **Not ported (deferred):** `IMachineUpgradable`/`speedMultiplier`/`powerMultiplier`/`outputMultiplier` (F15 sockets), `getEnergyPerWorkCycle` difficulty scaling (`EnergyHelper.scaleForDifficulty`/`Preference.ENERGY_DEMAND_MODIFIER` — no difficulty config in this port yet, FE is 1:1 with CE's RF numbers as specified), `IRenderableTile`/tank render info (no fluids in Factory yet), GUI data streaming (no concrete machine GUI to sync to yet — F5 Centrifuge's container can read `getEnergyManager()`/`getProgressScaled()` directly once it exists, the same way `ContainerBeeHousing` reads `TileBeeHousing` fields today).
  - **Ticking pattern:** unlike CE (which overrides `serverTick(Level, BlockPos, BlockState)` directly on the tile because Forge tiles are self-ticking), this codebase dispatches ticking through `BlockEntityTicker` wiring (see F3a — `MachineProperties`/`BlockMachine.getTicker`, or a block's own `getTicker` override). So `TilePowered` exposes `doWork()` as a plain instance method; concrete subclasses provide their own `static void serverTick(Level, BlockPos, BlockState, T tile)` that just calls `tile.doWork()` (see `TileDebugPowered.serverTick`, and how `TileBeeHousing.serverTick` already does the same for its own work loop). F5's `TileCentrifuge` will do the same and wire it via `MachineProperties.Builder.setServerTicker(TileCentrifuge::serverTick)`.
- `core/energy/EnergyHelper.java` — new package (`core/energy`), port of `forestry/energy/EnergyHelper.java` minus the send/engine-chaining half:
  - `consumeEnergyToDoWork(EnergyStorage, ticksPerWorkCycle, energyPerWorkCycle)` — CE drains a `ForestryEnergyStorage` field directly (`energyStorage.drainEnergy(...)`, an unconditional mutation once the balance check passes). Team Reborn storages don't expose a raw "subtract now" method by design — mutation only happens inside `insert`/`extract`, which must run inside a `Transaction`. So this opens `Transaction.openOuter()`, calls `energyStorage.extract(energyPerCycle, transaction)`, and only `transaction.commit()`s if the full amount was available; otherwise the `try`-with-resources closes without committing and the extraction is rolled back automatically (no manual "give it back" bookkeeping needed). Same energy-per-cycle math as CE: `ceil(energyPerWorkCycle / ticksPerWorkCycle)`.
  - `registerSided(BlockEntityType<T>)` — small helper, `T extends BlockEntity & IPowerHandler`, wraps `EnergyStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getEnergyManager(), type)`. Same one-line-per-type shape `ApicultureTiles.init()` already uses for the Alveary FE tiles. F5's `FactoryTiles.init()` can call `EnergyHelper.registerSided(FactoryTiles.CENTRIFUGE.type())` once `TileCentrifuge` exists — no new plumbing needed.
- **Creative FE test fixture** — `core/energy/TileCreativeEnergy.java` + `core/energy/BlockCreativeEnergy.java`, registry id **`reforestry:debug_creative_energy`**.
  - Energy-wise it's just Team Reborn's built-in `InfiniteEnergyStorage.INSTANCE` (extract always succeeds, insert always rejected — "creative battery", per its own javadoc).
  - Since Team Reborn's transfer system is **push-based** (`EnergyStorage.SIDED` javadoc: "power sources are responsible for pushing power to nearby machines... machines should NOT pull"), the block needs its own ticker: `TileCreativeEnergy.serverTick` loops the 6 `Direction`s, looks up `EnergyStorage.SIDED.find(level, pos.relative(dir), dir.getOpposite())` on each neighbor, and calls `EnergyStorageUtil.move(InfiniteEnergyStorage.INSTANCE, target, 1000, null)` — pushes up to 1000 FE/tick into whatever's next to it, capped by the neighbor's own `maxInsert`.
  - Also registered on `EnergyStorage.SIDED` itself (so it looks like a normal energy-having block to any inspection tooling), even though nothing needs to pull from it for this stage.
- **Smoke-test powered tile (Option A, temporary)** — `core/energy/TileDebugPowered.java extends TilePowered` + `core/energy/BlockDebugPowered.java`, registry id **`reforestry:debug_powered`**.
  - `CAPACITY = 10,000` FE, `MAX_RECEIVE = 200` FE/tick, `ENERGY_PER_WORK_CYCLE = 100` (over the default `ticksPerWorkCycle = 4`, i.e. ~25 FE consumed per 5-game-tick work step while powered).
  - `hasWork()` always `true`, `workCycle()` always `true` — it just perpetually cycles and drains its buffer, so you can watch FE go up (creative source charging it) and down (its own fake "work") in the same fixture.
  - `hasGui()` → `false`, `createMenu` → `null` (no screen — `TileForestry`/`ExtendedMenuProvider` requires an implementation, but nothing opens it). Right-clicking it instead prints `"Energy: <amount> / <capacity>"` via `player.sendSystemMessage` — the quickest way to read the buffer without a GUI or `/data get block`.
  - Registered on `EnergyStorage.SIDED` via `EnergyHelper.registerSided(CoreTiles.DEBUG_POWERED.type())` in `CoreTiles.init()` — this is exactly the call pattern F5's `FactoryTiles.init()` will reuse for `TileCentrifuge`.

## Files modified

- `core/tiles/TileForestry.java` — added `protected boolean isRedstoneActivated()` (`level.getBestNeighborSignal(getBlockPos()) > 0`, null-safe on `getLevel()`), 1:1 port of the same method already in CE's `TileForestry`. Needed by `TilePowered.doWork()`; kept on the shared base since energy tiles (engines, F5 machines) will all want it, same as CE.
- `core/features/CoreBlocks.java` — added `CREATIVE_ENERGY` (`debug_creative_energy`) and `DEBUG_POWERED` (`debug_powered`) block registrations (both with `BlockItem::new`, no group/`IBlockType` machinery — these aren't "machines" in the Factory sense, just standalone debug blocks).
- `core/features/CoreTiles.java` — **new file**. Registers `CREATIVE_ENERGY` and `DEBUG_POWERED` block entity types, `init()` wires `EnergyHelper.registerSided(DEBUG_POWERED.type())` (the creative source doesn't need it registered here since it registers itself directly in the same call style — see above).
- `core/ModuleCore.java` — calls `CoreTiles.init()` between `CoreBlocks.init()` and `CoreCreativeTabs.init()`.
- `assets/reforestry/{blockstates,models/block,models/item}/debug_creative_energy.json`, same for `debug_powered`, plus two `block.reforestry.*` lang keys. Both models are plain `cube_all` referencing a `reforestry:block/debug_*` texture that doesn't exist yet — same as several existing core blocks (`tin_ore`, `ash_brick`, etc. have no `.png` yet either), so this only matters for the missing-texture placeholder in-world, not for functionality.

## What was slimmed vs CE (and why)

- **No `IMachineUpgradable`/circuit multipliers.** Out of scope per stage rules (F15 sockets). `speedMultiplier`/`powerMultiplier`/`outputMultiplier` fields don't exist; `TilePowered` always runs at the base rate.
- **No engine chaining / `sendEnergy`.** CE's `EnergyHelper.sendEnergy` + `EngineBlockEntity` special-casing is explicitly out of scope — FE is receive-only (`maxExtract = 0` on every `SimpleEnergyStorage`), matching the local `TileAlvearyClimatiser` precedent. No "Engine" tiles exist in this port at all yet.
- **No difficulty scaling (`EnergyHelper.scaleForDifficulty`).** FE numbers are 1:1 with CE's RF numbers per stage rule 2 — no `Preference.ENERGY_DEMAND_MODIFIER` equivalent exists (or is planned) in this port.
- **No GUI energy sync (`IStreamableGui`/`writeGuiData`/`readGuiData`).** There's no concrete machine screen yet to sync to (F5 adds the first one, Centrifuge). When it lands, its `ContainerCentrifuge` can just read `TileCentrifuge.getEnergyManager()`/`getProgressScaled()` server-side the same way `ContainerBeeHousing` already reads `TileBeeHousing` fields, no packet plumbing needed given this port's `ExtendedMenuProvider` opens screens without Forge's Capability/NetworkHooks sync path.
- **No fluids/tank render info (`IRenderableTile`).** Factory fluids are out of scope for this stage (per rules, F5-adjacent territory at most).

## Energy model summary

- Unit: FE, numerically 1:1 with CE's RF (per stage rule).
- Storage: Team Reborn `SimpleEnergyStorage(capacity, maxInsert, maxExtract=0)` — insertion-only from the tile's perspective (world pushes power *into* it; it never pushes power back out through the same API).
- Consumption: `EnergyHelper.consumeEnergyToDoWork` — transactional `extract`, only committed if the tile could afford the full per-cycle cost; otherwise it's a no-op (rolled back) and `TilePowered` counts it as a "starved" tick (`NO_POWER` error after 4 in a row, matching CE).
- NBT key: `"Energy"` (`long`), read/written via `ValueInput.getLongOr`/`ValueOutput.putLong` — matches CE's `ForestryEnergyStorage` NBT key so future save-compat/porting stays consistent.
- Sided exposure: every powered tile type is registered once via `EnergyHelper.registerSided(type)` → `EnergyStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getEnergyManager(), type)`, same shape as `ApicultureTiles.init()`'s two `EnergyStorage.SIDED.registerForBlockEntity` calls for the Alveary fan/heater.

## How to smoke-test (creative FE → powered buffer)

1. `./gradlew runServer` (or `runClient` singleplayer).
2. Place `reforestry:debug_powered` at some position.
3. Place `reforestry:debug_creative_energy` directly adjacent to it (any face — the source pushes to all 6 neighbors every tick).
4. Wait a few seconds, then right-click `debug_powered` — it prints `Energy: <amount> / 10000` to chat. The amount should be > 0 and changing over time (rising while the creative source outpaces the tile's own fake work-cycle drain, since push rate 1000/t ≫ receive cap 200/t ≫ ~25 FE every 5 ticks of self-drain).
5. Alternative non-visual check: `/data get block <x> <y> <z> Energy` on the `debug_powered` position returns the raw NBT long.
6. Removing the creative source and watching the buffer drop (via repeated right-clicks) confirms drain-without-source still works (starves after the buffer empties, `NO_POWER` error sets internally — not surfaced in chat in this stage since there's no error-icon GUI yet, but the field is populated in `getErrorLogic()` and F5's Centrifuge GUI can read it later, same as `TileBeeHousing`'s error icons).

## Verified via MCP (`thedarkcolour-ForestryCE`)

- `forestry/core/tiles/TilePowered.java`, `IPowerHandler.java`, `TileBase.java`
- `forestry/energy/EnergyHelper.java`, `ForestryEnergyStorage.java`
- Confirmed `isRedstoneActivated()` lives on CE's `TileForestry`, not `TileBase`.

## Verified via Team Reborn `energy` 5.0.0 / Fabric Transfer API sources (local Gradle cache, decompiled — no MCP repo for this)

- `team.reborn.energy.api.EnergyStorage` (`SIDED` lookup is push-based per its own javadoc), `EnergyStorageUtil.move`, `base.SimpleEnergyStorage`, `base.InfiniteEnergyStorage`.
- `net.fabricmc.fabric.api.transfer.v1.transaction.Transaction.openOuter()`/`TransactionContext`.
- `net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup.find(Level, BlockPos, C)`.

## DoD status

- `./gradlew compileJava` — **BUILD SUCCESSFUL**.
- Creative FE fixture + smoke-test powered tile implemented and registered; steps above describe exactly how to verify charging in-game.
- **Not run live in this session** — a dedicated server was already active under RCON control from a concurrent task at the time this stage was implemented, so it wasn't restarted/rebuilt here to avoid disrupting that in-progress work. Whoever picks this up next (or the parent session) should restart `./gradlew runServer` and follow the smoke-test steps above to close out DoD #2 with an actual in-game observation.

## Blockers for F5

None functionally. F5's `TileCentrifuge` can `extends TilePowered` directly:
- Constructor: `super(FactoryTiles.CENTRIFUGE.type(), pos, state, capacity, maxReceive)`, then `setEnergyPerWorkCycle(...)`.
- `hasWork()`/`workCycle()` implement the actual centrifuge recipe logic (consume input, produce output) instead of `TileDebugPowered`'s always-true stubs.
- `FactoryTiles.init()` adds one line: `EnergyHelper.registerSided(FactoryTiles.CENTRIFUGE.type())`.
- A `ContainerCentrifuge`/screen can read `getEnergyManager()` and `getProgressScaled(pixels)` directly for the energy bar and progress arrow.
- `TileDebugPowered`/`BlockDebugPowered`/`BlockCreativeEnergy` are explicitly temporary debug content (not referenced from any recipe/tag/creative-tab beyond registration) and can be deleted once F5 gives Factory a real playable machine to smoke-test against, or kept around as a permanent FE test fixture — developer's call, noted here so it isn't mistaken for a real Forestry machine.
