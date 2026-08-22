# A1 — Simple materials

## Delivered
- Items: `gear_iron`, `brick_ash`, `brick_wax`, `brick_refractory_wax` on `CoreItems` (+ creative tab).
- Recipes producing them: `gear_iron`, carpenter `ash_brick`, `wax_bricks_ash` / `_sawdust`, `refractory_wax_bricks_ash` / `_sawdust`.
- Still craft restored to CE (uses `gear_iron`).

## Fabric tag mapping
| CE (Forge) | Re-Forestry |
|---|---|
| `forge:gears/iron` (+ parent `forge:gears`) | `c:gears/iron` (+ `c:gears` with bronze/copper/tin/iron) |
| `forge:nuggets/iron` | `c:nuggets/iron` → `minecraft:iron_nugget` |
| `forge:dusts/ash` | `c:dusts/ash` (existing) |
| `forge:sawdust` (`wood_pulp`) | `c:dusts/wood` → `reforestry:wood_pulp` |

## Skipped (outputs / deps unregistered)
- `ash_bricks`, `wax_bricks`, `refractory_wax_bricks` (and stairs/slabs/walls) — block families not ported.
- `analyzer` recipe — needs `analyzer` block + `portable_alyzer` (A7).
