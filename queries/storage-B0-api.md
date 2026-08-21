# Storage B0 — API + module shell (Fabric notes)

**Date:** 2026-07-26  
**CE refs:** `forestry.api.storage.*`, `forestry.storage.ModuleStorage`, `BackpackInterface`

## What landed

- Public API under `com.leon1236.reforestry.api.storage`:
  - `EnumBackpackType` (NORMAL / WOVEN / NATURALIST)
  - `IBackpackDefinition`
  - `IBackpackInterface` (`createBackpack`, `createNaturalistBackpack`, `createNaturalistBackpackFilter`)
  - `BackpackEvents` (Fabric) instead of Forge `BackpackEvent` / `BackpackStowEvent` / `BackpackResupplyEvent`
- Impl: `BackpackInterface`, `BackpackFilterNaturalist` (uses `IndividualItems.getSpeciesTypeId`)
- Module: `ModuleStorage` (`reforestry:storage`), depends `core`; empty `StorageItems` / `StorageMenuTypes`; `StorageCreativeTabs` (empty display; chest icon until B1 miner bag)
- Wired in `ReForestry` module list; config-gated via existing `ModuleConfig`

## Forge → Fabric event mapping

| CE (Forge) | Re-Forestry (Fabric) |
|---|---|
| `BackpackStowEvent extends Event` + `@Cancelable` | `BackpackEvents.STOW` — callback returns `true` to cancel |
| `BackpackResupplyEvent extends Event` + `@Cancelable` | `BackpackEvents.RESUPPLY` — callback returns `true` to cancel |
| `@SubscribeEvent` on Forge bus | `BackpackEvents.STOW.register(...)` / `RESUPPLY.register(...)` |

No Forge `Event` subclasses in the public API. Stow/resupply handlers (pickup tick) land with B1+.

## Deferred to later B stages

- `createBackpack` / `createNaturalistBackpack` throw until `ItemBackpack` (B1) / naturalist bag (B4)
- Pickup + resupply handlers (`PickupHandlerStorage`, `BackpackResupplyHandler`)
- Backpack definitions (MINER, …) on `ModuleStorage` — register with items in B1–B4
