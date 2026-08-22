# TechReborn-Energy — impl

| Field | Value |
|---|---|
| Repo / alias | TechReborn-Energy / Energy |
| Module slug | `impl` |
| Package | `team.reborn.energy.impl` |
| Path | `src/main/java/team/reborn/energy/impl` |
| Size | S — 3 Java classes, all `@ApiStatus.Internal` |
| Clone | `MarkDown_Maker/Finished_github_clone/2026-07-24/TechReborn-Energy` (mod `team_reborn_energy` **5.0.0**, MC **26.1-snapshot-1**) |

## Summary

`impl` is the **library bootstrap and private backends** for Team Reborn Energy. It is not content and not a public extension surface: every class is marked `@ApiStatus.Internal`. The package does three jobs:

1. **Mod entrypoint** — `EnergyImpl.init` (wired from `fabric.mod.json` `main`) registers the stock energy `DataComponentType` and an `EnergyStorage.ITEM` fallback for items that implement `SimpleEnergyItem`.
2. **Empty sentinel** — `EmptyEnergyStorage` is the singleton behind public `EnergyStorage.EMPTY`.
3. **Item energy backend** — `SimpleItemEnergyStorageImpl` stores energy on item stacks via `ENERGY_COMPONENT`, supports stackable even distribution, and is wrapped in `DelegatingEnergyStorage` for context validity.

Public API classes in `team.reborn.energy.api` / `api.base` re-export or call into this package; consumers are expected to use the API, not import `impl` directly.

## Player / API surface

| Audience | What they see |
|---|---|
| Players | Nothing from this package. No blocks, items, GUIs, or lang keys. |
| Mod authors (intended) | Indirect only: `EnergyStorage.EMPTY`, `EnergyStorage.ENERGY_COMPONENT`, and auto-wired item storages when an item implements `SimpleEnergyItem`. |
| Mod authors (must not) | Direct use of `EnergyImpl`, `EmptyEnergyStorage`, or `SimpleItemEnergyStorageImpl` — all `@ApiStatus.Internal`. |

`fabric.mod.json` entrypoint:

```json
"main": ["team.reborn.energy.impl.EnergyImpl::init"]
```

That is the only runtime hook owned by this package. Tests call `EnergyImpl.init()` manually after Bootstrap (`EnergyTests.setup`).

## Architecture

```
fabric.mod.json main
        │
        ▼
  EnergyImpl.init()
        ├── Registry.register(DATA_COMPONENT_TYPE, team_reborn_energy:energy, ENERGY_COMPONENT)
        └── EnergyStorage.ITEM.registerFallback(
                stack → SimpleEnergyItem? → SimpleEnergyItem.createStorage(...) : null)

EnergyStorage.EMPTY  ──re-exports──►  EmptyEnergyStorage.EMPTY  (no-op EnergyStorage)

SimpleEnergyItem.createStorage(...)
        └── SimpleItemEnergyStorageImpl.createSimpleStorage(ctx, capacity, maxInsert, maxExtract)
                └── DelegatingEnergyStorage(
                        SimpleItemEnergyStorageImpl,   // private; no context validation itself
                        () → same item + amount > 0)   // validity gate
```

**`EnergyImpl`**
- Builds `DataComponentType<Long>` with persistent codec `nonNegativeLong()` and network codec `ByteBufCodecs.VAR_LONG`.
- Registers component under id `team_reborn_energy:energy`.
- ITEM fallback: if `stack.getItem() instanceof SimpleEnergyItem`, returns storage with that item’s capacity / max input / max output for the current stack; otherwise `null` (other providers can still win).

**`EmptyEnergyStorage`**
- Private constructor; public static `EMPTY`.
- `supportsInsertion` / `supportsExtraction` → `false`; `insert` / `extract` / `getAmount` / `getCapacity` → `0`.

**`SimpleItemEnergyStorageImpl`**
- Factory validates non-negative capacity / maxInsert / maxExtract (`StoragePreconditions`).
- Remembers the **starting item** from `ContainerItemContext` so the delegate only operates while the variant is still that item and count &gt; 0.
- Amount and capacity scale by **stack count**: `getAmount() = count * energyPerItem`, `getCapacity() = count * capacity`.
- Insert/extract work **per count**, then multiply back: energy is distributed evenly across the stack (e.g. inserting 3 into a stack of 2 inserts 0 or 2).
- Mutation path (`trySetEnergy`): clone variant → `SimpleEnergyItem.setStoredEnergyUnchecked` → nested transaction `extract` old variant + `insert` new variant for exact `count`; commit only on full success.

No other packages under `impl`. No mixins, no networking beyond the component’s sync codec.

## Data & assets

| Kind | Detail |
|---|---|
| Registry | `DataComponentType<Long>` id **`team_reborn_energy:energy`** (built in `EnergyImpl`, exposed as `EnergyStorage.ENERGY_COMPONENT`) |
| Codec contract | Persistent: `Codec.LONG` validated `value >= 0`; reject with `"Energy value must be non-negative: …"` |
| Network | `ByteBufCodecs.VAR_LONG` |
| Package-local assets | **None** — mod icon `assets/team_reborn_energy/icon.png` and `fabric.mod.json` live under resources, not under this Java package |
| Datagen / tags / recipes | None |

Docs on `EnergyStorage.ENERGY_COMPONENT`: use only on **your own mod’s** item stacks; cross-mod interaction must go through `EnergyStorage.ITEM`, not by reading another mod’s component.

## Dependencies

