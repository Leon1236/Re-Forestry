# Machine / GUI pattern

Copy an existing factory machine. Do not invent a new tile/menu/screen shape.

Gold path: centrifuge (items + energy, no fluids) then still (fluids).

## File set (per machine)

| Piece | Example |
|---|---|
| Block subtype enum | `factory/blocks/BlockTypeFactoryPlain.java` |
| Block | `BlockFactoryPlain` via `FactoryBlocks.PLAIN` group |
| Tile | `factory/tiles/TileCentrifuge.java` |
| Menu | `factory/gui/ContainerCentrifuge.java` |
| Screen | `factory/client/ScreenCentrifuge.java` |
| Menu type | `FactoryMenuTypes` |
| Block entity type | `FactoryTiles` |
| Recipe type + serializer | `FactoryRecipeTypes` + `factory/recipes/CentrifugeRecipe.java` |
| JSON recipes | `data/reforestry/recipe/centrifuge/*.json` from `tools/extract_centrifuge_recipes.py` |
| JEI | `factory/compat/jei/CentrifugeRecipeCategory.java` + `FactoryJeiPlugin` |
| Client register | `FactoryClientHandler` (`MenuScreens` + BER) |

## Behavior bases

| Need | Extend / use |
|---|---|
| FE buffer + work cycle | `TilePowered` / `IPowerHandler` / `EnergyHelper` (Team Reborn `EnergyStorage`) |
| No power | `TileBase` (moistener) |
| Items in/out | `ContainerStorage` / `ItemStorage.SIDED` |
| Fluids | `MultiFluidTank` / `FilteredFluidStorage` / `FluidUnits` (mB ↔ droplets) — `queries/factory-fluids-fabric.md` |
| GUI tanks / pipette | Existing factory screen + `IToolPipette` |
| Sockets / circuits | `ISocketable` + `IMachineUpgradable` on centrifuge/squeezer/smelter |

## Menu networking (26.2 / Fabric)

`REGISTRY.menuType(name, ContainerX::fromNetwork, STREAM_CODEC)` — `ExtendedMenuType` + `StreamCodec`. Match an existing `FactoryMenuTypes` entry; do not bring back Forge `SimpleChannel`.

## Lookup in CE first

CE names: `TileCentrifuge`, `ContainerCentrifuge`, `GuiCentrifuge`, `BlockTypeFactoryTesr` / plain. Graph:

```bash
python3 tools/graphify_query.py CE "TileCentrifuge"
python3 tools/graphify_query.py --path CE TileCentrifuge TilePowered
python3 tools/graphify_query.py local "TileCentrifuge"
```

## Playable extras (do not skip if the machine is meant to be used)

- Crafting recipe for the block (usually sturdy casing + shaped)
- Loot table self-drop + `#minecraft:mineable/pickaxe`
- Hint keys + `data/reforestry/hints.properties` if other GUI machines have them
- Creative tab entry
- JEI category + catalyst + `JeiDescriptions` if factory JEI already covers siblings
