# TechReborn-Energy — api

## Summary

The `api` module is the **public contract** of Team Reborn Energy: a single storage interface plus one transfer helper. It is not a content mod — no blocks, items, worldgen, or recipes. Downstream mods (including Re-Forestry) implement or look up `EnergyStorage`, register block/item providers on its Fabric API Lookups, and move energy with `EnergyStorageUtil.move`.

Per inventory (`Reports/TechReborn-Energy/00-INVENTORY.md`), this module is **only** the top-level package `team.reborn.energy.api` (two files, ~200 LOC). Ready-made storages live in sibling module **base** (`team.reborn.energy.api.base`); registration of the energy data component and item fallback lives in **impl** (`team.reborn.energy.impl`).

| File | Role |
|---|---|
| `src/main/java/team/reborn/energy/api/EnergyStorage.java` | Core interface: lookups, empty sentinel, energy component handle, insert/extract/query |
| `src/main/java/team/reborn/energy/api/EnergyStorageUtil.java` | Static helpers: transactional `move`, item-stack “is energy storage?” check |

Clone pins Energy **5.0.0** for Minecraft **≥26.1** (Fabric transfer API). Re-Forestry already depends on / jars-in `teamreborn:energy:5.0.0` (`gradle.properties` `energy_version`, `build.gradle` `implementation` + `include`).

## Player / API surface

There is **no player-facing UI** in this package. Surface is entirely for mod authors:

### `EnergyStorage` interface

**Lookups (static fields)**

| Field | Type | Lookup id | Context |
|---|---|---|---|
| `SIDED` | `BlockApiLookup<EnergyStorage, @Nullable Direction>` | `teamreborn:sided_energy` | Block / BE energy; `Direction` may be `null` = full storage ignoring side limits |
| `ITEM` | `ItemApiLookup<EnergyStorage, ContainerItemContext>` | `teamreborn:energy` | Item energy; query via `ContainerItemContext#find` |
| `EMPTY` | `EnergyStorage` | — | Always-empty sentinel (backed by `impl.EmptyEnergyStorage`) |
| `ENERGY_COMPONENT` | `DataComponentType<Long>` | registered as `team_reborn_energy:energy` in `EnergyImpl` | Stock item-stack energy component |

**Transfer / query methods**

| Method | Contract |
|---|---|
| `supportsInsertion()` | Default `true`. Return `false` only if insert will **always** return 0. Cables use this to decide whether to connect. |
| `insert(long maxAmount, TransactionContext)` | Non-negative `maxAmount`; returns amount actually inserted (≤ max). Part of a Fabric transfer transaction. |
| `supportsExtraction()` | Default `true`. Same “never extract” hint as above for extract. |
| `extract(long maxAmount, TransactionContext)` | Non-negative `maxAmount`; returns amount actually extracted. |
| `getAmount()` / `getCapacity()` | Current stored energy and max capacity. Docs warn: amount may not be extractable; free capacity may not accept insert — **simulate** with insert/extract if unsure. |

### `EnergyStorageUtil`

| Method | Contract |
|---|---|
| `move(from, to, maxAmount, transaction)` | Move energy `from` → `to`. Null either side → 0. Opens nested transactions; simulates extract, inserts that amount, extracts for real, commits only if amounts match. Passing `transaction == null` opens a fresh nested root for this move. |
| `isEnergyStorage(ItemStack)` | `true` if `ContainerItemContext.withConstant(stack).find(EnergyStorage.ITEM) != null`. For slots that only accept chargeable items. |

### Conventions (from README, documented on `SIDED`)

- **Push-based:** power sources push to neighbors; machines/wires must **not** pull.
- Reference magnitudes (interop guide, not enforced in code): 1 coal = 4000, 1 plank = 750.
- When `supportsInsertion` / `supportsExtraction` change, the storage should notify neighbors (block update) so cables can refresh.
- `SIDED` is safe on server and client threads; on client, contents are unreliable and must not be modified.
- `ITEM` APIs should behave the same on both logical sides.
- `ENERGY_COMPONENT` should only be used on **your own** mod’s item stacks; inter-mod item energy goes through `ITEM`.

## Architecture

```
[Mod BE / Item]
      │ registerForBlockEntity / ITEM provider / implement EnergyStorage
      ▼
EnergyStorage.SIDED  /  EnergyStorage.ITEM     ← Fabric API Lookup
      │ find(...)
      ▼
EnergyStorage (this module)
      │ insert / extract (TransactionContext)
      ▼
Fabric transfer Transaction API (fabric-transfer-api-v1)
```

**Depends on (brief):**

