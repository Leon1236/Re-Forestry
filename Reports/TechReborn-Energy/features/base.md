# TechReborn-Energy — base

| Field | Value |
|---|---|
| Repo / alias | TechReborn-Energy / `Energy` |
| Module slug | `base` |
| Package | `team.reborn.energy.api.base` |
| Path | `src/main/java/team/reborn/energy/api/base/` |
| Clone | `MarkDown_Maker/Finished_github_clone/2026-07-24/TechReborn-Energy` |
| Library version (clone) | `5.0.0` (MC `26.1-snapshot-1`) |
| Re-Forestry pin | `energy_version=5.0.0` in `gradle.properties` |
| Size | **S** — 6 Java files, no resources |

## Summary

`base` is the **ready-made implementations** package for Team Reborn Energy. It does not define the `EnergyStorage` contract or Fabric lookups; it supplies concrete helpers that implement that contract for the common cases: fixed block storage, per-side I/O, rate-limiting wrappers, infinite extract sources, item batteries, and validity-gated delegation.

Re-Forestry already depends on this library and uses **two** of the six types heavily (`SimpleEnergyStorage`, `InfiniteEnergyStorage`). The other four are unused so far but remain the recommended paths for sided machines, chargeable items, and access-gated wrappers.

## Player / API surface

This package is **API for mod authors**, not player-facing content. There are no blocks, items, GUIs, commands, configs, or lang keys here.

| Type | Kind | What a consuming mod gets |
|---|---|---|
| `SimpleEnergyStorage` | class | Fixed-capacity block/entity buffer with per-op insert/extract caps; transactional via Fabric `SnapshotParticipant` |
| `SimpleSidedEnergyContainer` | abstract class | One shared `amount`, dynamic capacity, per-`Direction` (plus null) I/O limits via `getSideStorage` |
| `LimitingEnergyStorage` | class | Wrapper that clamps another storage’s insert/extract per operation |
| `DelegatingEnergyStorage` | class | Wrapper that forwards to a fixed or supplier-backed storage, optionally gated by `BooleanSupplier` |
| `InfiniteEnergyStorage` | class + `INSTANCE` | Extract-any-amount, never-accept creative source |
| `SimpleEnergyItem` | interface | Opt-in item battery: energy in `EnergyStorage.ENERGY_COMPONENT`; auto `EnergyStorage.ITEM` fallback when implemented |

Documented usage expectations (from library README + class javadoc, not in this package’s code):

- Override `SnapshotParticipant.onFinalCommit` on `SimpleEnergyStorage` / `SimpleSidedEnergyContainer` to `setChanged()` / mark dirty.
- Persist `SimpleEnergyStorage.amount` yourself in block-entity NBT / `ValueInput`/`ValueOutput`.
- Register storages on `EnergyStorage.SIDED` (block entities) yourself — `base` does not register anything.
- Implementing `SimpleEnergyItem` on an `Item` is enough for item lookup; registration is done in `impl` (`EnergyImpl` fallback).

## Architecture

```
EnergyStorage (api)  <── implements ──  SimpleEnergyStorage
                                      SimpleSidedEnergyContainer.SideStorage
                                      LimitingEnergyStorage
                                      DelegatingEnergyStorage
                                      InfiniteEnergyStorage
                                      (item path via createStorage → impl)

SimpleEnergyItem (base) ──createStorage──► SimpleItemEnergyStorageImpl (impl)
                                         └── wraps with DelegatingEnergyStorage (validity gate)

EnergyImpl (impl) ──ITEM.registerFallback──► if item instanceof SimpleEnergyItem → createStorage(...)
```

### Role of each class

1. **`SimpleEnergyStorage`** — primary block buffer. Public mutable `amount`; final `capacity`, `maxInsert`, `maxExtract`. Extends `SnapshotParticipant<Long>` so insert/extract call `updateSnapshots(transaction)` before mutating. `supportsInsertion` / `supportsExtraction` are true only when the corresponding max > 0.

2. **`SimpleSidedEnergyContainer`** — same transactional `amount`, but capacity and max I/O are abstract and side-aware. Eagerly builds 7 `SideStorage` views (6 directions + index 6 for `null` side). Outer class is **not** an `EnergyStorage`; callers expose `getSideStorage(side)` to `EnergyStorage.SIDED`.

3. **`LimitingEnergyStorage`** — thin decorator: `insert`/`extract` pass `Math.min(requested, max*)` to the backing storage; support flags AND with `max* > 0`.

4. **`DelegatingEnergyStorage`** — decorator for “still the same stack / still valid context”. Two constructors: fixed backing, or `Supplier<EnergyStorage>` for a changing target. If `validPredicate` is false, all ops return 0 / capacity 0 / unsupported false. Used internally by item storage creation (see depends-on).

