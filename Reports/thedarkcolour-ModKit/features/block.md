# thedarkcolour-ModKit — block

## Summary

The `thedarkcolour.modkit.block` package is a tiny shipped sample: one block class (`InfinitePowerBlock`) plus package-nullness annotations. It is a creative/dev **EntityBlock** that hosts `InfinitePowerBlockEntity` and runs a **server-only** ticker. Registration, BlockItem (EPIC rarity), creative-tab entry, datagen models/lang, and the FE push logic live outside this package (`ModKit`, `ModKitDataGen`, `blockentity`). Not a reusable block API library — content for ModKit’s own toolbox.

## Player/API surface

| Surface | Detail |
|---|---|
| Block id | `modkit:infinite_power` |
| BlockItem | Same id; `Item.Properties().rarity(Rarity.EPIC)` |
| Creative tab | `itemGroup.modkit` (“ModKit”); first display item |
| Lang | `block.modkit.infinite_power` → “Infinite Power” |
| Public Java API in package | `InfinitePowerBlock` ctor + `EntityBlock` overrides; package-local `createTickerHelper` |
| Player interaction | Placeable cube; no custom use/GUI/shape; behavior is adjacent FE injection via BE tick |

No tags, recipes, loot tables, or config for this block in the package itself.

## Architecture

```
ModKit (DeferredRegister)
  BLOCKS  → InfinitePowerBlock
  BLOCK_ENTITIES → InfinitePowerBlockEntity (type id infinite_power)
  ITEMS → BlockItem(INFINITE_POWER)
       ↓
InfinitePowerBlock (Block + EntityBlock)
  newBlockEntity → InfinitePowerBlockEntity
  getTicker (server) → InfinitePowerBlockEntity::tick
       ↓
InfinitePowerBlockEntity (Forge IEnergyStorage + ENERGY capability)
  tick: receiveEnergy(MAX) into neighbors on all 6 sides
```

- Extends vanilla `Block` with default `Properties.of()` (no strength/sound/map-color overrides).
- Implements `EntityBlock` rather than `BaseEntityBlock` (so it copies vanilla’s private `createTickerHelper` locally).
- Client ticker always `null`; server ticker type-checked against `ModKit.INFINITE_POWER_TYPE`.
- Hard dependency on root registry fields (`ModKit.INFINITE_POWER_TYPE`) and sibling `blockentity` package.

## Data & assets

| Kind | Location |
|---|---|
| Hand texture | `src/main/resources/assets/modkit/textures/block/infinite_power.png` (16×16 PNG) |
| Blockstate / models | Generated: `src/generated/resources/assets/modkit/{blockstates,models/block,models/item}/infinite_power.json` — `cube_all` → `modkit:block/infinite_power` |
| Lang | Generated `en_us.json` via `DataHelper.createEnglish` + auto name from registry |
| Datagen hook | `ModKitDataGen.addBlockModels` → `models.simpleBlock(ModKit.INFINITE_POWER.get())`; item models via `createItemModels(true, true, false, null)` |

No loot/recipe/tag JSON for this block under main resources.

## Dependencies

| Dep | Role for `block` |
|---|---|
| Minecraft 1.20.1 | `Block`, `EntityBlock`, `BlockEntityTicker`, … |
| Forge (loader ≥45) | Registration lives in Forge `DeferredRegister` / `RegistryObject` (outside package); package code is vanilla-shaped |
| `thedarkcolour.modkit.ModKit` | `INFINITE_POWER_TYPE` for ticker match |
| `thedarkcolour.modkit.blockentity.InfinitePowerBlockEntity` | BE instance + `tick` method ref |
| Forge Energy (via BE, not imported here) | Neighbor FE push / capability |

Package-level imports are vanilla + JetBrains `@Nullable` + the two ModKit types above. No Fabric / Team Reborn Energy in this repo.

## Notable algorithms/contracts

1. **Ticker gate:** `level.isClientSide ? null : createTickerHelper(type, expected, InfinitePowerBlockEntity::tick)`.
2. **Local `createTickerHelper`:** identity check `expected == type`, then unchecked cast — same contract as `BaseEntityBlock.createTickerHelper`.
3. **Default block properties:** `Properties.of()` only — soft/creative prop defaults; intentional for a test cube.
4. **Behavioral contract (owned by BE, triggered by this block):** each server tick, for every `Direction`, if neighbor has a BE with `ForgeCapabilities.ENERGY`, call `receiveEnergy(Integer.MAX_VALUE, false)`. Extract/side capability exposure is on the BE (`IEnergyStorage` with infinite extract, no receive).

## Port relevance to Re-Forestry

| Verdict | Why |
|---|---|
| **Do not port as a module** | One sample block, not a library. Re-Forestry already has the same *idea* as creative RF: `BlockCreativeEnergy` + `TileCreativeEnergy` (Team Reborn `InfiniteEnergyStorage` + sided `EnergyStorageUtil.move`). |
| **Pattern already covered** | Server-only ticker + `createTickerHelper` — used widely (`BlockMachine`, bee housing, etc.). Prefer `BaseEntityBlock` (as Re-Forestry does) over copying a private helper. |
| **Possible micro-note only** | ModKit’s “`Block` + `EntityBlock` + local helper” is a fallback if subclassing `BaseEntityBlock` is awkward; Re-Forestry rarely needs it. |
| **Assets** | Texture/models are ModKit-branded test content — no adopt unless a ModKit-parity creative energy block is desired (already have Forestry-style creative energy). |

Standalone-adopt rule: even if copied, strip Forge registry/capability wiring; energy must stay Team Reborn / Fabric transfer, not a ModKit jar dependency.

## Source map

| Path | Role |
|---|---|
| `src/main/java/thedarkcolour/modkit/block/InfinitePowerBlock.java` | Sole block class |
| `src/main/java/thedarkcolour/modkit/block/package-info.java` | `@MethodsReturnNonnullByDefault` / `@FieldsAreNonnullByDefault` / `@ParametersAreNonnullByDefault` |
| `src/main/java/thedarkcolour/modkit/ModKit.java` L61–63, L76 | Register block / BE type / BlockItem; creative tab accept |
| `src/main/java/thedarkcolour/modkit/ModKitDataGen.java` L42–44 | `simpleBlock` datagen |
| `src/main/java/thedarkcolour/modkit/blockentity/InfinitePowerBlockEntity.java` | BE + tick + FE (sibling module; not deep-dived) |
| `src/main/resources/assets/modkit/textures/block/infinite_power.png` | Hand asset |
| `src/generated/resources/assets/modkit/.../infinite_power.json` (+ lang) | Generated models/blockstate/name |

Graph (alias `modkit`): community ~14 around `InfinitePowerBlock`; edges to `EntityBlock`, ticker helpers, `ModKit`, `InfinitePowerBlockEntity`.

## Open questions/gaps

1. **`getCapability` on BE uses `this.remove && cap == ENERGY`** (likely meant `!this.remove`) — capability exposure may be inverted; confirm when reporting `blockentity` (not verified in play here).
2. **No hardness / tool / sound / map color** — fine for creative, odd if ever used as real content.
3. **No loot table** — creative break may drop nothing depending on default loot; BlockItem exists for creative give.
4. **Push rate:** `Integer.MAX_VALUE` per side per tick vs Re-Forestry creative energy’s capped `PUSH_RATE_PER_TICK` — different balancing; not a port target.
5. **MC 26.2 / Fabric:** `EntityBlock` + ticker pattern still valid; Forge `DeferredRegister` / `IEnergyStorage` / capabilities must be rewritten (already done for Re-Forestry creative energy).