- **Fabric API Lookup** (`BlockApiLookup`, `ItemApiLookup`) — discovery of storages on blocks/items.
- **Fabric transfer** — `Transaction` / `TransactionContext`, `ContainerItemContext`, `StoragePreconditions` (util only).
- **impl** — `EMPTY` and `ENERGY_COMPONENT` are wired to `EmptyEnergyStorage` / `EnergyImpl` at class-init (`Objects.requireNonNull`). Entrypoint `EnergyImpl::init` registers the component and the `SimpleEnergyItem` ITEM fallback (not in this package).
- **base** (referenced in javadoc / imports for discoverability only) — `SimpleEnergyStorage`, `SimpleSidedEnergyContainer`, `SimpleEnergyItem`, `DelegatingEnergyStorage` are suggested implementations; they are a separate inventory module.

**Used by (brief):**

- **base** — all ready-made classes implement or produce `EnergyStorage`.
- **impl** — empty storage + item storage impl + init.
- **tests** — `EnergyTests` exercises EMPTY / move / item paths.
- **Re-Forestry** — `EnergyStorage.SIDED.registerForBlockEntity`, custom `EnergyStorage` wrappers (`AccessStorageHelper.RestrictedEnergy`), `EnergyStorageUtil.move` (creative energy tile), plus **base** types (`SimpleEnergyStorage`, `InfiniteEnergyStorage`) for machine buffers.

Design notes from the interface itself:

- Energy is a **single resource** (long units), not a typed fluid/item variant — unlike Fabric `Storage<T>`.
- Side context is on the **lookup**, not on every method: each returned `EnergyStorage` is already the view for that side (or full storage when direction is null).
- Transactional integrity is mandatory for insert/extract; internal “cheat” mutations of `SimpleEnergyStorage.amount` (common in machine ticks) are outside this interface’s contract and are a base-module concern.

## Data & assets

This package defines **no** JSON assets, lang files, models, or loot tables.

Indirect data contracts exposed as API fields:

| Id / handle | Where defined | Notes |
|---|---|---|
| Lookup `teamreborn:sided_energy` | `EnergyStorage.SIDED` | Namespace `teamreborn` (not `team_reborn_energy`) |
| Lookup `teamreborn:energy` | `EnergyStorage.ITEM` | Same |
| Component `team_reborn_energy:energy` | Registered in `impl/EnergyImpl.java`; exposed as `EnergyStorage.ENERGY_COMPONENT` | Persistent non-negative `Long`; network `VAR_LONG` |

Mod metadata lives outside this package (`src/main/resources/fabric.mod.json`: id `team_reborn_energy`, depends `fabric-transfer-api-v1` ≥5.1.0, main entrypoint `EnergyImpl::init`). Only asset is the mod icon under `assets/team_reborn_energy/`.

## Dependencies

**Compile / runtime (library):**

| Dependency | Role for `api` |
|---|---|
| Minecraft (≥26.1 in clone) | `Direction`, `Identifier`, `ItemStack`, `DataComponentType` |
| `fabric-api-lookup-v1` (via Fabric API) | `BlockApiLookup`, `ItemApiLookup` |
| `fabric-transfer-api-v1` ≥5.1.0 | Transactions, `ContainerItemContext`, preconditions |
| JetBrains annotations | `@Nullable` |

**Intra-repo:**

| Package | Relationship |
|---|---|
| `team.reborn.energy.impl` | Provides `EMPTY` + `ENERGY_COMPONENT` implementations referenced by static fields |
| `team.reborn.energy.api.base` | Optional implementations; javadoc/import references only from `EnergyStorage` |

**Not dependencies of this package:** other content mods, energy cables, TechReborn machines — those *consume* the API.

## Notable algorithms / contracts

### Push-based transfer model

Documented on `SIDED`: producers call `EnergyStorage.SIDED.find` on neighbors and push with `insert` / `EnergyStorageUtil.move`. Consumers do not poll neighbors for pull. Changing support flags requires a block update so networks re-probe.

### Transactional insert / extract

All mutations go through `TransactionContext`. Callers who need “all or nothing” open an outer transaction, extract, and `commit()` only on success (README pattern). Abort (no commit) rolls back via Fabric’s snapshot participants (used by base storages).

### `EnergyStorageUtil.move` protocol

1. Reject null `from`/`to` or negative `maxAmount`.
2. Nested transaction: simulate `from.extract(maxAmount)` → `maxExtracted` (aborted automatically on close).
3. Nested transaction: `to.insert(maxExtracted)` → `accepted`; then `from.extract(accepted)`; if extracted amount equals `accepted`, `commit` and return `accepted`; else abort and return 0.

This avoids committing a partial move when the second extract disagrees with the simulated/accepted amount (important for storages whose extract is non-deterministic relative to the simulation).

### Support flags vs capacity

`supportsInsertion` / `supportsExtraction` are **connection hints**, not free-space checks. Free space and extractable amount must be discovered by simulating transfer. Defaults are optimistic (`true`).

### Item energy ownership

- Own items may store energy in `ENERGY_COMPONENT`.
- Other mods’ items: only via `ITEM` lookup / `isEnergyStorage`.
- Item providers should treat `ContainerItemContext` as a single slot and re-check variant/amount each op (docs point at `SimpleItemEnergyStorageImpl` + `DelegatingEnergyStorage` in base/impl).

