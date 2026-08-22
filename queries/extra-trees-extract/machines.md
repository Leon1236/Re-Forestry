# Extra Trees machines (ET0)

Parsed from `ExtraTreeMachine` + `ModuleMachine`. Design module counted only to bound **ET-D**.

## Shipped (ET4)

Crafted in `ModuleMachine.doInit`: `lumbermill`, `press`, `brewery`, `distillery`.

| Enum | Id | Stage |
|---|---|---|
| Lumbermill | `lumbermill` | ET4 |
| Press | `press` | ET4 |
| BREWERY | `brewery` | ET4 |
| Distillery | `distillery` | ET4 |

## Stub / never shipped (not stages)

| Item | Why |
|---|---|
| Infuser | Java exists under `machines/infuser/` (`InfuserMachine` uid `infuser`) but **is not an `ExtraTreeMachine` constant**, so it was never registered. Wave 7: never. |
| Nursery | `ExtraTreeMachine.Nursery` supplier returns `null` with a TODO pointing at `PackageNursery`. Tile class exists. Wave 7: never. |

## ET-D deferred

Designer machines (gated on carpentry module): Woodworker, Panelworker, Glassworker.
Binnie `design` `EnumDesign` has **101** patterns — that is the size bound for ET-D (stained glass / multi-fence / carpentry). Do not start ET-D in Wave 7.

Carpentry crafts (`woodworker`, `panelworker`, `glassworker`) only run if the carpentry module is enabled.

## Optional ET-K

`ModuleKitchen.registerItemsAndBlocks` is a TODO; `blockKitchen = Blocks.AIR`. Bottle rack after ET5 if wanted.

## Other skips

- `ItemArboristDatabase` / `ItemMothDatabase` — Wave 7 skip (databases).
- Hops (`BlockHops` / `ItemHops`) belong with ET5 foods, not machines.

## Enum dump

| Enum | Bucket | Reason |
|---|---|---|
| Lumbermill | shipped | registered in ExtraTreeMachine and crafted in ModuleMachine.doInit |
| Woodworker | et-d | designer; Wave 7 ET-D deferred |
| Panelworker | et-d | designer; Wave 7 ET-D deferred |
| Nursery | never | enum member returns null (TODO); never shipped |
| Press | shipped | registered in ExtraTreeMachine and crafted in ModuleMachine.doInit |
| BREWERY | shipped | registered in ExtraTreeMachine and crafted in ModuleMachine.doInit |
| Distillery | shipped | registered in ExtraTreeMachine and crafted in ModuleMachine.doInit |
| Glassworker | et-d | designer; Wave 7 ET-D deferred |
