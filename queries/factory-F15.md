# F15 — Machine circuits / sockets

Research/implementation date: 2026-07-25. CE reference: `thedarkcolour-ForestryCE` (`TilePowered`, `CircuitMachineUpgrade`, `DefaultForestryPlugin.registerCircuits`).

## What landed

- **API:** `IMachineUpgradable` on `TilePowered` — `speedMultiplier`, `powerMultiplier`, `outputMultiplier`; applied in `getTicksPerWorkCycle()`, `getEnergyPerWorkCycle()`, and machine output paths.
- **Sockets:** `SocketedPoweredTile` + `MachineSocketState` on **centrifuge, squeezer, smelter** only; `ContainerSocketedMachine` + `SlotCircuitSocket` at CE slot coords (79,37 / 75,20 / 95,21).
- **Items:** `circuit_board_*`, `electron_tube_*`, `soldering_iron`; soldering iron menu/screen.
- **Circuits:** `CircuitMachineUpgrade` registered in `ReforestryPlugin.registerCircuits` for layout `reforestry.machine.upgrade` / socket type `reforestry:machine`.
- **Carpenter smoke recipes:** `circuit_board_basic`, `electron_tube_blaze`, `soldering_iron`.

Other powered factory tiles (carpenter, fermenter, etc.) are unchanged — no socket slot.

## Smoke upgrade path

1. Carpenter (needs F10 machine + water tank):
   - `carpenter/circuit_board_basic.json` → empty basic board
   - `carpenter/soldering_iron.json` → soldering iron
   - Fabricator: `fabricator/electron_tubes/blaze.json` → 4× blaze tube (needs molten glass)
2. Right-click **soldering iron** → Machine Upgrade layout → insert 1× basic board + 1× blaze tube → **Speed Boost I** board (auto-assembles).
3. Place board in **centrifuge / squeezer / smelter** socket slot (top-left area of GUI).
4. Observe effects:

| Tube → board | Circuit id | Speed | Power draw | Output |
|---|---|---|---|---|
| Blaze | `reforestry.machine.speed.boost.1` | +12.5% | +5% | ×1 |
| Gold | `reforestry.machine.efficiency.1` | — | −10% | ×1 |
| Amber | `reforestry.machine.fortune.1` | — | +5% | ×1.25 product/remnant **chance** |

**Centrifuge:** fortune multiplies `ICentrifugeRecipe.getProducts(random, outputMult)`.

**Squeezer:** fortune multiplies remnant roll threshold (`remnantsChance × outputMult`).

**Smelter:** speed/power only (CE does not apply output mult to deterministic alloy output).

Remove board: carry **soldering iron**, click socket slot (damages tool).

## F16 blockers (hygroregulator — out of F15 scope)

- `IHygroregulatorRecipe` is registered on Factory module but consumed by **alveary hygroregulator** tile, not a socketed powered machine.
- F15 circuits/sockets intentionally limited to centrifuge, squeezer, smelter.
- F16 work = alveary hygro tile RecipeType migration + fluid recipes — separate from machine upgrade circuits.
