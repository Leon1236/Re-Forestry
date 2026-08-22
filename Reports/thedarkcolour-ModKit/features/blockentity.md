# thedarkcolour-ModKit — blockentity

## Summary

Tiny Forge sample package: one block entity (`InfinitePowerBlockEntity`) that is a creative/dev **Forge Energy (FE)** source. On every server tick it pushes `Integer.MAX_VALUE` FE into any adjacent block entity that exposes `ForgeCapabilities.ENERGY`. It also implements `IEnergyStorage` itself (extract-only infinite buffer). Not a reusable library API — shipped content for ModKit’s own `modkit:infinite_power` block. Size: **S** (2 Java files, ~114 LOC in-package).

## Player/API surface

| Surface | What players / other mods see |
|---|---|
| Block entity type | `modkit:infinite_power` (`ModKit.INFINITE_POWER_TYPE`) |
| Block (sibling package) | `modkit:infinite_power` — places this BE; EPIC `BlockItem` in ModKit creative tab |
| Energy capability | Exposes `ForgeCapabilities.ENERGY` via `getCapability` + self as `IEnergyStorage` |
| GUI / menu / network | None |
| Public library API | None — no interfaces, registries, or helpers meant for dependents |

Player-facing name (generated lang): **“Infinite Power”**. Behavior: place next to FE consumers; each server tick neighbors are force-filled.

## Architecture

```
ModKit (DeferredRegister BLOCK_ENTITY_TYPES)
  └─ INFINITE_POWER_TYPE → InfinitePowerBlockEntity::new
InfinitePowerBlock (block package, EntityBlock)
  ├─ newBlockEntity → InfinitePowerBlockEntity
  └─ getTicker (server only) → InfinitePowerBlockEntity::tick
InfinitePowerBlockEntity
  ├─ implements IEnergyStorage (self)
  ├─ LazyOptional<IEnergyStorage> energyCap → this
  ├─ tick: Direction.stream → neighbor.getCapability(ENERGY).receiveEnergy(MAX)
  └─ getCapability: return energyCap when ENERGY (+ remove check — see gaps)
```

Graphify (`modkit`, BFS around `InfinitePowerBlockEntity`): community ~12; edges to `IEnergyStorage`, `LazyOptional`, `ForgeCapabilities`-style energy methods, `tick`, `getCapability`, imports of root `ModKit` / sibling `InfinitePowerBlock`. Registration lives in root `ModKit.java` (out of this package’s inventory scope but required wiring).

No NBT save/load, no client ticker, no invalidation of the `LazyOptional` on remove.

## Data & assets

Nothing lives under `blockentity/` itself. Supporting assets for the paired block (out of package, for context):

| Kind | Path |
|---|---|
| Texture | `src/main/resources/assets/modkit/textures/block/infinite_power.png` |
| Blockstate / models / lang | `src/generated/resources/assets/modkit/...` (`infinite_power.json`, `en_us` → `block.modkit.infinite_power`) |
| Loot | No generated loot table found for this block in the clone |

## Dependencies

| Dep | Role |
|---|---|
| Minecraft 1.20.1 | `BlockEntity`, `Level`, `Direction`, tick signature |
| Forge 1.20.1-47.0.3 | `ForgeCapabilities.ENERGY`, `IEnergyStorage`, `LazyOptional`, `Capability` |
| Sibling `thedarkcolour.modkit.block.InfinitePowerBlock` | Host block + ticker wiring |
| Root `thedarkcolour.modkit.ModKit` | `INFINITE_POWER_TYPE` registry object |

No JEI, no energy libs beyond Forge Energy, no Fabric.

## Notable algorithms/contracts

1. **Push-all-sides tick** — Server-only static ticker. For each of 6 directions, if neighbor BE has ENERGY cap, call `receiveEnergy(Integer.MAX_VALUE, false)`. Ignores own extract API for the push path; neighbors are filled regardless of their max receive (subject to their `receiveEnergy` implementation).
2. **Self as infinite extract buffer** — `extractEnergy` / `getEnergyStored` / `getMaxEnergyStored` → `Integer.MAX_VALUE`; `receiveEnergy` → `0`; `canExtract` true; `canReceive` false.
3. **Capability gate anomaly** — `getCapability` returns the energy optional only when `this.remove && cap == ForgeCapabilities.ENERGY`. Vanilla/Forge convention is normally `!remove`. As written, ENERGY is exposed only while the BE is marked removed — likely a bug; pull-based extract from neighbors while the block is alive may fail even though tick-push still works.
4. **No `invalidateCaps`** — `energyCap` is never invalidated; usual Forge pattern would invalidate on remove.

## Port relevance to Re-Forestry

**Low — already covered.** Re-Forestry has the same *role* as debug creative energy:

- `com.leon1236.reforestry.core.energy.TileCreativeEnergy` + `BlockCreativeEnergy`
- Registry id `reforestry:debug_creative_energy`
- Team Reborn `InfiniteEnergyStorage.INSTANCE` + `EnergyStorageUtil.move(..., PUSH_RATE_PER_TICK=10_000)` to `EnergyStorage.SIDED` neighbors

Do **not** port ModKit’s Forge capability / `IEnergyStorage` / `Integer.MAX_VALUE` flood. If anything is useful to compare:

| ModKit | Re-Forestry |
|---|---|
| Push `Integer.MAX_VALUE` every tick | Cap push at 10k/tick (safer for testing) |
| Forge `ForgeCapabilities.ENERGY` | Fabric `EnergyStorage.SIDED` / TR Energy |
| BE implements storage interface | Returns `InfiniteEnergyStorage.INSTANCE` |

Optional follow-up (not from this package): confirm `debug_creative_energy` registers `EnergyStorage.SIDED` for pull-from-outside; tick push alone may be enough for local machine tests.

**Standalone-adopt rule:** do not add ModKit as a dependency; idea only, already adopted in spirit.

## Source map

| Path | Role |
|---|---|
| `src/main/java/thedarkcolour/modkit/blockentity/InfinitePowerBlockEntity.java` | Sole BE: tick push + `IEnergyStorage` + capability |
| `src/main/java/thedarkcolour/modkit/blockentity/package-info.java` | Nullness package annotations only |
| `src/main/java/thedarkcolour/modkit/block/InfinitePowerBlock.java` | Host block / ticker (sibling module) |
| `src/main/java/thedarkcolour/modkit/ModKit.java` L61–62 | Block + `BlockEntityType` registration |

## Open questions/gaps

1. Is `this.remove &&` in `getCapability` intentional (only expose when removed) or a typo for `!this.remove`? Affects pull-based consumers.
2. No `invalidateCaps` / `LazyOptional` lifecycle — intentional minimal sample or oversight?
3. Pushing `Integer.MAX_VALUE` every tick into up to 6 neighbors — intentional stress tool; any documented “safe for production test worlds” note? (None in-package.)
4. No loot table for `infinite_power` in generated resources — break drops / creative-only assumption unclear from this package alone.
5. Graphify does not model Forge capability runtime edges; architecture above is from source + registration, not capability graph completeness.
