# OR3 — Habitat Locator (Wave 8 restore)

**Date:** 2026-08-22  
**Status:** done (MVP)

Restored 1.12 `habitat_locator` dropped in CE 1.21.1.

## Player

- Craft `reforestry:habitat_locator` (compass + bronze gears + emerald electron tube).
- Right-click → GUI: honey charge slot, bee specimen slot, analyzed bee output.
- Insert honey + bee → consumes 1 honey charge, copies bee to output, server searches suitable biomes (CE spiral, min 8-block biome patch).
- Client receives `reforestry:habitat_biome_pointer` payload with target `BlockPos`.

## Assets

- Item texture: `textures/item/biomefinder.png` (+ `.mcmeta`) from CE reference.
- GUI: `textures/gui/biomefinder.png`.

## Deferred polish

- Animated compass needle on item (CE `TextureHabitatLocator` was commented out even in CE).
- Tooltip distance/direction (needs client-only hook).
- JEI description already in lang (`for.jei.description.habitat_locator`).

## Files

- `apiculture/items/ItemHabitatLocator.java`, `HabitatLocatorLogic.java`
- `apiculture/inventory/ItemInventoryHabitatLocator.java`
- `apiculture/gui/ContainerHabitatLocator.java`, `client/ScreenHabitatLocator.java`
- `apiculture/network/HabitatBiomePointerPayload.java`
