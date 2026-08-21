# Stage F4c — Fluid containers

Minimal can / capsule / refractory stubs matching CE registry ids, wired to Fabric `FluidStorage.ITEM`.

## Registry ids

| Container | Item id | Capacity | Hot-fluid rule |
|---|---|---|---|
| Tin can | `reforestry:can` | 1000 mB (81000 droplets) | accepts all Forestry fluids |
| Wax capsule | `reforestry:capsule` | 1000 mB | rejects fluids ≥ 310.15 K (CE wax-melting rule) |
| Refractory capsule | `reforestry:refractory` | 1000 mB | accepts all Forestry fluids |

Fluid stored in data component `reforestry:fluid_container_contents` (`FluidVariant` + droplet amount).

## Implementation

| Piece | Location |
|---|---|
| `EnumContainerType` | `core.items.definitions` |
| `ItemFluidContainerForestry` | `core.items` |
| `FluidContainerContents` / `FluidContainerItemStorage` | `core.items` |
| Registration | `core.features.FluidsItems` |
| Helpers | `FluidsItems.createFilled(type, fluid)` / `FluidsItems.isEmpty(stack)` |

Buckets: Fabric API auto-registers `BucketItem` with bidirectional fluid↔bucket mapping — no extra wiring needed for the nine Forestry buckets.

## Smoke test

1. Creative tab **Re-Forestry**: empty can/capsule/refractory + biomass-filled examples.
2. Place alveary hygroregulator (has a water tank + bucket slot) or any block exposing `FluidStorage.SIDED`.
3. Insert empty container in player hand; use item fluid transfer (or pipe mod) to fill from tank or empty from filled container.
4. Capsule: biomass/juice fill; glass/honey (hot) should refuse insert.
5. Bucket ↔ tank transfer should work for all nine `reforestry:bucket_*` items.

## Deferred

- Filled tint/overlay: **done** in `F-TINT` (`queries/f-tint.md`) — layered models + `reforestry:fluid_container` ItemTintSource, not the NeoForge loader.
- Container crafting recipes (tin can, wax capsule, refractory wax capsule) — F8/F9 datapack stage.
- Drink-from-container (short mead etc.) — not required for squeezer/bottler.

## F6 / F8 notes

- **F6 Smelter:** item-only; no dependency on F4c. No blockers from this stage.
- **F8 Squeezer / F9 Bottler:** can use `FluidsItems.CONTAINERS`, `FluidsItems.createFilled`, and `FluidStorage.ITEM` via `ContainerItemContext.find` — F4c unblocks these.

## DoD

- `./gradlew compileJava` — SUCCESS
- Containers accept/empty fluid via `FluidStorage.ITEM`
- Creative tab lists containers for smoke
