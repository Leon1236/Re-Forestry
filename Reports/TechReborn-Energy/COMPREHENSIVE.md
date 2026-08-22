# TechReborn-Energy — Comprehensive

| Field | Value |
|---|---|
| Repo | TechReborn-Energy |
| Alias | `Energy` |
| Clone | `MarkDown_Maker/Finished_github_clone/2026-07-24/TechReborn-Energy` |
| Library | `team_reborn_energy` **5.0.0** (Maven `teamreborn:energy`) |
| Clone MC pin | 26.1-snapshot-1 |
| Re-Forestry pin | `energy_version=5.0.0` (`gradle.properties`); `implementation` + `include` in `build.gradle` |
| Phase inputs | [`00-INVENTORY.md`](00-INVENTORY.md) + [`features/api.md`](features/api.md) · [`features/base.md`](features/base.md) · [`features/impl.md`](features/impl.md) |
| Graph | `python3 tools/graphify_query.py Energy "…"` |
| Date | 2026-07-30 |

**Verdict:** Tiny Fabric energy transfer library (11 main Java files, 3 packages). Re-Forestry already treats it as a **declared stack dependency** (jar-in), not code to vendor. Do not copy classes into `com.leon1236.reforestry.*`.

---

## 1. api / base / impl relationship

One jar, three packages. Public authors live in `api` + `api.base`; `impl` is bootstrap and private backends.

```
┌─────────────────────────────────────────────────────────────┐
│  api  (team.reborn.energy.api)                              │
│    EnergyStorage          — contract + SIDED / ITEM lookups │
│    EnergyStorageUtil      — move(), isEnergyStorage()       │
│         │                                                   │
│         │ implements / documented as                        │
│         ▼                                                   │
│  base (team.reborn.energy.api.base)                         │
│    SimpleEnergyStorage, SimpleSidedEnergyContainer,         │
│    LimitingEnergyStorage, DelegatingEnergyStorage,          │
│    InfiniteEnergyStorage, SimpleEnergyItem                  │
│         │                                                   │
│         │ EMPTY / ENERGY_COMPONENT / item createStorage     │
│         ▼                                                   │
│  impl (team.reborn.energy.impl)  @ApiStatus.Internal        │
│    EnergyImpl.init     — fabric.mod.json main               │
│    EmptyEnergyStorage  — EnergyStorage.EMPTY                │
│    SimpleItemEnergyStorageImpl — item stack energy backend  │
└─────────────────────────────────────────────────────────────┘
         │
         ▼
  fabric-transfer-api-v1  (Transaction, SnapshotParticipant,
                           ContainerItemContext, Lookup)
```

| Layer | Owns | Does **not** own |
|---|---|---|
| **api** | Interface, lookups (`teamreborn:sided_energy`, `teamreborn:energy`), transfer helper | Ready-made buffers, registration of the energy component |
| **base** | Ready-made storages + item battery interface | Lookups, mod entrypoint, empty sentinel |
| **impl** | Component registration, ITEM fallback for `SimpleEnergyItem`, EMPTY singleton, item backend | Public extension surface (all `@Internal`) |

**Dependency direction:** `base` → `api` (+ thin call into `impl` for item `createStorage`). `impl` → `api` + `base`. `api` static fields re-export `impl` (`EMPTY`, `ENERGY_COMPONENT`) at class-init.

**Namespace note:** Lookup ids use `teamreborn:…`; mod id / component registry use `team_reborn_energy`. Intentional; easy to mistype.

**Conventions (README):** push-based energy (sources push; machines/wires must not pull); reference magnitudes 1 coal = 4000, 1 plank = 750; single `long` resource (no voltage/tier).

---

## 2. Module map

| id | slug | Package | Size | Feature report | One-line purpose |
|---|---|---|---|---|---|
| 1 | api | `team.reborn.energy.api` | S (2 files) | [features/api.md](features/api.md) | `EnergyStorage` contract + `EnergyStorageUtil` |
| 2 | base | `team.reborn.energy.api.base` | S (6 files) | [features/base.md](features/base.md) | Ready-made storages and `SimpleEnergyItem` |
| 3 | impl | `team.reborn.energy.impl` | S (3 files) | [features/impl.md](features/impl.md) | Entrypoint, EMPTY, item-storage wiring |

**Out of scope (inventory):** Gradle/CI, README/LICENSE, assets, `src/test` smoke tests.

Filesystem: `api/base/` sits under `api/` on disk; inventory still treats **base** as its own module (package split).

---