5. **`InfiniteEnergyStorage`** — singleton creative source. `insert` always 0; `extract` returns the full `maxAmount`; amount/capacity `Long.MAX_VALUE`. Does not participate in snapshots (stateless).

6. **`SimpleEnergyItem`** — interface on items. Declares capacity / max in / max out from `ItemStack`. Defaults read/write `EnergyStorage.ENERGY_COMPONENT`. `tryUseEnergy` requires stack count == 1. Static `createStorage` delegates to internal `SimpleItemEnergyStorageImpl`. Zero-energy clears the component so empty batteries stack with freshly crafted ones.

### Transaction model (contracts shared across storages)

- Mutating APIs take `TransactionContext` (Fabric Transfer).
- `SimpleEnergyStorage` / sided `SideStorage` snapshot `amount` as `Long`.
- Wrappers (`Limiting`, `Delegating`) do not snapshot themselves; they forward into the backing storage’s transaction participation.
- Direct mutation of public `amount` (as README and Re-Forestry `EnergyHelper` do) **bypasses** transactions — intentional for internal machine drain when the mod owns the buffer.

## Data & assets

| Kind | In `base`? | Notes |
|---|---|---|
| Block/item/models/textures/lang | **None** | Pure Java API |
| Data components | **Uses, does not register** | `SimpleEnergyItem` reads/writes `EnergyStorage.ENERGY_COMPONENT` (`team_reborn_energy:energy`, registered in `EnergyImpl`) |
| Recipes / tags / datapack | **None** | |
| NBT keys | **None prescribed** | Mods persist `SimpleEnergyStorage.amount` under their own keys |

Unused imports in clone `SimpleEnergyItem.java` (`DataComponentPatch`, `Optional`) — cosmetic only; no runtime impact.

## Dependencies

### Depends on (brief — outside this module)

| Dependency | Why |
|---|---|
| `team.reborn.energy.api.EnergyStorage` | Interface + `ENERGY_COMPONENT` / `ITEM` docs |
| `team.reborn.energy.impl.SimpleItemEnergyStorageImpl` | Only referenced from `SimpleEnergyItem.createStorage` (internal) |
| Fabric Transfer `TransactionContext`, `SnapshotParticipant`, `StoragePreconditions` | Transactional storage |
| Fabric Transfer `ContainerItemContext`, `ItemVariant` | Item energy path |
| Vanilla `Direction`, `ItemStack`, `DataComponentMap` | Sided views + item components |

### Depended on by (within Energy, brief)

| Consumer | Usage |
|---|---|
| `api.EnergyStorage` javadoc | Points authors at `SimpleEnergyStorage`, `SimpleSidedEnergyContainer`, `SimpleEnergyItem`, `DelegatingEnergyStorage` |
| `impl.EnergyImpl` | `ITEM` fallback for `SimpleEnergyItem` |
| `impl.SimpleItemEnergyStorageImpl` | Uses `DelegatingEnergyStorage` + `SimpleEnergyItem` static energy helpers |
| Tests (`EnergyTests`, `TestBatteryItem`) | Cover `SimpleEnergyStorage` + `SimpleEnergyItem` |

No Gradle/Maven module split inside the jar — `base` is a package only.

## Notable algorithms / contracts

### Insert / extract math (`SimpleEnergyStorage`)

```
inserted  = min(maxInsert, min(maxAmount, capacity - amount))
extracted = min(maxExtract, min(maxAmount, amount))
```

Same shape for `SideStorage`, substituting `getMaxInsert(side)` / `getMaxExtract(side)` / `getCapacity()`.

### Item stack energy clearing

`setStoredEnergyUnchecked`: if `newAmount <= 0`, **remove** the component; otherwise `set`. Ensures empty and never-charged stacks merge.

### Stackable item energy (via `createStorage` → impl)

Energy is stored **per item** in the component; total amount/capacity scale by stack count. Insert/extract distribute evenly; partial fills that cannot assign the same integer amount to every item in the stack fail as a whole (impl detail, not in `base` itself).

### Validity gate (`DelegatingEnergyStorage`)

All public methods check `validPredicate` first. Item storages use this to freeze ops when the slot’s item identity or count becomes invalid.

### Infinite extract

`InfiniteEnergyStorage.extract` returns `maxAmount` unconditionally — callers must still pass a real transaction if composing with other storages (`EnergyStorageUtil.move` does).

### Open TODO in source

`SimpleEnergyItem` javadoc TODO: tooltip + recipe input→output energy transfer “like RC” — not implemented in this package.

## Port relevance to Re-Forestry

Re-Forestry already includes Team Reborn Energy (`energy_version=5.0.0`) as a declared stack dependency — **do not copy these classes into `com.leon1236.reforestry`**. Adopt by **using** the API.

### Already in use

