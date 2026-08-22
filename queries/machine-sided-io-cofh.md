# Machine sided I/O (CoFH-style on Fabric)

## Problem
Access ledger clicks never reached the server: `handleAccessButton` returned `false` on the client (`ServerPlayer` check), so `ScreenForestry` skipped `handleInventoryButtonClick`. Face config appeared broken.

Follow-up: client did not update `accessData` on click (server-only), so I/O face tints stayed stale. Fix: optimistic `accessData` cycle on client. Face color is shown by tinting the 3D sides (multiplyColor / translucent face wash), not 2D chips.

Fluid/energy `SIDED` lookups ignored `AccessMode` (items already gated via `WorldlyContainer` + `WorldlyAccessHelper`).

## CoFH pattern (Thermal `Reconfigurable4WayBlockEntity`)
Per-face `SideConfig` chooses which capability is exposed: `NONE` → null, `INPUT` / `OUTPUT` / `BOTH` → matching handlers.

## Fabric mapping
| CoFH | Re-Forestry |
|---|---|
| `SideConfig` | `AccessMode` on `TileBase` |
| Item handler by group | `WorldlyContainer` + `ContainerStorage.of` (unchanged) |
| Fluid/energy handler by config | `AccessStorageHelper.wrap` / `wrapEnergy` via `FilteringStorage` + restricted `EnergyStorage` |
| Neighbor refresh on config change | `level.updateNeighborsAt` in `TileBase` |

## Files
- `core/access/AccessStorageHelper.java` — wrap storages by face mode
- `core/fluids/FluidHelper.java` — `FluidStorage.SIDED` + access wrap
- `core/energy/EnergyHelper.java` — energy wrap when tile is `ISidedAccess`
- `core/gui/ContainerMachine.java` — client returns true so the button packet is sent; client also predicts `accessData`
- `core/client/AccessMachinePipRenderer.java` — tint 3D faces by access mode
- `factory/features/FactoryTiles.java` — use `FluidHelper.registerSided`
