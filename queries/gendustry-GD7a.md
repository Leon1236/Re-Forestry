# GD7a — Industrial Apiary

**Date:** 2026-08-21  
**Donor:** `thedarkcolour-gendustry` (`IndustrialApiaryBlockEntity`, inventory, menu, screen)

## Shipped

- Block + item: `reforestry:industrial_apiary` (BE, menu, screen, craft, loot, pickaxe, textures, hints, lang)
- `IBeeHousing` + inventory (queen / drone / 4 upgrade / 9 output) + climate provider
- Beekeeping: **`new BeekeepingLogic(this)`** like `TileBeeHousing` (not donor hive-manager factory)
- Energy: `TilePowered(capacity, maxReceive)` = `(1000000, 100000)` — donor `ForestryEnergyStorage(100000, 1000000)` argument swap; `BASE_ENERGY = 200` FE/t while bees can work
- Sided FE + inventory; redstone disable + `NO_POWER`; owner set on place
- GUI `industrial_apiary.png` (donor gendustry `apiary.png`; forestry `apiary.png` kept separate); climate + power ledgers; progress bar
- Upgrade slots accept `reforestry:upgrades` (one type per slot); **modifiers are identity** until GD7b
- Creative tab icon = industrial apiary (donor order: first machine enum)

## Choices

- Custom server tick (not `TilePowered.doWork` work-cycle); bees burn FE then `beeLogic.doWork()`
- No upgrade climate / throttle / recycle / fertility yet (GD7b)
- Recipe tags: `c:glass_blocks`, `c:gears/bronze` (donor `forge:glass` / `forge:gears/bronze`)
- No donor `fabric.mod.json` depends

## Gaps / next

- Upgrade modifier behavior + energy cost from upgrades: **GD7b**
- `BeekeepingLogic.setWorkThrottle` does not exist yet — GD7b may need it for productivity elite
- JEI + error sprites polish: **GD8**
- Owner ledger (donor screen had it; Re-Forestry ScreenForestry has no owner ledger yet)

## Review (full GD7a)

- Confirmed `new BeekeepingLogic(this)` (not donor hive-manager factory)
- Energy ctor swap verified against donor `ForestryEnergyStorage(maxTransfer, capacity)` → `TilePowered(capacity, maxReceive)` = `(1000000, 100000)`; `BASE_ENERGY = 200`
- `IBeeHousing` environment checks match `TileBeeHousing` (sky/rain/light/climate/owner/FX coords)
- Redstone disable clears other errors so only `DISABLED_BY_REDSTONE` shows; GUI open re-applies that after `onGuiOpened`
- Client bee FX throttled every 10 ticks (TileBeeHousing cadence)
- Recipe unlock advancement added (donor had one; other gendustry machines still lack them)
- Upgrade modifiers remain identity until GD7b (intentional)

## Player checks

1. Craft or `/give @s reforestry:industrial_apiary`; place; open GUI
2. Supply FE; insert princess + drone → queen breeding; queen produces into output slots
3. Redstone signal disables; empty energy shows no-power error
4. Upgrade items fit upgrade slots but do not change climate/production yet