| Energy `base` type | Re-Forestry call sites |
|---|---|
| `SimpleEnergyStorage` | `TilePowered` (factory/core powered tiles), `TileAlvearyClimatiser`, `IPowerHandler`, `EnergyHelper.consumeEnergyToDoWork` |
| `InfiniteEnergyStorage.INSTANCE` | `TileCreativeEnergy` (pushes via `EnergyStorageUtil.move`) |

Patterns already matching library README:

- Anonymous subclass overriding `onFinalCommit` → `setChanged()`.
- Machines often set `maxExtract = 0` (receive-only buffers); creative tile pushes outward.
- Internal work drain mutates `.amount` directly (`EnergyHelper`).

### Available but unused (likely useful later)

| Type | When to reach for it |
|---|---|
| `SimpleSidedEnergyContainer` | Engine / alveary / factory faces with different insert vs extract sides without custom wrappers |
| `LimitingEnergyStorage` | Cap external transfer rate while keeping a larger internal buffer |
| `DelegatingEnergyStorage` | Item contexts, or “storage valid only while multiblock formed” |
| `SimpleEnergyItem` | Chargeable tools, portable batteries, electrical backpacks — auto item API + component storage |

### Overlap / caution

`AccessStorageHelper.RestrictedEnergy` reimplements insert/extract gating similar to a combination of `Delegating`/`Limiting` + support flags, specialized for Forestry `AccessMode`. Not wrong, but future sided energy work should prefer `LimitingEnergyStorage` / `SimpleSidedEnergyContainer` before inventing more wrappers.

`files/implemented-features.md` does **not** currently document Energy/`base` usage (no energy hits in that tracker).

## Source map

All paths relative to clone root `…/TechReborn-Energy/`:

| File | Lines (approx) | Responsibility |
|---|---|---|
| `src/main/java/team/reborn/energy/api/base/SimpleEnergyStorage.java` | ~87 | Fixed capacity transactional buffer |
| `src/main/java/team/reborn/energy/api/base/SimpleSidedEnergyContainer.java` | ~115 | Abstract sided container + private `SideStorage` |
| `src/main/java/team/reborn/energy/api/base/LimitingEnergyStorage.java` | ~61 | Per-op rate limit wrapper |
| `src/main/java/team/reborn/energy/api/base/DelegatingEnergyStorage.java` | ~90 | Validity / supplier delegation wrapper |
| `src/main/java/team/reborn/energy/api/base/InfiniteEnergyStorage.java` | ~38 | Creative infinite extract singleton |
| `src/main/java/team/reborn/energy/api/base/SimpleEnergyItem.java` | ~116 | Item battery interface + component helpers + `createStorage` |

Supporting evidence (not in module, for wiring only):

| File | Relation |
|---|---|
| `src/main/java/team/reborn/energy/api/EnergyStorage.java` | Contract + lookups documenting `base` types |
| `src/main/java/team/reborn/energy/impl/EnergyImpl.java` | Registers component + `SimpleEnergyItem` ITEM fallback |
| `src/main/java/team/reborn/energy/impl/SimpleItemEnergyStorageImpl.java` | Item storage behind `createStorage` |
| `src/test/java/team/reborn/energy/test/EnergyTests.java` | Tests for `SimpleEnergyStorage` + item path |
| `src/test/java/team/reborn/energy/test/TestBatteryItem.java` | Sample `SimpleEnergyItem` |
| `README.md` | Author examples for `SimpleEnergyStorage` / sided / items |

## Open questions / gaps

1. **Clone vs game target:** Energy clone is built against MC **26.1-snapshot-1**; Re-Forestry targets **26.2**. Confirm published `teamreborn:energy:5.0.0` (or newer 5.x) is the Loom-resolved artifact and API-compatible — package contents above match the clone; verify jar if Loom pulls a different build.
2. **No tests** in Energy for `LimitingEnergyStorage`, `SimpleSidedEnergyContainer`, or `InfiniteEnergyStorage` — behavior inferred from source only.
3. **Re-Forestry persistence:** confirm every `SimpleEnergyStorage` owner saves/loads `.amount` (outside this module’s responsibility, but easy to miss when adding new powered tiles).
4. **Chargeable items:** Forestry CE had electrical engines / batteries conceptually; if Re-Forestry ports portable energy items, prefer `SimpleEnergyItem` over a custom component id (interop + auto lookup).
5. **Sided alveary energy:** fan/heater register `EnergyStorage.SIDED` with a single storage today; if face-specific limits are needed, evaluate `SimpleSidedEnergyContainer` vs continuing with `SimpleEnergyStorage` + access wrappers.
6. **Open upstream TODO** on tooltips / crafting energy transfer for `SimpleEnergyItem` — if Re-Forestry needs that UX, it must implement locally (JEI/tooltip), not wait on Energy.
