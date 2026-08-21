# Extra Trees ET4 — machines

**Date:** 2026-08-21  
**Status:** Implemented  
**Donor:** Binnie Extra Trees `ExtraTreeMachine` / `ModuleMachine` (no fabric.mod.json depends)

## Player exit

- Craft + place `lumbermill`, `press`, `brewery`, `distillery`
- Lumbermill: ET/CE/vanilla `#minecraft:logs` → crafting planks + 2 bark + 2 sawdust; needs water tank + FE
- Press / brewery / distillery: craftable, open GUI, tanks/slots present; **no fluid recipes until ET5**

## Machines

| Id | Tile | Playable recipes |
|---|---|---|
| `lumbermill` | `TileLumbermill` | Yes — crafting-lookup planks + byproducts |
| `press` | `TilePress` | Stub (ET5 fruit juices) |
| `brewery` | `TileBrewery` | Stub (ET5 alcohol) |
| `distillery` | `TileDistillery` | Stub (ET5 spirits) |

Never ported: designer / infuser / nursery.

## Misc items

| Id | Use |
|---|---|
| `proven_gear` | Crafted from impregnated sticks; lumbermill recipe ingredient |
| `sawdust` | Lumbermill byproduct; `#c:dusts/wood` |
| `bark` | Lumbermill byproduct |
| `wood_wax` | Item only — carpenter recipe needs turpentine (**ET5**) |

## Fabric notes

- Pattern mirrors factory `TilePowered` + gendustry machine block group
- Lumbermill resolves planks via `WorktableRecipeLookup` + `#minecraft:planks` (Binnie crafting fake-inv behavior)
- Energy: 900 FE / 30 ticks; water: 300 mB / cycle (Binnie drains 10 mB/tick × 30); tank 10000 mB
- Output tanks allow extract (match factory; Binnie `setReadOnly` is insert-only)
- GUI: Binnie `Lumbermill.png` shipped; Press/Brewery/Distillery GUI PNGs **missing from ACGaming-Binnie clone** — reused factory `still.png` stand-in (documented gap)
- Machine italic tooltips use Binnie `extratrees.machine.*.info` keys

## Gaps → ET5

- Press / brewery / distillery recipe managers + fluids (juices, alcohols, yeasts, grains)
- `wood_wax` carpenter recipes (turpentine / creosote)
- Optional JEI categories for lumbermill / kitchen machines
- True Binnie Press/Brewery/Distillery GUI art if recovered from another donor snapshot