## 3. How Re-Forestry already uses EnergyStorage

Re-Forestry jars Energy 5.0.0 (`include`), so `EnergyImpl.init` runs with the mod. All usage is **block-side**; no `SimpleEnergyItem` / item `ENERGY_COMPONENT` yet.

### 3.1 Dependency wiring

| Where | What |
|---|---|
| `gradle.properties` | `energy_version=5.0.0` |
| `build.gradle` | `implementation` + `include` of `teamreborn:energy:${energy_version}` |

Intentional stack dep (standalone-adopt rule satisfied — not pasted from the clone).

### 3.2 Call-site map

| Pattern | Energy symbols | Re-Forestry locations |
|---|---|---|
| Receive-only machine buffer | `SimpleEnergyStorage(cap, maxInsert, 0)` + `onFinalCommit` → `setChanged()` | `TilePowered` → factory machines; `TileAlvearyClimatiser` (fan/heater, 2k buffer) |
| SIDED registration (powered tiles + access wrap) | `EnergyStorage.SIDED.registerForBlockEntity` via `EnergyHelper.registerSided` | `FactoryTiles` (centrifuge, smelter, still, squeezer, bottler, carpenter, fermenter, fabricator); `CoreTiles` (`DEBUG_POWERED`) |
| SIDED registration (alveary) | Direct `SIDED.registerForBlockEntity` → `getEnergyStorage()` | `ApicultureTiles` — `ALVEARY_FAN`, `ALVEARY_HEATER` |
| Side gating | Custom `RestrictedEnergy` implementing `EnergyStorage` | `AccessStorageHelper.wrapEnergy` (Forestry `AccessMode`) |
| Internal work drain | Direct `.amount` mutation (README-approved tick shortcut) | `EnergyHelper.consumeEnergyToDoWork` from `TilePowered` |
| Creative push source | `InfiniteEnergyStorage.INSTANCE` + `SIDED.find` + `EnergyStorageUtil.move` | `TileCreativeEnergy` (`debug_creative_energy`) |
| Handler contract | `IPowerHandler.getEnergyManager()` → `SimpleEnergyStorage` | `TilePowered`, `TileDebugPowered`, sockets |
| Multiblock energy sum | Read `.amount` on climatiser tiles | `AlvearyController` |
| GUI power ledger | Reads container energy ints (synced from storage) | `GuiPowerLedger` + `IContainerEnergy` (not Energy API types) |

### 3.3 Flow (typical powered machine)

```
Neighbor / creative tile pushes
        │ EnergyStorageUtil.move / insert
        ▼
EnergyStorage.SIDED.find(level, pos, face)
        │ EnergyHelper.registerSided → optional AccessStorageHelper.RestrictedEnergy
        ▼
SimpleEnergyStorage (TilePowered / climatiser)
        │ tick: EnergyHelper.consumeEnergyToDoWork (amount -= …)
        ▼
Machine work cycle
```

Machines are **receive-only** (`maxExtract = 0`). Power enters because **sources push** (creative block, other mods’ cables/generators). That matches Energy’s push convention.

### 3.4 Used vs unused `base` types

| Type | In Re-Forestry? |
|---|---|
| `SimpleEnergyStorage` | **Yes** — primary buffer |
| `InfiniteEnergyStorage` | **Yes** — creative fixture |
| `SimpleSidedEnergyContainer` | No — candidates for face-specific I/O later |
| `LimitingEnergyStorage` | No — external rate cap vs larger internal buffer |
| `DelegatingEnergyStorage` | No — validity gates / changing targets |
| `SimpleEnergyItem` | No — portable batteries / electric tools deferred |

`RestrictedEnergy` overlaps Limiting/Delegating + support flags, specialized for `AccessMode`. Prefer library wrappers for new cases when they fit.

### 3.5 Tracker notes

- `files/implemented-features.md` §4.6: alveary fan/heater Team Reborn `EnergyStorage` (2k buffer).
- Factory F1–F15: `TilePowered` / `EnergyHelper` / creative FE fixture; moistener explicitly no FE.
- Deferred: ModuleEnergy engines (peat/biogas/…); external FE without debug block.

---

## 4. Feature links (quick index)

| Need | Read |
|---|---|
| Lookups, insert/extract contract, `move` protocol, push model | [features/api.md](features/api.md) |
| Buffer/wrapper/item types, math, Re-Forestry used/unused table | [features/base.md](features/base.md) |
| Entrypoint, component id, item stack distribution, `@Internal` rule | [features/impl.md](features/impl.md) |
| Module list / out of scope | [00-INVENTORY.md](00-INVENTORY.md) |