### Null direction on `SIDED`

`Direction == null` means “internal / full” storage without side I/O limits — used for machine-internal access or omnidirectional views.

## Port relevance to Re-Forestry

Re-Forestry treats this library as a **declared stack dependency** (not code to reimplement). Current usage already maps cleanly onto this `api` surface:

| Re-Forestry use | API symbol |
|---|---|
| Register machine / alveary energy BEs | `EnergyStorage.SIDED.registerForBlockEntity(...)` — e.g. `EnergyHelper.registerSided`, `ApicultureTiles` fan/heater |
| Machine buffers | `base.SimpleEnergyStorage` (sibling module) implementing this interface |
| Side access gating | Custom `EnergyStorage` in `AccessStorageHelper.RestrictedEnergy` (implements this interface; similar spirit to `LimitingEnergyStorage` / support flags) |
| Creative energy push | `EnergyStorage.SIDED.find` + `EnergyStorageUtil.move` + `InfiniteEnergyStorage.INSTANCE` in `TileCreativeEnergy` |
| Work-cycle consume | Mutates `SimpleEnergyStorage.amount` directly in `EnergyHelper.consumeEnergyToDoWork` (outside transactional API — intentional tick shortcut) |

`files/implemented-features.md` does **not** currently call out Team Reborn Energy as a tracked feature line (no hits for EnergyStorage / teamreborn energy); energy is infrastructure already in tree via Gradle jar-in.

**Port guidance (stay on this API):**

- Keep using `include modApi` / `implementation` + `include` of `teamreborn:energy` — do not vendor or fork the interface.
- New powered tiles: register `SIDED`, prefer push from generators, wrap with `ISidedAccess` where Forestry-style side modes apply.
- Chargeable tools/items (if added later): prefer `SimpleEnergyItem` / `ITEM`, not inventing a parallel component id.
- Align magnitudes with README reference values when balancing RF-like costs vs CE Forge energy numbers.
- Clone targets MC **26.1-snapshot**; Re-Forestry targets **26.2** — confirm Energy 5.x remains binary-compatible (already pinned `5.0.0` in project).

## Source map

Clone root: `MarkDown_Maker/Finished_github_clone/2026-07-24/TechReborn-Energy`

| Path | Symbol / notes |
|---|---|
| `src/main/java/team/reborn/energy/api/EnergyStorage.java` | Interface + `SIDED` / `ITEM` / `EMPTY` / `ENERGY_COMPONENT` |
| `src/main/java/team/reborn/energy/api/EnergyStorageUtil.java` | `move`, `isEnergyStorage` |
| `README.md` | Conventions, Gradle include snippet, usage examples (references base types) |
| `src/main/resources/fabric.mod.json` | Mod id / depends / entrypoint (supports whole lib; not owned by this package) |

**Adjacent (not this module — depends / used by only):**

| Path | Why mentioned |
|---|---|
| `src/main/java/team/reborn/energy/api/base/*.java` | Implementations of `EnergyStorage` |
| `src/main/java/team/reborn/energy/impl/EmptyEnergyStorage.java` | Backing for `EMPTY` |
| `src/main/java/team/reborn/energy/impl/EnergyImpl.java` | Registers `ENERGY_COMPONENT`, ITEM fallback for `SimpleEnergyItem` |
| `src/main/java/team/reborn/energy/impl/SimpleItemEnergyStorageImpl.java` | ITEM storage example cited by `EnergyStorage` javadoc |
| `src/test/java/team/reborn/energy/test/EnergyTests.java` | Contract smoke tests |

Graph alias: `python3 tools/graphify_query.py Energy "…"`.

## Open questions / gaps

1. **Inventory vs directory:** filesystem folder `api/` also contains `base/`; inventory treats **base** as a separate module. This report follows the inventory (top-level package only). Confirm manager agents write a separate `features/base.md`.
2. **Lookup namespace mismatch:** lookups use `teamreborn:…` while the mod id / component registry use `team_reborn_energy`. Intentional and stable, but easy to mistype when documenting or searching.
3. **Direct `amount` mutation** in consumers (including Re-Forestry’s `EnergyHelper`) bypasses transactions — safe only for single-threaded server tick private buffers; not documented as an API method. Risk if those buffers are also mutated transactionally in the same tick.
4. **No unit type / voltage / tier** in the interface — all energy is raw `long`. Cross-mod balance relies on README conventions only.
5. **MCP search** for this repo indexes the same files; no additional public API beyond these two classes in package `team.reborn.energy.api`.
6. **MC 26.2 vs clone 26.1:** Energy 5.x line is “26.1 or later”; watch for transfer-API or `Identifier` / component API churn on 26.2 Loom mappings when bumping the dependency.
7. **`implemented-features.md`:** energy stack is used in source but not listed as a finished “feature” — may want a short infrastructure note when docs are next updated (out of scope for this report file).
