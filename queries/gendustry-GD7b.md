# GD7b — Industrial Apiary upgrades

**Date:** 2026-08-21  
**Donor:** `thedarkcolour-gendustry` (`IndustrialApiaryBeeModifier`, upgrade hooks on `IndustrialApiaryBlockEntity`)

## Shipped

- `IndustrialApiaryBeeModifier` — stack upgrades: energy cost sum; climate (heater/cooler/humidifier/dryer/nether); pollination/scrubber; lifespan; lighting; productivity; weatherproof; sieve; sky; stabilizer; territory; immutable; elite mutation / activity_simulator / productivity (+throttle) / territory / youth / fertility
- Energy: `BASE_ENERGY + Σ(upgrade.energyCost * count)` while bees can work
- Fertility: extra drones on queen death (`spawnDrones` × fertility count)
- Automation: next tick recycles princess/drone from output into queen/drone slots; blocks hopper pull of those while recycling
- Youth: `mutation -= 0.2f * count` (donor code); tooltip says mutation −20% (not lifespan +20% lie)
- Sieve: `onPollenRetrieved` → `TreePollenType.createStack` (donor `IPollen.createStack`)
- `IBeekeepingLogic.setWorkThrottle` + `BeekeepingLogic` instance throttle (elite productivity: `max(5, 550 - throttle)`)
- Queen death: `BeekeepingLogic` now calls `IBeeListener.onQueenDeath()` before clearing the queen stack

## Choices

- Modifier package-private in `gendustry.blockentity` (donor shape)
- Climate via `ClimateState` + `TemperatureType`/`HumidityType.up(steps)` (negative steps for cooler/dryer)
- Pollen via `TreePollenType.createStack` (same `pollen_fertile` id as alveary sieve)
- No donor `fabric.mod.json` depends
- Elite `MUTATION` adds `0.25f` once (not × count) — matches donor; energy still scales with stack

## Review (full GD7b)

- All upgrade switch effects + energy/max-stack tables match donor byte-for-byte
- Youth tooltip corrected; lifespan tooltip left as donor (“−20%”) despite aging `× (1 + 2×count)`
- Automation recycle + hopper block match donor
- **Must fix:** `Bee.spawnDrones` was a queen `copy()`; now CE-like mated offspring (`BeeMating`, pristine drones, empty if unmated / fertility < 1) so fertility elite works
- **Should fix:** sieve uses `TreePollenType.createStack`; alveary sieve aligned

## Gaps / next

- JEI + error sprites polish: **GD8**
- Owner ledger still missing on industrial apiary screen

## Player checks

1. Insert heater/cooler → climate ledger steps change; nether → hellish/arid
2. Productivity elite → faster work cycles (lower throttle); energy use rises with upgrades
3. Fertility elite → more drones when queen dies (offspring genomes, not queen clones)
4. Automation → princess/drone return to slots after death
5. Youth tooltip: mutation −20%; mutations rarer with stacks
6. Sieve + pollinating bees near trees → fertile pollen in output
