# Factory play loop

Short end-to-end paths for Phase 6 machines. Power = any mod or debug block supplying ≥ machine receive rate (Team Reborn `EnergyStorage`).

## Bee products → resources

1. **Apiary** produces honey combs.
2. **Centrifuge** (`reforestry:centrifuge`) — insert comb in resource slot; powered → beeswax, honey drops, specialty drops (17 CE recipes). Optional **machine socket** board (F15): speed / efficiency / fortune on product chances.
3. **Squeezer** — honey drops → liquid honey in product tank; seeds → seed oil; full cans/capsules drain via container recipes.

## Biomass → ethanol

1. **Fermenter** — organic input (e.g. sugar cane) + **water** in resource tank + **compost/mulch/fertilizer** fuel slot → **biomass** in product tank (19 recipes).
2. **Still** — biomass in resource tank → **bio_ethanol** in product tank (**10 mB → 3 mB** per work cycle; matches JEI; see `queries/factory-F7.md`).
3. **Bottler** — fill empty **can/capsule/refractory** from machine tanks (viscosity-scaled FE); empty containers cost 0 FE. Auto-dumps fluid to adjacent tanks without power. JEI shows fill/empty recipes (`queries/factory-F9.md`).

## Carpenter / soil / weather

1. Craft **sturdy casing** (`reforestry:sturdy_machine`) from bronze → craft other factory blocks.
2. **Carpenter** — water + shaped grid + storage → bog earth, humus, impregnated casing, iodine/dissipation charges, hardened casing, etc. (16 safe CE carpenter recipes + F15 circuit recipes).
3. **Moistener** — wheat chain + water + light → mulch / mycelium (no FE). Hint ledger covers no-power / pipette / mossy products.
4. **Rainmaker** — right-click with iodine charge (clear → rain) or dissipation charge (rain → clear).

## Smelter / fabricator

1. **Smelter** — 3×3 alloy inputs (10 CE smelter recipes); product extracted from output slot only via hopper.
2. **Fabricator** — **fully tested** (reopen only on bug report). Smelt glass → liquid glass (heat + molten tank); craft tubes / flexible casing / fireproof wood with molten + shaped grid (~265 recipes); JEI category + recipes ledger.

## Fluid containers

- Craft **can** (tin), **capsule** (beeswax), **refractory** (refractory wax).
- Use with squeezer/bottler/fermenter/still/carpenter can slots and Fabric `FluidStorage`.

## Minimal smoke (creative / RCON)

| Step | Action |
|---|---|
| Power | `reforestry:debug_creative_energy` adjacent to machine |
| Combs | Centrifuge: any `reforestry:block_bee_comb_*` |
| Biomass chain | Fermenter: sugar cane + water + compost → still → bottler |
| Carpenter | Water tank + bog-earth pattern → 8× bog earth |
| Circuits | Carpenter → soldering iron → speed board → centrifuge socket |

## Hopper sidedness (code-level smoke notes)

All factory item inventories implement `WorldlyContainer`. Expected hopper behavior:

| Machine | Insert (any face) | Extract (any face) |
|---|---|---|
| Centrifuge | Resource slot (0) if valid | Product slots 1–9 only |
| Smelter | Input slots if valid | Output slot only |
| Squeezer | Inputs if valid | Remnant + can output |
| Bottler | Container slots if valid | Filled/emptied container outputs |
| Carpenter | Can input + storage rows | Product slot only |
| Fermenter | Resource/fuel/can if valid | Can output slot only |
| Moistener | Resource/stash if valid | Product; spent stash without fuel tag |
| Fabricator | Plan/craft/storage if valid | Result + smelting product per slot rules |

Fluids: powered machines expose `FluidStorage.SIDED` on block entity — use pipes or adjacent tanks, not hoppers.

## Not in this loop (deferred)

- **ModuleEnergy** engines (peat/biogas/combustion) — no FE generation in mod yet; use debug energy or external power.
- **JEI recipe transfer** packets — categories/catalysts/descriptions landed in F18; transfer is optional polish.
- **Recipe unlock advancements** — optional; pickaxe mineable + loot tables are done (`queries/factory-machine-gui-data-polish.md`).
- **Mail, farms** — CE carpenter recipes skipped (mail is out of scope; farm outputs unregistered).