| Dependency | Role in `impl` |
|---|---|
| `fabric-transfer-api-v1` | `ContainerItemContext`, `ItemVariant`, `Transaction` / `TransactionContext`, `StoragePreconditions` |
| Fabric API Lookup (via `EnergyStorage.ITEM`) | Fallback registration in `EnergyImpl.init` |
| Minecraft | `Registry` / `BuiltInRegistries.DATA_COMPONENT_TYPE`, `DataComponentType`, `Identifier`, `Item` / `ItemStack`, serialization codecs |
| Same-lib `api` | `EnergyStorage` interface + lookups |
| Same-lib `api.base` | `SimpleEnergyItem` (helpers + createStorage), `DelegatingEnergyStorage` (wrapper) |

Declared in `fabric.mod.json`: `java >= 25`, `minecraft >= 26.1-`, `fabric-transfer-api-v1 >= 5.1.0`. No client-only entrypoint.

Re-Forestry already jars this library (`build.gradle`: `implementation` + `include` of `teamreborn:energy:${energy_version}`, `energy_version=5.0.0`), so `EnergyImpl.init` runs when the included mod loads.

## Notable algorithms / contracts

1. **Non-negative energy component** — codec rejects negative longs; `SimpleEnergyItem.setStoredEnergyUnchecked` removes the component when `newAmount <= 0` so empty batteries stack with freshly crafted ones.
2. **Even stack distribution** — insert/extract divide by `ctx.getAmount()`; leftover that cannot be split evenly is not applied (integer division). Documented on `SimpleEnergyItem.createStorage`.
3. **Transactional item rewrite** — energy changes are item-variant swaps inside a nested transaction; failure leaves the context unchanged.
4. **Context validation is external** — `SimpleItemEnergyStorageImpl` itself does not re-check the variant; `DelegatingEnergyStorage`’s predicate enforces “same starting item and amount &gt; 0”.
5. **Push-based world energy is not this package** — block `SIDED` storages and `EnergyStorageUtil.move` live in `api` / `api.base`; `impl` only boots the library and backs item energy + EMPTY.
6. **Internal visibility** — JetBrains `@ApiStatus.Internal` on all three types; public constants are obtained through `EnergyStorage` / `SimpleEnergyItem`.

## Port relevance to Re-Forestry

| Topic | Relevance |
|---|---|
| Fork / reimplement `impl`? | **No.** Treat as a black-box dependency. Re-Forestry already includes Energy **5.0.0**. |
| What RF uses today | Block-side `EnergyStorage.SIDED` + `SimpleEnergyStorage` / `InfiniteEnergyStorage` / `EnergyStorageUtil` (`TilePowered`, alveary fan/heater, creative energy, `EnergyHelper`, `AccessStorageHelper`). **No** `SimpleEnergyItem` / item `ENERGY_COMPONENT` usage in `src/`. |
| What `impl` still does for RF | When the included jar loads, `EnergyImpl.init` registers the component and ITEM fallback — required for any other mod (or future RF battery item) that uses `SimpleEnergyItem`. `EnergyStorage.EMPTY` is available if RF needs a no-op handle. |
| Implemented features | `files/implemented-features.md` §4.6 notes alveary fan/heater Team Reborn `EnergyStorage` (2k buffer) — that path is **`api.base.SimpleEnergyStorage`**, not classes in `impl`. |
| Future item batteries / electric tools | Prefer `SimpleEnergyItem` + stock component; do **not** copy `SimpleItemEnergyStorageImpl`. Custom item storages should still validate `ContainerItemContext` like the delegate pattern. |
| Standalone-adopt rule | Already satisfied: Energy is an **intentional stack dependency**, not code pasted from the clone. |

## Source map

| File | Role |
|---|---|
| `src/main/java/team/reborn/energy/impl/EnergyImpl.java` | Entrypoint; `ENERGY_COMPONENT`; ITEM fallback for `SimpleEnergyItem` |
| `src/main/java/team/reborn/energy/impl/EmptyEnergyStorage.java` | Singleton empty `EnergyStorage` → `EnergyStorage.EMPTY` |
| `src/main/java/team/reborn/energy/impl/SimpleItemEnergyStorageImpl.java` | Item `EnergyStorage` backend + `createSimpleStorage` factory |

**Callers outside this package (for orientation only):**

| Caller | Use |
|---|---|
| `src/main/resources/fabric.mod.json` | `EnergyImpl::init` as `main` |
| `api/EnergyStorage.java` | Imports `EmptyEnergyStorage`, `EnergyImpl` for `EMPTY` and `ENERGY_COMPONENT` |
| `api/base/SimpleEnergyItem.java` | `SimpleItemEnergyStorageImpl.createSimpleStorage` |
| `src/test/.../EnergyTests.java` | Manual `EnergyImpl.init()`; component presence tests |

## Open questions / gaps

1. **Clone MC pin vs Re-Forestry** — Energy clone targets **26.1-snapshot-1**; Re-Forestry targets **26.2**. Confirm maven `teamreborn:energy:5.0.0` (or newer 5.x) remains binary-compatible with RF’s Fabric API / transfer API on 26.2; escalate only if component registration or ITEM lookup breaks in-game.
2. **Does RF need item energy?** — No current `SimpleEnergyItem` implementors. Deferred until electric tools, portable cells, or JEI/interop demos need stack energy.
3. **Javadoc leakage** — `EnergyStorage` javadoc names `SimpleItemEnergyStorageImpl` as an “implementation example” even though the type is `@ApiStatus.Internal`; authors should copy the *pattern* (context checks + `DelegatingEnergyStorage`), not depend on the class.
4. **Out of scope for this report** — `api`, `api.base`, tests, README conventions (coal=4000 / plank=750, push-based SIDED) — covered by sibling Phase 1 modules or inventory notes.
