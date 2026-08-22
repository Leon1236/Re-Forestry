# ET-K — Bottle rack

**Date:** 2026-08-22  
**Status:** done

Binnie `bottleRack` was implemented in source but never shipped (`ModuleKitchen` TODO). Re-Forestry adds it as a standalone Extra Trees machine.

## Player

- Craft `reforestry:bottle_rack` (glass + iron + planks).
- 36 × 1000 mB fluid tanks in a 12×3 GUI grid.
- Accepts `reforestry:` namespace fluids (ET juices/alcohols).
- Pipette fill/drain per tank slot.

## Files

- `extratrees/tiles/TileBottleRack.java`
- `extratrees/gui/ContainerBottleRack.java`, `client/ScreenBottleRack.java`
- `ExtraTreeMachineType.BOTTLE_RACK`, tiles/menus/recipe/loot/advancement

## ET-D

See `queries/et-d-et-k-port.md` — designer/stained glass remains **deferred** (XL scope, ~988 textures + custom multipass rendering).