---

## 5. Usage checklist (for Re-Forestry work)

### Always

- [ ] Depend on Maven `teamreborn:energy` (`include`); **do not** copy `api`/`base`/`impl` into our packages.
- [ ] Do **not** import `team.reborn.energy.impl.*` — use `EnergyStorage` / `api.base` only.
- [ ] New block energy: own a `SimpleEnergyStorage` (or sided container), override `onFinalCommit` → `setChanged()`, **persist `.amount`** yourself.
- [ ] Register with `EnergyStorage.SIDED` after the `BlockEntityType` exists (`EnergyHelper.registerSided` if `IPowerHandler`, or direct register like alveary).
- [ ] Prefer **push** from generators; consumers should not poll-pull neighbors.
- [ ] External transfer → transactional `insert`/`extract` or `EnergyStorageUtil.move`; internal tick drain may mutate `.amount` on an owned buffer (same tick: avoid mixing with transactional ops on that buffer).
- [ ] `supportsInsertion` / `supportsExtraction` are connection hints — simulate transfer for free space / extractable amount.
- [ ] Null `Direction` on SIDED = full/internal view (no side limits).
- [ ] Align balance with README refs (coal=4000, plank=750) when converting CE FE numbers.

### When adding side modes

- [ ] Prefer `AccessStorageHelper.wrapEnergy` for Forestry access panels.
- [ ] For per-face insert/extract caps without custom wrappers, evaluate `SimpleSidedEnergyContainer` / `LimitingEnergyStorage` before new `EnergyStorage` impls.

### When adding chargeable items

- [ ] Implement `SimpleEnergyItem` on the item; let `EnergyImpl` ITEM fallback wire storage.
- [ ] Use stock `EnergyStorage.ENERGY_COMPONENT` only on **our** stacks; cross-mod via `EnergyStorage.ITEM` / `isEnergyStorage`.
- [ ] Do not depend on `SimpleItemEnergyStorageImpl` (internal).

### Do not

- [ ] Invent a parallel energy API or Forge FE capability layer for Fabric machines.
- [ ] Read other mods’ energy components by id — use ITEM lookup.
- [ ] Pull power into machines/wires (breaks interop).
- [ ] Assume clone MC 26.1-snapshot == game 26.2 without verifying the published 5.x jar if transfer/component APIs churn.

### Future / deferred (tracker)

- [ ] ModuleEnergy engines as real push sources (replace reliance on `debug_creative_energy`).
- [ ] Optional note in `implemented-features.md` that Team Reborn Energy is infrastructure (already used; mostly undocumented as a line item outside alveary §4.6).

---

## 6. Open gaps (rolled up)

1. **MC 26.1 clone vs Re-Forestry 26.2** — Energy 5.x is “26.1 or later”; pin is already 5.0.0. Escalate only if SIDED/ITEM/component registration breaks in-game.
2. **No item energy in RF yet** — fine until tools/batteries/backpacks need it.
3. **Direct `amount` mutation** — intentional; document in new tile code reviews.
4. **Upstream `SimpleEnergyItem` TODO** (tooltip / craft energy transfer) — implement locally if needed.
5. **Lookup vs mod id namespaces** — `teamreborn` vs `team_reborn_energy`.

---

## 7. Source map (clone)

| Path | Module |
|---|---|
| `src/main/java/team/reborn/energy/api/EnergyStorage.java` | api |
| `src/main/java/team/reborn/energy/api/EnergyStorageUtil.java` | api |
| `src/main/java/team/reborn/energy/api/base/*.java` | base (6 types) |
| `src/main/java/team/reborn/energy/impl/EnergyImpl.java` | impl |
| `src/main/java/team/reborn/energy/impl/EmptyEnergyStorage.java` | impl |
| `src/main/java/team/reborn/energy/impl/SimpleItemEnergyStorageImpl.java` | impl |
| `src/main/resources/fabric.mod.json` | whole lib (entrypoint `EnergyImpl::init`) |
| `README.md` | conventions + author examples |

**Primary Re-Forestry energy files:** `core/energy/EnergyHelper.java`, `TileCreativeEnergy.java`, `core/tiles/TilePowered.java`, `IPowerHandler.java`, `core/access/AccessStorageHelper.java`, `factory/features/FactoryTiles.java`, `apiculture/features/ApicultureTiles.java`, `apiculture/multiblock/TileAlvearyClimatiser.java`.
